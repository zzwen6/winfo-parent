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
import top.hting.entity.oracle.inspect.TblInspectSound;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.sqlserver.inspect.CbsInspectSound;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.inspect.TblInspectSoundMapper;
import top.hting.mapper.sqlserver.inspect.CbsInspectSoundMapper;

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
 * 音响航标巡检记录
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestInspectSound {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的音响航标巡检记录
    List<TblInspectSound> successTblInspectSound = new ArrayList<TblInspectSound>();
    List<TblInspectSound> failedTblInspectSound = new ArrayList<TblInspectSound>();

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
    CbsInspectSoundMapper cbsInspectSoundMapper;
    @Autowired
    TblInspectSoundMapper tblInspectSoundMapper;

    @Test
    public void synTblInspectSound() {

        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsInspectSound> cbsInspectSounds = cbsInspectSoundMapper.selectByMap(params);

        for (CbsInspectSound sound : cbsInspectSounds) {

            TblInspectSound inspectSound = tblInspectSoundMapper.selectById(sound.getPid());

            if (inspectSound == null) {

                inspectSound = cbs2tbl(sound);

                try {
                    tblInspectSoundMapper.insert(inspectSound);

                    successTblInspectSound.add(inspectSound);

                } catch (Exception e) {
                    inspectSound.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblInspectSound.add(inspectSound);
                    e.printStackTrace();
                }

            }


        }


    }

    private TblInspectSound cbs2tbl(CbsInspectSound s) {
        TblInspectSound sound = new TblInspectSound();
        sound.setInspectId(s.getPid());
        sound.setPlanMarkId(s.getCbsInspectListFID());
        sound.setInspectNumber(generatePlanNumber(s.getInspectTime(), seq));
        sound.setInspectTime(s.getInspectTime());
        sound.setExaminer(s.getExaminer());
        sound.setShipId(null);
        sound.setOrgCode(s.getOrgCode());
        sound.setMarkId(s.getBasNavigationMarkFID());
        sound.setMarkTableCode(s.getMarkTable());
        sound.setMarkName(s.getMarkName());
        sound.setWorkStatusCode(s.getWorkStatusCode());
        sound.setWorkRepairCode(s.getWorkRepairCode());
        sound.setParamVoltage(s.getParamVoltage());
        sound.setParamPower(s.getParamPower());
        sound.setParamStatusCode(s.getParamStatusCode());
        sound.setParamRepairCode(s.getParamRepairCode());
        sound.setEnvironmentStatusCode(s.getEnvironmentStatusCode());
        sound.setEnvironmentRepairCode(s.getEnvironmentRepairCode());
        sound.setRemark(s.getRemark());
        sound.setNodeNumber("XJJL04");
        sound.setIsEnabled(1);
        sound.setSysCreated(s.getSysCreated());
        sound.setSysCreatedBy(getName(s.getSysCreatedBy()));
        sound.setSysOrg(s.getSysOrg());
        sound.setSysDept(s.getSysDept());
        sound.setSysLastUpd(s.getSysLastUpd());
        sound.setSysLastUpdBy(getName(s.getSysLastUpdBy()));

        return sound;
    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl音响航标巡检记录成功", "tbl音响航标巡检记录成功", ExcelType.XSSF),
                TblInspectSound.class, successTblInspectSound);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl音响航标巡检记录失败", "tbl音响航标巡检记录失败", ExcelType.XSSF),
                TblInspectSound.class, failedTblInspectSound);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl音响航标巡检记录航标列表失败", "tbl音响航标巡检记录航标列表失败", ExcelType.XSSF),
//                TblPlanAreaList.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl音响航标巡检记录航标列表成功", "tbl音响航标巡检记录航标列表成功", ExcelType.XSSF),
//                TblPlanAreaList.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblInspectSound/"+ System.currentTimeMillis() + "tbl音响航标巡检记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblInspectSound/" + System.currentTimeMillis()+ "tbl音响航标巡检记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl音响航标巡检记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tbl音响航标巡检记录航标列表-成功.xlsx");


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
