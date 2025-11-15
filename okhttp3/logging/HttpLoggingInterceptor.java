package okhttp3.logging;

import android.support.constraint.motion.C0080b;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import android.support.v7.widget.RecyclerView;
import java.io.EOFException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import p009b.C0413b;
import p192y1.C2103g;

/* loaded from: classes.dex */
public final class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private volatile Set<String> headersToRedact;
    private volatile Level level;
    private final Logger logger;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public interface Logger {
        public static final Logger DEFAULT = C2103g.f6237h;

        void log(String str);
    }

    public HttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    private static boolean bodyHasUnknownEncoding(Headers headers) {
        String str = headers.get("Content-Encoding");
        return (str == null || str.equalsIgnoreCase("identity") || str.equalsIgnoreCase("gzip")) ? false : true;
    }

    public static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, buffer.size() < 64 ? buffer.size() : 64L);
            for (int i7 = 0; i7 < 16; i7++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int utf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(utf8CodePoint) && !Character.isWhitespace(utf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private void logHeader(Headers headers, int i7) {
        String strValue = this.headersToRedact.contains(headers.name(i7)) ? "██" : headers.value(i7);
        this.logger.log(headers.name(i7) + ": " + strValue);
    }

    public Level getLevel() {
        return this.level;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Exception {
        String string;
        long j7;
        char c7;
        String string2;
        Level level = this.level;
        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }
        boolean z6 = level == Level.BODY;
        boolean z7 = z6 || level == Level.HEADERS;
        RequestBody requestBodyBody = request.body();
        boolean z8 = requestBodyBody != null;
        Connection connection = chain.connection();
        StringBuilder sbM112a = C0413b.m112a("--> ");
        sbM112a.append(request.method());
        sbM112a.append(' ');
        sbM112a.append(request.url());
        if (connection != null) {
            StringBuilder sbM112a2 = C0413b.m112a(" ");
            sbM112a2.append(connection.protocol());
            string = sbM112a2.toString();
        } else {
            string = "";
        }
        sbM112a.append(string);
        String string3 = sbM112a.toString();
        if (!z7 && z8) {
            StringBuilder sbM94a = C0080b.m94a(string3, " (");
            sbM94a.append(requestBodyBody.contentLength());
            sbM94a.append("-byte body)");
            string3 = sbM94a.toString();
        }
        this.logger.log(string3);
        if (z7) {
            if (z8) {
                if (requestBodyBody.contentType() != null) {
                    Logger logger = this.logger;
                    StringBuilder sbM112a3 = C0413b.m112a("Content-Type: ");
                    sbM112a3.append(requestBodyBody.contentType());
                    logger.log(sbM112a3.toString());
                }
                if (requestBodyBody.contentLength() != -1) {
                    Logger logger2 = this.logger;
                    StringBuilder sbM112a4 = C0413b.m112a("Content-Length: ");
                    sbM112a4.append(requestBodyBody.contentLength());
                    logger2.log(sbM112a4.toString());
                }
            }
            Headers headers = request.headers();
            int size = headers.size();
            for (int i7 = 0; i7 < size; i7++) {
                String strName = headers.name(i7);
                if (!"Content-Type".equalsIgnoreCase(strName) && !"Content-Length".equalsIgnoreCase(strName)) {
                    logHeader(headers, i7);
                }
            }
            if (!z6 || !z8) {
                Logger logger3 = this.logger;
                StringBuilder sbM112a5 = C0413b.m112a("--> END ");
                sbM112a5.append(request.method());
                logger3.log(sbM112a5.toString());
            } else if (bodyHasUnknownEncoding(request.headers())) {
                Logger logger4 = this.logger;
                StringBuilder sbM112a6 = C0413b.m112a("--> END ");
                sbM112a6.append(request.method());
                sbM112a6.append(" (encoded body omitted)");
                logger4.log(sbM112a6.toString());
            } else if (requestBodyBody.isDuplex()) {
                Logger logger5 = this.logger;
                StringBuilder sbM112a7 = C0413b.m112a("--> END ");
                sbM112a7.append(request.method());
                sbM112a7.append(" (duplex request body omitted)");
                logger5.log(sbM112a7.toString());
            } else {
                Buffer buffer = new Buffer();
                requestBodyBody.writeTo(buffer);
                Charset charset = UTF8;
                MediaType mediaTypeContentType = requestBodyBody.contentType();
                if (mediaTypeContentType != null) {
                    charset = mediaTypeContentType.charset(charset);
                }
                this.logger.log("");
                if (isPlaintext(buffer)) {
                    this.logger.log(buffer.readString(charset));
                    Logger logger6 = this.logger;
                    StringBuilder sbM112a8 = C0413b.m112a("--> END ");
                    sbM112a8.append(request.method());
                    sbM112a8.append(" (");
                    sbM112a8.append(requestBodyBody.contentLength());
                    sbM112a8.append("-byte body)");
                    logger6.log(sbM112a8.toString());
                } else {
                    Logger logger7 = this.logger;
                    StringBuilder sbM112a9 = C0413b.m112a("--> END ");
                    sbM112a9.append(request.method());
                    sbM112a9.append(" (binary ");
                    sbM112a9.append(requestBodyBody.contentLength());
                    sbM112a9.append("-byte body omitted)");
                    logger7.log(sbM112a9.toString());
                }
            }
        }
        long jNanoTime = System.nanoTime();
        try {
            Response responseProceed = chain.proceed(request);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime);
            ResponseBody responseBodyBody = responseProceed.body();
            long jContentLength = responseBodyBody.contentLength();
            String str = jContentLength != -1 ? jContentLength + "-byte" : "unknown-length";
            Logger logger8 = this.logger;
            StringBuilder sbM112a10 = C0413b.m112a("<-- ");
            sbM112a10.append(responseProceed.code());
            if (responseProceed.message().isEmpty()) {
                string2 = "";
                j7 = jContentLength;
                c7 = ' ';
            } else {
                StringBuilder sb = new StringBuilder();
                j7 = jContentLength;
                c7 = ' ';
                sb.append(' ');
                sb.append(responseProceed.message());
                string2 = sb.toString();
            }
            sbM112a10.append(string2);
            sbM112a10.append(c7);
            sbM112a10.append(responseProceed.request().url());
            sbM112a10.append(" (");
            sbM112a10.append(millis);
            sbM112a10.append("ms");
            sbM112a10.append(!z7 ? C0096a.m97a(", ", str, " body") : "");
            sbM112a10.append(')');
            logger8.log(sbM112a10.toString());
            if (z7) {
                Headers headers2 = responseProceed.headers();
                int size2 = headers2.size();
                for (int i8 = 0; i8 < size2; i8++) {
                    logHeader(headers2, i8);
                }
                if (!z6 || !HttpHeaders.hasBody(responseProceed)) {
                    this.logger.log("<-- END HTTP");
                } else if (bodyHasUnknownEncoding(responseProceed.headers())) {
                    this.logger.log("<-- END HTTP (encoded body omitted)");
                } else {
                    BufferedSource bufferedSourceSource = responseBodyBody.source();
                    bufferedSourceSource.request(RecyclerView.FOREVER_NS);
                    Buffer buffer2 = bufferedSourceSource.getBuffer();
                    Long lValueOf = null;
                    if ("gzip".equalsIgnoreCase(headers2.get("Content-Encoding"))) {
                        lValueOf = Long.valueOf(buffer2.size());
                        GzipSource gzipSource = new GzipSource(buffer2.clone());
                        try {
                            buffer2 = new Buffer();
                            buffer2.writeAll(gzipSource);
                            gzipSource.close();
                        } finally {
                        }
                    }
                    Charset charset2 = UTF8;
                    MediaType mediaTypeContentType2 = responseBodyBody.contentType();
                    if (mediaTypeContentType2 != null) {
                        charset2 = mediaTypeContentType2.charset(charset2);
                    }
                    if (!isPlaintext(buffer2)) {
                        this.logger.log("");
                        Logger logger9 = this.logger;
                        StringBuilder sbM112a11 = C0413b.m112a("<-- END HTTP (binary ");
                        sbM112a11.append(buffer2.size());
                        sbM112a11.append("-byte body omitted)");
                        logger9.log(sbM112a11.toString());
                        return responseProceed;
                    }
                    if (j7 != 0) {
                        this.logger.log("");
                        this.logger.log(buffer2.clone().readString(charset2));
                    }
                    if (lValueOf != null) {
                        Logger logger10 = this.logger;
                        StringBuilder sbM112a12 = C0413b.m112a("<-- END HTTP (");
                        sbM112a12.append(buffer2.size());
                        sbM112a12.append("-byte, ");
                        sbM112a12.append(lValueOf);
                        sbM112a12.append("-gzipped-byte body)");
                        logger10.log(sbM112a12.toString());
                    } else {
                        Logger logger11 = this.logger;
                        StringBuilder sbM112a13 = C0413b.m112a("<-- END HTTP (");
                        sbM112a13.append(buffer2.size());
                        sbM112a13.append("-byte body)");
                        logger11.log(sbM112a13.toString());
                    }
                }
            }
            return responseProceed;
        } catch (Exception e7) {
            this.logger.log("<-- HTTP FAILED: " + e7);
            throw e7;
        }
    }

    public void redactHeader(String str) {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        treeSet.addAll(this.headersToRedact);
        treeSet.add(str);
        this.headersToRedact = treeSet;
    }

    public HttpLoggingInterceptor setLevel(Level level) {
        Objects.requireNonNull(level, "level == null. Use Level.NONE instead.");
        this.level = level;
        return this;
    }

    public HttpLoggingInterceptor(Logger logger) {
        this.headersToRedact = Collections.emptySet();
        this.level = Level.NONE;
        this.logger = logger;
    }
}
