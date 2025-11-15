package p192y1;

import android.support.v7.widget.RecyclerView;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.cctv.p025tv.module.service.UploadLogService;
import com.tencent.mars.xlog.Log;
import p009b.C0413b;
import p186x2.C2073a;

/* compiled from: UploadLogService.java */
/* renamed from: y1.e */
/* loaded from: classes.dex */
public class C2101e implements OSSCompletedCallback<PutObjectRequest, PutObjectResult> {

    /* renamed from: a */
    public final /* synthetic */ UploadLogService f6233a;

    public C2101e(UploadLogService uploadLogService) {
        this.f6233a = uploadLogService;
    }

    @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
    public void onFailure(OSSRequest oSSRequest, ClientException clientException, ServiceException serviceException) {
        if (clientException != null) {
            clientException.printStackTrace();
        }
        if (serviceException != null) {
            StringBuilder sbM112a = C0413b.m112a("uploadLog ErrorCode = ");
            sbM112a.append(serviceException.getErrorCode());
            Log.m651e("XLog_APP ", sbM112a.toString());
            C2073a.m2456a("uploadLog ErrorCode = " + serviceException.getErrorCode());
            C2073a.m2456a("uploadLog RequestId = " + serviceException.getRequestId());
            C2073a.m2456a("uploadLog HostId = " + serviceException.getHostId());
            C2073a.m2456a("uploadLog RawMessage = " + serviceException.getRawMessage());
        }
        UploadLogService uploadLogService = this.f6233a;
        uploadLogService.m457b(uploadLogService.f648f);
        this.f6233a.f649g.sendEmptyMessage(RecyclerView.MAX_SCROLL_DURATION);
    }

    @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
    public void onSuccess(OSSRequest oSSRequest, OSSResult oSSResult) {
        PutObjectResult putObjectResult = (PutObjectResult) oSSResult;
        Log.m655i("XLog_APP ", "uploadLog UploadSuccess");
        C2073a.m2459d("uploadLog UploadSuccess");
        C2073a.m2459d("uploadLog ETag = " + putObjectResult.getETag());
        C2073a.m2459d("uploadLog RequestId = " + putObjectResult.getRequestId());
        UploadLogService uploadLogService = this.f6233a;
        uploadLogService.m457b(uploadLogService.f648f);
        this.f6233a.f649g.sendEmptyMessage(2001);
    }
}
