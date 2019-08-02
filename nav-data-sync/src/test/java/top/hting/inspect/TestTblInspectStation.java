package top.hting.inspect;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import top.hting.entity.oracle.inspect.TblInspectStation;
import top.hting.entity.oracle.paln.TblPlanAreaList;
import top.hting.entity.sqlserver.inspect.CbsInspectStation;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.inspect.TblInspectStationMaper;
import top.hting.mapper.sqlserver.inspect.CbsInspectStationMapper;

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
 * AIS基站巡检记录
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTblInspectStation {

    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的AIS基站巡检记录
    List<TblInspectStation> successTblInspectStation = new ArrayList<TblInspectStation>();
    List<TblInspectStation> failedTblInspectStation = new ArrayList<TblInspectStation>();

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
    CbsInspectStationMapper cbsInspectStationMapper;
    @Autowired
    TblInspectStationMaper tblInspectStationMaper;

    @Test
    public void synTblInspectStation() {
        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsInspectStation> cbsInspectStation = cbsInspectStationMapper.selectByMap(params);
        for (CbsInspectStation station : cbsInspectStation) {

            TblInspectStation inspectStation = tblInspectStationMaper.selectById(station.getPid());

            if (inspectStation == null) {

                inspectStation = cbs2tbl(station);

                try {
//                    tblInspectStationMaper.insert(inspectStation);

                    successTblInspectStation.add(inspectStation);

                } catch (Exception e) {
                    inspectStation.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblInspectStation.add(inspectStation);
                    e.printStackTrace();
                }

            }


        }
    }

    private TblInspectStation cbs2tbl(CbsInspectStation s) {
        TblInspectStation station1 = new TblInspectStation();
        station1.setInspectid(s.getPid());
        station1.setPlanmarkid(s.getCbsinspectlistfid());
        station1.setInspectnumber(generatePlanNumber(s.getInspecttime(), seq));
        station1.setInspecttime(s.getInspecttime());
        station1.setExaminer(s.getExaminer());
        station1.setOrgcode(s.getOrgcode());
        station1.setMarkid(s.getBasnavigationmarkfid());
        station1.setMarktablecode(s.getMarktable());
        station1.setMarkname(s.getMarkname());
        station1.setFirmcode(s.getFirmcode());
        station1.setWaterproofcode(s.getWaterproofcode());
        station1.setExteriorcode(s.getExteriorcode());
        station1.setAvhfstatuscode(s.getAvhfstatuscode());
        station1.setAgpsstatuscode(s.getAgpsstatuscode());
        station1.setUpsinvoltagestatuscode(s.getUpsinvoltagestatuscode());
        station1.setUpsoutvoltagestatuscode(s.getUpsoutvoltagestatuscode());
        station1.setUpseffectivecode(s.getUpseffectivecode());
        station1.setRoomtempstatuscode(s.getRoomtempstatuscode());
        station1.setRoomhumiditystatuscode(s.getRoomhumiditystatuscode());
        station1.setRoomduststatuscode(s.getRoomduststatuscode());
        station1.setDevicewiringstatuscode(s.getDevicewiringstatuscode());
        station1.setNodenumber("");
        station1.setComputerstatuscode(null);
        station1.setSparecomputerstatuscode(null);
        station1.setServersoftwaresystemcode(null);
        station1.setPowerequipmentcode(null);
        station1.setUpsworkstatuscode(null);
        station1.setNetworkmachinefaultcode(null);
        station1.setRemark(s.getRemark());
        station1.setLinestatuscode(null);
        station1.setSysCreated(s.getSysCreated());
        station1.setSysCreatedBy(getName(s.getSysCreatedBy()));
        station1.setSysOrg(s.getSysOrg());
        station1.setSysDept(s.getSysDept());
        station1.setSysLastUpd(s.getSysLastUpd());
        station1.setSysLastUpdBy(getName(s.getSysLastUpdBy()));
        return station1;

    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tblAIS基站巡检记录成功", "tblAIS基站巡检记录成功", ExcelType.XSSF),
                TblInspectStation.class, successTblInspectStation);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tblAIS基站巡检记录失败", "tblAIS基站巡检记录失败", ExcelType.XSSF),
                TblInspectStation.class, failedTblInspectStation);


//        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tblAIS基站巡检记录航标列表失败", "tblAIS基站巡检记录航标列表失败", ExcelType.XSSF),
//                TblPlanAreaList.class, failedTblAreaList);
//
//        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tblAIS基站巡检记录航标列表成功", "tblAIS基站巡检记录航标列表成功", ExcelType.XSSF),
//                TblPlanAreaList.class, successTblAreaList);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblInspectStation/" + System.currentTimeMillis() + "tblAIS基站巡检记录-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblInspectStation/" + System.currentTimeMillis() + "tblAIS基站巡检记录-失败.xlsx");
//            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tblAIS基站巡检记录航标列表-失败.xlsx");
//            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPlanArea/" + "tblAIS基站巡检记录航标列表-成功.xlsx");


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
