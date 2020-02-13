package window;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
public class PurchaseOrderDto {
	private String groupfield;

	private String	warehouseCode;	//仓库编码
	private String	warehouseName;	//仓库名称
	private String	companyBrandNo;	//公司品牌no
	private String	companyBrandNameCn;	//公司品牌中文名称
	private String	companyBrandNameEn;	//公司品牌英文名称
	private String	goodsNo;	//商品编号
	private String	goodsCode;	//商品编码
	private String	goodsName;	//商品名称
	private Double	marketPrice;	//吊牌价
	private Double purchasePrice;//采购单价
	private String supplierName;//供应商名称
	private String supplierCode;//供应商编码
	private Integer entryNum;//收货数量
	private String currencyType;//币种
	private Double purchaseAmount;//收货金额

	private Integer purchaseNum;//采购数量
	private Double purchaseTotalAmount;//采购金额

	private String barcode;	//商品条码
	private String firstCategoryCode;	//运营分类编码
	private String firstCategoryName;//商品一级分类名称
	private String processStatus;//订单状态(unprocessed("未处理") part_unprocessed("部分处理")processed("已处理") invalid("已作废"))

	private String	day;	//日期（天数）
	private Date updateTime	;//更新时间
}
