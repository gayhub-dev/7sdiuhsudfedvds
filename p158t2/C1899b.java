package p158t2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctvit.network.callback.SimpleCallBack;
import com.ctvit.network.exception.ApiException;
import okhttp3.internal.cache.DiskLruCache;
import p158t2.C1901d;
import p186x2.C2073a;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* compiled from: CtvitEncryptPlay.java */
/* renamed from: t2.b */
/* loaded from: classes.dex */
public class C1899b extends SimpleCallBack<String> {

    /* renamed from: f */
    public final /* synthetic */ String f5595f;

    /* renamed from: g */
    public final /* synthetic */ String f5596g;

    /* renamed from: h */
    public final /* synthetic */ C1900c f5597h;

    public C1899b(C1900c c1900c, String str, String str2) {
        this.f5597h = c1900c;
        this.f5595f = str;
        this.f5596g = str2;
    }

    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    public void onError(ApiException apiException) {
        C1901d.a aVar;
        super.onError(apiException);
        if (isSuccess() || (aVar = this.f5597h.f5598f) == null) {
            return;
        }
        aVar.mo2200a("", "", "", "");
    }

    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    public void onSuccess(Object obj) {
        JSONObject object;
        String str = (String) obj;
        super.onSuccess(str);
        try {
            object = JSON.parseObject(str);
        } catch (Exception e7) {
            C2073a.m2458c(e7);
        }
        String string = DiskLruCache.VERSION_1.equals(object.getString("succeed")) ? object.getString(IjkMediaPlayer.OnNativeInvokeListener.ARG_URL) : "";
        C1901d.a aVar = this.f5597h.f5598f;
        if (aVar != null) {
            aVar.mo2200a(string, this.f5595f, "5f39826474a524f95d5f436eacfacfb67457c4a7", this.f5596g);
        }
    }
}
