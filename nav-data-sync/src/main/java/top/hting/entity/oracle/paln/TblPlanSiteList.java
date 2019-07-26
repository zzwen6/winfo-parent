package top.hting.entity.oracle.paln;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Tbl_PlanSiteList")
public class TblPlanSiteList extends Base {
    @TableId("planMarkId")
    @Excel(name = "planMarkId")
    private String planMarkId;

    @Excel(name = "planId")
    private String planId;

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
}
