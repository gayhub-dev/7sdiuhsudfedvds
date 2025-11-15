package p184x0;

import java.util.HashMap;
import java.util.Objects;
import p156t0.C1896a;

/* compiled from: SensorsDataPrivate.java */
/* renamed from: x0.c */
/* loaded from: classes.dex */
public final class RunnableC2064c implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        if (C2065d.f6154d && C2065d.f6155e) {
            C2065d.f6154d = false;
            Objects.requireNonNull(C2062a.m2448a());
            HashMap map = new HashMap();
            map.put("state", "2");
            C1896a.m2197a().m2198b("app_state", "APP状态", map);
        }
    }
}
