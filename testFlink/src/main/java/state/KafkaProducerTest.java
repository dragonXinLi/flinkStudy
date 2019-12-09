package state;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class KafkaProducerTest {
	public static void main(String[] args) throws InterruptedException {
		// 设置配置属性
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.206.219:9092,192.168.206.16:9092,192.168.206.17:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// 创建producer
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
		// 产生并发送消息

		while (true) {
			producer.send(new ProducerRecord<>("cj_test_2019_12_6_17_19", "dddd"), new Callback() {
				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					if (exception != null) {
						System.out.println("Failed to send message with exception " + exception);
					}
				}
			});
			Thread.sleep(1000);
		}

		// 关闭producer
//		producer.close();
	}
}
