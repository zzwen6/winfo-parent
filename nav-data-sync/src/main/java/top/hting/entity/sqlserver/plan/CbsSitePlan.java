package top.hting.entity.sqlserver.plan;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.util.Date;

/**
 * 站点维护保养计划 Cbs_Site_Plan
 * @see CbsSiteList
 */
@Data
@TableName("Cbs_Site_Plan")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CbsSitePlan extends Base {

    @TableId(value = "pid")
    private String pid;

    private short annual;
    private String orgCode;
    private String makeUserId;
    private Date makeDate;

}
