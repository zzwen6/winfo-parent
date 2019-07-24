package top.hting;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.oracle.TblNews;
import top.hting.entity.sqlserver.CbsSitePlan;
import top.hting.mapper.oracle.TblNewsMapper;
import top.hting.mapper.sqlserver.CbsSitePlanMapper;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest 
{


    @Autowired
    TblNewsMapper tblNewsMapper;


    @Test
    public void shouldAnswerWithTrue()
    {

        System.out.println(tblNewsMapper.selectList(null).size());

    }
}
