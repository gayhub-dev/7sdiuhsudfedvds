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
import okhttp3.ResponseBody;
import p173v3.C2008d;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2126r;
import p201z3.InterfaceC2153b;

/* loaded from: classes.dex */
public class GetRequest extends BaseRequest<GetRequest> {

    /* renamed from: com.ctvit.network.request.GetRequest$1 */
    public class C06681<T> extends SimpleCallBack<T> {
        public C06681() {
        }
    }

    /* renamed from: com.ctvit.network.request.GetRequest$2 */
    public class C06692<T> extends CallBackProxy<ApiResult<T>, T> {
        public C06692(CallBack callBack) {
            super(callBack);
        }
    }

    /* renamed from: com.ctvit.network.request.GetRequest$3 */
    public class C06703<T> extends CallClazzProxy<ApiResult<T>, T> {
        public C06703(Type type) {
            super(type);
        }
    }

    /* renamed from: com.ctvit.network.request.GetRequest$4 */
    public class C06714<T> extends CallClazzProxy<ApiResult<T>, T> {
        public C06714(Type type) {
            super(type);
        }
    }

    /* renamed from: com.ctvit.network.request.GetRequest$5 */
    public class C06725 implements InterfaceC2126r {
        public C06725() {
        }

        @Override // p194y3.InterfaceC2126r
        public InterfaceC2125q apply(AbstractC2120l abstractC2120l) {
            return abstractC2120l.map(new CacheResultFunc());
        }
    }

    /* renamed from: com.ctvit.network.request.GetRequest$6 */
    public class C06736<T> extends CallBackProxy<ApiResult<T>, T> {
        public C06736(CallBack callBack) {
            super(callBack);
        }
    }

    /* renamed from: com.ctvit.network.request.GetRequest$7 */
    public class C06747<T> implements InterfaceC2126r<CacheResult<T>, T> {
        public C06747() {
        }

        @Override // p194y3.InterfaceC2126r
        public InterfaceC2125q<T> apply(AbstractC2120l<CacheResult<T>> abstractC2120l) {
            return abstractC2120l.map(new CacheResultFunc());
        }
    }

    /* renamed from: com.ctvit.network.request.GetRequest$8 */
    public class C06758 extends TypeToken<ResponseBody> {
        public C06758() {
        }
    }

    public GetRequest(String str) {
        super(str);
    }

    private <T> AbstractC2120l<CacheResult<T>> toObservable(AbstractC2120l abstractC2120l, CallBackProxy<? extends ApiResult<T>, T> callBackProxy) {
        return abstractC2120l.map(new ApiResultFunc(callBackProxy != null ? callBackProxy.getType() : new TypeToken<ResponseBody>() { // from class: com.ctvit.network.request.GetRequest.8
            public C06758() {
            }
        }.getType())).compose(this.isSyncRequest ? RxUtils._main() : RxUtils._io_main()).compose(this.rxCache.transformer(this.cacheMode, callBackProxy.getCallBack().getType())).retryWhen(new RetryExceptionFunc(this.retryCount, this.retryDelay, this.retryIncreaseDelay));
    }

    public <T> InterfaceC2153b execute() {
        return execute(new CallBackProxy<ApiResult<T>, T>(new SimpleCallBack<T>() { // from class: com.ctvit.network.request.GetRequest.1
            public C06681() {
            }
        }) { // from class: com.ctvit.network.request.GetRequest.2
            public C06692(CallBack callBack) {
                super(callBack);
            }
        });
    }

    @Override // com.ctvit.network.request.BaseRequest
    public AbstractC2120l<ResponseBody> generateRequest() {
        return this.apiManager.get(this.url, this.params.urlParamsMap);
    }

    public <T> AbstractC2120l<T> execute(Class<T> cls) {
        return execute(new CallClazzProxy<ApiResult<T>, T>(cls) { // from class: com.ctvit.network.request.GetRequest.3
            public C06703(Type cls2) {
                super(cls2);
            }
        });
    }

    public <T> AbstractC2120l<T> execute(Type type) {
        return execute(new CallClazzProxy<ApiResult<T>, T>(type) { // from class: com.ctvit.network.request.GetRequest.4
            public C06714(Type type2) {
                super(type2);
            }
        });
    }

    public <T> AbstractC2120l<T> execute(CallClazzProxy<? extends ApiResult<T>, T> callClazzProxy) {
        AbstractC2120l<T> abstractC2120l = (AbstractC2120l<T>) build().generateRequest().map(new ApiResultFunc(callClazzProxy.getType())).compose(this.isSyncRequest ? RxUtils._main() : RxUtils._io_main()).compose(this.rxCache.transformer(this.cacheMode, callClazzProxy.getCallType())).retryWhen(new RetryExceptionFunc(this.retryCount, this.retryDelay, this.retryIncreaseDelay)).compose(new InterfaceC2126r() { // from class: com.ctvit.network.request.GetRequest.5
            public C06725() {
            }

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
        return execute(new CallBackProxy<ApiResult<T>, T>(callBack) { // from class: com.ctvit.network.request.GetRequest.6
            public C06736(CallBack callBack2) {
                super(callBack2);
            }
        });
    }

    public <T> InterfaceC2153b execute(CallBackProxy<? extends ApiResult<T>, T> callBackProxy) {
        AbstractC2120l<CacheResult<T>> observable = build().toObservable(this.apiManager.get(this.url, this.params.urlParamsMap), callBackProxy);
        if (getLifecycle() != null) {
            observable.m2559as(C2008d.m2346a(C0860a.m668a(getLifecycle(), getAutoDisposeEvent())));
        }
        if (CacheResult.class != callBackProxy.getCallBack().getRawType()) {
            return (InterfaceC2153b) observable.compose(new InterfaceC2126r<CacheResult<T>, T>() { // from class: com.ctvit.network.request.GetRequest.7
                public C06747() {
                }

                @Override // p194y3.InterfaceC2126r
                public InterfaceC2125q<T> apply(AbstractC2120l<CacheResult<T>> abstractC2120l) {
                    return abstractC2120l.map(new CacheResultFunc());
                }
            }).subscribeWith(new CallBackSubsciber(callBackProxy.getCallBack()));
        }
        return (InterfaceC2153b) observable.subscribeWith(new CallBackSubsciber(callBackProxy.getCallBack()));
    }
}
