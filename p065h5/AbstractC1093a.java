package p065h5;

import android.support.constraint.motion.C0079a;
import java.io.IOException;
import java.util.Properties;
import p009b.C0413b;
import p073i5.C1158k;
import p073i5.C1162o;
import p073i5.C1166s;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1156i;
import p073i5.InterfaceC1161n;
import p161t5.C1926r;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: AbstractGenerator.java */
/* renamed from: h5.a */
/* loaded from: classes.dex */
public abstract class AbstractC1093a implements InterfaceC1095c {

    /* renamed from: t */
    public static final InterfaceC2016c f2241t;

    /* renamed from: a */
    public final InterfaceC1156i f2242a;

    /* renamed from: b */
    public final InterfaceC1161n f2243b;

    /* renamed from: f */
    public InterfaceC1152e f2247f;

    /* renamed from: g */
    public InterfaceC1152e f2248g;

    /* renamed from: h */
    public String f2249h;

    /* renamed from: o */
    public InterfaceC1152e f2256o;

    /* renamed from: p */
    public InterfaceC1152e f2257p;

    /* renamed from: q */
    public InterfaceC1152e f2258q;

    /* renamed from: r */
    public InterfaceC1152e f2259r;

    /* renamed from: s */
    public boolean f2260s;

    /* renamed from: c */
    public int f2244c = 0;

    /* renamed from: d */
    public int f2245d = 0;

    /* renamed from: e */
    public int f2246e = 11;

    /* renamed from: i */
    public long f2250i = 0;

    /* renamed from: j */
    public long f2251j = -3;

    /* renamed from: k */
    public boolean f2252k = false;

    /* renamed from: l */
    public boolean f2253l = false;

    /* renamed from: m */
    public boolean f2254m = false;

    /* renamed from: n */
    public Boolean f2255n = null;

    static {
        Properties properties = C2015b.f5863a;
        f2241t = C2015b.m2349a(AbstractC1093a.class.getName());
    }

    public AbstractC1093a(InterfaceC1156i interfaceC1156i, InterfaceC1161n interfaceC1161n) {
        this.f2242a = interfaceC1156i;
        this.f2243b = interfaceC1161n;
    }

    /* renamed from: a */
    public void m1181a(long j7) throws IOException {
        if (this.f2243b.mo923p()) {
            try {
                mo1184d();
                return;
            } catch (IOException e7) {
                this.f2243b.close();
                throw e7;
            }
        }
        if (this.f2243b.mo930w(j7)) {
            mo1184d();
        } else {
            this.f2243b.close();
            throw new C1162o("timeout");
        }
    }

    /* renamed from: b */
    public abstract void mo1182b(C1101i c1101i, boolean z6);

    /* renamed from: c */
    public void m1183c() {
        if (this.f2254m) {
            InterfaceC1152e interfaceC1152e = this.f2257p;
            if (interfaceC1152e != null) {
                interfaceC1152e.clear();
                return;
            }
            return;
        }
        this.f2250i += this.f2257p.length();
        if (this.f2253l) {
            this.f2257p.clear();
        }
    }

    @Override // p065h5.InterfaceC1095c
    public void complete() {
        if (this.f2244c == 0) {
            throw new IllegalStateException("State==HEADER");
        }
        long j7 = this.f2251j;
        if (j7 < 0 || j7 == this.f2250i || this.f2253l) {
            return;
        }
        InterfaceC2016c interfaceC2016c = f2241t;
        if (interfaceC2016c.mo2353d()) {
            StringBuilder sbM112a = C0413b.m112a("ContentLength written==");
            sbM112a.append(this.f2250i);
            sbM112a.append(" != contentLength==");
            sbM112a.append(this.f2251j);
            interfaceC2016c.mo2351a(sbM112a.toString(), new Object[0]);
        }
        this.f2255n = Boolean.FALSE;
    }

    /* renamed from: d */
    public abstract int mo1184d();

    /* renamed from: e */
    public boolean m1185e() {
        long j7 = this.f2251j;
        return j7 >= 0 && this.f2250i >= j7;
    }

    /* renamed from: f */
    public boolean mo1186f() {
        InterfaceC1152e interfaceC1152e = this.f2257p;
        if (interfaceC1152e == null || interfaceC1152e.mo1317D() != 0) {
            InterfaceC1152e interfaceC1152e2 = this.f2258q;
            return interfaceC1152e2 != null && interfaceC1152e2.length() > 0;
        }
        if (this.f2257p.length() == 0 && !this.f2257p.mo1329f()) {
            this.f2257p.mo1340w();
        }
        return this.f2257p.mo1317D() == 0;
    }

    /* renamed from: g */
    public boolean m1187g() {
        return this.f2244c != 0;
    }

    /* renamed from: h */
    public boolean m1188h() {
        return this.f2244c == 4;
    }

    /* renamed from: i */
    public boolean m1189i() {
        return this.f2244c == 0 && this.f2248g == null && this.f2245d == 0;
    }

    /* renamed from: j */
    public boolean m1190j() {
        return this.f2243b.isOpen();
    }

    /* renamed from: k */
    public boolean m1191k() {
        Boolean bool = this.f2255n;
        return bool != null ? bool.booleanValue() : mo1192l() || this.f2246e > 10;
    }

    /* renamed from: l */
    public abstract boolean mo1192l();

    /* renamed from: m */
    public abstract int mo1193m();

    /* renamed from: n */
    public void m1194n() {
        InterfaceC1152e interfaceC1152e = this.f2257p;
        if (interfaceC1152e != null && interfaceC1152e.length() == 0) {
            this.f2242a.mo1368a(this.f2257p);
            this.f2257p = null;
        }
        InterfaceC1152e interfaceC1152e2 = this.f2256o;
        if (interfaceC1152e2 == null || interfaceC1152e2.length() != 0) {
            return;
        }
        this.f2242a.mo1368a(this.f2256o);
        this.f2256o = null;
    }

    /* renamed from: o */
    public void m1195o(int i7, String str, String str2, boolean z6) {
        if (z6) {
            this.f2255n = Boolean.FALSE;
        }
        if (m1187g()) {
            f2241t.mo2351a("sendError on committed: {} {}", Integer.valueOf(i7), str);
            return;
        }
        f2241t.mo2351a("sendError: {} {}", Integer.valueOf(i7), str);
        m1198r(i7, str);
        if (i7 >= 400) {
            mo1182b(null, false);
            StringBuilder sbM112a = C0413b.m112a("Error: ");
            if (str == null) {
                str = C0079a.m93a("", i7);
            }
            sbM112a.append(str);
            ((C1105m) this).m1235t(new C1166s(new C1158k(sbM112a.toString())), true);
        } else {
            mo1182b(null, true);
        }
        complete();
    }

    /* renamed from: p */
    public void m1196p(long j7) {
        if (j7 < 0) {
            this.f2251j = -3L;
        } else {
            this.f2251j = j7;
        }
    }

    /* renamed from: q */
    public void m1197q(boolean z6) {
        this.f2255n = Boolean.valueOf(z6);
    }

    /* renamed from: r */
    public void m1198r(int i7, String str) {
        if (this.f2244c != 0) {
            throw new IllegalStateException("STATE!=START");
        }
        this.f2248g = null;
        this.f2245d = i7;
        if (str != null) {
            byte[] bArrM2253c = C1926r.m2253c(str);
            int length = bArrM2253c.length;
            if (length > 1024) {
                length = 1024;
            }
            this.f2247f = new C1158k(length);
            for (int i8 = 0; i8 < length; i8++) {
                byte b7 = bArrM2253c[i8];
                if (b7 == 13 || b7 == 10) {
                    this.f2247f.mo1321L((byte) 32);
                } else {
                    this.f2247f.mo1321L(b7);
                }
            }
        }
    }

    @Override // p065h5.InterfaceC1095c
    public void reset() {
        this.f2244c = 0;
        this.f2245d = 0;
        this.f2246e = 11;
        this.f2247f = null;
        this.f2252k = false;
        this.f2253l = false;
        this.f2254m = false;
        this.f2255n = null;
        this.f2250i = 0L;
        this.f2251j = -3L;
        this.f2259r = null;
        this.f2258q = null;
        this.f2248g = null;
    }

    /* renamed from: s */
    public void m1199s(int i7) {
        if (this.f2244c != 0) {
            StringBuilder sbM112a = C0413b.m112a("STATE!=START ");
            sbM112a.append(this.f2244c);
            throw new IllegalStateException(sbM112a.toString());
        }
        this.f2246e = i7;
        if (i7 != 9 || this.f2248g == null) {
            return;
        }
        this.f2254m = true;
    }
}
