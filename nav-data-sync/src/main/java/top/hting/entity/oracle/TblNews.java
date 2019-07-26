package top.hting.entity.oracle;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
 * 航标动态
 */
@Data
@ToString
@TableName("tbl_news")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TblNews implements Serializable {

    @TableId(value = "newsId")
    @Excel(name = "newsId")
    private String newsId;

    @Excel(name = "businessId")
    private String businessId;

    @Excel(name = "newsCode")
    private String newsCode;

    @Excel(name = "orgCode")
    private String orgCode;

    @Excel(name = "applyTime")
    private Date applyTime;

    @Excel(name = "userId")
    private String userId;

    @Excel(name = "userName")
    private String userName;

    @Excel(name = "applyNumber")
    private String applyNumber;

    @Excel(name = "title")
    private String title;
    @Excel(name = "contents")
    private String contents;
    @Excel(name = "contents2")
    private String contents2;



    @Excel(name = "publishCode")
    private String publishCode;

    @Excel(name = "recovery")
    private Short recovery;

    @Excel(name = "publishOrg")
    private String publishOrg;

    @Excel(name = "reportOrg")
    private String reportOrg;

    @Excel(name = "copyOrg")
    private String copyOrg;

    @Excel(name = "orgCode2")
    private String orgCode2;

    @Excel(name = "applyTime2")
    private Date applyTime2;

    @Excel(name = "userId2")
    private String userId2;

    @Excel(name = "userName2")
    private String userName2;

    @Excel(name = "applyNumber2")
    private String applyNumber2;

    @Excel(name = "title2")
    private String title2;

    @Excel(name = "publishCode2")
    private String publishCode2;

    @Excel(name = "publishOrg2")
    private String publishOrg2;

    @Excel(name = "reportOrg2")
    private String reportOrg2;

    @Excel(name = "copyOrg2")
    private String copyOrg2;

    @Excel(name = "nodeNumber")
    private String nodeNumber;

    @Excel(name = "sysCreated")
    private Date sysCreated;

    @Excel(name = "sysCreatedby")
    private String sysCreatedby;

    @Excel(name = "sysOrg")
    private String sysOrg;

    @Excel(name = "sysDept")
    private String sysDept;

    @Excel(name = "sysLastUpd")
    private Date sysLastUpd;

    @Excel(name = "sysLastUpdBy")
    private String sysLastUpdBy;

}
