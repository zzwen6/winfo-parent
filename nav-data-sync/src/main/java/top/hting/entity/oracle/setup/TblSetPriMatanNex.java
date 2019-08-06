package top.hting.entity.oracle.setup;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.OBase;

import java.io.Serializable;

/**
 * 提交材料附件
 */
@Data
@TableName("TBL_SETPRIMATANNEX")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TblSetPriMatanNex extends OBase implements Serializable {

    @Excel(name = "annexid")
    @TableId("annexid")
    private String annexid;

    @Excel(name = "materialid")
    private String materialid;

    @Excel(name = "serialnumber")
    private Integer serialnumber;

    @Excel(name = "contentid")
    private String contentid;

    @Excel(name = "remark")
    @TableField(exist = false)
    private String remark;

}
