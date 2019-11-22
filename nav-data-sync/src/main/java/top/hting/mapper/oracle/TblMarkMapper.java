package top.hting.mapper.oracle;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.oracle.TblMark;

import java.util.List;

public interface TblMarkMapper extends BaseMapper<TblMark> {

    List<TblMark> findByMarkName(String markName);

    List<TblMark> findByMarkTableCode(String markTableCode);
}
