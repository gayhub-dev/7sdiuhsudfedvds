package p089k5;

import android.support.graphics.drawable.C0116a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Properties;
import p073i5.AbstractC1148a;
import p073i5.InterfaceC1152e;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: DirectNIOBuffer.java */
/* renamed from: k5.c */
/* loaded from: classes.dex */
public class C1394c extends AbstractC1148a implements InterfaceC1396e {

    /* renamed from: u */
    public static final InterfaceC2016c f4057u;

    /* renamed from: p */
    public final ByteBuffer f4058p;

    /* renamed from: q */
    public ReadableByteChannel f4059q;

    /* renamed from: r */
    public InputStream f4060r;

    /* renamed from: s */
    public WritableByteChannel f4061s;

    /* renamed from: t */
    public OutputStream f4062t;

    static {
        Properties properties = C2015b.f5863a;
        f4057u = C2015b.m2349a(C1394c.class.getName());
    }

    public C1394c(int i7) {
        super(2, false);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i7);
        this.f4058p = byteBufferAllocateDirect;
        byteBufferAllocateDirect.position(0);
        byteBufferAllocateDirect.limit(byteBufferAllocateDirect.capacity());
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: H */
    public byte mo1348H(int i7) {
        return this.f4058p.get(i7);
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: P */
    public byte[] mo1349P() {
        return null;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: a */
    public int mo1350a() {
        return this.f4058p.capacity();
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: e */
    public void mo1351e(int i7, byte b7) {
        if (mo1318E()) {
            throw new IllegalStateException("READONLY");
        }
        if (i7 < 0) {
            throw new IllegalArgumentException("index<0: " + i7 + "<0");
        }
        if (i7 <= mo1350a()) {
            this.f4058p.put(i7, b7);
        } else {
            StringBuilder sbM98a = C0116a.m98a("index>capacity(): ", i7, ">");
            sbM98a.append(mo1350a());
            throw new IllegalArgumentException(sbM98a.toString());
        }
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: m */
    public int mo1352m(int i7, byte[] bArr, int i8, int i9) {
        if ((i7 + i9 > mo1350a() && (i9 = mo1350a() - i7) == 0) || i9 < 0) {
            return -1;
        }
        try {
            this.f4058p.position(i7);
            this.f4058p.get(bArr, i8, i9);
            return i9;
        } finally {
            this.f4058p.position(0);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
    
        r9.f4059q = null;
        r9.f4060r = r10;
     */
    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: n */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int mo1333n(java.io.InputStream r10, int r11) {
        /*
            r9 = this;
            java.nio.channels.ReadableByteChannel r0 = r9.f4059q
            if (r0 == 0) goto Le
            boolean r0 = r0.isOpen()
            if (r0 == 0) goto Le
            java.io.InputStream r0 = r9.f4060r
            if (r10 == r0) goto L16
        Le:
            java.nio.channels.ReadableByteChannel r0 = java.nio.channels.Channels.newChannel(r10)
            r9.f4059q = r0
            r9.f4060r = r10
        L16:
            if (r11 < 0) goto L1e
            int r0 = r9.mo1317D()
            if (r11 <= r0) goto L22
        L1e:
            int r11 = r9.mo1317D()
        L22:
            int r0 = r9.f2525h
            r1 = 0
            r3 = r11
            r2 = 0
            r4 = 0
            r5 = 0
        L29:
            r6 = 0
            if (r2 >= r11) goto L84
            java.nio.ByteBuffer r5 = r9.f4058p     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r5.position(r0)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.nio.ByteBuffer r5 = r9.f4058p     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            int r7 = r0 + r3
            r5.limit(r7)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.nio.channels.ReadableByteChannel r5 = r9.f4059q     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.nio.ByteBuffer r7 = r9.f4058p     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            int r5 = r5.read(r7)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            if (r5 >= 0) goto L47
            r9.f4059q = r6     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r9.f4060r = r10     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            goto L84
        L47:
            if (r5 <= 0) goto L51
            int r0 = r0 + r5
            int r2 = r2 + r5
            int r3 = r3 - r5
            r9.mo1324Q(r0)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r4 = 0
            goto L58
        L51:
            int r7 = r4 + 1
            r8 = 1
            if (r4 <= r8) goto L57
            goto L84
        L57:
            r4 = r7
        L58:
            int r7 = r10.available()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            if (r7 > 0) goto L29
            goto L84
        L5f:
            r11 = move-exception
            goto L67
        L61:
            r11 = move-exception
            r9.f4059q = r6     // Catch: java.lang.Throwable -> L5f
            r9.f4060r = r10     // Catch: java.lang.Throwable -> L5f
            throw r11     // Catch: java.lang.Throwable -> L5f
        L67:
            java.nio.channels.ReadableByteChannel r0 = r9.f4059q
            if (r0 == 0) goto L75
            boolean r0 = r0.isOpen()
            if (r0 != 0) goto L75
            r9.f4059q = r6
            r9.f4060r = r10
        L75:
            java.nio.ByteBuffer r10 = r9.f4058p
            r10.position(r1)
            java.nio.ByteBuffer r10 = r9.f4058p
            int r0 = r10.capacity()
            r10.limit(r0)
            throw r11
        L84:
            if (r5 >= 0) goto La6
            if (r2 != 0) goto La6
            r11 = -1
            java.nio.channels.ReadableByteChannel r0 = r9.f4059q
            if (r0 == 0) goto L97
            boolean r0 = r0.isOpen()
            if (r0 != 0) goto L97
            r9.f4059q = r6
            r9.f4060r = r10
        L97:
            java.nio.ByteBuffer r10 = r9.f4058p
            r10.position(r1)
            java.nio.ByteBuffer r10 = r9.f4058p
            int r0 = r10.capacity()
            r10.limit(r0)
            return r11
        La6:
            java.nio.channels.ReadableByteChannel r11 = r9.f4059q
            if (r11 == 0) goto Lb4
            boolean r11 = r11.isOpen()
            if (r11 != 0) goto Lb4
            r9.f4059q = r6
            r9.f4060r = r10
        Lb4:
            java.nio.ByteBuffer r10 = r9.f4058p
            r10.position(r1)
            java.nio.ByteBuffer r10 = r9.f4058p
            int r11 = r10.capacity()
            r10.limit(r11)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p089k5.C1394c.mo1333n(java.io.InputStream, int):int");
    }

    @Override // p089k5.InterfaceC1396e
    /* renamed from: o */
    public ByteBuffer mo1563o() {
        return this.f4058p;
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: p */
    public int mo1334p(int i7, InterfaceC1152e interfaceC1152e) {
        if (mo1318E()) {
            throw new IllegalStateException("READONLY");
        }
        byte[] bArrMo1349P = interfaceC1152e.mo1349P();
        if (bArrMo1349P != null) {
            return mo1336s(i7, bArrMo1349P, interfaceC1152e.mo1316C(), interfaceC1152e.length());
        }
        InterfaceC1152e interfaceC1152eBuffer = interfaceC1152e.buffer();
        if (!(interfaceC1152eBuffer instanceof C1394c)) {
            return super.mo1334p(i7, interfaceC1152e);
        }
        ByteBuffer byteBufferDuplicate = ((C1394c) interfaceC1152eBuffer).f4058p;
        ByteBuffer byteBuffer = this.f4058p;
        if (byteBufferDuplicate == byteBuffer) {
            byteBufferDuplicate = byteBuffer.duplicate();
        }
        try {
            this.f4058p.position(i7);
            int iRemaining = this.f4058p.remaining();
            int length = interfaceC1152e.length();
            if (length <= iRemaining) {
                iRemaining = length;
            }
            byteBufferDuplicate.position(interfaceC1152e.mo1316C());
            byteBufferDuplicate.limit(interfaceC1152e.mo1316C() + iRemaining);
            this.f4058p.put(byteBufferDuplicate);
            return iRemaining;
        } finally {
            this.f4058p.position(0);
            byteBufferDuplicate.limit(byteBufferDuplicate.capacity());
            byteBufferDuplicate.position(0);
        }
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: r */
    public void mo1335r(OutputStream outputStream) {
        int iWrite;
        WritableByteChannel writableByteChannel = this.f4061s;
        if (writableByteChannel == null || !writableByteChannel.isOpen() || outputStream != this.f4062t) {
            this.f4061s = Channels.newChannel(outputStream);
            this.f4062t = outputStream;
        }
        synchronized (this.f4058p) {
            loop0: while (true) {
                int i7 = 0;
                while (true) {
                    try {
                        try {
                            if (!mo1314A() || !this.f4061s.isOpen()) {
                                break loop0;
                            }
                            this.f4058p.position(this.f2524g);
                            this.f4058p.limit(this.f2525h);
                            iWrite = this.f4061s.write(this.f4058p);
                            if (iWrite < 0) {
                                break loop0;
                            }
                            if (iWrite > 0) {
                                break;
                            }
                            int i8 = i7 + 1;
                            if (i7 > 1) {
                                break loop0;
                            } else {
                                i7 = i8;
                            }
                        } catch (IOException e7) {
                            this.f4061s = null;
                            this.f4062t = null;
                            throw e7;
                        }
                    } finally {
                        WritableByteChannel writableByteChannel2 = this.f4061s;
                        if (writableByteChannel2 != null && !writableByteChannel2.isOpen()) {
                            this.f4061s = null;
                            this.f4062t = null;
                        }
                        this.f4058p.position(0);
                        ByteBuffer byteBuffer = this.f4058p;
                        byteBuffer.limit(byteBuffer.capacity());
                    }
                }
                mo1330h(iWrite);
            }
        }
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: s */
    public int mo1336s(int i7, byte[] bArr, int i8, int i9) {
        if (mo1318E()) {
            throw new IllegalStateException("READONLY");
        }
        if (i7 < 0) {
            throw new IllegalArgumentException("index<0: " + i7 + "<0");
        }
        if (i7 + i9 > mo1350a() && (i9 = mo1350a() - i7) < 0) {
            StringBuilder sbM98a = C0116a.m98a("index>capacity(): ", i7, ">");
            sbM98a.append(mo1350a());
            throw new IllegalArgumentException(sbM98a.toString());
        }
        try {
            this.f4058p.position(i7);
            int iRemaining = this.f4058p.remaining();
            if (i9 > iRemaining) {
                i9 = iRemaining;
            }
            if (i9 > 0) {
                this.f4058p.put(bArr, i8, i9);
            }
            return i9;
        } finally {
            this.f4058p.position(0);
        }
    }

    public C1394c(ByteBuffer byteBuffer, boolean z6) {
        super(z6 ? 0 : 2, false);
        if (byteBuffer.isDirect()) {
            this.f4058p = byteBuffer;
            mo1331i(byteBuffer.position());
            mo1324Q(byteBuffer.limit());
            return;
        }
        throw new IllegalArgumentException();
    }
}
