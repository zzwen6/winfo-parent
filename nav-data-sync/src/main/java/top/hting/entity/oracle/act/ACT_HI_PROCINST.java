package top.hting.entity.oracle.act;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ACT_HI_PROCINST")
public class ACT_HI_PROCINST {
    @TableId("ID_")
    private String ID_;
    private String PROC_INST_ID_;
    private String BUSINESS_KEY_;
    private String PROC_DEF_ID_;
    private Date START_TIME_;
    private Date END_TIME_;
    private String DURATION_;
    private String START_USER_ID_;
    private String START_ACT_ID_;
    private String END_ACT_ID_;
    private String SUPER_PROCESS_INSTANCE_ID_;
    private String DELETE_REASON_;
    private String TENANT_ID_;
    private String NAME_;
}
