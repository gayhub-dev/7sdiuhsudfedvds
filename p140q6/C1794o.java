package p140q6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import p159t3.AbstractC1905d;
import p159t3.C1903b;

/* compiled from: HttpsExchangeImpl.java */
/* renamed from: q6.o */
/* loaded from: classes.dex */
public class C1794o extends AbstractC1905d {

    /* renamed from: a */
    public C1786g f5120a;

    public C1794o(C1786g c1786g) {
        this.f5120a = c1786g;
    }

    @Override // p159t3.AbstractC1905d
    /* renamed from: a */
    public InetSocketAddress mo1985a() {
        return this.f5120a.m1978b();
    }

    @Override // p159t3.AbstractC1905d
    /* renamed from: b */
    public String mo1986b() {
        String str = this.f5120a.f5081c.f5125a;
        return str.substring(str.lastIndexOf(32) + 1);
    }

    @Override // p159t3.AbstractC1905d
    /* renamed from: c */
    public InetSocketAddress mo1987c() {
        return this.f5120a.m1979c();
    }

    @Override // p159t3.AbstractC1905d
    /* renamed from: d */
    public InputStream mo1988d() {
        return this.f5120a.m1980d();
    }

    @Override // p159t3.AbstractC1905d
    /* renamed from: e */
    public C1903b mo1989e() {
        return new C1804y(this.f5120a.f5079a);
    }

    @Override // p159t3.AbstractC1905d
    /* renamed from: f */
    public String mo1990f() {
        return this.f5120a.f5082d;
    }

    @Override // p159t3.AbstractC1905d
    /* renamed from: g */
    public URI mo1991g() {
        return this.f5120a.f5083e;
    }

    @Override // p159t3.AbstractC1905d
    /* renamed from: h */
    public OutputStream mo1992h() {
        return this.f5120a.m1981e();
    }

    @Override // p159t3.AbstractC1905d
    /* renamed from: i */
    public C1903b mo1993i() {
        return this.f5120a.f5080b;
    }

    @Override // p159t3.AbstractC1905d
    /* renamed from: j */
    public void mo1994j(int i7, long j7) throws IOException {
        this.f5120a.m1982f(i7, j7);
    }
}
