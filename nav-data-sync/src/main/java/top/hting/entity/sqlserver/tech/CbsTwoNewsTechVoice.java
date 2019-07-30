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
@TableName("Cbs_TwoNews_Tech_Voice")
public class CbsTwoNewsTechVoice implements Serializable {
    @TableId("pid")
    private String pid;
    private String CbsMarkNewsFID;
    private Integer SerialNumber;
    private String FBasNavigationMarkFID;
    private String BasNavigationMarkFID;
    private String MarkNo;
    private String HbTypeCode;
    private String KindCode;
    private String MarkName;
    private Short LatitudeDegree;
    private Short LatitudeMinute;
    private BigDecimal LatitudeSecond;
    private Short LongitudeDegree;
    private Short LongitudeMinute;
    private BigDecimal LongitudeSecond;
    private String TypeCode;
    private String WorkCycle;
    private String EffectDistance;
    private String SetTypeCode;
    private String OrgCode;
    private String Remark;
    private String state;
    private Date SysCreated;
    private String SysCreatedby;
    private String SysOrg;
    private String SysDept;
    private Date SysLastUpd;
    private String SysLastUpdBy;
    private String SysDeleted;

}
