package p037e1;

import com.cctv.cctvplayer.CCTVVideoView;
import p103m3.InterfaceC1480t;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* compiled from: CCTVVideoView.java */
/* renamed from: e1.v */
/* loaded from: classes.dex */
public class C0941v implements InterfaceC1480t {

    /* renamed from: a */
    public final /* synthetic */ CCTVVideoView f1704a;

    public C0941v(CCTVVideoView cCTVVideoView) {
        this.f1704a = cCTVVideoView;
    }

    @Override // p103m3.InterfaceC1480t
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        this.f1704a.mo384o(iMediaPlayer);
    }
}
