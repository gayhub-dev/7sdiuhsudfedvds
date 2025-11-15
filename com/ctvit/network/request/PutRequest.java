package com.ctvit.network.request;

import com.ctvit.network.cache.model.CacheResult;
import com.ctvit.network.callback.CallBack;
import com.ctvit.network.callback.CallBackProxy;
import com.ctvit.network.callback.CallClazzProxy;
import com.ctvit.network.callback.SimpleCallBack;
import com.ctvit.network.func.ApiResultFunc;
import com.ctvit.network.func.CacheResultFunc;
import com.ctvit.network.func.RetryExceptionFunc;
import com.ctvit.network.model.ApiResult;
import com.ctvit.network.subsciber.CallBackSubsciber;
import com.ctvit.network.utils.RxUtils;
import com.google.gson.reflect.TypeToken;
import com.uber.autodispose.android.lifecycle.C0860a;
import java.lang.reflect.Type;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import p173v3.C2008d;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2126r;
import p201z3.InterfaceC2153b;

/* loaded from: classes.dex */
public class PutRequest extends BaseBodyRequest<PutRequest> {
    public PutRequest(String str) {
        super(str);
    }

    private <T> AbstractC2120l<CacheResult<T>> toObservable(AbstractC2120l abstractC2120l, CallBackProxy<? extends ApiResult<T>, T> callBackProxy) {
        return abstractC2120l.map(new ApiResultFunc(callBackProxy != null ? callBackProxy.getType() : new TypeToken<ResponseBody>() { // from class: com.ctvit.network.request.PutRequest.8
        }.getType())).compose(this.isSyncRequest ? RxUtils._main() : RxUtils._io_main()).compose(this.rxCache.transformer(this.cacheMode, callBackProxy.getCallBack().getType())).retryWhen(new RetryExceptionFunc(this.retryCount, this.retryDelay, this.retryIncreaseDelay));
    }

    public <T> InterfaceC2153b execute() {
        return execute(new CallBackProxy<ApiResult<T>, T>(new SimpleCallBack<T>() { // from class: com.ctvit.network.request.PutRequest.1
        }) { // from class: com.ctvit.network.request.PutRequest.2
        });
    }

    @Override // com.ctvit.network.request.BaseBodyRequest, com.ctvit.network.request.BaseRequest
    public AbstractC2120l<ResponseBody> generateRequest() {
        RequestBody requestBody = this.requestBody;
        if (requestBody != null) {
            return this.apiManager.putBody(this.url, requestBody);
        }
        if (this.json != null) {
            return this.apiManager.putJson(this.url, RequestBody.create(MediaType.parse("application/json; charset=utf-8"), this.json));
        }
        Object obj = this.object;
        if (obj != null) {
            return this.apiManager.putBody(this.url, obj);
        }
        String str = this.string;
        if (str == null) {
            return this.apiManager.put(this.url, this.params.urlParamsMap);
        }
        return this.apiManager.putBody(this.url, RequestBody.create(this.mediaType, str));
    }

    public <T> AbstractC2120l<T> execute(Class<T> cls) {
        return execute(new CallClazzProxy<ApiResult<T>, T>(cls) { // from class: com.ctvit.network.request.PutRequest.3
        });
    }

    public <T> AbstractC2120l<T> execute(Type type) {
        return execute(new CallClazzProxy<ApiResult<T>, T>(type) { // from class: com.ctvit.network.request.PutRequest.4
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> AbstractC2120l<T> execute(CallClazzProxy<? extends ApiResult<T>, T> callClazzProxy) {
        AbstractC2120l<T> abstractC2120l = (AbstractC2120l<T>) ((PutRequest) build()).generateRequest().map(new ApiResultFunc(callClazzProxy.getType())).compose(this.isSyncRequest ? RxUtils._main() : RxUtils._io_main()).compose(this.rxCache.transformer(this.cacheMode, callClazzProxy.getCallType())).retryWhen(new RetryExceptionFunc(this.retryCount, this.retryDelay, this.retryIncreaseDelay)).compose(new InterfaceC2126r() { // from class: com.ctvit.network.request.PutRequest.5
            @Override // p194y3.InterfaceC2126r
            public InterfaceC2125q apply(AbstractC2120l abstractC2120l2) {
                return abstractC2120l2.map(new CacheResultFunc());
            }
        });
        if (getLifecycle() != null) {
            abstractC2120l.m2559as(C2008d.m2346a(C0860a.m668a(getLifecycle(), getAutoDisposeEvent())));
        }
        return abstractC2120l;
    }

    public <T> InterfaceC2153b execute(CallBack<T> callBack) {
        return execute(new CallBackProxy<ApiResult<T>, T>(callBack) { // from class: com.ctvit.network.request.PutRequest.6
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> InterfaceC2153b execute(CallBackProxy<? extends ApiResult<T>, T> callBackProxy) {
        AbstractC2120l<CacheResult<T>> observable = ((PutRequest) build()).toObservable(generateRequest(), callBackProxy);
        if (getLifecycle() != null) {
            observable.m2559as(C2008d.m2346a(C0860a.m668a(getLifecycle(), getAutoDisposeEvent())));
        }
        if (CacheResult.class != callBackProxy.getCallBack().getRawType()) {
            return (InterfaceC2153b) observable.compose(new InterfaceC2126r<CacheResult<T>, T>() { // from class: com.ctvit.network.request.PutRequest.7
                @Override // p194y3.InterfaceC2126r
                public InterfaceC2125q<T> apply(AbstractC2120l<CacheResult<T>> abstractC2120l) {
                    return abstractC2120l.map(new CacheResultFunc());
                }
            }).subscribeWith(new CallBackSubsciber(callBackProxy.getCallBack()));
        }
        return (InterfaceC2153b) observable.subscribeWith(new CallBackSubsciber(callBackProxy.getCallBack()));
    }
}
