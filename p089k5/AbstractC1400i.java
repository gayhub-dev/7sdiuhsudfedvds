package p089k5;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import javax.net.ssl.SSLEngine;
import p007a6.C0045e;
import p009b.C0413b;
import p041e5.C0958c;
import p041e5.C0963h;
import p041e5.C0966k;
import p065h5.C1097e;
import p073i5.InterfaceC1159l;
import p073i5.InterfaceC1161n;
import p168u5.AbstractC1980a;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;
import p196y5.C2133a;

/* compiled from: SelectorManager.java */
/* renamed from: k5.i */
/* loaded from: classes.dex */
public abstract class AbstractC1400i extends AbstractC1980a {

    /* renamed from: i */
    public static final InterfaceC2016c f4084i = C2015b.m2349a("org.eclipse.jetty.io.nio");

    /* renamed from: j */
    public static final int f4085j = Integer.getInteger("org.eclipse.jetty.io.nio.MONITOR_PERIOD", 1000).intValue();

    /* renamed from: k */
    public static final int f4086k = Integer.getInteger("org.eclipse.jetty.io.nio.MAX_SELECTS", 100000).intValue();

    /* renamed from: l */
    public static final int f4087l = Integer.getInteger("org.eclipse.jetty.io.nio.BUSY_PAUSE", 50).intValue();

    /* renamed from: m */
    public static final int f4088m = Integer.getInteger("org.eclipse.jetty.io.nio.IDLE_TICK", 400).intValue();

    /* renamed from: e */
    public d[] f4089e;

    /* renamed from: f */
    public int f4090f = 1;

    /* renamed from: g */
    public volatile int f4091g = 0;

    /* renamed from: h */
    public boolean f4092h = true;

    /* compiled from: SelectorManager.java */
    /* renamed from: k5.i$a */
    public class a implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ int f4093e;

        public a(int i7) {
            this.f4093e = i7;
        }

        @Override // java.lang.Runnable
        public void run() {
            String name = Thread.currentThread().getName();
            Thread.currentThread().getPriority();
            try {
                d[] dVarArr = AbstractC1400i.this.f4089e;
                if (dVarArr == null) {
                    AbstractC1400i.f4084i.mo2351a("Stopped {} on {}", Thread.currentThread(), this);
                    Thread.currentThread().setName(name);
                    Objects.requireNonNull(AbstractC1400i.this);
                    return;
                }
                d dVar = dVarArr[this.f4093e];
                Thread.currentThread().setName(name + " Selector" + this.f4093e);
                Objects.requireNonNull(AbstractC1400i.this);
                AbstractC1400i.f4084i.mo2351a("Starting {} on {}", Thread.currentThread(), this);
                while (AbstractC1400i.this.isRunning()) {
                    try {
                        try {
                            dVar.m1575d();
                        } catch (IOException e7) {
                            AbstractC1400i.f4084i.mo2360k(e7);
                        }
                    } catch (Exception e8) {
                        AbstractC1400i.f4084i.mo2358i(e8);
                    }
                }
                AbstractC1400i.f4084i.mo2351a("Stopped {} on {}", Thread.currentThread(), this);
                Thread.currentThread().setName(name);
                Objects.requireNonNull(AbstractC1400i.this);
            } catch (Throwable th) {
                AbstractC1400i.f4084i.mo2351a("Stopped {} on {}", Thread.currentThread(), this);
                Thread.currentThread().setName(name);
                Objects.requireNonNull(AbstractC1400i.this);
                throw th;
            }
        }
    }

    /* compiled from: SelectorManager.java */
    /* renamed from: k5.i$b */
    public interface b extends Runnable {
    }

    /* compiled from: SelectorManager.java */
    /* renamed from: k5.i$c */
    public static class c {

        /* renamed from: a */
        public final SelectableChannel f4095a;

        /* renamed from: b */
        public final Object f4096b;

        public c(SelectableChannel selectableChannel, Object obj) {
            this.f4095a = selectableChannel;
            this.f4096b = obj;
        }
    }

    /* compiled from: SelectorManager.java */
    /* renamed from: k5.i$d */
    public class d {

        /* renamed from: a */
        public final int f4097a;

        /* renamed from: b */
        public final C0045e f4098b;

        /* renamed from: d */
        public volatile Selector f4100d;

        /* renamed from: e */
        public volatile Thread f4101e;

        /* renamed from: f */
        public int f4102f;

        /* renamed from: g */
        public long f4103g;

        /* renamed from: h */
        public boolean f4104h;

        /* renamed from: i */
        public boolean f4105i;

        /* renamed from: c */
        public final ConcurrentLinkedQueue<Object> f4099c = new ConcurrentLinkedQueue<>();

        /* renamed from: k */
        public ConcurrentMap<C1398g, Object> f4107k = new ConcurrentHashMap();

        /* renamed from: j */
        public volatile long f4106j = System.currentTimeMillis();

        /* compiled from: SelectorManager.java */
        /* renamed from: k5.i$d$a */
        public class a implements Runnable {

            /* renamed from: e */
            public final /* synthetic */ long f4109e;

            public a(long j7) {
                this.f4109e = j7;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (C1398g c1398g : d.this.f4107k.keySet()) {
                    long j7 = this.f4109e;
                    if (c1398g.f4066B && c1398g.f4054j > 0) {
                        long j8 = j7 - c1398g.f4065A;
                        if (j8 > c1398g.f4054j) {
                            c1398g.m1567D(false);
                            C0966k.this.f1782h.f1747n.dispatch(new RunnableC1399h(c1398g, j8));
                        }
                    }
                }
            }

            public String toString() {
                StringBuilder sbM112a = C0413b.m112a("Idle-");
                sbM112a.append(super.toString());
                return sbM112a.toString();
            }
        }

        /* compiled from: SelectorManager.java */
        /* renamed from: k5.i$d$b */
        public class b implements b {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.m1576e();
            }
        }

        public d(int i7) {
            this.f4097a = i7;
            C0045e c0045e = new C0045e(this);
            this.f4098b = c0045e;
            c0045e.f60b = 0L;
            this.f4100d = Selector.open();
            this.f4103g = System.currentTimeMillis() + AbstractC1400i.f4085j;
        }

        /* renamed from: a */
        public void m1572a(SelectableChannel selectableChannel, Object obj) {
            if (obj == null) {
                this.f4099c.add(selectableChannel);
            } else if (obj instanceof InterfaceC1161n) {
                this.f4099c.add(obj);
            } else {
                this.f4099c.add(new c(selectableChannel, obj));
            }
        }

        /* renamed from: b */
        public final C1398g m1573b(SocketChannel socketChannel, SelectionKey selectionKey) {
            InterfaceC1159l cVar;
            SSLEngine sSLEngineCreateSSLEngine;
            C0966k.b bVar = (C0966k.b) AbstractC1400i.this;
            C0045e.a aVarRemove = C0966k.this.f1784j.remove(socketChannel);
            if (aVarRemove != null) {
                aVarRemove.m56b();
            }
            if (bVar.f1788n.mo2353d()) {
                bVar.f1788n.mo2351a("Channels with connection pending: {}", Integer.valueOf(C0966k.this.f1784j.size()));
            }
            C0963h c0963h = (C0963h) selectionKey.attachment();
            C1398g c1398g = new C1398g(socketChannel, this, selectionKey, (int) C0966k.this.f1782h.f1749p);
            if (c0963h.f1766g) {
                bVar.f1788n.mo2351a("secure to {}, proxied={}", socketChannel, Boolean.valueOf(c0963h.m899b()));
                C2133a c2133a = c0963h.f1767h;
                synchronized (bVar) {
                    if (socketChannel != null) {
                        sSLEngineCreateSSLEngine = c2133a.f6277p ? c2133a.f6278q.createSSLEngine(socketChannel.socket().getInetAddress().getHostAddress(), socketChannel.socket().getPort()) : c2133a.f6278q.createSSLEngine();
                        c2133a.m2563G(sSLEngineCreateSSLEngine);
                    } else {
                        sSLEngineCreateSSLEngine = c2133a.f6278q.createSSLEngine();
                        c2133a.m2563G(sSLEngineCreateSSLEngine);
                    }
                    sSLEngineCreateSSLEngine.setUseClientMode(true);
                    sSLEngineCreateSSLEngine.beginHandshake();
                }
                cVar = new C0966k.c(c1398g, sSLEngineCreateSSLEngine);
            } else {
                cVar = c1398g;
            }
            AbstractC1400i abstractC1400i = AbstractC1400i.this;
            selectionKey.attachment();
            C0966k.b bVar2 = (C0966k.b) abstractC1400i;
            Objects.requireNonNull(bVar2);
            C1097e c1097e = C0966k.this.f1782h.f1757x;
            C0958c c0958c = new C0958c(c1097e.f2271n, c1097e.f2272o, cVar);
            cVar.mo910c(c0958c);
            c0958c.f1716c = c0963h;
            if (c0963h.f1766g && !c0963h.m899b()) {
                ((C0966k.c) cVar).m933z();
            }
            c0963h.m901d(c0958c);
            AbstractC1400i.f4084i.mo2351a("created {}", c1398g);
            Objects.requireNonNull(AbstractC1400i.this);
            this.f4107k.put(c1398g, this);
            return c1398g;
        }

        /* renamed from: c */
        public void m1574c(C1398g c1398g) {
            AbstractC1400i.f4084i.mo2351a("destroyEndPoint {}", c1398g);
            this.f4107k.remove(c1398g);
            Objects.requireNonNull(AbstractC1400i.this);
        }

        /* JADX WARN: Code restructure failed: missing block: B:62:0x00d8, code lost:
        
            r2 = r1.selectNow();
            r5 = java.lang.System.currentTimeMillis();
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x00e1, code lost:
        
            if (r2 != 0) goto L102;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00eb, code lost:
        
            if (r1.selectedKeys().isEmpty() == false) goto L102;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x00ef, code lost:
        
            if (r14.f4104h == false) goto L79;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00f1, code lost:
        
            java.lang.Thread.sleep(p089k5.AbstractC1400i.f4087l);
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x0101, code lost:
        
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x0102, code lost:
        
            p089k5.AbstractC1400i.f4084i.mo2360k(r2);
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:110:0x0182 A[Catch: all -> 0x00f8, CancelledKeyException -> 0x00fb, ClosedSelectorException -> 0x00fe, TRY_LEAVE, TryCatch #16 {CancelledKeyException -> 0x00fb, ClosedSelectorException -> 0x00fe, blocks: (B:3:0x0001, B:7:0x000e, B:8:0x0014, B:10:0x0019, B:50:0x00af, B:52:0x00b7, B:55:0x00c4, B:58:0x00c9, B:53:0x00bd, B:60:0x00d0, B:62:0x00d8, B:64:0x00e3, B:66:0x00ed, B:68:0x00f1, B:78:0x0107, B:79:0x010b, B:81:0x011f, B:93:0x0136, B:95:0x0145, B:97:0x014c, B:99:0x0155, B:101:0x015b, B:77:0x0102, B:102:0x0168, B:104:0x016c, B:107:0x0174, B:108:0x017c, B:110:0x0182, B:148:0x020c, B:150:0x0214, B:153:0x0221, B:158:0x022d, B:160:0x0235, B:162:0x023b, B:156:0x0226, B:151:0x021a, B:164:0x0241, B:165:0x0248, B:167:0x025f, B:169:0x0263, B:170:0x0272, B:171:0x0279, B:173:0x0284, B:174:0x029d, B:176:0x02a1, B:178:0x02a7), top: B:212:0x0001, outer: #12 }] */
        /* JADX WARN: Removed duplicated region for block: B:167:0x025f A[Catch: all -> 0x00f8, CancelledKeyException -> 0x00fb, ClosedSelectorException -> 0x00fe, TryCatch #16 {CancelledKeyException -> 0x00fb, ClosedSelectorException -> 0x00fe, blocks: (B:3:0x0001, B:7:0x000e, B:8:0x0014, B:10:0x0019, B:50:0x00af, B:52:0x00b7, B:55:0x00c4, B:58:0x00c9, B:53:0x00bd, B:60:0x00d0, B:62:0x00d8, B:64:0x00e3, B:66:0x00ed, B:68:0x00f1, B:78:0x0107, B:79:0x010b, B:81:0x011f, B:93:0x0136, B:95:0x0145, B:97:0x014c, B:99:0x0155, B:101:0x015b, B:77:0x0102, B:102:0x0168, B:104:0x016c, B:107:0x0174, B:108:0x017c, B:110:0x0182, B:148:0x020c, B:150:0x0214, B:153:0x0221, B:158:0x022d, B:160:0x0235, B:162:0x023b, B:156:0x0226, B:151:0x021a, B:164:0x0241, B:165:0x0248, B:167:0x025f, B:169:0x0263, B:170:0x0272, B:171:0x0279, B:173:0x0284, B:174:0x029d, B:176:0x02a1, B:178:0x02a7), top: B:212:0x0001, outer: #12 }] */
        /* JADX WARN: Removed duplicated region for block: B:173:0x0284 A[Catch: all -> 0x00f8, CancelledKeyException -> 0x00fb, ClosedSelectorException -> 0x00fe, TryCatch #16 {CancelledKeyException -> 0x00fb, ClosedSelectorException -> 0x00fe, blocks: (B:3:0x0001, B:7:0x000e, B:8:0x0014, B:10:0x0019, B:50:0x00af, B:52:0x00b7, B:55:0x00c4, B:58:0x00c9, B:53:0x00bd, B:60:0x00d0, B:62:0x00d8, B:64:0x00e3, B:66:0x00ed, B:68:0x00f1, B:78:0x0107, B:79:0x010b, B:81:0x011f, B:93:0x0136, B:95:0x0145, B:97:0x014c, B:99:0x0155, B:101:0x015b, B:77:0x0102, B:102:0x0168, B:104:0x016c, B:107:0x0174, B:108:0x017c, B:110:0x0182, B:148:0x020c, B:150:0x0214, B:153:0x0221, B:158:0x022d, B:160:0x0235, B:162:0x023b, B:156:0x0226, B:151:0x021a, B:164:0x0241, B:165:0x0248, B:167:0x025f, B:169:0x0263, B:170:0x0272, B:171:0x0279, B:173:0x0284, B:174:0x029d, B:176:0x02a1, B:178:0x02a7), top: B:212:0x0001, outer: #12 }] */
        /* JADX WARN: Removed duplicated region for block: B:206:0x00c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:222:0x00d5 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00b7 A[Catch: all -> 0x00f8, CancelledKeyException -> 0x00fb, ClosedSelectorException -> 0x00fe, TryCatch #16 {CancelledKeyException -> 0x00fb, ClosedSelectorException -> 0x00fe, blocks: (B:3:0x0001, B:7:0x000e, B:8:0x0014, B:10:0x0019, B:50:0x00af, B:52:0x00b7, B:55:0x00c4, B:58:0x00c9, B:53:0x00bd, B:60:0x00d0, B:62:0x00d8, B:64:0x00e3, B:66:0x00ed, B:68:0x00f1, B:78:0x0107, B:79:0x010b, B:81:0x011f, B:93:0x0136, B:95:0x0145, B:97:0x014c, B:99:0x0155, B:101:0x015b, B:77:0x0102, B:102:0x0168, B:104:0x016c, B:107:0x0174, B:108:0x017c, B:110:0x0182, B:148:0x020c, B:150:0x0214, B:153:0x0221, B:158:0x022d, B:160:0x0235, B:162:0x023b, B:156:0x0226, B:151:0x021a, B:164:0x0241, B:165:0x0248, B:167:0x025f, B:169:0x0263, B:170:0x0272, B:171:0x0279, B:173:0x0284, B:174:0x029d, B:176:0x02a1, B:178:0x02a7), top: B:212:0x0001, outer: #12 }] */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00bd A[Catch: all -> 0x00f8, CancelledKeyException -> 0x00fb, ClosedSelectorException -> 0x00fe, TRY_LEAVE, TryCatch #16 {CancelledKeyException -> 0x00fb, ClosedSelectorException -> 0x00fe, blocks: (B:3:0x0001, B:7:0x000e, B:8:0x0014, B:10:0x0019, B:50:0x00af, B:52:0x00b7, B:55:0x00c4, B:58:0x00c9, B:53:0x00bd, B:60:0x00d0, B:62:0x00d8, B:64:0x00e3, B:66:0x00ed, B:68:0x00f1, B:78:0x0107, B:79:0x010b, B:81:0x011f, B:93:0x0136, B:95:0x0145, B:97:0x014c, B:99:0x0155, B:101:0x015b, B:77:0x0102, B:102:0x0168, B:104:0x016c, B:107:0x0174, B:108:0x017c, B:110:0x0182, B:148:0x020c, B:150:0x0214, B:153:0x0221, B:158:0x022d, B:160:0x0235, B:162:0x023b, B:156:0x0226, B:151:0x021a, B:164:0x0241, B:165:0x0248, B:167:0x025f, B:169:0x0263, B:170:0x0272, B:171:0x0279, B:173:0x0284, B:174:0x029d, B:176:0x02a1, B:178:0x02a7), top: B:212:0x0001, outer: #12 }] */
        /* renamed from: d */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void m1575d() {
            /*
                Method dump skipped, instructions count: 722
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p089k5.AbstractC1400i.d.m1575d():void");
        }

        /* renamed from: e */
        public final void m1576e() {
            try {
                synchronized (this) {
                    Selector selector = this.f4100d;
                    if (selector == null) {
                        return;
                    }
                    Selector selectorOpen = Selector.open();
                    for (SelectionKey selectionKey : selector.keys()) {
                        if (selectionKey.isValid() && selectionKey.interestOps() != 0) {
                            SelectableChannel selectableChannelChannel = selectionKey.channel();
                            Object objAttachment = selectionKey.attachment();
                            if (objAttachment == null) {
                                this.f4099c.add(selectableChannelChannel);
                            } else {
                                m1572a(selectableChannelChannel, objAttachment);
                            }
                        }
                    }
                    this.f4100d.close();
                    this.f4100d = selectorOpen;
                }
            } catch (IOException e7) {
                throw new RuntimeException("recreating selector", e7);
            }
        }

        /* renamed from: f */
        public void m1577f() {
            try {
                Selector selector = this.f4100d;
                if (selector != null) {
                    selector.wakeup();
                }
            } catch (Exception unused) {
                this.f4099c.add(new b());
                m1576e();
            }
        }

        public String toString() {
            Selector selector = this.f4100d;
            Object[] objArr = new Object[3];
            objArr[0] = super.toString();
            int size = -1;
            objArr[1] = Integer.valueOf((selector == null || !selector.isOpen()) ? -1 : selector.keys().size());
            if (selector != null && selector.isOpen()) {
                size = selector.selectedKeys().size();
            }
            objArr[2] = Integer.valueOf(size);
            return String.format("%s keys=%d selected=%d", objArr);
        }
    }

    /* renamed from: G */
    public abstract void mo907G(SocketChannel socketChannel, Throwable th, Object obj);

    /* renamed from: H */
    public void m1571H(SocketChannel socketChannel, Object obj) {
        int i7 = this.f4091g;
        this.f4091g = i7 + 1;
        if (i7 < 0) {
            i7 = -i7;
        }
        int i8 = i7 % this.f4090f;
        d[] dVarArr = this.f4089e;
        if (dVarArr != null) {
            d dVar = dVarArr[i8];
            dVar.m1572a(socketChannel, obj);
            dVar.m1577f();
        }
    }

    public abstract boolean dispatch(Runnable runnable);

    @Override // p168u5.AbstractC1980a
    public void doStart() {
        this.f4089e = new d[this.f4090f];
        int i7 = 0;
        while (true) {
            d[] dVarArr = this.f4089e;
            if (i7 >= dVarArr.length) {
                break;
            }
            dVarArr[i7] = new d(i7);
            i7++;
        }
        super.doStart();
        for (int i8 = 0; i8 < this.f4090f; i8++) {
            if (!C0966k.this.f1782h.f1747n.dispatch(new a(i8))) {
                throw new IllegalStateException("!Selecting");
            }
        }
    }

    @Override // p168u5.AbstractC1980a
    public void doStop() throws InterruptedException {
        d[] dVarArr = this.f4089e;
        this.f4089e = null;
        if (dVarArr != null) {
            for (d dVar : dVarArr) {
                if (dVar != null) {
                    for (int i7 = 0; i7 < 100; i7++) {
                        try {
                            if (dVar.f4101e == null) {
                                break;
                            }
                            dVar.m1577f();
                            Thread.sleep(10L);
                        } catch (Exception e7) {
                            f4084i.mo2360k(e7);
                        }
                    }
                    synchronized (dVar) {
                        for (SelectionKey selectionKey : dVar.f4100d.keys()) {
                            if (selectionKey != null) {
                                Object objAttachment = selectionKey.attachment();
                                if (objAttachment instanceof InterfaceC1161n) {
                                    try {
                                        ((InterfaceC1161n) objAttachment).close();
                                    } catch (IOException e8) {
                                        f4084i.mo2360k(e8);
                                    }
                                }
                            }
                        }
                        dVar.f4098b.m50a();
                        try {
                            Selector selector = dVar.f4100d;
                            if (selector != null) {
                                selector.close();
                            }
                        } catch (IOException e9) {
                            f4084i.mo2360k(e9);
                        }
                        dVar.f4100d = null;
                    }
                }
            }
        }
        super.doStop();
    }
}
