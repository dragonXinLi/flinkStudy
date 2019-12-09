package batch;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>> {
	Long count = 0L;
	@Override
	public void flatMap(String value, Collector<Tuple2<String, Integer>> collector) throws Exception {
		count++;
		System.out.println(count);
		// normalize and split the line
		String[] tokens = value.toLowerCase().split("\\W+");

		// emit the pairs
		for (String token : tokens) {
			if (token.length() > 0) {
				collector.collect(new Tuple2<String, Integer>(token, 1));
			}
		}
	}
}
