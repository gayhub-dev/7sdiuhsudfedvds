package p116o0;

import android.support.constraint.solver.C0084a;
import p009b.C0413b;
import p108n0.C1514e;
import p142r0.C1823h;

/* compiled from: SimpleTarget.java */
/* renamed from: o0.d */
/* loaded from: classes.dex */
public abstract class AbstractC1575d<Z> extends AbstractC1572a<Z> {

    /* renamed from: b */
    public final int f4732b = Integer.MIN_VALUE;

    /* renamed from: c */
    public final int f4733c = Integer.MIN_VALUE;

    @Override // p116o0.InterfaceC1577f
    /* renamed from: c */
    public final void mo1822c(InterfaceC1576e interfaceC1576e) {
        if (C1823h.m2065i(this.f4732b, this.f4733c)) {
            ((C1514e) interfaceC1576e).mo1695f(this.f4732b, this.f4733c);
        } else {
            StringBuilder sbM112a = C0413b.m112a("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: ");
            sbM112a.append(this.f4732b);
            sbM112a.append(" and height: ");
            throw new IllegalArgumentException(C0084a.m96a(sbM112a, this.f4733c, ", either provide dimensions in the constructor or call override()"));
        }
    }

    @Override // p116o0.InterfaceC1577f
    /* renamed from: d */
    public void mo1823d(InterfaceC1576e interfaceC1576e) {
    }
}
