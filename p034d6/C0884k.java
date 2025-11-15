package p034d6;

import android.support.constraint.motion.C0079a;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.chrono.GregorianChronology;
import p016b6.AbstractC0475f;
import p034d6.AbstractC0874a;
import p159t3.AbstractC1904c;

/* compiled from: GregorianChronology.java */
/* renamed from: d6.k */
/* loaded from: classes.dex */
public final class C0884k extends AbstractC0878e {
    private static final long serialVersionUID = -861407383323710522L;

    /* renamed from: o0 */
    public static final ConcurrentHashMap<AbstractC0475f, GregorianChronology[]> f1468o0 = new ConcurrentHashMap<>();

    /* renamed from: n0 */
    public static final C0884k f1467n0 = m760t0(AbstractC0475f.f314f, 4);

    public C0884k(AbstractC1904c abstractC1904c, Object obj, int i7) {
        super(abstractC1904c, null, i7);
    }

    private Object readResolve() {
        AbstractC1904c abstractC1904c = this.f1362e;
        int i7 = this.f1437R;
        if (i7 == 0) {
            i7 = 4;
        }
        return abstractC1904c == null ? m760t0(AbstractC0475f.f314f, i7) : m760t0(abstractC1904c.mo230n(), i7);
    }

    /* renamed from: t0 */
    public static C0884k m760t0(AbstractC0475f abstractC0475f, int i7) throws ClassNotFoundException {
        if (abstractC0475f == null) {
            abstractC0475f = AbstractC0475f.m235g();
        }
        ConcurrentHashMap<AbstractC0475f, GregorianChronology[]> concurrentHashMap = f1468o0;
        C0884k[] c0884kArr = concurrentHashMap.get(abstractC0475f);
        if (c0884kArr == null) {
            c0884kArr = new C0884k[7];
            C0884k[] c0884kArr2 = (C0884k[]) concurrentHashMap.putIfAbsent(abstractC0475f, c0884kArr);
            if (c0884kArr2 != null) {
                c0884kArr = c0884kArr2;
            }
        }
        int i8 = i7 - 1;
        try {
            C0884k c0884k = c0884kArr[i8];
            if (c0884k == null) {
                synchronized (c0884kArr) {
                    c0884k = c0884kArr[i8];
                    if (c0884k == null) {
                        AbstractC0475f abstractC0475f2 = AbstractC0475f.f314f;
                        C0884k c0884k2 = abstractC0475f == abstractC0475f2 ? new C0884k(null, null, i7) : new C0884k(C0887n.m763U(m760t0(abstractC0475f2, i7), abstractC0475f), null, i7);
                        c0884kArr[i8] = c0884k2;
                        c0884k = c0884k2;
                    }
                }
            }
            return c0884k;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException(C0079a.m93a("Invalid min days in first week: ", i7));
        }
    }

    @Override // p159t3.AbstractC1904c
    /* renamed from: K */
    public AbstractC1904c mo228K() {
        return f1467n0;
    }

    @Override // p159t3.AbstractC1904c
    /* renamed from: L */
    public AbstractC1904c mo229L(AbstractC0475f abstractC0475f) throws ClassNotFoundException {
        if (abstractC0475f == null) {
            abstractC0475f = AbstractC0475f.m235g();
        }
        return abstractC0475f == mo230n() ? this : m760t0(abstractC0475f, 4);
    }

    @Override // p034d6.AbstractC0876c, p034d6.AbstractC0874a
    /* renamed from: Q */
    public void mo694Q(AbstractC0874a.a aVar) {
        if (this.f1362e == null) {
            super.mo694Q(aVar);
        }
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: S */
    public long mo724S(int i7) {
        int i8;
        int i9 = i7 / 100;
        if (i7 < 0) {
            i8 = ((((i7 + 3) >> 2) - i9) + ((i9 + 3) >> 2)) - 1;
        } else {
            i8 = ((i7 >> 2) - i9) + (i9 >> 2);
            if (mo749r0(i7)) {
                i8--;
            }
        }
        return ((i7 * 365) + (i8 - 719527)) * 86400000;
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: T */
    public long mo725T() {
        return 31083597720000L;
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: U */
    public long mo726U() {
        return 2629746000L;
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: V */
    public long mo727V() {
        return 31556952000L;
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: W */
    public long mo728W() {
        return 15778476000L;
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: c0 */
    public int mo734c0() {
        return 292278993;
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: e0 */
    public int mo736e0() {
        return -292275054;
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: r0 */
    public boolean mo749r0(int i7) {
        return (i7 & 3) == 0 && (i7 % 100 != 0 || i7 % 400 == 0);
    }
}
