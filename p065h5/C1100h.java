package p065h5;

import java.io.IOException;
import p009b.C0413b;

/* compiled from: HttpException.java */
/* renamed from: h5.h */
/* loaded from: classes.dex */
public class C1100h extends IOException {

    /* renamed from: e */
    public int f2282e;

    /* renamed from: f */
    public String f2283f;

    public C1100h(int i7) {
        this.f2282e = i7;
        this.f2283f = null;
    }

    /* renamed from: a */
    public String m1217a() {
        return this.f2283f;
    }

    /* renamed from: b */
    public int m1218b() {
        return this.f2282e;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("HttpException(");
        sbM112a.append(this.f2282e);
        sbM112a.append(",");
        sbM112a.append(this.f2283f);
        sbM112a.append(",");
        sbM112a.append(getCause());
        sbM112a.append(")");
        return sbM112a.toString();
    }

    public C1100h(int i7, String str) {
        this.f2282e = i7;
        this.f2283f = str;
    }

    public C1100h(int i7, String str, Throwable th) {
        this.f2282e = i7;
        this.f2283f = null;
        initCause(th);
    }
}
