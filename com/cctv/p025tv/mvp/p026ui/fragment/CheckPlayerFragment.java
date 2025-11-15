package com.cctv.p025tv.mvp.p026ui.fragment;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.base.BaseFragment;
import com.cctv.p025tv.module.player.CheckDevicePlayer;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.request.PostRequest;
import okhttp3.internal.cache.DiskLruCache;
import p043f.C0988e;
import p054g2.C1052c;
import p132p6.C1749a;
import p190y.AbstractC2085c;

/* loaded from: classes.dex */
public class CheckPlayerFragment extends BaseFragment implements View.OnClickListener {

    /* renamed from: t */
    public static final /* synthetic */ int f757t = 0;

    /* renamed from: h */
    public CheckDevicePlayer f758h;

    /* renamed from: i */
    public Button f759i;

    /* renamed from: j */
    public Button f760j;

    /* renamed from: k */
    public LinearLayout f761k;

    /* renamed from: l */
    public RelativeLayout f762l;

    /* renamed from: m */
    public RelativeLayout f763m;

    /* renamed from: n */
    public TextView f764n;

    /* renamed from: o */
    public TextView f765o;

    /* renamed from: p */
    public Dialog f766p;

    /* renamed from: q */
    public Button f767q;

    /* renamed from: r */
    public Button f768r;

    /* renamed from: s */
    public int f769s;

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_check_player;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
        m486k();
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        CheckDevicePlayer checkDevicePlayer = (CheckDevicePlayer) this.f577f.findViewById(R.id.vp_check);
        this.f758h = checkDevicePlayer;
        checkDevicePlayer.setFragment(this);
        this.f759i = (Button) this.f577f.findViewById(R.id.btn_play_err);
        this.f760j = (Button) this.f577f.findViewById(R.id.btn_play_normal);
        this.f762l = (RelativeLayout) this.f577f.findViewById(R.id.rl_check_result);
        this.f761k = (LinearLayout) this.f577f.findViewById(R.id.ll_check_play_time);
        this.f763m = (RelativeLayout) this.f577f.findViewById(R.id.rl_check_over);
        this.f765o = (TextView) this.f577f.findViewById(R.id.tv_check_tips_time);
        this.f764n = (TextView) this.f577f.findViewById(R.id.tv_check_tips);
        this.f768r = (Button) this.f577f.findViewById(R.id.btn_exit);
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
        this.f759i.setOnClickListener(this);
        this.f760j.setOnClickListener(this);
        this.f768r.setOnClickListener(this);
    }

    /* renamed from: j */
    public final void m485j() {
        Dialog dialog = this.f766p;
        if (dialog != null) {
            dialog.dismiss();
        }
        CheckDevicePlayer checkDevicePlayer = this.f758h;
        if (checkDevicePlayer == null || !checkDevicePlayer.getPlayerView().m615K()) {
            return;
        }
        this.f758h.getPlayerView().start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: k */
    public final void m486k() {
        this.f763m.setVisibility(8);
        this.f769s = 0;
        ((PostRequest) CtvitHttp.post("https://ytpaddr.cctv.cn/gsnw/play/check/video/list").cacheMode(CacheMode.NO_CACHE)).execute(new C1052c(this));
    }

    /* renamed from: l */
    public final void m487l() {
        int iM1010d = this.f758h.getPlayEntity().m1010d();
        int size = this.f758h.getPlayEntity().f1843g.size();
        if (iM1010d == size - 1) {
            this.f763m.setVisibility(0);
            this.f768r.requestFocus();
            return;
        }
        this.f758h.getPlayEntity().m1008b().f1853d = false;
        int i7 = iM1010d + 1;
        if (i7 <= size) {
            this.f758h.getPlayEntity().f1843g.get(i7).f1853d = true;
        }
        CheckDevicePlayer checkDevicePlayer = this.f758h;
        checkDevicePlayer.mo388s(checkDevicePlayer.getPlayEntity().m1011e(false), false, null, false);
    }

    /* renamed from: m */
    public void m488m(boolean z6) {
        this.f762l.setVisibility(z6 ? 0 : 8);
        if (z6) {
            this.f760j.requestFocus();
            m490o();
        }
    }

    /* renamed from: n */
    public void m489n(boolean z6) {
        this.f761k.setVisibility(z6 ? 0 : 8);
    }

    /* renamed from: o */
    public final void m490o() {
        CheckDevicePlayer checkDevicePlayer = this.f758h;
        if (checkDevicePlayer != null) {
            checkDevicePlayer.mo387r();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws NumberFormatException {
        switch (view.getId()) {
            case R.id.btn_exit /* 2131230790 */:
                C0988e.m971O(getActivity().getSupportFragmentManager(), "SYSTEM_SETTING");
                break;
            case R.id.btn_play_err /* 2131230792 */:
                if (this.f762l.getVisibility() == 0) {
                    this.f769s++;
                    m488m(false);
                    m491p(DiskLruCache.VERSION_1, this.f758h.getPlayEntity().m1008b().f1854e);
                    m487l();
                    break;
                }
                break;
            case R.id.btn_play_normal /* 2131230793 */:
                if (this.f762l.getVisibility() == 0) {
                    m488m(false);
                    m491p(this.f758h.getPlayEntity().m1009c(), this.f758h.getPlayEntity().m1008b().f1854e);
                    m487l();
                    break;
                }
                break;
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        super.onHiddenChanged(z6);
        if (!z6) {
            C1749a.m1913b(getActivity(), this.f577f);
            m486k();
        } else {
            m490o();
            m489n(false);
            m488m(false);
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        m490o();
        super.onPause();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0088  */
    /* renamed from: p */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m491p(java.lang.String r8, boolean r9) throws java.lang.NumberFormatException {
        /*
            r7 = this;
            r0 = 0
            boolean r1 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Exception -> Ld
            if (r1 == 0) goto L8
            goto Ld
        L8:
            int r8 = java.lang.Integer.parseInt(r8)     // Catch: java.lang.Exception -> Ld
            goto Le
        Ld:
            r8 = 0
        Le:
            java.lang.String r1 = "PLAY_CHECK_RATE_VIVID"
            java.lang.String r2 = "PLAY_CHECK_RATE"
            if (r9 == 0) goto L16
            r3 = r1
            goto L17
        L16:
            r3 = r2
        L17:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)
            java.lang.Object r3 = p200z2.C2150a.m2590a(r3, r4)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            r4 = 1
            if (r8 == r4) goto L2b
            if (r8 > r3) goto L2b
            return
        L2b:
            n1.a r3 = new n1.a
            r3.<init>()
            android.content.Context r4 = r7.getContext()
            p179w2.C2025a.m2376d()
            java.lang.String r5 = p078j2.C1195j.m1418b()
            java.lang.String r6 = "ETHERNET"
            r6.equals(r5)
            java.lang.String r5 = android.os.Build.BRAND
            java.lang.String r5 = android.os.Build.MODEL
            java.lang.String r5 = android.os.Build.MANUFACTURER
            java.lang.Runtime r5 = java.lang.Runtime.getRuntime()
            r5.availableProcessors()
            java.lang.String r5 = "ro.product.cpu.abi"
            java.lang.String r6 = "错误"
            p043f.C0988e.m998x(r5, r6)
            p078j2.C1194i.m1415c(r4)
            p078j2.C1194i.m1413a()
            p078j2.C1194i.m1416d()
            java.lang.String r4 = android.os.Build.VERSION.RELEASE
            n1.a$a r4 = new n1.a$a
            r4.<init>()
            com.cctv.p025tv.module.function.guid.TempCloudDevice.getGuid()
            if (r9 == 0) goto L6b
            r5 = r1
            goto L6c
        L6b:
            r5 = r2
        L6c:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r8)
            p200z2.C2150a.m2591b(r5, r6)
            if (r9 == 0) goto L88
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)
            java.lang.Object r9 = p200z2.C2150a.m2590a(r2, r9)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r4.f4390b = r8
            r4.f4389a = r9
            goto L9a
        L88:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)
            java.lang.Object r9 = p200z2.C2150a.m2590a(r1, r9)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r4.f4390b = r9
            r4.f4389a = r8
        L9a:
            r3.f4388a = r4
            java.lang.String r8 = "uploadCheckResult = "
            java.lang.StringBuilder r8 = p009b.C0413b.m112a(r8)
            java.lang.String r9 = com.alibaba.fastjson.JSON.toJSONString(r3)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            p186x2.C2073a.m2459d(r8)
            java.lang.String r8 = "https://ytpaddr.cctv.cn/gsnw/play/check/report"
            com.ctvit.network.request.PostRequest r8 = com.ctvit.network.CtvitHttp.post(r8)
            com.ctvit.network.cache.model.CacheMode r9 = com.ctvit.network.cache.model.CacheMode.NO_CACHE
            com.ctvit.network.request.BaseRequest r8 = r8.cacheMode(r9)
            com.ctvit.network.request.PostRequest r8 = (com.ctvit.network.request.PostRequest) r8
            java.lang.String r9 = com.alibaba.fastjson.JSON.toJSONString(r3)
            com.ctvit.network.request.BaseBodyRequest r8 = r8.upJson(r9)
            com.ctvit.network.request.PostRequest r8 = (com.ctvit.network.request.PostRequest) r8
            r8.execute()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cctv.p025tv.mvp.p026ui.fragment.CheckPlayerFragment.m491p(java.lang.String, boolean):void");
    }
}
