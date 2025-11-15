package com.ctvit.dlna.discreteness;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.ctvit.dlna.activity.DlnaSearchDeviceActivity;
import p136q2.InterfaceC1762b;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class DlnaNetworkReceiver extends BroadcastReceiver {

    /* renamed from: a */
    public InterfaceC1762b f954a;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        char c7 = (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) ? activeNetworkInfo.getType() == 1 ? (char) 1 : (char) 2 : (char) 65535;
        if (c7 == 1) {
            InterfaceC1762b interfaceC1762b = this.f954a;
            if (interfaceC1762b != null) {
                DlnaSearchDeviceActivity.m524c(((DlnaSearchDeviceActivity.C0623a) interfaceC1762b).f953a, 1);
            }
            C2073a.m2459d("Wifi");
            return;
        }
        if (c7 == 2) {
            InterfaceC1762b interfaceC1762b2 = this.f954a;
            if (interfaceC1762b2 != null) {
                DlnaSearchDeviceActivity.m524c(((DlnaSearchDeviceActivity.C0623a) interfaceC1762b2).f953a, 2);
            }
            C2073a.m2459d("其它网络");
            return;
        }
        InterfaceC1762b interfaceC1762b3 = this.f954a;
        if (interfaceC1762b3 != null) {
            DlnaSearchDeviceActivity.m524c(((DlnaSearchDeviceActivity.C0623a) interfaceC1762b3).f953a, -1);
        }
        C2073a.m2459d("无网络");
    }
}
