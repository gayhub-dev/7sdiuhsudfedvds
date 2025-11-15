package com.cctv.p025tv.module.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.tencent.mars.xlog.Log;
import p078j2.C1186a;
import p078j2.C1191f;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class NetWorkBroadcast extends BroadcastReceiver {

    /* renamed from: a */
    public NetworkInfo f581a;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getState() != NetworkInfo.State.CONNECTED) {
            if (activeNetworkInfo == null) {
                this.f581a = null;
                C2073a.m2459d("网络变化广播 - WIFI断开");
                Log.m655i("XLog_APP ", "NetWorkBroadcast 网络变化广播 - WIFI断开");
                return;
            }
            return;
        }
        NetworkInfo networkInfo = this.f581a;
        if (networkInfo != null && networkInfo.getType() == activeNetworkInfo.getType()) {
            String extraInfo = this.f581a.getExtraInfo();
            String extraInfo2 = activeNetworkInfo.getExtraInfo();
            if (extraInfo == null ? extraInfo2 == null : extraInfo.equals(extraInfo2)) {
                return;
            }
        }
        this.f581a = activeNetworkInfo;
        C2073a.m2459d("网络变化广播 - WIFI连接");
        Log.m655i("XLog_APP ", "NetWorkBroadcast 网络变化广播 - WIFI连接");
        C1191f.m1409c(false);
        C1186a.m1393m();
    }
}
