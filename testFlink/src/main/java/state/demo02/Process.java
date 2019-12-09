package state.demo02;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

public class Process {
	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();

		env.setParallelism(1);

		DataStream<Long> input=env.generateSequence(0,10).process(new ProcessFunction<Long, Long>() {
			@Override
			public void processElement(Long value, Context ctx, Collector<Long> out) throws Exception {
				out.collect(value);

				//仅在键控流上支持设置计时器
//				ctx.timerService().registerProcessingTimeTimer(1000);
			}


			@Override
			public void open(Configuration parameters) throws Exception {
				super.open(parameters);


			}
		});

		DataStream plusOne=input.map(new MapFunction<Long, Long>() {

			@Override
			public Long map(Long value) throws Exception {
				System.out.println("源数据：--------------------"+value);
				return value+1;
			}
		});

		plusOne.print();

		env.execute();
	}
}
