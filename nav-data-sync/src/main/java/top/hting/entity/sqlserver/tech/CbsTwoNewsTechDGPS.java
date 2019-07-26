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

@TableName("Cbs_TwoNews_Tech_DGPS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CbsTwoNewsTechDGPS implements Serializable {
    @TableId("pid")
    private String pid;
    private String CbsMarkNewsFID;
    private Integer SerialNumber;
    private String BasNavigationMarkFID;
    private String MarkNo;
    private String MarkName;
    private String MarkNameE;
    private String KindCode;
    private Short LatitudeDegree;
    private Short LatitudeMinute;
    private BigDecimal LatitudeSecond;
    private Short LongitudeDegree;
    private Short LongitudeMinute;
    private BigDecimal LongitudeSecond;
    private String InformationType;
    private String SignalFormat;
    private BigDecimal OperatingRange;
    private String WorkTime;
    private String RadioIdentifier;
    private String Modulation;
    private String BroadcastCategory;
    private Integer TransferRate;
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
