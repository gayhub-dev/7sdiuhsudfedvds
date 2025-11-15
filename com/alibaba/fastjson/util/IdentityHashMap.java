package com.alibaba.fastjson.util;

import java.util.Arrays;

/* loaded from: classes.dex */
public class IdentityHashMap<K, V> {
    public static final int DEFAULT_SIZE = 8192;
    private final Entry<K, V>[] buckets;
    private final int indexMask;

    public static final class Entry<K, V> {
        public final int hashCode;
        public final K key;
        public final Entry<K, V> next;
        public V value;

        public Entry(K k7, V v6, int i7, Entry<K, V> entry) {
            this.key = k7;
            this.value = v6;
            this.next = entry;
            this.hashCode = i7;
        }
    }

    public IdentityHashMap() {
        this(8192);
    }

    public void clear() {
        Arrays.fill(this.buckets, (Object) null);
    }

    public Class findClass(String str) {
        int i7 = 0;
        while (true) {
            Entry<K, V>[] entryArr = this.buckets;
            if (i7 >= entryArr.length) {
                return null;
            }
            Entry<K, V> entry = entryArr[i7];
            if (entry != null) {
                for (Entry<K, V> entry2 = entry; entry2 != null; entry2 = entry2.next) {
                    K k7 = entry.key;
                    if (k7 instanceof Class) {
                        Class cls = (Class) k7;
                        if (cls.getName().equals(str)) {
                            return cls;
                        }
                    }
                }
            }
            i7++;
        }
    }

    public final V get(K k7) {
        for (Entry<K, V> entry = this.buckets[System.identityHashCode(k7) & this.indexMask]; entry != null; entry = entry.next) {
            if (k7 == entry.key) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean put(K k7, V v6) {
        int iIdentityHashCode = System.identityHashCode(k7);
        int i7 = this.indexMask & iIdentityHashCode;
        for (Entry<K, V> entry = this.buckets[i7]; entry != null; entry = entry.next) {
            if (k7 == entry.key) {
                entry.value = v6;
                return true;
            }
        }
        this.buckets[i7] = new Entry<>(k7, v6, iIdentityHashCode, this.buckets[i7]);
        return false;
    }

    public IdentityHashMap(int i7) {
        this.indexMask = i7 - 1;
        this.buckets = new Entry[i7];
    }
}
