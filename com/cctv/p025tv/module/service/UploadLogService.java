package com.cctv.p025tv.module.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cctv.p025tv.R;
import com.cctv.p025tv.entity.UploadInfoEntity;
import com.cctv.p025tv.module.function.guid.CloudUtils;
import com.cctv.p025tv.module.function.guid.TempCloudDevice;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.callback.SimpleCallBack;
import com.ctvit.network.exception.ApiException;
import com.ctvit.network.request.PostRequest;
import com.tencent.mars.xlog.Log;
import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;
import p078j2.C1196k;
import p165u2.C1974a;
import p186x2.C2073a;
import p192y1.C2102f;
import p192y1.RunnableC2099c;
import p199z1.InterfaceC2148b;

/* loaded from: classes.dex */
public class UploadLogService extends Service {

    /* renamed from: h */
    public static final /* synthetic */ int f646h = 0;

    /* renamed from: f */
    public String f648f;

    /* renamed from: e */
    public BinderC0595c f647e = new BinderC0595c(null);

    /* renamed from: g */
    public Handler f649g = new HandlerC0593a(Looper.getMainLooper());

    /* renamed from: com.cctv.tv.module.service.UploadLogService$a */
    public class HandlerC0593a extends Handler {
        public HandlerC0593a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            int i7 = message.what;
            if (i7 == 2000) {
                UploadLogService uploadLogService = UploadLogService.this;
                int i8 = UploadLogService.f646h;
                Objects.requireNonNull(uploadLogService);
                C1974a.m2299b(R.string.log_upload_failed).m2345a();
            } else if (i7 == 2001) {
                UploadLogService uploadLogService2 = UploadLogService.this;
                int i9 = UploadLogService.f646h;
                Objects.requireNonNull(uploadLogService2);
                C1974a.m2299b(R.string.log_upload_success).m2345a();
            }
            super.handleMessage(message);
        }
    }

    /* renamed from: com.cctv.tv.module.service.UploadLogService$b */
    public class C0594b extends SimpleCallBack<UploadInfoEntity> {
        public C0594b() {
        }

        @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
        public void onError(ApiException apiException) {
            super.onError(apiException);
            UploadLogService.this.f649g.sendEmptyMessage(RecyclerView.MAX_SCROLL_DURATION);
        }

        @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
        public void onSuccess(Object obj) {
            UploadInfoEntity uploadInfoEntity = (UploadInfoEntity) obj;
            super.onSuccess(uploadInfoEntity);
            if (uploadInfoEntity == null || !"0".equals(uploadInfoEntity.getResult()) || uploadInfoEntity.getData() == null) {
                return;
            }
            UploadInfoEntity.DataBean data = uploadInfoEntity.getData();
            StringBuilder sbM112a = C0413b.m112a("UploadInfo entity = ");
            sbM112a.append(JSON.toJSONString(data));
            C2073a.m2459d(sbM112a.toString());
            new Thread(new RunnableC2099c(this, data)).start();
        }
    }

    /* renamed from: com.cctv.tv.module.service.UploadLogService$c */
    public class BinderC0595c extends Binder implements InterfaceC2148b {
        public BinderC0595c(C2102f c2102f) {
        }

        @Override // p199z1.InterfaceC2148b
        /* renamed from: a */
        public UploadLogService mo459a() {
            return UploadLogService.this;
        }
    }

    /* renamed from: a */
    public final void m456a(String str, String str2, ZipOutputStream zipOutputStream) {
        try {
            File file = new File(str + str2);
            if (!file.isFile()) {
                String[] list = file.list();
                if (list.length <= 0) {
                    zipOutputStream.putNextEntry(new ZipEntry(str2 + File.separator));
                    zipOutputStream.closeEntry();
                }
                for (String str3 : list) {
                    m456a(str + str2 + ServiceReference.DELIMITER, str3, zipOutputStream);
                }
                return;
            }
            ZipEntry zipEntry = new ZipEntry(str2);
            FileInputStream fileInputStream = new FileInputStream(file);
            zipOutputStream.putNextEntry(zipEntry);
            byte[] bArr = new byte[4096];
            while (true) {
                int i7 = fileInputStream.read(bArr);
                if (i7 == -1) {
                    zipOutputStream.closeEntry();
                    return;
                }
                zipOutputStream.write(bArr, 0, i7);
            }
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("uploadLog ZipFiles Exception = ");
            sbM112a.append(e7.getMessage());
            C2073a.m2456a(sbM112a.toString());
            Log.m651e("XLog_APP ", "uploadLog ZipFiles Exception = " + e7.getMessage());
            this.f649g.sendEmptyMessage(RecyclerView.MAX_SCROLL_DURATION);
        }
    }

    /* renamed from: b */
    public final void m457b(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (!file.isDirectory()) {
                if (file.isFile()) {
                    C2073a.m2459d("uploadLog delete = " + file.delete());
                    return;
                }
                return;
            }
            File[] fileArrListFiles = file.listFiles();
            Objects.requireNonNull(fileArrListFiles);
            for (File file2 : fileArrListFiles) {
                m457b(file2.getPath());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: c */
    public void m458c() {
        String strEncryptByPublicKey;
        C2073a.m2459d("UploadLogService uploadLog");
        try {
            strEncryptByPublicKey = CloudUtils.encryptByPublicKey(TempCloudDevice.getGuid(), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/ZeLwTPPLSU7QGwv6tVgdawz9n7S2CxboIEVQlQ1USAHvBRlWBsU2l7+HuUVMJ5blqGc/5y3AoaUzPGoXPfIm0GnBdFL+iLeRDwOS1KgcQ0fIquvr/2Xzj3fVA1o4Y81wJK5BP8bDTBFYMVOlOoCc1ZzWwdZBYpb4FNxt//5dAwIDAQAB");
        } catch (Exception e7) {
            e7.printStackTrace();
            strEncryptByPublicKey = "";
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("guid", (Object) strEncryptByPublicKey);
        StringBuilder sbM112a = C0413b.m112a("UploadInfo json = ");
        sbM112a.append(jSONObject.toJSONString());
        C2073a.m2459d(sbM112a.toString());
        ((PostRequest) CtvitHttp.post("https://ytpaddr.cctv.cn/gsnw/log/upload/token/obtain").cacheMode(CacheMode.NO_CACHE)).upJson(jSONObject.toJSONString()).execute(new C0594b());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f647e;
    }

    @Override // android.app.Service
    public void onCreate() {
        startForeground(1004, C1196k.m1420a());
        C2073a.m2459d("onCreate");
        Log.m655i("XLog_APP ", " UploadLogService onCreate");
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        C2073a.m2459d("onDestroy");
        Log.m655i("XLog_APP ", " UploadLogService onDestroy");
        stopForeground(true);
        Handler handler = this.f649g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f649g = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        C2073a.m2459d("onStartCommand");
        return super.onStartCommand(intent, i7, i8);
    }
}
