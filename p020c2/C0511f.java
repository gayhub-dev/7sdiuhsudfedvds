package p020c2;

import com.alibaba.fastjson.JSON;
import com.cctv.p025tv.entity.DRMCertEntity;
import com.cctv.p025tv.mvp.p026ui.activity.MainActivity;
import com.ctvit.network.callback.SimpleCallBack;
import com.tencent.mars.xlog.Log;
import p009b.C0413b;
import p186x2.C2073a;
import p200z2.C2150a;

/* compiled from: MainActivity.java */
/* renamed from: c2.f */
/* loaded from: classes.dex */
public class C0511f extends SimpleCallBack<DRMCertEntity> {
    public C0511f(MainActivity mainActivity) {
    }

    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    public void onSuccess(Object obj) {
        DRMCertEntity dRMCertEntity = (DRMCertEntity) obj;
        super.onSuccess(dRMCertEntity);
        if (dRMCertEntity == null || !"0".equals(dRMCertEntity.getResult()) || dRMCertEntity.getData() == null) {
            return;
        }
        try {
            C2073a.m2459d("drm info = " + JSON.toJSONString(dRMCertEntity.getData()));
            C2150a.m2591b("DRM_CERT", JSON.toJSONString(dRMCertEntity));
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Drm getDrmCert 数据解析报错");
            sbM112a.append(e7.getMessage());
            Log.m651e("XLog_APP ", sbM112a.toString());
        }
    }
}
