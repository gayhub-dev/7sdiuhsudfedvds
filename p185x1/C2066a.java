package p185x1;

import com.cctv.p025tv.entity.AudioTrackEntity;
import com.cctv.p025tv.module.player.VideoPlayer;
import java.util.ArrayList;
import java.util.List;
import p186x2.C2073a;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/* compiled from: AudioTrack.java */
/* renamed from: x1.a */
/* loaded from: classes.dex */
public class C2066a {

    /* renamed from: d */
    public static volatile C2066a f6158d;

    /* renamed from: a */
    public ITrackInfo[] f6159a;

    /* renamed from: b */
    public IMediaPlayer f6160b;

    /* renamed from: c */
    public List<AudioTrackEntity> f6161c = new ArrayList(8);

    /* renamed from: a */
    public static int m2449a(VideoPlayer videoPlayer) {
        if (videoPlayer == null) {
            return -1;
        }
        int iMo579f = videoPlayer.getPlayerView().f1105e.mo579f(2);
        C2073a.m2459d("当前播放音轨索引：" + iMo579f);
        return iMo579f;
    }

    /* renamed from: b */
    public static C2066a m2450b() {
        if (f6158d == null) {
            synchronized (C2066a.class) {
                if (f6158d == null) {
                    f6158d = new C2066a();
                }
            }
        }
        return f6158d;
    }
}
