package com.cctv.p025tv.module.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.cctv.p025tv.module.service.bean.WebSocketMsgInfo;
import com.tencent.mars.xlog.Log;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.cache.DiskLruCache;
import p009b.C0413b;
import p021c3.C0512a;
import p031d3.C0869b;
import p031d3.InterfaceC0868a;
import p078j2.C1196k;
import p086k2.C1231b;
import p101m1.C1458b;
import p186x2.C2073a;
import p192y1.C2103g;
import p192y1.C2104h;
import p192y1.C2105i;
import p193y2.C2106a;
import p199z1.InterfaceC2149c;

/* loaded from: classes.dex */
public class WebSocketService extends Service {

    /* renamed from: m */
    public static final /* synthetic */ int f653m = 0;

    /* renamed from: e */
    public C0512a f654e = new C0512a();

    /* renamed from: f */
    public BinderC0596a f655f = new BinderC0596a(null);

    /* renamed from: g */
    public String f656g = "0";

    /* renamed from: h */
    public String f657h = DiskLruCache.VERSION_1;

    /* renamed from: i */
    public String f658i = "other";

    /* renamed from: j */
    public boolean f659j = false;

    /* renamed from: k */
    public C2103g f660k = C2103g.f6234e;

    /* renamed from: l */
    public InterfaceC0868a f661l = new C2104h(this);

    /* renamed from: com.cctv.tv.module.service.WebSocketService$a */
    public class BinderC0596a extends Binder implements InterfaceC2149c {
        public BinderC0596a(C2105i c2105i) {
        }

        @Override // p199z1.InterfaceC2149c
        public WebSocketService getService() {
            return WebSocketService.this;
        }
    }

    /* renamed from: a */
    public void m460a() {
        if (this.f659j) {
            ArrayList arrayList = new ArrayList();
            WebSocketMsgInfo webSocketMsgInfo = new WebSocketMsgInfo();
            WebSocketMsgInfo.C0597a c0597a = new WebSocketMsgInfo.C0597a();
            Objects.requireNonNull(C1231b.m1454a());
            String str = "";
            c0597a.setImei("");
            C2106a.m2548a();
            c0597a.setAndroid_id("");
            if (Build.VERSION.SDK_INT < 30) {
                try {
                    if (TextUtils.isEmpty("")) {
                        str = Build.SERIAL;
                    }
                } catch (Exception unused) {
                }
                try {
                    c0597a.setSerial(str);
                } catch (Exception e7) {
                    StringBuilder sbM112a = C0413b.m112a("sendDeviceIdMessage setSerial = ");
                    sbM112a.append(e7.getMessage());
                    Log.m651e("XLog_APP ", sbM112a.toString());
                }
            }
            webSocketMsgInfo.setDeviceId(c0597a);
            webSocketMsgInfo.setTotal(arrayList);
            String jSONString = JSON.toJSONString(webSocketMsgInfo);
            C1458b.m1642a("msg = ", jSONString);
            C0512a c0512a = this.f654e;
            if (c0512a != null) {
                c0512a.m320b(jSONString);
            }
        }
    }

    /* renamed from: b */
    public void m461b() {
        C2073a.m2459d("WebSocket startWebSocket");
        C0512a c0512a = this.f654e;
        if (c0512a != null) {
            c0512a.m319a();
            Request requestBuild = new Request.Builder().url(c0512a.f385a).build();
            OkHttpClient.Builder builderNewBuilder = new OkHttpClient().newBuilder();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            OkHttpClient.Builder builderWriteTimeout = builderNewBuilder.connectTimeout(60L, timeUnit).readTimeout(60L, timeUnit).writeTimeout(60L, timeUnit);
            if (TextUtils.isEmpty(null)) {
                builderWriteTimeout.pingInterval(c0512a.f386b, TimeUnit.MILLISECONDS);
            }
            c0512a.f389e = builderWriteTimeout.build();
            C0869b c0869b = new C0869b(c0512a.f388d);
            c0512a.f391g = c0869b;
            c0869b.f1296b = c0512a.f390f;
            c0512a.f387c = c0512a.f389e.newWebSocket(requestBuild, c0869b);
            C2073a.m2459d("WebSocket connect");
        }
    }

    /* renamed from: c */
    public void m462c() {
        C2073a.m2459d("stopWebSocket");
        Log.m655i("XLog_APP ", "WebSocketService stopWebSocket");
        C0512a c0512a = this.f654e;
        if (c0512a != null) {
            c0512a.m319a();
        }
        this.f659j = false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f655f;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        startForeground(1003, C1196k.m1420a());
        C2073a.m2459d("WebSocket onCreate");
        Log.m655i("XLog_APP ", "WebSocketService WebSocket onCreate");
        C0512a c0512a = this.f654e;
        c0512a.f386b = 240000;
        c0512a.f385a = "ws://39.107.15.222:1000/ws";
        c0512a.f388d.f1712a = this.f660k;
        c0512a.f390f = this.f661l;
        m461b();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        C2073a.m2459d("WebSocket onDestroy");
        Log.m655i("XLog_APP ", "WebSocketService onDestroy");
        m462c();
        stopForeground(true);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        C2073a.m2459d("WebSocket onStartCommand");
        m461b();
        return super.onStartCommand(intent, i7, i8);
    }
}
