package top.hting.entity.sqlserver.plan;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("Cbs_Division_Plan")
public class CbsDivisionPlan extends Base implements Serializable {
    @TableId("pid")
    private String pid;
    private short annual;
    private String orgCode;
    private String makeUserId;
    private Date makeDate;

}
