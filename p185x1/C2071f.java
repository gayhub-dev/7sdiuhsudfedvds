package p185x1;

import com.cctv.p025tv.entity.PlayerThresholdEntity;
import com.ctvit.network.callback.SimpleCallBack;
import com.ctvit.network.exception.ApiException;

/* compiled from: PlayerThreshold.java */
/* renamed from: x1.f */
/* loaded from: classes.dex */
public class C2071f extends SimpleCallBack<PlayerThresholdEntity> {

    /* renamed from: f */
    public final /* synthetic */ C2072g f6166f;

    public C2071f(C2072g c2072g) {
        this.f6166f = c2072g;
    }

    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    public void onError(ApiException apiException) {
        super.onError(apiException);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0057 A[SYNTHETIC] */
    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onSuccess(java.lang.Object r9) {
        /*
            r8 = this;
            com.cctv.tv.entity.PlayerThresholdEntity r9 = (com.cctv.p025tv.entity.PlayerThresholdEntity) r9
            super.onSuccess(r9)
            x1.g r0 = r8.f6166f
            java.util.Objects.requireNonNull(r0)
            if (r9 != 0) goto Le
            goto Lfa
        Le:
            java.lang.String r1 = r9.getResult()
            java.lang.String r2 = "0"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto Lfa
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean r1 = r9.getData()
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean$DefaultVodBean r1 = r1.getDefaultVod()
            r0.f6168a = r1
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean r1 = r9.getData()
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean$DefaultLiveBean r1 = r1.getDefaultLive()
            r0.f6169b = r1
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean$DefaultVodBean r1 = r0.f6168a
            int r1 = r1.getLevel1()
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean$DefaultVodBean r2 = r0.f6168a
            int r2 = r2.getLevel2()
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean$DefaultLiveBean r3 = r0.f6169b
            int r3 = r3.getLevel1()
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean$DefaultLiveBean r4 = r0.f6169b
            int r4 = r4.getLevel2()
            r0.m2454a(r1, r2, r3, r4)
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean r9 = r9.getData()
            java.util.List r9 = r9.getDeviceList()
            if (r9 == 0) goto Lfa
            java.util.Iterator r9 = r9.iterator()
        L57:
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto Lfa
            java.lang.Object r1 = r9.next()
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean$DeviceListBean r1 = (com.cctv.tv.entity.PlayerThresholdEntity.DataBean.DeviceListBean) r1
            java.lang.String r2 = r1.getBrand()
            java.lang.String r3 = r1.getModel()
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 == 0) goto L7d
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 == 0) goto L7d
            java.lang.String r1 = "设备品牌+型号为空！！！"
            p186x2.C2073a.m2456a(r1)
            goto L57
        L7d:
            r4 = 0
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 != 0) goto Lad
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            if (r5 != 0) goto Lad
            java.lang.String r5 = android.arch.lifecycle.C0063n.m88a(r2, r3)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = r0.f6170c
            r6.append(r7)
            java.lang.String r7 = r0.f6171d
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            boolean r5 = r5.equalsIgnoreCase(r6)
            if (r5 == 0) goto Lad
            java.lang.String r2 = "设备品牌+型号匹配"
            p186x2.C2073a.m2459d(r2)
            goto Ld4
        Lad:
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 != 0) goto Lc1
            java.lang.String r5 = r0.f6170c
            boolean r5 = r2.equalsIgnoreCase(r5)
            if (r5 == 0) goto Lc1
            java.lang.String r2 = "设备品牌匹配"
            p186x2.C2073a.m2459d(r2)
            goto Ld4
        Lc1:
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto Ld5
            java.lang.String r3 = r0.f6171d
            boolean r2 = r2.equalsIgnoreCase(r3)
            if (r2 == 0) goto Ld5
            java.lang.String r2 = "设备型号匹配"
            p186x2.C2073a.m2459d(r2)
        Ld4:
            r4 = 1
        Ld5:
            if (r4 == 0) goto L57
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean$DeviceListBean$VodBean r9 = r1.getVod()
            int r9 = r9.getLevel1()
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean$DeviceListBean$VodBean r2 = r1.getVod()
            int r2 = r2.getLevel2()
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean$DeviceListBean$LiveBean r3 = r1.getLive()
            int r3 = r3.getLevel1()
            com.cctv.tv.entity.PlayerThresholdEntity$DataBean$DeviceListBean$LiveBean r1 = r1.getLive()
            int r1 = r1.getLevel2()
            r0.m2454a(r9, r2, r3, r1)
        Lfa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p185x1.C2071f.onSuccess(java.lang.Object):void");
    }
}
