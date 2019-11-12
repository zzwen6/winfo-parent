package top.hting.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.mysql.ShipData;

import java.util.List;

/**
 * @author zzwen6
 * @date 2019/11/11 10:14
 */
public interface ShipDataMapper extends BaseMapper<ShipData> {
    List<ShipData> list();
}
