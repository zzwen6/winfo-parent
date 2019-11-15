package top.hting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.mysql.CbdtDict;
import top.hting.entity.mysql.Cbjbqk;
import top.hting.entity.mysql.Ship;
import top.hting.entity.mysql.ShipData;
import top.hting.entity.mysql.TbArea;
import top.hting.entity.mysql.TbBaseStation;
import top.hting.entity.mysql.TbDistrict;
import top.hting.entity.mysql.TbOrganization;
import top.hting.entity.mysql.TbPort;
import top.hting.mapper.mysql.CbdtDictMsaOrgMapper;
import top.hting.mapper.mysql.CbjbqkMapper;
import top.hting.mapper.mysql.ShipDataMapper;
import top.hting.mapper.mysql.ShipMapper;
import top.hting.mapper.mysql.TbAreaMapper;
import top.hting.mapper.mysql.TbBaseStationMapper;
import top.hting.mapper.mysql.TbDistrictMapper;
import top.hting.mapper.mysql.TbOrganizationMapper;
import top.hting.mapper.mysql.TbPortMapper;
import top.hting.mapper.oracle.CbdtDictMsaOrgMapper1;
import top.hting.mapper.oracle.CbjbqkMapper1;
import top.hting.mapper.oracle.ShipDataMapper1;
import top.hting.mapper.oracle.ShipMapper1;
import top.hting.mapper.oracle.TbBaseStationMapper1;
import top.hting.mapper.oracle.TbDistrictMapper1;
import top.hting.mapper.oracle.TbOrganizationMapper1;
import top.hting.mapper.oracle.TbPortMapper1;

import java.util.List;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest 
{
    @Autowired
    TbAreaMapper mysql;
    @Autowired
    top.hting.mapper.oracle.TbAreaMapper1 oracle;


    /**
     * tbAreas
     *
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        List<TbArea> tbAreas = mysql.selectByMap(null);

        for (TbArea tbArea : tbAreas) {

            top.hting.entity.oracle.TbArea target = new top.hting.entity.oracle.TbArea();
            BeanUtils.copyProperties(tbArea, target);

            oracle.save(target);

        }


    }


    @Autowired
    TbBaseStationMapper baseStationMapperMysql;
    @Autowired
    TbBaseStationMapper1 baseStationMapper1;

    @Test
    public void baseStationTest() {

        List<TbBaseStation> tbBaseStations = baseStationMapperMysql.selectByMap(null);
        for (TbBaseStation station : tbBaseStations) {

            top.hting.entity.oracle.TbBaseStation target = new top.hting.entity.oracle.TbBaseStation();
            BeanUtils.copyProperties(station, target);

            baseStationMapper1.insert(target);

        }


    }

    @Autowired
    TbDistrictMapper tbDistrictMapper;

    @Autowired
    TbDistrictMapper1 tbDistrictMapper1;

    @Test
    public void disTest() {

        List<TbDistrict> tbDistricts = tbDistrictMapper.selectByMap(null);

        for (TbDistrict tbDistrict : tbDistricts) {

            top.hting.entity.oracle.TbDistrict target = new top.hting.entity.oracle.TbDistrict();
            BeanUtils.copyProperties(tbDistrict, target);
            try {
                tbDistrictMapper1.save(target);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }


    @Autowired
    TbPortMapper portMapper;

    @Autowired
    TbPortMapper1 portMapper1;

    @Test
    public void portTest() {

        List<TbPort> tbPorts = portMapper.selectByMap(null);
        for (TbPort port : tbPorts) {

            top.hting.entity.oracle.TbPort target = new top.hting.entity.oracle.TbPort();

            BeanUtils.copyProperties(port, target);

            try {
                portMapper1.save(target);
            } catch (Exception e) {
                if (e.getMessage().contains("ORA-12899")) {

                    e.printStackTrace();
                }
            }

        }


    }


    @Autowired
    TbOrganizationMapper organizationMapper;

    @Autowired
    TbOrganizationMapper1 organizationMapper1;

    @Test
    public void orgTest() {
        List<TbOrganization> tbOrganizations = organizationMapper.selectByMap(null);

        for (TbOrganization organization : tbOrganizations) {

            top.hting.entity.oracle.TbOrganization target = new top.hting.entity.oracle.TbOrganization();

            BeanUtils.copyProperties(organization, target);

            organizationMapper1.insert(target);
        }
    }


    @Autowired
    ShipMapper shipMapper;
    @Autowired
    ShipMapper1 shipMapper1;

    @Test
    public void shipMapperTest() {

        List<Ship> ships = shipMapper.selectByMap(null);

        for (Ship ship : ships) {
            top.hting.entity.oracle.Ship target = new top.hting.entity.oracle.Ship();
            BeanUtils.copyProperties(ship,target);

            shipMapper1.insert(target);

        }


    }


    @Autowired
    CbdtDictMsaOrgMapper cbdtDictMsaOrgMapper;
    @Autowired
    CbdtDictMsaOrgMapper1 cbdtDictMsaOrgMapper1;

    @Test
    public void cbdtTest() {

        List<CbdtDict> cbdtDicts = cbdtDictMsaOrgMapper.selectByMap(null);

        for (CbdtDict dict : cbdtDicts) {

            top.hting.entity.oracle.CbdtDict target = new top.hting.entity.oracle.CbdtDict();
            BeanUtils.copyProperties(dict,target);

            cbdtDictMsaOrgMapper1.insert(target);
        }


    }


    @Autowired
    ShipDataMapper shipDataMapper;

    @Autowired
    ShipDataMapper1 shipDataMapper1;

    @Test
    public void shipDataTest() {
        List<ShipData> shipData = shipDataMapper.list();

        for (ShipData data : shipData) {
            top.hting.entity.oracle.ShipData target = new top.hting.entity.oracle.ShipData();
            BeanUtils.copyProperties(data, target);
            try {
                shipDataMapper1.insert(target);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    CbjbqkMapper cbjbqkMapper;

    @Autowired
    CbjbqkMapper1 cbjbqkMapper1;

    @Test
    public void cbjbqkTest() {

        List<Cbjbqk> cbjbqks = cbjbqkMapper.selectByMap(null);

        for (int i = cbjbqks.size() - 1; i >=0; i--) {

            Cbjbqk cbjbqk = cbjbqks.get(i);

            top.hting.entity.oracle.Cbjbqk target = new top.hting.entity.oracle.Cbjbqk();
            BeanUtils.copyProperties(cbjbqk, target);
            try {
                cbjbqkMapper1.insert(target);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }



}
