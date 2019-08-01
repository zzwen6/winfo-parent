package top.hting.plan;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.QueryEntity;
import top.hting.entity.oracle.Seq;
import top.hting.entity.oracle.TblUser;
import top.hting.entity.oracle.paln.TblPlanSite;
import top.hting.entity.oracle.paln.TblPlanSiteList;
import top.hting.entity.sqlserver.plan.CbsSiteList;
import top.hting.entity.sqlserver.plan.CbsSitePlan;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblPlanSiteListMapper;
import top.hting.mapper.oracle.TblPlanSiteMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.sqlserver.CbsSiteListMapper;
import top.hting.mapper.sqlserver.CbsSitePlanMapper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    SeqMapper seqMapper;

    // 新系统成功、失败的站点维护计划
    List<TblPlanSite> successPlanSites = new ArrayList<>();
    List<TblPlanSite> failedPlanSites = new ArrayList<>();

    // 新系统成功失败的巡检航标，更新或新增
    List<TblPlanSiteList> successTblPlanSiteList = new ArrayList<>();
    List<TblPlanSiteList> failedTblPlanSiteList = new ArrayList<>();
    List<TblPlanSiteList> successUpdateTblPlanSiteList = new ArrayList<>();
    List<TblPlanSiteList> failedUpdateTblPlanSiteList = new ArrayList<>();


    final Map<String, TblUser> userMap = new HashMap<>();

    @Before
    public void init() {
        List<TblUser> tblUsers = tblUserMapper.selectList(null);

        tblUsers.forEach(tblUser -> {
            userMap.put(tblUser.getUserId(), tblUser);
        });
    }


    /**
     * 2019年7月29日 17:43:18 生成的excel中同样是测试处航标站长的数据，可认为不需要同步
     */
    @Test
    public void synTblPlanSiteList() {

        // cbs 所有的站点维护计划(未删除)
        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsSitePlan> cbsSitePlans = cbsSitePlanMapper.selectByMap(params);

        // 获取seq
        Seq seq = seqMapper.selectById("WHJH");

        // 遍历cbs 站点维护计划
        for (CbsSitePlan plan : cbsSitePlans) {

            // tbl中的维护计划
            TblPlanSite planSite = tblPlanSiteMapper.selectById(plan.getPid());

            if (planSite == null) {

                // 转换cbs为tbl数据
                planSite = cbsPlan2TblPlan(plan, seq);

                try {
                    // 执行新增
                    tblPlanSiteMapper.insert(planSite);
                    successPlanSites.add(planSite);
                } catch (Exception e) {
                    planSite.setSysOrg("出错备注:" + e.getLocalizedMessage());
                    failedPlanSites.add(planSite);
                    e.printStackTrace();
                    continue;
                }

                // cbs下的巡检航标
                List<CbsSiteList> cbsSiteLists = cbsSiteListMapper.findByParams(QueryEntity.builder().parentId(plan.getPid()).build());

                if (cbsSiteLists != null && cbsSiteLists.size() > 0) {
                    List<TblPlanSiteList> planSiteLists = cbsSiteList2TblPlanSites(cbsSiteLists);

                    for (TblPlanSiteList site : planSiteLists) {

                        try {
                            tblPlanSiteListMapper.insert(site);

                            successTblPlanSiteList.add(site);
                        } catch (Exception e) {
                            e.printStackTrace();
                            site.setRemark("出错备注:" + e.getLocalizedMessage());

                            failedTblPlanSiteList.add(site);
                        }


                    }

                }

            } else {
                // 存在站点维护计划，更新相关信息 TODO
            }


        }

        // 重新更新序列

        seqMapper.updateValue(Seq.builder().sequenceid(seq.getSequenceid()).sequence(seq.getSequence()).build());

        // 写入文件
        saveFile();

    }

    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl站点维护计划成功", "tbl站点维护计划成功", ExcelType.XSSF),
                TblPlanSite.class, successPlanSites);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl站点维护计划失败", "tbl站点维护计划失败", ExcelType.XSSF),
                TblPlanSite.class, failedPlanSites);


        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl站点维护计划航标列表失败", "tbl站点维护计划航标列表失败", ExcelType.XSSF),
                TblPlanSiteList.class, failedTblPlanSiteList);

        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl站点维护计划航标列表成功", "tbl站点维护计划航标列表成功", ExcelType.XSSF),
                TblPlanSiteList.class, successTblPlanSiteList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblPlanSiteList/" + System.currentTimeMillis()+"tbl站点维护计划-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblPlanSiteList/" + System.currentTimeMillis()+"tbl站点维护计划-失败.xlsx");
            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPlanSiteList/" + System.currentTimeMillis()+"tbl站点维护计划航标列表-失败.xlsx");
            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPlanSiteList/" + System.currentTimeMillis()+"tbl站点维护计划航标列表-成功.xlsx");


            workbook.write(fos);
            workbook1.write(fos1);
            workbook2.write(fos2);
            workbook3.write(fos3);
            // workbook4.write(fos4);
            // workbook5.write(fos5);
            // workbook6.write(fos6);
            // workbook7.write(fos7);

            fos.close();
            fos1.close();
            fos2.close();
            fos3.close();
            // fos4.close();
            // fos5.close();
            // fos6.close();
            // fos7.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // 维护计划航标转换
    private List<TblPlanSiteList> cbsSiteList2TblPlanSites(List<CbsSiteList> cbsSiteLists) {

        List<TblPlanSiteList> result = new ArrayList<>();
        for (CbsSiteList c : cbsSiteLists) {
            TblPlanSiteList planSiteList = new TblPlanSiteList();

            planSiteList.setPlanMarkId(c.getPid());
            planSiteList.setPlanId(c.getCbsSitePlanFID());
            planSiteList.setMarkId(c.getBasNavigationMarkFID());
            planSiteList.setMarkTableCode(c.getMarkTable());
            planSiteList.setMarkName(c.getMarkName());
            planSiteList.setMonthly(c.getMonthly());
            planSiteList.setMaintainCode(c.getMaintainCode());
            planSiteList.setRequireShip(c.getRequireShip());
            planSiteList.setRemark(c.getRemark());
            planSiteList.setSysCreated(c.getSysCreated());
            planSiteList.setSysCreatedBy(getName(c.getSysCreatedBy()));
            planSiteList.setSysOrg(c.getSysOrg());
            planSiteList.setSysDept(c.getSysDept());
            planSiteList.setSysLastUpd(c.getSysLastUpd());
            planSiteList.setSysLastUpdBy(getName(c.getSysLastUpdBy()));
            planSiteList.setIsEnabled(1);

            result.add(planSiteList);
        }


        return result;
    }

    //站点维护计划转换
    private TblPlanSite cbsPlan2TblPlan(CbsSitePlan plan, Seq seq) {

        TblPlanSite site = new TblPlanSite();
        site.setPlanId(plan.getPid());
        site.setPlanNumber(generatePlanNumber(plan.getMakeDate(), seq)); //
        site.setAnnual(plan.getAnnual());
        site.setUserId(plan.getMakeUserId());
        site.setUserName(getName(plan.getMakeUserId())); //
        site.setMakeDate(plan.getMakeDate());
        site.setMakeOrgCode(plan.getOrgCode());
        site.setNodeNumber("ZWJH04"); // TODO 办结
        site.setSysCreated(plan.getSysCreated());
        site.setSysCreatedBy(getName(plan.getSysCreatedBy()));
        site.setSysOrg(plan.getSysOrg());
        site.setSysDept(plan.getSysDept());
        site.setSysLastUpd(plan.getSysLastUpd());
        site.setSysLastUpdBy(getName(plan.getSysLastUpdBy()));
        site.setIsEnabled(1);

        return site;
    }


    private String getName(String userId) {
        return userMap.get(userId) != null ? userMap.get(userId).getUserName() : userId;
    }

    public String generatePlanNumber(Date makeDate, Seq seq) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        String format1 = format.format(makeDate);

        DecimalFormat format2 = new DecimalFormat("000000");
        // 增加1
        seq.setSequence(seq.getSequence() + 1);

        return seq.getSequenceid() + format1 + format2.format(seq.getSequence());
    }


}
