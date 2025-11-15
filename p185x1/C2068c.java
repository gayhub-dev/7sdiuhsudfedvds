package p185x1;

import com.cctv.p025tv.entity.HighBitrateEntity;
import com.cctv.p025tv.module.player.VideoPlayer;
import com.ctvit.network.callback.SimpleCallBack;
import com.ctvit.network.exception.ApiException;
import p045f1.C0992a;
import p186x2.C2073a;

/* compiled from: PlayerCctv.java */
/* renamed from: x1.c */
/* loaded from: classes.dex */
public class C2068c extends SimpleCallBack<HighBitrateEntity> {

    /* renamed from: f */
    public final /* synthetic */ C0992a f6162f;

    /* renamed from: g */
    public final /* synthetic */ String f6163g;

    /* renamed from: h */
    public final /* synthetic */ VideoPlayer f6164h;

    public C2068c(C2069d c2069d, C0992a c0992a, String str, VideoPlayer videoPlayer) {
        this.f6162f = c0992a;
        this.f6163g = str;
        this.f6164h = videoPlayer;
    }

    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    public void onError(ApiException apiException) {
        super.onError(apiException);
        if (isSuccess()) {
            return;
        }
        C2073a.m2457b("高码接口出错，直接播低码：", apiException);
        this.f6164h.m442E(this.f6162f, null, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0101 A[Catch: Exception -> 0x0264, TryCatch #0 {Exception -> 0x0264, blocks: (B:3:0x001d, B:5:0x002d, B:7:0x0035, B:9:0x003f, B:11:0x0045, B:13:0x0053, B:18:0x00a4, B:20:0x00b4, B:22:0x00bc, B:30:0x0101, B:32:0x0107, B:34:0x010d, B:36:0x011c, B:38:0x013c, B:40:0x0156, B:23:0x00d5, B:25:0x00df, B:29:0x00e8), top: B:86:0x001d }] */
    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onSuccess(java.lang.Object r18) {
        /*
            Method dump skipped, instructions count: 631
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p185x1.C2068c.onSuccess(java.lang.Object):void");
    }
}
