package top.hting.entity.oracle;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tbl_user")
public class TblUser implements Serializable {
    /**
     * 用户主键
     */
    @TableId("userId")
    private String userId;

    /**
     * 所在机构部门代码
     */
    private String orgCode;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 职务
     */
    private String duty;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 固定电话
     */
    private String officePhone;

    /**
     * 手机号码
     */
    private String mobilePhone;

    /**
     * 传真号码
     */
    private String fax;

    /**
     * 开户时间
     */
    private Date openDate;

    /**
     * 0:否,1:是
     */
    private Short isEnabled;

    /**
     * 排序
     */
    private Integer serialNumber;

    /**
     * 创建时间
     */
    private Date sysCreated;

    /**
     * 创建人
     */
    private String sysCreatedby;

    /**
     * 创建机构代码
     */
    private String sysOrg;

    /**
     * 创建部门代码
     */
    private String sysDept;

    /**
     * 最后修改时间
     */
    private Date sysLastUpd;

    /**
     * 最后修改人
     */
    private String sysLastUpdBy;


}