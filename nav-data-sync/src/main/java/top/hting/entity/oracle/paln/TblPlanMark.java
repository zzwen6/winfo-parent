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
 * 航标巡检计划[Tbl_PlanMark]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Tbl_PlanMark")
public class TblPlanMark extends OBase implements Serializable {
    @TableId("planId")
    @Excel(name = "planId")
    private String planId;

    @Excel(name = "planNumber")
    private String planNumber;

    @Excel(name = "annual")
    private Integer annual;

    @Excel(name = "monthly")
    private Integer monthly;

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


}
