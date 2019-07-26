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
 * 航标巡检计划航标表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("Cbs_Monthly_List")
public class CbsMonthlyList extends Base {
    @TableId("pid")
    @Excel(name = "planMarkId")
    private String pid;

    @Excel(name = "cbsMonthlyPlanFID")
    private String cbsMonthlyPlanFID;

    @Excel(name = "basNavigationMarkFID")
    private String basNavigationMarkFID;

    @Excel(name = "markTable")
    private String markTable;

    @Excel(name = "markName")
    private String markName;

    @Excel(name = "inspectDate")
    private Date inspectDate;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "endInspectDate")
    private Date endInspectDate;


}
