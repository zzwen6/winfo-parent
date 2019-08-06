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
import java.util.Date;

/**
 * 专用航标设置申请
 */
@Data
@TableName("Cbs_Private_Mark")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsPrivateMark extends Base implements Serializable {

    @TableId("Pid")
    private String Pid;

    private String OrgCode;

    private String DeclareCode;

    private Date DeclareTime;

    private String OrgCode2;

    private String DeclareUnit;

    private String UserId;

    private String Manager;

    private String Corporation;

    private String Telephone;

    private String Fax;

    private String Address;

    private String Email;

    private String MobilePhone;

    private Short LatitudeMinute;

    private Short LatitudeDegree;

    private BigDecimal LatitudeSecond;

    private Short LongitudeMinute;

    private Short LongitudeDegree;

    private BigDecimal LongitudeSecond;

    private String Reason;

    private String DesignUnit;

    private String OrgCode3;

    private String MaintenanceUnit;

    private Date TenderTime;

    private String BookingPeriod;

    private String SetAddress;

    private String Lifetime;

    private boolean EmailNotice;

    private boolean SmsNotice;

    private String AcceptCode;

    private boolean HasSubmit;

    private String SetDeclare;

    private String SetTypeCode;

    private String WorkeUnit;

}
