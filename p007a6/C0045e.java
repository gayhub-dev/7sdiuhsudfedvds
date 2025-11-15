package p007a6;

import java.util.Properties;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: Timeout.java */
/* renamed from: a6.e */
/* loaded from: classes.dex */
public class C0045e {

    /* renamed from: e */
    public static final InterfaceC2016c f58e;

    /* renamed from: a */
    public Object f59a;

    /* renamed from: b */
    public long f60b;

    /* renamed from: c */
    public volatile long f61c = System.currentTimeMillis();

    /* renamed from: d */
    public a f62d;

    /* compiled from: Timeout.java */
    /* renamed from: a6.e$a */
    public static class a {

        /* renamed from: g */
        public C0045e f65g;

        /* renamed from: h */
        public long f66h = 0;

        /* renamed from: f */
        public a f64f = this;

        /* renamed from: e */
        public a f63e = this;

        /* renamed from: a */
        public static void m55a(a aVar) {
            a aVar2 = aVar.f63e;
            aVar2.f64f = aVar.f64f;
            aVar.f64f.f63e = aVar2;
            aVar.f64f = aVar;
            aVar.f63e = aVar;
        }

        /* renamed from: b */
        public void m56b() {
            C0045e c0045e = this.f65g;
            if (c0045e != null) {
                synchronized (c0045e.f59a) {
                    a aVar = this.f63e;
                    aVar.f64f = this.f64f;
                    this.f64f.f63e = aVar;
                    this.f64f = this;
                    this.f63e = this;
                    this.f66h = 0L;
                }
            }
        }

        /* renamed from: c */
        public void mo57c() {
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f58e = C2015b.m2349a(C0045e.class.getName());
    }

    public C0045e() {
        a aVar = new a();
        this.f62d = aVar;
        this.f59a = new Object();
        aVar.f65g = this;
    }

    /* renamed from: a */
    public void m50a() {
        synchronized (this.f59a) {
            a aVar = this.f62d;
            aVar.f64f = aVar;
            aVar.f63e = aVar;
        }
    }

    /* renamed from: b */
    public a m51b() {
        synchronized (this.f59a) {
            long j7 = this.f61c - this.f60b;
            a aVar = this.f62d;
            a aVar2 = aVar.f63e;
            if (aVar2 == aVar) {
                return null;
            }
            if (aVar2.f66h > j7) {
                return null;
            }
            a.m55a(aVar2);
            return aVar2;
        }
    }

    /* renamed from: c */
    public long m52c() {
        synchronized (this.f59a) {
            a aVar = this.f62d;
            a aVar2 = aVar.f63e;
            if (aVar2 == aVar) {
                return -1L;
            }
            long j7 = (this.f60b + aVar2.f66h) - this.f61c;
            if (j7 < 0) {
                j7 = 0;
            }
            return j7;
        }
    }

    /* renamed from: d */
    public void m53d(a aVar, long j7) {
        synchronized (this.f59a) {
            if (aVar.f66h != 0) {
                a.m55a(aVar);
                aVar.f66h = 0L;
            }
            aVar.f65g = this;
            aVar.f66h = this.f61c + j7;
            a aVar2 = this.f62d.f64f;
            while (aVar2 != this.f62d && aVar2.f66h > aVar.f66h) {
                aVar2 = aVar2.f64f;
            }
            a aVar3 = aVar2.f63e;
            aVar3.f64f = aVar;
            aVar2.f63e = aVar;
            aVar.f63e = aVar3;
            aVar2.f63e.f64f = aVar2;
        }
    }

    /* renamed from: e */
    public void m54e(long j7) {
        a aVar;
        this.f61c = j7;
        long j8 = this.f61c - this.f60b;
        while (true) {
            try {
                synchronized (this.f59a) {
                    a aVar2 = this.f62d;
                    aVar = aVar2.f63e;
                    if (aVar != aVar2 && aVar.f66h <= j8) {
                        a.m55a(aVar);
                    }
                    return;
                }
                aVar.mo57c();
            } catch (Throwable th) {
                f58e.mo2354e("EXCEPTION ", th);
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        for (a aVar = this.f62d.f63e; aVar != this.f62d; aVar = aVar.f63e) {
            stringBuffer.append("-->");
            stringBuffer.append(aVar);
        }
        return stringBuffer.toString();
    }

    public C0045e(Object obj) {
        a aVar = new a();
        this.f62d = aVar;
        this.f59a = obj;
        aVar.f65g = this;
    }
}
