package com.ctvit.network.body;

import java.io.IOException;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/* loaded from: classes.dex */
public class RequestBodyUtils {
    public static RequestBody create(final MediaType mediaType, final InputStream inputStream) {
        return new RequestBody() { // from class: com.ctvit.network.body.RequestBodyUtils.1
            @Override // okhttp3.RequestBody
            public long contentLength() {
                try {
                    return inputStream.available();
                } catch (IOException unused) {
                    return 0L;
                }
            }

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return mediaType;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(inputStream);
                    bufferedSink.writeAll(source);
                } finally {
                    Util.closeQuietly(source);
                }
            }
        };
    }
}
