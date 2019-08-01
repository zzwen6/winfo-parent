package top.hting.maintain;

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
import top.hting.entity.oracle.maintain.TblMaintainRadar;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.sqlserver.maintain.CbsMaintainRadar;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.maintain.TblMaintainRadarMapper;
import top.hting.mapper.sqlserver.maintain.CbsMaintainRadarMapper;

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
public class TestTblMaintainRadar {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的雷达应答器维护记录
    List<TblMaintainRadar> successTblMaintainRadar = new ArrayList<TblMaintainRadar>();
    List<TblMaintainRadar> failedTblMaintainRadar = new ArrayList<TblMaintainRadar>();

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
        seq = seqMapper.selectById("HBWH");

    }

    @After
    public void after() {
        // 重新更新序列
        seqMapper.updateValue(Seq.builder().sequenceid(seq.getSequenceid()).sequence(seq.getSequence()).build());
        // 写入文件
        saveFile();
    }

    @Autowired
    CbsMaintainRadarMapper cbsMaintainRadarMapper;
    @Autowired
    TblMaintainRadarMapper tblMaintainRadarMapper;

    @Test
    public void synTblMaintainRadar() {

        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsMaintainRadar> cbsMaintainRadars = cbsMaintainRadarMapper.selectByMap(params);

        for (CbsMaintainRadar maintainRadar : cbsMaintainRadars) {

            TblMaintainRadar tbl = tblMaintainRadarMapper.selectById(maintainRadar.getPid());

            if (tbl == null) {

                tbl = cbs2tbl(maintainRadar);


                try {
                    tblMaintainRadarMapper.insert(tbl);
                    successTblMaintainRadar.add(tbl);
                } catch (Exception e) {
                    tbl.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblMaintainRadar.add(tbl);


                    e.printStackTrace();
                }


            }


        }


    }

    private TblMaintainRadar cbs2tbl(CbsMaintainRadar m) {
        TblMaintainRadar tblMaintainRadar = new TblMaintainRadar();
        tblMaintainRadar.setMaintainId(m.getPid());
        tblMaintainRadar.setPlanMarkId(m.getCbsMaintainListFID());
        tblMaintainRadar.setInspectNumber(generatePlanNumber(m.getInspectTime(), seq));
        tblMaintainRadar.setInspectTime(m.getInspectTime());
        tblMaintainRadar.setExaminer(m.getExaminer());
        tblMaintainRadar.setOrgCode(m.getOrgCode());
        tblMaintainRadar.setMarkId(m.getBasNavigationMarkFID());
        tblMaintainRadar.setMarkTableCode(m.getMarkTable());
        tblMaintainRadar.setMarkName(m.getMarkName());
        tblMaintainRadar.setMaintain1(m.getMaintain1());
        tblMaintainRadar.setMaintain2(m.getMaintain2());
        tblMaintainRadar.setMaintain3(m.getMaintain3());
        tblMaintainRadar.setMaintain4(m.getMaintain4());
        tblMaintainRadar.setMaintain5(m.getMaintain5());
        tblMaintainRadar.setRemark(m.getRemark());
        tblMaintainRadar.setNodeNumber("WHJL04");
        tblMaintainRadar.setSysCreated(m.getSysCreated());
        tblMaintainRadar.setSysCreatedBy(getName(m.getSysCreatedBy()));
        tblMaintainRadar.setSysOrg(m.getSysOrg());
        tblMaintainRadar.setSysDept(m.getSysDept());
        tblMaintainRadar.setSysLastUpd(m.getSysLastUpd());
        tblMaintainRadar.setSysLastUpdBy(getName(m.getSysLastUpdBy()));
        return tblMaintainRadar;

    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl雷达应答器维护记录成功", "tbl雷达应答器维护记录成功", ExcelType.XSSF),
                TblMaintainRadar.class, successTblMaintainRadar);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl雷达应答器维护记录失败", "tbl雷达应答器维护记录失败", ExcelType.XSSF),
                TblMaintainRadar.class, failedTblMaintainRadar);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl雷达应答器维护记录航标列表失败", "tbl雷达应答器维护记录航标列表失败", ExcelType.XSSF),
//                TblMaintainRadar.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl雷达应答器维护记录航标列表成功", "tbl雷达应答器维护记录航标列表成功", ExcelType.XSSF),
//                TblMaintainRadar.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblMaintainRadar/" + System.currentTimeMillis()+ "tbl雷达应答器维护记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblMaintainRadar/" + System.currentTimeMillis()+ "tbl雷达应答器维护记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblMaintainRadar/" + "tbl雷达应答器维护记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblMaintainRadar/" + "tbl雷达应答器维护记录航标列表-成功.xlsx");


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
