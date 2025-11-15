package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {

    @Nullable
    public MapCollections<K, V> mCollections;

    public ArrayMap() {
    }

    private MapCollections<K, V> getCollection() {
        if (this.mCollections == null) {
            this.mCollections = new MapCollections<K, V>() { // from class: android.support.v4.util.ArrayMap.1
                @Override // android.support.v4.util.MapCollections
                public void colClear() {
                    ArrayMap.this.clear();
                }

                @Override // android.support.v4.util.MapCollections
                public Object colGetEntry(int i7, int i8) {
                    return ArrayMap.this.mArray[(i7 << 1) + i8];
                }

                @Override // android.support.v4.util.MapCollections
                public Map<K, V> colGetMap() {
                    return ArrayMap.this;
                }

                @Override // android.support.v4.util.MapCollections
                public int colGetSize() {
                    return ArrayMap.this.mSize;
                }

                @Override // android.support.v4.util.MapCollections
                public int colIndexOfKey(Object obj) {
                    return ArrayMap.this.indexOfKey(obj);
                }

                @Override // android.support.v4.util.MapCollections
                public int colIndexOfValue(Object obj) {
                    return ArrayMap.this.indexOfValue(obj);
                }

                @Override // android.support.v4.util.MapCollections
                public void colPut(K k7, V v6) {
                    ArrayMap.this.put(k7, v6);
                }

                @Override // android.support.v4.util.MapCollections
                public void colRemoveAt(int i7) {
                    ArrayMap.this.removeAt(i7);
                }

                @Override // android.support.v4.util.MapCollections
                public V colSetValue(int i7, V v6) {
                    return ArrayMap.this.setValueAt(i7, v6);
                }
            };
        }
        return this.mCollections;
    }

    public boolean containsAll(@NonNull Collection<?> collection) {
        return MapCollections.containsAllHelper(this, collection);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return getCollection().getEntrySet();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return getCollection().getKeySet();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(map.size() + this.mSize);
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean removeAll(@NonNull Collection<?> collection) {
        return MapCollections.removeAllHelper(this, collection);
    }

    public boolean retainAll(@NonNull Collection<?> collection) {
        return MapCollections.retainAllHelper(this, collection);
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return getCollection().getValues();
    }

    public ArrayMap(int i7) {
        super(i7);
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap) {
        super(simpleArrayMap);
    }
}
