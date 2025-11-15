package p192y1;

import android.content.Context;
import com.cctv.p025tv.entity.UploadInfoEntity;
import com.cctv.p025tv.module.service.UploadLogService;
import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import com.ctvit.dlna.entity.DlnaContentEntity;

/* renamed from: y1.c */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC2099c implements Runnable {

    /* renamed from: e */
    public final /* synthetic */ int f6230e = 0;

    /* renamed from: f */
    public final /* synthetic */ Object f6231f;

    /* renamed from: g */
    public final /* synthetic */ Object f6232g;

    public /* synthetic */ RunnableC2099c(UploadLogService.C0594b c0594b, UploadInfoEntity.DataBean dataBean) {
        this.f6231f = c0594b;
        this.f6232g = dataBean;
    }

    public /* synthetic */ RunnableC2099c(VideoFragment videoFragment, DlnaContentEntity dlnaContentEntity) {
        this.f6231f = videoFragment;
        this.f6232g = dlnaContentEntity;
    }

    public /* synthetic */ RunnableC2099c(DlnaContentEntity dlnaContentEntity, Context context) {
        this.f6231f = dlnaContentEntity;
        this.f6232g = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0183 A[Catch: Exception -> 0x01c4, TryCatch #4 {Exception -> 0x01c4, blocks: (B:230:0x0104, B:232:0x010c, B:234:0x011a, B:235:0x0124, B:237:0x0142, B:238:0x0148, B:240:0x0152, B:249:0x0174, B:251:0x0179, B:253:0x0183, B:255:0x0192, B:254:0x018b, B:244:0x015f, B:246:0x0169, B:250:0x0177), top: B:356:0x0104 }] */
    /* JADX WARN: Removed duplicated region for block: B:254:0x018b A[Catch: Exception -> 0x01c4, TryCatch #4 {Exception -> 0x01c4, blocks: (B:230:0x0104, B:232:0x010c, B:234:0x011a, B:235:0x0124, B:237:0x0142, B:238:0x0148, B:240:0x0152, B:249:0x0174, B:251:0x0179, B:253:0x0183, B:255:0x0192, B:254:0x018b, B:244:0x015f, B:246:0x0169, B:250:0x0177), top: B:356:0x0104 }] */
    /* JADX WARN: Removed duplicated region for block: B:267:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x02fd A[Catch: Exception -> 0x0321, TRY_LEAVE, TryCatch #5 {Exception -> 0x0321, blocks: (B:285:0x02e5, B:287:0x02fd), top: B:358:0x02e5 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1632
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p192y1.RunnableC2099c.run():void");
    }
}
