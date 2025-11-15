package com.alibaba.sdk.android.oss.network;

import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/* loaded from: classes.dex */
public class ProgressTouchableResponseBody<T extends OSSRequest> extends ResponseBody {
    private BufferedSource mBufferedSource;
    private OSSProgressCallback mProgressListener;
    private final ResponseBody mResponseBody;
    private T request;

    public ProgressTouchableResponseBody(ResponseBody responseBody, ExecutionContext executionContext) {
        this.mResponseBody = responseBody;
        this.mProgressListener = executionContext.getProgressCallback();
        this.request = (T) executionContext.getRequest();
    }

    @Override // okhttp3.ResponseBody
    public long contentLength() {
        return this.mResponseBody.contentLength();
    }

    @Override // okhttp3.ResponseBody
    public MediaType contentType() {
        return this.mResponseBody.contentType();
    }

    @Override // okhttp3.ResponseBody
    public BufferedSource source() {
        if (this.mBufferedSource == null) {
            this.mBufferedSource = Okio.buffer(source(this.mResponseBody.source()));
        }
        return this.mBufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) { // from class: com.alibaba.sdk.android.oss.network.ProgressTouchableResponseBody.1
            private long totalBytesRead = 0;

            @Override // okio.ForwardingSource, okio.Source
            public long read(Buffer buffer, long j7) {
                long j8 = super.read(buffer, j7);
                this.totalBytesRead += j8 != -1 ? j8 : 0L;
                if (ProgressTouchableResponseBody.this.mProgressListener != null && j8 != -1 && this.totalBytesRead != 0) {
                    ProgressTouchableResponseBody.this.mProgressListener.onProgress(ProgressTouchableResponseBody.this.request, this.totalBytesRead, ProgressTouchableResponseBody.this.mResponseBody.contentLength());
                }
                return j8;
            }
        };
    }
}
