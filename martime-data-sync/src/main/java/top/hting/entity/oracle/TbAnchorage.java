package top.hting.entity.oracle;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zzwen6
 * @date 2019/11/11 10:53
 */
@TableName("tb_anchorage")
@Data
public class TbAnchorage implements Serializable {
    @TableId("ID")
    private Integer ID;
    private String MTBH;
    private String MTMC;
    private String MTYWMC;
    private String MTWZ;
    private String MTZB;
    private String MTJZRQ;
    private String MTYZDW;
    private String MTDJ;
    private String MTBWSL;
    private String MTCD;
    private String MTJG;
    private String MTGN;
    private String QYSS;
    private String XGR;
    private String XGSJ;
    private String LXDH;
    private String JYR;
    private String GXHSC;
    private String SSGQ;
    private String MTXZ;
    private String SSLY;
    private String AISXH;
    private String VTSXH;
    private String MTSFKF;
    private String SFWXPMT;
    private String FWNLYS;
    private String HWLX;
    private String SSZYQ;
    private String XYJ;
    private String PVCWYL;
    private String XJXWYL;
    private String ZLSWYL;
    private String XFP;
    private String SM;
    private String BZ;
    private String QYSYKD;
    private String SFAZCCTV;
    private String CJR;
    private String CJSJ;
    private String YXZD;
    private String YSLXID;
    private String XSLX;
    private String SSHSJG;
    private String SYR;
    private String LXR;
    private String WYCGRL;
    private String WSZSCLNL;
    private String NSXXYJ;
    private String XSXXYJ;
    private String XYZ;
    private String KJSXCSZL;
    private String GKWXHWZYFZYXQ;
    private String ZYHZ;
    private String GHWYJCZFF;
    private String FDDBR;
    private String WXHCXHFG;
    private String HDWLANXHFG;
    private String anchorage_area;
    private String organization_uuid;
}
