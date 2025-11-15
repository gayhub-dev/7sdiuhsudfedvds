package p164u1;

import android.text.TextUtils;
import com.cctv.p025tv.module.player.CheckDevicePlayer;
import com.cctv.p025tv.module.player.WarmVideoPlayer;
import java.util.Objects;
import p043f.C0988e;
import p158t2.C1901d;
import p186x2.C2073a;

/* renamed from: u1.a */
/* loaded from: classes.dex */
public final /* synthetic */ class C1971a implements C1901d.a {

    /* renamed from: a */
    public final /* synthetic */ int f5759a = 0;

    /* renamed from: b */
    public final /* synthetic */ Object f5760b;

    /* renamed from: c */
    public final /* synthetic */ String f5761c;

    /* renamed from: d */
    public final /* synthetic */ boolean f5762d;

    /* renamed from: e */
    public final /* synthetic */ String f5763e;

    /* renamed from: f */
    public final /* synthetic */ boolean f5764f;

    public /* synthetic */ C1971a(CheckDevicePlayer checkDevicePlayer, String str, boolean z6, String str2, boolean z7) {
        this.f5760b = checkDevicePlayer;
        this.f5761c = str;
        this.f5762d = z6;
        this.f5763e = str2;
        this.f5764f = z7;
    }

    public /* synthetic */ C1971a(WarmVideoPlayer warmVideoPlayer, boolean z6, String str, boolean z7, String str2) {
        this.f5760b = warmVideoPlayer;
        this.f5762d = z6;
        this.f5761c = str;
        this.f5764f = z7;
        this.f5763e = str2;
    }

    @Override // p158t2.C1901d.a
    /* renamed from: a */
    public final void mo2200a(String str, String str2, String str3, String str4) {
        switch (this.f5759a) {
            case 0:
                CheckDevicePlayer checkDevicePlayer = (CheckDevicePlayer) this.f5760b;
                String str5 = this.f5761c;
                boolean z6 = this.f5762d;
                String str6 = this.f5763e;
                boolean z7 = this.f5764f;
                int i7 = CheckDevicePlayer.f583N;
                Objects.requireNonNull(checkDevicePlayer);
                if (!TextUtils.isEmpty(str)) {
                    checkDevicePlayer.getPlayEntity().m1007a(str);
                    checkDevicePlayer.getPlayEntity().f1842f = C0988e.m995u(str2, str3, str4);
                    C2073a.m2459d("header = " + C0988e.m995u(str2, str3, str4).toString());
                    checkDevicePlayer.m433y(str, z6, str6, z7);
                    break;
                } else {
                    C2073a.m2456a("VDN 接口出错，播放低码率");
                    checkDevicePlayer.getPlayEntity().f1842f = null;
                    checkDevicePlayer.m433y(str5, z6, str6, z7);
                    break;
                }
            default:
                WarmVideoPlayer warmVideoPlayer = (WarmVideoPlayer) this.f5760b;
                boolean z8 = this.f5762d;
                String str7 = this.f5761c;
                boolean z9 = this.f5764f;
                String str8 = this.f5763e;
                int i8 = WarmVideoPlayer.f627M;
                Objects.requireNonNull(warmVideoPlayer);
                if (!TextUtils.isEmpty(str)) {
                    warmVideoPlayer.getPlayEntity().f1842f = C0988e.m995u(str2, str3, str4);
                    warmVideoPlayer.m454x(str, z8, str7, z9);
                    C2073a.m2456a("VDN 接口成功，播放header =" + str);
                    break;
                } else {
                    C2073a.m2456a("VDN 接口出错，播放原地址");
                    C2073a.m2459d("warmVideo 低码不带防盗链");
                    warmVideoPlayer.getPlayEntity().f1842f = null;
                    warmVideoPlayer.m454x(str8, z8, str7, z9);
                    break;
                }
        }
    }
}
