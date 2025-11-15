package p075j;

import android.content.Context;
import p035e.C0888a;

/* compiled from: MDAbsPlugin.java */
/* renamed from: j.b */
/* loaded from: classes.dex */
public abstract class AbstractC1174b {

    /* renamed from: a */
    public boolean f2563a;

    /* renamed from: b */
    public long f2564b;

    /* renamed from: a */
    public abstract void mo807a(int i7, int i8);

    /* renamed from: b */
    public abstract void mo1374b();

    /* renamed from: c */
    public abstract void mo1375c(Context context);

    /* renamed from: d */
    public abstract void mo1376d(int i7, int i8, int i9, C0888a c0888a);

    /* renamed from: e */
    public final void m1377e(Context context) {
        long id = Thread.currentThread().getId();
        if (id != this.f2564b) {
            this.f2564b = id;
            this.f2563a = false;
        }
        if (this.f2563a) {
            return;
        }
        mo1375c(context);
        this.f2563a = true;
    }
}
