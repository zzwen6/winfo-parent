package top.hting.entity.oracle.act;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("ACT_HI_TASKINST")
public class ACTHITASKINST {
    @TableId("ID_")
    @Excel(name = "ID_")
    private String ID_;
    @Excel(name = "PROC_DEF_ID_")
    private String PROC_DEF_ID_;
    @Excel(name = "TASK_DEF_KEY_")
    private String TASK_DEF_KEY_;
    @Excel(name = "PROC_INST_ID_")
    private String PROC_INST_ID_;
    @Excel(name = "EXECUTION_ID_")
    private String EXECUTION_ID_;
    @Excel(name = "PARENT_TASK_ID_")
    private String PARENT_TASK_ID_;
    @Excel(name = "NAME_")
    private String NAME_;
    @Excel(name = "DESCRIPTION_")
    private String DESCRIPTION_;
    @Excel(name = "OWNER_")
    private String OWNER_;
    @Excel(name = "ASSIGNEE_")
    private String ASSIGNEE_;
    @Excel(name = "START_TIME_")
    private Timestamp START_TIME_;
    @Excel(name = "CLAIM_TIME_")
    private Timestamp CLAIM_TIME_;
    @Excel(name = "END_TIME_")
    private Timestamp END_TIME_;
    @Excel(name = "DURATION_")
    private Long DURATION_;
    @Excel(name = "DELETE_REASON_")
    private String DELETE_REASON_;
    @Excel(name = "PRIORITY_")
    private String PRIORITY_;
    @Excel(name = "DUE_DATE_")
    private String DUE_DATE_;
    @Excel(name = "FORM_KEY_")
    private String FORM_KEY_;
    @Excel(name = "CATEGORY_")
    private String CATEGORY_;
    @Excel(name = "TENANT_ID_")
    private String TENANT_ID_;
}
