package com.ctvit.network.body;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class UploadProgressRequestBody extends RequestBody {
    public CountingSink countingSink;
    public RequestBody delegate;
    public ProgressResponseCallBack progressCallBack;

    public final class CountingSink extends ForwardingSink {
        private long bytesWritten;
        private long contentLength;
        private long lastRefreshUiTime;

        public CountingSink(Sink sink) {
            super(sink);
            this.bytesWritten = 0L;
            this.contentLength = 0L;
        }

        @Override // okio.ForwardingSink, okio.Sink
        public void write(Buffer buffer, long j7) {
            super.write(buffer, j7);
            if (this.contentLength <= 0) {
                this.contentLength = UploadProgressRequestBody.this.contentLength();
            }
            this.bytesWritten += j7;
            if (System.currentTimeMillis() - this.lastRefreshUiTime >= 100 || this.bytesWritten == this.contentLength) {
                ProgressResponseCallBack progressResponseCallBack = UploadProgressRequestBody.this.progressCallBack;
                long j8 = this.bytesWritten;
                long j9 = this.contentLength;
                progressResponseCallBack.onResponseProgress(j8, j9, j8 == j9);
                this.lastRefreshUiTime = System.currentTimeMillis();
            }
        }
    }

    public UploadProgressRequestBody(ProgressResponseCallBack progressResponseCallBack) {
        this.progressCallBack = progressResponseCallBack;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        try {
            return this.delegate.contentLength();
        } catch (IOException e7) {
            C2073a.m2456a(e7.getMessage());
            return -1L;
        }
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.delegate.contentType();
    }

    public void setRequestBody(RequestBody requestBody) {
        this.delegate = requestBody;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) {
        CountingSink countingSink = new CountingSink(bufferedSink);
        this.countingSink = countingSink;
        BufferedSink bufferedSinkBuffer = Okio.buffer(countingSink);
        this.delegate.writeTo(bufferedSinkBuffer);
        bufferedSinkBuffer.flush();
    }

    public UploadProgressRequestBody(RequestBody requestBody, ProgressResponseCallBack progressResponseCallBack) {
        this.delegate = requestBody;
        this.progressCallBack = progressResponseCallBack;
    }
}
