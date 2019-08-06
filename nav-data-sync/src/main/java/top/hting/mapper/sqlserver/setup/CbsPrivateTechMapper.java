package top.hting.mapper.sqlserver.setup;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.setup.CbsPrivateTech;

import java.util.List;

public interface CbsPrivateTechMapper extends BaseMapper<CbsPrivateTech> {
    List<CbsPrivateTech> findByParams(QueryEntity entity);

}
