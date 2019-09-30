package top.hting.entity.oracle.act;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("ACT_HI_ACTINST")
public class ActHiActInst {

    @TableId("ID_")
    @Excel(name = "ID_")
    private String ID_;
    @Excel(name = "PROC_DEF_ID_")
    private String PROC_DEF_ID_;
    @Excel(name = "PROC_INST_ID_")
    private String PROC_INST_ID_;
    @Excel(name = "EXECUTION_ID_")
    private String EXECUTION_ID_;
    @Excel(name = "ACT_ID_")
    private String ACT_ID_;
    @Excel(name = "TASK_ID_")
    private String TASK_ID_;
    @Excel(name = "CALL_PROC_INST_ID_")
    private String CALL_PROC_INST_ID_;
    @Excel(name = "ACT_NAME_")
    private String ACT_NAME_;
    @Excel(name = "ACT_TYPE_")
    private String ACT_TYPE_;
    @Excel(name = "ASSIGNEE_")
    private String ASSIGNEE_;
    @Excel(name = "START_TIME_")
    private Timestamp START_TIME_;
    @Excel(name = "END_TIME_")
    private Timestamp END_TIME_;
    @Excel(name = "DURATION_")
    private Long DURATION_;
    @Excel(name = "DELETE_REASON_")
    private String DELETE_REASON_;
    @Excel(name = "TENANT_ID_")
    private String TENANT_ID_;
}
