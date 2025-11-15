package p140q6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import p159t3.C1903b;

/* compiled from: Request.java */
/* renamed from: q6.r */
/* loaded from: classes.dex */
public class C1797r {

    /* renamed from: a */
    public String f5125a;

    /* renamed from: c */
    public InputStream f5127c;

    /* renamed from: d */
    public OutputStream f5128d;

    /* renamed from: e */
    public char[] f5129e = new char[2048];

    /* renamed from: h */
    public C1903b f5132h = null;

    /* renamed from: b */
    public SocketChannel f5126b = this.f5126b;

    /* renamed from: b */
    public SocketChannel f5126b = this.f5126b;

    /* renamed from: f */
    public int f5130f = 0;

    /* renamed from: g */
    public StringBuffer f5131g = new StringBuffer();

    /* compiled from: Request.java */
    /* renamed from: q6.r$a */
    public static class a extends InputStream {

        /* renamed from: q */
        public static long f5133q = C1800u.f5165b;

        /* renamed from: e */
        public SocketChannel f5134e;

        /* renamed from: f */
        public C1799t f5135f;

        /* renamed from: g */
        public Selector f5136g;

        /* renamed from: h */
        public ByteBuffer f5137h;

        /* renamed from: i */
        public int f5138i;

        /* renamed from: j */
        public byte[] f5139j;

        /* renamed from: k */
        public boolean f5140k;

        /* renamed from: l */
        public boolean f5141l = false;

        /* renamed from: m */
        public ByteBuffer f5142m;

        /* renamed from: n */
        public boolean f5143n;

        /* renamed from: o */
        public boolean f5144o;

        /* renamed from: p */
        public C1801v f5145p;

        public a(C1801v c1801v, SocketChannel socketChannel) {
            this.f5140k = false;
            this.f5134e = socketChannel;
            this.f5145p = c1801v;
            C1799t c1799tM2003c = C1799t.m2003c();
            this.f5135f = c1799tM2003c;
            this.f5136g = c1799tM2003c.m2005b();
            this.f5137h = ByteBuffer.allocate(8192);
            socketChannel.register(this.f5136g, 1);
            this.f5138i = 0;
            this.f5139j = new byte[1];
            this.f5144o = false;
            this.f5143n = false;
            this.f5140k = false;
        }

        /* renamed from: a */
        public final synchronized void m2001a() {
            long j7 = this.f5145p.f5191p;
            long j8 = f5133q + j7;
            while (j7 < j8) {
                if (this.f5136g.select(f5133q) == 1) {
                    this.f5136g.selectedKeys().clear();
                    available();
                } else {
                    j7 = this.f5145p.f5191p;
                }
            }
            throw new SocketTimeoutException("no data received");
        }

        @Override // java.io.InputStream
        public synchronized int available() {
            if (this.f5140k) {
                throw new IOException("Stream is closed");
            }
            if (this.f5141l) {
                return -1;
            }
            if (this.f5144o) {
                return this.f5142m.remaining();
            }
            int i7 = this.f5138i;
            if (i7 > 0) {
                return i7;
            }
            this.f5137h.clear();
            int i8 = this.f5134e.read(this.f5137h);
            this.f5138i = i8;
            if (i8 > 0) {
                this.f5137h.flip();
            } else if (i8 == -1) {
                this.f5141l = true;
                this.f5138i = 0;
            }
            return this.f5138i;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f5140k) {
                return;
            }
            this.f5134e.close();
            this.f5136g.selectNow();
            this.f5135f.m2004a(this.f5136g);
            this.f5140k = true;
        }

        @Override // java.io.InputStream
        public synchronized void mark(int i7) {
            if (this.f5140k) {
                return;
            }
            this.f5142m = ByteBuffer.allocate(i7);
            this.f5143n = true;
            this.f5144o = false;
        }

        @Override // java.io.InputStream
        public synchronized int read(byte[] bArr) {
            return read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            if (this.f5140k) {
                return;
            }
            if (!this.f5143n) {
                throw new IOException("Stream not marked");
            }
            this.f5143n = false;
            this.f5144o = true;
            this.f5142m.flip();
        }

        @Override // java.io.InputStream
        public synchronized int read() {
            if (read(this.f5139j, 0, 1) != 1) {
                return -1;
            }
            return this.f5139j[0] & 255;
        }

        @Override // java.io.InputStream
        public synchronized int read(byte[] bArr, int i7, int i8) {
            if (!this.f5140k) {
                if (this.f5141l) {
                    return -1;
                }
                if (this.f5144o) {
                    int iRemaining = this.f5142m.remaining();
                    if (iRemaining <= i8) {
                        i8 = iRemaining;
                    }
                    this.f5142m.get(bArr, i7, i8);
                    if (iRemaining == i8) {
                        this.f5144o = false;
                    }
                } else {
                    int iAvailable = available();
                    while (iAvailable == 0 && !this.f5141l) {
                        m2001a();
                        iAvailable = available();
                    }
                    if (this.f5141l) {
                        return -1;
                    }
                    if (iAvailable <= i8) {
                        i8 = iAvailable;
                    }
                    this.f5137h.get(bArr, i7, i8);
                    this.f5138i -= i8;
                    if (this.f5143n) {
                        try {
                            this.f5142m.put(bArr, i7, i8);
                        } catch (BufferOverflowException unused) {
                            this.f5143n = false;
                        }
                    }
                }
                return i8;
            }
            throw new IOException("Stream closed");
        }
    }

    public C1797r(InputStream inputStream, OutputStream outputStream) throws IOException {
        String str;
        int i7;
        this.f5127c = inputStream;
        this.f5128d = outputStream;
        do {
            boolean z6 = false;
            while (true) {
                boolean z7 = false;
                while (true) {
                    if (z6) {
                        this.f5131g.append(this.f5129e, 0, this.f5130f);
                        str = new String(this.f5131g);
                        break;
                    }
                    i7 = this.f5127c.read();
                    if (i7 == -1) {
                        str = null;
                        break;
                    } else if (z7) {
                        if (i7 == 10) {
                            z6 = true;
                        }
                    } else if (i7 == 13) {
                        z7 = true;
                    } else {
                        m1999a(i7);
                    }
                }
                m1999a(13);
                m1999a(i7);
            }
            this.f5125a = str;
        } while (str.equals(""));
    }

    /* renamed from: a */
    public final void m1999a(int i7) {
        if (this.f5130f == 2048) {
            this.f5131g.append(this.f5129e);
            this.f5130f = 0;
        }
        char[] cArr = this.f5129e;
        int i8 = this.f5130f;
        this.f5130f = i8 + 1;
        cArr[i8] = (char) i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x007c A[SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public p159t3.C1903b m2000b() {
        /*
            Method dump skipped, instructions count: 191
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p140q6.C1797r.m2000b():t3.b");
    }

    /* compiled from: Request.java */
    /* renamed from: q6.r$b */
    public static class b extends OutputStream {

        /* renamed from: l */
        public static long f5146l = C1800u.f5166c;

        /* renamed from: e */
        public SocketChannel f5147e;

        /* renamed from: f */
        public ByteBuffer f5148f;

        /* renamed from: g */
        public C1799t f5149g;

        /* renamed from: h */
        public Selector f5150h;

        /* renamed from: i */
        public boolean f5151i;

        /* renamed from: j */
        public byte[] f5152j;

        /* renamed from: k */
        public C1801v f5153k;

        public b(C1801v c1801v, SocketChannel socketChannel) {
            this.f5147e = socketChannel;
            this.f5153k = c1801v;
            C1799t c1799tM2003c = C1799t.m2003c();
            this.f5149g = c1799tM2003c;
            Selector selectorM2005b = c1799tM2003c.m2005b();
            this.f5150h = selectorM2005b;
            socketChannel.register(selectorM2005b, 4);
            this.f5151i = false;
            this.f5152j = new byte[1];
            this.f5148f = ByteBuffer.allocate(4096);
        }

        /* renamed from: a */
        public void m2002a() throws SocketTimeoutException {
            long j7 = this.f5153k.f5191p;
            long j8 = f5146l + j7;
            while (j7 < j8) {
                if (this.f5150h.select(f5146l) == 1) {
                    this.f5150h.selectedKeys().clear();
                    return;
                }
                j7 = this.f5153k.f5191p;
            }
            throw new SocketTimeoutException("write blocked too long");
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f5151i) {
                return;
            }
            this.f5147e.close();
            this.f5150h.selectNow();
            this.f5149g.m2004a(this.f5150h);
            this.f5151i = true;
        }

        @Override // java.io.OutputStream
        public synchronized void write(int i7) {
            byte[] bArr = this.f5152j;
            bArr[0] = (byte) i7;
            write(bArr, 0, 1);
        }

        @Override // java.io.OutputStream
        public synchronized void write(byte[] bArr) {
            write(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public synchronized void write(byte[] bArr, int i7, int i8) {
            if (!this.f5151i) {
                int iCapacity = this.f5148f.capacity();
                if (iCapacity < i8) {
                    this.f5148f = ByteBuffer.allocate(((i8 - iCapacity) + iCapacity) * 2);
                }
                this.f5148f.clear();
                this.f5148f.put(bArr, i7, i8);
                this.f5148f.flip();
                while (true) {
                    int iWrite = this.f5147e.write(this.f5148f);
                    if (iWrite >= i8) {
                        return;
                    }
                    i8 -= iWrite;
                    if (i8 == 0) {
                        return;
                    } else {
                        m2002a();
                    }
                }
            } else {
                throw new IOException("stream is closed");
            }
        }
    }
}
