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
@TableName("Cbs_Inspect_Fixed_new")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsInspectFixedNew extends Base {

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

    private String LightingCode;

    private String LightingDeal;

    private String LightCode;

    private String LightDeal;

    private String LightQualityCode;

    private String LightQualityDeal;

    private String LightingBreakReason;

    private String SolarValve;

    private String SolarValveDeal;

    private String BulbGroup;

    private String BulbGroupDeal;

    private String LampVoltage;

    private String LampVoltageDeal;

    private BigDecimal MirrorCode;

    private String MirrorDeal;

    private String SolarPanelCode;

    private String SolarPanelDeal;

    private BigDecimal SolarSupportCode;

    private String SolarSupportDeal;

    private String LighthouseFirmCode;

    private String LighthouseFirmDeal;

    private String LighthouseSealCode;

    private String LighthouseSealDeal;

    private BigDecimal LighthouseLanternCode;

    private String LighthouseLanternDeal;

    private String FlashCode;

    private String FlashDeal;

    private String Remark;

    private String IsFinished;

    private String LightingType;

    private String LightSerialNumber;


}
