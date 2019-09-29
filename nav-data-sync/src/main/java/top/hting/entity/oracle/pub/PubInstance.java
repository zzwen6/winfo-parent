package top.hting.entity.oracle.pub;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 流程和公用航标设置组合数据
 */
@Data
public class PubInstance {
    @Excel(name = "setupId")
    private String setupId;

    @Excel(name = "projectName")
    private String projectName;

    @Excel(name = "instanceId")
    private String instanceId;

    @Excel(name = "applyTime")
    private Date applyTime;

}
