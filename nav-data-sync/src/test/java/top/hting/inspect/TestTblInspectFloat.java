package top.hting.inspect;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.lang3.StringUtils;
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
import top.hting.entity.oracle.inspect.TblInspectFloat;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.sqlserver.inspect.CbsInspectFloatingNew;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.inspect.TblInspectFloatMapper;
import top.hting.mapper.sqlserver.inspect.CbsInspectFloatingNewMapper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 浮动标志巡检记录
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTblInspectFloat {
    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的浮动标志巡检记录
    List<TblInspectFloat> successTblInspectFloat = new ArrayList<TblInspectFloat>();
    List<TblInspectFloat> failedTblInspectFloat = new ArrayList<TblInspectFloat>();

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
    CbsInspectFloatingNewMapper cbsInspectFloatingNewMapper;
    @Autowired
    TblInspectFloatMapper tblInspectFloatMapper;


    @Test
    public void synTblInspectFloat() {

        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsInspectFloatingNew> floatingNews = cbsInspectFloatingNewMapper.selectByMap(params);

        for (CbsInspectFloatingNew fnew : floatingNews) {

            TblInspectFloat inspectFloat = tblInspectFloatMapper.selectById(fnew.getPid());

            if (inspectFloat == null) {

                inspectFloat = cbs2tbl(fnew);


                try {
                    tblInspectFloatMapper.insert(inspectFloat);

                    successTblInspectFloat.add(inspectFloat);


                } catch (Exception e) {
                    inspectFloat.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblInspectFloat.add(inspectFloat);

                    e.printStackTrace();
                }

            }


        }


    }

    private TblInspectFloat cbs2tbl(CbsInspectFloatingNew f) {
        TblInspectFloat inspectFloat = new TblInspectFloat();
        inspectFloat.setInspectId(f.getPid());
        inspectFloat.setPlanMarkId(f.getCbsInspectListFID()); // 计划航标主键
        inspectFloat.setInspectNumber(generatePlanNumber(f.getInspectTime(), seq));
        inspectFloat.setInspectTime(f.getInspectTime());
        inspectFloat.setExaminer(f.getExaminer());
        inspectFloat.setShipId(f.getBscShipFID());
        inspectFloat.setOrgCode(f.getOrgCode());
        inspectFloat.setMarkId(f.getBasNavigationMarkFID());
        inspectFloat.setMarkTableCode(f.getMarkTable());
        inspectFloat.setMarkName(f.getMarkName());
        inspectFloat.setLatitudeDegree(f.getLatitudeDegree());
        inspectFloat.setLatitudeMinute(f.getLatitudeMinute());
        inspectFloat.setLatitudeSecond(f.getLatitudeSecond());
        inspectFloat.setLongitudeDegree(f.getLongitudeDegree());
        inspectFloat.setLongitudeMinute(f.getLongitudeMinute());
        inspectFloat.setLongitudeSecond(f.getLongitudeSecond());

        // cbs distance 进行拆分 272.53°13.35m
        if (StringUtils.isNotBlank(f.getDistance())) {

            String[] split = f.getDistance().toLowerCase().split("°");

            inspectFloat.setDistanceDegree(new BigDecimal(split[0]));

            inspectFloat.setDistanceMeter(new BigDecimal(split[1].replace("m", "")));

        }


        inspectFloat.setRecoveryPosition(f.getRecoveryPosition());
        inspectFloat.setReLatitudeDegree(f.getReLatitudeDegree());
        inspectFloat.setReLatitudeMinute(f.getReLatitudeMinute());
        inspectFloat.setReLatitudeSecond(f.getReLatitudeSecond());
        inspectFloat.setReLongitudeDegree(f.getReLongitudeDegree());
        inspectFloat.setReLongitudeMinute(f.getReLongitudeMinute());
        inspectFloat.setReLongitudeSecond(f.getReLongitudeSecond());
        inspectFloat.setLightQualityCode(f.getLightQualityCode());
        inspectFloat.setLightQualityDeal(f.getLightQualityDeal());
        inspectFloat.setLightingCode(f.getLightingCode());
        inspectFloat.setLightingDeal(f.getLightingDeal());
        inspectFloat.setLightingBreakReasonCode(f.getLightingBreakReason());

        inspectFloat.setLightingWorkVoltage(null); // 无对应值
        inspectFloat.setLightingWorkCurrent(null);

        inspectFloat.setStructureCode(f.getStructureCode());
        inspectFloat.setStructureDeal(f.getStructureDeal());
        inspectFloat.setColoringCode(f.getColoring());
        inspectFloat.setColoringDeal(f.getColoringDeal());
        inspectFloat.setBatteryCode(f.getXBatteryCode());
        inspectFloat.setBatteryDeal(f.getXBatteryDeal());

        inspectFloat.setBatteryVoltage(null); // 无对应值
        inspectFloat.setBatteryVoltageDeal(null);

        inspectFloat.setSolarPanelCode(f.getSolarPanelCode());
        inspectFloat.setSolarPanelDeal(f.getSolarPanelDeal());
        inspectFloat.setChargeCurrent(f.getChargeCurrent());
        inspectFloat.setChargeCurrentDeal(null); // 无对应值
        inspectFloat.setRemark(f.getRemark());
        inspectFloat.setNodeNumber("XJJL04");
        inspectFloat.setSysCreated(f.getSysCreated());
        inspectFloat.setSysCreatedBy(getName(f.getSysCreatedBy()));
        inspectFloat.setSysOrg(f.getSysOrg());
        inspectFloat.setSysDept(f.getSysDept());
        inspectFloat.setSysLastUpd(f.getSysLastUpd());
        inspectFloat.setSysLastUpdBy(getName(f.getSysLastUpdBy()));
        inspectFloat.setIsEnabled(1);

        return inspectFloat;
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

    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl浮动标志巡检记录成功", "tbl浮动标志巡检记录成功", ExcelType.XSSF),
                TblInspectFloat.class, successTblInspectFloat);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl浮动标志巡检记录失败", "tbl浮动标志巡检记录失败", ExcelType.XSSF),
                TblInspectFloat.class, failedTblInspectFloat);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl浮动标志巡检记录航标列表失败", "tbl浮动标志巡检记录航标列表失败", ExcelType.XSSF),
//                TblPlanAreaList.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl浮动标志巡检记录航标列表成功", "tbl浮动标志巡检记录航标列表成功", ExcelType.XSSF),
//                TblPlanAreaList.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblInspectFloat/" + System.currentTimeMillis()+ "tbl浮动标志巡检记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblInspectFloat/" + System.currentTimeMillis()+ "tbl浮动标志巡检记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblInspectFloat/" + "tbl浮动标志巡检记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblInspectFloat/" + "tbl浮动标志巡检记录航标列表-成功.xlsx");


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

}
