package top.hting.entity.sqlserver.inspect;

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

@Data
@TableName("Cbs_Inspect_Radar_New")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsInpsectRadarNew extends Base implements Serializable {

    @TableId("pid")
    private String Pid;

    private String CbsInspectListFID;

    private String BasNavigationMarkFID;

    private String MarkTable;

    private String MarkName;

    private Date InspectTime;

    private String Examiner;

    private String OrgCode;

    private Short SilentHour;

    private Short SilentMinute;

    private Short SilentSecond;

    private BigDecimal SilentpPower;

    private Short SilentHourN;

    private Short SilentMinuteN;

    private Short SilentSecondN;

    private BigDecimal SilentpPowerN;

    private Short AlertHour;

    private Short AlertMinute;

    private Short AlertSecond;

    private BigDecimal AlertPower;

    private Short LaunchHour;

    private Short LaunchMinute;

    private Short LaunchSecond;

    private BigDecimal LaunchPower;

    private BigDecimal SupplyVoltage;

    private String LampVoltage;

    private String LampVoltageDeal;

    private String IdentiferStatusCode;

    private String IdentiferRepairCode;

    private String Remark;

    private Integer IsFinished;

    private Integer zhouqi;


}
