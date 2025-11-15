package p143r1;

import java.util.HashMap;
import java.util.Objects;
import p117o1.C1579a;

/* compiled from: ReportSensorsDataPrivate.java */
/* renamed from: r1.c */
/* loaded from: classes.dex */
public class RunnableC1826c implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        if (C1827d.f5307d && C1827d.f5308e) {
            C1827d.f5307d = false;
            Objects.requireNonNull(C1824a.m2066a());
            HashMap map = new HashMap();
            map.put("state", "2");
            C1579a.m1830a().m1831b("app_state", "APP状态", map);
        }
    }
}
