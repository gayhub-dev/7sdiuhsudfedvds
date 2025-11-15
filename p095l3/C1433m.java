package p095l3;

import com.easefun.povplayer.core.video.PolyvVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* compiled from: PolyvVideoView.java */
/* renamed from: l3.m */
/* loaded from: classes.dex */
public class C1433m implements IMediaPlayer.OnBufferingUpdateListener {

    /* renamed from: a */
    public final /* synthetic */ PolyvVideoView f4174a;

    public C1433m(PolyvVideoView polyvVideoView) {
        this.f4174a = polyvVideoView;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i7) {
        PolyvVideoView polyvVideoView = this.f4174a;
        polyvVideoView.f1158L = i7;
        polyvVideoView.m571m(iMediaPlayer, i7);
    }
}
