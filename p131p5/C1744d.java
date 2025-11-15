package p131p5;

import android.arch.lifecycle.C0063n;
import java.util.Map;
import java.util.Properties;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p065h5.C1117y;
import p113n5.C1542c;
import p113n5.C1553n;
import p113n5.InterfaceC1548i;
import p113n5.InterfaceC1549j;
import p161t5.C1918j;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: ContextHandlerCollection.java */
/* renamed from: p5.d */
/* loaded from: classes.dex */
public class C1744d extends C1746f {

    /* renamed from: m */
    public static final InterfaceC2016c f4952m;

    /* renamed from: l */
    public volatile C1117y f4953l;

    static {
        Properties properties = C2015b.f5863a;
        f4952m = C2015b.m2349a(C1744d.class.getName());
    }

    public C1744d() {
        super(true);
    }

    @Override // p131p5.C1746f
    /* renamed from: P */
    public void mo1906P(InterfaceC1548i[] interfaceC1548iArr) {
        this.f4953l = null;
        super.mo1906P(interfaceC1548iArr);
        if (isStarted()) {
            m1907Q();
        }
    }

    /* renamed from: Q */
    public void m1907Q() {
        InterfaceC1548i[] interfaceC1548iArrMo1773l;
        C1117y c1117y = new C1117y();
        InterfaceC1548i[] interfaceC1548iArr = this.f4959k;
        for (int i7 = 0; interfaceC1548iArr != null && i7 < interfaceC1548iArr.length; i7++) {
            if (interfaceC1548iArr[i7] instanceof C1743c) {
                interfaceC1548iArrMo1773l = new InterfaceC1548i[]{interfaceC1548iArr[i7]};
            } else if (interfaceC1548iArr[i7] instanceof InterfaceC1549j) {
                interfaceC1548iArrMo1773l = ((InterfaceC1549j) interfaceC1548iArr[i7]).mo1773l(C1743c.class);
            } else {
                continue;
            }
            for (InterfaceC1548i interfaceC1548i : interfaceC1548iArrMo1773l) {
                String strM88a = ((C1743c) interfaceC1548i).f4942r;
                if (strM88a == null || strM88a.indexOf(44) >= 0 || strM88a.startsWith("*")) {
                    throw new IllegalArgumentException(C0063n.m88a("Illegal context spec:", strM88a));
                }
                if (!strM88a.startsWith(ServiceReference.DELIMITER)) {
                    strM88a = '/' + strM88a;
                }
                if (strM88a.length() > 1) {
                    if (strM88a.endsWith(ServiceReference.DELIMITER)) {
                        strM88a = C0063n.m88a(strM88a, "*");
                    } else if (!strM88a.endsWith("/*")) {
                        strM88a = C0063n.m88a(strM88a, "/*");
                    }
                }
                Object obj = c1117y.get(strM88a);
                if (obj instanceof Map) {
                    Map map = (Map) obj;
                    map.put("*", C1918j.m2222b(map.get("*"), interfaceC1548iArr[i7]));
                } else {
                    c1117y.put(strM88a, C1918j.m2222b(obj, interfaceC1548iArr[i7]));
                }
            }
        }
        this.f4953l = c1117y;
    }

    @Override // p131p5.C1746f, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() throws Exception {
        m1907Q();
        super.doStart();
    }

    @Override // p131p5.C1746f, p113n5.InterfaceC1548i
    /* renamed from: u */
    public void mo1630u(String str, C1553n c1553n, InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) throws Throwable {
        int i7;
        boolean z6;
        Object obj;
        InterfaceC1548i[] interfaceC1548iArr = this.f4959k;
        if (interfaceC1548iArr == null || interfaceC1548iArr.length == 0) {
            return;
        }
        C1542c c1542c = c1553n.f4632a;
        synchronized (c1542c) {
            int i8 = c1542c.f4571d;
            z6 = (i8 == 0 || i8 == 1 || i8 == 8 || i8 == 9) ? false : true;
        }
        if (z6) {
            C1542c.a aVar = c1542c.f4576i;
            C1743c c1743c = aVar != null ? C1743c.this : null;
            if (c1743c != null) {
                if (c1743c.f4962k == null) {
                    c1743c.mo1896R(str, c1553n, interfaceC0458c, interfaceC0460e);
                    return;
                } else {
                    c1743c.mo1895Q(str, c1553n, interfaceC0458c, interfaceC0460e);
                    return;
                }
            }
        }
        C1117y c1117y = this.f4953l;
        if (c1117y == null || str == null || !str.startsWith(ServiceReference.DELIMITER)) {
            for (InterfaceC1548i interfaceC1548i : interfaceC1548iArr) {
                interfaceC1548i.mo1630u(str, c1553n, interfaceC0458c, interfaceC0460e);
                if (c1553n.f4647p) {
                    return;
                }
            }
            return;
        }
        int length = str.length();
        Map.Entry entryM2246b = c1117y.f2403g.m2246b(str, 0, length);
        Object objM2222b = entryM2246b != null ? C1918j.m2222b(null, entryM2246b.getValue()) : null;
        int iLastIndexOf = length - 1;
        while (true) {
            iLastIndexOf = str.lastIndexOf(47, iLastIndexOf - 1);
            if (iLastIndexOf < 0) {
                break;
            }
            Map.Entry entryM2246b2 = c1117y.f2401e.m2246b(str, 0, iLastIndexOf);
            if (entryM2246b2 != null) {
                objM2222b = C1918j.m2222b(objM2222b, entryM2246b2.getValue());
            }
        }
        C1117y.a aVar2 = c1117y.f2405i;
        if (aVar2 != null) {
            objM2222b = C1918j.m2222b(objM2222b, aVar2);
        }
        int iIndexOf = 0;
        while (true) {
            iIndexOf = str.indexOf(46, iIndexOf + 1);
            if (iIndexOf <= 0) {
                break;
            }
            Map.Entry entryM2246b3 = c1117y.f2402f.m2246b(str, iIndexOf + 1, (length - iIndexOf) - 1);
            if (entryM2246b3 != null) {
                objM2222b = C1918j.m2222b(objM2222b, entryM2246b3.getValue());
            }
        }
        C1117y.a aVar3 = c1117y.f2406j;
        if (aVar3 == null) {
            obj = objM2222b;
        } else if (objM2222b == null) {
            obj = c1117y.f2404h;
        } else {
            objM2222b = C1918j.m2222b(objM2222b, aVar3);
            obj = objM2222b;
        }
        for (int i9 = 0; i9 < C1918j.m2228x(obj); i9++) {
            Object value = ((Map.Entry) C1918j.m2225j(obj, i9)).getValue();
            if (value instanceof Map) {
                Map map = (Map) value;
                String strMo35u = interfaceC0458c.mo35u();
                if (strMo35u == null) {
                    strMo35u = null;
                } else if (strMo35u.endsWith(".")) {
                    strMo35u = strMo35u.substring(0, strMo35u.length() - 1);
                }
                Object obj2 = map.get(strMo35u);
                for (int i10 = 0; i10 < C1918j.m2228x(obj2); i10++) {
                    ((InterfaceC1548i) C1918j.m2225j(obj2, i10)).mo1630u(str, c1553n, interfaceC0458c, interfaceC0460e);
                    if (c1553n.f4647p) {
                        return;
                    }
                }
                StringBuilder sbM112a = C0413b.m112a("*.");
                sbM112a.append(strMo35u.substring(strMo35u.indexOf(".") + 1));
                Object obj3 = map.get(sbM112a.toString());
                for (int i11 = 0; i11 < C1918j.m2228x(obj3); i11++) {
                    ((InterfaceC1548i) C1918j.m2225j(obj3, i11)).mo1630u(str, c1553n, interfaceC0458c, interfaceC0460e);
                    if (c1553n.f4647p) {
                        return;
                    }
                }
                Object obj4 = map.get("*");
                for (int i12 = 0; i12 < C1918j.m2228x(obj4); i12++) {
                    ((InterfaceC1548i) C1918j.m2225j(obj4, i12)).mo1630u(str, c1553n, interfaceC0458c, interfaceC0460e);
                    if (c1553n.f4647p) {
                        return;
                    }
                }
            } else {
                for (int i13 = 0; i13 < C1918j.m2228x(value); i13++) {
                    ((InterfaceC1548i) C1918j.m2225j(value, i13)).mo1630u(str, c1553n, interfaceC0458c, interfaceC0460e);
                    if (c1553n.f4647p) {
                        return;
                    }
                }
            }
        }
    }
}
