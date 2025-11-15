package p183x;

import android.support.v4.util.Pools;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import p009b.C0413b;
import p010b0.InterfaceC0426l;
import p076j0.InterfaceC1182b;
import p141r.C1812g;
import p162u.C1966j;
import p162u.EnumC1957a;
import p162u.EnumC1959c;
import p162u.InterfaceC1964h;
import p162u.InterfaceC1967k;
import p162u.InterfaceC1968l;
import p162u.InterfaceC1969m;
import p169v.InterfaceC1987c;
import p183x.RunnableC2045f;

/* compiled from: DecodePath.java */
/* renamed from: x.g */
/* loaded from: classes.dex */
public class C2046g<DataType, ResourceType, Transcode> {

    /* renamed from: a */
    public final Class<DataType> f6038a;

    /* renamed from: b */
    public final List<? extends InterfaceC1967k<DataType, ResourceType>> f6039b;

    /* renamed from: c */
    public final InterfaceC1182b<ResourceType, Transcode> f6040c;

    /* renamed from: d */
    public final Pools.Pool<List<Exception>> f6041d;

    /* renamed from: e */
    public final String f6042e;

    /* compiled from: DecodePath.java */
    /* renamed from: x.g$a */
    public interface a<ResourceType> {
    }

    public C2046g(Class<DataType> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<? extends InterfaceC1967k<DataType, ResourceType>> list, InterfaceC1182b<ResourceType, Transcode> interfaceC1182b, Pools.Pool<List<Exception>> pool) {
        this.f6038a = cls;
        this.f6039b = list;
        this.f6040c = interfaceC1182b;
        this.f6041d = pool;
        StringBuilder sbM112a = C0413b.m112a("Failed DecodePath{");
        sbM112a.append(cls.getSimpleName());
        sbM112a.append("->");
        sbM112a.append(cls2.getSimpleName());
        sbM112a.append("->");
        sbM112a.append(cls3.getSimpleName());
        sbM112a.append("}");
        this.f6042e = sbM112a.toString();
    }

    /* renamed from: a */
    public InterfaceC2057r<Transcode> m2425a(InterfaceC1987c<DataType> interfaceC1987c, int i7, int i8, C1966j c1966j, a<ResourceType> aVar) {
        InterfaceC2057r<ResourceType> interfaceC2057rMo672a;
        InterfaceC1969m interfaceC1969m;
        EnumC1959c enumC1959cMo821s;
        InterfaceC1964h c2059t;
        List<Exception> listAcquire = this.f6041d.acquire();
        try {
            InterfaceC2057r<ResourceType> interfaceC2057rM2426b = m2426b(interfaceC1987c, i7, i8, c1966j, listAcquire);
            this.f6041d.release(listAcquire);
            RunnableC2045f.b bVar = (RunnableC2045f.b) aVar;
            Objects.requireNonNull(bVar);
            Class<?> cls = interfaceC2057rM2426b.get().getClass();
            InterfaceC1968l interfaceC1968l = null;
            if (bVar.f6019a != EnumC1957a.RESOURCE_DISK_CACHE) {
                InterfaceC1969m interfaceC1969mM2409f = RunnableC2045f.this.f5997e.m2409f(cls);
                RunnableC2045f runnableC2045f = RunnableC2045f.this;
                interfaceC1969m = interfaceC1969mM2409f;
                interfaceC2057rMo672a = interfaceC1969mM2409f.mo672a(runnableC2045f.f6004l, interfaceC2057rM2426b, runnableC2045f.f6008p, runnableC2045f.f6009q);
            } else {
                interfaceC2057rMo672a = interfaceC2057rM2426b;
                interfaceC1969m = null;
            }
            if (!interfaceC2057rM2426b.equals(interfaceC2057rMo672a)) {
                interfaceC2057rM2426b.recycle();
            }
            boolean z6 = false;
            if (RunnableC2045f.this.f5997e.f5973c.f5235a.f5249d.m1641a(interfaceC2057rMo672a.mo824b()) != null) {
                InterfaceC1968l interfaceC1968lM1641a = RunnableC2045f.this.f5997e.f5973c.f5235a.f5249d.m1641a(interfaceC2057rMo672a.mo824b());
                if (interfaceC1968lM1641a == null) {
                    throw new C1812g.d(interfaceC2057rMo672a.mo824b());
                }
                enumC1959cMo821s = interfaceC1968lM1641a.mo821s(RunnableC2045f.this.f6011s);
                interfaceC1968l = interfaceC1968lM1641a;
            } else {
                enumC1959cMo821s = EnumC1959c.NONE;
            }
            RunnableC2045f runnableC2045f2 = RunnableC2045f.this;
            C2044e<R> c2044e = runnableC2045f2.f5997e;
            InterfaceC1964h interfaceC1964h = runnableC2045f2.f5989A;
            List<InterfaceC0426l.a<?>> listM2406c = c2044e.m2406c();
            int size = listM2406c.size();
            int i9 = 0;
            while (true) {
                if (i9 >= size) {
                    break;
                }
                if (listM2406c.get(i9).f231a.equals(interfaceC1964h)) {
                    z6 = true;
                    break;
                }
                i9++;
            }
            InterfaceC2057r<ResourceType> interfaceC2057r = interfaceC2057rMo672a;
            if (RunnableC2045f.this.f6010r.mo2430d(!z6, bVar.f6019a, enumC1959cMo821s)) {
                if (interfaceC1968l == null) {
                    throw new C1812g.d(interfaceC2057rMo672a.get().getClass());
                }
                if (enumC1959cMo821s == EnumC1959c.SOURCE) {
                    RunnableC2045f runnableC2045f3 = RunnableC2045f.this;
                    c2059t = new C2041b(runnableC2045f3.f5989A, runnableC2045f3.f6005m);
                } else {
                    if (enumC1959cMo821s != EnumC1959c.TRANSFORMED) {
                        throw new IllegalArgumentException("Unknown strategy: " + enumC1959cMo821s);
                    }
                    RunnableC2045f runnableC2045f4 = RunnableC2045f.this;
                    c2059t = new C2059t(runnableC2045f4.f5989A, runnableC2045f4.f6005m, runnableC2045f4.f6008p, runnableC2045f4.f6009q, interfaceC1969m, cls, runnableC2045f4.f6011s);
                }
                C2056q<Z> c2056qM2445a = C2056q.m2445a(interfaceC2057rMo672a);
                RunnableC2045f.c<?> cVar = RunnableC2045f.this.f6002j;
                cVar.f6021a = c2059t;
                cVar.f6022b = interfaceC1968l;
                cVar.f6023c = c2056qM2445a;
                interfaceC2057r = c2056qM2445a;
            }
            return this.f6040c.mo942b(interfaceC2057r);
        } catch (Throwable th) {
            this.f6041d.release(listAcquire);
            throw th;
        }
    }

    /* renamed from: b */
    public final InterfaceC2057r<ResourceType> m2426b(InterfaceC1987c<DataType> interfaceC1987c, int i7, int i8, C1966j c1966j, List<Exception> list) throws C2053n {
        int size = this.f6039b.size();
        InterfaceC2057r<ResourceType> interfaceC2057rMo820b = null;
        for (int i9 = 0; i9 < size; i9++) {
            InterfaceC1967k<DataType, ResourceType> interfaceC1967k = this.f6039b.get(i9);
            try {
                if (interfaceC1967k.mo819a(interfaceC1987c.mo1003a(), c1966j)) {
                    interfaceC2057rMo820b = interfaceC1967k.mo820b(interfaceC1987c.mo1003a(), i7, i8, c1966j);
                }
            } catch (IOException e7) {
                if (Log.isLoggable("DecodePath", 2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to decode data for ");
                    sb.append(interfaceC1967k);
                }
                list.add(e7);
            }
            if (interfaceC2057rMo820b != null) {
                break;
            }
        }
        if (interfaceC2057rMo820b != null) {
            return interfaceC2057rMo820b;
        }
        throw new C2053n(this.f6042e, new ArrayList(list));
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("DecodePath{ dataClass=");
        sbM112a.append(this.f6038a);
        sbM112a.append(", decoders=");
        sbM112a.append(this.f6039b);
        sbM112a.append(", transcoder=");
        sbM112a.append(this.f6040c);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
