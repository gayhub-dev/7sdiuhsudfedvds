package p190y;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import p009b.C0413b;
import p142r0.C1823h;

/* compiled from: LruBitmapPool.java */
/* renamed from: y.i */
/* loaded from: classes.dex */
public class C2091i implements InterfaceC2086d {

    /* renamed from: j */
    public static final Bitmap.Config f6196j = Bitmap.Config.ARGB_8888;

    /* renamed from: a */
    public final InterfaceC2092j f6197a;

    /* renamed from: b */
    public final Set<Bitmap.Config> f6198b;

    /* renamed from: c */
    public final a f6199c;

    /* renamed from: d */
    public int f6200d;

    /* renamed from: e */
    public int f6201e;

    /* renamed from: f */
    public int f6202f;

    /* renamed from: g */
    public int f6203g;

    /* renamed from: h */
    public int f6204h;

    /* renamed from: i */
    public int f6205i;

    /* compiled from: LruBitmapPool.java */
    /* renamed from: y.i$a */
    public interface a {
    }

    /* compiled from: LruBitmapPool.java */
    /* renamed from: y.i$b */
    public static class b implements a {
    }

    public C2091i(int i7) {
        C2094l c2094l = new C2094l();
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(Bitmap.Config.values()));
        int i8 = Build.VERSION.SDK_INT;
        hashSet.add(null);
        if (i8 >= 26) {
            hashSet.remove(Bitmap.Config.HARDWARE);
        }
        Set<Bitmap.Config> setUnmodifiableSet = Collections.unmodifiableSet(hashSet);
        this.f6200d = i7;
        this.f6197a = c2094l;
        this.f6198b = setUnmodifiableSet;
        this.f6199c = new b();
    }

    @Override // p190y.InterfaceC2086d
    @SuppressLint({"InlinedApi"})
    /* renamed from: a */
    public void mo2516a(int i7) {
        Log.isLoggable("LruBitmapPool", 3);
        if (i7 >= 40) {
            Log.isLoggable("LruBitmapPool", 3);
            m2534i(0);
        } else if (i7 >= 20) {
            m2534i(this.f6200d / 2);
        }
    }

    @Override // p190y.InterfaceC2086d
    /* renamed from: b */
    public void mo2517b() {
        Log.isLoggable("LruBitmapPool", 3);
        m2534i(0);
    }

    @Override // p190y.InterfaceC2086d
    @NonNull
    /* renamed from: c */
    public Bitmap mo2518c(int i7, int i8, Bitmap.Config config) {
        Bitmap bitmapM2533h = m2533h(i7, i8, config);
        return bitmapM2533h == null ? Bitmap.createBitmap(i7, i8, config) : bitmapM2533h;
    }

    @Override // p190y.InterfaceC2086d
    @NonNull
    /* renamed from: d */
    public Bitmap mo2519d(int i7, int i8, Bitmap.Config config) {
        Bitmap bitmapM2533h = m2533h(i7, i8, config);
        if (bitmapM2533h == null) {
            return Bitmap.createBitmap(i7, i8, config);
        }
        bitmapM2533h.eraseColor(0);
        return bitmapM2533h;
    }

    @Override // p190y.InterfaceC2086d
    /* renamed from: e */
    public synchronized void mo2520e(Bitmap bitmap) {
        try {
            if (bitmap == null) {
                throw new NullPointerException("Bitmap must not be null");
            }
            if (bitmap.isRecycled()) {
                throw new IllegalStateException("Cannot pool recycled bitmap");
            }
            if (bitmap.isMutable()) {
                Objects.requireNonNull((C2094l) this.f6197a);
                if (C1823h.m2060d(bitmap) <= this.f6200d && this.f6198b.contains(bitmap.getConfig())) {
                    Objects.requireNonNull((C2094l) this.f6197a);
                    int iM2060d = C1823h.m2060d(bitmap);
                    ((C2094l) this.f6197a).m2540f(bitmap);
                    Objects.requireNonNull(this.f6199c);
                    this.f6204h++;
                    this.f6201e += iM2060d;
                    if (Log.isLoggable("LruBitmapPool", 2)) {
                        ((C2094l) this.f6197a).m2539e(bitmap);
                    }
                    m2531f();
                    m2534i(this.f6200d);
                    return;
                }
            }
            if (Log.isLoggable("LruBitmapPool", 2)) {
                ((C2094l) this.f6197a).m2539e(bitmap);
                bitmap.isMutable();
                this.f6198b.contains(bitmap.getConfig());
            }
            bitmap.recycle();
        } catch (Throwable th) {
            throw th;
        }
    }

    /* renamed from: f */
    public final void m2531f() {
        if (Log.isLoggable("LruBitmapPool", 2)) {
            m2532g();
        }
    }

    /* renamed from: g */
    public final void m2532g() {
        StringBuilder sbM112a = C0413b.m112a("Hits=");
        sbM112a.append(this.f6202f);
        sbM112a.append(", misses=");
        sbM112a.append(this.f6203g);
        sbM112a.append(", puts=");
        sbM112a.append(this.f6204h);
        sbM112a.append(", evictions=");
        sbM112a.append(this.f6205i);
        sbM112a.append(", currentSize=");
        sbM112a.append(this.f6201e);
        sbM112a.append(", maxSize=");
        sbM112a.append(this.f6200d);
        sbM112a.append("\nStrategy=");
        sbM112a.append(this.f6197a);
    }

    @Nullable
    /* renamed from: h */
    public final synchronized Bitmap m2533h(int i7, int i8, Bitmap.Config config) {
        Bitmap bitmapM2537b;
        if (Build.VERSION.SDK_INT >= 26 && config == Bitmap.Config.HARDWARE) {
            throw new IllegalArgumentException("Cannot create a mutable Bitmap with config: " + config + ". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
        }
        bitmapM2537b = ((C2094l) this.f6197a).m2537b(i7, i8, config != null ? config : f6196j);
        if (bitmapM2537b == null) {
            if (Log.isLoggable("LruBitmapPool", 3)) {
                Objects.requireNonNull((C2094l) this.f6197a);
                C2094l.m2535c(C1823h.m2059c(i7, i8, config), config);
            }
            this.f6203g++;
        } else {
            this.f6202f++;
            int i9 = this.f6201e;
            Objects.requireNonNull((C2094l) this.f6197a);
            this.f6201e = i9 - C1823h.m2060d(bitmapM2537b);
            Objects.requireNonNull(this.f6199c);
            bitmapM2537b.setHasAlpha(true);
            bitmapM2537b.setPremultiplied(true);
        }
        if (Log.isLoggable("LruBitmapPool", 2)) {
            Objects.requireNonNull((C2094l) this.f6197a);
            C2094l.m2535c(C1823h.m2059c(i7, i8, config), config);
        }
        m2531f();
        return bitmapM2537b;
    }

    /* renamed from: i */
    public final synchronized void m2534i(int i7) {
        while (this.f6201e > i7) {
            C2094l c2094l = (C2094l) this.f6197a;
            Bitmap bitmapM2523c = c2094l.f6211b.m2523c();
            if (bitmapM2523c != null) {
                c2094l.m2536a(Integer.valueOf(C1823h.m2060d(bitmapM2523c)), bitmapM2523c);
            }
            if (bitmapM2523c == null) {
                if (Log.isLoggable("LruBitmapPool", 5)) {
                    m2532g();
                }
                this.f6201e = 0;
                return;
            }
            Objects.requireNonNull(this.f6199c);
            int i8 = this.f6201e;
            Objects.requireNonNull((C2094l) this.f6197a);
            this.f6201e = i8 - C1823h.m2060d(bitmapM2523c);
            this.f6205i++;
            if (Log.isLoggable("LruBitmapPool", 3)) {
                ((C2094l) this.f6197a).m2539e(bitmapM2523c);
            }
            m2531f();
            bitmapM2523c.recycle();
        }
    }
}
