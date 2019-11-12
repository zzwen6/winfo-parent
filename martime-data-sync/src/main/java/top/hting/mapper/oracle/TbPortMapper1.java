package top.hting.mapper.oracle;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.oracle.TbPort;

/**
 * @author zzwen6
 * @date 2019/11/11 10:13
 */
public interface TbPortMapper1 extends BaseMapper<TbPort> {
    void save(TbPort port);
}
