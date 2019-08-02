package top.hting.entity.sqlserver.inspect;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AIS基站巡检记录
 */
@Data
@TableName("Cbs_Inspect_Site")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsInspectStation extends Base {
    @Excel(name = "pid")
    @TableId("pid")
    private String pid;

    @Excel(name = "cbsinspectlistfid")
    private String cbsinspectlistfid;

    @Excel(name = "basnavigationmarkfid")
    private String basnavigationmarkfid;

    @Excel(name = "marktable")
    private String marktable;

    @Excel(name = "markname")
    private String markname;

    @Excel(name = "inspecttime")
    private Date inspecttime;

    @Excel(name = "examiner")
    private String examiner;

    @Excel(name = "orgcode")
    private String orgcode;

    @Excel(name = "firmcode")
    private String firmcode;

    @Excel(name = "waterproofcode")
    private String waterproofcode;

    @Excel(name = "exteriorcode")
    private String exteriorcode;

    @Excel(name = "avhfstatuscode")
    private String avhfstatuscode;

    @Excel(name = "avhf")
    private String avhf;

    @Excel(name = "agpsstatuscode")
    private String agpsstatuscode;

    @Excel(name = "agps")
    private String agps;

    @Excel(name = "atrack")
    private String atrack;

    @Excel(name = "arange")
    private BigDecimal arange;

    @Excel(name = "aloada")
    private BigDecimal aloada;

    @Excel(name = "aloadb")
    private BigDecimal aloadb;

    @Excel(name = "aunita")
    private Integer aunita;

    @Excel(name = "aunitb")
    private Integer aunitb;

    @Excel(name = "atarget")
    private Integer atarget;

    @Excel(name = "bvhfstatuscode")
    private String bvhfstatuscode;

    @Excel(name = "bvhf")
    private BigDecimal bvhf;

    @Excel(name = "bgpsstatuscode")
    private String bgpsstatuscode;

    @Excel(name = "bgps")
    private String bgps;

    @Excel(name = "btrack")
    private String btrack;

    @Excel(name = "brange")
    private BigDecimal brange;

    @Excel(name = "bloada")
    private BigDecimal bloada;

    @Excel(name = "bloadb")
    private BigDecimal bloadb;

    @Excel(name = "bunita")
    private Integer bunita;

    @Excel(name = "bunitb")
    private Integer bunitb;

    @Excel(name = "btarget")
    private Integer btarget;

    @Excel(name = "upsinvoltagestatuscode")
    private String upsinvoltagestatuscode;

    @Excel(name = "upsinvoltage")
    private Integer upsinvoltage;

    @Excel(name = "upsoutvoltagestatuscode")
    private String upsoutvoltagestatuscode;

    @Excel(name = "upsoutvoltage")
    private Integer upsoutvoltage;

    @Excel(name = "upseffectivecode")
    private String upseffectivecode;

    @Excel(name = "upsload")
    private BigDecimal upsload;

    @Excel(name = "upssupply")
    private BigDecimal upssupply;

    @Excel(name = "upsbattery")
    private BigDecimal upsbattery;

    @Excel(name = "roomtempstatuscode")
    private String roomtempstatuscode;

    @Excel(name = "roomtemp")
    private BigDecimal roomtemp;

    @Excel(name = "roomhumiditystatuscode")
    private String roomhumiditystatuscode;

    @Excel(name = "roomhumidity")
    private BigDecimal roomhumidity;

    @Excel(name = "roomduststatuscode")
    private String roomduststatuscode;

    @Excel(name = "devicewiringstatuscode")
    private String devicewiringstatuscode;

    @Excel(name = "ais1")
    private String ais1;

    @Excel(name = "ais2")
    private String ais2;

    @Excel(name = "radio1")
    private BigDecimal radio1;

    @Excel(name = "radio2")
    private BigDecimal radio2;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "isfinished")
    private String isfinished;


}
