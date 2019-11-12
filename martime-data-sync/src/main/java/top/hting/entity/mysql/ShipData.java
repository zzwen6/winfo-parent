package top.hting.entity.mysql;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zzwen6
 * @date 2019/11/11 15:29
 */
@TableName("SHIPDATA")
@Data
public class ShipData implements Serializable {
    @TableId("SHIP_REG_NO")
    private String SHIP_REG_NO;
    private String SHIP_ID;
    private String SHIP_NAME_CN;
    private String SHIP_NAME_EN;
    private String ORIG_SHIP_NAME_CN;
    private String SHIP_FIRSTREG_NO;
    private String SHIP_IMO;
    private String MMSI;
    private String REGPORT_CODE;
    private String SHIP_ROUTE_CODE;
    private String SHIP_TYPE_CODE;
    private String SHIP_VALUE;
    private Double SHIP_SUMMER_DRAFT;
    private String SHIP_WIND_LEVEL;
    private Double SHIP_MINIMUM_FREEBOARD;
    private Integer SHIP_CONTAINER_NUM;
    private Integer SHIP_PARKING_NUM;
    private Integer SHIP_PASSENGER_NUM;
    private String IC_CARD_NO;
    private String MORTGAGE_FLAG;
    private String BAREBOAT_FLAG;
    private String ALTER_FLAG;
    private String STATUS_FLAG;
    private String HANDOUT_CARD_FLAG;
    private String FINANCIAL_LEASE_FLAG;
    private String HIBERNATE_FLAG;
    private String TRIAL_SHIP_FLAG;
    private String REMARK;
    private String SHIP_INSPECT_NO;
    private String SHIP_CALLSIGN;
    private String SAILINGAREA_CODE;
    private String SHIP_HULL_MATERIAL_CODE;
    private Date SHIP_BUILT_DATE;
    private String SHIPYARD_CN;
    private String SHIPYARD_EN;
    private String SHIP_BUILT_ADDR_CN;
    private String SHIP_BUILT_ADDR_EN;
    private Double SHIP_LENGTH;
    private Double SHIP_BREADTH;
    private Double SHIP_DEPTH;
    private Double SHIP_GROSSTON;
    private Double SHIP_NETTON;
    private String SHIP_DWT;
    private String SHIP_ENGINE_TYPE_CODE;
    private Double SHIP_ENGINE_POWER;
    private Integer SHIP_ENGINE_NUM;
    private String SHIP_PROPELLER_NUM;
    private String SHIP_PROPELLER_KIND_CODE;
    private String ORIG_REGPORT_NAME;
    private String SOURCE_CODE;
    private String DATA_ORG_CODE;
    private Date CREATE_DATE;
    private String CREATOR;
    private Date LAST_UPDATE_DATE;
    private String UPDATE_BY;
    private String DELETE_FLAG;
    private String PROCESS_STATUS;
    private String SHIP_ID_FLAG;
    private String ORIG_SHIP_NAME_EN;
    private String ORIG_SHIP_REG_NO;
    private String SHIP_REGION_TYPE;
    private Date SHIP_REBUILT_DATE;
    private String SHIP_REBUILT_ADDR_CN;
    private String SHIP_REBUILT_ADDR_EN;
    private Date ORIG_DELETION_DATE;
    private String PERMANENT_SEAL_REMARK;
    private String DETENTION_FLAG;
    private String SHIPID_SEAL_FLAG;
    private String ORG_CODE;
    private String CREATOR_CODE;
    private Date CREATE_TIME;
    private String OPERATOR_CODE;
    private Date OPERATE_TIME;
    private String OPERATE_FLAG;
}
