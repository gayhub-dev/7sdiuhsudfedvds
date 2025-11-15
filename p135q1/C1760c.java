package p135q1;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.cctv.p025tv.module.collect.report.network.ReportResponse;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.callback.SimpleCallBack;
import com.ctvit.network.exception.ApiException;
import com.ctvit.network.request.PostRequest;
import java.util.Objects;
import okhttp3.HttpUrl;
import p086k2.C1231b;
import p101m1.C1458b;
import p101m1.RunnableC1457a;
import p186x2.C2073a;

/* compiled from: ReportHttpUtils.java */
/* renamed from: q1.c */
/* loaded from: classes.dex */
public class C1760c {

    /* compiled from: ReportHttpUtils.java */
    /* renamed from: q1.c$a */
    public class a extends SimpleCallBack<ReportResponse> {

        /* renamed from: f */
        public final /* synthetic */ String f5009f;

        public a(String str) {
            this.f5009f = str;
        }

        @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
        public void onError(ApiException apiException) {
            super.onError(apiException);
            C2073a.m2459d("report ReportHttpUtils post error = " + apiException);
            C1759b.m1924a().m1926c(this.f5009f);
        }

        @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
        public void onSuccess(Object obj) {
            super.onSuccess((ReportResponse) obj);
            C2073a.m2459d("report ReportHttpUtils post success");
            C1759b c1759bM1924a = C1759b.m1924a();
            Objects.requireNonNull(c1759bM1924a);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - c1759bM1924a.f5008f <= 60000) {
                C2073a.m2459d("report uploadCache 上传太快了没到1分钟");
                return;
            }
            C2073a.m2459d("report uploadCache 上传了");
            c1759bM1924a.f5008f = jCurrentTimeMillis;
            c1759bM1924a.f5003a.execute(new RunnableC1457a(c1759bM1924a));
        }
    }

    /* compiled from: ReportHttpUtils.java */
    /* renamed from: q1.c$b */
    public class b extends SimpleCallBack<ReportResponse> {

        /* renamed from: f */
        public final /* synthetic */ String f5010f;

        public b(String str) {
            this.f5010f = str;
        }

        @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
        public void onError(ApiException apiException) {
            super.onError(apiException);
            C2073a.m2459d("report ReportHttpUtils postCache error = " + apiException);
        }

        @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
        public void onSuccess(Object obj) {
            super.onSuccess((ReportResponse) obj);
            C2073a.m2459d("report ReportHttpUtils postCache success");
            C1759b.m1924a().f5003a.execute(new RunnableC1457a(this.f5010f));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static void m1927a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        C1458b.m1642a("report ReportHttpUtils post json = ", str);
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) C1231b.f2761c.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            ((PostRequest) CtvitHttp.post("https://ytpdata.cctv.cn/das/app/data/message/single").cacheMode(CacheMode.NO_CACHE)).upJson(str).execute(new a(str));
        } else {
            C2073a.m2456a("report ReportHttpUtils post 无网络-缓存数据");
            C1759b.m1924a().m1926c(str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public static void m1928b(String str, String str2) {
        if (!TextUtils.isEmpty(str) || str.equals(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)) {
            if (str.contains("[,{")) {
                str = str.replaceFirst(",", "");
            }
            C2073a.m2459d("report ReportHttpUtils postCache json = " + str);
            ((PostRequest) CtvitHttp.post("https://ytpdata.cctv.cn/das/app/data/message/batch").cacheMode(CacheMode.NO_CACHE)).upJson(str).execute(new b(str2));
        }
    }
}
