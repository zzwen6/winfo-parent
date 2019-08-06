package top.hting.entity.sqlserver.setup;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.io.Serializable;

/**
 * 提交材料附件
 */
@Data
@TableName("Cbs_Private_Sub_Material")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsPrivateSubMaterial extends Base implements Serializable {

    @TableId("Pid")
    private String Pid;

    private String CbsPrivateSubFID;

    private Integer SerialNumber;

    private String AnnexName;

    private String SaveFolder;

    private String SaveName;
}
