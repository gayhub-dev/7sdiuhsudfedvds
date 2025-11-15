package p016b6;

import android.support.constraint.C0072a;
import p009b.C0413b;
import p024c6.AbstractC0527h;
import p159t3.AbstractC1904c;

/* compiled from: MutablePeriod.java */
/* renamed from: b6.q */
/* loaded from: classes.dex */
public class C0486q extends AbstractC0527h implements InterfaceC0489t, Cloneable {
    private static final long serialVersionUID = 3436451121567212165L;

    public C0486q(long j7, C0488s c0488s) {
        super(j7, c0488s, (AbstractC1904c) null);
    }

    @Override // p016b6.InterfaceC0489t
    /* renamed from: I */
    public void mo275I(InterfaceC0494y interfaceC0494y) {
        if (interfaceC0494y == null) {
            int[] iArr = new int[size()];
            int[] iArr2 = this.f401f;
            System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
            return;
        }
        int[] iArr3 = new int[size()];
        int size = interfaceC0494y.size();
        for (int i7 = 0; i7 < size; i7++) {
            AbstractC0478i abstractC0478iMo293d = interfaceC0494y.mo293d(i7);
            int iMo294k = interfaceC0494y.mo294k(i7);
            int iM286a = mo295q().m286a(abstractC0478iMo293d);
            if (iM286a != -1) {
                iArr3[iM286a] = iMo294k;
            } else if (iMo294k != 0) {
                throw new IllegalArgumentException(C0072a.m92a(C0413b.m112a("Period does not support field '"), abstractC0478iMo293d.f334e, "'"));
            }
        }
        int[] iArr4 = this.f401f;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
    }

    @Override // p016b6.InterfaceC0489t
    /* renamed from: J */
    public void mo276J(int i7) {
        m336X(AbstractC0478i.f325i, i7);
    }

    @Override // p016b6.InterfaceC0489t
    /* renamed from: K */
    public void mo277K(int i7) {
        m336X(AbstractC0478i.f330n, i7);
    }

    @Override // p016b6.InterfaceC0489t
    /* renamed from: R */
    public void mo278R(int i7) {
        m336X(AbstractC0478i.f328l, i7);
    }

    @Override // p016b6.InterfaceC0489t
    /* renamed from: S */
    public void mo279S(int i7) {
        m336X(AbstractC0478i.f332p, i7);
    }

    @Override // p016b6.InterfaceC0489t
    /* renamed from: U */
    public void mo280U(int i7) {
        m336X(AbstractC0478i.f326j, i7);
    }

    @Override // p016b6.InterfaceC0489t
    /* renamed from: b */
    public void mo281b(int i7, int i8) {
        this.f401f[i7] = i8;
    }

    @Override // p016b6.InterfaceC0489t
    public void clear() {
        int[] iArr = new int[size()];
        int[] iArr2 = this.f401f;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError("Clone error");
        }
    }

    @Override // p016b6.InterfaceC0489t
    /* renamed from: g */
    public void mo282g(int i7) {
        m336X(AbstractC0478i.f331o, i7);
    }

    @Override // p016b6.InterfaceC0489t
    /* renamed from: j */
    public void mo283j(int i7) {
        m336X(AbstractC0478i.f333q, i7);
    }

    @Override // p016b6.InterfaceC0489t
    /* renamed from: x */
    public void mo284x(int i7) {
        m336X(AbstractC0478i.f327k, i7);
    }

    public C0486q(Object obj, C0488s c0488s, AbstractC1904c abstractC1904c) {
        super(obj, c0488s, abstractC1904c);
    }
}
