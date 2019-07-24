package top.hting.entity.oracle;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@TableName("tbl_news")
public class TblNews implements Serializable {

    @TableId(value = "newsId")
    private String newsId;

    private String businessId;

    private String newsCode;

    private String orgCode;

    private Date applyTime;

    private String userId;

    private String userName;

    private String applyNumber;

    private String title;

    private String publishCode;

    private Short recovery;

    private String publishOrg;

    private String reportOrg;

    private String copyOrg;

    private String orgCode2;

    private Date applyTime2;

    private String userId2;

    private String userName2;

    private String applyNumber2;

    private String title2;

    private String publishCode2;

    private String publishOrg2;

    private String reportOrg2;

    private String copyOrg2;

    private String nodeNumber;

    private Date sysCreated;

    private String sysCreatedby;

    private String sysOrg;

    private String sysDept;

    private Date sysLastUpd;

    private String sysLastUpdBy;

}
