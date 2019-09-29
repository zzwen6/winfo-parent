package top.hting.entity.oracle.pub;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.hting.entity.OBase;

@Data
@TableName("TBL_SETUPPUBLIC")
public class TblSetupPublic extends OBase {

    @TableId("setupid")
    private String setupId;

    private String projectName;

    private String nodeNumber;


}
