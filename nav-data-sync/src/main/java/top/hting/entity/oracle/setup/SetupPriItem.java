package top.hting.entity.oracle.setup;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 许可项
 */
@Data
@TableName("TBL_SETPRIITEM")
public class SetupPriItem implements Serializable {
    @TableId("itemId")
    private String itemId;
    private String setupId;
    private String serialNumber;
    private String itemContent;
    private Date sysCreated;

    private String sysCreatedBy;

    private String sysOrg;

    private String sysDept;

    private Date sysLastUpd;

    private String sysLastUpdBy;


}
