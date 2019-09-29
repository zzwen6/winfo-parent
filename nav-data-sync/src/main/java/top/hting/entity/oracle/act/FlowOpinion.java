package top.hting.entity.oracle.act;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.hting.entity.OBase;

import java.util.Date;

@Data
@TableName("TBL_FLOWOPINION")
public class FlowOpinion extends OBase {

    /**
     * 意见主键
     */
    private String opinionid;

    /**
     * 任务主键
     */
    private String taskid;

    /**
     * 节点编号
     */
    private String nodenumber;

    /**
     * 序号
     */
    private Integer serialnumber;

    /**
     * 机构代码
     */
    private String orgcode;

    /**
     * 审核人主键
     */
    private String userid;

    /**
     * 审核人姓名
     */
    private String username;

    /**
     * 审核时间
     */
    private Date audittime;

    /**
     * 下一节点编号
     */
    private String nextnodenumber;
}
