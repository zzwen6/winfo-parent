package top.hting.entity.oracle;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 序列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("TBL_SEQUENCE")
public class Seq implements Serializable {

    /**
     * 序列主键
     */
    @TableId("sequenceid")
    private String sequenceid;

    /**
     * 序列名称
     */
    private String fullname;

    /**
     * 序列前缀
     */
    private String prefix;

    /**
     * 序列分隔符
     */
    private String separators;

    /**
     * 年份
     */
    private Short annual;

    /**
     * 升序序列
     */
    private Integer sequence;

    /**
     * 倒序序列
     */
    private Integer reduction;

    /**
     * 步骤
     */
    private Integer step;

    /**
     * 0:否,1:是
     */
    private Short isvisible;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date syscreated;

    /**
     * 创建人
     */
    private String syscreatedby;

    /**
     * 创建机构代码
     */
    private String sysorg;

    /**
     * 创建部门代码
     */
    private String sysdept;

    /**
     * 最后修改时间
     */
    private Date syslastupd;

    /**
     * 最后修改人
     */
    private String syslastupdby;


}
