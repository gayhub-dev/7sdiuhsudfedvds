package p161t5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: MultiMap.java */
/* renamed from: t5.l */
/* loaded from: classes.dex */
public class ConcurrentMapC1920l<K> implements ConcurrentMap<K, Object>, Serializable {
    private static final long serialVersionUID = -6878723138353851005L;

    /* renamed from: e */
    public Map<K, Object> f5650e;

    /* renamed from: f */
    public ConcurrentMap<K, Object> f5651f;

    public ConcurrentMapC1920l() {
        this.f5650e = new HashMap();
    }

    /* renamed from: b */
    public void m2231b(K k7, Object obj) {
        Object obj2 = this.f5650e.get(k7);
        Object objM2222b = C1918j.m2222b(obj2, obj);
        if (obj2 != objM2222b) {
            this.f5650e.put(k7, objM2222b);
        }
    }

    @Override // java.util.Map
    public void clear() {
        this.f5650e.clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.f5650e.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.f5650e.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, Object>> entrySet() {
        return this.f5650e.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.f5650e.equals(obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        Object obj2 = this.f5650e.get(obj);
        int iM2228x = C1918j.m2228x(obj2);
        if (iM2228x != 0) {
            return iM2228x != 1 ? C1918j.m2226k(obj2, true) : C1918j.m2225j(obj2, 0);
        }
        return null;
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.f5650e.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.f5650e.isEmpty();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return this.f5650e.keySet();
    }

    @Override // java.util.Map
    public Object put(K k7, Object obj) {
        return this.f5650e.put(k7, C1918j.m2222b(null, obj));
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends Object> map) {
        if (!(map instanceof ConcurrentMapC1920l)) {
            this.f5650e.putAll(map);
            return;
        }
        for (Map.Entry<? extends K, ? extends Object> entry : map.entrySet()) {
            Map<K, Object> map2 = this.f5650e;
            K key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                value = null;
            } else if (value instanceof List) {
                value = new ArrayList((List) value);
            }
            map2.put(key, value);
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public Object putIfAbsent(K k7, Object obj) {
        ConcurrentMap<K, Object> concurrentMap = this.f5651f;
        if (concurrentMap != null) {
            return concurrentMap.putIfAbsent(k7, obj);
        }
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return this.f5650e.remove(obj);
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean replace(K k7, Object obj, Object obj2) {
        ConcurrentMap<K, Object> concurrentMap = this.f5651f;
        if (concurrentMap != null) {
            return concurrentMap.replace(k7, obj, obj2);
        }
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public int size() {
        return this.f5650e.size();
    }

    public String toString() {
        Object obj = this.f5651f;
        if (obj == null) {
            obj = this.f5650e;
        }
        return obj.toString();
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return this.f5650e.values();
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean remove(Object obj, Object obj2) {
        ConcurrentMap<K, Object> concurrentMap = this.f5651f;
        if (concurrentMap != null) {
            return concurrentMap.remove(obj, obj2);
        }
        throw new UnsupportedOperationException();
    }

    public ConcurrentMapC1920l(ConcurrentMapC1920l<K> concurrentMapC1920l) {
        if (concurrentMapC1920l.f5651f != null) {
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(concurrentMapC1920l.f5651f);
            this.f5651f = concurrentHashMap;
            this.f5650e = concurrentHashMap;
            return;
        }
        this.f5650e = new HashMap(concurrentMapC1920l.f5650e);
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public Object replace(K k7, Object obj) {
        ConcurrentMap<K, Object> concurrentMap = this.f5651f;
        if (concurrentMap != null) {
            return concurrentMap.replace(k7, obj);
        }
        throw new UnsupportedOperationException();
    }

    public ConcurrentMapC1920l(int i7) {
        this.f5650e = new HashMap(i7);
    }
}
