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
import java.util.Date;

/**
 * 差分台巡检记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("Tbl_InspectDgps")
public class TblInspectDgps extends OBase implements Serializable {
    @TableId("inspectId")
    @Excel(name = "inspectId")
    private String inspectId;

    @Excel(name = "planMarkId")
    private String planMarkId;

    @Excel(name = "markId")
    private String markId;

    @Excel(name = "markTableCode")
    private String markTableCode;

    @Excel(name = "markName")
    private String markName;

    @Excel(name = "inspectTime")
    private Date inspectTime;

    @Excel(name = "examiner")
    private String examiner;

    @Excel(name = "orgCode")
    private String orgCode;

    @Excel(name = "outputSignalStatusCode")
    private String outputSignalStatusCode;

    @Excel(name = "outputSignalRepairCode")
    private String outputSignalRepairCode;

    @Excel(name = "deviceStatusCode")
    private String deviceStatusCode;

    @Excel(name = "deviceRepairCode")
    private String deviceRepairCode;

    @Excel(name = "antennaStatusCode")
    private String antennaStatusCode;

    @Excel(name = "antennaRepairCode")
    private String antennaRepairCode;

    @Excel(name = "computerStatusCode")
    private String computerStatusCode;

    @Excel(name = "computerRepairCode")
    private String computerRepairCode;

    @Excel(name = "powerStatusCode")
    private String powerStatusCode;

    @Excel(name = "powerRepairCode")
    private String powerRepairCode;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "nodeNumber")
    private String nodeNumber;

    @Excel(name = "inspectNumber")
    private String inspectNumber;

}
