package p018c0;

import java.io.InputStream;
import java.net.URL;
import p010b0.C0420f;
import p010b0.C0430p;
import p010b0.InterfaceC0426l;
import p010b0.InterfaceC0427m;
import p162u.C1966j;

/* compiled from: UrlLoader.java */
/* renamed from: c0.e */
/* loaded from: classes.dex */
public class C0503e implements InterfaceC0426l<URL, InputStream> {

    /* renamed from: a */
    public final InterfaceC0426l<C0420f, InputStream> f371a;

    /* compiled from: UrlLoader.java */
    /* renamed from: c0.e$a */
    public static class a implements InterfaceC0427m<URL, InputStream> {
        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<URL, InputStream> mo120b(C0430p c0430p) {
            return new C0503e(c0430p.m144b(C0420f.class, InputStream.class));
        }
    }

    public C0503e(InterfaceC0426l<C0420f, InputStream> interfaceC0426l) {
        this.f371a = interfaceC0426l;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a<InputStream> mo117a(URL url, int i7, int i8, C1966j c1966j) {
        return this.f371a.mo117a(new C0420f(url), i7, i8, c1966j);
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public /* bridge */ /* synthetic */ boolean mo118b(URL url) {
        return true;
    }
}
