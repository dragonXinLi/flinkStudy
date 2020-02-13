package test.demo10;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class TestFlatmap {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> input=env.fromElements(WORDS);

        DataStream<Tuple2> wordStream=input.flatMap(new FlatMapFunction<String, Tuple2>() {
			@Override
			public void flatMap(String value, Collector<Tuple2> out) throws Exception {
				String[] tokens = value.toLowerCase().split("\\W+");

				for (String token : tokens) {
					if (token.length() > 0) {
//                        out.collect(token);
						out.collect(new Tuple2(token,1));
					}
				}
			}
		});

        wordStream.keyBy(new KeySelector<Tuple2, Object>() {
			@Override
			public Object getKey(Tuple2 value) throws Exception {
				return value.f0;
			}
		});

        env.execute();
    }

    public static final String[] WORDS = new String[] {
            "To be, or not to be,--that is the question:--",
            "Whether 'tis nobler in the mind to suffer",
            "The slings and arrows of outrageous fortune",
            "And by opposing end them?--To die,--to sleep,--",
            "Be all my sins remember'd."
    };
}
