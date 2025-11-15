package p065h5;

import org.fourthline.cling.model.ServiceReference;
import p073i5.C1157j;
import p073i5.InterfaceC1156i;
import p168u5.AbstractC1980a;

/* compiled from: HttpBuffersImpl.java */
/* renamed from: h5.e */
/* loaded from: classes.dex */
public class C1097e extends AbstractC1980a implements InterfaceC1096d {

    /* renamed from: e */
    public int f2262e = 16384;

    /* renamed from: f */
    public int f2263f = 6144;

    /* renamed from: g */
    public int f2264g = 32768;

    /* renamed from: h */
    public int f2265h = 6144;

    /* renamed from: i */
    public int f2266i = 1024;

    /* renamed from: j */
    public int f2267j = 1;

    /* renamed from: k */
    public int f2268k = 1;

    /* renamed from: l */
    public int f2269l = 1;

    /* renamed from: m */
    public int f2270m = 1;

    /* renamed from: n */
    public InterfaceC1156i f2271n;

    /* renamed from: o */
    public InterfaceC1156i f2272o;

    @Override // p065h5.InterfaceC1096d
    /* renamed from: F */
    public InterfaceC1156i mo891F() {
        return this.f2271n;
    }

    @Override // p168u5.AbstractC1980a
    public void doStart() {
        int i7 = this.f2268k;
        int i8 = this.f2263f;
        int i9 = this.f2267j;
        this.f2271n = C1157j.m1371a(i7, i8, i9, this.f2262e, i9, this.f2266i);
        int i10 = this.f2270m;
        int i11 = this.f2265h;
        int i12 = this.f2269l;
        this.f2272o = C1157j.m1371a(i10, i11, i12, this.f2264g, i12, this.f2266i);
        super.doStart();
    }

    @Override // p168u5.AbstractC1980a
    public void doStop() {
        this.f2271n = null;
        this.f2272o = null;
    }

    @Override // p065h5.InterfaceC1096d
    /* renamed from: t */
    public InterfaceC1156i mo895t() {
        return this.f2272o;
    }

    public String toString() {
        return this.f2271n + ServiceReference.DELIMITER + this.f2272o;
    }
}
