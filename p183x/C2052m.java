package p183x;

import android.os.Looper;
import java.util.Objects;
import p009b.C0413b;
import p142r0.C1823h;
import p162u.InterfaceC1964h;
import p197z.C2141h;

/* compiled from: EngineResource.java */
/* renamed from: x.m */
/* loaded from: classes.dex */
public class C2052m<Z> implements InterfaceC2057r<Z> {

    /* renamed from: e */
    public final boolean f6102e;

    /* renamed from: f */
    public a f6103f;

    /* renamed from: g */
    public InterfaceC1964h f6104g;

    /* renamed from: h */
    public int f6105h;

    /* renamed from: i */
    public boolean f6106i;

    /* renamed from: j */
    public final InterfaceC2057r<Z> f6107j;

    /* compiled from: EngineResource.java */
    /* renamed from: x.m$a */
    public interface a {
    }

    public C2052m(InterfaceC2057r<Z> interfaceC2057r, boolean z6) {
        Objects.requireNonNull(interfaceC2057r, "Argument must not be null");
        this.f6107j = interfaceC2057r;
        this.f6102e = z6;
    }

    /* renamed from: a */
    public void m2437a() {
        if (this.f6106i) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
        if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new IllegalThreadStateException("Must call acquire on the main thread");
        }
        this.f6105h++;
    }

    @Override // p183x.InterfaceC2057r
    /* renamed from: b */
    public Class<Z> mo824b() {
        return this.f6107j.mo824b();
    }

    /* renamed from: c */
    public void m2438c() {
        if (this.f6105h <= 0) {
            throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
        }
        if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new IllegalThreadStateException("Must call release on the main thread");
        }
        int i7 = this.f6105h - 1;
        this.f6105h = i7;
        if (i7 == 0) {
            a aVar = this.f6103f;
            InterfaceC1964h interfaceC1964h = this.f6104g;
            C2048i c2048i = (C2048i) aVar;
            Objects.requireNonNull(c2048i);
            C1823h.m2057a();
            c2048i.f6050e.remove(interfaceC1964h);
            if (this.f6102e) {
                ((C2141h) c2048i.f6048c).m2053d(interfaceC1964h, this);
            } else {
                c2048i.f6051f.m2447a(this);
            }
        }
    }

    @Override // p183x.InterfaceC2057r
    public Z get() {
        return this.f6107j.get();
    }

    @Override // p183x.InterfaceC2057r
    public int getSize() {
        return this.f6107j.getSize();
    }

    @Override // p183x.InterfaceC2057r
    public void recycle() {
        if (this.f6105h > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        }
        if (this.f6106i) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
        this.f6106i = true;
        this.f6107j.recycle();
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("EngineResource{isCacheable=");
        sbM112a.append(this.f6102e);
        sbM112a.append(", listener=");
        sbM112a.append(this.f6103f);
        sbM112a.append(", key=");
        sbM112a.append(this.f6104g);
        sbM112a.append(", acquired=");
        sbM112a.append(this.f6105h);
        sbM112a.append(", isRecycled=");
        sbM112a.append(this.f6106i);
        sbM112a.append(", resource=");
        sbM112a.append(this.f6107j);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
