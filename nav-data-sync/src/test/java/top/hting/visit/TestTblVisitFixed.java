package top.hting.visit;

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
import top.hting.entity.oracle.paln.TblPlanArea;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.oracle.visit.TblVisitFixed;
import top.hting.entity.sqlserver.visit.CbsVisitFixed;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.visit.TblVisitFixedMapper;
import top.hting.mapper.sqlserver.visit.CbsVisitFixedMapper;

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
 * 固定标志巡视记录
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTblVisitFixed {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的固定标志巡视记录
    List<TblVisitFixed> successTblVisitFixed = new ArrayList<TblVisitFixed>();
    List<TblVisitFixed> failedTblVisitFixed = new ArrayList<TblVisitFixed>();

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
        seq = seqMapper.selectById("HBXS");

    }

    @After
    public void after() {
        // 重新更新序列
        seqMapper.updateValue(Seq.builder().sequenceid(seq.getSequenceid()).sequence(seq.getSequence()).build());
        // 写入文件
        saveFile();
    }

    @Autowired
    TblVisitFixedMapper tblVisitFixedMapper;
    @Autowired
    CbsVisitFixedMapper cbsVisitFixedMapper;


    /**
     * 固定标志巡检记录同步
     */
    @Test
    public void synTblVisitFixed() {

        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsVisitFixed> cbsVisitFixeds = cbsVisitFixedMapper.selectByMap(params);

        for (CbsVisitFixed fixed : cbsVisitFixeds) {

            TblVisitFixed fix = tblVisitFixedMapper.selectById(fixed.getPid());

            if (fix == null) {

                fix = cbsVisit2TblVisit(fixed);


                try {
                    tblVisitFixedMapper.insert(fix);
                    successTblVisitFixed.add(fix);

                } catch (Exception e) {
                    fix.setRemark("出错备注:" + e.getLocalizedMessage());

                    failedTblVisitFixed.add(fix);

                    e.printStackTrace();
                }

            }

        }

    }

    private TblVisitFixed cbsVisit2TblVisit(CbsVisitFixed f) {
        TblVisitFixed fix = new TblVisitFixed();
        fix.setVisitId(f.getPid());
        fix.setVisitNumber(generatePlanNumber(f.getInspectTime(), seq));
        fix.setInspectTime(f.getInspectTime());
        fix.setExaminer(f.getExaminer());
        fix.setShipId(f.getBscShipFID());
        fix.setOrgCode(f.getOrgCode());
        fix.setMarkId(f.getBasNavigationMarkFID());
        fix.setMarkTableCode(f.getMarkTable());
        fix.setMarkName(f.getMarkName());
        fix.setVision(f.getVision());
        fix.setRadarType(f.getRadarType());
        fix.setRadarTurnSpeed(f.getRadarTurnSpeed());
        fix.setRadarsSpeed(f.getRadarsSpeed());
        fix.setRadarHight(f.getRadarHight());
        fix.setEyeHight(f.getEyeHight());
        fix.setLightQualityCode(f.getLightQualityCode());
        fix.setLightingCode(f.getLightingCode());
        fix.setWeather(f.getWeather());
        fix.setVisibility(f.getVisibility());
        fix.setLightingRange(f.getLightingRange());
        fix.setStructureCode(f.getStructureCode());
        fix.setRadarCode(f.getRadarCode());
        fix.setInterval(f.getInterval());
        fix.setRange(f.getRange());
        fix.setReservations(f.getReservations());
        fix.setDistance(f.getDistance());
        fix.setRemark(f.getRemark());
        fix.setSysCreated(f.getSysCreated());
        fix.setSysCreatedBy(getName(f.getSysCreatedBy()));
        fix.setSysOrg(f.getSysOrg());
        fix.setSysDept(f.getSysDept());
        fix.setSysLastUpd(f.getSysLastUpd());
        fix.setSysLastUpdBy(getName(f.getSysLastUpdBy()));

        return fix;
    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl固定标志巡视记录成功", "tbl固定标志巡视记录成功", ExcelType.XSSF),
                TblPlanArea.class, successTblVisitFixed);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl固定标志巡视记录失败", "tbl固定标志巡视记录失败", ExcelType.XSSF),
                TblPlanArea.class, failedTblVisitFixed);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl固定标志巡视记录航标列表失败", "tbl固定标志巡视记录航标列表失败", ExcelType.XSSF),
//                TblPlanAreaList.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl固定标志巡视记录航标列表成功", "tbl固定标志巡视记录航标列表成功", ExcelType.XSSF),
//                TblPlanAreaList.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl固定标志巡视记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl固定标志巡视记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl固定标志巡视记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl固定标志巡视记录航标列表-成功.xlsx");


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
