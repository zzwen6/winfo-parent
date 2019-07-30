package top.hting.entity.sqlserver.maintain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.util.Date;

/**
 * 音响航标维护记录
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("Cbs_Maintain_Sound")
public class CbsMaintainSound extends Base {

    @TableId("pid")
    private String Pid;

    private String CbsMaintainListFID;

    private String CbsAreaListFID;

    private String BasNavigationMarkFID;

    private String MarkTable;

    private String MarkName;

    private Date InspectTime;

    private String Examiner;

    private String OrgCode;

    private Short Maintain1;

    private Short Maintain2;

    private Short Maintain3;

    private Short Maintain4;

    private Short Maintain5;

    private Short Maintain6;

    private Short Maintain7;

    private String Remark;

    private String IsFinished;
}
