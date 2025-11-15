package p150s1;

import com.cctv.p025tv.mvp.p026ui.view.versionupdate.VersionDownloadingView;
import com.ctvit.network.callback.DownloadProgressCallBack;
import com.ctvit.network.exception.ApiException;
import p094l2.C1420a;
import p110n2.InterfaceC1517a;
import p186x2.C2073a;

/* compiled from: DownloadApk.java */
/* renamed from: s1.a */
/* loaded from: classes.dex */
public class C1864a extends DownloadProgressCallBack<String> {

    /* renamed from: e */
    public final /* synthetic */ C1865b f5439e;

    public C1864a(C1865b c1865b) {
        this.f5439e = c1865b;
    }

    @Override // com.ctvit.network.callback.DownloadProgressCallBack
    public void onComplete(String str) {
        try {
            C1868e.f5445a = false;
            InterfaceC1517a interfaceC1517a = this.f5439e.f5441b;
            if (interfaceC1517a != null) {
                ((VersionDownloadingView.C0622a) interfaceC1517a).m523a(1);
            }
            C1868e.m2140c(C1420a.m1605b(), str);
        } catch (Exception e7) {
            C2073a.m2458c(e7);
        }
    }

    @Override // com.ctvit.network.callback.DownloadProgressCallBack, com.ctvit.network.callback.CallBack
    public void onError(ApiException apiException) {
        super.onError(apiException);
        C2073a.m2459d("下载失败！");
        InterfaceC1517a interfaceC1517a = this.f5439e.f5441b;
        if (interfaceC1517a != null) {
            ((VersionDownloadingView.C0622a) interfaceC1517a).m523a(2);
        }
    }

    @Override // com.ctvit.network.callback.DownloadProgressCallBack
    public void update(long j7, long j8, boolean z6) {
        float f7 = (j7 / j8) * 100.0f;
        InterfaceC1517a interfaceC1517a = this.f5439e.f5441b;
        if (interfaceC1517a != null) {
            VersionDownloadingView.C0622a c0622a = (VersionDownloadingView.C0622a) interfaceC1517a;
            int i7 = (int) f7;
            VersionDownloadingView.this.f912f.setText("正在更新..." + i7 + "%");
            VersionDownloadingView.this.f915i.setProgress(i7);
        }
    }
}
