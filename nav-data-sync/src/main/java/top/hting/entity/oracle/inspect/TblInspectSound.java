package top.hting.entity.oracle.inspect;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.hting.entity.OBase;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 音响航标巡检记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("Tbl_InspectSound")
public class TblInspectSound extends OBase implements Serializable {

    private String inspectId;

    private String planMarkId;

    private String inspectNumber;

    private Date inspectTime;

    private String examiner;

    private String shipId;

    private String orgCode;

    private String markId;

    private String markTableCode;

    private String markName ;

    private String workStatusCode;

    private String workRepairCode;

    private BigDecimal paramVoltage;

    private BigDecimal paramPower;

    private String paramStatusCode;

    private String paramRepairCode;

    private String environmentStatusCode;

    private String environmentRepairCode;

    private String remark;

    private String nodeNumber;

    @Excel(name = "isEnabled")
    private Integer isEnabled;
}
