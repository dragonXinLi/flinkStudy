package window;/**
 * [简要描述]:
 * [详细描述]:
 *
 * @since JDK 1.8
 */

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [简要描述]采购订单明细bean:
 * [详细描述]:
 * @author: 王鹏
 * @author 2162
 * @version 1.0, 2019/10/18 17:26
 * @since JDK 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseOrderSendItemDto
{
    /** ID */
    private String id;

    /** 采购单ID */
    private String purchaseId;

    /** 行项目号 序号(同单内有序不重复) */
    private Integer sn;

    /** 商品ID 来自主数据 */
    private String productId;

    /** 商品编码 来自主数据 */
    private String productCode;

    /** 商品品牌 来自主数据 */
    private String productBrandCode;

    /** SKUID 来自主数据 */
    private String skuId;

    /** SKU编码 来自主数据 */
    private String skuCode;

    /** 商品条码 来自主数据 */
    private String skuBarcode;

    /** 商品名称 来自主数据 */
    private String skuName;

    /** 商品颜色 来自主数据 */
    private String skuColor;

    /** 商品规格 来自主数据 */
    private String skuSize;

    /** 单位 来自商品主数据,例如:包,件 */
    private String unitType;

    /** 吊牌价 来自主数据 */
    private BigDecimal marketPrice;

    /** com.purcotton.omni.stock.enums.CurrencyType 货币 全局枚举#CurrencyType#参考t_field_dictionary表tname=common以及fname=CURRENCY_TYPE 示例值:RMB */
    private String currencyType;

    /** 采购价格 */
    private BigDecimal purchasePrice;

    /** 采购数量 */
    private Integer purchaseNum;

    /** 入库数量 */
    private Integer entryNum;

    /** 差异数量(仍要交货) */
    private Integer differNum;

    /** 当前出入采购数量 */
    private Integer currentNum;

    /** 采购吊牌金额 吊牌金额*采购数量 */
    private BigDecimal purchaseMarketAmount;

    /** 采购金额 采购金额*采购数量 */
    private BigDecimal purchaseAmount;

    /** 计划交期 页面查询条件 */
    private Date planEntryDate;

    /** 删除 未生成出入库单之前可以勾选删除,非数据库逻辑删除的意义 */
    private Integer isRemove;

    /** 乐观锁 */
    private Integer revision;

    /** 创建人 */
    private String createdBy;

    /** 创建时间 */
    private Date createdTime;

    /** 更新人 */
    private String updatedBy;

    /** 更新时间 */
    private Date updatedTime;
}
