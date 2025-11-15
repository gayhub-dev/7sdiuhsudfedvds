package p115o;

import android.content.Context;
import p051g.AbstractC1039i;
import p051g.C1037g;
import p067i.AbstractC1126a;
import p067i.C1127b;
import p067i.C1131f;
import p067i.C1133h;
import p075j.AbstractC1174b;
import p075j.C1178f;

/* compiled from: CubeProjection.java */
/* renamed from: o.b */
/* loaded from: classes.dex */
public class C1565b extends AbstractC1564a {

    /* renamed from: a */
    public final /* synthetic */ int f4691a;

    /* renamed from: b */
    public AbstractC1126a f4692b;

    public C1565b(int i7) {
        this.f4691a = i7;
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: a */
    public boolean mo1588a(Context context) {
        return true;
    }

    @Override // p115o.AbstractC1564a
    /* renamed from: c */
    public AbstractC1174b mo1810c(C1037g c1037g) {
        switch (this.f4691a) {
        }
        return new C1178f(c1037g);
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: e */
    public void mo1591e(Context context) {
        switch (this.f4691a) {
            case 0:
                C1127b c1127b = new C1127b();
                this.f4692b = c1127b;
                C1131f.m1294a(context, c1127b);
                break;
            default:
                C1133h c1133h = new C1133h();
                this.f4692b = c1133h;
                C1131f.m1294a(context, c1133h);
                break;
        }
    }

    @Override // p115o.InterfaceC1567d
    /* renamed from: f */
    public AbstractC1126a mo1812f() {
        switch (this.f4691a) {
        }
        return this.f4692b;
    }

    @Override // p115o.InterfaceC1567d
    /* renamed from: g */
    public AbstractC1039i mo1813g() {
        switch (this.f4691a) {
        }
        return AbstractC1039i.f1969a;
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: h */
    public void mo1592h(Context context) {
    }
}
