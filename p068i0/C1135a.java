package p068i0;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import p009b.C0413b;
import p028d0.C0863a;
import p142r0.C1819d;
import p142r0.C1823h;
import p155t.C1892b;
import p155t.C1893c;
import p155t.C1894d;
import p155t.C1895e;
import p162u.C1965i;
import p162u.C1966j;
import p162u.InterfaceC1962f;
import p162u.InterfaceC1967k;
import p183x.InterfaceC2057r;
import p190y.InterfaceC2084b;
import p190y.InterfaceC2086d;

/* compiled from: ByteBufferGifDecoder.java */
/* renamed from: i0.a */
/* loaded from: classes.dex */
public class C1135a implements InterfaceC1967k<ByteBuffer, C1137c> {

    /* renamed from: g */
    public static final a f2466g = new a();

    /* renamed from: h */
    public static final C1965i<Boolean> f2467h = C1965i.m2295a("com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder.DisableAnimation", Boolean.FALSE);

    /* renamed from: i */
    public static final b f2468i = new b();

    /* renamed from: a */
    public final Context f2469a;

    /* renamed from: b */
    public final List<InterfaceC1962f> f2470b;

    /* renamed from: c */
    public final b f2471c;

    /* renamed from: d */
    public final InterfaceC2086d f2472d;

    /* renamed from: e */
    public final a f2473e;

    /* renamed from: f */
    public final C1136b f2474f;

    /* compiled from: ByteBufferGifDecoder.java */
    /* renamed from: i0.a$a */
    public static class a {
    }

    /* compiled from: ByteBufferGifDecoder.java */
    /* renamed from: i0.a$b */
    public static class b {

        /* renamed from: a */
        public final Queue<C1894d> f2475a;

        public b() {
            char[] cArr = C1823h.f5300a;
            this.f2475a = new ArrayDeque(0);
        }

        /* renamed from: a */
        public synchronized void m1298a(C1894d c1894d) {
            c1894d.f5567b = null;
            c1894d.f5568c = null;
            this.f2475a.offer(c1894d);
        }
    }

    public C1135a(Context context, List<InterfaceC1962f> list, InterfaceC2086d interfaceC2086d, InterfaceC2084b interfaceC2084b) {
        b bVar = f2468i;
        a aVar = f2466g;
        this.f2469a = context.getApplicationContext();
        this.f2470b = list;
        this.f2472d = interfaceC2086d;
        this.f2473e = aVar;
        this.f2474f = new C1136b(interfaceC2086d, interfaceC2084b);
        this.f2471c = bVar;
    }

    @Override // p162u.InterfaceC1967k
    /* renamed from: a */
    public boolean mo819a(ByteBuffer byteBuffer, C1966j c1966j) {
        ByteBuffer byteBuffer2 = byteBuffer;
        if (!((Boolean) c1966j.m2296c(f2467h)).booleanValue()) {
            List<InterfaceC1962f> list = this.f2470b;
            InterfaceC1962f.a aVar = InterfaceC1962f.a.UNKNOWN;
            if (byteBuffer2 != null) {
                Iterator<InterfaceC1962f> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    InterfaceC1962f.a aVarMo826a = it.next().mo826a(byteBuffer2);
                    if (aVarMo826a != aVar) {
                        aVar = aVarMo826a;
                        break;
                    }
                }
            }
            if (aVar == InterfaceC1962f.a.GIF) {
                return true;
            }
        }
        return false;
    }

    @Override // p162u.InterfaceC1967k
    /* renamed from: b */
    public InterfaceC2057r<C1137c> mo820b(ByteBuffer byteBuffer, int i7, int i8, C1966j c1966j) {
        C1894d c1894dPoll;
        ByteBuffer byteBuffer2 = byteBuffer;
        b bVar = this.f2471c;
        synchronized (bVar) {
            c1894dPoll = bVar.f2475a.poll();
            if (c1894dPoll == null) {
                c1894dPoll = new C1894d();
            }
            c1894dPoll.f5567b = null;
            Arrays.fill(c1894dPoll.f5566a, (byte) 0);
            c1894dPoll.f5568c = new C1893c();
            c1894dPoll.f5569d = 0;
            ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer2.asReadOnlyBuffer();
            c1894dPoll.f5567b = byteBufferAsReadOnlyBuffer;
            byteBufferAsReadOnlyBuffer.position(0);
            c1894dPoll.f5567b.order(ByteOrder.LITTLE_ENDIAN);
        }
        try {
            return m1297c(byteBuffer2, i7, i8, c1894dPoll);
        } finally {
            this.f2471c.m1298a(c1894dPoll);
        }
    }

    /* renamed from: c */
    public final C1138d m1297c(ByteBuffer byteBuffer, int i7, int i8, C1894d c1894d) {
        C1893c c1893c;
        int i9 = C1819d.f5292b;
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        if (c1894d.f5567b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (c1894d.m2187a()) {
            c1893c = c1894d.f5568c;
        } else {
            String string = "";
            for (int i10 = 0; i10 < 6; i10++) {
                StringBuilder sbM112a = C0413b.m112a(string);
                sbM112a.append((char) c1894d.m2188b());
                string = sbM112a.toString();
            }
            if (string.startsWith("GIF")) {
                c1894d.f5568c.f5560f = c1894d.m2191e();
                c1894d.f5568c.f5561g = c1894d.m2191e();
                int iM2188b = c1894d.m2188b();
                C1893c c1893c2 = c1894d.f5568c;
                c1893c2.f5562h = (iM2188b & 128) != 0;
                c1893c2.f5563i = (int) Math.pow(2.0d, (iM2188b & 7) + 1);
                c1894d.f5568c.f5564j = c1894d.m2188b();
                C1893c c1893c3 = c1894d.f5568c;
                c1894d.m2188b();
                Objects.requireNonNull(c1893c3);
                if (c1894d.f5568c.f5562h && !c1894d.m2187a()) {
                    C1893c c1893c4 = c1894d.f5568c;
                    c1893c4.f5555a = c1894d.m2190d(c1893c4.f5563i);
                    C1893c c1893c5 = c1894d.f5568c;
                    c1893c5.f5565k = c1893c5.f5555a[c1893c5.f5564j];
                }
            } else {
                c1894d.f5568c.f5556b = 1;
            }
            if (!c1894d.m2187a()) {
                boolean z6 = false;
                while (!z6 && !c1894d.m2187a() && c1894d.f5568c.f5557c <= Integer.MAX_VALUE) {
                    int iM2188b2 = c1894d.m2188b();
                    if (iM2188b2 == 33) {
                        int iM2188b3 = c1894d.m2188b();
                        if (iM2188b3 == 1) {
                            c1894d.m2192f();
                        } else if (iM2188b3 == 249) {
                            c1894d.f5568c.f5558d = new C1892b();
                            c1894d.m2188b();
                            int iM2188b4 = c1894d.m2188b();
                            C1892b c1892b = c1894d.f5568c.f5558d;
                            int i11 = (iM2188b4 & 28) >> 2;
                            c1892b.f5550g = i11;
                            if (i11 == 0) {
                                c1892b.f5550g = 1;
                            }
                            c1892b.f5549f = (iM2188b4 & 1) != 0;
                            int iM2191e = c1894d.m2191e();
                            if (iM2191e < 2) {
                                iM2191e = 10;
                            }
                            C1892b c1892b2 = c1894d.f5568c.f5558d;
                            c1892b2.f5552i = iM2191e * 10;
                            c1892b2.f5551h = c1894d.m2188b();
                            c1894d.m2188b();
                        } else if (iM2188b3 == 254) {
                            c1894d.m2192f();
                        } else if (iM2188b3 != 255) {
                            c1894d.m2192f();
                        } else {
                            c1894d.m2189c();
                            String string2 = "";
                            for (int i12 = 0; i12 < 11; i12++) {
                                StringBuilder sbM112a2 = C0413b.m112a(string2);
                                sbM112a2.append((char) c1894d.f5566a[i12]);
                                string2 = sbM112a2.toString();
                            }
                            if (string2.equals("NETSCAPE2.0")) {
                                do {
                                    c1894d.m2189c();
                                    byte[] bArr = c1894d.f5566a;
                                    if (bArr[0] == 1) {
                                        byte b7 = bArr[1];
                                        byte b8 = bArr[2];
                                        Objects.requireNonNull(c1894d.f5568c);
                                    }
                                    if (c1894d.f5569d > 0) {
                                    }
                                } while (!c1894d.m2187a());
                            } else {
                                c1894d.m2192f();
                            }
                        }
                    } else if (iM2188b2 == 44) {
                        C1893c c1893c6 = c1894d.f5568c;
                        if (c1893c6.f5558d == null) {
                            c1893c6.f5558d = new C1892b();
                        }
                        c1893c6.f5558d.f5544a = c1894d.m2191e();
                        c1894d.f5568c.f5558d.f5545b = c1894d.m2191e();
                        c1894d.f5568c.f5558d.f5546c = c1894d.m2191e();
                        c1894d.f5568c.f5558d.f5547d = c1894d.m2191e();
                        int iM2188b5 = c1894d.m2188b();
                        boolean z7 = (iM2188b5 & 128) != 0;
                        int iPow = (int) Math.pow(2.0d, (iM2188b5 & 7) + 1);
                        C1892b c1892b3 = c1894d.f5568c.f5558d;
                        c1892b3.f5548e = (iM2188b5 & 64) != 0;
                        if (z7) {
                            c1892b3.f5554k = c1894d.m2190d(iPow);
                        } else {
                            c1892b3.f5554k = null;
                        }
                        c1894d.f5568c.f5558d.f5553j = c1894d.f5567b.position();
                        c1894d.m2188b();
                        c1894d.m2192f();
                        if (!c1894d.m2187a()) {
                            C1893c c1893c7 = c1894d.f5568c;
                            c1893c7.f5557c++;
                            c1893c7.f5559e.add(c1893c7.f5558d);
                        }
                    } else if (iM2188b2 != 59) {
                        c1894d.f5568c.f5556b = 1;
                    } else {
                        z6 = true;
                    }
                }
                C1893c c1893c8 = c1894d.f5568c;
                if (c1893c8.f5557c < 0) {
                    c1893c8.f5556b = 1;
                }
            }
            c1893c = c1894d.f5568c;
        }
        if (c1893c.f5557c <= 0 || c1893c.f5556b != 0) {
            return null;
        }
        int iMin = Math.min(c1893c.f5561g / i8, c1893c.f5560f / i7);
        int iMax = Math.max(1, iMin != 0 ? Integer.highestOneBit(iMin) : 0);
        Log.isLoggable("BufferGifDecoder", 2);
        a aVar = this.f2473e;
        C1136b c1136b = this.f2474f;
        Objects.requireNonNull(aVar);
        C1895e c1895e = new C1895e(c1136b, c1893c, byteBuffer, iMax);
        c1895e.f5582m = (c1895e.f5582m + 1) % c1895e.f5583n.f5557c;
        Bitmap bitmapMo2181b = c1895e.mo2181b();
        if (bitmapMo2181b == null) {
            return null;
        }
        C1137c c1137c = new C1137c(this.f2469a, c1895e, this.f2472d, (C0863a) C0863a.f1288b, i7, i8, bitmapMo2181b);
        if (Log.isLoggable("BufferGifDecoder", 2)) {
            C1819d.m2050a(jElapsedRealtimeNanos);
        }
        return new C1138d(c1137c);
    }
}
