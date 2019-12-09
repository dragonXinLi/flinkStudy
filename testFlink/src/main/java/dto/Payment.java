package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Payment {

    private Long id;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 退款时使用。
     */
    private String originalOrderId;
    /**
     * 门店编码
     */
    private String storeId;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 支付方式
     */
    private String paymentMethod;
    /**
     * 支付金额
     */
    private BigDecimal amount;
    /**
     * 商户号
     */
    private String bankId;
    /**
     * 积分
     */
    private Integer point;
    /**
     * 积分兑换比率
     */
    private Double conversionRatio;
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
     * 会员号
     */
    private String memberId;
    /**
     * POS机IP
     */
    private String ip;
    /**
     * 支付授权码
     */
    private String authCode;

    private String openId;

    private String body;

    /**
     * 储值卡编号
     */
    private String cardNumber;
    /**
     * 储值卡密码
     */
    private String cardPassword;

    private BigDecimal changeAmount;
}
