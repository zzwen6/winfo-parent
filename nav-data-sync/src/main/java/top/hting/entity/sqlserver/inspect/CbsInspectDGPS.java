package top.hting.entity.sqlserver.inspect;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.util.Date;

/**
 * 差分台巡检记录
 */
@Data
@TableName("Cbs_Inspect_DGPS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsInspectDGPS extends Base {
    @TableId("pid")
    private String pid;

    private String cbsInspectListFID;

    private String basNavigationMarkFID;

    private String markTable;

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

    private Integer IsFinished;
}
