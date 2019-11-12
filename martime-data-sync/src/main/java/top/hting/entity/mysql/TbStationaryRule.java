package top.hting.entity.mysql;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zzwen6
 * @date 2019/11/11 10:49
 */
@Data
@TableName("tb_stationary_rule")
public class TbStationaryRule {
    @TableId("id")
    private Integer id;
    private String ship_type;
    private Integer port_id;
    private String area;
    private Integer enter_speed;
    private Integer enter_time;
    private Integer leave_speed;
    private Integer leave_time;
}
