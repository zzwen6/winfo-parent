package top.hting;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import top.hting.wsdl4a.LDAPDataService_PortType;
import top.hting.wsdl4a.LDAPDataService_ServiceLocator;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * 10	广西海事局	广西海事局	广西海事局
 * 1001	南宁海事局	南宁海事局	南宁
 * 1009	梧州海事局	梧州海事局	梧州
 * 1002	北海海事局	北海海事局	北海
 * 1004	钦州海事局	钦州海事局	钦州
 * 1005	柳州海事局	柳州海事局	柳州
 * 1006	河池海事局	河池海事局	河池
 * 1007	桂林海事局	桂林海事局	桂林
 * 1008	贵港海事局	贵港海事局	贵港
 * 1010	百色海事局	百色海事局	百色
 * 1011	来宾海事局	来宾海事局	来宾
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * 4A 机构数据同步
     * Rigorous Test :-)
     */
    @Test
    public void syn4AOrg() throws ServiceException, RemoteException {

        LDAPDataService_ServiceLocator locator = new LDAPDataService_ServiceLocator();

        LDAPDataService_PortType portType = locator.getLDAPDataServicePort();

        JSONObject object = new JSONObject();

        object.put("orgCode", "1001");
        object.put("isQuerySubOrg", "1");
        object.put("isQueryDept", "1");

        String queryJson = object.toJSONString();


        String resultJson = portType.orgQueryServices_queryOrg4Detail(queryJson);


        System.out.println(resultJson);

    }
}
