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
@TableName("Tbl_InspectFloat")
public class TblInspectFloat extends OBase implements Serializable {


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

    @Excel(name = "latitudeDegree")
    private Short latitudeDegree;

    @Excel(name = "latitudeMinute")
    private Short latitudeMinute;

    @Excel(name = "latitudeSecond")
    private BigDecimal latitudeSecond;

    @Excel(name = "longitudeDegree")
    private Short longitudeDegree;

    @Excel(name = "longitudeMinute")
    private Short longitudeMinute;

    @Excel(name = "longitudeSecond")
    private BigDecimal longitudeSecond;

    @Excel(name = "distanceDegree")
    private BigDecimal distanceDegree;

    @Excel(name = "distanceMeter")
    private BigDecimal distanceMeter;

    @Excel(name = "recoveryPosition")
    private Short recoveryPosition;

    @Excel(name = "reLatitudeDegree")
    private Short reLatitudeDegree;

    @Excel(name = "reLatitudeMinute")
    private Short reLatitudeMinute;

    @Excel(name = "reLatitudeSecond")
    private BigDecimal reLatitudeSecond;

    @Excel(name = "reLongitudeDegree")
    private Short reLongitudeDegree;

    @Excel(name = "reLongitudeMinute")
    private Short reLongitudeMinute;

    @Excel(name = "reLongitudeSecond")
    private BigDecimal reLongitudeSecond;

    @Excel(name = "lightQualityCode")
    private String lightQualityCode;

    @Excel(name = "lightQualityDeal")
    private String lightQualityDeal;

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

    @Excel(name = "structureCode")
    private String structureCode;

    @Excel(name = "structureDeal")
    private String structureDeal;

    @Excel(name = "coloringCode")
    private String coloringCode;

    @Excel(name = "coloringDeal")
    private String coloringDeal;

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

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "nodeNumber")
    private String nodeNumber;

    @Excel(name = "isEnabled")
    private Integer isEnabled;
}
