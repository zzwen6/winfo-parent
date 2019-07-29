package top.hting;

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
import top.hting.entity.oracle.paln.TblPlanMark;
import top.hting.entity.oracle.paln.TblPlanMarkList;
import top.hting.entity.oracle.paln.TblPlanSite;
import top.hting.entity.oracle.paln.TblPlanSiteList;
import top.hting.entity.sqlserver.plan.CbsMonthlyList;
import top.hting.entity.sqlserver.plan.CbsMonthlyPlan;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblPlanMarkListMapper;
import top.hting.mapper.oracle.TblPlanMarkMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.sqlserver.CbsMonthlyListMapper;
import top.hting.mapper.sqlserver.CbsMonthlyPlanMapper;

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
 * 航标巡检计划
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTblPlanMarkAndList {

    @Autowired
    TblUserMapper tblUserMapper;

    @Autowired
    SeqMapper seqMapper;

    @Autowired
    TblPlanMarkMapper tblPlanMarkMapper;

    @Autowired
    TblPlanMarkListMapper tblPlanMarkListMapper;

    @Autowired
    CbsMonthlyPlanMapper cbsMonthlyPlanMapper;

    @Autowired
    CbsMonthlyListMapper cbsMonthlyListMapper;


    final Map<String, TblUser> userMap = new HashMap<>();

    // 新系统成功、失败的站点维护计划
    List<TblPlanMark> successPlanMark = new ArrayList<>();
    List<TblPlanMark> failedPlanMark = new ArrayList<>();

    // 新系统成功失败的巡检航标，更新或新增
    List<TblPlanMarkList> successTblPlanMarkList = new ArrayList<>();
    List<TblPlanMarkList> failedTblPlanMarkList = new ArrayList<>();

    @Before
    public void init() {
        List<TblUser> tblUsers = tblUserMapper.selectList(null);

        tblUsers.forEach(tblUser -> {
            userMap.put(tblUser.getUserId(), tblUser);
        });
    }


    /**
     * 同步航标巡检计划和需巡检的航标
     */
    @Test
    public void synTblPlanMarkAndList() {

        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        // 未标记删除的航标巡检计划
        List<CbsMonthlyPlan> cbsMonthlyPlans = cbsMonthlyPlanMapper.selectByMap(params);
        Seq seq = seqMapper.selectById("HBXS");
        for (CbsMonthlyPlan c : cbsMonthlyPlans) {

            TblPlanMark planMark = tblPlanMarkMapper.selectById(c.getPid());

            if (planMark == null) {

                planMark = cbsMonthlyPlan2TblPlanMark(c, seq);

                try {
                    tblPlanMarkMapper.insert(planMark);
                    successPlanMark.add(planMark);
                } catch (Exception e) {
                    planMark.setSysOrg("出错备注:" + e.getLocalizedMessage());
                    failedPlanMark.add(planMark);
                    e.printStackTrace();
                    continue;
                }

                // cbs 需要巡检的航标列表
                List<CbsMonthlyList> monthlyLists = cbsMonthlyListMapper.findByParams(QueryEntity.builder().parentId(c.getPid()).build());
                List<TblPlanMarkList> planMarkLists = cbsMonthlyPlans2PlanMarkLists(monthlyLists);

                for (TblPlanMarkList markList : planMarkLists) {

                    try {
                        tblPlanMarkListMapper.insert(markList);

                        successTblPlanMarkList.add(markList);

                    } catch (Exception e) {
                        markList.setRemark("出错备注:" + e.getLocalizedMessage());
                        failedTblPlanMarkList.add(markList);

                        e.printStackTrace();
                    }
                }


            }

        }
        seqMapper.updateValue(Seq.builder().sequenceid(seq.getSequenceid()).sequence(seq.getSequence()).build());


        saveFile();

    }

    private TblPlanMark cbsMonthlyPlan2TblPlanMark(CbsMonthlyPlan c, Seq seq) {

        TblPlanMark planMark = new TblPlanMark();
        planMark.setPlanId(c.getPid());
        planMark.setPlanNumber(generatePlanNumber(c.getMakeDate(), seq)); // TODO
        planMark.setAnnual(c.getAnnual());
        planMark.setMonthly(c.getMonthly());
        planMark.setUserId(c.getMakeUserId());
        planMark.setUserName(getName(c.getMakeUserId()));
        planMark.setMakeDate(c.getMakeDate());
        planMark.setMakeOrgCode(c.getMakeOrgCode());
        planMark.setNodeNumber("XJJH05");
        planMark.setSysCreated(c.getSysCreated());
        planMark.setSysCreatedBy(getName(c.getSysCreatedBy()));
        planMark.setSysOrg(c.getSysOrg());
        planMark.setSysDept(c.getSysDept());
        planMark.setSysLastUpd(c.getSysLastUpd());
        planMark.setSysLastUpdBy(getName(c.getSysLastUpdBy()));
        return planMark;
    }

    private List<TblPlanMarkList> cbsMonthlyPlans2PlanMarkLists(List<CbsMonthlyList> monthlyLists) {

        List<TblPlanMarkList> result = new ArrayList<>();

        for (CbsMonthlyList m : monthlyLists) {

            TblPlanMarkList markList = new TblPlanMarkList();
            markList.setPlanMarkId(m.getPid());
            markList.setPlanId(m.getCbsMonthlyPlanFID());
            markList.setMarkId(m.getBasNavigationMarkFID());
            markList.setInspectId(null);
            markList.setMarkTableCode(m.getMarkTable());
            markList.setMarkName(m.getMarkName());
            markList.setInspectDate(m.getInspectDate());
            markList.setRemark(m.getRemark());
            markList.setIsFinished((short) (1));
            markList.setEndInspectDate(m.getEndInspectDate());
            markList.setSysCreated(m.getSysCreated());
            markList.setSysCreatedBy(getName(m.getSysCreatedBy()));
            markList.setSysOrg(m.getSysOrg());
            markList.setSysDept(m.getSysDept());
            markList.setSysLastUpd(m.getSysLastUpd());
            markList.setSysLastUpdBy(getName(m.getSysLastUpdBy()));
            markList.setIsEnable(1);

            result.add(markList);
        }

        return result;
    }

    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl航标巡检计划成功", "tbl航标巡检计划成功", ExcelType.XSSF),
                TblPlanMark.class, successPlanMark);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl航标巡检计划失败", "tbl航标巡检计划失败", ExcelType.XSSF),
                TblPlanMark.class, failedPlanMark);


        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl航标巡检计划失败航标列表失败", "tbl航标巡检计划失败航标列表失败", ExcelType.XSSF),
                TblPlanMarkList.class, failedTblPlanMarkList);

        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl航标巡检计划航标列表成功", "tbl航标巡检计划航标列表成功", ExcelType.XSSF),
                TblPlanMarkList.class, successTblPlanMarkList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblPlanMarkAndList/" + "tbl航标巡检计划-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblPlanMarkAndList/" + "tbl航标巡检计划-失败.xlsx");
            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPlanMarkAndList/" + "tbl航标巡检计划失败航标列表-失败.xlsx");
            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPlanMarkAndList/" + "tbl航标巡检计划失败航标列表-成功.xlsx");


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
