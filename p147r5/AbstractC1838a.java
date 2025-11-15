package p147r5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import p015b5.C0464i;
import p015b5.InterfaceC0465j;
import p147r5.AbstractC1840c;
import p175v5.InterfaceC2016c;

/* compiled from: AbstractSession.java */
/* renamed from: r5.a */
/* loaded from: classes.dex */
public abstract class AbstractC1838a implements AbstractC1840c.b {

    /* renamed from: m */
    public static final InterfaceC2016c f5342m = C1844g.f5397o;

    /* renamed from: a */
    public final AbstractC1840c f5343a;

    /* renamed from: b */
    public final String f5344b;

    /* renamed from: c */
    public final String f5345c;

    /* renamed from: e */
    public boolean f5347e;

    /* renamed from: g */
    public long f5349g;

    /* renamed from: h */
    public long f5350h;

    /* renamed from: i */
    public boolean f5351i;

    /* renamed from: j */
    public boolean f5352j;

    /* renamed from: k */
    public long f5353k;

    /* renamed from: l */
    public int f5354l;

    /* renamed from: d */
    public final Map<String, Object> f5346d = new HashMap();

    /* renamed from: f */
    public final long f5348f = System.currentTimeMillis();

    /* JADX WARN: Removed duplicated region for block: B:12:0x0030 A[Catch: all -> 0x0045, TryCatch #0 {all -> 0x0045, blocks: (B:6:0x001b, B:8:0x0021, B:10:0x002e, B:12:0x0030, B:14:0x003a, B:16:0x0043, B:22:0x004b, B:24:0x0051, B:27:0x005b, B:28:0x0060, B:37:0x00ac, B:39:0x00b2, B:44:0x00dd, B:46:0x00e3, B:48:0x00eb, B:50:0x00fc, B:51:0x0108, B:52:0x0123, B:54:0x0127, B:59:0x0151, B:55:0x0146, B:40:0x00d0), top: B:62:0x001b }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AbstractC1838a(p147r5.AbstractC1840c r13, p015b5.InterfaceC0458c r14) {
        /*
            Method dump skipped, instructions count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p147r5.AbstractC1838a.<init>(r5.c, b5.c):void");
    }

    @Override // p015b5.InterfaceC0462g
    /* renamed from: a */
    public Object mo186a(String str) {
        Object obj;
        synchronized (this) {
            mo2088h();
            obj = this.f5346d.get(str);
        }
        return obj;
    }

    @Override // p015b5.InterfaceC0462g
    /* renamed from: b */
    public void mo187b(String str, Object obj) {
        Object objRemove;
        synchronized (this) {
            mo2088h();
            objRemove = obj == null ? this.f5346d.remove(str) : this.f5346d.put(str, obj);
        }
        if (obj == null || !obj.equals(objRemove)) {
            if (objRemove != null) {
                m2094n(str, objRemove);
            }
            if (obj != null && (obj instanceof InterfaceC0465j)) {
                ((InterfaceC0465j) obj).mo195p(new C0464i(this, str));
            }
            this.f5343a.m2099K(this, str, objRemove, obj);
        }
    }

    @Override // p147r5.AbstractC1840c.b
    /* renamed from: c */
    public AbstractC1838a mo2086c() {
        return this;
    }

    /* renamed from: d */
    public boolean m2087d(long j7) {
        synchronized (this) {
            if (this.f5351i) {
                return false;
            }
            long j8 = this.f5350h;
            this.f5350h = j7;
            long j9 = this.f5353k;
            if (j9 <= 0 || j8 <= 0 || j8 + j9 >= j7) {
                this.f5354l++;
                return true;
            }
            mo189f();
            return false;
        }
    }

    @Override // p015b5.InterfaceC0462g
    /* renamed from: e */
    public void mo188e(String str) {
        mo187b(str, null);
    }

    @Override // p015b5.InterfaceC0462g
    /* renamed from: f */
    public void mo189f() {
        this.f5343a.m2103O(this, true);
        mo2090j();
    }

    @Override // p015b5.InterfaceC0462g
    /* renamed from: g */
    public Enumeration<String> mo190g() {
        Enumeration<String> enumeration;
        synchronized (this) {
            mo2088h();
            enumeration = Collections.enumeration(this.f5346d == null ? Collections.EMPTY_LIST : new ArrayList(this.f5346d.keySet()));
        }
        return enumeration;
    }

    @Override // p015b5.InterfaceC0462g
    public String getId() {
        Objects.requireNonNull(this.f5343a);
        return this.f5344b;
    }

    /* renamed from: h */
    public void mo2088h() {
        if (this.f5351i) {
            throw new IllegalStateException();
        }
    }

    /* renamed from: i */
    public void m2089i() {
        ArrayList arrayList;
        Object objRemove;
        while (true) {
            Map<String, Object> map = this.f5346d;
            if (map == null || map.size() <= 0) {
                break;
            }
            synchronized (this) {
                arrayList = new ArrayList(this.f5346d.keySet());
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                synchronized (this) {
                    objRemove = this.f5346d.remove(str);
                }
                if (objRemove != null && (objRemove instanceof InterfaceC0465j)) {
                    ((InterfaceC0465j) objRemove).mo194c(new C0464i(this, str));
                }
                this.f5343a.m2099K(this, str, objRemove, null);
            }
        }
        Map<String, Object> map2 = this.f5346d;
        if (map2 != null) {
            map2.clear();
        }
    }

    /* renamed from: j */
    public void mo2090j() {
        try {
            f5342m.mo2351a("invalidate {}", this.f5344b);
            if (!this.f5351i) {
                m2089i();
            }
            synchronized (this) {
                this.f5351i = true;
            }
        } catch (Throwable th) {
            synchronized (this) {
                this.f5351i = true;
                throw th;
            }
        }
    }

    /* renamed from: k */
    public long m2091k() {
        long j7;
        synchronized (this) {
            j7 = this.f5350h;
        }
        return j7;
    }

    /* renamed from: l */
    public int m2092l() {
        return (int) (this.f5353k / 1000);
    }

    /* renamed from: m */
    public void m2093m() {
        boolean z6 = true;
        this.f5343a.m2103O(this, true);
        synchronized (this) {
            if (this.f5351i) {
                z6 = false;
            } else if (this.f5354l > 0) {
                this.f5352j = true;
                z6 = false;
            }
        }
        if (z6) {
            mo2090j();
        }
    }

    /* renamed from: n */
    public void m2094n(String str, Object obj) {
        if (obj instanceof InterfaceC0465j) {
            ((InterfaceC0465j) obj).mo194c(new C0464i(this, str));
        }
    }

    public String toString() {
        return getClass().getName() + ":" + getId() + "@" + hashCode();
    }
}
