package top.hting.entity.oracle.visit;

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

    private String visitId;

    private String visitNumber;

    private Date inspectTime;

    private String examiner;

    private String shipId;

    private String orgCode;

    private String markId;

    private String markTableCode;

    private String markName ;

    private String radarType;

    private BigDecimal eyeHight  ;

    private Integer radarsSpeed;

    private BigDecimal range;

    private BigDecimal radarHight;

    private String radarTurnSpeed;

    private Integer reservations;

    private BigDecimal distance;

    private String radarCode;

    private Integer interval;

    private String lightQualityCode;

    private String lightCode;

    private BigDecimal lightRange;

    private String structureCode;

    private String weather;

    private String visibility;

    private String remark;
}
