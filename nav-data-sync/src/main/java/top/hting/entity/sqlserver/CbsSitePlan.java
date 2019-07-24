package top.hting.entity.sqlserver;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("Cbs_Site_Plan")
public class CbsSitePlan extends Base {

    @TableId(value = "pid")
    private String pid;

    private short annual;
    private String orgCode;
    private String makeUserId;
    private Date makeDate;

}
