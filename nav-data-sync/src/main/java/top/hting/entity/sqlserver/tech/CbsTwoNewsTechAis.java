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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Cbs_TwoNews_Tech_AIS")
public class CbsTwoNewsTechAis implements Serializable {
    @TableId("pid")
    private String pid;
    private String CbsMarkNewsFID;
    private int SerialNumber;
    private String FBasNavigationMarkFID;
    private String BasNavigationMarkFID;
    private String MarkNo;
    private String HbTypeCode;
    private String KindCode;
    private String MarkName;
    private String MarkNameE;
    private Short LatitudeDegree;
    private Short LatitudeMinute;
    private BigDecimal LatitudeSecond;
    private Short LongitudeDegree;
    private Short LongitudeMinute;
    private BigDecimal LongitudeSecond;
    private String MMSI;
    private BigDecimal TransmitPower;
    private String WorkModeCode;
    private Short workInterval;
    private Short VirtualMark;
    private String SetTypeCode;
    private String OrgCode;
    private String Remark;
    private Integer state;
    private Date SysCreated;
    private String SysCreatedby;
    private String SysOrg;
    private String SysDept;
    private Date SysLastUpd;
    private String SysLastUpdBy;
    private boolean SysDeleted;

}
