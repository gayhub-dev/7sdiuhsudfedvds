package p185x1;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.cctv.p025tv.module.player.VideoPlayer;
import com.ctvit.dlna.entity.CctvEntity;
import com.ctvit.dlna.entity.DlnaContentEntity;
import p043f.C0988e;
import p171v1.C2002a;

/* compiled from: ListenMode.java */
/* renamed from: x1.b */
/* loaded from: classes.dex */
public class C2067b {
    /* renamed from: a */
    public static void m2451a(VideoPlayer videoPlayer) {
        if (C0988e.m996v()) {
            C0988e.m969M(false);
            if (videoPlayer != null) {
                videoPlayer.m447J(false, "");
            }
        }
    }

    /* renamed from: b */
    public static void m2452b(DlnaContentEntity dlnaContentEntity, VideoPlayer videoPlayer) {
        try {
            CctvEntity cctvEntity = (CctvEntity) JSON.parseObject(dlnaContentEntity.getCctv(), CctvEntity.class);
            if (cctvEntity == null) {
                m2451a(videoPlayer);
                return;
            }
            CctvEntity.EventBean event = cctvEntity.getEvent();
            if (event == null || TextUtils.isEmpty(event.getType()) || !"ListenAudioVivid".equalsIgnoreCase(event.getType())) {
                m2451a(videoPlayer);
            } else {
                new C2002a().m2339a(dlnaContentEntity, videoPlayer);
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }
}
