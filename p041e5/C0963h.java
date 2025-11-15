package p041e5;

import android.support.constraint.motion.C0080b;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import p009b.C0413b;
import p041e5.C0962g;
import p041e5.C0966k;
import p049f5.InterfaceC1013a;
import p065h5.C1117y;
import p073i5.C1158k;
import p073i5.InterfaceC1161n;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;
import p196y5.C2133a;

/* compiled from: HttpDestination.java */
/* renamed from: e5.h */
/* loaded from: classes.dex */
public class C0963h {

    /* renamed from: q */
    public static final InterfaceC2016c f1759q;

    /* renamed from: e */
    public final C0962g f1764e;

    /* renamed from: f */
    public final C0957b f1765f;

    /* renamed from: g */
    public final boolean f1766g;

    /* renamed from: h */
    public final C2133a f1767h;

    /* renamed from: i */
    public final C1158k f1768i;

    /* renamed from: j */
    public volatile int f1769j;

    /* renamed from: k */
    public volatile int f1770k;

    /* renamed from: n */
    public volatile C0957b f1773n;

    /* renamed from: o */
    public InterfaceC1013a f1774o;

    /* renamed from: p */
    public C1117y f1775p;

    /* renamed from: a */
    public final List<C0965j> f1760a = new LinkedList();

    /* renamed from: b */
    public final List<AbstractC0956a> f1761b = new LinkedList();

    /* renamed from: c */
    public final BlockingQueue<Object> f1762c = new ArrayBlockingQueue(10, true);

    /* renamed from: d */
    public final List<AbstractC0956a> f1763d = new ArrayList();

    /* renamed from: l */
    public int f1771l = 0;

    /* renamed from: m */
    public int f1772m = 0;

    /* compiled from: HttpDestination.java */
    /* renamed from: e5.h$a */
    public class a extends C0961f {

        /* renamed from: a */
        public final C0966k.c f1776a;

        public a(C0957b c0957b, C0966k.c cVar) {
            this.f1776a = cVar;
            setMethod("CONNECT");
            String string = c0957b.toString();
            setRequestURI(string);
            addRequestHeader(HttpHeaders.HOST, string);
            addRequestHeader("Proxy-Connection", com.ctvit.network.model.HttpHeaders.HEAD_VALUE_CONNECTION_KEEP_ALIVE);
            addRequestHeader("User-Agent", "Jetty-Client");
        }

        @Override // p041e5.C0965j
        public void onConnectionFailed(Throwable th) throws InterruptedException {
            C0963h.this.m900c(th);
        }

        @Override // p041e5.C0965j
        public void onException(Throwable th) {
            C0965j c0965jRemove;
            synchronized (C0963h.this) {
                c0965jRemove = !C0963h.this.f1760a.isEmpty() ? C0963h.this.f1760a.remove(0) : null;
            }
            if (c0965jRemove == null || !c0965jRemove.setStatus(9)) {
                return;
            }
            c0965jRemove.getEventListener().mo880f(th);
        }

        @Override // p041e5.C0965j
        public void onExpire() {
            C0965j c0965jRemove;
            synchronized (C0963h.this) {
                c0965jRemove = !C0963h.this.f1760a.isEmpty() ? C0963h.this.f1760a.remove(0) : null;
            }
            if (c0965jRemove == null || !c0965jRemove.setStatus(8)) {
                return;
            }
            c0965jRemove.getEventListener().mo882h();
        }

        @Override // p041e5.C0965j
        public void onResponseComplete() {
            int responseStatus = getResponseStatus();
            if (responseStatus == 200) {
                this.f1776a.m933z();
                return;
            }
            if (responseStatus == 504) {
                onExpire();
                return;
            }
            StringBuilder sbM112a = C0413b.m112a("Proxy: ");
            sbM112a.append(this.f1776a.mo912e());
            sbM112a.append(":");
            sbM112a.append(this.f1776a.mo924q());
            sbM112a.append(" didn't return http return code 200, but ");
            sbM112a.append(responseStatus);
            onException(new ProtocolException(sbM112a.toString()));
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f1759q = C2015b.m2349a(C0963h.class.getName());
    }

    public C0963h(C0962g c0962g, C0957b c0957b, boolean z6, C2133a c2133a) {
        this.f1764e = c0962g;
        this.f1765f = c0957b;
        this.f1766g = z6;
        this.f1767h = c2133a;
        this.f1769j = c0962g.f1744k;
        this.f1770k = c0962g.f1745l;
        String string = c0957b.f1731a;
        if (c0957b.f1732b != (z6 ? 443 : 80)) {
            StringBuilder sbM94a = C0080b.m94a(string, ":");
            sbM94a.append(c0957b.f1732b);
            string = sbM94a.toString();
        }
        this.f1768i = new C1158k(string);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f A[Catch: all -> 0x00a9, TryCatch #2 {, blocks: (B:12:0x001d, B:13:0x0026, B:15:0x002f, B:16:0x003c), top: B:60:0x001d }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m898a(p041e5.C0965j r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lac
            h5.y r0 = r6.f1775p
            if (r0 == 0) goto L15
            java.lang.String r1 = r7.getRequestURI()
            java.lang.Object r0 = r0.m1261g(r1)
            f5.a r0 = (p049f5.InterfaceC1013a) r0
            if (r0 == 0) goto L15
            r0.m1026a(r7)
        L15:
            r7.scheduleTimeout(r6)
            r0 = 0
            r1 = r0
        L1a:
            monitor-enter(r6)
            if (r1 == 0) goto L26
            java.util.List<e5.a> r2 = r6.f1761b     // Catch: java.lang.Throwable -> La9
            r2.remove(r1)     // Catch: java.lang.Throwable -> La9
            r1.m863f()     // Catch: java.lang.Throwable -> La9
            r1 = r0
        L26:
            java.util.List<e5.a> r2 = r6.f1763d     // Catch: java.lang.Throwable -> La9
            int r2 = r2.size()     // Catch: java.lang.Throwable -> La9
            r3 = 1
            if (r2 <= 0) goto L3c
            java.util.List<e5.a> r1 = r6.f1763d     // Catch: java.lang.Throwable -> La9
            int r2 = r1.size()     // Catch: java.lang.Throwable -> La9
            int r2 = r2 - r3
            java.lang.Object r1 = r1.remove(r2)     // Catch: java.lang.Throwable -> La9
            e5.a r1 = (p041e5.AbstractC0956a) r1     // Catch: java.lang.Throwable -> La9
        L3c:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> La9
            r2 = 0
            if (r1 != 0) goto L41
            goto L5e
        L41:
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicBoolean r4 = r1.f1725l     // Catch: java.lang.Throwable -> La6
            boolean r4 = r4.compareAndSet(r3, r2)     // Catch: java.lang.Throwable -> La6
            if (r4 == 0) goto L59
            e5.h r4 = r1.f1716c     // Catch: java.lang.Throwable -> La6
            e5.g r4 = r4.f1764e     // Catch: java.lang.Throwable -> La6
            a6.e$a r5 = r1.f1724k     // Catch: java.lang.Throwable -> La6
            java.util.Objects.requireNonNull(r4)     // Catch: java.lang.Throwable -> La6
            r5.m56b()     // Catch: java.lang.Throwable -> La6
            monitor-exit(r1)     // Catch: java.lang.Throwable -> La6
            r4 = 1
            goto L5b
        L59:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> La6
            r4 = 0
        L5b:
            if (r4 == 0) goto L1a
            r0 = r1
        L5e:
            if (r0 == 0) goto L64
            r6.m904g(r0, r7)
            goto L89
        L64:
            monitor-enter(r6)
            java.util.List<e5.j> r0 = r6.f1760a     // Catch: java.lang.Throwable -> La3
            int r0 = r0.size()     // Catch: java.lang.Throwable -> La3
            int r1 = r6.f1770k     // Catch: java.lang.Throwable -> La3
            if (r0 == r1) goto L8a
            java.util.List<e5.j> r0 = r6.f1760a     // Catch: java.lang.Throwable -> La3
            r0.add(r7)     // Catch: java.lang.Throwable -> La3
            java.util.List<e5.a> r7 = r6.f1761b     // Catch: java.lang.Throwable -> La3
            int r7 = r7.size()     // Catch: java.lang.Throwable -> La3
            int r0 = r6.f1771l     // Catch: java.lang.Throwable -> La3
            int r7 = r7 + r0
            int r0 = r6.f1769j     // Catch: java.lang.Throwable -> La3
            if (r7 >= r0) goto L82
            goto L83
        L82:
            r3 = 0
        L83:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> La3
            if (r3 == 0) goto L89
            r6.m906i()
        L89:
            return
        L8a:
            java.util.concurrent.RejectedExecutionException r7 = new java.util.concurrent.RejectedExecutionException     // Catch: java.lang.Throwable -> La3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La3
            r0.<init>()     // Catch: java.lang.Throwable -> La3
            java.lang.String r1 = "Queue full for address "
            r0.append(r1)     // Catch: java.lang.Throwable -> La3
            e5.b r1 = r6.f1765f     // Catch: java.lang.Throwable -> La3
            r0.append(r1)     // Catch: java.lang.Throwable -> La3
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> La3
            r7.<init>(r0)     // Catch: java.lang.Throwable -> La3
            throw r7     // Catch: java.lang.Throwable -> La3
        La3:
            r7 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> La3
            throw r7
        La6:
            r7 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> La6
            throw r7
        La9:
            r7 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> La9
            throw r7
        Lac:
            r7 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lac
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p041e5.C0963h.m898a(e5.j):void");
    }

    /* renamed from: b */
    public boolean m899b() {
        return this.f1773n != null;
    }

    /* renamed from: c */
    public void m900c(Throwable th) throws InterruptedException {
        boolean z6;
        synchronized (this) {
            z6 = true;
            this.f1771l--;
            int i7 = this.f1772m;
            if (i7 > 0) {
                this.f1772m = i7 - 1;
            } else {
                if (this.f1760a.size() > 0) {
                    C0965j c0965jRemove = this.f1760a.remove(0);
                    if (c0965jRemove.setStatus(9)) {
                        c0965jRemove.getEventListener().mo878d(th);
                    }
                    if (!this.f1760a.isEmpty() && this.f1764e.isStarted()) {
                        th = null;
                    }
                }
                th = null;
            }
            z6 = false;
        }
        if (z6) {
            m906i();
        }
        if (th != null) {
            try {
                this.f1762c.put(th);
            } catch (InterruptedException e7) {
                f1759q.mo2360k(e7);
            }
        }
    }

    /* renamed from: d */
    public void m901d(AbstractC0956a abstractC0956a) {
        synchronized (this) {
            this.f1771l--;
            this.f1761b.add(abstractC0956a);
            int i7 = this.f1772m;
            if (i7 > 0) {
                this.f1772m = i7 - 1;
            } else {
                InterfaceC1161n interfaceC1161n = abstractC0956a.f2538a;
                if (m899b() && (interfaceC1161n instanceof C0966k.c)) {
                    a aVar = new a(this.f1765f, (C0966k.c) interfaceC1161n);
                    aVar.setAddress(this.f1773n);
                    f1759q.mo2351a("Establishing tunnel to {} via {}", this.f1765f, this.f1773n);
                    m904g(abstractC0956a, aVar);
                } else if (this.f1760a.size() == 0) {
                    f1759q.mo2351a("No exchanges for new connection {}", abstractC0956a);
                    abstractC0956a.m867j();
                    this.f1763d.add(abstractC0956a);
                } else {
                    m904g(abstractC0956a, this.f1760a.remove(0));
                }
                abstractC0956a = null;
            }
        }
        if (abstractC0956a != null) {
            try {
                this.f1762c.put(abstractC0956a);
            } catch (InterruptedException e7) {
                f1759q.mo2360k(e7);
            }
        }
    }

    /* renamed from: e */
    public void m902e(AbstractC0956a abstractC0956a, boolean z6) {
        boolean z7 = false;
        if (abstractC0956a.f1721h) {
            abstractC0956a.f1721h = false;
        }
        if (z6) {
            try {
                abstractC0956a.m863f();
            } catch (IOException e7) {
                f1759q.mo2360k(e7);
            }
        }
        if (this.f1764e.isStarted()) {
            if (!z6 && abstractC0956a.f2538a.isOpen()) {
                synchronized (this) {
                    if (this.f1760a.size() == 0) {
                        abstractC0956a.m867j();
                        this.f1763d.add(abstractC0956a);
                    } else {
                        m904g(abstractC0956a, this.f1760a.remove(0));
                    }
                    notifyAll();
                }
                return;
            }
            synchronized (this) {
                this.f1761b.remove(abstractC0956a);
                if (this.f1760a.isEmpty()) {
                    Objects.requireNonNull(this.f1764e);
                } else if (this.f1764e.isStarted()) {
                    z7 = true;
                }
            }
            if (z7) {
                m906i();
            }
        }
    }

    /* renamed from: f */
    public void m903f(AbstractC0956a abstractC0956a) {
        abstractC0956a.mo889d(abstractC0956a.f2538a != null ? r0.mo916i() : -1L);
        boolean z6 = false;
        synchronized (this) {
            this.f1763d.remove(abstractC0956a);
            this.f1761b.remove(abstractC0956a);
            if (this.f1760a.isEmpty()) {
                Objects.requireNonNull(this.f1764e);
            } else if (this.f1764e.isStarted()) {
                z6 = true;
            }
        }
        if (z6) {
            m906i();
        }
    }

    /* renamed from: g */
    public void m904g(AbstractC0956a abstractC0956a, C0965j c0965j) {
        synchronized (this) {
            if (!abstractC0956a.mo866i(c0965j)) {
                if (c0965j.getStatus() <= 1) {
                    this.f1760a.add(0, c0965j);
                }
                m903f(abstractC0956a);
            }
        }
    }

    /* renamed from: h */
    public void m905h(C0965j c0965j) {
        c0965j.setStatus(1);
        Objects.requireNonNull(this.f1764e);
        Objects.requireNonNull(this.f1764e);
        m898a(c0965j);
    }

    /* renamed from: i */
    public void m906i() {
        try {
            synchronized (this) {
                this.f1771l++;
            }
            C0962g.b bVar = this.f1764e.f1748o;
            if (bVar != null) {
                bVar.mo897n(this);
            }
        } catch (Exception e7) {
            f1759q.mo2359j(e7);
            m900c(e7);
        }
    }

    public synchronized String toString() {
        C0957b c0957b;
        c0957b = this.f1765f;
        return String.format("HttpDestination@%x//%s:%d(%d/%d,%d,%d/%d)%n", Integer.valueOf(hashCode()), c0957b.f1731a, Integer.valueOf(c0957b.f1732b), Integer.valueOf(this.f1761b.size()), Integer.valueOf(this.f1769j), Integer.valueOf(this.f1763d.size()), Integer.valueOf(this.f1760a.size()), Integer.valueOf(this.f1770k));
    }
}
