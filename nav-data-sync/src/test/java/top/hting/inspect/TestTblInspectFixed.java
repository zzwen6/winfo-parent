package top.hting.inspect;

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
import top.hting.entity.oracle.Seq;
import top.hting.entity.oracle.TblUser;
import top.hting.entity.oracle.inspect.TblInspectFixed;
import top.hting.entity.oracle.paln.TblPlanArea;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.sqlserver.inspect.CbsInspectFixedNew;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.inspect.TblInspectFixedMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.sqlserver.inspect.CbsInspectFixedNewMapper;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTblInspectFixed {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的固定标志巡检记录
    List<TblInspectFixed> successTblInspectFixed = new ArrayList<TblInspectFixed>();
    List<TblInspectFixed> failedTblInspectFixed = new ArrayList<TblInspectFixed>();

    // 新系统成功失败的海区，更新或新增
//    List<TblPlanAreaList> successTblAreaList = new ArrayList<>();
//    List<TblPlanAreaList> failedTblAreaList = new ArrayList<>();
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
        seq = seqMapper.selectById("HBXJ");

    }

    @After
    public void after() {
        // 重新更新序列
        seqMapper.updateValue(Seq.builder().sequenceid(seq.getSequenceid()).sequence(seq.getSequence()).build());
        // 写入文件
        saveFile();
    }

    @Autowired
    CbsInspectFixedNewMapper cbsInspectFixedNewMapper;
    @Autowired
    TblInspectFixedMapper tblInspectFixedMapper;

    /**
     * 同步  固定标志巡检记录
     */
    @Test
    public void synTblInspectFixed() {
        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsInspectFixedNew> cbsInspectFixedNews = cbsInspectFixedNewMapper.selectByMap(params);

        for (CbsInspectFixedNew fixedNew : cbsInspectFixedNews) {


            TblInspectFixed inspectFixed = tblInspectFixedMapper.selectById(fixedNew.getPid());

            if (inspectFixed == null) {

                inspectFixed = cbs2tbl(fixedNew);


                try {
                    tblInspectFixedMapper.insert(inspectFixed);

                    successTblInspectFixed.add(inspectFixed);


                } catch (Exception e) {
                    inspectFixed.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblInspectFixed.add(inspectFixed);

                    e.printStackTrace();
                }

            }



        }


    }

    private TblInspectFixed cbs2tbl(CbsInspectFixedNew f) {
        TblInspectFixed fixed = new TblInspectFixed();
        fixed.setInspectId(f.getPid());
        fixed.setPlanMarkId(f.getCbsInspectListFID());
        fixed.setInspectNumber(generatePlanNumber(f.getInspectTime(), seq));
        fixed.setInspectTime(f.getInspectTime());
        fixed.setExaminer(f.getExaminer());
        fixed.setShipId(f.getBscShipFID());
        fixed.setOrgCode(f.getOrgCode());
        fixed.setMarkId(f.getBasNavigationMarkFID());
        fixed.setMarkTableCode(f.getMarkTable());
        fixed.setMarkName(f.getMarkName());
        fixed.setLightingCode(f.getLightingCode());
        fixed.setLightingDeal(f.getLightingDeal());
        fixed.setLightingBreakReasonCode(f.getLightingBreakReason());
        fixed.setLightingWorkVoltage(null);
        fixed.setLightingWorkCurrent(null);
        fixed.setLightCode(f.getLightCode());
        fixed.setLightDeal(f.getLightDeal());
        fixed.setLightQualityCode(f.getLightQualityCode());
        fixed.setLightQualityDeal(f.getLightQualityDeal());
        fixed.setSolarValveCode(f.getSolarValve());
        fixed.setSolarValveDeal(f.getSolarValveDeal());
        fixed.setBulbGroup(f.getBulbGroup());
        fixed.setBulbGroupDeal(f.getBulbGroupDeal());
        fixed.setBatteryCode(f.getLampVoltage());
        fixed.setBatteryDeal(f.getLampVoltageDeal());
        fixed.setBatteryVoltage(f.getMirrorCode());
        fixed.setBatteryVoltageDeal(f.getMirrorDeal());
        fixed.setSolarPanelCode(f.getSolarPanelCode());
        fixed.setSolarPanelDeal(f.getSolarPanelDeal());
        fixed.setChargeCurrent(null);
        fixed.setChargeCurrentDeal(null);
        fixed.setLighthouseFirmCode(f.getLighthouseFirmCode());
        fixed.setLighthouseFirmDeal(f.getLighthouseFirmDeal());
        fixed.setLighthouseSealCode(f.getLighthouseSealCode());
        fixed.setLighthouseSealDeal(f.getLighthouseSealDeal());
        fixed.setLighthouseLanternCode(f.getLighthouseLanternCode());
        fixed.setRemark(f.getRemark());
        fixed.setNodeNumber("XJJL04");
        fixed.setSysCreated(f.getSysCreated());
        fixed.setSysCreatedBy(getName(f.getSysCreatedBy()));
        fixed.setSysOrg(f.getSysOrg());
        fixed.setSysDept(f.getSysDept());
        fixed.setSysLastUpd(f.getSysLastUpd());
        fixed.setSysLastUpdBy(getName(f.getSysLastUpdBy()));

        fixed.setIsEnabled(1);

        return fixed;
    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl固定标志巡检记录成功", "tbl固定标志巡检记录成功", ExcelType.XSSF),
                TblPlanArea.class, successTblInspectFixed);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl固定标志巡检记录失败", "tbl固定标志巡检记录失败", ExcelType.XSSF),
                TblPlanArea.class, failedTblInspectFixed);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl固定标志巡检记录航标列表失败", "tbl固定标志巡检记录航标列表失败", ExcelType.XSSF),
//                TblPlanAreaList.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl固定标志巡检记录航标列表成功", "tbl固定标志巡检记录航标列表成功", ExcelType.XSSF),
//                TblPlanAreaList.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblInspectFixed/" + "tbl固定标志巡检记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblInspectFixed/" + "tbl固定标志巡检记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl固定标志巡检记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl固定标志巡检记录航标列表-成功.xlsx");


            workbook.write(fos);
            workbook1.write(fos1);
//            workbook2.write(fos2);
//            workbook3.write(fos3);
            // workbook4.write(fos4);
            // workbook5.write(fos5);
            // workbook6.write(fos6);
            // workbook7.write(fos7);

            fos.close();
            fos1.close();
//            fos2.close();
//            fos3.close();
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
