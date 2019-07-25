package top.hting.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryEntity {

    // 主键
    private String pid;


    // 航标主键
    private String markId;


    // 数据父级主键
    private String parentId;


}
