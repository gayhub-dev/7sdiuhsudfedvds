package p140q6;

import com.ctvit.network.model.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import p140q6.C1797r;
import p159t3.AbstractC1902a;
import p159t3.AbstractC1905d;
import p159t3.AbstractC1907f;
import p159t3.C1903b;
import p159t3.InterfaceC1906e;
import p186x2.C2074b;

/* compiled from: ServerImpl.java */
/* renamed from: q6.v */
/* loaded from: classes.dex */
public class C1801v {

    /* renamed from: v */
    public static final int f5172v = C1800u.f5164a;

    /* renamed from: w */
    public static final long f5173w = C1800u.f5167d;

    /* renamed from: x */
    public static final int f5174x = C1800u.f5170g;

    /* renamed from: y */
    public static boolean f5175y = C1800u.f5171h;

    /* renamed from: a */
    public String f5176a;

    /* renamed from: b */
    public boolean f5177b;

    /* renamed from: c */
    public Executor f5178c;

    /* renamed from: e */
    public ServerSocketChannel f5180e;

    /* renamed from: f */
    public Selector f5181f;

    /* renamed from: g */
    public SelectionKey f5182g;

    /* renamed from: h */
    public Set<C1789j> f5183h;

    /* renamed from: i */
    public Set<C1789j> f5184i;

    /* renamed from: j */
    public List<C1785f> f5185j;

    /* renamed from: n */
    public boolean f5189n;

    /* renamed from: p */
    public volatile long f5191p;

    /* renamed from: q */
    public volatile long f5192q;

    /* renamed from: r */
    public Timer f5193r;

    /* renamed from: t */
    public c f5195t;

    /* renamed from: k */
    public Object f5186k = new Object();

    /* renamed from: l */
    public volatile boolean f5187l = false;

    /* renamed from: m */
    public volatile boolean f5188m = false;

    /* renamed from: o */
    public boolean f5190o = false;

    /* renamed from: u */
    public int f5196u = 0;

    /* renamed from: s */
    public Logger f5194s = Logger.getLogger("com.sun.net.httpserver");

    /* renamed from: d */
    public C1783d f5179d = new C1783d();

    /* compiled from: ServerImpl.java */
    /* renamed from: q6.v$b */
    public static class b implements Executor {
        public b(a aVar) {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            runnable.run();
        }
    }

    /* compiled from: ServerImpl.java */
    /* renamed from: q6.v$c */
    public class c implements Runnable {
        public c() {
        }

        /* renamed from: a */
        public void m2009a(SocketChannel socketChannel, C1789j c1789j) {
            try {
                C1801v c1801v = C1801v.this;
                c1801v.f5178c.execute(c1801v.new d(socketChannel, c1801v.f5176a, c1789j));
            } catch (IOException e7) {
                C1801v.this.f5194s.log(Level.FINER, "Dispatcher (6)", (Throwable) e7);
                c1789j.m1983a();
            } catch (C1791l e8) {
                C1801v.this.f5194s.log(Level.FINER, "Dispatcher (5)", (Throwable) e8);
                c1789j.m1983a();
            }
        }

        /* renamed from: b */
        public final void m2010b(C1785f c1785f) {
            int i7;
            C1786g c1786g = c1785f.f5076a;
            C1789j c1789j = c1786g.f5084f;
            try {
                if (c1785f instanceof C1805z) {
                    C1801v c1801v = C1801v.this;
                    synchronized (c1801v) {
                        i7 = c1801v.f5196u - 1;
                        c1801v.f5196u = i7;
                    }
                    if (C1801v.this.f5188m && i7 == 0) {
                        C1801v.this.f5187l = true;
                    }
                    Objects.requireNonNull(c1789j);
                    AbstractC1795p abstractC1795p = c1786g.f5093o;
                    if (!abstractC1795p.f5122f) {
                        c1786g.f5088j = true;
                    }
                    if (!c1786g.f5088j && C1801v.this.f5183h.size() < C1801v.f5174x) {
                        if (abstractC1795p.mo1970a()) {
                            m2009a(c1789j.f5107f, c1789j);
                            return;
                        }
                        SelectionKey selectionKey = c1789j.f5108g;
                        if (selectionKey.isValid()) {
                            selectionKey.interestOps(selectionKey.interestOps() | 1);
                        }
                        c1789j.f5109h = C1801v.this.f5191p + C1801v.f5173w;
                        C1801v.this.f5183h.add(c1789j);
                        return;
                    }
                    c1789j.m1983a();
                    C1801v.this.f5184i.remove(c1789j);
                }
            } catch (IOException e7) {
                C1801v.this.f5194s.log(Level.FINER, "Dispatcher (1)", (Throwable) e7);
                c1789j.m1983a();
            }
        }

        /* renamed from: c */
        public void m2011c() throws IOException {
            int size;
            SocketChannel socketChannelAccept;
            while (!C1801v.this.f5187l) {
                while (true) {
                    try {
                        try {
                            C1801v c1801v = C1801v.this;
                            synchronized (c1801v.f5186k) {
                                size = c1801v.f5185j.size();
                            }
                            if (size <= 0) {
                                break;
                            }
                            synchronized (C1801v.this.f5186k) {
                                m2010b(C1801v.this.f5185j.remove(0));
                            }
                        } catch (IOException e7) {
                            C1801v.this.f5194s.log(Level.FINER, "Dispatcher (4)", (Throwable) e7);
                        }
                    } catch (CancelledKeyException e8) {
                        C1801v.this.f5194s.log(Level.FINER, "Dispatcher (3)", (Throwable) e8);
                    }
                }
                C1801v.this.f5181f.select(1000L);
                Iterator<SelectionKey> it = C1801v.this.f5181f.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey next = it.next();
                    it.remove();
                    if (!next.equals(C1801v.this.f5182g)) {
                        try {
                            if (next.isReadable()) {
                                SocketChannel socketChannel = (SocketChannel) next.channel();
                                C1789j c1789j = (C1789j) next.attachment();
                                next.interestOps(0);
                                m2009a(socketChannel, c1789j);
                            }
                        } catch (IOException e9) {
                            C1789j c1789j2 = (C1789j) next.attachment();
                            C1801v.this.f5194s.log(Level.FINER, "Dispatcher (2)", (Throwable) e9);
                            c1789j2.m1983a();
                        }
                    } else if (!C1801v.this.f5188m && (socketChannelAccept = C1801v.this.f5180e.accept()) != null) {
                        socketChannelAccept.configureBlocking(false);
                        SelectionKey selectionKeyRegister = socketChannelAccept.register(C1801v.this.f5181f, 1);
                        C1789j c1789j3 = new C1789j();
                        c1789j3.f5108g = selectionKeyRegister;
                        c1789j3.f5107f = socketChannelAccept;
                        selectionKeyRegister.attach(c1789j3);
                        C1801v.this.f5184i.add(c1789j3);
                    }
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                m2011c();
            } catch (Exception e7) {
                C1801v.this.f5194s.log(Level.FINE, "Dispatcher (7)", (Throwable) e7);
            }
        }
    }

    /* compiled from: ServerImpl.java */
    /* renamed from: q6.v$d */
    public class d implements Runnable {

        /* renamed from: e */
        public SocketChannel f5198e;

        /* renamed from: f */
        public C1789j f5199f;

        /* renamed from: g */
        public InputStream f5200g;

        /* renamed from: h */
        public OutputStream f5201h;

        /* renamed from: i */
        public String f5202i;

        /* renamed from: j */
        public C1786g f5203j;

        /* renamed from: k */
        public C1790k f5204k;

        /* compiled from: ServerImpl.java */
        /* renamed from: q6.v$d$a */
        public class a implements InterfaceC1906e {

            /* renamed from: a */
            public AbstractC1902a.a f5206a;

            public a(d dVar, AbstractC1902a.a aVar) {
                this.f5206a = aVar;
            }

            @Override // p159t3.InterfaceC1906e
            public void handle(AbstractC1905d abstractC1905d) {
                this.f5206a.m2201a(abstractC1905d);
            }
        }

        public d(SocketChannel socketChannel, String str, C1789j c1789j) {
            this.f5198e = socketChannel;
            this.f5199f = c1789j;
            this.f5202i = str;
        }

        /* renamed from: a */
        public void m2012a(int i7, String str, String str2) throws IOException {
            C1801v.this.m2008c(i7, str, str2);
            m2013b(i7, true, "<h1>" + i7 + C2074b.m2499v(i7) + "</h1>" + str2);
        }

        /* renamed from: b */
        public void m2013b(int i7, boolean z6, String str) throws IOException {
            String str2;
            try {
                String str3 = "HTTP/1.1 " + i7 + C2074b.m2499v(i7) + "\r\n";
                if (str == null || str.length() == 0) {
                    str2 = str3 + "Content-Length: 0\r\n";
                    str = "";
                } else {
                    str2 = (str3 + "Content-Length: " + str.length() + "\r\n") + "Content-Type: text/html\r\n";
                }
                if (z6) {
                    str2 = str2 + "Connection: close\r\n";
                }
                this.f5201h.write((str2 + "\r\n" + str).getBytes("ISO8859_1"));
                this.f5201h.flush();
                if (z6) {
                    this.f5199f.m1983a();
                }
            } catch (IOException e7) {
                C1801v.this.f5194s.log(Level.FINER, "ServerImpl.sendReply", (Throwable) e7);
                this.f5199f.m1983a();
            }
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            boolean z6;
            String str;
            int i7;
            C1903b c1903b;
            C1789j c1789j = this.f5199f;
            String str2 = null;
            try {
                try {
                    if (c1789j.f5102a != null) {
                        this.f5200g = c1789j.f5104c;
                        this.f5201h = c1789j.f5106e;
                        z6 = false;
                    } else {
                        C1801v c1801v = C1801v.this;
                        if (c1801v.f5177b) {
                            Objects.requireNonNull(c1801v);
                            C1801v.this.f5194s.warning("SSL connection received. No https contxt created");
                            throw new C1791l("No SSL context established");
                        }
                        this.f5200g = new BufferedInputStream(new C1797r.a(C1801v.this, this.f5198e));
                        this.f5201h = new C1797r.b(C1801v.this, this.f5198e);
                        z6 = true;
                    }
                    C1797r c1797r = new C1797r(this.f5200g, this.f5201h);
                    String str3 = c1797r.f5125a;
                    try {
                        if (str3 == null) {
                            this.f5199f.m1983a();
                            return;
                        }
                        int iIndexOf = str3.indexOf(32);
                        if (iIndexOf == -1) {
                            m2012a(400, str3, "Bad request line");
                            return;
                        }
                        String strSubstring = str3.substring(0, iIndexOf);
                        int i8 = iIndexOf + 1;
                        int iIndexOf2 = str3.indexOf(32, i8);
                        if (iIndexOf2 == -1) {
                            m2012a(400, str3, "Bad request line");
                            return;
                        }
                        URI uri = new URI(str3.substring(i8, iIndexOf2));
                        String strSubstring2 = str3.substring(iIndexOf2 + 1);
                        C1903b c1903bM2000b = c1797r.m2000b();
                        String strMo2016c = c1903bM2000b.mo2016c("Transfer-encoding");
                        if (strMo2016c == null || !strMo2016c.equalsIgnoreCase("chunked")) {
                            String strMo2016c2 = c1903bM2000b.mo2016c("Content-Length");
                            i7 = strMo2016c2 != null ? Integer.parseInt(strMo2016c2) : 0;
                        } else {
                            i7 = -1;
                        }
                        C1790k c1790kM1974a = C1801v.this.f5179d.m1974a(this.f5202i, uri.getPath());
                        this.f5204k = c1790kM1974a;
                        if (c1790kM1974a == null) {
                            m2012a(404, str3, "No context found for request");
                            return;
                        }
                        this.f5199f.f5102a = c1790kM1974a;
                        if (c1790kM1974a.f5114c == null) {
                            m2012a(500, str3, "No handler for context");
                            return;
                        }
                        this.f5203j = new C1786g(strSubstring, uri, c1797r, i7, this.f5199f);
                        String strMo2016c3 = c1903bM2000b.mo2016c(HttpHeaders.HEAD_KEY_CONNECTION);
                        C1903b c1903b2 = this.f5203j.f5080b;
                        if (strMo2016c3 != null && strMo2016c3.equalsIgnoreCase(HttpHeaders.HEAD_VALUE_CONNECTION_CLOSE)) {
                            this.f5203j.f5088j = true;
                        }
                        if (strSubstring2.equalsIgnoreCase("http/1.0")) {
                            C1786g c1786g = this.f5203j;
                            c1786g.f5090l = true;
                            if (strMo2016c3 == null) {
                                c1786g.f5088j = true;
                                c1903b2.mo2019g(HttpHeaders.HEAD_KEY_CONNECTION, HttpHeaders.HEAD_VALUE_CONNECTION_CLOSE);
                            } else if (strMo2016c3.equalsIgnoreCase(HttpHeaders.HEAD_VALUE_CONNECTION_KEEP_ALIVE)) {
                                c1903b2.mo2019g(HttpHeaders.HEAD_KEY_CONNECTION, HttpHeaders.HEAD_VALUE_CONNECTION_KEEP_ALIVE);
                                c1903b2.mo2019g("Keep-Alive", "timeout=" + (((int) C1800u.f5167d) / 1000) + ", max=" + C1800u.f5170g);
                            }
                        }
                        if (z6) {
                            C1789j c1789j2 = this.f5199f;
                            InputStream inputStream = this.f5200g;
                            OutputStream outputStream = this.f5201h;
                            SocketChannel socketChannel = this.f5198e;
                            Objects.requireNonNull(C1801v.this);
                            c1903b = c1903bM2000b;
                            str = str3;
                            try {
                                c1789j2.m1984b(inputStream, outputStream, socketChannel, null, null, null, this.f5202i, this.f5204k, this.f5200g);
                            } catch (NumberFormatException unused) {
                                str2 = str;
                                m2012a(400, str2, "NumberFormatException thrown");
                                return;
                            } catch (URISyntaxException unused2) {
                                str2 = str;
                                m2012a(400, str2, "URISyntaxException thrown");
                                return;
                            }
                        } else {
                            c1903b = c1903bM2000b;
                            str = str3;
                        }
                        String strMo2016c4 = c1903b.mo2016c("Expect");
                        if (strMo2016c4 != null && strMo2016c4.equalsIgnoreCase("100-continue")) {
                            C1801v.this.m2008c(100, str, null);
                            m2013b(100, false, null);
                        }
                        C1790k c1790k = this.f5204k;
                        AbstractC1902a.a aVar = new AbstractC1902a.a(c1790k.f5117f, new a(this, new AbstractC1902a.a(c1790k.f5116e, c1790k.f5114c)));
                        this.f5203j.m1980d();
                        this.f5203j.m1981e();
                        if (C1801v.this.f5177b) {
                            aVar.m2201a(new C1794o(this.f5203j));
                        } else {
                            aVar.m2201a(new C1792m(this.f5203j));
                        }
                    } catch (NumberFormatException unused3) {
                        str = str3;
                    } catch (URISyntaxException unused4) {
                        str = str3;
                    }
                } catch (NumberFormatException unused5) {
                } catch (URISyntaxException unused6) {
                }
            } catch (IOException e7) {
                C1801v.this.f5194s.log(Level.FINER, "ServerImpl.Exchange (1)", (Throwable) e7);
                this.f5199f.m1983a();
            } catch (Exception e8) {
                C1801v.this.f5194s.log(Level.FINER, "ServerImpl.Exchange (2)", (Throwable) e8);
                this.f5199f.m1983a();
            }
        }
    }

    /* compiled from: ServerImpl.java */
    /* renamed from: q6.v$e */
    public class e extends TimerTask {
        public e() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            LinkedList linkedList = new LinkedList();
            C1801v.this.f5191p = System.currentTimeMillis();
            C1801v.this.f5192q++;
            synchronized (C1801v.this.f5183h) {
                for (C1789j c1789j : C1801v.this.f5183h) {
                    if (c1789j.f5109h <= C1801v.this.f5191p) {
                        linkedList.add(c1789j);
                    }
                }
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    C1789j c1789j2 = (C1789j) it.next();
                    C1801v.this.f5183h.remove(c1789j2);
                    C1801v.this.f5184i.remove(c1789j2);
                    c1789j2.m1983a();
                }
            }
        }
    }

    public C1801v(AbstractC1907f abstractC1907f, String str, InetSocketAddress inetSocketAddress, int i7) throws IOException {
        this.f5189n = false;
        this.f5176a = str;
        this.f5177b = str.equalsIgnoreCase("https");
        ServerSocketChannel serverSocketChannelOpen = ServerSocketChannel.open();
        this.f5180e = serverSocketChannelOpen;
        if (inetSocketAddress != null) {
            serverSocketChannelOpen.socket().bind(inetSocketAddress, i7);
            this.f5189n = true;
        }
        this.f5181f = Selector.open();
        this.f5180e.configureBlocking(false);
        this.f5182g = this.f5180e.register(this.f5181f, 16);
        this.f5195t = new c();
        this.f5183h = Collections.synchronizedSet(new HashSet());
        this.f5184i = Collections.synchronizedSet(new HashSet());
        this.f5191p = System.currentTimeMillis();
        Timer timer = new Timer("server-timer", true);
        this.f5193r = timer;
        e eVar = new e();
        long j7 = f5172v;
        timer.schedule(eVar, j7, j7);
        this.f5185j = new LinkedList();
        this.f5194s.config("HttpServer created " + str + " " + inetSocketAddress);
    }

    /* renamed from: b */
    public static synchronized void m2006b(Exception exc) {
        if (f5175y) {
            System.out.println(exc);
            exc.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m2007a(C1785f c1785f) {
        synchronized (this.f5186k) {
            this.f5185j.add(c1785f);
            this.f5181f.wakeup();
        }
    }

    /* renamed from: c */
    public void m2008c(int i7, String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        this.f5194s.fine(str + " [" + i7 + " " + C2074b.m2499v(i7) + "] (" + str2 + ")");
    }
}
