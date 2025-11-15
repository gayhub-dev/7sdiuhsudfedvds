package com.ctvit.network.request;

import com.ctvit.network.callback.CallBack;
import com.ctvit.network.callback.DownloadProgressCallBack;
import com.ctvit.network.func.RetryExceptionFunc;
import com.ctvit.network.subsciber.DownloadSubscriber;
import com.ctvit.network.transformer.HandleErrTransformer;
import okhttp3.ResponseBody;
import p174v4.C2012a;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2126r;
import p201z3.InterfaceC2153b;

/* loaded from: classes.dex */
public class DownloadRequest extends BaseRequest<DownloadRequest> {
    private String saveName;
    private String savePath;

    /* renamed from: com.ctvit.network.request.DownloadRequest$1 */
    public class C06661 extends DownloadProgressCallBack<String> {
        public C06661() {
        }

        @Override // com.ctvit.network.callback.DownloadProgressCallBack
        public void onComplete(String str) {
        }

        @Override // com.ctvit.network.callback.DownloadProgressCallBack
        public void update(long j7, long j8, boolean z6) {
        }
    }

    /* renamed from: com.ctvit.network.request.DownloadRequest$2 */
    public class C06672 implements InterfaceC2126r<ResponseBody, ResponseBody> {
        public C06672() {
        }

        @Override // p194y3.InterfaceC2126r
        public InterfaceC2125q<ResponseBody> apply(AbstractC2120l<ResponseBody> abstractC2120l) {
            if (DownloadRequest.this.isSyncRequest) {
                return abstractC2120l;
            }
            AbstractC2128t abstractC2128t = C2012a.f5854b;
            return abstractC2120l.subscribeOn(abstractC2128t).unsubscribeOn(abstractC2128t).observeOn(C2012a.f5853a);
        }
    }

    public DownloadRequest(String str) {
        super(str);
    }

    public InterfaceC2153b execute() {
        return execute(new DownloadProgressCallBack<String>() { // from class: com.ctvit.network.request.DownloadRequest.1
            public C06661() {
            }

            @Override // com.ctvit.network.callback.DownloadProgressCallBack
            public void onComplete(String str) {
            }

            @Override // com.ctvit.network.callback.DownloadProgressCallBack
            public void update(long j7, long j8, boolean z6) {
            }
        });
    }

    @Override // com.ctvit.network.request.BaseRequest
    public AbstractC2120l<ResponseBody> generateRequest() {
        return this.apiManager.downloadFile(this.url);
    }

    public DownloadRequest saveName(String str) {
        this.saveName = str;
        return this;
    }

    public DownloadRequest savePath(String str) {
        this.savePath = str;
        return this;
    }

    public <T> InterfaceC2153b execute(CallBack<T> callBack) {
        return (InterfaceC2153b) build().generateRequest().compose(new InterfaceC2126r<ResponseBody, ResponseBody>() { // from class: com.ctvit.network.request.DownloadRequest.2
            public C06672() {
            }

            @Override // p194y3.InterfaceC2126r
            public InterfaceC2125q<ResponseBody> apply(AbstractC2120l<ResponseBody> abstractC2120l) {
                if (DownloadRequest.this.isSyncRequest) {
                    return abstractC2120l;
                }
                AbstractC2128t abstractC2128t = C2012a.f5854b;
                return abstractC2120l.subscribeOn(abstractC2128t).unsubscribeOn(abstractC2128t).observeOn(C2012a.f5853a);
            }
        }).compose(new HandleErrTransformer()).retryWhen(new RetryExceptionFunc(this.retryCount, this.retryDelay, this.retryIncreaseDelay)).subscribeWith(new DownloadSubscriber(this.savePath, this.saveName, callBack));
    }
}
