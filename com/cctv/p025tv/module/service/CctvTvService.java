package com.cctv.p025tv.module.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.cctv.p025tv.module.broadcast.NetWorkBroadcast;
import com.cctv.p025tv.module.broadcast.ScreenStateBroadcast;
import p078j2.C1186a;
import p078j2.C1191f;
import p078j2.C1196k;
import p118o2.C1581b;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class CctvTvService extends Service {

    /* renamed from: e */
    public NetWorkBroadcast f632e;

    /* renamed from: f */
    public ScreenStateBroadcast f633f;

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        startForeground(1001, C1196k.m1420a());
        C1191f.m1409c(false);
        NetWorkBroadcast netWorkBroadcast = this.f632e;
        if (netWorkBroadcast != null) {
            unregisterReceiver(netWorkBroadcast);
            this.f632e = null;
        }
        ScreenStateBroadcast screenStateBroadcast = this.f633f;
        if (screenStateBroadcast != null) {
            unregisterReceiver(screenStateBroadcast);
            this.f633f = null;
        }
        C2073a.m2459d("注册网络变化广播");
        this.f632e = new NetWorkBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.f632e, intentFilter, "com.cctv.tv.permission.connectivity_main", null);
        C2073a.m2459d("动态注册屏幕亮屏/锁屏广播");
        this.f633f = new ScreenStateBroadcast();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.SCREEN_ON");
        registerReceiver(this.f633f, intentFilter2, "com.cctv.tv.permission.screenOn", null);
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        NetWorkBroadcast netWorkBroadcast = this.f632e;
        if (netWorkBroadcast != null) {
            unregisterReceiver(netWorkBroadcast);
            this.f632e = null;
        }
        ScreenStateBroadcast screenStateBroadcast = this.f633f;
        if (screenStateBroadcast != null) {
            unregisterReceiver(screenStateBroadcast);
            this.f633f = null;
        }
        stopForeground(true);
        C1186a.m1390j();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        C2073a.m2459d("onStartCommand");
        if (C1581b.m1832a() != null) {
            return 1;
        }
        C2073a.m2459d("DlnaClientSendDataListener为null，重新注册");
        C1191f.m1408b(this);
        return 1;
    }
}
