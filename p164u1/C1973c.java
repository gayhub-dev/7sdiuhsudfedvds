package p164u1;

import com.cctv.p025tv.module.player.VideoPlayer;
import java.util.TimerTask;
import p009b.C0413b;
import p186x2.C2073a;

/* compiled from: VideoPlayer.java */
/* renamed from: u1.c */
/* loaded from: classes.dex */
public class C1973c extends TimerTask {

    /* renamed from: e */
    public final /* synthetic */ VideoPlayer f5771e;

    public C1973c(VideoPlayer videoPlayer) {
        this.f5771e = videoPlayer;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        StringBuilder sbM112a = C0413b.m112a("timerTask = ");
        sbM112a.append(this.f5771e.f612g0);
        C2073a.m2459d(sbM112a.toString());
        VideoPlayer videoPlayer = this.f5771e;
        if (videoPlayer.f608c0) {
            double d7 = videoPlayer.f610e0;
            long jCurrentTimeMillis = System.currentTimeMillis();
            VideoPlayer videoPlayer2 = this.f5771e;
            videoPlayer.f610e0 = d7 + (jCurrentTimeMillis - videoPlayer2.f605W);
            videoPlayer2.f605W = System.currentTimeMillis();
        }
        VideoPlayer videoPlayer3 = this.f5771e;
        double d8 = videoPlayer3.f610e0 / 1000.0d;
        C2073a.m2459d("c_lt_sum = " + d8);
        videoPlayer3.f607b0 = false;
        videoPlayer3.f608c0 = false;
        if (!videoPlayer3.m443F(10000)) {
            C2073a.m2459d("视频小于10000分钟return ");
        } else if (d8 >= 5.0d) {
            C2073a.m2459d("switchVideo = switchVideoDown");
            if (videoPlayer3.getPlayEntity().f1843g != null && videoPlayer3.getPlayEntity().f1843g.size() > 1 && videoPlayer3.getPlayEntity().m1010d() > 0 && videoPlayer3.m444G(false)) {
                videoPlayer3.f602T.m514r(videoPlayer3.f601S, videoPlayer3.getPlayEntity(), videoPlayer3.f604V, videoPlayer3.getPlayerView().getCurrentPosition() + 1000, String.valueOf(videoPlayer3.getPlayEntity().m1010d() - 1));
            }
        } else if (d8 == 0.0d) {
            videoPlayer3.f614i0++;
            StringBuilder sbM112a2 = C0413b.m112a("videoUpTime = ");
            sbM112a2.append(videoPlayer3.f614i0);
            C2073a.m2459d(sbM112a2.toString());
            if (videoPlayer3.f614i0 == 3) {
                videoPlayer3.f614i0 = 0;
                C2073a.m2459d("switchVideo = switchVideoUp");
                if (videoPlayer3.getPlayEntity().f1843g != null && videoPlayer3.getPlayEntity().f1843g.size() > 1 && videoPlayer3.getPlayEntity().m1010d() < videoPlayer3.getPlayEntity().f1843g.size() - 1 && videoPlayer3.m444G(true)) {
                    videoPlayer3.f602T.m514r(videoPlayer3.f601S, videoPlayer3.getPlayEntity(), videoPlayer3.f604V, videoPlayer3.getPlayerView().getCurrentPosition() + 1000, String.valueOf(videoPlayer3.getPlayEntity().m1010d() + 1));
                }
            }
        }
        this.f5771e.f610e0 = 0.0d;
    }
}
