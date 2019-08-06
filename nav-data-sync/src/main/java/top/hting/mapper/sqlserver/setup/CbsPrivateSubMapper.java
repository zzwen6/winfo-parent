package top.hting.mapper.sqlserver.setup;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.setup.CbsPrivateSub;

import java.util.List;

public interface CbsPrivateSubMapper extends BaseMapper<CbsPrivateSub> {

    List<CbsPrivateSub> findByParams(QueryEntity entity);

}
