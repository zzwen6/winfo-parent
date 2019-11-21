package top.hting.mapper.oracle.markrelation;

import top.hting.entity.oracle.markrelation.MarkRelation;

import java.util.List;

/**
 * @author zzwen6
 * @date 2019/11/21 17:11
 */
public interface MarkRelationMapper {

    List<MarkRelation> get();


    int insert(MarkRelation relation);

}
