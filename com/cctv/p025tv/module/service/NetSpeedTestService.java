package com.cctv.p025tv.module.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.C0164a;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.utils.PermissionsUtils;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import com.tencent.mars.xlog.Log;
import java.io.File;
import java.util.Objects;
import p078j2.C1196k;
import p186x2.C2073a;
import p186x2.C2074b;
import p192y1.C2097a;
import p192y1.C2098b;
import p199z1.InterfaceC2147a;

/* loaded from: classes.dex */
public class NetSpeedTestService extends Service {

    /* renamed from: l */
    public static final /* synthetic */ int f636l = 0;

    /* renamed from: f */
    public long f638f;

    /* renamed from: g */
    public long f639g;

    /* renamed from: h */
    public long f640h;

    /* renamed from: i */
    public int f641i;

    /* renamed from: e */
    public BinderC0592b f637e = new BinderC0592b(null);

    /* renamed from: j */
    public boolean f642j = false;

    /* renamed from: k */
    public Handler f643k = new HandlerC0591a(Looper.getMainLooper());

    /* renamed from: com.cctv.tv.module.service.NetSpeedTestService$a */
    public class HandlerC0591a extends Handler {
        public HandlerC0591a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (message.what != 3000) {
                return;
            }
            NetSpeedTestService netSpeedTestService = NetSpeedTestService.this;
            int i7 = NetSpeedTestService.f636l;
            Objects.requireNonNull(netSpeedTestService);
            if (PermissionsUtils.isReadWritePermissionsStatus()) {
                StringBuilder sb = new StringBuilder();
                sb.append(C2074b.m2487j());
                String str = File.separator;
                CtvitHttp.downLoad("https://ytppic.cctv.cn/cctv/ytp/bandwidth/index1.png").context(MyApplication.f561e).cacheMode(CacheMode.NO_CACHE).saveName("test").savePath(C0164a.m99a(sb, str, "nettest", str)).execute(new C2097a(netSpeedTestService));
            }
        }
    }

    /* renamed from: com.cctv.tv.module.service.NetSpeedTestService$b */
    public class BinderC0592b extends Binder implements InterfaceC2147a {
        public BinderC0592b(C2098b c2098b) {
        }

        @Override // p199z1.InterfaceC2147a
        /* renamed from: a */
        public NetSpeedTestService mo455a() {
            return NetSpeedTestService.this;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f637e;
    }

    @Override // android.app.Service
    public void onCreate() {
        startForeground(1004, C1196k.m1420a());
        C2073a.m2459d("onCreate");
        Log.m655i("XLog_APP ", "NetSpeedTestService onCreate");
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        C2073a.m2459d("onDestroy");
        Log.m655i("XLog_APP ", "NetSpeedTestService onDestroy");
        stopForeground(true);
        Handler handler = this.f643k;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f643k = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        C2073a.m2459d("onStartCommand");
        return super.onStartCommand(intent, i7, i8);
    }
}
