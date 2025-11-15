package p037e1;

import android.text.TextUtils;
import com.cctv.cctvplayer.CCTVVideoView;
import com.easefun.povplayer.core.video.PolyvSubVideoView;
import p103m3.InterfaceC1480t;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* compiled from: CCTVVideoView.java */
/* renamed from: e1.k */
/* loaded from: classes.dex */
public class C0930k implements InterfaceC1480t {

    /* renamed from: a */
    public final /* synthetic */ CCTVVideoView f1694a;

    public C0930k(CCTVVideoView cCTVVideoView) {
        this.f1694a = cCTVVideoView;
    }

    @Override // p103m3.InterfaceC1480t
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        TextUtils.isEmpty("sub onPrepared");
        CCTVVideoView cCTVVideoView = this.f1694a;
        PolyvSubVideoView polyvSubVideoView = cCTVVideoView.f501i;
        int aspectRatio = cCTVVideoView.f503k.getAspectRatio();
        if (polyvSubVideoView.getRenderView() == null) {
            return;
        }
        polyvSubVideoView.setCurrentAspectRatio(aspectRatio);
    }
}
