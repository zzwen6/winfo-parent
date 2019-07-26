package top.hting.mapper.sqlserver;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.entity.QueryEntity;
import top.hting.entity.sqlserver.plan.CbsMonthlyList;

import java.util.List;

public interface CbsMonthlyListMapper extends BaseMapper<CbsMonthlyList> {


    List<CbsMonthlyList> findByParams(QueryEntity entity);

}
