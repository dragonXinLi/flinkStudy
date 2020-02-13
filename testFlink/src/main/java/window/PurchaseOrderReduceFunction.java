package window;


import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.ReduceFunction;

@Slf4j
public class PurchaseOrderReduceFunction implements ReduceFunction<PurchaseOrderDto> {

	private static final long serialVersionUID = 2241152256836602452L;

	@Override
	public PurchaseOrderDto reduce(PurchaseOrderDto value1, PurchaseOrderDto value2){

		PurchaseOrderDto dto = value1.getUpdateTime().after(value2.getUpdateTime()) == true ? value1:value2;

		PurchaseOrderDto orderDto = new PurchaseOrderDto();
		orderDto.setWarehouseCode(value1.getWarehouseCode());
		orderDto.setWarehouseName(value1.getWarehouseName());
		orderDto.setGoodsNo(value1.getGoodsNo());
		orderDto.setGoodsCode(value1.getGoodsCode());
		orderDto.setGoodsName(value1.getGoodsName());
		orderDto.setMarketPrice(value1.getMarketPrice());
		orderDto.setPurchasePrice(value1.getPurchasePrice());
		orderDto.setSupplierCode(value1.getSupplierCode());
		orderDto.setSupplierName(value1.getSupplierName());
		orderDto.setEntryNum(value1.getEntryNum()+value2.getEntryNum());
		orderDto.setCurrencyType(dto.getCurrencyType());
		orderDto.setPurchaseAmount(value1.getPurchaseAmount()+value2.getPurchaseAmount());
		orderDto.setPurchaseNum(value1.getPurchaseNum()+value2.getPurchaseNum());
		orderDto.setPurchaseTotalAmount(value1.getPurchaseTotalAmount()+value2.getPurchaseTotalAmount());
		orderDto.setDay(value1.getDay());
		orderDto.setUpdateTime(dto.getUpdateTime());

//		log.info("reduce ==============PurchaseOrderReduceFunction =====" + dto );
		return orderDto;
	}
}
