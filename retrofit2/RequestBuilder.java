package retrofit2;

import android.arch.lifecycle.C0063n;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import p009b.C0413b;

/* loaded from: classes.dex */
final class RequestBuilder {
    private static final String PATH_SEGMENT_ALWAYS_ENCODE_SET = " \"<>^`{}|\\?#";
    private final HttpUrl baseUrl;

    @Nullable
    private RequestBody body;

    @Nullable
    private MediaType contentType;

    @Nullable
    private FormBody.Builder formBuilder;
    private final boolean hasBody;
    private final String method;

    @Nullable
    private MultipartBody.Builder multipartBuilder;

    @Nullable
    private String relativeUrl;
    private final Request.Builder requestBuilder;

    @Nullable
    private HttpUrl.Builder urlBuilder;
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final Pattern PATH_TRAVERSAL = Pattern.compile("(.*/)?(\\.|%2e|%2E){1,2}(/.*)?");

    public static class ContentTypeOverridingRequestBody extends RequestBody {
        private final MediaType contentType;
        private final RequestBody delegate;

        public ContentTypeOverridingRequestBody(RequestBody requestBody, MediaType mediaType) {
            this.delegate = requestBody;
            this.contentType = mediaType;
        }

        @Override // okhttp3.RequestBody
        public long contentLength() {
            return this.delegate.contentLength();
        }

        @Override // okhttp3.RequestBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) {
            this.delegate.writeTo(bufferedSink);
        }
    }

    public RequestBuilder(String str, HttpUrl httpUrl, @Nullable String str2, @Nullable Headers headers, @Nullable MediaType mediaType, boolean z6, boolean z7, boolean z8) {
        this.method = str;
        this.baseUrl = httpUrl;
        this.relativeUrl = str2;
        Request.Builder builder = new Request.Builder();
        this.requestBuilder = builder;
        this.contentType = mediaType;
        this.hasBody = z6;
        if (headers != null) {
            builder.headers(headers);
        }
        if (z7) {
            this.formBuilder = new FormBody.Builder();
        } else if (z8) {
            MultipartBody.Builder builder2 = new MultipartBody.Builder();
            this.multipartBuilder = builder2;
            builder2.setType(MultipartBody.FORM);
        }
    }

    private static String canonicalizeForPath(String str, boolean z6) {
        int length = str.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt < 32 || iCodePointAt >= 127 || PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(iCodePointAt) != -1 || (!z6 && (iCodePointAt == 47 || iCodePointAt == 37))) {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, 0, iCharCount);
                canonicalizeForPath(buffer, str, iCharCount, length, z6);
                return buffer.readUtf8();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str;
    }

    public void addFormField(String str, String str2, boolean z6) {
        if (z6) {
            this.formBuilder.addEncoded(str, str2);
        } else {
            this.formBuilder.add(str, str2);
        }
    }

    public void addHeader(String str, String str2) {
        if (!"Content-Type".equalsIgnoreCase(str)) {
            this.requestBuilder.addHeader(str, str2);
            return;
        }
        try {
            this.contentType = MediaType.get(str2);
        } catch (IllegalArgumentException e7) {
            throw new IllegalArgumentException(C0063n.m88a("Malformed content type: ", str2), e7);
        }
    }

    public void addPart(Headers headers, RequestBody requestBody) {
        this.multipartBuilder.addPart(headers, requestBody);
    }

    public void addPathParam(String str, String str2, boolean z6) {
        if (this.relativeUrl == null) {
            throw new AssertionError();
        }
        String strCanonicalizeForPath = canonicalizeForPath(str2, z6);
        String strReplace = this.relativeUrl.replace("{" + str + "}", strCanonicalizeForPath);
        if (PATH_TRAVERSAL.matcher(strReplace).matches()) {
            throw new IllegalArgumentException(C0063n.m88a("@Path parameters shouldn't perform path traversal ('.' or '..'): ", str2));
        }
        this.relativeUrl = strReplace;
    }

    public void addQueryParam(String str, @Nullable String str2, boolean z6) {
        String str3 = this.relativeUrl;
        if (str3 != null) {
            HttpUrl.Builder builderNewBuilder = this.baseUrl.newBuilder(str3);
            this.urlBuilder = builderNewBuilder;
            if (builderNewBuilder == null) {
                StringBuilder sbM112a = C0413b.m112a("Malformed URL. Base: ");
                sbM112a.append(this.baseUrl);
                sbM112a.append(", Relative: ");
                sbM112a.append(this.relativeUrl);
                throw new IllegalArgumentException(sbM112a.toString());
            }
            this.relativeUrl = null;
        }
        if (z6) {
            this.urlBuilder.addEncodedQueryParameter(str, str2);
        } else {
            this.urlBuilder.addQueryParameter(str, str2);
        }
    }

    public Request.Builder get() {
        HttpUrl httpUrlResolve;
        HttpUrl.Builder builder = this.urlBuilder;
        if (builder != null) {
            httpUrlResolve = builder.build();
        } else {
            httpUrlResolve = this.baseUrl.resolve(this.relativeUrl);
            if (httpUrlResolve == null) {
                StringBuilder sbM112a = C0413b.m112a("Malformed URL. Base: ");
                sbM112a.append(this.baseUrl);
                sbM112a.append(", Relative: ");
                sbM112a.append(this.relativeUrl);
                throw new IllegalArgumentException(sbM112a.toString());
            }
        }
        RequestBody contentTypeOverridingRequestBody = this.body;
        if (contentTypeOverridingRequestBody == null) {
            FormBody.Builder builder2 = this.formBuilder;
            if (builder2 != null) {
                contentTypeOverridingRequestBody = builder2.build();
            } else {
                MultipartBody.Builder builder3 = this.multipartBuilder;
                if (builder3 != null) {
                    contentTypeOverridingRequestBody = builder3.build();
                } else if (this.hasBody) {
                    contentTypeOverridingRequestBody = RequestBody.create((MediaType) null, new byte[0]);
                }
            }
        }
        MediaType mediaType = this.contentType;
        if (mediaType != null) {
            if (contentTypeOverridingRequestBody != null) {
                contentTypeOverridingRequestBody = new ContentTypeOverridingRequestBody(contentTypeOverridingRequestBody, mediaType);
            } else {
                this.requestBuilder.addHeader("Content-Type", mediaType.toString());
            }
        }
        return this.requestBuilder.url(httpUrlResolve).method(this.method, contentTypeOverridingRequestBody);
    }

    public void setBody(RequestBody requestBody) {
        this.body = requestBody;
    }

    public void setRelativeUrl(Object obj) {
        this.relativeUrl = obj.toString();
    }

    public void addPart(MultipartBody.Part part) {
        this.multipartBuilder.addPart(part);
    }

    private static void canonicalizeForPath(Buffer buffer, String str, int i7, int i8, boolean z6) {
        Buffer buffer2 = null;
        while (i7 < i8) {
            int iCodePointAt = str.codePointAt(i7);
            if (!z6 || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt >= 32 && iCodePointAt < 127 && PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(iCodePointAt) == -1 && (z6 || (iCodePointAt != 47 && iCodePointAt != 37))) {
                    buffer.writeUtf8CodePoint(iCodePointAt);
                } else {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    buffer2.writeUtf8CodePoint(iCodePointAt);
                    while (!buffer2.exhausted()) {
                        int i9 = buffer2.readByte() & 255;
                        buffer.writeByte(37);
                        char[] cArr = HEX_DIGITS;
                        buffer.writeByte((int) cArr[(i9 >> 4) & 15]);
                        buffer.writeByte((int) cArr[i9 & 15]);
                    }
                }
            }
            i7 += Character.charCount(iCodePointAt);
        }
    }
}
