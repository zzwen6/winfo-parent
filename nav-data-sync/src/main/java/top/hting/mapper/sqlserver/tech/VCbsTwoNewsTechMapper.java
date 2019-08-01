package top.hting.mapper.sqlserver.tech;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.VCbsTwoNewsTech;

import java.util.List;

public interface VCbsTwoNewsTechMapper extends BaseMapper<VCbsTwoNewsTech> {

    List<VCbsTwoNewsTech> findByParentNewsId(QueryEntity queryEntity);

}
