package top.hting.mapper.sqlserver;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.CbsMarkNews;
import top.hting.entity.sqlserver.VCbsTwoNewsTech;

import java.util.List;

public interface CbsMarkNewsMapper extends BaseMapper<CbsMarkNews> {

    // 查询技术参数视图的数据
    List<VCbsTwoNewsTech> findByParams(QueryEntity queryEntity);




}
