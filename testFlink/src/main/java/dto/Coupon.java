package dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 优惠券。
 */
@Data
public class Coupon {

    /**
     * ID。
     */
    private Long id;
    /**
     * 订单号（小票号）。
     */
    private String orderId;
    /**
     * 门店编码
     */
    private String storeId;
    /**
     * 优惠券编号。
     */
    private String couponId;
    /**
     * 优惠券名称。
     */
    private String couponName;
    /**
     * 优惠券类型。
     */
    private String couponType;
    /**
     * 优惠券折扣价格。
     */
    private BigDecimal discountPrice;
}
