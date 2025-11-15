package p142r0;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: ByteBufferUtil.java */
/* renamed from: r0.a */
/* loaded from: classes.dex */
public final class C1816a {

    /* renamed from: a */
    public static final /* synthetic */ int f5280a = 0;

    /* compiled from: ByteBufferUtil.java */
    /* renamed from: r0.a$b */
    public static final class b {

        /* renamed from: a */
        public final int f5283a;

        /* renamed from: b */
        public final int f5284b;

        /* renamed from: c */
        public final byte[] f5285c;

        public b(byte[] bArr, int i7, int i8) {
            this.f5285c = bArr;
            this.f5283a = i7;
            this.f5284b = i8;
        }
    }

    static {
        new AtomicReference();
    }

    /* renamed from: a */
    public static ByteBuffer m2046a(File file) throws Throwable {
        RandomAccessFile randomAccessFile;
        FileChannel channel = null;
        try {
            long length = file.length();
            if (length > 2147483647L) {
                throw new IOException("File too large to map into memory");
            }
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                channel = randomAccessFile.getChannel();
                MappedByteBuffer mappedByteBufferLoad = channel.map(FileChannel.MapMode.READ_ONLY, 0L, length).load();
                try {
                    channel.close();
                } catch (IOException unused) {
                }
                try {
                    randomAccessFile.close();
                } catch (IOException unused2) {
                }
                return mappedByteBufferLoad;
            } catch (Throwable th) {
                th = th;
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException unused3) {
                    }
                }
                if (randomAccessFile == null) {
                    throw th;
                }
                try {
                    randomAccessFile.close();
                    throw th;
                } catch (IOException unused4) {
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
        }
    }

    /* renamed from: b */
    public static void m2047b(ByteBuffer byteBuffer, File file) throws Throwable {
        RandomAccessFile randomAccessFile;
        byteBuffer.position(0);
        FileChannel channel = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
        } catch (Throwable th) {
            th = th;
            randomAccessFile = null;
        }
        try {
            channel = randomAccessFile.getChannel();
            channel.write(byteBuffer);
            channel.force(false);
            channel.close();
            randomAccessFile.close();
            try {
                channel.close();
            } catch (IOException unused) {
            }
            try {
                randomAccessFile.close();
            } catch (IOException unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException unused3) {
                }
            }
            if (randomAccessFile == null) {
                throw th;
            }
            try {
                randomAccessFile.close();
                throw th;
            } catch (IOException unused4) {
                throw th;
            }
        }
    }

    /* compiled from: ByteBufferUtil.java */
    /* renamed from: r0.a$a */
    public static class a extends InputStream {

        /* renamed from: e */
        public final ByteBuffer f5281e;

        /* renamed from: f */
        public int f5282f = -1;

        public a(ByteBuffer byteBuffer) {
            this.f5281e = byteBuffer;
        }

        @Override // java.io.InputStream
        public int available() {
            return this.f5281e.remaining();
        }

        @Override // java.io.InputStream
        public synchronized void mark(int i7) {
            this.f5282f = this.f5281e.position();
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.f5281e.hasRemaining()) {
                return this.f5281e.get();
            }
            return -1;
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            int i7 = this.f5282f;
            if (i7 == -1) {
                throw new IOException("Cannot reset to unset mark position");
            }
            this.f5281e.position(i7);
        }

        @Override // java.io.InputStream
        public long skip(long j7) {
            if (!this.f5281e.hasRemaining()) {
                return -1L;
            }
            long jMin = Math.min(j7, available());
            this.f5281e.position((int) (r0.position() + jMin));
            return jMin;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i7, int i8) {
            if (!this.f5281e.hasRemaining()) {
                return -1;
            }
            int iMin = Math.min(i8, available());
            this.f5281e.get(bArr, i7, iMin);
            return iMin;
        }
    }
}
