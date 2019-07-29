package top.hting.mapper.sqlserver;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.plan.CbsAreaList;

import java.util.List;

public interface CbsAreaListMapper extends BaseMapper<CbsAreaList> {
    List<CbsAreaList> findByParams(QueryEntity entity);

}
