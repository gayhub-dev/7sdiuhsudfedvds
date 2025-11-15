package p192y1;

import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.module.service.NetSpeedTestService;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.callback.DownloadProgressCallBack;
import com.ctvit.network.exception.ApiException;
import com.ctvit.network.request.PostRequest;
import com.tencent.mars.xlog.Log;
import java.io.File;
import java.math.BigDecimal;
import java.util.Objects;
import p009b.C0413b;
import p043f.C0988e;
import p078j2.C1197l;
import p185x1.C2071f;
import p185x1.C2072g;
import p186x2.C2073a;

/* compiled from: NetSpeedTestService.java */
/* renamed from: y1.a */
/* loaded from: classes.dex */
public class C2097a extends DownloadProgressCallBack<String> {

    /* renamed from: e */
    public final /* synthetic */ NetSpeedTestService f6229e;

    public C2097a(NetSpeedTestService netSpeedTestService) {
        this.f6229e = netSpeedTestService;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ctvit.network.callback.DownloadProgressCallBack
    public void onComplete(String str) {
        NetSpeedTestService netSpeedTestService = this.f6229e;
        netSpeedTestService.f642j = false;
        netSpeedTestService.f639g = System.currentTimeMillis();
        StringBuilder sbM112a = C0413b.m112a("downLoadStartTime = ");
        sbM112a.append(this.f6229e.f638f);
        C2073a.m2459d(sbM112a.toString());
        C2073a.m2459d("downLoadCompleteTime = " + this.f6229e.f639g);
        StringBuilder sb = new StringBuilder();
        sb.append("downLoadStartTime - downLoadCompleteTime = ");
        NetSpeedTestService netSpeedTestService2 = this.f6229e;
        sb.append(netSpeedTestService2.f639g - netSpeedTestService2.f638f);
        C2073a.m2459d(sb.toString());
        Log.m655i("XLog_APP ", "NetSpeedTestService 下载完成");
        try {
            double dM980f = C0988e.m980f(this.f6229e.f640h, 1048576.0d, 4);
            C2073a.m2459d("fileSize_M = " + dM980f);
            double dDoubleValue = new BigDecimal(Double.valueOf(dM980f).doubleValue()).multiply(new BigDecimal(Double.valueOf(8.0d).doubleValue())).doubleValue();
            NetSpeedTestService netSpeedTestService3 = this.f6229e;
            double dM980f2 = C0988e.m980f(dDoubleValue, C0988e.m980f((double) (netSpeedTestService3.f639g - netSpeedTestService3.f638f), 1000.0d, 4), 4);
            C2073a.m2459d("result = " + dM980f2);
            this.f6229e.f641i = (int) dM980f2;
        } catch (IllegalAccessException e7) {
            e7.printStackTrace();
        }
        C1197l.m1423c(MyApplication.f561e, "NETSPEED_RESULT", this.f6229e.f641i);
        if (C2072g.f6167e == null) {
            synchronized (C2072g.class) {
                if (C2072g.f6167e == null) {
                    C2072g.f6167e = new C2072g();
                }
            }
        }
        C2072g c2072g = C2072g.f6167e;
        Objects.requireNonNull(c2072g);
        ((PostRequest) CtvitHttp.post("https://ytpaddr.cctv.cn/gsnw/bandwidth").cacheMode(CacheMode.NO_CACHE)).execute(new C2071f(c2072g));
        C2073a.m2459d("downLoadResult = " + this.f6229e.f641i);
        C2073a.m2459d("path = " + str);
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            C2073a.m2459d("delete = " + file.delete());
        }
    }

    @Override // com.ctvit.network.callback.DownloadProgressCallBack, com.ctvit.network.callback.CallBack
    public void onError(ApiException apiException) {
        super.onError(apiException);
        this.f6229e.f642j = false;
        C2073a.m2459d("下载失败！");
        Log.m655i("XLog_APP ", "NetSpeedTestService 下载失败！");
    }

    @Override // com.ctvit.network.callback.DownloadProgressCallBack
    public void update(long j7, long j8, boolean z6) {
        NetSpeedTestService netSpeedTestService = this.f6229e;
        if (netSpeedTestService.f642j) {
            return;
        }
        netSpeedTestService.f642j = true;
        netSpeedTestService.f638f = System.currentTimeMillis();
        this.f6229e.f640h = j8;
        StringBuilder sbM112a = C0413b.m112a("fileSize = ");
        sbM112a.append(this.f6229e.f640h);
        C2073a.m2459d(sbM112a.toString());
    }
}
