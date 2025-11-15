package com.alibaba.fastjson.asm;

/* loaded from: classes.dex */
public class ByteVector {
    public byte[] data;
    public int length;

    public ByteVector() {
        this.data = new byte[64];
    }

    private void enlarge(int i7) {
        byte[] bArr = this.data;
        int length = bArr.length * 2;
        int i8 = this.length;
        int i9 = i7 + i8;
        if (length <= i9) {
            length = i9;
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, i8);
        this.data = bArr2;
    }

    public ByteVector put11(int i7, int i8) {
        int i9 = this.length;
        if (i9 + 2 > this.data.length) {
            enlarge(2);
        }
        byte[] bArr = this.data;
        int i10 = i9 + 1;
        bArr[i9] = (byte) i7;
        bArr[i10] = (byte) i8;
        this.length = i10 + 1;
        return this;
    }

    public ByteVector put12(int i7, int i8) {
        int i9 = this.length;
        if (i9 + 3 > this.data.length) {
            enlarge(3);
        }
        byte[] bArr = this.data;
        int i10 = i9 + 1;
        bArr[i9] = (byte) i7;
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i8 >>> 8);
        bArr[i11] = (byte) i8;
        this.length = i11 + 1;
        return this;
    }

    public ByteVector putByte(int i7) {
        int i8 = this.length;
        int i9 = i8 + 1;
        if (i9 > this.data.length) {
            enlarge(1);
        }
        this.data[i8] = (byte) i7;
        this.length = i9;
        return this;
    }

    public ByteVector putByteArray(byte[] bArr, int i7, int i8) {
        if (this.length + i8 > this.data.length) {
            enlarge(i8);
        }
        if (bArr != null) {
            System.arraycopy(bArr, i7, this.data, this.length, i8);
        }
        this.length += i8;
        return this;
    }

    public ByteVector putInt(int i7) {
        int i8 = this.length;
        if (i8 + 4 > this.data.length) {
            enlarge(4);
        }
        byte[] bArr = this.data;
        int i9 = i8 + 1;
        bArr[i8] = (byte) (i7 >>> 24);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i7 >>> 16);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i7 >>> 8);
        bArr[i11] = (byte) i7;
        this.length = i11 + 1;
        return this;
    }

    public ByteVector putShort(int i7) {
        int i8 = this.length;
        if (i8 + 2 > this.data.length) {
            enlarge(2);
        }
        byte[] bArr = this.data;
        int i9 = i8 + 1;
        bArr[i8] = (byte) (i7 >>> 8);
        bArr[i9] = (byte) i7;
        this.length = i9 + 1;
        return this;
    }

    public ByteVector putUTF8(String str) {
        int length = str.length();
        int i7 = this.length;
        if (i7 + 2 + length > this.data.length) {
            enlarge(length + 2);
        }
        byte[] bArr = this.data;
        int i8 = i7 + 1;
        bArr[i7] = (byte) (length >>> 8);
        int i9 = i8 + 1;
        bArr[i8] = (byte) length;
        int i10 = 0;
        while (i10 < length) {
            char cCharAt = str.charAt(i10);
            if (cCharAt < 1 || cCharAt > 127) {
                throw new UnsupportedOperationException();
            }
            bArr[i9] = (byte) cCharAt;
            i10++;
            i9++;
        }
        this.length = i9;
        return this;
    }

    public ByteVector(int i7) {
        this.data = new byte[i7];
    }
}
