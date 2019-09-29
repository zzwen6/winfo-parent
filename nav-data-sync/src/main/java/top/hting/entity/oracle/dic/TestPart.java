package top.hting.entity.oracle.dic;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@TableName("Test_Part")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TestPart implements Serializable {

    private int id;
    private Date gmtCreate;

    private String name;

}
