package com.cctv.p025tv.mvp.p026ui.fragment;

import android.arch.lifecycle.C0063n;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.base.BaseFragment;
import com.cctv.p025tv.entity.MainEntity;
import com.cctv.p025tv.module.broadcast.NetWorkChangeBroadcast;
import com.cctv.p025tv.module.collect.C0580b;
import com.ctvit.appupdate.entity.AppUpdateEntity;
import com.tencent.mars.xlog.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import p003a2.AbstractC0007b;
import p003a2.InterfaceC0006a;
import p004a3.C0008a;
import p012b2.C0438a;
import p043f.C0988e;
import p078j2.C1189d;
import p078j2.C1190e;
import p078j2.C1194i;
import p086k2.C1231b;
import p093l1.C1415a;
import p101m1.C1458b;
import p108n0.C1512c;
import p118o2.C1581b;
import p132p6.C1749a;
import p141r.C1813h;
import p141r.ComponentCallbacks2C1808c;
import p157t1.InterfaceC1897a;
import p179w2.C2025a;
import p179w2.C2026b;
import p186x2.C2073a;
import p190y.AbstractC2085c;
import p200z2.C2150a;

/* loaded from: classes.dex */
public class MainFragment extends BaseFragment<InterfaceC0006a, AbstractC0007b> implements InterfaceC0006a, View.OnClickListener, View.OnFocusChangeListener {

    /* renamed from: J */
    public static final /* synthetic */ int f783J = 0;

    /* renamed from: A */
    public RelativeLayout f784A;

    /* renamed from: B */
    public LinearLayout f785B;

    /* renamed from: C */
    public NetWorkChangeBroadcast f786C;

    /* renamed from: D */
    public Timer f787D;

    /* renamed from: E */
    public TimerTask f788E;

    /* renamed from: G */
    public boolean f790G;

    /* renamed from: H */
    public boolean f791H;

    /* renamed from: h */
    public ImageView f793h;

    /* renamed from: i */
    public ImageView f794i;

    /* renamed from: j */
    public ImageView f795j;

    /* renamed from: k */
    public ImageView f796k;

    /* renamed from: l */
    public TextView f797l;

    /* renamed from: m */
    public TextView f798m;

    /* renamed from: n */
    public TextView f799n;

    /* renamed from: o */
    public TextView f800o;

    /* renamed from: p */
    public TextView f801p;

    /* renamed from: q */
    public TextView f802q;

    /* renamed from: r */
    public TextView f803r;

    /* renamed from: s */
    public TextView f804s;

    /* renamed from: t */
    public TextView f805t;

    /* renamed from: u */
    public TextView f806u;

    /* renamed from: v */
    public RelativeLayout f807v;

    /* renamed from: w */
    public RelativeLayout f808w;

    /* renamed from: x */
    public RelativeLayout f809x;

    /* renamed from: y */
    public RelativeLayout f810y;

    /* renamed from: z */
    public RelativeLayout f811z;

    /* renamed from: F */
    public int f789F = 0;

    /* renamed from: I */
    public InterfaceC1897a f792I = new C0613a();

    /* renamed from: com.cctv.tv.mvp.ui.fragment.MainFragment$a */
    public class C0613a implements InterfaceC1897a {
        public C0613a() {
        }
    }

    /* renamed from: com.cctv.tv.mvp.ui.fragment.MainFragment$b */
    public class C0614b extends TimerTask {

        /* renamed from: com.cctv.tv.mvp.ui.fragment.MainFragment$b$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MainFragment mainFragment = MainFragment.this;
                int i7 = MainFragment.f783J;
                Objects.requireNonNull(mainFragment);
                Date date = new Date();
                mainFragment.f803r.setText(new SimpleDateFormat("HH:mm", Locale.CHINA).format(date));
                mainFragment.f805t.setText(new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA).format(date));
                mainFragment.f804s.setText(new SimpleDateFormat("EEEE", Locale.CHINA).format(date));
            }
        }

        public C0614b() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (MainFragment.this.getContext() instanceof AppCompatActivity) {
                ((AppCompatActivity) MainFragment.this.getContext()).runOnUiThread(new a());
            }
        }
    }

    @Override // p003a2.InterfaceC0006a
    @RequiresApi(api = 16)
    /* renamed from: a */
    public void mo5a(MainEntity.DataBean dataBean) throws NumberFormatException {
        String strM8a;
        boolean zM11d;
        String image = dataBean.getImage();
        C2073a.m2459d("首页远程背景图：" + image);
        if (!TextUtils.isEmpty(image)) {
            C1512c c1512cM1679K = new C1512c().m1679K(R.drawable.bg_empty);
            C1813h<Drawable> c1813hM2042l = ComponentCallbacks2C1808c.m2023e(getContext()).m2042l(image);
            c1813hM2042l.m2035b(c1512cM1679K);
            c1813hM2042l.m2038j(this.f793h);
        }
        String sign = dataBean.getSign();
        String text = dataBean.getText();
        if (TextUtils.isEmpty(sign) || TextUtils.isEmpty(text)) {
            strM8a = null;
        } else {
            strM8a = C0008a.m8a(text, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/ZeLwTPPLSU7QGwv6tVgdawz9n7S2CxboIEVQlQ1USAHvBRlWBsU2l7+HuUVMJ5blqGc/5y3AoaUzPGoXPfIm0GnBdFL+iLeRDwOS1KgcQ0fIquvr/2Xzj3fVA1o4Y81wJK5BP8bDTBFYMVOlOoCc1ZzWwdZBYpb4FNxt//5dAwIDAQAB");
            try {
                zM11d = C0008a.m11d(strM8a, C0008a.m10c("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/ZeLwTPPLSU7QGwv6tVgdawz9n7S2CxboIEVQlQ1USAHvBRlWBsU2l7+HuUVMJ5blqGc/5y3AoaUzPGoXPfIm0GnBdFL+iLeRDwOS1KgcQ0fIquvr/2Xzj3fVA1o4Y81wJK5BP8bDTBFYMVOlOoCc1ZzWwdZBYpb4FNxt//5dAwIDAQAB"), sign);
            } catch (Exception e7) {
                C2073a.m2458c(e7);
                zM11d = false;
            }
            if (zM11d) {
                C1458b.m1642a("首页文本可用:", strM8a);
            } else {
                C2073a.m2459d("首页文本不可用，验证签名失败");
                strM8a = null;
            }
        }
        if (TextUtils.isEmpty(strM8a)) {
            this.f802q.setText(R.string.main_app_explain);
        } else if (strM8a == null || !strM8a.contains("央视少儿")) {
            this.f802q.setText(strM8a);
        } else {
            this.f802q.setText(R.string.main_app_explain);
        }
        this.f785B.setVisibility(dataBean.isVisible() ? 0 : 8);
        C2150a.m2591b("IS_MOURN_COLOR", Boolean.valueOf(dataBean.isGray()));
        if (getActivity() != null && getActivity().getWindow() != null) {
            if (C1749a.m1912a()) {
                getActivity().getWindow().getDecorView().setBackground(C1231b.f2761c.getResources().getDrawable(R.drawable.bg_grey, null));
            } else {
                getActivity().getWindow().getDecorView().setBackground(C1231b.f2761c.getResources().getDrawable(R.drawable.bg_empty, null));
            }
        }
        C1749a.m1913b(getActivity(), this.f577f);
        MainEntity.DataBean.DeviceCheckBean deviceCheck = dataBean.getDeviceCheck();
        if (deviceCheck != null) {
            double d7 = 0.0d;
            try {
                String str = Build.VERSION.RELEASE;
                if (!TextUtils.isEmpty(str)) {
                    String strReplaceAll = str.replaceAll("[^\\d.]", "");
                    C2073a.m2459d("deviceCheck currentAN = " + str);
                    C2073a.m2459d("deviceCheck cleanedVersion = " + strReplaceAll);
                    if (TextUtils.isEmpty(strReplaceAll) || !strReplaceAll.contains(".")) {
                        d7 = Double.parseDouble(strReplaceAll);
                    } else {
                        String[] strArrSplit = strReplaceAll.split("\\.");
                        if (strArrSplit.length >= 2) {
                            d7 = Double.parseDouble(strArrSplit[0] + "." + strArrSplit[1]);
                        } else if (strArrSplit.length == 1) {
                            d7 = Double.parseDouble(strArrSplit[0] + ".0");
                        }
                    }
                }
                C2073a.m2459d("deviceCheck android_version = " + d7);
                if (C1194i.m1414b(getContext()) < deviceCheck.getFreeRAM().intValue() || C1194i.m1413a() < deviceCheck.getFreeMem().intValue() || Runtime.getRuntime().availableProcessors() < deviceCheck.getCPU().intValue() || d7 < deviceCheck.getVersionAN().doubleValue()) {
                    C2150a.m2591b("DEVICE_CHECK_RESULT", Boolean.FALSE);
                    C2073a.m2459d("deviceCheck  不可以");
                } else {
                    C2150a.m2591b("DEVICE_CHECK_RESULT", Boolean.TRUE);
                    C2073a.m2459d("deviceCheck  可以");
                }
                Log.m655i("XLog_APP ", "FreeRAM = " + C1194i.m1414b(getContext()));
                Log.m655i("XLog_APP ", "FreeMem = " + C1194i.m1413a());
                Log.m655i("XLog_APP ", "CPU = " + Runtime.getRuntime().availableProcessors());
                Log.m655i("XLog_APP ", "VersionAN = " + d7);
            } catch (Exception e8) {
                C2073a.m2456a("deviceCheck Exception = " + e8);
            }
        }
    }

    @Override // p003a2.InterfaceC0006a
    /* renamed from: d */
    public void mo6d() {
        this.f791H = true;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return new C0438a();
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_main;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() throws Resources.NotFoundException {
        m499p();
        if (C1189d.m1405g(MyApplication.f561e)) {
            this.f800o.setText(m496m());
        } else {
            this.f800o.setText(C2026b.m2379b(R.string.version_cur) + m496m());
        }
        if ("央视频电视投屏助手".equals(C1581b.m1837f())) {
            this.f801p.setText("央视频\r\n电视投屏助手");
        } else {
            this.f801p.setText(C1581b.m1837f());
        }
        ((AbstractC0007b) this.f576e).mo7e();
        C1190e.m1406a(MyApplication.f561e, C1581b.m1837f());
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f793h = (ImageView) this.f577f.findViewById(R.id.main_bg);
        this.f797l = (TextView) this.f577f.findViewById(R.id.tv_wifi_name);
        this.f798m = (TextView) this.f577f.findViewById(R.id.tv_wifi);
        this.f800o = (TextView) this.f577f.findViewById(R.id.tv_update);
        this.f801p = (TextView) this.f577f.findViewById(R.id.dlna_name);
        this.f799n = (TextView) this.f577f.findViewById(R.id.device_name_text);
        this.f807v = (RelativeLayout) this.f577f.findViewById(R.id.device_name_view);
        this.f808w = (RelativeLayout) this.f577f.findViewById(R.id.device_name_layout);
        this.f809x = (RelativeLayout) this.f577f.findViewById(R.id.setting_view);
        this.f810y = (RelativeLayout) this.f577f.findViewById(R.id.setting_layout);
        this.f811z = (RelativeLayout) this.f577f.findViewById(R.id.internet_setting_view);
        this.f784A = (RelativeLayout) this.f577f.findViewById(R.id.internet_setting_layout);
        this.f802q = (TextView) this.f577f.findViewById(R.id.app_content_text);
        this.f785B = (LinearLayout) this.f577f.findViewById(R.id.view_main_qr_code);
        this.f803r = (TextView) this.f577f.findViewById(R.id.tv_time);
        this.f804s = (TextView) this.f577f.findViewById(R.id.tv_week);
        this.f805t = (TextView) this.f577f.findViewById(R.id.tv_date);
        this.f806u = (TextView) this.f577f.findViewById(R.id.setting_text);
        this.f794i = (ImageView) this.f577f.findViewById(R.id.iv_internet_icon);
        this.f795j = (ImageView) this.f577f.findViewById(R.id.iv_device_name_icon);
        this.f796k = (ImageView) this.f577f.findViewById(R.id.iv_setting_icon);
        ((TextView) this.f577f.findViewById(R.id.tv_record_num)).setText(C1189d.m1399a());
        this.f809x.getBackground().setAlpha(229);
        this.f811z.getBackground().setAlpha(229);
        this.f807v.getBackground().setAlpha(229);
        ImageView imageView = (ImageView) this.f577f.findViewById(R.id.shenpian_erweima);
        if (!"shenpian".equalsIgnoreCase(C1415a.f4150b)) {
            imageView.setVisibility(8);
            return;
        }
        if (!"HiSTBAndroidV6IPBS3930".equalsIgnoreCase(Build.BRAND + Build.MODEL)) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
            ComponentCallbacks2C1808c.m2023e(getContext()).m2042l("mnt/sdcard/shared/QR.png").m2038j(imageView);
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
        this.f807v.setOnClickListener(this);
        this.f809x.setOnClickListener(this);
        this.f807v.setOnFocusChangeListener(this);
        this.f809x.setOnFocusChangeListener(this);
        this.f811z.setOnFocusChangeListener(this);
        this.f811z.setOnClickListener(this);
        this.f807v.requestFocus();
        m497n(0);
    }

    /* renamed from: j */
    public final void m493j() {
        C2073a.m2459d("cancelTimerTask");
        Timer timer = this.f787D;
        if (timer != null) {
            timer.cancel();
            this.f787D.purge();
            this.f787D = null;
        }
        TimerTask timerTask = this.f788E;
        if (timerTask != null) {
            timerTask.cancel();
            this.f788E = null;
        }
    }

    /* renamed from: k */
    public final void m494k(RelativeLayout relativeLayout) {
        ViewCompat.animate(relativeLayout).setDuration(200L).scaleX(1.1f).scaleY(1.1f).start();
    }

    /* renamed from: l */
    public final void m495l(RelativeLayout relativeLayout) {
        ViewCompat.animate(relativeLayout).setDuration(200L).scaleX(1.0f).scaleY(1.0f).start();
    }

    /* renamed from: m */
    public final String m496m() {
        String strM2376d = C2025a.m2376d();
        return "shenpian".equalsIgnoreCase(C1415a.f4150b) ? C0063n.m88a(strM2376d, "-T") : strM2376d;
    }

    /* renamed from: n */
    public final void m497n(int i7) {
        if (this.f790G) {
            return;
        }
        C2073a.m2459d("focus position = " + i7);
        this.f789F = i7;
    }

    /* renamed from: o */
    public final void m498o(View view, boolean z6) {
        if (view == null) {
            return;
        }
        view.setFocusableInTouchMode(z6);
        view.setFocusable(z6);
        view.setClickable(z6);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        C2073a.m2459d("Main onClick");
        int id = view.getId();
        if (id == R.id.device_name_view) {
            C0580b.m417c("DEVICENAME", getClass().getSimpleName());
            C0988e.m971O(getActivity().getSupportFragmentManager(), "TV_NAME");
            m497n(0);
            return;
        }
        if (id != R.id.internet_setting_view) {
            if (id != R.id.setting_view) {
                return;
            }
            C0580b.m417c("SETTING", getClass().getSimpleName());
            C0988e.m971O(getActivity().getSupportFragmentManager(), "SYSTEM_SETTING");
            m497n(2);
            return;
        }
        C0580b.m417c("TO_INTERNET", getClass().getSimpleName());
        Context context = getContext();
        if (context != null) {
            if (C1189d.m1402d(MyApplication.f561e)) {
                try {
                    context.startActivity(new Intent("android.settings.SETTINGS"));
                } catch (Exception e7) {
                    e7.printStackTrace();
                    try {
                        Intent intent = new Intent();
                        intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
                        context.startActivity(intent);
                    } catch (Exception e8) {
                        e8.printStackTrace();
                    }
                }
            } else {
                try {
                    context.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            }
        }
        m497n(1);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z6) {
        if (view == null) {
            return;
        }
        C2073a.m2459d("Main onFocusChange：" + z6);
        int id = view.getId();
        if (id == R.id.device_name_view) {
            if (!z6) {
                this.f795j.setImageResource(R.drawable.tv_name);
                this.f801p.setTextColor(getResources().getColor(R.color.color_black));
                this.f799n.setTextColor(getResources().getColor(R.color.color_black));
                this.f795j.getDrawable().setAlpha(255);
                this.f807v.getBackground().setAlpha(229);
                m495l(this.f808w);
                return;
            }
            m497n(0);
            this.f795j.setImageResource(R.drawable.tv_name_hl);
            this.f801p.setTextColor(getResources().getColor(R.color.main_title_hl));
            this.f799n.setTextColor(getResources().getColor(R.color.main_title_hl));
            this.f795j.getDrawable().setAlpha(229);
            this.f807v.getBackground().setAlpha(255);
            m494k(this.f808w);
            return;
        }
        if (id != R.id.internet_setting_view) {
            if (id != R.id.setting_view) {
                return;
            }
            if (!z6) {
                this.f796k.setImageResource(R.drawable.setting_icon);
                this.f806u.setTextColor(getResources().getColor(R.color.color_black));
                this.f796k.getDrawable().setAlpha(255);
                this.f809x.getBackground().setAlpha(229);
                m495l(this.f810y);
                return;
            }
            m497n(2);
            this.f796k.setImageResource(R.drawable.setting_icon_hl);
            this.f806u.setTextColor(getResources().getColor(R.color.main_title_hl));
            this.f796k.getDrawable().setAlpha(229);
            this.f809x.getBackground().setAlpha(255);
            m494k(this.f810y);
            return;
        }
        if (!z6) {
            this.f794i.setImageResource(R.drawable.internet);
            this.f798m.setTextColor(getResources().getColor(R.color.color_black));
            this.f797l.setTextColor(getResources().getColor(R.color.color_black));
            this.f794i.getDrawable().setAlpha(255);
            this.f811z.getBackground().setAlpha(229);
            m495l(this.f784A);
            return;
        }
        m497n(1);
        this.f794i.setImageResource(R.drawable.internet_hl);
        this.f798m.setTextColor(getResources().getColor(R.color.main_title_hl));
        this.f797l.setTextColor(getResources().getColor(R.color.main_title_hl));
        this.f794i.getDrawable().setAlpha(229);
        this.f811z.getBackground().setAlpha(255);
        m494k(this.f784A);
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) throws Resources.NotFoundException {
        super.onHiddenChanged(z6);
        if (z6) {
            this.f790G = true;
            m493j();
            m498o(this.f811z, false);
            m498o(this.f809x, false);
            m498o(this.f807v, false);
            return;
        }
        int i7 = this.f789F;
        if (i7 == 0) {
            m498o(this.f807v, true);
            RelativeLayout relativeLayout = this.f807v;
            if (relativeLayout != null) {
                relativeLayout.requestFocus();
            }
        } else if (i7 == 1) {
            m498o(this.f811z, true);
            RelativeLayout relativeLayout2 = this.f811z;
            if (relativeLayout2 != null) {
                relativeLayout2.requestFocus();
            }
        } else if (i7 == 2) {
            m498o(this.f809x, true);
            RelativeLayout relativeLayout3 = this.f809x;
            if (relativeLayout3 != null) {
                relativeLayout3.requestFocus();
            }
        }
        m498o(this.f811z, true);
        m498o(this.f809x, true);
        m498o(this.f807v, true);
        if (this.f801p != null) {
            if ("央视频电视投屏助手".equals(C1581b.m1837f())) {
                this.f801p.setText("央视频\r\n电视投屏助手");
            } else {
                this.f801p.setText(C1581b.m1837f());
            }
        }
        m499p();
        m500q();
        this.f790G = false;
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.f786C != null) {
            getActivity().unregisterReceiver(this.f786C);
            this.f786C = null;
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() throws Resources.NotFoundException {
        super.onResume();
        C2073a.m2459d("注册了网络变化的广播");
        NetWorkChangeBroadcast netWorkChangeBroadcast = new NetWorkChangeBroadcast();
        this.f786C = netWorkChangeBroadcast;
        netWorkChangeBroadcast.f582a = this.f792I;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        getActivity().registerReceiver(this.f786C, intentFilter, "com.cctv.tv.permission.connectivity_main", null);
        m499p();
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onStart() {
        P p7;
        super.onStart();
        m500q();
        if (!this.f791H || (p7 = this.f576e) == 0) {
            return;
        }
        ((AbstractC0007b) p7).mo7e();
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        m493j();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0056  */
    /* renamed from: p */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m499p() throws android.content.res.Resources.NotFoundException {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cctv.p025tv.mvp.p026ui.fragment.MainFragment.m499p():void");
    }

    /* renamed from: q */
    public final void m500q() {
        m493j();
        this.f787D = new Timer(getClass().getSimpleName());
        C0614b c0614b = new C0614b();
        this.f788E = c0614b;
        this.f787D.schedule(c0614b, 0L, 1000L);
    }

    /* renamed from: r */
    public void m501r(AppUpdateEntity.AndroidBean androidBean) {
        TextView textView = this.f800o;
        if (textView == null) {
            return;
        }
        if (androidBean != null) {
            textView.setText(m496m());
            return;
        }
        textView.setText(m496m() + C2026b.m2379b(R.string.version_newest));
    }
}
