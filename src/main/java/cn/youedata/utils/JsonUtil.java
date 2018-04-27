package cn.youedata.utils;

import org.json.JSONObject;

public class JsonUtil {

    public static JSONObject getJson(String str){
        JSONObject jsonObject = new JSONObject(str);
        return jsonObject;
    }
}
