package top.hting.entity.sqlserver.inspect;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("Cbs_Inspect_Floating_new")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsInspectFloatingNew extends Base {

    @TableId("pid")
    private String pid;

    private String CbsInspectListFID;

    private String BscShipFID;

    private String BasNavigationMarkFID;

    private String MarkTable;

    private String MarkName;

    private Date InspectTime;

    private String Examiner;

    private String OrgCode;

    private Short LatitudeMinute;

    private Short LatitudeDegree;

    private BigDecimal LatitudeSecond;

    private Short LongitudeMinute;

    private Short LongitudeDegree;

    private BigDecimal LongitudeSecond;

    private String Distance;

    private Short RecoveryPosition;

    private Short ReLatitudeMinute;

    private Short ReLatitudeDegree;

    private BigDecimal ReLatitudeSecond;

    private Short ReLongitudeMinute;

    private Short ReLongitudeDegree;

    private BigDecimal ReLongitudeSecond;

    private String LightQualityCode;

    private String LightQualityDeal;

    private String LightingCode;

    private String LightingDeal;

    private String LightingBreakReason;

    private String StructureCode;

    private String StructureDeal;

    private String Coloring;

    private String ColoringDeal;

    private String XBatteryCode;

    private String XBatteryDeal;

    private String BatteryDianYa;

    private String MirrorDeal;

    private String SolarPanelCode;

    private String FloatChangeReason;

    private String SolarPanelDeal;

    private BigDecimal ChargeCurrent;

    private String SolarSupportDeal;

    private String Remark;

    private String IsFinished;

    private String LightingType;

    private String LightSerialNumber;

    private String FloatNumber;


}
