package top.hting;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.QueryEntity;
import top.hting.entity.oracle.Seq;
import top.hting.entity.oracle.TblMark;
import top.hting.entity.oracle.TblUser;
import top.hting.entity.oracle.paln.TblPlanArea;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.oracle.paln.TblPlanDivList;
import top.hting.entity.oracle.paln.TblPlanDivision;
import top.hting.entity.sqlserver.plan.CbsAreaList;
import top.hting.entity.sqlserver.plan.CbsAreaPlan;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblMarkMapper;
import top.hting.mapper.oracle.TblPlanAreaListMapper;
import top.hting.mapper.oracle.TblPlanAreaMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.sqlserver.CbsAreaListMapper;
import top.hting.mapper.sqlserver.CbsAreaPlanMapper;

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
 * 海区维护保养计划
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTblAreaPlan {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的海区维护计划
    List<TblPlanArea> successTblAreaPlan = new ArrayList<>();
    List<TblPlanArea> failedTblAreaPlan = new ArrayList<>();

    // 新系统成功失败的海区，更新或新增
    List<TblPlanAreaList> successTblAreaList = new ArrayList<>();
    List<TblPlanAreaList> failedTblAreaList = new ArrayList<>();
    List<TblPlanAreaList> successUpdateTblAreaList = new ArrayList<>();
    List<TblPlanAreaList> failedUpdateTblAreaList = new ArrayList<>();


    final Map<String, TblUser> userMap = new HashMap<>();
    Seq seq = null;

    @Before
    public void init() {
        List<TblUser> tblUsers = tblUserMapper.selectList(null);

        tblUsers.forEach(tblUser -> {
            userMap.put(tblUser.getUserId(), tblUser);
        });
        seq = seqMapper.selectById("WHJH");

    }

    @After
    public void after() {
        // 重新更新序列
        seqMapper.updateValue(Seq.builder().sequenceid(seq.getSequenceid()).sequence(seq.getSequence()).build());
        // 写入文件
        saveFile();
    }


    @Autowired
    CbsAreaPlanMapper cbsAreaPlanMapper;
    @Autowired
    CbsAreaListMapper cbsAreaListMapper;
    @Autowired
    TblPlanAreaMapper tblPlanAreaMapper;
    @Autowired
    TblPlanAreaListMapper tblPlanAreaListMapper;
    @Autowired
    TblMarkMapper tblMarkMapper;

    /**
     * 海区维护计划同步
     */
    @Test
    public void synTblPlanArea() {
        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsAreaPlan> cbsAreaPlans = cbsAreaPlanMapper.selectByMap(params);

        for (CbsAreaPlan plan : cbsAreaPlans) {

            TblPlanArea planArea = tblPlanAreaMapper.selectById(plan.getPid());
            if (planArea == null) {

                planArea = cbsPlan2TblPlan(plan);

                try {
                    tblPlanAreaMapper.insert(planArea);
                    successTblAreaPlan.add(planArea);

                } catch (Exception e) {
                    planArea.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblAreaPlan.add(planArea);
                    e.printStackTrace();
                    continue;
                }

                List<CbsAreaList> cbsAreaLists = cbsAreaListMapper.findByParams(QueryEntity.builder().parentId(plan.getPid()).build());

                List<TblPlanAreaList> planAreaLists = cbsAreaLists2TblAreaList(cbsAreaLists);

                for (TblPlanAreaList areaList : planAreaLists) {

                    try {
                        tblPlanAreaListMapper.insert(areaList);
                        successTblAreaList.add(areaList);
                    } catch (Exception e) {
                        areaList.setRemark("出错备注:" + e.getLocalizedMessage());

                        failedTblAreaList.add(areaList);

                        e.printStackTrace();
                    }

                }


            }

        }

    }

    private TblPlanArea cbsPlan2TblPlan(CbsAreaPlan plan) {

        TblPlanArea area = new TblPlanArea();
        area.setPlanId(plan.getPid());
        area.setPlanNumber(generatePlanNumber(plan.getMakeDate(), seq));
        area.setAnnual(plan.getAnnual());
        area.setUserId(plan.getMakeUserId());
        area.setUserName(getName(plan.getMakeUserId()));
        area.setMakeDate(plan.getMakeDate());
        area.setMakeOrgCode(plan.getOrgCode());
        area.setNodeNumber("QWJH04");
        area.setIsEnabled(1);
        area.setRemark("");
        area.setSysCreated(plan.getSysCreated());
        area.setSysCreatedBy(getName(plan.getSysCreatedBy()));
        area.setSysOrg(plan.getSysOrg());
        area.setSysDept(plan.getSysDept());
        area.setSysLastUpd(plan.getSysLastUpd());
        area.setSysLastUpdBy(getName(plan.getSysLastUpdBy()));

        return area;
    }

    private List<TblPlanAreaList> cbsAreaLists2TblAreaList(List<CbsAreaList> cbsAreaLists) {
        List<TblPlanAreaList> result = new ArrayList<>();
        for (CbsAreaList c : cbsAreaLists) {
            TblMark tblMark = tblMarkMapper.selectById(c.getBasNavigationMarkFID());

            TblPlanAreaList tbl = new TblPlanAreaList();
            tbl.setPlanMarkId(c.getPid());
            tbl.setPlanId(c.getCbsAreaPlanFID());
            tbl.setManageOrgCode(tblMark.getManageOrgCode());
            tbl.setMaintainOrgCode(tblMark.getMaintainOrgCode());
            tbl.setMarkId(c.getBasNavigationMarkFID());
            tbl.setMarkTableCode(c.getMarkTable());
            tbl.setMarkName(c.getMarkName());
            tbl.setMonthly(c.getMonthly());
            tbl.setMaintainCode(c.getMaintainCode());
            tbl.setRequireShip(c.getRequireShip());
            tbl.setRemark(c.getRemark());
            tbl.setIsFinished(new Short("1"));

            tbl.setEndInspectDate(null); // TODO 需要在插件维护记录后拿到值
            tbl.setMaintainId(null);

            tbl.setIsEnabled(1);
            tbl.setSysCreated(c.getSysCreated());
            tbl.setSysCreatedBy(getName(c.getSysCreatedBy()));
            tbl.setSysOrg(c.getSysOrg());
            tbl.setSysDept(c.getSysDept());
            tbl.setSysLastUpd(c.getSysLastUpd());
            tbl.setSysLastUpdBy(getName(c.getSysCreatedBy()));

            result.add(tbl);
        }
        return result;

    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl海区维护计划成功", "tbl海区维护计划成功", ExcelType.XSSF),
                TblPlanArea.class, successTblAreaPlan);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl海区维护计划失败", "tbl海区维护计划失败", ExcelType.XSSF),
                TblPlanArea.class, failedTblAreaPlan);


        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl海区维护计划航标列表失败", "tbl海区维护计划航标列表失败", ExcelType.XSSF),
                TblPlanAreaList.class, failedTblAreaList);

        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl海区维护计划航标列表成功", "tbl海区维护计划航标列表成功", ExcelType.XSSF),
                TblPlanAreaList.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl海区维护计划-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl海区维护计划-失败.xlsx");
            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl海区维护计划航标列表-失败.xlsx");
            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl海区维护计划航标列表-成功.xlsx");


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
