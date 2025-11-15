package p197z;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import p148s.C1857a;
import p162u.InterfaceC1964h;
import p183x.C2042c;
import p197z.C2136c;
import p197z.InterfaceC2134a;

/* compiled from: DiskLruCacheWrapper.java */
/* renamed from: z.e */
/* loaded from: classes.dex */
public class C2138e implements InterfaceC2134a {

    /* renamed from: f */
    public static C2138e f6287f;

    /* renamed from: b */
    public final File f6289b;

    /* renamed from: c */
    public final int f6290c;

    /* renamed from: e */
    public C1857a f6292e;

    /* renamed from: d */
    public final C2136c f6291d = new C2136c();

    /* renamed from: a */
    public final C2144k f6288a = new C2144k();

    public C2138e(File file, int i7) {
        this.f6289b = file;
        this.f6290c = i7;
    }

    @Override // p197z.InterfaceC2134a
    /* renamed from: a */
    public File mo2566a(InterfaceC1964h interfaceC1964h) {
        String strM2572a = this.f6288a.m2572a(interfaceC1964h);
        if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Get: Obtained: ");
            sb.append(strM2572a);
            sb.append(" for for Key: ");
            sb.append(interfaceC1964h);
        }
        try {
            C1857a.e eVarM2117e = m2570c().m2117e(strM2572a);
            if (eVarM2117e != null) {
                return eVarM2117e.f5426a[0];
            }
            return null;
        } catch (IOException unused) {
            Log.isLoggable("DiskLruCacheWrapper", 5);
            return null;
        }
    }

    @Override // p197z.InterfaceC2134a
    /* renamed from: b */
    public void mo2567b(InterfaceC1964h interfaceC1964h, InterfaceC2134a.b bVar) {
        C2136c.a aVarPoll;
        boolean z6;
        C2136c c2136c = this.f6291d;
        synchronized (c2136c) {
            aVarPoll = c2136c.f6280a.get(interfaceC1964h);
            if (aVarPoll == null) {
                C2136c.b bVar2 = c2136c.f6281b;
                synchronized (bVar2.f6284a) {
                    aVarPoll = bVar2.f6284a.poll();
                }
                if (aVarPoll == null) {
                    aVarPoll = new C2136c.a();
                }
                c2136c.f6280a.put(interfaceC1964h, aVarPoll);
            }
            aVarPoll.f6283b++;
        }
        aVarPoll.f6282a.lock();
        try {
            String strM2572a = this.f6288a.m2572a(interfaceC1964h);
            if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
                Objects.toString(interfaceC1964h);
            }
            try {
                C1857a c1857aM2570c = m2570c();
                if (c1857aM2570c.m2117e(strM2572a) == null) {
                    C1857a.c cVarM2116d = c1857aM2570c.m2116d(strM2572a);
                    if (cVarM2116d == null) {
                        throw new IllegalStateException("Had two simultaneous puts for: " + strM2572a);
                    }
                    try {
                        C2042c c2042c = (C2042c) bVar;
                        if (c2042c.f5968a.mo784D(c2042c.f5969b, cVarM2116d.m2125b(0), c2042c.f5970c)) {
                            C1857a.m2111a(C1857a.this, cVarM2116d, true);
                            cVarM2116d.f5416c = true;
                        }
                        if (!z6) {
                            try {
                                cVarM2116d.m2124a();
                            } catch (IOException unused) {
                            }
                        }
                    } finally {
                        if (!cVarM2116d.f5416c) {
                            try {
                                cVarM2116d.m2124a();
                            } catch (IOException unused2) {
                            }
                        }
                    }
                }
            } catch (IOException unused3) {
                Log.isLoggable("DiskLruCacheWrapper", 5);
            }
        } finally {
            this.f6291d.m2568a(interfaceC1964h);
        }
    }

    /* renamed from: c */
    public final synchronized C1857a m2570c() {
        if (this.f6292e == null) {
            this.f6292e = C1857a.m2113g(this.f6289b, 1, 1, this.f6290c);
        }
        return this.f6292e;
    }
}
