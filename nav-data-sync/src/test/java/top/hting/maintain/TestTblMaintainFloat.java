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
import top.hting.entity.oracle.maintain.TblMaintainFloat;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.sqlserver.maintain.CbsMaintainFloating;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.maintain.TblMaintainFloatMapper;
import top.hting.mapper.sqlserver.maintain.CbsMaintainFloatingMapper;

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
public class TestTblMaintainFloat {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的浮动标志维护记录
    List<TblMaintainFloat> successTblMaintainFloat = new ArrayList<TblMaintainFloat>();
    List<TblMaintainFloat> failedTblMaintainFloat = new ArrayList<TblMaintainFloat>();

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
    CbsMaintainFloatingMapper cbsMaintainFloatingMapper;
    @Autowired
    TblMaintainFloatMapper tblMaintainFloatMapper;


    @Test
    public void synTblMaintainFloat(){


        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsMaintainFloating> maintainSounds = cbsMaintainFloatingMapper.selectByMap(params);

        for (CbsMaintainFloating maintainSound : maintainSounds) {

            TblMaintainFloat tbl = tblMaintainFloatMapper.selectById(maintainSound.getPid());

            if (tbl == null) {

                tbl = cbs2tbl(maintainSound);


                try {
                    tblMaintainFloatMapper.insert(tbl);
                    successTblMaintainFloat.add(tbl);
                } catch (Exception e) {
                    tbl.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblMaintainFloat.add(tbl);


                    e.printStackTrace();
                }


            }


        }


    }

    private TblMaintainFloat cbs2tbl(CbsMaintainFloating m) {
        TblMaintainFloat tblMaintainFloat = new TblMaintainFloat();
        tblMaintainFloat.setMaintainId(m.getPid());
        tblMaintainFloat.setPlanMarkId(m.getCbsMaintainListFID());
        tblMaintainFloat.setInspectNumber(generatePlanNumber(m.getInspectTime(), seq));
        tblMaintainFloat.setInspectTime(m.getInspectTime());
        tblMaintainFloat.setExaminer(m.getExaminer());
        tblMaintainFloat.setOrgCode(m.getOrgCode());
        tblMaintainFloat.setMarkId(m.getBasNavigationMarkFID());
        tblMaintainFloat.setMarkTableCode(m.getMarkTable());
        tblMaintainFloat.setMarkName(m.getMarkName());
        tblMaintainFloat.setMaintain1(m.getMaintain1());
        tblMaintainFloat.setMaintain2(m.getMaintain2());
        tblMaintainFloat.setMaintain3(m.getMaintain3());
        tblMaintainFloat.setMaintain4(m.getMaintain4());
        tblMaintainFloat.setMaintain5(m.getMaintain5());
        tblMaintainFloat.setMaintain6(m.getMaintain6());
        tblMaintainFloat.setMaintain7(m.getMaintain7());
        tblMaintainFloat.setMaintain8(m.getMaintain8());
        tblMaintainFloat.setMaintain9(m.getMaintain9());
        tblMaintainFloat.setMaintain10(m.getMaintain10());
        tblMaintainFloat.setMaintain11(m.getMaintain11());
        tblMaintainFloat.setMaintain12(m.getMaintain12());
        tblMaintainFloat.setMaintain13(m.getMaintain13());
        tblMaintainFloat.setMaintain14(m.getMaintain14());
        tblMaintainFloat.setMaintain15(m.getMaintain15());
        tblMaintainFloat.setMaintain16(m.getMaintain16());
        tblMaintainFloat.setMaintain17(new Short("0")); // 不知道是什么值 TODO
        tblMaintainFloat.setRemark(m.getRemark());
        tblMaintainFloat.setNodeNumber("WHJL04");
        tblMaintainFloat.setSysCreated(m.getSysCreated());
        tblMaintainFloat.setSysCreatedBy(getName(m.getSysCreatedBy()));
        tblMaintainFloat.setSysOrg(m.getSysOrg());
        tblMaintainFloat.setSysDept(m.getSysDept());
        tblMaintainFloat.setSysLastUpd(m.getSysLastUpd());
        tblMaintainFloat.setSysLastUpdBy(getName(m.getSysLastUpdBy()));

        return tblMaintainFloat;
    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl浮动标志维护记录成功", "tbl浮动标志维护记录成功", ExcelType.XSSF),
                TblMaintainFloat.class, successTblMaintainFloat);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl浮动标志维护记录失败", "tbl浮动标志维护记录失败", ExcelType.XSSF),
                TblMaintainFloat.class, failedTblMaintainFloat);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl浮动标志维护记录航标列表失败", "tbl浮动标志维护记录航标列表失败", ExcelType.XSSF),
//                TblMaintainFloat.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl浮动标志维护记录航标列表成功", "tbl浮动标志维护记录航标列表成功", ExcelType.XSSF),
//                TblMaintainFloat.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblMaintainFloat/" + "tbl浮动标志维护记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblMaintainFloat/" + "tbl浮动标志维护记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblMaintainFloat/" + "tbl浮动标志维护记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblMaintainFloat/" + "tbl浮动标志维护记录航标列表-成功.xlsx");


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
