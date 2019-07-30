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
import top.hting.entity.oracle.inspect.TblInspectDgps;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.sqlserver.inspect.CbsInspectDGPS;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.inspect.TblInspectDgpsMapper;
import top.hting.mapper.sqlserver.inspect.CbsInspectDGPSMapper;

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
 * 差分台巡检记录
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestInspectDGPS {


    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的差分台巡检记录
    List<TblInspectDgps> successTblInspectDgps = new ArrayList<TblInspectDgps>();
    List<TblInspectDgps> failedTblInspectDgps = new ArrayList<TblInspectDgps>();

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
    CbsInspectDGPSMapper cbsInspectDGPSMapper;
    @Autowired
    TblInspectDgpsMapper tblInspectDgpsMapper;

    @Test
    public void synTblInspectDGPS() {

        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsInspectDGPS> cbsInspectSounds = cbsInspectDGPSMapper.selectByMap(params);

        for (CbsInspectDGPS dgps : cbsInspectSounds) {

            TblInspectDgps inspectDgps = tblInspectDgpsMapper.selectById(dgps.getPid());

            if (inspectDgps == null) {

                inspectDgps = cbs2tbl(dgps);

                try {
                    tblInspectDgpsMapper.insert(inspectDgps);

                    successTblInspectDgps.add(inspectDgps);

                } catch (Exception e) {
                    inspectDgps.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblInspectDgps.add(inspectDgps);
                    e.printStackTrace();
                }

            }


        }


    }

    private TblInspectDgps cbs2tbl(CbsInspectDGPS d) {

        TblInspectDgps inspectDgps = new TblInspectDgps();
        inspectDgps.setInspectId(d.getPid());
        inspectDgps.setPlanMarkId(d.getCbsInspectListFID());
        inspectDgps.setMarkId(d.getBasNavigationMarkFID());
        inspectDgps.setMarkTableCode(d.getMarkTable());
        inspectDgps.setMarkName(d.getMarkName());
        inspectDgps.setInspectTime(d.getInspectTime());
        inspectDgps.setExaminer(d.getExaminer());
        inspectDgps.setOrgCode(d.getOrgCode());
        inspectDgps.setOutputSignalStatusCode(d.getOutputSignalStatusCode());
        inspectDgps.setOutputSignalRepairCode(d.getOutputSignalRepairCode());
        inspectDgps.setDeviceStatusCode(d.getDeviceStatusCode());
        inspectDgps.setDeviceRepairCode(d.getDeviceRepairCode());
        inspectDgps.setAntennaStatusCode(d.getAntennaStatusCode());
        inspectDgps.setAntennaRepairCode(d.getAntennaRepairCode());
        inspectDgps.setComputerStatusCode(d.getComputerStatusCode());
        inspectDgps.setComputerRepairCode(d.getComputerRepairCode());
        inspectDgps.setPowerStatusCode(d.getPowerStatusCode());
        inspectDgps.setPowerRepairCode(d.getPowerRepairCode());
        inspectDgps.setRemark(d.getRemark());
        inspectDgps.setNodeNumber("XJJL04");
        inspectDgps.setInspectNumber(generatePlanNumber(d.getInspectTime(), seq));
        inspectDgps.setSysCreated(d.getSysCreated());
        inspectDgps.setSysCreatedBy(getName(d.getSysCreatedBy()));
        inspectDgps.setSysOrg(d.getSysOrg());
        inspectDgps.setSysDept(d.getSysDept());
        inspectDgps.setSysLastUpd(d.getSysLastUpd());
        inspectDgps.setSysLastUpdBy(getName(d.getSysLastUpdBy()));
        return inspectDgps;
    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl差分台巡检记录成功", "tbl差分台巡检记录成功", ExcelType.XSSF),
                TblInspectDgps.class, successTblInspectDgps);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl差分台巡检记录失败", "tbl差分台巡检记录失败", ExcelType.XSSF),
                TblInspectDgps.class, failedTblInspectDgps);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl差分台巡检记录航标列表失败", "tbl差分台巡检记录航标列表失败", ExcelType.XSSF),
//                TblPlanAreaList.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl差分台巡检记录航标列表成功", "tbl差分台巡检记录航标列表成功", ExcelType.XSSF),
//                TblPlanAreaList.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblInspectDGPS/" + "tbl差分台巡检记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblInspectDGPS/" + "tbl差分台巡检记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl差分台巡检记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl差分台巡检记录航标列表-成功.xlsx");


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
