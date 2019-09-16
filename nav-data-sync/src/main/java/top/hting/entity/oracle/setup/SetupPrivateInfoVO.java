package top.hting.entity.oracle.setup;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:
 *      专用航标设置详情
 * @outhor lizhichao
 * @create 2019-02-27 13:34
 */
@Data
public class SetupPrivateInfoVO implements Serializable {
    private static final long serialVersionUID = -6100046930978834649L;


    /**
     * 设置主键
     */
    @TableId("setupId")
    private String setupId;

    /**
     * 申请单编号
     */
    private String applyNumber;

    /**
     * 申请单位
     */
    private String applyOrgName;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 申请人姓名
     */
    private String applyUserName;

    /**
     * 受理单位名称
     */
    private String acceptOrgName;

    /**
     * 技术审查机构代码（航标处）
     */
    private String orgCode;
    
    /**
     * 技术审查机构名称（航标处）
     */
    private String orgName;

    /**
     * 工程名称
     */
    private String projectName;

    /**
     * 工程情况概要说明
     */
    private String explain;

    /**
     * 设计单位
     */
    private String designOrgName;

    /**
     * 施工单位
     */
    private String constructOrgName;

    /**
     * 维护单位代码
     */
    private String maintainOrgCode;

    /**
     * 维护单位
     */
    private String maintainOrgName;

    /**
     * 计划设置时间
     */
    private Date setupTime;

    /**
     * 预定工期
     */
    private String bookingPeriod;

    /**
     * 设置类别
     */
    private String setupType;

    /**
     * 使用期限
     */
    private String lifetime;

    /**
     * 节点编号
     */
    private String nodeNumber;

    /**
     * 节点显示名称
     */
    private String nodeDisplayName;

    /**
     * 流程实例ID
     */
    private String instanceId;
    
    /**
     * 文件路径
     */
    private String fliePath;

    private String licenseNumber;

    private Date acceptDate;

    private Date approveDate;

    private String copyOrg;


}
