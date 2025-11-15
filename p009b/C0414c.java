package p009b;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: SafeIterableMap.java */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: b.c */
/* loaded from: classes.dex */
public class C0414c<K, V> implements Iterable<Map.Entry<K, V>> {

    /* renamed from: e */
    public d<K, V> f177e;

    /* renamed from: f */
    public d<K, V> f178f;

    /* renamed from: g */
    public WeakHashMap<g<K, V>, Boolean> f179g = new WeakHashMap<>();

    /* renamed from: h */
    public int f180h = 0;

    /* compiled from: SafeIterableMap.java */
    /* renamed from: b.c$b */
    public static class b<K, V> extends f<K, V> {
        public b(d<K, V> dVar, d<K, V> dVar2) {
            super(dVar, dVar2);
        }

        @Override // p009b.C0414c.f
        /* renamed from: b */
        public d<K, V> mo114b(d<K, V> dVar) {
            return dVar.f184h;
        }

        @Override // p009b.C0414c.f
        /* renamed from: c */
        public d<K, V> mo115c(d<K, V> dVar) {
            return dVar.f183g;
        }
    }

    /* compiled from: SafeIterableMap.java */
    /* renamed from: b.c$c */
    public static class c<K, V> extends f<K, V> {
        public c(d<K, V> dVar, d<K, V> dVar2) {
            super(dVar, dVar2);
        }

        @Override // p009b.C0414c.f
        /* renamed from: b */
        public d<K, V> mo114b(d<K, V> dVar) {
            return dVar.f183g;
        }

        @Override // p009b.C0414c.f
        /* renamed from: c */
        public d<K, V> mo115c(d<K, V> dVar) {
            return dVar.f184h;
        }
    }

    /* compiled from: SafeIterableMap.java */
    /* renamed from: b.c$d */
    public static class d<K, V> implements Map.Entry<K, V> {

        /* renamed from: e */
        @NonNull
        public final K f181e;

        /* renamed from: f */
        @NonNull
        public final V f182f;

        /* renamed from: g */
        public d<K, V> f183g;

        /* renamed from: h */
        public d<K, V> f184h;

        public d(@NonNull K k7, @NonNull V v6) {
            this.f181e = k7;
            this.f182f = v6;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof d)) {
                return false;
            }
            d dVar = (d) obj;
            return this.f181e.equals(dVar.f181e) && this.f182f.equals(dVar.f182f);
        }

        @Override // java.util.Map.Entry
        @NonNull
        public K getKey() {
            return this.f181e;
        }

        @Override // java.util.Map.Entry
        @NonNull
        public V getValue() {
            return this.f182f;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v6) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f181e + "=" + this.f182f;
        }
    }

    /* compiled from: SafeIterableMap.java */
    /* renamed from: b.c$e */
    public class e implements Iterator<Map.Entry<K, V>>, g<K, V> {

        /* renamed from: e */
        public d<K, V> f185e;

        /* renamed from: f */
        public boolean f186f = true;

        public e(a aVar) {
        }

        @Override // p009b.C0414c.g
        /* renamed from: a */
        public void mo116a(@NonNull d<K, V> dVar) {
            d<K, V> dVar2 = this.f185e;
            if (dVar == dVar2) {
                d<K, V> dVar3 = dVar2.f184h;
                this.f185e = dVar3;
                this.f186f = dVar3 == null;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f186f) {
                return C0414c.this.f177e != null;
            }
            d<K, V> dVar = this.f185e;
            return (dVar == null || dVar.f183g == null) ? false : true;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (this.f186f) {
                this.f186f = false;
                this.f185e = C0414c.this.f177e;
            } else {
                d<K, V> dVar = this.f185e;
                this.f185e = dVar != null ? dVar.f183g : null;
            }
            return this.f185e;
        }
    }

    /* compiled from: SafeIterableMap.java */
    /* renamed from: b.c$f */
    public static abstract class f<K, V> implements Iterator<Map.Entry<K, V>>, g<K, V> {

        /* renamed from: e */
        public d<K, V> f188e;

        /* renamed from: f */
        public d<K, V> f189f;

        public f(d<K, V> dVar, d<K, V> dVar2) {
            this.f188e = dVar2;
            this.f189f = dVar;
        }

        @Override // p009b.C0414c.g
        /* renamed from: a */
        public void mo116a(@NonNull d<K, V> dVar) {
            d<K, V> dVarMo115c = null;
            if (this.f188e == dVar && dVar == this.f189f) {
                this.f189f = null;
                this.f188e = null;
            }
            d<K, V> dVar2 = this.f188e;
            if (dVar2 == dVar) {
                this.f188e = mo114b(dVar2);
            }
            d<K, V> dVar3 = this.f189f;
            if (dVar3 == dVar) {
                d<K, V> dVar4 = this.f188e;
                if (dVar3 != dVar4 && dVar4 != null) {
                    dVarMo115c = mo115c(dVar3);
                }
                this.f189f = dVarMo115c;
            }
        }

        /* renamed from: b */
        public abstract d<K, V> mo114b(d<K, V> dVar);

        /* renamed from: c */
        public abstract d<K, V> mo115c(d<K, V> dVar);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f189f != null;
        }

        @Override // java.util.Iterator
        public Object next() {
            d<K, V> dVar = this.f189f;
            d<K, V> dVar2 = this.f188e;
            this.f189f = (dVar == dVar2 || dVar2 == null) ? null : mo115c(dVar);
            return dVar;
        }
    }

    /* compiled from: SafeIterableMap.java */
    /* renamed from: b.c$g */
    public interface g<K, V> {
        /* renamed from: a */
        void mo116a(@NonNull d<K, V> dVar);
    }

    /* renamed from: a */
    public d<K, V> mo109a(K k7) {
        d<K, V> dVar = this.f177e;
        while (dVar != null && !dVar.f181e.equals(k7)) {
            dVar = dVar.f183g;
        }
        return dVar;
    }

    /* renamed from: b */
    public d<K, V> m113b(@NonNull K k7, @NonNull V v6) {
        d<K, V> dVar = new d<>(k7, v6);
        this.f180h++;
        d<K, V> dVar2 = this.f178f;
        if (dVar2 == null) {
            this.f177e = dVar;
            this.f178f = dVar;
            return dVar;
        }
        dVar2.f183g = dVar;
        dVar.f184h = dVar2;
        this.f178f = dVar;
        return dVar;
    }

    /* renamed from: c */
    public V mo110c(@NonNull K k7, @NonNull V v6) {
        d<K, V> dVarMo109a = mo109a(k7);
        if (dVarMo109a != null) {
            return dVarMo109a.f182f;
        }
        m113b(k7, v6);
        return null;
    }

    /* renamed from: d */
    public V mo111d(@NonNull K k7) {
        d<K, V> dVarMo109a = mo109a(k7);
        if (dVarMo109a == null) {
            return null;
        }
        this.f180h--;
        if (!this.f179g.isEmpty()) {
            Iterator<g<K, V>> it = this.f179g.keySet().iterator();
            while (it.hasNext()) {
                it.next().mo116a(dVarMo109a);
            }
        }
        d<K, V> dVar = dVarMo109a.f184h;
        if (dVar != null) {
            dVar.f183g = dVarMo109a.f183g;
        } else {
            this.f177e = dVarMo109a.f183g;
        }
        d<K, V> dVar2 = dVarMo109a.f183g;
        if (dVar2 != null) {
            dVar2.f184h = dVar;
        } else {
            this.f178f = dVar;
        }
        dVarMo109a.f183g = null;
        dVarMo109a.f184h = null;
        return dVarMo109a.f182f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
    
        if (r3.hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0050, code lost:
    
        if (((p009b.C0414c.f) r7).hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0053, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:?, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != r6) goto L4
            return r0
        L4:
            boolean r1 = r7 instanceof p009b.C0414c
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            b.c r7 = (p009b.C0414c) r7
            int r1 = r6.f180h
            int r3 = r7.f180h
            if (r1 == r3) goto L13
            return r2
        L13:
            java.util.Iterator r1 = r6.iterator()
            java.util.Iterator r7 = r7.iterator()
        L1b:
            r3 = r1
            b.c$f r3 = (p009b.C0414c.f) r3
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L44
            r4 = r7
            b.c$f r4 = (p009b.C0414c.f) r4
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L44
            java.lang.Object r3 = r3.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r4.next()
            if (r3 != 0) goto L3b
            if (r4 != 0) goto L43
        L3b:
            if (r3 == 0) goto L1b
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L1b
        L43:
            return r2
        L44:
            boolean r1 = r3.hasNext()
            if (r1 != 0) goto L53
            b.c$f r7 = (p009b.C0414c.f) r7
            boolean r7 = r7.hasNext()
            if (r7 != 0) goto L53
            goto L54
        L53:
            r0 = 0
        L54:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p009b.C0414c.equals(java.lang.Object):boolean");
    }

    @Override // java.lang.Iterable
    @NonNull
    public Iterator<Map.Entry<K, V>> iterator() {
        b bVar = new b(this.f177e, this.f178f);
        this.f179g.put(bVar, Boolean.FALSE);
        return bVar;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (true) {
            f fVar = (f) it;
            if (!fVar.hasNext()) {
                sbM112a.append("]");
                return sbM112a.toString();
            }
            sbM112a.append(((Map.Entry) fVar.next()).toString());
            if (fVar.hasNext()) {
                sbM112a.append(", ");
            }
        }
    }
}
