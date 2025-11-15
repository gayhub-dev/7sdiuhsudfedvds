package p192y1;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.alibaba.fastjson.JSON;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.module.service.WebSocketService;
import com.ctvit.dlna.entity.DlnaContentEntity;
import com.tencent.mars.xlog.Log;
import java.util.Objects;
import p014b4.InterfaceC0455o;
import p031d3.InterfaceC0868a;
import p078j2.C1189d;
import p085k1.C1229c;
import p118o2.C1581b;
import p136q2.InterfaceC1761a;
import p144r2.C1830c;
import p186x2.C2073a;
import p187x3.C2080f;

/* renamed from: y1.h */
/* loaded from: classes.dex */
public final /* synthetic */ class C2104h implements InterfaceC0868a, InterfaceC1761a, InterfaceC0455o {

    /* renamed from: e */
    public final /* synthetic */ Object f6238e;

    public /* synthetic */ C2104h(Context context) {
        this.f6238e = context;
    }

    public /* synthetic */ C2104h(WebSocketService webSocketService) {
        this.f6238e = webSocketService;
    }

    public /* synthetic */ C2104h(Object obj) {
        this.f6238e = obj;
    }

    @Override // p136q2.InterfaceC1761a
    /* renamed from: a */
    public void mo318a(DlnaContentEntity dlnaContentEntity) {
        Context context = (Context) this.f6238e;
        C2073a.m2459d("接收到投屏指令 - 后台");
        Log.m655i("XLog_DLNA ", "DlnaServiceUtils 接收到投屏指令 - 后台");
        Log.m655i("XLog_DLNA ", "DlnaServiceUtils 接收到投屏指令 - 后台 entity = " + JSON.toJSONString(dlnaContentEntity));
        if (!C1189d.m1402d(MyApplication.f561e) || !MyApplication.f570n) {
            C1229c.f2759a = "recall";
            new Handler(Looper.getMainLooper()).post(new RunnableC2099c(dlnaContentEntity, context));
            return;
        }
        MyApplication.f569m = dlnaContentEntity;
        Log.m655i("XLog_DLNA ", "华为渠道 打开了悬浮窗授权页面 接收到投屏指令 - 后台");
        if (C1581b.m1833b() != null) {
            ((C1830c.b) C1581b.m1833b()).m2078e();
        }
    }

    /* renamed from: b */
    public void m2547b(int i7) {
        WebSocketService webSocketService = (WebSocketService) this.f6238e;
        int i8 = WebSocketService.f653m;
        Objects.requireNonNull(webSocketService);
        if (i7 == 1) {
            C2073a.m2459d("WebSocket 连接成功0000");
            Log.m655i("XLog_APP ", "WebSocketService 连接成功0000");
            webSocketService.f659j = true;
            webSocketService.m460a();
            return;
        }
        if (i7 == 2) {
            C2073a.m2459d("WebSocket 连接失败0000");
            Log.m655i("XLog_APP ", "WebSocketService 连接失败0000");
            Objects.requireNonNull(webSocketService.f654e);
            webSocketService.f659j = false;
        }
    }

    @Override // p014b4.InterfaceC0455o
    public boolean test(Object obj) {
        Object obj2 = this.f6238e;
        int i7 = C2080f.f6177a;
        return obj.equals(obj2);
    }
}
