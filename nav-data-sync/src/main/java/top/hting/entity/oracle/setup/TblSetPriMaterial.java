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
 * 申请提交材料
 */
@Data
@TableName("TBL_SETPRIMATERIAL")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TblSetPriMaterial extends OBase implements Serializable {

    @Excel(name = "materialid")
    @TableId("materialid")
    private String materialid;

    @Excel(name = "setupid")
    private String setupid;

    @Excel(name = "serialnumber")
    private Integer serialnumber;

    @Excel(name = "materialname")
    private String materialname;

    @Excel(name = "typecode")
    private String typecode;

    @Excel(name = "quantity")
    private Integer quantity;

    @Excel(name = "remark")
    @TableField(exist =  false)
    private String remark;


}
