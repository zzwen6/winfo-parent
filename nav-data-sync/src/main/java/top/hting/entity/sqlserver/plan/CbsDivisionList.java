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
@TableName("Cbs_Division_List")
public class CbsDivisionList extends Base implements Serializable {
    @TableId("pid")
    private String pid;
    private String cbsDivisionPlanFID;
    private String basNavigationMarkFID;
    private String markTable;
    private String markName;
    private String maintainCode;
    private Short requireShip;
    private Short monthly;
    private String remark;

}
