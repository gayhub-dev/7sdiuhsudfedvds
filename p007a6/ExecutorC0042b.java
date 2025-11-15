package p007a6;

import android.support.constraint.solver.C0084a;
import com.ctvit.network.CtvitHttp;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;
import p161t5.BlockingQueueC1912d;
import p161t5.C1915g;
import p168u5.AbstractC1980a;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: QueuedThreadPool.java */
/* renamed from: a6.b */
/* loaded from: classes.dex */
public class ExecutorC0042b extends AbstractC1980a implements InterfaceC0044d, Executor {

    /* renamed from: t */
    public static final InterfaceC2016c f38t;

    /* renamed from: j */
    public BlockingQueue<Runnable> f44j;

    /* renamed from: k */
    public String f45k;

    /* renamed from: e */
    public final AtomicInteger f39e = new AtomicInteger();

    /* renamed from: f */
    public final AtomicInteger f40f = new AtomicInteger();

    /* renamed from: g */
    public final AtomicLong f41g = new AtomicLong();

    /* renamed from: h */
    public final C1915g<Thread> f42h = new C1915g<>();

    /* renamed from: i */
    public final Object f43i = new Object();

    /* renamed from: l */
    public int f46l = CtvitHttp.DEFAULT_MILLISECONDS;

    /* renamed from: m */
    public int f47m = 254;

    /* renamed from: n */
    public int f48n = 8;

    /* renamed from: o */
    public int f49o = -1;

    /* renamed from: p */
    public int f50p = 5;

    /* renamed from: q */
    public boolean f51q = false;

    /* renamed from: r */
    public int f52r = 100;

    /* renamed from: s */
    public Runnable f53s = new b();

    /* compiled from: QueuedThreadPool.java */
    /* renamed from: a6.b$a */
    public class a implements Runnable {
        public a(ExecutorC0042b executorC0042b) {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    /* compiled from: QueuedThreadPool.java */
    /* renamed from: a6.b$b */
    public class b implements Runnable {
        public b() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x009a, code lost:
        
            if (r1 != false) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x009c, code lost:
        
            r13.f54e.f39e.decrementAndGet();
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00dd, code lost:
        
            if (r2 == false) goto L69;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:74:0x010d  */
        /* JADX WARN: Type inference failed for: r1v16, types: [java.lang.Runnable] */
        /* JADX WARN: Type inference failed for: r1v20, types: [java.lang.Runnable] */
        /* JADX WARN: Type inference failed for: r1v23 */
        /* JADX WARN: Type inference failed for: r1v27, types: [java.lang.Runnable] */
        /* JADX WARN: Type inference failed for: r1v28 */
        /* JADX WARN: Type inference failed for: r1v35, types: [java.lang.Runnable] */
        /* JADX WARN: Type inference failed for: r1v49 */
        /* JADX WARN: Type inference failed for: r1v50 */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 288
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p007a6.ExecutorC0042b.b.run():void");
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f38t = C2015b.m2349a(ExecutorC0042b.class.getName());
    }

    public ExecutorC0042b() {
        StringBuilder sbM112a = C0413b.m112a("qtp");
        sbM112a.append(super.hashCode());
        this.f45k = sbM112a.toString();
    }

    /* renamed from: G */
    public final boolean m49G(int i7) {
        if (!this.f39e.compareAndSet(i7, i7 + 1)) {
            return false;
        }
        try {
            Thread thread = new Thread(this.f53s);
            thread.setDaemon(this.f51q);
            thread.setPriority(this.f50p);
            thread.setName(this.f45k + "-" + thread.getId());
            this.f42h.add(thread);
            thread.start();
            return true;
        } catch (Throwable th) {
            this.f39e.decrementAndGet();
            throw th;
        }
    }

    @Override // p007a6.InterfaceC0044d
    public boolean dispatch(Runnable runnable) {
        int i7;
        if (isRunning()) {
            int size = this.f44j.size();
            int i8 = this.f40f.get();
            if (this.f44j.offer(runnable)) {
                if ((i8 == 0 || size > i8) && (i7 = this.f39e.get()) < this.f47m) {
                    m49G(i7);
                }
                return true;
            }
        }
        f38t.mo2351a("Dispatched {} to stopped {}", runnable, this);
        return false;
    }

    @Override // p168u5.AbstractC1980a
    public void doStart() {
        BlockingQueue<Runnable> blockingQueueC1912d;
        super.doStart();
        this.f39e.set(0);
        if (this.f44j == null) {
            if (this.f49o > 0) {
                blockingQueueC1912d = new ArrayBlockingQueue<>(this.f49o);
            } else {
                int i7 = this.f48n;
                blockingQueueC1912d = new BlockingQueueC1912d<>(i7, i7);
            }
            this.f44j = blockingQueueC1912d;
        }
        int i8 = this.f39e.get();
        while (isRunning() && i8 < this.f48n) {
            m49G(i8);
            i8 = this.f39e.get();
        }
    }

    @Override // p168u5.AbstractC1980a
    public void doStop() throws InterruptedException {
        super.doStop();
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (this.f39e.get() > 0 && System.currentTimeMillis() - jCurrentTimeMillis < this.f52r / 2) {
            Thread.sleep(1L);
        }
        this.f44j.clear();
        a aVar = new a(this);
        int i7 = this.f40f.get();
        while (true) {
            int i8 = i7 - 1;
            if (i7 <= 0) {
                break;
            }
            this.f44j.offer(aVar);
            i7 = i8;
        }
        Thread.yield();
        if (this.f39e.get() > 0) {
            Iterator<Thread> it = this.f42h.iterator();
            while (it.hasNext()) {
                it.next().interrupt();
            }
        }
        while (this.f39e.get() > 0 && System.currentTimeMillis() - jCurrentTimeMillis < this.f52r) {
            Thread.sleep(1L);
        }
        Thread.yield();
        int size = this.f42h.size();
        if (size > 0) {
            InterfaceC2016c interfaceC2016c = f38t;
            interfaceC2016c.mo2356g(size + " threads could not be stopped", new Object[0]);
            if (size == 1 || interfaceC2016c.mo2353d()) {
                Iterator<Thread> it2 = this.f42h.iterator();
                while (it2.hasNext()) {
                    Thread next = it2.next();
                    f38t.mo2357h("Couldn't stop " + next, new Object[0]);
                    for (StackTraceElement stackTraceElement : next.getStackTrace()) {
                        f38t.mo2357h(" at " + stackTraceElement, new Object[0]);
                    }
                }
            }
        }
        synchronized (this.f43i) {
            this.f43i.notifyAll();
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (!dispatch(runnable)) {
            throw new RejectedExecutionException();
        }
    }

    @Override // p007a6.InterfaceC0044d
    public boolean isLowOnThreads() {
        return this.f39e.get() == this.f47m && this.f44j.size() >= this.f40f.get();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f45k);
        sb.append("{");
        sb.append(this.f48n);
        sb.append("<=");
        sb.append(this.f40f.get());
        sb.append("<=");
        sb.append(this.f39e.get());
        sb.append(ServiceReference.DELIMITER);
        sb.append(this.f47m);
        sb.append(",");
        BlockingQueue<Runnable> blockingQueue = this.f44j;
        return C0084a.m96a(sb, blockingQueue == null ? -1 : blockingQueue.size(), "}");
    }
}
