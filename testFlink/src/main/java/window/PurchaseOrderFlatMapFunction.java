package window;

import Utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.util.Collector;

@Slf4j
public class PurchaseOrderFlatMapFunction extends RichFlatMapFunction<PurchaseOrderSendDto, PurchaseOrderDto> {

	private static final long serialVersionUID = -8986026892891359299L;


	@Override
	public void flatMap(PurchaseOrderSendDto value, Collector<PurchaseOrderDto> collector) throws Exception {
		try {

			String day = DateUtils.DateToString(value.getBusinessDate(), "yyyy-MM-dd");

			for (PurchaseOrderSendItemDto dto : value.getPurchaseordersenditems()) {
				if (dto.getCurrentNum() == 0){
					continue;
				}
				PurchaseOrderDto orderDto = new PurchaseOrderDto();
				orderDto.setWarehouseCode(value.getWarehouseCode());
				orderDto.setWarehouseName(value.getWarehouseName());
				orderDto.setGoodsNo(dto.getSkuId());
				orderDto.setGoodsCode(dto.getSkuCode());
				orderDto.setGoodsName(dto.getSkuName());
				orderDto.setMarketPrice(dto.getMarketPrice().doubleValue());
				orderDto.setPurchasePrice(dto.getPurchasePrice().doubleValue());
				orderDto.setSupplierCode(value.getSupplierCode());
				orderDto.setSupplierName(value.getSupplierName());
				orderDto.setCurrencyType(dto.getCurrencyType());
				orderDto.setDay(day);
				orderDto.setUpdateTime(dto.getUpdatedTime());
				orderDto.setBarcode(dto.getSkuBarcode());
//				orderDto.setProcessStatus(value.getProcessStatus());
				if ("entry".equals(value.getOrderType())){
					orderDto.setEntryNum(dto.getCurrentNum());
					orderDto.setPurchaseAmount(orderDto.getPurchasePrice()*orderDto.getEntryNum());

					orderDto.setPurchaseNum(dto.getPurchaseNum());
					orderDto.setPurchaseTotalAmount(dto.getPurchaseAmount().doubleValue());
				}else {
					orderDto.setEntryNum(-Math.abs(dto.getCurrentNum()));
					orderDto.setPurchaseAmount(-Math.abs(orderDto.getPurchasePrice()*orderDto.getEntryNum()));

					orderDto.setPurchaseNum(-Math.abs(dto.getPurchaseNum()));
					orderDto.setPurchaseTotalAmount(-Math.abs(dto.getPurchaseAmount().doubleValue()));
				}

				orderDto.setGroupfield("PurchaseOrderFlatMapFunction=="+orderDto.getWarehouseCode()
						+ orderDto.getGoodsNo()
						+ orderDto.getSupplierCode()
						+ orderDto.getDay());
				collector.collect(orderDto);
			}
		} catch (Exception e) {
			log.error("错误。。。。"+e);
		} finally {
		}
	}
}
