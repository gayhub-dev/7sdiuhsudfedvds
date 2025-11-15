package p007a6;

import android.support.v7.widget.RecyclerView;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import p168u5.AbstractC1980a;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: ExecutorThreadPool.java */
/* renamed from: a6.a */
/* loaded from: classes.dex */
public class C0041a extends AbstractC1980a implements InterfaceC0044d {
    private static final InterfaceC2016c LOG;
    private final ExecutorService _executor;

    static {
        Properties properties = C2015b.f5863a;
        LOG = C2015b.m2349a(C0041a.class.getName());
    }

    public C0041a(ExecutorService executorService) {
        this._executor = executorService;
    }

    @Override // p007a6.InterfaceC0044d
    public boolean dispatch(Runnable runnable) {
        try {
            this._executor.execute(runnable);
            return true;
        } catch (RejectedExecutionException e7) {
            LOG.mo2358i(e7);
            return false;
        }
    }

    @Override // p168u5.AbstractC1980a
    public void doStop() {
        super.doStop();
        this._executor.shutdownNow();
    }

    public int getIdleThreads() {
        ExecutorService executorService = this._executor;
        if (!(executorService instanceof ThreadPoolExecutor)) {
            return -1;
        }
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        return threadPoolExecutor.getPoolSize() - threadPoolExecutor.getActiveCount();
    }

    public int getThreads() {
        ExecutorService executorService = this._executor;
        if (executorService instanceof ThreadPoolExecutor) {
            return ((ThreadPoolExecutor) executorService).getPoolSize();
        }
        return -1;
    }

    @Override // p007a6.InterfaceC0044d
    public boolean isLowOnThreads() {
        ExecutorService executorService = this._executor;
        if (!(executorService instanceof ThreadPoolExecutor)) {
            return false;
        }
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        return threadPoolExecutor.getPoolSize() == threadPoolExecutor.getMaximumPoolSize() && threadPoolExecutor.getQueue().size() >= threadPoolExecutor.getPoolSize() - threadPoolExecutor.getActiveCount();
    }

    public void join() throws InterruptedException {
        this._executor.awaitTermination(RecyclerView.FOREVER_NS, TimeUnit.MILLISECONDS);
    }

    public C0041a() {
        this(new ThreadPoolExecutor(256, 256, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue()));
    }

    public C0041a(int i7) {
        ThreadPoolExecutor threadPoolExecutor;
        if (i7 < 0) {
            threadPoolExecutor = new ThreadPoolExecutor(256, 256, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        } else {
            threadPoolExecutor = i7 == 0 ? new ThreadPoolExecutor(32, 256, 60L, TimeUnit.SECONDS, new SynchronousQueue()) : new ThreadPoolExecutor(32, 256, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(i7));
        }
        this(threadPoolExecutor);
    }

    public C0041a(int i7, int i8, long j7) {
        this(i7, i8, j7, TimeUnit.MILLISECONDS);
    }

    public C0041a(int i7, int i8, long j7, TimeUnit timeUnit) {
        this(i7, i8, j7, timeUnit, new LinkedBlockingQueue());
    }

    public C0041a(int i7, int i8, long j7, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        this(new ThreadPoolExecutor(i7, i8, j7, timeUnit, blockingQueue));
    }
}
