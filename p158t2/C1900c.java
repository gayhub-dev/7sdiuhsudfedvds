package p158t2;

import android.support.constraint.motion.C0080b;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.ctvit.encryptplay.entity.VDNSKEntity;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.callback.SimpleCallBack;
import com.ctvit.network.exception.ApiException;
import com.ctvit.network.request.PostRequest;
import com.tencent.mars.xlog.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import p004a3.C0008a;
import p009b.C0413b;
import p101m1.C1458b;
import p158t2.C1901d;
import p186x2.C2073a;
import p186x2.C2074b;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* compiled from: CtvitEncryptPlay.java */
/* renamed from: t2.c */
/* loaded from: classes.dex */
public class C1900c extends SimpleCallBack<VDNSKEntity> {

    /* renamed from: f */
    public final /* synthetic */ C1901d.a f5598f;

    /* renamed from: g */
    public final /* synthetic */ C1901d f5599g;

    public C1900c(C1901d c1901d, C1901d.a aVar) {
        this.f5599g = c1901d;
        this.f5598f = aVar;
    }

    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    public void onError(ApiException apiException) {
        super.onError(apiException);
        StringBuilder sbM112a = C0413b.m112a("VDN_SK ApiException = ");
        sbM112a.append(apiException.getMessage());
        C2073a.m2459d(sbM112a.toString());
        Log.m655i("XLog_APP ", "VDN_SK ApiException = " + apiException.getMessage());
        C1901d.a aVar = this.f5598f;
        if (aVar != null) {
            aVar.mo2200a("", "", "", "");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    public void onSuccess(Object obj) throws NoSuchAlgorithmException {
        String jSONString;
        String strM2479b;
        VDNSKEntity vDNSKEntity = (VDNSKEntity) obj;
        super.onSuccess(vDNSKEntity);
        if (vDNSKEntity.getData() == null || TextUtils.isEmpty(vDNSKEntity.getData().getAppSecret())) {
            C1901d.a aVar = this.f5598f;
            if (aVar != null) {
                aVar.mo2200a("", "", "", "");
                return;
            }
            return;
        }
        String strM8a = C0008a.m8a(vDNSKEntity.getData().getAppSecret(), this.f5599g.f5607h);
        C1458b.m1642a("VDN_SK AppSecret = ", strM8a);
        PostRequest postRequest = (PostRequest) ((PostRequest) CtvitHttp.post(this.f5599g.f5600a ? C1898a.f5594b : C1898a.f5593a).cacheMode(CacheMode.NO_CACHE)).bindLifecycle(this.f5599g.f5602c);
        C1901d c1901d = this.f5599g;
        String strM2479b2 = null;
        if (TextUtils.isEmpty(c1901d.f5604e)) {
            C2073a.m2459d("视频防盗链：非二次鉴权");
            jSONString = c1901d.f5601b;
        } else {
            C2073a.m2459d("视频防盗链：二次鉴权");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_URL, (Object) c1901d.f5601b);
            jSONObject.put("UID", (Object) c1901d.f5603d);
            StringBuilder sbM94a = C0080b.m94a(c1901d.f5601b, c1901d.f5603d);
            sbM94a.append(c1901d.f5604e);
            String string = sbM94a.toString();
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                messageDigest.update(string.getBytes());
                strM2479b = C2074b.m2479b(messageDigest.digest());
            } catch (NoSuchAlgorithmException e7) {
                C2073a.m2458c(e7);
                strM2479b = null;
            }
            jSONObject.put("key", (Object) strM2479b);
            jSONString = jSONObject.toJSONString();
        }
        PostRequest postRequest2 = (PostRequest) postRequest.params(IjkMediaPlayer.OnNativeInvokeListener.ARG_URL, jSONString);
        if (this.f5599g.f5600a) {
            postRequest2.removeCommonHeaders();
        }
        String string2 = UUID.randomUUID().toString();
        String strM97a = C0096a.m97a("5f39826474a524f95d5f436eacfacfb67457c4a7", strM8a, string2);
        try {
            MessageDigest messageDigest2 = MessageDigest.getInstance("MD5");
            messageDigest2.update(strM97a.getBytes());
            strM2479b2 = C2074b.m2479b(messageDigest2.digest());
        } catch (NoSuchAlgorithmException e8) {
            C2073a.m2458c(e8);
        }
        postRequest2.headers("APPID", "5f39826474a524f95d5f436eacfacfb67457c4a7");
        postRequest2.headers("APPSIGN", strM2479b2);
        postRequest2.headers("APPRANDOMSTR", string2);
        postRequest2.execute(new C1899b(this, string2, strM2479b2));
    }
}
