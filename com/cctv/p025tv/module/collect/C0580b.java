package com.cctv.p025tv.module.collect;

import com.cctv.p025tv.module.collect.CollectEventDataEntity;
import com.ctvit.dlna.entity.CctvEntity;
import com.ctvit.dlna.entity.DlnaContentEntity;
import p078j2.C1186a;
import p186x2.C2073a;

/* compiled from: CollectEventData.java */
/* renamed from: com.cctv.tv.module.collect.b */
/* loaded from: classes.dex */
public class C0580b {
    /* renamed from: a */
    public static String m415a(String str) {
        if ("MainFragment".equals(str)) {
            return "indexPage";
        }
        if ("SystemFragment".equals(str)) {
            return "settingPage";
        }
        if ("AboutUsFragment".equals(str)) {
            return "aboutUsPage";
        }
        if ("VersionUpdateFragment".equals(str)) {
            return "versionPage";
        }
        if ("ChangeNameFragment".equals(str)) {
            return "tvNamePage";
        }
        if ("DlnaModifyNameFragment".equals(str)) {
            return "myNamePage";
        }
        if ("SharpnessSwitchFragment".equals(str)) {
            return "sharpnessSwitchPage";
        }
        if ("PrivacyPolicyFragment".equals(str)) {
            return "privacyPolicyPage";
        }
        if ("VideoFragment".equals(str)) {
            return "playPage";
        }
        return null;
    }

    /* renamed from: b */
    public static boolean m416b(String str) {
        return "ABOUTUSS".equals(str) || "DEVICENAME".equals(str) || "UPDATE".equals(str) || "SETTING".equals(str) || "SHARPNESSSWITCH".equals(str) || "PRIVACYPOLICY".equals(str) || "NAME_CUSTOM".equals(str) || "BACK".equals(str) || "DOWNLOAD_SKIP".equals(str) || "UPDATE_CHECK_CANCEL".equals(str) || "UPDATE_CHECK_YES".equals(str) || "SHARPNESSSWITCH_HINT_OFF".equals(str) || "DLNA".equals(str) || "DLNA_EXIT".equals(str);
    }

    /* renamed from: c */
    public static void m417c(String str, String str2) {
        if (C1186a.m1385e()) {
            m418d(str, str2, new DlnaContentEntity());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m418d(java.lang.String r6, java.lang.String r7, com.ctvit.dlna.entity.DlnaContentEntity r8) {
        /*
            java.lang.String r0 = "GMT+08"
            java.lang.String r1 = "yyyy-MM-dd HH:mm:ss"
            r2 = 0
            if (r8 == 0) goto L38
            java.lang.String r3 = r8.getCctv()     // Catch: java.lang.Exception -> L35
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L35
            if (r3 != 0) goto L38
            java.lang.String r8 = r8.getCctv()     // Catch: java.lang.Exception -> L35
            java.lang.Class<com.ctvit.dlna.entity.CctvEntity> r3 = com.ctvit.dlna.entity.CctvEntity.class
            java.lang.Object r8 = com.alibaba.fastjson.JSON.parseObject(r8, r3)     // Catch: java.lang.Exception -> L35
            com.ctvit.dlna.entity.CctvEntity r8 = (com.ctvit.dlna.entity.CctvEntity) r8     // Catch: java.lang.Exception -> L35
            if (r8 == 0) goto L38
            com.ctvit.dlna.entity.CctvEntity$ClientBean r3 = r8.getClient()     // Catch: java.lang.Exception -> L35
            com.ctvit.dlna.entity.CctvEntity$PlayerBean r4 = r8.getPlayer()     // Catch: java.lang.Exception -> L35
            if (r4 == 0) goto L32
            com.ctvit.dlna.entity.CctvEntity$PlayerBean r8 = r8.getPlayer()     // Catch: java.lang.Exception -> L35
            java.lang.String r8 = r8.getHigh_bitrate_id()     // Catch: java.lang.Exception -> L35
            r2 = r8
        L32:
            r8 = r2
            r2 = r3
            goto L39
        L35:
            r6 = move-exception
            goto Le2
        L38:
            r8 = r2
        L39:
            if (r2 != 0) goto L40
            com.ctvit.dlna.entity.CctvEntity$ClientBean r2 = new com.ctvit.dlna.entity.CctvEntity$ClientBean     // Catch: java.lang.Exception -> L35
            r2.<init>()     // Catch: java.lang.Exception -> L35
        L40:
            com.cctv.tv.module.collect.CollectEventDataEntity r3 = new com.cctv.tv.module.collect.CollectEventDataEntity     // Catch: java.lang.Exception -> L35
            r3.<init>()     // Catch: java.lang.Exception -> L35
            com.cctv.tv.module.collect.CollectEventDataEntity$a r2 = m419e(r3, r2)     // Catch: java.lang.Exception -> L35
            if (r2 != 0) goto L4c
            return
        L4c:
            com.cctv.tv.module.collect.CollectEventDataEntity$b r2 = new com.cctv.tv.module.collect.CollectEventDataEntity$b     // Catch: java.lang.Exception -> L35
            r2.<init>()     // Catch: java.lang.Exception -> L35
            r2.setContent_id(r8)     // Catch: java.lang.Exception -> L35
            r2.setEvent_id(r6)     // Catch: java.lang.Exception -> L35
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L35
            java.lang.String r8 = p013b3.C0440a.m151b(r4, r1, r0)     // Catch: java.lang.Exception -> L35
            r2.setOccur_time(r8)     // Catch: java.lang.Exception -> L35
            com.cctv.tv.module.collect.CollectEventDataEntity$c r8 = new com.cctv.tv.module.collect.CollectEventDataEntity$c     // Catch: java.lang.Exception -> L35
            r8.<init>()     // Catch: java.lang.Exception -> L35
            java.lang.String r4 = com.cctv.p025tv.app.MyApplication.f567k     // Catch: java.lang.Exception -> L35
            r8.setSession_start_time(r4)     // Catch: java.lang.Exception -> L35
            org.fourthline.cling.model.types.UDN r4 = p043f.C0988e.m972P()     // Catch: java.lang.Exception -> L35
            java.lang.String r4 = r4.getIdentifierString()     // Catch: java.lang.Exception -> L35
            r8.setSession_id(r4)     // Catch: java.lang.Exception -> L35
            long r4 = com.cctv.p025tv.app.MyApplication.f565i     // Catch: java.lang.Exception -> L35
            java.lang.String r0 = p013b3.C0440a.m151b(r4, r1, r0)     // Catch: java.lang.Exception -> L35
            r8.setPage_start_time(r0)     // Catch: java.lang.Exception -> L35
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L35
            long r4 = com.cctv.p025tv.app.MyApplication.f565i     // Catch: java.lang.Exception -> L35
            long r0 = r0 - r4
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch: java.lang.Exception -> L35
            r8.setDuration(r0)     // Catch: java.lang.Exception -> L35
            java.lang.String r0 = m415a(r7)     // Catch: java.lang.Exception -> L35
            r8.setCur_page(r0)     // Catch: java.lang.Exception -> L35
            java.lang.String r0 = com.cctv.p025tv.app.MyApplication.f566j     // Catch: java.lang.Exception -> L35
            java.lang.String r0 = m415a(r0)     // Catch: java.lang.Exception -> L35
            r8.setPrev_page(r0)     // Catch: java.lang.Exception -> L35
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Exception -> L35
            r1 = 1
            r0.<init>(r1)     // Catch: java.lang.Exception -> L35
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch: java.lang.Exception -> L35
            r4.<init>(r1)     // Catch: java.lang.Exception -> L35
            r0.add(r2)     // Catch: java.lang.Exception -> L35
            r4.add(r8)     // Catch: java.lang.Exception -> L35
            r3.setEvent(r0)     // Catch: java.lang.Exception -> L35
            r3.setPage(r4)     // Catch: java.lang.Exception -> L35
            java.lang.String r8 = com.alibaba.fastjson.JSON.toJSONString(r3)     // Catch: java.lang.Exception -> L35
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L35
            r0.<init>()     // Catch: java.lang.Exception -> L35
            java.lang.String r1 = "jsonEVENT = "
            r0.append(r1)     // Catch: java.lang.Exception -> L35
            r0.append(r8)     // Catch: java.lang.Exception -> L35
            java.lang.String r8 = r0.toString()     // Catch: java.lang.Exception -> L35
            p186x2.C2073a.m2459d(r8)     // Catch: java.lang.Exception -> L35
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L35
            if (r8 != 0) goto Le5
            boolean r6 = m416b(r6)     // Catch: java.lang.Exception -> L35
            if (r6 == 0) goto Le5
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L35
            com.cctv.p025tv.app.MyApplication.f565i = r0     // Catch: java.lang.Exception -> L35
            com.cctv.p025tv.app.MyApplication.f566j = r7     // Catch: java.lang.Exception -> L35
            goto Le5
        Le2:
            p186x2.C2073a.m2458c(r6)
        Le5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cctv.p025tv.module.collect.C0580b.m418d(java.lang.String, java.lang.String, com.ctvit.dlna.entity.DlnaContentEntity):void");
    }

    /* renamed from: e */
    public static CollectEventDataEntity.C0576a m419e(CollectEventDataEntity collectEventDataEntity, CctvEntity.ClientBean clientBean) {
        try {
            CollectEventDataEntity.C0576a common = collectEventDataEntity.getCommon();
            common.setApp_key(clientBean.getApp_key());
            common.setUserid(clientBean.getUser_id());
            CctvEntity.ClientBean.AndroidDeviceIdBean android_device_id = clientBean.getAndroid_device_id();
            CctvEntity.ClientBean.IosDeviceIdBean ios_device_id = clientBean.getIos_device_id();
            CollectEventDataEntity.C0576a.b bVar = new CollectEventDataEntity.C0576a.b();
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
