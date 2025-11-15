package p054g2;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.cctv.p025tv.entity.CheckPlayListEntity;
import com.cctv.p025tv.mvp.p026ui.fragment.CheckPlayerFragment;
import com.ctvit.network.callback.SimpleCallBack;
import java.util.ArrayList;
import java.util.List;
import p009b.C0413b;
import p045f1.C0992a;
import p045f1.C0993b;
import p186x2.C2073a;

/* compiled from: CheckPlayerFragment.java */
/* renamed from: g2.c */
/* loaded from: classes.dex */
public class C1052c extends SimpleCallBack<CheckPlayListEntity> {

    /* renamed from: f */
    public final /* synthetic */ CheckPlayerFragment f1979f;

    public C1052c(CheckPlayerFragment checkPlayerFragment) {
        this.f1979f = checkPlayerFragment;
    }

    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    public void onSuccess(Object obj) {
        CheckPlayListEntity checkPlayListEntity = (CheckPlayListEntity) obj;
        StringBuilder sbM112a = C0413b.m112a("CheckPlayListEntity = ");
        sbM112a.append(JSON.toJSONString(checkPlayListEntity));
        C2073a.m2459d(sbM112a.toString());
        try {
            String result = checkPlayListEntity.getResult();
            String message = checkPlayListEntity.getMessage();
            if ("0".equals(result) && "SUCCESS".equals(message)) {
                List<CheckPlayListEntity.DataBean.VideoListBean> videoList = checkPlayListEntity.getData().getVideoList();
                if (videoList != null && !videoList.isEmpty()) {
                    C0992a c0992a = new C0992a();
                    ArrayList arrayList = new ArrayList();
                    for (int i7 = 0; i7 < videoList.size(); i7++) {
                        CheckPlayListEntity.DataBean.VideoListBean videoListBean = videoList.get(i7);
                        String url = videoListBean.getUrl();
                        String rateName = videoListBean.getRateName();
                        String strValueOf = String.valueOf(videoListBean.getRateValue());
                        String audioType = videoListBean.getAudioType();
                        String videoType = videoListBean.getVideoType();
                        if (!TextUtils.isEmpty(url)) {
                            C0993b c0993b = new C0993b();
                            c0993b.f1850a = url;
                            c0993b.f1851b = rateName;
                            c0993b.f1852c = strValueOf;
                            c0993b.f1853d = videoListBean.getIsDefault().booleanValue();
                            c0993b.f1854e = "2".equals(audioType) || "2".equals(videoType);
                            c0993b.f1855f = "2".equals(videoType);
                            arrayList.add(c0993b);
                        }
                    }
                    c0992a.f1843g = arrayList;
                    this.f1979f.f758h.m432x(c0992a);
                }
            } else {
                C2073a.m2456a("搞码率接口返回失败信息");
            }
        } catch (Exception e7) {
            C2073a.m2458c(e7);
        }
        super.onSuccess(checkPlayListEntity);
    }
}
