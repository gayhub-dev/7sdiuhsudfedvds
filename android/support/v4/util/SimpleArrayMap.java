package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ConcurrentModificationException;
import java.util.Map;

/* loaded from: classes.dex */
public class SimpleArrayMap<K, V> {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayMap";

    @Nullable
    public static Object[] mBaseCache;
    public static int mBaseCacheSize;

    @Nullable
    public static Object[] mTwiceBaseCache;
    public static int mTwiceBaseCacheSize;
    public Object[] mArray;
    public int[] mHashes;
    public int mSize;

    public SimpleArrayMap() {
        this.mHashes = ContainerHelpers.EMPTY_INTS;
        this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        this.mSize = 0;
    }

    private void allocArrays(int i7) {
        if (i7 == 8) {
            synchronized (ArrayMap.class) {
                Object[] objArr = mTwiceBaseCache;
                if (objArr != null) {
                    this.mArray = objArr;
                    mTwiceBaseCache = (Object[]) objArr[0];
                    this.mHashes = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    mTwiceBaseCacheSize--;
                    return;
                }
            }
        } else if (i7 == 4) {
            synchronized (ArrayMap.class) {
                Object[] objArr2 = mBaseCache;
                if (objArr2 != null) {
                    this.mArray = objArr2;
                    mBaseCache = (Object[]) objArr2[0];
                    this.mHashes = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    mBaseCacheSize--;
                    return;
                }
            }
        }
        this.mHashes = new int[i7];
        this.mArray = new Object[i7 << 1];
    }

    private static int binarySearchHashes(int[] iArr, int i7, int i8) {
        try {
            return ContainerHelpers.binarySearch(iArr, i7, i8);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    private static void freeArrays(int[] iArr, Object[] objArr, int i7) {
        if (iArr.length == 8) {
            synchronized (ArrayMap.class) {
                if (mTwiceBaseCacheSize < 10) {
                    objArr[0] = mTwiceBaseCache;
                    objArr[1] = iArr;
                    for (int i8 = (i7 << 1) - 1; i8 >= 2; i8--) {
                        objArr[i8] = null;
                    }
                    mTwiceBaseCache = objArr;
                    mTwiceBaseCacheSize++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (ArrayMap.class) {
                if (mBaseCacheSize < 10) {
                    objArr[0] = mBaseCache;
                    objArr[1] = iArr;
                    for (int i9 = (i7 << 1) - 1; i9 >= 2; i9--) {
                        objArr[i9] = null;
                    }
                    mBaseCache = objArr;
                    mBaseCacheSize++;
                }
            }
        }
    }

    public void clear() {
        int i7 = this.mSize;
        if (i7 > 0) {
            int[] iArr = this.mHashes;
            Object[] objArr = this.mArray;
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
            this.mSize = 0;
            freeArrays(iArr, objArr, i7);
        }
        if (this.mSize > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(@Nullable Object obj) {
        return indexOfKey(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return indexOfValue(obj) >= 0;
    }

    public void ensureCapacity(int i7) {
        int i8 = this.mSize;
        int[] iArr = this.mHashes;
        if (iArr.length < i7) {
            Object[] objArr = this.mArray;
            allocArrays(i7);
            if (this.mSize > 0) {
                System.arraycopy(iArr, 0, this.mHashes, 0, i8);
                System.arraycopy(objArr, 0, this.mArray, 0, i8 << 1);
            }
            freeArrays(iArr, objArr, i8);
        }
        if (this.mSize != i8) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SimpleArrayMap) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
            if (size() != simpleArrayMap.size()) {
                return false;
            }
            for (int i7 = 0; i7 < this.mSize; i7++) {
                try {
                    K kKeyAt = keyAt(i7);
                    V vValueAt = valueAt(i7);
                    Object obj2 = simpleArrayMap.get(kKeyAt);
                    if (vValueAt == null) {
                        if (obj2 != null || !simpleArrayMap.containsKey(kKeyAt)) {
                            return false;
                        }
                    } else if (!vValueAt.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i8 = 0; i8 < this.mSize; i8++) {
                try {
                    K kKeyAt2 = keyAt(i8);
                    V vValueAt2 = valueAt(i8);
                    Object obj3 = map.get(kKeyAt2);
                    if (vValueAt2 == null) {
                        if (obj3 != null || !map.containsKey(kKeyAt2)) {
                            return false;
                        }
                    } else if (!vValueAt2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    @Nullable
    public V get(Object obj) {
        int iIndexOfKey = indexOfKey(obj);
        if (iIndexOfKey >= 0) {
            return (V) this.mArray[(iIndexOfKey << 1) + 1];
        }
        return null;
    }

    public int hashCode() {
        int[] iArr = this.mHashes;
        Object[] objArr = this.mArray;
        int i7 = this.mSize;
        int i8 = 1;
        int i9 = 0;
        int iHashCode = 0;
        while (i9 < i7) {
            Object obj = objArr[i8];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i9];
            i9++;
            i8 += 2;
        }
        return iHashCode;
    }

    public int indexOf(Object obj, int i7) {
        int i8 = this.mSize;
        if (i8 == 0) {
            return -1;
        }
        int iBinarySearchHashes = binarySearchHashes(this.mHashes, i8, i7);
        if (iBinarySearchHashes < 0 || obj.equals(this.mArray[iBinarySearchHashes << 1])) {
            return iBinarySearchHashes;
        }
        int i9 = iBinarySearchHashes + 1;
        while (i9 < i8 && this.mHashes[i9] == i7) {
            if (obj.equals(this.mArray[i9 << 1])) {
                return i9;
            }
            i9++;
        }
        for (int i10 = iBinarySearchHashes - 1; i10 >= 0 && this.mHashes[i10] == i7; i10--) {
            if (obj.equals(this.mArray[i10 << 1])) {
                return i10;
            }
        }
        return ~i9;
    }

    public int indexOfKey(@Nullable Object obj) {
        return obj == null ? indexOfNull() : indexOf(obj, obj.hashCode());
    }

    public int indexOfNull() {
        int i7 = this.mSize;
        if (i7 == 0) {
            return -1;
        }
        int iBinarySearchHashes = binarySearchHashes(this.mHashes, i7, 0);
        if (iBinarySearchHashes < 0 || this.mArray[iBinarySearchHashes << 1] == null) {
            return iBinarySearchHashes;
        }
        int i8 = iBinarySearchHashes + 1;
        while (i8 < i7 && this.mHashes[i8] == 0) {
            if (this.mArray[i8 << 1] == null) {
                return i8;
            }
            i8++;
        }
        for (int i9 = iBinarySearchHashes - 1; i9 >= 0 && this.mHashes[i9] == 0; i9--) {
            if (this.mArray[i9 << 1] == null) {
                return i9;
            }
        }
        return ~i8;
    }

    public int indexOfValue(Object obj) {
        int i7 = this.mSize * 2;
        Object[] objArr = this.mArray;
        if (obj == null) {
            for (int i8 = 1; i8 < i7; i8 += 2) {
                if (objArr[i8] == null) {
                    return i8 >> 1;
                }
            }
            return -1;
        }
        for (int i9 = 1; i9 < i7; i9 += 2) {
            if (obj.equals(objArr[i9])) {
                return i9 >> 1;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.mSize <= 0;
    }

    public K keyAt(int i7) {
        return (K) this.mArray[i7 << 1];
    }

    @Nullable
    public V put(K k7, V v6) {
        int i7;
        int iIndexOf;
        int i8 = this.mSize;
        if (k7 == null) {
            iIndexOf = indexOfNull();
            i7 = 0;
        } else {
            int iHashCode = k7.hashCode();
            i7 = iHashCode;
            iIndexOf = indexOf(k7, iHashCode);
        }
        if (iIndexOf >= 0) {
            int i9 = (iIndexOf << 1) + 1;
            Object[] objArr = this.mArray;
            V v7 = (V) objArr[i9];
            objArr[i9] = v6;
            return v7;
        }
        int i10 = ~iIndexOf;
        int[] iArr = this.mHashes;
        if (i8 >= iArr.length) {
            int i11 = 4;
            if (i8 >= 8) {
                i11 = (i8 >> 1) + i8;
            } else if (i8 >= 4) {
                i11 = 8;
            }
            Object[] objArr2 = this.mArray;
            allocArrays(i11);
            if (i8 != this.mSize) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.mHashes;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.mArray, 0, objArr2.length);
            }
            freeArrays(iArr, objArr2, i8);
        }
        if (i10 < i8) {
            int[] iArr3 = this.mHashes;
            int i12 = i10 + 1;
            System.arraycopy(iArr3, i10, iArr3, i12, i8 - i10);
            Object[] objArr3 = this.mArray;
            System.arraycopy(objArr3, i10 << 1, objArr3, i12 << 1, (this.mSize - i10) << 1);
        }
        int i13 = this.mSize;
        if (i8 == i13) {
            int[] iArr4 = this.mHashes;
            if (i10 < iArr4.length) {
                iArr4[i10] = i7;
                Object[] objArr4 = this.mArray;
                int i14 = i10 << 1;
                objArr4[i14] = k7;
                objArr4[i14 + 1] = v6;
                this.mSize = i13 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public void putAll(@NonNull SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        int i7 = simpleArrayMap.mSize;
        ensureCapacity(this.mSize + i7);
        if (this.mSize != 0) {
            for (int i8 = 0; i8 < i7; i8++) {
                put(simpleArrayMap.keyAt(i8), simpleArrayMap.valueAt(i8));
            }
        } else if (i7 > 0) {
            System.arraycopy(simpleArrayMap.mHashes, 0, this.mHashes, 0, i7);
            System.arraycopy(simpleArrayMap.mArray, 0, this.mArray, 0, i7 << 1);
            this.mSize = i7;
        }
    }

    @Nullable
    public V remove(Object obj) {
        int iIndexOfKey = indexOfKey(obj);
        if (iIndexOfKey >= 0) {
            return removeAt(iIndexOfKey);
        }
        return null;
    }

    public V removeAt(int i7) {
        Object[] objArr = this.mArray;
        int i8 = i7 << 1;
        V v6 = (V) objArr[i8 + 1];
        int i9 = this.mSize;
        int i10 = 0;
        if (i9 <= 1) {
            freeArrays(this.mHashes, objArr, i9);
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        } else {
            int i11 = i9 - 1;
            int[] iArr = this.mHashes;
            if (iArr.length <= 8 || i9 >= iArr.length / 3) {
                if (i7 < i11) {
                    int i12 = i7 + 1;
                    int i13 = i11 - i7;
                    System.arraycopy(iArr, i12, iArr, i7, i13);
                    Object[] objArr2 = this.mArray;
                    System.arraycopy(objArr2, i12 << 1, objArr2, i8, i13 << 1);
                }
                Object[] objArr3 = this.mArray;
                int i14 = i11 << 1;
                objArr3[i14] = null;
                objArr3[i14 + 1] = null;
            } else {
                allocArrays(i9 > 8 ? i9 + (i9 >> 1) : 8);
                if (i9 != this.mSize) {
                    throw new ConcurrentModificationException();
                }
                if (i7 > 0) {
                    System.arraycopy(iArr, 0, this.mHashes, 0, i7);
                    System.arraycopy(objArr, 0, this.mArray, 0, i8);
                }
                if (i7 < i11) {
                    int i15 = i7 + 1;
                    int i16 = i11 - i7;
                    System.arraycopy(iArr, i15, this.mHashes, i7, i16);
                    System.arraycopy(objArr, i15 << 1, this.mArray, i8, i16 << 1);
                }
            }
            i10 = i11;
        }
        if (i9 != this.mSize) {
            throw new ConcurrentModificationException();
        }
        this.mSize = i10;
        return v6;
    }

    public V setValueAt(int i7, V v6) {
        int i8 = (i7 << 1) + 1;
        Object[] objArr = this.mArray;
        V v7 = (V) objArr[i8];
        objArr[i8] = v6;
        return v7;
    }

    public int size() {
        return this.mSize;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.mSize * 28);
        sb.append('{');
        for (int i7 = 0; i7 < this.mSize; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            K kKeyAt = keyAt(i7);
            if (kKeyAt != this) {
                sb.append(kKeyAt);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V vValueAt = valueAt(i7);
            if (vValueAt != this) {
                sb.append(vValueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public V valueAt(int i7) {
        return (V) this.mArray[(i7 << 1) + 1];
    }

    public SimpleArrayMap(int i7) {
        if (i7 == 0) {
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        } else {
            allocArrays(i7);
        }
        this.mSize = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SimpleArrayMap(SimpleArrayMap<K, V> simpleArrayMap) {
        this();
        if (simpleArrayMap != 0) {
            putAll(simpleArrayMap);
        }
    }
}
