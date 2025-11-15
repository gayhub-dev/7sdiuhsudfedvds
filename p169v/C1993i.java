package p169v;

import java.io.InputStream;
import p036e0.C0916o;
import p169v.InterfaceC1987c;
import p190y.InterfaceC2084b;

/* compiled from: InputStreamRewinder.java */
/* renamed from: v.i */
/* loaded from: classes.dex */
public final class C1993i implements InterfaceC1987c<InputStream> {

    /* renamed from: a */
    public final C0916o f5819a;

    /* compiled from: InputStreamRewinder.java */
    /* renamed from: v.i$a */
    public static final class a implements InterfaceC1987c.a<InputStream> {

        /* renamed from: a */
        public final InterfaceC2084b f5820a;

        public a(InterfaceC2084b interfaceC2084b) {
            this.f5820a = interfaceC2084b;
        }

        @Override // p169v.InterfaceC1987c.a
        /* renamed from: a */
        public Class<InputStream> mo1005a() {
            return InputStream.class;
        }

        @Override // p169v.InterfaceC1987c.a
        /* renamed from: b */
        public InterfaceC1987c<InputStream> mo1006b(InputStream inputStream) {
            return new C1993i(inputStream, this.f5820a);
        }
    }

    public C1993i(InputStream inputStream, InterfaceC2084b interfaceC2084b) {
        C0916o c0916o = new C0916o(inputStream, interfaceC2084b);
        this.f5819a = c0916o;
        c0916o.mark(5242880);
    }

    @Override // p169v.InterfaceC1987c
    /* renamed from: a */
    public InputStream mo1003a() {
        this.f5819a.reset();
        return this.f5819a;
    }

    @Override // p169v.InterfaceC1987c
    /* renamed from: b */
    public void mo1004b() {
        this.f5819a.m849b();
    }
}
