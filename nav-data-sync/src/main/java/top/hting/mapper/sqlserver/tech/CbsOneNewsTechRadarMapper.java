package top.hting.mapper.sqlserver.tech;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.tech.CbsOneNewsTechRadar;

import java.util.List;

public interface CbsOneNewsTechRadarMapper extends BaseMapper<CbsOneNewsTechRadar> {
    List<CbsOneNewsTechRadar> findByParentNewsId(QueryEntity queryEntity);

}
