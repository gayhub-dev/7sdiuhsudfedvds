package p105m5;

import p006a5.InterfaceC0032r;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0462g;
import p097l5.AbstractC1443h;
import p097l5.InterfaceC1436a;
import p097l5.InterfaceC1440e;
import p097l5.InterfaceC1441f;
import p113n5.InterfaceC1561v;
import p147r5.AbstractC1840c;

/* compiled from: LoginAuthenticator.java */
/* renamed from: m5.f */
/* loaded from: classes.dex */
public abstract class AbstractC1495f implements InterfaceC1436a {

    /* renamed from: a */
    public InterfaceC1441f f4273a;

    /* renamed from: b */
    public InterfaceC1440e f4274b;

    /* renamed from: c */
    public boolean f4275c;

    @Override // p097l5.InterfaceC1436a
    /* renamed from: c */
    public void mo1610c(InterfaceC1436a.a aVar) {
        InterfaceC1441f interfaceC1441f = ((AbstractC1443h) aVar).f4194o;
        this.f4273a = interfaceC1441f;
        if (interfaceC1441f == null) {
            throw new IllegalStateException("No LoginService for " + this + " in " + aVar);
        }
        AbstractC1443h abstractC1443h = (AbstractC1443h) aVar;
        InterfaceC1440e interfaceC1440e = abstractC1443h.f4196q;
        this.f4274b = interfaceC1440e;
        if (interfaceC1440e != null) {
            this.f4275c = abstractC1443h.f4197r;
            return;
        }
        throw new IllegalStateException("No IdentityService for " + this + " in " + aVar);
    }

    /* renamed from: e */
    public InterfaceC1561v mo1654e(String str, Object obj, InterfaceC0032r interfaceC0032r) {
        InterfaceC1561v interfaceC1561vM1621a = this.f4273a.m1621a(str, obj);
        if (interfaceC1561vM1621a == null) {
            return null;
        }
        InterfaceC0458c interfaceC0458c = (InterfaceC0458c) interfaceC0032r;
        InterfaceC0462g interfaceC0462gMo171t = interfaceC0458c.mo171t(false);
        if (this.f4275c && interfaceC0462gMo171t != null && interfaceC0462gMo171t.mo186a("org.eclipse.jetty.security.sessionKnownOnlytoAuthenticated") != Boolean.TRUE) {
            synchronized (this) {
                AbstractC1840c.m2095P(interfaceC0458c, interfaceC0462gMo171t, true);
            }
        }
        return interfaceC1561vM1621a;
    }
}
