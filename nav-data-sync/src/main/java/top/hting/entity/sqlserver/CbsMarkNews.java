package top.hting.entity.sqlserver;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 航标动态
 */
@Data
@TableName("Cbs_Mark_News")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsMarkNews extends Base{

    @TableId("pid")
    @Excel(name = "pid")
    private String pid;
    @Excel(name = "sourceCode")
    private String sourceCode;
    @Excel(name = "sourceParentFID")
    private String sourceParentFID;
    @Excel(name = "releaseCode")
    private String releaseCode;
    @Excel(name = "orgCode")
    private String orgCode;
    @Excel(name = "declareCode")
    private String declareCode;
    @Excel(name = "title")
    private String title;
    @Excel(name = "declareTime")
    private Date declareTime;
    @Excel(name = "newsCode")
    private String newsCode;
    @Excel(name = "contents")
    private String contents;
    @Excel(name = "recovery")
    private boolean recovery;
    @Excel(name = "unitName")
    private String unitName;
    @Excel(name = "reportOrg")
    private String reportOrg;
    @Excel(name = "copyOrg")
    private String copyOrg;

    // 二类的
    @Excel(name = "orgCodeR")
    private String orgCodeR;
    @Excel(name = "declareCodeR")
    private String declareCodeR;
    @Excel(name = "titleR")
    private String titleR;
    @Excel(name = "declareTimeR")
    private Date declareTimeR;
    @Excel(name = "contentsR")
    private String contentsR;
    @Excel(name = "unitNameR")
    private String unitNameR;
    @Excel(name = "reportOrgR")
    private String reportOrgR;
    @Excel(name = "copyOrgR")
    private String copyOrgR;


}
