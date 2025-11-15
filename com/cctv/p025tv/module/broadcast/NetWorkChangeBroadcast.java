package com.cctv.p025tv.module.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.cctv.p025tv.mvp.p026ui.fragment.MainFragment;
import com.tencent.mars.xlog.Log;
import java.util.Objects;
import p009b.C0413b;
import p157t1.InterfaceC1897a;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class NetWorkChangeBroadcast extends BroadcastReceiver {

    /* renamed from: a */
    public InterfaceC1897a f582a;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            MainFragment.C0613a c0613a = (MainFragment.C0613a) this.f582a;
            Objects.requireNonNull(c0613a);
            C2073a.m2459d("收到广播");
            MainFragment.this.m499p();
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("NetWorkChangeBroadcast onReceive e = ");
            sbM112a.append(e7.getMessage());
            Log.m651e("XLog_APP ", sbM112a.toString());
        }
    }
}
