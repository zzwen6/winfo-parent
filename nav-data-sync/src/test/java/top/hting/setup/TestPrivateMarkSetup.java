package top.hting.setup;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.QueryEntity;
import top.hting.entity.oracle.Seq;
import top.hting.entity.oracle.TblUser;
import top.hting.entity.oracle.setup.TblContent;
import top.hting.entity.oracle.setup.TblSetPriMatanNex;
import top.hting.entity.oracle.setup.TblSetPriMaterial;
import top.hting.entity.oracle.setup.TblSetPriTechnology;
import top.hting.entity.oracle.setup.TblSetupPrivate;
import top.hting.entity.sqlserver.setup.CbsPrivateMark;
import top.hting.entity.sqlserver.setup.CbsPrivateSub;
import top.hting.entity.sqlserver.setup.CbsPrivateSubMaterial;
import top.hting.entity.sqlserver.setup.CbsPrivateTech;
import top.hting.mapper.oracle.SeqMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.oracle.setup.TblSetPriMatanNexMapper;
import top.hting.mapper.oracle.setup.TblSetPriMaterialMapper;
import top.hting.mapper.oracle.setup.TblSetPriTechnologyMapper;
import top.hting.mapper.oracle.setup.TblSetupPrivateMapper;
import top.hting.mapper.sqlserver.setup.CbsPrivateMarkMapper;
import top.hting.mapper.sqlserver.setup.CbsPrivateSubMapper;
import top.hting.mapper.sqlserver.setup.CbsPrivateSubMaterialMapper;
import top.hting.mapper.sqlserver.setup.CbsPrivateTechMapper;
import top.hting.mapper.oracle.setup.TblContentMapper;

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
 * 专用航标数据同步测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPrivateMarkSetup {


    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    SeqMapper seqMapper;


    // 新系统成功、失败的专用航标设置申请
    List<TblSetupPrivate> successTblSetupPrivate = new ArrayList<>();
    List<TblSetupPrivate> failedTblSetupPrivat = new ArrayList<>();

    // 新系统成功失败的材料，材料附件，更新或新增
    List<TblSetPriMaterial> successTblSetPriMaterial = new ArrayList<>();
    List<TblSetPriMaterial> failedTblSetPriMaterial = new ArrayList<>();
    List<TblSetPriMatanNex> successTblSetPriMatanNex = new ArrayList<>();
    List<TblSetPriMatanNex> failedTblSetPriMatanNex = new ArrayList<>();

    // 技术参数
    List<TblSetPriTechnology> successTblSetPriTechnology = new ArrayList<>();
    List<TblSetPriTechnology> failedTblSetPriTechnology = new ArrayList<>();


    final Map<String, TblUser> userMap = new HashMap<>();
    Seq seq = null;

    @Before
    public void init() {
        List<TblUser> tblUsers = tblUserMapper.selectList(null);

        tblUsers.forEach(tblUser -> {
            userMap.put(tblUser.getUserId(), tblUser);
        });
        //seq = seqMapper.selectById("HBXS");

    }

    @After
    public void after() {
        // 重新更新序列
//        seqMapper.updateValue(Seq.builder().sequenceid(seq.getSequenceid()).sequence(seq.getSequence()).build());
        // 写入文件
        saveFile();
    }

    @Autowired
    CbsPrivateMarkMapper cbsPrivateMarkMapper;
    @Autowired
    CbsPrivateSubMapper cbsPrivateSubMapper;
    @Autowired
    CbsPrivateSubMaterialMapper cbsPrivateSubMaterialMapper;
    @Autowired
    CbsPrivateTechMapper cbsPrivateTechMapper;

    @Autowired
    TblSetupPrivateMapper tblSetupPrivateMapper;
    @Autowired
    TblSetPriMatanNexMapper tblSetPriMatanNexMapper;
    @Autowired
    TblSetPriMaterialMapper tblSetPriMaterialMapper;
    @Autowired
    TblSetPriTechnologyMapper tblSetPriTechnologyMapper;

    @Autowired
    TblContentMapper tblContentMapper;

    /**
     * 同步专用航标数据
     */
    @Test
    public void synTblPrivateSetup() {

        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);

        // 所有专用航标有效数据
        List<CbsPrivateMark> cbsPrivateMarks = cbsPrivateMarkMapper.selectByMap(params);

        for (CbsPrivateMark privateMark : cbsPrivateMarks) {

            TblSetupPrivate tblSetupPrivate = tblSetupPrivateMapper.selectById(privateMark.getPid());

            if (tblSetupPrivate == null) {

                tblSetupPrivate = cbs2Tbl(privateMark);

                try {

                    // 主表插入成功
                    tblSetupPrivateMapper.insert(tblSetupPrivate);
                    successTblSetupPrivate.add(tblSetupPrivate);

                } catch (Exception e) {
                    tblSetupPrivate.setRemark("出错备注:" + e.getLocalizedMessage());
                    failedTblSetupPrivat.add(tblSetupPrivate);
                    e.printStackTrace();
                    continue;
                }


                // 提交材料和材料附件(两表一对一，子表可无)
                // 提交材料 TODO 要处理重复的数据  拿后面一半的数据
                List<CbsPrivateSub> privateSubs = cbsPrivateSubMapper.findByParams(QueryEntity.builder().parentId(privateMark.getPid()).build());
                if (privateSubs != null && privateSubs.size() > 0) {

                    for (int i = privateSubs.size() / 2; i < privateSubs.size(); i++) {
                        CbsPrivateSub privateSub = privateSubs.get(i);
                        // 申请材料
                        TblSetPriMaterial material = privateSubs2Tbl(privateSub);
                        try {
                            tblSetPriMaterialMapper.insert(material);
                            successTblSetPriMaterial.add(material);
                            // 材料附件
                            List<CbsPrivateSubMaterial> subMaterials = cbsPrivateSubMaterialMapper.findByParentId(QueryEntity.builder().parentId(privateSub.getPid()).build());
                            for (CbsPrivateSubMaterial subMaterial : subMaterials) {

                                TblSetPriMatanNex matanNex = subMaterial2Tbl(subMaterial);

                                // 报错就把content表给删除了
                                try {
                                    tblSetPriMatanNexMapper.insert(matanNex);
                                    successTblSetPriMatanNex.add(matanNex);
                                } catch (Exception ex) {
                                    matanNex.setRemark(ex.getLocalizedMessage());
                                    failedTblSetPriMatanNex.add(matanNex);
                                    ex.printStackTrace();
                                    try {
                                        tblContentMapper.deleteById(matanNex.getContentid());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            }

                        } catch (Exception e) {
                            material.setRemark(e.getLocalizedMessage());
                            failedTblSetPriMaterial.add(material);

                            e.printStackTrace();
                        }


                    }
                }


                // 技术数据 拿后面一半的数据
                List<CbsPrivateTech> techList = cbsPrivateTechMapper.findByParams(QueryEntity.builder().parentId(privateMark.getPid()).build());
                if (techList != null && techList.size() > 0) {
                    for (int i = techList.size() / 2; i < techList.size(); i++) {
                        CbsPrivateTech tech = techList.get(i);
                        TblSetPriTechnology technology = cbsTech2Tbl(tech);


                        try {
                            tblSetPriTechnologyMapper.insert(technology);
                            successTblSetPriTechnology.add(technology);
                        } catch (Exception e) {
                            technology.setRemark(e.getLocalizedMessage());
                            failedTblSetPriTechnology.add(technology);
                            e.printStackTrace();
                        }


                    }

                }


            }


        }


    }

    private TblSetPriTechnology cbsTech2Tbl(CbsPrivateTech m) {
        TblSetPriTechnology technology = new TblSetPriTechnology();

        technology.setTechnologyid(m.getPid());
        technology.setSetupid(m.getCbsPrivateMarkFID());
        technology.setSetuptypecodes(m.getSetTypeCode());
        technology.setSerialnumber(m.getSerialNumber());
        technology.setMarkid(m.getBasNavigationMarkFID());
        technology.setTypecode(m.getTypeCode());
        technology.setMarktablecode(null); // 通过markId去查 TODO
        technology.setMarkname(m.getMarkName());
        technology.setMarknameen(null);
        technology.setLatitudedegree(m.getLatitudeDegree());
        technology.setLatitudeminute(m.getLatitudeMinute());
        technology.setLatitudesecond(m.getLatitudeSecond());
        technology.setLongitudedegree(m.getLongitudeDegree());
        technology.setLongitudeminute(m.getLongitudeMinute());
        technology.setLongitudesecond(m.getLongitudeSecond());
        technology.setLightrhythmcode(m.getLightRhythmCode());
        technology.setLightparametercode(m.getLightParameterCode());
        technology.setLightcolorcode(m.getLightColorCode());
        technology.setLightperiodcode(m.getLightPeriodCode());
        technology.setLightdetailcode(m.getDetailCode());
        technology.setLightheight(m.getLightHeight());
        technology.setRange(m.getRange());
        technology.setMarkheight(m.getMarkHeight());
        technology.setConstruct(m.getConstruct());
//        technology.setLightdegree();
//        technology.setLightminute();
//        technology.setLightsecond();
//        technology.setMarkdegree();
//        technology.setMarkminute();
//        technology.setMarksecond();
        technology.setBridgetypecode(m.getBridgetypeCode());
//        technology.setModel();
//        technology.setOperatingrange();
//        technology.setFrequency();
//        technology.setBandcode();
//        technology.setIdentifercode();
//        technology.setPeriod();
//        technology.setMmsi();
//        technology.setVirtualmark();
//        technology.setTransmitpower();
//        technology.setWorkmodecode();
//        technology.setBroadcastinterval();
//        technology.setInformationtype();
//        technology.setSignalformat();
//        technology.setWorktime();
//        technology.setMarkidentifier();
//        technology.setModulation();
//        technology.setBroadcastcategory();
//        technology.setTransferrate();
        technology.setRemark(m.getRemark());

        technology.setSysCreated(m.getSysCreated());
        technology.setSysCreatedBy(getName(m.getSysCreatedBy()));
        technology.setSysOrg(m.getSysOrg());
        technology.setSysDept(m.getSysDept());
        technology.setSysLastUpd(m.getSysLastUpd());
        technology.setSysLastUpdBy(getName(m.getSysLastUpdBy()));

        return technology;
    }

    private TblSetPriMatanNex subMaterial2Tbl(CbsPrivateSubMaterial m) {
        TblSetPriMatanNex matanNex = new TblSetPriMatanNex();
        matanNex.setAnnexid(m.getPid());
        matanNex.setMaterialid(m.getCbsPrivateSubFID());
        matanNex.setSerialnumber(m.getSerialNumber());

        // 此id需要对tbl_content进行操作，然后伪造出来 TODO

        String contentId = getTblContentId(m);
        matanNex.setContentid(contentId); // 不知道怎么填

        matanNex.setSysCreated(m.getSysCreated());
        matanNex.setSysCreatedBy(getName(m.getSysCreatedBy()));
        matanNex.setSysOrg(m.getSysOrg());
        matanNex.setSysDept(m.getSysDept());
        matanNex.setSysLastUpd(m.getSysLastUpd());
        matanNex.setSysLastUpdBy(getName(m.getSysLastUpdBy()));
        return matanNex;
    }

    private String getTblContentId(CbsPrivateSubMaterial m) {
        TblContent content = new TblContent();

        content.setFullName(m.getAnnexName());
        content.setUrl(m.getSaveName());
        String fileType = "png"; // 假设是图片
        String extension = FilenameUtils.getExtension(m.getSaveName());
        if (StringUtils.isNotBlank(extension)) {
            fileType = extension;
        }

        content.setFileType(fileType);
        content.setFileSize(null);
        content.setThumbnailUrl(null);

        content.setSysCreated(m.getSysCreated());
        content.setSysCreatedBy(getName(m.getSysCreatedBy()));
        content.setSysOrg(m.getSysOrg());
        content.setSysDept(m.getSysDept());
        content.setSysLastUpd(m.getSysLastUpd());
        content.setSysLastUpdBy(getName(m.getSysLastUpdBy()));


        content.setContentId(m.getPid());

        tblContentMapper.insert(content);

        return content.getContentId();

    }

    private TblSetPriMaterial privateSubs2Tbl(CbsPrivateSub m) {
        TblSetPriMaterial material = new TblSetPriMaterial();
        material.setMaterialid(m.getPid());
        material.setSetupid(m.getCbsPrivateMarkFID());
        material.setSerialnumber(m.getSerialNumber());
        material.setMaterialname(m.getMaterialName());
        material.setTypecode(m.getTypeCode());
        material.setQuantity(m.getQuantity());

        material.setSysCreated(m.getSysCreated());
        material.setSysCreatedBy(getName(m.getSysCreatedBy()));
        material.setSysOrg(m.getSysOrg());
        material.setSysDept(m.getSysDept());
        material.setSysLastUpd(m.getSysLastUpd());
        material.setSysLastUpdBy(getName(m.getSysLastUpdBy()));

        return material;

    }

    private TblSetupPrivate cbs2Tbl(CbsPrivateMark m) {
        TblSetupPrivate tblSetupPrivate = new TblSetupPrivate();
        tblSetupPrivate.setSetupid(m.getPid());

        tblSetupPrivate.setApplynumber(m.getDeclareCode());
        tblSetupPrivate.setApplyorgname(m.getDeclareUnit());
        tblSetupPrivate.setApplytime(m.getDeclareTime());
        tblSetupPrivate.setApplyusername(m.getManager());

        tblSetupPrivate.setAcceptorgname(m.getOrgCode()); // code 是否要转
        tblSetupPrivate.setOrgcode(m.getOrgCode());
        tblSetupPrivate.setProjectname(m.getSetDeclare());
        tblSetupPrivate.setExplain(m.getReason());
        tblSetupPrivate.setDesignorgname(m.getDesignUnit());
        tblSetupPrivate.setConstructorgname(m.getWorkeUnit());
        tblSetupPrivate.setMaintainorgcode(m.getOrgCode3());
        tblSetupPrivate.setMaintainorgname(m.getMaintenanceUnit());
        tblSetupPrivate.setSetuptime(m.getTenderTime());
        tblSetupPrivate.setBookingperiod(m.getBookingPeriod());
        tblSetupPrivate.setSetuptype(m.getSetTypeCode());
        tblSetupPrivate.setLifetime(m.getLifetime());
        tblSetupPrivate.setNodenumber("SZZY05"); //
        tblSetupPrivate.setFliepath(null);

        tblSetupPrivate.setSysCreated(m.getSysCreated());
        tblSetupPrivate.setSysCreatedBy(getName(m.getSysCreatedBy()));
        tblSetupPrivate.setSysOrg(m.getSysOrg());
        tblSetupPrivate.setSysDept(m.getSysDept());
        tblSetupPrivate.setSysLastUpd(m.getSysLastUpd());
        tblSetupPrivate.setSysLastUpdBy(getName(m.getSysLastUpdBy()));
        return tblSetupPrivate;

    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("tbl专用航标设置申请成功", "tbl专用航标设置申请成功", ExcelType.XSSF),
                TblSetupPrivate.class, successTblSetupPrivate);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("tbl专用航标设置申请失败", "tbl专用航标设置申请失败", ExcelType.XSSF),
                TblSetupPrivate.class, failedTblSetupPrivat);


        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl专用航标设置申请材料失败", "tbl专用航标设置申请材料失败", ExcelType.XSSF),
                TblSetPriMaterial.class, failedTblSetPriMaterial);

        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl专用航标设置申请材料成功", "tbl专用航标设置申请材料成功", ExcelType.XSSF),
                TblSetPriMaterial.class, successTblSetPriMaterial);
        // 材料附件
        Workbook workbook4 = ExcelExportUtil.exportExcel(new ExportParams("tbl专用航标设置材料附件失败", "tbl专用航标设置材料附件失败", ExcelType.XSSF),
                TblSetPriMatanNex.class, failedTblSetPriMatanNex);

        Workbook workbook5 = ExcelExportUtil.exportExcel(new ExportParams("tbl专用航标设置材料附件成功", "tbl专用航标设置材料附件成功", ExcelType.XSSF),
                TblSetPriMatanNex.class, successTblSetPriMatanNex);
        // 技术参数
        Workbook workbook6 = ExcelExportUtil.exportExcel(new ExportParams("tbl专用航标设置技术参数失败", "tbl专用航标设置技术参数失败", ExcelType.XSSF),
                TblSetPriTechnology.class, failedTblSetPriTechnology);

        Workbook workbook7 = ExcelExportUtil.exportExcel(new ExportParams("tbl专用航标设置技术参数成功", "tbl专用航标设置技术参数成功", ExcelType.XSSF),
                TblSetPriTechnology.class, successTblSetPriTechnology);


        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synTblPrivateSetup/" + System.currentTimeMillis()+"tbl专用航标设置申请-成功.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synTblPrivateSetup/" + System.currentTimeMillis()+"tbl专用航标设置申请-失败.xlsx");
            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synTblPrivateSetup/" + System.currentTimeMillis()+"tbl专用航标设置申请材料-失败.xlsx");
            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synTblPrivateSetup/" + System.currentTimeMillis()+"tbl专用航标设置申请材料-成功.xlsx");

            FileOutputStream fos4 = new FileOutputStream("D:/winfo/syn/synTblPrivateSetup/" + System.currentTimeMillis()+"tbl专用航标设置材料附件-失败.xlsx");
            FileOutputStream fos5 = new FileOutputStream("D:/winfo/syn/synTblPrivateSetup/" + System.currentTimeMillis()+"tbl专用航标设置材料附件-成功.xlsx");
            FileOutputStream fos6 = new FileOutputStream("D:/winfo/syn/synTblPrivateSetup/" + System.currentTimeMillis()+"tbl专用航标设置技术参数-失败.xlsx");
            FileOutputStream fos7 = new FileOutputStream("D:/winfo/syn/synTblPrivateSetup/" + System.currentTimeMillis()+"tbl专用航标设置技术参数-成功.xlsx");



            workbook.write(fos);
            workbook1.write(fos1);
            workbook2.write(fos2);
            workbook3.write(fos3);
            workbook4.write(fos4);
            workbook5.write(fos5);
            workbook6.write(fos6);
            workbook7.write(fos7);

            fos.close();
            fos1.close();
            fos2.close();
            fos3.close();
            fos4.close();
            fos5.close();
            fos6.close();
            fos7.close();


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
