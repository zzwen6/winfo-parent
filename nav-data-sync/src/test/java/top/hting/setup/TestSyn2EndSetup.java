package top.hting.setup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.oracle.setup.TblSetupPrivate;
import top.hting.mapper.oracle.setup.TblSetupPrivateMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 同步非end表的数据到end表中
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSyn2EndSetup {
    
    @Autowired
    private TblSetupPrivateMapper setupPrivateMapper;

    @Test
    public void syn2EndTable() {
        String setupId = "00e3d2a5-2457-4859-afa8-a087863b7e53";

        TblSetupPrivate setupPrivate = setupPrivateMapper.selectById(setupId);


        // List<TblSetupPrivate> setupPrivates = new ArrayList<>();
        // setupPrivates.add(setupPrivate);
        List<TblSetupPrivate> setupPrivates = setupPrivateMapper.selectList(null);

        for (TblSetupPrivate aPrivate : setupPrivates) {

            setupPrivateMapper.updateTblSetupPrivateEnd(aPrivate);


        }




    }
    
    
}
