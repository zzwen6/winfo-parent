package top.hting.entity.oracle.inspect;

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

    private String inspectId;

    private String planMarkId;

    private String markId;

    private String markTableCode;

    private String markName;

    private Date inspectTime;

    private String examiner;

    private String orgCode;

    private String outputSignalStatusCode;

    private String outputSignalRepairCode;

    private String deviceStatusCode;

    private String deviceRepairCode;

    private String antennaStatusCode;

    private String antennaRepairCode;

    private String computerStatusCode;

    private String computerRepairCode;

    private String powerStatusCode;

    private String powerRepairCode;

    private String remark;

    private String nodeNumber;

    private String inspectNumber;

}
