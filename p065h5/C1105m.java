package p065h5;

import android.support.v7.widget.ActivityChooserView;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Objects;
import java.util.Properties;
import p043f.C0986c;
import p073i5.AbstractC1148a;
import p073i5.C1155h;
import p073i5.C1158k;
import p073i5.C1162o;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1156i;
import p073i5.InterfaceC1161n;
import p082j6.C1212a;
import p161t5.C1926r;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: HttpGenerator.java */
/* renamed from: h5.m */
/* loaded from: classes.dex */
public class C1105m extends AbstractC1093a {

    /* renamed from: A */
    public static final byte[] f2307A;

    /* renamed from: B */
    public static final byte[] f2308B;

    /* renamed from: C */
    public static final byte[] f2309C;

    /* renamed from: D */
    public static final byte[] f2310D;

    /* renamed from: E */
    public static final byte[] f2311E;

    /* renamed from: F */
    public static final byte[] f2312F;

    /* renamed from: G */
    public static final byte[] f2313G;

    /* renamed from: H */
    public static byte[] f2314H;

    /* renamed from: y */
    public static final InterfaceC2016c f2315y;

    /* renamed from: z */
    public static final b[] f2316z;

    /* renamed from: u */
    public boolean f2317u;

    /* renamed from: v */
    public boolean f2318v;

    /* renamed from: w */
    public boolean f2319w;

    /* renamed from: x */
    public boolean f2320x;

    /* compiled from: HttpGenerator.java */
    /* renamed from: h5.m$b */
    public static class b {

        /* renamed from: a */
        public InterfaceC1152e f2321a;

        /* renamed from: b */
        public InterfaceC1152e f2322b;

        public b(a aVar) {
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f2315y = C2015b.m2349a(C1105m.class.getName());
        f2316z = new b[508];
        int length = ((AbstractC1148a) C1114v.f2382c).length();
        int i7 = 0;
        while (i7 < f2316z.length) {
            int[] iArr = C1111s.f2364a;
            int i8 = i7 <= 507 ? C1111s.f2364a[i7] : 0;
            if (i8 != 0) {
                String strM954g = C0986c.m954g(i8);
                int i9 = length + 5;
                int length2 = strM954g.length() + i9 + 2;
                byte[] bArr = new byte[length2];
                ((C1158k) C1114v.f2382c).mo1352m(0, bArr, 0, length);
                bArr[length + 0] = 32;
                bArr[length + 1] = (byte) ((i7 / 100) + 48);
                bArr[length + 2] = (byte) (((i7 % 100) / 10) + 48);
                bArr[length + 3] = (byte) ((i7 % 10) + 48);
                bArr[length + 4] = 32;
                for (int i10 = 0; i10 < strM954g.length(); i10++) {
                    bArr[i9 + i10] = (byte) strM954g.charAt(i10);
                }
                bArr[strM954g.length() + i9] = C1212a.f2735CR;
                bArr[strM954g.length() + length + 6] = 10;
                b[] bVarArr = f2316z;
                bVarArr[i7] = new b(null);
                b bVar = bVarArr[i7];
                new C1158k(bArr, i9, (length2 - length) - 7, 0);
                Objects.requireNonNull(bVar);
                bVarArr[i7].f2321a = new C1158k(bArr, 0, i9, 0);
                bVarArr[i7].f2322b = new C1158k(bArr, 0, length2, 0);
            }
            i7++;
        }
        f2307A = new byte[]{48, C1212a.f2735CR, 10, C1212a.f2735CR, 10};
        f2308B = C1926r.m2253c("Content-Length: 0\r\n");
        f2309C = C1926r.m2253c("Connection: keep-alive\r\n");
        f2310D = C1926r.m2253c("Connection: close\r\n");
        f2311E = C1926r.m2253c("Connection: ");
        f2312F = C1926r.m2253c("\r\n");
        f2313G = C1926r.m2253c("Transfer-Encoding: chunked\r\n");
        f2314H = C1926r.m2253c("Server: Jetty(7.0.x)\r\n");
    }

    public C1105m(InterfaceC1156i interfaceC1156i, InterfaceC1161n interfaceC1161n) {
        super(interfaceC1156i, interfaceC1161n);
        this.f2317u = false;
        this.f2318v = false;
        this.f2319w = false;
        this.f2320x = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x023b A[Catch: ArrayIndexOutOfBoundsException -> 0x0539, TryCatch #0 {ArrayIndexOutOfBoundsException -> 0x0539, blocks: (B:21:0x0039, B:23:0x0050, B:26:0x005a, B:28:0x0080, B:30:0x00a2, B:32:0x00a7, B:75:0x0194, B:77:0x019a, B:79:0x019e, B:82:0x01c2, B:84:0x01d4, B:193:0x0376, B:87:0x01e0, B:95:0x01f7, B:96:0x01fe, B:98:0x0202, B:99:0x020c, B:111:0x023b, B:112:0x023f, B:102:0x021b, B:103:0x0221, B:105:0x0225, B:108:0x0233, B:113:0x0248, B:115:0x0258, B:121:0x0264, B:122:0x026d, B:125:0x0274, B:127:0x027a, B:128:0x027f, B:135:0x0294, B:137:0x029f, B:136:0x029a, B:138:0x02a8, B:140:0x02ae, B:141:0x02b5, B:143:0x02bb, B:145:0x02c1, B:147:0x02c9, B:149:0x02cf, B:150:0x02d3, B:152:0x02db, B:154:0x02e1, B:156:0x02e7, B:158:0x02f0, B:160:0x02fd, B:162:0x0300, B:164:0x030e, B:168:0x0317, B:170:0x0322, B:192:0x0372, B:169:0x031d, B:171:0x0328, B:173:0x032e, B:175:0x0334, B:177:0x033a, B:179:0x0340, B:180:0x0344, B:182:0x034c, B:184:0x0352, B:186:0x0358, B:189:0x0362, B:191:0x036d, B:190:0x0368, B:195:0x038c, B:240:0x0443, B:243:0x044b, B:245:0x0456, B:247:0x0462, B:248:0x0468, B:249:0x046f, B:250:0x0470, B:251:0x0477, B:253:0x047f, B:255:0x0486, B:257:0x048c, B:260:0x0496, B:262:0x049c, B:264:0x04a5, B:266:0x04ce, B:268:0x04d7, B:270:0x0500, B:272:0x051d, B:274:0x0523, B:276:0x0527, B:277:0x052e, B:201:0x039c, B:203:0x03a2, B:209:0x03b0, B:210:0x03b9, B:211:0x03c5, B:213:0x03cb, B:215:0x03cf, B:216:0x03d7, B:218:0x03db, B:220:0x03e1, B:222:0x03e7, B:225:0x03f1, B:227:0x03f5, B:228:0x0419, B:230:0x0421, B:235:0x042c, B:237:0x0434, B:239:0x043c, B:31:0x00a5, B:33:0x00b3, B:35:0x00b7, B:37:0x00c0, B:42:0x00c9, B:43:0x00cf, B:45:0x00d6, B:49:0x00de, B:51:0x0112, B:53:0x0137, B:58:0x0160, B:61:0x0166, B:63:0x016e, B:64:0x0171, B:66:0x0177, B:72:0x0189, B:74:0x0191, B:52:0x0132, B:54:0x013f, B:56:0x0143, B:57:0x014b), top: B:282:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02c9 A[Catch: ArrayIndexOutOfBoundsException -> 0x0539, TryCatch #0 {ArrayIndexOutOfBoundsException -> 0x0539, blocks: (B:21:0x0039, B:23:0x0050, B:26:0x005a, B:28:0x0080, B:30:0x00a2, B:32:0x00a7, B:75:0x0194, B:77:0x019a, B:79:0x019e, B:82:0x01c2, B:84:0x01d4, B:193:0x0376, B:87:0x01e0, B:95:0x01f7, B:96:0x01fe, B:98:0x0202, B:99:0x020c, B:111:0x023b, B:112:0x023f, B:102:0x021b, B:103:0x0221, B:105:0x0225, B:108:0x0233, B:113:0x0248, B:115:0x0258, B:121:0x0264, B:122:0x026d, B:125:0x0274, B:127:0x027a, B:128:0x027f, B:135:0x0294, B:137:0x029f, B:136:0x029a, B:138:0x02a8, B:140:0x02ae, B:141:0x02b5, B:143:0x02bb, B:145:0x02c1, B:147:0x02c9, B:149:0x02cf, B:150:0x02d3, B:152:0x02db, B:154:0x02e1, B:156:0x02e7, B:158:0x02f0, B:160:0x02fd, B:162:0x0300, B:164:0x030e, B:168:0x0317, B:170:0x0322, B:192:0x0372, B:169:0x031d, B:171:0x0328, B:173:0x032e, B:175:0x0334, B:177:0x033a, B:179:0x0340, B:180:0x0344, B:182:0x034c, B:184:0x0352, B:186:0x0358, B:189:0x0362, B:191:0x036d, B:190:0x0368, B:195:0x038c, B:240:0x0443, B:243:0x044b, B:245:0x0456, B:247:0x0462, B:248:0x0468, B:249:0x046f, B:250:0x0470, B:251:0x0477, B:253:0x047f, B:255:0x0486, B:257:0x048c, B:260:0x0496, B:262:0x049c, B:264:0x04a5, B:266:0x04ce, B:268:0x04d7, B:270:0x0500, B:272:0x051d, B:274:0x0523, B:276:0x0527, B:277:0x052e, B:201:0x039c, B:203:0x03a2, B:209:0x03b0, B:210:0x03b9, B:211:0x03c5, B:213:0x03cb, B:215:0x03cf, B:216:0x03d7, B:218:0x03db, B:220:0x03e1, B:222:0x03e7, B:225:0x03f1, B:227:0x03f5, B:228:0x0419, B:230:0x0421, B:235:0x042c, B:237:0x0434, B:239:0x043c, B:31:0x00a5, B:33:0x00b3, B:35:0x00b7, B:37:0x00c0, B:42:0x00c9, B:43:0x00cf, B:45:0x00d6, B:49:0x00de, B:51:0x0112, B:53:0x0137, B:58:0x0160, B:61:0x0166, B:63:0x016e, B:64:0x0171, B:66:0x0177, B:72:0x0189, B:74:0x0191, B:52:0x0132, B:54:0x013f, B:56:0x0143, B:57:0x014b), top: B:282:0x0039 }] */
    @Override // p065h5.AbstractC1093a
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo1182b(p065h5.C1101i r21, boolean r22) throws p073i5.C1162o {
        /*
            Method dump skipped, instructions count: 1363
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p065h5.C1105m.mo1182b(h5.i, boolean):void");
    }

    @Override // p065h5.AbstractC1093a, p065h5.InterfaceC1095c
    public void complete() throws IOException {
        if (this.f2244c == 4) {
            return;
        }
        super.complete();
        if (this.f2244c < 3) {
            this.f2244c = 3;
            if (this.f2251j == -2) {
                this.f2319w = true;
            }
        }
        mo1184d();
    }

    @Override // p065h5.AbstractC1093a
    /* renamed from: d */
    public int mo1184d() throws IOException {
        InterfaceC1152e interfaceC1152e;
        Boolean bool;
        InterfaceC1152e interfaceC1152e2;
        InterfaceC1152e interfaceC1152e3;
        try {
            if (this.f2244c == 0) {
                throw new IllegalStateException("State==HEADER");
            }
            m1239x();
            if (this.f2243b == null) {
                if (this.f2318v && (interfaceC1152e3 = this.f2257p) != null) {
                    interfaceC1152e3.mo1328c(InterfaceC1112t.f2365a);
                }
                if (this.f2319w && (interfaceC1152e2 = this.f2257p) != null && !this.f2253l) {
                    interfaceC1152e2.mo1328c(f2307A);
                }
                this.f2318v = false;
                this.f2319w = false;
                return 0;
            }
            int iMo931x = -1;
            int iM1236u = m1236u();
            int i7 = 0;
            while (true) {
                switch (iM1236u) {
                    case 0:
                        InterfaceC1152e interfaceC1152e4 = this.f2256o;
                        if (interfaceC1152e4 != null) {
                            interfaceC1152e4.clear();
                        }
                        this.f2317u = false;
                        this.f2320x = false;
                        InterfaceC1152e interfaceC1152e5 = this.f2257p;
                        if (interfaceC1152e5 != null) {
                            interfaceC1152e5.clear();
                            if (this.f2251j == -2) {
                                this.f2257p.mo1324Q(12);
                                this.f2257p.mo1331i(12);
                                InterfaceC1152e interfaceC1152e6 = this.f2258q;
                                if (interfaceC1152e6 != null && interfaceC1152e6.length() < this.f2257p.mo1317D() && this.f2244c != 3) {
                                    this.f2257p.mo1337t(this.f2258q);
                                    this.f2258q.clear();
                                    this.f2258q = null;
                                }
                            }
                        }
                        if (this.f2318v || this.f2319w || !((interfaceC1152e = this.f2258q) == null || interfaceC1152e.length() == 0)) {
                            m1239x();
                        } else {
                            if (this.f2244c == 3) {
                                this.f2244c = 4;
                            }
                            if (this.f2244c == 4 && (bool = this.f2255n) != null && !bool.booleanValue() && this.f2245d != 100 && this.f2248g == null) {
                                this.f2243b.mo927t();
                            }
                        }
                        iMo931x = 0;
                        break;
                    case 1:
                        iMo931x = this.f2243b.mo931x(this.f2258q);
                        break;
                    case 2:
                        iMo931x = this.f2243b.mo931x(this.f2257p);
                        break;
                    case 3:
                        iMo931x = this.f2243b.mo917j(this.f2257p, this.f2258q, null);
                        break;
                    case 4:
                        iMo931x = this.f2243b.mo931x(this.f2256o);
                        break;
                    case 5:
                        iMo931x = this.f2243b.mo917j(this.f2256o, this.f2258q, null);
                        break;
                    case 6:
                        iMo931x = this.f2243b.mo917j(this.f2256o, this.f2257p, null);
                        break;
                    case 7:
                        throw new IllegalStateException();
                }
                if (iMo931x > 0) {
                    i7 += iMo931x;
                }
                int iM1236u2 = m1236u();
                if (iMo931x > 0 || (iM1236u2 != 0 && iM1236u == 0)) {
                    iM1236u = iM1236u2;
                }
            }
            return i7;
        } catch (IOException e7) {
            f2315y.mo2360k(e7);
            if (e7 instanceof C1162o) {
                throw e7;
            }
            throw new C1162o(e7);
        }
    }

    @Override // p065h5.AbstractC1093a
    /* renamed from: f */
    public boolean mo1186f() {
        InterfaceC1152e interfaceC1152e;
        return super.mo1186f() || this.f2320x || this.f2317u || (this.f2251j == -2 && (interfaceC1152e = this.f2257p) != null && interfaceC1152e.mo1317D() < 12);
    }

    @Override // p065h5.AbstractC1093a
    /* renamed from: l */
    public boolean mo1192l() {
        return this.f2248g != null;
    }

    @Override // p065h5.AbstractC1093a
    /* renamed from: m */
    public int mo1193m() throws IOException {
        if (this.f2254m || this.f2252k || this.f2244c == 4) {
            return -1;
        }
        InterfaceC1152e interfaceC1152e = this.f2258q;
        if ((interfaceC1152e != null && interfaceC1152e.length() > 0) || this.f2320x) {
            mo1184d();
            if ((interfaceC1152e != null && interfaceC1152e.length() > 0) || this.f2320x) {
                throw new IllegalStateException("FULL");
            }
        }
        if (this.f2257p == null) {
            this.f2257p = this.f2242a.getBuffer();
        }
        this.f2250i -= this.f2257p.length();
        if (this.f2253l) {
            return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        return this.f2257p.mo1317D() - (this.f2251j == -2 ? 12 : 0);
    }

    @Override // p065h5.AbstractC1093a, p065h5.InterfaceC1095c
    public void reset() {
        InterfaceC1161n interfaceC1161n;
        Boolean bool = this.f2255n;
        if (bool != null && !bool.booleanValue() && (interfaceC1161n = this.f2243b) != null && !interfaceC1161n.mo925r()) {
            try {
                this.f2243b.mo927t();
            } catch (IOException e7) {
                f2315y.mo2360k(e7);
            }
        }
        super.reset();
        InterfaceC1152e interfaceC1152e = this.f2257p;
        if (interfaceC1152e != null) {
            interfaceC1152e.clear();
        }
        InterfaceC1152e interfaceC1152e2 = this.f2256o;
        if (interfaceC1152e2 != null) {
            interfaceC1152e2.clear();
        }
        if (this.f2258q != null) {
            this.f2258q = null;
        }
        this.f2317u = false;
        this.f2318v = false;
        this.f2319w = false;
        this.f2320x = false;
        this.f2248g = null;
        this.f2249h = null;
        this.f2254m = false;
    }

    /* renamed from: t */
    public void m1235t(InterfaceC1152e interfaceC1152e, boolean z6) throws IOException {
        InterfaceC1152e interfaceC1152e2;
        InterfaceC1152e interfaceC1152eMo1369b;
        if (this.f2254m) {
            throw new IllegalStateException("NO CONTENT");
        }
        if (this.f2252k || this.f2244c == 4) {
            f2315y.mo2356g("Ignoring extra content {}", interfaceC1152e);
            interfaceC1152e.clear();
            return;
        }
        this.f2252k = z6;
        InterfaceC1152e interfaceC1152e3 = this.f2258q;
        if ((interfaceC1152e3 != null && interfaceC1152e3.length() > 0) || this.f2320x) {
            if (this.f2243b.mo925r()) {
                throw new C1162o();
            }
            mo1184d();
            InterfaceC1152e interfaceC1152e4 = this.f2258q;
            if (interfaceC1152e4 != null && interfaceC1152e4.length() > 0) {
                if (this.f2320x) {
                    interfaceC1152eMo1369b = this.f2242a.mo1369b(interfaceC1152e.length() + this.f2258q.length() + 12);
                    interfaceC1152eMo1369b.mo1337t(this.f2258q);
                    byte[] bArr = InterfaceC1112t.f2365a;
                    interfaceC1152eMo1369b.mo1328c(bArr);
                    C1155h.m1364b(interfaceC1152eMo1369b, interfaceC1152e.length());
                    interfaceC1152eMo1369b.mo1328c(bArr);
                    interfaceC1152eMo1369b.mo1337t(interfaceC1152e);
                } else {
                    interfaceC1152eMo1369b = this.f2242a.mo1369b(interfaceC1152e.length() + this.f2258q.length());
                    interfaceC1152eMo1369b.mo1337t(this.f2258q);
                    interfaceC1152eMo1369b.mo1337t(interfaceC1152e);
                }
                interfaceC1152e = interfaceC1152eMo1369b;
            }
        }
        this.f2258q = interfaceC1152e;
        this.f2250i += interfaceC1152e.length();
        if (this.f2253l) {
            interfaceC1152e.clear();
            this.f2258q = null;
            return;
        }
        if (this.f2243b != null && (((interfaceC1152e2 = this.f2257p) == null || interfaceC1152e2.length() == 0) && this.f2258q.length() > 0 && (this.f2252k || (m1187g() && this.f2258q.length() > 1024)))) {
            this.f2317u = true;
            return;
        }
        if (this.f2320x) {
            return;
        }
        if (this.f2257p == null) {
            this.f2257p = this.f2242a.getBuffer();
        }
        this.f2258q.mo1330h(this.f2257p.mo1337t(this.f2258q));
        if (this.f2258q.length() == 0) {
            this.f2258q = null;
        }
    }

    public String toString() {
        InterfaceC1152e interfaceC1152e = this.f2256o;
        InterfaceC1152e interfaceC1152e2 = this.f2257p;
        InterfaceC1152e interfaceC1152e3 = this.f2258q;
        Object[] objArr = new Object[5];
        objArr[0] = C1105m.class.getSimpleName();
        objArr[1] = Integer.valueOf(this.f2244c);
        objArr[2] = Integer.valueOf(interfaceC1152e == null ? -1 : interfaceC1152e.length());
        objArr[3] = Integer.valueOf(interfaceC1152e2 == null ? -1 : interfaceC1152e2.length());
        objArr[4] = Integer.valueOf(interfaceC1152e3 != null ? interfaceC1152e3.length() : -1);
        return String.format("%s{s=%d,h=%d,b=%d,c=%d}", objArr);
    }

    /* renamed from: u */
    public final int m1236u() {
        InterfaceC1152e interfaceC1152e;
        InterfaceC1152e interfaceC1152e2 = this.f2256o;
        int i7 = 0;
        int i8 = (interfaceC1152e2 == null || interfaceC1152e2.length() <= 0) ? 0 : 4;
        InterfaceC1152e interfaceC1152e3 = this.f2257p;
        int i9 = i8 | ((interfaceC1152e3 == null || interfaceC1152e3.length() <= 0) ? 0 : 2);
        if (this.f2317u && (interfaceC1152e = this.f2258q) != null && interfaceC1152e.length() > 0) {
            i7 = 1;
        }
        return i9 | i7;
    }

    /* renamed from: v */
    public boolean m1237v() {
        InterfaceC1152e interfaceC1152e;
        InterfaceC1152e interfaceC1152e2;
        InterfaceC1152e interfaceC1152e3 = this.f2256o;
        return (interfaceC1152e3 == null || interfaceC1152e3.length() == 0) && ((interfaceC1152e = this.f2257p) == null || interfaceC1152e.length() == 0) && ((interfaceC1152e2 = this.f2258q) == null || interfaceC1152e2.length() == 0);
    }

    /* renamed from: w */
    public boolean m1238w() {
        return this.f2248g == null;
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0194  */
    /* renamed from: x */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m1239x() {
        /*
            Method dump skipped, instructions count: 502
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p065h5.C1105m.m1239x():void");
    }

    /* renamed from: y */
    public void m1240y(int i7) {
        if (this.f2244c != 0) {
            return;
        }
        if (i7 < 100 || i7 > 199) {
            throw new IllegalArgumentException("!1xx");
        }
        b bVar = f2316z[i7];
        if (bVar == null) {
            throw new IllegalArgumentException(i7 + "?");
        }
        if (this.f2256o == null) {
            this.f2256o = this.f2242a.mo1370c();
        }
        this.f2256o.mo1337t(bVar.f2322b);
        this.f2256o.mo1328c(InterfaceC1112t.f2365a);
        while (this.f2256o.length() > 0) {
            try {
                int iMo931x = this.f2243b.mo931x(this.f2256o);
                if (iMo931x < 0 || !this.f2243b.isOpen()) {
                    throw new C1162o();
                }
                if (iMo931x == 0) {
                    Thread.sleep(100L);
                }
            } catch (InterruptedException e7) {
                f2315y.mo2359j(e7);
                throw new InterruptedIOException(e7.toString());
            }
        }
    }
}
