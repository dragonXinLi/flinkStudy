package window;/**
 * [简要描述]:
 * [详细描述]:
 *
 * @since JDK 1.8
 */

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * [简要描述]:回传采购单bean文件
 * [详细描述]:
 * @author: 王鹏
 * @author 2162
 * @version 1.0, 2019/10/18 16:11
 * @since JDK 1.8
 */
@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class PurchaseOrderSendDto
{
    /** ID */
    private String id;

    /** 原始单号 */
    private String originalSn;

    /** 单据类型
     * @see PurchaseType */
    private String orderType;

    /** 凭证时间 */
    private Date billTime;


    /** 仓库编码 */
    private String warehouseCode;

    /** 仓库名称 */
    private String warehouseName;

    /** com.purcotton.omni.warehouse.entity.Purchase.PurchaseStatus 内部枚举类(处理状态) */
    private String processStatus;


    /** 备注 */
    private String remark;

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

    /** 供应商编码 */
    private String supplierCode;

    /** 供应商名称 */
    private String supplierName;

    /** 订货数量 */
    private Integer purchaseNum;

    /** 交货数量 */
    private Integer entryNum;

    /** 仍要交货 */
    private Integer differNum;

    /** 业务日期 */
    private Date businessDate;

    List<PurchaseOrderSendItemDto> purchaseordersenditems;

    /** 订单类型 {entry=采购入库, delivery=采购退货} */
    public enum PurchaseType
    {
        /**
         * 采购入库
         */
        entry("采购入库"),
        /**
         * 采购退货
         */
        delivery("采购退货");

        private final String desc;

        PurchaseType(String desc)
        {
            this.desc = desc;
        }
    }
}
