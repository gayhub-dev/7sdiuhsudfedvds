package p105m5;

import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import java.io.IOException;
import java.util.Properties;
import p006a5.InterfaceC0032r;
import p006a5.InterfaceC0037w;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p097l5.C1444i;
import p097l5.C1445j;
import p113n5.InterfaceC1543d;
import p113n5.InterfaceC1561v;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: SpnegoAuthenticator.java */
/* renamed from: m5.h */
/* loaded from: classes.dex */
public class C1497h extends AbstractC1495f {

    /* renamed from: e */
    public static final InterfaceC2016c f4282e;

    /* renamed from: d */
    public String f4283d;

    static {
        Properties properties = C2015b.f5863a;
        f4282e = C2015b.m2349a(C1497h.class.getName());
    }

    public C1497h() {
        this.f4283d = "SPNEGO";
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: a */
    public String mo1608a() {
        return this.f4283d;
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: b */
    public boolean mo1609b(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w, boolean z6, InterfaceC1543d.g gVar) {
        return true;
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: d */
    public InterfaceC1543d mo1611d(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w, boolean z6) throws C1444i {
        InterfaceC1561v interfaceC1561vMo1654e;
        InterfaceC0460e interfaceC0460e = (InterfaceC0460e) interfaceC0037w;
        String strMo173x = ((InterfaceC0458c) interfaceC0032r).mo173x(HttpHeaders.AUTHORIZATION);
        if (!z6) {
            return new C1492c(this);
        }
        if (strMo173x != null) {
            return (!strMo173x.startsWith("Negotiate") || (interfaceC1561vMo1654e = mo1654e(null, strMo173x.substring(10), interfaceC0032r)) == null) ? InterfaceC1543d.f4583a : new C1445j(this.f4283d, interfaceC1561vMo1654e);
        }
        try {
            if (C1492c.m1651b(interfaceC0460e)) {
                return InterfaceC1543d.f4583a;
            }
            f4282e.mo2351a("SpengoAuthenticator: sending challenge", new Object[0]);
            interfaceC0460e.mo177J("WWW-Authenticate", "Negotiate");
            interfaceC0460e.mo183v(401);
            return InterfaceC1543d.f4585c;
        } catch (IOException e7) {
            throw new C1444i(e7);
        }
    }

    public C1497h(String str) {
        this.f4283d = "SPNEGO";
        this.f4283d = str;
    }
}
