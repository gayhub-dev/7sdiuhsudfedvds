package p113n5;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import org.fourthline.cling.model.ServiceReference;
import p006a5.C0022h;
import p006a5.C0029o;
import p006a5.C0033s;
import p006a5.InterfaceC0014a;
import p006a5.InterfaceC0017c;
import p006a5.InterfaceC0023i;
import p006a5.InterfaceC0026l;
import p006a5.InterfaceC0034t;
import p015b5.C0456a;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0462g;
import p015b5.InterfaceC0469n;
import p057g5.InterfaceC1061a;
import p065h5.AbstractC1093a;
import p065h5.C1099g;
import p065h5.C1101i;
import p065h5.C1102j;
import p065h5.C1103k;
import p065h5.C1104l;
import p065h5.C1105m;
import p065h5.C1107o;
import p065h5.C1109q;
import p065h5.C1113u;
import p065h5.C1115w;
import p073i5.C1155h;
import p073i5.C1158k;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1161n;
import p089k5.C1394c;
import p089k5.C1395d;
import p113n5.AbstractC1541b;
import p113n5.C1542c;
import p113n5.InterfaceC1561v;
import p131p5.C1743c;
import p147r5.AbstractC1840c;
import p147r5.C1842e;
import p147r5.C1843f;
import p161t5.C1910b;
import p161t5.C1917i;
import p161t5.C1918j;
import p161t5.C1922n;
import p161t5.C1926r;
import p161t5.C1928t;
import p161t5.C1929u;
import p161t5.ConcurrentMapC1920l;
import p161t5.InterfaceC1909a;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: Request.java */
/* renamed from: n5.n */
/* loaded from: classes.dex */
public class C1553n implements InterfaceC0458c {

    /* renamed from: O */
    public static final InterfaceC2016c f4617O;

    /* renamed from: A */
    public Object f4618A;

    /* renamed from: B */
    public String f4619B;

    /* renamed from: C */
    public boolean f4620C;

    /* renamed from: D */
    public String f4621D;

    /* renamed from: E */
    public String f4622E;

    /* renamed from: F */
    public InterfaceC1561v.a f4623F;

    /* renamed from: G */
    public String f4624G;

    /* renamed from: H */
    public String f4625H;

    /* renamed from: I */
    public InterfaceC0462g f4626I;

    /* renamed from: J */
    public InterfaceC1559t f4627J;

    /* renamed from: K */
    public long f4628K;

    /* renamed from: L */
    public InterfaceC1152e f4629L;

    /* renamed from: M */
    public C1113u f4630M;

    /* renamed from: N */
    public C1922n f4631N;

    /* renamed from: a */
    public final C1542c f4632a;

    /* renamed from: b */
    public boolean f4633b;

    /* renamed from: c */
    public volatile InterfaceC1909a f4634c;

    /* renamed from: d */
    public InterfaceC1543d f4635d;

    /* renamed from: e */
    public ConcurrentMapC1920l<String> f4636e;

    /* renamed from: f */
    public String f4637f;

    /* renamed from: g */
    public AbstractC1541b f4638g;

    /* renamed from: h */
    public C1743c.b f4639h;

    /* renamed from: i */
    public boolean f4640i;

    /* renamed from: j */
    public String f4641j;

    /* renamed from: k */
    public C1546g f4642k;

    /* renamed from: l */
    public boolean f4643l;

    /* renamed from: m */
    public int f4644m;

    /* renamed from: n */
    public boolean f4645n;

    /* renamed from: o */
    public InterfaceC1161n f4646o;

    /* renamed from: p */
    public boolean f4647p;

    /* renamed from: q */
    public int f4648q;

    /* renamed from: r */
    public String f4649r;

    /* renamed from: s */
    public ConcurrentMapC1920l<String> f4650s;

    /* renamed from: t */
    public boolean f4651t;

    /* renamed from: u */
    public String f4652u;

    /* renamed from: v */
    public int f4653v;

    /* renamed from: w */
    public String f4654w;

    /* renamed from: x */
    public String f4655x;

    /* renamed from: y */
    public String f4656y;

    /* renamed from: z */
    public String f4657z;

    static {
        Properties properties = C2015b.f5863a;
        f4617O = C2015b.m2349a(C1553n.class.getName());
        Collections.singleton(Locale.getDefault());
    }

    public C1553n() {
        this.f4632a = new C1542c();
        this.f4633b = true;
        this.f4643l = false;
        this.f4645n = false;
        this.f4647p = false;
        this.f4648q = 0;
        this.f4654w = "HTTP/1.1";
        this.f4620C = false;
        this.f4622E = "http";
    }

    /* renamed from: A */
    public void m1778A(boolean z6) {
        this.f4647p = z6;
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: B */
    public String mo160B() {
        if (this.f4625H == null) {
            this.f4625H = "";
        }
        return this.f4625H;
    }

    /* renamed from: C */
    public void m1779C(ConcurrentMapC1920l<String> concurrentMapC1920l) {
        if (concurrentMapC1920l == null) {
            concurrentMapC1920l = this.f4636e;
        }
        this.f4650s = concurrentMapC1920l;
        if (this.f4651t && concurrentMapC1920l == null) {
            throw new IllegalStateException();
        }
    }

    /* renamed from: D */
    public void m1780D(String str) {
        this.f4652u = str;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: E */
    public InterfaceC0014a mo23E() {
        boolean z6;
        C1542c c1542c = this.f4632a;
        synchronized (c1542c) {
            z6 = c1542c.f4572e;
        }
        if (!z6 || this.f4632a.m1755m()) {
            return this.f4632a;
        }
        throw new IllegalStateException(this.f4632a.m1753k());
    }

    /* renamed from: F */
    public void m1781F(String str) {
        this.f4656y = str;
        this.f4655x = null;
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: G */
    public String mo161G() {
        return this.f4652u;
    }

    /* renamed from: H */
    public void m1782H(String str) {
        this.f4621D = str;
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: I */
    public long mo162I(String str) {
        String strM1221n;
        long time;
        C1101i.e eVarM1226g = this.f4638g.f4547h.m1226g(str);
        if (eVarM1226g == null || (strM1221n = C1101i.m1221n(C1155h.m1365c(eVarM1226g.f2302b), null)) == null) {
            return -1L;
        }
        C1101i.d dVar = C1101i.f2291j.get();
        int i7 = 0;
        int i8 = 0;
        while (true) {
            SimpleDateFormat[] simpleDateFormatArr = dVar.f2300a;
            if (i8 < simpleDateFormatArr.length) {
                if (simpleDateFormatArr[i8] == null) {
                    simpleDateFormatArr[i8] = new SimpleDateFormat(C1101i.f2290i[i8], Locale.US);
                    dVar.f2300a[i8].setTimeZone(C1101i.f2285d);
                }
                try {
                    continue;
                    time = ((Date) dVar.f2300a[i8].parseObject(strM1221n)).getTime();
                    break;
                } catch (Exception unused) {
                    i8++;
                }
            } else if (strM1221n.endsWith(" GMT")) {
                String strSubstring = strM1221n.substring(0, strM1221n.length() - 4);
                while (true) {
                    SimpleDateFormat[] simpleDateFormatArr2 = dVar.f2300a;
                    if (i7 >= simpleDateFormatArr2.length) {
                        break;
                    }
                    try {
                        time = ((Date) simpleDateFormatArr2[i7].parseObject(strSubstring)).getTime();
                        break;
                    } catch (Exception unused2) {
                        i7++;
                    }
                }
            } else {
                time = -1;
            }
        }
        if (time != -1) {
            return time;
        }
        throw new IllegalArgumentException(C0063n.m88a("Cannot convert date: ", strM1221n));
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: K */
    public String mo163K() {
        return this.f4619B;
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: L */
    public Enumeration mo164L(String str) {
        C1101i c1101i = this.f4638g.f4547h;
        C1101i.e eVarM1226g = c1101i.m1226g(str);
        Enumeration enumeration = eVarM1226g == null ? Collections.enumeration(Collections.emptyList()) : new C1103k(c1101i, eVarM1226g);
        return enumeration == null ? Collections.enumeration(Collections.EMPTY_LIST) : enumeration;
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: N */
    public String mo165N() {
        return this.f4649r;
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: O */
    public String mo166O() {
        C1113u c1113u;
        if (this.f4656y == null && (c1113u = this.f4630M) != null) {
            String str = this.f4655x;
            if (str == null) {
                this.f4656y = c1113u.mo1207i();
            } else {
                int i7 = c1113u.f2376j;
                this.f4656y = i7 == c1113u.f2377k ? null : C1926r.m2257g(c1113u.f2367a, i7 + 1, (r3 - i7) - 1, str);
            }
        }
        return this.f4656y;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: Q */
    public String mo24Q() {
        return this.f4654w;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: R */
    public InterfaceC0014a mo25R() {
        if (!this.f4633b) {
            throw new IllegalStateException("!asyncSupported");
        }
        C1542c c1542c = this.f4632a;
        Objects.requireNonNull(c1542c);
        AbstractC1541b abstractC1541b = c1542c.f4568a;
        C1553n c1553n = abstractC1541b.f4548i;
        C1743c.b bVar = c1553n.f4639h;
        C1554o c1554o = abstractC1541b.f4552m;
        synchronized (c1542c) {
            int i7 = c1542c.f4571d;
            if (i7 != 1 && i7 != 6) {
                throw new IllegalStateException(c1542c.m1753k());
            }
            c1542c.f4573f = false;
            c1542c.f4574g = false;
            C1542c.a aVar = c1542c.f4576i;
            if (aVar != null && c1553n == aVar.f27a && c1554o == aVar.f28b && bVar == aVar.m1764a()) {
                C1542c.a aVar2 = c1542c.f4576i;
                aVar2.f4579e = null;
                aVar2.f4580f = null;
            } else {
                c1542c.f4576i = new C1542c.a(c1542c, bVar, c1553n, c1554o);
            }
            c1542c.f4571d = 2;
            List<InterfaceC0017c> list = c1542c.f4569b;
            c1542c.f4569b = c1542c.f4570c;
            c1542c.f4570c = list;
            if (list != null) {
                list.clear();
            }
        }
        List<InterfaceC0017c> list2 = c1542c.f4569b;
        if (list2 != null) {
            Iterator<InterfaceC0017c> it = list2.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onStartAsync(c1542c.f4576i);
                } catch (Exception e7) {
                    C1542c.f4567k.mo2358i(e7);
                }
            }
        }
        return this.f4632a;
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: U */
    public String mo167U() {
        C1113u c1113u;
        if (this.f4621D == null && (c1113u = this.f4630M) != null) {
            this.f4621D = c1113u.mo1205g();
        }
        return this.f4621D;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: a */
    public Object mo26a(String str) {
        if ("org.eclipse.jetty.io.EndPoint.maxIdleTime".equalsIgnoreCase(str)) {
            return new Long(this.f4638g.f2538a.mo916i());
        }
        Object objMo892a = this.f4634c == null ? null : this.f4634c.mo892a(str);
        return (objMo892a == null && "org.eclipse.jetty.continuation".equals(str)) ? this.f4632a : objMo892a;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: b */
    public void mo27b(String str, Object obj) {
        Object objMo892a = this.f4634c == null ? null : this.f4634c.mo892a(str);
        if (str.startsWith("org.eclipse.jetty.")) {
            if ("org.eclipse.jetty.server.Request.queryEncoding".equals(str)) {
                this.f4655x = obj == null ? null : obj.toString();
                this.f4656y = null;
            } else if ("org.eclipse.jetty.server.sendContent".equals(str)) {
                try {
                    ((AbstractC1541b.b) this.f4638g.f4552m.mo45i()).m1741e(obj);
                } catch (IOException e7) {
                    throw new RuntimeException(e7);
                }
            } else if ("org.eclipse.jetty.server.ResponseBuffer".equals(str)) {
                try {
                    ByteBuffer byteBuffer = (ByteBuffer) obj;
                    synchronized (byteBuffer) {
                        ((AbstractC1541b.b) this.f4638g.f4552m.mo45i()).m1742f(byteBuffer.isDirect() ? new C1394c(byteBuffer, true) : new C1395d(byteBuffer, true));
                    }
                } catch (IOException e8) {
                    throw new RuntimeException(e8);
                }
            } else if ("org.eclipse.jetty.io.EndPoint.maxIdleTime".equalsIgnoreCase(str)) {
                try {
                    this.f4638g.f2538a.mo918k(Integer.valueOf(obj.toString()).intValue());
                } catch (IOException e9) {
                    throw new RuntimeException(e9);
                }
            }
        }
        if (this.f4634c == null) {
            this.f4634c = new C1910b();
        }
        this.f4634c.mo893b(str, obj);
        if (this.f4618A != null) {
            C0033s c0033s = new C0033s(this.f4639h, this, str, objMo892a == null ? obj : objMo892a);
            int iM2228x = C1918j.m2228x(this.f4618A);
            for (int i7 = 0; i7 < iM2228x; i7++) {
                InterfaceC0034t interfaceC0034t = (InterfaceC0034t) C1918j.m2225j(this.f4618A, i7);
                if (interfaceC0034t instanceof InterfaceC0034t) {
                    if (objMo892a == null) {
                        interfaceC0034t.m38x(c0033s);
                    } else if (obj == null) {
                        interfaceC0034t.m37w(c0033s);
                    } else {
                        interfaceC0034t.m36v(c0033s);
                    }
                }
            }
        }
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: c */
    public C1550k mo28c() {
        int i7 = this.f4648q;
        if (i7 != 0 && i7 != 1) {
            throw new IllegalStateException("READER");
        }
        this.f4648q = 1;
        AbstractC1541b abstractC1541b = this.f4638g;
        if (abstractC1541b.f4559t) {
            if (((C1109q) abstractC1541b.f4546g).m1242b() == null || ((C1109q) abstractC1541b.f4546g).m1242b().length() < 2) {
                if (((AbstractC1093a) abstractC1541b.f4550k).m1187g()) {
                    throw new IllegalStateException("Committed before 100 Continues");
                }
                ((C1105m) abstractC1541b.f4550k).m1240y(100);
            }
            abstractC1541b.f4559t = false;
        }
        if (abstractC1541b.f4549j == null) {
            abstractC1541b.f4549j = new C1550k(abstractC1541b);
        }
        return abstractC1541b.f4549j;
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: d */
    public String mo168d() {
        return this.f4641j;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: e */
    public String mo29e() {
        String str = this.f4657z;
        if (str != null) {
            return str;
        }
        InterfaceC1161n interfaceC1161n = this.f4646o;
        if (interfaceC1161n == null) {
            return null;
        }
        return interfaceC1161n.mo912e();
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: f */
    public InterfaceC0023i mo30f(String str) {
        String strM2266g = C1928t.m2266g(str);
        if (strM2266g == null || this.f4639h == null) {
            return null;
        }
        String strSubstring = ServiceReference.DELIMITER;
        if (!strM2266g.startsWith(ServiceReference.DELIMITER)) {
            String strM2264b = C1928t.m2264b(this.f4625H, this.f4652u);
            int iLastIndexOf = strM2264b.lastIndexOf(ServiceReference.DELIMITER);
            if (iLastIndexOf > 1) {
                strSubstring = strM2264b.substring(0, iLastIndexOf + 1);
            }
            strM2266g = C1928t.m2264b(strSubstring, strM2266g);
        }
        return this.f4639h.mo17f(strM2266g);
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: g */
    public String mo31g() {
        InterfaceC1161n interfaceC1161n = this.f4646o;
        if (interfaceC1161n == null) {
            return null;
        }
        return interfaceC1161n.mo914g();
    }

    @Override // p006a5.InterfaceC0032r
    public String getContentType() {
        C1101i.e eVarM1225f = this.f4638g.f4547h.m1225f(C1107o.f2331i);
        if (eVarM1225f == null) {
            return null;
        }
        return eVarM1225f.m1233a();
    }

    @Override // p006a5.InterfaceC0032r
    public InterfaceC0026l getServletContext() {
        return this.f4639h;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p015b5.InterfaceC0458c
    /* renamed from: h */
    public C0456a[] mo169h() {
        String str;
        if (this.f4643l) {
            C1546g c1546g = this.f4642k;
            if (c1546g == null) {
                return null;
            }
            return c1546g.m1769a();
        }
        this.f4643l = true;
        C1101i c1101i = this.f4638g.f4547h;
        C1101i.e eVarM1225f = c1101i.m1225f(C1107o.f2336n);
        Enumeration enumeration = eVarM1225f == null ? Collections.enumeration(Collections.emptyList()) : new C1104l(c1101i, eVarM1225f);
        if (enumeration != null) {
            if (this.f4642k == null) {
                this.f4642k = new C1546g();
            }
            while (enumeration.hasMoreElements()) {
                String str2 = (String) enumeration.nextElement();
                C1546g c1546g2 = this.f4642k;
                Objects.requireNonNull(c1546g2);
                if (str2 != null) {
                    String strTrim = str2.trim();
                    if (strTrim.length() != 0) {
                        int iM2228x = C1918j.m2228x(c1546g2.f4591c);
                        int i7 = c1546g2.f4592d;
                        if (iM2228x > i7) {
                            if (!strTrim.equals(C1918j.m2225j(c1546g2.f4591c, i7))) {
                                while (true) {
                                    int iM2228x2 = C1918j.m2228x(c1546g2.f4591c);
                                    int i8 = c1546g2.f4592d;
                                    if (iM2228x2 <= i8) {
                                        break;
                                    }
                                    c1546g2.f4591c = C1918j.m2227q(c1546g2.f4591c, i8);
                                }
                            } else {
                                c1546g2.f4592d++;
                            }
                        }
                        c1546g2.f4589a = null;
                        c1546g2.f4590b = null;
                        Object obj = c1546g2.f4591c;
                        int i9 = c1546g2.f4592d;
                        c1546g2.f4592d = i9 + 1;
                        if (obj == 0) {
                            if (i9 > 0 || (strTrim instanceof List)) {
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(i9, strTrim);
                                str = arrayList;
                                strTrim = str;
                            }
                            c1546g2.f4591c = strTrim;
                        } else if (obj instanceof List) {
                            ((List) obj).add(i9, strTrim);
                            str = obj;
                            strTrim = str;
                            c1546g2.f4591c = strTrim;
                        } else {
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(obj);
                            arrayList2.add(i9, strTrim);
                            strTrim = arrayList2;
                            c1546g2.f4591c = strTrim;
                        }
                    }
                }
            }
        }
        C1546g c1546g3 = this.f4642k;
        if (c1546g3 == null) {
            return null;
        }
        return c1546g3.m1769a();
    }

    /* renamed from: i */
    public void m1783i(EventListener eventListener) {
        if (eventListener instanceof InterfaceC0034t) {
            this.f4618A = C1918j.m2222b(this.f4618A, eventListener);
        }
        if (eventListener instanceof InterfaceC1061a) {
            throw new IllegalArgumentException(eventListener.getClass().toString());
        }
        if (eventListener instanceof InterfaceC0017c) {
            throw new IllegalArgumentException(eventListener.getClass().toString());
        }
    }

    /* renamed from: j */
    public void m1784j() {
        int iIntValue;
        int iIntValue2;
        ConcurrentMapC1920l<String> concurrentMapC1920l;
        if (this.f4636e == null) {
            this.f4636e = new ConcurrentMapC1920l<>(16);
        }
        if (this.f4651t) {
            if (concurrentMapC1920l == null) {
                return;
            } else {
                return;
            }
        }
        this.f4651t = true;
        try {
            C1113u c1113u = this.f4630M;
            if (c1113u != null && c1113u.mo1209k()) {
                String str = this.f4655x;
                if (str == null) {
                    this.f4630M.mo1200a(this.f4636e);
                } else {
                    try {
                        this.f4630M.mo1201b(this.f4636e, str);
                    } catch (UnsupportedEncodingException e7) {
                        InterfaceC2016c interfaceC2016c = f4617O;
                        if (interfaceC2016c.mo2353d()) {
                            interfaceC2016c.mo2358i(e7);
                        } else {
                            interfaceC2016c.mo2356g(e7.toString(), new Object[0]);
                        }
                    }
                }
            }
            String str2 = this.f4637f;
            String contentType = getContentType();
            if (contentType != null && contentType.length() > 0) {
                contentType = C1101i.m1221n(contentType, null);
                if ("application/x-www-form-urlencoded".equalsIgnoreCase(contentType) && this.f4648q == 0 && ("POST".equals(this.f4649r) || "PUT".equals(this.f4649r))) {
                    C1101i.e eVarM1225f = this.f4638g.f4547h.m1225f(C1107o.f2328f);
                    int iM1367e = (int) (eVarM1225f == null ? -1L : C1155h.m1367e(eVarM1225f.f2302b));
                    if (iM1367e != 0) {
                        try {
                            C1743c.b bVar = this.f4639h;
                            if (bVar != null) {
                                C1743c c1743c = C1743c.this;
                                iIntValue2 = c1743c.f4948x;
                                iIntValue = c1743c.f4947w;
                            } else {
                                iIntValue = -1;
                                iIntValue2 = -1;
                            }
                            if (iIntValue2 < 0) {
                                Object obj = this.f4638g.f4543d.mo1724c().f4671l.f5612e.get("org.eclipse.jetty.server.Request.maxFormContentSize");
                                if (obj == null) {
                                    iIntValue2 = 200000;
                                } else if (obj instanceof Number) {
                                    iIntValue2 = ((Number) obj).intValue();
                                } else if (obj instanceof String) {
                                    iIntValue2 = Integer.valueOf((String) obj).intValue();
                                }
                            }
                            if (iIntValue < 0) {
                                Object obj2 = this.f4638g.f4543d.mo1724c().f4671l.f5612e.get("org.eclipse.jetty.server.Request.maxFormKeys");
                                if (obj2 == null) {
                                    iIntValue = 1000;
                                } else if (obj2 instanceof Number) {
                                    iIntValue = ((Number) obj2).intValue();
                                } else if (obj2 instanceof String) {
                                    iIntValue = Integer.valueOf((String) obj2).intValue();
                                }
                            }
                            if (iM1367e > iIntValue2 && iIntValue2 > 0) {
                                throw new IllegalStateException("Form too large " + iM1367e + ">" + iIntValue2);
                            }
                            C1929u.m2271g(mo28c(), this.f4636e, str2, iM1367e < 0 ? iIntValue2 : -1, iIntValue);
                        } catch (IOException e8) {
                            InterfaceC2016c interfaceC2016c2 = f4617O;
                            if (interfaceC2016c2.mo2353d()) {
                                interfaceC2016c2.mo2358i(e8);
                            } else {
                                interfaceC2016c2.mo2356g(e8.toString(), new Object[0]);
                            }
                        }
                    }
                }
            }
            ConcurrentMapC1920l<String> concurrentMapC1920l2 = this.f4650s;
            if (concurrentMapC1920l2 == null) {
                this.f4650s = this.f4636e;
            } else {
                ConcurrentMapC1920l<String> concurrentMapC1920l3 = this.f4636e;
                if (concurrentMapC1920l2 != concurrentMapC1920l3) {
                    for (Map.Entry<String, Object> entry : concurrentMapC1920l3.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        for (int i7 = 0; i7 < C1918j.m2228x(value); i7++) {
                            this.f4650s.m2231b(key, C1918j.m2225j(value, i7));
                        }
                    }
                }
            }
            if (contentType != null && contentType.length() > 0 && contentType.startsWith("multipart/form-data") && mo26a("org.eclipse.multipartConfig") != null) {
                try {
                    m1786m();
                } catch (C0029o e9) {
                    if (f4617O.mo2353d()) {
                        f4617O.mo2358i(e9);
                    } else {
                        f4617O.mo2356g(e9.toString(), new Object[0]);
                    }
                } catch (IOException e10) {
                    if (f4617O.mo2353d()) {
                        f4617O.mo2358i(e10);
                    } else {
                        f4617O.mo2356g(e10.toString(), new Object[0]);
                    }
                }
            }
            if (this.f4650s == null) {
                this.f4650s = this.f4636e;
            }
        } finally {
            if (this.f4650s == null) {
                this.f4650s = this.f4636e;
            }
        }
    }

    /* renamed from: k */
    public C1542c m1785k() {
        return this.f4632a;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: l */
    public boolean mo32l() {
        return this.f4632a.m1755m();
    }

    /* renamed from: m */
    public Collection<InterfaceC0469n> m1786m() throws Throwable {
        if (getContentType() == null || !getContentType().startsWith("multipart/form-data")) {
            throw new C0029o("Content-Type != multipart/form-data");
        }
        if (this.f4631N == null) {
            this.f4631N = (C1922n) mo26a("org.eclipse.multiPartInputStream");
        }
        if (this.f4631N == null) {
            C0022h c0022h = (C0022h) mo26a("org.eclipse.multipartConfig");
            if (c0022h == null) {
                throw new IllegalStateException("No multipart config for servlet");
            }
            C1550k c1550kMo28c = mo28c();
            String contentType = getContentType();
            C1743c.b bVar = this.f4639h;
            ByteArrayOutputStream byteArrayOutputStream = null;
            C1922n c1922n = new C1922n(c1550kMo28c, contentType, c0022h, bVar != null ? (File) bVar.m1904a("javax.servlet.context.tempdir") : null);
            this.f4631N = c1922n;
            mo27b("org.eclipse.multiPartInputStream", c1922n);
            mo27b("org.eclipse.multiPartContext", this.f4639h);
            Iterator it = ((ArrayList) this.f4631N.m2232a()).iterator();
            while (it.hasNext()) {
                C1922n.b bVar2 = (C1922n.b) ((InterfaceC0469n) it.next());
                if (bVar2.f5664b == null) {
                    String strM1257a = bVar2.f5668f != null ? C1115w.m1257a(new C1158k(bVar2.f5668f)) : null;
                    InputStream bufferedInputStream = bVar2.f5665c != null ? new BufferedInputStream(new FileInputStream(bVar2.f5665c)) : new ByteArrayInputStream(bVar2.f5667e.m2212a(), 0, bVar2.f5667e.size());
                    try {
                        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        try {
                            byte[] bArr = new byte[C1917i.f5645b];
                            while (true) {
                                int i7 = bufferedInputStream.read(bArr, 0, C1917i.f5645b);
                                if (i7 < 0) {
                                    break;
                                }
                                byteArrayOutputStream2.write(bArr, 0, i7);
                            }
                            byte[] byteArray = byteArrayOutputStream2.toByteArray();
                            if (strM1257a == null) {
                                strM1257a = "UTF-8";
                            }
                            String str = new String(byteArray, strM1257a);
                            mo34r("");
                            this.f4650s.m2231b(bVar2.f5663a, str);
                            C1917i.m2220b(byteArrayOutputStream2);
                            C1917i.m2219a(bufferedInputStream);
                        } catch (Throwable th) {
                            th = th;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            C1917i.m2220b(byteArrayOutputStream);
                            C1917i.m2219a(bufferedInputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
        }
        return this.f4631N.m2232a();
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: n */
    public StringBuffer mo170n() {
        StringBuffer stringBuffer = new StringBuffer(48);
        synchronized (stringBuffer) {
            String str = this.f4622E;
            int iM1787o = m1787o();
            stringBuffer.append(str);
            stringBuffer.append("://");
            stringBuffer.append(mo35u());
            if (this.f4653v > 0 && ((str.equalsIgnoreCase("http") && iM1787o != 80) || (str.equalsIgnoreCase("https") && iM1787o != 443))) {
                stringBuffer.append(':');
                stringBuffer.append(this.f4653v);
            }
            stringBuffer.append(mo167U());
        }
        return stringBuffer;
    }

    /* renamed from: o */
    public int m1787o() {
        C1113u c1113u;
        if (this.f4653v <= 0) {
            if (this.f4624G == null) {
                mo35u();
            }
            if (this.f4653v <= 0) {
                if (this.f4624G == null || (c1113u = this.f4630M) == null) {
                    InterfaceC1161n interfaceC1161n = this.f4646o;
                    this.f4653v = interfaceC1161n == null ? 0 : interfaceC1161n.mo913f();
                } else {
                    this.f4653v = c1113u.mo1206h();
                }
            }
        }
        int i7 = this.f4653v;
        return i7 <= 0 ? this.f4622E.equalsIgnoreCase("https") ? 443 : 80 : i7;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: p */
    public boolean mo33p() {
        InterfaceC1545f interfaceC1545f = this.f4638g.f4543d;
        return interfaceC1545f != null && interfaceC1545f.mo1727m(this);
    }

    /* renamed from: q */
    public boolean m1788q() {
        return this.f4647p;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: r */
    public String mo34r(String str) {
        if (!this.f4651t) {
            m1784j();
        }
        Object obj = this.f4650s.f5650e.get(str);
        return (String) (C1918j.m2228x(obj) == 0 ? null : C1918j.m2225j(obj, 0));
    }

    /* renamed from: s */
    public void m1789s(String str) {
        boolean z6;
        ConcurrentMapC1920l<String> concurrentMapC1920l = new ConcurrentMapC1920l<>();
        C1929u.m2272j(str, concurrentMapC1920l, "UTF-8", -1);
        if (!this.f4651t) {
            m1784j();
        }
        ConcurrentMapC1920l<String> concurrentMapC1920l2 = this.f4650s;
        if (concurrentMapC1920l2 == null || concurrentMapC1920l2.size() <= 0) {
            z6 = false;
        } else {
            z6 = false;
            for (Map.Entry<String, Object> entry : this.f4650s.entrySet()) {
                String key = entry.getKey();
                if (concurrentMapC1920l.containsKey(key)) {
                    z6 = true;
                }
                Object value = entry.getValue();
                for (int i7 = 0; i7 < C1918j.m2228x(value); i7++) {
                    concurrentMapC1920l.m2231b(key, C1918j.m2225j(value, i7));
                }
            }
        }
        String str2 = this.f4656y;
        if (str2 != null && str2.length() > 0) {
            if (z6) {
                StringBuilder sb = new StringBuilder();
                ConcurrentMapC1920l concurrentMapC1920l3 = new ConcurrentMapC1920l();
                C1929u.m2272j(this.f4656y, concurrentMapC1920l3, this.f4655x, -1);
                ConcurrentMapC1920l concurrentMapC1920l4 = new ConcurrentMapC1920l();
                C1929u.m2272j(str, concurrentMapC1920l4, "UTF-8", -1);
                for (Map.Entry entry2 : concurrentMapC1920l3.entrySet()) {
                    String str3 = (String) entry2.getKey();
                    if (!concurrentMapC1920l4.containsKey(str3)) {
                        Object value2 = entry2.getValue();
                        for (int i8 = 0; i8 < C1918j.m2228x(value2); i8++) {
                            sb.append("&");
                            sb.append(str3);
                            sb.append("=");
                            sb.append((String) C1918j.m2225j(value2, i8));
                        }
                    }
                }
                str = str + ((Object) sb);
            } else {
                StringBuilder sbM94a = C0080b.m94a(str, "&");
                sbM94a.append(this.f4656y);
                str = sbM94a.toString();
            }
        }
        m1779C(concurrentMapC1920l);
        m1781F(str);
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: t */
    public InterfaceC0462g mo171t(boolean z6) {
        InterfaceC0462g interfaceC0462g = this.f4626I;
        if (interfaceC0462g != null) {
            InterfaceC1559t interfaceC1559t = this.f4627J;
            if (interfaceC1559t == null || ((AbstractC1840c) interfaceC1559t).m2102N(interfaceC0462g)) {
                return this.f4626I;
            }
            this.f4626I = null;
        }
        if (!z6) {
            return null;
        }
        InterfaceC1559t interfaceC1559t2 = this.f4627J;
        if (interfaceC1559t2 == null) {
            throw new IllegalStateException("No SessionManager");
        }
        AbstractC1840c abstractC1840c = (AbstractC1840c) interfaceC1559t2;
        Objects.requireNonNull(abstractC1840c);
        C1843f c1843f = new C1843f((C1842e) abstractC1840c, this);
        int i7 = abstractC1840c.f5362g;
        c1843f.f5353k = i7 * 1000;
        if (c1843f.m2092l() > 0) {
            long jM2092l = (c1843f.m2092l() * 1000) / 10;
            C1842e c1842e = c1843f.f5394n;
            if (jM2092l < c1842e.f5390F) {
                c1842e.m2108Q((i7 + 9) / 10);
            }
        }
        abstractC1840c.m2097H(c1843f, true);
        this.f4626I = c1843f;
        C1099g c1099gM2101M = ((AbstractC1840c) this.f4627J).m2101M(c1843f, this.f4641j, mo33p());
        if (c1099gM2101M != null) {
            this.f4638g.f4552m.m1793a(c1099gM2101M);
        }
        return this.f4626I;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f4647p ? "[" : "(");
        sb.append(this.f4649r);
        sb.append(" ");
        sb.append(this.f4630M);
        sb.append(this.f4647p ? "]@" : ")@");
        sb.append(hashCode());
        sb.append(" ");
        sb.append(super.toString());
        return sb.toString();
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: u */
    public String mo35u() {
        String str = this.f4624G;
        if (str != null) {
            return str;
        }
        C1113u c1113u = this.f4630M;
        if (c1113u == null) {
            throw new IllegalStateException("No uri");
        }
        this.f4624G = c1113u.mo1203e();
        this.f4653v = this.f4630M.mo1206h();
        String str2 = this.f4624G;
        if (str2 != null) {
            return str2;
        }
        C1101i.e eVarM1225f = this.f4638g.f4547h.m1225f(C1107o.f2327e);
        String strMo921n = null;
        InterfaceC1152e interfaceC1152e = eVarM1225f == null ? null : eVarM1225f.f2302b;
        if (interfaceC1152e == null) {
            if (this.f4638g != null) {
                InterfaceC1161n interfaceC1161n = this.f4646o;
                if (interfaceC1161n != null) {
                    if (this.f4645n) {
                        strMo921n = interfaceC1161n.mo921n();
                    } else {
                        String strMo914g = interfaceC1161n.mo914g();
                        if (strMo914g != null && strMo914g.indexOf(58) >= 0) {
                            strMo914g = C0096a.m97a("[", strMo914g, "]");
                        }
                        strMo921n = strMo914g;
                    }
                }
                this.f4624G = strMo921n;
                InterfaceC1161n interfaceC1161n2 = this.f4646o;
                this.f4653v = interfaceC1161n2 != null ? interfaceC1161n2.mo913f() : 0;
                String str3 = this.f4624G;
                if (str3 != null && !"0.0.0.0".equals(str3)) {
                    return this.f4624G;
                }
            }
            try {
                this.f4624G = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e7) {
                f4617O.mo2360k(e7);
            }
            return this.f4624G;
        }
        int iMo1322M = interfaceC1152e.mo1322M();
        while (true) {
            int i7 = iMo1322M - 1;
            if (iMo1322M <= interfaceC1152e.mo1316C()) {
                break;
            }
            char cMo1348H = (char) (interfaceC1152e.mo1348H(i7) & 255);
            if (cMo1348H == ':') {
                this.f4624G = C1155h.m1365c(interfaceC1152e.mo1338u(interfaceC1152e.mo1316C(), i7 - interfaceC1152e.mo1316C()));
                try {
                    try {
                        this.f4653v = C1155h.m1366d(interfaceC1152e.mo1338u(i7 + 1, (interfaceC1152e.mo1322M() - i7) - 1));
                    } catch (IOException e8) {
                        throw new RuntimeException(e8);
                    }
                } catch (NumberFormatException unused) {
                    AbstractC1541b abstractC1541b = this.f4638g;
                    if (abstractC1541b != null) {
                        ((AbstractC1093a) abstractC1541b.f4550k).m1195o(400, "Bad Host header", null, true);
                    }
                }
                return this.f4624G;
            }
            if (cMo1348H == ']') {
                break;
            }
            iMo1322M = i7;
        }
        if (this.f4624G == null || this.f4653v < 0) {
            this.f4624G = C1155h.m1365c(interfaceC1152e);
            this.f4653v = 0;
        }
        return this.f4624G;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0005  */
    /* renamed from: v */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m1790v(java.util.EventListener r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f4618A
            r1 = 0
            if (r0 != 0) goto L7
        L5:
            r0 = r1
            goto L1f
        L7:
            boolean r2 = r0 instanceof java.util.List
            if (r2 == 0) goto L18
            r2 = r0
            java.util.List r2 = (java.util.List) r2
            r2.remove(r4)
            int r4 = r2.size()
            if (r4 != 0) goto L1f
            goto L5
        L18:
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L1f
            goto L5
        L1f:
            r3.f4618A = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p113n5.C1553n.m1790v(java.util.EventListener):void");
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: w */
    public Enumeration mo172w() {
        C1101i c1101i = this.f4638g.f4547h;
        return new C1102j(c1101i, Collections.enumeration(c1101i.f2297b.keySet()));
    }

    @Override // p015b5.InterfaceC0458c
    /* renamed from: x */
    public String mo173x(String str) {
        return this.f4638g.f4547h.m1227h(str);
    }

    /* renamed from: y */
    public void m1791y(C1743c.b bVar) {
        this.f4640i = this.f4639h != bVar;
        this.f4639h = bVar;
    }

    /* renamed from: z */
    public void m1792z(int i7) {
        this.f4644m = i7;
    }

    public C1553n(AbstractC1541b abstractC1541b) {
        C1542c c1542c = new C1542c();
        this.f4632a = c1542c;
        this.f4633b = true;
        this.f4643l = false;
        this.f4645n = false;
        this.f4647p = false;
        this.f4648q = 0;
        this.f4654w = "HTTP/1.1";
        this.f4620C = false;
        this.f4622E = "http";
        this.f4638g = abstractC1541b;
        synchronized (c1542c) {
            c1542c.f4568a = abstractC1541b;
        }
        this.f4646o = abstractC1541b.f2538a;
        this.f4645n = abstractC1541b.f4543d.mo1731r();
    }
}
