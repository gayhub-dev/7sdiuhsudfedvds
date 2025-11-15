package p036e0;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import p162u.C1966j;
import p162u.InterfaceC1967k;
import p183x.InterfaceC2057r;
import p190y.InterfaceC2086d;

/* compiled from: BitmapDrawableDecoder.java */
/* renamed from: e0.a */
/* loaded from: classes.dex */
public class C0902a<DataType> implements InterfaceC1967k<DataType, BitmapDrawable> {

    /* renamed from: a */
    public final InterfaceC1967k<DataType, Bitmap> f1623a;

    /* renamed from: b */
    public final Resources f1624b;

    /* renamed from: c */
    public final InterfaceC2086d f1625c;

    public C0902a(Resources resources, InterfaceC2086d interfaceC2086d, InterfaceC1967k<DataType, Bitmap> interfaceC1967k) {
        this.f1624b = resources;
        this.f1625c = interfaceC2086d;
        this.f1623a = interfaceC1967k;
    }

    @Override // p162u.InterfaceC1967k
    /* renamed from: a */
    public boolean mo819a(DataType datatype, C1966j c1966j) {
        return this.f1623a.mo819a(datatype, c1966j);
    }

    @Override // p162u.InterfaceC1967k
    /* renamed from: b */
    public InterfaceC2057r<BitmapDrawable> mo820b(DataType datatype, int i7, int i8, C1966j c1966j) {
        InterfaceC2057r<Bitmap> interfaceC2057rMo820b = this.f1623a.mo820b(datatype, i7, i8, c1966j);
        if (interfaceC2057rMo820b == null) {
            return null;
        }
        return new C0915n(this.f1624b, this.f1625c, interfaceC2057rMo820b.get());
    }
}
