package top.hting.markrelation;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.oracle.TblMark;
import top.hting.entity.oracle.markrelation.MarkRelation;
import top.hting.mapper.oracle.TblMarkMapper;
import top.hting.mapper.oracle.markrelation.MarkRelationMapper;

import java.util.ArrayList;
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

        List<String> markIds = new ArrayList<>();


        for (MarkRelation relation : markRelations) {

            String markName = relation.getMarkName();

            // 去除其他字串
            String otherMarkName = markName.replace("雷达应答器", "")
                    .replace("AIS航标", "")
                    .replace("虚拟航标", "")
                    .replace("AIS", "'")
                    .replace("虚拟", "")
                    .replace("雾钟", "");

            List<TblMark> byMarkName = markMapper.findByMarkName(otherMarkName);

            for (TblMark tblMark : byMarkName) {

                // 是否有同名航标
                if (tblMark.getMarkName().equals(otherMarkName)) {

                    System.out.println(String.format("%s:%s", tblMark.getMarkName(), tblMark.getMarkId()));
                    MarkRelation markRelation = new MarkRelation();

                    markRelation.setMarkId(tblMark.getMarkId());
                    markRelation.setMarkId2(relation.getMarkId());

                    System.out.println(markRelation.toString());
                    markRelationMapper.insert(markRelation);

                    markIds.add(tblMark.getMarkId());
                }


            }
            // 航标表编号匹配
            String markTableCode = relation.getMarkTableCode();

            char c = markTableCode.charAt(markTableCode.length() - 1);
            if ( !(c == 'A' || c == 'R') || !markTableCode.contains("雷应")) {
                continue;
            }


            String tmp = markTableCode.substring(0, markTableCode.length() - 1);


            List<TblMark> markTableCodeList =  markMapper.findByMarkTableCode(tmp);
            List<MarkRelation> relations = new ArrayList<>();
            for (TblMark mark : markTableCodeList) {

                if (mark.getMarkName().contains("AIS") ||
                    mark.getMarkName().contains("虚拟") ||
                        mark.getMarkName().contains("雷达")
                ) {
                    continue;
                }

                if (!markIds.contains(mark.getMarkId()) &&!StringUtils.equals(mark.getMarkTableCode(), markTableCode)
                    && markTableCode.length() == mark.getMarkTableCode().length() + 1
                ) {
                    // 判断是否匹配成功，航标名规则

                    MarkRelation relation1 = new MarkRelation();

                    relation1.setMarkId(mark.getMarkId());
                    relation1.setMarkId2(relation.getMarkId());
                    relation1.setMarkName2(relation.getMarkName());

                    relation1.setMarkName(mark.getMarkName());
                    relation1.setMarkTableCode(markTableCode);
                    relation1.setMarkTableCode2(mark.getMarkTableCode());

                     System.out.println(relation1);
                    relations.add(relation1);
                }

            }
            // 如果是一一对应关系，就进行插入数据库
            if (relations.size() == 1) {
                // insert
                markRelationMapper.insert(relations.get(0));
            }




        }



    }

}
