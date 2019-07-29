package top.hting.entity.oracle.paln;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.OBase;

import java.util.Date;

/**
 * 海区维护计划
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@TableName("Tbl_PlanArea")
public class TblPlanArea extends OBase {
    @TableId("planId")
    @Excel(name = "planId")
    private String planId;

    @Excel(name = "planNumber")
    private String planNumber;

    @Excel(name = "annual")
    private Short annual;

    @Excel(name = "userId")
    private String userId;

    @Excel(name = "userName")
    private String userName;

    @Excel(name = "makeDate")
    private Date makeDate;

    @Excel(name = "makeOrgCode")
    private String makeOrgCode;

    @Excel(name = "nodeNumber")
    private String nodeNumber;


    @Excel(name = "isEnable")
    private Integer isEnable;

    @TableField(exist = false)
    @Excel(name = "isEnable")
    private String remark;

}
