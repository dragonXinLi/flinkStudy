package dto;

/**
 * 订单（小票）的开票状态。
 */
public enum BillingStatus {

    /**
     * 未开票。
     */
    NOT_INVOICE,
    /**
     * 正在开票。
     */
    INVOICING,
    /**
     * 已开票。
     */
    INVOICED
}
