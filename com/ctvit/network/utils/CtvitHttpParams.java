package com.ctvit.network.utils;

import com.alibaba.fastjson.JSONObject;
import com.ctvit.network.model.HttpParams;
import p179w2.C2025a;
import p193y2.C2106a;

/* loaded from: classes.dex */
public class CtvitHttpParams {
    private static volatile CtvitHttpParams singleton;

    /* renamed from: ap */
    private String f962ap;
    private HttpParams params = new HttpParams();

    public static CtvitHttpParams getInstance() {
        if (singleton == null) {
            synchronized (CtvitHttpParams.class) {
                if (singleton == null) {
                    singleton = new CtvitHttpParams();
                }
            }
        }
        return singleton;
    }

    /* renamed from: ap */
    public CtvitHttpParams m529ap(String str) {
        this.f962ap = str;
        return this;
    }

    public HttpParams params() {
        if (this.params.urlParamsMap.isEmpty()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("adid", (Object) C2106a.m2549b());
            jSONObject.put("av", (Object) C2025a.m2376d());
            jSONObject.put("an", (Object) C2025a.m2373a());
            jSONObject.put("ap", (Object) this.f962ap);
            this.params.put("appcommon", jSONObject.toJSONString());
        }
        return this.params;
    }
}
