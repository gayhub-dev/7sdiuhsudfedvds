package p037e1;

import android.text.TextUtils;
import com.cctv.cctvplayer.CCTVVideoView;
import java.util.Objects;
import p053g1.InterfaceC1043a;
import p103m3.InterfaceC1479s;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* compiled from: CCTVVideoView.java */
/* renamed from: e1.n */
/* loaded from: classes.dex */
public class C0933n implements InterfaceC1479s {

    /* renamed from: a */
    public final /* synthetic */ CCTVVideoView f1697a;

    public C0933n(CCTVVideoView cCTVVideoView) {
        this.f1697a = cCTVVideoView;
    }

    @Override // p103m3.InterfaceC1479s
    /* renamed from: a */
    public void mo855a() {
        int playStage = this.f1697a.f501i.getPlayStage();
        TextUtils.isEmpty("sub " + (playStage == 1 ? "片头广告" : playStage == 2 ? "暖场视频" : playStage == 3 ? "片尾广告" : "") + " onPreparing");
        if (playStage != 1) {
            this.f1697a.f505m.f477s.setVisibility(8);
            this.f1697a.f505m.m372w(0, -1);
        } else {
            this.f1697a.f505m.m349A(0);
        }
        CCTVVideoView cCTVVideoView = this.f1697a;
        if (cCTVVideoView.f496I == null) {
            return;
        }
        cCTVVideoView.f501i.getCurrentPlayPath();
        CCTVVideoView cCTVVideoView2 = this.f1697a;
        InterfaceC1043a interfaceC1043a = cCTVVideoView2.f496I;
        Objects.requireNonNull(cCTVVideoView2.getPlayEntity());
        throw null;
    }

    @Override // p103m3.InterfaceC1479s
    /* renamed from: b */
    public void mo856b(boolean z6) {
        TextUtils.isEmpty("sub onPlay：" + z6);
    }

    @Override // p103m3.InterfaceC1479s
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        TextUtils.isEmpty("sub teaser onCompletion");
    }

    @Override // p103m3.InterfaceC1479s
    public void onPause() {
        TextUtils.isEmpty("sub onPause");
    }
}
