package p037e1;

import android.text.TextUtils;
import com.cctv.cctvplayer.CCTVVideoView;
import java.util.Objects;
import p103m3.InterfaceC1479s;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* compiled from: CCTVVideoView.java */
/* renamed from: e1.t */
/* loaded from: classes.dex */
public class C0939t implements InterfaceC1479s {

    /* renamed from: a */
    public final /* synthetic */ CCTVVideoView f1702a;

    public C0939t(CCTVVideoView cCTVVideoView) {
        this.f1702a = cCTVVideoView;
    }

    @Override // p103m3.InterfaceC1479s
    /* renamed from: a */
    public void mo855a() {
        this.f1702a.mo385p();
    }

    @Override // p103m3.InterfaceC1479s
    /* renamed from: b */
    public void mo856b(boolean z6) {
        this.f1702a.mo383n(z6);
    }

    @Override // p103m3.InterfaceC1479s
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        CCTVVideoView cCTVVideoView = this.f1702a;
        Objects.requireNonNull(cCTVVideoView);
        TextUtils.isEmpty("onCompletion");
        cCTVVideoView.f505m.m364o(true);
        cCTVVideoView.f505m.setBackground(true);
        cCTVVideoView.f505m.m363n(false);
        if (!cCTVVideoView.f501i.f1136z) {
            cCTVVideoView.mo382m();
        }
        cCTVVideoView.f505m.m369t();
        cCTVVideoView.f505m.m370u();
    }

    @Override // p103m3.InterfaceC1479s
    public void onPause() {
        this.f1702a.mo381l();
    }
}
