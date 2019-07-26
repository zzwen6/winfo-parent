package top.hting.entity.oracle.paln;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Tbl_PlanMarkList")
public class TblPlanMarkList extends Base implements Serializable {
    @TableId("planMarkId")
    @Excel(name = "planMarkId")
    private String planMarkId;

    @Excel(name = "planId")
    private String planId;

    @Excel(name = "markId")
    private String markId;

    @Excel(name = "inspectId")
    private String inspectId;

    @Excel(name = "markTableCode")
    private String markTableCode;

    @Excel(name = "markName")
    private String markName ;

    @Excel(name = "inspectDate")
    private Date inspectDate;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "isFinished")
    private Short isFinished;

    @Excel(name = "endInspectDate")
    private Date endInspectDate;
}
