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
 * 航标处维护保养计划
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Tbl_PlanDivision")
public class TblPlanDivision extends OBase implements Serializable {
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


}
