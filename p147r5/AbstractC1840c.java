package p147r5;

import android.support.constraint.C0072a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.fourthline.cling.model.ServiceReference;
import p006a5.C0027m;
import p006a5.EnumC0039y;
import p006a5.InterfaceC0038x;
import p009b.C0413b;
import p015b5.C0464i;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0462g;
import p015b5.InterfaceC0463h;
import p015b5.InterfaceC0466k;
import p065h5.C1099g;
import p113n5.C1555p;
import p113n5.InterfaceC1558s;
import p113n5.InterfaceC1559t;
import p131p5.C1743c;
import p168u5.AbstractC1980a;
import p175v5.InterfaceC2016c;
import p186x2.C2074b;
import p203z5.C2157a;
import p203z5.C2158b;

/* compiled from: AbstractSessionManager.java */
/* renamed from: r5.c */
/* loaded from: classes.dex */
public abstract class AbstractC1840c extends AbstractC1980a implements InterfaceC1559t {

    /* renamed from: A */
    public static final InterfaceC2016c f5359A = C1844g.f5397o;

    /* renamed from: e */
    public Set<EnumC0039y> f5360e;

    /* renamed from: f */
    public boolean f5361f;

    /* renamed from: g */
    public int f5362g;

    /* renamed from: h */
    public C1844g f5363h;

    /* renamed from: i */
    public InterfaceC1558s f5364i;

    /* renamed from: j */
    public boolean f5365j;

    /* renamed from: k */
    public final List<InterfaceC0463h> f5366k;

    /* renamed from: l */
    public final List<InterfaceC0466k> f5367l;

    /* renamed from: m */
    public ClassLoader f5368m;

    /* renamed from: n */
    public C1743c.b f5369n;

    /* renamed from: o */
    public String f5370o;

    /* renamed from: p */
    public String f5371p;

    /* renamed from: q */
    public String f5372q;

    /* renamed from: r */
    public String f5373r;

    /* renamed from: s */
    public String f5374s;

    /* renamed from: t */
    public int f5375t;

    /* renamed from: u */
    public boolean f5376u;

    /* renamed from: v */
    public Set<EnumC0039y> f5377v;

    /* renamed from: w */
    public boolean f5378w;

    /* renamed from: x */
    public final C2157a f5379x;

    /* renamed from: y */
    public final C2158b f5380y;

    /* renamed from: z */
    public InterfaceC0038x f5381z;

    /* compiled from: AbstractSessionManager.java */
    /* renamed from: r5.c$a */
    public class a implements InterfaceC0038x {
        public a() {
        }
    }

    /* compiled from: AbstractSessionManager.java */
    /* renamed from: r5.c$b */
    public interface b extends InterfaceC0462g {
        /* renamed from: c */
        AbstractC1838a mo2086c();
    }

    public AbstractC1840c() {
        EnumC0039y enumC0039y = EnumC0039y.COOKIE;
        EnumC0039y enumC0039y2 = EnumC0039y.URL;
        this.f5360e = Collections.unmodifiableSet(new HashSet(Arrays.asList(enumC0039y, enumC0039y2)));
        this.f5361f = true;
        this.f5362g = -1;
        this.f5365j = true;
        this.f5366k = new CopyOnWriteArrayList();
        this.f5367l = new CopyOnWriteArrayList();
        this.f5370o = "JSESSIONID";
        this.f5371p = "jsessionid";
        this.f5372q = C0072a.m92a(C0413b.m112a(";"), this.f5371p, "=");
        this.f5375t = -1;
        this.f5379x = new C2157a();
        this.f5380y = new C2158b();
        this.f5381z = new a();
        HashSet hashSet = new HashSet(this.f5360e);
        this.f5377v = hashSet;
        this.f5361f = hashSet.contains(enumC0039y);
        this.f5378w = this.f5377v.contains(enumC0039y2);
    }

    /* renamed from: P */
    public static InterfaceC0462g m2095P(InterfaceC0458c interfaceC0458c, InterfaceC0462g interfaceC0462g, boolean z6) {
        HashMap map = new HashMap();
        Enumeration<String> enumerationMo190g = interfaceC0462g.mo190g();
        while (enumerationMo190g.hasMoreElements()) {
            String strNextElement = enumerationMo190g.nextElement();
            map.put(strNextElement, interfaceC0462g.mo186a(strNextElement));
            interfaceC0462g.mo188e(strNextElement);
        }
        interfaceC0462g.mo189f();
        InterfaceC0462g interfaceC0462gMo171t = interfaceC0458c.mo171t(true);
        if (z6) {
            interfaceC0462gMo171t.mo187b("org.eclipse.jetty.security.sessionKnownOnlytoAuthenticated", Boolean.TRUE);
        }
        for (Map.Entry entry : map.entrySet()) {
            interfaceC0462gMo171t.mo187b((String) entry.getKey(), entry.getValue());
        }
        return interfaceC0462gMo171t;
    }

    /* renamed from: G */
    public C1099g m2096G(InterfaceC0462g interfaceC0462g, boolean z6) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        AbstractC1838a abstractC1838aMo2086c = ((b) interfaceC0462g).mo2086c();
        if (!abstractC1838aMo2086c.m2087d(jCurrentTimeMillis) || !this.f5361f) {
            return null;
        }
        if (!abstractC1838aMo2086c.f5347e) {
            int i7 = AbstractC1840c.this.f5375t;
            return null;
        }
        C1743c.b bVar = this.f5369n;
        C1099g c1099gM2101M = m2101M(interfaceC0462g, bVar == null ? ServiceReference.DELIMITER : bVar.mo16d(), z6);
        synchronized (abstractC1838aMo2086c) {
            abstractC1838aMo2086c.f5349g = abstractC1838aMo2086c.f5350h;
        }
        abstractC1838aMo2086c.f5347e = false;
        return c1099gM2101M;
    }

    /* renamed from: H */
    public void m2097H(AbstractC1838a abstractC1838a, boolean z6) {
        synchronized (this.f5364i) {
            ((C1841d) this.f5364i).m2104G(abstractC1838a);
            C1842e c1842e = (C1842e) this;
            if (c1842e.isRunning()) {
                c1842e.f5386B.put(abstractC1838a.f5344b, (C1843f) abstractC1838a);
            }
        }
        if (z6) {
            this.f5379x.m2598a(1L);
            if (this.f5367l != null) {
                C0027m c0027m = new C0027m(abstractC1838a);
                Iterator<InterfaceC0466k> it = this.f5367l.iterator();
                while (it.hasNext()) {
                    it.next().mo196g(c0027m);
                }
            }
        }
    }

    /* renamed from: J */
    public void m2098J(InterfaceC0462g interfaceC0462g) {
        AbstractC1838a abstractC1838aMo2086c = ((b) interfaceC0462g).mo2086c();
        synchronized (abstractC1838aMo2086c) {
            int i7 = abstractC1838aMo2086c.f5354l - 1;
            abstractC1838aMo2086c.f5354l = i7;
            if (abstractC1838aMo2086c.f5352j && i7 <= 0) {
                abstractC1838aMo2086c.mo2090j();
            }
        }
    }

    /* renamed from: K */
    public void m2099K(AbstractC1838a abstractC1838a, String str, Object obj, Object obj2) {
        if (this.f5366k.isEmpty()) {
            return;
        }
        C0464i c0464i = new C0464i(abstractC1838a, str, obj == null ? obj2 : obj);
        for (InterfaceC0463h interfaceC0463h : this.f5366k) {
            if (obj == null) {
                interfaceC0463h.m193u(c0464i);
            } else if (obj2 == null) {
                interfaceC0463h.m191f(c0464i);
            } else {
                interfaceC0463h.m192t(c0464i);
            }
        }
    }

    /* renamed from: L */
    public InterfaceC0462g m2100L(String str) {
        C1843f c1843f;
        String strM2105H = ((C1841d) this.f5364i).m2105H(str);
        ConcurrentMap<String, C1843f> concurrentMap = ((C1842e) this).f5386B;
        if (concurrentMap == null || (c1843f = concurrentMap.get(strM2105H)) == null) {
            c1843f = null;
        }
        if (c1843f != null && !c1843f.f5345c.equals(str)) {
            c1843f.f5347e = true;
        }
        return c1843f;
    }

    /* renamed from: M */
    public C1099g m2101M(InterfaceC0462g interfaceC0462g, String str, boolean z6) {
        if (!this.f5361f) {
            return null;
        }
        String str2 = this.f5374s;
        if (str2 != null) {
            str = str2;
        }
        if (str == null || str.length() == 0) {
            str = ServiceReference.DELIMITER;
        }
        String str3 = str;
        String str4 = ((b) interfaceC0462g).mo2086c().f5345c;
        String str5 = this.f5370o;
        String str6 = this.f5373r;
        AbstractC1840c abstractC1840c = AbstractC1840c.this;
        int i7 = abstractC1840c.f5375t;
        Objects.requireNonNull(abstractC1840c);
        Objects.requireNonNull(AbstractC1840c.this);
        return new C1099g(str5, str4, str6, str3, i7, false, this.f5365j && z6);
    }

    /* renamed from: N */
    public boolean m2102N(InterfaceC0462g interfaceC0462g) {
        return !((b) interfaceC0462g).mo2086c().f5351i;
    }

    /* renamed from: O */
    public void m2103O(AbstractC1838a abstractC1838a, boolean z6) {
        Set<WeakReference<InterfaceC0462g>> setRemove;
        if (((C1842e) this).f5386B.remove(abstractC1838a.f5344b) != null) {
            this.f5379x.m2598a(-1L);
            C2158b c2158b = this.f5380y;
            long jRound = Math.round((System.currentTimeMillis() - abstractC1838a.f5348f) / 1000.0d);
            long jAddAndGet = ((AtomicLong) c2158b.f6335b).addAndGet(jRound);
            long jIncrementAndGet = ((AtomicLong) c2158b.f6336c).incrementAndGet();
            if (jIncrementAndGet > 1) {
                long j7 = (10 * jRound) - ((jAddAndGet * 10) / jIncrementAndGet);
                ((AtomicLong) c2158b.f6337d).addAndGet(j7 * j7);
            }
            C2074b.m2476P((AtomicLong) c2158b.f6334a, jRound);
            C1841d c1841d = (C1841d) this.f5364i;
            Objects.requireNonNull(c1841d);
            String strM2105H = c1841d.m2105H(abstractC1838a.getId());
            synchronized (c1841d) {
                Set<WeakReference<InterfaceC0462g>> set = c1841d.f5383i.get(strM2105H);
                if (set != null) {
                    Iterator<WeakReference<InterfaceC0462g>> it = set.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        InterfaceC0462g interfaceC0462g = it.next().get();
                        if (interfaceC0462g == null) {
                            it.remove();
                        } else if (interfaceC0462g == abstractC1838a) {
                            it.remove();
                            break;
                        }
                    }
                    if (set.isEmpty()) {
                        c1841d.f5383i.remove(strM2105H);
                    }
                }
            }
            if (z6) {
                InterfaceC1558s interfaceC1558s = this.f5364i;
                String str = abstractC1838a.f5344b;
                C1841d c1841d2 = (C1841d) interfaceC1558s;
                synchronized (c1841d2) {
                    setRemove = c1841d2.f5383i.remove(str);
                }
                if (setRemove != null) {
                    Iterator<WeakReference<InterfaceC0462g>> it2 = setRemove.iterator();
                    while (it2.hasNext()) {
                        AbstractC1838a abstractC1838a2 = (AbstractC1838a) it2.next().get();
                        if (abstractC1838a2 != null && (!abstractC1838a2.f5351i)) {
                            abstractC1838a2.mo189f();
                        }
                    }
                    setRemove.clear();
                }
            }
            if (!z6 || this.f5367l == null) {
                return;
            }
            C0027m c0027m = new C0027m(abstractC1838a);
            Iterator<InterfaceC0466k> it3 = this.f5367l.iterator();
            while (it3.hasNext()) {
                it3.next().mo197k(c0027m);
            }
        }
    }

    @Override // p168u5.AbstractC1980a
    public void doStart() {
        String str;
        this.f5369n = C1743c.m1894W();
        this.f5368m = Thread.currentThread().getContextClassLoader();
        if (this.f5364i == null) {
            C1555p c1555p = this.f5363h.f4928h;
            synchronized (c1555p) {
                InterfaceC1558s interfaceC1558s = c1555p.f4674o;
                this.f5364i = interfaceC1558s;
                if (interfaceC1558s == null) {
                    C1841d c1841d = new C1841d();
                    this.f5364i = c1841d;
                    InterfaceC1558s interfaceC1558s2 = c1555p.f4674o;
                    if (interfaceC1558s2 != null) {
                        c1555p.mo1799L(interfaceC1558s2);
                    }
                    c1555p.f4670k.m2314f(c1555p, c1555p.f4674o, c1841d, "sessionIdManager", false);
                    c1555p.f4674o = c1841d;
                    c1555p.mo1798G(c1841d);
                }
            }
        }
        if (!((AbstractC1980a) this.f5364i).isStarted()) {
            ((AbstractC1980a) this.f5364i).start();
        }
        C1743c.b bVar = this.f5369n;
        if (bVar != null) {
            String str2 = C1743c.this.f4941q.get("org.eclipse.jetty.servlet.SessionCookie");
            if (str2 != null) {
                this.f5370o = str2;
            }
            String str3 = C1743c.this.f4941q.get("org.eclipse.jetty.servlet.SessionIdPathParameterName");
            if (str3 != null) {
                this.f5371p = "none".equals(str3) ? null : str3;
                this.f5372q = "none".equals(str3) ? null : C0072a.m92a(C0413b.m112a(";"), this.f5371p, "=");
            }
            if (this.f5375t == -1 && (str = C1743c.this.f4941q.get("org.eclipse.jetty.servlet.MaxAge")) != null) {
                this.f5375t = Integer.parseInt(str.trim());
            }
            if (this.f5373r == null) {
                this.f5373r = C1743c.this.f4941q.get("org.eclipse.jetty.servlet.SessionDomain");
            }
            if (this.f5374s == null) {
                this.f5374s = C1743c.this.f4941q.get("org.eclipse.jetty.servlet.SessionPath");
            }
            String str4 = C1743c.this.f4941q.get("org.eclipse.jetty.servlet.CheckingRemoteSessionIdEncoding");
            if (str4 != null) {
                this.f5376u = Boolean.parseBoolean(str4);
            }
        }
        super.doStart();
    }

    @Override // p168u5.AbstractC1980a
    public void doStop() {
        super.doStop();
        C1842e c1842e = (C1842e) this;
        ArrayList arrayList = new ArrayList(c1842e.f5386B.values());
        int i7 = 100;
        while (arrayList.size() > 0) {
            int i8 = i7 - 1;
            if (i7 <= 0) {
                break;
            }
            c1842e.isStopping();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((C1843f) it.next()).mo189f();
            }
            arrayList = new ArrayList(c1842e.f5386B.values());
            i7 = i8;
        }
        this.f5368m = null;
    }
}
