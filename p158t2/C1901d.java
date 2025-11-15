package p158t2;

import android.arch.lifecycle.AbstractC0052c;
import com.alibaba.fastjson.JSONObject;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.request.PostRequest;
import p186x2.C2073a;
import p193y2.C2106a;

/* compiled from: CtvitEncryptPlay.java */
/* renamed from: t2.d */
/* loaded from: classes.dex */
public class C1901d {

    /* renamed from: a */
    public boolean f5600a;

    /* renamed from: b */
    public String f5601b;

    /* renamed from: c */
    public AbstractC0052c f5602c;

    /* renamed from: d */
    public String f5603d = C2106a.m2549b();

    /* renamed from: e */
    public String f5604e;

    /* renamed from: f */
    public String f5605f;

    /* renamed from: g */
    public String f5606g;

    /* renamed from: h */
    public String f5607h;

    /* compiled from: CtvitEncryptPlay.java */
    /* renamed from: t2.d$a */
    public interface a {
        /* renamed from: a */
        void mo2200a(String str, String str2, String str3, String str4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public void m2199a(a aVar) {
        C2073a.m2459d("VDN_SK");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("guid", (Object) this.f5606g);
        ((PostRequest) CtvitHttp.post(this.f5605f).cacheMode(CacheMode.NO_CACHE)).upJson(jSONObject.toJSONString()).execute(new C1900c(this, aVar));
    }
}
