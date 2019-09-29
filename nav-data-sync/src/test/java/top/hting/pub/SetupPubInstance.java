package top.hting.pub;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.hting.entity.oracle.act.ACTREPROCDEF;
import top.hting.entity.oracle.act.ACT_HI_PROCINST;
import top.hting.entity.oracle.act.ActHiActInst;
import top.hting.entity.oracle.act.FlowInstance;
import top.hting.entity.oracle.act.FlowTask;
import top.hting.entity.oracle.pub.PubInstance;
import top.hting.mapper.oracle.pub.ACTREPROCDEFMapper;
import top.hting.mapper.oracle.pub.ACT_HI_PROCINSTMapper;
import top.hting.mapper.oracle.pub.ActHiActInstMapper;
import top.hting.mapper.oracle.pub.FlowInstanceMapper;
import top.hting.mapper.oracle.pub.FlowTaskMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SetupPubInstance {
    @Autowired
    ACT_HI_PROCINSTMapper act_hi_procinstMapper;

    @Autowired
    ActHiActInstMapper actHiActInstMapper;

    @Autowired
    FlowInstanceMapper flowInstanceMapper;

    @Autowired
    FlowTaskMapper flowTaskMapper;

    @Autowired
    ACTREPROCDEFMapper actreprocdefMapper;

    @Test
    public void synPubInstance() {
        // 所有的无流程id的公用航标数据
        List<PubInstance> pubInstances = actHiActInstMapper.listSetupPubNoInstanceId();
        List<ACTREPROCDEF> actreprocdefs = actreprocdefMapper.maxProcDef("SZGY");

        ACTREPROCDEF actreprocdef = null;
        if (actreprocdefs.size()>0) {
            actreprocdef = actreprocdefs.get(0);
        }


        //
        for (PubInstance instance : pubInstances) {

            String setupId = instance.getSetupId();

            FlowInstance flowInstance = flowInstanceMapper.selectById(setupId);

            // 查询数据是否导入
            if (flowInstance != null){

                // 创建ACT流程 TODO
                ACT_HI_PROCINST actEntity = new ACT_HI_PROCINST();
                actEntity.setID_(flowInstance.getInstanceid());
                actEntity.setPROC_INST_ID_(flowInstance.getInstanceid());
                actEntity.setBUSINESS_KEY_(flowInstance.getBusinessid());
                actEntity.setPROC_DEF_ID_(actreprocdef.getId_());
                actEntity.setSTART_TIME_(flowInstance.getSysCreated());
                actEntity.setEND_TIME_(flowInstance.getSysLastUpd());
                actEntity.setDURATION_(null);
                actEntity.setSTART_USER_ID_(flowInstance.getSysCreatedBy());
                actEntity.setSTART_ACT_ID_(flowInstance.getFlownumber() + "01");
                actEntity.setEND_ACT_ID_(null);
                actEntity.setSUPER_PROCESS_INSTANCE_ID_(null);
                actEntity.setDELETE_REASON_(null);
                actEntity.setTENANT_ID_(null);
                actEntity.setNAME_(null);
                // act_hi_procinstMapper.insert(actEntity);

                // 创建流程历史 TODO  ACT_HI_ACTINST
                Map<String, Object> map = new HashMap<>();
                map.put("instanceId", setupId);
                List<FlowTask> flowTasks = flowTaskMapper.selectByMap(map);

                // 已有的流程，需要进行转换
                String excludeId = UUID.randomUUID().toString();
                for (FlowTask task : flowTasks) {
                    ActHiActInst actHiActInst = new ActHiActInst();


                    // 开始节点
                    if ("SZGY01".equals(task.getNodeNumber())) {
                        actHiActInst.setID_(UUID.randomUUID().toString());
                        actHiActInst.setPROC_DEF_ID_(actEntity.getPROC_DEF_ID_());
                        actHiActInst.setPROC_INST_ID_(actEntity.getPROC_INST_ID_());
                        actHiActInst.setEXECUTION_ID_(excludeId);
                        actHiActInst.setACT_ID_(task.getNodeNumber());
                        actHiActInst.setTASK_ID_(task.getTaskId());
                        actHiActInst.setCALL_PROC_INST_ID_(null);
                        actHiActInst.setACT_NAME_(""); // 节点名称 导航处审批
                        actHiActInst.setACT_TYPE_(""); // 类型 userTask
                        actHiActInst.setASSIGNEE_(""); // 处理人ID
                        actHiActInst.setSTART_TIME_(""); // 开始时间，
                        actHiActInst.setEND_TIME_(""); // 结束时间
                        actHiActInst.setDURATION_("");
                        actHiActInst.setDELETE_REASON_(null);
                        actHiActInst.setTENANT_ID_(null);

                    }




                }


                // 创建流程历史任务 TODO ACT_HI_TASKINST  需要配合 TBL_FLOWOPINION

            }




        }


    }


}
