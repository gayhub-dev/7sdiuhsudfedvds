package com.cctv.p025tv.module.function.guid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.entity.CheckPlayReportEntity;
import com.cctv.p025tv.module.collect.report.event.ReportDeviceInfoEvent;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.callback.SimpleCallBack;
import com.ctvit.network.exception.ApiException;
import com.ctvit.network.request.PostRequest;
import com.tencent.mars.xlog.Log;
import p009b.C0413b;
import p101m1.C1458b;
import p118o2.C1581b;
import p186x2.C2073a;
import p200z2.C2150a;

/* loaded from: classes.dex */
public class GuidManager {
    private static volatile GuidManager instance;
    private String firstDeviceId;
    private int createTimes = 0;
    private final int REGISTER_WAIT_MSG = 201;
    private final int REGISTER_FIRST_WAIT_MSG = 202;
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.cctv.tv.module.function.guid.GuidManager.1
        public HandlerC05811(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            int i7 = message.what;
            if (i7 == 201) {
                C2073a.m2459d("cloud registerDelay REGISTER_WAIT_MSG");
                GuidManager guidManager = GuidManager.this;
                guidManager.register(guidManager.getDeviceID(), true);
            } else if (i7 == 202) {
                C2073a.m2459d("cloud registerDelay REGISTER_FIRST_WAIT_MSG");
                GuidManager guidManager2 = GuidManager.this;
                guidManager2.register(guidManager2.firstDeviceId, false);
            }
        }
    };

    /* renamed from: com.cctv.tv.module.function.guid.GuidManager$1 */
    public class HandlerC05811 extends Handler {
        public HandlerC05811(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            int i7 = message.what;
            if (i7 == 201) {
                C2073a.m2459d("cloud registerDelay REGISTER_WAIT_MSG");
                GuidManager guidManager = GuidManager.this;
                guidManager.register(guidManager.getDeviceID(), true);
            } else if (i7 == 202) {
                C2073a.m2459d("cloud registerDelay REGISTER_FIRST_WAIT_MSG");
                GuidManager guidManager2 = GuidManager.this;
                guidManager2.register(guidManager2.firstDeviceId, false);
            }
        }
    }

    /* renamed from: com.cctv.tv.module.function.guid.GuidManager$2 */
    public class C05822 extends SimpleCallBack<RegisterEntity> {
        public final /* synthetic */ String val$finalSecretId;
        public final /* synthetic */ boolean val$first;

        public C05822(String str, boolean z6) {
            str = str;
            z = z6;
        }

        @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
        public void onError(ApiException apiException) {
            super.onError(apiException);
            if (GuidManager.this.createTimes > 3) {
                GuidManager.this.release();
            }
            C2073a.m2459d("cloud onError ");
        }

        @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
        public void onSuccess(RegisterEntity registerEntity) {
            super.onSuccess((C05822) registerEntity);
            StringBuilder sbM112a = C0413b.m112a("cloud onSuccess = ");
            sbM112a.append(JSON.toJSONString(registerEntity));
            C2073a.m2459d(sbM112a.toString());
            if (registerEntity.getResult() == 0) {
                if (registerEntity.getData() != null) {
                    TempCloudDevice.saveDeviceId(str);
                    TempCloudDevice.saveGuid(registerEntity.getData().getGuid());
                    TempCloudDevice.saveSecret(registerEntity.getData().getSecretKey());
                    GuidManager.this.release();
                    ReportDeviceInfoEvent.post(MyApplication.f561e);
                    return;
                }
                return;
            }
            if (z && (registerEntity.getResult() == 694 || registerEntity.getResult() == 695)) {
                GuidManager.this.registerDelay();
            } else {
                if (z || registerEntity.getResult() != 695) {
                    return;
                }
                GuidManager.this.registerDelay();
            }
        }
    }

    /* renamed from: com.cctv.tv.module.function.guid.GuidManager$3 */
    public class C05833 extends SimpleCallBack<CheckPlayReportEntity> {
        public C05833() {
        }

        @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
        public void onSuccess(CheckPlayReportEntity checkPlayReportEntity) {
            if (checkPlayReportEntity != null) {
                String result = checkPlayReportEntity.getResult();
                String message = checkPlayReportEntity.getMessage();
                if ("0".equals(result) && "SUCCESS".equals(message) && checkPlayReportEntity.getData() != null && checkPlayReportEntity.getData().f4388a != null) {
                    StringBuilder sbM112a = C0413b.m112a("getCheckPlayResult = ");
                    sbM112a.append(JSON.toJSONString(checkPlayReportEntity));
                    C2073a.m2459d(sbM112a.toString());
                    C2150a.m2591b("PLAY_CHECK_RATE", Integer.valueOf(checkPlayReportEntity.getData().f4388a.f4389a));
                    C2150a.m2591b("PLAY_CHECK_RATE_VIVID", Integer.valueOf(checkPlayReportEntity.getData().f4388a.f4390b));
                }
            }
            super.onSuccess((C05833) checkPlayReportEntity);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void getCheckPlayResult() {
        C2073a.m2459d("getCheckPlayResult");
        if ((((Integer) C2150a.m2590a("PLAY_CHECK_RATE", 0)).intValue() == 0 && ((Integer) C2150a.m2590a("PLAY_CHECK_RATE_VIVID", 0)).intValue() == 0) ? false : true) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("guid", (Object) TempCloudDevice.getGuid());
        ((PostRequest) CtvitHttp.post("https://ytpaddr.cctv.cn/gsnw/play/check/obtain").cacheMode(CacheMode.NO_CACHE)).upJson(jSONObject.toJSONString()).execute(new SimpleCallBack<CheckPlayReportEntity>() { // from class: com.cctv.tv.module.function.guid.GuidManager.3
            public C05833() {
            }

            @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
            public void onSuccess(CheckPlayReportEntity checkPlayReportEntity) {
                if (checkPlayReportEntity != null) {
                    String result = checkPlayReportEntity.getResult();
                    String message = checkPlayReportEntity.getMessage();
                    if ("0".equals(result) && "SUCCESS".equals(message) && checkPlayReportEntity.getData() != null && checkPlayReportEntity.getData().f4388a != null) {
                        StringBuilder sbM112a = C0413b.m112a("getCheckPlayResult = ");
                        sbM112a.append(JSON.toJSONString(checkPlayReportEntity));
                        C2073a.m2459d(sbM112a.toString());
                        C2150a.m2591b("PLAY_CHECK_RATE", Integer.valueOf(checkPlayReportEntity.getData().f4388a.f4389a));
                        C2150a.m2591b("PLAY_CHECK_RATE_VIVID", Integer.valueOf(checkPlayReportEntity.getData().f4388a.f4390b));
                    }
                }
                super.onSuccess((C05833) checkPlayReportEntity);
            }
        });
    }

    public String getDeviceID() {
        this.createTimes++;
        StringBuilder sbM112a = C0413b.m112a("cloud 重新获取deviceID createTimes = ");
        sbM112a.append(this.createTimes);
        Log.m655i("XLog_APP ", sbM112a.toString());
        return TempCloudDevice.createNewDeviceId(this.createTimes);
    }

    public static GuidManager getInstance() {
        if (instance == null) {
            synchronized (GuidManager.class) {
                if (instance == null) {
                    instance = new GuidManager();
                }
            }
        }
        return instance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void register(String str, boolean z6) {
        C2073a.m2459d("cloud register");
        if (this.createTimes > 3) {
            return;
        }
        C1458b.m1642a("cloud deviceId = ", str);
        boolean zHasDeviceId = z6 ? true : true ^ TempCloudDevice.hasDeviceId();
        JSONObject jSONObject = new JSONObject();
        if (zHasDeviceId) {
            try {
                str = CloudUtils.encryptByPublicKey(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/ZeLwTPPLSU7QGwv6tVgdawz9n7S2CxboIEVQlQ1USAHvBRlWBsU2l7+HuUVMJ5blqGc/5y3AoaUzPGoXPfIm0GnBdFL+iLeRDwOS1KgcQ0fIquvr/2Xzj3fVA1o4Y81wJK5BP8bDTBFYMVOlOoCc1ZzWwdZBYpb4FNxt//5dAwIDAQAB");
            } catch (Exception e7) {
                e7.printStackTrace();
                str = null;
            }
        }
        jSONObject.put("device_id", (Object) str);
        jSONObject.put("device_name", (Object) C1581b.m1837f());
        C2073a.m2459d("cloud json = " + jSONObject);
        ((PostRequest) CtvitHttp.post(zHasDeviceId ? "https://ytpcloudws.cctv.cn/cloudps/wssapi/device/v1/register" : "https://ytpcloudws.cctv.cn/cloudps/wssapi/device/v1/get").cacheMode(CacheMode.NO_CACHE)).upJson(jSONObject.toJSONString()).execute(new SimpleCallBack<RegisterEntity>() { // from class: com.cctv.tv.module.function.guid.GuidManager.2
            public final /* synthetic */ String val$finalSecretId;
            public final /* synthetic */ boolean val$first;

            public C05822(String str2, boolean zHasDeviceId2) {
                str = str2;
                z = zHasDeviceId2;
            }

            @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
            public void onError(ApiException apiException) {
                super.onError(apiException);
                if (GuidManager.this.createTimes > 3) {
                    GuidManager.this.release();
                }
                C2073a.m2459d("cloud onError ");
            }

            @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
            public void onSuccess(RegisterEntity registerEntity) {
                super.onSuccess((C05822) registerEntity);
                StringBuilder sbM112a = C0413b.m112a("cloud onSuccess = ");
                sbM112a.append(JSON.toJSONString(registerEntity));
                C2073a.m2459d(sbM112a.toString());
                if (registerEntity.getResult() == 0) {
                    if (registerEntity.getData() != null) {
                        TempCloudDevice.saveDeviceId(str);
                        TempCloudDevice.saveGuid(registerEntity.getData().getGuid());
                        TempCloudDevice.saveSecret(registerEntity.getData().getSecretKey());
                        GuidManager.this.release();
                        ReportDeviceInfoEvent.post(MyApplication.f561e);
                        return;
                    }
                    return;
                }
                if (z && (registerEntity.getResult() == 694 || registerEntity.getResult() == 695)) {
                    GuidManager.this.registerDelay();
                } else {
                    if (z || registerEntity.getResult() != 695) {
                        return;
                    }
                    GuidManager.this.registerDelay();
                }
            }
        });
    }

    public void registerDelay() {
        C2073a.m2459d("cloud registerDelay");
        Handler handler = this.handler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(201, 5000L);
        }
    }

    public void release() {
        if (this.handler != null) {
            C2073a.m2459d("cloud release");
            this.handler.removeCallbacksAndMessages(null);
            this.handler = null;
        }
    }

    public void initCloudRegister(String str) {
        this.firstDeviceId = str;
        Handler handler = this.handler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(202, 5000L);
        }
    }
}
