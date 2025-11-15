package okio;

import android.support.constraint.motion.C0079a;
import android.support.graphics.drawable.C0116a;
import android.support.v7.widget.RecyclerView;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {
    private static final byte[] DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    public static final int REPLACEMENT_CHARACTER = 65533;

    @Nullable
    public Segment head;
    public long size;

    public static final class UnsafeCursor implements Closeable {
        public Buffer buffer;
        public byte[] data;
        public boolean readWrite;
        private Segment segment;
        public long offset = -1;
        public int start = -1;
        public int end = -1;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.buffer == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            this.buffer = null;
            this.segment = null;
            this.offset = -1L;
            this.data = null;
            this.start = -1;
            this.end = -1;
        }

        public final long expandBuffer(int i7) {
            if (i7 <= 0) {
                throw new IllegalArgumentException(C0079a.m93a("minByteCount <= 0: ", i7));
            }
            if (i7 > 8192) {
                throw new IllegalArgumentException(C0079a.m93a("minByteCount > Segment.SIZE: ", i7));
            }
            Buffer buffer = this.buffer;
            if (buffer == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if (!this.readWrite) {
                throw new IllegalStateException("expandBuffer() only permitted for read/write buffers");
            }
            long j7 = buffer.size;
            Segment segmentWritableSegment = buffer.writableSegment(i7);
            int i8 = 8192 - segmentWritableSegment.limit;
            segmentWritableSegment.limit = 8192;
            long j8 = i8;
            this.buffer.size = j7 + j8;
            this.segment = segmentWritableSegment;
            this.offset = j7;
            this.data = segmentWritableSegment.data;
            this.start = 8192 - i8;
            this.end = 8192;
            return j8;
        }

        public final int next() {
            long j7 = this.offset;
            if (j7 != this.buffer.size) {
                return j7 == -1 ? seek(0L) : seek(j7 + (this.end - this.start));
            }
            throw new IllegalStateException();
        }

        public final long resizeBuffer(long j7) {
            Buffer buffer = this.buffer;
            if (buffer == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if (!this.readWrite) {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
            }
            long j8 = buffer.size;
            if (j7 <= j8) {
                if (j7 < 0) {
                    throw new IllegalArgumentException(C0534b.m341a("newSize < 0: ", j7));
                }
                long j9 = j8 - j7;
                while (true) {
                    if (j9 <= 0) {
                        break;
                    }
                    Buffer buffer2 = this.buffer;
                    Segment segment = buffer2.head.prev;
                    int i7 = segment.limit;
                    long j10 = i7 - segment.pos;
                    if (j10 > j9) {
                        segment.limit = (int) (i7 - j9);
                        break;
                    }
                    buffer2.head = segment.pop();
                    SegmentPool.recycle(segment);
                    j9 -= j10;
                }
                this.segment = null;
                this.offset = j7;
                this.data = null;
                this.start = -1;
                this.end = -1;
            } else if (j7 > j8) {
                long j11 = j7 - j8;
                boolean z6 = true;
                while (j11 > 0) {
                    Segment segmentWritableSegment = this.buffer.writableSegment(1);
                    int iMin = (int) Math.min(j11, 8192 - segmentWritableSegment.limit);
                    int i8 = segmentWritableSegment.limit + iMin;
                    segmentWritableSegment.limit = i8;
                    j11 -= iMin;
                    if (z6) {
                        this.segment = segmentWritableSegment;
                        this.offset = j8;
                        this.data = segmentWritableSegment.data;
                        this.start = i8 - iMin;
                        this.end = i8;
                        z6 = false;
                    }
                }
            }
            this.buffer.size = j7;
            return j8;
        }

        public final int seek(long j7) {
            if (j7 >= -1) {
                Buffer buffer = this.buffer;
                long j8 = buffer.size;
                if (j7 <= j8) {
                    if (j7 == -1 || j7 == j8) {
                        this.segment = null;
                        this.offset = j7;
                        this.data = null;
                        this.start = -1;
                        this.end = -1;
                        return -1;
                    }
                    long j9 = 0;
                    Segment segment = buffer.head;
                    Segment segmentPush = this.segment;
                    if (segmentPush != null) {
                        long j10 = this.offset - (this.start - segmentPush.pos);
                        if (j10 > j7) {
                            j8 = j10;
                            segmentPush = segment;
                            segment = segmentPush;
                        } else {
                            j9 = j10;
                        }
                    } else {
                        segmentPush = segment;
                    }
                    if (j8 - j7 > j7 - j9) {
                        while (true) {
                            int i7 = segmentPush.limit;
                            int i8 = segmentPush.pos;
                            if (j7 < (i7 - i8) + j9) {
                                break;
                            }
                            j9 += i7 - i8;
                            segmentPush = segmentPush.next;
                        }
                    } else {
                        while (j8 > j7) {
                            segment = segment.prev;
                            j8 -= segment.limit - segment.pos;
                        }
                        segmentPush = segment;
                        j9 = j8;
                    }
                    if (this.readWrite && segmentPush.shared) {
                        Segment segmentUnsharedCopy = segmentPush.unsharedCopy();
                        Buffer buffer2 = this.buffer;
                        if (buffer2.head == segmentPush) {
                            buffer2.head = segmentUnsharedCopy;
                        }
                        segmentPush = segmentPush.push(segmentUnsharedCopy);
                        segmentPush.prev.pop();
                    }
                    this.segment = segmentPush;
                    this.offset = j7;
                    this.data = segmentPush.data;
                    int i9 = segmentPush.pos + ((int) (j7 - j9));
                    this.start = i9;
                    int i10 = segmentPush.limit;
                    this.end = i10;
                    return i10 - i9;
                }
            }
            throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", Long.valueOf(j7), Long.valueOf(this.buffer.size)));
        }
    }

    private ByteString digest(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            Segment segment = this.head;
            if (segment != null) {
                byte[] bArr = segment.data;
                int i7 = segment.pos;
                messageDigest.update(bArr, i7, segment.limit - i7);
                Segment segment2 = this.head;
                while (true) {
                    segment2 = segment2.next;
                    if (segment2 == this.head) {
                        break;
                    }
                    byte[] bArr2 = segment2.data;
                    int i8 = segment2.pos;
                    messageDigest.update(bArr2, i8, segment2.limit - i8);
                }
            }
            return ByteString.m1869of(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    private ByteString hmac(String str, ByteString byteString) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            Segment segment = this.head;
            if (segment != null) {
                byte[] bArr = segment.data;
                int i7 = segment.pos;
                mac.update(bArr, i7, segment.limit - i7);
                Segment segment2 = this.head;
                while (true) {
                    segment2 = segment2.next;
                    if (segment2 == this.head) {
                        break;
                    }
                    byte[] bArr2 = segment2.data;
                    int i8 = segment2.pos;
                    mac.update(bArr2, i8, segment2.limit - i8);
                }
            }
            return ByteString.m1869of(mac.doFinal());
        } catch (InvalidKeyException e7) {
            throw new IllegalArgumentException(e7);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this;
    }

    public final void clear() {
        try {
            skip(this.size);
        } catch (EOFException e7) {
            throw new AssertionError(e7);
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public final long completeSegmentByteCount() {
        long j7 = this.size;
        if (j7 == 0) {
            return 0L;
        }
        Segment segment = this.head.prev;
        return (segment.limit >= 8192 || !segment.owner) ? j7 : j7 - (r3 - segment.pos);
    }

    public final Buffer copyTo(OutputStream outputStream) {
        return copyTo(outputStream, 0L, this.size);
    }

    @Override // okio.BufferedSink
    public BufferedSink emit() {
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer emitCompleteSegments() {
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Buffer)) {
            return false;
        }
        Buffer buffer = (Buffer) obj;
        long j7 = this.size;
        if (j7 != buffer.size) {
            return false;
        }
        long j8 = 0;
        if (j7 == 0) {
            return true;
        }
        Segment segment = this.head;
        Segment segment2 = buffer.head;
        int i7 = segment.pos;
        int i8 = segment2.pos;
        while (j8 < this.size) {
            long jMin = Math.min(segment.limit - i7, segment2.limit - i8);
            int i9 = 0;
            while (i9 < jMin) {
                int i10 = i7 + 1;
                int i11 = i8 + 1;
                if (segment.data[i7] != segment2.data[i8]) {
                    return false;
                }
                i9++;
                i7 = i10;
                i8 = i11;
            }
            if (i7 == segment.limit) {
                segment = segment.next;
                i7 = segment.pos;
            }
            if (i8 == segment2.limit) {
                segment2 = segment2.next;
                i8 = segment2.pos;
            }
            j8 += jMin;
        }
        return true;
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        return this.size == 0;
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // okio.BufferedSource
    public Buffer getBuffer() {
        return this;
    }

    public final byte getByte(long j7) {
        int i7;
        Util.checkOffsetAndCount(this.size, j7, 1L);
        long j8 = this.size;
        if (j8 - j7 <= j7) {
            long j9 = j7 - j8;
            Segment segment = this.head;
            do {
                segment = segment.prev;
                int i8 = segment.limit;
                i7 = segment.pos;
                j9 += i8 - i7;
            } while (j9 < 0);
            return segment.data[i7 + ((int) j9)];
        }
        Segment segment2 = this.head;
        while (true) {
            int i9 = segment2.limit;
            int i10 = segment2.pos;
            long j10 = i9 - i10;
            if (j7 < j10) {
                return segment2.data[i10 + ((int) j7)];
            }
            j7 -= j10;
            segment2 = segment2.next;
        }
    }

    public int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int i7 = 1;
        do {
            int i8 = segment.limit;
            for (int i9 = segment.pos; i9 < i8; i9++) {
                i7 = (i7 * 31) + segment.data[i9];
            }
            segment = segment.next;
        } while (segment != this.head);
        return i7;
    }

    public final ByteString hmacSha1(ByteString byteString) {
        return hmac("HmacSHA1", byteString);
    }

    public final ByteString hmacSha256(ByteString byteString) {
        return hmac("HmacSHA256", byteString);
    }

    public final ByteString hmacSha512(ByteString byteString) {
        return hmac("HmacSHA512", byteString);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b7) {
        return indexOf(b7, 0L, RecyclerView.FOREVER_NS);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString) {
        return indexOfElement(byteString, 0L);
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() { // from class: okio.Buffer.2
            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(Buffer.this.size, 2147483647L);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.InputStream
            public int read() {
                Buffer buffer = Buffer.this;
                if (buffer.size > 0) {
                    return buffer.readByte() & 255;
                }
                return -1;
            }

            public String toString() {
                return Buffer.this + ".inputStream()";
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i7, int i8) {
                return Buffer.this.read(bArr, i7, i8);
            }
        };
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    public final ByteString md5() {
        return digest("MD5");
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() { // from class: okio.Buffer.1
            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            public String toString() {
                return Buffer.this + ".outputStream()";
            }

            @Override // java.io.OutputStream
            public void write(int i7) {
                Buffer.this.writeByte((int) ((byte) i7));
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i7, int i8) {
                Buffer.this.write(bArr, i7, i8);
            }
        };
    }

    @Override // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j7, ByteString byteString) {
        return rangeEquals(j7, byteString, 0, byteString.size());
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // okio.BufferedSource
    public long readAll(Sink sink) {
        long j7 = this.size;
        if (j7 > 0) {
            sink.write(this, j7);
        }
        return j7;
    }

    public final UnsafeCursor readAndWriteUnsafe() {
        return readAndWriteUnsafe(new UnsafeCursor());
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        long j7 = this.size;
        if (j7 == 0) {
            throw new IllegalStateException("size == 0");
        }
        Segment segment = this.head;
        int i7 = segment.pos;
        int i8 = segment.limit;
        int i9 = i7 + 1;
        byte b7 = segment.data[i7];
        this.size = j7 - 1;
        if (i9 == i8) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i9;
        }
        return b7;
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() {
        try {
            return readByteArray(this.size);
        } catch (EOFException e7) {
            throw new AssertionError(e7);
        }
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003d, code lost:
    
        r1 = new okio.Buffer().writeDecimalLong(r3).writeByte((int) r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004a, code lost:
    
        if (r8 != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004c, code lost:
    
        r1.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004f, code lost:
    
        r3 = p009b.C0413b.m112a("Number too large: ");
        r3.append(r1.readUtf8());
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0065, code lost:
    
        throw new java.lang.NumberFormatException(r3.toString());
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a2  */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long readDecimalLong() {
        /*
            r17 = this;
            r0 = r17
            long r1 = r0.size
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto Lbd
            r1 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            r5 = -7
            r7 = 0
            r8 = 0
            r9 = 0
        L14:
            okio.Segment r10 = r0.head
            byte[] r11 = r10.data
            int r12 = r10.pos
            int r13 = r10.limit
        L1c:
            if (r12 >= r13) goto L96
            r14 = r11[r12]
            r15 = 48
            if (r14 < r15) goto L66
            r15 = 57
            if (r14 > r15) goto L66
            int r15 = 48 - r14
            int r16 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r16 < 0) goto L3d
            if (r16 != 0) goto L36
            long r1 = (long) r15
            int r16 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r16 >= 0) goto L36
            goto L3d
        L36:
            r1 = 10
            long r3 = r3 * r1
            long r1 = (long) r15
            long r3 = r3 + r1
            goto L70
        L3d:
            okio.Buffer r1 = new okio.Buffer
            r1.<init>()
            okio.Buffer r1 = r1.writeDecimalLong(r3)
            okio.Buffer r1 = r1.writeByte(r14)
            if (r8 != 0) goto L4f
            r1.readByte()
        L4f:
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            java.lang.String r3 = "Number too large: "
            java.lang.StringBuilder r3 = p009b.C0413b.m112a(r3)
            java.lang.String r1 = r1.readUtf8()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L66:
            r1 = 45
            if (r14 != r1) goto L7a
            if (r7 != 0) goto L7a
            r1 = 1
            long r5 = r5 - r1
            r8 = 1
        L70:
            int r12 = r12 + 1
            int r7 = r7 + 1
            r1 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            goto L1c
        L7a:
            if (r7 == 0) goto L7f
            r1 = 1
            r9 = 1
            goto L96
        L7f:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r2 = "Expected leading [0-9] or '-' character but was 0x"
            java.lang.StringBuilder r2 = p009b.C0413b.m112a(r2)
            java.lang.String r3 = java.lang.Integer.toHexString(r14)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L96:
            if (r12 != r13) goto La2
            okio.Segment r1 = r10.pop()
            r0.head = r1
            okio.SegmentPool.recycle(r10)
            goto La4
        La2:
            r10.pos = r12
        La4:
            if (r9 != 0) goto Lb2
            okio.Segment r1 = r0.head
            if (r1 != 0) goto Lab
            goto Lb2
        Lab:
            r1 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            goto L14
        Lb2:
            long r1 = r0.size
            long r5 = (long) r7
            long r1 = r1 - r5
            r0.size = r1
            if (r8 == 0) goto Lbb
            goto Lbc
        Lbb:
            long r3 = -r3
        Lbc:
            return r3
        Lbd:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "size == 0"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readDecimalLong():long");
    }

    public final Buffer readFrom(InputStream inputStream) throws IOException {
        readFrom(inputStream, RecyclerView.FOREVER_NS, true);
        return this;
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer buffer, long j7) throws EOFException {
        long j8 = this.size;
        if (j8 >= j7) {
            buffer.write(this, j7);
        } else {
            buffer.write(this, j8);
            throw new EOFException();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x009c A[EDGE_INSN: B:44:0x009c->B:38:0x009c BREAK  A[LOOP:0: B:5:0x000b->B:46:?], SYNTHETIC] */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long readHexadecimalUnsignedLong() {
        /*
            r15 = this;
            long r0 = r15.size
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto La3
            r0 = 0
            r1 = 0
            r4 = r2
        Lb:
            okio.Segment r6 = r15.head
            byte[] r7 = r6.data
            int r8 = r6.pos
            int r9 = r6.limit
        L13:
            if (r8 >= r9) goto L88
            r10 = r7[r8]
            r11 = 48
            if (r10 < r11) goto L22
            r11 = 57
            if (r10 > r11) goto L22
            int r11 = r10 + (-48)
            goto L39
        L22:
            r11 = 97
            if (r10 < r11) goto L2d
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L2d
            int r11 = r10 + (-97)
            goto L37
        L2d:
            r11 = 65
            if (r10 < r11) goto L6d
            r11 = 70
            if (r10 > r11) goto L6d
            int r11 = r10 + (-65)
        L37:
            int r11 = r11 + 10
        L39:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r14 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r14 != 0) goto L49
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L13
        L49:
            okio.Buffer r0 = new okio.Buffer
            r0.<init>()
            okio.Buffer r0 = r0.writeHexadecimalUnsignedLong(r4)
            okio.Buffer r0 = r0.writeByte(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r2 = "Number too large: "
            java.lang.StringBuilder r2 = p009b.C0413b.m112a(r2)
            java.lang.String r0 = r0.readUtf8()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L6d:
            if (r0 == 0) goto L71
            r1 = 1
            goto L88
        L71:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = "Expected leading [0-9a-fA-F] character but was 0x"
            java.lang.StringBuilder r1 = p009b.C0413b.m112a(r1)
            java.lang.String r2 = java.lang.Integer.toHexString(r10)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L88:
            if (r8 != r9) goto L94
            okio.Segment r7 = r6.pop()
            r15.head = r7
            okio.SegmentPool.recycle(r6)
            goto L96
        L94:
            r6.pos = r8
        L96:
            if (r1 != 0) goto L9c
            okio.Segment r6 = r15.head
            if (r6 != 0) goto Lb
        L9c:
            long r1 = r15.size
            long r6 = (long) r0
            long r1 = r1 - r6
            r15.size = r1
            return r4
        La3:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "size == 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readHexadecimalUnsignedLong():long");
    }

    @Override // okio.BufferedSource
    public int readInt() {
        long j7 = this.size;
        if (j7 < 4) {
            StringBuilder sbM112a = C0413b.m112a("size < 4: ");
            sbM112a.append(this.size);
            throw new IllegalStateException(sbM112a.toString());
        }
        Segment segment = this.head;
        int i7 = segment.pos;
        int i8 = segment.limit;
        if (i8 - i7 < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = segment.data;
        int i9 = i7 + 1;
        int i10 = i9 + 1;
        int i11 = ((bArr[i7] & 255) << 24) | ((bArr[i9] & 255) << 16);
        int i12 = i10 + 1;
        int i13 = i11 | ((bArr[i10] & 255) << 8);
        int i14 = i12 + 1;
        int i15 = i13 | (bArr[i12] & 255);
        this.size = j7 - 4;
        if (i14 == i8) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i14;
        }
        return i15;
    }

    @Override // okio.BufferedSource
    public int readIntLe() {
        return Util.reverseBytesInt(readInt());
    }

    @Override // okio.BufferedSource
    public long readLong() {
        long j7 = this.size;
        if (j7 < 8) {
            StringBuilder sbM112a = C0413b.m112a("size < 8: ");
            sbM112a.append(this.size);
            throw new IllegalStateException(sbM112a.toString());
        }
        Segment segment = this.head;
        int i7 = segment.pos;
        int i8 = segment.limit;
        if (i8 - i7 < 8) {
            return ((readInt() & 4294967295L) << 32) | (4294967295L & readInt());
        }
        byte[] bArr = segment.data;
        long j8 = (bArr[i7] & 255) << 56;
        int i9 = i7 + 1 + 1 + 1;
        long j9 = ((bArr[r8] & 255) << 48) | j8 | ((bArr[r3] & 255) << 40);
        long j10 = j9 | ((bArr[i9] & 255) << 32) | ((bArr[r3] & 255) << 24);
        long j11 = j10 | ((bArr[r6] & 255) << 16);
        long j12 = j11 | ((bArr[r3] & 255) << 8);
        int i10 = i9 + 1 + 1 + 1 + 1 + 1;
        long j13 = (bArr[r6] & 255) | j12;
        this.size = j7 - 8;
        if (i10 == i8) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i10;
        }
        return j13;
    }

    @Override // okio.BufferedSource
    public long readLongLe() {
        return Util.reverseBytesLong(readLong());
    }

    @Override // okio.BufferedSource
    public short readShort() {
        long j7 = this.size;
        if (j7 < 2) {
            StringBuilder sbM112a = C0413b.m112a("size < 2: ");
            sbM112a.append(this.size);
            throw new IllegalStateException(sbM112a.toString());
        }
        Segment segment = this.head;
        int i7 = segment.pos;
        int i8 = segment.limit;
        if (i8 - i7 < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = segment.data;
        int i9 = i7 + 1;
        int i10 = i9 + 1;
        int i11 = ((bArr[i7] & 255) << 8) | (bArr[i9] & 255);
        this.size = j7 - 2;
        if (i10 == i8) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i10;
        }
        return (short) i11;
    }

    @Override // okio.BufferedSource
    public short readShortLe() {
        return Util.reverseBytesShort(readShort());
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) {
        try {
            return readString(this.size, charset);
        } catch (EOFException e7) {
            throw new AssertionError(e7);
        }
    }

    public final UnsafeCursor readUnsafe() {
        return readUnsafe(new UnsafeCursor());
    }

    @Override // okio.BufferedSource
    public String readUtf8() {
        try {
            return readString(this.size, Util.UTF_8);
        } catch (EOFException e7) {
            throw new AssertionError(e7);
        }
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        int i7;
        int i8;
        int i9;
        if (this.size == 0) {
            throw new EOFException();
        }
        byte b7 = getByte(0L);
        if ((b7 & 128) == 0) {
            i7 = b7 & 127;
            i8 = 1;
            i9 = 0;
        } else if ((b7 & 224) == 192) {
            i7 = b7 & 31;
            i8 = 2;
            i9 = 128;
        } else if ((b7 & 240) == 224) {
            i7 = b7 & 15;
            i8 = 3;
            i9 = 2048;
        } else {
            if ((b7 & 248) != 240) {
                skip(1L);
                return REPLACEMENT_CHARACTER;
            }
            i7 = b7 & 7;
            i8 = 4;
            i9 = 65536;
        }
        long j7 = i8;
        if (this.size < j7) {
            StringBuilder sbM98a = C0116a.m98a("size < ", i8, ": ");
            sbM98a.append(this.size);
            sbM98a.append(" (to read code point prefixed 0x");
            sbM98a.append(Integer.toHexString(b7));
            sbM98a.append(")");
            throw new EOFException(sbM98a.toString());
        }
        for (int i10 = 1; i10 < i8; i10++) {
            long j8 = i10;
            byte b8 = getByte(j8);
            if ((b8 & 192) != 128) {
                skip(j8);
                return REPLACEMENT_CHARACTER;
            }
            i7 = (i7 << 6) | (b8 & 63);
        }
        skip(j7);
        return i7 > 1114111 ? REPLACEMENT_CHARACTER : ((i7 < 55296 || i7 > 57343) && i7 >= i9) ? i7 : REPLACEMENT_CHARACTER;
    }

    @Override // okio.BufferedSource
    @Nullable
    public String readUtf8Line() {
        long jIndexOf = indexOf((byte) 10);
        if (jIndexOf != -1) {
            return readUtf8Line(jIndexOf);
        }
        long j7 = this.size;
        if (j7 != 0) {
            return readUtf8(j7);
        }
        return null;
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() {
        return readUtf8LineStrict(RecyclerView.FOREVER_NS);
    }

    @Override // okio.BufferedSource
    public boolean request(long j7) {
        return this.size >= j7;
    }

    @Override // okio.BufferedSource
    public void require(long j7) throws EOFException {
        if (this.size < j7) {
            throw new EOFException();
        }
    }

    public List<Integer> segmentSizes() {
        if (this.head == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Segment segment = this.head;
        arrayList.add(Integer.valueOf(segment.limit - segment.pos));
        Segment segment2 = this.head;
        while (true) {
            segment2 = segment2.next;
            if (segment2 == this.head) {
                return arrayList;
            }
            arrayList.add(Integer.valueOf(segment2.limit - segment2.pos));
        }
    }

    @Override // okio.BufferedSource
    public int select(Options options) {
        int iSelectPrefix = selectPrefix(options, false);
        if (iSelectPrefix == -1) {
            return -1;
        }
        try {
            skip(options.byteStrings[iSelectPrefix].size());
            return iSelectPrefix;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0055, code lost:
    
        if (r19 == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0057, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0058, code lost:
    
        return r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int selectPrefix(okio.Options r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 161
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.selectPrefix(okio.Options, boolean):int");
    }

    public final ByteString sha1() {
        return digest("SHA-1");
    }

    public final ByteString sha256() {
        return digest("SHA-256");
    }

    public final ByteString sha512() {
        return digest("SHA-512");
    }

    public final long size() {
        return this.size;
    }

    @Override // okio.BufferedSource
    public void skip(long j7) throws EOFException {
        while (j7 > 0) {
            if (this.head == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j7, r0.limit - r0.pos);
            long j8 = iMin;
            this.size -= j8;
            j7 -= j8;
            Segment segment = this.head;
            int i7 = segment.pos + iMin;
            segment.pos = i7;
            if (i7 == segment.limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }

    public final ByteString snapshot() {
        long j7 = this.size;
        if (j7 <= 2147483647L) {
            return snapshot((int) j7);
        }
        StringBuilder sbM112a = C0413b.m112a("size > Integer.MAX_VALUE: ");
        sbM112a.append(this.size);
        throw new IllegalArgumentException(sbM112a.toString());
    }

    @Override // okio.Source
    public Timeout timeout() {
        return Timeout.NONE;
    }

    public String toString() {
        return snapshot().toString();
    }

    public Segment writableSegment(int i7) {
        if (i7 < 1 || i7 > 8192) {
            throw new IllegalArgumentException();
        }
        Segment segment = this.head;
        if (segment != null) {
            Segment segment2 = segment.prev;
            return (segment2.limit + i7 > 8192 || !segment2.owner) ? segment2.push(SegmentPool.take()) : segment2;
        }
        Segment segmentTake = SegmentPool.take();
        this.head = segmentTake;
        segmentTake.prev = segmentTake;
        segmentTake.next = segmentTake;
        return segmentTake;
    }

    @Override // okio.BufferedSink
    public long writeAll(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j7 = 0;
        while (true) {
            long j8 = source.read(this, 8192L);
            if (j8 == -1) {
                return j7;
            }
            j7 += j8;
        }
    }

    public final Buffer writeTo(OutputStream outputStream) {
        return writeTo(outputStream, this.size);
    }

    public Buffer clone() {
        Buffer buffer = new Buffer();
        if (this.size == 0) {
            return buffer;
        }
        Segment segmentSharedCopy = this.head.sharedCopy();
        buffer.head = segmentSharedCopy;
        segmentSharedCopy.prev = segmentSharedCopy;
        segmentSharedCopy.next = segmentSharedCopy;
        Segment segment = this.head;
        while (true) {
            segment = segment.next;
            if (segment == this.head) {
                buffer.size = this.size;
                return buffer;
            }
            buffer.head.prev.push(segment.sharedCopy());
        }
    }

    public final Buffer copyTo(OutputStream outputStream, long j7, long j8) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, j7, j8);
        if (j8 == 0) {
            return this;
        }
        Segment segment = this.head;
        while (true) {
            int i7 = segment.limit;
            int i8 = segment.pos;
            if (j7 < i7 - i8) {
                break;
            }
            j7 -= i7 - i8;
            segment = segment.next;
        }
        while (j8 > 0) {
            int iMin = (int) Math.min(segment.limit - r10, j8);
            outputStream.write(segment.data, (int) (segment.pos + j7), iMin);
            j8 -= iMin;
            segment = segment.next;
            j7 = 0;
        }
        return this;
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b7, long j7) {
        return indexOf(b7, j7, RecyclerView.FOREVER_NS);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString, long j7) {
        int i7;
        int i8;
        long j8 = 0;
        if (j7 < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        Segment segment = this.head;
        if (segment == null) {
            return -1L;
        }
        long j9 = this.size;
        if (j9 - j7 < j7) {
            while (j9 > j7) {
                segment = segment.prev;
                j9 -= segment.limit - segment.pos;
            }
        } else {
            while (true) {
                long j10 = (segment.limit - segment.pos) + j8;
                if (j10 >= j7) {
                    break;
                }
                segment = segment.next;
                j8 = j10;
            }
            j9 = j8;
        }
        if (byteString.size() == 2) {
            byte b7 = byteString.getByte(0);
            byte b8 = byteString.getByte(1);
            while (j9 < this.size) {
                byte[] bArr = segment.data;
                i7 = (int) ((segment.pos + j7) - j9);
                int i9 = segment.limit;
                while (i7 < i9) {
                    byte b9 = bArr[i7];
                    if (b9 == b7 || b9 == b8) {
                        i8 = segment.pos;
                        return (i7 - i8) + j9;
                    }
                    i7++;
                }
                j9 += segment.limit - segment.pos;
                segment = segment.next;
                j7 = j9;
            }
            return -1L;
        }
        byte[] bArrInternalArray = byteString.internalArray();
        while (j9 < this.size) {
            byte[] bArr2 = segment.data;
            i7 = (int) ((segment.pos + j7) - j9);
            int i10 = segment.limit;
            while (i7 < i10) {
                byte b10 = bArr2[i7];
                for (byte b11 : bArrInternalArray) {
                    if (b10 == b11) {
                        i8 = segment.pos;
                        return (i7 - i8) + j9;
                    }
                }
                i7++;
            }
            j9 += segment.limit - segment.pos;
            segment = segment.next;
            j7 = j9;
        }
        return -1L;
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j7, ByteString byteString, int i7, int i8) {
        if (j7 < 0 || i7 < 0 || i8 < 0 || this.size - j7 < i8 || byteString.size() - i7 < i8) {
            return false;
        }
        for (int i9 = 0; i9 < i8; i9++) {
            if (getByte(i9 + j7) != byteString.getByte(i7 + i9)) {
                return false;
            }
        }
        return true;
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr, int i7, int i8) {
        Util.checkOffsetAndCount(bArr.length, i7, i8);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int iMin = Math.min(i8, segment.limit - segment.pos);
        System.arraycopy(segment.data, segment.pos, bArr, i7, iMin);
        int i9 = segment.pos + iMin;
        segment.pos = i9;
        this.size -= iMin;
        if (i9 == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return iMin;
    }

    public final UnsafeCursor readAndWriteUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        unsafeCursor.buffer = this;
        unsafeCursor.readWrite = true;
        return unsafeCursor;
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long j7) {
        return new ByteString(readByteArray(j7));
    }

    public final Buffer readFrom(InputStream inputStream, long j7) throws IOException {
        if (j7 < 0) {
            throw new IllegalArgumentException(C0534b.m341a("byteCount < 0: ", j7));
        }
        readFrom(inputStream, j7, false);
        return this;
    }

    public final UnsafeCursor readUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        unsafeCursor.buffer = this;
        unsafeCursor.readWrite = false;
        return unsafeCursor;
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long j7) throws EOFException {
        if (j7 < 0) {
            throw new IllegalArgumentException(C0534b.m341a("limit < 0: ", j7));
        }
        long j8 = RecyclerView.FOREVER_NS;
        if (j7 != RecyclerView.FOREVER_NS) {
            j8 = j7 + 1;
        }
        long jIndexOf = indexOf((byte) 10, 0L, j8);
        if (jIndexOf != -1) {
            return readUtf8Line(jIndexOf);
        }
        if (j8 < size() && getByte(j8 - 1) == 13 && getByte(j8) == 10) {
            return readUtf8Line(j8);
        }
        Buffer buffer = new Buffer();
        copyTo(buffer, 0L, Math.min(32L, size()));
        StringBuilder sbM112a = C0413b.m112a("\\n not found: limit=");
        sbM112a.append(Math.min(size(), j7));
        sbM112a.append(" content=");
        sbM112a.append(buffer.readByteString().hex());
        sbM112a.append((char) 8230);
        throw new EOFException(sbM112a.toString());
    }

    @Override // okio.BufferedSink
    public Buffer writeByte(int i7) {
        Segment segmentWritableSegment = writableSegment(1);
        byte[] bArr = segmentWritableSegment.data;
        int i8 = segmentWritableSegment.limit;
        segmentWritableSegment.limit = i8 + 1;
        bArr[i8] = (byte) i7;
        this.size++;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeDecimalLong(long j7) {
        if (j7 == 0) {
            return writeByte(48);
        }
        boolean z6 = false;
        int i7 = 1;
        if (j7 < 0) {
            j7 = -j7;
            if (j7 < 0) {
                return writeUtf8("-9223372036854775808");
            }
            z6 = true;
        }
        if (j7 >= 100000000) {
            i7 = j7 < 1000000000000L ? j7 < 10000000000L ? j7 < 1000000000 ? 9 : 10 : j7 < 100000000000L ? 11 : 12 : j7 < 1000000000000000L ? j7 < 10000000000000L ? 13 : j7 < 100000000000000L ? 14 : 15 : j7 < 100000000000000000L ? j7 < 10000000000000000L ? 16 : 17 : j7 < 1000000000000000000L ? 18 : 19;
        } else if (j7 >= 10000) {
            i7 = j7 < 1000000 ? j7 < 100000 ? 5 : 6 : j7 < 10000000 ? 7 : 8;
        } else if (j7 >= 100) {
            i7 = j7 < 1000 ? 3 : 4;
        } else if (j7 >= 10) {
            i7 = 2;
        }
        if (z6) {
            i7++;
        }
        Segment segmentWritableSegment = writableSegment(i7);
        byte[] bArr = segmentWritableSegment.data;
        int i8 = segmentWritableSegment.limit + i7;
        while (j7 != 0) {
            i8--;
            bArr[i8] = DIGITS[(int) (j7 % 10)];
            j7 /= 10;
        }
        if (z6) {
            bArr[i8 - 1] = 45;
        }
        segmentWritableSegment.limit += i7;
        this.size += i7;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeHexadecimalUnsignedLong(long j7) {
        if (j7 == 0) {
            return writeByte(48);
        }
        int iNumberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j7)) / 4) + 1;
        Segment segmentWritableSegment = writableSegment(iNumberOfTrailingZeros);
        byte[] bArr = segmentWritableSegment.data;
        int i7 = segmentWritableSegment.limit;
        for (int i8 = (i7 + iNumberOfTrailingZeros) - 1; i8 >= i7; i8--) {
            bArr[i8] = DIGITS[(int) (15 & j7)];
            j7 >>>= 4;
        }
        segmentWritableSegment.limit += iNumberOfTrailingZeros;
        this.size += iNumberOfTrailingZeros;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeInt(int i7) {
        Segment segmentWritableSegment = writableSegment(4);
        byte[] bArr = segmentWritableSegment.data;
        int i8 = segmentWritableSegment.limit;
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((i7 >>> 24) & 255);
        int i10 = i9 + 1;
        bArr[i9] = (byte) ((i7 >>> 16) & 255);
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((i7 >>> 8) & 255);
        bArr[i11] = (byte) (i7 & 255);
        segmentWritableSegment.limit = i11 + 1;
        this.size += 4;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeIntLe(int i7) {
        return writeInt(Util.reverseBytesInt(i7));
    }

    @Override // okio.BufferedSink
    public Buffer writeLong(long j7) {
        Segment segmentWritableSegment = writableSegment(8);
        byte[] bArr = segmentWritableSegment.data;
        int i7 = segmentWritableSegment.limit;
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((j7 >>> 56) & 255);
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((j7 >>> 48) & 255);
        int i10 = i9 + 1;
        bArr[i9] = (byte) ((j7 >>> 40) & 255);
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((j7 >>> 32) & 255);
        int i12 = i11 + 1;
        bArr[i11] = (byte) ((j7 >>> 24) & 255);
        int i13 = i12 + 1;
        bArr[i12] = (byte) ((j7 >>> 16) & 255);
        int i14 = i13 + 1;
        bArr[i13] = (byte) ((j7 >>> 8) & 255);
        bArr[i14] = (byte) (j7 & 255);
        segmentWritableSegment.limit = i14 + 1;
        this.size += 8;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeLongLe(long j7) {
        return writeLong(Util.reverseBytesLong(j7));
    }

    @Override // okio.BufferedSink
    public Buffer writeShort(int i7) {
        Segment segmentWritableSegment = writableSegment(2);
        byte[] bArr = segmentWritableSegment.data;
        int i8 = segmentWritableSegment.limit;
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((i7 >>> 8) & 255);
        bArr[i9] = (byte) (i7 & 255);
        segmentWritableSegment.limit = i9 + 1;
        this.size += 2;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShortLe(int i7) {
        return writeShort((int) Util.reverseBytesShort((short) i7));
    }

    public final Buffer writeTo(OutputStream outputStream, long j7) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, 0L, j7);
        Segment segment = this.head;
        while (j7 > 0) {
            int iMin = (int) Math.min(j7, segment.limit - segment.pos);
            outputStream.write(segment.data, segment.pos, iMin);
            int i7 = segment.pos + iMin;
            segment.pos = i7;
            long j8 = iMin;
            this.size -= j8;
            j7 -= j8;
            if (i7 == segment.limit) {
                Segment segmentPop = segment.pop();
                this.head = segmentPop;
                SegmentPool.recycle(segment);
                segment = segmentPop;
            }
        }
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8CodePoint(int i7) {
        if (i7 < 128) {
            writeByte(i7);
        } else if (i7 < 2048) {
            writeByte((i7 >> 6) | Opcodes.CHECKCAST);
            writeByte((i7 & 63) | 128);
        } else if (i7 < 65536) {
            if (i7 < 55296 || i7 > 57343) {
                writeByte((i7 >> 12) | 224);
                writeByte(((i7 >> 6) & 63) | 128);
                writeByte((i7 & 63) | 128);
            } else {
                writeByte(63);
            }
        } else {
            if (i7 > 1114111) {
                StringBuilder sbM112a = C0413b.m112a("Unexpected code point: ");
                sbM112a.append(Integer.toHexString(i7));
                throw new IllegalArgumentException(sbM112a.toString());
            }
            writeByte((i7 >> 18) | 240);
            writeByte(((i7 >> 12) & 63) | 128);
            writeByte(((i7 >> 6) & 63) | 128);
            writeByte((i7 & 63) | 128);
        }
        return this;
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b7, long j7, long j8) {
        Segment segment;
        long j9 = 0;
        if (j7 >= 0 && j8 >= j7) {
            long j10 = this.size;
            long j11 = j8 > j10 ? j10 : j8;
            if (j7 == j11 || (segment = this.head) == null) {
                return -1L;
            }
            if (j10 - j7 < j7) {
                while (j10 > j7) {
                    segment = segment.prev;
                    j10 -= segment.limit - segment.pos;
                }
            } else {
                while (true) {
                    long j12 = (segment.limit - segment.pos) + j9;
                    if (j12 >= j7) {
                        break;
                    }
                    segment = segment.next;
                    j9 = j12;
                }
                j10 = j9;
            }
            long j13 = j7;
            while (j10 < j11) {
                byte[] bArr = segment.data;
                int iMin = (int) Math.min(segment.limit, (segment.pos + j11) - j10);
                for (int i7 = (int) ((segment.pos + j13) - j10); i7 < iMin; i7++) {
                    if (bArr[i7] == b7) {
                        return (i7 - segment.pos) + j10;
                    }
                }
                j10 += segment.limit - segment.pos;
                segment = segment.next;
                j13 = j10;
            }
            return -1L;
        }
        throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.size), Long.valueOf(j7), Long.valueOf(j8)));
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray(long j7) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, j7);
        if (j7 <= 2147483647L) {
            byte[] bArr = new byte[(int) j7];
            readFully(bArr);
            return bArr;
        }
        throw new IllegalArgumentException(C0534b.m341a("byteCount > Integer.MAX_VALUE: ", j7));
    }

    @Override // okio.BufferedSource
    public String readString(long j7, Charset charset) {
        Util.checkOffsetAndCount(this.size, 0L, j7);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j7 > 2147483647L) {
            throw new IllegalArgumentException(C0534b.m341a("byteCount > Integer.MAX_VALUE: ", j7));
        }
        if (j7 == 0) {
            return "";
        }
        Segment segment = this.head;
        int i7 = segment.pos;
        if (i7 + j7 > segment.limit) {
            return new String(readByteArray(j7), charset);
        }
        String str = new String(segment.data, i7, (int) j7, charset);
        int i8 = (int) (segment.pos + j7);
        segment.pos = i8;
        this.size -= j7;
        if (i8 == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return str;
    }

    @Override // okio.BufferedSource
    public String readUtf8(long j7) {
        return readString(j7, Util.UTF_8);
    }

    @Override // okio.BufferedSink
    public Buffer writeString(String str, Charset charset) {
        return writeString(str, 0, str.length(), charset);
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String str) {
        return writeUtf8(str, 0, str.length());
    }

    private void readFrom(InputStream inputStream, long j7, boolean z6) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (true) {
            if (j7 <= 0 && !z6) {
                return;
            }
            Segment segmentWritableSegment = writableSegment(1);
            int i7 = inputStream.read(segmentWritableSegment.data, segmentWritableSegment.limit, (int) Math.min(j7, 8192 - segmentWritableSegment.limit));
            if (i7 == -1) {
                if (!z6) {
                    throw new EOFException();
                }
                return;
            } else {
                segmentWritableSegment.limit += i7;
                long j8 = i7;
                this.size += j8;
                j7 -= j8;
            }
        }
    }

    public String readUtf8Line(long j7) throws EOFException {
        if (j7 > 0) {
            long j8 = j7 - 1;
            if (getByte(j8) == 13) {
                String utf8 = readUtf8(j8);
                skip(2L);
                return utf8;
            }
        }
        String utf82 = readUtf8(j7);
        skip(1L);
        return utf82;
    }

    public final ByteString snapshot(int i7) {
        if (i7 == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, i7);
    }

    @Override // okio.BufferedSink
    public Buffer write(ByteString byteString) {
        if (byteString != null) {
            byteString.write(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    @Override // okio.BufferedSink
    public Buffer writeString(String str, int i7, int i8, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i7 < 0) {
            throw new IllegalAccessError(C0079a.m93a("beginIndex < 0: ", i7));
        }
        if (i8 >= i7) {
            if (i8 > str.length()) {
                StringBuilder sbM98a = C0116a.m98a("endIndex > string.length: ", i8, " > ");
                sbM98a.append(str.length());
                throw new IllegalArgumentException(sbM98a.toString());
            }
            if (charset != null) {
                if (charset.equals(Util.UTF_8)) {
                    return writeUtf8(str, i7, i8);
                }
                byte[] bytes = str.substring(i7, i8).getBytes(charset);
                return write(bytes, 0, bytes.length);
            }
            throw new IllegalArgumentException("charset == null");
        }
        throw new IllegalArgumentException("endIndex < beginIndex: " + i8 + " < " + i7);
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String str, int i7, int i8) {
        char cCharAt;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i7 < 0) {
            throw new IllegalArgumentException(C0079a.m93a("beginIndex < 0: ", i7));
        }
        if (i8 >= i7) {
            if (i8 > str.length()) {
                StringBuilder sbM98a = C0116a.m98a("endIndex > string.length: ", i8, " > ");
                sbM98a.append(str.length());
                throw new IllegalArgumentException(sbM98a.toString());
            }
            while (i7 < i8) {
                char cCharAt2 = str.charAt(i7);
                if (cCharAt2 < 128) {
                    Segment segmentWritableSegment = writableSegment(1);
                    byte[] bArr = segmentWritableSegment.data;
                    int i9 = segmentWritableSegment.limit - i7;
                    int iMin = Math.min(i8, 8192 - i9);
                    int i10 = i7 + 1;
                    bArr[i7 + i9] = (byte) cCharAt2;
                    while (true) {
                        i7 = i10;
                        if (i7 >= iMin || (cCharAt = str.charAt(i7)) >= 128) {
                            break;
                        }
                        i10 = i7 + 1;
                        bArr[i7 + i9] = (byte) cCharAt;
                    }
                    int i11 = segmentWritableSegment.limit;
                    int i12 = (i9 + i7) - i11;
                    segmentWritableSegment.limit = i11 + i12;
                    this.size += i12;
                } else {
                    if (cCharAt2 < 2048) {
                        writeByte((cCharAt2 >> 6) | Opcodes.CHECKCAST);
                        writeByte((cCharAt2 & '?') | 128);
                    } else if (cCharAt2 >= 55296 && cCharAt2 <= 57343) {
                        int i13 = i7 + 1;
                        char cCharAt3 = i13 < i8 ? str.charAt(i13) : (char) 0;
                        if (cCharAt2 <= 56319 && cCharAt3 >= 56320 && cCharAt3 <= 57343) {
                            int i14 = (((cCharAt2 & 10239) << 10) | (9215 & cCharAt3)) + 65536;
                            writeByte((i14 >> 18) | 240);
                            writeByte(((i14 >> 12) & 63) | 128);
                            writeByte(((i14 >> 6) & 63) | 128);
                            writeByte((i14 & 63) | 128);
                            i7 += 2;
                        } else {
                            writeByte(63);
                            i7 = i13;
                        }
                    } else {
                        writeByte((cCharAt2 >> '\f') | 224);
                        writeByte(((cCharAt2 >> 6) & 63) | 128);
                        writeByte((cCharAt2 & '?') | 128);
                    }
                    i7++;
                }
            }
            return this;
        }
        throw new IllegalArgumentException("endIndex < beginIndex: " + i8 + " < " + i7);
    }

    private boolean rangeEquals(Segment segment, int i7, ByteString byteString, int i8, int i9) {
        int i10 = segment.limit;
        byte[] bArr = segment.data;
        while (i8 < i9) {
            if (i7 == i10) {
                segment = segment.next;
                byte[] bArr2 = segment.data;
                bArr = bArr2;
                i7 = segment.pos;
                i10 = segment.limit;
            }
            if (bArr[i7] != byteString.getByte(i8)) {
                return false;
            }
            i7++;
            i8++;
        }
        return true;
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] bArr) throws EOFException {
        int i7 = 0;
        while (i7 < bArr.length) {
            int i8 = read(bArr, i7, bArr.length - i7);
            if (i8 == -1) {
                throw new EOFException();
            }
            i7 += i8;
        }
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] bArr) {
        if (bArr != null) {
            return write(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] bArr, int i7, int i8) {
        if (bArr != null) {
            long j7 = i8;
            Util.checkOffsetAndCount(bArr.length, i7, j7);
            int i9 = i8 + i7;
            while (i7 < i9) {
                Segment segmentWritableSegment = writableSegment(1);
                int iMin = Math.min(i9 - i7, 8192 - segmentWritableSegment.limit);
                System.arraycopy(bArr, i7, segmentWritableSegment.data, segmentWritableSegment.limit, iMin);
                i7 += iMin;
                segmentWritableSegment.limit += iMin;
            }
            this.size += j7;
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    public final Buffer copyTo(Buffer buffer, long j7, long j8) {
        if (buffer != null) {
            Util.checkOffsetAndCount(this.size, j7, j8);
            if (j8 == 0) {
                return this;
            }
            buffer.size += j8;
            Segment segment = this.head;
            while (true) {
                int i7 = segment.limit;
                int i8 = segment.pos;
                if (j7 < i7 - i8) {
                    break;
                }
                j7 -= i7 - i8;
                segment = segment.next;
            }
            while (j8 > 0) {
                Segment segmentSharedCopy = segment.sharedCopy();
                int i9 = (int) (segmentSharedCopy.pos + j7);
                segmentSharedCopy.pos = i9;
                segmentSharedCopy.limit = Math.min(i9 + ((int) j8), segmentSharedCopy.limit);
                Segment segment2 = buffer.head;
                if (segment2 == null) {
                    segmentSharedCopy.prev = segmentSharedCopy;
                    segmentSharedCopy.next = segmentSharedCopy;
                    buffer.head = segmentSharedCopy;
                } else {
                    segment2.prev.push(segmentSharedCopy);
                }
                j8 -= segmentSharedCopy.limit - segmentSharedCopy.pos;
                segment = segment.next;
                j7 = 0;
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), segment.limit - segment.pos);
        byteBuffer.put(segment.data, segment.pos, iMin);
        int i7 = segment.pos + iMin;
        segment.pos = i7;
        this.size -= iMin;
        if (i7 == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return iMin;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            int iRemaining = byteBuffer.remaining();
            int i7 = iRemaining;
            while (i7 > 0) {
                Segment segmentWritableSegment = writableSegment(1);
                int iMin = Math.min(i7, 8192 - segmentWritableSegment.limit);
                byteBuffer.get(segmentWritableSegment.data, segmentWritableSegment.limit, iMin);
                i7 -= iMin;
                segmentWritableSegment.limit += iMin;
            }
            this.size += iRemaining;
            return iRemaining;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString) {
        return indexOf(byteString, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString, long j7) {
        byte[] bArr;
        if (byteString.size() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        long j8 = 0;
        if (j7 >= 0) {
            Segment segment = this.head;
            long j9 = -1;
            if (segment == null) {
                return -1L;
            }
            long j10 = this.size;
            if (j10 - j7 < j7) {
                while (j10 > j7) {
                    segment = segment.prev;
                    j10 -= segment.limit - segment.pos;
                }
            } else {
                while (true) {
                    long j11 = (segment.limit - segment.pos) + j8;
                    if (j11 >= j7) {
                        break;
                    }
                    segment = segment.next;
                    j8 = j11;
                }
                j10 = j8;
            }
            byte b7 = byteString.getByte(0);
            int size = byteString.size();
            long j12 = 1 + (this.size - size);
            long j13 = j7;
            Segment segment2 = segment;
            long j14 = j10;
            while (j14 < j12) {
                byte[] bArr2 = segment2.data;
                int iMin = (int) Math.min(segment2.limit, (segment2.pos + j12) - j14);
                int i7 = (int) ((segment2.pos + j13) - j14);
                while (i7 < iMin) {
                    if (bArr2[i7] == b7) {
                        bArr = bArr2;
                        if (rangeEquals(segment2, i7 + 1, byteString, 1, size)) {
                            return (i7 - segment2.pos) + j14;
                        }
                    } else {
                        bArr = bArr2;
                    }
                    i7++;
                    bArr2 = bArr;
                }
                j14 += segment2.limit - segment2.pos;
                segment2 = segment2.next;
                j13 = j14;
                j9 = -1;
            }
            return j9;
        }
        throw new IllegalArgumentException("fromIndex < 0");
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j7) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j7 >= 0) {
            long j8 = this.size;
            if (j8 == 0) {
                return -1L;
            }
            if (j7 > j8) {
                j7 = j8;
            }
            buffer.write(this, j7);
            return j7;
        }
        throw new IllegalArgumentException(C0534b.m341a("byteCount < 0: ", j7));
    }

    @Override // okio.BufferedSink
    public BufferedSink write(Source source, long j7) throws EOFException {
        while (j7 > 0) {
            long j8 = source.read(this, j7);
            if (j8 == -1) {
                throw new EOFException();
            }
            j7 -= j8;
        }
        return this;
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j7) {
        if (buffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (buffer != this) {
            Util.checkOffsetAndCount(buffer.size, 0L, j7);
            while (j7 > 0) {
                Segment segment = buffer.head;
                if (j7 < segment.limit - segment.pos) {
                    Segment segment2 = this.head;
                    Segment segment3 = segment2 != null ? segment2.prev : null;
                    if (segment3 != null && segment3.owner) {
                        if ((segment3.limit + j7) - (segment3.shared ? 0 : segment3.pos) <= 8192) {
                            segment.writeTo(segment3, (int) j7);
                            buffer.size -= j7;
                            this.size += j7;
                            return;
                        }
                    }
                    buffer.head = segment.split((int) j7);
                }
                Segment segment4 = buffer.head;
                long j8 = segment4.limit - segment4.pos;
                buffer.head = segment4.pop();
                Segment segment5 = this.head;
                if (segment5 == null) {
                    this.head = segment4;
                    segment4.prev = segment4;
                    segment4.next = segment4;
                } else {
                    segment5.prev.push(segment4).compact();
                }
                buffer.size -= j8;
                this.size += j8;
                j7 -= j8;
            }
            return;
        }
        throw new IllegalArgumentException("source == this");
    }
}
