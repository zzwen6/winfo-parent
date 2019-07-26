package top.hting.entity.sqlserver;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class Base {
    @Excel(name = "sysCreated")

    private Date sysCreated;
    @Excel(name = "sysCreatedBy")

    private String sysCreatedBy;
    @Excel(name = "sysOrg")

    private String sysOrg;

    @Excel(name = "sysDept")
    private String sysDept;

    @Excel(name = "sysLastUpd")
    private Date sysLastUpd;

    @Excel(name = "sysLastUpdBy")
    private String sysLastUpdBy;

    @Excel(name = "sysDeleted")
    private boolean sysDeleted;

}
