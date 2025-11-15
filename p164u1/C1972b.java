package p164u1;

import android.text.TextUtils;
import com.cctv.p025tv.module.player.VideoPlayer;
import com.tencent.mars.xlog.Log;
import java.util.Objects;
import p043f.C0988e;
import p158t2.C1901d;
import p186x2.C2073a;

/* renamed from: u1.b */
/* loaded from: classes.dex */
public final /* synthetic */ class C1972b implements C1901d.a {

    /* renamed from: a */
    public final /* synthetic */ int f5765a = 0;

    /* renamed from: b */
    public final /* synthetic */ VideoPlayer f5766b;

    /* renamed from: c */
    public final /* synthetic */ String f5767c;

    /* renamed from: d */
    public final /* synthetic */ boolean f5768d;

    /* renamed from: e */
    public final /* synthetic */ String f5769e;

    /* renamed from: f */
    public final /* synthetic */ boolean f5770f;

    public /* synthetic */ C1972b(VideoPlayer videoPlayer, String str, boolean z6, String str2, boolean z7) {
        this.f5766b = videoPlayer;
        this.f5767c = str;
        this.f5768d = z6;
        this.f5769e = str2;
        this.f5770f = z7;
    }

    public /* synthetic */ C1972b(VideoPlayer videoPlayer, boolean z6, String str, boolean z7, String str2) {
        this.f5766b = videoPlayer;
        this.f5768d = z6;
        this.f5767c = str;
        this.f5770f = z7;
        this.f5769e = str2;
    }

    @Override // p158t2.C1901d.a
    /* renamed from: a */
    public final void mo2200a(String str, String str2, String str3, String str4) {
        switch (this.f5765a) {
            case 0:
                VideoPlayer videoPlayer = this.f5766b;
                String str5 = this.f5767c;
                boolean z6 = this.f5768d;
                String str6 = this.f5769e;
                boolean z7 = this.f5770f;
                int i7 = VideoPlayer.f591r0;
                Objects.requireNonNull(videoPlayer);
                if (!TextUtils.isEmpty(str)) {
                    videoPlayer.f606a0 = true;
                    videoPlayer.getPlayEntity().m1007a(str);
                    videoPlayer.getPlayEntity().f1842f = C0988e.m995u(str2, str3, str4);
                    C2073a.m2459d("header = " + C0988e.m995u(str2, str3, str4).toString());
                    videoPlayer.m452O(str, z6, str6, z7);
                    break;
                } else {
                    C2073a.m2456a("VDN 接口出错，播放低码率");
                    Log.m651e("XLog_PLAY ", "play VDN 接口出错，播放低码率");
                    videoPlayer.f609d0 = false;
                    videoPlayer.m445H(str5, z6, str6, z7);
                    break;
                }
            default:
                VideoPlayer videoPlayer2 = this.f5766b;
                boolean z8 = this.f5768d;
                String str7 = this.f5767c;
                boolean z9 = this.f5770f;
                String str8 = this.f5769e;
                int i8 = VideoPlayer.f591r0;
                Objects.requireNonNull(videoPlayer2);
                if (!TextUtils.isEmpty(str)) {
                    videoPlayer2.getPlayEntity().f1842f = C0988e.m995u(str2, str3, str4);
                    videoPlayer2.f606a0 = false;
                    videoPlayer2.m452O(str, z8, str7, z9);
                    C2073a.m2456a("VDN 接口成功，播放header =" + str);
                    break;
                } else {
                    C2073a.m2456a("VDN 接口出错，播放原地址");
                    videoPlayer2.m445H(str8, z8, str7, z9);
                    break;
                }
        }
    }
}
