package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* loaded from: classes.dex */
public class LongSparseArray<E> implements Cloneable {
    private static final Object DELETED = new Object();
    private boolean mGarbage;
    private long[] mKeys;
    private int mSize;
    private Object[] mValues;

    public LongSparseArray() {
        this(10);
    }

    /* renamed from: gc */
    private void m101gc() {
        int i7 = this.mSize;
        long[] jArr = this.mKeys;
        Object[] objArr = this.mValues;
        int i8 = 0;
        for (int i9 = 0; i9 < i7; i9++) {
            Object obj = objArr[i9];
            if (obj != DELETED) {
                if (i9 != i8) {
                    jArr[i8] = jArr[i9];
                    objArr[i8] = obj;
                    objArr[i9] = null;
                }
                i8++;
            }
        }
        this.mGarbage = false;
        this.mSize = i8;
    }

    public void append(long j7, E e7) {
        int i7 = this.mSize;
        if (i7 != 0 && j7 <= this.mKeys[i7 - 1]) {
            put(j7, e7);
            return;
        }
        if (this.mGarbage && i7 >= this.mKeys.length) {
            m101gc();
        }
        int i8 = this.mSize;
        if (i8 >= this.mKeys.length) {
            int iIdealLongArraySize = ContainerHelpers.idealLongArraySize(i8 + 1);
            long[] jArr = new long[iIdealLongArraySize];
            Object[] objArr = new Object[iIdealLongArraySize];
            long[] jArr2 = this.mKeys;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.mValues;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.mKeys = jArr;
            this.mValues = objArr;
        }
        this.mKeys[i8] = j7;
        this.mValues[i8] = e7;
        this.mSize = i8 + 1;
    }

    public void clear() {
        int i7 = this.mSize;
        Object[] objArr = this.mValues;
        for (int i8 = 0; i8 < i7; i8++) {
            objArr[i8] = null;
        }
        this.mSize = 0;
        this.mGarbage = false;
    }

    public boolean containsKey(long j7) {
        return indexOfKey(j7) >= 0;
    }

    public boolean containsValue(E e7) {
        return indexOfValue(e7) >= 0;
    }

    public void delete(long j7) {
        int iBinarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, j7);
        if (iBinarySearch >= 0) {
            Object[] objArr = this.mValues;
            Object obj = objArr[iBinarySearch];
            Object obj2 = DELETED;
            if (obj != obj2) {
                objArr[iBinarySearch] = obj2;
                this.mGarbage = true;
            }
        }
    }

    @Nullable
    public E get(long j7) {
        return get(j7, null);
    }

    public int indexOfKey(long j7) {
        if (this.mGarbage) {
            m101gc();
        }
        return ContainerHelpers.binarySearch(this.mKeys, this.mSize, j7);
    }

    public int indexOfValue(E e7) {
        if (this.mGarbage) {
            m101gc();
        }
        for (int i7 = 0; i7 < this.mSize; i7++) {
            if (this.mValues[i7] == e7) {
                return i7;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public long keyAt(int i7) {
        if (this.mGarbage) {
            m101gc();
        }
        return this.mKeys[i7];
    }

    public void put(long j7, E e7) {
        int iBinarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, j7);
        if (iBinarySearch >= 0) {
            this.mValues[iBinarySearch] = e7;
            return;
        }
        int i7 = ~iBinarySearch;
        int i8 = this.mSize;
        if (i7 < i8) {
            Object[] objArr = this.mValues;
            if (objArr[i7] == DELETED) {
                this.mKeys[i7] = j7;
                objArr[i7] = e7;
                return;
            }
        }
        if (this.mGarbage && i8 >= this.mKeys.length) {
            m101gc();
            i7 = ~ContainerHelpers.binarySearch(this.mKeys, this.mSize, j7);
        }
        int i9 = this.mSize;
        if (i9 >= this.mKeys.length) {
            int iIdealLongArraySize = ContainerHelpers.idealLongArraySize(i9 + 1);
            long[] jArr = new long[iIdealLongArraySize];
            Object[] objArr2 = new Object[iIdealLongArraySize];
            long[] jArr2 = this.mKeys;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.mValues;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.mKeys = jArr;
            this.mValues = objArr2;
        }
        int i10 = this.mSize;
        if (i10 - i7 != 0) {
            long[] jArr3 = this.mKeys;
            int i11 = i7 + 1;
            System.arraycopy(jArr3, i7, jArr3, i11, i10 - i7);
            Object[] objArr4 = this.mValues;
            System.arraycopy(objArr4, i7, objArr4, i11, this.mSize - i7);
        }
        this.mKeys[i7] = j7;
        this.mValues[i7] = e7;
        this.mSize++;
    }

    public void putAll(@NonNull LongSparseArray<? extends E> longSparseArray) {
        int size = longSparseArray.size();
        for (int i7 = 0; i7 < size; i7++) {
            put(longSparseArray.keyAt(i7), longSparseArray.valueAt(i7));
        }
    }

    public void remove(long j7) {
        delete(j7);
    }

    public void removeAt(int i7) {
        Object[] objArr = this.mValues;
        Object obj = objArr[i7];
        Object obj2 = DELETED;
        if (obj != obj2) {
            objArr[i7] = obj2;
            this.mGarbage = true;
        }
    }

    public void setValueAt(int i7, E e7) {
        if (this.mGarbage) {
            m101gc();
        }
        this.mValues[i7] = e7;
    }

    public int size() {
        if (this.mGarbage) {
            m101gc();
        }
        return this.mSize;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.mSize * 28);
        sb.append('{');
        for (int i7 = 0; i7 < this.mSize; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            sb.append(keyAt(i7));
            sb.append('=');
            E eValueAt = valueAt(i7);
            if (eValueAt != this) {
                sb.append(eValueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public E valueAt(int i7) {
        if (this.mGarbage) {
            m101gc();
        }
        return (E) this.mValues[i7];
    }

    public LongSparseArray(int i7) {
        this.mGarbage = false;
        if (i7 == 0) {
            this.mKeys = ContainerHelpers.EMPTY_LONGS;
            this.mValues = ContainerHelpers.EMPTY_OBJECTS;
        } else {
            int iIdealLongArraySize = ContainerHelpers.idealLongArraySize(i7);
            this.mKeys = new long[iIdealLongArraySize];
            this.mValues = new Object[iIdealLongArraySize];
        }
        this.mSize = 0;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public LongSparseArray<E> m2606clone() {
        try {
            LongSparseArray<E> longSparseArray = (LongSparseArray) super.clone();
            longSparseArray.mKeys = (long[]) this.mKeys.clone();
            longSparseArray.mValues = (Object[]) this.mValues.clone();
            return longSparseArray;
        } catch (CloneNotSupportedException e7) {
            throw new AssertionError(e7);
        }
    }

    public E get(long j7, E e7) {
        int iBinarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, j7);
        if (iBinarySearch >= 0) {
            Object[] objArr = this.mValues;
            if (objArr[iBinarySearch] != DELETED) {
                return (E) objArr[iBinarySearch];
            }
        }
        return e7;
    }
}
