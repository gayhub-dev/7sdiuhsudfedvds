package p197z;

import android.support.v4.util.Pools;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import p142r0.C1820e;
import p142r0.C1823h;
import p149s0.AbstractC1863d;
import p149s0.C1860a;
import p162u.InterfaceC1964h;

/* compiled from: SafeKeyGenerator.java */
/* renamed from: z.k */
/* loaded from: classes.dex */
public class C2144k {

    /* renamed from: a */
    public final C1820e<InterfaceC1964h, String> f6306a = new C1820e<>(1000);

    /* renamed from: b */
    public final Pools.Pool<b> f6307b = new C1860a.c(new Pools.SynchronizedPool(10), new a(this), C1860a.f5434a);

    /* compiled from: SafeKeyGenerator.java */
    /* renamed from: z.k$a */
    public class a implements C1860a.b<b> {
        public a(C2144k c2144k) {
        }

        @Override // p149s0.C1860a.b
        /* renamed from: a */
        public b mo1703a() {
            try {
                return new b(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e7) {
                throw new RuntimeException(e7);
            }
        }
    }

    /* compiled from: SafeKeyGenerator.java */
    /* renamed from: z.k$b */
    public static final class b implements C1860a.d {

        /* renamed from: e */
        public final MessageDigest f6308e;

        /* renamed from: f */
        public final AbstractC1863d f6309f = new AbstractC1863d.b();

        public b(MessageDigest messageDigest) {
            this.f6308e = messageDigest;
        }

        @Override // p149s0.C1860a.d
        /* renamed from: e */
        public AbstractC1863d mo1694e() {
            return this.f6309f;
        }
    }

    /* renamed from: a */
    public String m2572a(InterfaceC1964h interfaceC1964h) {
        String strM2051a;
        synchronized (this.f6306a) {
            strM2051a = this.f6306a.m2051a(interfaceC1964h);
        }
        if (strM2051a == null) {
            b bVarAcquire = this.f6307b.acquire();
            try {
                interfaceC1964h.mo130b(bVarAcquire.f6308e);
                byte[] bArrDigest = bVarAcquire.f6308e.digest();
                char[] cArr = C1823h.f5301b;
                synchronized (cArr) {
                    for (int i7 = 0; i7 < bArrDigest.length; i7++) {
                        int i8 = bArrDigest[i7] & 255;
                        int i9 = i7 * 2;
                        char[] cArr2 = C1823h.f5300a;
                        cArr[i9] = cArr2[i8 >>> 4];
                        cArr[i9 + 1] = cArr2[i8 & 15];
                    }
                    strM2051a = new String(cArr);
                }
            } finally {
                this.f6307b.release(bVarAcquire);
            }
        }
        synchronized (this.f6306a) {
            this.f6306a.m2053d(interfaceC1964h, strM2051a);
        }
        return strM2051a;
    }
}
