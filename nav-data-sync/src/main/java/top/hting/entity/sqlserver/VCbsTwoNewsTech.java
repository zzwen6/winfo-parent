package top.hting.entity.sqlserver;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 航标动态技术参数
 */
@Data
@ToString
public class VCbsTwoNewsTech {


    private String pid;
    // 航标动态主键
    private String cbsMarkNewsFID;
    // 航标主键
    private String basNavigationMarkFID;
    private String orgCode;
    private Date sysLastUpd;
    private String setTypeCode;
    private String serialNumber;
    private String markno;
    private String MarkName;
    private String TypeCode;
    private Integer LatitudeDegree;
    private Integer LatitudeMinute;
    private Integer LatitudeSecond;
    private Integer LongitudeDegree;
    private Integer LongitudeMinute;
    private BigDecimal LongitudeSecond;
    private String Light;
    private String LightHeight;
    private String Range;
    private String MarkHeight;
    private String Construct;
    private String Remark;
    private int State;
    private boolean SysDeleted;

}
