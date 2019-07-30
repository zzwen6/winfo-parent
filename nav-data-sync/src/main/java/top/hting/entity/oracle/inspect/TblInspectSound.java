package top.hting.entity.oracle.inspect;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @TableId("inspectId")
    @Excel(name = "inspectId")
    private String inspectId;

    @Excel(name = "planMarkId")
    private String planMarkId;

    @Excel(name = "inspectNumber")
    private String inspectNumber;

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

    @Excel(name = "workStatusCode")
    private String workStatusCode;

    @Excel(name = "workRepairCode")
    private String workRepairCode;

    @Excel(name = "paramVoltage")
    private BigDecimal paramVoltage;

    @Excel(name = "paramPower")
    private BigDecimal paramPower;

    @Excel(name = "paramStatusCode")
    private String paramStatusCode;

    @Excel(name = "paramRepairCode")
    private String paramRepairCode;

    @Excel(name = "environmentStatusCode")
    private String environmentStatusCode;

    @Excel(name = "environmentRepairCode")
    private String environmentRepairCode;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "nodeNumber")
    private String nodeNumber;

    @Excel(name = "isEnabled")
    private Integer isEnabled;
}
