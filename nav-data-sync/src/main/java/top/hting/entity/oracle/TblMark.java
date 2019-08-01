package top.hting.entity.oracle;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.hting.entity.OBase;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
@Data
@ToString
@TableName("Tbl_Mark")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TblMark extends OBase implements Serializable {
	/**
	 * 航标主键
	 */
	@TableId("markId")
    private String markId;

    /**
     * 航标种类代码
     */
    private String typeCode;

    /**
     * 航标表编号
     */
    private String markTableCode;

    /**
     * 中文名称
     */
    private String markName;

    /**
     * 英文名称
     */
    private String markNameEn;

    /**
     * 设置日期
     */
    private Date setDate;

    /**
     * 最新重大变化日期
     */
    private Date resetDate;

    /**
     * 港口代码
     */
    private String portCode;

    /**
     * 水域代码
     */
    private String waterCode;

    /**
     * 航道代码
     */
    private String channelCode;
    /**
     * 设置地点
     */
    private String location;
    /**
     * 纬度度
     */
    private Short latitudeDegree;
    /**
     * 纬度分
     */
    private Short latitudeMinute;

    /**
     * 纬度秒
     */
    private BigDecimal latitudeSecond;

    /**
     * 纬度度
     */
    private Short longitudeDegree;

    /**
     * 纬度分
     */
    private Short longitudeMinute;

    /**
     * 纬度秒
     */
    private BigDecimal longitudeSecond;

    /**
     * 安全距离
     */
    private BigDecimal safeDistance;

    /**
     * 航标性质代码
     */
    private String kindCode;

    /**
     * 航标类别代码
     */
    private String categoryCode;

    /**
     * 维护单位代码
     */
    private String maintainOrgCode;

    /**
     * 所属单位代码
     */
    private String ownOrgCode;
    
    /**
     *管辖单位 
     */
    private String manageOrgCode;

    /**
     * 有无人值守
     */
    private Short isWatch;

    
    /**
     * 备注
     */
    private String remark;

    /**
     * 是否撤除
     */
    private Short isDismantle;




}