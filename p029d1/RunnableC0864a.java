package p029d1;

import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import com.tencent.mars.xlog.Log;
import java.io.File;
import java.util.Objects;
import p186x2.C2073a;

/* renamed from: d1.a */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC0864a implements Runnable {

    /* renamed from: f */
    public static final /* synthetic */ RunnableC0864a f1289f = new RunnableC0864a(0);

    /* renamed from: g */
    public static final /* synthetic */ RunnableC0864a f1290g = new RunnableC0864a(1);

    /* renamed from: h */
    public static final /* synthetic */ RunnableC0864a f1291h = new RunnableC0864a(2);

    /* renamed from: e */
    public final /* synthetic */ int f1292e;

    public /* synthetic */ RunnableC0864a(int i7) {
        this.f1292e = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1292e) {
            case 0:
                Log.m655i("XLog_APP ", "removeOldFile ");
                Objects.requireNonNull(C0866c.m674a());
                File file = new File(C0866c.f1294b);
                long jCurrentTimeMillis = System.currentTimeMillis() - 259200000;
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    C2073a.m2459d("xlog removeOldFile files.length  = " + fileArrListFiles.length);
                    for (File file2 : fileArrListFiles) {
                        C2073a.m2459d("xlog removeOldFile file = " + file2.getName());
                        if (file2.lastModified() < jCurrentTimeMillis && file2.delete()) {
                            Log.m655i("XLog_APP ", "remove " + file2.getName());
                        }
                    }
                    break;
                }
                break;
            case 1:
                int i7 = VideoFragment.f854B;
                break;
            default:
                System.gc();
                break;
        }
    }
}
