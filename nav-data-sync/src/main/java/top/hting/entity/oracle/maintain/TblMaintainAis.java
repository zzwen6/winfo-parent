package top.hting.entity.oracle.maintain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.hting.entity.OBase;

import java.util.Date;

/**
 * AIS实体航标维护保养
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("TBL_MAINTAINAIS")
public class TblMaintainAis extends OBase {

    @TableId("maintainId")
    @Excel(name = "maintainId")
    private String maintainId;

    @Excel(name = "planMarkId")
    private String planMarkId;

    @Excel(name = "inspectNumber")
    private String inspectNumber;

    @Excel(name = "inspectTime")
    private Date inspectTime;

    @Excel(name = "examiner")
    private String examiner;

    @Excel(name = "orgCode")
    private String orgCode;

    @Excel(name = "markId")
    private String markId;

    @Excel(name = "markTableCode")
    private String markTableCode;

    @Excel(name = "markName")
    private String markName ;

    @Excel(name = "maintain1")
    private Short maintain1;

    @Excel(name = "maintain2")
    private Short maintain2;

    @Excel(name = "maintain3")
    private Short maintain3;

    @Excel(name = "maintain4")
    private Short maintain4;

    @Excel(name = "maintain5")
    private Short maintain5;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "nodeNumber")
    private String nodeNumber;
}
