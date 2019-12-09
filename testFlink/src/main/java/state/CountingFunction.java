package state;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.checkpoint.ListCheckpointed;

import java.util.Collections;
import java.util.List;

public class CountingFunction<Order> extends RichMapFunction<Order, Order> implements ListCheckpointed<Long> {
	private long count;


	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
	}

	@Override
	public void close() throws Exception {
		super.close();
	}

	@Override
    public List<Long> snapshotState(long checkpointId, long timestamp) {
        return Collections.singletonList(count);
    }

	@Override
    public void restoreState(List<Long> state) throws Exception {
        for (Long l : state) {
            count += l;
        }
    }

	@Override
	public Order map(Order value) throws Exception {
		count++;
		return value;
	}
}
