package top.hting.mapper.oracle.pub;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.oracle.act.ActHiActInst;
import top.hting.entity.oracle.pub.PubInstance;

import java.util.List;

public interface ActHiActInstMapper extends BaseMapper<ActHiActInst> {

    /**
     * 公用无流程的数据
     */

    List<PubInstance> listSetupPubNoInstanceId();

}
