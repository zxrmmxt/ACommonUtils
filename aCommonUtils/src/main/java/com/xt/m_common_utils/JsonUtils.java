package com.xt.m_common_utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/13.
 */

public class JsonUtils {
    /********************************************json的反序列化********************************************/
    /**************************json数组字符串转成对象数组**********************************/
    public static <T> List<T> fromJsonArray(String jsonArrayStr, Class<T> clazz) {
        try {
            JsonArray array = new JsonParser().parse(jsonArrayStr).getAsJsonArray();
            return getTs(array, clazz);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private static <T> List<T> getTs(JsonArray array, Class<T> tClass) {
        List<T> lst = new ArrayList<>();
        for (final JsonElement elem : array) {
            lst.add(new Gson().fromJson(elem, tClass));
        }
        return lst;
    }

    /**************************json数组字符串转成对象数组**********************************/

    /***********************************json字符串转成对象**************************************************/
    //json转成JsonObject
    public static JsonObject fromJson(String json) {
        JsonElement parse = new JsonParser().parse(json);
        return parse.getAsJsonObject();
    }

    //json字符串转成对象
    public static <T> T fromJson(String json, Class<T> tClass) {
        try {
            return new Gson().fromJson(json, tClass);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    //json字符串转成对象
    public static <T> T fromJson(String json, Type type) {
        try {
            return new Gson().fromJson(json, type);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    //取出json字符串中的字段值，这个值不是json数组(java.lang.UnsupportedOperationException: JsonObject)

    /*****************************************json字符串中取字段值*******************************************************/

    /*********json字段值是字符串***********/
    public static String getStrFromJsonObj(JsonObject obj, String key) {
        JsonElement element = obj.get(key);
        return element.getAsString();
    }


    public static String getStrfromJson(String json, String key) {
        return getStrFromJsonObj(fromJson(json), key);
    }
    /*********json字段值是字符串***********/

    /*********json字段值是json***********/
    public static String getJsonStrfromJson(String json, String key) {
        return fromJson(json).getAsJsonObject(key).toString();
    }

    /**
     * @return 对象
     */
    public static <T> T getJsonfromJson(String json, String key, Class<T> tClass) {
        JsonElement element = fromJson(json).get(key);
//        XTLogUtil.d("json对象----------------------------》" + element.toString());
        return new Gson().fromJson(element, tClass);
    }
    /*********json字段值是json***********/

    /********************json字段值是json数组**************************/
    //取出json字符串中的字段值，这个值不是json数组

    /**
     * @return json数组字符串
     */
    public static String getJsonArrayStrfromJson(String json, String key) {
        return fromJson(json).getAsJsonArray(key).toString();
    }

    /**
     * @return 对象数组
     */
    public static <T> List<T> getJsonArrayfromJson(String json, String key, Class<T> tClass) {
        return getTs(fromJson(json).getAsJsonArray(key), tClass);
    }
    /********************json字段值是json数组**************************/

    /*****************************************json字符串中取字段值*******************************************************/

    /********************************************json的反序列化********************************************/


    /********************************************json的序列化********************************************/

    //对象转成JsonObject
    public static <T extends Object> JsonObject fromObj(T t) {
        return fromJson(toJson(t, t.getClass()));
    }

    /**
     * Map转成json
     *
     * @param map
     * @return
     */
    public static String toJson(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }

        StringBuilder paras = new StringBuilder();
        paras.append("{");
        Iterator<Map.Entry<String, String>> ite = map.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) ite.next();
            paras.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
            if (ite.hasNext()) {
                paras.append(",");
            }
        }
        paras.append("}");
        return paras.toString();
    }

    //键值对生成json
    public static String toJson(String key, String value) {
        String s = null;
        try {
            s = new JSONObject().put(key, value).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * @param key_values {key,value}
     */
    /*public static String key_valuesToJson(String[]... key_values) {
        JSONObject jsonObject = new JSONObject();
        for (String[] key_value : key_values) {
            try {
                jsonObject.put(key_value[0], key_value[1]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject.toString();
    }*/

    /**
     * @param key_values {key,value}
     */
    public static String key_valuesToJson(String[]... key_values) {
        Map<String, String> stringStringMap = new HashMap<>();
        for (String[] key_value : key_values) {
            stringStringMap.put(key_value[0], key_value[1]);
        }

        return objectToJson(stringStringMap);
    }

    //对象转成json字符串
    public static <T> String toJson(T t, Class<? extends Object> tClass) {
        return new Gson().toJson(t, tClass);
    }

    /**
     * 最牛逼的方法，可以将Object类型的对象转成json字符串
     * 1、javabean对象转成json字符串
     * 2、javabean对象数组转成json数组
     */
    public static String objectToJson(Object object) {
        return new Gson().toJson(object);
    }

    public static String updateJsonField(String json, String key, String value) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            json = jsonObject.put(key, value).toString();
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /********************************************json的序列化********************************************/

    public static boolean hasJsonField(String json, String key) {
        JsonElement parse = new JsonParser().parse(json);
        JsonObject asJsonObject = parse.getAsJsonObject();
        return asJsonObject.has(key);
    }
}
