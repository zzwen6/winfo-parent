package top.hting.mapper.oracle;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.oracle.TbDistrict;

/**
 * @author zzwen6
 * @date 2019/11/11 10:11
 */
public interface TbDistrictMapper1 extends BaseMapper<TbDistrict> {
    void save(TbDistrict tbDistrict);
}
