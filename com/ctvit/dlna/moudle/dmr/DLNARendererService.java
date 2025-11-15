package com.ctvit.dlna.moudle.dmr;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.text.TextUtils;
import java.net.SocketException;
import java.util.Objects;
import org.fourthline.cling.UpnpServiceConfiguration;
import org.fourthline.cling.android.AndroidRouter;
import org.fourthline.cling.android.AndroidUpnpServiceConfiguration;
import org.fourthline.cling.android.AndroidUpnpServiceImpl;
import org.fourthline.cling.protocol.ProtocolFactory;
import org.fourthline.cling.transport.spi.StreamClient;
import p078j2.C1191f;
import p078j2.C1195j;
import p085k1.C1227a;
import p093l1.C1418d;
import p118o2.C1581b;
import p128p2.C1736a;
import p186x2.C2073a;
import p200z2.C2150a;

/* loaded from: classes.dex */
public class DLNARendererService extends AndroidUpnpServiceImpl {

    /* renamed from: com.ctvit.dlna.moudle.dmr.DLNARendererService$a */
    public class C0625a extends AndroidUpnpServiceConfiguration {
        public C0625a(DLNARendererService dLNARendererService) {
        }

        @Override // org.fourthline.cling.android.AndroidUpnpServiceConfiguration, org.fourthline.cling.DefaultUpnpServiceConfiguration, org.fourthline.cling.UpnpServiceConfiguration
        public StreamClient createStreamClient() throws SocketException {
            C2073a.m2459d("new服务开启结束");
            if (C1581b.m1835d() != null) {
                Objects.requireNonNull((C1418d.a) C1581b.m1835d());
                C2073a.m2459d("投屏服务启动完成");
                String strM1417a = C1195j.m1417a();
                if (!TextUtils.isEmpty(strM1417a)) {
                    C2150a.m2591b("SERVICE_START_IP", strM1417a);
                }
                C1227a.f2757a = "BIND_COMPLETE";
            }
            return super.createStreamClient();
        }

        @Override // org.fourthline.cling.DefaultUpnpServiceConfiguration, org.fourthline.cling.UpnpServiceConfiguration
        public int getAliveIntervalMillis() {
            return PathInterpolatorCompat.MAX_NUM_POINTS;
        }

        @Override // org.fourthline.cling.DefaultUpnpServiceConfiguration, org.fourthline.cling.UpnpServiceConfiguration
        public void shutdown() {
            super.shutdown();
            if (C1581b.m1835d() != null) {
                Objects.requireNonNull((C1418d.a) C1581b.m1835d());
                C2073a.m2459d("投屏服务关闭完成 bindService");
                C1227a.f2757a = "UNBIND_COMPLETE";
                C1191f.m1409c(false);
            }
            C2073a.m2459d("new 解绑Upnp服务结束");
        }
    }

    @Override // org.fourthline.cling.android.AndroidUpnpServiceImpl
    public UpnpServiceConfiguration createConfiguration() {
        return new C0625a(this);
    }

    @Override // org.fourthline.cling.android.AndroidUpnpServiceImpl
    public AndroidRouter createRouter(UpnpServiceConfiguration upnpServiceConfiguration, ProtocolFactory protocolFactory, Context context) {
        return new C1736a(upnpServiceConfiguration, protocolFactory, context);
    }

    @Override // org.fourthline.cling.android.AndroidUpnpServiceImpl, android.app.Service
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override // org.fourthline.cling.android.AndroidUpnpServiceImpl, android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // org.fourthline.cling.android.AndroidUpnpServiceImpl, android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        return 1;
    }
}
