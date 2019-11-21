package top.hting.markrelation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.oracle.TblMark;
import top.hting.entity.oracle.markrelation.MarkRelation;
import top.hting.mapper.oracle.TblMarkMapper;
import top.hting.mapper.oracle.markrelation.MarkRelationMapper;

import java.util.List;

/**
 *
 * 航标附属关系修正
 * @author zzwen6
 * @date 2019/11/21 14:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMarkRelation {


    @Autowired
    MarkRelationMapper markRelationMapper;
    @Autowired
    TblMarkMapper markMapper;
    @Test
    public void fixdMarkRelationTest() {

        List<MarkRelation> markRelations = markRelationMapper.get();

        for (MarkRelation relation : markRelations) {

            String markName = relation.getMarkName();

            // 去除其他字串
            String otherMarkName = markName.replace("雷达应答器", "")
                    .replace("AIS航标", "")
                    .replace("AIS虚拟航标", "'")
                    .replace("雾钟", "");

            List<TblMark> byMarkName = markMapper.findByMarkName(otherMarkName);

            for (TblMark tblMark : byMarkName) {

                // 是否有同名航标
                if (tblMark.getMarkName().equals(otherMarkName)) {

                    System.out.println(String.format("%s:%s", tblMark.getMarkName(), tblMark.getMarkId()));
                    MarkRelation markRelation = new MarkRelation();

                    markRelation.setMarkId(tblMark.getMarkId());
                    markRelation.setMarkId2(relation.getMarkId2());


                    markRelationMapper.insert(relation);


                }


            }


        }



    }

}
