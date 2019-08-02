package top.hting.entity.sqlserver.inspect;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * CBS  AIS中心巡检记录
 *
 * 没有数据，可以不进行同步
 */

@Data
@TableName("Cbs_Inspect_Center")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Deprecated

public class CbsInspectCenter extends Base implements Serializable {


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

    @Excel(name = "ntc")
    private String ntc;

    @Excel(name = "automachron")
    private String automachron;

    @Excel(name = "gpsdatetime")
    private Date gpsdatetime;

    @Excel(name = "ntc100")
    private Date ntc100;

    @Excel(name = "servertime")
    private Date servertime;

    @Excel(name = "upsinvoltagestatuscode1")
    private String upsinvoltagestatuscode1;

    @Excel(name = "upsinvoltage1")
    private BigDecimal upsinvoltage1;

    @Excel(name = "upsoutvoltagestatuscode1")
    private String upsoutvoltagestatuscode1;

    @Excel(name = "upsoutvoltage1")
    private BigDecimal upsoutvoltage1;

    @Excel(name = "upseffectivecode1")
    private String upseffectivecode1;

    @Excel(name = "upsload1")
    private String upsload1;

    @Excel(name = "upssupply1")
    private BigDecimal upssupply1;

    @Excel(name = "upsbattery1")
    private BigDecimal upsbattery1;

    @Excel(name = "upsinvoltagestatuscode2")
    private String upsinvoltagestatuscode2;

    @Excel(name = "upsinvoltage2")
    private BigDecimal upsinvoltage2;

    @Excel(name = "upsoutvoltagestatuscode2")
    private String upsoutvoltagestatuscode2;

    @Excel(name = "upsoutvoltage2")
    private BigDecimal upsoutvoltage2;

    @Excel(name = "upseffectivecode2")
    private String upseffectivecode2;

    @Excel(name = "upsload2")
    private String upsload2;

    @Excel(name = "upssupply2")
    private BigDecimal upssupply2;

    @Excel(name = "upsbattery2")
    private BigDecimal upsbattery2;

    @Excel(name = "roomtempstatuscode")
    private String roomtempstatuscode;

    @Excel(name = "roomtemp")
    private BigDecimal roomtemp;

    @Excel(name = "roomhumiditystatuscode")
    private String roomhumiditystatuscode;

    @Excel(name = "roomhumidity")
    private BigDecimal roomhumidity;

    @Excel(name = "roomduststatuscode")
    private String roomduststatuscode;

    @Excel(name = "linestatuscode")
    private String linestatuscode;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "isfinished")
    private Integer isfinished;


}
