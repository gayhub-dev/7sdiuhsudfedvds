package com.ctvit.network.func;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.ctvit.network.model.ApiResult;
import com.ctvit.network.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import p014b4.InterfaceC0454n;

/* loaded from: classes.dex */
public class ApiResultFunc<T> implements InterfaceC0454n<ResponseBody, ApiResult<T>> {
    public Gson gson = new GsonBuilder().excludeFieldsWithModifiers(16, 128, 8).serializeNulls().create();
    public Type type;

    public ApiResultFunc(Type type) {
        this.type = type;
    }

    private ApiResult parseApiResult(String str, ApiResult apiResult) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has("code")) {
            apiResult.setCode(jSONObject.getInt("code"));
        }
        if (jSONObject.has("data")) {
            apiResult.setData(jSONObject.getString("data"));
        }
        if (jSONObject.has(NotificationCompat.CATEGORY_MESSAGE)) {
            apiResult.setMsg(jSONObject.getString(NotificationCompat.CATEGORY_MESSAGE));
        }
        return apiResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p014b4.InterfaceC0454n
    public ApiResult<T> apply(ResponseBody responseBody) {
        ApiResult<T> apiResult = (ApiResult<T>) new ApiResult();
        apiResult.setCode(0);
        Type type = this.type;
        try {
            if (!(type instanceof ParameterizedType)) {
                try {
                    String strString = responseBody.string();
                    Class<T> cls = Utils.getClass(this.type, 0);
                    if (cls.equals(String.class)) {
                        apiResult.setData(strString);
                        apiResult.setCode(0);
                    } else {
                        apiResult.setData(this.gson.fromJson(strString, (Class) cls));
                    }
                } catch (IOException e7) {
                    e7.printStackTrace();
                    apiResult.setMsg(e7.getMessage());
                }
            } else if (ApiResult.class.isAssignableFrom((Class) ((ParameterizedType) type).getRawType())) {
                Class<T> cls2 = Utils.getClass(((ParameterizedType) this.type).getActualTypeArguments()[0], 0);
                Class cls3 = Utils.getClass(this.type, 0);
                try {
                    try {
                        String strString2 = responseBody.string();
                        if (List.class.isAssignableFrom(cls3) || !cls2.equals(String.class)) {
                            apiResult.setData(this.gson.fromJson(strString2, (Class) cls2));
                        } else {
                            apiResult.setData(strString2);
                            apiResult.setCode(0);
                        }
                    } catch (Exception e8) {
                        e8.printStackTrace();
                        apiResult.setMsg(e8.getMessage());
                    }
                } finally {
                }
            } else {
                apiResult.setMsg("ApiResult.class.isAssignableFrom(cls) err!!");
            }
            return apiResult;
        } finally {
        }
    }
}
