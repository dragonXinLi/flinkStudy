package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Activity {

    private Long id;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 门店编码
     */
    private String storeId;
    /**
     * 活动编号
     */
    private String activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动简称
     */
    private String activityShortName;
    /**
     * 活动折扣
     */
    private BigDecimal discountPrice;
}
