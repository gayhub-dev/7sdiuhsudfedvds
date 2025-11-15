package com.cctv.p025tv.module.collect;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.cctv.p025tv.module.collect.CollectDataEntity;
import com.cctv.p025tv.module.player.VideoPlayer;
import com.ctvit.dlna.entity.CctvEntity;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.request.PostRequest;
import java.util.ArrayList;
import p013b3.C0440a;
import p078j2.C1195j;
import p171v1.C2003b;
import p186x2.C2073a;

/* compiled from: CollectData.java */
/* renamed from: com.cctv.tv.module.collect.a */
/* loaded from: classes.dex */
public class C0579a {
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static void m412a(CollectDataEntity collectDataEntity) {
        String jSONString = JSON.toJSONString(collectDataEntity);
        C2073a.m2459d("CollectData = " + jSONString);
        ((PostRequest) CtvitHttp.post("https://ytpdata.cctv.cn/das/operation").cacheMode(CacheMode.NO_CACHE)).upJson(jSONString).execute();
    }

    /* renamed from: b */
    public static void m413b(VideoPlayer videoPlayer, CctvEntity.ClientBean clientBean, String str, long j7) {
        if (videoPlayer.getPlayEntity() != null) {
            try {
                CollectDataEntity collectDataEntity = new CollectDataEntity();
                if (m414c(collectDataEntity, clientBean) == null) {
                    return;
                }
                String strM2340a = C2003b.m2340a(videoPlayer.getPlayEntity());
                CollectDataEntity.C0575b c0575b = new CollectDataEntity.C0575b();
                c0575b.setPlayer_id(videoPlayer.getPlayerId());
                if (TextUtils.isEmpty(strM2340a)) {
                    strM2340a = videoPlayer.getPlayEntity().f1837a;
                }
                c0575b.setContent_id(strM2340a);
                c0575b.setContent_name(videoPlayer.getPlayEntity().f1838b);
                c0575b.setTotal_duration(videoPlayer.getPlayerView().getDuration() + "");
                c0575b.setPlayer_type(videoPlayer.getMediaController().m357h() ? "live" : "vod");
                c0575b.setPlayer_status(str);
                c0575b.setPlayer_url(videoPlayer.getPlayerView().getCurrentPlayPath());
                c0575b.setPlayer_rate(videoPlayer.getPlayEntity().m1009c());
                c0575b.setCurrent_position(videoPlayer.getPlayerView().getCurrentPosition() + "");
                c0575b.setOccur_time(C0440a.m151b(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss", "GMT+08"));
                c0575b.setDuration(j7 + "");
                c0575b.setNetwork_type(C1195j.m1418b());
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(c0575b);
                collectDataEntity.setVideo(arrayList);
                m412a(collectDataEntity);
            } catch (Exception e7) {
                C2073a.m2458c(e7);
            }
        }
    }

    /* renamed from: c */
    public static CollectDataEntity.C0574a m414c(CollectDataEntity collectDataEntity, CctvEntity.ClientBean clientBean) {
        try {
            CollectDataEntity.C0574a common = collectDataEntity.getCommon();
            common.setApp_key(clientBean.getApp_key());
            common.setUserid(clientBean.getUser_id());
            CctvEntity.ClientBean.AndroidDeviceIdBean android_device_id = clientBean.getAndroid_device_id();
            CctvEntity.ClientBean.IosDeviceIdBean ios_device_id = clientBean.getIos_device_id();
            CollectDataEntity.C0574a.b bVar = new CollectDataEntity.C0574a.b();
            if (android_device_id != null) {
                bVar.setAndroid_id(android_device_id.getAndroid_id());
                bVar.setImei(android_device_id.getImei());
                bVar.setSerial(android_device_id.getSerial());
            }
            if (ios_device_id != null) {
                bVar.setUd_id(ios_device_id.getUd_id());
            }
            common.setMobile_id(bVar);
            return common;
        } catch (Exception e7) {
            C2073a.m2458c(e7);
            return null;
        }
    }
}
