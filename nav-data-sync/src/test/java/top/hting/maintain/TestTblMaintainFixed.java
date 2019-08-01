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
import top.hting.entity.oracle.maintain.TblMaintainFixed;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.sqlserver.maintain.CbsMaintainFixed;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.maintain.TblMaintainFixedMapper;
import top.hting.mapper.sqlserver.maintain.CbsMaintainFixedMapper;

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
public class TestTblMaintainFixed {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的固定标志维护记录
    List<TblMaintainFixed> successTblMaintainFixed = new ArrayList<TblMaintainFixed>();
    List<TblMaintainFixed> failedTblMaintainFixed = new ArrayList<TblMaintainFixed>();

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
    CbsMaintainFixedMapper cbsMaintainFixedMapper;
    @Autowired
    TblMaintainFixedMapper tblMaintainFixedMapper;

    @Test
    public void synTblMaintainFixed(){

        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsMaintainFixed> maintainFixeds = cbsMaintainFixedMapper.selectByMap(params);

        for (CbsMaintainFixed maintainFixed : maintainFixeds) {

            TblMaintainFixed tbl = tblMaintainFixedMapper.selectById(maintainFixed.getPid());

            if (tbl == null) {

                tbl = cbs2tbl(maintainFixed);


                try {
                    tblMaintainFixedMapper.insert(tbl);
                    successTblMaintainFixed.add(tbl);
                } catch (Exception e) {
                    tbl.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblMaintainFixed.add(tbl);


                    e.printStackTrace();
                }


            }


        }



    }

    private TblMaintainFixed cbs2tbl(CbsMaintainFixed m) {
        TblMaintainFixed tblMaintainFixed = new TblMaintainFixed();


        tblMaintainFixed.setMaintainId(m.getPid());
        tblMaintainFixed.setPlanMarkId(m.getCbsMaintainListFID());
        tblMaintainFixed.setInspectNumber(generatePlanNumber(m.getInspectTime(), seq));
        tblMaintainFixed.setInspectTime(m.getInspectTime());

        tblMaintainFixed.setExaminer(m.getExaminer());
        tblMaintainFixed.setOrgCode(m.getOrgCode());
        tblMaintainFixed.setMarkId(m.getBasNavigationMarkFID());
        tblMaintainFixed.setMarkTableCode(m.getMarkTable());
        tblMaintainFixed.setMarkName(m.getMarkName());

        tblMaintainFixed.setMaintain1(m.getMaintain1());
        tblMaintainFixed.setMaintain2(m.getMaintain2());
        tblMaintainFixed.setMaintain3(m.getMaintain3());
        tblMaintainFixed.setMaintain4(m.getMaintain4());
        tblMaintainFixed.setMaintain5(m.getMaintain5());
        tblMaintainFixed.setMaintain6(m.getMaintain6());

        tblMaintainFixed.setRemark(m.getRemark());
        tblMaintainFixed.setNodeNumber("WHJL04");

        tblMaintainFixed.setSysCreated(m.getSysCreated());
        tblMaintainFixed.setSysCreatedBy(getName(m.getSysCreatedBy()));
        tblMaintainFixed.setSysOrg(m.getSysOrg());
        tblMaintainFixed.setSysDept(m.getSysDept());
        tblMaintainFixed.setSysLastUpd(m.getSysLastUpd());
        tblMaintainFixed.setSysLastUpdBy(getName(m.getSysLastUpdBy()));
        return tblMaintainFixed;

    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl固定标志维护记录成功", "tbl固定标志维护记录成功", ExcelType.XSSF),
                TblMaintainFixed.class, successTblMaintainFixed);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl固定标志维护记录失败", "tbl固定标志维护记录失败", ExcelType.XSSF),
                TblMaintainFixed.class, failedTblMaintainFixed);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl固定标志维护记录航标列表失败", "tbl固定标志维护记录航标列表失败", ExcelType.XSSF),
//                TblPlanAreaList.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl固定标志维护记录航标列表成功", "tbl固定标志维护记录航标列表成功", ExcelType.XSSF),
//                TblPlanAreaList.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblMaintainFixed/" + System.currentTimeMillis() + "tbl固定标志维护记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblMaintainFixed/" + System.currentTimeMillis() + "tbl固定标志维护记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblMaintainFixed/" + "tbl固定标志维护记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblMaintainFixed/" + "tbl固定标志维护记录航标列表-成功.xlsx");


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
