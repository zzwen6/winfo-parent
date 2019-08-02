package top.hting.entity.oracle.inspect;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.hting.entity.OBase;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * AIS实体航标巡检记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("TBL_INSPECTAIS")
public class TblInspectAis extends OBase implements Serializable {

    @TableId("inspectId")
    @Excel(name = "inspectId")
    private String inspectid;

    @Excel(name = "planmarkid")
    private String planmarkid;

    @Excel(name = "inspectnumber")
    private String inspectnumber;

    @Excel(name = "inspecttime")
    private Date inspecttime;

    @Excel(name = "examiner")
    private String examiner;

    @Excel(name = "shipid")
    private String shipid;

    @Excel(name = "orgcode")
    private String orgcode;

    @Excel(name = "markid")
    private String markid;

    @Excel(name = "marktablecode")
    private String marktablecode;

    @Excel(name = "markname")
    private String markname;

    @Excel(name = "gpsstatuscode")
    private String gpsstatuscode;

    @Excel(name = "gpsrepaircode")
    private String gpsrepaircode;

    @Excel(name = "vhfstatuscode")
    private String vhfstatuscode;

    @Excel(name = "vhfrepaircode")
    private String vhfrepaircode;

    @Excel(name = "computerstatuscode")
    private String computerstatuscode;

    @Excel(name = "computerrepaircode")
    private String computerrepaircode;

    @Excel(name = "supplyvoltage")
    private BigDecimal supplyvoltage;

    @Excel(name = "supplypower")
    private BigDecimal supplypower;

    @Excel(name = "supplystatuscode")
    private String supplystatuscode;

    @Excel(name = "supplyrepaircode")
    private String supplyrepaircode;

    @Excel(name = "aissysisvisual")
    private String aissysisvisual;

    @Excel(name = "isrepair")
    private String isrepair;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "nodenumber")
    private String nodenumber;

    @Excel(name = "isEnabled")
    private Integer isEnabled;
}
