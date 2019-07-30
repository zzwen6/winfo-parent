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
import top.hting.entity.oracle.inspect.TblInspectRadar;
import top.hting.entity.oracle.paln.TblPlanArea;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.sqlserver.inspect.CbsInpsectRadarNew;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.inspect.TblInspectRadarMapper;
import top.hting.mapper.sqlserver.inspect.CbsInpsectRadarNewMapper;

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
 * 雷达巡检记录
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTblInspectRadar {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的雷达应答器巡检记录
    List<TblInspectRadar> successTblInspectRadar = new ArrayList<TblInspectRadar>();
    List<TblInspectRadar> failedTblInspectRadar = new ArrayList<TblInspectRadar>();

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
    CbsInpsectRadarNewMapper cbsInpsectRadarNewMapper;
    @Autowired
    TblInspectRadarMapper tblInspectRadarMapper;

    @Test
    public void synTblInspectRadar(){

        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsInpsectRadarNew> cbsInpsectRadarNews = cbsInpsectRadarNewMapper.selectByMap(params);

        for (CbsInpsectRadarNew radarNew : cbsInpsectRadarNews) {

            TblInspectRadar inspectRadar = tblInspectRadarMapper.selectById(radarNew.getPid());

            if (inspectRadar == null) {

                inspectRadar = cbs2tbl(radarNew);

                try {
                    tblInspectRadarMapper.insert(inspectRadar);

                    successTblInspectRadar.add(inspectRadar);

                } catch (Exception e) {
                    inspectRadar.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblInspectRadar.add(inspectRadar);
                    e.printStackTrace();
                }

            }


        }


    }

    private TblInspectRadar cbs2tbl(CbsInpsectRadarNew r) {
        TblInspectRadar radar = new TblInspectRadar();
        radar.setInspectId(r.getPid());
        radar.setPlanMarkId(r.getCbsInspectListFID());
        radar.setInspectNumber(generatePlanNumber(r.getInspectTime(), seq));
        radar.setInspectTime(r.getInspectTime());
        radar.setExaminer(r.getExaminer());
        radar.setShipId(null);
        radar.setOrgCode(r.getOrgCode());
        radar.setMarkId(r.getBasNavigationMarkFID());
        radar.setMarkTableCode(r.getMarkTable());
        radar.setMarkName(r.getMarkName());
        radar.setSilentHour(r.getSilentHour());
        radar.setSilentMinute(r.getSilentMinute());
        radar.setSilentSecond(r.getSilentSecond());
        radar.setSilentpPower(r.getSilentpPower());
        radar.setSilentHourN(r.getSilentHourN());
        radar.setSilentMinuteN(r.getSilentMinuteN());
        radar.setSilentSecondN(r.getSilentSecondN());
        radar.setSilentpPowerN(r.getSilentpPowerN());
        radar.setAlertHour(r.getAlertHour());
        radar.setAlertMinute(r.getAlertMinute());
        radar.setAlertSecond(r.getAlertSecond());
        radar.setAlertPower(r.getAlertPower());
        radar.setLaunchHour(r.getLaunchHour());
        radar.setLaunchMinute(r.getLaunchMinute());
        radar.setLaunchSecond(r.getLaunchSecond());
        radar.setLaunchPower(r.getLaunchPower());
        radar.setBatteryVoltage(r.getSupplyVoltage());
        radar.setBatteryCode(r.getLampVoltage());
        radar.setBatteryDeal(r.getLampVoltageDeal());
        radar.setIdentiferStatusCode(r.getIdentiferStatusCode());
        radar.setIdentiferRepairCode(r.getIdentiferRepairCode());
        radar.setPeriod(r.getZhouqi());
        radar.setRemark(r.getRemark());
        radar.setNodeNumber("XJJL04");
        radar.setIsEnabled(1);
        radar.setSysCreated(r.getSysCreated());
        radar.setSysCreatedBy(getName(r.getSysCreatedBy()));
        radar.setSysOrg(r.getSysOrg());
        radar.setSysDept(r.getSysDept());
        radar.setSysLastUpd(r.getSysLastUpd());
        radar.setSysLastUpdBy(getName(r.getSysLastUpdBy()));

        return radar;

    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl雷达应答器巡检记录成功", "tbl雷达应答器巡检记录成功", ExcelType.XSSF),
                TblPlanArea.class, successTblInspectRadar);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl雷达应答器巡检记录失败", "tbl雷达应答器巡检记录失败", ExcelType.XSSF),
                TblPlanArea.class, failedTblInspectRadar);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl雷达应答器巡检记录航标列表失败", "tbl雷达应答器巡检记录航标列表失败", ExcelType.XSSF),
//                TblPlanAreaList.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl雷达应答器巡检记录航标列表成功", "tbl雷达应答器巡检记录航标列表成功", ExcelType.XSSF),
//                TblPlanAreaList.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblInspectRadar/" + "tbl雷达应答器巡检记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblInspectRadar/" + "tbl雷达应答器巡检记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl雷达应答器巡检记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl雷达应答器巡检记录航标列表-成功.xlsx");


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
