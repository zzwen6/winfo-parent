package top.hting.entity.sqlserver.setup;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 技术参数
 */
@Data
@TableName("Cbs_Private_Tech")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsPrivateTech extends Base implements Serializable {

    @TableId("Pid")
    private String Pid;

    private String CbsPrivateMarkFID;

    private Integer SerialNumber;

    private String BasNavigationMarkFID;

    private String MarkNo;

    private String MarkName;

    private Integer TwoOnlineDegree;

    private Integer TwoOnlineMinute;

    private BigDecimal TwoOnlineSecond;

    private String SubsidiaryEquipment;

    private String bridgetypeCode;

    private String TypeCode;

    private Integer LatitudeDegree;

    private Integer LatitudeMinute;

    private BigDecimal LatitudeSecond;

    private Integer LongitudeDegree;

    private Integer LongitudeMinute;

    private BigDecimal LongitudeSecond;

    private String LightRhythmCode;

    private String LightParameterCode;

    private String LightColorCode;

    private String LightPeriodCode;

    private String DetailCode;

    private BigDecimal LightHeight;

    private BigDecimal Range;

    private BigDecimal MarkHeight;

    private String Construct;

    private String SetTypeCode;

    private String Remark;

    private String BussinsseType;

}
