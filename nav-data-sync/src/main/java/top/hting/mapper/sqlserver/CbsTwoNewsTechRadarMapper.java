package top.hting.mapper.sqlserver;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.tech.CbsTwoNewsTechRadar;

import java.util.List;

public interface CbsTwoNewsTechRadarMapper extends BaseMapper<CbsTwoNewsTechRadar> {

    List<CbsTwoNewsTechRadar> findByParentNewsId(QueryEntity queryEntity);

}
