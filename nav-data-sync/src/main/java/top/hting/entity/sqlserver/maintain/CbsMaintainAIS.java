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
@TableName("Cbs_Maintain_AIS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsMaintainAIS extends Base {
    @TableId("pid")
    private String pid;

    private String cbsMaintainListFID;
    private String cbsAreaListFID;
    private String BasNavigationMarkFID;
    private String markTable;

    private String markName;


    private Date inspectTime;

    private String examiner;

    private String orgCode;


    private Short maintain1;

    private Short maintain2;

    private Short maintain3;

    private Short maintain4;

    private Short maintain5;

    private String remark;
}

