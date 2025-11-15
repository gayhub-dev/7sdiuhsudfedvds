package p117o1;

import android.text.TextUtils;
import com.cctv.p025tv.module.collect.report.event.ReportCustomEvent;
import java.util.Map;
import java.util.Random;
import p043f.C0988e;
import p127p1.C1735a;
import p135q1.C1760c;

/* compiled from: CCTVReport.java */
/* renamed from: o1.a */
/* loaded from: classes.dex */
public class C1579a {

    /* renamed from: a */
    public static C1579a f4740a;

    /* renamed from: a */
    public static C1579a m1830a() {
        if (f4740a == null) {
            synchronized (C1579a.class) {
                if (f4740a == null) {
                    f4740a = new C1579a();
                }
            }
        }
        return f4740a;
    }

    /* renamed from: b */
    public void m1831b(String str, String str2, Map<String, String> map) {
        if (map != null) {
            try {
                if (map.containsKey("play_duration")) {
                    String str3 = map.get("play_duration");
                    if (!TextUtils.isEmpty(str3)) {
                        long jNextFloat = Long.parseLong(str3);
                        if (jNextFloat > 10800000) {
                            jNextFloat = (long) (((new Random().nextFloat() * 1.5f) + 1.5f) * 60.0f * 60.0f * 1000.0f);
                        }
                        map.put("play_duration", String.valueOf(jNextFloat));
                    }
                }
            } catch (Exception e7) {
                e7.printStackTrace();
            }
        }
        ReportCustomEvent reportCustomEvent = new ReportCustomEvent();
        reportCustomEvent.setCctv_id(C0988e.m987m(C0988e.f1828f));
        reportCustomEvent.setDevice_id(C0988e.m992r(C0988e.f1828f));
        reportCustomEvent.setNetwork_type(C1735a.m1881e(C0988e.f1828f));
        reportCustomEvent.setEvent_time(C1735a.m1878b());
        reportCustomEvent.setEvent_id(str);
        reportCustomEvent.setEvent_name(str2);
        reportCustomEvent.setMap(map);
        C1760c.m1927a(reportCustomEvent.toJson());
    }
}
