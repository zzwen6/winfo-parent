package top.hting.entity.oracle.dic;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


/**
 * 字典详细
 */
@Data
@ToString
@TableName("TBL_DICTIONARYDETAIL")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dictionarydetail implements Serializable {

    /**
     * 明细主键
     */
    @TableId("detailId")
    private String detailId;

    /**
     * 字典类型代码
     */
    private String dictCode;

    /**
     * 序号
     */
    private Integer serialNumber;

    /**
     * 字典项代码
     */
    private String itemCode;

    /**
     * 字典项名称
     */
    private String itemName;

    /**
     * 字典项简称
     */
    private String itemAbbreviation;

    /**
     * 字典项英文名称
     */
    private String itemNameEn;

    /**
     * 字典项英文简称
     */
    private String itemAbbreviationEn;

    /**
     * 父字典项代码
     */
    private String pItemCode;

    /**
     * 是否有效:0:否,1:是
     */
    private Short ifEnabled;

    /**
     * 创建时间
     */
    private Date sysCreated;

    /**
     * 创建人
     */
    private String sysCreatedBy;

    /**
     * 创建机构代码
     */
    private String sysOrg;

    /**
     * 创建部门代码
     */
    private String sysDept;

    /**
     * 最后修改时间
     */
    private Date sysLastupd;

    /**
     * 最后修改人
     */
    private String sysLastupdBy;

}
