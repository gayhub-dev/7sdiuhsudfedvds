package p112n4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: SchedulerPoolFactory.java */
/* renamed from: n4.k */
/* loaded from: classes.dex */
public final class C1536k {

    /* renamed from: a */
    public static final boolean f4477a;

    /* renamed from: b */
    public static final int f4478b;

    /* renamed from: c */
    public static final AtomicReference<ScheduledExecutorService> f4479c = new AtomicReference<>();

    /* renamed from: d */
    public static final Map<ScheduledThreadPoolExecutor, Object> f4480d = new ConcurrentHashMap();

    /* compiled from: SchedulerPoolFactory.java */
    /* renamed from: n4.k$a */
    public static final class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            Iterator it = new ArrayList(((ConcurrentHashMap) C1536k.f4480d).keySet()).iterator();
            while (it.hasNext()) {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it.next();
                if (scheduledThreadPoolExecutor.isShutdown()) {
                    ((ConcurrentHashMap) C1536k.f4480d).remove(scheduledThreadPoolExecutor);
                } else {
                    scheduledThreadPoolExecutor.purge();
                }
            }
        }
    }

    static {
        int i7;
        Properties properties = System.getProperties();
        boolean z6 = properties.containsKey("rx2.purge-enabled") ? Boolean.parseBoolean(properties.getProperty("rx2.purge-enabled")) : true;
        if (z6 && properties.containsKey("rx2.purge-period-seconds")) {
            try {
                i7 = Integer.parseInt(properties.getProperty("rx2.purge-period-seconds"));
            } catch (NumberFormatException unused) {
            }
        } else {
            i7 = 1;
        }
        f4477a = z6;
        f4478b = i7;
        if (!z6) {
            return;
        }
        while (true) {
            AtomicReference<ScheduledExecutorService> atomicReference = f4479c;
            ScheduledExecutorService scheduledExecutorService = atomicReference.get();
            if (scheduledExecutorService != null) {
                return;
            }
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, new ThreadFactoryC1532g("RxSchedulerPurge"));
            if (atomicReference.compareAndSet(scheduledExecutorService, scheduledExecutorServiceNewScheduledThreadPool)) {
                a aVar = new a();
                long j7 = f4478b;
                scheduledExecutorServiceNewScheduledThreadPool.scheduleAtFixedRate(aVar, j7, j7, TimeUnit.SECONDS);
                return;
            }
            scheduledExecutorServiceNewScheduledThreadPool.shutdownNow();
        }
    }

    /* renamed from: a */
    public static ScheduledExecutorService m1716a(ThreadFactory threadFactory) {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (f4477a && (scheduledExecutorServiceNewScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            ((ConcurrentHashMap) f4480d).put((ScheduledThreadPoolExecutor) scheduledExecutorServiceNewScheduledThreadPool, scheduledExecutorServiceNewScheduledThreadPool);
        }
        return scheduledExecutorServiceNewScheduledThreadPool;
    }
}
