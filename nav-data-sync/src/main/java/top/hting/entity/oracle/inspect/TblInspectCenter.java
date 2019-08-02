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
 * AIS中心巡检记录
 * 没数据，不同进行同步
 *
 * AIS中继站也没有数据不用同步
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("TBL_INSPECTCENTER")
@Deprecated
public class TblInspectCenter extends OBase implements Serializable {

    @Excel(name = "inspectid")
    @TableId("inspectid")
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

    @Excel(name = "linestatuscode")
    private String linestatuscode;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "nodenumber")
    private String nodenumber;

    @Excel(name = "powerequipmentcode")
    private String powerequipmentcode;

    @Excel(name = "upsworkstatuscode")
    private String upsworkstatuscode;

    @Excel(name = "workstatuscode")
    private String workstatuscode;

    @Excel(name = "networkmachinefaultcod")
    private String networkmachinefaultcod;

}
