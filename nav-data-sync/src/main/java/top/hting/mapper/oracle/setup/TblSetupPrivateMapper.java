package top.hting.mapper.oracle.setup;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.oracle.setup.SetupPriItem;
import top.hting.entity.oracle.setup.SetupPrivateInfoVO;
import top.hting.entity.oracle.setup.TblSetupPrivate;

import java.util.List;

public interface TblSetupPrivateMapper extends BaseMapper<TblSetupPrivate> {
    SetupPrivateInfoVO selectInfoById(String setupId);

    List<SetupPriItem> selectBySetupId(String setupId);


    int updateTblSetupPrivateEnd(TblSetupPrivate setupPrivate);

}
