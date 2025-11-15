package android.support.v4.util;

/* loaded from: classes.dex */
public final class CircularArray<E> {
    private int mCapacityBitmask;
    private E[] mElements;
    private int mHead;
    private int mTail;

    public CircularArray() {
        this(8);
    }

    private void doubleCapacity() {
        E[] eArr = this.mElements;
        int length = eArr.length;
        int i7 = this.mHead;
        int i8 = length - i7;
        int i9 = length << 1;
        if (i9 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        E[] eArr2 = (E[]) new Object[i9];
        System.arraycopy(eArr, i7, eArr2, 0, i8);
        System.arraycopy(this.mElements, 0, eArr2, i8, this.mHead);
        this.mElements = eArr2;
        this.mHead = 0;
        this.mTail = length;
        this.mCapacityBitmask = i9 - 1;
    }

    public void addFirst(E e7) {
        int i7 = (this.mHead - 1) & this.mCapacityBitmask;
        this.mHead = i7;
        this.mElements[i7] = e7;
        if (i7 == this.mTail) {
            doubleCapacity();
        }
    }

    public void addLast(E e7) {
        E[] eArr = this.mElements;
        int i7 = this.mTail;
        eArr[i7] = e7;
        int i8 = this.mCapacityBitmask & (i7 + 1);
        this.mTail = i8;
        if (i8 == this.mHead) {
            doubleCapacity();
        }
    }

    public void clear() {
        removeFromStart(size());
    }

    public E get(int i7) {
        if (i7 < 0 || i7 >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mCapacityBitmask & (this.mHead + i7)];
    }

    public E getFirst() {
        int i7 = this.mHead;
        if (i7 != this.mTail) {
            return this.mElements[i7];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E getLast() {
        int i7 = this.mHead;
        int i8 = this.mTail;
        if (i7 != i8) {
            return this.mElements[(i8 - 1) & this.mCapacityBitmask];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public boolean isEmpty() {
        return this.mHead == this.mTail;
    }

    public E popFirst() {
        int i7 = this.mHead;
        if (i7 == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E[] eArr = this.mElements;
        E e7 = eArr[i7];
        eArr[i7] = null;
        this.mHead = (i7 + 1) & this.mCapacityBitmask;
        return e7;
    }

    public E popLast() {
        int i7 = this.mHead;
        int i8 = this.mTail;
        if (i7 == i8) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i9 = this.mCapacityBitmask & (i8 - 1);
        E[] eArr = this.mElements;
        E e7 = eArr[i9];
        eArr[i9] = null;
        this.mTail = i9;
        return e7;
    }

    public void removeFromEnd(int i7) {
        int i8;
        if (i7 <= 0) {
            return;
        }
        if (i7 > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i9 = this.mTail;
        int i10 = i7 < i9 ? i9 - i7 : 0;
        int i11 = i10;
        while (true) {
            i8 = this.mTail;
            if (i11 >= i8) {
                break;
            }
            this.mElements[i11] = null;
            i11++;
        }
        int i12 = i8 - i10;
        int i13 = i7 - i12;
        this.mTail = i8 - i12;
        if (i13 > 0) {
            int length = this.mElements.length;
            this.mTail = length;
            int i14 = length - i13;
            for (int i15 = i14; i15 < this.mTail; i15++) {
                this.mElements[i15] = null;
            }
            this.mTail = i14;
        }
    }

    public void removeFromStart(int i7) {
        if (i7 <= 0) {
            return;
        }
        if (i7 > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int length = this.mElements.length;
        int i8 = this.mHead;
        if (i7 < length - i8) {
            length = i8 + i7;
        }
        while (i8 < length) {
            this.mElements[i8] = null;
            i8++;
        }
        int i9 = this.mHead;
        int i10 = length - i9;
        int i11 = i7 - i10;
        this.mHead = this.mCapacityBitmask & (i9 + i10);
        if (i11 > 0) {
            for (int i12 = 0; i12 < i11; i12++) {
                this.mElements[i12] = null;
            }
            this.mHead = i11;
        }
    }

    public int size() {
        return (this.mTail - this.mHead) & this.mCapacityBitmask;
    }

    public CircularArray(int i7) {
        if (i7 < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (i7 > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        i7 = Integer.bitCount(i7) != 1 ? Integer.highestOneBit(i7 - 1) << 1 : i7;
        this.mCapacityBitmask = i7 - 1;
        this.mElements = (E[]) new Object[i7];
    }
}
