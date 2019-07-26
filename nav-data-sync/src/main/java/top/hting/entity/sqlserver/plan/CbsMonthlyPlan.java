package top.hting.entity.sqlserver.plan;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.util.Date;

/**
 * 航标站航标巡检计划
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Cbs_Monthly_Plan")
public class CbsMonthlyPlan extends Base {
    @TableId("pid")
    @Excel(name = "pid")
    private String pid;

    @Excel(name = "annual")
    private int annual;

    @Excel(name = "monthly")
    private int monthly;

    @Excel(name = "makeUserId")
    private String makeUserId;

    @Excel(name = "makeDate")
    private Date makeDate;

    @Excel(name = "makeOrgCode")
    private String makeOrgCode;


}
