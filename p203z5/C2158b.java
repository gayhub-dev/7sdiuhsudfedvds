package p203z5;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;
import p016b6.C0486q;
import p016b6.C0488s;
import p016b6.InterfaceC0494y;
import p058g6.C1070h;
import p058g6.InterfaceC1076n;
import p058g6.InterfaceC1077o;

/* compiled from: SampleStatistic.java */
/* renamed from: z5.b */
/* loaded from: classes.dex */
public class C2158b {

    /* renamed from: a */
    public final Object f6334a;

    /* renamed from: b */
    public final Object f6335b;

    /* renamed from: c */
    public final Object f6336c;

    /* renamed from: d */
    public final Object f6337d;

    public C2158b() {
        this.f6334a = new AtomicLong();
        this.f6335b = new AtomicLong();
        this.f6336c = new AtomicLong();
        this.f6337d = new AtomicLong();
    }

    /* renamed from: a */
    public void m2599a() {
        if (((InterfaceC1076n) this.f6335b) == null) {
            throw new UnsupportedOperationException("Parsing not supported");
        }
    }

    /* renamed from: b */
    public void m2600b(InterfaceC0494y interfaceC0494y) {
        if (interfaceC0494y == null) {
            throw new IllegalArgumentException("Period must not be null");
        }
    }

    /* renamed from: c */
    public void m2601c() {
        if (((InterfaceC1077o) this.f6334a) == null) {
            throw new UnsupportedOperationException("Printing not supported");
        }
    }

    /* renamed from: d */
    public C0486q m2602d(String str) {
        m2599a();
        C0486q c0486q = new C0486q(0L, (C0488s) this.f6337d);
        int iMo1156c = ((InterfaceC1076n) this.f6335b).mo1156c(c0486q, str, 0, (Locale) this.f6336c);
        if (iMo1156c < 0) {
            iMo1156c = ~iMo1156c;
        } else if (iMo1156c >= str.length()) {
            return c0486q;
        }
        throw new IllegalArgumentException(C1070h.m1113d(str, iMo1156c));
    }

    public C2158b(InterfaceC1077o interfaceC1077o, InterfaceC1076n interfaceC1076n) {
        this.f6334a = interfaceC1077o;
        this.f6335b = interfaceC1076n;
        this.f6336c = null;
        this.f6337d = null;
    }

    public C2158b(InterfaceC1077o interfaceC1077o, InterfaceC1076n interfaceC1076n, Locale locale, C0488s c0488s) {
        this.f6334a = interfaceC1077o;
        this.f6335b = interfaceC1076n;
        this.f6336c = locale;
        this.f6337d = c0488s;
    }
}
