package top.hting.mapper.sqlserver.tech;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.tech.CbsOneNewsTechVoice;

import java.util.List;

public interface CbsOneNewsTechVoiceMapper extends BaseMapper<CbsOneNewsTechVoice> {

    List<CbsOneNewsTechVoice> findByParentNewsId(QueryEntity queryEntity);


}
