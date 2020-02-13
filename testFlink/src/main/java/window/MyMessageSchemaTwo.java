package window;

import Utils.DateUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;

@Slf4j
public class MyMessageSchemaTwo implements DeserializationSchema<PurchaseOrderSendDto> {
    @Override
    public PurchaseOrderSendDto deserialize(byte[] bytes) {
		String s = new String(bytes);
		log.info("~~~~~~~~~~~~原始消息：{}", s);

		DeliveryMessageDto dto = null;
		String pullTime = DateUtils.currentTime();
		String errorMsg = "";

		Gson gson = new Gson();

		try {
//			dto = (DeliveryMessageDto)gson.fromJson(s, DeliveryMessageDto.class);

			PurchaseOrderSendDto dto2 = (PurchaseOrderSendDto)gson.fromJson(s, PurchaseOrderSendDto.class);
			return dto2;
		} catch (JsonParseException var14) {
			log.error("~~~~~~原始消息内容：{}", s);
		}

//		if (null != dto) {
//			if (log.isDebugEnabled()) {
//				log.debug("业务消息内容：{}", gson.toJson(dto));
//			}
//
//			PurchaseOrderSendDto dto2 = new Gson().fromJson(dto.getMessageBody(), PurchaseOrderSendDto.class);
//
//			return dto2;
//		}
        return null;
    }

    @Override
    public boolean isEndOfStream(PurchaseOrderSendDto string) {
        return false;
    }

    @Override
    public TypeInformation<PurchaseOrderSendDto> getProducedType() {
        return TypeExtractor.getForClass(PurchaseOrderSendDto.class);
    }
}
