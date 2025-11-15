package p142r0;

import android.support.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: LruCache.java */
/* renamed from: r0.e */
/* loaded from: classes.dex */
public class C1820e<T, Y> {

    /* renamed from: b */
    public int f5294b;

    /* renamed from: a */
    public final LinkedHashMap<T, Y> f5293a = new LinkedHashMap<>(100, 0.75f, true);

    /* renamed from: c */
    public int f5295c = 0;

    public C1820e(int i7) {
        this.f5294b = i7;
    }

    @Nullable
    /* renamed from: a */
    public synchronized Y m2051a(T t6) {
        return this.f5293a.get(t6);
    }

    /* renamed from: b */
    public int mo2052b(Y y6) {
        return 1;
    }

    /* renamed from: c */
    public void mo136c(T t6, Y y6) {
    }

    /* renamed from: d */
    public synchronized Y m2053d(T t6, Y y6) {
        if (mo2052b(y6) >= this.f5294b) {
            mo136c(t6, y6);
            return null;
        }
        Y yPut = this.f5293a.put(t6, y6);
        if (y6 != null) {
            this.f5295c += mo2052b(y6);
        }
        if (yPut != null) {
            this.f5295c -= mo2052b(yPut);
        }
        m2054e(this.f5294b);
        return yPut;
    }

    /* renamed from: e */
    public synchronized void m2054e(int i7) {
        while (this.f5295c > i7) {
            Map.Entry<T, Y> next = this.f5293a.entrySet().iterator().next();
            Y value = next.getValue();
            this.f5295c -= mo2052b(value);
            T key = next.getKey();
            this.f5293a.remove(key);
            mo136c(key, value);
        }
    }
}
