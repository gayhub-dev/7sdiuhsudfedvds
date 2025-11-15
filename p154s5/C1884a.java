package p154s5;

import java.util.Iterator;
import java.util.Properties;
import p006a5.C0029o;
import p006a5.InterfaceC0018d;
import p006a5.InterfaceC0020f;
import p154s5.C1887d;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: FilterHolder.java */
/* renamed from: s5.a */
/* loaded from: classes.dex */
public class C1884a extends C1886c<InterfaceC0018d> {

    /* renamed from: o */
    public static final InterfaceC2016c f5482o;

    /* renamed from: n */
    public transient InterfaceC0018d f5483n;

    /* compiled from: FilterHolder.java */
    /* renamed from: s5.a$a */
    public class a extends C1886c<InterfaceC0018d>.a implements InterfaceC0020f {
        public a(C1884a c1884a) {
            super();
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f5482o = C2015b.m2349a(C1884a.class.getName());
    }

    public C1884a() {
        super(1);
    }

    @Override // p154s5.C1886c, p168u5.AbstractC1980a
    public void doStart() throws IllegalAccessException, InstantiationException, C0029o {
        super.doStart();
        if (!InterfaceC0018d.class.isAssignableFrom(this.f5488f)) {
            String str = this.f5488f + " is not a javax.servlet.Filter";
            super.stop();
            throw new IllegalStateException(str);
        }
        if (this.f5483n == null) {
            try {
                this.f5483n = ((C1887d.a) this.f5494l.f5510o).m2155c(this.f5488f);
            } catch (C0029o e7) {
                Throwable th = e7.f31e;
                if (th instanceof InstantiationException) {
                    throw ((InstantiationException) th);
                }
                if (!(th instanceof IllegalAccessException)) {
                    throw e7;
                }
                throw ((IllegalAccessException) th);
            }
        }
        this.f5483n.m14b(new a(this));
    }

    @Override // p154s5.C1886c, p168u5.AbstractC1980a
    public void doStop() {
        InterfaceC0018d interfaceC0018d = this.f5483n;
        if (interfaceC0018d != null) {
            try {
                interfaceC0018d.destroy();
                C1887d c1887d = this.f5494l.f5509n;
                if (c1887d != null) {
                    Iterator<C1887d.b> it = c1887d.f5496J.iterator();
                    while (it.hasNext()) {
                        it.next().m2162f(interfaceC0018d);
                    }
                }
            } catch (Exception e7) {
                f5482o.mo2358i(e7);
            }
        }
        boolean z6 = this.f5491i;
        if (!z6) {
            this.f5483n = null;
        }
        if (z6) {
            return;
        }
        this.f5488f = null;
    }

    @Override // p154s5.C1886c
    public String toString() {
        return this.f5493k;
    }
}
