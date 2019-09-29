package top.hting.entity.oracle.dic;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("WINFO_BIG")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class WinfoBig {
    @TableId("bid")
    private long bid;

    private String name;

    private long versionCode;

    private String remark;

    private Date gmtCreate;



}
