package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private Long id;
    /**
     * 小票号/退单号
     */
    private String orderId;
    /**
     * 门店编号
     */
    private String storeId;
    /**
     * 商品品牌编码。
     */
    private String brandId;
    /**
     * 销售类型
     */
    private SaleType saleType;
    /**
     * 序号（行项目号）
     */
    private Integer sequence;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品图片url
     */
    private String imageUrl;
    /**
     * 规格
     */
    private String specification;
    /**
     * 来源于商品主数据
     */
    private String productNo;
    /**
     * 来源于商品主数据
     */
    private String productCode;
    /**
     * 来源于商品主数据
     */
    private String goodsNo;
    /**
     * 来源于商品主数据
     */
    private String goodsCode;
    /**
     * 商品条码
     */
    private String barcode;
    /**
     * 吊牌价
     */
    private BigDecimal tagPrice;
    /**
     * 商品原价
     *
     * @deprecated
     */
    private BigDecimal originalPrice;
    /**
     * 促销价
     */
    private BigDecimal salePrice;
    /**
     * 总售出价格，取自营销中心
     */
    private BigDecimal totalSellingPrice;
    /**
     * 售出价格，这个价格也作为退货价格
     */
    private BigDecimal sellingPrice;
    /**
     * 商品折扣
     */
    private BigDecimal discount;
    /**
     * 商品折扣率
     */
    private Double discountRate;
    /**
     * 销售数量/退货数量。销售 - 正数，退货 - 负数
     */
    private Integer count;
    /**
     * 促销差额 = 订单售价 - SUM(明细行商品售价 * 数量)
     */
    private BigDecimal balance;
    /**
     * 导购员编号
     */
    private String guideId;
    /**
     * 导购员名称
     */
    private String guideName;
    /**
     * 商品税费
     */
    private BigDecimal tax;
    /**
     * 射频识别码，使用“,”分隔
     */
    private String rfid;
    /**
     * 退货时的原明细行ID
     */
    private Long originalId;
    /**
     * 销售门店编号
     */
    private String saleStoreId;
    /**
     * 发货方编号
     */
    private String shipperId;
    /**
     * 发货方类型
     */
    private LocationType shipperType;
    /**
     * 收货方编号
     */
    private String receiverId;
    /**
     * 收货方类型
     */
    private LocationType receiverType;
    /**
     * 总促销赠送积分，取自营销中心
     */
    private Integer totalGiftPoint;
    /**
     * 商品的促销赠送积分
     */
    private Integer giftPoint;
    /**
     * 已退货数量
     */
    private int returnedQuantity;
    /**
     * 已经退款的金额（退单不存在此字段）
     */
    private BigDecimal refundedAmount;
    /**
     * 无货销售的已发货数量
     */
    private Integer deliveredQuantity;
    /**
     * 无货销售的已取消数量，一旦有任何一个数量发货就不可以取消
     */
    private Integer cancelledQuantity;
    /**
     * 可开票数量，初始值=销售数量
     */
    private Integer availableInvoiceQuantity;
    /**
     * 订单创建时间
     */
    private String createdTime;
    /**
     * 创建人（工号）
     */
    private String createdBy;
    /**
     * 订单最后更新时间
     */
    private String updatedTime;
    /**
     * 最后更新人（工号）
     */
    private String updatedBy;

    /**
     * 销售单位
     */
    private String unit;

    /**
     * 是否跨境购
     */
    private Integer haitao;

    /**
     * 是否为换货商品 0不是，1是
     */
    private Integer exchange;

    private BigDecimal vipDiscount;

}
