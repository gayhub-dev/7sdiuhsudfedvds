package p113n5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import p006a5.C0016b;
import p006a5.InterfaceC0014a;
import p006a5.InterfaceC0017c;
import p006a5.InterfaceC0026l;
import p006a5.InterfaceC0032r;
import p006a5.InterfaceC0037w;
import p007a6.C0045e;
import p057g5.C1062b;
import p073i5.InterfaceC1151d;
import p073i5.InterfaceC1161n;
import p131p5.C1743c;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: AsyncContinuation.java */
/* renamed from: n5.c */
/* loaded from: classes.dex */
public class C1542c implements InterfaceC0014a {

    /* renamed from: k */
    public static final InterfaceC2016c f4567k;

    /* renamed from: a */
    public AbstractC1541b f4568a;

    /* renamed from: b */
    public List<InterfaceC0017c> f4569b;

    /* renamed from: c */
    public List<InterfaceC0017c> f4570c;

    /* renamed from: f */
    public boolean f4573f;

    /* renamed from: g */
    public boolean f4574g;

    /* renamed from: i */
    public a f4576i;

    /* renamed from: j */
    public volatile long f4577j;

    /* renamed from: h */
    public long f4575h = 30000;

    /* renamed from: d */
    public int f4571d = 0;

    /* renamed from: e */
    public boolean f4572e = true;

    /* compiled from: AsyncContinuation.java */
    /* renamed from: n5.c$a */
    public class a extends C0016b {

        /* renamed from: d */
        public final InterfaceC0026l f4578d;

        /* renamed from: e */
        public InterfaceC0026l f4579e;

        /* renamed from: f */
        public String f4580f;

        /* renamed from: g */
        public C0045e.a f4581g;

        public a(C1542c c1542c, InterfaceC0026l interfaceC0026l, InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w) {
            super(c1542c, interfaceC0032r, interfaceC0037w);
            this.f4581g = c1542c.new b();
            this.f4578d = interfaceC0026l;
            C1553n c1553n = c1542c.f4568a.f4548i;
            if (c1553n.mo26a("javax.servlet.async.request_uri") == null) {
                String str = (String) c1553n.mo26a("javax.servlet.forward.request_uri");
                if (str != null) {
                    c1553n.mo27b("javax.servlet.async.request_uri", str);
                    c1553n.mo27b("javax.servlet.async.context_path", c1553n.mo26a("javax.servlet.forward.context_path"));
                    c1553n.mo27b("javax.servlet.async.servlet_path", c1553n.mo26a("javax.servlet.forward.servlet_path"));
                    c1553n.mo27b("javax.servlet.async.path_info", c1553n.mo26a("javax.servlet.forward.path_info"));
                    c1553n.mo27b("javax.servlet.async.query_string", c1553n.mo26a("javax.servlet.forward.query_string"));
                    return;
                }
                c1553n.mo27b("javax.servlet.async.request_uri", c1553n.mo167U());
                c1553n.mo27b("javax.servlet.async.context_path", c1553n.f4641j);
                c1553n.mo27b("javax.servlet.async.servlet_path", c1553n.mo160B());
                c1553n.mo27b("javax.servlet.async.path_info", c1553n.f4652u);
                c1553n.mo27b("javax.servlet.async.query_string", c1553n.mo166O());
            }
        }

        /* renamed from: a */
        public InterfaceC0026l m1764a() {
            InterfaceC0026l interfaceC0026l = this.f4579e;
            return interfaceC0026l == null ? this.f4578d : interfaceC0026l;
        }

        /* renamed from: b */
        public void m1765b(String str) {
            this.f4580f = str;
        }
    }

    /* compiled from: AsyncContinuation.java */
    /* renamed from: n5.c$b */
    public class b extends C0045e.a implements Runnable {
        public b() {
        }

        @Override // p007a6.C0045e.a
        /* renamed from: c */
        public void mo57c() {
            C1542c.this.m1749g();
        }

        @Override // java.lang.Runnable
        public void run() {
            C1542c.this.m1749g();
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f4567k = C2015b.m2349a(C1542c.class.getName());
        new C1062b();
    }

    /* renamed from: a */
    public void m1743a(InterfaceC0017c interfaceC0017c) {
        synchronized (this) {
            if (this.f4570c == null) {
                this.f4570c = new ArrayList();
            }
            this.f4570c.add(interfaceC0017c);
        }
    }

    /* renamed from: b */
    public void m1744b() {
        InterfaceC1161n interfaceC1161n = this.f4568a.f2538a;
        if (interfaceC1161n.mo923p()) {
            synchronized (this) {
                this.f4577j = 0L;
                notifyAll();
            }
        } else {
            a aVar = this.f4576i;
            if (aVar != null) {
                ((InterfaceC1151d) interfaceC1161n).mo908a(aVar.f4581g);
            }
        }
    }

    /* renamed from: c */
    public void m1745c() {
        synchronized (this) {
            int i7 = this.f4571d;
            if (i7 != 1) {
                if (i7 == 2) {
                    this.f4571d = 7;
                    return;
                }
                if (i7 == 4) {
                    this.f4571d = 7;
                    boolean z6 = !this.f4574g;
                    if (z6) {
                        m1744b();
                        m1761s();
                        return;
                    }
                    return;
                }
                if (i7 != 6) {
                    throw new IllegalStateException(m1753k());
                }
            }
            throw new IllegalStateException(m1753k());
        }
    }

    /* renamed from: d */
    public void m1746d() {
        synchronized (this) {
            int i7 = this.f4571d;
            if (i7 == 2) {
                this.f4571d = 3;
                this.f4573f = true;
                return;
            }
            if (i7 != 4) {
                if (i7 != 5) {
                    throw new IllegalStateException(m1753k());
                }
                return;
            }
            boolean z6 = !this.f4574g;
            this.f4571d = 5;
            this.f4573f = true;
            if (z6) {
                m1744b();
                m1761s();
            }
        }
    }

    /* renamed from: e */
    public void m1747e(Throwable th) {
        List<InterfaceC0017c> list;
        synchronized (this) {
            if (this.f4571d != 8) {
                throw new IllegalStateException(m1753k());
            }
            this.f4571d = 9;
            list = this.f4570c;
        }
        if (list != null) {
            for (InterfaceC0017c interfaceC0017c : list) {
                if (th != null) {
                    try {
                        this.f4576i.f27a.mo27b("javax.servlet.error.exception", th);
                        this.f4576i.f27a.mo27b("javax.servlet.error.message", th.getMessage());
                        interfaceC0017c.onError(this.f4576i);
                    } catch (Exception e7) {
                        f4567k.mo2358i(e7);
                    }
                } else {
                    interfaceC0017c.onComplete(this.f4576i);
                }
            }
        }
    }

    /* renamed from: f */
    public void m1748f() {
        synchronized (this) {
            int i7 = this.f4571d;
            if (i7 == 2 || i7 == 3) {
                this.f4571d = 7;
                this.f4573f = false;
            } else if (i7 != 7) {
                throw new IllegalStateException(m1753k());
            }
        }
    }

    /* renamed from: g */
    public void m1749g() {
        synchronized (this) {
            int i7 = this.f4571d;
            if (i7 == 2 || i7 == 4) {
                List<InterfaceC0017c> list = this.f4570c;
                this.f4574g = true;
                if (list != null) {
                    Iterator<InterfaceC0017c> it = list.iterator();
                    while (it.hasNext()) {
                        try {
                            it.next().onTimeout(this.f4576i);
                        } catch (Exception e7) {
                            f4567k.mo2359j(e7);
                            this.f4568a.f4548i.mo27b("javax.servlet.error.exception", e7);
                        }
                    }
                }
                synchronized (this) {
                    int i8 = this.f4571d;
                    if (i8 == 2 || i8 == 4) {
                        m1746d();
                    } else {
                        this.f4574g = false;
                    }
                }
                m1761s();
            }
        }
    }

    /* renamed from: h */
    public a m1750h() {
        a aVar;
        synchronized (this) {
            aVar = this.f4576i;
        }
        return aVar;
    }

    /* renamed from: i */
    public C1743c m1751i() {
        a aVar = this.f4576i;
        if (aVar != null) {
            return C1743c.this;
        }
        return null;
    }

    /* renamed from: j */
    public InterfaceC0032r m1752j() {
        a aVar = this.f4576i;
        return aVar != null ? aVar.f27a : this.f4568a.f4548i;
    }

    /* renamed from: k */
    public String m1753k() {
        String str;
        String string;
        synchronized (this) {
            StringBuilder sb = new StringBuilder();
            int i7 = this.f4571d;
            if (i7 == 0) {
                str = "IDLE";
            } else if (i7 == 1) {
                str = "DISPATCHED";
            } else if (i7 == 2) {
                str = "ASYNCSTARTED";
            } else if (i7 == 4) {
                str = "ASYNCWAIT";
            } else if (i7 == 3) {
                str = "REDISPATCHING";
            } else if (i7 == 5) {
                str = "REDISPATCH";
            } else if (i7 == 6) {
                str = "REDISPATCHED";
            } else if (i7 == 7) {
                str = "COMPLETING";
            } else if (i7 == 8) {
                str = "UNCOMPLETED";
            } else if (i7 == 9) {
                str = "COMPLETE";
            } else {
                str = "UNKNOWN?" + this.f4571d;
            }
            sb.append(str);
            sb.append(this.f4572e ? ",initial" : "");
            sb.append(this.f4573f ? ",resumed" : "");
            sb.append(this.f4574g ? ",expired" : "");
            string = sb.toString();
        }
        return string;
    }

    /* renamed from: l */
    public boolean m1754l() {
        synchronized (this) {
            int i7 = this.f4571d;
            if (i7 != 0) {
                if (i7 == 7) {
                    this.f4571d = 8;
                    return false;
                }
                if (i7 == 4) {
                    return false;
                }
                if (i7 != 5) {
                    throw new IllegalStateException(m1753k());
                }
                this.f4571d = 6;
                return true;
            }
            this.f4572e = true;
            this.f4571d = 1;
            List<InterfaceC0017c> list = this.f4569b;
            if (list != null) {
                list.clear();
            }
            List<InterfaceC0017c> list2 = this.f4570c;
            if (list2 != null) {
                list2.clear();
            } else {
                this.f4570c = this.f4569b;
                this.f4569b = null;
            }
            return true;
        }
    }

    /* renamed from: m */
    public boolean m1755m() {
        synchronized (this) {
            int i7 = this.f4571d;
            return i7 == 2 || i7 == 3 || i7 == 4 || i7 == 5;
        }
    }

    /* renamed from: n */
    public boolean m1756n() {
        return false;
    }

    /* renamed from: o */
    public boolean m1757o() {
        boolean z6;
        synchronized (this) {
            z6 = this.f4574g;
        }
        return z6;
    }

    /* renamed from: p */
    public boolean m1758p() {
        boolean z6;
        synchronized (this) {
            z6 = this.f4572e;
        }
        return z6;
    }

    /* renamed from: q */
    public boolean m1759q() {
        synchronized (this) {
            int i7 = this.f4571d;
            return i7 == 2 || i7 == 3 || i7 == 4 || i7 == 7;
        }
    }

    /* renamed from: r */
    public boolean m1760r() {
        boolean z6;
        synchronized (this) {
            z6 = this.f4571d == 8;
        }
        return z6;
    }

    /* renamed from: s */
    public void m1761s() {
        InterfaceC1161n interfaceC1161n = this.f4568a.f2538a;
        if (interfaceC1161n.mo923p()) {
            return;
        }
        ((InterfaceC1151d) interfaceC1161n).mo915h();
    }

    /* renamed from: t */
    public void m1762t() {
        InterfaceC1161n interfaceC1161n = this.f4568a.f2538a;
        if (this.f4575h > 0) {
            if (!interfaceC1161n.mo923p()) {
                ((InterfaceC1151d) interfaceC1161n).mo909b(this.f4576i.f4581g, this.f4575h);
                return;
            }
            synchronized (this) {
                this.f4577j = System.currentTimeMillis() + this.f4575h;
                long jCurrentTimeMillis = this.f4575h;
                while (this.f4577j > 0 && jCurrentTimeMillis > 0 && this.f4568a.f4544e.isRunning()) {
                    try {
                        wait(jCurrentTimeMillis);
                    } catch (InterruptedException e7) {
                        f4567k.mo2360k(e7);
                    }
                    jCurrentTimeMillis = this.f4577j - System.currentTimeMillis();
                }
                if (this.f4577j > 0 && jCurrentTimeMillis <= 0 && this.f4568a.f4544e.isRunning()) {
                    m1749g();
                }
            }
        }
    }

    public String toString() {
        String str;
        synchronized (this) {
            str = super.toString() + "@" + m1753k();
        }
        return str;
    }

    /* renamed from: u */
    public boolean m1763u() {
        synchronized (this) {
            int i7 = this.f4571d;
            if (i7 == 0) {
                throw new IllegalStateException(m1753k());
            }
            if (i7 != 1) {
                if (i7 == 2) {
                    this.f4572e = false;
                    this.f4571d = 4;
                    m1762t();
                    int i8 = this.f4571d;
                    if (i8 == 4) {
                        return true;
                    }
                    if (i8 == 7) {
                        this.f4571d = 8;
                        return true;
                    }
                    this.f4572e = false;
                    this.f4571d = 6;
                    return false;
                }
                if (i7 == 3) {
                    this.f4572e = false;
                    this.f4571d = 6;
                    return false;
                }
                if (i7 != 6) {
                    if (i7 != 7) {
                        throw new IllegalStateException(m1753k());
                    }
                    this.f4572e = false;
                    this.f4571d = 8;
                    return true;
                }
            }
            this.f4571d = 8;
            return true;
        }
    }
}
