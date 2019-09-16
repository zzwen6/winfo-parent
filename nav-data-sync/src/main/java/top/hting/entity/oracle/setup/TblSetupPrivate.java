package top.hting.entity.oracle.setup;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.OBase;

import java.io.Serializable;
import java.util.Date;

/**
 * 专用航标设置申请
 */
@Data
@TableName("TBL_SETUPPRIVATE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TblSetupPrivate extends OBase implements Serializable {

    @Excel(name = "setupid")
    @TableId("setupid")
    private String setupid;

    @Excel(name = "applynumber")
    private String applynumber;

    @Excel(name = "applyorgname")
    private String applyorgname;

    @Excel(name = "applytime")
    private Date applytime;

    @Excel(name = "applyusername")
    private String applyusername;

    @Excel(name = "acceptorgname")
    private String acceptorgname;

    @Excel(name = "orgcode")
    private String orgcode;

    @Excel(name = "projectname")
    private String projectname;

    @Excel(name = "explain")
    private String explain;

    @Excel(name = "designorgname")
    private String designorgname;

    @Excel(name = "constructorgname")
    private String constructorgname;

    @Excel(name = "maintainorgcode")
    private String maintainorgcode;

    @Excel(name = "maintainorgname")
    private String maintainorgname;

    @Excel(name = "setuptime")
    private Date setuptime;

    @Excel(name = "bookingperiod")
    private String bookingperiod;

    @Excel(name = "setuptype")
    private String setuptype;

    @Excel(name = "lifetime")
    private String lifetime;

    @Excel(name = "nodenumber")
    private String nodenumber;

    @Excel(name = "fliepath")
    private String fliepath;

    @TableField(exist = false)
    @Excel(name = "remark")
    private String remark;

    private String licenseNumber;

    private Date acceptDate;

    private Date approveDate;

    private String copyOrg;

}
