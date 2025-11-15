package p012b2;

import com.cctv.p025tv.entity.MainEntity;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.callback.SimpleCallBack;
import com.ctvit.network.request.PostRequest;
import java.lang.ref.Reference;
import java.util.Objects;
import p003a2.AbstractC0007b;
import p003a2.InterfaceC0006a;
import p043f.C0984a;

/* compiled from: MainPresenter.java */
/* renamed from: b2.a */
/* loaded from: classes.dex */
public class C0438a extends AbstractC0007b {

    /* renamed from: b */
    public C0984a f271b = new C0984a(9);

    /* compiled from: MainPresenter.java */
    /* renamed from: b2.a$a */
    public class a extends SimpleCallBack<MainEntity> {

        /* renamed from: f */
        public final /* synthetic */ InterfaceC0006a f272f;

        public a(C0438a c0438a, InterfaceC0006a interfaceC0006a) {
            this.f272f = interfaceC0006a;
        }

        @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
        public void onCompleted() {
            super.onCompleted();
            this.f272f.mo6d();
        }

        @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
        public void onSuccess(Object obj) {
            MainEntity.DataBean data;
            MainEntity mainEntity = (MainEntity) obj;
            super.onSuccess(mainEntity);
            String result = mainEntity.getResult();
            String message = mainEntity.getMessage();
            if ("0".equals(result) && "SUCCESS".equals(message) && (data = mainEntity.getData()) != null) {
                this.f272f.mo5a(data);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p003a2.AbstractC0007b
    /* renamed from: e */
    public void mo7e() {
        InterfaceC0006a interfaceC0006a = (InterfaceC0006a) (m2514c() ? ((Reference) this.f6179a).get() : null);
        if (interfaceC0006a == null) {
            return;
        }
        C0984a c0984a = this.f271b;
        a aVar = new a(this, interfaceC0006a);
        Objects.requireNonNull(c0984a);
        ((PostRequest) CtvitHttp.post("https://ytpaddr.cctv.cn/gsnw/index").upJson(c0984a.m944d()).cacheMode(CacheMode.NO_CACHE)).execute(aVar);
    }
}
