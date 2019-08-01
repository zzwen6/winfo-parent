package top.hting.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hting.entity.oracle.TblMark;
import top.hting.mapper.oracle.TblMarkMapper;

import java.util.List;

@Service
public class MarkService {

    @Autowired
    TblMarkMapper tblMarkMapper;


    /**
     * cbs的航标转移后pid无法对应，这里给出通过名字查询like，然后作martTablecode匹配
     *
     *
     * @return
     */
    public TblMark getByMarkName(String markName, String markTableCode){
        List<TblMark> tblMarks = tblMarkMapper.findByMarkName(markName);

        for (TblMark mark : tblMarks) {

            if (StringUtils.equals(markTableCode, mark.getMarkTableCode())) {
                return mark;
            }

        }
        return null;
    }



}
