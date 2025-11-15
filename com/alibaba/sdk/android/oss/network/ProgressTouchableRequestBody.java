package com.alibaba.sdk.android.oss.network;

import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/* loaded from: classes.dex */
public class ProgressTouchableRequestBody<T extends OSSRequest> extends RequestBody {
    private static final int SEGMENT_SIZE = 2048;
    private OSSProgressCallback callback;
    private long contentLength;
    private String contentType;
    private InputStream inputStream;
    private T request;

    public ProgressTouchableRequestBody(InputStream inputStream, long j7, String str, ExecutionContext executionContext) {
        this.inputStream = inputStream;
        this.contentType = str;
        this.contentLength = j7;
        this.callback = executionContext.getProgressCallback();
        this.request = (T) executionContext.getRequest();
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        return this.contentLength;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return MediaType.parse(this.contentType);
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) {
        Source source = Okio.source(this.inputStream);
        long j7 = 0;
        while (true) {
            long j8 = this.contentLength;
            if (j7 >= j8) {
                break;
            }
            long j9 = source.read(bufferedSink.buffer(), Math.min(j8 - j7, 2048L));
            if (j9 == -1) {
                break;
            }
            j7 += j9;
            bufferedSink.flush();
            OSSProgressCallback oSSProgressCallback = this.callback;
            if (oSSProgressCallback != null && j7 != 0) {
                oSSProgressCallback.onProgress(this.request, j7, this.contentLength);
            }
        }
        if (source != null) {
            source.close();
        }
    }
}
