package p190y;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p190y.InterfaceC2093k;

/* compiled from: GroupedLinkedMap.java */
/* renamed from: y.g */
/* loaded from: classes.dex */
public class C2089g<K extends InterfaceC2093k, V> {

    /* renamed from: a */
    public final a<K, V> f6181a = new a<>(null);

    /* renamed from: b */
    public final Map<K, a<K, V>> f6182b = new HashMap();

    /* compiled from: GroupedLinkedMap.java */
    /* renamed from: y.g$a */
    public static class a<K, V> {

        /* renamed from: a */
        public final K f6183a;

        /* renamed from: b */
        public List<V> f6184b;

        /* renamed from: c */
        public a<K, V> f6185c;

        /* renamed from: d */
        public a<K, V> f6186d;

        public a() {
            this(null);
        }

        @Nullable
        /* renamed from: a */
        public V m2524a() {
            List<V> list = this.f6184b;
            int size = list != null ? list.size() : 0;
            if (size > 0) {
                return this.f6184b.remove(size - 1);
            }
            return null;
        }

        public a(K k7) {
            this.f6186d = this;
            this.f6185c = this;
            this.f6183a = k7;
        }
    }

    @Nullable
    /* renamed from: a */
    public V m2521a(K k7) {
        a<K, V> aVar = this.f6182b.get(k7);
        if (aVar == null) {
            aVar = new a<>(k7);
            this.f6182b.put(k7, aVar);
        } else {
            k7.mo2529a();
        }
        a<K, V> aVar2 = aVar.f6186d;
        aVar2.f6185c = aVar.f6185c;
        aVar.f6185c.f6186d = aVar2;
        a<K, V> aVar3 = this.f6181a;
        aVar.f6186d = aVar3;
        a<K, V> aVar4 = aVar3.f6185c;
        aVar.f6185c = aVar4;
        aVar4.f6186d = aVar;
        aVar.f6186d.f6185c = aVar;
        return aVar.m2524a();
    }

    /* renamed from: b */
    public void m2522b(K k7, V v6) {
        a<K, V> aVar = this.f6182b.get(k7);
        if (aVar == null) {
            aVar = new a<>(k7);
            a<K, V> aVar2 = aVar.f6186d;
            aVar2.f6185c = aVar.f6185c;
            aVar.f6185c.f6186d = aVar2;
            a<K, V> aVar3 = this.f6181a;
            aVar.f6186d = aVar3.f6186d;
            aVar.f6185c = aVar3;
            aVar3.f6186d = aVar;
            aVar.f6186d.f6185c = aVar;
            this.f6182b.put(k7, aVar);
        } else {
            k7.mo2529a();
        }
        if (aVar.f6184b == null) {
            aVar.f6184b = new ArrayList();
        }
        aVar.f6184b.add(v6);
    }

    @Nullable
    /* renamed from: c */
    public V m2523c() {
        for (a aVar = this.f6181a.f6186d; !aVar.equals(this.f6181a); aVar = aVar.f6186d) {
            V v6 = (V) aVar.m2524a();
            if (v6 != null) {
                return v6;
            }
            a<K, V> aVar2 = aVar.f6186d;
            aVar2.f6185c = aVar.f6185c;
            aVar.f6185c.f6186d = aVar2;
            this.f6182b.remove(aVar.f6183a);
            ((InterfaceC2093k) aVar.f6183a).mo2529a();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        boolean z6 = false;
        for (a aVar = this.f6181a.f6185c; !aVar.equals(this.f6181a); aVar = aVar.f6185c) {
            z6 = true;
            sb.append('{');
            sb.append(aVar.f6183a);
            sb.append(':');
            List<V> list = aVar.f6184b;
            sb.append(list != null ? list.size() : 0);
            sb.append("}, ");
        }
        if (z6) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        return sb.toString();
    }
}
