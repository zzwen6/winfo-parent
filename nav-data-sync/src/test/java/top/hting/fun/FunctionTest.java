package top.hting.fun;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.entity.oracle.dic.Dictionarydetail;
import top.hting.entity.oracle.dic.TblFunction;
import top.hting.mapper.oracle.dic.DictionarydetailMapper;
import top.hting.mapper.oracle.dic.TblFunctionMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 功能代码测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FunctionTest {

    @Autowired
    DictionarydetailMapper dictionarydetailMapper;
    @Autowired
    TblFunctionMapper tblFunctionMapper;

    @Test
    public void importFunctionDataTest() {

        String file = "C:\\Users\\NC040\\Desktop\\功能代码.xlsx";

        ImportParams params = new ImportParams();
        List<TblFunction> objects = ExcelImportUtil.importExcel(new File(file), TblFunction.class, params);

        // 转换数据值
        List<Dictionarydetail> function_type = dictionarydetailMapper.findByDictCode("pl_function_type");
        List<Dictionarydetail> pl_color = dictionarydetailMapper.findByDictCode("pl_color");
        List<Dictionarydetail> pl_rhythm_name = dictionarydetailMapper.findByDictCode("pl_rhythm_name");
        List<Dictionarydetail> pl_light_period = dictionarydetailMapper.findByDictCode("pl_light_period");
        List<Dictionarydetail> pl_rhythm_param = dictionarydetailMapper.findByDictCode("pl_rhythm_param");
        List<Dictionarydetail> pl_rhythm_detail = dictionarydetailMapper.findByDictCode("pl_rhythm_detail");

        Map<String, String> map_function_type = new HashMap<>();
        Map<String, String> map_pl_color = new HashMap<>();
        Map<String, String> map_pl_rhythm_name = new HashMap<>();
        Map<String, String> map_pl_light_period = new HashMap<>();
        Map<String, String> map_pl_rhythm_param = new HashMap<>();
        Map<String, String> map_pl_rhythm_detail = new HashMap<>();

        function_type.forEach(p ->{
            map_function_type.put(p.getItemName(), p.getItemCode());

        });
        pl_color.forEach(p ->{
            map_pl_color.put(p.getItemName(), p.getItemCode());

        });
        pl_rhythm_name.forEach(p ->{
            map_pl_rhythm_name.put(p.getItemName(), p.getItemCode());

        });
        pl_light_period.forEach(p ->{
            map_pl_light_period.put(p.getItemName(), p.getItemCode());

        });
        pl_rhythm_param.forEach(p ->{
            map_pl_rhythm_param.put(p.getItemName(), p.getItemCode());

        });
        pl_rhythm_detail.forEach(p ->{
            map_pl_rhythm_detail.put(p.getItemName(), p.getItemCode());

        });


        List<TblFunction> failList = new ArrayList<>();
        // 所有对象
        for (TblFunction object : objects) {
            TblFunction tmp = new TblFunction();
            boolean flag = true;
            BeanUtils.copyProperties(object, tmp);

            tmp.setSysCreated(new Date());
            tmp.setSysLastUpd(new Date());




            if (map_function_type.get(tmp.getFunctionCode()) == null) {
                log.error("{} 航标功能代码为空", tmp.getReferenceId());
                flag = false;
            }
            if (map_pl_color.get(tmp.getLightColorCode()) == null) {
                log.error("{} 灯质颜色代码为空", tmp.getReferenceId());
                flag = false;
            }
            if (map_pl_rhythm_name.get(tmp.getLightRhythmCode()) == null) {
                log.error("{} 灯质名称代码为空", tmp.getReferenceId());
                flag = false;
            }
            if (map_pl_light_period.get(tmp.getLightPeriodCode()) == null) {
                log.error("{} 灯质信号周期代码为空", tmp.getReferenceId());
                flag = false;
            }
            if (map_pl_rhythm_param.get(tmp.getLightParameterCode()) == null) {
                log.error("{} 节奏参数代码为空", tmp.getReferenceId());
                flag = false;
            }
            if (map_pl_rhythm_detail.get(tmp.getLightRhythmCodeDetailCode()) == null) {
                log.error("{} 节奏明细代码为空", tmp.getReferenceId());
                flag = false;
            }

            if (flag) {
                tmp.setReferenceId(UUID.randomUUID().toString());

                tmp.setFunctionCode(map_function_type.get(tmp.getFunctionCode()));
                tmp.setLightColorCode(map_pl_color.get(tmp.getLightColorCode()));
                tmp.setLightRhythmCode(map_pl_rhythm_name.get(tmp.getLightRhythmCode()));
                tmp.setLightPeriodCode(map_pl_light_period.get(tmp.getLightPeriodCode()));
                tmp.setLightParameterCode(map_pl_rhythm_param.get(tmp.getLightParameterCode()));
                tmp.setLightRhythmCodeDetailCode(map_pl_rhythm_detail.get(tmp.getLightRhythmCodeDetailCode()));
                tblFunctionMapper.insert(tmp);

            }else  {
                failList.add(object);
            }



        }

        for (TblFunction function : failList) {

            System.out.println(function.toString());

        }



    }



}
