package okhttp3;

import android.support.v7.widget.ActivityChooserView;
import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.platform.Platform;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class ConnectionPool {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Executor executor = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
    private final Runnable cleanupRunnable;
    public boolean cleanupRunning;
    private final Deque<RealConnection> connections;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;
    public final RouteDatabase routeDatabase;

    public ConnectionPool() {
        this(5, 5L, TimeUnit.MINUTES);
    }

    private int pruneAndGetAllocationCount(RealConnection realConnection, long j7) {
        List<Reference<StreamAllocation>> list = realConnection.allocations;
        int i7 = 0;
        while (i7 < list.size()) {
            Reference<StreamAllocation> reference = list.get(i7);
            if (reference.get() != null) {
                i7++;
            } else {
                StringBuilder sbM112a = C0413b.m112a("A connection to ");
                sbM112a.append(realConnection.route().address().url());
                sbM112a.append(" was leaked. Did you forget to close a response body?");
                Platform.get().logCloseableLeak(sbM112a.toString(), ((StreamAllocation.StreamAllocationReference) reference).callStackTrace);
                list.remove(i7);
                realConnection.noNewStreams = true;
                if (list.isEmpty()) {
                    realConnection.idleAtNanos = j7 - this.keepAliveDurationNs;
                    return 0;
                }
            }
        }
        return list.size();
    }

    public long cleanup(long j7) throws IOException {
        synchronized (this) {
            RealConnection realConnection = null;
            long j8 = Long.MIN_VALUE;
            int i7 = 0;
            int i8 = 0;
            for (RealConnection realConnection2 : this.connections) {
                if (pruneAndGetAllocationCount(realConnection2, j7) > 0) {
                    i8++;
                } else {
                    i7++;
                    long j9 = j7 - realConnection2.idleAtNanos;
                    if (j9 > j8) {
                        realConnection = realConnection2;
                        j8 = j9;
                    }
                }
            }
            long j10 = this.keepAliveDurationNs;
            if (j8 < j10 && i7 <= this.maxIdleConnections) {
                if (i7 > 0) {
                    return j10 - j8;
                }
                if (i8 > 0) {
                    return j10;
                }
                this.cleanupRunning = false;
                return -1L;
            }
            this.connections.remove(realConnection);
            Util.closeQuietly(realConnection.socket());
            return 0L;
        }
    }

    public boolean connectionBecameIdle(RealConnection realConnection) {
        if (realConnection.noNewStreams || this.maxIdleConnections == 0) {
            this.connections.remove(realConnection);
            return true;
        }
        notifyAll();
        return false;
    }

    public synchronized int connectionCount() {
        return this.connections.size();
    }

    @Nullable
    public Socket deduplicate(Address address, StreamAllocation streamAllocation) {
        for (RealConnection realConnection : this.connections) {
            if (realConnection.isEligible(address, null) && realConnection.isMultiplexed() && realConnection != streamAllocation.connection()) {
                return streamAllocation.releaseAndAcquire(realConnection);
            }
        }
        return null;
    }

    public void evictAll() throws IOException {
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<RealConnection> it = this.connections.iterator();
            while (it.hasNext()) {
                RealConnection next = it.next();
                if (next.allocations.isEmpty()) {
                    next.noNewStreams = true;
                    arrayList.add(next);
                    it.remove();
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Util.closeQuietly(((RealConnection) it2.next()).socket());
        }
    }

    @Nullable
    public RealConnection get(Address address, StreamAllocation streamAllocation, Route route) {
        for (RealConnection realConnection : this.connections) {
            if (realConnection.isEligible(address, route)) {
                streamAllocation.acquire(realConnection, true);
                return realConnection;
            }
        }
        return null;
    }

    public synchronized int idleConnectionCount() {
        int i7;
        i7 = 0;
        Iterator<RealConnection> it = this.connections.iterator();
        while (it.hasNext()) {
            if (it.next().allocations.isEmpty()) {
                i7++;
            }
        }
        return i7;
    }

    public void put(RealConnection realConnection) {
        if (!this.cleanupRunning) {
            this.cleanupRunning = true;
            executor.execute(this.cleanupRunnable);
        }
        this.connections.add(realConnection);
    }

    public ConnectionPool(int i7, long j7, TimeUnit timeUnit) {
        this.cleanupRunnable = new Runnable() { // from class: okhttp3.ConnectionPool.1
            @Override // java.lang.Runnable
            public void run() throws IOException {
                while (true) {
                    long jCleanup = ConnectionPool.this.cleanup(System.nanoTime());
                    if (jCleanup == -1) {
                        return;
                    }
                    if (jCleanup > 0) {
                        long j8 = jCleanup / 1000000;
                        long j9 = jCleanup - (1000000 * j8);
                        synchronized (ConnectionPool.this) {
                            try {
                                ConnectionPool.this.wait(j8, (int) j9);
                            } catch (InterruptedException unused) {
                            }
                        }
                    }
                }
            }
        };
        this.connections = new ArrayDeque();
        this.routeDatabase = new RouteDatabase();
        this.maxIdleConnections = i7;
        this.keepAliveDurationNs = timeUnit.toNanos(j7);
        if (j7 <= 0) {
            throw new IllegalArgumentException(C0534b.m341a("keepAliveDuration <= 0: ", j7));
        }
    }
}
