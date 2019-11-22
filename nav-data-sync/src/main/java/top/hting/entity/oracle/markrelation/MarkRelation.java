package top.hting.entity.oracle.markrelation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 航标关联关系
 *
 * @author zzwen6
 * @date 2019/11/21 14:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarkRelation {

    private String markId;

    private String markName;

    private String markId2;

    private String markTableCode;
    private String markTableCode2;

    private String markName2;


}
