package top.hting.mapper.sqlserver;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.tech.CbsTwoNewsTechDGPS;

import java.util.List;

public interface CbsTwoNewsTechDGPSMapper extends BaseMapper<CbsTwoNewsTechDGPS> {
    List<CbsTwoNewsTechDGPS> findByParentNewsId(QueryEntity queryEntity);


}
