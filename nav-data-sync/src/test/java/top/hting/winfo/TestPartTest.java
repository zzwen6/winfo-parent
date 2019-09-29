package top.hting.winfo;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.oracle.dic.TestPart;
import top.hting.mapper.oracle.dic.TestPartMapper;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPartTest {

    @Autowired
    TestPartMapper testPartMapper;

    @Test
    public void insert() {
        for (int i=400;i<500;i++){
            testPartMapper.insert(new TestPart(i, new Date(), i+":" + DateFormatUtils.format(new Date(), "yyyyMMddhhmmss")));

        }

    }

}
