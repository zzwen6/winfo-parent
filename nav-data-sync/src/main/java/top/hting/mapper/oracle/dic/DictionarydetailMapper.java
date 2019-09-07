package top.hting.mapper.oracle.dic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.oracle.dic.Dictionarydetail;

import java.util.List;

public interface DictionarydetailMapper  extends BaseMapper<Dictionarydetail> {

    List<Dictionarydetail> findByDictCode(String dictCode);

}
