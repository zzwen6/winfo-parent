package top.hting.entity.sqlserver;

import lombok.Data;

import java.util.Date;

@Data
public class Base {

    private Date sysCreated;

    private String sysCreatedBy;

    private String sysOrg;

    private String sysDept;

    private Date sysLastUpd;

    private String sysLastUpdBy;

    private boolean sysDeleted;

}
