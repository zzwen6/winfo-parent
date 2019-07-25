package top.hting;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.QueryEntity;
import top.hting.entity.oracle.TblNews;
import top.hting.entity.oracle.TblNewsTechnology;
import top.hting.entity.sqlserver.CbsMarkNews;
import top.hting.entity.sqlserver.VCbsTwoNewsTech;
import top.hting.entity.sqlserver.tech.CbsTwoNewsTech;
import top.hting.entity.sqlserver.tech.CbsTwoNewsTechAis;
import top.hting.mapper.oracle.TblNewsMapper;
import top.hting.mapper.oracle.TblNewsTechnologyMapper;
import top.hting.mapper.sqlserver.CbsMarkNewsMapper;
import top.hting.mapper.sqlserver.CbsTwoNewsTechAisMapper;
import top.hting.mapper.sqlserver.CbsTwoNewsTechMapper;

import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {


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

    /**
     * 同步航标动态和技术参数(流程暂未在oracle中进行创建同步)
     */
    @Test
    public void synMarkNewsAndTechnology() {
        String newsid = "cc9b093f-80f1-4136-8d3a-fba55b977fc4";
        // 旧数据库中所有的航标动态数据
        // List<CbsMarkNews> cbsMarkNews = cbsMarkNewsMapper.selectList(null);
        List<CbsMarkNews> cbsMarkNews = new ArrayList<>();

        CbsMarkNews news = cbsMarkNewsMapper.selectById(newsid);
        cbsMarkNews.add(news);


        // 遍历旧数据，在新数据库中比对是否存在此航标动态
        for (CbsMarkNews markNews : cbsMarkNews) {

            // 新系统航标动态
            TblNews tblNews = tblNewsMapper.selectById(markNews.getPid());

            // 不存在航标动态，进行插入
            if (tblNews == null) {
                tblNews = convertCBS2TblNews(markNews);

                //tblNewsMapper.insert(tblNews);

                // 旧系统技术参数技术参数
                List<VCbsTwoNewsTech> twoNewsTeches = cbsMarkNewsMapper.findByParams(QueryEntity.builder().parentId(markNews.getPid()).build());

                // TODO 还差几张表，组合起来就是视图的数据
                List<CbsTwoNewsTech> cbsTwoNewsTeches = cbsTwoNewsTechMapper.findByParentNewsId(QueryEntity.builder().parentId(markNews.getPid()).build());
                List<CbsTwoNewsTechAis> cbsTwoNewsTechAis = cbsTwoNewsTechAisMapper.findByParentNewsId(QueryEntity.builder().parentId(markNews.getPid()).build());

                // 1.
                List<TblNewsTechnology> t1 = cbsTwoNewsTeches2TblNewsTechnology(cbsTwoNewsTeches);

                List<TblNewsTechnology> t2 = cbsTwoNewsTechAis2TblNewsTechnology(cbsTwoNewsTechAis);





            } else {
                // 存在航标动态，检测是技术参数是否存在，是否完整


            }

        }


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
            technology.setSysCreatedby(c.getSysCreatedby());
            technology.setSysOrg(c.getSysOrg());
            technology.setSysDept(c.getSysDept());
            technology.setSysLastUpd(c.getSysLastUpd());
            technology.setSysLastUpdBy(c.getSysLastUpdBy());
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
            technology.setSysCreatedby(c.getSysCreatedby());
            technology.setSysOrg(c.getSysOrg());
            technology.setSysDept(c.getSysDept());
            technology.setSysLastUpd(c.getSysLastUpd());
            technology.setSysLastUpdBy(c.getSysLastUpdBy());

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
                // userid1,2,可以通过cbs的创建人获取
                // username1,2 可通过导航处拟写的那个人(可能得通过cbs的流程系统来查询)
                // 其他值暂不填充，还差两三个
                .build();
    }
}
