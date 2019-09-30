package top.hting.entity.oracle.act;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.hting.entity.OBase;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("TBL_FLOWTASK")
public class FlowTask extends OBase {
    private String taskId;

    private String instanceId;

    private String nodeNumber;

    private Integer serialNumber;

    private String taskName;

    private Timestamp sendTime;

    private String sendUserId;

    private String sendUserName;

    private Timestamp expireTime;

    private Short taskState;

}
