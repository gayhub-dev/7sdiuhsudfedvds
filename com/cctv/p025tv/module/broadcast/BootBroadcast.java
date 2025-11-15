package com.cctv.p025tv.module.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import p009b.C0413b;
import p101m1.RunnableC1457a;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class BootBroadcast extends BroadcastReceiver {

    /* renamed from: a */
    public static final /* synthetic */ int f580a = 0;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        StringBuilder sbM112a = C0413b.m112a("接收到开机广播");
        sbM112a.append(intent.getAction());
        C2073a.m2459d(sbM112a.toString());
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) || "android.intent.action.MEDIA_MOUNTED".equals(intent.getAction())) {
            new Thread(new RunnableC1457a(context)).start();
        }
    }
}
