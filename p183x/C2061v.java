package p183x;

import android.os.SystemClock;
import android.util.Log;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import p010b0.InterfaceC0426l;
import p142r0.C1819d;
import p162u.EnumC1957a;
import p162u.InterfaceC1960d;
import p162u.InterfaceC1964h;
import p169v.InterfaceC1986b;
import p183x.InterfaceC2043d;

/* compiled from: SourceGenerator.java */
/* renamed from: x.v */
/* loaded from: classes.dex */
public class C2061v implements InterfaceC2043d, InterfaceC1986b.a<Object>, InterfaceC2043d.a {

    /* renamed from: e */
    public final C2044e<?> f6143e;

    /* renamed from: f */
    public final InterfaceC2043d.a f6144f;

    /* renamed from: g */
    public int f6145g;

    /* renamed from: h */
    public C2040a f6146h;

    /* renamed from: i */
    public Object f6147i;

    /* renamed from: j */
    public volatile InterfaceC0426l.a<?> f6148j;

    /* renamed from: k */
    public C2041b f6149k;

    public C2061v(C2044e<?> c2044e, InterfaceC2043d.a aVar) {
        this.f6143e = c2044e;
        this.f6144f = aVar;
    }

    @Override // p183x.InterfaceC2043d.a
    /* renamed from: a */
    public void mo2401a(InterfaceC1964h interfaceC1964h, Exception exc, InterfaceC1986b<?> interfaceC1986b, EnumC1957a enumC1957a) {
        this.f6144f.mo2401a(interfaceC1964h, exc, interfaceC1986b, this.f6148j.f233c.getDataSource());
    }

    @Override // p183x.InterfaceC2043d.a
    /* renamed from: b */
    public void mo2402b() {
        throw new UnsupportedOperationException();
    }

    @Override // p183x.InterfaceC2043d.a
    /* renamed from: c */
    public void mo2403c(InterfaceC1964h interfaceC1964h, Object obj, InterfaceC1986b<?> interfaceC1986b, EnumC1957a enumC1957a, InterfaceC1964h interfaceC1964h2) {
        this.f6144f.mo2403c(interfaceC1964h, obj, interfaceC1986b, this.f6148j.f233c.getDataSource(), interfaceC1964h);
    }

    @Override // p183x.InterfaceC2043d
    public void cancel() {
        InterfaceC0426l.a<?> aVar = this.f6148j;
        if (aVar != null) {
            aVar.f233c.cancel();
        }
    }

    @Override // p169v.InterfaceC1986b.a
    /* renamed from: d */
    public void mo140d(Exception exc) {
        this.f6144f.mo2401a(this.f6149k, exc, this.f6148j.f233c, this.f6148j.f233c.getDataSource());
    }

    @Override // p169v.InterfaceC1986b.a
    /* renamed from: e */
    public void mo141e(Object obj) {
        AbstractC2047h abstractC2047h = this.f6143e.f5986p;
        if (obj == null || !abstractC2047h.mo2429c(this.f6148j.f233c.getDataSource())) {
            this.f6144f.mo2403c(this.f6148j.f231a, obj, this.f6148j.f233c, this.f6148j.f233c.getDataSource(), this.f6149k);
        } else {
            this.f6147i = obj;
            this.f6144f.mo2402b();
        }
    }

    @Override // p183x.InterfaceC2043d
    /* renamed from: f */
    public boolean mo2400f() {
        Object obj = this.f6147i;
        if (obj != null) {
            this.f6147i = null;
            int i7 = C1819d.f5292b;
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            try {
                InterfaceC1960d<X> interfaceC1960dM2408e = this.f6143e.m2408e(obj);
                C2042c c2042c = new C2042c(interfaceC1960dM2408e, obj, this.f6143e.f5979i);
                InterfaceC1964h interfaceC1964h = this.f6148j.f231a;
                C2044e<?> c2044e = this.f6143e;
                this.f6149k = new C2041b(interfaceC1964h, c2044e.f5984n);
                c2044e.m2405b().mo2567b(this.f6149k, c2042c);
                if (Log.isLoggable("SourceGenerator", 2)) {
                    Objects.toString(this.f6149k);
                    obj.toString();
                    interfaceC1960dM2408e.toString();
                    C1819d.m2050a(jElapsedRealtimeNanos);
                }
                this.f6148j.f233c.mo124b();
                this.f6146h = new C2040a(Collections.singletonList(this.f6148j.f231a), this.f6143e, this);
            } catch (Throwable th) {
                this.f6148j.f233c.mo124b();
                throw th;
            }
        }
        C2040a c2040a = this.f6146h;
        if (c2040a != null && c2040a.mo2400f()) {
            return true;
        }
        this.f6146h = null;
        this.f6148j = null;
        boolean z6 = false;
        while (!z6) {
            if (!(this.f6145g < this.f6143e.m2406c().size())) {
                break;
            }
            List<InterfaceC0426l.a<?>> listM2406c = this.f6143e.m2406c();
            int i8 = this.f6145g;
            this.f6145g = i8 + 1;
            this.f6148j = listM2406c.get(i8);
            if (this.f6148j != null && (this.f6143e.f5986p.mo2429c(this.f6148j.f233c.getDataSource()) || this.f6143e.m2410g(this.f6148j.f233c.mo123a()))) {
                this.f6148j.f233c.mo125c(this.f6143e.f5985o, this);
                z6 = true;
            }
        }
        return z6;
    }
}
