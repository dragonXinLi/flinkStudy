package dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class Order {

    /**
     * 小票号/退单号
     */
    private String id;
    /**
     * 门店编号
     */
    private String storeId;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 班次
     */
    private String workday;
    /**
     * 收银机编号
     */
    private String posId;
    /**
     * 收银员编号
     */
    private String cashierId;
    /**
     * 收银员名称
     */
    private String cashierName;
    /**
     * 小票状态
     */
    private OrderStatus status;
    /**
     * 支付状态
     */
    private PaymentStatus paymentStatus;
    /**
     * 物流状态
     */
    private LogisticsStatus logisticsStatus;
    /**
     * 订单（小票）的开票状态
     */
    private BillingStatus billingStatus;
    /**
     * 会员编号
     */
    private String memberId;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 会员手机号
     */
    private String memberMobile;
    /**
     * 会员品牌编码
     */
    private String memberBrandId;
    /**
     * 会员等级
     */
    private Integer memberLevel;
    /**
     * 会员名称
     */
    private String memberLevelName;
    /**
     * 使用促销活动
     */
    private Boolean joinedActivities;
    /**
     * 是否使用优惠券。true - 使用，false - 不使用
     */
    private Boolean usedCoupons;
    /**
     * 商品总数量
     */
    private Integer totalCount;
    /**
     * 订单总吊牌价格，ex. 399.00
     */
    private BigDecimal totalTagPrice;
    /**
     * 订单总折后价格, ex. 319.20
     */
    private BigDecimal totalDiscountPrice;
    /**
     * 订单销售价格, ex. 319.00
     */
    private BigDecimal totalPrice;
    /**
     * 抹零金额, ex. 0.20
     */
    private BigDecimal zeroAmount;
    /**
     * 总税费
     */
    private BigDecimal totalTax;
    /**
     * 实收金额
     */
    private BigDecimal paidInAmount;
    /**
     * 找零金额
     */
    private BigDecimal changeAmount;
    /**
     * 促销差异金额 = SUM(明细售价 * 数量) - 促销总价，此价格反应了无法除尽时的价格差异
     */
    private BigDecimal balance;
    /**
     * 订单的促销赠送积分
     */
    private Integer giftPoint;
    /**
     * 是否赠送优惠券
     */
    private Boolean sendCoupon;
    /**
     * 赠送的优惠券编码，用“,”分隔
     */
    private String giftCouponCodes;
    /**
     * 快递费用
     */
    private BigDecimal shippedFee;
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
     * 结账时间
     */
    private String paymentTime;
    /**
     * 微信支付的用户ID
     */
    private String openId;
    /**
     * 支付宝支付的用户ID
     */
    private String buyerLogonId;
    /**
     * 收件人
     */
    private String consignee;
    /**
     * 收件人手机号
     */
    private String consigneeMobile;
    /**
     * 备注
     */
    private String comment;
    /**
     * 邮政编码
     */
    private String postcode;
    /**
     * 省
     */
    private String province;
    /**
     * 省份编码
     */
    private String provinceCode;
    /**
     * 市
     */
    private String city;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 区
     */
    private String county;
    /**
     * 区域编码
     */
    private String countyCode;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 订单来源
     */
    private OrderSource source;
    /**
     * 原小票号。退单的原订单号不为空
     */
    private String originalOrderId;
    /**
     * 原小票的门店编号
     */
    private String originalStoreId;
    /**
     * 订单的已退商品数量（退单不存在此字段）
     */
    private Integer returnedQuantity;
    /**
     * 已经退款的金额（退单不存在此字段）
     */
    private BigDecimal refundedAmount;
    /**
     * 退货类型
     */
    private ReturnType returnType;
    /**
     * 订单的RFIDs是否处在锁定状态，默认为锁定状态
     */
    private Boolean locked;
    /**
     * 订单是否已经预占库存，只有正常销售的才需要预占库存。true - 已经预占，false - 未预占
     */
    private Boolean stockLocked;
    /**
     * 订单是否被删除
     */
    private Boolean deleted;
    /**
     * 订单商品列表
     */
    private List<Product> products ;
    /**
     * 订单支付信息列表
     */
    private List<Payment> payments;
    /**
     * 订单的促销活动列表
     */
    private List<Activity> activities ;
    /**
     * 订单的优惠券列表
     */
    private List<Coupon> coupons ;
    
    /**
     * 订单的退单列表
     */
    private List<Order> returnedOrders ;

    /**
     * 是否抹零
     */
    private Boolean round;

    private BigDecimal vipDiscount;
}
