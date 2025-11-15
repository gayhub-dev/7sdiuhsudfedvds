package p073i5;

import p043f.C0986c;
import p089k5.C1394c;
import p089k5.C1395d;

/* compiled from: AbstractBuffers.java */
/* renamed from: i5.b */
/* loaded from: classes.dex */
public abstract class AbstractC1149b implements InterfaceC1156i {

    /* renamed from: a */
    public final int f2532a;

    /* renamed from: b */
    public final int f2533b;

    /* renamed from: c */
    public final int f2534c;

    /* renamed from: d */
    public final int f2535d;

    /* renamed from: e */
    public final int f2536e;

    public AbstractC1149b(int i7, int i8, int i9, int i10, int i11) {
        this.f2532a = i7;
        this.f2533b = i8;
        this.f2534c = i9;
        this.f2535d = i10;
        this.f2536e = i11;
    }

    /* renamed from: d */
    public final boolean m1343d(InterfaceC1152e interfaceC1152e) {
        if (interfaceC1152e.mo1350a() == this.f2535d) {
            int iM950c = C0986c.m950c(this.f2534c);
            if (iM950c != 0) {
                if (iM950c == 1) {
                    return interfaceC1152e instanceof C1394c;
                }
                if (iM950c == 2) {
                    return interfaceC1152e instanceof C1395d;
                }
            } else if ((interfaceC1152e instanceof C1158k) && !(interfaceC1152e instanceof C1395d)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: e */
    public final boolean m1344e(InterfaceC1152e interfaceC1152e) {
        if (interfaceC1152e.mo1350a() == this.f2533b) {
            int iM950c = C0986c.m950c(this.f2532a);
            if (iM950c != 0) {
                if (iM950c == 1) {
                    return interfaceC1152e instanceof C1394c;
                }
                if (iM950c == 2) {
                    return interfaceC1152e instanceof C1395d;
                }
            } else if ((interfaceC1152e instanceof C1158k) && !(interfaceC1152e instanceof C1395d)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: f */
    public final InterfaceC1152e m1345f() {
        int iM950c = C0986c.m950c(this.f2534c);
        if (iM950c == 0) {
            return new C1158k(this.f2535d);
        }
        if (iM950c == 1) {
            return new C1394c(this.f2535d);
        }
        if (iM950c == 2) {
            return new C1395d(this.f2535d);
        }
        throw new IllegalStateException();
    }

    /* renamed from: g */
    public final InterfaceC1152e m1346g(int i7) {
        int iM950c = C0986c.m950c(this.f2536e);
        if (iM950c == 0) {
            return new C1158k(i7);
        }
        if (iM950c == 1) {
            return new C1394c(i7);
        }
        if (iM950c == 2) {
            return new C1395d(i7);
        }
        throw new IllegalStateException();
    }

    /* renamed from: h */
    public final InterfaceC1152e m1347h() {
        int iM950c = C0986c.m950c(this.f2532a);
        if (iM950c == 0) {
            return new C1158k(this.f2533b);
        }
        if (iM950c == 1) {
            return new C1394c(this.f2533b);
        }
        if (iM950c == 2) {
            return new C1395d(this.f2533b);
        }
        throw new IllegalStateException();
    }
}
