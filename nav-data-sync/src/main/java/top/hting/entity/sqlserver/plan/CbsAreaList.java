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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("Cbs_Area_List")
public class CbsAreaList extends Base implements Serializable {
    @TableId("pid")
    private String pid;
    private String cbsAreaPlanFID;
    private String basNavigationMarkFID;
    private String markTable;
    private String markName;
    private String maintainCode;
    private short requireShip;
    private short monthly;
    private String remark;

}
