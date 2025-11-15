package com.cctv.cctvplayer.player;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import com.easefun.povplayer.core.video.PolyvVideoView;
import p087k3.C1234c;

/* loaded from: classes.dex */
public class DolbyHeadsetPlugReceiver extends BroadcastReceiver {

    /* renamed from: a */
    public Context f520a;

    /* renamed from: b */
    public PolyvVideoView f521b;

    /* renamed from: c */
    public boolean f522c;

    public DolbyHeadsetPlugReceiver(Context context, PolyvVideoView polyvVideoView) {
        this.f520a = context;
        this.f521b = polyvVideoView;
    }

    /* renamed from: a */
    public final void m393a(boolean z6) {
        this.f521b.f1105e.mo577d(z6);
        this.f521b.getSubVideoView().f1105e.mo577d(z6);
    }

    @Override // android.content.BroadcastReceiver
    @RequiresApi(api = 14)
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!"android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
            if ("android.intent.action.HEADSET_PLUG".equals(action) && intent.hasExtra("state")) {
                if (intent.getIntExtra("state", 0) == 0) {
                    if (C1234c.m1459b(this.f520a)) {
                        return;
                    }
                    TextUtils.isEmpty("耳机未连接");
                    m393a(false);
                    return;
                }
                if (intent.getIntExtra("state", 0) == 1) {
                    TextUtils.isEmpty("耳机连接");
                    m393a(true);
                    return;
                }
                return;
            }
            return;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return;
        }
        int profileConnectionState = defaultAdapter.getProfileConnectionState(2);
        int profileConnectionState2 = defaultAdapter.getProfileConnectionState(1);
        int profileConnectionState3 = defaultAdapter.getProfileConnectionState(3);
        if (2 == profileConnectionState || 2 == profileConnectionState2 || 2 == profileConnectionState3) {
            TextUtils.isEmpty("蓝牙耳机连接");
            m393a(true);
            return;
        }
        if (1 == profileConnectionState || 1 == profileConnectionState2 || 1 == profileConnectionState3 || 3 == profileConnectionState || 3 == profileConnectionState2 || 3 == profileConnectionState3) {
            return;
        }
        if ((profileConnectionState == 0 || profileConnectionState2 == 0 || profileConnectionState3 == 0) && !C1234c.m1459b(this.f520a)) {
            TextUtils.isEmpty("蓝牙耳机未连接");
            m393a(false);
        }
    }
}
