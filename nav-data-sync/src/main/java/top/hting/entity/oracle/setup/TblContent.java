package top.hting.entity.oracle.setup;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hting.entity.OBase;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("Tbl_Content")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TblContent extends OBase implements Serializable {

    /**
     * 内容主键
     */
    @TableId("contentId")
    private String contentId;

    /**
     * 文件全名
     */
    private String fullName;

    /**
     * 存储文件夹
     */
    private String url;

    /**
     * 存储全名
     */

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件大小kb
     */
    private BigDecimal fileSize;



    /**
     * 缩略图路径
     */
    private String thumbnailUrl;
}
