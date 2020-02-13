package window;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Date;
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




//		while (true) {

		String s = "{\"id\":\"a3ec593d703f4c149f12a442302def31\",\"originalSn\":\"JA119123100001\",\"orderType\":\"entry\",\"billTime\":\"Dec 31, 2019 11:10:21 AM\",\"warehouseCode\":\"DJ01\",\"warehouseName\":\"津梁生活龙华仓\",\"processStatus\":\"part_unprocessed\",\"remark\":\"单价更改，订单有抵扣\",\"revision\":0,\"createdBy\":\"1893\",\"createdTime\":\"Dec 31, 2019 11:10:21 AM\",\"updatedBy\":\"1893\",\"updatedTime\":\"Jan 6, 2020 9:58:31 AM\",\"supplierCode\":\"11109\",\"supplierName\":\"深圳德升贸易有限公司\",\"purchaseNum\":364,\"differNum\":104,\"businessDate\":\"Jan 6, 2020 9:58:27 AM\",\"purchaseordersenditems\":[{\"id\":\"6786839fc4d54ee497d11d4b65c6fde5\",\"purchaseId\":\"a3ec593d703f4c149f12a442302def31\",\"sn\":1,\"productId\":\"5484428430\",\"productCode\":\"J5500100041\",\"productBrandCode\":\"1001\",\"skuId\":\"5484428430001\",\"skuCode\":\"J5500100041001\",\"skuBarcode\":\"6946050100106\",\"skuName\":\"波路梦普奇软曲奇 巧巧脆可可56g\",\"skuColor\":\"\",\"skuSize\":\"规格:56g,口味:巧克力味\",\"unitType\":\"条\",\"marketPrice\":5.50,\"currencyType\":\"RMB\",\"purchasePrice\":2.20,\"purchaseNum\":72,\"entryNum\":72,\"differNum\":0,\"currentNum\":72,\"purchaseMarketAmount\":396.00,\"purchaseAmount\":158.40,\"planEntryDate\":\"Jan 2, 2020 12:00:00 AM\",\"createdBy\":\"\",\"createdTime\":\"Dec 31, 2019 11:10:21 AM\",\"updatedBy\":\"\",\"updatedTime\":\"Jan 6, 2020 9:58:31 AM\"},{\"id\":\"86cf3610358b427094e03304cd1c818c\",\"purchaseId\":\"a3ec593d703f4c149f12a442302def31\",\"sn\":3,\"productId\":\"5488977250\",\"productCode\":\"J5500100088\",\"productBrandCode\":\"1001\",\"skuId\":\"5488977250001\",\"skuCode\":\"J5500100088001\",\"skuBarcode\":\"6946050100144\",\"skuName\":\"波路梦普奇软曲奇 草莓奶油夹心47g\",\"skuColor\":\"\",\"skuSize\":\"草莓奶油夹心,47g\",\"unitType\":\"条\",\"marketPrice\":5.50,\"currencyType\":\"RMB\",\"purchasePrice\":2.20,\"purchaseNum\":72,\"entryNum\":72,\"differNum\":0,\"currentNum\":72,\"purchaseMarketAmount\":396.00,\"purchaseAmount\":158.40,\"planEntryDate\":\"Jan 2, 2020 12:00:00 AM\",\"createdBy\":\"\",\"createdTime\":\"Dec 31, 2019 11:10:21 AM\",\"updatedBy\":\"\",\"updatedTime\":\"Jan 6, 2020 9:58:31 AM\"},{\"id\":\"8c7257fba1df428ab4a16e7453f72a6a\",\"purchaseId\":\"a3ec593d703f4c149f12a442302def31\",\"sn\":6,\"productId\":\"5688428972\",\"productCode\":\"J5500100188\",\"productBrandCode\":\"1001\",\"skuId\":\"5688428972001\",\"skuCode\":\"J5500100188001\",\"skuBarcode\":\"6946050105644\",\"skuName\":\"波路梦蛋塔曲奇 芝士味 \",\"skuColor\":\"\",\"skuSize\":\",36g,芝士味\",\"unitType\":\"盒\",\"marketPrice\":5.50,\"currencyType\":\"RMB\",\"purchasePrice\":1.05,\"purchaseNum\":32,\"entryNum\":0,\"differNum\":32,\"currentNum\":0,\"purchaseMarketAmount\":176.00,\"purchaseAmount\":33.60,\"planEntryDate\":\"Jan 2, 2020 12:00:00 AM\",\"createdBy\":\"\",\"createdTime\":\"Dec 31, 2019 11:10:21 AM\",\"updatedBy\":\"\",\"updatedTime\":\"Dec 31, 2019 11:10:21 AM\"},{\"id\":\"92f9711b8f7a4379bd45370c26875fb5\",\"purchaseId\":\"a3ec593d703f4c149f12a442302def31\",\"sn\":5,\"productId\":\"5727306279\",\"productCode\":\"J5500100187\",\"productBrandCode\":\"1001\",\"skuId\":\"5727306279001\",\"skuCode\":\"J5500100187001\",\"skuBarcode\":\"6946050100427\",\"skuName\":\"波路梦普奇软曲奇 酸奶味\",\"skuColor\":\"\",\"skuSize\":\",56g,酸奶\",\"unitType\":\"条\",\"marketPrice\":5.50,\"currencyType\":\"RMB\",\"purchasePrice\":2.20,\"purchaseNum\":36,\"entryNum\":0,\"differNum\":36,\"currentNum\":0,\"purchaseMarketAmount\":198.00,\"purchaseAmount\":79.20,\"planEntryDate\":\"Jan 2, 2020 12:00:00 AM\",\"createdBy\":\"\",\"createdTime\":\"Dec 31, 2019 11:10:21 AM\",\"updatedBy\":\"\",\"updatedTime\":\"Dec 31, 2019 11:10:21 AM\"},{\"id\":\"a764d77bb5844e59aa1fd6fe27d4fcc7\",\"purchaseId\":\"a3ec593d703f4c149f12a442302def31\",\"sn\":2,\"productId\":\"5484428430\",\"productCode\":\"J5500100041\",\"productBrandCode\":\"1001\",\"skuId\":\"5484428430002\",\"skuCode\":\"J5500100041002\",\"skuBarcode\":\"6946050100113\",\"skuName\":\"波路梦普奇软曲奇 黄油曲奇56g\",\"skuColor\":\"\",\"skuSize\":\"规格:56g,口味:黄油味\",\"unitType\":\"条\",\"marketPrice\":5.50,\"currencyType\":\"RMB\",\"purchasePrice\":2.20,\"purchaseNum\":36,\"entryNum\":36,\"differNum\":0,\"currentNum\":36,\"purchaseMarketAmount\":198.00,\"purchaseAmount\":79.20,\"planEntryDate\":\"Jan 2, 2020 12:00:00 AM\",\"createdBy\":\"\",\"createdTime\":\"Dec 31, 2019 11:10:21 AM\",\"updatedBy\":\"\",\"updatedTime\":\"Jan 6, 2020 9:58:31 AM\"},{\"id\":\"cb2bb8b967cd42c0a7081b26c5559a5e\",\"purchaseId\":\"a3ec593d703f4c149f12a442302def31\",\"sn\":8,\"productId\":\"51215766320\",\"productCode\":\"J5500100190\",\"productBrandCode\":\"1001\",\"skuId\":\"51215766320001\",\"skuCode\":\"J5500100190001\",\"skuBarcode\":\"6946050105682\",\"skuName\":\"波路梦蛋塔曲奇 芒果味\",\"skuColor\":\"\",\"skuSize\":\",36g,芒果味\",\"unitType\":\"盒\",\"marketPrice\":5.50,\"currencyType\":\"RMB\",\"purchasePrice\":2.20,\"purchaseNum\":48,\"entryNum\":48,\"differNum\":0,\"currentNum\":48,\"purchaseMarketAmount\":264.00,\"purchaseAmount\":105.60,\"planEntryDate\":\"Jan 2, 2020 12:00:00 AM\",\"createdBy\":\"\",\"createdTime\":\"Dec 31, 2019 11:10:21 AM\",\"updatedBy\":\"\",\"updatedTime\":\"Jan 6, 2020 9:58:31 AM\"},{\"id\":\"e4f64a797ccc4c67bca8de04587ba5e4\",\"purchaseId\":\"a3ec593d703f4c149f12a442302def31\",\"sn\":7,\"productId\":\"5624265749\",\"productCode\":\"J5500100189\",\"productBrandCode\":\"1001\",\"skuId\":\"5624265749001\",\"skuCode\":\"J5500100189001\",\"skuBarcode\":\"6946050105668\",\"skuName\":\"波路梦蛋塔曲奇 抹茶味\",\"skuColor\":\"\",\"skuSize\":\",36g,抹茶味\",\"unitType\":\"盒\",\"marketPrice\":5.50,\"currencyType\":\"RMB\",\"purchasePrice\":2.20,\"purchaseNum\":32,\"entryNum\":32,\"differNum\":0,\"currentNum\":32,\"purchaseMarketAmount\":176.00,\"purchaseAmount\":70.40,\"planEntryDate\":\"Jan 2, 2020 12:00:00 AM\",\"createdBy\":\"\",\"createdTime\":\"Dec 31, 2019 11:10:21 AM\",\"updatedBy\":\"\",\"updatedTime\":\"Jan 6, 2020 9:58:31 AM\"},{\"id\":\"f33a8021ddc64a7eac95d62873845985\",\"purchaseId\":\"a3ec593d703f4c149f12a442302def31\",\"sn\":4,\"productId\":\"5442912298\",\"productCode\":\"J5500100089\",\"productBrandCode\":\"1001\",\"skuId\":\"5442912298001\",\"skuCode\":\"J5500100089001\",\"skuBarcode\":\"6946050100120\",\"skuName\":\"波路梦普奇软曲奇 牛奶曲奇57g\",\"skuColor\":\"\",\"skuSize\":\"牛奶味,57g\",\"unitType\":\"条\",\"marketPrice\":5.50,\"currencyType\":\"RMB\",\"purchasePrice\":2.20,\"purchaseNum\":36,\"entryNum\":0,\"differNum\":36,\"currentNum\":0,\"purchaseMarketAmount\":198.00,\"purchaseAmount\":79.20,\"planEntryDate\":\"Jan 2, 2020 12:00:00 AM\",\"createdBy\":\"\",\"createdTime\":\"Dec 31, 2019 11:10:21 AM\",\"updatedBy\":\"\",\"updatedTime\":\"Dec 31, 2019 11:10:21 AM\"}]}";

		Gson gson = new Gson();
		PurchaseOrderSendDto purchaseOrderSendDto = gson.fromJson(s, PurchaseOrderSendDto.class);
		purchaseOrderSendDto.setBusinessDate(new Date());
		purchaseOrderSendDto.setUpdatedTime(new Date());

		String s1 = gson.toJson(purchaseOrderSendDto);

		producer.send(new ProducerRecord<>("testaaaaaaaaaa1", s1), new Callback() {
				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					if (exception != null) {
						System.out.println("Failed to send message with exception " + exception);
					}
				}
			});
			Thread.sleep(1000);
//		}

		// 关闭producer
//		producer.close();
	}
}
