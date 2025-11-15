package p051g;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.io.File;
import java.util.Map;
import p036e0.C0905d;
import p036e0.C0915n;
import p076j0.InterfaceC1182b;
import p162u.C1966j;
import p162u.EnumC1959c;
import p162u.InterfaceC1968l;
import p183x.InterfaceC2057r;
import p190y.InterfaceC2086d;

/* compiled from: MDRay.java */
/* renamed from: g.k */
/* loaded from: classes.dex */
public class C1041k implements InterfaceC1968l, InterfaceC1182b {

    /* renamed from: a */
    public final /* synthetic */ int f1972a = 2;

    /* renamed from: b */
    public Object f1973b;

    /* renamed from: c */
    public Object f1974c;

    public C1041k(C1040j c1040j, C1040j c1040j2) {
        this.f1973b = c1040j;
        this.f1974c = c1040j2;
    }

    @Override // p162u.InterfaceC1960d
    /* renamed from: D */
    public boolean mo784D(Object obj, File file, C1966j c1966j) {
        return ((InterfaceC1968l) this.f1974c).mo784D(new C0905d(((BitmapDrawable) ((InterfaceC2057r) obj).get()).getBitmap(), (InterfaceC2086d) this.f1973b), file, c1966j);
    }

    @Override // p076j0.InterfaceC1182b
    /* renamed from: b */
    public InterfaceC2057r<BitmapDrawable> mo942b(InterfaceC2057r<Bitmap> interfaceC2057r) {
        return new C0915n((Resources) this.f1973b, (InterfaceC2086d) this.f1974c, interfaceC2057r.get());
    }

    @Override // p162u.InterfaceC1968l
    /* renamed from: s */
    public EnumC1959c mo821s(C1966j c1966j) {
        return ((InterfaceC1968l) this.f1974c).mo821s(c1966j);
    }

    public String toString() {
        switch (this.f1972a) {
            case 0:
                return "MDRay{, mDir=" + ((C1040j) this.f1974c) + ", mOrig=" + ((C1040j) this.f1973b) + '}';
            default:
                return super.toString();
        }
    }

    public C1041k(String str, Map map) {
        this.f1973b = str;
        this.f1974c = map;
    }

    public C1041k(InterfaceC2086d interfaceC2086d, InterfaceC1968l interfaceC1968l) {
        this.f1973b = interfaceC2086d;
        this.f1974c = interfaceC1968l;
    }

    public C1041k(Resources resources, InterfaceC2086d interfaceC2086d) {
        this.f1973b = resources;
        this.f1974c = interfaceC2086d;
    }
}
