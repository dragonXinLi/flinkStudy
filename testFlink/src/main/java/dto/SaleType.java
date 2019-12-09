package dto;

/**
 * 销售类型。
 */
public enum SaleType {
    /**
     * 正常销售。
     */
    NORMAL {
        @Override
        public int getSign() {
            return 1;
        }
    },
    /**
     * 无货寻源销售。
     */
    SOURCING {
        @Override
        public int getSign() {
            return 1;
        }
    },
    /**
     * 退货。
     */
    RETURN {
        @Override
        public int getSign() {
            return -1;
        }
    };

    public abstract int getSign();
}
