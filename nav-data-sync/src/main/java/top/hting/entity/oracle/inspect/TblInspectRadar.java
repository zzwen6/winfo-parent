package top.hting.entity.oracle.inspect;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.hting.entity.OBase;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 雷达应答器巡检记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("Tbl_InspectRadar")
public class TblInspectRadar extends OBase implements Serializable {

    @TableId("inspectId")
    @Excel(name = "inspectId")
    private String inspectId;

    @Excel(name = "planMarkId")
    private String planMarkId;

    @Excel(name = "inspectNumber")
    private String inspectNumber;

    @Excel(name = "inspectTime")
    private Date inspectTime;

    @Excel(name = "examiner")
    private String examiner;

    @Excel(name = "shipId")
    private String shipId;

    @Excel(name = "orgCode")
    private String orgCode;

    @Excel(name = "markId")
    private String markId;

    @Excel(name = "markTableCode")
    private String markTableCode;

    @Excel(name = "markName")
    private String markName ;

    @Excel(name = "silentHour")
    private Short silentHour;

    @Excel(name = "silentMinute")
    private Short silentMinute;

    @Excel(name = "silentSecond")
    private Short silentSecond;

    @Excel(name = "silentpPower")
    private BigDecimal silentpPower;

    @Excel(name = "silentHourN")
    private Short silentHourN;

    @Excel(name = "silentMinuteN")
    private Short silentMinuteN;

    @Excel(name = "silentSecondN")
    private Short silentSecondN;

    @Excel(name = "silentpPowerN")
    private BigDecimal silentpPowerN;

    @Excel(name = "alertHour")
    private Short alertHour;

    @Excel(name = "alertMinute")
    private Short alertMinute;

    @Excel(name = "alertSecond")
    private Short alertSecond;

    @Excel(name = "alertPower")
    private BigDecimal alertPower;

    @Excel(name = "launchHour")
    private Short launchHour;

    @Excel(name = "launchMinute")
    private Short launchMinute;

    @Excel(name = "launchSecond")
    private Short launchSecond;

    @Excel(name = "launchPower")
    private BigDecimal launchPower;

    @Excel(name = "batteryVoltage")
    private BigDecimal batteryVoltage;

    @Excel(name = "batteryCode")
    private String batteryCode;

    @Excel(name = "batteryDeal")
    private String batteryDeal;

    @Excel(name = "identiferStatusCode")
    private String identiferStatusCode;

    @Excel(name = "identiferRepairCode")
    private String identiferRepairCode;

    @Excel(name = "period")
    private Integer period;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "nodeNumber")
    private String nodeNumber;

    @Excel(name = "isEnabled")
    private Integer isEnabled;

}
