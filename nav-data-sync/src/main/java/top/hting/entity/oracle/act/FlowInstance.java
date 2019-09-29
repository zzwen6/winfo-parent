package top.hting.entity.oracle.act;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.hting.entity.OBase;

@TableName("TBL_FLOWINSTANCE")
@Data
public class FlowInstance extends OBase {

    /**
     * 实例主键
     */
    private String instanceid;

    /**
     * 流程编号
     */
    private String flownumber;

    /**
     * 关联业务主键
     */
    private String businessid;

    /**
     * 当前任务主键
     */
    private String taskid;

    /**
     * 父实例主键
     */
    private String pinstanceid;

    /**
     * 参数1
     */
    private String param1;

    /**
     * 参数2
     */
    private String param2;
}
