package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes.dex */
public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int i7) {
        if (i7 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = i7;
        this.map = new LinkedHashMap<>(0, 0.75f, true);
    }

    private int safeSizeOf(K k7, V v6) {
        int iSizeOf = sizeOf(k7, v6);
        if (iSizeOf >= 0) {
            return iSizeOf;
        }
        throw new IllegalStateException("Negative size: " + k7 + "=" + v6);
    }

    @Nullable
    public V create(@NonNull K k7) {
        return null;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public void entryRemoved(boolean z6, @NonNull K k7, @NonNull V v6, @Nullable V v7) {
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    @Nullable
    public final V get(@NonNull K k7) {
        V v6;
        Objects.requireNonNull(k7, "key == null");
        synchronized (this) {
            V v7 = this.map.get(k7);
            if (v7 != null) {
                this.hitCount++;
                return v7;
            }
            this.missCount++;
            V vCreate = create(k7);
            if (vCreate == null) {
                return null;
            }
            synchronized (this) {
                this.createCount++;
                v6 = (V) this.map.put(k7, vCreate);
                if (v6 != null) {
                    this.map.put(k7, v6);
                } else {
                    this.size += safeSizeOf(k7, vCreate);
                }
            }
            if (v6 != null) {
                entryRemoved(false, k7, vCreate, v6);
                return v6;
            }
            trimToSize(this.maxSize);
            return vCreate;
        }
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    @Nullable
    public final V put(@NonNull K k7, @NonNull V v6) {
        V vPut;
        if (k7 == null || v6 == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.putCount++;
            this.size += safeSizeOf(k7, v6);
            vPut = this.map.put(k7, v6);
            if (vPut != null) {
                this.size -= safeSizeOf(k7, vPut);
            }
        }
        if (vPut != null) {
            entryRemoved(false, k7, vPut, v6);
        }
        trimToSize(this.maxSize);
        return vPut;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    @Nullable
    public final V remove(@NonNull K k7) {
        V vRemove;
        Objects.requireNonNull(k7, "key == null");
        synchronized (this) {
            vRemove = this.map.remove(k7);
            if (vRemove != null) {
                this.size -= safeSizeOf(k7, vRemove);
            }
        }
        if (vRemove != null) {
            entryRemoved(false, k7, vRemove, null);
        }
        return vRemove;
    }

    public void resize(int i7) {
        if (i7 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized (this) {
            this.maxSize = i7;
        }
        trimToSize(i7);
    }

    public final synchronized int size() {
        return this.size;
    }

    public int sizeOf(@NonNull K k7, @NonNull V v6) {
        return 1;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public final synchronized String toString() {
        int i7;
        int i8;
        i7 = this.hitCount;
        i8 = this.missCount + i7;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(i8 != 0 ? (i7 * 100) / i8 : 0));
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0070, code lost:
    
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void trimToSize(int r5) {
        /*
            r4 = this;
        L0:
            monitor-enter(r4)
            int r0 = r4.size     // Catch: java.lang.Throwable -> L71
            if (r0 < 0) goto L52
            java.util.LinkedHashMap<K, V> r0 = r4.map     // Catch: java.lang.Throwable -> L71
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L11
            int r0 = r4.size     // Catch: java.lang.Throwable -> L71
            if (r0 != 0) goto L52
        L11:
            int r0 = r4.size     // Catch: java.lang.Throwable -> L71
            if (r0 <= r5) goto L50
            java.util.LinkedHashMap<K, V> r0 = r4.map     // Catch: java.lang.Throwable -> L71
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L1e
            goto L50
        L1e:
            java.util.LinkedHashMap<K, V> r0 = r4.map     // Catch: java.lang.Throwable -> L71
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L71
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L71
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L71
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: java.lang.Throwable -> L71
            java.lang.Object r1 = r0.getKey()     // Catch: java.lang.Throwable -> L71
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Throwable -> L71
            java.util.LinkedHashMap<K, V> r2 = r4.map     // Catch: java.lang.Throwable -> L71
            r2.remove(r1)     // Catch: java.lang.Throwable -> L71
            int r2 = r4.size     // Catch: java.lang.Throwable -> L71
            int r3 = r4.safeSizeOf(r1, r0)     // Catch: java.lang.Throwable -> L71
            int r2 = r2 - r3
            r4.size = r2     // Catch: java.lang.Throwable -> L71
            int r2 = r4.evictionCount     // Catch: java.lang.Throwable -> L71
            r3 = 1
            int r2 = r2 + r3
            r4.evictionCount = r2     // Catch: java.lang.Throwable -> L71
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L71
            r2 = 0
            r4.entryRemoved(r3, r1, r0, r2)
            goto L0
        L50:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L71
            return
        L52:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L71
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L71
            r0.<init>()     // Catch: java.lang.Throwable -> L71
            java.lang.Class r1 = r4.getClass()     // Catch: java.lang.Throwable -> L71
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L71
            r0.append(r1)     // Catch: java.lang.Throwable -> L71
            java.lang.String r1 = ".sizeOf() is reporting inconsistent results!"
            r0.append(r1)     // Catch: java.lang.Throwable -> L71
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L71
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L71
            throw r5     // Catch: java.lang.Throwable -> L71
        L71:
            r5 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L71
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.trimToSize(int):void");
    }
}
