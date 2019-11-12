package top.hting.entity.oracle;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zzwen6
 * @date 2019/11/11 10:16
 */
@Data
@TableName("CBDT_DICT_MSAORG")
public class CbdtDict {

    private String org_code;

    private String login_org_code;

    private String parent_org_code;

    private String org_name;

    private String org_level;

}
