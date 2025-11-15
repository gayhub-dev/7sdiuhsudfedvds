package okhttp3.internal.http;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Sink;

/* loaded from: classes.dex */
public interface HttpCodec {
    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

    void cancel();

    Sink createRequestBody(Request request, long j7);

    void finishRequest();

    void flushRequest();

    ResponseBody openResponseBody(Response response);

    Response.Builder readResponseHeaders(boolean z6);

    void writeRequestHeaders(Request request);
}
