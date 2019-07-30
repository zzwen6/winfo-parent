package top.hting.entity.oracle.visit;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.hting.entity.OBase;

import java.math.BigDecimal;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("tbl_visitfloat")
public class TblVisitFloat extends OBase {
    @TableId("visitId")
    @Excel(name = "visitId")
    private String visitId;

    @Excel(name = "visitNumber")
    private String visitNumber;

    @Excel(name = "inspectTime")
    private Date inspectTime;

    @Excel(name = "examiner")
    private String examiner;

    @Excel(name = "shipId")
    private String shipId;

    @Excel(name = "orgCode")
    private String orgCode;

    @Excel(name = "markId")
    private String markId;

    @Excel(name = "markTableCode")
    private String markTableCode;

    @Excel(name = "markName")
    private String markName ;

    @Excel(name = "radarType")
    private String radarType;

    @Excel(name = "eyeHight")
    private BigDecimal eyeHight  ;

    @Excel(name = "radarsSpeed")
    private Integer radarsSpeed;

    @Excel(name = "range")
    private BigDecimal range;

    @Excel(name = "radarHight")
    private BigDecimal radarHight;

    @Excel(name = "radarTurnSpeed")
    private String radarTurnSpeed;

    @Excel(name = "reservations")
    private Integer reservations;

    @Excel(name = "distance")
    private BigDecimal distance;

    @Excel(name = "radarCode")
    private String radarCode;

    @Excel(name = "interval")
    private Integer interval;

    @Excel(name = "lightQualityCode")
    private String lightQualityCode;

    @Excel(name = "lightCode")
    private String lightCode;

    @Excel(name = "lightRange")
    private BigDecimal lightRange;

    @Excel(name = "structureCode")
    private String structureCode;

    @Excel(name = "weather")
    private String weather;

    @Excel(name = "visibility")
    private String visibility;

    @Excel(name = "remark")
    private String remark;
}
