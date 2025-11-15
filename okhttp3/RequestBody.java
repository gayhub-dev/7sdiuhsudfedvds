package okhttp3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;

/* loaded from: classes.dex */
public abstract class RequestBody {
    public static RequestBody create(@Nullable MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null) {
            Charset charset2 = mediaType.charset();
            if (charset2 == null) {
                mediaType = MediaType.parse(mediaType + "; charset=utf-8");
            } else {
                charset = charset2;
            }
        }
        return create(mediaType, str.getBytes(charset));
    }

    public long contentLength() {
        return -1L;
    }

    @Nullable
    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink);

    public static RequestBody create(@Nullable final MediaType mediaType, final ByteString byteString) {
        return new RequestBody() { // from class: okhttp3.RequestBody.1
            @Override // okhttp3.RequestBody
            public long contentLength() {
                return byteString.size();
            }

            @Override // okhttp3.RequestBody
            @Nullable
            public MediaType contentType() {
                return mediaType;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) {
                bufferedSink.write(byteString);
            }
        };
    }

    public static RequestBody create(@Nullable MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr, 0, bArr.length);
    }

    public static RequestBody create(@Nullable final MediaType mediaType, final byte[] bArr, final int i7, final int i8) {
        Objects.requireNonNull(bArr, "content == null");
        Util.checkOffsetAndCount(bArr.length, i7, i8);
        return new RequestBody() { // from class: okhttp3.RequestBody.2
            @Override // okhttp3.RequestBody
            public long contentLength() {
                return i8;
            }

            @Override // okhttp3.RequestBody
            @Nullable
            public MediaType contentType() {
                return mediaType;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) {
                bufferedSink.write(bArr, i7, i8);
            }
        };
    }

    public static RequestBody create(@Nullable final MediaType mediaType, final File file) {
        Objects.requireNonNull(file, "file == null");
        return new RequestBody() { // from class: okhttp3.RequestBody.3
            @Override // okhttp3.RequestBody
            public long contentLength() {
                return file.length();
            }

            @Override // okhttp3.RequestBody
            @Nullable
            public MediaType contentType() {
                return mediaType;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(file);
                    bufferedSink.writeAll(source);
                } finally {
                    Util.closeQuietly(source);
                }
            }
        };
    }
}
