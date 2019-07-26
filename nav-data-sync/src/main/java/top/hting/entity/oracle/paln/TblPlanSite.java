package top.hting.entity.oracle.paln;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.util.Date;

/**
 * 站点维护保养计划
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Tbl_PlanSite")
public class TblPlanSite extends Base {
    @TableId("planId")
    private String planId;

    private String planNumber;

    private Short annual;

    private String userId;

    private String userName;

    private Date makeDate;

    private String makeOrgCode;

    private String nodeNumber;
}
