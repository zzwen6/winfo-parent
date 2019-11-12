package top.hting.entity.mysql;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zzwen6
 * @date 2019/11/11 10:51
 */
@TableName("tb_district")
@Data
public class TbDistrict implements Serializable {
    @TableId("id")
    private Integer id;
    private String name;
    private String level;
    private String parent;
    private String code;
    private String bd_coordinator;
    private String gps_coordinator;
}
