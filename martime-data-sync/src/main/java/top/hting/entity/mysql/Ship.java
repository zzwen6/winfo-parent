package top.hting.entity.mysql;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zzwen6
 * @date 2019/11/11 10:56
 */
@Data
@TableName("SHIPMAPPER")
public class Ship implements Serializable {
    @TableId("id")
    private Integer id;
    private String CBZJ;
    private String CBDJH;
    private String CBSBH;
    private String CCDJH;
    private String CJDJH;
    private String CYZWCM;
    private String ZWCM;
    private String YWCM;
    private String MMSI;
    private String IMO;
    private String HH;
    private Date update_time;
}
