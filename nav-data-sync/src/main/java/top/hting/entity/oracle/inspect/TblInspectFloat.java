package top.hting.entity.oracle.inspect;

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

    private Short latitudeDegree;

    private Short latitudeMinute;

    private BigDecimal latitudeSecond;

    private Short longitudeDegree;

    private Short longitudeMinute;

    private BigDecimal longitudeSecond;

    private BigDecimal distanceDegree;

    private BigDecimal distanceMeter;

    private Short recoveryPosition;

    private Short reLatitudeDegree;

    private Short reLatitudeMinute;

    private BigDecimal reLatitudeSecond;

    private Short reLongitudeDegree;

    private Short reLongitudeMinute;

    private BigDecimal reLongitudeSecond;

    private String lightQualityCode;

    private String lightQualityDeal;

    private String lightingCode;

    private String lightingDeal;

    private String lightingBreakReasonCode;

    private BigDecimal lightingWorkVoltage;

    private BigDecimal lightingWorkCurrent;

    private String structureCode;

    private String structureDeal;

    private String coloringCode;

    private String coloringDeal;

    private String batteryCode;

    private String batteryDeal;

    private BigDecimal batteryVoltage;

    private String batteryVoltageDeal;

    private String solarPanelCode;

    private String solarPanelDeal;

    private BigDecimal chargeCurrent;

    private String chargeCurrentDeal;

    private String remark;

    private String nodeNumber;

}
