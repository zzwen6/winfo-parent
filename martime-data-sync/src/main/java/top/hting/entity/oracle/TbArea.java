package top.hting.entity.oracle;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zzwen6
 * @date 2019/11/11 10:34
 */
@TableName("tb_area")
@Data
public class TbArea implements Serializable {

    private int id;

    private String display_name;

    private String organization_uuid;

    private String area_range;

}
