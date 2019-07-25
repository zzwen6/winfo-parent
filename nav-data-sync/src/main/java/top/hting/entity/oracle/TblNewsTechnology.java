package top.hting.entity.oracle;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Tbl_NewsTechnology")
public class TblNewsTechnology implements Serializable {
    @TableId("technologyId")
    private String technologyId;

    private String newsId;

    private String setupTypeCodes;

    private Integer serialNumber;

    private String markId;

    private String typeCode;

    private String markTableCode;

    private String markName;

    private String markNameEn;

    private Short latitudeDegree;

    private Short latitudeMinute;

    private BigDecimal latitudeSecond;

    private Short longitudeDegree;

    private Short longitudeMinute;

    private BigDecimal longitudeSecond;

    private String lightRhythmCode;

    private String lightParameterCode;

    private String lightColorCode;

    private String lightPeriodCode;

    private String lightDetailCode;

    private BigDecimal lightHeight;

    private BigDecimal range;

    private BigDecimal markHeight;

    private String construct;

    private Short lightDegree;

    private Short lightMinute;

    private BigDecimal lightSecond;

    private Short markDegree;

    private Short markMinute;

    private BigDecimal markSecond;

    private String bridgeTypeCode;

    private String model;

    private BigDecimal operatingRange;

    private String frequency;

    private String bandCode;

    private String identiferCode;

    private Integer period;

    private String MMSI;

    private Short virtualMark;

    private BigDecimal transmitPower;

    private String workModeCode;

    private Short broadcastInterval;

    private String informationType;

    private String signalFormat;

    private String workTime;

    private String markIdentifier;

    private String modulation;

    private String broadcastCategory;

    private Integer transferRate;

    private String remark;

    private Date sysCreated;

    private String sysCreatedby;

    private String sysOrg;

    private String sysDept;

    private Date sysLastUpd;

    private String sysLastUpdBy;


}