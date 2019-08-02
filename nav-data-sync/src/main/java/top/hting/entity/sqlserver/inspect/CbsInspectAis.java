package top.hting.entity.sqlserver.inspect;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AIS实体航标巡检记录
 */
@Data
@TableName("Cbs_Inspect_AIS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsInspectAis extends Base {


    @Excel(name = "pid")
    @TableId("pid")
    private String pid;

    @Excel(name = "cbsinspectlistfid")
    private String cbsinspectlistfid;

    @Excel(name = "basnavigationmarkfid")
    private String basnavigationmarkfid;

    @Excel(name = "marktable")
    private String marktable;

    @Excel(name = "markname")
    private String markname;

    @Excel(name = "inspecttime")
    private Date inspecttime;

    @Excel(name = "examiner")
    private String examiner;

    @Excel(name = "orgcode")
    private String orgcode;

    @Excel(name = "gpsstatuscode")
    private String gpsstatuscode;

    @Excel(name = "gpsrepaircode")
    private String gpsrepaircode;

    @Excel(name = "vhfstatuscode")
    private String vhfstatuscode;

    @Excel(name = "vhfrepaircode")
    private String vhfrepaircode;

    @Excel(name = "computerstatuscode")
    private String computerstatuscode;

    @Excel(name = "computerrepaircode")
    private String computerrepaircode;

    @Excel(name = "supplyvoltage")
    private BigDecimal supplyvoltage;

    @Excel(name = "supplypower")
    private BigDecimal supplypower;

    @Excel(name = "supplystatuscode")
    private String supplystatuscode;

    @Excel(name = "supplyrepaircode")
    private String supplyrepaircode;

    @Excel(name = "environmentstatuscode")
    private String environmentstatuscode;

    @Excel(name = "environmentrepaircode")
    private String environmentrepaircode;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "isfinished")
    private String isfinished;
}
