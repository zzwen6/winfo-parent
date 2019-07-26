package top.hting.entity.sqlserver.plan;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Cbs_Site_List")
public class CbsSiteList extends Base implements Serializable {
    @TableId("pid")
    @Excel(name = "pid")
    private String pid;

    @Excel(name = "cbsSitePlanFID")
    private String cbsSitePlanFID;

    @Excel(name = "basNavigationMarkFID")
    private String basNavigationMarkFID;
    
    @Excel(name = "markTable")
    private String markTable;
    
    @Excel(name = "markName")
    private String markName;
    
    @Excel(name = "monthly")
    private short monthly;
    
    @Excel(name = "maintainCode")
    private String maintainCode;
    
    @Excel(name = "requireShip")
    private short requireShip;
    
    @Excel(name = "remark")
    private String remark;

}
