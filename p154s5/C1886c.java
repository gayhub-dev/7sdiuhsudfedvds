package p154s5;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import p006a5.C0015a0;
import p006a5.InterfaceC0026l;
import p009b.C0413b;
import p043f.C0984a;
import p043f.C0986c;
import p168u5.AbstractC1980a;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: Holder.java */
/* renamed from: s5.c */
/* loaded from: classes.dex */
public class C1886c<T> extends AbstractC1980a {

    /* renamed from: m */
    public static final InterfaceC2016c f5486m;

    /* renamed from: e */
    public final int f5487e;

    /* renamed from: f */
    public transient Class<? extends T> f5488f;

    /* renamed from: g */
    public final Map<String, String> f5489g = new HashMap(3);

    /* renamed from: h */
    public String f5490h;

    /* renamed from: i */
    public boolean f5491i;

    /* renamed from: j */
    public boolean f5492j;

    /* renamed from: k */
    public String f5493k;

    /* renamed from: l */
    public C1888e f5494l;

    /* compiled from: Holder.java */
    /* renamed from: s5.c$a */
    public class a {
        public a() {
        }

        public String getInitParameter(String str) {
            Map<String, String> map = C1886c.this.f5489g;
            if (map == null) {
                return null;
            }
            return map.get(str);
        }

        public Enumeration getInitParameterNames() {
            Map<String, String> map = C1886c.this.f5489g;
            return map == null ? Collections.enumeration(Collections.EMPTY_LIST) : Collections.enumeration(map.keySet());
        }

        public InterfaceC0026l getServletContext() {
            return C1886c.this.f5494l.f5510o;
        }
    }

    /* compiled from: Holder.java */
    /* renamed from: s5.c$b */
    public class b {
        public b(C1886c c1886c) {
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f5486m = C2015b.m2349a(C1886c.class.getName());
    }

    public C1886c(int i7) {
        this.f5487e = i7;
        int iM950c = C0986c.m950c(i7);
        if (iM950c == 1 || iM950c == 2 || iM950c == 3) {
            this.f5492j = false;
        } else {
            this.f5492j = true;
        }
    }

    /* renamed from: G */
    public void m2153G(Class<? extends T> cls) {
        this.f5488f = cls;
        this.f5490h = cls.getName();
        if (this.f5493k == null) {
            this.f5493k = cls.getName() + "-" + Integer.toHexString(hashCode());
        }
    }

    @Override // p168u5.AbstractC1980a
    public void doStart() throws C0015a0 {
        String str;
        if (this.f5488f == null && ((str = this.f5490h) == null || str.equals(""))) {
            StringBuilder sbM112a = C0413b.m112a("No class for Servlet or Filter for ");
            sbM112a.append(this.f5493k);
            throw new C0015a0(sbM112a.toString());
        }
        if (this.f5488f == null) {
            try {
                this.f5488f = C0984a.m940e(C1886c.class, this.f5490h);
                InterfaceC2016c interfaceC2016c = f5486m;
                if (interfaceC2016c.mo2353d()) {
                    interfaceC2016c.mo2351a("Holding {}", this.f5488f);
                }
            } catch (Exception e7) {
                f5486m.mo2358i(e7);
                throw new C0015a0(e7.getMessage());
            }
        }
    }

    @Override // p168u5.AbstractC1980a
    public void doStop() {
        if (this.f5491i) {
            return;
        }
        this.f5488f = null;
    }

    public String toString() {
        return this.f5493k;
    }
}
