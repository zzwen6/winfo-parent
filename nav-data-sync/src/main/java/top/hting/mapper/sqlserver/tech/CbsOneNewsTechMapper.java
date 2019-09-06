package top.hting.mapper.sqlserver.tech;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.tech.CbsOneNewsTech;
import top.hting.entity.sqlserver.tech.CbsOneNewsTechRadar;

import java.util.List;

public interface CbsOneNewsTechMapper extends BaseMapper<CbsOneNewsTech> {
    List<CbsOneNewsTech> findByParentNewsId(QueryEntity queryEntity);

}
