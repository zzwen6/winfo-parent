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
import top.hting.entity.oracle.maintain.TblMaintainSound;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.sqlserver.maintain.CbsMaintainSound;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.maintain.TblMaintainSoundMapper;
import top.hting.mapper.sqlserver.maintain.CbsMaintainSoundMapper;

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
public class TestTblMaintainSound {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的音响航标维护记录
    List<TblMaintainSound> successTblMaintainSound = new ArrayList<TblMaintainSound>();
    List<TblMaintainSound> failedTblMaintainSound = new ArrayList<TblMaintainSound>();

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
    CbsMaintainSoundMapper cbsMaintainSoundMapper;
    @Autowired
    TblMaintainSoundMapper tblMaintainSoundMapper;

    @Test
    public void synTblMaintainSound() {

        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsMaintainSound> maintainSounds = cbsMaintainSoundMapper.selectByMap(params);

        for (CbsMaintainSound maintainSound : maintainSounds) {

            TblMaintainSound tbl = tblMaintainSoundMapper.selectById(maintainSound.getPid());

            if (tbl == null) {

                tbl = cbs2tbl(maintainSound);


                try {
                    tblMaintainSoundMapper.insert(tbl);
                    successTblMaintainSound.add(tbl);
                } catch (Exception e) {
                    tbl.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblMaintainSound.add(tbl);


                    e.printStackTrace();
                }


            }


        }


    }

    private TblMaintainSound cbs2tbl(CbsMaintainSound m) {
        TblMaintainSound tblMaintainSound = new TblMaintainSound();
        tblMaintainSound.setMaintainId(m.getPid());
        tblMaintainSound.setPlanMarkId(m.getCbsMaintainListFID());
        tblMaintainSound.setInspectNumber(generatePlanNumber(m.getInspectTime(), seq));
        tblMaintainSound.setInspectTime(m.getInspectTime());
        tblMaintainSound.setExaminer(m.getExaminer());
        tblMaintainSound.setOrgCode(m.getOrgCode());
        tblMaintainSound.setMarkId(m.getBasNavigationMarkFID());
        tblMaintainSound.setMarkTableCode(m.getMarkTable());
        tblMaintainSound.setMarkName(m.getMarkName());
        tblMaintainSound.setMaintain1(m.getMaintain1());
        tblMaintainSound.setMaintain2(m.getMaintain2());
        tblMaintainSound.setMaintain3(m.getMaintain3());
        tblMaintainSound.setMaintain4(m.getMaintain4());
        tblMaintainSound.setMaintain5(m.getMaintain5());
        tblMaintainSound.setMaintain6(m.getMaintain6());
        tblMaintainSound.setMaintain7(m.getMaintain7());
        tblMaintainSound.setRemark(m.getRemark());
        tblMaintainSound.setNodeNumber("WHJL04");
        tblMaintainSound.setSysCreated(m.getSysCreated());
        tblMaintainSound.setSysCreatedBy(getName(m.getSysCreatedBy()));
        tblMaintainSound.setSysOrg(m.getSysOrg());
        tblMaintainSound.setSysDept(m.getSysDept());
        tblMaintainSound.setSysLastUpd(m.getSysLastUpd());
        tblMaintainSound.setSysLastUpdBy(getName(m.getSysLastUpdBy()));

        return tblMaintainSound;


    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl音响航标维护记录成功", "tbl音响航标维护记录成功", ExcelType.XSSF),
                TblMaintainSound.class, successTblMaintainSound);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl音响航标维护记录失败", "tbl音响航标维护记录失败", ExcelType.XSSF),
                TblMaintainSound.class, failedTblMaintainSound);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl音响航标维护记录航标列表失败", "tbl音响航标维护记录航标列表失败", ExcelType.XSSF),
//                TblMaintainSound.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl音响航标维护记录航标列表成功", "tbl音响航标维护记录航标列表成功", ExcelType.XSSF),
//                TblMaintainSound.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblMaintainSound/" + "tbl音响航标维护记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblMaintainSound/" + "tbl音响航标维护记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblMaintainSound/" + "tbl音响航标维护记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblMaintainSound/" + "tbl音响航标维护记录航标列表-成功.xlsx");


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
