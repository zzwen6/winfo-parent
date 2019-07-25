package top.hting.entity.sqlserver;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 航标动态
 */
@Data
@TableName("Cbs_Mark_News")
public class CbsMarkNews extends Base{

    @TableId("pid")
    private String pid;
    private String sourceCode;
    private String sourceParentFID;
    private String releaseCode;
    private String orgCode;
    private String declareCode;
    private String title;
    private Date declareTime;
    private String newsCode;
    private String contents;
    private boolean recovery;
    private String unitName;
    private String reportOrg;
    private String copyOrg;

    // 二类的
    private String orgCodeR;
    private String declareCodeR;
    private String titleR;
    private Date declareTimeR;
    private String contentsR;
    private String unitNameR;
    private String reportOrgR;
    private String copyOrgR;


}
