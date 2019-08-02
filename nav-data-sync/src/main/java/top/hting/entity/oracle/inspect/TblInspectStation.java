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
import java.util.Date;

/**
 * AIS基站巡检记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("TBL_INSPECTSTATION")
public class TblInspectStation extends OBase implements Serializable {
    @TableId("inspectid")
    @Excel(name = "inspectid")
    private String inspectid;

    @Excel(name = "planmarkid")
    private String planmarkid;

    @Excel(name = "inspectnumber")
    private String inspectnumber;

    @Excel(name = "inspecttime")
    private Date inspecttime;

    @Excel(name = "examiner")
    private String examiner;

    @Excel(name = "orgcode")
    private String orgcode;

    @Excel(name = "markid")
    private String markid;

    @Excel(name = "marktablecode")
    private String marktablecode;

    @Excel(name = "markname")
    private String markname;

    @Excel(name = "firmcode")
    private String firmcode;

    @Excel(name = "waterproofcode")
    private String waterproofcode;

    @Excel(name = "exteriorcode")
    private String exteriorcode;

    @Excel(name = "avhfstatuscode")
    private String avhfstatuscode;

    @Excel(name = "agpsstatuscode")
    private String agpsstatuscode;

    @Excel(name = "upsinvoltagestatuscode")
    private String upsinvoltagestatuscode;

    @Excel(name = "upsoutvoltagestatuscode")
    private String upsoutvoltagestatuscode;

    @Excel(name = "upseffectivecode")
    private String upseffectivecode;

    @Excel(name = "roomtempstatuscode")
    private String roomtempstatuscode;

    @Excel(name = "roomhumiditystatuscode")
    private String roomhumiditystatuscode;

    @Excel(name = "roomduststatuscode")
    private String roomduststatuscode;

    @Excel(name = "devicewiringstatuscode")
    private String devicewiringstatuscode;

    @Excel(name = "nodenumber")
    private String nodenumber;

    @Excel(name = "computerstatuscode")
    private String computerstatuscode;

    @Excel(name = "sparecomputerstatuscode")
    private String sparecomputerstatuscode;

    @Excel(name = "serversoftwaresystemcode")
    private String serversoftwaresystemcode;

    @Excel(name = "powerequipmentcode")
    private String powerequipmentcode;

    @Excel(name = "upsworkstatuscode")
    private String upsworkstatuscode;

    @Excel(name = "networkmachinefaultcode")
    private String networkmachinefaultcode;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "linestatuscode")
    private String linestatuscode;
}
