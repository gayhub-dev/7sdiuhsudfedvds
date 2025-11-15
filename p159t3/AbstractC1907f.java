package p159t3;

import java.net.InetSocketAddress;
import java.security.AccessController;
import p166u3.AbstractC1976b;
import p166u3.C1975a;

/* compiled from: HttpServer.java */
/* renamed from: t3.f */
/* loaded from: classes.dex */
public abstract class AbstractC1907f {
    /* renamed from: a */
    public static AbstractC1907f m2203a(InetSocketAddress inetSocketAddress, int i7) {
        AbstractC1976b abstractC1976b;
        synchronized (AbstractC1976b.f5775a) {
            abstractC1976b = AbstractC1976b.f5776b;
            if (abstractC1976b == null) {
                abstractC1976b = (AbstractC1976b) AccessController.doPrivileged(new C1975a());
            }
        }
        return abstractC1976b.mo1976a(inetSocketAddress, i7);
    }

    /* renamed from: b */
    public abstract AbstractC1904c mo1995b(String str, InterfaceC1906e interfaceC1906e);

    /* renamed from: c */
    public abstract void mo1996c();

    /* renamed from: d */
    public abstract void mo1997d(int i7);
}
