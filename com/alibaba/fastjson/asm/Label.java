package com.alibaba.fastjson.asm;

/* loaded from: classes.dex */
public class Label {
    public int inputStackTop;
    public Label next;
    public int outputStackMax;
    public int position;
    private int referenceCount;
    private int[] srcAndRefPositions;
    public int status;
    public Label successor;

    private void addReference(int i7, int i8) {
        if (this.srcAndRefPositions == null) {
            this.srcAndRefPositions = new int[6];
        }
        int i9 = this.referenceCount;
        int[] iArr = this.srcAndRefPositions;
        if (i9 >= iArr.length) {
            int[] iArr2 = new int[iArr.length + 6];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.srcAndRefPositions = iArr2;
        }
        int[] iArr3 = this.srcAndRefPositions;
        int i10 = this.referenceCount;
        int i11 = i10 + 1;
        this.referenceCount = i11;
        iArr3[i10] = i7;
        this.referenceCount = i11 + 1;
        iArr3[i11] = i8;
    }

    public void put(MethodWriter methodWriter, ByteVector byteVector, int i7) {
        if ((this.status & 2) != 0) {
            byteVector.putShort(this.position - i7);
        } else {
            addReference(i7, byteVector.length);
            byteVector.putShort(-1);
        }
    }

    public void resolve(MethodWriter methodWriter, int i7, byte[] bArr) {
        this.status |= 2;
        this.position = i7;
        int i8 = 0;
        while (i8 < this.referenceCount) {
            int[] iArr = this.srcAndRefPositions;
            int i9 = i8 + 1;
            int i10 = iArr[i8];
            int i11 = iArr[i9];
            int i12 = i7 - i10;
            bArr[i11] = (byte) (i12 >>> 8);
            bArr[i11 + 1] = (byte) i12;
            i8 = i9 + 1;
        }
    }
}
