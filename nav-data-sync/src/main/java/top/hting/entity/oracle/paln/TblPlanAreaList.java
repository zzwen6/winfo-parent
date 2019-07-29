package top.hting.entity.oracle.paln;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.OBase;

import java.io.Serializable;
import java.util.Date;

/**
 * 海区维护计划航标列表
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@TableName("Tbl_PlanAreaList")
public class TblPlanAreaList extends OBase implements Serializable {
    @TableId("planMarkId")
    @Excel(name = "planMarkId")
    private String planMarkId;

    @Excel(name = "planId")
    private String planId;

    @Excel(name = "manageOrgCode")
    private String manageOrgCode;

    @Excel(name = "maintainOrgCode")
    private String maintainOrgCode;

    @Excel(name = "markId")
    private String markId;

    @Excel(name = "markTableCode")
    private String markTableCode;

    @Excel(name = "markName")
    private String markName ;

    @Excel(name = "monthly")
    private Short monthly;

    @Excel(name = "maintainCode")
    private String maintainCode;

    @Excel(name = "requireShip")
    private Short requireShip;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "isFinished")
    private Short isFinished;

    @Excel(name = "endInspectDate")
    private Date endInspectDate;

    @Excel(name = "maintainId")
    private String maintainId;


    @Excel(name = "isEnable")
    private Integer isEnable;
}
