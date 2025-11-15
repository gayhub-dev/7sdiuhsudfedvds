package p065h5;

import java.io.IOException;
import java.util.Properties;
import p009b.C0413b;
import p073i5.C1153f;
import p073i5.C1162o;
import p073i5.C1166s;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1156i;
import p073i5.InterfaceC1161n;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: HttpParser.java */
/* renamed from: h5.q */
/* loaded from: classes.dex */
public class C1109q implements InterfaceC1116x {

    /* renamed from: v */
    public static final InterfaceC2016c f2340v;

    /* renamed from: a */
    public final a f2341a;

    /* renamed from: b */
    public final InterfaceC1156i f2342b;

    /* renamed from: c */
    public final InterfaceC1161n f2343c;

    /* renamed from: d */
    public InterfaceC1152e f2344d;

    /* renamed from: e */
    public InterfaceC1152e f2345e;

    /* renamed from: f */
    public InterfaceC1152e f2346f;

    /* renamed from: g */
    public C1153f.a f2347g;

    /* renamed from: j */
    public String f2350j;

    /* renamed from: k */
    public int f2351k;

    /* renamed from: l */
    public boolean f2352l;

    /* renamed from: o */
    public byte f2355o;

    /* renamed from: p */
    public int f2356p;

    /* renamed from: q */
    public long f2357q;

    /* renamed from: r */
    public long f2358r;

    /* renamed from: s */
    public int f2359s;

    /* renamed from: t */
    public int f2360t;

    /* renamed from: u */
    public boolean f2361u;

    /* renamed from: m */
    public final C1166s f2353m = new C1166s();

    /* renamed from: n */
    public int f2354n = -14;

    /* renamed from: h */
    public final C1166s.a f2348h = new C1166s.a();

    /* renamed from: i */
    public final C1166s.a f2349i = new C1166s.a();

    /* compiled from: HttpParser.java */
    /* renamed from: h5.q$a */
    public static abstract class a {
        /* renamed from: a */
        public abstract void mo868a(InterfaceC1152e interfaceC1152e);

        /* renamed from: b */
        public abstract void mo869b();

        /* renamed from: c */
        public abstract void mo870c();

        /* renamed from: d */
        public abstract void mo871d(long j7);

        /* renamed from: e */
        public abstract void mo872e(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2);

        /* renamed from: f */
        public abstract void mo873f(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2, InterfaceC1152e interfaceC1152e3);

        /* renamed from: g */
        public abstract void mo874g(InterfaceC1152e interfaceC1152e, int i7, InterfaceC1152e interfaceC1152e2);
    }

    static {
        Properties properties = C2015b.f5863a;
        f2340v = C2015b.m2349a(C1109q.class.getName());
    }

    public C1109q(InterfaceC1156i interfaceC1156i, InterfaceC1161n interfaceC1161n, a aVar) {
        this.f2342b = interfaceC1156i;
        this.f2343c = interfaceC1161n;
        this.f2341a = aVar;
    }

    /* renamed from: a */
    public int m1241a() throws IOException {
        InterfaceC1152e interfaceC1152e;
        InterfaceC1152e interfaceC1152e2;
        if (this.f2346f == null) {
            this.f2346f = m1242b();
        }
        if (this.f2354n > 0) {
            InterfaceC1152e interfaceC1152e3 = this.f2346f;
            InterfaceC1152e interfaceC1152e4 = this.f2344d;
            if (interfaceC1152e3 == interfaceC1152e4 && interfaceC1152e4 != null && !interfaceC1152e4.mo1314A() && (interfaceC1152e2 = this.f2345e) != null && interfaceC1152e2.mo1314A()) {
                InterfaceC1152e interfaceC1152e5 = this.f2345e;
                this.f2346f = interfaceC1152e5;
                return interfaceC1152e5.length();
            }
        }
        InterfaceC1152e interfaceC1152e6 = this.f2346f;
        InterfaceC1152e interfaceC1152e7 = this.f2344d;
        if (interfaceC1152e6 == interfaceC1152e7 && this.f2354n > 0 && interfaceC1152e7.length() == 0 && this.f2357q - this.f2358r > this.f2344d.mo1350a() && ((interfaceC1152e = this.f2345e) != null || this.f2342b != null)) {
            if (interfaceC1152e == null) {
                this.f2345e = this.f2342b.getBuffer();
            }
            this.f2346f = this.f2345e;
        }
        if (this.f2343c == null) {
            return -1;
        }
        InterfaceC1152e interfaceC1152e8 = this.f2346f;
        if (interfaceC1152e8 == this.f2345e || this.f2354n > 0) {
            interfaceC1152e8.mo1340w();
        }
        if (this.f2346f.mo1317D() == 0) {
            f2340v.mo2356g("HttpParser Full for {} ", this.f2343c);
            this.f2346f.clear();
            StringBuilder sbM112a = C0413b.m112a("Request Entity Too Large: ");
            sbM112a.append(this.f2346f == this.f2345e ? "body" : "head");
            throw new C1100h(413, sbM112a.toString());
        }
        try {
            return this.f2343c.mo929v(this.f2346f);
        } catch (IOException e7) {
            f2340v.mo2359j(e7);
            if (e7 instanceof C1162o) {
                throw e7;
            }
            throw new C1162o(e7);
        }
    }

    /* renamed from: b */
    public InterfaceC1152e m1242b() {
        if (this.f2344d == null) {
            InterfaceC1152e interfaceC1152eMo1370c = this.f2342b.mo1370c();
            this.f2344d = interfaceC1152eMo1370c;
            this.f2348h.m1373d(interfaceC1152eMo1370c);
            this.f2349i.m1373d(this.f2344d);
        }
        return this.f2344d;
    }

    /* renamed from: c */
    public boolean m1243c() {
        return this.f2351k > 0 ? m1246f(0) || m1246f(7) : m1246f(0);
    }

    /* renamed from: d */
    public boolean m1244d() {
        return m1246f(-14);
    }

    /* renamed from: e */
    public boolean m1245e() {
        InterfaceC1152e interfaceC1152e;
        InterfaceC1152e interfaceC1152e2 = this.f2344d;
        return (interfaceC1152e2 != null && interfaceC1152e2.mo1314A()) || ((interfaceC1152e = this.f2345e) != null && interfaceC1152e.mo1314A());
    }

    /* renamed from: f */
    public boolean m1246f(int i7) {
        return this.f2354n == i7;
    }

    /* renamed from: g */
    public boolean m1247g() {
        InterfaceC1152e interfaceC1152e;
        boolean z6 = m1248h() > 0;
        while (!m1243c() && (interfaceC1152e = this.f2346f) != null && interfaceC1152e.length() > 0 && !this.f2353m.mo1314A()) {
            z6 |= m1248h() > 0;
        }
        return z6;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x0442, code lost:
    
        r3 = r18.f2351k;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x0444, code lost:
    
        if (r3 <= 0) goto L234;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x0448, code lost:
    
        if (r3 == 304) goto L233;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x044c, code lost:
    
        if (r3 == 204) goto L233;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x0450, code lost:
    
        if (r3 >= 200) goto L234;
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x0452, code lost:
    
        r18.f2357q = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x045d, code lost:
    
        if (r18.f2357q != (-3)) goto L246;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x045f, code lost:
    
        if (r3 == 0) goto L245;
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x0463, code lost:
    
        if (r3 == 304) goto L245;
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x0467, code lost:
    
        if (r3 == 204) goto L245;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x046b, code lost:
    
        if (r3 >= 200) goto L244;
     */
    /* JADX WARN: Code restructure failed: missing block: B:244:0x046e, code lost:
    
        r18.f2357q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x0473, code lost:
    
        r3 = 0;
        r18.f2357q = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:0x0478, code lost:
    
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x047a, code lost:
    
        r18.f2358r = r3;
        r18.f2355o = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x047e, code lost:
    
        if (r2 != 13) goto L254;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x0486, code lost:
    
        if (r18.f2346f.mo1314A() == false) goto L254;
     */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x048e, code lost:
    
        if (r18.f2346f.peek() != 10) goto L254;
     */
    /* JADX WARN: Code restructure failed: missing block: B:253:0x0490, code lost:
    
        r18.f2355o = r18.f2346f.get();
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x0498, code lost:
    
        r2 = r18.f2357q;
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x049f, code lost:
    
        if (r2 <= 2147483647L) goto L257;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x04a1, code lost:
    
        r2 = android.support.v7.widget.ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x04a5, code lost:
    
        r2 = (int) r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x04a7, code lost:
    
        if (r2 == (-2)) goto L276;
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x04aa, code lost:
    
        if (r2 == (-1)) goto L275;
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x04ac, code lost:
    
        if (r2 == 0) goto L264;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x04ae, code lost:
    
        r18.f2354n = 2;
        r18.f2341a.mo870c();
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x04b9, code lost:
    
        if (r18.f2352l != false) goto L272;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x04bb, code lost:
    
        r2 = r18.f2351k;
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x04bf, code lost:
    
        if (r2 < 100) goto L271;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x04c3, code lost:
    
        if (r2 >= 200) goto L271;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x04c6, code lost:
    
        r2 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x04c8, code lost:
    
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x04c9, code lost:
    
        r18.f2354n = r2;
        r18.f2341a.mo870c();
        r18.f2341a.mo871d(r18.f2358r);
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x04d7, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x04d8, code lost:
    
        r18.f2354n = 1;
        r18.f2341a.mo870c();
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x04e0, code lost:
    
        r18.f2354n = 3;
        r18.f2341a.mo870c();
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x04e8, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:351:0x06eb, code lost:
    
        r3 = r18.f2351k;
     */
    /* JADX WARN: Code restructure failed: missing block: B:352:0x06ed, code lost:
    
        if (r3 <= 0) goto L365;
     */
    /* JADX WARN: Code restructure failed: missing block: B:354:0x06f1, code lost:
    
        if (r18.f2361u == false) goto L365;
     */
    /* JADX WARN: Code restructure failed: missing block: B:356:0x06f5, code lost:
    
        if (r18.f2352l != false) goto L363;
     */
    /* JADX WARN: Code restructure failed: missing block: B:358:0x06f9, code lost:
    
        if (r3 < 100) goto L362;
     */
    /* JADX WARN: Code restructure failed: missing block: B:360:0x06fd, code lost:
    
        if (r3 >= 200) goto L362;
     */
    /* JADX WARN: Code restructure failed: missing block: B:362:0x0700, code lost:
    
        r3 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:0x0702, code lost:
    
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:364:0x0703, code lost:
    
        r18.f2354n = r3;
        r18.f2341a.mo871d(r18.f2357q);
     */
    /* JADX WARN: Code restructure failed: missing block: B:365:0x070c, code lost:
    
        r3 = r18.f2346f.length();
        r5 = r18.f2354n;
     */
    /* JADX WARN: Code restructure failed: missing block: B:366:0x0714, code lost:
    
        r7 = r18.f2354n;
     */
    /* JADX WARN: Code restructure failed: missing block: B:367:0x0716, code lost:
    
        if (r7 <= 0) goto L517;
     */
    /* JADX WARN: Code restructure failed: missing block: B:368:0x0718, code lost:
    
        if (r3 <= 0) goto L514;
     */
    /* JADX WARN: Code restructure failed: missing block: B:369:0x071a, code lost:
    
        if (r5 == r7) goto L371;
     */
    /* JADX WARN: Code restructure failed: missing block: B:370:0x071c, code lost:
    
        r4 = r4 + 1;
        r5 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:372:0x0721, code lost:
    
        if (r18.f2355o != 13) goto L515;
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x0729, code lost:
    
        if (r18.f2346f.peek() != 10) goto L516;
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x072b, code lost:
    
        r18.f2355o = r18.f2346f.get();
        r3 = r18.f2346f.length();
     */
    /* JADX WARN: Code restructure failed: missing block: B:376:0x073a, code lost:
    
        r18.f2355o = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:377:0x073f, code lost:
    
        switch(r18.f2354n) {
            case 1: goto L525;
            case 2: goto L524;
            case 3: goto L456;
            case 4: goto L416;
            case 5: goto L397;
            case 6: goto L390;
            case 7: goto L381;
            default: goto L378;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:382:0x0752, code lost:
    
        if (r18.f2346f.length() <= 2) goto L384;
     */
    /* JADX WARN: Code restructure failed: missing block: B:383:0x0754, code lost:
    
        r18.f2354n = 0;
        r18.f2343c.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:385:0x0762, code lost:
    
        if (r18.f2346f.length() <= 0) goto L529;
     */
    /* JADX WARN: Code restructure failed: missing block: B:387:0x076e, code lost:
    
        if (java.lang.Character.isWhitespace(r18.f2346f.get()) != false) goto L532;
     */
    /* JADX WARN: Code restructure failed: missing block: B:388:0x0770, code lost:
    
        r18.f2354n = 0;
        r18.f2343c.close();
        r18.f2346f.clear();
     */
    /* JADX WARN: Code restructure failed: missing block: B:389:0x077e, code lost:
    
        r18.f2346f.clear();
     */
    /* JADX WARN: Code restructure failed: missing block: B:390:0x0785, code lost:
    
        r7 = r18.f2359s - r18.f2360t;
     */
    /* JADX WARN: Code restructure failed: missing block: B:391:0x078b, code lost:
    
        if (r7 != 0) goto L518;
     */
    /* JADX WARN: Code restructure failed: missing block: B:392:0x078d, code lost:
    
        r18.f2354n = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:393:0x0791, code lost:
    
        if (r3 <= r7) goto L395;
     */
    /* JADX WARN: Code restructure failed: missing block: B:394:0x0793, code lost:
    
        r3 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:395:0x0794, code lost:
    
        r2 = r18.f2346f.get(r3);
        r18.f2358r += r2.length();
        r18.f2360t += r2.length();
        r18.f2353m.m1373d(r2);
        r18.f2341a.mo868a(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:396:0x07b7, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:0x07b8, code lost:
    
        r3 = r18.f2346f.get();
     */
    /* JADX WARN: Code restructure failed: missing block: B:398:0x07bf, code lost:
    
        if (r3 == 13) goto L400;
     */
    /* JADX WARN: Code restructure failed: missing block: B:399:0x07c1, code lost:
    
        if (r3 != 10) goto L415;
     */
    /* JADX WARN: Code restructure failed: missing block: B:400:0x07c3, code lost:
    
        r18.f2355o = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:0x07c7, code lost:
    
        if (r18.f2359s != 0) goto L414;
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x07c9, code lost:
    
        if (r3 != 13) goto L408;
     */
    /* JADX WARN: Code restructure failed: missing block: B:404:0x07d1, code lost:
    
        if (r18.f2346f.mo1314A() == false) goto L408;
     */
    /* JADX WARN: Code restructure failed: missing block: B:406:0x07d9, code lost:
    
        if (r18.f2346f.peek() != 10) goto L408;
     */
    /* JADX WARN: Code restructure failed: missing block: B:407:0x07db, code lost:
    
        r18.f2355o = r18.f2346f.get();
     */
    /* JADX WARN: Code restructure failed: missing block: B:409:0x07e5, code lost:
    
        if (r18.f2352l == false) goto L411;
     */
    /* JADX WARN: Code restructure failed: missing block: B:410:0x07e7, code lost:
    
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:411:0x07e9, code lost:
    
        r2 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:412:0x07ea, code lost:
    
        r18.f2354n = r2;
        r18.f2341a.mo871d(r18.f2358r);
     */
    /* JADX WARN: Code restructure failed: missing block: B:413:0x07f3, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:414:0x07f4, code lost:
    
        r18.f2354n = 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:416:0x07fb, code lost:
    
        r3 = r18.f2346f.get();
     */
    /* JADX WARN: Code restructure failed: missing block: B:417:0x0802, code lost:
    
        if (r3 == 13) goto L441;
     */
    /* JADX WARN: Code restructure failed: missing block: B:418:0x0804, code lost:
    
        if (r3 != 10) goto L420;
     */
    /* JADX WARN: Code restructure failed: missing block: B:420:0x0807, code lost:
    
        if (r3 <= 32) goto L440;
     */
    /* JADX WARN: Code restructure failed: missing block: B:422:0x080b, code lost:
    
        if (r3 != 59) goto L424;
     */
    /* JADX WARN: Code restructure failed: missing block: B:425:0x0812, code lost:
    
        if (r3 < 48) goto L428;
     */
    /* JADX WARN: Code restructure failed: missing block: B:426:0x0814, code lost:
    
        if (r3 > 57) goto L428;
     */
    /* JADX WARN: Code restructure failed: missing block: B:427:0x0816, code lost:
    
        r18.f2359s = (r3 - 48) + (r18.f2359s * 16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:429:0x0823, code lost:
    
        if (r3 < 97) goto L433;
     */
    /* JADX WARN: Code restructure failed: missing block: B:431:0x0827, code lost:
    
        if (r3 > 102) goto L433;
     */
    /* JADX WARN: Code restructure failed: missing block: B:432:0x0829, code lost:
    
        r18.f2359s = ((r3 + 10) - 97) + (r18.f2359s * 16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:434:0x0838, code lost:
    
        if (r3 < 65) goto L520;
     */
    /* JADX WARN: Code restructure failed: missing block: B:436:0x083c, code lost:
    
        if (r3 > 70) goto L521;
     */
    /* JADX WARN: Code restructure failed: missing block: B:437:0x083e, code lost:
    
        r18.f2359s = ((r3 + 10) - 65) + (r18.f2359s * 16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:439:0x0861, code lost:
    
        throw new java.io.IOException("bad chunk char: " + ((int) r3));
     */
    /* JADX WARN: Code restructure failed: missing block: B:440:0x0862, code lost:
    
        r18.f2354n = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:441:0x086a, code lost:
    
        r18.f2355o = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:442:0x0872, code lost:
    
        if (r18.f2359s != 0) goto L455;
     */
    /* JADX WARN: Code restructure failed: missing block: B:443:0x0874, code lost:
    
        if (r3 != 13) goto L449;
     */
    /* JADX WARN: Code restructure failed: missing block: B:445:0x087c, code lost:
    
        if (r18.f2346f.mo1314A() == false) goto L449;
     */
    /* JADX WARN: Code restructure failed: missing block: B:447:0x0884, code lost:
    
        if (r18.f2346f.peek() != 10) goto L449;
     */
    /* JADX WARN: Code restructure failed: missing block: B:448:0x0886, code lost:
    
        r18.f2355o = r18.f2346f.get();
     */
    /* JADX WARN: Code restructure failed: missing block: B:450:0x0890, code lost:
    
        if (r18.f2352l == false) goto L452;
     */
    /* JADX WARN: Code restructure failed: missing block: B:451:0x0892, code lost:
    
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:452:0x0894, code lost:
    
        r2 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:453:0x0895, code lost:
    
        r18.f2354n = r2;
        r18.f2341a.mo871d(r18.f2358r);
     */
    /* JADX WARN: Code restructure failed: missing block: B:454:0x089e, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:455:0x089f, code lost:
    
        r18.f2354n = 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:456:0x08a4, code lost:
    
        r3 = r18.f2346f.peek();
     */
    /* JADX WARN: Code restructure failed: missing block: B:457:0x08af, code lost:
    
        if (r3 == 13) goto L463;
     */
    /* JADX WARN: Code restructure failed: missing block: B:458:0x08b1, code lost:
    
        if (r3 != 10) goto L460;
     */
    /* JADX WARN: Code restructure failed: missing block: B:460:0x08b4, code lost:
    
        if (r3 > 32) goto L462;
     */
    /* JADX WARN: Code restructure failed: missing block: B:461:0x08b6, code lost:
    
        r18.f2346f.get();
     */
    /* JADX WARN: Code restructure failed: missing block: B:462:0x08bd, code lost:
    
        r18.f2359s = 0;
        r18.f2360t = 0;
        r18.f2354n = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:463:0x08c7, code lost:
    
        r18.f2355o = r18.f2346f.get();
     */
    /* JADX WARN: Code restructure failed: missing block: B:464:0x08d1, code lost:
    
        r4 = r18.f2357q;
        r7 = r18.f2358r;
        r4 = r4 - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:465:0x08da, code lost:
    
        if (r4 != 0) goto L472;
     */
    /* JADX WARN: Code restructure failed: missing block: B:467:0x08de, code lost:
    
        if (r18.f2352l == false) goto L469;
     */
    /* JADX WARN: Code restructure failed: missing block: B:468:0x08e0, code lost:
    
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:469:0x08e2, code lost:
    
        r2 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:470:0x08e3, code lost:
    
        r18.f2354n = r2;
        r18.f2341a.mo871d(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:471:0x08ea, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:473:0x08ee, code lost:
    
        if (r3 <= r4) goto L475;
     */
    /* JADX WARN: Code restructure failed: missing block: B:474:0x08f0, code lost:
    
        r3 = (int) r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:475:0x08f1, code lost:
    
        r2 = r18.f2346f.get(r3);
        r18.f2358r += r2.length();
        r18.f2353m.m1373d(r2);
        r18.f2341a.mo868a(r2);
        r2 = r18.f2358r;
     */
    /* JADX WARN: Code restructure failed: missing block: B:476:0x0911, code lost:
    
        if (r2 != r18.f2357q) goto L482;
     */
    /* JADX WARN: Code restructure failed: missing block: B:478:0x0915, code lost:
    
        if (r18.f2352l == false) goto L480;
     */
    /* JADX WARN: Code restructure failed: missing block: B:479:0x0917, code lost:
    
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:480:0x0919, code lost:
    
        r4 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:481:0x091a, code lost:
    
        r18.f2354n = r4;
        r18.f2341a.mo871d(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:482:0x0921, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:483:0x0922, code lost:
    
        r2 = r18.f2346f;
        r2 = r2.get(r2.length());
        r18.f2358r += r2.length();
        r18.f2353m.m1373d(r2);
        r18.f2341a.mo868a(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:484:0x0940, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:485:0x0941, code lost:
    
        r3 = r18.f2346f.length();
     */
    /* JADX WARN: Code restructure failed: missing block: B:487:0x0949, code lost:
    
        return r4;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0280 A[Catch: h -> 0x094a, TryCatch #2 {h -> 0x094a, blocks: (B:3:0x0004, B:6:0x0009, B:8:0x000d, B:9:0x0013, B:11:0x0019, B:13:0x0021, B:15:0x0029, B:57:0x00d8, B:59:0x00e1, B:60:0x00e9, B:62:0x00f4, B:65:0x00fa, B:66:0x00fd, B:69:0x010b, B:72:0x0111, B:73:0x0116, B:74:0x0117, B:82:0x012e, B:83:0x0134, B:85:0x0138, B:87:0x0140, B:88:0x0155, B:90:0x0159, B:91:0x0161, B:92:0x0193, B:93:0x019a, B:98:0x01a6, B:100:0x01ab, B:101:0x01b0, B:102:0x01c4, B:104:0x01c8, B:106:0x01d0, B:107:0x01e5, B:109:0x01e9, B:110:0x01f1, B:111:0x0223, B:117:0x0234, B:118:0x023e, B:120:0x0242, B:122:0x0246, B:123:0x025a, B:124:0x0261, B:126:0x0265, B:127:0x0279, B:128:0x0280, B:134:0x028f, B:136:0x0297, B:137:0x029c, B:138:0x02b0, B:140:0x02b4, B:142:0x02b8, B:143:0x02cc, B:144:0x02d3, B:146:0x02d7, B:147:0x02eb, B:151:0x02f8, B:153:0x02fc, B:155:0x0304, B:157:0x030c, B:216:0x03fa, B:220:0x0407, B:222:0x0413, B:224:0x0425, B:225:0x0442, B:233:0x0452, B:247:0x047a, B:249:0x0480, B:251:0x0488, B:253:0x0490, B:254:0x0498, B:263:0x04ae, B:264:0x04b7, B:266:0x04bb, B:273:0x04c9, B:275:0x04d8, B:276:0x04e0, B:257:0x04a5, B:234:0x0457, B:244:0x046e, B:245:0x0473, B:161:0x0315, B:165:0x0322, B:167:0x0329, B:169:0x0333, B:215:0x03e0, B:177:0x0349, B:180:0x034f, B:183:0x035b, B:185:0x0360, B:186:0x036d, B:187:0x036e, B:189:0x037b, B:190:0x037e, B:192:0x038a, B:193:0x038d, B:196:0x0396, B:197:0x039e, B:198:0x039f, B:203:0x03af, B:204:0x03b2, B:205:0x03b6, B:207:0x03c4, B:213:0x03db, B:211:0x03d5, B:212:0x03d8, B:168:0x032c, B:164:0x031a, B:278:0x04e9, B:282:0x04f6, B:284:0x04fa, B:286:0x052c, B:290:0x053b, B:285:0x0510, B:296:0x055f, B:298:0x0563, B:299:0x058f, B:301:0x05b2, B:304:0x05bf, B:307:0x05db, B:311:0x0605, B:316:0x0625, B:319:0x0634, B:320:0x0660, B:327:0x0673, B:328:0x067a, B:329:0x067b, B:331:0x0684, B:332:0x068e, B:335:0x0698, B:339:0x06b7, B:343:0x06c5, B:344:0x06cc, B:345:0x06cd, B:348:0x06da, B:351:0x06eb, B:353:0x06ef, B:355:0x06f3, B:364:0x0703, B:365:0x070c, B:366:0x0714, B:370:0x071c, B:371:0x071f, B:373:0x0723, B:375:0x072b, B:376:0x073a, B:377:0x073f, B:485:0x0941, B:381:0x074b, B:383:0x0754, B:389:0x077e, B:384:0x075c, B:386:0x0764, B:388:0x0770, B:390:0x0785, B:392:0x078d, B:395:0x0794, B:397:0x07b8, B:400:0x07c3, B:403:0x07cb, B:405:0x07d3, B:407:0x07db, B:408:0x07e3, B:412:0x07ea, B:414:0x07f4, B:416:0x07fb, B:427:0x0816, B:432:0x0829, B:437:0x083e, B:438:0x084b, B:439:0x0861, B:440:0x0862, B:441:0x086a, B:444:0x0876, B:446:0x087e, B:448:0x0886, B:449:0x088e, B:453:0x0895, B:455:0x089f, B:456:0x08a4, B:461:0x08b6, B:462:0x08bd, B:463:0x08c7, B:464:0x08d1, B:466:0x08dc, B:470:0x08e3, B:472:0x08eb, B:474:0x08f0, B:475:0x08f1, B:477:0x0913, B:481:0x091a, B:483:0x0922, B:29:0x0069, B:31:0x006f, B:33:0x0077, B:35:0x007b, B:36:0x0099, B:40:0x00a1, B:42:0x00a7, B:43:0x00ac, B:47:0x00c2, B:49:0x00c8, B:52:0x00cf, B:53:0x00d4, B:55:0x00d6, B:44:0x00b4, B:45:0x00be, B:25:0x0059), top: B:495:0x0004, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00be A[Catch: h -> 0x094a, TryCatch #2 {h -> 0x094a, blocks: (B:3:0x0004, B:6:0x0009, B:8:0x000d, B:9:0x0013, B:11:0x0019, B:13:0x0021, B:15:0x0029, B:57:0x00d8, B:59:0x00e1, B:60:0x00e9, B:62:0x00f4, B:65:0x00fa, B:66:0x00fd, B:69:0x010b, B:72:0x0111, B:73:0x0116, B:74:0x0117, B:82:0x012e, B:83:0x0134, B:85:0x0138, B:87:0x0140, B:88:0x0155, B:90:0x0159, B:91:0x0161, B:92:0x0193, B:93:0x019a, B:98:0x01a6, B:100:0x01ab, B:101:0x01b0, B:102:0x01c4, B:104:0x01c8, B:106:0x01d0, B:107:0x01e5, B:109:0x01e9, B:110:0x01f1, B:111:0x0223, B:117:0x0234, B:118:0x023e, B:120:0x0242, B:122:0x0246, B:123:0x025a, B:124:0x0261, B:126:0x0265, B:127:0x0279, B:128:0x0280, B:134:0x028f, B:136:0x0297, B:137:0x029c, B:138:0x02b0, B:140:0x02b4, B:142:0x02b8, B:143:0x02cc, B:144:0x02d3, B:146:0x02d7, B:147:0x02eb, B:151:0x02f8, B:153:0x02fc, B:155:0x0304, B:157:0x030c, B:216:0x03fa, B:220:0x0407, B:222:0x0413, B:224:0x0425, B:225:0x0442, B:233:0x0452, B:247:0x047a, B:249:0x0480, B:251:0x0488, B:253:0x0490, B:254:0x0498, B:263:0x04ae, B:264:0x04b7, B:266:0x04bb, B:273:0x04c9, B:275:0x04d8, B:276:0x04e0, B:257:0x04a5, B:234:0x0457, B:244:0x046e, B:245:0x0473, B:161:0x0315, B:165:0x0322, B:167:0x0329, B:169:0x0333, B:215:0x03e0, B:177:0x0349, B:180:0x034f, B:183:0x035b, B:185:0x0360, B:186:0x036d, B:187:0x036e, B:189:0x037b, B:190:0x037e, B:192:0x038a, B:193:0x038d, B:196:0x0396, B:197:0x039e, B:198:0x039f, B:203:0x03af, B:204:0x03b2, B:205:0x03b6, B:207:0x03c4, B:213:0x03db, B:211:0x03d5, B:212:0x03d8, B:168:0x032c, B:164:0x031a, B:278:0x04e9, B:282:0x04f6, B:284:0x04fa, B:286:0x052c, B:290:0x053b, B:285:0x0510, B:296:0x055f, B:298:0x0563, B:299:0x058f, B:301:0x05b2, B:304:0x05bf, B:307:0x05db, B:311:0x0605, B:316:0x0625, B:319:0x0634, B:320:0x0660, B:327:0x0673, B:328:0x067a, B:329:0x067b, B:331:0x0684, B:332:0x068e, B:335:0x0698, B:339:0x06b7, B:343:0x06c5, B:344:0x06cc, B:345:0x06cd, B:348:0x06da, B:351:0x06eb, B:353:0x06ef, B:355:0x06f3, B:364:0x0703, B:365:0x070c, B:366:0x0714, B:370:0x071c, B:371:0x071f, B:373:0x0723, B:375:0x072b, B:376:0x073a, B:377:0x073f, B:485:0x0941, B:381:0x074b, B:383:0x0754, B:389:0x077e, B:384:0x075c, B:386:0x0764, B:388:0x0770, B:390:0x0785, B:392:0x078d, B:395:0x0794, B:397:0x07b8, B:400:0x07c3, B:403:0x07cb, B:405:0x07d3, B:407:0x07db, B:408:0x07e3, B:412:0x07ea, B:414:0x07f4, B:416:0x07fb, B:427:0x0816, B:432:0x0829, B:437:0x083e, B:438:0x084b, B:439:0x0861, B:440:0x0862, B:441:0x086a, B:444:0x0876, B:446:0x087e, B:448:0x0886, B:449:0x088e, B:453:0x0895, B:455:0x089f, B:456:0x08a4, B:461:0x08b6, B:462:0x08bd, B:463:0x08c7, B:464:0x08d1, B:466:0x08dc, B:470:0x08e3, B:472:0x08eb, B:474:0x08f0, B:475:0x08f1, B:477:0x0913, B:481:0x091a, B:483:0x0922, B:29:0x0069, B:31:0x006f, B:33:0x0077, B:35:0x007b, B:36:0x0099, B:40:0x00a1, B:42:0x00a7, B:43:0x00ac, B:47:0x00c2, B:49:0x00c8, B:52:0x00cf, B:53:0x00d4, B:55:0x00d6, B:44:0x00b4, B:45:0x00be, B:25:0x0059), top: B:495:0x0004, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:498:0x06eb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00f4 A[Catch: h -> 0x094a, TryCatch #2 {h -> 0x094a, blocks: (B:3:0x0004, B:6:0x0009, B:8:0x000d, B:9:0x0013, B:11:0x0019, B:13:0x0021, B:15:0x0029, B:57:0x00d8, B:59:0x00e1, B:60:0x00e9, B:62:0x00f4, B:65:0x00fa, B:66:0x00fd, B:69:0x010b, B:72:0x0111, B:73:0x0116, B:74:0x0117, B:82:0x012e, B:83:0x0134, B:85:0x0138, B:87:0x0140, B:88:0x0155, B:90:0x0159, B:91:0x0161, B:92:0x0193, B:93:0x019a, B:98:0x01a6, B:100:0x01ab, B:101:0x01b0, B:102:0x01c4, B:104:0x01c8, B:106:0x01d0, B:107:0x01e5, B:109:0x01e9, B:110:0x01f1, B:111:0x0223, B:117:0x0234, B:118:0x023e, B:120:0x0242, B:122:0x0246, B:123:0x025a, B:124:0x0261, B:126:0x0265, B:127:0x0279, B:128:0x0280, B:134:0x028f, B:136:0x0297, B:137:0x029c, B:138:0x02b0, B:140:0x02b4, B:142:0x02b8, B:143:0x02cc, B:144:0x02d3, B:146:0x02d7, B:147:0x02eb, B:151:0x02f8, B:153:0x02fc, B:155:0x0304, B:157:0x030c, B:216:0x03fa, B:220:0x0407, B:222:0x0413, B:224:0x0425, B:225:0x0442, B:233:0x0452, B:247:0x047a, B:249:0x0480, B:251:0x0488, B:253:0x0490, B:254:0x0498, B:263:0x04ae, B:264:0x04b7, B:266:0x04bb, B:273:0x04c9, B:275:0x04d8, B:276:0x04e0, B:257:0x04a5, B:234:0x0457, B:244:0x046e, B:245:0x0473, B:161:0x0315, B:165:0x0322, B:167:0x0329, B:169:0x0333, B:215:0x03e0, B:177:0x0349, B:180:0x034f, B:183:0x035b, B:185:0x0360, B:186:0x036d, B:187:0x036e, B:189:0x037b, B:190:0x037e, B:192:0x038a, B:193:0x038d, B:196:0x0396, B:197:0x039e, B:198:0x039f, B:203:0x03af, B:204:0x03b2, B:205:0x03b6, B:207:0x03c4, B:213:0x03db, B:211:0x03d5, B:212:0x03d8, B:168:0x032c, B:164:0x031a, B:278:0x04e9, B:282:0x04f6, B:284:0x04fa, B:286:0x052c, B:290:0x053b, B:285:0x0510, B:296:0x055f, B:298:0x0563, B:299:0x058f, B:301:0x05b2, B:304:0x05bf, B:307:0x05db, B:311:0x0605, B:316:0x0625, B:319:0x0634, B:320:0x0660, B:327:0x0673, B:328:0x067a, B:329:0x067b, B:331:0x0684, B:332:0x068e, B:335:0x0698, B:339:0x06b7, B:343:0x06c5, B:344:0x06cc, B:345:0x06cd, B:348:0x06da, B:351:0x06eb, B:353:0x06ef, B:355:0x06f3, B:364:0x0703, B:365:0x070c, B:366:0x0714, B:370:0x071c, B:371:0x071f, B:373:0x0723, B:375:0x072b, B:376:0x073a, B:377:0x073f, B:485:0x0941, B:381:0x074b, B:383:0x0754, B:389:0x077e, B:384:0x075c, B:386:0x0764, B:388:0x0770, B:390:0x0785, B:392:0x078d, B:395:0x0794, B:397:0x07b8, B:400:0x07c3, B:403:0x07cb, B:405:0x07d3, B:407:0x07db, B:408:0x07e3, B:412:0x07ea, B:414:0x07f4, B:416:0x07fb, B:427:0x0816, B:432:0x0829, B:437:0x083e, B:438:0x084b, B:439:0x0861, B:440:0x0862, B:441:0x086a, B:444:0x0876, B:446:0x087e, B:448:0x0886, B:449:0x088e, B:453:0x0895, B:455:0x089f, B:456:0x08a4, B:461:0x08b6, B:462:0x08bd, B:463:0x08c7, B:464:0x08d1, B:466:0x08dc, B:470:0x08e3, B:472:0x08eb, B:474:0x08f0, B:475:0x08f1, B:477:0x0913, B:481:0x091a, B:483:0x0922, B:29:0x0069, B:31:0x006f, B:33:0x0077, B:35:0x007b, B:36:0x0099, B:40:0x00a1, B:42:0x00a7, B:43:0x00ac, B:47:0x00c2, B:49:0x00c8, B:52:0x00cf, B:53:0x00d4, B:55:0x00d6, B:44:0x00b4, B:45:0x00be, B:25:0x0059), top: B:495:0x0004, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x019a A[Catch: h -> 0x094a, TryCatch #2 {h -> 0x094a, blocks: (B:3:0x0004, B:6:0x0009, B:8:0x000d, B:9:0x0013, B:11:0x0019, B:13:0x0021, B:15:0x0029, B:57:0x00d8, B:59:0x00e1, B:60:0x00e9, B:62:0x00f4, B:65:0x00fa, B:66:0x00fd, B:69:0x010b, B:72:0x0111, B:73:0x0116, B:74:0x0117, B:82:0x012e, B:83:0x0134, B:85:0x0138, B:87:0x0140, B:88:0x0155, B:90:0x0159, B:91:0x0161, B:92:0x0193, B:93:0x019a, B:98:0x01a6, B:100:0x01ab, B:101:0x01b0, B:102:0x01c4, B:104:0x01c8, B:106:0x01d0, B:107:0x01e5, B:109:0x01e9, B:110:0x01f1, B:111:0x0223, B:117:0x0234, B:118:0x023e, B:120:0x0242, B:122:0x0246, B:123:0x025a, B:124:0x0261, B:126:0x0265, B:127:0x0279, B:128:0x0280, B:134:0x028f, B:136:0x0297, B:137:0x029c, B:138:0x02b0, B:140:0x02b4, B:142:0x02b8, B:143:0x02cc, B:144:0x02d3, B:146:0x02d7, B:147:0x02eb, B:151:0x02f8, B:153:0x02fc, B:155:0x0304, B:157:0x030c, B:216:0x03fa, B:220:0x0407, B:222:0x0413, B:224:0x0425, B:225:0x0442, B:233:0x0452, B:247:0x047a, B:249:0x0480, B:251:0x0488, B:253:0x0490, B:254:0x0498, B:263:0x04ae, B:264:0x04b7, B:266:0x04bb, B:273:0x04c9, B:275:0x04d8, B:276:0x04e0, B:257:0x04a5, B:234:0x0457, B:244:0x046e, B:245:0x0473, B:161:0x0315, B:165:0x0322, B:167:0x0329, B:169:0x0333, B:215:0x03e0, B:177:0x0349, B:180:0x034f, B:183:0x035b, B:185:0x0360, B:186:0x036d, B:187:0x036e, B:189:0x037b, B:190:0x037e, B:192:0x038a, B:193:0x038d, B:196:0x0396, B:197:0x039e, B:198:0x039f, B:203:0x03af, B:204:0x03b2, B:205:0x03b6, B:207:0x03c4, B:213:0x03db, B:211:0x03d5, B:212:0x03d8, B:168:0x032c, B:164:0x031a, B:278:0x04e9, B:282:0x04f6, B:284:0x04fa, B:286:0x052c, B:290:0x053b, B:285:0x0510, B:296:0x055f, B:298:0x0563, B:299:0x058f, B:301:0x05b2, B:304:0x05bf, B:307:0x05db, B:311:0x0605, B:316:0x0625, B:319:0x0634, B:320:0x0660, B:327:0x0673, B:328:0x067a, B:329:0x067b, B:331:0x0684, B:332:0x068e, B:335:0x0698, B:339:0x06b7, B:343:0x06c5, B:344:0x06cc, B:345:0x06cd, B:348:0x06da, B:351:0x06eb, B:353:0x06ef, B:355:0x06f3, B:364:0x0703, B:365:0x070c, B:366:0x0714, B:370:0x071c, B:371:0x071f, B:373:0x0723, B:375:0x072b, B:376:0x073a, B:377:0x073f, B:485:0x0941, B:381:0x074b, B:383:0x0754, B:389:0x077e, B:384:0x075c, B:386:0x0764, B:388:0x0770, B:390:0x0785, B:392:0x078d, B:395:0x0794, B:397:0x07b8, B:400:0x07c3, B:403:0x07cb, B:405:0x07d3, B:407:0x07db, B:408:0x07e3, B:412:0x07ea, B:414:0x07f4, B:416:0x07fb, B:427:0x0816, B:432:0x0829, B:437:0x083e, B:438:0x084b, B:439:0x0861, B:440:0x0862, B:441:0x086a, B:444:0x0876, B:446:0x087e, B:448:0x0886, B:449:0x088e, B:453:0x0895, B:455:0x089f, B:456:0x08a4, B:461:0x08b6, B:462:0x08bd, B:463:0x08c7, B:464:0x08d1, B:466:0x08dc, B:470:0x08e3, B:472:0x08eb, B:474:0x08f0, B:475:0x08f1, B:477:0x0913, B:481:0x091a, B:483:0x0922, B:29:0x0069, B:31:0x006f, B:33:0x0077, B:35:0x007b, B:36:0x0099, B:40:0x00a1, B:42:0x00a7, B:43:0x00ac, B:47:0x00c2, B:49:0x00c8, B:52:0x00cf, B:53:0x00d4, B:55:0x00d6, B:44:0x00b4, B:45:0x00be, B:25:0x0059), top: B:495:0x0004, inners: #3 }] */
    /* JADX WARN: Type inference failed for: r2v100, types: [h5.n, i5.f] */
    /* JADX WARN: Type inference failed for: r2v95, types: [h5.q$a] */
    /* JADX WARN: Type inference failed for: r3v111, types: [h5.n, i5.f] */
    /* JADX WARN: Type inference failed for: r7v40, types: [i5.a, i5.e] */
    /* JADX WARN: Type inference failed for: r7v41, types: [i5.e] */
    /* JADX WARN: Type inference failed for: r7v42, types: [i5.e] */
    /* JADX WARN: Type inference failed for: r7v60 */
    /* JADX WARN: Type inference failed for: r7v61 */
    /* renamed from: h */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int m1248h() {
        /*
            Method dump skipped, instructions count: 2438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p065h5.C1109q.m1248h():int");
    }

    /* renamed from: i */
    public void m1249i() {
        InterfaceC1152e interfaceC1152e;
        C1166s c1166s = this.f2353m;
        c1166s.mo1331i(c1166s.f2525h);
        this.f2354n = this.f2352l ? -14 : this.f2343c.mo926s() ? 0 : 7;
        this.f2357q = -3L;
        this.f2358r = 0L;
        this.f2356p = 0;
        this.f2351k = 0;
        if (this.f2355o == 13 && (interfaceC1152e = this.f2346f) != null && interfaceC1152e.mo1314A() && this.f2346f.peek() == 10) {
            this.f2355o = this.f2346f.get();
        }
        InterfaceC1152e interfaceC1152e2 = this.f2345e;
        if (interfaceC1152e2 != null && interfaceC1152e2.mo1314A()) {
            InterfaceC1152e interfaceC1152e3 = this.f2344d;
            if (interfaceC1152e3 == null) {
                m1242b();
            } else {
                interfaceC1152e3.mo1326V(-1);
                this.f2344d.mo1340w();
            }
            int iMo1317D = this.f2344d.mo1317D();
            if (iMo1317D > this.f2345e.length()) {
                iMo1317D = this.f2345e.length();
            }
            InterfaceC1152e interfaceC1152e4 = this.f2345e;
            interfaceC1152e4.mo1338u(interfaceC1152e4.mo1316C(), iMo1317D);
            InterfaceC1152e interfaceC1152e5 = this.f2345e;
            interfaceC1152e5.mo1330h(this.f2344d.mo1337t(interfaceC1152e5.mo1338u(interfaceC1152e5.mo1316C(), iMo1317D)));
        }
        InterfaceC1152e interfaceC1152e6 = this.f2344d;
        if (interfaceC1152e6 != null) {
            interfaceC1152e6.mo1326V(-1);
            this.f2344d.mo1340w();
        }
        InterfaceC1152e interfaceC1152e7 = this.f2345e;
        if (interfaceC1152e7 != null) {
            interfaceC1152e7.mo1326V(-1);
        }
        this.f2346f = this.f2344d;
        m1250j();
    }

    /* renamed from: j */
    public void m1250j() {
        InterfaceC1156i interfaceC1156i;
        InterfaceC1156i interfaceC1156i2;
        InterfaceC1152e interfaceC1152e = this.f2345e;
        if (interfaceC1152e != null && !interfaceC1152e.mo1314A() && this.f2345e.mo1323O() == -1 && (interfaceC1156i2 = this.f2342b) != null) {
            InterfaceC1152e interfaceC1152e2 = this.f2346f;
            InterfaceC1152e interfaceC1152e3 = this.f2345e;
            if (interfaceC1152e2 == interfaceC1152e3) {
                this.f2346f = this.f2344d;
            }
            if (interfaceC1156i2 != null) {
                interfaceC1156i2.mo1368a(interfaceC1152e3);
            }
            this.f2345e = null;
        }
        InterfaceC1152e interfaceC1152e4 = this.f2344d;
        if (interfaceC1152e4 == null || interfaceC1152e4.mo1314A() || this.f2344d.mo1323O() != -1 || (interfaceC1156i = this.f2342b) == null) {
            return;
        }
        InterfaceC1152e interfaceC1152e5 = this.f2346f;
        InterfaceC1152e interfaceC1152e6 = this.f2344d;
        if (interfaceC1152e5 == interfaceC1152e6) {
            this.f2346f = null;
        }
        interfaceC1156i.mo1368a(interfaceC1152e6);
        this.f2344d = null;
    }

    public String toString() {
        return String.format("%s{s=%d,l=%d,c=%d}", C1109q.class.getSimpleName(), Integer.valueOf(this.f2354n), Integer.valueOf(this.f2356p), Long.valueOf(this.f2357q));
    }
}
