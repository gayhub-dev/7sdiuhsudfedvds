package p041e5;

import java.util.Properties;
import p073i5.InterfaceC1151d;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1156i;
import p073i5.InterfaceC1161n;
import p089k5.InterfaceC1392a;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: AsyncHttpConnection.java */
/* renamed from: e5.c */
/* loaded from: classes.dex */
public class C0958c extends AbstractC0956a implements InterfaceC1392a {

    /* renamed from: q */
    public static final InterfaceC2016c f1733q;

    /* renamed from: n */
    public boolean f1734n;

    /* renamed from: o */
    public InterfaceC1152e f1735o;

    /* renamed from: p */
    public final InterfaceC1151d f1736p;

    static {
        Properties properties = C2015b.f5863a;
        f1733q = C2015b.m2349a(C0958c.class.getName());
    }

    public C0958c(InterfaceC1156i interfaceC1156i, InterfaceC1156i interfaceC1156i2, InterfaceC1161n interfaceC1161n) {
        super(interfaceC1156i, interfaceC1156i2, interfaceC1161n);
        this.f1736p = (InterfaceC1151d) interfaceC1161n;
    }

    @Override // p089k5.InterfaceC1392a
    /* renamed from: c */
    public void mo886c() {
        if (this.f1717d.m1189i()) {
            this.f2538a.mo927t();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:141:0x0221, code lost:
    
        r12.mo2351a("finally {} on {} progress={} {}", r2, r15, java.lang.Boolean.valueOf(r11), r15.f2538a);
        r15.f1717d.m1197q(false);
        m888k();
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x023e, code lost:
    
        monitor-enter(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x023f, code lost:
    
        r2 = r15.f1722i;
        r15.f1722i = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0243, code lost:
    
        if (r2 == null) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0245, code lost:
    
        r2.cancelTimeout(r15.f1716c.f1764e);
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x024e, code lost:
    
        if (r15.f1719f != 101) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0250, code lost:
    
        r2 = r2.onSwitchProtocol(r15.f2538a);
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0256, code lost:
    
        if (r2 == 0) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0258, code lost:
    
        r3 = r15.f1723j;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x025a, code lost:
    
        if (r3 == null) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x025c, code lost:
    
        r15.f1716c.m905h(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0261, code lost:
    
        r15.f1723j = null;
        r3 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0264, code lost:
    
        r2 = r15.f1723j;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0266, code lost:
    
        if (r2 == null) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0268, code lost:
    
        r15.f1716c.m905h(r2);
        r15.f1723j = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0271, code lost:
    
        if (r15.f1722i != null) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0275, code lost:
    
        if (r15.f1721h != false) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0277, code lost:
    
        r15.f1716c.m902e(r15, true);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01f9  */
    /* JADX WARN: Type inference failed for: r2v38, types: [i5.m] */
    /* JADX WARN: Type inference failed for: r4v8, types: [i5.m] */
    /* JADX WARN: Type inference failed for: r6v19, types: [i5.m] */
    @Override // p073i5.InterfaceC1160m
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public p073i5.InterfaceC1160m mo887e() {
        /*
            Method dump skipped, instructions count: 905
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p041e5.C0958c.mo887e():i5.m");
    }

    @Override // p041e5.AbstractC0956a
    /* renamed from: i */
    public boolean mo866i(C0965j c0965j) {
        boolean zMo866i = super.mo866i(c0965j);
        if (zMo866i) {
            this.f1736p.mo915h();
        }
        return zMo866i;
    }

    /* renamed from: k */
    public void m888k() {
        this.f1734n = false;
        this.f1718e.m1249i();
        this.f1717d.reset();
    }
}
