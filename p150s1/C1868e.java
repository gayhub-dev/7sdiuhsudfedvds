package p150s1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.mvp.p026ui.activity.MainActivity;
import com.cctv.p025tv.mvp.p026ui.fragment.VersionUpdateFragment;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import java.io.File;
import java.util.Objects;
import p020c2.C0510e;
import p043f.C0988e;
import p078j2.C1186a;
import p094l2.C1420a;
import p102m2.C1459a;
import p102m2.C1460b;
import p186x2.C2073a;

/* compiled from: VersionUpdate.java */
/* renamed from: s1.e */
/* loaded from: classes.dex */
public class C1868e {

    /* renamed from: a */
    public static boolean f5445a;

    /* renamed from: b */
    public static boolean f5446b;

    /* renamed from: c */
    public static boolean f5447c;

    /* renamed from: d */
    public static a f5448d;

    /* compiled from: VersionUpdate.java */
    /* renamed from: s1.e$a */
    public interface a {
    }

    /* renamed from: a */
    public static void m2138a(FragmentManager fragmentManager, boolean z6) {
        if (!z6) {
            C0988e.m971O(fragmentManager, "VERSION_UPDATE_FRAGMENT");
            VersionUpdateFragment versionUpdateFragment = (VersionUpdateFragment) fragmentManager.findFragmentByTag("VERSION_UPDATE_FRAGMENT");
            if (versionUpdateFragment != null) {
                versionUpdateFragment.m505j(1, null);
            }
        }
        C1460b c1460b = new C1460b();
        c1460b.f4227a = new C1867d(fragmentManager, z6, 0);
        c1460b.f4228b = new C1867d(z6, fragmentManager);
        c1460b.f4229c = new C1867d(fragmentManager, z6, 2);
        CtvitHttp.get(C1420a.m1606c().f4159b).cacheMode(CacheMode.NO_CACHE).execute(new C1459a(c1460b));
    }

    /* renamed from: b */
    public static void m2139b(boolean z6, boolean z7) {
        a aVar = f5448d;
        if (aVar != null) {
            MainActivity mainActivity = ((C0510e) aVar).f384e;
            int i7 = MainActivity.f663D;
            Objects.requireNonNull(mainActivity);
            C2073a.m2459d("版本更新接口请求完成：isUpdate=" + z6 + " isAutoUpdate=" + z7);
            if (z6 || !z7) {
                return;
            }
            if (C1186a.m1391k(mainActivity) && MyApplication.f568l) {
                mainActivity.m475r(true);
            } else {
                C2073a.m2456a("showVideo  1");
                mainActivity.m476s();
            }
        }
    }

    /* renamed from: c */
    public static void m2140c(Context context, String str) {
        File file = new File(str);
        if (!file.isFile()) {
            C2073a.m2456a("安装包不存在");
            return;
        }
        int i7 = Build.VERSION.SDK_INT;
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        if (i7 >= 24) {
            intent.addFlags(1);
            intent.setDataAndType(FileProvider.getUriForFile(context, "com.cctv.tv.fileProvider", file), "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        try {
            context.startActivity(intent);
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }
}
