package state;

import Utils.DateUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import dto.DeliveryMessageDto;
import dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;

@Slf4j
public class MyMessageSchema implements DeserializationSchema<Order> {
    @Override
    public Order deserialize(byte[] bytes) {

        DeliveryMessageDto dto = null;
        Order order = null;
        String pullTime = DateUtils.currentTime();
        String errorMsg = "";
        String s = new String(bytes);

        try {
            Gson gson = new Gson();
            try {
                dto = gson.fromJson(s, DeliveryMessageDto.class);
                order = gson.fromJson(dto.getMessageBody(), Order.class);

                log.info("~~~~~~~~~~~~接收到topic为{}的消息!", "user-behavior-pos-storeOrder");
                log.info("~~~~~~~~~~~~原始消息：{}", s);
                log.info("~~~~~~~~~~~~消息key：{}", dto.getMessageId());

            } catch (JsonParseException var14) {
                log.error("~~~~~~消息解析异常，主题：{\"user-behavior-pos-storeOrder\"}，消息Key{}", dto.getMessageId());
                log.error("~~~~~~原始消息内容：{}", s);
                log.error("~~~~~~异常消息------------", var14);
            }
            if (null != dto) {
                return  order;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean isEndOfStream(Order order) {
        return false;
    }

    @Override
    public TypeInformation<Order> getProducedType() {
        return TypeExtractor.getForClass(Order.class);
    }
}
