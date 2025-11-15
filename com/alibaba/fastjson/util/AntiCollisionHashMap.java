package com.alibaba.fastjson.util;

import android.support.constraint.motion.C0079a;
import android.support.v7.widget.ActivityChooserView;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

/* JADX WARN: Unexpected interfaces in signature: [java.io.Serializable] */
/* loaded from: classes.dex */
public class AntiCollisionHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable {
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public static final int KEY = 16777619;
    public static final int MAXIMUM_CAPACITY = 1073741824;
    public static final int M_MASK = -2023358765;
    public static final int SEED = -2128831035;
    private static final long serialVersionUID = 362498820763181265L;
    private transient Set<Map.Entry<K, V>> entrySet;
    public volatile transient Set<K> keySet;
    public final float loadFactor;
    public volatile transient int modCount;
    public final int random;
    public transient int size;
    public transient Entry<K, V>[] table;
    public int threshold;
    public volatile transient Collection<V> values;

    public static class Entry<K, V> implements Map.Entry<K, V> {
        public final int hash;
        public final K key;
        public Entry<K, V> next;
        public V value;

        public Entry(int i7, K k7, V v6, Entry<K, V> entry) {
            this.value = v6;
            this.next = entry;
            this.key = k7;
            this.hash = i7;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            K key = getKey();
            Object key2 = entry.getKey();
            if (key == key2 || (key != null && key.equals(key2))) {
                V value = getValue();
                Object value2 = entry.getValue();
                if (value == value2) {
                    return true;
                }
                if (value != null && value.equals(value2)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public final K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public final V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            K k7 = this.key;
            int iHashCode = k7 == null ? 0 : k7.hashCode();
            V v6 = this.value;
            return iHashCode ^ (v6 != null ? v6.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public final V setValue(V v6) {
            V v7 = this.value;
            this.value = v6;
            return v7;
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    public final class EntryIterator extends AntiCollisionHashMap<K, V>.HashIterator<Map.Entry<K, V>> {
        private EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    public final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            AntiCollisionHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Entry<K, V> entry2 = AntiCollisionHashMap.this.getEntry(entry.getKey());
            return entry2 != null && entry2.equals(entry);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return AntiCollisionHashMap.this.newEntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return AntiCollisionHashMap.this.removeMapping(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return AntiCollisionHashMap.this.size;
        }
    }

    public abstract class HashIterator<E> implements Iterator<E> {
        public Entry<K, V> current;
        public int expectedModCount;
        public int index;
        public Entry<K, V> next;

        public HashIterator() {
            Entry<K, V> entry;
            this.expectedModCount = AntiCollisionHashMap.this.modCount;
            if (AntiCollisionHashMap.this.size > 0) {
                Entry<K, V>[] entryArr = AntiCollisionHashMap.this.table;
                do {
                    int i7 = this.index;
                    if (i7 >= entryArr.length) {
                        return;
                    }
                    this.index = i7 + 1;
                    entry = entryArr[i7];
                    this.next = entry;
                } while (entry == null);
            }
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.next != null;
        }

        public final Entry<K, V> nextEntry() {
            Entry<K, V> entry;
            if (AntiCollisionHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            Entry<K, V> entry2 = this.next;
            if (entry2 == null) {
                throw new NoSuchElementException();
            }
            Entry<K, V> entry3 = entry2.next;
            this.next = entry3;
            if (entry3 == null) {
                Entry<K, V>[] entryArr = AntiCollisionHashMap.this.table;
                do {
                    int i7 = this.index;
                    if (i7 >= entryArr.length) {
                        break;
                    }
                    this.index = i7 + 1;
                    entry = entryArr[i7];
                    this.next = entry;
                } while (entry == null);
            }
            this.current = entry2;
            return entry2;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.current == null) {
                throw new IllegalStateException();
            }
            if (AntiCollisionHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            K k7 = this.current.key;
            this.current = null;
            AntiCollisionHashMap.this.removeEntryForKey(k7);
            this.expectedModCount = AntiCollisionHashMap.this.modCount;
        }
    }

    public final class KeyIterator extends AntiCollisionHashMap<K, V>.HashIterator<K> {
        private KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public K next() {
            return nextEntry().getKey();
        }
    }

    public final class KeySet extends AbstractSet<K> {
        private KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            AntiCollisionHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return AntiCollisionHashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return AntiCollisionHashMap.this.newKeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return AntiCollisionHashMap.this.removeEntryForKey(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return AntiCollisionHashMap.this.size;
        }
    }

    public final class ValueIterator extends AntiCollisionHashMap<K, V>.HashIterator<V> {
        private ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public V next() {
            return nextEntry().value;
        }
    }

    public final class Values extends AbstractCollection<V> {
        private Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            AntiCollisionHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return AntiCollisionHashMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return AntiCollisionHashMap.this.newValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return AntiCollisionHashMap.this.size;
        }
    }

    public AntiCollisionHashMap(int i7, float f7) {
        this.keySet = null;
        this.values = null;
        this.random = new Random().nextInt(99999);
        this.entrySet = null;
        if (i7 < 0) {
            throw new IllegalArgumentException(C0079a.m93a("Illegal initial capacity: ", i7));
        }
        i7 = i7 > 1073741824 ? 1073741824 : i7;
        if (f7 <= 0.0f || Float.isNaN(f7)) {
            throw new IllegalArgumentException("Illegal load factor: " + f7);
        }
        int i8 = 1;
        while (i8 < i7) {
            i8 <<= 1;
        }
        this.loadFactor = f7;
        this.threshold = (int) (i8 * f7);
        this.table = new Entry[i8];
        init();
    }

    private boolean containsNullValue() {
        for (Entry<K, V> entry : this.table) {
            for (; entry != null; entry = entry.next) {
                if (entry.value == null) {
                    return true;
                }
            }
        }
        return false;
    }

    private Set<Map.Entry<K, V>> entrySet0() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet();
        this.entrySet = entrySet;
        return entrySet;
    }

    private V getForNullKey() {
        for (Entry<K, V> entry = this.table[0]; entry != null; entry = entry.next) {
            if (entry.key == null) {
                return entry.value;
            }
        }
        return null;
    }

    public static int hash(int i7) {
        int i8 = i7 * i7;
        int i9 = i8 ^ ((i8 >>> 20) ^ (i8 >>> 12));
        return (i9 >>> 4) ^ ((i9 >>> 7) ^ i9);
    }

    private int hashString(String str) {
        int iCharAt = this.random * SEED;
        for (int i7 = 0; i7 < str.length(); i7++) {
            iCharAt = (iCharAt * KEY) ^ str.charAt(i7);
        }
        return ((iCharAt >> 1) ^ iCharAt) & M_MASK;
    }

    public static int indexFor(int i7, int i8) {
        return i7 & (i8 - 1);
    }

    private void putAllForCreate(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            putForCreate(entry.getKey(), entry.getValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void putForCreate(K k7, V v6) {
        K k8;
        int iHash = k7 == 0 ? 0 : k7 instanceof String ? hash(hashString((String) k7)) : hash(k7.hashCode());
        int iIndexFor = indexFor(iHash, this.table.length);
        for (Entry<K, V> entry = this.table[iIndexFor]; entry != null; entry = entry.next) {
            if (entry.hash == iHash && ((k8 = entry.key) == k7 || (k7 != 0 && k7.equals(k8)))) {
                entry.value = v6;
                return;
            }
        }
        createEntry(iHash, k7, v6, iIndexFor);
    }

    private V putForNullKey(V v6) {
        for (Entry<K, V> entry = this.table[0]; entry != null; entry = entry.next) {
            if (entry.key == null) {
                V v7 = entry.value;
                entry.value = v6;
                return v7;
            }
        }
        this.modCount++;
        addEntry(0, null, v6, 0);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.table = new Entry[objectInputStream.readInt()];
        init();
        int i7 = objectInputStream.readInt();
        for (int i8 = 0; i8 < i7; i8++) {
            putForCreate(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        Iterator<Map.Entry<K, V>> it = this.size > 0 ? entrySet0().iterator() : null;
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.table.length);
        objectOutputStream.writeInt(this.size);
        if (it != null) {
            while (it.hasNext()) {
                Map.Entry<K, V> next = it.next();
                objectOutputStream.writeObject(next.getKey());
                objectOutputStream.writeObject(next.getValue());
            }
        }
    }

    public void addEntry(int i7, K k7, V v6, int i8) {
        Entry<K, V>[] entryArr = this.table;
        entryArr[i8] = new Entry<>(i7, k7, v6, entryArr[i8]);
        int i9 = this.size;
        this.size = i9 + 1;
        if (i9 >= this.threshold) {
            resize(this.table.length * 2);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.modCount++;
        Entry<K, V>[] entryArr = this.table;
        for (int i7 = 0; i7 < entryArr.length; i7++) {
            entryArr[i7] = null;
        }
        this.size = 0;
    }

    @Override // java.util.AbstractMap
    public Object clone() {
        AntiCollisionHashMap antiCollisionHashMap;
        try {
            antiCollisionHashMap = (AntiCollisionHashMap) super.clone();
        } catch (CloneNotSupportedException unused) {
            antiCollisionHashMap = null;
        }
        antiCollisionHashMap.table = new Entry[this.table.length];
        antiCollisionHashMap.entrySet = null;
        antiCollisionHashMap.modCount = 0;
        antiCollisionHashMap.size = 0;
        antiCollisionHashMap.init();
        antiCollisionHashMap.putAllForCreate(this);
        return antiCollisionHashMap;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return getEntry(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        if (obj == null) {
            return containsNullValue();
        }
        for (Entry<K, V> entry : this.table) {
            for (; entry != null; entry = entry.next) {
                if (obj.equals(entry.value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void createEntry(int i7, K k7, V v6, int i8) {
        Entry<K, V>[] entryArr = this.table;
        entryArr[i8] = new Entry<>(i7, k7, v6, entryArr[i8]);
        this.size++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet0();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        K k7;
        if (obj == null) {
            return getForNullKey();
        }
        int iHash = obj instanceof String ? hash(hashString((String) obj)) : hash(obj.hashCode());
        Entry<K, V>[] entryArr = this.table;
        for (Entry<K, V> entry = entryArr[indexFor(iHash, entryArr.length)]; entry != null; entry = entry.next) {
            if (entry.hash == iHash && ((k7 = entry.key) == obj || obj.equals(k7))) {
                return entry.value;
            }
        }
        return null;
    }

    public final Entry<K, V> getEntry(Object obj) {
        K k7;
        int iHash = obj == null ? 0 : obj instanceof String ? hash(hashString((String) obj)) : hash(obj.hashCode());
        Entry<K, V>[] entryArr = this.table;
        for (Entry<K, V> entry = entryArr[indexFor(iHash, entryArr.length)]; entry != null; entry = entry.next) {
            if (entry.hash == iHash && ((k7 = entry.key) == obj || (obj != null && obj.equals(k7)))) {
                return entry;
            }
        }
        return null;
    }

    public void init() {
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet();
        this.keySet = keySet;
        return keySet;
    }

    public Iterator<Map.Entry<K, V>> newEntryIterator() {
        return new EntryIterator();
    }

    public Iterator<K> newKeyIterator() {
        return new KeyIterator();
    }

    public Iterator<V> newValueIterator() {
        return new ValueIterator();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k7, V v6) {
        K k8;
        if (k7 == 0) {
            return putForNullKey(v6);
        }
        int iHash = k7 instanceof String ? hash(hashString((String) k7)) : hash(k7.hashCode());
        int iIndexFor = indexFor(iHash, this.table.length);
        for (Entry<K, V> entry = this.table[iIndexFor]; entry != null; entry = entry.next) {
            if (entry.hash == iHash && ((k8 = entry.key) == k7 || k7.equals(k8))) {
                V v7 = entry.value;
                entry.value = v6;
                return v7;
            }
        }
        this.modCount++;
        addEntry(iHash, k7, v6, iIndexFor);
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        int size = map.size();
        if (size == 0) {
            return;
        }
        if (size > this.threshold) {
            int i7 = (int) ((size / this.loadFactor) + 1.0f);
            if (i7 > 1073741824) {
                i7 = 1073741824;
            }
            int length = this.table.length;
            while (length < i7) {
                length <<= 1;
            }
            if (length > this.table.length) {
                resize(length);
            }
        }
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        Entry<K, V> entryRemoveEntryForKey = removeEntryForKey(obj);
        if (entryRemoveEntryForKey == null) {
            return null;
        }
        return entryRemoveEntryForKey.value;
    }

    public final Entry<K, V> removeEntryForKey(Object obj) {
        K k7;
        int iHash = obj == null ? 0 : obj instanceof String ? hash(hashString((String) obj)) : hash(obj.hashCode());
        int iIndexFor = indexFor(iHash, this.table.length);
        Entry<K, V> entry = this.table[iIndexFor];
        Entry<K, V> entry2 = entry;
        while (entry != null) {
            Entry<K, V> entry3 = entry.next;
            if (entry.hash == iHash && ((k7 = entry.key) == obj || (obj != null && obj.equals(k7)))) {
                this.modCount++;
                this.size--;
                if (entry2 == entry) {
                    this.table[iIndexFor] = entry3;
                } else {
                    entry2.next = entry3;
                }
                return entry;
            }
            entry2 = entry;
            entry = entry3;
        }
        return entry;
    }

    public final Entry<K, V> removeMapping(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return null;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        int iHash = key == null ? 0 : key instanceof String ? hash(hashString((String) key)) : hash(key.hashCode());
        int iIndexFor = indexFor(iHash, this.table.length);
        Entry<K, V> entry2 = this.table[iIndexFor];
        Entry<K, V> entry3 = entry2;
        while (entry2 != null) {
            Entry<K, V> entry4 = entry2.next;
            if (entry2.hash == iHash && entry2.equals(entry)) {
                this.modCount++;
                this.size--;
                if (entry3 == entry2) {
                    this.table[iIndexFor] = entry4;
                } else {
                    entry3.next = entry4;
                }
                return entry2;
            }
            entry3 = entry2;
            entry2 = entry4;
        }
        return entry2;
    }

    public void resize(int i7) {
        if (this.table.length == 1073741824) {
            this.threshold = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            return;
        }
        Entry<K, V>[] entryArr = new Entry[i7];
        transfer(entryArr);
        this.table = entryArr;
        this.threshold = (int) (i7 * this.loadFactor);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    public void transfer(Entry[] entryArr) {
        Entry<K, V>[] entryArr2 = this.table;
        int length = entryArr.length;
        for (int i7 = 0; i7 < entryArr2.length; i7++) {
            Entry<K, V> entry = entryArr2[i7];
            if (entry != null) {
                entryArr2[i7] = null;
                while (true) {
                    Entry<K, V> entry2 = entry.next;
                    int iIndexFor = indexFor(entry.hash, length);
                    entry.next = entryArr[iIndexFor];
                    entryArr[iIndexFor] = entry;
                    if (entry2 == null) {
                        break;
                    } else {
                        entry = entry2;
                    }
                }
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Values values = new Values();
        this.values = values;
        return values;
    }

    public AntiCollisionHashMap(int i7) {
        this(i7, 0.75f);
    }

    public AntiCollisionHashMap() {
        this.keySet = null;
        this.values = null;
        this.random = new Random().nextInt(99999);
        this.entrySet = null;
        this.loadFactor = 0.75f;
        this.threshold = 12;
        this.table = new Entry[16];
        init();
    }

    public AntiCollisionHashMap(Map<? extends K, ? extends V> map) {
        this(Math.max(((int) (map.size() / 0.75f)) + 1, 16), 0.75f);
        putAllForCreate(map);
    }
}
