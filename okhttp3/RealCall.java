package okhttp3;

import android.support.v4.app.NotificationCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;
import okio.Timeout;

/* loaded from: classes.dex */
final class RealCall implements Call {
    public final OkHttpClient client;

    @Nullable
    private EventListener eventListener;
    private boolean executed;
    public final boolean forWebSocket;
    public final Request originalRequest;
    public final RetryAndFollowUpInterceptor retryAndFollowUpInterceptor;
    public final AsyncTimeout timeout;

    public final class AsyncCall extends NamedRunnable {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Callback responseCallback;

        public AsyncCall(Callback callback) {
            super("OkHttp %s", RealCall.this.redactedUrl());
            this.responseCallback = callback;
        }

        @Override // okhttp3.internal.NamedRunnable
        public void execute() {
            IOException e7;
            boolean z6;
            RealCall.this.timeout.enter();
            try {
                try {
                    z6 = true;
                    try {
                        this.responseCallback.onResponse(RealCall.this, RealCall.this.getResponseWithInterceptorChain());
                    } catch (IOException e8) {
                        e7 = e8;
                        IOException iOExceptionTimeoutExit = RealCall.this.timeoutExit(e7);
                        if (z6) {
                            Platform.get().log(4, "Callback failure for " + RealCall.this.toLoggableString(), iOExceptionTimeoutExit);
                        } else {
                            RealCall.this.eventListener.callFailed(RealCall.this, iOExceptionTimeoutExit);
                            this.responseCallback.onFailure(RealCall.this, iOExceptionTimeoutExit);
                        }
                    }
                } catch (IOException e9) {
                    e7 = e9;
                    z6 = false;
                }
            } finally {
                RealCall.this.client.dispatcher().finished(this);
            }
        }

        public void executeOn(ExecutorService executorService) {
            try {
                try {
                    executorService.execute(this);
                } catch (RejectedExecutionException e7) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                    interruptedIOException.initCause(e7);
                    RealCall.this.eventListener.callFailed(RealCall.this, interruptedIOException);
                    this.responseCallback.onFailure(RealCall.this, interruptedIOException);
                    RealCall.this.client.dispatcher().finished(this);
                }
            } catch (Throwable th) {
                RealCall.this.client.dispatcher().finished(this);
                throw th;
            }
        }

        public RealCall get() {
            return RealCall.this;
        }

        public String host() {
            return RealCall.this.originalRequest.url().host();
        }

        public Request request() {
            return RealCall.this.originalRequest;
        }
    }

    private RealCall(OkHttpClient okHttpClient, Request request, boolean z6) {
        this.client = okHttpClient;
        this.originalRequest = request;
        this.forWebSocket = z6;
        this.retryAndFollowUpInterceptor = new RetryAndFollowUpInterceptor(okHttpClient, z6);
        AsyncTimeout asyncTimeout = new AsyncTimeout() { // from class: okhttp3.RealCall.1
            @Override // okio.AsyncTimeout
            public void timedOut() throws IOException {
                RealCall.this.cancel();
            }
        };
        this.timeout = asyncTimeout;
        asyncTimeout.timeout(okHttpClient.callTimeoutMillis(), TimeUnit.MILLISECONDS);
    }

    private void captureCallStackTrace() {
        this.retryAndFollowUpInterceptor.setCallStackTrace(Platform.get().getStackTraceForCloseable("response.body().close()"));
    }

    public static RealCall newRealCall(OkHttpClient okHttpClient, Request request, boolean z6) {
        RealCall realCall = new RealCall(okHttpClient, request, z6);
        realCall.eventListener = okHttpClient.eventListenerFactory().create(realCall);
        return realCall;
    }

    @Override // okhttp3.Call
    public void cancel() throws IOException {
        this.retryAndFollowUpInterceptor.cancel();
    }

    @Override // okhttp3.Call
    public void enqueue(Callback callback) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        captureCallStackTrace();
        this.eventListener.callStart(this);
        this.client.dispatcher().enqueue(new AsyncCall(callback));
    }

    @Override // okhttp3.Call
    public Response execute() {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        captureCallStackTrace();
        this.timeout.enter();
        this.eventListener.callStart(this);
        try {
            try {
                this.client.dispatcher().executed(this);
                Response responseWithInterceptorChain = getResponseWithInterceptorChain();
                if (responseWithInterceptorChain != null) {
                    return responseWithInterceptorChain;
                }
                throw new IOException("Canceled");
            } catch (IOException e7) {
                IOException iOExceptionTimeoutExit = timeoutExit(e7);
                this.eventListener.callFailed(this, iOExceptionTimeoutExit);
                throw iOExceptionTimeoutExit;
            }
        } finally {
            this.client.dispatcher().finished(this);
        }
    }

    public Response getResponseWithInterceptorChain() throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.client.interceptors());
        arrayList.add(this.retryAndFollowUpInterceptor);
        arrayList.add(new BridgeInterceptor(this.client.cookieJar()));
        arrayList.add(new CacheInterceptor(this.client.internalCache()));
        arrayList.add(new ConnectInterceptor(this.client));
        if (!this.forWebSocket) {
            arrayList.addAll(this.client.networkInterceptors());
        }
        arrayList.add(new CallServerInterceptor(this.forWebSocket));
        Response responseProceed = new RealInterceptorChain(arrayList, null, null, null, 0, this.originalRequest, this, this.eventListener, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis()).proceed(this.originalRequest);
        if (!this.retryAndFollowUpInterceptor.isCanceled()) {
            return responseProceed;
        }
        Util.closeQuietly(responseProceed);
        throw new IOException("Canceled");
    }

    @Override // okhttp3.Call
    public boolean isCanceled() {
        return this.retryAndFollowUpInterceptor.isCanceled();
    }

    @Override // okhttp3.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public String redactedUrl() {
        return this.originalRequest.url().redact();
    }

    @Override // okhttp3.Call
    public Request request() {
        return this.originalRequest;
    }

    public StreamAllocation streamAllocation() {
        return this.retryAndFollowUpInterceptor.streamAllocation();
    }

    @Override // okhttp3.Call
    public Timeout timeout() {
        return this.timeout;
    }

    @Nullable
    public IOException timeoutExit(@Nullable IOException iOException) {
        if (!this.timeout.exit()) {
            return iOException;
        }
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public String toLoggableString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "canceled " : "");
        sb.append(this.forWebSocket ? "web socket" : NotificationCompat.CATEGORY_CALL);
        sb.append(" to ");
        sb.append(redactedUrl());
        return sb.toString();
    }

    @Override // okhttp3.Call
    public RealCall clone() {
        return newRealCall(this.client, this.originalRequest, this.forWebSocket);
    }
}
