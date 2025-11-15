package p054g2;

import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.module.collect.C0580b;
import com.cctv.p025tv.mvp.p026ui.fragment.VersionUpdateFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import com.ctvit.dlna.entity.DlnaContentEntity;
import com.tencent.mars.xlog.Log;
import java.util.Objects;
import p078j2.C1186a;
import p085k1.C1229c;
import p136q2.InterfaceC1761a;
import p186x2.C2073a;

/* renamed from: g2.h */
/* loaded from: classes.dex */
public final /* synthetic */ class C1057h implements InterfaceC1761a {

    /* renamed from: e */
    public final /* synthetic */ VideoFragment f1992e;

    public /* synthetic */ C1057h(VideoFragment videoFragment, int i7) {
        if (i7 == 1 || i7 != 2) {
        }
        this.f1992e = videoFragment;
    }

    @Override // p136q2.InterfaceC1761a
    /* renamed from: a */
    public void mo318a(DlnaContentEntity dlnaContentEntity) {
        VideoFragment videoFragment = this.f1992e;
        int i7 = VideoFragment.f854B;
        Objects.requireNonNull(videoFragment);
        C2073a.m2459d("DLNA 播放指令 - Video");
        Log.m655i("XLog_DLNA ", "fragment 播放指令");
        if (VersionUpdateFragment.f849l) {
            C2073a.m2459d("更新页显示中 - Video");
            MyApplication.f563g = dlnaContentEntity;
        } else if (C1186a.m1389i()) {
            C1229c.f2759a = "other";
            videoFragment.m510n(dlnaContentEntity);
            C0580b.m418d("DLNA", MyApplication.f564h, videoFragment.f861m);
        }
    }
}
