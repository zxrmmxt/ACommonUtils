package com.xt.m_common_utils;

import com.google.gson.reflect.TypeToken;

public class JsonParseWithTypeToken<T> implements IJsonParse<T> {
    protected TypeToken<T> mTypeToken;

    private JsonParseWithTypeToken(TypeToken<T> typeToken) {
        mTypeToken = typeToken;
    }

    @Override
    public T fromJson(String json) {
        return JsonUtils.fromJson(json,mTypeToken.getType());
    }

    public static <T> JsonParseWithTypeToken<T> getInstance(TypeToken<T> typeToken){
        return new JsonParseWithTypeToken<>(typeToken);
    }
}
