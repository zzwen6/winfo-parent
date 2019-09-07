package top.hting.entity.oracle.dic;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("TBL_FUNCTION")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TblFunction {

    /**
     * 对应主键
     */
    @TableId
    @Excel(name = "REFERENCEID")
    private String referenceId;

    /**
     * 航标功能代码
     */
    @Excel(name = "航标功能")
    private String functionCode;

    /**
     * 标体形状代码
     */
    private String bodySharpCode;

    /**
     * 顶标形状代码
     */
    private String topShapeCode;

    /**
     * 构造颜色代码
     */
    private String colorCode;

    /**
     * 灯质颜色代码
     */
    @Excel(name = "灯质颜色")
    private String lightColorCode;
    /**
     * 灯质节奏名称代码
     */
    @Excel(name = "灯质节奏名称")
    private String lightRhythmCode;
    /**
     * 灯质信号周期代码
     */
    @Excel(name = "灯质周期")
    private String lightPeriodCode;
    /**
     * 灯质节奏参数代码
     */
    @Excel(name = "灯质节奏参数")
    private String lightParameterCode;

    /**
     * 节奏明细代码
     */
    @Excel(name = "节奏明细")
    private String lightRhythmCodeDetailCode;

    /**
     * 创建时间
     */
    private Date sysCreated;
    /**
     * 创建人
     */
    private String sysCreatedby;
    /**
     * 创建机构代码
     */
    private String sysOrg;
    /**
     * 创建部门代码
     */
    private String sysDept;
    /**
     * 最后修改时间
     */
    private Date sysLastUpd;

    /**
     * 最后修改人
     */
    private String sysLastUpdBy;

}
