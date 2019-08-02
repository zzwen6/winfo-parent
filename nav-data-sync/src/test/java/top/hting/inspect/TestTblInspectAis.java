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
import top.hting.entity.oracle.inspect.TblInspectAis;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.sqlserver.inspect.CbsInspectAis;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.inspect.TblInspectAisMapper;
import top.hting.mapper.sqlserver.inspect.CbsInspectAisMapper;

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
 * AIS实体航标巡检记录
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTblInspectAis {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的AIS实体航标巡检记录
    List<TblInspectAis> successTblInspectAis = new ArrayList<TblInspectAis>();
    List<TblInspectAis> failedTblInspectAis = new ArrayList<TblInspectAis>();

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
//        seqMapper.updateValue(Seq.builder().sequenceid(seq.getSequenceid()).sequence(seq.getSequence()).build());
        // 写入文件
        saveFile();
    }

    @Autowired
    CbsInspectAisMapper cbsInspectAisMapper;
    @Autowired
    TblInspectAisMapper tblInspectAisMapper;

    /**
     * 同步  --AIS实体航标巡检记录
     */
    @Test
    public void synTblInspectAis() {
        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsInspectAis> cbsInspectAis = cbsInspectAisMapper.selectByMap(params);

        for (CbsInspectAis ais : cbsInspectAis) {

            TblInspectAis inspectAis = tblInspectAisMapper.selectById(ais.getPid());

            if (inspectAis == null) {

                inspectAis = cbs2tbl(ais);

                try {
//                    tblInspectAisMapper.insert(inspectAis);

                    successTblInspectAis.add(inspectAis);

                } catch (Exception e) {
                    inspectAis.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblInspectAis.add(inspectAis);
                    e.printStackTrace();
                }

            }


        }
    }

    private TblInspectAis cbs2tbl(CbsInspectAis a) {
        TblInspectAis ais = new TblInspectAis();
        ais.setInspectid(a.getPid());
        ais.setPlanmarkid(a.getCbsinspectlistfid());
        ais.setInspectnumber(generatePlanNumber(a.getInspecttime(), seq));
        ais.setInspecttime(a.getInspecttime());
        ais.setExaminer(a.getExaminer());
        ais.setShipid(null);
        ais.setOrgcode(a.getOrgcode());
        ais.setMarkid(a.getBasnavigationmarkfid());
        ais.setMarktablecode(a.getMarktable());
        ais.setMarkname(a.getMarkname());
        ais.setGpsstatuscode(a.getGpsstatuscode());
        ais.setGpsrepaircode(a.getGpsrepaircode());
        ais.setVhfstatuscode(a.getVhfstatuscode());
        ais.setVhfrepaircode(a.getVhfrepaircode());
        ais.setComputerstatuscode(a.getComputerstatuscode());
        ais.setComputerrepaircode(a.getComputerrepaircode());
        ais.setSupplyvoltage(a.getSupplyvoltage());
        ais.setSupplypower(a.getSupplypower());
        ais.setSupplystatuscode(a.getSupplystatuscode());
        ais.setSupplyrepaircode(a.getSupplyrepaircode());
        ais.setAissysisvisual(a.getEnvironmentstatuscode());
        ais.setIsrepair(a.getEnvironmentrepaircode());
        ais.setRemark(a.getRemark());
        ais.setNodenumber("XJJL04");
        ais.setIsEnabled(1);
        ais.setSysCreated(a.getSysCreated());
        ais.setSysCreatedBy(getName(a.getSysCreatedBy()));
        ais.setSysOrg(a.getSysOrg());
        ais.setSysDept(a.getSysDept());
        ais.setSysLastUpd(a.getSysLastUpd());
        ais.setSysLastUpdBy(getName(a.getSysLastUpdBy()));

        return ais;

    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tblAIS实体航标巡检记录成功", "tblAIS实体航标巡检记录成功", ExcelType.XSSF),
                TblInspectAis.class, successTblInspectAis);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tblAIS实体航标巡检记录失败", "tblAIS实体航标巡检记录失败", ExcelType.XSSF),
                TblInspectAis.class, failedTblInspectAis);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tblAIS实体航标巡检记录航标列表失败", "tblAIS实体航标巡检记录航标列表失败", ExcelType.XSSF),
//                TblPlanAreaList.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tblAIS实体航标巡检记录航标列表成功", "tblAIS实体航标巡检记录航标列表成功", ExcelType.XSSF),
//                TblPlanAreaList.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblInspectAis/" + System.currentTimeMillis() + "tblAIS实体航标巡检记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblInspectAis/" + System.currentTimeMillis() + "tblAIS实体航标巡检记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tblAIS实体航标巡检记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tblAIS实体航标巡检记录航标列表-成功.xlsx");


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
