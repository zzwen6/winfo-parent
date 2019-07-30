package top.hting.entity.oracle.maintain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.hting.entity.OBase;

import java.util.Date;

/**
 * 固定标志维护保养记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("Tbl_MaintainFixed")
public class TblMaintainFixed extends OBase {

    @TableId("maintainId")
    private String maintainId;

    private String planMarkId;

    private String inspectNumber;

    private Date inspectTime;

    private String examiner;

    private String orgCode;

    private String markId;

    private String markTableCode;

    private String markName ;

    private Short maintain1;

    private Short maintain2;

    private Short maintain3;

    private Short maintain4;

    private Short maintain5;

    private Short maintain6;

    private String remark;

    private String nodeNumber;


}
