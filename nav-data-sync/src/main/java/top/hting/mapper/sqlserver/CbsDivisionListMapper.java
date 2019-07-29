package top.hting.mapper.sqlserver;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.plan.CbsDivisionList;

import java.util.List;

public interface CbsDivisionListMapper extends BaseMapper<CbsDivisionList> {
    List<CbsDivisionList> findByParams(QueryEntity entity);


}
