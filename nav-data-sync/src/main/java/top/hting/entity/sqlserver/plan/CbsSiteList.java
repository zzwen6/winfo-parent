package top.hting.entity.sqlserver.plan;

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
    private String pid;

    private String cbsSitePlanFID;
    private String basNavigationMarkFID;
    private String markTable;
    private String markName;
    private short monthly;
    private String maintainCode;
    private short requireShip;
    private String remark;

}
