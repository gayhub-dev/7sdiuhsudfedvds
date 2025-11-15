package p102m2;

import com.ctvit.appupdate.entity.AppUpdateEntity;
import com.ctvit.network.callback.SimpleCallBack;
import com.ctvit.network.exception.ApiException;
import okhttp3.internal.cache.DiskLruCache;
import p110n2.InterfaceC1518b;
import p110n2.InterfaceC1519c;
import p110n2.InterfaceC1520d;
import p150s1.C1867d;
import p179w2.C2025a;
import p186x2.C2073a;

/* compiled from: GetUpdateInfo.java */
/* renamed from: m2.a */
/* loaded from: classes.dex */
public class C1459a extends SimpleCallBack<AppUpdateEntity> {

    /* renamed from: f */
    public final /* synthetic */ C1460b f4226f;

    public C1459a(C1460b c1460b) {
        this.f4226f = c1460b;
    }

    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    public void onError(ApiException apiException) {
        super.onError(apiException);
        if (isSuccess()) {
            return;
        }
        C2073a.m2458c(apiException);
        InterfaceC1518b interfaceC1518b = this.f4226f.f4229c;
        if (interfaceC1518b != null) {
            ((C1867d) interfaceC1518b).m2135a(InterfaceC1518b.a.REQUEST);
        }
    }

    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    public void onSuccess(Object obj) throws NumberFormatException {
        AppUpdateEntity appUpdateEntity = (AppUpdateEntity) obj;
        super.onSuccess(appUpdateEntity);
        try {
            AppUpdateEntity.AndroidBean android2 = appUpdateEntity.getAndroid();
            int i7 = Integer.parseInt(android2.getVersionCode());
            int iM2375c = C2025a.m2375c();
            C2073a.m2459d("目标版本：" + i7);
            C2073a.m2459d("当前版本：" + iM2375c);
            if (i7 <= iM2375c || !DiskLruCache.VERSION_1.equals(android2.getOpenFun())) {
                InterfaceC1519c interfaceC1519c = this.f4226f.f4228b;
                if (interfaceC1519c != null) {
                    ((C1867d) interfaceC1519c).m2136b(android2);
                }
            } else {
                String forced = appUpdateEntity.getForced();
                InterfaceC1520d interfaceC1520d = this.f4226f.f4227a;
                if (interfaceC1520d != null) {
                    ((C1867d) interfaceC1520d).m2137c(DiskLruCache.VERSION_1.equals(forced), android2);
                }
            }
        } catch (Exception e7) {
            C2073a.m2458c(e7);
            InterfaceC1518b interfaceC1518b = this.f4226f.f4229c;
            if (interfaceC1518b != null) {
                ((C1867d) interfaceC1518b).m2135a(InterfaceC1518b.a.PARSE);
            }
        }
    }
}
