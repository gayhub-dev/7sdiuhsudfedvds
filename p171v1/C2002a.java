package p171v1;

import android.media.AudioManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.module.player.VideoPlayer;
import com.ctvit.dlna.entity.CctvEntity;
import com.ctvit.dlna.entity.DlnaContentEntity;
import p043f.C0988e;
import p172v2.C2004a;
import p186x2.C2073a;

/* compiled from: ControlCctvEvent.java */
/* renamed from: v1.a */
/* loaded from: classes.dex */
public class C2002a {
    /* renamed from: a */
    public void m2339a(DlnaContentEntity dlnaContentEntity, VideoPlayer videoPlayer) {
        try {
            CctvEntity cctvEntity = (CctvEntity) JSON.parseObject(dlnaContentEntity.getCctv(), CctvEntity.class);
            CctvEntity.EventBean event = cctvEntity.getEvent();
            String type = event.getType();
            String value = event.getValue();
            if ("review".equalsIgnoreCase(type)) {
                C2073a.m2459d("审片");
                return;
            }
            if (TextUtils.isEmpty(value)) {
                return;
            }
            if ("speed".equalsIgnoreCase(type)) {
                C2073a.m2459d("倍速播放：" + value);
                float fFloatValue = Float.valueOf(value).floatValue();
                if (fFloatValue > -1.0f) {
                    videoPlayer.getPlayerView().setSpeed(fFloatValue);
                }
                C2004a c2004a = new C2004a("当前视频已切换为" + fFloatValue + "倍速度播放");
                c2004a.f5846a = RecyclerView.MAX_SCROLL_DURATION;
                c2004a.m2345a();
                return;
            }
            if ("seek_to".equalsIgnoreCase(type)) {
                C2073a.m2459d("指定位置播放：" + value);
                int iIntValue = Integer.valueOf(value).intValue();
                if (iIntValue > -1) {
                    videoPlayer.mo389t(iIntValue);
                    return;
                }
                return;
            }
            if ("volume".equalsIgnoreCase(type)) {
                if (C0988e.m996v()) {
                    return;
                }
                C2073a.m2459d("音量控制：" + value);
                AudioManager audioManager = (AudioManager) MyApplication.f561e.getSystemService("audio");
                if ("raise".equalsIgnoreCase(value)) {
                    audioManager.adjustStreamVolume(3, 1, 5);
                    return;
                } else {
                    if ("lower".equalsIgnoreCase(value)) {
                        audioManager.adjustStreamVolume(3, -1, 5);
                        return;
                    }
                    return;
                }
            }
            if ("ListenAudioVivid".equalsIgnoreCase(type)) {
                if ("on".equalsIgnoreCase(value)) {
                    C0988e.m969M(true);
                    if (videoPlayer != null) {
                        videoPlayer.m447J(true, cctvEntity.getItem_id());
                        return;
                    }
                    return;
                }
                if ("off".equalsIgnoreCase(value)) {
                    C0988e.m969M(false);
                    if (videoPlayer != null) {
                        videoPlayer.m447J(false, cctvEntity.getItem_id());
                    }
                }
            }
        } catch (Exception e7) {
            C2073a.m2458c(e7);
        }
    }
}
