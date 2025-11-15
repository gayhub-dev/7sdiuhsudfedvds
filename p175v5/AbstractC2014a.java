package p175v5;

import android.support.constraint.solver.widgets.analyzer.C0096a;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: AbstractLogger.java */
/* renamed from: v5.a */
/* loaded from: classes.dex */
public abstract class AbstractC2014a implements InterfaceC2016c {
    /* renamed from: l */
    public static boolean m2347l(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            if (!Character.isWhitespace(str.charAt(i7))) {
                return false;
            }
        }
        return true;
    }

    @Override // p175v5.InterfaceC2016c
    /* renamed from: b */
    public final InterfaceC2016c mo2348b(String str) {
        if (m2347l(str)) {
            return this;
        }
        C2017d c2017d = (C2017d) this;
        String str2 = c2017d.f5879f;
        if (!m2347l(str2)) {
            C2015b.m2350b();
            if (C2015b.f5867e != this) {
                str = C0096a.m97a(str2, ".", str);
            }
        }
        ConcurrentMap<String, InterfaceC2016c> concurrentMap = C2015b.f5866d;
        InterfaceC2016c interfaceC2016c = (InterfaceC2016c) Collections.unmodifiableMap(concurrentMap).get(str);
        if (interfaceC2016c != null) {
            return interfaceC2016c;
        }
        C2017d c2017d2 = new C2017d(str);
        c2017d2.f5878e = c2017d.f5878e;
        c2017d2.f5877d = c2017d.f5877d;
        c2017d2.f5876c = c2017d.f5876c;
        int i7 = c2017d.f5874a;
        if (i7 != c2017d.f5875b) {
            c2017d2.f5874a = i7;
        }
        InterfaceC2016c interfaceC2016c2 = (InterfaceC2016c) ((ConcurrentHashMap) concurrentMap).putIfAbsent(str, c2017d2);
        return interfaceC2016c2 == null ? c2017d2 : interfaceC2016c2;
    }
}
