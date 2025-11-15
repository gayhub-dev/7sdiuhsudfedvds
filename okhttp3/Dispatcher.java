package okhttp3;

import android.support.constraint.motion.C0079a;
import android.support.v7.widget.ActivityChooserView;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.RealCall;
import okhttp3.internal.Util;

/* loaded from: classes.dex */
public final class Dispatcher {
    public static final /* synthetic */ boolean $assertionsDisabled = false;

    @Nullable
    private ExecutorService executorService;

    @Nullable
    private Runnable idleCallback;
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    private final Deque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque();
    private final Deque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque();
    private final Deque<RealCall> runningSyncCalls = new ArrayDeque();

    public Dispatcher(ExecutorService executorService) {
        this.executorService = executorService;
    }

    private boolean promoteAndExecute() {
        int i7;
        boolean z6;
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<RealCall.AsyncCall> it = this.readyAsyncCalls.iterator();
            while (it.hasNext()) {
                RealCall.AsyncCall next = it.next();
                if (this.runningAsyncCalls.size() >= this.maxRequests) {
                    break;
                }
                if (runningCallsForHost(next) < this.maxRequestsPerHost) {
                    it.remove();
                    arrayList.add(next);
                    this.runningAsyncCalls.add(next);
                }
            }
            z6 = runningCallsCount() > 0;
        }
        int size = arrayList.size();
        for (i7 = 0; i7 < size; i7++) {
            ((RealCall.AsyncCall) arrayList.get(i7)).executeOn(executorService());
        }
        return z6;
    }

    private int runningCallsForHost(RealCall.AsyncCall asyncCall) {
        int i7 = 0;
        for (RealCall.AsyncCall asyncCall2 : this.runningAsyncCalls) {
            if (!asyncCall2.get().forWebSocket && asyncCall2.host().equals(asyncCall.host())) {
                i7++;
            }
        }
        return i7;
    }

    public synchronized void cancelAll() {
        Iterator<RealCall.AsyncCall> it = this.readyAsyncCalls.iterator();
        while (it.hasNext()) {
            it.next().get().cancel();
        }
        Iterator<RealCall.AsyncCall> it2 = this.runningAsyncCalls.iterator();
        while (it2.hasNext()) {
            it2.next().get().cancel();
        }
        Iterator<RealCall> it3 = this.runningSyncCalls.iterator();
        while (it3.hasNext()) {
            it3.next().cancel();
        }
    }

    public void enqueue(RealCall.AsyncCall asyncCall) {
        synchronized (this) {
            this.readyAsyncCalls.add(asyncCall);
        }
        promoteAndExecute();
    }

    public synchronized void executed(RealCall realCall) {
        this.runningSyncCalls.add(realCall);
    }

    public synchronized ExecutorService executorService() {
        if (this.executorService == null) {
            this.executorService = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return this.executorService;
    }

    public void finished(RealCall.AsyncCall asyncCall) {
        finished(this.runningAsyncCalls, asyncCall);
    }

    public synchronized int getMaxRequests() {
        return this.maxRequests;
    }

    public synchronized int getMaxRequestsPerHost() {
        return this.maxRequestsPerHost;
    }

    public synchronized List<Call> queuedCalls() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<RealCall.AsyncCall> it = this.readyAsyncCalls.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().get());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized int queuedCallsCount() {
        return this.readyAsyncCalls.size();
    }

    public synchronized List<Call> runningCalls() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        arrayList.addAll(this.runningSyncCalls);
        Iterator<RealCall.AsyncCall> it = this.runningAsyncCalls.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().get());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized int runningCallsCount() {
        return this.runningAsyncCalls.size() + this.runningSyncCalls.size();
    }

    public synchronized void setIdleCallback(@Nullable Runnable runnable) {
        this.idleCallback = runnable;
    }

    public void setMaxRequests(int i7) {
        if (i7 < 1) {
            throw new IllegalArgumentException(C0079a.m93a("max < 1: ", i7));
        }
        synchronized (this) {
            this.maxRequests = i7;
        }
        promoteAndExecute();
    }

    public void setMaxRequestsPerHost(int i7) {
        if (i7 < 1) {
            throw new IllegalArgumentException(C0079a.m93a("max < 1: ", i7));
        }
        synchronized (this) {
            this.maxRequestsPerHost = i7;
        }
        promoteAndExecute();
    }

    public void finished(RealCall realCall) {
        finished(this.runningSyncCalls, realCall);
    }

    private <T> void finished(Deque<T> deque, T t6) {
        Runnable runnable;
        synchronized (this) {
            if (deque.remove(t6)) {
                runnable = this.idleCallback;
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        if (promoteAndExecute() || runnable == null) {
            return;
        }
        runnable.run();
    }

    public Dispatcher() {
    }
}
