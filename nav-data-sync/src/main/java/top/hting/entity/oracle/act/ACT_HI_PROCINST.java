package top.hting.entity.oracle.act;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("ACT_HI_PROCINST")
public class ACT_HI_PROCINST {
    @TableId("ID_")
    @Excel(name = "ID_")
    private String ID_;
    @Excel(name = "PROC_INST_ID_")
    private String PROC_INST_ID_;
    @Excel(name = "BUSINESS_KEY_")
    private String BUSINESS_KEY_;
    @Excel(name = "PROC_DEF_ID_")
    private String PROC_DEF_ID_;
    @Excel(name = "START_TIME_")
    private Timestamp START_TIME_;
    @Excel(name = "END_TIME_")
    private Timestamp END_TIME_;
    @Excel(name = "DURATION_")
    private String DURATION_;
    @Excel(name = "START_USER_ID_")
    private String START_USER_ID_;
    @Excel(name = "START_ACT_ID_")
    private String START_ACT_ID_;
    @Excel(name = "END_ACT_ID_")
    private String END_ACT_ID_;
    @Excel(name = "SUPER_PROCESS_INSTANCE_ID_")
    private String SUPER_PROCESS_INSTANCE_ID_;
    @Excel(name = "DELETE_REASON_")
    private String DELETE_REASON_;
    @Excel(name = "TENANT_ID_")
    private String TENANT_ID_;
    @Excel(name = "NAME_")
    private String NAME_;
}
