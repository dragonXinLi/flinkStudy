package window;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

public class ProcessTimeTriggerTwo extends Trigger<PurchaseOrderDto, TimeWindow> {
	private static final long serialVersionUID = 8876825316333166858L;

//	private final ReducingStateDescriptor<Long> stateDesc =
//		new ReducingStateDescriptor<Long>("ptcls-time", (ReduceFunction<Long>) TypeInformation.of(new TypeHint<Long>() {
//		}), LongSerializer.INSTANCE);



	ValueStateDescriptor<Long> stateDesc = new ValueStateDescriptor<Long>(
	"sum",
	TypeInformation.of(new TypeHint<Long>() {}));

	private final long interval;

	public ProcessTimeTriggerTwo(long interval) {
		this.interval = interval;
	}

	@Override
	public TriggerResult onElement(PurchaseOrderDto element, long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {
		ValueState<Long> fireTimestamp = ctx.getPartitionedState(stateDesc);
		timestamp = ctx.getCurrentProcessingTime();

		if (fireTimestamp.value() == null) {
			long start = timestamp - (timestamp % interval);
			long nextFireTimestamp = start + interval;

			ctx.registerProcessingTimeTimer(nextFireTimestamp);

			fireTimestamp.update(nextFireTimestamp);
			return TriggerResult.CONTINUE;
		}
		return TriggerResult.CONTINUE;
	}

	@Override
	public TriggerResult onProcessingTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {

		ValueState<Long> fireTimestamp = ctx.getPartitionedState(stateDesc);

		if (fireTimestamp.value().equals(time)) {
			fireTimestamp.clear();
			fireTimestamp.update(time + interval);
			ctx.registerProcessingTimeTimer(time + interval);
			return TriggerResult.FIRE_AND_PURGE;
		} else if(window.maxTimestamp() == time) {
			return TriggerResult.FIRE;
		}
		return TriggerResult.CONTINUE;
	}

	@Override
	public TriggerResult onEventTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
		return null;
	}

	@Override
	public void clear(TimeWindow window, TriggerContext ctx) throws Exception {

	}
}
