package top.hting.entity.sqlserver.setup;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.Base;

import java.io.Serializable;

/**
 * 设置申请提交材料
 */
@Data
@TableName("Cbs_Private_Sub")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CbsPrivateSub extends Base implements Serializable {

    @TableId("Pid")
    private String Pid;

    private String CbsPrivateMarkFID;

    private Integer SerialNumber;

    private String CbsItemMaterialFID;

    private String TypeCode;

    private Integer Quantity;

    private String MaterialName;

}
