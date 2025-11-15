package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: classes.dex */
final class SegmentedByteString extends ByteString {
    public final transient int[] directory;
    public final transient byte[][] segments;

    public SegmentedByteString(Buffer buffer, int i7) {
        super(null);
        Util.checkOffsetAndCount(buffer.size, 0L, i7);
        Segment segment = buffer.head;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i9 < i7) {
            int i11 = segment.limit;
            int i12 = segment.pos;
            if (i11 == i12) {
                throw new AssertionError("s.limit == s.pos");
            }
            i9 += i11 - i12;
            i10++;
            segment = segment.next;
        }
        this.segments = new byte[i10][];
        this.directory = new int[i10 * 2];
        Segment segment2 = buffer.head;
        int i13 = 0;
        while (i8 < i7) {
            byte[][] bArr = this.segments;
            bArr[i13] = segment2.data;
            int i14 = segment2.limit;
            int i15 = segment2.pos;
            int i16 = (i14 - i15) + i8;
            i8 = i16 > i7 ? i7 : i16;
            int[] iArr = this.directory;
            iArr[i13] = i8;
            iArr[bArr.length + i13] = i15;
            segment2.shared = true;
            i13++;
            segment2 = segment2.next;
        }
    }

    private int segment(int i7) {
        int iBinarySearch = Arrays.binarySearch(this.directory, 0, this.segments.length, i7 + 1);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }

    private ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    private Object writeReplace() {
        return toByteString();
    }

    @Override // okio.ByteString
    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    @Override // okio.ByteString
    public String base64() {
        return toByteString().base64();
    }

    @Override // okio.ByteString
    public String base64Url() {
        return toByteString().base64Url();
    }

    @Override // okio.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == size() && rangeEquals(0, byteString, 0, size())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    public byte getByte(int i7) {
        Util.checkOffsetAndCount(this.directory[this.segments.length - 1], i7, 1L);
        int iSegment = segment(i7);
        int i8 = iSegment == 0 ? 0 : this.directory[iSegment - 1];
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        return bArr[iSegment][(i7 - i8) + iArr[bArr.length + iSegment]];
    }

    @Override // okio.ByteString
    public int hashCode() {
        int i7 = this.hashCode;
        if (i7 != 0) {
            return i7;
        }
        int length = this.segments.length;
        int i8 = 0;
        int i9 = 0;
        int i10 = 1;
        while (i8 < length) {
            byte[] bArr = this.segments[i8];
            int[] iArr = this.directory;
            int i11 = iArr[length + i8];
            int i12 = iArr[i8];
            int i13 = (i12 - i9) + i11;
            while (i11 < i13) {
                i10 = (i10 * 31) + bArr[i11];
                i11++;
            }
            i8++;
            i9 = i12;
        }
        this.hashCode = i10;
        return i10;
    }

    @Override // okio.ByteString
    public String hex() {
        return toByteString().hex();
    }

    @Override // okio.ByteString
    public ByteString hmacSha1(ByteString byteString) {
        return toByteString().hmacSha1(byteString);
    }

    @Override // okio.ByteString
    public ByteString hmacSha256(ByteString byteString) {
        return toByteString().hmacSha256(byteString);
    }

    @Override // okio.ByteString
    public int indexOf(byte[] bArr, int i7) {
        return toByteString().indexOf(bArr, i7);
    }

    @Override // okio.ByteString
    public byte[] internalArray() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public int lastIndexOf(byte[] bArr, int i7) {
        return toByteString().lastIndexOf(bArr, i7);
    }

    @Override // okio.ByteString
    public ByteString md5() {
        return toByteString().md5();
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int i7, ByteString byteString, int i8, int i9) {
        if (i7 < 0 || i7 > size() - i9) {
            return false;
        }
        int iSegment = segment(i7);
        while (i9 > 0) {
            int i10 = iSegment == 0 ? 0 : this.directory[iSegment - 1];
            int iMin = Math.min(i9, ((this.directory[iSegment] - i10) + i10) - i7);
            int[] iArr = this.directory;
            byte[][] bArr = this.segments;
            if (!byteString.rangeEquals(i8, bArr[iSegment], (i7 - i10) + iArr[bArr.length + iSegment], iMin)) {
                return false;
            }
            i7 += iMin;
            i8 += iMin;
            i9 -= iMin;
            iSegment++;
        }
        return true;
    }

    @Override // okio.ByteString
    public ByteString sha1() {
        return toByteString().sha1();
    }

    @Override // okio.ByteString
    public ByteString sha256() {
        return toByteString().sha256();
    }

    @Override // okio.ByteString
    public int size() {
        return this.directory[this.segments.length - 1];
    }

    @Override // okio.ByteString
    public String string(Charset charset) {
        return toByteString().string(charset);
    }

    @Override // okio.ByteString
    public ByteString substring(int i7) {
        return toByteString().substring(i7);
    }

    @Override // okio.ByteString
    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    @Override // okio.ByteString
    public ByteString toAsciiUppercase() {
        return toByteString().toAsciiUppercase();
    }

    @Override // okio.ByteString
    public byte[] toByteArray() {
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            int[] iArr2 = this.directory;
            int i9 = iArr2[length + i7];
            int i10 = iArr2[i7];
            System.arraycopy(this.segments[i7], i9, bArr2, i8, i10 - i8);
            i7++;
            i8 = i10;
        }
        return bArr2;
    }

    @Override // okio.ByteString
    public String toString() {
        return toByteString().toString();
    }

    @Override // okio.ByteString
    public String utf8() {
        return toByteString().utf8();
    }

    @Override // okio.ByteString
    public void write(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        int length = this.segments.length;
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            int[] iArr = this.directory;
            int i9 = iArr[length + i7];
            int i10 = iArr[i7];
            outputStream.write(this.segments[i7], i9, i10 - i8);
            i7++;
            i8 = i10;
        }
    }

    @Override // okio.ByteString
    public ByteString substring(int i7, int i8) {
        return toByteString().substring(i7, i8);
    }

    @Override // okio.ByteString
    public void write(Buffer buffer) {
        int length = this.segments.length;
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            int[] iArr = this.directory;
            int i9 = iArr[length + i7];
            int i10 = iArr[i7];
            Segment segment = new Segment(this.segments[i7], i9, (i9 + i10) - i8, true, false);
            Segment segment2 = buffer.head;
            if (segment2 == null) {
                segment.prev = segment;
                segment.next = segment;
                buffer.head = segment;
            } else {
                segment2.prev.push(segment);
            }
            i7++;
            i8 = i10;
        }
        buffer.size += i8;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int i7, byte[] bArr, int i8, int i9) {
        if (i7 < 0 || i7 > size() - i9 || i8 < 0 || i8 > bArr.length - i9) {
            return false;
        }
        int iSegment = segment(i7);
        while (i9 > 0) {
            int i10 = iSegment == 0 ? 0 : this.directory[iSegment - 1];
            int iMin = Math.min(i9, ((this.directory[iSegment] - i10) + i10) - i7);
            int[] iArr = this.directory;
            byte[][] bArr2 = this.segments;
            if (!Util.arrayRangeEquals(bArr2[iSegment], (i7 - i10) + iArr[bArr2.length + iSegment], bArr, i8, iMin)) {
                return false;
            }
            i7 += iMin;
            i8 += iMin;
            i9 -= iMin;
            iSegment++;
        }
        return true;
    }
}
