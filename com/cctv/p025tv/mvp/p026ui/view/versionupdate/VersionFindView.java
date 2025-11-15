package com.cctv.p025tv.mvp.p026ui.view.versionupdate;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.module.collect.C0580b;
import com.cctv.p025tv.mvp.p026ui.activity.MainActivity;
import com.cctv.p025tv.mvp.p026ui.fragment.VersionUpdateFragment;
import com.cctv.p025tv.utils.PermissionsUtils;
import com.ctvit.appupdate.entity.AppUpdateEntity;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import p009b.C0413b;
import p043f.C0988e;
import p078j2.C1186a;
import p093l1.C1415a;
import p150s1.C1868e;
import p186x2.C2073a;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.RationaleDialogFragmentCompat;

/* loaded from: classes.dex */
public class VersionFindView extends RelativeLayout implements View.OnClickListener {

    /* renamed from: e */
    public Button f919e;

    /* renamed from: f */
    public Button f920f;

    /* renamed from: g */
    public TextView f921g;

    /* renamed from: h */
    public TextView f922h;

    /* renamed from: i */
    public AppUpdateEntity.AndroidBean f923i;

    public VersionFindView(Context context) {
        this(context, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int id = view.getId();
        if (id == R.id.cancelBtn) {
            C0580b.m417c("DOWNLOAD_SKIP", "VersionUpdateFragment");
            if (MyApplication.f563g != null && (getContext() instanceof MainActivity)) {
                C2073a.m2459d("由投屏操作从后台唤起的，并且还有更新");
                ((MainActivity) getContext()).m479v(MyApplication.f563g, false);
                MyApplication.f563g = null;
                return;
            } else if (C1186a.m1385e()) {
                C0988e.m971O(((AppCompatActivity) getContext()).getSupportFragmentManager(), "MAIN_FRAGMENT");
                return;
            } else {
                C0988e.m971O(((AppCompatActivity) getContext()).getSupportFragmentManager(), "WELCOME");
                return;
            }
        }
        if (id != R.id.enterBtn) {
            return;
        }
        if (C1415a.f4150b.equals("huawei")) {
            C1186a.m1392l(getContext());
            return;
        }
        if (PermissionsUtils.isReadWritePermissionsStatus()) {
            MyApplication.f563g = null;
            VersionUpdateFragment versionUpdateFragment = (VersionUpdateFragment) ((AppCompatActivity) getContext()).getSupportFragmentManager().findFragmentByTag("VERSION_UPDATE_FRAGMENT");
            if (versionUpdateFragment != null) {
                C0580b.m417c("DOWNLOAD", "VersionUpdateFragment");
                versionUpdateFragment.m505j(4, this.f923i);
                return;
            }
            return;
        }
        Context context = getContext();
        if (context instanceof Activity) {
            String[] strArr = PermissionsUtils.WRITE;
            Activity activity = (Activity) context;
            if (!EasyPermissions.m1918d(activity, Arrays.asList(strArr))) {
                PermissionsUtils.readWritePermissions(activity);
                return;
            }
            FragmentManager supportFragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
            RationaleDialogFragmentCompat rationaleDialogFragmentCompatM1921e = RationaleDialogFragmentCompat.m1921e(context.getString(R.string.permission_not_remind), context.getString(R.string.permission_not_remind_agree), "", 0, 1, strArr);
            if (supportFragmentManager.isStateSaved()) {
                return;
            }
            rationaleDialogFragmentCompatM1921e.show(supportFragmentManager, "RationaleDialogFragmentCompat");
        }
    }

    public void setVersionEntity(AppUpdateEntity.AndroidBean androidBean) {
        this.f923i = androidBean;
        if (androidBean != null) {
            TextView textView = this.f921g;
            StringBuilder sbM112a = C0413b.m112a("发现新版本");
            sbM112a.append(androidBean.getVersionName());
            sbM112a.append("，是否更新?");
            textView.setText(sbM112a.toString());
            this.f922h.setText(androidBean.getBrief());
        }
    }

    public VersionFindView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VersionFindView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        LayoutInflater.from(getContext()).inflate(R.layout.view_app_upadte_find, this);
        this.f919e = (Button) findViewById(R.id.enterBtn);
        this.f920f = (Button) findViewById(R.id.cancelBtn);
        this.f921g = (TextView) findViewById(R.id.version_name_view);
        this.f922h = (TextView) findViewById(R.id.version_update_text);
        if (C1868e.f5445a) {
            this.f920f.setVisibility(8);
        }
        this.f919e.setOnClickListener(this);
        this.f920f.setOnClickListener(this);
        this.f919e.requestFocus();
    }
}
