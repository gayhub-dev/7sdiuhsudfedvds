package p016b6;

import android.support.constraint.solver.widgets.analyzer.C0096a;
import p058g6.C1063a;

/* compiled from: IllegalInstantException.java */
/* renamed from: b6.k */
/* loaded from: classes.dex */
public class C0480k extends IllegalArgumentException {
    private static final long serialVersionUID = 2858712538216L;

    public C0480k(String str) {
        super(str);
    }

    public C0480k(long j7, String str) {
        super(C0096a.m97a("Illegal instant due to time zone offset transition (daylight savings time 'gap'): ", C1063a.m1055a("yyyy-MM-dd'T'HH:mm:ss.SSS").m1061d(new C0481l(j7)), str != null ? C0096a.m97a(" (", str, ")") : ""));
    }
}
