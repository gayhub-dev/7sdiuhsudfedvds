package com.cctv.p025tv.module.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class HomeBroadcast extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS") && "homekey".equals(intent.getStringExtra("reason"))) {
            C2073a.m2459d("HOME 键（广播）");
            ((Activity) context).moveTaskToBack(true);
        }
    }
}
