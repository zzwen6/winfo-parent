package top.hting.winfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.oracle.dic.WinfoBig;
import top.hting.mapper.oracle.dic.WinfoBigMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WinfoBigTest {

    @Autowired
    WinfoBigMapper winfoBigMapper;

    @Test
    public void testUpdate(){
        WinfoBig winfoBig = winfoBigMapper.selectById(1L);

        List<Thread> threadNames = new ArrayList<>();
        List<Thread> threadsVersionCode = new ArrayList<>();

        for(int i=0;i<100;i++){
            long millis = System.currentTimeMillis();

            threadNames.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    winfoBigMapper.updateName(winfoBig.getBid(),  millis+ ":" + new Random().nextInt(10000));

                }
            }
             ));

            threadsVersionCode.add(new Thread(new Runnable() {

                @Override
                public void run() {
                    winfoBigMapper.updateVersionCode(winfoBig.getBid(), millis);
                }
            }));

        }
        threadsVersionCode.forEach(s->{ s.start(); });
        threadNames.forEach(p->{ p.start(); });



    }


    @Test
    public void insert() {

        for (long i = 2; i < 300000000; i++) {
            WinfoBig big = new WinfoBig(i, System.currentTimeMillis() +":" + i, i, System.currentTimeMillis() +":" + i, new Date());
            winfoBigMapper.insert(big);


        }

    }



}
