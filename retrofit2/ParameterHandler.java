package retrofit2;

import android.support.constraint.C0072a;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import p009b.C0413b;

/* loaded from: classes.dex */
abstract class ParameterHandler<T> {

    public static final class Body<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;

        public Body(Converter<T, RequestBody> converter) {
            this.converter = converter;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable T t6) {
            if (t6 == null) {
                throw new IllegalArgumentException("Body parameter value must not be null.");
            }
            try {
                requestBuilder.setBody(this.converter.convert(t6));
            } catch (IOException e7) {
                throw new RuntimeException("Unable to convert " + t6 + " to RequestBody", e7);
            }
        }
    }

    public static final class Field<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        public Field(String str, Converter<T, String> converter, boolean z6) {
            this.name = (String) Utils.checkNotNull(str, "name == null");
            this.valueConverter = converter;
            this.encoded = z6;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable T t6) {
            String strConvert;
            if (t6 == null || (strConvert = this.valueConverter.convert(t6)) == null) {
                return;
            }
            requestBuilder.addFormField(this.name, strConvert, this.encoded);
        }
    }

    public static final class FieldMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Converter<T, String> valueConverter;

        public FieldMap(Converter<T, String> converter, boolean z6) {
            this.valueConverter = converter;
            this.encoded = z6;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable Map<String, T> map) {
            if (map == null) {
                throw new IllegalArgumentException("Field map was null.");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw new IllegalArgumentException("Field map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException(C0096a.m97a("Field map contained null value for key '", key, "'."));
                }
                String strConvert = this.valueConverter.convert(value);
                if (strConvert == null) {
                    throw new IllegalArgumentException("Field map value '" + value + "' converted to null by " + this.valueConverter.getClass().getName() + " for key '" + key + "'.");
                }
                requestBuilder.addFormField(key, strConvert, this.encoded);
            }
        }
    }

    public static final class Header<T> extends ParameterHandler<T> {
        private final String name;
        private final Converter<T, String> valueConverter;

        public Header(String str, Converter<T, String> converter) {
            this.name = (String) Utils.checkNotNull(str, "name == null");
            this.valueConverter = converter;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable T t6) {
            String strConvert;
            if (t6 == null || (strConvert = this.valueConverter.convert(t6)) == null) {
                return;
            }
            requestBuilder.addHeader(this.name, strConvert);
        }
    }

    public static final class HeaderMap<T> extends ParameterHandler<Map<String, T>> {
        private final Converter<T, String> valueConverter;

        public HeaderMap(Converter<T, String> converter) {
            this.valueConverter = converter;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable Map<String, T> map) {
            if (map == null) {
                throw new IllegalArgumentException("Header map was null.");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw new IllegalArgumentException("Header map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException(C0096a.m97a("Header map contained null value for key '", key, "'."));
                }
                requestBuilder.addHeader(key, this.valueConverter.convert(value));
            }
        }
    }

    public static final class Part<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;
        private final Headers headers;

        public Part(Headers headers, Converter<T, RequestBody> converter) {
            this.headers = headers;
            this.converter = converter;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable T t6) {
            if (t6 == null) {
                return;
            }
            try {
                requestBuilder.addPart(this.headers, this.converter.convert(t6));
            } catch (IOException e7) {
                throw new RuntimeException("Unable to convert " + t6 + " to RequestBody", e7);
            }
        }
    }

    public static final class PartMap<T> extends ParameterHandler<Map<String, T>> {
        private final String transferEncoding;
        private final Converter<T, RequestBody> valueConverter;

        public PartMap(Converter<T, RequestBody> converter, String str) {
            this.valueConverter = converter;
            this.transferEncoding = str;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable Map<String, T> map) {
            if (map == null) {
                throw new IllegalArgumentException("Part map was null.");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw new IllegalArgumentException("Part map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException(C0096a.m97a("Part map contained null value for key '", key, "'."));
                }
                requestBuilder.addPart(Headers.m1865of("Content-Disposition", C0096a.m97a("form-data; name=\"", key, "\""), "Content-Transfer-Encoding", this.transferEncoding), this.valueConverter.convert(value));
            }
        }
    }

    public static final class Path<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        public Path(String str, Converter<T, String> converter, boolean z6) {
            this.name = (String) Utils.checkNotNull(str, "name == null");
            this.valueConverter = converter;
            this.encoded = z6;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable T t6) {
            if (t6 == null) {
                throw new IllegalArgumentException(C0072a.m92a(C0413b.m112a("Path parameter \""), this.name, "\" value must not be null."));
            }
            requestBuilder.addPathParam(this.name, this.valueConverter.convert(t6), this.encoded);
        }
    }

    public static final class Query<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        public Query(String str, Converter<T, String> converter, boolean z6) {
            this.name = (String) Utils.checkNotNull(str, "name == null");
            this.valueConverter = converter;
            this.encoded = z6;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable T t6) {
            String strConvert;
            if (t6 == null || (strConvert = this.valueConverter.convert(t6)) == null) {
                return;
            }
            requestBuilder.addQueryParam(this.name, strConvert, this.encoded);
        }
    }

    public static final class QueryMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Converter<T, String> valueConverter;

        public QueryMap(Converter<T, String> converter, boolean z6) {
            this.valueConverter = converter;
            this.encoded = z6;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable Map<String, T> map) {
            if (map == null) {
                throw new IllegalArgumentException("Query map was null.");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw new IllegalArgumentException("Query map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException(C0096a.m97a("Query map contained null value for key '", key, "'."));
                }
                String strConvert = this.valueConverter.convert(value);
                if (strConvert == null) {
                    throw new IllegalArgumentException("Query map value '" + value + "' converted to null by " + this.valueConverter.getClass().getName() + " for key '" + key + "'.");
                }
                requestBuilder.addQueryParam(key, strConvert, this.encoded);
            }
        }
    }

    public static final class QueryName<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final Converter<T, String> nameConverter;

        public QueryName(Converter<T, String> converter, boolean z6) {
            this.nameConverter = converter;
            this.encoded = z6;
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable T t6) {
            if (t6 == null) {
                return;
            }
            requestBuilder.addQueryParam(this.nameConverter.convert(t6), null, this.encoded);
        }
    }

    public static final class RawPart extends ParameterHandler<MultipartBody.Part> {
        public static final RawPart INSTANCE = new RawPart();

        private RawPart() {
        }

        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable MultipartBody.Part part) {
            if (part != null) {
                requestBuilder.addPart(part);
            }
        }
    }

    public static final class RelativeUrl extends ParameterHandler<Object> {
        @Override // retrofit2.ParameterHandler
        public void apply(RequestBuilder requestBuilder, @Nullable Object obj) {
            Utils.checkNotNull(obj, "@Url parameter is null.");
            requestBuilder.setRelativeUrl(obj);
        }
    }

    public abstract void apply(RequestBuilder requestBuilder, @Nullable T t6);

    public final ParameterHandler<Object> array() {
        return new ParameterHandler<Object>() { // from class: retrofit2.ParameterHandler.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // retrofit2.ParameterHandler
            public void apply(RequestBuilder requestBuilder, @Nullable Object obj) {
                if (obj == null) {
                    return;
                }
                int length = Array.getLength(obj);
                for (int i7 = 0; i7 < length; i7++) {
                    ParameterHandler.this.apply(requestBuilder, Array.get(obj, i7));
                }
            }
        };
    }

    public final ParameterHandler<Iterable<T>> iterable() {
        return new ParameterHandler<Iterable<T>>() { // from class: retrofit2.ParameterHandler.1
            @Override // retrofit2.ParameterHandler
            public void apply(RequestBuilder requestBuilder, @Nullable Iterable<T> iterable) {
                if (iterable == null) {
                    return;
                }
                Iterator<T> it = iterable.iterator();
                while (it.hasNext()) {
                    ParameterHandler.this.apply(requestBuilder, it.next());
                }
            }
        };
    }
}
