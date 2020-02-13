package state.demo01;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.checkpoint.ListCheckpointed;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer09;
import org.apache.flink.types.ListValue;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TopN {
	private static final Logger LOG = LoggerFactory.getLogger(TopN.class);
	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		//Operate Sate，如果为多并行度，那么snapshotState方法会执行多并行度的次数
		env.setParallelism(1);
		env.getConfig().disableSysoutLogging();
		env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(1, 10000));
		env.enableCheckpointing(1000*60);
		env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
		env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);
		env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
		env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
		env.getCheckpointConfig().setFailOnCheckpointingErrors(false);
		env.setStateBackend(new FsStateBackend("file:///D:/Download/flinkState/"));

		env.setBufferTimeout(5);
//		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		DataStream<String> text = env.socketTextStream("localhost", 9000, "\n");

		// parse the data, group it, window it, and aggregate the counts
		DataStream<WordWithCount> windowCounts = text
		.flatMap(new RichFlatCheck());
//			.flatMap(new RichFlatMapFunction<String, WordWithCount>() {
//
////				private transient ValueState<Tuple2<String,Long>> sum;
////				private transient ValueState<Long> count;
//
//				private transient ListState<String> sum;
//				private List<String> bufferSum;
//
//				@Override
//				public void flatMap(String value, Collector<WordWithCount> out) throws IOException {
//					bufferSum.add(value);
//					for (String word : value.split("\\s")) {
//						out.collect(new WordWithCount(word, 1L));
//					}
//				}
//
//				@Override
//				public void open(Configuration parameters) throws Exception {
//
//					ListStateDescriptor descriptor = new ListStateDescriptor<String>(
//						"sum",
//						TypeInformation.of(new TypeHint<String>() {})
//					);
//					sum = getRuntimeContext().getListState(descriptor);
//				}
//			} );
//			.keyBy("word")
//		.flatMap(new RichFlatMapFunction<WordWithCount, WordWithCount>() {
//
//			private transient ValueState<Tuple2<String,Long>> sum;
//			private transient ValueState<Long> count;
//
//			@Override
//			public void flatMap(WordWithCount value, Collector<WordWithCount> out) throws Exception {
//				Tuple2<String, Long> currentSum = sum.value();
//				currentSum.f1 += value.count;
//				currentSum.f0 = value.word;
//
//				Long value1 = count.value();
//				value1 += 1;
//
//				sum.update(currentSum);
//				count.update(value1);
//
//				if (currentSum.f1 >= 2){
//					out.collect(new WordWithCount(value.word,currentSum.f1));
//					sum.clear();
////					count.clear();
//				}
//
//				if (value1 >=3){
//					int a= 1/0;
//				}
//			}
//
//			@Override
//			public void open(Configuration parameters) throws Exception {
//
//				ValueStateDescriptor<Tuple2<String,Long>> descriptor = new ValueStateDescriptor<Tuple2<String, Long>>(
//					"sum",
//					TypeInformation.of(new TypeHint<Tuple2<String, Long>>() {}),
//					new Tuple2<>("",0L)
//				);
//				sum = getRuntimeContext().getState(descriptor);
//
//				ValueStateDescriptor<Long> descriptor1 = new ValueStateDescriptor<>(
//					"count",
//					TypeInformation.of(new TypeHint<Long>() {
//					}),
//					0L
//				);
//
//				count = getRuntimeContext().getState(descriptor1);
//			}
//		});

		windowCounts.print();

		env.execute("Socket Window WordCount");
	}


	public static class WordWithCount {

		public String word;
		public long count;

		public WordWithCount() {}

		public WordWithCount(String word, long count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public String toString() {
			return word + " : " + count;
		}
	}

	public static class RichFlatCheck extends RichFlatMapFunction<String, WordWithCount> implements ListCheckpointed<String>{

		private transient ListState<String> sum;
		private List<String> bufferSum;

		public RichFlatCheck(){
			this.bufferSum = new ArrayList<>();
		}

		@Override
		public void flatMap(String value, Collector<WordWithCount> out) throws IOException {
			bufferSum.add(value);
			for (String word : value.split("\\s")) {
				out.collect(new WordWithCount(word, 1L));
			}
		}

		@Override
		public void open(Configuration parameters) throws Exception {


		}

		@Override
		public List snapshotState(long checkpointId, long timestamp) throws Exception {
			return null;
		}

		@Override
		public void restoreState(List state) throws Exception {
			ListStateDescriptor descriptor = new ListStateDescriptor<String>(
				"sum",
				TypeInformation.of(new TypeHint<String>() {})
			);
			sum = getRuntimeContext().getListState(descriptor);
		}
	}
}
