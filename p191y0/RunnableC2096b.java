package p191y0;

import android.text.TextUtils;
import java.io.File;
import okhttp3.internal.cache.DiskLruCache;
import org.json.JSONException;
import org.json.JSONObject;
import p043f.C0988e;
import p163u0.C1970a;
import p170v0.C2000e;
import p177w0.C2023c;
import p198z0.C2145a;
import p198z0.C2146b;

/* compiled from: CCTVAgentMonitor.java */
/* renamed from: y0.b */
/* loaded from: classes.dex */
public final class RunnableC2096b implements Runnable {

    /* renamed from: e */
    public final /* synthetic */ String f6225e;

    /* renamed from: f */
    public final /* synthetic */ String f6226f;

    /* renamed from: g */
    public final /* synthetic */ String f6227g;

    /* renamed from: h */
    public final /* synthetic */ String f6228h;

    public RunnableC2096b(String str, String str2, String str3, String str4) {
        this.f6225e = str;
        this.f6226f = str2;
        this.f6227g = str3;
        this.f6228h = str4;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (!new File(this.f6225e).exists()) {
                String strM999y = C0988e.m999y(this.f6226f, this.f6227g);
                if (!strM999y.contains(this.f6225e + ",")) {
                    if (strM999y.contains(this.f6225e)) {
                        C0988e.m974R(C0988e.m990p(this.f6226f, this.f6227g), strM999y.replace(this.f6225e, ""));
                        return;
                    }
                    return;
                } else {
                    C0988e.m974R(C0988e.m990p(this.f6226f, this.f6227g), strM999y.replace(this.f6225e + ",", ""));
                    return;
                }
            }
            String strM965I = C0988e.m965I(this.f6225e);
            if (TextUtils.isEmpty(strM965I)) {
                String strM999y2 = C0988e.m999y(this.f6226f, this.f6227g);
                if (!strM999y2.contains(this.f6225e + ",")) {
                    if (strM999y2.contains(this.f6225e)) {
                        C0988e.m974R(C0988e.m990p(this.f6226f, this.f6227g), strM999y2.replace(this.f6225e, ""));
                        return;
                    }
                    return;
                } else {
                    C0988e.m974R(C0988e.m990p(this.f6226f, this.f6227g), strM999y2.replace(this.f6225e + ",", ""));
                    return;
                }
            }
            String strM2372c = C2023c.m2372c(this.f6228h, strM965I);
            if (TextUtils.isEmpty(strM2372c)) {
                if ("无网络".equals(C2145a.m2580h(C1970a.f5748f))) {
                    return;
                }
                C2095a.m2542a(this.f6225e, this.f6226f, this.f6227g);
                return;
            }
            if (DiskLruCache.VERSION_1.equals(new JSONObject(strM2372c).optString("succeed"))) {
                new File(this.f6225e).delete();
                String strM999y3 = C0988e.m999y(this.f6226f, this.f6227g);
                if (strM999y3.contains(this.f6225e + ",")) {
                    C0988e.m974R(C0988e.m990p(this.f6226f, this.f6227g), strM999y3.replace(this.f6225e + ",", ""));
                } else if (strM999y3.contains(this.f6225e)) {
                    C0988e.m974R(C0988e.m990p(this.f6226f, this.f6227g), strM999y3.replace(this.f6225e, ""));
                }
                if (!C1970a.f5743a) {
                    C0988e.m982h("CCTVAgent:数据上报成功");
                    return;
                }
                C0988e.m982h("CCTVAgent:" + strM965I);
                return;
            }
            Exception exc = new Exception("接口请求失败" + strM2372c);
            C2000e.a aVar = new C2000e.a();
            aVar.f5840b = "接口请求失败";
            aVar.f5839a = "S10000";
            aVar.f5841c = exc.getMessage();
            aVar.f5842d = C2145a.m2576d(exc);
            aVar.f5843e = C2145a.m2574b();
            aVar.f5844f = C2145a.m2580h(C1970a.f5748f);
            C2146b.m2589c(C1970a.f5748f);
            C2146b.m2588b(C1970a.f5748f);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("key", "sdk_error_d1");
                jSONObject.put("value", aVar.m2336b());
            } catch (JSONException e7) {
                C0988e.m977c(new C2000e(e7));
                if (C1970a.f5743a) {
                    e7.printStackTrace();
                }
            }
            C0988e.m976b("sdk_exception_cache", "sdkExceptionEvent.txt", jSONObject.toString());
            new File(this.f6225e).delete();
            String strM999y4 = C0988e.m999y(this.f6226f, this.f6227g);
            if (!strM999y4.contains(this.f6225e + ",")) {
                if (strM999y4.contains(this.f6225e)) {
                    C0988e.m974R(C0988e.m990p(this.f6226f, this.f6227g), strM999y4.replace(this.f6225e, ""));
                }
            } else {
                C0988e.m974R(C0988e.m990p(this.f6226f, this.f6227g), strM999y4.replace(this.f6225e + ",", ""));
            }
        } catch (Exception unused) {
        }
    }
}
