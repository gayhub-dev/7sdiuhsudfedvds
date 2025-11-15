package com.ctvit.dlna.activity;

import android.R;
import android.app.Activity;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ctvit.dlna.R$id;
import com.ctvit.dlna.R$layout;
import com.ctvit.dlna.R$string;
import com.ctvit.dlna.discreteness.DlnaNetworkReceiver;
import com.ctvit.dlna.discreteness.DlnaWebView;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import p009b.C0413b;
import p118o2.C1581b;
import p122o6.AbstractC1595d;
import p136q2.InterfaceC1762b;
import pub.devrel.easypermissions.C1753b;
import pub.devrel.easypermissions.EasyPermissions;

/* loaded from: classes.dex */
public class DlnaSearchDeviceActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    /* renamed from: e */
    public DlnaNetworkReceiver f945e = new DlnaNetworkReceiver();

    /* renamed from: f */
    public DlnaWebView f946f;

    /* renamed from: g */
    public LinearLayout f947g;

    /* renamed from: h */
    public LinearLayout f948h;

    /* renamed from: i */
    public TextView f949i;

    /* renamed from: j */
    public TextView f950j;

    /* renamed from: k */
    public ImageView f951k;

    /* renamed from: l */
    public ProgressBar f952l;

    /* renamed from: com.ctvit.dlna.activity.DlnaSearchDeviceActivity$a */
    public class C0623a implements InterfaceC1762b {

        /* renamed from: a */
        public final /* synthetic */ DlnaSearchDeviceActivity f953a;
    }

    /* renamed from: c */
    public static void m524c(DlnaSearchDeviceActivity dlnaSearchDeviceActivity, int i7) {
        if (i7 == 1) {
            dlnaSearchDeviceActivity.m525d();
            dlnaSearchDeviceActivity.f947g.setVisibility(8);
            dlnaSearchDeviceActivity.f948h.setVisibility(0);
            dlnaSearchDeviceActivity.f951k.setVisibility(0);
            C1581b.m1834c();
            DlnaWebView dlnaWebView = dlnaSearchDeviceActivity.f946f;
            Objects.requireNonNull(C1581b.m1836e());
            dlnaWebView.loadUrl(null);
            return;
        }
        if (i7 != 2) {
            dlnaSearchDeviceActivity.f949i.setText(R$string.dlan_network_no2);
            dlnaSearchDeviceActivity.f950j.setText(R$string.dlan_network_no);
            dlnaSearchDeviceActivity.f947g.setVisibility(0);
            dlnaSearchDeviceActivity.f948h.setVisibility(8);
            dlnaSearchDeviceActivity.f952l.setVisibility(8);
            dlnaSearchDeviceActivity.f951k.setVisibility(8);
            return;
        }
        dlnaSearchDeviceActivity.f949i.setText(R$string.dlan_network_not_is_wifi2);
        dlnaSearchDeviceActivity.f950j.setText(R$string.dlan_network_not_is_wifi);
        dlnaSearchDeviceActivity.f947g.setVisibility(0);
        dlnaSearchDeviceActivity.f948h.setVisibility(8);
        dlnaSearchDeviceActivity.f952l.setVisibility(8);
        dlnaSearchDeviceActivity.f951k.setVisibility(8);
        DlnaWebView dlnaWebView2 = dlnaSearchDeviceActivity.f946f;
        Objects.requireNonNull(C1581b.m1836e());
        dlnaWebView2.loadUrl(null);
    }

    @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
    /* renamed from: a */
    public void mo463a(int i7, @NonNull List<String> list) {
    }

    @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
    /* renamed from: b */
    public void mo464b(int i7, @NonNull List<String> list) {
        if (i7 != 100) {
            return;
        }
        m525d();
    }

    /* renamed from: d */
    public final void m525d() {
        WifiInfo connectionInfo;
        NetworkInfo activeNetworkInfo;
        String string = getString(R$string.dlan_network_is_wifi);
        TextView textView = this.f950j;
        StringBuilder sbM112a = C0413b.m112a(string);
        String extraInfo = (Build.VERSION.SDK_INT <= 26 || (activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? "" : activeNetworkInfo.getExtraInfo();
        if (TextUtils.isEmpty(extraInfo) && (connectionInfo = ((WifiManager) getSystemService("wifi")).getConnectionInfo()) != null) {
            extraInfo = connectionInfo.getSSID();
        }
        sbM112a.append(extraInfo != null ? extraInfo.replaceAll("\"", "") : "");
        textView.setText(sbM112a.toString());
    }

    public void onClickBack(View view) {
        finish();
    }

    public void onClickSearchDevice(View view) {
        C1581b.m1834c();
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.onCreate(bundle);
        supportRequestWindowFeature(1);
        setContentView(R$layout.activity_dlan_search_device);
        this.f946f = (DlnaWebView) findViewById(R$id.dlanWebView);
        this.f947g = (LinearLayout) findViewById(R$id.dlanNoWifiLayout);
        this.f948h = (LinearLayout) findViewById(R$id.dlanDeviceListLayout);
        this.f949i = (TextView) findViewById(R$id.networkStatusTips);
        this.f950j = (TextView) findViewById(R$id.networkStatusTips2);
        this.f951k = (ImageView) findViewById(R$id.dlanSearchDevice);
        this.f952l = (ProgressBar) findViewById(R$id.dlanSearchDeviceLoading);
        C1581b.m1834c();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.f945e, intentFilter, "com.ctvit.dlna.permission.connectivity", null);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.f945e, intentFilter2, "com.ctvit.dlna.permission.connectivity", null);
        SharedPreferences sharedPreferences = getSharedPreferences(DlnaSearchDeviceActivity.class.getName(), 0);
        if (sharedPreferences.getBoolean("requestPermissions", false)) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean("requestPermissions", true);
        editorEdit.commit();
        String[] strArr = {"android.permission.ACCESS_FINE_LOCATION"};
        AbstractC1595d<? extends Activity> abstractC1595dM1863c = AbstractC1595d.m1863c(this);
        String string = abstractC1595dM1863c.mo1860b().getString(R$string.request_permissions_wifi_name);
        String string2 = abstractC1595dM1863c.mo1860b().getString(R$string.request_permissions_ok);
        String string3 = abstractC1595dM1863c.mo1860b().getString(R$string.request_permissions_cancel);
        if (string == null) {
            string = abstractC1595dM1863c.mo1860b().getString(pub.devrel.easypermissions.R$string.rationale_ask);
        }
        if (string2 == null) {
            string2 = abstractC1595dM1863c.mo1860b().getString(R.string.ok);
        }
        EasyPermissions.m1917c(new C1753b(abstractC1595dM1863c, strArr, 100, string, string2, string3 == null ? abstractC1595dM1863c.mo1860b().getString(R.string.cancel) : string3, -1, null));
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.f946f.destroy();
        DlnaNetworkReceiver dlnaNetworkReceiver = this.f945e;
        if (dlnaNetworkReceiver != null) {
            unregisterReceiver(dlnaNetworkReceiver);
        }
        super.onDestroy();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i7, @NonNull String[] strArr, @NonNull int[] iArr) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.onRequestPermissionsResult(i7, strArr, iArr);
        EasyPermissions.m1916b(i7, strArr, iArr, this);
    }
}
