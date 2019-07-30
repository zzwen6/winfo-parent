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
import top.hting.entity.oracle.visit.TblVisitFloat;
import top.hting.entity.sqlserver.visit.CbsVisitFloating;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.TblVisitFloatMapper;
import top.hting.mapper.sqlserver.CbsVisitFloatingMapper;

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
 * 浮动标志巡视记录
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTblVisitFloat {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的浮动巡视记录
    List<TblVisitFloat> successTblVisitFloat = new ArrayList<TblVisitFloat>();
    List<TblVisitFloat> failedTblVisitFloat = new ArrayList<>();

    // 新系统成功失败的海区，更新或新增
//    List<TblPlanAreaList> successTblAreaList = new ArrayList<>();
//    List<TblPlanAreaList> failedTblAreaList = new ArrayList<>();
//    List<TblPlanAreaList> successUpdateTblAreaList = new ArrayList<>();
//    List<TblPlanAreaList> failedUpdateTblAreaList = new ArrayList<>();


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
    TblVisitFloatMapper tblVisitFloatMapper;

    @Autowired
    CbsVisitFloatingMapper cbsVisitFloatingMapper;

    /**
     * 同步浮动巡视记录
     */
    @Test
    public void synTblVisitFloat(){

        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);

        List<CbsVisitFloating> cbsVisitFloatings = cbsVisitFloatingMapper.selectByMap(params);

        for (CbsVisitFloating floating : cbsVisitFloatings) {

            TblVisitFloat tbl = tblVisitFloatMapper.selectById(floating.getPid());

            if (tbl == null) {

                tbl = cbsFloat2TblFloat(floating);


                try {

                    tblVisitFloatMapper.insert(tbl);
                    successTblVisitFloat.add(tbl);

                } catch (Exception e) {
                    tbl.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblVisitFloat.add(tbl);
                    e.printStackTrace();
                }


            }


        }

    }

    private TblVisitFloat cbsFloat2TblFloat(CbsVisitFloating f) {
        TblVisitFloat visitFloat = new TblVisitFloat();
        visitFloat.setVisitId(f.getPid());
        visitFloat.setVisitNumber(generatePlanNumber(f.getInspectTime(), seq));
        visitFloat.setInspectTime(f.getInspectTime());
        visitFloat.setExaminer(f.getExaminer());
        visitFloat.setShipId(f.getBscShipFID());
        visitFloat.setOrgCode(f.getOrgCode());
        visitFloat.setMarkId(f.getBasNavigationMarkFID());
        visitFloat.setMarkTableCode(f.getMarkTable());
        visitFloat.setMarkName(f.getMarkName());
        visitFloat.setRadarType(f.getRadarType());
        visitFloat.setEyeHight(f.getEyeHight());
        visitFloat.setRadarsSpeed(f.getRadarsSpeed());
        visitFloat.setRange(f.getRange());
        visitFloat.setRadarHight(f.getRadarHight());
        visitFloat.setRadarTurnSpeed(f.getRadarTurnSpeed());
        visitFloat.setReservations(f.getReservations());
        visitFloat.setDistance(f.getDistance());
        visitFloat.setRadarCode(f.getRadarCode());
        visitFloat.setInterval(f.getInterval());
        visitFloat.setLightQualityCode(f.getLightQualityCode());
        visitFloat.setLightCode(f.getLightCode());
        visitFloat.setLightRange(f.getLightRange());
        visitFloat.setStructureCode(f.getStructureCode());
        visitFloat.setWeather(f.getWeather());
        visitFloat.setVisibility(f.getVisibility());
        visitFloat.setRemark(f.getRemark());
        visitFloat.setSysCreated(f.getSysCreated());
        visitFloat.setSysCreatedBy(getName(f.getSysCreatedBy()));
        visitFloat.setSysOrg(f.getSysOrg());
        visitFloat.setSysDept(f.getSysDept());
        visitFloat.setSysLastUpd(f.getSysLastUpd());
        visitFloat.setSysLastUpdBy(getName(f.getSysLastUpdBy()));
        return visitFloat;

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
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl浮动巡视记录成功", "tbl浮动巡视记录成功", ExcelType.XSSF),
                TblVisitFloat.class, successTblVisitFloat);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl浮动巡视记录失败", "tbl浮动巡视记录失败", ExcelType.XSSF),
                TblVisitFloat.class, failedTblVisitFloat);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl浮动巡视记录航标列表失败", "tbl浮动巡视记录航标列表失败", ExcelType.XSSF),
//                TblPlanAreaList.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl浮动巡视记录航标列表成功", "tbl浮动巡视记录航标列表成功", ExcelType.XSSF),
//                TblPlanAreaList.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblVisitFloat/" + "tbl浮动巡视记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblVisitFloat/" + "tbl浮动巡视记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl浮动巡视记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl浮动巡视记录航标列表-成功.xlsx");


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
