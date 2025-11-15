package p191y0;

import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.cache.DiskLruCache;
import org.json.JSONException;
import org.json.JSONObject;
import p011b1.C0436a;
import p043f.C0988e;
import p163u0.C1970a;
import p170v0.C2000e;
import p177w0.C2023c;
import p198z0.C2145a;
import p198z0.C2146b;

/* compiled from: CCTVAgentMonitor.java */
/* renamed from: y0.a */
/* loaded from: classes.dex */
public class C2095a {

    /* renamed from: a */
    public static int f6217a;

    /* renamed from: b */
    public static ExecutorService f6218b;

    /* renamed from: c */
    public static HashMap<String, Integer> f6219c;

    /* compiled from: CCTVAgentMonitor.java */
    /* renamed from: y0.a$a */
    public static class a implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ Object f6220e;

        public a(Object obj) {
            this.f6220e = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0260 A[Catch: Exception -> 0x028f, TryCatch #2 {Exception -> 0x028f, blocks: (B:69:0x0238, B:71:0x0260, B:72:0x027c, B:74:0x0282, B:66:0x022e, B:68:0x0235, B:83:0x02a5, B:86:0x02bf), top: B:112:0x00ca }] */
        /* JADX WARN: Removed duplicated region for block: B:72:0x027c A[Catch: Exception -> 0x028f, TryCatch #2 {Exception -> 0x028f, blocks: (B:69:0x0238, B:71:0x0260, B:72:0x027c, B:74:0x0282, B:66:0x022e, B:68:0x0235, B:83:0x02a5, B:86:0x02bf), top: B:112:0x00ca }] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 888
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p191y0.C2095a.a.run():void");
        }
    }

    /* compiled from: CCTVAgentMonitor.java */
    /* renamed from: y0.a$b */
    public static class b implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ String f6221e;

        /* renamed from: f */
        public final /* synthetic */ String f6222f;

        /* renamed from: g */
        public final /* synthetic */ String f6223g;

        /* renamed from: h */
        public final /* synthetic */ String f6224h;

        public b(String str, String str2, String str3, String str4) {
            this.f6221e = str;
            this.f6222f = str2;
            this.f6223g = str3;
            this.f6224h = str4;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            try {
                if (!new File(this.f6221e).exists()) {
                    String strM999y = C0988e.m999y(this.f6222f, this.f6223g);
                    if (!strM999y.contains(this.f6221e + ",")) {
                        if (strM999y.contains(this.f6221e)) {
                            C0988e.m974R(C0988e.m990p(this.f6222f, this.f6223g), strM999y.replace(this.f6221e, ""));
                            return;
                        }
                        return;
                    } else {
                        C0988e.m974R(C0988e.m990p(this.f6222f, this.f6223g), strM999y.replace(this.f6221e + ",", ""));
                        return;
                    }
                }
                String strM965I = C0988e.m965I(this.f6221e);
                if (TextUtils.isEmpty(strM965I)) {
                    String strM999y2 = C0988e.m999y(this.f6222f, this.f6223g);
                    if (!strM999y2.contains(this.f6221e + ",")) {
                        if (strM999y2.contains(this.f6221e)) {
                            C0988e.m974R(C0988e.m990p(this.f6222f, this.f6223g), strM999y2.replace(this.f6221e, ""));
                            return;
                        }
                        return;
                    } else {
                        C0988e.m974R(C0988e.m990p(this.f6222f, this.f6223g), strM999y2.replace(this.f6221e + ",", ""));
                        return;
                    }
                }
                String strM2372c = C2023c.m2372c(this.f6224h, strM965I);
                if (TextUtils.isEmpty(strM2372c)) {
                    if ("无网络".equals(C2145a.m2580h(C1970a.f5748f))) {
                        return;
                    }
                    C2095a.m2542a(this.f6221e, this.f6222f, this.f6223g);
                    return;
                }
                if (DiskLruCache.VERSION_1.equals(new JSONObject(strM2372c).optString("succeed"))) {
                    new File(this.f6221e).delete();
                    String strM999y3 = C0988e.m999y(this.f6222f, this.f6223g);
                    if (strM999y3.contains(this.f6221e + ",")) {
                        C0988e.m974R(C0988e.m990p(this.f6222f, this.f6223g), strM999y3.replace(this.f6221e + ",", ""));
                    } else if (strM999y3.contains(this.f6221e)) {
                        C0988e.m974R(C0988e.m990p(this.f6222f, this.f6223g), strM999y3.replace(this.f6221e, ""));
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
                new File(this.f6221e).delete();
                String strM999y4 = C0988e.m999y(this.f6222f, this.f6223g);
                if (!strM999y4.contains(this.f6221e + ",")) {
                    if (strM999y4.contains(this.f6221e)) {
                        C0988e.m974R(C0988e.m990p(this.f6222f, this.f6223g), strM999y4.replace(this.f6221e, ""));
                    }
                } else {
                    C0988e.m974R(C0988e.m990p(this.f6222f, this.f6223g), strM999y4.replace(this.f6221e + ",", ""));
                }
            } catch (Exception unused) {
            }
        }
    }

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        new SynchronousQueue();
        C0436a c0436a = new C0436a();
        c0436a.f267d = 3;
        f6218b = c0436a.m149a();
        f6219c = new HashMap<>();
    }

    /* renamed from: a */
    public static void m2542a(String str, String str2, String str3) throws Throwable {
        if (!f6219c.containsKey(str)) {
            f6219c.put(str, 1);
            return;
        }
        Integer num = f6219c.get(str);
        if (num.intValue() < 2) {
            f6219c.put(str, Integer.valueOf(num.intValue() + 1));
            return;
        }
        new File(str).delete();
        String strM999y = C0988e.m999y(str2, str3);
        if (!strM999y.contains(str + ",")) {
            if (strM999y.contains(str)) {
                C0988e.m974R(C0988e.m990p(str2, str3), strM999y.replace(str, ""));
            }
        } else {
            C0988e.m974R(C0988e.m990p(str2, str3), strM999y.replace(str + ",", ""));
        }
    }

    /* renamed from: b */
    public static void m2543b(Object obj) {
        f6218b.execute(new a(obj));
        String strM999y = C0988e.m999y("custom_cache", "exposureContribute.txt");
        if (TextUtils.isEmpty(strM999y)) {
            return;
        }
        String[] strArrSplit = strM999y.split(",");
        if (strArrSplit.length > 0) {
            for (String str : strArrSplit) {
                f6218b.execute(new RunnableC2096b(str, "custom_cache", "exposureContribute.txt", "https://collect.cctv.cn/cctvmobileinf/rest/cctv/receive/new/app"));
            }
        }
    }

    /* renamed from: c */
    public static void m2544c() {
        f6217a = 0;
        m2545d("start_cache", "startEvent.txt", "https://collect.cctv.cn/cctvmobileinf/rest/cctv/receive/new/app");
        m2545d("page_cache", "pageEvent.txt", "https://collect.cctv.cn/cctvmobileinf/rest/cctv/receive/new/app");
        m2545d("custom_cache", "customEvent.txt", "https://collect.cctv.cn/cctvmobileinf/rest/cctv/receive/new/app");
        m2545d("app_exception_cache", "appExceptionEvent.txt", "https://collect.cctv.cn/cctvmobileinf/rest/cctv/receive/new/app");
        m2545d("sdk_exception_cache", "sdkExceptionEvent.txt", "https://collect.cctv.cn/cctvmobileinf/rest/cctv/receive/new/app");
    }

    /* renamed from: d */
    public static void m2545d(String str, String str2, String str3) {
        if (f6217a > 15) {
            return;
        }
        String strM999y = C0988e.m999y(str, str2);
        if (TextUtils.isEmpty(strM999y)) {
            return;
        }
        String[] strArrSplit = strM999y.split(",");
        if (strArrSplit.length > 0) {
            for (String str4 : strArrSplit) {
                f6217a++;
                f6218b.execute(new b(str4, str, str2, str3));
            }
        }
    }
}
