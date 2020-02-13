package window;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.evictors.TimeEvictor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.ContinuousProcessingTimeTrigger;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

@Slf4j
public class TestOrderMainTwo {
	public static void main(String[] args) throws Exception {
//		LogBackConfigLoader.load(TestOrderMain.class.getClassLoader().getResource("logback-spring.xml").getPath());

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(2);
		env.getConfig().disableSysoutLogging();
		env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000));
		env.enableCheckpointing(1000*60);
		env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
		env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);
		env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
		env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
		env.getCheckpointConfig().setFailOnCheckpointingErrors(false);
//		env.setStateBackend(new FsStateBackend("file:///D:/Download/flinkState/"));

		env.setBufferTimeout(5);
//		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "192.168.206.219:9092,192.168.206.16:9092,192.168.206.17:9092");
		properties.setProperty("group.id", "CID_ANALYSIS_STOCK_PURCHASE");

		FlinkKafkaConsumer<PurchaseOrderSendDto> consumer = new FlinkKafkaConsumer<>("testaaaaaaaaaa1", new MyMessageSchemaTwo(), properties);
		consumer.setCommitOffsetsOnCheckpoints(true);

		DataStreamSource<PurchaseOrderSendDto> source = env.addSource(consumer);

		source.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<PurchaseOrderSendDto>(Time.seconds(60)) {
			@Override
			public long extractTimestamp(PurchaseOrderSendDto element) {
				long time = element.getBusinessDate().getTime();
				return time;
			}
		}).flatMap(new PurchaseOrderFlatMapFunction())
			.keyBy("groupfield")
			.window(TumblingProcessingTimeWindows.of(Time.days(1),Time.hours(16)))
			.trigger(ContinuousProcessingTimeTrigger.of(Time.seconds(10)))
//			.trigger(new ProcessTimeTriggerTwo(1000*30))
			.evictor(TimeEvictor.of(Time.seconds(0),true))
			.reduce(new PurchaseOrderReduceFunction())
			.print();

		env.execute("TestOrderMain");
	}
}
