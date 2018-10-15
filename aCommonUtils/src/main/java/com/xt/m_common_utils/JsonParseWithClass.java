package com.xt.m_common_utils;

public class JsonParseWithClass<T> implements IJsonParse<T> {
    protected Class<T> mTClass;

    public JsonParseWithClass(Class<T> TClass) {
        mTClass = TClass;
    }

    @Override
    public T fromJson(String json) {
        return JsonUtils.fromJson(json, mTClass);
    }

    public static <T> JsonParseWithClass<T> getInstance(Class<T> tClass){
        return new JsonParseWithClass<>(tClass);
    }
}
