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

    private String planId;

    private String manageOrgCode;

    private String maintainOrgCode;

    private String markId;

    private String markTableCode;

    private String markName ;

    private Short monthly;

    private String maintainCode;

    private Short requireShip;

    private String remark;

    private Short isFinished;

    private Date endInspectDate;

    private String maintainId;


    @Excel(name = "isEnable")
    private Integer isEnable;
}
