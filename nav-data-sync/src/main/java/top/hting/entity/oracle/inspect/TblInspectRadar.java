package top.hting.entity.oracle.inspect;

import cn.afterturn.easypoi.excel.annotation.Excel;
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

    private String inspectId;

    private String planMarkId;

    private String inspectNumber;

    private Date inspectTime;

    private String examiner;

    private String shipId;

    private String orgCode;

    private String markId;

    private String markTableCode;

    private String markName ;

    private Short silentHour;

    private Short silentMinute;

    private Short silentSecond;

    private BigDecimal silentpPower;

    private Short silentHourN;

    private Short silentMinuteN;

    private Short silentSecondN;

    private BigDecimal silentpPowerN;

    private Short alertHour;

    private Short alertMinute;

    private Short alertSecond;

    private BigDecimal alertPower;

    private Short launchHour;

    private Short launchMinute;

    private Short launchSecond;

    private BigDecimal launchPower;

    private BigDecimal batteryVoltage;

    private String batteryCode;

    private String batteryDeal;

    private String identiferStatusCode;

    private String identiferRepairCode;

    private Integer period;

    private String remark;

    private String nodeNumber;


    @Excel(name = "isEnabled")
    private Integer isEnabled;

}
