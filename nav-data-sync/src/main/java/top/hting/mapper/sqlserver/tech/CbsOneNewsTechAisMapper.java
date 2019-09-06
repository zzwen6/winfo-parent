package top.hting.mapper.sqlserver.tech;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.tech.CbsOneNewsTechAis;

import java.util.List;

public interface CbsOneNewsTechAisMapper extends BaseMapper<CbsOneNewsTechAis> {
    List<CbsOneNewsTechAis> findByParentNewsId(QueryEntity queryEntity);


}
