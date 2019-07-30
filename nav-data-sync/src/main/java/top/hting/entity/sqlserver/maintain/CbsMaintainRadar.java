package top.hting.entity.sqlserver.maintain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("Cbs_Maintain_Radar")
public class CbsMaintainRadar extends Base {
    @TableId("pid")
    private String pid;

    private String cbsMaintainListFID;
    private String cbsAreaListFID;
    private String basNavigationMarkFID;

    private Date inspectTime;

    private String examiner;

    private String orgCode;
    private String markTable;

    private String markName;

    private Short maintain1;

    private Short maintain2;

    private Short maintain3;

    private Short maintain4;

    private Short maintain5;

    private String remark;
}
