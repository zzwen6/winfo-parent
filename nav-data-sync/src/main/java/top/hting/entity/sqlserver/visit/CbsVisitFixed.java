package top.hting.entity.sqlserver.visit;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("Cbs_Visit_Fixed")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsVisitFixed extends Base {

    @TableId("pid")
    private String pid;

    private String BscShipFID;

    private String BasNavigationMarkFID;

    private String MarkTable;

    private String MarkName;

    private Date InspectTime;

    private String Examiner;

    private String OrgCode;

    private String Vision;

    private String RadarType;

    private String RadarTurnSpeed;

    private Integer RadarsSpeed;

    private BigDecimal RadarHight;

    private BigDecimal EyeHight;

    private String Weather;

    private String LightQualityCode;

    private String LightingCode;

    private String Visibility;

    private String LightingRange;

    private String StructureCode;

    private BigDecimal Distance;

    private BigDecimal Range;

    private String RadarCode;

    private Integer Reservations;

    private Integer Interval;

    private String Remark;

}
