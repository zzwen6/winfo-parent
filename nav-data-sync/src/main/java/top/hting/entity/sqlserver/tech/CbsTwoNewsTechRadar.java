package top.hting.entity.sqlserver.tech;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("Cbs_TwoNews_Tech_Radar")
public class CbsTwoNewsTechRadar  implements Serializable {
    private String pid;
    private String CbsMarkNewsFID;
    private Integer SerialNumber;
    private String FBasNavigationMarkFID;
    private String BasNavigationMarkFID;
    private String MarkNo;
    private String MarkName;
    private String KindCode;
    private Short LatitudeDegree;
    private Short LatitudeMinute;
    private BigDecimal LatitudeSecond;
    private Short LongitudeDegree;
    private Short LongitudeMinute;
    private BigDecimal LongitudeSecond;

    private String Frequency;
    private String BandCode;
    private BigDecimal OperatingRange;
    private String IdentiferCode;
    private String SetTypeCode;
    private String Remark;
    private String state;
    private String OrgCode;
    private Date SysCreated;
    private String SysCreatedby;
    private String SysOrg;
    private String SysDept;
    private Date SysLastUpd;
    private String SysLastUpdBy;
    private String SysDeleted;

}
