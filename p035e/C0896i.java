package p035e;

import android.os.Looper;
import android.view.MotionEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import p035e.C0901n;
import p043f.C0988e;
import p043f.C0989f;
import p051g.C1033c;
import p051g.C1035e;
import p051g.C1036f;
import p051g.C1041k;
import p075j.AbstractC1174b;
import p075j.C1179g;
import p075j.C1180h;
import p083k.InterfaceC1214a;
import p087k3.C1237f;
import p099m.C1451b;
import p115o.C1570g;

/* compiled from: MDPickerManager.java */
/* renamed from: e.i */
/* loaded from: classes.dex */
public class C0896i {

    /* renamed from: a */
    public boolean f1540a;

    /* renamed from: b */
    public C1451b f1541b;

    /* renamed from: c */
    public C1570g f1542c;

    /* renamed from: d */
    public C1180h f1543d;

    /* renamed from: e */
    public C0901n.e f1544e;

    /* renamed from: f */
    public C0901n.i f1545f;

    /* renamed from: g */
    public e f1546g = new e(null);

    /* renamed from: h */
    public h f1547h = new h(null);

    /* renamed from: i */
    public g f1548i = new g(null);

    /* renamed from: j */
    public f f1549j = new f(null);

    /* renamed from: k */
    public d f1550k = new d(null);

    /* renamed from: l */
    public final Object f1551l = new Object();

    /* renamed from: m */
    public C0901n.f f1552m = new a();

    /* renamed from: n */
    public AbstractC1174b f1553n = new b();

    /* compiled from: MDPickerManager.java */
    /* renamed from: e.i$a */
    public class a implements C0901n.f {
        public a() {
        }

        @Override // p035e.C0901n.f
        /* renamed from: a */
        public void mo805a(MotionEvent motionEvent) {
            g gVar = C0896i.this.f1548i;
            float x6 = motionEvent.getX();
            float y6 = motionEvent.getY();
            gVar.f1566e = x6;
            gVar.f1567f = y6;
            C0896i.this.f1548i.run();
        }

        @Override // p035e.C0901n.f
        /* renamed from: b */
        public void mo806b(MotionEvent motionEvent) {
        }
    }

    /* compiled from: MDPickerManager.java */
    /* renamed from: e.i$b */
    public class b extends C1179g {

        /* renamed from: c */
        public long f1555c;

        public b() {
        }

        @Override // p075j.AbstractC1174b
        /* renamed from: a */
        public void mo807a(int i7, int i8) {
            synchronized (C0896i.this.f1551l) {
                C0896i c0896i = C0896i.this;
                c0896i.f1550k.m809b(c0896i.f1542c.f4717j);
            }
            if (C0896i.this.f1540a) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.f1555c > 100) {
                    C0988e.f1823a.post(C0896i.this.f1549j);
                    this.f1555c = jCurrentTimeMillis;
                }
            }
        }
    }

    /* compiled from: MDPickerManager.java */
    /* renamed from: e.i$c */
    public static class c {

        /* renamed from: a */
        public C1451b f1557a;

        /* renamed from: b */
        public C1570g f1558b;

        /* renamed from: c */
        public C1180h f1559c;

        public c(a aVar) {
        }
    }

    /* compiled from: MDPickerManager.java */
    /* renamed from: e.i$d */
    public static class d {

        /* renamed from: a */
        public int f1560a;

        /* renamed from: b */
        public List<C1033c> f1561b = new LinkedList();

        public d(a aVar) {
        }

        /* renamed from: a */
        public C1033c m808a(int i7) {
            if (i7 < this.f1560a) {
                return this.f1561b.get(0);
            }
            return null;
        }

        /* renamed from: b */
        public void m809b(List<C0888a> list) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                throw new RuntimeException("snapshot must in gl thread!");
            }
            int size = list.size();
            this.f1560a = size;
            while (this.f1561b.size() < size) {
                this.f1561b.add(new C1033c());
            }
            for (int i7 = 0; i7 < list.size(); i7++) {
                C1033c c1033c = this.f1561b.get(i7);
                C0888a c0888a = list.get(i7);
                Objects.requireNonNull(c1033c);
                C0893f c0893f = c0888a.f1492k;
                c1033c.f1951c = c0893f.f1533j;
                c1033c.f1952d = c0893f.f1534k;
                System.arraycopy(c0888a.f1482a, 0, c1033c.f1949a, 0, 16);
                System.arraycopy(c0888a.f1483b, 0, c1033c.f1950b, 0, 16);
            }
        }
    }

    /* compiled from: MDPickerManager.java */
    /* renamed from: e.i$e */
    public class e {

        /* renamed from: a */
        public InterfaceC1214a f1562a;

        /* renamed from: b */
        public long f1563b;

        public e(a aVar) {
        }
    }

    /* compiled from: MDPickerManager.java */
    /* renamed from: e.i$f */
    public class f implements Runnable {
        public f(a aVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (C0896i.this.f1551l) {
                C0896i c0896i = C0896i.this;
                d dVar = c0896i.f1550k;
                Objects.requireNonNull(c0896i);
                C1033c c1033cM808a = dVar.m808a(0);
                if (c1033cM808a != null) {
                    c0896i.m804a(C0989f.m1002b(c1033cM808a.f1951c / 2.0f, c1033cM808a.f1952d / 2.0f, c1033cM808a), 1);
                }
            }
        }
    }

    /* compiled from: MDPickerManager.java */
    /* renamed from: e.i$g */
    public class g implements Runnable {

        /* renamed from: e */
        public float f1566e;

        /* renamed from: f */
        public float f1567f;

        public g(a aVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            C1033c c1033cM808a;
            C1033c c1033cM808a2;
            synchronized (C0896i.this.f1551l) {
                C0896i c0896i = C0896i.this;
                float f7 = this.f1566e;
                float f8 = this.f1567f;
                d dVar = c0896i.f1550k;
                int iM1636e = c0896i.f1541b.m1636e();
                if (iM1636e != 0 && (c1033cM808a = dVar.m808a(0)) != null) {
                    int i7 = (int) (f7 / ((int) c1033cM808a.f1951c));
                    if (i7 < iM1636e && (c1033cM808a2 = dVar.m808a(i7)) != null) {
                        c0896i.m804a(C0989f.m1002b(f7 - (r6 * i7), f8, c1033cM808a2), 2);
                    }
                }
            }
        }
    }

    /* compiled from: MDPickerManager.java */
    /* renamed from: e.i$h */
    public class h {
        public h(a aVar) {
        }
    }

    public C0896i(c cVar, a aVar) {
        this.f1541b = cVar.f1557a;
        this.f1542c = cVar.f1558b;
        this.f1543d = cVar.f1559c;
    }

    /* renamed from: a */
    public final InterfaceC1214a m804a(C1041k c1041k, int i7) {
        e eVar;
        if (c1041k == null) {
            return null;
        }
        C0989f.m1001a("hitTest must in main thread");
        List<AbstractC1174b> list = this.f1543d.f2594a;
        C1036f c1036f = C1036f.f1959b;
        Iterator<AbstractC1174b> it = list.iterator();
        InterfaceC1214a interfaceC1214a = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object obj = (AbstractC1174b) it.next();
            if (obj instanceof InterfaceC1214a) {
                InterfaceC1214a interfaceC1214a2 = (InterfaceC1214a) obj;
                C1036f c1036fM1439a = interfaceC1214a2.m1439a(c1041k);
                float f7 = c1036fM1439a.f1960a;
                if (!(f7 == Float.MAX_VALUE)) {
                    if (f7 <= c1036f.f1960a) {
                        interfaceC1214a = interfaceC1214a2;
                        c1036f = c1036fM1439a;
                    }
                }
            }
        }
        if (i7 == 1) {
            e eVar2 = this.f1546g;
            InterfaceC1214a interfaceC1214a3 = eVar2.f1562a;
            if (interfaceC1214a3 != interfaceC1214a) {
                if (interfaceC1214a3 != null) {
                    interfaceC1214a3.m1442d(eVar2.f1563b);
                }
                eVar2.f1563b = System.currentTimeMillis();
            }
            eVar2.f1562a = interfaceC1214a;
            Queue<C1035e> queue = C1035e.f1956c;
            C1035e c1035e = (C1035e) ((LinkedBlockingQueue) queue).poll();
            if (c1035e == null) {
                c1035e = new C1035e();
            }
            c1035e.f1957a = interfaceC1214a;
            c1035e.f1958b = eVar2.f1563b;
            InterfaceC1214a interfaceC1214a4 = eVar2.f1562a;
            if (interfaceC1214a4 != null) {
                interfaceC1214a4.m1441c(c1035e);
            }
            C0901n.e eVar3 = C0896i.this.f1544e;
            if (eVar3 != null) {
                C1237f c1237f = (C1237f) eVar3;
                if (System.currentTimeMillis() - c1035e.f1958b > 5000 && (eVar = c1237f.f2771a.f1599f.f1546g) != null) {
                    InterfaceC1214a interfaceC1214a5 = eVar.f1562a;
                    if (interfaceC1214a5 != null) {
                        interfaceC1214a5.m1442d(eVar.f1563b);
                        eVar.f1563b = System.currentTimeMillis();
                    }
                    eVar.f1562a = null;
                }
            }
            c1035e.f1958b = 0L;
            queue.add(c1035e);
        } else if (i7 == 2 && interfaceC1214a != null) {
            if (!(c1036f.f1960a == Float.MAX_VALUE)) {
                interfaceC1214a.m1440b(c1041k);
                h hVar = this.f1547h;
                if (C0896i.this.f1545f != null) {
                    Queue<C1035e> queue2 = C1035e.f1956c;
                    C1035e c1035e2 = (C1035e) ((LinkedBlockingQueue) queue2).poll();
                    if (c1035e2 == null) {
                        c1035e2 = new C1035e();
                    }
                    c1035e2.f1957a = interfaceC1214a;
                    c1035e2.f1958b = System.currentTimeMillis();
                    Objects.requireNonNull(C0896i.this.f1545f);
                    c1035e2.f1958b = 0L;
                    queue2.add(c1035e2);
                }
            }
        }
        return interfaceC1214a;
    }
}
