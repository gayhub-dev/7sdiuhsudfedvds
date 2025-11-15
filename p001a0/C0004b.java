package p001a0;

import android.os.Process;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: GlideExecutor.java */
/* renamed from: a0.b */
/* loaded from: classes.dex */
public final class C0004b extends ThreadPoolExecutor {

    /* renamed from: f */
    public static final long f6f = TimeUnit.SECONDS.toMillis(10);

    /* renamed from: g */
    public static final /* synthetic */ int f7g = 0;

    /* renamed from: e */
    public final boolean f8e;

    /* compiled from: GlideExecutor.java */
    /* renamed from: a0.b$a */
    public static final class a implements ThreadFactory {

        /* renamed from: e */
        public final String f9e;

        /* renamed from: f */
        public final b f10f;

        /* renamed from: g */
        public final boolean f11g;

        /* renamed from: h */
        public int f12h;

        /* compiled from: GlideExecutor.java */
        /* renamed from: a0.b$a$a, reason: collision with other inner class name */
        public class C2159a extends Thread {
            public C2159a(Runnable runnable, String str) {
                super(runnable, str);
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws SecurityException, IllegalArgumentException {
                Process.setThreadPriority(9);
                if (a.this.f11g) {
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
                }
                try {
                    super.run();
                } catch (Throwable th) {
                    a.this.f10f.mo4a(th);
                }
            }
        }

        public a(String str, b bVar, boolean z6) {
            this.f9e = str;
            this.f10f = bVar;
            this.f11g = z6;
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(@NonNull Runnable runnable) {
            C2159a c2159a;
            c2159a = new C2159a(runnable, "glide-" + this.f9e + "-thread-" + this.f12h);
            this.f12h = this.f12h + 1;
            return c2159a;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: GlideExecutor.java */
    /* renamed from: a0.b$b */
    public static class b {

        /* renamed from: e */
        public static final b f14e;

        /* renamed from: f */
        public static final /* synthetic */ b[] f15f;

        /* JADX INFO: Fake field, exist only in values array */
        b EF0;

        /* compiled from: GlideExecutor.java */
        /* renamed from: a0.b$b$a */
        public enum a extends b {
            public a(String str, int i7) {
                super(str, i7, null);
            }

            @Override // p001a0.C0004b.b
            /* renamed from: a */
            public void mo4a(Throwable th) {
                Log.isLoggable("GlideExecutor", 6);
            }
        }

        /* compiled from: GlideExecutor.java */
        /* renamed from: a0.b$b$b, reason: collision with other inner class name */
        public enum C2160b extends b {
            public C2160b(String str, int i7) {
                super(str, i7, null);
            }

            @Override // p001a0.C0004b.b
            /* renamed from: a */
            public void mo4a(Throwable th) {
                throw new RuntimeException("Request threw uncaught throwable", th);
            }
        }

        static {
            b bVar = new b("IGNORE", 0);
            a aVar = new a("LOG", 1);
            f14e = aVar;
            f15f = new b[]{bVar, aVar, new C2160b("THROW", 2)};
        }

        public b(String str, int i7) {
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) f15f.clone();
        }

        /* renamed from: a */
        public void mo4a(Throwable th) {
        }

        public b(String str, int i7, C0003a c0003a) {
        }
    }

    public C0004b(int i7, String str, b bVar, boolean z6, boolean z7) {
        this(i7, i7, 0L, str, bVar, z6, z7, new PriorityBlockingQueue());
    }

    /* renamed from: a */
    public final <T> Future<T> m3a(Future<T> future) {
        if (this.f8e) {
            boolean z6 = false;
            while (!future.isDone()) {
                try {
                    try {
                        future.get();
                    } catch (InterruptedException unused) {
                        z6 = true;
                    } catch (ExecutionException e7) {
                        throw new RuntimeException(e7);
                    }
                } finally {
                    if (z6) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        return future;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (this.f8e) {
            runnable.run();
        } else {
            super.execute(runnable);
        }
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    @NonNull
    public Future<?> submit(Runnable runnable) {
        Future<?> futureSubmit = super.submit(runnable);
        m3a(futureSubmit);
        return futureSubmit;
    }

    public C0004b(int i7, int i8, long j7, String str, b bVar, boolean z6, boolean z7, BlockingQueue<Runnable> blockingQueue) {
        super(i7, i8, j7, TimeUnit.MILLISECONDS, blockingQueue, new a(str, bVar, z6));
        this.f8e = z7;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    @NonNull
    public <T> Future<T> submit(Runnable runnable, T t6) {
        Future<T> futureSubmit = super.submit(runnable, t6);
        m3a(futureSubmit);
        return futureSubmit;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        Future<T> futureSubmit = super.submit(callable);
        m3a(futureSubmit);
        return futureSubmit;
    }
}
