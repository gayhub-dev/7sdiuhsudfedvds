package android.support.v4.util;

/* loaded from: classes.dex */
public final class CircularIntArray {
    private int mCapacityBitmask;
    private int[] mElements;
    private int mHead;
    private int mTail;

    public CircularIntArray() {
        this(8);
    }

    private void doubleCapacity() {
        int[] iArr = this.mElements;
        int length = iArr.length;
        int i7 = this.mHead;
        int i8 = length - i7;
        int i9 = length << 1;
        if (i9 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        int[] iArr2 = new int[i9];
        System.arraycopy(iArr, i7, iArr2, 0, i8);
        System.arraycopy(this.mElements, 0, iArr2, i8, this.mHead);
        this.mElements = iArr2;
        this.mHead = 0;
        this.mTail = length;
        this.mCapacityBitmask = i9 - 1;
    }

    public void addFirst(int i7) {
        int i8 = (this.mHead - 1) & this.mCapacityBitmask;
        this.mHead = i8;
        this.mElements[i8] = i7;
        if (i8 == this.mTail) {
            doubleCapacity();
        }
    }

    public void addLast(int i7) {
        int[] iArr = this.mElements;
        int i8 = this.mTail;
        iArr[i8] = i7;
        int i9 = this.mCapacityBitmask & (i8 + 1);
        this.mTail = i9;
        if (i9 == this.mHead) {
            doubleCapacity();
        }
    }

    public void clear() {
        this.mTail = this.mHead;
    }

    public int get(int i7) {
        if (i7 < 0 || i7 >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mCapacityBitmask & (this.mHead + i7)];
    }

    public int getFirst() {
        int i7 = this.mHead;
        if (i7 != this.mTail) {
            return this.mElements[i7];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int getLast() {
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

    public int popFirst() {
        int i7 = this.mHead;
        if (i7 == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i8 = this.mElements[i7];
        this.mHead = (i7 + 1) & this.mCapacityBitmask;
        return i8;
    }

    public int popLast() {
        int i7 = this.mHead;
        int i8 = this.mTail;
        if (i7 == i8) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i9 = this.mCapacityBitmask & (i8 - 1);
        int i10 = this.mElements[i9];
        this.mTail = i9;
        return i10;
    }

    public void removeFromEnd(int i7) {
        if (i7 <= 0) {
            return;
        }
        if (i7 > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mTail = this.mCapacityBitmask & (this.mTail - i7);
    }

    public void removeFromStart(int i7) {
        if (i7 <= 0) {
            return;
        }
        if (i7 > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mHead = this.mCapacityBitmask & (this.mHead + i7);
    }

    public int size() {
        return (this.mTail - this.mHead) & this.mCapacityBitmask;
    }

    public CircularIntArray(int i7) {
        if (i7 < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (i7 > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        i7 = Integer.bitCount(i7) != 1 ? Integer.highestOneBit(i7 - 1) << 1 : i7;
        this.mCapacityBitmask = i7 - 1;
        this.mElements = new int[i7];
    }
}
