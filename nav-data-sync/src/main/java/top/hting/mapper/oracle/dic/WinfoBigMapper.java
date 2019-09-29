package top.hting.mapper.oracle.dic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.hting.entity.oracle.dic.WinfoBig;

public interface WinfoBigMapper extends BaseMapper<WinfoBig> {


    int updateVersionCode(@Param("bid") long bid, @Param("versionCode") long versionCode);

    int updateName(@Param("bid") long bid, @Param("name") String name);


}
