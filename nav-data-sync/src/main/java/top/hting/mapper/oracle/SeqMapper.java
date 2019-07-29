package top.hting.mapper.oracle;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.oracle.Seq;

public interface SeqMapper extends BaseMapper<Seq> {

    void updateValue(Seq seq);

}
