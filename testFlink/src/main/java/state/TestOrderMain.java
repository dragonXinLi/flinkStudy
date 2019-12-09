package state;

import Utils.DateUtils;
import dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.*;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.Properties;

@Slf4j
public class TestOrderMain {
	public static void main(String[] args) throws Exception {
//		LogBackConfigLoader.load(TestOrderMain.class.getClassLoader().getResource("logback-spring.xml").getPath());

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		//Operate Sate，如果为多并行度，那么snapshotState方法会执行多并行度的次数
		env.setParallelism(2);
		env.getConfig().disableSysoutLogging();
		env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000));
		env.enableCheckpointing(1000*60);
		env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
		env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);
		env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
		env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
		env.getCheckpointConfig().setFailOnCheckpointingErrors(false);
		env.setStateBackend(new FsStateBackend("file:///D:/Download/flinkState/"));

		env.setBufferTimeout(5);
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
		properties.setProperty("group.id", "CID_ANALYSIS_POS_STOREORDER");

		FlinkKafkaConsumer<Order> consumer = new FlinkKafkaConsumer<>("user-behavior-pos-storeOrder2", new MyMessageSchema(), properties);
		consumer.setCommitOffsetsOnCheckpoints(true);
		env.addSource(consumer)
			.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<Order>() {
				long currentTimeStamp = 0;
				long maxDelayAllowed = 0;//延迟为0
				long currentWaterMark;

				@Override
				public long extractTimestamp(Order element, long previousElementTimestamp) {
					Date today = DateUtils.stringToDate(element.getPaymentTime(),"yyyy-MM-dd HH:mm:ss");
					long l = today.getTime();
					currentTimeStamp = Math.max(l, currentTimeStamp);
					log.info("Key:" + element.getId() + "，时间："+element.getPaymentTime()+",EventTime:" + l + ",水位线:" + currentWaterMark);
					return currentTimeStamp ;
				}

				@Nullable
				@Override
				public Watermark getCurrentWatermark() {
//						currentWaterMark = System.currentTimeMillis();
					currentWaterMark = currentTimeStamp - maxDelayAllowed;
					return new Watermark(currentWaterMark);
				}
			})
			.map(new CountingFunction())
			.process(new ProcessFunction<Order, Order>() {
			@Override
			public void processElement(Order o, Context context, Collector<Order> collector) throws Exception {
				collector.collect(o );
			}
		}).keyBy("id")
//			.window(ProcessingTimeSessionWindows.withGap(Time.seconds(5)))
//			.window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
//			.window(SlidingEventTimeWindows.of(Time.seconds(5),Time.seconds(2)))
//			.window(SlidingProcessingTimeWindows.of(Time.seconds(5),Time.seconds(2)))
//			.window(TumblingEventTimeWindows.of(Time.seconds(5)))
//			.window(TumblingEventTimeWindows.of(Time.seconds(5),Time.seconds(2)))
//			.window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
//			.window(TumblingProcessingTimeWindows.of(Time.seconds(5),Time.seconds(2)))


			.timeWindow(Time.seconds(5))
			.reduce(new ReduceFunction<Order>() {
				@Override
				public Order reduce(Order value1, Order value2) throws Exception {
//					if (value2.getId().equals("003")){
//						int a = 1/0;
//					}
					return value1;
				}
			}).addSink(new SinkFunction<Order>() {
			@Override
			public void invoke(Order value, Context context) throws Exception {
//				if (value.getId().equals("004")){
//					int a = 1/0;
//				}
				log.info(value.toString());
			}
		});
//			.print();
		env.execute("TestOrderMain");
	}
}
