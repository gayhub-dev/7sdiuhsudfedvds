package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class ArraySet<E> implements Collection<E>, Set<E> {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean DEBUG = false;
    private static final int[] INT = new int[0];
    private static final Object[] OBJECT = new Object[0];
    private static final String TAG = "ArraySet";

    @Nullable
    private static Object[] sBaseCache;
    private static int sBaseCacheSize;

    @Nullable
    private static Object[] sTwiceBaseCache;
    private static int sTwiceBaseCacheSize;
    public Object[] mArray;
    private MapCollections<E, E> mCollections;
    private int[] mHashes;
    public int mSize;

    public ArraySet() {
        this(0);
    }

    private void allocArrays(int i7) {
        if (i7 == 8) {
            synchronized (ArraySet.class) {
                Object[] objArr = sTwiceBaseCache;
                if (objArr != null) {
                    this.mArray = objArr;
                    sTwiceBaseCache = (Object[]) objArr[0];
                    this.mHashes = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    sTwiceBaseCacheSize--;
                    return;
                }
            }
        } else if (i7 == 4) {
            synchronized (ArraySet.class) {
                Object[] objArr2 = sBaseCache;
                if (objArr2 != null) {
                    this.mArray = objArr2;
                    sBaseCache = (Object[]) objArr2[0];
                    this.mHashes = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    sBaseCacheSize--;
                    return;
                }
            }
        }
        this.mHashes = new int[i7];
        this.mArray = new Object[i7];
    }

    private static void freeArrays(int[] iArr, Object[] objArr, int i7) {
        if (iArr.length == 8) {
            synchronized (ArraySet.class) {
                if (sTwiceBaseCacheSize < 10) {
                    objArr[0] = sTwiceBaseCache;
                    objArr[1] = iArr;
                    for (int i8 = i7 - 1; i8 >= 2; i8--) {
                        objArr[i8] = null;
                    }
                    sTwiceBaseCache = objArr;
                    sTwiceBaseCacheSize++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (ArraySet.class) {
                if (sBaseCacheSize < 10) {
                    objArr[0] = sBaseCache;
                    objArr[1] = iArr;
                    for (int i9 = i7 - 1; i9 >= 2; i9--) {
                        objArr[i9] = null;
                    }
                    sBaseCache = objArr;
                    sBaseCacheSize++;
                }
            }
        }
    }

    private MapCollections<E, E> getCollection() {
        if (this.mCollections == null) {
            this.mCollections = new MapCollections<E, E>() { // from class: android.support.v4.util.ArraySet.1
                @Override // android.support.v4.util.MapCollections
                public void colClear() {
                    ArraySet.this.clear();
                }

                @Override // android.support.v4.util.MapCollections
                public Object colGetEntry(int i7, int i8) {
                    return ArraySet.this.mArray[i7];
                }

                @Override // android.support.v4.util.MapCollections
                public Map<E, E> colGetMap() {
                    throw new UnsupportedOperationException("not a map");
                }

                @Override // android.support.v4.util.MapCollections
                public int colGetSize() {
                    return ArraySet.this.mSize;
                }

                @Override // android.support.v4.util.MapCollections
                public int colIndexOfKey(Object obj) {
                    return ArraySet.this.indexOf(obj);
                }

                @Override // android.support.v4.util.MapCollections
                public int colIndexOfValue(Object obj) {
                    return ArraySet.this.indexOf(obj);
                }

                @Override // android.support.v4.util.MapCollections
                public void colPut(E e7, E e8) {
                    ArraySet.this.add(e7);
                }

                @Override // android.support.v4.util.MapCollections
                public void colRemoveAt(int i7) {
                    ArraySet.this.removeAt(i7);
                }

                @Override // android.support.v4.util.MapCollections
                public E colSetValue(int i7, E e7) {
                    throw new UnsupportedOperationException("not a map");
                }
            };
        }
        return this.mCollections;
    }

    private int indexOf(Object obj, int i7) {
        int i8 = this.mSize;
        if (i8 == 0) {
            return -1;
        }
        int iBinarySearch = ContainerHelpers.binarySearch(this.mHashes, i8, i7);
        if (iBinarySearch < 0 || obj.equals(this.mArray[iBinarySearch])) {
            return iBinarySearch;
        }
        int i9 = iBinarySearch + 1;
        while (i9 < i8 && this.mHashes[i9] == i7) {
            if (obj.equals(this.mArray[i9])) {
                return i9;
            }
            i9++;
        }
        for (int i10 = iBinarySearch - 1; i10 >= 0 && this.mHashes[i10] == i7; i10--) {
            if (obj.equals(this.mArray[i10])) {
                return i10;
            }
        }
        return ~i9;
    }

    private int indexOfNull() {
        int i7 = this.mSize;
        if (i7 == 0) {
            return -1;
        }
        int iBinarySearch = ContainerHelpers.binarySearch(this.mHashes, i7, 0);
        if (iBinarySearch < 0 || this.mArray[iBinarySearch] == null) {
            return iBinarySearch;
        }
        int i8 = iBinarySearch + 1;
        while (i8 < i7 && this.mHashes[i8] == 0) {
            if (this.mArray[i8] == null) {
                return i8;
            }
            i8++;
        }
        for (int i9 = iBinarySearch - 1; i9 >= 0 && this.mHashes[i9] == 0; i9--) {
            if (this.mArray[i9] == null) {
                return i9;
            }
        }
        return ~i8;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(@Nullable E e7) {
        int i7;
        int iIndexOf;
        if (e7 == null) {
            iIndexOf = indexOfNull();
            i7 = 0;
        } else {
            int iHashCode = e7.hashCode();
            i7 = iHashCode;
            iIndexOf = indexOf(e7, iHashCode);
        }
        if (iIndexOf >= 0) {
            return false;
        }
        int i8 = ~iIndexOf;
        int i9 = this.mSize;
        int[] iArr = this.mHashes;
        if (i9 >= iArr.length) {
            int i10 = 4;
            if (i9 >= 8) {
                i10 = (i9 >> 1) + i9;
            } else if (i9 >= 4) {
                i10 = 8;
            }
            Object[] objArr = this.mArray;
            allocArrays(i10);
            int[] iArr2 = this.mHashes;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.mArray, 0, objArr.length);
            }
            freeArrays(iArr, objArr, this.mSize);
        }
        int i11 = this.mSize;
        if (i8 < i11) {
            int[] iArr3 = this.mHashes;
            int i12 = i8 + 1;
            System.arraycopy(iArr3, i8, iArr3, i12, i11 - i8);
            Object[] objArr2 = this.mArray;
            System.arraycopy(objArr2, i8, objArr2, i12, this.mSize - i8);
        }
        this.mHashes[i8] = i7;
        this.mArray[i8] = e7;
        this.mSize++;
        return true;
    }

    public void addAll(@NonNull ArraySet<? extends E> arraySet) {
        int i7 = arraySet.mSize;
        ensureCapacity(this.mSize + i7);
        if (this.mSize != 0) {
            for (int i8 = 0; i8 < i7; i8++) {
                add(arraySet.valueAt(i8));
            }
        } else if (i7 > 0) {
            System.arraycopy(arraySet.mHashes, 0, this.mHashes, 0, i7);
            System.arraycopy(arraySet.mArray, 0, this.mArray, 0, i7);
            this.mSize = i7;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void append(E e7) {
        int i7 = this.mSize;
        int iHashCode = e7 == null ? 0 : e7.hashCode();
        int[] iArr = this.mHashes;
        if (i7 >= iArr.length) {
            throw new IllegalStateException("Array is full");
        }
        if (i7 > 0 && iArr[i7 - 1] > iHashCode) {
            add(e7);
            return;
        }
        this.mSize = i7 + 1;
        iArr[i7] = iHashCode;
        this.mArray[i7] = e7;
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i7 = this.mSize;
        if (i7 != 0) {
            freeArrays(this.mHashes, this.mArray, i7);
            this.mHashes = INT;
            this.mArray = OBJECT;
            this.mSize = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(@Nullable Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(@NonNull Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public void ensureCapacity(int i7) {
        int[] iArr = this.mHashes;
        if (iArr.length < i7) {
            Object[] objArr = this.mArray;
            allocArrays(i7);
            int i8 = this.mSize;
            if (i8 > 0) {
                System.arraycopy(iArr, 0, this.mHashes, 0, i8);
                System.arraycopy(objArr, 0, this.mArray, 0, this.mSize);
            }
            freeArrays(iArr, objArr, this.mSize);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            for (int i7 = 0; i7 < this.mSize; i7++) {
                try {
                    if (!set.contains(valueAt(i7))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.mHashes;
        int i7 = this.mSize;
        int i8 = 0;
        for (int i9 = 0; i9 < i7; i9++) {
            i8 += iArr[i9];
        }
        return i8;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.mSize <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return getCollection().getKeySet().iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(@Nullable Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    public boolean removeAll(@NonNull ArraySet<? extends E> arraySet) {
        int i7 = arraySet.mSize;
        int i8 = this.mSize;
        for (int i9 = 0; i9 < i7; i9++) {
            remove(arraySet.valueAt(i9));
        }
        return i8 != this.mSize;
    }

    public E removeAt(int i7) {
        Object[] objArr = this.mArray;
        E e7 = (E) objArr[i7];
        int i8 = this.mSize;
        if (i8 <= 1) {
            freeArrays(this.mHashes, objArr, i8);
            this.mHashes = INT;
            this.mArray = OBJECT;
            this.mSize = 0;
        } else {
            int[] iArr = this.mHashes;
            if (iArr.length <= 8 || i8 >= iArr.length / 3) {
                int i9 = i8 - 1;
                this.mSize = i9;
                if (i7 < i9) {
                    int i10 = i7 + 1;
                    System.arraycopy(iArr, i10, iArr, i7, i9 - i7);
                    Object[] objArr2 = this.mArray;
                    System.arraycopy(objArr2, i10, objArr2, i7, this.mSize - i7);
                }
                this.mArray[this.mSize] = null;
            } else {
                allocArrays(i8 > 8 ? i8 + (i8 >> 1) : 8);
                this.mSize--;
                if (i7 > 0) {
                    System.arraycopy(iArr, 0, this.mHashes, 0, i7);
                    System.arraycopy(objArr, 0, this.mArray, 0, i7);
                }
                int i11 = this.mSize;
                if (i7 < i11) {
                    int i12 = i7 + 1;
                    System.arraycopy(iArr, i12, this.mHashes, i7, i11 - i7);
                    System.arraycopy(objArr, i12, this.mArray, i7, this.mSize - i7);
                }
            }
        }
        return e7;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(@NonNull Collection<?> collection) {
        boolean z6 = false;
        for (int i7 = this.mSize - 1; i7 >= 0; i7--) {
            if (!collection.contains(this.mArray[i7])) {
                removeAt(i7);
                z6 = true;
            }
        }
        return z6;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.mSize;
    }

    @Override // java.util.Collection, java.util.Set
    @NonNull
    public Object[] toArray() {
        int i7 = this.mSize;
        Object[] objArr = new Object[i7];
        System.arraycopy(this.mArray, 0, objArr, 0, i7);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.mSize * 14);
        sb.append('{');
        for (int i7 = 0; i7 < this.mSize; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            E eValueAt = valueAt(i7);
            if (eValueAt != this) {
                sb.append(eValueAt);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Nullable
    public E valueAt(int i7) {
        return (E) this.mArray[i7];
    }

    public ArraySet(int i7) {
        if (i7 == 0) {
            this.mHashes = INT;
            this.mArray = OBJECT;
        } else {
            allocArrays(i7);
        }
        this.mSize = 0;
    }

    @Override // java.util.Collection, java.util.Set
    @NonNull
    public <T> T[] toArray(@NonNull T[] tArr) {
        if (tArr.length < this.mSize) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.mSize));
        }
        System.arraycopy(this.mArray, 0, tArr, 0, this.mSize);
        int length = tArr.length;
        int i7 = this.mSize;
        if (length > i7) {
            tArr[i7] = null;
        }
        return tArr;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(@NonNull Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArraySet(@Nullable ArraySet<E> arraySet) {
        this();
        if (arraySet != 0) {
            addAll((ArraySet) arraySet);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(@NonNull Collection<? extends E> collection) {
        ensureCapacity(collection.size() + this.mSize);
        Iterator<? extends E> it = collection.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    public int indexOf(@Nullable Object obj) {
        return obj == null ? indexOfNull() : indexOf(obj, obj.hashCode());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArraySet(@Nullable Collection<E> collection) {
        this();
        if (collection != 0) {
            addAll(collection);
        }
    }
}
