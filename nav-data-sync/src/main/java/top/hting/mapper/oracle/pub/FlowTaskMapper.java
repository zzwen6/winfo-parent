package top.hting.mapper.oracle.pub;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.oracle.act.FlowTask;

import java.util.List;

public interface FlowTaskMapper extends BaseMapper<FlowTask> {

    List<FlowTask> selectByInstatncId(String instanceId);

}
