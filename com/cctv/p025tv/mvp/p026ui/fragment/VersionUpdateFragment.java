package com.cctv.p025tv.mvp.p026ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.base.BaseFragment;
import com.cctv.p025tv.mvp.p026ui.view.versionupdate.VersionCheckingView;
import com.cctv.p025tv.mvp.p026ui.view.versionupdate.VersionDownloadingView;
import com.cctv.p025tv.mvp.p026ui.view.versionupdate.VersionFindView;
import com.cctv.p025tv.mvp.p026ui.view.versionupdate.VersionNotFindView;
import com.cctv.p025tv.utils.PermissionsUtils;
import com.ctvit.appupdate.entity.AppUpdateEntity;
import java.util.Arrays;
import p094l2.C1420a;
import p132p6.C1749a;
import p150s1.C1868e;
import p179w2.C2026b;
import p190y.AbstractC2085c;
import p193y2.C2108c;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.RationaleDialogFragmentCompat;

/* loaded from: classes.dex */
public class VersionUpdateFragment extends BaseFragment {

    /* renamed from: l */
    public static boolean f849l;

    /* renamed from: h */
    public TextView f850h;

    /* renamed from: i */
    public RelativeLayout f851i;

    /* renamed from: j */
    public RelativeLayout f852j;

    /* renamed from: k */
    public VersionDownloadingView f853k;

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_version_update;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
        this.f850h.setText(C2026b.m2379b(R.string.update_text));
        this.f852j.setBackgroundResource(R.drawable.left_four);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f850h = (TextView) this.f577f.findViewById(R.id.top_name);
        this.f851i = (RelativeLayout) this.f577f.findViewById(R.id.parent_layout);
        this.f852j = (RelativeLayout) this.f577f.findViewById(R.id.rl_left_bg);
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
    }

    /* renamed from: j */
    public void m505j(int i7, AppUpdateEntity.AndroidBean androidBean) {
        this.f851i.removeAllViews();
        f849l = false;
        View versionCheckingView = null;
        versionCheckingView = null;
        versionCheckingView = null;
        versionCheckingView = null;
        if (i7 == 1) {
            versionCheckingView = new VersionCheckingView(getContext());
        } else if (i7 == 2) {
            f849l = true;
            VersionFindView versionFindView = new VersionFindView(getContext());
            versionFindView.setVersionEntity(androidBean);
            versionCheckingView = versionFindView;
        } else if (i7 == 3) {
            VersionNotFindView versionNotFindView = new VersionNotFindView(getContext());
            versionNotFindView.setVersionEntity(androidBean);
            versionCheckingView = versionNotFindView;
        } else if (i7 == 4) {
            if (PermissionsUtils.isReadWritePermissionsStatus()) {
                MyApplication.f563g = null;
                VersionDownloadingView versionDownloadingView = new VersionDownloadingView(getContext());
                this.f853k = versionDownloadingView;
                versionDownloadingView.setVersionEntity(androidBean);
                versionCheckingView = versionDownloadingView;
            } else {
                Context context = getContext();
                if (context instanceof Activity) {
                    String[] strArr = PermissionsUtils.WRITE;
                    Activity activity = (Activity) context;
                    if (EasyPermissions.m1918d(activity, Arrays.asList(strArr))) {
                        FragmentManager supportFragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                        RationaleDialogFragmentCompat rationaleDialogFragmentCompatM1921e = RationaleDialogFragmentCompat.m1921e(context.getString(R.string.permission_not_remind), context.getString(R.string.permission_not_remind_agree), "", 0, 1, strArr);
                        if (!supportFragmentManager.isStateSaved()) {
                            rationaleDialogFragmentCompatM1921e.show(supportFragmentManager, "RationaleDialogFragmentCompat");
                        }
                        C1868e.f5447c = true;
                        C1868e.f5445a = false;
                    } else {
                        PermissionsUtils.readWritePermissions(activity);
                    }
                }
            }
        }
        if (versionCheckingView != null) {
            this.f851i.addView(versionCheckingView);
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        super.onHiddenChanged(z6);
        if (!z6) {
            C1749a.m1913b(getActivity(), this.f577f);
            return;
        }
        f849l = false;
        C1868e.f5446b = false;
        VersionDownloadingView versionDownloadingView = this.f853k;
        if (versionDownloadingView != null) {
            C2108c c2108c = versionDownloadingView.f916j;
            if (c2108c != null) {
                c2108c.m2551a();
            }
            C1420a.m1604a();
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        VersionDownloadingView versionDownloadingView = this.f853k;
        if (versionDownloadingView != null) {
            C2108c c2108c = versionDownloadingView.f916j;
            if (c2108c != null) {
                c2108c.m2551a();
            }
            C1420a.m1604a();
        }
    }
}
