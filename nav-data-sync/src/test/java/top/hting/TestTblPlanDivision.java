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
import top.hting.entity.oracle.paln.TblPlanDivList;
import top.hting.entity.oracle.paln.TblPlanDivision;
import top.hting.entity.sqlserver.plan.CbsDivisionList;
import top.hting.entity.sqlserver.plan.CbsDivisionPlan;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblMarkMapper;
import top.hting.mapper.oracle.TblPlanDivListMapper;
import top.hting.mapper.oracle.TblPlanDivisionMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.sqlserver.CbsDivisionListMapper;
import top.hting.mapper.sqlserver.CbsDivisionPlanMapper;

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
 * 航标处维护保养计划[Tbl_PlanDivision]
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTblPlanDivision {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的航标处维护计划
    List<TblPlanDivision> successPlanDivision = new ArrayList<>();
    List<TblPlanDivision> failedPlanDivision = new ArrayList<>();

    // 新系统成功失败的航标处，更新或新增
    List<TblPlanDivList> successTblPlanDivList = new ArrayList<>();
    List<TblPlanDivList> failedTblPlanDivList = new ArrayList<>();
    List<TblPlanDivList> successUpdateTblPlanDivList = new ArrayList<>();
    List<TblPlanDivList> failedUpdateTblPlanDivList = new ArrayList<>();


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
    CbsDivisionPlanMapper cbsDivisionPlanMapper;
    @Autowired
    CbsDivisionListMapper cbsDivisionListMapper;

    @Autowired
    TblPlanDivisionMapper tblPlanDivisionMapper;
    @Autowired
    TblPlanDivListMapper tblPlanDivListMapper;

    @Autowired
    TblMarkMapper tblMarkMapper;

    @Test
    public void synTblPlanDivList() {


        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);

        List<CbsDivisionPlan> cbsDivisionPlans = cbsDivisionPlanMapper.selectByMap(params);

        for (CbsDivisionPlan plan : cbsDivisionPlans) {

            TblPlanDivision division = tblPlanDivisionMapper.selectById(plan.getPid());

            if (division == null) {

                division = plan2TblPlan(plan);

                try {
                    tblPlanDivisionMapper.insert(division);
                    successPlanDivision.add(division);

                } catch (Exception e) {
                    division.setSysOrg("出错备注:" + e.getLocalizedMessage());
                    failedPlanDivision.add(division);
                    e.printStackTrace();
                }

                List<CbsDivisionList> divs = cbsDivisionListMapper.findByParams(QueryEntity.builder().parentId(plan.getPid()).build());

                List<TblPlanDivList> divLists = divs2TblDivs(divs);

                for (TblPlanDivList divList : divLists) {

                    try {
                        tblPlanDivListMapper.insert(divList);
                        successTblPlanDivList.add(divList);
                    } catch (Exception e) {

                        divList.setRemark("出错备注:" + e.getLocalizedMessage());
                        failedTblPlanDivList.add(divList);
                        e.printStackTrace();
                    }

                }
            }

        }


    }

    private TblPlanDivision plan2TblPlan(CbsDivisionPlan plan) {
        TblPlanDivision tblPlanDivision = new TblPlanDivision();
        tblPlanDivision.setPlanId(plan.getPid());
        tblPlanDivision.setPlanNumber(generatePlanNumber(plan.getMakeDate(), seq));
        tblPlanDivision.setAnnual(plan.getAnnual());
        tblPlanDivision.setUserId(plan.getMakeUserId());
        tblPlanDivision.setUserName(getName(plan.getMakeUserId()));
        tblPlanDivision.setMakeDate(plan.getMakeDate());
        tblPlanDivision.setMakeOrgCode(plan.getOrgCode());
        tblPlanDivision.setNodeNumber("CWJH06");
        tblPlanDivision.setIsEnable(1);
        tblPlanDivision.setSysCreated(plan.getSysCreated());
        tblPlanDivision.setSysCreatedBy(getName(plan.getSysCreatedBy()));
        tblPlanDivision.setSysOrg(plan.getSysOrg());
        tblPlanDivision.setSysDept(plan.getSysDept());
        tblPlanDivision.setSysLastUpd(plan.getSysLastUpd());
        tblPlanDivision.setSysLastUpdBy(getName(plan.getSysCreatedBy()));

        return tblPlanDivision;
    }

    private List<TblPlanDivList> divs2TblDivs(List<CbsDivisionList> divs) {
        List<TblPlanDivList> result = new ArrayList<>();
        for (CbsDivisionList div : divs) {
            TblMark tblMark = tblMarkMapper.selectById(div.getBasNavigationMarkFID());

            TblPlanDivList divList = new TblPlanDivList();
            divList.setPlanMarkId(div.getPid());
            divList.setPlanId(div.getCbsDivisionPlanFID());
            divList.setMaintainOrgCode(tblMark != null ? tblMark.getMaintainOrgCode() : null);
            divList.setMarkId(div.getBasNavigationMarkFID());
            divList.setMarkTableCode(div.getMarkTable());
            divList.setMarkName(div.getMarkName());
            divList.setMonthly(div.getMonthly());
            divList.setMaintainCode(div.getMaintainCode());
            divList.setRequireShip(div.getRequireShip());
            divList.setRemark(div.getRemark());
            divList.setIsEnable(1);
            divList.setSysCreated(div.getSysCreated());
            divList.setSysCreatedBy(getName(div.getSysCreatedBy()));
            divList.setSysOrg(div.getSysOrg());
            divList.setSysDept(div.getSysDept());
            divList.setSysLastUpd(div.getSysLastUpd());
            divList.setSysLastUpdBy(getName(div.getSysCreatedBy()));

            result.add(divList);
        }

        return result;
    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl航标处维护计划成功", "tbl航标处维护计划成功", ExcelType.XSSF),
                TblPlanDivision.class, successPlanDivision);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl航标处维护计划失败", "tbl航标处维护计划失败", ExcelType.XSSF),
                TblPlanDivision.class, failedPlanDivision);


        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl航标处维护计划航标列表失败", "tbl航标处维护计划航标列表失败", ExcelType.XSSF),
                TblPlanDivList.class, failedTblPlanDivList);

        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl航标处维护计划航标列表成功", "tbl航标处维护计划航标列表成功", ExcelType.XSSF),
                TblPlanDivList.class, successTblPlanDivList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblPlanDivList/" + "tbl航标处维护计划-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblPlanDivList/" + "tbl航标处维护计划-失败.xlsx");
            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPlanDivList/" + "tbl航标处维护计划航标列表-失败.xlsx");
            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPlanDivList/" + "tbl航标处维护计划航标列表-成功.xlsx");


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
