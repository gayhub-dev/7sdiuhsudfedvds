package p150s1;

import android.support.v4.app.FragmentManager;
import com.alibaba.fastjson.JSON;
import com.cctv.p025tv.mvp.p026ui.fragment.MainFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.VersionUpdateFragment;
import com.ctvit.appupdate.entity.AppUpdateEntity;
import com.tencent.mars.xlog.Log;
import java.util.Objects;
import p009b.C0413b;
import p043f.C0988e;
import p078j2.C1186a;
import p093l1.C1415a;
import p094l2.C1420a;
import p110n2.InterfaceC1518b;
import p110n2.InterfaceC1519c;
import p110n2.InterfaceC1520d;

/* renamed from: s1.d */
/* loaded from: classes.dex */
public final /* synthetic */ class C1867d implements InterfaceC1520d, InterfaceC1519c, InterfaceC1518b {

    /* renamed from: a */
    public final /* synthetic */ FragmentManager f5443a;

    /* renamed from: b */
    public final /* synthetic */ boolean f5444b;

    public /* synthetic */ C1867d(FragmentManager fragmentManager, boolean z6, int i7) {
        this.f5443a = fragmentManager;
        this.f5444b = z6;
    }

    public /* synthetic */ C1867d(boolean z6, FragmentManager fragmentManager) {
        this.f5444b = z6;
        this.f5443a = fragmentManager;
    }

    /* renamed from: a */
    public void m2135a(InterfaceC1518b.a aVar) {
        FragmentManager fragmentManager = this.f5443a;
        boolean z6 = this.f5444b;
        Log.m655i("XLog_SERVICE_INTERFACES ", "setOnNotFindListener errorStatus = " + aVar);
        C1868e.f5445a = false;
        C1868e.f5446b = false;
        MainFragment mainFragment = (MainFragment) fragmentManager.findFragmentByTag("MAIN_FRAGMENT");
        if (mainFragment != null) {
            mainFragment.m501r(null);
        }
        C1868e.m2139b(false, z6);
    }

    /* renamed from: b */
    public void m2136b(AppUpdateEntity.AndroidBean androidBean) {
        VersionUpdateFragment versionUpdateFragment;
        boolean z6 = this.f5444b;
        FragmentManager fragmentManager = this.f5443a;
        StringBuilder sbM112a = C0413b.m112a("setOnNotFindListener bean = ");
        sbM112a.append(JSON.toJSONString(androidBean));
        Log.m655i("XLog_SERVICE_INTERFACES ", sbM112a.toString());
        C1868e.f5445a = false;
        C1868e.f5446b = false;
        if (!z6 && (versionUpdateFragment = (VersionUpdateFragment) fragmentManager.findFragmentByTag("VERSION_UPDATE_FRAGMENT")) != null) {
            versionUpdateFragment.m505j(3, androidBean);
        }
        MainFragment mainFragment = (MainFragment) fragmentManager.findFragmentByTag("MAIN_FRAGMENT");
        if (mainFragment != null) {
            mainFragment.m501r(null);
        }
        C1868e.m2139b(false, z6);
    }

    /* renamed from: c */
    public void m2137c(boolean z6, AppUpdateEntity.AndroidBean androidBean) {
        FragmentManager fragmentManager = this.f5443a;
        boolean z7 = this.f5444b;
        StringBuilder sbM112a = C0413b.m112a("setOnUpdateListener bean = ");
        sbM112a.append(JSON.toJSONString(androidBean));
        Log.m655i("XLog_SERVICE_INTERFACES ", sbM112a.toString());
        Objects.requireNonNull(C1420a.m1606c());
        C1868e.f5445a = true;
        C1868e.f5446b = z7;
        if (z7) {
            C0988e.m971O(fragmentManager, "VERSION_UPDATE_FRAGMENT");
        }
        VersionUpdateFragment versionUpdateFragment = (VersionUpdateFragment) fragmentManager.findFragmentByTag("VERSION_UPDATE_FRAGMENT");
        if (versionUpdateFragment != null && !versionUpdateFragment.isHidden()) {
            if (C1415a.f4150b.equals("huawei")) {
                C1186a.m1392l(versionUpdateFragment.getContext());
            } else {
                versionUpdateFragment.m505j(4, androidBean);
            }
        }
        MainFragment mainFragment = (MainFragment) fragmentManager.findFragmentByTag("MAIN_FRAGMENT");
        if (mainFragment != null) {
            mainFragment.m501r(androidBean);
        }
        C1868e.m2139b(true, z7);
    }
}
