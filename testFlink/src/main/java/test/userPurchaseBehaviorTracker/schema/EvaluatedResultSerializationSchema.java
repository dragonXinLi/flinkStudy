package test.userPurchaseBehaviorTracker.schema;

import com.alibaba.fastjson.JSON;
import test.userPurchaseBehaviorTracker.model.EvaluatedResult;
import org.apache.flink.streaming.util.serialization.KeyedSerializationSchema;

public class EvaluatedResultSerializationSchema implements KeyedSerializationSchema<EvaluatedResult> {
    @Override
    public byte[] serializeKey(EvaluatedResult element) {
        return element.getUserId().getBytes();
    }

    @Override
    public byte[] serializeValue(EvaluatedResult element) {
        return JSON.toJSONString(element).getBytes();
    }

    @Override
    public String getTargetTopic(EvaluatedResult element) {
        return null;
    }
}
