package top.hting.entity.oracle.act;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ACT_HI_ACTINST")
public class ActHiActInst {

    @TableId("ID_")
    private String ID_;
    private String PROC_DEF_ID_;
    private String PROC_INST_ID_;
    private String EXECUTION_ID_;
    private String ACT_ID_;
    private String TASK_ID_;
    private String CALL_PROC_INST_ID_;
    private String ACT_NAME_;
    private String ACT_TYPE_;
    private String ASSIGNEE_;
    private String START_TIME_;
    private String END_TIME_;
    private String DURATION_;
    private String DELETE_REASON_;
    private String TENANT_ID_;
}
