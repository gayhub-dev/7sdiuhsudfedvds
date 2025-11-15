package p192y1;

import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.cctv.p025tv.module.service.UploadLogService;
import p186x2.C2073a;

/* compiled from: UploadLogService.java */
/* renamed from: y1.d */
/* loaded from: classes.dex */
public class C2100d implements OSSProgressCallback<PutObjectRequest> {
    public C2100d(UploadLogService uploadLogService) {
    }

    @Override // com.alibaba.sdk.android.oss.callback.OSSProgressCallback
    public void onProgress(PutObjectRequest putObjectRequest, long j7, long j8) {
        C2073a.m2459d("uploadLog currentSize: " + j7 + " totalSize: " + j8);
    }
}
