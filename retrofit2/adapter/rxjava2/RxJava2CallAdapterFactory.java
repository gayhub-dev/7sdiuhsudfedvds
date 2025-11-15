package retrofit2.adapter.rxjava2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import javax.annotation.Nullable;
import p194y3.AbstractC2110b;
import p194y3.AbstractC2114f;
import p194y3.AbstractC2116h;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;
import p194y3.AbstractC2129u;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

/* loaded from: classes.dex */
public final class RxJava2CallAdapterFactory extends CallAdapter.Factory {
    private final boolean isAsync;

    @Nullable
    private final AbstractC2128t scheduler;

    private RxJava2CallAdapterFactory(@Nullable AbstractC2128t abstractC2128t, boolean z6) {
        this.scheduler = abstractC2128t;
        this.isAsync = z6;
    }

    public static RxJava2CallAdapterFactory create() {
        return new RxJava2CallAdapterFactory(null, false);
    }

    public static RxJava2CallAdapterFactory createAsync() {
        return new RxJava2CallAdapterFactory(null, true);
    }

    public static RxJava2CallAdapterFactory createWithScheduler(AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(abstractC2128t, "scheduler == null");
        return new RxJava2CallAdapterFactory(abstractC2128t, false);
    }

    @Override // retrofit2.CallAdapter.Factory
    @Nullable
    public CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        Type parameterUpperBound;
        boolean z6;
        boolean z7;
        Class<?> rawType = CallAdapter.Factory.getRawType(type);
        if (rawType == AbstractC2110b.class) {
            return new RxJava2CallAdapter(Void.class, this.scheduler, this.isAsync, false, true, false, false, false, true);
        }
        boolean z8 = rawType == AbstractC2114f.class;
        boolean z9 = rawType == AbstractC2129u.class;
        boolean z10 = rawType == AbstractC2116h.class;
        if (rawType != AbstractC2120l.class && !z8 && !z9 && !z10) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            String str = !z8 ? !z9 ? z10 ? "Maybe" : "Observable" : "Single" : "Flowable";
            throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
        }
        Type parameterUpperBound2 = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) type);
        Class<?> rawType2 = CallAdapter.Factory.getRawType(parameterUpperBound2);
        if (rawType2 == Response.class) {
            if (!(parameterUpperBound2 instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
            }
            parameterUpperBound = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound2);
            z6 = false;
        } else {
            if (rawType2 != Result.class) {
                parameterUpperBound = parameterUpperBound2;
                z6 = false;
                z7 = true;
                return new RxJava2CallAdapter(parameterUpperBound, this.scheduler, this.isAsync, z6, z7, z8, z9, z10, false);
            }
            if (!(parameterUpperBound2 instanceof ParameterizedType)) {
                throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
            }
            parameterUpperBound = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound2);
            z6 = true;
        }
        z7 = false;
        return new RxJava2CallAdapter(parameterUpperBound, this.scheduler, this.isAsync, z6, z7, z8, z9, z10, false);
    }
}
