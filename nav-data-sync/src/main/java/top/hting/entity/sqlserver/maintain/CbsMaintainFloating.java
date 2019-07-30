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
@TableName ("Cbs_Maintain_Floating")
public class CbsMaintainFloating extends Base {
    @TableId("pid")
    private String pid;


    private String cbsMaintainListFID;
    private String cbsAreaListFID;

    private String basNavigationMarkFID;


    private String markTable;

    private String markName;
    private Date inspectTime;

    private Short maintain1;
    private String examiner;

    private String orgCode;
    private Short maintain2;

    private Short maintain3;

    private Short maintain4;

    private Short maintain5;

    private Short maintain6;

    private Short maintain7;

    private Short maintain8;

    private Short maintain9;

    private Short maintain10;

    private Short maintain11;

    private Short maintain12;

    private Short maintain13;

    private Short maintain14;

    private Short maintain15;

    private Short maintain16;


    private String remark;

}
