package p024c6;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import p009b.C0413b;
import p016b6.AbstractC0475f;
import p016b6.AbstractC0478i;
import p016b6.C0473d;
import p016b6.C0486q;
import p016b6.C0488s;
import p016b6.InterfaceC0489t;
import p042e6.C0971d;
import p042e6.InterfaceC0977j;
import p159t3.AbstractC1904c;

/* compiled from: BasePeriod.java */
/* renamed from: c6.h */
/* loaded from: classes.dex */
public abstract class AbstractC0527h extends AbstractC0523d implements Serializable {
    private static final long serialVersionUID = -2110953284060001145L;

    /* renamed from: e */
    public final C0488s f400e;

    /* renamed from: f */
    public final int[] f401f;

    public AbstractC0527h(long j7, C0488s c0488s, AbstractC1904c abstractC1904c) {
        AtomicReference<Map<String, AbstractC0475f>> atomicReference = C0473d.f313a;
        c0488s = c0488s == null ? C0488s.m285b() : c0488s;
        AbstractC1904c abstractC1904cM225a = C0473d.m225a(null);
        this.f400e = c0488s;
        this.f401f = abstractC1904cM225a.mo722k(this, j7);
    }

    /* renamed from: X */
    public void m336X(AbstractC0478i abstractC0478i, int i7) {
        int[] iArr = this.f401f;
        int iM286a = this.f400e.m286a(abstractC0478i);
        if (iM286a != -1) {
            iArr[iM286a] = i7;
        } else {
            if (i7 == 0) {
                return;
            }
            throw new IllegalArgumentException("Period does not support field '" + abstractC0478i + "'");
        }
    }

    @Override // p016b6.InterfaceC0494y
    /* renamed from: k */
    public int mo294k(int i7) {
        return this.f401f[i7];
    }

    @Override // p016b6.InterfaceC0494y
    /* renamed from: q */
    public C0488s mo295q() {
        return this.f400e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractC0527h(Object obj, C0488s c0488s, AbstractC1904c abstractC1904c) {
        if (C0971d.f1797f == null) {
            C0971d.f1797f = new C0971d();
        }
        InterfaceC0977j interfaceC0977j = (InterfaceC0977j) C0971d.f1797f.f1801d.m937b(obj == null ? null : obj.getClass());
        if (interfaceC0977j != null) {
            c0488s = c0488s == null ? interfaceC0977j.mo934c(obj) : c0488s;
            AtomicReference<Map<String, AbstractC0475f>> atomicReference = C0473d.f313a;
            c0488s = c0488s == null ? C0488s.m285b() : c0488s;
            this.f400e = c0488s;
            if (this instanceof InterfaceC0489t) {
                this.f401f = new int[size()];
                interfaceC0977j.mo939b((InterfaceC0489t) this, obj, C0473d.m225a(abstractC1904c));
                return;
            }
            C0486q c0486q = new C0486q(obj, c0488s, abstractC1904c);
            int size = c0486q.size();
            int[] iArr = new int[size];
            for (int i7 = 0; i7 < size; i7++) {
                iArr[i7] = c0486q.f401f[i7];
            }
            this.f401f = iArr;
            return;
        }
        StringBuilder sbM112a = C0413b.m112a("No period converter found for type: ");
        sbM112a.append(obj == null ? "null" : obj.getClass().getName());
        throw new IllegalArgumentException(sbM112a.toString());
    }
}
