package p150s1;

import com.cctv.p025tv.module.player.VideoPlayer;
import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import com.ctvit.dlna.entity.CctvEntity;

/* compiled from: TeleController.java */
/* renamed from: s1.c */
/* loaded from: classes.dex */
public class C1866c {

    /* renamed from: a */
    public static boolean f5442a;

    /* renamed from: a */
    public static void m2134a(VideoFragment videoFragment) {
        CctvEntity.PlayerBean player;
        if (f5442a) {
            return;
        }
        f5442a = true;
        VideoPlayer videoPlayerM508l = videoFragment.m508l();
        if (videoPlayerM508l == null) {
            return;
        }
        Object obj = videoPlayerM508l.getPlayEntity().f1844h;
        if ((obj instanceof CctvEntity) && (player = ((CctvEntity) obj).getPlayer()) != null && "live".equals(player.getType())) {
            return;
        }
        videoPlayerM508l.getMediaController().m365p(true, false);
        videoPlayerM508l.getMediaController().m363n(false);
        videoPlayerM508l.getMediaController().getPlayProgressView().requestFocus();
    }
}
