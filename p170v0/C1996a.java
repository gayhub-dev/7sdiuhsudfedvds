package p170v0;

import android.os.Build;
import org.json.JSONObject;
import p163u0.C1970a;
import p198z0.C2145a;
import p198z0.C2146b;

/* compiled from: CommentEvent.java */
/* renamed from: v0.a */
/* loaded from: classes.dex */
public class C1996a {
    /* renamed from: a */
    public void m2329a(JSONObject jSONObject) {
        jSONObject.put("cctv_id", C2145a.m2586n(C2146b.m2588b(C1970a.f5748f), 64));
        jSONObject.put("device_id", C2145a.m2586n(C2146b.m2589c(C1970a.f5748f), 64));
        jSONObject.put("idfa", "");
        jSONObject.put("idfv", "");
        jSONObject.put("user_id", C2145a.m2586n(C1970a.f5744b, 64));
        jSONObject.put("app_key", C2145a.m2586n(C1970a.f5745c, 64));
        jSONObject.put("imei", C2145a.m2586n("", 64));
        jSONObject.put("android_id", C2145a.m2586n(C2146b.m2587a(C1970a.f5748f), 64));
        jSONObject.put("mac", C2145a.m2586n(C2145a.m2578f(C1970a.f5748f), 64));
        jSONObject.put("device_builder_type", C2145a.m2586n(Build.TYPE, 64));
        jSONObject.put("device_hardware", C2145a.m2586n(Build.HARDWARE, 64));
        jSONObject.put("device_board", C2145a.m2586n(Build.BOARD, 64));
        jSONObject.put("device_brand", C2145a.m2586n(Build.BRAND, 64));
        jSONObject.put("device_params", C2145a.m2586n(Build.DEVICE, 64));
        jSONObject.put("device_display", C2145a.m2586n(Build.DISPLAY, 64));
        jSONObject.put("device_version_id", C2145a.m2586n(Build.ID, 64));
        jSONObject.put("device_host", C2145a.m2586n(Build.HOST, 128));
        jSONObject.put("device_product", C2145a.m2586n(Build.PRODUCT, 64));
        jSONObject.put("device_tags", C2145a.m2586n(Build.TAGS, 64));
        jSONObject.put("device_user", C2145a.m2586n(Build.USER, 30));
        jSONObject.put("device_fingerprint", C2145a.m2586n(Build.FINGERPRINT, 128));
        jSONObject.put("device_manufacturer", C2145a.m2586n(C2145a.m2579g(), 64));
        jSONObject.put("device_model", C2145a.m2586n(C2145a.m2582j(), 50));
        jSONObject.put("device_resolution", C2145a.m2586n(C2145a.m2583k(C1970a.f5748f), 20));
        jSONObject.put("system_type", C2145a.m2586n(C2145a.m2585m(), 24));
        jSONObject.put("device_type", C2145a.m2586n(C2145a.m2575c(), 24));
        jSONObject.put("app_language", C2145a.m2586n(C2145a.m2577e(), 24));
        jSONObject.put("app_version", C2145a.m2586n(C2145a.m2573a(C1970a.f5748f), 30));
        jSONObject.put("sdk_version", C2145a.m2586n(C2145a.m2584l(), 30));
        jSONObject.put("os_version", C2145a.m2586n(C2145a.m2581i(), 20));
        jSONObject.put("app_channel", C2145a.m2586n(C1970a.f5746d, 50));
        jSONObject.put("data_time", C2145a.m2586n(C2145a.m2574b(), 13));
    }
}
