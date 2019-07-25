package top.hting.mapper.sqlserver;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.tech.CbsTwoNewsTech;

import java.util.List;

public interface CbsTwoNewsTechMapper extends BaseMapper<CbsTwoNewsTech> {

    List<CbsTwoNewsTech> findByParentNewsId(QueryEntity queryEntity);


}
