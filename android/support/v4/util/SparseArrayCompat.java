package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* loaded from: classes.dex */
public class SparseArrayCompat<E> implements Cloneable {
    private static final Object DELETED = new Object();
    private boolean mGarbage;
    private int[] mKeys;
    private int mSize;
    private Object[] mValues;

    public SparseArrayCompat() {
        this(10);
    }

    /* renamed from: gc */
    private void m102gc() {
        int i7 = this.mSize;
        int[] iArr = this.mKeys;
        Object[] objArr = this.mValues;
        int i8 = 0;
        for (int i9 = 0; i9 < i7; i9++) {
            Object obj = objArr[i9];
            if (obj != DELETED) {
                if (i9 != i8) {
                    iArr[i8] = iArr[i9];
                    objArr[i8] = obj;
                    objArr[i9] = null;
                }
                i8++;
            }
        }
        this.mGarbage = false;
        this.mSize = i8;
    }

    public void append(int i7, E e7) {
        int i8 = this.mSize;
        if (i8 != 0 && i7 <= this.mKeys[i8 - 1]) {
            put(i7, e7);
            return;
        }
        if (this.mGarbage && i8 >= this.mKeys.length) {
            m102gc();
        }
        int i9 = this.mSize;
        if (i9 >= this.mKeys.length) {
            int iIdealIntArraySize = ContainerHelpers.idealIntArraySize(i9 + 1);
            int[] iArr = new int[iIdealIntArraySize];
            Object[] objArr = new Object[iIdealIntArraySize];
            int[] iArr2 = this.mKeys;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.mValues;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.mKeys = iArr;
            this.mValues = objArr;
        }
        this.mKeys[i9] = i7;
        this.mValues[i9] = e7;
        this.mSize = i9 + 1;
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

    public boolean containsKey(int i7) {
        return indexOfKey(i7) >= 0;
    }

    public boolean containsValue(E e7) {
        return indexOfValue(e7) >= 0;
    }

    public void delete(int i7) {
        int iBinarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, i7);
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
    public E get(int i7) {
        return get(i7, null);
    }

    public int indexOfKey(int i7) {
        if (this.mGarbage) {
            m102gc();
        }
        return ContainerHelpers.binarySearch(this.mKeys, this.mSize, i7);
    }

    public int indexOfValue(E e7) {
        if (this.mGarbage) {
            m102gc();
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

    public int keyAt(int i7) {
        if (this.mGarbage) {
            m102gc();
        }
        return this.mKeys[i7];
    }

    public void put(int i7, E e7) {
        int iBinarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, i7);
        if (iBinarySearch >= 0) {
            this.mValues[iBinarySearch] = e7;
            return;
        }
        int i8 = ~iBinarySearch;
        int i9 = this.mSize;
        if (i8 < i9) {
            Object[] objArr = this.mValues;
            if (objArr[i8] == DELETED) {
                this.mKeys[i8] = i7;
                objArr[i8] = e7;
                return;
            }
        }
        if (this.mGarbage && i9 >= this.mKeys.length) {
            m102gc();
            i8 = ~ContainerHelpers.binarySearch(this.mKeys, this.mSize, i7);
        }
        int i10 = this.mSize;
        if (i10 >= this.mKeys.length) {
            int iIdealIntArraySize = ContainerHelpers.idealIntArraySize(i10 + 1);
            int[] iArr = new int[iIdealIntArraySize];
            Object[] objArr2 = new Object[iIdealIntArraySize];
            int[] iArr2 = this.mKeys;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.mValues;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.mKeys = iArr;
            this.mValues = objArr2;
        }
        int i11 = this.mSize;
        if (i11 - i8 != 0) {
            int[] iArr3 = this.mKeys;
            int i12 = i8 + 1;
            System.arraycopy(iArr3, i8, iArr3, i12, i11 - i8);
            Object[] objArr4 = this.mValues;
            System.arraycopy(objArr4, i8, objArr4, i12, this.mSize - i8);
        }
        this.mKeys[i8] = i7;
        this.mValues[i8] = e7;
        this.mSize++;
    }

    public void putAll(@NonNull SparseArrayCompat<? extends E> sparseArrayCompat) {
        int size = sparseArrayCompat.size();
        for (int i7 = 0; i7 < size; i7++) {
            put(sparseArrayCompat.keyAt(i7), sparseArrayCompat.valueAt(i7));
        }
    }

    public void remove(int i7) {
        delete(i7);
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

    public void removeAtRange(int i7, int i8) {
        int iMin = Math.min(this.mSize, i8 + i7);
        while (i7 < iMin) {
            removeAt(i7);
            i7++;
        }
    }

    public void setValueAt(int i7, E e7) {
        if (this.mGarbage) {
            m102gc();
        }
        this.mValues[i7] = e7;
    }

    public int size() {
        if (this.mGarbage) {
            m102gc();
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
            m102gc();
        }
        return (E) this.mValues[i7];
    }

    public SparseArrayCompat(int i7) {
        this.mGarbage = false;
        if (i7 == 0) {
            this.mKeys = ContainerHelpers.EMPTY_INTS;
            this.mValues = ContainerHelpers.EMPTY_OBJECTS;
        } else {
            int iIdealIntArraySize = ContainerHelpers.idealIntArraySize(i7);
            this.mKeys = new int[iIdealIntArraySize];
            this.mValues = new Object[iIdealIntArraySize];
        }
        this.mSize = 0;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public SparseArrayCompat<E> m2607clone() {
        try {
            SparseArrayCompat<E> sparseArrayCompat = (SparseArrayCompat) super.clone();
            sparseArrayCompat.mKeys = (int[]) this.mKeys.clone();
            sparseArrayCompat.mValues = (Object[]) this.mValues.clone();
            return sparseArrayCompat;
        } catch (CloneNotSupportedException e7) {
            throw new AssertionError(e7);
        }
    }

    public E get(int i7, E e7) {
        int iBinarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, i7);
        if (iBinarySearch >= 0) {
            Object[] objArr = this.mValues;
            if (objArr[iBinarySearch] != DELETED) {
                return (E) objArr[iBinarySearch];
            }
        }
        return e7;
    }
}
