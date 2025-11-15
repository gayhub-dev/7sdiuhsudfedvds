package p018c0;

import android.support.annotation.Nullable;
import java.io.InputStream;
import java.util.Objects;
import p010b0.C0420f;
import p010b0.C0425k;
import p010b0.C0430p;
import p010b0.InterfaceC0426l;
import p010b0.InterfaceC0427m;
import p162u.C1965i;
import p162u.C1966j;
import p169v.C1992h;

/* compiled from: HttpGlideUrlLoader.java */
/* renamed from: c0.a */
/* loaded from: classes.dex */
public class C0499a implements InterfaceC0426l<C0420f, InputStream> {

    /* renamed from: b */
    public static final C1965i<Integer> f362b = C1965i.m2295a("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 2500);

    /* renamed from: a */
    @Nullable
    public final C0425k<C0420f, C0420f> f363a;

    /* compiled from: HttpGlideUrlLoader.java */
    /* renamed from: c0.a$a */
    public static class a implements InterfaceC0427m<C0420f, InputStream> {

        /* renamed from: a */
        public final C0425k<C0420f, C0420f> f364a = new C0425k<>(500);

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<C0420f, InputStream> mo120b(C0430p c0430p) {
            return new C0499a(this.f364a);
        }
    }

    public C0499a(C0425k<C0420f, C0420f> c0425k) {
        this.f363a = c0425k;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a<InputStream> mo117a(C0420f c0420f, int i7, int i8, C1966j c1966j) {
        C0420f c0420f2 = c0420f;
        C0425k<C0420f, C0420f> c0425k = this.f363a;
        if (c0425k != null) {
            C0425k.b<C0420f> bVarM137a = C0425k.b.m137a(c0420f2, 0, 0);
            C0420f c0420fM2051a = c0425k.f226a.m2051a(bVarM137a);
            bVarM137a.m138b();
            C0420f c0420f3 = c0420fM2051a;
            if (c0420f3 == null) {
                C0425k<C0420f, C0420f> c0425k2 = this.f363a;
                Objects.requireNonNull(c0425k2);
                c0425k2.f226a.m2053d(C0425k.b.m137a(c0420f2, 0, 0), c0420f2);
            } else {
                c0420f2 = c0420f3;
            }
        }
        return new InterfaceC0426l.a<>(c0420f2, new C1992h(c0420f2, ((Integer) c1966j.m2296c(f362b)).intValue()));
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public /* bridge */ /* synthetic */ boolean mo118b(C0420f c0420f) {
        return true;
    }
}
