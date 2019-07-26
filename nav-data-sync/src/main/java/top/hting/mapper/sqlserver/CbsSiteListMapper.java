package top.hting.mapper.sqlserver;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.plan.CbsSiteList;

import java.util.List;

public interface CbsSiteListMapper extends BaseMapper<CbsSiteList> {
    List<CbsSiteList> findByParams(QueryEntity entity);

}
