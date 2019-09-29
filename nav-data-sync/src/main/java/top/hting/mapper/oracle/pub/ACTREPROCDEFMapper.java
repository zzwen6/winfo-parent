package top.hting.mapper.oracle.pub;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.hting.entity.oracle.act.ACTREPROCDEF;

import java.util.List;

public interface ACTREPROCDEFMapper extends BaseMapper<ACTREPROCDEF> {


    List<ACTREPROCDEF> maxProcDef(@Param("code") String code);

}
