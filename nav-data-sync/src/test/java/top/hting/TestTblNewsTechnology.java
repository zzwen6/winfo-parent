package top.hting;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.QueryEntity;
import top.hting.entity.oracle.TblNews;
import top.hting.entity.oracle.TblNewsTechnology;
import top.hting.entity.oracle.TblUser;
import top.hting.entity.sqlserver.CbsMarkNews;
import top.hting.entity.sqlserver.VCbsOneNewsTech;
import top.hting.entity.sqlserver.VCbsTwoNewsTech;
import top.hting.entity.sqlserver.tech.CbsOneNewsTech;
import top.hting.entity.sqlserver.tech.CbsOneNewsTechAis;
import top.hting.entity.sqlserver.tech.CbsOneNewsTechDGPS;
import top.hting.entity.sqlserver.tech.CbsOneNewsTechRadar;
import top.hting.entity.sqlserver.tech.CbsOneNewsTechVoice;
import top.hting.entity.sqlserver.tech.CbsTwoNewsTech;
import top.hting.entity.sqlserver.tech.CbsTwoNewsTechAis;
import top.hting.entity.sqlserver.tech.CbsTwoNewsTechDGPS;
import top.hting.entity.sqlserver.tech.CbsTwoNewsTechRadar;
import top.hting.entity.sqlserver.tech.CbsTwoNewsTechVoice;
import top.hting.mapper.oracle.tech.TblNewsMapper;
import top.hting.mapper.oracle.tech.TblNewsTechnologyMapper;
import top.hting.mapper.oracle.TblUserMapper;
import top.hting.mapper.sqlserver.tech.CbsMarkNewsMapper;
import top.hting.mapper.sqlserver.tech.CbsOneNewsTechAisMapper;
import top.hting.mapper.sqlserver.tech.CbsOneNewsTechDGPSMapper;
import top.hting.mapper.sqlserver.tech.CbsOneNewsTechMapper;
import top.hting.mapper.sqlserver.tech.CbsOneNewsTechRadarMapper;
import top.hting.mapper.sqlserver.tech.CbsOneNewsTechVoiceMapper;
import top.hting.mapper.sqlserver.tech.CbsTwoNewsTechAisMapper;
import top.hting.mapper.sqlserver.tech.CbsTwoNewsTechDGPSMapper;
import top.hting.mapper.sqlserver.tech.CbsTwoNewsTechMapper;
import top.hting.mapper.sqlserver.tech.CbsTwoNewsTechRadarMapper;
import top.hting.mapper.sqlserver.tech.CbsTwoNewsTechVoiceMapper;
import top.hting.mapper.sqlserver.tech.VCbsOneNewsTechMapper;
import top.hting.mapper.sqlserver.tech.VCbsTwoNewsTechMapper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 航标动态，技术参数
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTblNewsTechnology {


    @Autowired
    TblNewsMapper tblNewsMapper;

    @Autowired
    TblNewsTechnologyMapper tblNewsTechnologyMapper;

    @Autowired
    CbsMarkNewsMapper cbsMarkNewsMapper;

    @Autowired
    CbsTwoNewsTechMapper cbsTwoNewsTechMapper;
    @Autowired
    CbsTwoNewsTechAisMapper cbsTwoNewsTechAisMapper;
    @Autowired
    CbsTwoNewsTechDGPSMapper cbsTwoNewsTechDGPSMapper;
    @Autowired
    CbsTwoNewsTechRadarMapper cbsTwoNewsTechRadarMapper;
    @Autowired
    CbsTwoNewsTechVoiceMapper cbsTwoNewsTechVoiceMapper;

    // 一类动态技术数据
    @Autowired
    CbsOneNewsTechMapper cbsOneNewsTechMapper;
    @Autowired
    CbsOneNewsTechAisMapper cbsOneNewsTechAisMapper;
    @Autowired
    CbsOneNewsTechDGPSMapper cbsOneNewsTechDGPSMapper;
    @Autowired
    CbsOneNewsTechRadarMapper cbsOneNewsTechRadarMapper;
    @Autowired
    CbsOneNewsTechVoiceMapper cbsOneNewsTechVoiceMapper;


    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    VCbsTwoNewsTechMapper vCbsTwoNewsTechMapper;
    @Autowired
    VCbsOneNewsTechMapper vCbsOneNewsTechMapper;

    final Map<String, TblUser> userMap = new HashMap<>();


    String cbsMarkNewsSuccessFileName = "旧系统航标动态-成功.xlsx";
    String cbsMarkNewsFailedFileName = "旧系统航标动态-失败.xlsx";
    String tblNewsSuccessFileName = "新系统航标动态-成功.xlsx";
    String tblNewsFailedFileName = "新系统航标动态-失败.xlsx";

    String tblTechSuccessFileName = "新系统技术参数-新增-成功.xlsx";
    String tblTechFailedFileName = "新系统技术参数-新增-失败.xlsx";
    String tblTechUpdateSuccessFileName = "新系统技术参数-更新-成功.xlsx";
    String tblTechUpdateFailedFileName = "新系统技术参数-更新-失败.xlsx";

    // 旧系统航标动态成功，失败列表
    List<CbsMarkNews> cbsMarkNewsSuccessList = new ArrayList<>();
    List<CbsMarkNews> cbsMarkNewsFailedList = new ArrayList<>();

    // 新系统成功，失败列表
    List<TblNews> tblNewsSuccessList = new ArrayList<>();
    List<TblNews> tblNewsFailedList = new ArrayList<>();

    // 新系统技术参数 新增、修改的成功失败项
    List<TblNewsTechnology> tblTechSuccessList = new ArrayList<>();
    List<TblNewsTechnology> tblTechFailedList = new ArrayList<>();
    List<TblNewsTechnology> tblTechUpdateSuccessList = new ArrayList<>();
    List<TblNewsTechnology> tblTechUpdateFailedList = new ArrayList<>();

    /**
     * 同步航标动态和技术参数(流程暂未在oracle中进行创建同步)
     */
    @Test
    public void synMarkNewsAndTechnology() {
        String newsid = "42b621de-6862-4ffd-abff-a1c589ff2a06";
        // 旧数据库中所有的航标动态数据
        Map<String, Object> params = new HashMap<>();
        params.put("sysDeleted", 0);
        List<CbsMarkNews> cbsMarkNews = cbsMarkNewsMapper.selectByMap(params);
       // List<CbsMarkNews> cbsMarkNews = new ArrayList<>();

       // CbsMarkNews news = cbsMarkNewsMapper.selectById(newsid);
       // cbsMarkNews.add(news);

        List<TblUser> tblUsers = tblUserMapper.selectList(null);

        tblUsers.forEach(tblUser -> {
            userMap.put(tblUser.getUserId(), tblUser);
        });




        // 遍历旧数据，在新数据库中比对是否存在此航标动态
        for (CbsMarkNews markNews : cbsMarkNews) {

            // 新系统航标动态
            TblNews tblNews = tblNewsMapper.selectById(markNews.getPid());

            // 不存在航标动态，进行插入
            if (tblNews == null) {
                tblNews = convertCBS2TblNews(markNews);
                if (StringUtils.contains(tblNews.getTitle(), "测试")){
                    continue;
                }
                try {
                    tblNewsMapper.insert(tblNews);

                    tblNewsSuccessList.add(tblNews);
                    cbsMarkNewsSuccessList.add(markNews);
                } catch (Exception e) {
                    e.printStackTrace();
                    tblNews.setSysOrg("失败备注:" + e.getLocalizedMessage());
                    tblNewsFailedList.add(tblNews);
                    cbsMarkNewsFailedList.add(markNews);

                    // 失败后没必要再进行技术参数的添加
                    continue;

                }

                // 旧系统技术参数转tbl技术参数
                List<TblNewsTechnology> allTblNewsTechnology = getAllTblNewsTechnology(markNews);

                // 技术参数增加到新系统中
                for (TblNewsTechnology technology : allTblNewsTechnology) {

                    try {
                        tblNewsTechnologyMapper.insert(technology);

                        tblTechSuccessList.add(technology);

                    } catch (Exception e) {
                        // typeCode有可能空值，这里再取出视图的数据，然后再填充。非空约束
                        if (e.getLocalizedMessage().contains("ORA-01400")) {
                            VCbsTwoNewsTech vCbsTwoNewsTech = vCbsTwoNewsTechMapper.selectById(technology.getTechnologyId());
                            technology.setTypeCode(vCbsTwoNewsTech.getTypeCode());
                            // 重新尝试新增
                            try {
                                tblNewsTechnologyMapper.insert(technology);

                                tblTechSuccessList.add(technology);
                            }catch (Exception e1) {
                                technology.setRemark(e.getLocalizedMessage());
                                tblTechFailedList.add(technology);
                                e.printStackTrace();


                            }
                            continue;
                        }


                        e.printStackTrace();
                        technology.setRemark(e.getLocalizedMessage());
                        tblTechFailedList.add(technology);
                    }
                }



            } else {
                // 存在航标动态，检测是技术参数是否存在，是否完整
                List<TblNewsTechnology> allTblNewsTechnology = getAllTblNewsTechnology(markNews);
                for (TblNewsTechnology technology : allTblNewsTechnology) {
                    // 更新或者新增
                    TblNewsTechnology temp = null;
                    if ( (temp = tblNewsTechnologyMapper.selectById(technology.getTechnologyId()) ) != null) {
                        try {
                            // 防止typecode空值
                            technology.setTypeCode(temp.getTypeCode());
                            tblNewsTechnologyMapper.updateById(technology);

                            tblTechUpdateSuccessList.add(technology);

                        } catch (Exception e) {
                            technology.setRemark(e.getLocalizedMessage());
                            tblTechUpdateFailedList.add(technology);
                        }
                    }else {
                        try {

                            tblNewsTechnologyMapper.insert(technology);

                            tblTechSuccessList.add(technology);

                        } catch (Exception e) {

                            if (e.getLocalizedMessage().contains("ORA-01400")) {
                                if (StringUtils.equals(markNews.getNewsCode(), "01")) {
                                    VCbsOneNewsTech vCbsOneNewsTech = vCbsOneNewsTechMapper.selectById(technology.getTechnologyId());
                                    technology.setTypeCode(vCbsOneNewsTech.getTypeCode());
                                }else {

                                    VCbsTwoNewsTech vCbsTwoNewsTech = vCbsTwoNewsTechMapper.selectById(technology.getTechnologyId());
                                    technology.setTypeCode(vCbsTwoNewsTech.getTypeCode());
                                }



                                // 重新尝试新增
                                try {
                                    tblNewsTechnologyMapper.insert(technology);

                                    tblTechSuccessList.add(technology);
                                }catch (Exception e1) {
                                    technology.setRemark(e.getLocalizedMessage());
                                    tblTechFailedList.add(technology);


                                }
                                continue;
                            }

                            technology.setRemark(e.getLocalizedMessage());
                            tblTechFailedList.add(technology);
                        }
                    }
                }


            }

        }


        saveFile();


    }

    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("cbs航标动态成功", "cbs航标动态成功", ExcelType.XSSF),
                CbsMarkNews.class, cbsMarkNewsSuccessList);



        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("cbs航标动态失败", "cbs航标动态失败", ExcelType.XSSF),
                CbsMarkNews.class, cbsMarkNewsFailedList);


        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("tbl航标动态失败", "tbl航标动态失败", ExcelType.XSSF),
                TblNews.class, tblNewsFailedList);

        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("tbl航标动态成功", "tbl航标动态成功", ExcelType.XSSF),
                TblNews.class, tblNewsSuccessList);


        Workbook workbook4 = ExcelExportUtil.exportExcel(new ExportParams("tbl航标技术参数新增成功", "tbl航标技术参数新增成功", ExcelType.XSSF),
                TblNewsTechnology.class, tblTechSuccessList);

        Workbook workbook5 = ExcelExportUtil.exportExcel(new ExportParams("tbl航标技术参数新增失败", "tbl航标技术参数新增失败", ExcelType.XSSF),
                TblNewsTechnology.class, tblTechFailedList);

        Workbook workbook6 = ExcelExportUtil.exportExcel(new ExportParams("tbl航标技术参数更新成功", "tbl航标技术参数更新成功", ExcelType.XSSF),
                TblNewsTechnology.class, tblTechUpdateSuccessList);

        Workbook workbook7 = ExcelExportUtil.exportExcel(new ExportParams("tbl航标技术参数更新失败", "tbl航标技术参数更新失败", ExcelType.XSSF),
                TblNewsTechnology.class, tblTechUpdateFailedList);

        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synMarkNewsAndTechnology/" +System.currentTimeMillis()+ cbsMarkNewsSuccessFileName);
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synMarkNewsAndTechnology/" +System.currentTimeMillis()+ cbsMarkNewsFailedFileName);
            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synMarkNewsAndTechnology/" +System.currentTimeMillis()+ tblNewsFailedFileName);
            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synMarkNewsAndTechnology/" +System.currentTimeMillis()+ tblNewsSuccessFileName);
            FileOutputStream fos4 = new FileOutputStream("D:/winfo/syn/synMarkNewsAndTechnology/" +System.currentTimeMillis()+ tblTechSuccessFileName);
            FileOutputStream fos5 = new FileOutputStream("D:/winfo/syn/synMarkNewsAndTechnology/" +System.currentTimeMillis()+ tblTechFailedFileName);
            FileOutputStream fos6 = new FileOutputStream("D:/winfo/syn/synMarkNewsAndTechnology/" +System.currentTimeMillis()+ tblTechUpdateSuccessFileName);
            FileOutputStream fos7 = new FileOutputStream("D:/winfo/syn/synMarkNewsAndTechnology/" +System.currentTimeMillis()+ tblTechUpdateFailedFileName);


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

    /**
     * 获取某航标所有的技术参数，转换成tbl的
     * @param markNews
     * @return
     */
    private List<TblNewsTechnology> getAllTblNewsTechnology(CbsMarkNews markNews) {
        // List<VCbsTwoNewsTech> twoNewsTeches = cbsMarkNewsMapper.findByParams(QueryEntity.builder().parentId(markNews.getPid()).build());
        List<TblNewsTechnology> technologies = new ArrayList<>();
        if (StringUtils.equals(markNews.getNewsCode(), "01")) {
            List<CbsOneNewsTech> cbsOneNewsTeches = cbsOneNewsTechMapper.findByParentNewsId(QueryEntity.builder().parentId(markNews.getPid()).build());
            List<CbsOneNewsTechAis> cbsOneNewsTechAis = cbsOneNewsTechAisMapper.findByParentNewsId(QueryEntity.builder().parentId(markNews.getPid()).build());
            List<CbsOneNewsTechDGPS> cbsOneNewsTechDGPS = cbsOneNewsTechDGPSMapper.findByParentNewsId(QueryEntity.builder().parentId(markNews.getPid()).build());
            List<CbsOneNewsTechRadar> cbsOneNewsTechRadars = cbsOneNewsTechRadarMapper.findByParentNewsId(QueryEntity.builder().parentId(markNews.getPid()).build());
            List<CbsOneNewsTechVoice> cbsOneNewsTechVoices = cbsOneNewsTechVoiceMapper.findByParentNewsId(QueryEntity.builder().parentId(markNews.getPid()).build());

            List<CbsTwoNewsTech> cbsTwoNewsTeches = new ArrayList<>();
            List<CbsTwoNewsTechAis> cbsTwoNewsTechAis =  new ArrayList<>();
            List<CbsTwoNewsTechDGPS> cbsTwoNewsTechDGPS =  new ArrayList<>();
            List<CbsTwoNewsTechRadar> cbsTwoNewsTechRadars =  new ArrayList<>();
            List<CbsTwoNewsTechVoice> cbsTwoNewsTechVoices =  new ArrayList<>();


            cbsOneNewsTeches.forEach(p->{
                CbsTwoNewsTech tmp = new CbsTwoNewsTech();
                BeanUtils.copyProperties(p, tmp);
                cbsTwoNewsTeches.add(tmp);
            });
            cbsOneNewsTechAis.forEach(p->{
                CbsTwoNewsTechAis tmp = new CbsTwoNewsTechAis();
                BeanUtils.copyProperties(p, tmp);
                cbsTwoNewsTechAis.add(tmp);
            });
            cbsOneNewsTechDGPS.forEach(p->{
                CbsTwoNewsTechDGPS tmp = new CbsTwoNewsTechDGPS();
                BeanUtils.copyProperties(p, tmp);
                cbsTwoNewsTechDGPS.add(tmp);
            });
            cbsOneNewsTechRadars.forEach(p->{
                CbsTwoNewsTechRadar tmp = new CbsTwoNewsTechRadar();
                BeanUtils.copyProperties(p, tmp);
                cbsTwoNewsTechRadars.add(tmp);
            });
            cbsOneNewsTechVoices.forEach(p->{
                CbsTwoNewsTechVoice tmp = new CbsTwoNewsTechVoice();
                BeanUtils.copyProperties(p, tmp);
                cbsTwoNewsTechVoices.add(tmp);
            });
            // 1.
            List<TblNewsTechnology> t1 = cbsTwoNewsTeches2TblNewsTechnology(cbsTwoNewsTeches);
            // 2.
            List<TblNewsTechnology> t2 = cbsTwoNewsTechAis2TblNewsTechnology(cbsTwoNewsTechAis);
            // 3.
            List<TblNewsTechnology> t3 = cbsTwoNewsTechDGPS2TblNewsTechnology(cbsTwoNewsTechDGPS);
            // 4.
            List<TblNewsTechnology> t4 = cbsTwoNewsTechRadars2TblNewsTechnology(cbsTwoNewsTechRadars);
            // 5.
            List<TblNewsTechnology> t5 = cbsTwoNewsTechVoices2TblNewsTechnology(cbsTwoNewsTechVoices);

            technologies.addAll(t1);
            technologies.addAll(t2);
            technologies.addAll(t3);
            technologies.addAll(t4);
            technologies.addAll(t5);

        }else if (StringUtils.equals(markNews.getNewsCode(), "02")) {

            // 几张表，组合起来就是视图的数据
            List<CbsTwoNewsTech> cbsTwoNewsTeches = cbsTwoNewsTechMapper.findByParentNewsId(QueryEntity.builder().parentId(markNews.getPid()).build());
            List<CbsTwoNewsTechAis> cbsTwoNewsTechAis = cbsTwoNewsTechAisMapper.findByParentNewsId(QueryEntity.builder().parentId(markNews.getPid()).build());
            List<CbsTwoNewsTechDGPS> cbsTwoNewsTechDGPS = cbsTwoNewsTechDGPSMapper.findByParentNewsId(QueryEntity.builder().parentId(markNews.getPid()).build());
            List<CbsTwoNewsTechRadar> cbsTwoNewsTechRadars = cbsTwoNewsTechRadarMapper.findByParentNewsId(QueryEntity.builder().parentId(markNews.getPid()).build());
            List<CbsTwoNewsTechVoice> cbsTwoNewsTechVoices = cbsTwoNewsTechVoiceMapper.findByParentNewsId(QueryEntity.builder().parentId(markNews.getPid()).build());
            // 1.
            List<TblNewsTechnology> t1 = cbsTwoNewsTeches2TblNewsTechnology(cbsTwoNewsTeches);
            // 2.
            List<TblNewsTechnology> t2 = cbsTwoNewsTechAis2TblNewsTechnology(cbsTwoNewsTechAis);
            // 3.
            List<TblNewsTechnology> t3 = cbsTwoNewsTechDGPS2TblNewsTechnology(cbsTwoNewsTechDGPS);
            // 4.
            List<TblNewsTechnology> t4 = cbsTwoNewsTechRadars2TblNewsTechnology(cbsTwoNewsTechRadars);
            // 5.
            List<TblNewsTechnology> t5 = cbsTwoNewsTechVoices2TblNewsTechnology(cbsTwoNewsTechVoices);

            technologies.addAll(t1);
            technologies.addAll(t2);
            technologies.addAll(t3);
            technologies.addAll(t4);
            technologies.addAll(t5);
        }

        return technologies;
    }

    private List<TblNewsTechnology> cbsTwoNewsTechVoices2TblNewsTechnology(List<CbsTwoNewsTechVoice> cbsTwoNewsTechVoices) {
        List<TblNewsTechnology> t1 = new ArrayList<>();
        for (CbsTwoNewsTechVoice c : cbsTwoNewsTechVoices) {
            TblNewsTechnology t = new TblNewsTechnology();
            t.setTechnologyId(c.getPid());
            t.setNewsId(c.getCbsMarkNewsFID());
            t.setSerialNumber(c.getSerialNumber());
            t.setMarkId(c.getBasNavigationMarkFID());
            t.setMarkTableCode(c.getMarkNo());
            t.setTypeCode(c.getHbTypeCode());
            t.setMarkName(c.getMarkName());

            t.setLatitudeDegree(c.getLatitudeDegree());
            t.setLatitudeMinute(c.getLatitudeMinute());
            t.setLatitudeSecond(c.getLatitudeSecond());
            t.setLongitudeDegree(c.getLongitudeDegree());
            t.setLongitudeMinute(c.getLongitudeMinute());
            t.setLongitudeSecond(c.getLongitudeSecond());

            t.setSetupTypeCodes(c.getSetTypeCode());

            t.setRemark(c.getRemark());

            t.setSysCreated(c.getSysCreated());
            t.setSysCreatedby(userMap.get(c.getSysCreatedby()) != null ? userMap.get(c.getSysCreatedby()).getUserName() : c.getSysCreatedby());
            t.setSysOrg(c.getSysOrg());
            t.setSysDept(c.getSysDept());
            t.setSysLastUpd(c.getSysLastUpd());
            t.setSysLastUpdBy((userMap.get(c.getSysLastUpdBy()) != null ? userMap.get(c.getSysLastUpdBy()).getUserName() : c.getSysLastUpdBy()));

            t1.add(t);
        }
        return t1;
    }


    private List<TblNewsTechnology> cbsTwoNewsTechRadars2TblNewsTechnology(List<CbsTwoNewsTechRadar> cbsTwoNewsTechRadars) {
        List<TblNewsTechnology> t1 = new ArrayList<>();
        for (CbsTwoNewsTechRadar c : cbsTwoNewsTechRadars) {
            TblNewsTechnology t = new TblNewsTechnology();

            t.setTechnologyId(c.getPid());
            t.setNewsId(c.getCbsMarkNewsFID());
            t.setSerialNumber(c.getSerialNumber());
            t.setMarkId(c.getBasNavigationMarkFID());
            t.setMarkTableCode(c.getMarkNo());
//            t.setMarkNameEn(c.getMarkNameE());
            t.setMarkName(c.getMarkName());

            t.setLatitudeDegree(c.getLatitudeDegree());
            t.setLatitudeMinute(c.getLatitudeMinute());
            t.setLatitudeSecond(c.getLatitudeSecond());
            t.setLongitudeDegree(c.getLongitudeDegree());
            t.setLongitudeMinute(c.getLongitudeMinute());
            t.setLongitudeSecond(c.getLongitudeSecond());

            t.setFrequency(c.getFrequency());
            t.setBandCode(c.getBandCode());
            t.setOperatingRange(c.getOperatingRange());
            t.setIdentiferCode(c.getIdentiferCode());
            t.setSetupTypeCodes(c.getSetTypeCode());
            t.setRemark(c.getRemark());

            t.setSysCreated(c.getSysCreated());
            t.setSysCreatedby(userMap.get(c.getSysCreatedby()) != null ? userMap.get(c.getSysCreatedby()).getUserName() : c.getSysCreatedby());
            t.setSysOrg(c.getSysOrg());
            t.setSysDept(c.getSysDept());
            t.setSysLastUpd(c.getSysLastUpd());
            t.setSysLastUpdBy((userMap.get(c.getSysLastUpdBy()) != null ? userMap.get(c.getSysLastUpdBy()).getUserName() : c.getSysLastUpdBy()));

            t1.add(t);
        }
        return t1;
    }

    private List<TblNewsTechnology> cbsTwoNewsTechDGPS2TblNewsTechnology(List<CbsTwoNewsTechDGPS> cbsTwoNewsTechDGPS) {
        List<TblNewsTechnology> t1 = new ArrayList<>();
        for (CbsTwoNewsTechDGPS c : cbsTwoNewsTechDGPS) {
            TblNewsTechnology t = new TblNewsTechnology();

            t.setTechnologyId(c.getPid());
            t.setNewsId(c.getCbsMarkNewsFID());
            t.setSerialNumber(c.getSerialNumber());
            t.setMarkId(c.getBasNavigationMarkFID());
            t.setMarkTableCode(c.getMarkNo());
            t.setMarkNameEn(c.getMarkNameE());
            t.setMarkName(c.getMarkName());

            t.setLatitudeDegree(c.getLatitudeDegree());
            t.setLatitudeMinute(c.getLatitudeMinute());
            t.setLatitudeSecond(c.getLatitudeSecond());
            t.setLongitudeDegree(c.getLongitudeDegree());
            t.setLongitudeMinute(c.getLongitudeMinute());
            t.setLongitudeSecond(c.getLongitudeSecond());

            t.setInformationType(c.getInformationType());
            t.setSignalFormat(c.getSignalFormat());
            t.setOperatingRange(c.getOperatingRange());
            t.setWorkTime(c.getWorkTime());
            c.getRadioIdentifier(); // TODO 这个字段不知对应哪个

            t.setModulation(c.getModulation());
            t.setBroadcastCategory(c.getBroadcastCategory());
            t.setTransferRate(c.getTransferRate());
            t.setSetupTypeCodes(c.getSetTypeCode());
            t.setRemark(c.getRemark());

            t.setSysCreated(c.getSysCreated());
            t.setSysCreatedby(userMap.get(c.getSysCreatedby()) != null ? userMap.get(c.getSysCreatedby()).getUserName() : c.getSysCreatedby());
            t.setSysOrg(c.getSysOrg());
            t.setSysDept(c.getSysDept());
            t.setSysLastUpd(c.getSysLastUpd());
            t.setSysLastUpdBy((userMap.get(c.getSysLastUpdBy()) != null ? userMap.get(c.getSysLastUpdBy()).getUserName() : c.getSysLastUpdBy()));

            t1.add(t);
        }
        return t1;
    }

    /**
     * tech ais 包装
     *
     * @param cbsTwoNewsTechAis
     * @return
     */
    private List<TblNewsTechnology> cbsTwoNewsTechAis2TblNewsTechnology(List<CbsTwoNewsTechAis> cbsTwoNewsTechAis) {
        List<TblNewsTechnology> t1 = new ArrayList<>();

        for (CbsTwoNewsTechAis c : cbsTwoNewsTechAis) {
            TblNewsTechnology technology = new TblNewsTechnology();
            technology.setTechnologyId(c.getPid());
            technology.setNewsId(c.getCbsMarkNewsFID());
            technology.setSerialNumber(c.getSerialNumber());
            technology.setMarkId(c.getBasNavigationMarkFID());
            technology.setMarkTableCode(c.getMarkNo());
            technology.setTypeCode(c.getHbTypeCode());
            technology.setMarkName(c.getMarkName());
            technology.setMarkNameEn(c.getMarkNameE());

            technology.setLatitudeDegree(c.getLatitudeDegree());
            technology.setLatitudeMinute(c.getLatitudeMinute());
            technology.setLatitudeSecond(c.getLatitudeSecond());
            technology.setLongitudeDegree(c.getLongitudeDegree());
            technology.setLongitudeMinute(c.getLongitudeMinute());
            technology.setLongitudeSecond(c.getLongitudeSecond());

            technology.setMMSI(c.getMMSI());
            technology.setVirtualMark(c.getVirtualMark());
            technology.setTransmitPower(c.getTransmitPower());
            technology.setWorkModeCode(c.getWorkModeCode());
            technology.setBroadcastInterval(c.getWorkInterval());

            technology.setRemark(c.getRemark());
            technology.setSysCreated(c.getSysCreated());
            technology.setSysCreatedby(userMap.get(c.getSysCreatedby()) != null ? userMap.get(c.getSysCreatedby()).getUserName() : c.getSysCreatedby());
            technology.setSysOrg(c.getSysOrg());
            technology.setSysDept(c.getSysDept());
            technology.setSysLastUpd(c.getSysLastUpd());
            technology.setSysLastUpdBy(userMap.get(c.getSysLastUpdBy()) != null ? userMap.get(c.getSysLastUpdBy()).getUserName() : c.getSysLastUpdBy());

            t1.add(technology);

        }
        return t1;
    }

    private List<TblNewsTechnology> cbsTwoNewsTeches2TblNewsTechnology(List<CbsTwoNewsTech> cbsTwoNewsTeches) {
        List<TblNewsTechnology> t1 = new ArrayList<>();
        for (CbsTwoNewsTech c : cbsTwoNewsTeches) {
            TblNewsTechnology technology = new TblNewsTechnology();
            technology.setTechnologyId(c.getPid());
            technology.setNewsId(c.getCbsMarkNewsFID());
            technology.setSetupTypeCodes(c.getSetTypeCode());
            technology.setSerialNumber(c.getSerialNumber());
            technology.setMarkId(c.getBasNavigationMarkFID());
            technology.setTypeCode(c.getTypeCode());
            technology.setMarkTableCode(c.getMarkNo());
            technology.setMarkName(c.getMarkName());
            technology.setMarkNameEn(null);
            technology.setLatitudeDegree(  c.getLatitudeDegree());
            technology.setLatitudeMinute(c.getLatitudeMinute());
            technology.setLatitudeSecond(c.getLatitudeSecond());
            technology.setLongitudeDegree(c.getLongitudeDegree());
            technology.setLongitudeMinute(c.getLongitudeMinute());
            technology.setLongitudeSecond(c.getLongitudeSecond());
            technology.setLightRhythmCode(c.getLightRhythmCode());
            technology.setLightParameterCode(c.getLightParameterCode());
            technology.setLightColorCode(c.getLightColorCode());
            technology.setLightPeriodCode(c.getLightPeriodCode());
            technology.setLightDetailCode(c.getDetailCode());
            technology.setLightHeight(c.getLightHeight());
            technology.setRange(c.getRange());
            technology.setMarkHeight(c.getMarkHeight());
            technology.setConstruct(c.getConstruct());
            technology.setLightDegree(null);
            technology.setLightMinute(null);
            technology.setLightSecond(null);
            technology.setMarkDegree(null);
            technology.setMarkMinute(null);
            technology.setMarkSecond(null);
            technology.setBridgeTypeCode(c.getBridgetypeCode());
            technology.setModel(null);
            technology.setOperatingRange(null);
            technology.setFrequency(null);
            technology.setBandCode(null);
            technology.setIdentiferCode(null);
            technology.setPeriod(null);
            technology.setMMSI(null);
            technology.setVirtualMark(new Short("0")); // 是否虚拟航标，给0非
            technology.setTransmitPower(null);
            technology.setWorkModeCode(null);
            technology.setBroadcastInterval(null);
            technology.setInformationType(null);
            technology.setSignalFormat(null);
            technology.setWorkTime(null);
            technology.setMarkIdentifier(null);
            technology.setModulation(null);
            technology.setBroadcastCategory(null);
            technology.setTransferRate(null);
            technology.setRemark(c.getRemark());
            technology.setSysCreated(c.getSysCreated());
            technology.setSysCreatedby(userMap.get(c.getSysCreatedby()) != null ? userMap.get(c.getSysCreatedby()).getUserName() : c.getSysCreatedby());
            technology.setSysOrg(c.getSysOrg());
            technology.setSysDept(c.getSysDept());
            technology.setSysLastUpd(c.getSysLastUpd());
            technology.setSysLastUpdBy(userMap.get(c.getSysLastUpdBy()) != null ? userMap.get(c.getSysLastUpdBy()).getUserName() : c.getSysLastUpdBy());


            t1.add(technology);
        }
        return t1;
    }

    private TblNewsTechnology convertCBStech2TblNewsTechnology(VCbsTwoNewsTech twoNewsTech) {
        TblNewsTechnology technology = new TblNewsTechnology();
        technology.setTechnologyId(twoNewsTech.getPid());
        technology.setNewsId("");
        technology.setSetupTypeCodes("");
        technology.setSerialNumber(0);
        technology.setMarkId("");
        technology.setTypeCode("");
        technology.setMarkTableCode("");
        technology.setMarkName("");
        technology.setMarkNameEn("");
        technology.setLatitudeDegree((short)0);
        technology.setLatitudeMinute((short)0);
        technology.setLatitudeSecond(new BigDecimal("0"));
        technology.setLongitudeDegree((short)0);
        technology.setLongitudeMinute((short)0);
        technology.setLongitudeSecond(new BigDecimal("0"));
        technology.setLightRhythmCode("");
        technology.setLightParameterCode("");
        technology.setLightColorCode("");
        technology.setLightPeriodCode("");
        technology.setLightDetailCode("");
        technology.setLightHeight(new BigDecimal("0"));
        technology.setRange(new BigDecimal("0"));
        technology.setMarkHeight(new BigDecimal("0"));
        technology.setConstruct("");
        technology.setLightDegree((short)0);
        technology.setLightMinute((short)0);
        technology.setLightSecond(new BigDecimal("0"));
        technology.setMarkDegree((short)0);
        technology.setMarkMinute((short)0);
        technology.setMarkSecond(new BigDecimal("0"));
        technology.setBridgeTypeCode("");
        technology.setModel("");
        technology.setOperatingRange(new BigDecimal("0"));
        technology.setFrequency("");
        technology.setBandCode("");
        technology.setIdentiferCode("");
        technology.setPeriod(0);
        technology.setMMSI("");
        technology.setVirtualMark((short)0);
        technology.setTransmitPower(new BigDecimal("0"));
        technology.setWorkModeCode("");
        technology.setBroadcastInterval((short)0);
        technology.setInformationType("");
        technology.setSignalFormat("");
        technology.setWorkTime("");
        technology.setMarkIdentifier("");
        technology.setModulation("");
        technology.setBroadcastCategory("");
        technology.setTransferRate(0);
        technology.setRemark("");
        technology.setSysCreated(new Date());
        technology.setSysCreatedby("");
        technology.setSysOrg("");
        technology.setSysDept("");
        technology.setSysLastUpd(new Date());
        technology.setSysLastUpdBy("");

        return null;
    }

    private TblNews convertCBS2TblNews(CbsMarkNews markNews) {

        return TblNews.builder()
                .newsId(markNews.getPid())
                .publishCode(markNews.getReleaseCode())
                .orgCode(markNews.getOrgCode())
                .applyNumber(markNews.getDeclareCode())
                .title(markNews.getTitle())
                .applyTime(markNews.getDeclareTime())
                .newsCode(markNews.getNewsCode())
                .contents(markNews.getContents())
                .recovery( (short)  (markNews.isRecovery() ? 1:0 ))
                .publishOrg(markNews.getUnitName())
                .reportOrg(markNews.getReportOrg())
                .copyOrg(markNews.getCopyOrg())
                .orgCode2(markNews.getOrgCodeR())
                .applyNumber2(markNews.getDeclareCodeR())
                .title2(markNews.getTitleR())
                .applyTime2(markNews.getDeclareTimeR())
                .contents2(markNews.getContentsR())
                .publishOrg2(markNews.getUnitNameR())
                .reportOrg2(markNews.getReportOrgR())
                .copyOrg2(markNews.getCopyOrgR())
                .publishCode2(markNews.getReleaseCode())
                .sysCreated(markNews.getSysCreated())
                .sysDept(markNews.getSysDept())
                .sysLastUpd(markNews.getSysLastUpd())
                .sysOrg(markNews.getSysOrg())
                .sysCreatedby(userMap.get(markNews.getSysCreatedBy()) != null ? userMap.get(markNews.getSysCreatedBy()).getUserName() : markNews.getSysCreatedBy())
                .sysLastUpdBy(userMap.get(markNews.getSysLastUpdBy()) != null ? userMap.get(markNews.getSysLastUpdBy()).getUserName() : markNews.getSysLastUpdBy())
                .nodeNumber("HBDT09") // 设定节点编号,不能为空
                .isEnabled(1) // 设置1 为可用
                .userId(markNews.getSysCreatedBy())
                .userName(userMap.get(markNews.getSysCreatedBy()) != null ? userMap.get(markNews.getSysCreatedBy()).getUserName() : markNews.getSysCreatedBy())
                // userid1,2,可以通过cbs的创建人获取
                // username1,2 可通过导航处拟写的那个人(可能得通过cbs的流程系统来查询)
                // 其他值暂不填充，还差两三个
                .build();
    }
}
