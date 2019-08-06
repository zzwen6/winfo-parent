package top.hting.mapper.sqlserver.setup;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.setup.CbsPrivateSubMaterial;

import java.util.List;

public interface CbsPrivateSubMaterialMapper extends BaseMapper<CbsPrivateSubMaterial> {
    List<CbsPrivateSubMaterial> findByParentId(QueryEntity entity);



}
