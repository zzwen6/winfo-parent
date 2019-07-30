package top.hting.mapper.sqlserver.tech;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.tech.CbsTwoNewsTechVoice;

import java.util.List;

public interface CbsTwoNewsTechVoiceMapper extends BaseMapper<CbsTwoNewsTechVoice> {

    List<CbsTwoNewsTechVoice> findByParentNewsId(QueryEntity queryEntity);

}
