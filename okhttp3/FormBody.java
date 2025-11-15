package okhttp3;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;

/* loaded from: classes.dex */
public final class FormBody extends RequestBody {
    private static final MediaType CONTENT_TYPE = MediaType.get("application/x-www-form-urlencoded");
    private final List<String> encodedNames;
    private final List<String> encodedValues;

    public static final class Builder {
        private final Charset charset;
        private final List<String> names;
        private final List<String> values;

        public Builder() {
            this(null);
        }

        public Builder add(String str, String str2) {
            Objects.requireNonNull(str, "name == null");
            Objects.requireNonNull(str2, "value == null");
            this.names.add(HttpUrl.canonicalize(str, HttpUrl.FORM_ENCODE_SET, false, false, true, true, this.charset));
            this.values.add(HttpUrl.canonicalize(str2, HttpUrl.FORM_ENCODE_SET, false, false, true, true, this.charset));
            return this;
        }

        public Builder addEncoded(String str, String str2) {
            Objects.requireNonNull(str, "name == null");
            Objects.requireNonNull(str2, "value == null");
            this.names.add(HttpUrl.canonicalize(str, HttpUrl.FORM_ENCODE_SET, true, false, true, true, this.charset));
            this.values.add(HttpUrl.canonicalize(str2, HttpUrl.FORM_ENCODE_SET, true, false, true, true, this.charset));
            return this;
        }

        public FormBody build() {
            return new FormBody(this.names, this.values);
        }

        public Builder(Charset charset) {
            this.names = new ArrayList();
            this.values = new ArrayList();
            this.charset = charset;
        }
    }

    public FormBody(List<String> list, List<String> list2) {
        this.encodedNames = Util.immutableList(list);
        this.encodedValues = Util.immutableList(list2);
    }

    private long writeOrCountBytes(@Nullable BufferedSink bufferedSink, boolean z6) {
        Buffer buffer = z6 ? new Buffer() : bufferedSink.buffer();
        int size = this.encodedNames.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (i7 > 0) {
                buffer.writeByte(38);
            }
            buffer.writeUtf8(this.encodedNames.get(i7));
            buffer.writeByte(61);
            buffer.writeUtf8(this.encodedValues.get(i7));
        }
        if (!z6) {
            return 0L;
        }
        long size2 = buffer.size();
        buffer.clear();
        return size2;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        return writeOrCountBytes(null, true);
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return CONTENT_TYPE;
    }

    public String encodedName(int i7) {
        return this.encodedNames.get(i7);
    }

    public String encodedValue(int i7) {
        return this.encodedValues.get(i7);
    }

    public String name(int i7) {
        return HttpUrl.percentDecode(encodedName(i7), true);
    }

    public int size() {
        return this.encodedNames.size();
    }

    public String value(int i7) {
        return HttpUrl.percentDecode(encodedValue(i7), true);
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) {
        writeOrCountBytes(bufferedSink, false);
    }
}
