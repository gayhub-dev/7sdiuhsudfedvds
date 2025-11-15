package p037e1;

import android.text.TextUtils;
import com.cctv.cctvplayer.CCTVVideoView;
import p103m3.InterfaceC1477q;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* compiled from: CCTVVideoView.java */
/* renamed from: e1.l */
/* loaded from: classes.dex */
public class C0931l implements InterfaceC1477q {

    /* renamed from: a */
    public final /* synthetic */ CCTVVideoView f1695a;

    public C0931l(CCTVVideoView cCTVVideoView) {
        this.f1695a = cCTVVideoView;
    }

    @Override // p103m3.InterfaceC1477q
    /* renamed from: a */
    public void mo854a(IMediaPlayer iMediaPlayer, int i7, int i8) {
        if (i7 == 701 || i7 == 10005) {
            TextUtils.isEmpty("sub 开始缓冲");
            this.f1695a.f502j.setVisibility(0);
        } else if (i7 == 702) {
            TextUtils.isEmpty("sub 缓冲结束");
            this.f1695a.f502j.setVisibility(8);
        }
    }
}
