package top.hting.entity.oracle.paln;

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
    private String planMarkId;

    private String planId;

    private String markId;

    private String markTableCode;

    private String markName ;

    private Short monthly;

    private String maintainCode;

    private Short requireShip;

    private String remark;
}
