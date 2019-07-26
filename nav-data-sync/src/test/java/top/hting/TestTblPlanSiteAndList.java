package top.hting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.oracle.paln.TblPlanSite;
import top.hting.entity.sqlserver.plan.CbsSitePlan;
import top.hting.mapper.oracle.TblPlanSiteListMapper;
import top.hting.mapper.oracle.TblPlanSiteMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.sqlserver.CbsSiteListMapper;
import top.hting.mapper.sqlserver.CbsSitePlanMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 站点维护保养计划，计划航标同步
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTblPlanSiteAndList {

    @Autowired
    TblUserMapper tblUserMapper;

    @Autowired
    TblPlanSiteMapper tblPlanSiteMapper;
    @Autowired
    TblPlanSiteListMapper tblPlanSiteListMapper;
    @Autowired
    CbsSitePlanMapper cbsSitePlanMapper;
    @Autowired
    CbsSiteListMapper cbsSiteListMapper;

    @Test
    public void synTblPlanSiteList(){

        // cbs 所有的站点维护计划
        Map<String,Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsSitePlan> cbsSitePlans = cbsSitePlanMapper.selectByMap(params);

        System.out.println(cbsSitePlans.size());


    }


}
