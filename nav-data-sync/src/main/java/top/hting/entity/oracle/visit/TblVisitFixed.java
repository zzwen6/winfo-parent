package top.hting.entity.oracle.visit;

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
@TableName("tbl_visitfixed")
public class TblVisitFixed extends OBase {

    @TableId("visitId")
    private String visitId;

    private String visitNumber;

    private Date inspectTime;

    private String examiner;

    private String shipId;

    private String orgCode;

    private String markId;

    private String markTableCode;

    private String markName ;

    private String vision;

    private String radarType;

    private String radarTurnSpeed;

    private Integer radarsSpeed;

    private BigDecimal radarHight;

    private BigDecimal eyeHight;

    private String lightQualityCode;

    private String lightingCode;

    private String weather;

    private String visibility;

    private String lightingRange;

    private String structureCode;

    private String radarCode;

    private Integer interval;

    private BigDecimal range;

    private Integer reservations;

    private BigDecimal distance;

    private String remark;

}
