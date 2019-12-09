package test.demo10;

import org.apache.flink.api.common.functions.*;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class TestKeyBy {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Tuple3<String,String,Integer>> input=env.fromElements(TRANSCRIPT);
//        System.out.println("-----------"+input.getParallelism());
        //input.print();
		env.setParallelism(1);

		KeyedStream<Tuple3<String, String, Integer>, Tuple> keyedStream = input.keyBy("f0");
		keyedStream.map(new RichMapFunction<Tuple3<String, String, Integer>, Tuple3<String, String, Integer>>() {

			private transient ValueState<Integer> state;

			@Override
			public void open(Configuration parameters) throws Exception {
				ValueStateDescriptor<Integer> counter = new ValueStateDescriptor<>("counter", Integer.class, 0);
				state=getRuntimeContext().getState(counter);
			}

			@Override
			public Tuple3<String, String, Integer> map(Tuple3<String, String, Integer> value) throws Exception {
				Integer value1 = state.value();
				value1+=value.f2;
				state.update(value1);
				return new Tuple3<>(value.f0,value.f1,value1);
			}
		})

//		keyedStream.flatMap(new RichFlatMapFunction<Tuple3<String, String, Integer>, Tuple3<String, String, Integer>>() {
//
//			private transient ValueState<Integer> state;
//
//			@Override
//			public void open(Configuration parameters) throws Exception {
//				ValueStateDescriptor<Integer> counter = new ValueStateDescriptor<>("counter", Integer.class, 0);
//				state=getRuntimeContext().getState(counter);
//			}
//
//			@Override
//			public void flatMap(Tuple3<String, String, Integer> value, Collector<Tuple3<String, String, Integer>> out) throws Exception {
//				Integer value1 = state.value();
//				value1+=value.f2;
//				state.update(value1);
//				out.collect(new Tuple3<>(value.f0,value.f1,value1));
//			}
//		}).


			.print();
		env.execute();


//		.map(new MapFunction<Tuple3<String, String, Integer>, Tuple3<String, String, Integer>>() {
//			@Override
//			public Tuple3<String, String, Integer> map(Tuple3<String, String, Integer> value) throws Exception {
//				return value;
//			}
//		})

//		keyedStream.sum("f2").print();

//        KeyedStream<Tuple4<String,String,String,Integer>,String> keyedStream = input.keyBy(new KeySelector<Tuple4<String, String, String, Integer>, String>() {
//
//            @Override
//            public String getKey(Tuple4<String, String, String, Integer> value) throws Exception {
//                return value.f0;
//            }
//        });


//        keyedStream.process(new KeyedProcessFunction<Tuple, Tuple4<String,String,String,Integer>, Object>() {
//
//            @Override
//            public void processElement(Tuple4<String, String, String, Integer> value, Context ctx, Collector<Object> out) throws Exception {
//                System.out.println(ctx.getCurrentKey());
//            }
//        });
        //System.out.println("***********"+keyedStream.getParallelism());

//        System.out.println("---------444444---"+keyedStream.max(3).getParallelism());
//        keyedStream.maxBy("f2").print();
//		keyedStream.reduce(new RichReduceFunction<Tuple4<String, String, String, Integer>>() {
//			@Override
//			public Tuple4<String, String, String, Integer> reduce(Tuple4<String, String, String, Integer> value1, Tuple4<String, String, String, Integer> value2) throws Exception {
//				return value1.f3>value2.f3?value1:value2;
//			}
//		}).print();

//        SingleOutputStreamOperator<Tuple4<String,String,String,Integer>> sumed=keyed.min(3);
//
//        //使用了DataStreamUtils就不需要env.execute()
//        Iterator<Tuple4<String,String,String,Integer>> it=DataStreamUtils.collect(sumed);
//
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }

    }

    public static final Tuple3[] TRANSCRIPT = new Tuple3[] {
    	Tuple3.of("class1","张三",100),
		Tuple3.of("class2","赵六",81),
		Tuple3.of("class1","李四",78),
		Tuple3.of("class1","王五",99),
		Tuple3.of("class2","钱七",59),
		Tuple3.of("class2","马二",97)
    };
}
