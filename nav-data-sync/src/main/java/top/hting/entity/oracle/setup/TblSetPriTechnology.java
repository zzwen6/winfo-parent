package top.hting.entity.oracle.setup;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.OBase;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 专用航标技术参数
 */
@Data
@TableName("TBL_SETPRITECHNOLOGY_end")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TblSetPriTechnology extends OBase implements Serializable {

    @Excel(name = "technologyid")
    @TableId("technologyid")
    private String technologyid;

    @Excel(name = "setupid")
    private String setupid;

    @Excel(name = "setuptypecodes")
    private String setuptypecodes;

    @Excel(name = "serialnumber")
    private Integer serialnumber;

    @Excel(name = "markid")
    private String markid;

    @Excel(name = "typecode")
    private String typecode;

    @Excel(name = "marktablecode")
    private String marktablecode;

    @Excel(name = "markname")
    private String markname;

    @Excel(name = "marknameen")
    private String marknameen;

    @Excel(name = "latitudedegree")
    private Integer latitudedegree;

    @Excel(name = "latitudeminute")
    private Integer latitudeminute;

    @Excel(name = "latitudesecond")
    private BigDecimal latitudesecond;

    @Excel(name = "longitudedegree")
    private Integer longitudedegree;

    @Excel(name = "longitudeminute")
    private Integer longitudeminute;

    @Excel(name = "longitudesecond")
    private BigDecimal longitudesecond;

    @Excel(name = "lightrhythmcode")
    private String lightrhythmcode;

    @Excel(name = "lightparametercode")
    private String lightparametercode;

    @Excel(name = "lightcolorcode")
    private String lightcolorcode;

    @Excel(name = "lightperiodcode")
    private String lightperiodcode;

    @Excel(name = "lightdetailcode")
    private String lightdetailcode;

    @Excel(name = "lightheight")
    private BigDecimal lightheight;

    @Excel(name = "range")
    private BigDecimal range;

    @Excel(name = "markheight")
    private BigDecimal markheight;

    @Excel(name = "construct")
    private String construct;

    @Excel(name = "lightdegree")
    private Integer lightdegree;

    @Excel(name = "lightminute")
    private Integer lightminute;

    @Excel(name = "lightsecond")
    private BigDecimal lightsecond;

    @Excel(name = "markdegree")
    private Integer markdegree;

    @Excel(name = "markminute")
    private Integer markminute;

    @Excel(name = "marksecond")
    private BigDecimal marksecond;

    @Excel(name = "bridgetypecode")
    private String bridgetypecode;

    @Excel(name = "model")
    private String model;

    @Excel(name = "operatingrange")
    private BigDecimal operatingrange;

    @Excel(name = "frequency")
    private String frequency;

    @Excel(name = "bandcode")
    private String bandcode;

    @Excel(name = "identifercode")
    private Integer identifercode;

    @Excel(name = "period")
    private Integer period;

    @Excel(name = "mmsi")
    private String mmsi;

    @Excel(name = "virtualmark")
    private Integer virtualmark;

    @Excel(name = "transmitpower")
    private BigDecimal transmitpower;

    @Excel(name = "workmodecode")
    private String workmodecode;

    @Excel(name = "broadcastinterval")
    private Integer broadcastinterval;

    @Excel(name = "informationtype")
    private String informationtype;

    @Excel(name = "signalformat")
    private String signalformat;

    @Excel(name = "worktime")
    private String worktime;

    @Excel(name = "markidentifier")
    private String markidentifier;

    @Excel(name = "modulation")
    private String modulation;

    @Excel(name = "broadcastcategory")
    private String broadcastcategory;

    @Excel(name = "transferrate")
    private BigDecimal transferrate;

    @Excel(name = "remark")
    private String remark;


}
