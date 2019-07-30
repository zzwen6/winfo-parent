package top.hting.entity.sqlserver.inspect;

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
@TableName("Cbs_Inspect_Sound")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsInspectSound extends Base {

    @TableId("pid")
    private String Pid;

    private String CbsInspectListFID;

    private String BasNavigationMarkFID;

    private String MarkTable;

    private String MarkName;

    private Date InspectTime;

    private String Examiner;

    private String OrgCode;

    private String WorkStatusCode;

    private String WorkRepairCode;

    private BigDecimal ParamVoltage;

    private BigDecimal ParamPower;

    private String ParamStatusCode;

    private String ParamRepairCode;

    private String EnvironmentStatusCode;

    private String EnvironmentRepairCode;

    private String Remark;

    private Integer IsFinished;


}
