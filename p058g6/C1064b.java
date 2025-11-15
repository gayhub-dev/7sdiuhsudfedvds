package p058g6;

import android.support.v7.widget.RecyclerView;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import p016b6.AbstractC0475f;
import p016b6.C0470a;
import p016b6.C0473d;
import p016b6.C0483n;
import p016b6.InterfaceC0491v;
import p016b6.InterfaceC0493x;
import p034d6.C0885l;
import p159t3.AbstractC1904c;

/* compiled from: DateTimeFormatter.java */
/* renamed from: g6.b */
/* loaded from: classes.dex */
public class C1064b {

    /* renamed from: a */
    public final InterfaceC1074l f2011a;

    /* renamed from: b */
    public final InterfaceC1072j f2012b;

    /* renamed from: c */
    public final Locale f2013c;

    /* renamed from: d */
    public final boolean f2014d;

    /* renamed from: e */
    public final AbstractC1904c f2015e;

    /* renamed from: f */
    public final AbstractC0475f f2016f;

    /* renamed from: g */
    public final Integer f2017g;

    /* renamed from: h */
    public final int f2018h;

    public C1064b(InterfaceC1074l interfaceC1074l, InterfaceC1072j interfaceC1072j) {
        this.f2011a = interfaceC1074l;
        this.f2012b = interfaceC1072j;
        this.f2013c = null;
        this.f2014d = false;
        this.f2015e = null;
        this.f2016f = null;
        this.f2017g = null;
        this.f2018h = RecyclerView.MAX_SCROLL_DURATION;
    }

    /* renamed from: a */
    public InterfaceC1066d m1058a() {
        return C1073k.m1147c(this.f2012b);
    }

    /* renamed from: b */
    public C0470a m1059b(String str) {
        AbstractC1904c abstractC1904cM225a;
        Integer num;
        InterfaceC1072j interfaceC1072jM1063f = m1063f();
        AbstractC1904c abstractC1904cM1065h = m1065h(null);
        C1067e c1067e = new C1067e(0L, abstractC1904cM1065h, this.f2013c, this.f2017g, this.f2018h);
        int iMo1097f = interfaceC1072jM1063f.mo1097f(c1067e, str, 0);
        if (iMo1097f < 0) {
            iMo1097f = ~iMo1097f;
        } else if (iMo1097f >= str.length()) {
            long jM1102b = c1067e.m1102b(true, str);
            if (!this.f2014d || (num = c1067e.f2060f) == null) {
                AbstractC0475f abstractC0475f = c1067e.f2059e;
                if (abstractC0475f != null) {
                    abstractC1904cM1065h = abstractC1904cM1065h.mo229L(abstractC0475f);
                }
            } else {
                abstractC1904cM1065h = abstractC1904cM1065h.mo229L(AbstractC0475f.m233e(num.intValue()));
            }
            C0470a c0470a = new C0470a(jM1102b, abstractC1904cM1065h);
            AbstractC0475f abstractC0475f2 = this.f2016f;
            return (abstractC0475f2 == null || (abstractC1904cM225a = C0473d.m225a(c0470a.f398f.mo229L(abstractC0475f2))) == c0470a.f398f) ? c0470a : new C0470a(c0470a.f397e, abstractC1904cM225a);
        }
        throw new IllegalArgumentException(C1070h.m1113d(str, iMo1097f));
    }

    /* renamed from: c */
    public C0483n m1060c(String str) {
        InterfaceC1072j interfaceC1072jM1063f = m1063f();
        AbstractC1904c abstractC1904cMo228K = m1065h(null).mo228K();
        C1067e c1067e = new C1067e(0L, abstractC1904cMo228K, this.f2013c, this.f2017g, this.f2018h);
        int iMo1097f = interfaceC1072jM1063f.mo1097f(c1067e, str, 0);
        if (iMo1097f < 0) {
            iMo1097f = ~iMo1097f;
        } else if (iMo1097f >= str.length()) {
            long jM1102b = c1067e.m1102b(true, str);
            Integer num = c1067e.f2060f;
            if (num != null) {
                abstractC1904cMo228K = abstractC1904cMo228K.mo229L(AbstractC0475f.m233e(num.intValue()));
            } else {
                AbstractC0475f abstractC0475f = c1067e.f2059e;
                if (abstractC0475f != null) {
                    abstractC1904cMo228K = abstractC1904cMo228K.mo229L(abstractC0475f);
                }
            }
            return new C0483n(jM1102b, abstractC1904cMo228K);
        }
        throw new IllegalArgumentException(C1070h.m1113d(str, iMo1097f));
    }

    /* renamed from: d */
    public String m1061d(InterfaceC0491v interfaceC0491v) {
        StringBuilder sb = new StringBuilder(m1064g().mo1095c());
        try {
            AtomicReference<Map<String, AbstractC0475f>> atomicReference = C0473d.f313a;
            long jMo261g = interfaceC0491v.mo261g();
            AbstractC1904c abstractC1904cMo262j = interfaceC0491v.mo262j();
            if (abstractC1904cMo262j == null) {
                abstractC1904cMo262j = C0885l.m761S();
            }
            InterfaceC1074l interfaceC1074lM1064g = m1064g();
            AbstractC1904c abstractC1904cM1065h = m1065h(abstractC1904cMo262j);
            AbstractC0475f abstractC0475fMo230n = abstractC1904cM1065h.mo230n();
            int iMo245k = abstractC0475fMo230n.mo245k(jMo261g);
            long j7 = iMo245k;
            long j8 = jMo261g + j7;
            if ((jMo261g ^ j8) < 0 && (j7 ^ jMo261g) >= 0) {
                abstractC0475fMo230n = AbstractC0475f.f314f;
                iMo245k = 0;
                j8 = jMo261g;
            }
            interfaceC1074lM1064g.mo1094b(sb, j8, abstractC1904cM1065h.mo228K(), iMo245k, abstractC0475fMo230n, this.f2013c);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    /* renamed from: e */
    public String m1062e(InterfaceC0493x interfaceC0493x) {
        InterfaceC1074l interfaceC1074lM1064g;
        StringBuilder sb = new StringBuilder(m1064g().mo1095c());
        try {
            interfaceC1074lM1064g = m1064g();
        } catch (IOException unused) {
        }
        if (interfaceC0493x == null) {
            throw new IllegalArgumentException("The partial must not be null");
        }
        interfaceC1074lM1064g.mo1096e(sb, interfaceC0493x, this.f2013c);
        return sb.toString();
    }

    /* renamed from: f */
    public final InterfaceC1072j m1063f() {
        InterfaceC1072j interfaceC1072j = this.f2012b;
        if (interfaceC1072j != null) {
            return interfaceC1072j;
        }
        throw new UnsupportedOperationException("Parsing not supported");
    }

    /* renamed from: g */
    public final InterfaceC1074l m1064g() {
        InterfaceC1074l interfaceC1074l = this.f2011a;
        if (interfaceC1074l != null) {
            return interfaceC1074l;
        }
        throw new UnsupportedOperationException("Printing not supported");
    }

    /* renamed from: h */
    public final AbstractC1904c m1065h(AbstractC1904c abstractC1904c) {
        AbstractC1904c abstractC1904cM225a = C0473d.m225a(abstractC1904c);
        AbstractC1904c abstractC1904c2 = this.f2015e;
        if (abstractC1904c2 != null) {
            abstractC1904cM225a = abstractC1904c2;
        }
        AbstractC0475f abstractC0475f = this.f2016f;
        return abstractC0475f != null ? abstractC1904cM225a.mo229L(abstractC0475f) : abstractC1904cM225a;
    }

    /* renamed from: i */
    public C1064b m1066i(AbstractC0475f abstractC0475f) {
        return this.f2016f == abstractC0475f ? this : new C1064b(this.f2011a, this.f2012b, this.f2013c, false, this.f2015e, abstractC0475f, this.f2017g, this.f2018h);
    }

    /* renamed from: j */
    public C1064b m1067j() {
        return m1066i(AbstractC0475f.f314f);
    }

    public C1064b(InterfaceC1074l interfaceC1074l, InterfaceC1072j interfaceC1072j, Locale locale, boolean z6, AbstractC1904c abstractC1904c, AbstractC0475f abstractC0475f, Integer num, int i7) {
        this.f2011a = interfaceC1074l;
        this.f2012b = interfaceC1072j;
        this.f2013c = locale;
        this.f2014d = z6;
        this.f2015e = abstractC1904c;
        this.f2016f = abstractC0475f;
        this.f2017g = num;
        this.f2018h = i7;
    }
}
