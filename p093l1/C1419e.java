package p093l1;

import com.cctv.p025tv.app.MyApplication;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.model.HttpHeaders;
import com.ctvit.network.model.HttpParams;
import com.ctvit.network.utils.CtvitHttpHeaders;
import com.ctvit.network.utils.CtvitHttpParams;
import okhttp3.CertificatePinner;
import p043f.C0988e;

/* compiled from: InitAfterPolicyManager.java */
/* renamed from: l1.e */
/* loaded from: classes.dex */
public class C1419e {
    /* renamed from: a */
    public static void m1603a() {
        HttpParams httpParamsParams = CtvitHttpParams.getInstance().m529ap("cctv_app_tv").params();
        HttpHeaders httpHeadersHeaders = CtvitHttpHeaders.getInstance().userAgent("cctv_app_tv").headers();
        String strM997w = C1415a.f4150b;
        if (strM997w == null) {
            strM997w = C0988e.m997w(MyApplication.f561e);
        }
        httpHeadersHeaders.put("appChannel", strM997w);
        CtvitHttp.getOkHttpClientBuilder().certificatePinner(new CertificatePinner.Builder().add("*.cctv.cn", "sha256/i7WTqTvh0OioIruIfFR4kMPnBqrS2rdiVPl/s2uC/CY=").build());
        CtvitHttp.getInstance().addCommonHeaders(httpHeadersHeaders).addCommonParams(httpParamsParams).setRetryCount(0).setConnectTimeout(20000L);
    }
}
