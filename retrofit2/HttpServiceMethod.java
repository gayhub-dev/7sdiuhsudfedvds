package retrofit2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import okhttp3.Call;
import okhttp3.ResponseBody;
import p009b.C0413b;

/* loaded from: classes.dex */
final class HttpServiceMethod<ResponseT, ReturnT> extends ServiceMethod<ReturnT> {
    private final CallAdapter<ResponseT, ReturnT> callAdapter;
    private final Call.Factory callFactory;
    private final RequestFactory requestFactory;
    private final Converter<ResponseBody, ResponseT> responseConverter;

    private HttpServiceMethod(RequestFactory requestFactory, Call.Factory factory, CallAdapter<ResponseT, ReturnT> callAdapter, Converter<ResponseBody, ResponseT> converter) {
        this.requestFactory = requestFactory;
        this.callFactory = factory;
        this.callAdapter = callAdapter;
        this.responseConverter = converter;
    }

    private static <ResponseT, ReturnT> CallAdapter<ResponseT, ReturnT> createCallAdapter(Retrofit retrofit, Method method) {
        Type genericReturnType = method.getGenericReturnType();
        try {
            return (CallAdapter<ResponseT, ReturnT>) retrofit.callAdapter(genericReturnType, method.getAnnotations());
        } catch (RuntimeException e7) {
            throw Utils.methodError(method, e7, "Unable to create call adapter for %s", genericReturnType);
        }
    }

    private static <ResponseT> Converter<ResponseBody, ResponseT> createResponseConverter(Retrofit retrofit, Method method, Type type) {
        try {
            return retrofit.responseBodyConverter(type, method.getAnnotations());
        } catch (RuntimeException e7) {
            throw Utils.methodError(method, e7, "Unable to create converter for %s", type);
        }
    }

    public static <ResponseT, ReturnT> HttpServiceMethod<ResponseT, ReturnT> parseAnnotations(Retrofit retrofit, Method method, RequestFactory requestFactory) {
        CallAdapter callAdapterCreateCallAdapter = createCallAdapter(retrofit, method);
        Type typeResponseType = callAdapterCreateCallAdapter.responseType();
        if (typeResponseType == Response.class || typeResponseType == okhttp3.Response.class) {
            StringBuilder sbM112a = C0413b.m112a("'");
            sbM112a.append(Utils.getRawType(typeResponseType).getName());
            sbM112a.append("' is not a valid response body type. Did you mean ResponseBody?");
            throw Utils.methodError(method, sbM112a.toString(), new Object[0]);
        }
        if (requestFactory.httpMethod.equals("HEAD") && !Void.class.equals(typeResponseType)) {
            throw Utils.methodError(method, "HEAD method must use Void as response type.", new Object[0]);
        }
        return new HttpServiceMethod<>(requestFactory, retrofit.callFactory, callAdapterCreateCallAdapter, createResponseConverter(retrofit, method, typeResponseType));
    }

    @Override // retrofit2.ServiceMethod
    public ReturnT invoke(Object[] objArr) {
        return this.callAdapter.adapt(new OkHttpCall(this.requestFactory, objArr, this.callFactory, this.responseConverter));
    }
}
