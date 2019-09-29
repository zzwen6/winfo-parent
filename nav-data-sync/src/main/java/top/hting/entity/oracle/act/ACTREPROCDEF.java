package top.hting.entity.oracle.act;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ACT_RE_PROCDEF")
public class ACTREPROCDEF {

    @TableId("id_")
    private String id_;

    private String key_;

    private Long version_;

    private String deployment_id_;


}
