package retrofit2;

import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/* loaded from: classes.dex */
final class OkHttpCall<T> implements Call<T> {
    private final Object[] args;
    private final Call.Factory callFactory;
    private volatile boolean canceled;

    @GuardedBy("this")
    @Nullable
    private Throwable creationFailure;

    @GuardedBy("this")
    private boolean executed;

    @GuardedBy("this")
    @Nullable
    private okhttp3.Call rawCall;
    private final RequestFactory requestFactory;
    private final Converter<ResponseBody, T> responseConverter;

    /* renamed from: retrofit2.OkHttpCall$1 */
    public class C18521 implements okhttp3.Callback {
        public final /* synthetic */ Callback val$callback;

        public C18521(Callback callback) {
            callback = callback;
        }

        private void callFailure(Throwable th) {
            try {
                callback.onFailure(OkHttpCall.this, th);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }

        @Override // okhttp3.Callback
        public void onFailure(okhttp3.Call call, IOException iOException) {
            callFailure(iOException);
        }

        @Override // okhttp3.Callback
        public void onResponse(okhttp3.Call call, okhttp3.Response response) {
            try {
                try {
                    callback.onResponse(OkHttpCall.this, OkHttpCall.this.parseResponse(response));
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } catch (Throwable th2) {
                Utils.throwIfFatal(th2);
                callFailure(th2);
            }
        }
    }

    public static final class ExceptionCatchingResponseBody extends ResponseBody {
        private final ResponseBody delegate;

        @Nullable
        public IOException thrownException;

        /* renamed from: retrofit2.OkHttpCall$ExceptionCatchingResponseBody$1 */
        public class C18531 extends ForwardingSource {
            public C18531(Source source) {
                super(source);
            }

            @Override // okio.ForwardingSource, okio.Source
            public long read(Buffer buffer, long j7) throws IOException {
                try {
                    return super.read(buffer, j7);
                } catch (IOException e7) {
                    ExceptionCatchingResponseBody.this.thrownException = e7;
                    throw e7;
                }
            }
        }

        public ExceptionCatchingResponseBody(ResponseBody responseBody) {
            this.delegate = responseBody;
        }

        @Override // okhttp3.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.delegate.contentLength();
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.delegate.contentType();
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            return Okio.buffer(new ForwardingSource(this.delegate.source()) { // from class: retrofit2.OkHttpCall.ExceptionCatchingResponseBody.1
                public C18531(Source source) {
                    super(source);
                }

                @Override // okio.ForwardingSource, okio.Source
                public long read(Buffer buffer, long j7) throws IOException {
                    try {
                        return super.read(buffer, j7);
                    } catch (IOException e7) {
                        ExceptionCatchingResponseBody.this.thrownException = e7;
                        throw e7;
                    }
                }
            });
        }

        public void throwIfCaught() throws IOException {
            IOException iOException = this.thrownException;
            if (iOException != null) {
                throw iOException;
            }
        }
    }

    public static final class NoContentResponseBody extends ResponseBody {
        private final long contentLength;

        @Nullable
        private final MediaType contentType;

        public NoContentResponseBody(@Nullable MediaType mediaType, long j7) {
            this.contentType = mediaType;
            this.contentLength = j7;
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.contentLength;
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    public OkHttpCall(RequestFactory requestFactory, Object[] objArr, Call.Factory factory, Converter<ResponseBody, T> converter) {
        this.requestFactory = requestFactory;
        this.args = objArr;
        this.callFactory = factory;
        this.responseConverter = converter;
    }

    private okhttp3.Call createRawCall() {
        okhttp3.Call callNewCall = this.callFactory.newCall(this.requestFactory.create(this.args));
        Objects.requireNonNull(callNewCall, "Call.Factory returned null.");
        return callNewCall;
    }

    @Override // retrofit2.Call
    public void cancel() {
        okhttp3.Call call;
        this.canceled = true;
        synchronized (this) {
            call = this.rawCall;
        }
        if (call != null) {
            call.cancel();
        }
    }

    @Override // retrofit2.Call
    public void enqueue(Callback<T> callback) {
        okhttp3.Call call;
        Throwable th;
        Utils.checkNotNull(callback, "callback == null");
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            call = this.rawCall;
            th = this.creationFailure;
            if (call == null && th == null) {
                try {
                    okhttp3.Call callCreateRawCall = createRawCall();
                    this.rawCall = callCreateRawCall;
                    call = callCreateRawCall;
                } catch (Throwable th2) {
                    th = th2;
                    Utils.throwIfFatal(th);
                    this.creationFailure = th;
                }
            }
        }
        if (th != null) {
            callback.onFailure(this, th);
            return;
        }
        if (this.canceled) {
            call.cancel();
        }
        call.enqueue(new okhttp3.Callback() { // from class: retrofit2.OkHttpCall.1
            public final /* synthetic */ Callback val$callback;

            public C18521(Callback callback2) {
                callback = callback2;
            }

            private void callFailure(Throwable th3) {
                try {
                    callback.onFailure(OkHttpCall.this, th3);
                } catch (Throwable th22) {
                    th22.printStackTrace();
                }
            }

            @Override // okhttp3.Callback
            public void onFailure(okhttp3.Call call2, IOException iOException) {
                callFailure(iOException);
            }

            @Override // okhttp3.Callback
            public void onResponse(okhttp3.Call call2, okhttp3.Response response) {
                try {
                    try {
                        callback.onResponse(OkHttpCall.this, OkHttpCall.this.parseResponse(response));
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                } catch (Throwable th22) {
                    Utils.throwIfFatal(th22);
                    callFailure(th22);
                }
            }
        });
    }

    @Override // retrofit2.Call
    public Response<T> execute() {
        okhttp3.Call callCreateRawCall;
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            Throwable th = this.creationFailure;
            if (th != null) {
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                throw ((Error) th);
            }
            callCreateRawCall = this.rawCall;
            if (callCreateRawCall == null) {
                try {
                    callCreateRawCall = createRawCall();
                    this.rawCall = callCreateRawCall;
                } catch (IOException | Error | RuntimeException e7) {
                    Utils.throwIfFatal(e7);
                    this.creationFailure = e7;
                    throw e7;
                }
            }
        }
        if (this.canceled) {
            callCreateRawCall.cancel();
        }
        return parseResponse(callCreateRawCall.execute());
    }

    @Override // retrofit2.Call
    public boolean isCanceled() {
        boolean z6 = true;
        if (this.canceled) {
            return true;
        }
        synchronized (this) {
            okhttp3.Call call = this.rawCall;
            if (call == null || !call.isCanceled()) {
                z6 = false;
            }
        }
        return z6;
    }

    @Override // retrofit2.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public Response<T> parseResponse(okhttp3.Response response) throws IOException {
        ResponseBody responseBodyBody = response.body();
        okhttp3.Response responseBuild = response.newBuilder().body(new NoContentResponseBody(responseBodyBody.contentType(), responseBodyBody.contentLength())).build();
        int iCode = responseBuild.code();
        if (iCode < 200 || iCode >= 300) {
            try {
                return Response.error(Utils.buffer(responseBodyBody), responseBuild);
            } finally {
                responseBodyBody.close();
            }
        }
        if (iCode == 204 || iCode == 205) {
            responseBodyBody.close();
            return Response.success((Object) null, responseBuild);
        }
        ExceptionCatchingResponseBody exceptionCatchingResponseBody = new ExceptionCatchingResponseBody(responseBodyBody);
        try {
            return Response.success(this.responseConverter.convert(exceptionCatchingResponseBody), responseBuild);
        } catch (RuntimeException e7) {
            exceptionCatchingResponseBody.throwIfCaught();
            throw e7;
        }
    }

    @Override // retrofit2.Call
    public synchronized Request request() {
        okhttp3.Call call = this.rawCall;
        if (call != null) {
            return call.request();
        }
        Throwable th = this.creationFailure;
        if (th != null) {
            if (th instanceof IOException) {
                throw new RuntimeException("Unable to create request.", this.creationFailure);
            }
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            throw ((Error) th);
        }
        try {
            okhttp3.Call callCreateRawCall = createRawCall();
            this.rawCall = callCreateRawCall;
            return callCreateRawCall.request();
        } catch (IOException e7) {
            this.creationFailure = e7;
            throw new RuntimeException("Unable to create request.", e7);
        } catch (Error e8) {
            e = e8;
            Utils.throwIfFatal(e);
            this.creationFailure = e;
            throw e;
        } catch (RuntimeException e9) {
            e = e9;
            Utils.throwIfFatal(e);
            this.creationFailure = e;
            throw e;
        }
    }

    @Override // retrofit2.Call
    public OkHttpCall<T> clone() {
        return new OkHttpCall<>(this.requestFactory, this.args, this.callFactory, this.responseConverter);
    }
}
