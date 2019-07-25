package top.hting.entity.sqlserver.tech;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("Cbs_TwoNews_Tech")
public class CbsTwoNewsTech implements Serializable {
    @TableId("pid")
    private String pid;
    private String CbsMarkNewsFID;
    private int SerialNumber;
    private String BasNavigationMarkFID;
    private String MarkNo;
    private String MarkName;
    private String KindCode;
    private String LightResult;
    private Short TwoOnlineDegree;
    private Short TwoOnlineMinute;
    private BigDecimal TwoOnlineSecond;
    private String bridgetypeCode;
    private String TypeCode;
    private Short LatitudeDegree;
    private Short LatitudeMinute;
    private BigDecimal LatitudeSecond;
    private Short LongitudeDegree;
    private Short LongitudeMinute;
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
    private String DataChange;
    private String Remark;
    private String OrgCode;
    private Date SysCreated;
    private String SysCreatedby;
    private String SysOrg;
    private String SysDept;
    private Date SysLastUpd;
    private String SysLastUpdBy;
    private boolean SysDeleted;
    private int State;
}
