package top.hting.mapper.oracle;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.oracle.TbAnchorage;

/**
 * @author zzwen6
 * @date 2019/11/11 10:10
 */
public interface TbAnchorageMapper1 extends BaseMapper<TbAnchorage> {

    void save(TbAnchorage tbAnchorage);

}
