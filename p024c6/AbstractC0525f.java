package p024c6;

import java.io.Serializable;
import p009b.C0413b;
import p042e6.C0971d;
import p042e6.InterfaceC0974g;

/* compiled from: BaseDuration.java */
/* renamed from: c6.f */
/* loaded from: classes.dex */
public abstract class AbstractC0525f extends AbstractC0521b implements Serializable {
    private static final long serialVersionUID = 2581698638990L;

    /* renamed from: e */
    public volatile long f399e;

    public AbstractC0525f(Object obj) {
        if (C0971d.f1797f == null) {
            C0971d.f1797f = new C0971d();
        }
        InterfaceC0974g interfaceC0974g = (InterfaceC0974g) C0971d.f1797f.f1800c.m937b(obj == null ? null : obj.getClass());
        if (interfaceC0974g != null) {
            this.f399e = interfaceC0974g.mo938d(obj);
        } else {
            StringBuilder sbM112a = C0413b.m112a("No duration converter found for type: ");
            sbM112a.append(obj == null ? "null" : obj.getClass().getName());
            throw new IllegalArgumentException(sbM112a.toString());
        }
    }

    @Override // p016b6.InterfaceC0490u
    /* renamed from: g */
    public long mo287g() {
        return this.f399e;
    }
}
