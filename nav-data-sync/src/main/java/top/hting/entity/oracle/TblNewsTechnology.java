package top.hting.entity.oracle;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
    @Excel(name = "technologyId")
    private String technologyId;

    @Excel(name = "newsId")
    private String newsId;

    @Excel(name = "setupTypeCodes")
    private String setupTypeCodes;

    @Excel(name = "serialNumber")
    private Integer serialNumber;

    @Excel(name = "markId")
    private String markId;

    @Excel(name = "typeCode")
    private String typeCode;

    @Excel(name = "markTableCode")
    private String markTableCode;

    @Excel(name = "markName")
    private String markName;

    @Excel(name = "markNameEn")
    private String markNameEn;

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

    @Excel(name = "lightRhythmCode")
    private String lightRhythmCode;

    @Excel(name = "lightParameterCode")
    private String lightParameterCode;

    @Excel(name = "lightColorCode")
    private String lightColorCode;

    @Excel(name = "lightPeriodCode")
    private String lightPeriodCode;

    @Excel(name = "lightDetailCode")
    private String lightDetailCode;

    @Excel(name = "lightHeight")
    private BigDecimal lightHeight;

    @Excel(name = "range")
    private BigDecimal range;

    @Excel(name = "markHeight")
    private BigDecimal markHeight;

    @Excel(name = "construct")
    private String construct;

    @Excel(name = "lightDegree")
    private Short lightDegree;

    @Excel(name = "lightMinute")
    private Short lightMinute;

    @Excel(name = "lightSecond")
    private BigDecimal lightSecond;

    @Excel(name = "markDegree")
    private Short markDegree;

    @Excel(name = "markMinute")
    private Short markMinute;

    @Excel(name = "markSecond")
    private BigDecimal markSecond;

    @Excel(name = "bridgeTypeCode")
    private String bridgeTypeCode;

    @Excel(name = "model")
    private String model;

    @Excel(name = "operatingRange")
    private BigDecimal operatingRange;

    @Excel(name = "frequency")
    private String frequency;

    @Excel(name = "bandCode")
    private String bandCode;

    @Excel(name = "identiferCode")
    private String identiferCode;

    @Excel(name = "period")
    private Integer period;

    @Excel(name = "MMSI")
    private String MMSI;

    @Excel(name = "virtualMark")
    private Short virtualMark;

    @Excel(name = "transmitPower")
    private BigDecimal transmitPower;

    @Excel(name = "workModeCode")
    private String workModeCode;

    @Excel(name = "broadcastInterval")
    private Short broadcastInterval;

    @Excel(name = "informationType")
    private String informationType;

    @Excel(name = "signalFormat")
    private String signalFormat;

    @Excel(name = "workTime")
    private String workTime;

    @Excel(name = "markIdentifier")
    private String markIdentifier;

    @Excel(name = "modulation")
    private String modulation;

    @Excel(name = "broadcastCategory")
    private String broadcastCategory;

    @Excel(name = "transferRate")
    private Integer transferRate;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "sysCreated")
    private Date sysCreated;

    @Excel(name = "sysCreatedby")
    private String sysCreatedby;

    @Excel(name = "sysOrg")
    private String sysOrg;

    @Excel(name = "sysDept")
    private String sysDept;

    @Excel(name = "sysLastUpd")
    private Date sysLastUpd;

    @Excel(name = "sysLastUpdBy")
    private String sysLastUpdBy;


}