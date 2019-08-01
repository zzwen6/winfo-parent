package top.hting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.oracle.TblMark;
import top.hting.service.MarkService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlyTest {


    @Autowired
    MarkService markService;

    @Test
    public void testMarkService(){

        TblMark mark = markService.getByMarkName("秀英灯塔", "5020");

        System.out.println(mark);


    }





}
