package p170v0;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p043f.C0988e;
import p163u0.C1970a;
import p170v0.C2000e;
import p198z0.C2145a;
import p198z0.C2146b;

/* compiled from: ReportCustomEvent.java */
/* renamed from: v0.c */
/* loaded from: classes.dex */
public class C1998c extends C1996a {

    /* renamed from: a */
    public String f5826a;

    /* renamed from: b */
    public String f5827b;

    /* renamed from: c */
    public String f5828c;

    /* renamed from: d */
    public String f5829d;

    /* renamed from: e */
    public Map<String, String> f5830e;

    /* renamed from: b */
    public String m2332b() throws Throwable {
        JSONObject jSONObject = new JSONObject();
        try {
            m2329a(jSONObject);
            String str = this.f5826a;
            if (str == null) {
                str = "";
            }
            jSONObject.put("event_id", C2145a.m2586n(str, 256));
            String str2 = this.f5827b;
            if (str2 == null) {
                str2 = "";
            }
            jSONObject.put("event_name", C2145a.m2586n(str2, 64));
            String str3 = this.f5828c;
            if (str3 == null) {
                str3 = "";
            }
            jSONObject.put("event_time", C2145a.m2586n(str3, 13));
            String str4 = this.f5829d;
            if (str4 == null) {
                str4 = "";
            }
            jSONObject.put("network_type", C2145a.m2586n(str4, 10));
            Map<String, String> map = this.f5830e;
            if (map != null && map.size() > 0) {
                for (String str5 : this.f5830e.keySet()) {
                    try {
                        if (TextUtils.isEmpty(this.f5830e.get(str5))) {
                            jSONObject.put(C2145a.m2586n(str5, 50), "");
                        } else {
                            jSONObject.put(C2145a.m2586n(str5, 50), C2145a.m2586n(this.f5830e.get(str5).replace("\"", "'"), 1024));
                        }
                    } catch (Exception e7) {
                        e7.printStackTrace();
                    }
                }
            }
        } catch (JSONException e8) {
            C2000e.a aVar = new C2000e.a();
            aVar.f5839a = "S10001";
            aVar.f5840b = "SDK容错异常";
            aVar.f5841c = e8.getMessage();
            aVar.f5842d = C2145a.m2576d(e8);
            aVar.f5843e = C2145a.m2574b();
            aVar.f5844f = C2145a.m2580h(C1970a.f5748f);
            C2146b.m2589c(C1970a.f5748f);
            C2146b.m2588b(C1970a.f5748f);
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("key", "sdk_error_d1");
                jSONObject2.put("value", aVar.m2336b());
            } catch (JSONException e9) {
                C0988e.m977c(new C2000e(e9));
                if (C1970a.f5743a) {
                    e9.printStackTrace();
                }
            }
            C0988e.m976b("sdk_exception_cache", "sdkExceptionEvent.txt", jSONObject2.toString());
            if (C1970a.f5743a) {
                e8.printStackTrace();
            }
        }
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("key", NotificationCompat.CATEGORY_EVENT);
            jSONObject3.put("value", jSONObject);
        } catch (JSONException e10) {
            e10.printStackTrace();
        }
        return jSONObject3.toString();
    }
}
