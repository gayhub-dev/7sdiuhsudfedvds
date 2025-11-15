package com.cctv.p025tv.module.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import p009b.C0413b;
import p078j2.C1186a;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class ScreenStateBroadcast extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        StringBuilder sbM112a = C0413b.m112a("接收到广播 ScreenStateBroadcast");
        sbM112a.append(intent.getAction());
        C2073a.m2459d(sbM112a.toString());
        if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
            C1186a.m1390j();
        }
    }
}
