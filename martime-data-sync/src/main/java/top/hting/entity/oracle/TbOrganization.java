package top.hting.entity.oracle;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zzwen6
 * @date 2019/11/11 10:43
 */
@Data
@TableName("tb_organization")
public class TbOrganization implements Serializable {
    @TableId("id")
    private Integer id;
    private String uuid;
    private String parent_uuid;
    private String display_name;
    private String code_fees;
    private String code_4a;
    private String code_district;
    private Integer level;
    private Integer is_forbidden;
}
