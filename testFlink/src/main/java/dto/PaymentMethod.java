package dto;

import lombok.Getter;

public enum PaymentMethod {
    /**
     * 现金。
     */
    CASH("CASH") {
        @Override
        public String description() {
            return "现金";
        }
    },
    /**
     * 银行卡
     */
    CARD("CARD") {
        @Override
        public String description() {
            return "银行卡";
        }
    },
    /**
     * 支付宝。
     */
    ALI_PAY("ALI_PAY") {
        @Override
        public String description() {
            return "支付宝";
        }
    },
    /**
     * 微信支付。
     */
    WECHAT_PAY("WECHAT_PAY") {
        @Override
        public String description() {
            return "微信";
        }
    },
    /**
     * 现金券。
     */
    CASH_COUPON("CASH_COUPON") {
        @Override
        public String description() {
            return "现金券";
        }
    },
    /**
     * 储值卡。
     */
    GIFT_CARD("GIFT_CARD") {
        @Override
        public String description() {
            return "储值卡";
        }
    },
    /**
     * 积分。
     */
    POINT("POINT") {
        @Override
        public String description() {
            return "积分";
        }
    },
    /**
     * 代收银
     */
    CASH2("CASH2") {
        @Override
        public String description() {
            return "代收银";
        }
    },
    /** 壹卡会*/
    YKH("YKH") {
        @Override
        public String description() {
            return "壹卡会";
        }
    },
    /** 业主购物卡*/
    YZGWK("YZGWK") {
        @Override
        public String description() {
            return "业主购物卡";
        }
    },
    /** 商场现金券*/
    SCXJQ("SCXJQ") {
        @Override
        public String description() {
            return "商场现金券";
        }
    },
    /** 代收银(线上) */
    DSY_XS("DSY_XS"){
        @Override
        public String description() {
            return "代收银(线上)";
        }
    };

    @Getter
    private String code;

    PaymentMethod(String code) {
        this.code = code;
    }

    /**
     * 返回支付方式描述。
     */
    public abstract String description();

    /**
     * 返回对应的PaymentMethod枚举对象。
     *
     * @param code payMode code
     * @return PaymentMethod枚举对象
     */
    public static PaymentMethod getPaymentMethod(String code) {
        if (code == null) {
            return null;
        }

        for (PaymentMethod value : values()) {
            if (code.equals(value.code)) {
                return value;
            }
        }
        return null;
    }
}
