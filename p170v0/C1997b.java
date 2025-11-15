package p170v0;

import org.json.JSONException;
import org.json.JSONObject;
import p043f.C0988e;
import p163u0.C1970a;
import p170v0.C2000e;
import p198z0.C2145a;
import p198z0.C2146b;

/* compiled from: ReportAppExceptionEvent.java */
/* renamed from: v0.b */
/* loaded from: classes.dex */
public class C1997b {

    /* renamed from: a */
    public a f5825a;

    /* compiled from: ReportAppExceptionEvent.java */
    /* renamed from: v0.b$a */
    public static class a extends C1996a {
        /* renamed from: b */
        public JSONObject m2331b() {
            throw null;
        }
    }

    /* renamed from: a */
    public String m2330a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", "app_error_d1");
            jSONObject.put("value", this.f5825a.m2331b());
        } catch (JSONException e7) {
            C2000e.a aVar = new C2000e.a();
            aVar.f5839a = "S10001";
            aVar.f5840b = "SDK容错异常";
            aVar.f5841c = e7.getMessage();
            aVar.f5842d = C2145a.m2576d(e7);
            aVar.f5843e = C2145a.m2574b();
            aVar.f5844f = C2145a.m2580h(C1970a.f5748f);
            C2146b.m2589c(C1970a.f5748f);
            C2146b.m2588b(C1970a.f5748f);
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("key", "sdk_error_d1");
                jSONObject2.put("value", aVar.m2336b());
            } catch (JSONException e8) {
                C0988e.m977c(new C2000e(e8));
                if (C1970a.f5743a) {
                    e8.printStackTrace();
                }
            }
            C0988e.m976b("sdk_exception_cache", "sdkExceptionEvent.txt", jSONObject2.toString());
            if (C1970a.f5743a) {
                e7.printStackTrace();
            }
        }
        return jSONObject.toString();
    }
}
