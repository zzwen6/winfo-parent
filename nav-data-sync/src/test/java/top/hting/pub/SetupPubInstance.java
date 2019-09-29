package top.hting.pub;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.hting.entity.oracle.act.ACTHITASKINST;
import top.hting.entity.oracle.act.ACTREPROCDEF;
import top.hting.entity.oracle.act.ACT_HI_PROCINST;
import top.hting.entity.oracle.act.ActHiActInst;
import top.hting.entity.oracle.act.FlowInstance;
import top.hting.entity.oracle.act.FlowTask;
import top.hting.entity.oracle.pub.PubInstance;
import top.hting.entity.oracle.setup.TblSetPriMatanNex;
import top.hting.entity.oracle.setup.TblSetPriMaterial;
import top.hting.entity.oracle.setup.TblSetPriTechnology;
import top.hting.entity.oracle.setup.TblSetupPrivate;
import top.hting.mapper.oracle.pub.ACTHITASKINSTMapper;
import top.hting.mapper.oracle.pub.ACTREPROCDEFMapper;
import top.hting.mapper.oracle.pub.ACT_HI_PROCINSTMapper;
import top.hting.mapper.oracle.pub.ActHiActInstMapper;
import top.hting.mapper.oracle.pub.FlowInstanceMapper;
import top.hting.mapper.oracle.pub.FlowTaskMapper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

    @Autowired
    ACTHITASKINSTMapper acthitaskinstMapper;

    Map<String, String> nodeNameMap = new HashMap<>();


    List<PubInstance> pubSuccessList = new ArrayList<>();
    List<PubInstance> pubFailedList = new ArrayList<>();

    List<ACT_HI_PROCINST> procinstsSuccess = new ArrayList<>();
    List<ACT_HI_PROCINST> procinstsFailed = new ArrayList<>();


    List<ActHiActInst> ActHiActInstSuccess = new ArrayList<>();
    List<ActHiActInst> ActHiActInstFailed = new ArrayList<>();

    List<ACTHITASKINST> ACTHITASKINSTSuccess = new ArrayList<>();
    List<ACTHITASKINST> ACTHITASKINSTFailed = new ArrayList<>();

    @Test
    public void synPubInstance() {
        // 所有的无流程id的公用航标数据
        List<PubInstance> pubInstances = actHiActInstMapper.listSetupPubNoInstanceId();
        List<ACTREPROCDEF> actreprocdefs = actreprocdefMapper.maxProcDef("SZGY");

        ACTREPROCDEF actreprocdef = null;
        if (actreprocdefs.size()>0) {
            actreprocdef = actreprocdefs.get(0);
        }
        nodeNameMap.put("SZGY01", "开始");
        // 任务的节点 2-6
        nodeNameMap.put("SZGY02", "航标处申请");
        nodeNameMap.put("SZGY03", "航标处审查");
        nodeNameMap.put("SZGY04", "航标处签发");
        nodeNameMap.put("SZGY05", "导航处技术审查");
        nodeNameMap.put("SZGY06", "导航处审批");

        nodeNameMap.put("SZGY07", "办结（同意）");

        //
        List<PubInstance> pubInstancesTemp = new ArrayList<>();
        pubInstancesTemp.add(pubInstances.get(0));

        for (PubInstance instance : pubInstancesTemp) {

            String setupId = instance.getSetupId();

            FlowInstance flowInstance = flowInstanceMapper.selectById(setupId);

            // 查询数据是否导入
            if (flowInstance != null){

                // 创建ACT流程
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
                actEntity.setEND_ACT_ID_(flowInstance.getFlownumber() + "07"); // TODO
                actEntity.setSUPER_PROCESS_INSTANCE_ID_(null);
                actEntity.setDELETE_REASON_(null);
                actEntity.setTENANT_ID_(null);
                actEntity.setNAME_(null);
                try {
                    act_hi_procinstMapper.insert(actEntity);

                    pubSuccessList.add(instance);
                    procinstsSuccess.add(actEntity);

                } catch (Exception e) {
                    e.printStackTrace();
                    pubFailedList.add(instance);
                    procinstsFailed.add(actEntity);
                    continue;
                }

                // 创建流程历史
                Map<String, Object> map = new HashMap<>();
                map.put("instanceId", setupId);
                List<FlowTask> flowTasks = flowTaskMapper.selectByMap(map);

                // 已有的流程，需要进行转换
                String excludeId = UUID.randomUUID().toString();
                for (FlowTask task : flowTasks) {
                    ActHiActInst actHiActInst = new ActHiActInst();


                    actHiActInst.setID_(UUID.randomUUID().toString());
                    actHiActInst.setPROC_DEF_ID_(actEntity.getPROC_DEF_ID_());
                    actHiActInst.setPROC_INST_ID_(actEntity.getPROC_INST_ID_());
                    actHiActInst.setEXECUTION_ID_(excludeId);
                    actHiActInst.setACT_ID_(task.getNodeNumber());
                    actHiActInst.setTASK_ID_(task.getTaskId());
                    actHiActInst.setCALL_PROC_INST_ID_(actEntity.getPROC_INST_ID_());
                    actHiActInst.setACT_NAME_(nodeNameMap.get(task.getNodeNumber())); // 节点名称 导航处审批
                    actHiActInst.setDELETE_REASON_(null);
                    actHiActInst.setTENANT_ID_(null);
                    // 开始节点
                    if ("SZGY01".equals(task.getNodeNumber())) {

                        actHiActInst.setACT_TYPE_("startEvent"); // 类型 userTask
                        actHiActInst.setASSIGNEE_(null); // 处理人ID

                        actHiActInst.setSTART_TIME_(task.getSysCreated()); // 开始时间，
                        actHiActInst.setEND_TIME_(task.getSysCreated()); // 结束时间
                        actHiActInst.setDURATION_(0L);


                        // 结束结点
                    } else if ("SZGY07".equals(task.getNodeNumber())){
                        actHiActInst.setACT_TYPE_("endEvent"); // 类型 userTask
                        actHiActInst.setASSIGNEE_(null); // 处理人ID

                        actHiActInst.setSTART_TIME_(task.getSysCreated()); // 开始时间，
                        actHiActInst.setEND_TIME_(task.getSysCreated()); // 结束时间
                        actHiActInst.setDURATION_(0L);
                        // isPass节点
                    } else {
                        // 2-6的节点
                        actHiActInst.setACT_TYPE_("userTask"); // 类型 userTask
                        actHiActInst.setASSIGNEE_(task.getSendUserId()); // 处理人ID TODO

                        actHiActInst.setSTART_TIME_(task.getSysCreated()); // 开始时间，
                        actHiActInst.setEND_TIME_(task.getSysLastUpd()); // 结束时间
                        actHiActInst.setDURATION_(task.getSysLastUpd().getTime() - task.getSysCreated().getTime());
                    }
                    try {
                        actHiActInstMapper.insert(actHiActInst);

                        ActHiActInstSuccess.add(actHiActInst);


                    }catch (Exception e) {
                        e.printStackTrace();

                        ActHiActInstFailed.add(actHiActInst);
                    }

                    // 创建流程历史任务   ACT_HI_TASKINST  需要配合 TBL_FLOWOPINION
                    if ("SZGY02".equals(task.getNodeNumber()) ||
                            "SZGY03".equals(task.getNodeNumber()) ||
                            "SZGY04".equals(task.getNodeNumber()) ||
                            "SZGY05".equals(task.getNodeNumber()) ||
                            "SZGY06".equals(task.getNodeNumber())) {

                        ACTHITASKINST acthitaskinst = new ACTHITASKINST();
                        acthitaskinst.setID_(UUID.randomUUID().toString());
                        acthitaskinst.setPROC_DEF_ID_(actEntity.getPROC_DEF_ID_());
                        acthitaskinst.setTASK_DEF_KEY_(task.getNodeNumber());
                        acthitaskinst.setPROC_INST_ID_(actEntity.getPROC_INST_ID_());
                        acthitaskinst.setEXECUTION_ID_(excludeId);
                        acthitaskinst.setPARENT_TASK_ID_(null);
                        acthitaskinst.setNAME_(nodeNameMap.get(task.getNodeNumber())); // 节点名称
                        acthitaskinst.setDESCRIPTION_(null);
                        acthitaskinst.setOWNER_(null);
                        acthitaskinst.setASSIGNEE_(task.getSendUserId()); // 处理人 TODO 发送人?接收人
                        acthitaskinst.setSTART_TIME_(task.getSysCreated());
                        acthitaskinst.setCLAIM_TIME_(task.getSysCreated());
                        acthitaskinst.setEND_TIME_(task.getSysLastUpd());
                        acthitaskinst.setDURATION_(task.getSysLastUpd().getTime() - task.getSysCreated().getTime());
                        acthitaskinst.setDELETE_REASON_(null);
                        acthitaskinst.setPRIORITY_(null);
                        acthitaskinst.setDUE_DATE_(null);
                        acthitaskinst.setFORM_KEY_(null);
                        acthitaskinst.setCATEGORY_(null);
                        acthitaskinst.setTENANT_ID_(null);
                        try {
                            acthitaskinstMapper.insert(acthitaskinst);
                            ACTHITASKINSTSuccess.add(acthitaskinst);
                        }catch (Exception e) {
                            e.printStackTrace();
                            ACTHITASKINSTFailed.add(acthitaskinst);
                        }

                    }


                }



            }




        }


    }


    @After
    public void after(){
        saveFile();
    }


    private void saveFile() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("公用航标-成功列表", "公用航标-成功列表", ExcelType.XSSF),
                PubInstance.class, pubSuccessList);


        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("公用航标-失败列表", "公用航标-失败列表", ExcelType.XSSF),
                PubInstance.class, pubFailedList);


        Workbook workbook2 = ExcelExportUtil.exportExcel(new ExportParams("流程实例-成功", "流程实例-成功", ExcelType.XSSF),
                ACT_HI_PROCINST.class, procinstsSuccess);

        Workbook workbook3 = ExcelExportUtil.exportExcel(new ExportParams("流程实例-失败", "流程实例-失败", ExcelType.XSSF),
                ACT_HI_PROCINST.class, procinstsFailed);

        Workbook workbook4 = ExcelExportUtil.exportExcel(new ExportParams("流程所有节点-成功", "流程所有节点-成功", ExcelType.XSSF),
                ActHiActInst.class, ActHiActInstSuccess);

        Workbook workbook5 = ExcelExportUtil.exportExcel(new ExportParams("流程所有节点-失败", "流程所有节点-失败", ExcelType.XSSF),
                ActHiActInst.class, ActHiActInstFailed);

        Workbook workbook6 = ExcelExportUtil.exportExcel(new ExportParams("流程任务-成功", "流程任务-成功", ExcelType.XSSF),
                ACTHITASKINST.class, ACTHITASKINSTSuccess);

        Workbook workbook7 = ExcelExportUtil.exportExcel(new ExportParams("流程任务-失败", "流程任务-失败", ExcelType.XSSF),
                ACTHITASKINST.class, ACTHITASKINSTFailed);



        try {
            FileOutputStream fos = new FileOutputStream("D:/winfo/syn/synPubInstance/" + System.currentTimeMillis()+"公用航标-成功列表.xlsx");
            FileOutputStream fos1 = new FileOutputStream("D:/winfo/syn/synPubInstance/" + System.currentTimeMillis()+"公用航标-失败列表.xlsx");

            FileOutputStream fos2 = new FileOutputStream("D:/winfo/syn/synPubInstance/" + System.currentTimeMillis()+"流程实例-成功.xlsx");
            FileOutputStream fos3 = new FileOutputStream("D:/winfo/syn/synPubInstance/" + System.currentTimeMillis()+"流程实例-失败.xlsx");

            FileOutputStream fos4 = new FileOutputStream("D:/winfo/syn/synPubInstance/" + System.currentTimeMillis()+"流程所有节点-成功.xlsx");
            FileOutputStream fos5 = new FileOutputStream("D:/winfo/syn/synPubInstance/" + System.currentTimeMillis()+"流程所有节点-失败.xlsx");

            FileOutputStream fos6 = new FileOutputStream("D:/winfo/syn/synPubInstance/" + System.currentTimeMillis()+"流程所有任务-成功.xlsx");
            FileOutputStream fos7 = new FileOutputStream("D:/winfo/syn/synPubInstance/" + System.currentTimeMillis()+"流程所有任务-失败.xlsx");



            workbook.write(fos);
            workbook1.write(fos1);
            workbook2.write(fos2);
            workbook3.write(fos3);
            workbook4.write(fos4);
            workbook5.write(fos5);
            workbook6.write(fos6);
            workbook7.write(fos7);

            fos.close();
            fos1.close();
            fos2.close();
            fos3.close();
            fos4.close();
            fos5.close();
            fos6.close();
            fos7.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
