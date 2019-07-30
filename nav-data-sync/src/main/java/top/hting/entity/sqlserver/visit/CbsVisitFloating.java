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
@TableName("Cbs_Visit_Floating")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsVisitFloating extends Base {

    @TableId("pid")
    private String pid;

    private String BscShipFID;

    private String BasNavigationMarkFID;

    private String MarkTable;

    private String MarkName;

    private Date InspectTime;

    private String Examiner;

    private String OrgCode;

    private String RadarType;

    private BigDecimal EyeHight;

    private Integer RadarsSpeed;

    private BigDecimal Range;

    private BigDecimal RadarHight;

    private String RadarTurnSpeed;

    private Integer Reservations;

    private BigDecimal Distance;

    private String RadarCode;

    private Integer Interval;

    private String LightQualityCode;

    private String LightCode;

    private String StructureCode;

    private String FloatingDeal;

    private String StandDeal;

    private String TopMarkDeal;

    private String RetainerDeal;

    private String EnergyDeal;

    private String Remark;

    private BigDecimal LightRange;

    private String Weather;

    private String Visibility;

}
