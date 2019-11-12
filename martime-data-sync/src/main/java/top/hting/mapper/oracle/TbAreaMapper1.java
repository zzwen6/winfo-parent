package top.hting.mapper.oracle;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.oracle.TbArea;

/**
 * @author zzwen6
 * @date 2019/11/11 10:11
 */
public interface TbAreaMapper1 extends BaseMapper<TbArea> {

    void save(TbArea tbArea);

}
