package p009b;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.HashMap;
import p009b.C0414c;

/* compiled from: FastSafeIterableMap.java */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: b.a */
/* loaded from: classes.dex */
public class C0412a<K, V> extends C0414c<K, V> {

    /* renamed from: i */
    public HashMap<K, C0414c.d<K, V>> f176i = new HashMap<>();

    @Override // p009b.C0414c
    /* renamed from: a */
    public C0414c.d<K, V> mo109a(K k7) {
        return this.f176i.get(k7);
    }

    @Override // p009b.C0414c
    /* renamed from: c */
    public V mo110c(@NonNull K k7, @NonNull V v6) {
        C0414c.d<K, V> dVar = this.f176i.get(k7);
        if (dVar != null) {
            return dVar.f182f;
        }
        this.f176i.put(k7, m113b(k7, v6));
        return null;
    }

    public boolean contains(K k7) {
        return this.f176i.containsKey(k7);
    }

    @Override // p009b.C0414c
    /* renamed from: d */
    public V mo111d(@NonNull K k7) {
        V v6 = (V) super.mo111d(k7);
        this.f176i.remove(k7);
        return v6;
    }
}
