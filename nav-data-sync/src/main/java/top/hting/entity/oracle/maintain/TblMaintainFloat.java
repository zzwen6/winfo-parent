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
 * 浮动标志维护保养记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("Tbl_MaintainFloat")
public class TblMaintainFloat extends OBase {


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

    private Short maintain17;

    private String remark;

    private String nodeNumber;

}
