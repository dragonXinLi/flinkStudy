package batch;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;

public class WordCount {
	public static void main(String[] args) throws Exception {
		ExecutionEnvironment environment = ExecutionEnvironment.getExecutionEnvironment();
		environment.setParallelism(10);
//		final String JSON_PATH = "E:\\java\\idea\\user-behavior\\user-behavior\\upload-data-service\\src\\test\\java\\com\\purcotton\\data\\center\\pos\\api\\text.json";
		DataSource<String> source = environment.readTextFile("C:\\Users\\lilong\\Desktop\\22.txt");
		AggregateOperator<Tuple2<String, Integer>> sum = source.flatMap(new Tokenizer())
			.groupBy(0)
			.sum(1);
		sum.writeAsCsv("E:\\java\\idea\\flink\\testFlink\\111", "\n", " ").setParallelism(1);
		environment.execute();
	}
}
