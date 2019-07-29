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
    private String planId;

    private String planNumber;

    private Short annual;

    private String userId;

    private String userName;

    private Date makeDate;

    private String makeOrgCode;

    private String nodeNumber;


    @Excel(name = "isEnable")
    private Integer isEnable;

    @TableField(exist = false)
    @Excel(name = "isEnable")
    private String remark;

}
