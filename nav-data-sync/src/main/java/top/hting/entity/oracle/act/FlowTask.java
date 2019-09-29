package top.hting.entity.oracle.act;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.hting.entity.OBase;

import java.util.Date;

@Data
@TableName("TBL_FLOWTASK")
public class FlowTask extends OBase {
    private String taskId;

    private String instanceId;

    private String nodeNumber;

    private Integer serialNumber;

    private String taskName;

    private Date sendTime;

    private String sendUserId;

    private String sendUserName;

    private Date expireTime;

    private Short taskState;

}
