package p170v0;

import org.json.JSONException;
import org.json.JSONObject;
import p043f.C0988e;
import p163u0.C1970a;
import p198z0.C2145a;
import p198z0.C2146b;

/* compiled from: ReportSdkExceptionEvent.java */
/* renamed from: v0.e */
/* loaded from: classes.dex */
public class C2000e {

    /* renamed from: a */
    public a f5838a;

    /* compiled from: ReportSdkExceptionEvent.java */
    /* renamed from: v0.e$a */
    public static class a extends C1996a {

        /* renamed from: a */
        public String f5839a;

        /* renamed from: b */
        public String f5840b;

        /* renamed from: c */
        public String f5841c;

        /* renamed from: d */
        public String f5842d;

        /* renamed from: e */
        public String f5843e;

        /* renamed from: f */
        public String f5844f;

        /* renamed from: b */
        public JSONObject m2336b() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            try {
                m2329a(jSONObject);
                String str = this.f5839a;
                String str2 = "";
                if (str == null) {
                    str = "";
                }
                jSONObject.put("error_code", C2145a.m2586n(str, 20));
                String str3 = this.f5840b;
                if (str3 == null) {
                    str3 = "";
                }
                jSONObject.put("error_name", C2145a.m2586n(str3, 50));
                String str4 = this.f5841c;
                if (str4 == null) {
                    str4 = "";
                }
                jSONObject.put("stacktrace_name", C2145a.m2586n(str4, 1024));
                String str5 = this.f5842d;
                if (str5 == null) {
                    str5 = "";
                }
                jSONObject.put("stacktrace", C2145a.m2586n(str5, 4096));
                String str6 = this.f5843e;
                if (str6 == null) {
                    str6 = "";
                }
                jSONObject.put("error_time", C2145a.m2586n(str6, 13));
                String str7 = this.f5844f;
                if (str7 != null) {
                    str2 = str7;
                }
                jSONObject.put("network_type", C2145a.m2586n(str2, 10));
            } catch (JSONException e7) {
                C0988e.m977c(new C2000e(e7));
                if (C1970a.f5743a) {
                    e7.printStackTrace();
                }
            }
            return jSONObject;
        }
    }

    public C2000e(Exception exc) {
        a aVar = new a();
        aVar.f5839a = "S10001";
        aVar.f5840b = "SDK容错异常";
        aVar.f5841c = exc.getMessage();
        aVar.f5842d = C2145a.m2576d(exc);
        aVar.f5843e = C2145a.m2574b();
        aVar.f5844f = C2145a.m2580h(C1970a.f5748f);
        C2146b.m2589c(C1970a.f5748f);
        C2146b.m2588b(C1970a.f5748f);
        this.f5838a = aVar;
    }

    /* renamed from: a */
    public String m2335a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", "sdk_error_d1");
            jSONObject.put("value", this.f5838a.m2336b());
        } catch (JSONException e7) {
            C0988e.m977c(new C2000e(e7));
            if (C1970a.f5743a) {
                e7.printStackTrace();
            }
        }
        return jSONObject.toString();
    }
}
