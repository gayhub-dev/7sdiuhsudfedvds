package p105m5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.EventListener;
import java.util.Properties;
import p009b.C0413b;
import p015b5.C0464i;
import p015b5.InterfaceC0462g;
import p015b5.InterfaceC0465j;
import p097l5.AbstractC1443h;
import p097l5.InterfaceC1440e;
import p097l5.InterfaceC1441f;
import p113n5.InterfaceC1543d;
import p113n5.InterfaceC1561v;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: SessionAuthentication.java */
/* renamed from: m5.g */
/* loaded from: classes.dex */
public class C1496g implements InterfaceC1543d.g, Serializable, EventListener, InterfaceC0465j {

    /* renamed from: j */
    public static final InterfaceC2016c f4276j;
    private static final long serialVersionUID = -4643200685888258706L;

    /* renamed from: e */
    public final String f4277e;

    /* renamed from: f */
    public final String f4278f;

    /* renamed from: g */
    public final Object f4279g;

    /* renamed from: h */
    public transient InterfaceC1561v f4280h;

    /* renamed from: i */
    public transient InterfaceC0462g f4281i;

    static {
        Properties properties = C2015b.f5863a;
        f4276j = C2015b.m2349a(C1496g.class.getName());
    }

    public C1496g(String str, InterfaceC1561v interfaceC1561v, Object obj) {
        this.f4277e = str;
        this.f4280h = interfaceC1561v;
        this.f4278f = interfaceC1561v.m1808a().getName();
        this.f4279g = obj;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        AbstractC1443h abstractC1443hM1629S = AbstractC1443h.m1629S();
        if (abstractC1443hM1629S == null) {
            throw new IllegalStateException("!SecurityHandler");
        }
        InterfaceC1441f interfaceC1441f = abstractC1443hM1629S.f4194o;
        if (interfaceC1441f == null) {
            throw new IllegalStateException("!LoginService");
        }
        this.f4280h = interfaceC1441f.m1621a(this.f4278f, this.f4279g);
        f4276j.mo2351a("Deserialized and relogged in {}", this);
    }

    @Override // p113n5.InterfaceC1543d.g
    /* renamed from: a */
    public String mo1631a() {
        return this.f4277e;
    }

    @Override // p015b5.InterfaceC0465j
    /* renamed from: c */
    public void mo194c(C0464i c0464i) {
        AbstractC1443h abstractC1443hM1629S = AbstractC1443h.m1629S();
        if (abstractC1443hM1629S != null) {
            AbstractC1443h.f4189s.mo2351a("logout {}", this);
            InterfaceC1441f interfaceC1441f = abstractC1443hM1629S.f4194o;
            if (interfaceC1441f != null) {
                interfaceC1441f.m1624d(this.f4280h);
            }
            InterfaceC1440e interfaceC1440e = abstractC1443hM1629S.f4196q;
            if (interfaceC1440e != null) {
                interfaceC1440e.m1618c(null);
            }
        }
        InterfaceC0462g interfaceC0462g = this.f4281i;
        if (interfaceC0462g != null) {
            interfaceC0462g.mo188e("org.eclipse.jetty.security.sessionKnownOnlytoAuthenticated");
        }
    }

    @Override // p113n5.InterfaceC1543d.g
    /* renamed from: h */
    public InterfaceC1561v mo1632h() {
        return this.f4280h;
    }

    @Override // p015b5.InterfaceC0465j
    /* renamed from: p */
    public void mo195p(C0464i c0464i) {
        if (this.f4281i == null) {
            this.f4281i = c0464i.mo19a();
        }
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Session");
        sbM112a.append(super.toString());
        return sbM112a.toString();
    }
}
