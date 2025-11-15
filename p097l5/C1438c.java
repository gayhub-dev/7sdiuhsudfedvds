package p097l5;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import p009b.C0413b;
import p065h5.C1117y;
import p113n5.AbstractC1541b;
import p113n5.C1553n;
import p113n5.C1554o;
import p113n5.InterfaceC1543d;
import p113n5.InterfaceC1545f;
import p113n5.InterfaceC1561v;
import p161t5.C1925q;

/* compiled from: ConstraintSecurityHandler.java */
/* renamed from: l5.c */
/* loaded from: classes.dex */
public class C1438c extends AbstractC1443h {

    /* renamed from: t */
    public final List<C1437b> f4181t = new CopyOnWriteArrayList();

    /* renamed from: u */
    public final Set<String> f4182u = new CopyOnWriteArraySet();

    /* renamed from: v */
    public final C1117y f4183v = new C1117y();

    @Override // p097l5.AbstractC1443h
    /* renamed from: Q */
    public boolean mo1612Q(String str, C1553n c1553n, C1554o c1554o, Object obj) throws IOException {
        StringBuilder sbM94a;
        StringBuilder sbM94a2;
        if (obj == null) {
            return true;
        }
        C1442g c1442g = (C1442g) obj;
        if (c1442g.f4186c) {
            return false;
        }
        EnumC1446k enumC1446k = c1442g.f4187d;
        if (enumC1446k == null || enumC1446k == EnumC1446k.None) {
            return true;
        }
        InterfaceC1545f interfaceC1545f = AbstractC1541b.m1735g().f4543d;
        if (enumC1446k == EnumC1446k.Integral) {
            if (interfaceC1545f.mo1720C(c1553n)) {
                return true;
            }
            if (interfaceC1545f.mo1729p() > 0) {
                String strMo1734z = interfaceC1545f.mo1734z();
                int iMo1729p = interfaceC1545f.mo1729p();
                if ("https".equalsIgnoreCase(strMo1734z) && iMo1729p == 443) {
                    sbM94a2 = C0413b.m112a("https://");
                    sbM94a2.append(c1553n.mo35u());
                } else {
                    sbM94a2 = C0080b.m94a(strMo1734z, "://");
                    sbM94a2.append(c1553n.mo35u());
                    sbM94a2.append(":");
                    sbM94a2.append(iMo1729p);
                }
                sbM94a2.append(c1553n.mo167U());
                String string = sbM94a2.toString();
                if (c1553n.mo166O() != null) {
                    StringBuilder sbM94a3 = C0080b.m94a(string, "?");
                    sbM94a3.append(c1553n.mo166O());
                    string = sbM94a3.toString();
                }
                c1554o.mo42H(0);
                c1554o.mo179T(string);
            } else {
                c1554o.mo175A(403, "!Integral");
            }
            c1553n.f4647p = true;
            return false;
        }
        if (enumC1446k != EnumC1446k.Confidential) {
            throw new IllegalArgumentException("Invalid dataConstraint value: " + enumC1446k);
        }
        if (interfaceC1545f.mo1727m(c1553n)) {
            return true;
        }
        if (interfaceC1545f.mo1719A() > 0) {
            String strMo1728o = interfaceC1545f.mo1728o();
            int iMo1719A = interfaceC1545f.mo1719A();
            if ("https".equalsIgnoreCase(strMo1728o) && iMo1719A == 443) {
                sbM94a = C0413b.m112a("https://");
                sbM94a.append(c1553n.mo35u());
            } else {
                sbM94a = C0080b.m94a(strMo1728o, "://");
                sbM94a.append(c1553n.mo35u());
                sbM94a.append(":");
                sbM94a.append(iMo1719A);
            }
            sbM94a.append(c1553n.mo167U());
            String string2 = sbM94a.toString();
            if (c1553n.mo166O() != null) {
                StringBuilder sbM94a4 = C0080b.m94a(string2, "?");
                sbM94a4.append(c1553n.mo166O());
                string2 = sbM94a4.toString();
            }
            c1554o.mo42H(0);
            c1554o.mo179T(string2);
        } else {
            c1554o.mo175A(403, "!Confidential");
        }
        c1553n.f4647p = true;
        return false;
    }

    @Override // p097l5.AbstractC1443h
    /* renamed from: R */
    public boolean mo1613R(String str, C1553n c1553n, C1554o c1554o, Object obj, InterfaceC1561v interfaceC1561v) {
        if (obj == null) {
            return true;
        }
        C1442g c1442g = (C1442g) obj;
        if (!c1442g.f4185b) {
            return true;
        }
        if (c1442g.f4184a) {
            InterfaceC1543d interfaceC1543d = c1553n.f4635d;
            if (interfaceC1543d instanceof InterfaceC1543d.e) {
                c1553n.f4635d = ((InterfaceC1543d.e) interfaceC1543d).mo1652A(c1553n);
            }
            InterfaceC1543d interfaceC1543d2 = c1553n.f4635d;
            if ((interfaceC1543d2 instanceof InterfaceC1543d.g ? ((InterfaceC1543d.g) interfaceC1543d2).mo1631a() : null) != null) {
                return true;
            }
        }
        Iterator<String> it = c1442g.f4188e.iterator();
        while (it.hasNext()) {
            if (interfaceC1561v.m1809b(it.next(), null)) {
                return true;
            }
        }
        return false;
    }

    @Override // p097l5.AbstractC1443h
    /* renamed from: T */
    public boolean mo1614T(C1553n c1553n, C1554o c1554o, Object obj) {
        if (obj == null) {
            return false;
        }
        return ((C1442g) obj).f4185b;
    }

    @Override // p097l5.AbstractC1443h
    /* renamed from: U */
    public Object mo1615U(String str, C1553n c1553n) {
        C1117y.a aVarM1260b = this.f4183v.m1260b(str);
        Map map = (Map) (aVarM1260b != null ? aVarM1260b.f2408f : null);
        if (map == null) {
            return null;
        }
        String str2 = c1553n.f4649r;
        C1442g c1442g = (C1442g) map.get(str2);
        if (c1442g != null) {
            return c1442g;
        }
        ArrayList arrayList = new ArrayList();
        C1442g c1442g2 = (C1442g) map.get(null);
        if (c1442g2 != null) {
            arrayList.add(c1442g2);
        }
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getKey() != null && ((String) entry.getKey()).contains(".omission") && !C0063n.m88a(str2, ".omission").equals(entry.getKey())) {
                arrayList.add(entry.getValue());
            }
        }
        if (arrayList.size() == 1) {
            return (C1442g) arrayList.get(0);
        }
        C1442g c1442g3 = new C1442g();
        c1442g3.m1628c(EnumC1446k.None);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            c1442g3.m1626a((C1442g) it.next());
        }
        return c1442g3;
    }

    @Override // p097l5.AbstractC1443h, p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() {
        this.f4183v.clear();
        List<C1437b> list = this.f4181t;
        if (list != null) {
            for (C1437b c1437b : list) {
                C1117y c1117y = this.f4183v;
                Objects.requireNonNull(c1437b);
                Map map = (Map) c1117y.get(null);
                if (map == null) {
                    this.f4183v.put(null, new C1925q());
                    throw null;
                }
                C1442g c1442g = (C1442g) map.get(null);
                if (c1442g == null || !c1442g.f4186c) {
                    C1442g c1442g2 = (C1442g) map.get(null);
                    if (c1442g2 == null) {
                        c1442g2 = new C1442g();
                        map.put(null, c1442g2);
                        if (c1442g != null) {
                            c1442g2.m1626a(c1442g);
                        }
                    }
                    if (!c1442g2.f4186c) {
                        throw null;
                    }
                }
            }
        }
        super.doStart();
    }

    @Override // p097l5.AbstractC1443h, p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStop() {
        this.f4183v.clear();
        this.f4181t.clear();
        this.f4182u.clear();
        super.doStop();
    }
}
