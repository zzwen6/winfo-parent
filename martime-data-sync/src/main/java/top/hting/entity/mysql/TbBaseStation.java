package top.hting.entity.mysql;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zzwen6
 * @date 2019/11/11 10:39
 */
@Data
@TableName("tb_base_station")
public class TbBaseStation {
    @TableId("id")
    private Integer id;
    private Integer serial_number;
    private String chinese_name;
    private String english_name;
    private String location;
    private String manage_organizaion;
    private int control_points_number;
    private String control_points_location;
    private String phone;
    private String distance;
    private Double height;
    private String create_time;
    private String change_time;
    private String org_name;
}
