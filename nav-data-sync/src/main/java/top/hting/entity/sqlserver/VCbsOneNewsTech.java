package top.hting.entity.sqlserver;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 航标动态技术参数
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("V_Cbs_OneNews_Tech")
public class VCbsOneNewsTech {

    @TableId("pid")
    private String pid;
    // 航标动态主键
    private String cbsMarkNewsFID;
    // 航标主键
    private String basNavigationMarkFID;
    private String orgCode;
    private Date sysLastUpd;
    private String setTypeCode;
    private Integer serialNumber;
    private String markno;
    private String MarkName;
    private String TypeCode;
    private Short LatitudeDegree;
    private Short LatitudeMinute;
    private BigDecimal LatitudeSecond;
    private Short LongitudeDegree;
    private Short LongitudeMinute;
    private BigDecimal LongitudeSecond;
    private String Light;
    private String LightHeight;
    private String Range;
    private String MarkHeight;
    private String Construct;
    private String Remark;
    private Integer State;
    private boolean SysDeleted;

}
