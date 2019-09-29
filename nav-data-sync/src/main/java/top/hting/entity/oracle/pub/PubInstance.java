package top.hting.entity.oracle.pub;

import lombok.Data;

import java.util.Date;

/**
 * 流程和公用航标设置组合数据
 */
@Data
public class PubInstance {

    private String setupId;

    private String projectName;

    private String instanceId;

    private Date applyTime;

}
