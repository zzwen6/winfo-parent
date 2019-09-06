package top.hting.mapper.sqlserver.tech;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.tech.CbsOneNewsTech;
import top.hting.entity.sqlserver.tech.CbsOneNewsTechDGPS;

import java.util.List;

public interface CbsOneNewsTechDGPSMapper extends BaseMapper<CbsOneNewsTechDGPS> {

    List<CbsOneNewsTechDGPS> findByParentNewsId(QueryEntity queryEntity);

}
