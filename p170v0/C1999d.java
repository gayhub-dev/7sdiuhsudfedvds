package p170v0;

import org.json.JSONException;
import org.json.JSONObject;
import p043f.C0988e;
import p163u0.C1970a;
import p170v0.C2000e;
import p198z0.C2145a;
import p198z0.C2146b;

/* compiled from: ReportPageEvent.java */
/* renamed from: v0.d */
/* loaded from: classes.dex */
public class C1999d {

    /* renamed from: a */
    public a f5831a;

    /* compiled from: ReportPageEvent.java */
    /* renamed from: v0.d$a */
    public static class a extends C1996a {

        /* renamed from: a */
        public String f5832a;

        /* renamed from: b */
        public String f5833b;

        /* renamed from: c */
        public String f5834c;

        /* renamed from: d */
        public String f5835d;

        /* renamed from: e */
        public String f5836e;

        /* renamed from: f */
        public String f5837f;

        /* renamed from: b */
        public JSONObject m2334b() throws Throwable {
            JSONObject jSONObject = new JSONObject();
            try {
                m2329a(jSONObject);
                String str = this.f5832a;
                String str2 = "";
                if (str == null) {
                    str = "";
                }
                jSONObject.put("start_time", C2145a.m2586n(str, 13));
                String str3 = this.f5833b;
                if (str3 == null) {
                    str3 = "";
                }
                jSONObject.put("end_time", C2145a.m2586n(str3, 13));
                String str4 = this.f5834c;
                if (str4 == null) {
                    str4 = "";
                }
                jSONObject.put("duration", C2145a.m2586n(str4, 13));
                String str5 = this.f5835d;
                if (str5 == null) {
                    str5 = "";
                }
                jSONObject.put("page_name", C2145a.m2586n(str5, 256));
                String str6 = this.f5836e;
                if (str6 == null) {
                    str6 = "";
                }
                jSONObject.put("session_id", C2145a.m2586n(str6, 64));
                String str7 = this.f5837f;
                if (str7 != null) {
                    str2 = str7;
                }
                jSONObject.put("network_type", C2145a.m2586n(str2, 10));
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
            return jSONObject;
        }
    }

    /* renamed from: a */
    public String m2333a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", "page_d1");
            jSONObject.put("value", this.f5831a.m2334b());
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
