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

    private String lightingCode;

    private String lightingDeal;

    private String lightingBreakReasonCode;

    private BigDecimal lightingWorkVoltage;

    private BigDecimal lightingWorkCurrent;

    private String lightCode;

    private String lightDeal;

    private String lightQualityCode;

    private String lightQualityDeal;

    private String solarValveCode;

    private String solarValveDeal;

    private String bulbGroup;

    private String bulbGroupDeal;

    private String batteryCode;

    private String batteryDeal;

    private BigDecimal batteryVoltage;

    private String batteryVoltageDeal;

    private String solarPanelCode;

    private String solarPanelDeal;

    private BigDecimal chargeCurrent;

    private String chargeCurrentDeal;

    private String lighthouseFirmCode;

    private String lighthouseFirmDeal;

    private String lighthouseSealCode;

    private String lighthouseSealDeal;

    private BigDecimal lighthouseLanternCode;

    private String remark;

    private String nodeNumber;

    @Excel(name = "isEnabled")
    private Integer isEnabled;
}
