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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("Tbl_InspectFixed")
public class TblInspectFixed extends OBase implements Serializable {

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

    @Excel(name = "lightingCode")
    private String lightingCode;

    @Excel(name = "lightingDeal")
    private String lightingDeal;

    @Excel(name = "lightingBreakReasonCode")
    private String lightingBreakReasonCode;

    @Excel(name = "lightingWorkVoltage")
    private BigDecimal lightingWorkVoltage;

    @Excel(name = "lightingWorkCurrent")
    private BigDecimal lightingWorkCurrent;

    @Excel(name = "lightCode")
    private String lightCode;

    @Excel(name = "lightDeal")
    private String lightDeal;

    @Excel(name = "lightQualityCode")
    private String lightQualityCode;

    @Excel(name = "lightQualityDeal")
    private String lightQualityDeal;

    @Excel(name = "solarValveCode")
    private String solarValveCode;

    @Excel(name = "solarValveDeal")
    private String solarValveDeal;

    @Excel(name = "bulbGroup")
    private String bulbGroup;

    @Excel(name = "bulbGroupDeal")
    private String bulbGroupDeal;

    @Excel(name = "batteryCode")
    private String batteryCode;

    @Excel(name = "batteryDeal")
    private String batteryDeal;

    @Excel(name = "batteryVoltage")
    private BigDecimal batteryVoltage;

    @Excel(name = "batteryVoltageDeal")
    private String batteryVoltageDeal;

    @Excel(name = "solarPanelCode")
    private String solarPanelCode;

    @Excel(name = "solarPanelDeal")
    private String solarPanelDeal;

    @Excel(name = "chargeCurrent")
    private BigDecimal chargeCurrent;

    @Excel(name = "chargeCurrentDeal")
    private String chargeCurrentDeal;

    @Excel(name = "lighthouseFirmCode")
    private String lighthouseFirmCode;

    @Excel(name = "lighthouseFirmDeal")
    private String lighthouseFirmDeal;

    @Excel(name = "lighthouseSealCode")
    private String lighthouseSealCode;

    @Excel(name = "lighthouseSealDeal")
    private String lighthouseSealDeal;

    @Excel(name = "lighthouseLanternCode")
    private BigDecimal lighthouseLanternCode;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "nodeNumber")
    private String nodeNumber;

    @Excel(name = "inspectId")
    private Integer isEnabled;
}
