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
    @Excel(name = "pid")
    private String pid;

    @Excel(name = "annual")
    private short annual;
    
    @Excel(name = "orgCode")
    private String orgCode;
    
    @Excel(name = "makeUserId")
    private String makeUserId;
    
    @Excel(name = "makeDate")
    private Date makeDate;

}
