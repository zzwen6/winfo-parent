package top.hting.mapper.sqlserver;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.tech.CbsTwoNewsTechAis;

import java.util.List;

public interface CbsTwoNewsTechAisMapper extends BaseMapper<CbsTwoNewsTechAis> {

    List<CbsTwoNewsTechAis> findByParentNewsId(QueryEntity queryEntity);

}
