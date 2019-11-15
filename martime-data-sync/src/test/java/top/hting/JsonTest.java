package top.hting;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zzwen6
 * @date 2019/11/13 15:48
 */
public class JsonTest {

    @Test
    public void json() throws IOException {
        Set<String> sets = new HashSet<>();

        String string = FileUtils.readFileToString(new File("C:\\Users\\NC040\\Desktop\\mts\\json.js"), "utf-8");

        JSONObject object = JSON.parseObject(string);

        JSONArray datas = object.getJSONArray("datas");

        for (Object data : datas) {
            JSONObject object1 = (JSONObject) data;

            sets.add(  object1.getString("icon"));


        }
        System.out.println(sets.size());
        System.out.println(sets.toString());


    }


}
