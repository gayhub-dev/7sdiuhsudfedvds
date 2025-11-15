package com.ctvit.network.request;

import com.ctvit.network.cache.model.CacheResult;
import com.ctvit.network.callback.CallBack;
import com.ctvit.network.callback.CallBackProxy;
import com.ctvit.network.func.ApiResultFunc;
import com.ctvit.network.func.CacheResultFunc;
import com.ctvit.network.func.HandleFuc;
import com.ctvit.network.func.RetryExceptionFunc;
import com.ctvit.network.model.ApiResult;
import com.ctvit.network.subsciber.CallBackSubsciber;
import com.ctvit.network.transformer.HandleErrTransformer;
import com.ctvit.network.utils.RxUtils;
import com.ctvit.network.utils.Utils;
import com.google.gson.reflect.TypeToken;
import com.uber.autodispose.android.lifecycle.C0860a;
import okhttp3.ResponseBody;
import p173v3.C2008d;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2126r;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* loaded from: classes.dex */
public class CustomRequest extends BaseRequest<CustomRequest> {
    public CustomRequest() {
        super("");
    }

    private void checkvalidate() {
        Utils.checkNotNull(this.retrofit, "请先在调用build()才能使用");
    }

    private <T> AbstractC2120l<CacheResult<T>> toObservable(AbstractC2120l abstractC2120l, CallBackProxy<? extends ApiResult<T>, T> callBackProxy) {
        return abstractC2120l.map(new ApiResultFunc(callBackProxy != null ? callBackProxy.getType() : new TypeToken<ResponseBody>() { // from class: com.ctvit.network.request.CustomRequest.3
        }.getType())).compose(this.isSyncRequest ? RxUtils._main() : RxUtils._io_main()).compose(this.rxCache.transformer(this.cacheMode, callBackProxy.getCallBack().getType())).retryWhen(new RetryExceptionFunc(this.retryCount, this.retryDelay, this.retryIncreaseDelay));
    }

    public <T> AbstractC2120l<T> apiCall(AbstractC2120l<ApiResult<T>> abstractC2120l) {
        checkvalidate();
        if (getLifecycle() != null) {
            abstractC2120l.m2559as(C2008d.m2346a(C0860a.m668a(getLifecycle(), getAutoDisposeEvent())));
        }
        return abstractC2120l.map(new HandleFuc()).compose(RxUtils.io_main()).compose(new HandleErrTransformer()).retryWhen(new RetryExceptionFunc(this.retryCount, this.retryDelay, this.retryIncreaseDelay));
    }

    public <T> AbstractC2120l<T> call(AbstractC2120l<T> abstractC2120l) {
        checkvalidate();
        if (getLifecycle() != null) {
            abstractC2120l.m2559as(C2008d.m2346a(C0860a.m668a(getLifecycle(), getAutoDisposeEvent())));
        }
        return abstractC2120l.compose(RxUtils.io_main()).compose(new HandleErrTransformer()).retryWhen(new RetryExceptionFunc(this.retryCount, this.retryDelay, this.retryIncreaseDelay));
    }

    public <T> T create(Class<T> cls) {
        checkvalidate();
        return (T) this.retrofit.create(cls);
    }

    @Override // com.ctvit.network.request.BaseRequest
    public AbstractC2120l<ResponseBody> generateRequest() {
        return null;
    }

    @Override // com.ctvit.network.request.BaseRequest
    public CustomRequest build() {
        return (CustomRequest) super.build();
    }

    public <T> void call(AbstractC2120l<T> abstractC2120l, CallBack<T> callBack) {
        call(abstractC2120l, new CallBackSubsciber(callBack));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <R> void call(AbstractC2120l abstractC2120l, InterfaceC2127s<R> interfaceC2127s) {
        if (getLifecycle() != null) {
            abstractC2120l.m2559as(C2008d.m2346a(C0860a.m668a(getLifecycle(), getAutoDisposeEvent())));
        }
        abstractC2120l.compose(RxUtils.io_main()).subscribe((InterfaceC2127s<? super R>) interfaceC2127s);
    }

    public <T> InterfaceC2153b apiCall(AbstractC2120l<T> abstractC2120l, CallBack<T> callBack) {
        return call(abstractC2120l, new CallBackProxy<ApiResult<T>, T>(callBack) { // from class: com.ctvit.network.request.CustomRequest.1
        });
    }

    public <T> InterfaceC2153b call(AbstractC2120l<T> abstractC2120l, CallBackProxy<? extends ApiResult<T>, T> callBackProxy) {
        AbstractC2120l<CacheResult<T>> observable = build().toObservable(abstractC2120l, callBackProxy);
        if (getLifecycle() != null) {
            observable.m2559as(C2008d.m2346a(C0860a.m668a(getLifecycle(), getAutoDisposeEvent())));
        }
        if (CacheResult.class != callBackProxy.getCallBack().getRawType()) {
            return (InterfaceC2153b) observable.compose(new InterfaceC2126r<CacheResult<T>, T>() { // from class: com.ctvit.network.request.CustomRequest.2
                @Override // p194y3.InterfaceC2126r
                public InterfaceC2125q<T> apply(AbstractC2120l<CacheResult<T>> abstractC2120l2) {
                    return abstractC2120l2.map(new CacheResultFunc());
                }
            }).subscribeWith(new CallBackSubsciber(callBackProxy.getCallBack()));
        }
        return (InterfaceC2153b) observable.subscribeWith(new CallBackSubsciber(callBackProxy.getCallBack()));
    }
}
