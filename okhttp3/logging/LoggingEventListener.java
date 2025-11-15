package okhttp3.logging;

import android.arch.lifecycle.C0063n;
import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class LoggingEventListener extends EventListener {
    private final HttpLoggingInterceptor.Logger logger;
    private long startNs;

    public static class Factory implements EventListener.Factory {
        private final HttpLoggingInterceptor.Logger logger;

        public Factory() {
            this(HttpLoggingInterceptor.Logger.DEFAULT);
        }

        @Override // okhttp3.EventListener.Factory
        public EventListener create(Call call) {
            return new LoggingEventListener(this.logger);
        }

        public Factory(HttpLoggingInterceptor.Logger logger) {
            this.logger = logger;
        }
    }

    private void logWithTime(String str) {
        long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.startNs);
        this.logger.log("[" + millis + " ms] " + str);
    }

    @Override // okhttp3.EventListener
    public void callEnd(Call call) {
        logWithTime("callEnd");
    }

    @Override // okhttp3.EventListener
    public void callFailed(Call call, IOException iOException) {
        logWithTime("callFailed: " + iOException);
    }

    @Override // okhttp3.EventListener
    public void callStart(Call call) {
        this.startNs = System.nanoTime();
        StringBuilder sbM112a = C0413b.m112a("callStart: ");
        sbM112a.append(call.request());
        logWithTime(sbM112a.toString());
    }

    @Override // okhttp3.EventListener
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol) {
        logWithTime("connectEnd: " + protocol);
    }

    @Override // okhttp3.EventListener
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol, IOException iOException) {
        logWithTime("connectFailed: " + protocol + " " + iOException);
    }

    @Override // okhttp3.EventListener
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        logWithTime("connectStart: " + inetSocketAddress + " " + proxy);
    }

    @Override // okhttp3.EventListener
    public void connectionAcquired(Call call, Connection connection) {
        logWithTime("connectionAcquired: " + connection);
    }

    @Override // okhttp3.EventListener
    public void connectionReleased(Call call, Connection connection) {
        logWithTime("connectionReleased");
    }

    @Override // okhttp3.EventListener
    public void dnsEnd(Call call, String str, List<InetAddress> list) {
        logWithTime("dnsEnd: " + list);
    }

    @Override // okhttp3.EventListener
    public void dnsStart(Call call, String str) {
        logWithTime(C0063n.m88a("dnsStart: ", str));
    }

    @Override // okhttp3.EventListener
    public void requestBodyEnd(Call call, long j7) {
        logWithTime(C0534b.m341a("requestBodyEnd: byteCount=", j7));
    }

    @Override // okhttp3.EventListener
    public void requestBodyStart(Call call) {
        logWithTime("requestBodyStart");
    }

    public void requestFailed(Call call, IOException iOException) {
        logWithTime("requestFailed: " + iOException);
    }

    @Override // okhttp3.EventListener
    public void requestHeadersEnd(Call call, Request request) {
        logWithTime("requestHeadersEnd");
    }

    @Override // okhttp3.EventListener
    public void requestHeadersStart(Call call) {
        logWithTime("requestHeadersStart");
    }

    @Override // okhttp3.EventListener
    public void responseBodyEnd(Call call, long j7) {
        logWithTime(C0534b.m341a("responseBodyEnd: byteCount=", j7));
    }

    @Override // okhttp3.EventListener
    public void responseBodyStart(Call call) {
        logWithTime("responseBodyStart");
    }

    public void responseFailed(Call call, IOException iOException) {
        logWithTime("responseFailed: " + iOException);
    }

    @Override // okhttp3.EventListener
    public void responseHeadersEnd(Call call, Response response) {
        logWithTime("responseHeadersEnd: " + response);
    }

    @Override // okhttp3.EventListener
    public void responseHeadersStart(Call call) {
        logWithTime("responseHeadersStart");
    }

    @Override // okhttp3.EventListener
    public void secureConnectEnd(Call call, @Nullable Handshake handshake) {
        logWithTime("secureConnectEnd: " + handshake);
    }

    @Override // okhttp3.EventListener
    public void secureConnectStart(Call call) {
        logWithTime("secureConnectStart");
    }

    private LoggingEventListener(HttpLoggingInterceptor.Logger logger) {
        this.logger = logger;
    }
}
