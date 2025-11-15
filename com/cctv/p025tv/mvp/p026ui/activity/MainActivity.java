package com.cctv.p025tv.mvp.p026ui.activity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.base.BaseActivity;
import com.cctv.p025tv.module.broadcast.HomeBroadcast;
import com.cctv.p025tv.module.collect.C0580b;
import com.cctv.p025tv.module.collect.report.event.ReportHeartBeatEvent;
import com.cctv.p025tv.module.function.guid.GuidManager;
import com.cctv.p025tv.module.function.guid.TempCloudDevice;
import com.cctv.p025tv.module.service.NetSpeedTestService;
import com.cctv.p025tv.module.service.UploadLogService;
import com.cctv.p025tv.module.service.WebSocketService;
import com.cctv.p025tv.mvp.p026ui.activity.MainActivity;
import com.cctv.p025tv.mvp.p026ui.fragment.MainFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.VersionUpdateFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import com.cctv.p025tv.utils.PermissionsUtils;
import com.ctvit.appupdate.entity.AppUpdateEntity;
import com.ctvit.dlna.entity.DlnaContentEntity;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.request.PostRequest;
import com.huan.appstore.third.ThirdUpgradeInfo;
import com.tencent.mars.xlog.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import p009b.C0413b;
import p013b3.C0440a;
import p020c2.C0510e;
import p020c2.C0511f;
import p020c2.RunnableC0508c;
import p029d1.C0865b;
import p029d1.C0866c;
import p043f.C0988e;
import p078j2.C1186a;
import p078j2.C1189d;
import p078j2.C1191f;
import p093l1.C1415a;
import p093l1.C1416b;
import p093l1.C1417c;
import p093l1.C1418d;
import p118o2.C1581b;
import p129p3.InterfaceC1737a;
import p132p6.C1749a;
import p144r2.C1830c;
import p150s1.C1868e;
import p179w2.C2025a;
import p186x2.C2073a;
import p190y.AbstractC2085c;
import p199z1.InterfaceC2147a;
import p199z1.InterfaceC2148b;
import p199z1.InterfaceC2149c;
import p200z2.C2150a;
import pub.devrel.easypermissions.EasyPermissions;

/* loaded from: classes.dex */
public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    /* renamed from: D */
    public static final /* synthetic */ int f663D = 0;

    /* renamed from: A */
    public Thread f664A;

    /* renamed from: f */
    public FragmentManager f667f;

    /* renamed from: g */
    public HomeBroadcast f668g;

    /* renamed from: h */
    public WebSocketService f669h;

    /* renamed from: i */
    public boolean f670i;

    /* renamed from: j */
    public NetSpeedTestService f671j;

    /* renamed from: k */
    public boolean f672k;

    /* renamed from: l */
    public Intent f673l;

    /* renamed from: m */
    public UploadLogService f674m;

    /* renamed from: n */
    public boolean f675n;

    /* renamed from: o */
    public boolean f676o;

    /* renamed from: p */
    public boolean f677p;

    /* renamed from: w */
    public AlertDialog f684w;

    /* renamed from: z */
    public InterfaceC1737a f687z;

    /* renamed from: q */
    public boolean f678q = false;

    /* renamed from: r */
    public Handler f679r = new HandlerC0599a(Looper.getMainLooper());

    /* renamed from: s */
    public boolean f680s = false;

    /* renamed from: t */
    public boolean f681t = true;

    /* renamed from: u */
    public boolean f682u = false;

    /* renamed from: v */
    public boolean f683v = true;

    /* renamed from: x */
    public ServiceConnection f685x = new ServiceConnectionC0600b();

    /* renamed from: y */
    public ServiceConnection f686y = new ServiceConnectionC0601c();

    /* renamed from: B */
    public ServiceConnection f665B = new ServiceConnectionC0602d();

    /* renamed from: C */
    public ServiceConnection f666C = new ServiceConnectionC0604f();

    /* renamed from: com.cctv.tv.mvp.ui.activity.MainActivity$a */
    public class HandlerC0599a extends Handler {
        public HandlerC0599a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            if (message.what == 800) {
                C2073a.m2459d("report heartBead");
                ReportHeartBeatEvent.post(MyApplication.f561e);
                MainActivity.this.f679r.removeMessages(800);
                MainActivity mainActivity = MainActivity.this;
                if (mainActivity.f680s) {
                    mainActivity.f679r.sendEmptyMessageDelayed(800, 25000L);
                }
            }
            super.handleMessage(message);
        }
    }

    /* renamed from: com.cctv.tv.mvp.ui.activity.MainActivity$b */
    public class ServiceConnectionC0600b implements ServiceConnection {
        public ServiceConnectionC0600b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MainActivity.this.f671j = ((InterfaceC2147a) iBinder).mo455a();
            C2073a.m2459d("绑定StatisticAnalysisService服务成功");
            NetSpeedTestService netSpeedTestService = MainActivity.this.f671j;
            netSpeedTestService.f643k.removeMessages(PathInterpolatorCompat.MAX_NUM_POINTS);
            netSpeedTestService.f643k.sendEmptyMessageDelayed(PathInterpolatorCompat.MAX_NUM_POINTS, 10000L);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* renamed from: com.cctv.tv.mvp.ui.activity.MainActivity$c */
    public class ServiceConnectionC0601c implements ServiceConnection {
        public ServiceConnectionC0601c() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MainActivity.this.f669h = ((InterfaceC2149c) iBinder).getService();
            C2073a.m2459d("绑定webSocketService服务成功");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* renamed from: com.cctv.tv.mvp.ui.activity.MainActivity$d */
    public class ServiceConnectionC0602d implements ServiceConnection {
        public ServiceConnectionC0602d() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            InterfaceC1737a c2190a;
            try {
                MainActivity mainActivity = MainActivity.this;
                int i7 = InterfaceC1737a.a.f4921a;
                if (iBinder == null) {
                    c2190a = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.huan.appstore.third.IThirdUpgradeService");
                    c2190a = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1737a)) ? new InterfaceC1737a.a.C2190a(iBinder) : (InterfaceC1737a) iInterfaceQueryLocalInterface;
                }
                mainActivity.f687z = c2190a;
                C2073a.m2459d("mStoreService 绑定成功 " + componentName);
                Log.m655i("XLog_APP ", "mStoreService 绑定成功 " + componentName);
                MainActivity.this.m467j(false);
            } catch (Throwable th) {
                StringBuilder sbM112a = C0413b.m112a("mStoreService 绑定异常 ");
                sbM112a.append(th.getMessage());
                C2073a.m2456a(sbM112a.toString());
                Log.m651e("XLog_APP ", "mStoreService 绑定异常 " + th.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            C2073a.m2459d("mStoreService  onServiceDisconnected " + componentName);
        }
    }

    /* renamed from: com.cctv.tv.mvp.ui.activity.MainActivity$e */
    public class RunnableC0603e implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ boolean f692e;

        /* renamed from: com.cctv.tv.mvp.ui.activity.MainActivity$e$a */
        public class a implements Runnable {

            /* renamed from: e */
            public final /* synthetic */ ThirdUpgradeInfo f694e;

            public a(ThirdUpgradeInfo thirdUpgradeInfo) {
                this.f694e = thirdUpgradeInfo;
            }

            @Override // java.lang.Runnable
            @SuppressLint({"DefaultLocale"})
            public void run() {
                MainFragment mainFragment = (MainFragment) MainActivity.this.getSupportFragmentManager().findFragmentByTag("MAIN_FRAGMENT");
                VersionUpdateFragment versionUpdateFragment = (VersionUpdateFragment) MainActivity.this.getSupportFragmentManager().findFragmentByTag("VERSION_UPDATE_FRAGMENT");
                AppUpdateEntity.AndroidBean androidBean = new AppUpdateEntity.AndroidBean();
                if (this.f694e == null) {
                    C2073a.m2459d("mStoreService appInfo == null");
                    Log.m655i("XLog_APP ", "mStoreService appInfo == null");
                    C1868e.f5445a = false;
                    C1868e.f5446b = false;
                    if (versionUpdateFragment != null && RunnableC0603e.this.f692e) {
                        androidBean.setBrief("");
                        versionUpdateFragment.m505j(3, androidBean);
                    }
                    if (mainFragment != null) {
                        mainFragment.m501r(null);
                        return;
                    }
                    return;
                }
                StringBuilder sbM112a = C0413b.m112a("mStoreService appInfo = ");
                sbM112a.append(JSON.toJSONString(this.f694e));
                Log.m655i("XLog_APP ", sbM112a.toString());
                androidBean.setBrief(this.f694e.f1278h);
                androidBean.setVersionCode(String.valueOf(this.f694e.f1277g));
                androidBean.setVersionName(this.f694e.f1276f);
                if (this.f694e.f1277g > C2025a.m2375c()) {
                    C1868e.f5445a = true;
                    MainActivity mainActivity = MainActivity.this;
                    Objects.requireNonNull(mainActivity);
                    C2073a.m2459d("mStoreService startUpgrade ");
                    mainActivity.m478u();
                    try {
                        if (mainActivity.m471n().booleanValue()) {
                            mainActivity.f687z.mo1887a(mainActivity.getPackageName(), mainActivity.getPackageName());
                            C1868e.f5447c = true;
                            C1868e.f5445a = false;
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    if (mainFragment != null) {
                        mainFragment.m501r(androidBean);
                    }
                } else {
                    C1868e.f5445a = false;
                    C1868e.f5446b = false;
                    if (versionUpdateFragment != null && RunnableC0603e.this.f692e) {
                        versionUpdateFragment.m505j(3, androidBean);
                    }
                    if (mainFragment != null) {
                        mainFragment.m501r(null);
                    }
                }
                StringBuilder sbM112a2 = C0413b.m112a("mStoreService upgradeStr =  ");
                ThirdUpgradeInfo thirdUpgradeInfo = this.f694e;
                sbM112a2.append(String.format("待升级版本:\nversionCode:%d\nversionName:%s\n更新信息:%s", Integer.valueOf(this.f694e.f1277g), thirdUpgradeInfo.f1276f, thirdUpgradeInfo.f1278h));
                C2073a.m2459d(sbM112a2.toString());
            }
        }

        public RunnableC0603e(boolean z6) {
            this.f692e = z6;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                MainActivity mainActivity = MainActivity.this;
                MainActivity.this.runOnUiThread(new a(mainActivity.f687z.mo1888b(mainActivity.getPackageName(), MainActivity.this.getPackageName())));
            } catch (Throwable th) {
                StringBuilder sbM112a = C0413b.m112a("mStoreService throwable = ");
                sbM112a.append(th.getMessage());
                C2073a.m2456a(sbM112a.toString());
                Log.m651e("XLog_APP ", "mStoreService throwable = " + th.getMessage());
            }
        }
    }

    /* renamed from: com.cctv.tv.mvp.ui.activity.MainActivity$f */
    public class ServiceConnectionC0604f implements ServiceConnection {
        public ServiceConnectionC0604f() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MainActivity.this.f674m = ((InterfaceC2148b) iBinder).mo459a();
            C2073a.m2459d("绑定uploadLogService服务成功");
            MainActivity.this.f674m.m458c();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
    /* renamed from: a */
    public void mo463a(int i7, @NonNull List<String> list) {
        if (i7 == 1000) {
            PermissionsUtils.saveReadWritePermissionsStatus(false);
        }
        if (i7 != 1001 || list == null) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if ("android.permission.WRITE_EXTERNAL_STORAGE".equalsIgnoreCase(it.next())) {
                PermissionsUtils.saveReadWritePermissionsStatus(false);
                C2073a.m2459d("cloud initCloudRegister  WRITE_EXTERNAL_STORAGE  onPermissionsDenied");
                Log.m655i("XLog_APP ", "onPermissionsDenied  WRITE_EXTERNAL_STORAGE");
                GuidManager.getInstance().initCloudRegister(TempCloudDevice.hasDeviceId() ? TempCloudDevice.getDeviceId() : TempCloudDevice.createDeviceId());
                return;
            }
        }
    }

    @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
    /* renamed from: b */
    public void mo464b(int i7, @NonNull List<String> list) {
        if (i7 == 1000) {
            PermissionsUtils.saveReadWritePermissionsStatus(true);
            if (this.f671j == null && C0988e.m962F()) {
                m465h();
            }
            if (!C1189d.m1401c() && !C1189d.m1400b()) {
                C1186a.m1388h(getSupportFragmentManager(), this);
            }
            Log.m655i("XLog_APP ", "REQUEST_CODE_READ_WRITE  REQUEST_CODE_READ_WRITE");
            C0865b.m673a();
            return;
        }
        if (i7 == 1001) {
            for (String str : list) {
                if ("android.permission.WRITE_EXTERNAL_STORAGE".equalsIgnoreCase(str)) {
                    PermissionsUtils.saveReadWritePermissionsStatus(true);
                    C2073a.m2459d("cloud initCloudRegister  WRITE_EXTERNAL_STORAGE");
                    Log.m655i("XLog_APP ", "REQUEST_CODE_APP_INIT  WRITE_EXTERNAL_STORAGE");
                    GuidManager.getInstance().initCloudRegister(TempCloudDevice.hasDeviceId() ? TempCloudDevice.getDeviceId() : TempCloudDevice.createDeviceId());
                } else if ("android.permission.ACCESS_FINE_LOCATION".equalsIgnoreCase(str)) {
                    Log.m655i("XLog_APP ", "REQUEST_CODE_APP_INIT  ACCESS_FINE_LOCATION");
                }
            }
        }
    }

    @Override // com.cctv.p025tv.base.BaseActivity
    /* renamed from: c */
    public AbstractC2085c mo402c() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseActivity
    /* renamed from: d */
    public int mo403d() {
        return R.layout.activity_main;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:200)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:61)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:101)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:101)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0386  */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.SupportActivity, android.app.Activity, android.view.Window.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchKeyEvent(android.view.KeyEvent r14) {
        /*
            Method dump skipped, instructions count: 1426
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cctv.p025tv.mvp.p026ui.activity.MainActivity.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cctv.p025tv.base.BaseActivity
    /* renamed from: e */
    public void mo404e() {
        this.f667f = getSupportFragmentManager();
        m473p();
        ((PostRequest) CtvitHttp.post("https://ytpaddr.cctv.cn/gsnw/drm/config/obtain").cacheMode(CacheMode.NO_CACHE)).execute(new C0511f(this));
    }

    @Override // com.cctv.p025tv.base.BaseActivity
    /* renamed from: f */
    public void mo405f() {
        WindowManager windowManager;
        Display defaultDisplay;
        Display.Mode[] supportedModes;
        Window window = getWindow();
        if (Build.VERSION.SDK_INT < 23 || (windowManager = (WindowManager) MyApplication.f561e.getSystemService("window")) == null || (supportedModes = (defaultDisplay = windowManager.getDefaultDisplay()).getSupportedModes()) == null) {
            return;
        }
        Display.Mode mode = null;
        long j7 = 0;
        for (Display.Mode mode2 : supportedModes) {
            C2073a.m2459d(String.format("available display mode: Mode %d: %dx%d/%.1ffps", Integer.valueOf(mode2.getModeId()), Integer.valueOf(mode2.getPhysicalWidth()), Integer.valueOf(mode2.getPhysicalHeight()), Float.valueOf(mode2.getRefreshRate())));
            long physicalHeight = mode2.getPhysicalHeight() * mode2.getPhysicalWidth();
            if (physicalHeight > j7) {
                j7 = physicalHeight;
                mode = mode2;
            }
        }
        if (mode == null || mode.getModeId() == defaultDisplay.getMode().getModeId()) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.preferredDisplayModeId = mode.getModeId();
        window.setAttributes(attributes);
        C2073a.m2459d(String.format("selected display mode: Mode %d: %dx%d/%.1ffps", Integer.valueOf(mode.getModeId()), Integer.valueOf(mode.getPhysicalWidth()), Integer.valueOf(mode.getPhysicalHeight()), Float.valueOf(mode.getRefreshRate())));
    }

    @Override // com.cctv.p025tv.base.BaseActivity
    /* renamed from: g */
    public void mo406g() {
        C1581b.m1836e().f4748d = new C0510e(this, 0);
        C1868e.f5448d = new C0510e(this, 1);
    }

    /* renamed from: h */
    public final void m465h() {
        C2073a.m2459d("bindNetSpeedTestServices");
        this.f672k = MyApplication.f561e.bindService(new Intent(MyApplication.f561e, (Class<?>) NetSpeedTestService.class), this.f685x, 1);
    }

    /* renamed from: i */
    public void m466i() {
        C2073a.m2459d("bindWebSocketServices");
        Intent intent = new Intent(MyApplication.f561e, (Class<?>) WebSocketService.class);
        this.f673l = intent;
        if (Build.VERSION.SDK_INT >= 26) {
            MyApplication.f561e.startForegroundService(intent);
        } else {
            MyApplication.f561e.startService(intent);
        }
        this.f670i = MyApplication.f561e.bindService(this.f673l, this.f686y, 1);
    }

    /* renamed from: j */
    public void m467j(boolean z6) {
        m478u();
        C2073a.m2459d("mStoreService checkUpgrade 1");
        if (m471n().booleanValue()) {
            C2073a.m2459d("mStoreService checkUpgrade 2");
            Thread thread = new Thread(new RunnableC0603e(z6));
            this.f664A = thread;
            thread.start();
        }
    }

    /* renamed from: k */
    public void m468k() {
        Uri data;
        C2073a.m2459d("openTest getUriData");
        Intent intent = getIntent();
        if (!"android.intent.action.VIEW".equals(intent.getAction()) || (data = intent.getData()) == null) {
            return;
        }
        String queryParameter = data.getQueryParameter("from");
        String queryParameter2 = data.getQueryParameter("dlna_url");
        String queryParameter3 = data.getQueryParameter("dlna_metadata");
        C2073a.m2459d("openTest from = " + queryParameter);
        C2073a.m2459d("openTest currentURI = " + queryParameter2);
        C2073a.m2459d("openTest currentURIMetaData = " + queryParameter3);
    }

    /* renamed from: l */
    public void m469l() {
        if (Build.VERSION.SDK_INT >= 30) {
            if (this.f671j == null && C0988e.m962F()) {
                m465h();
            }
            if (!C1189d.m1401c() && !C1189d.m1400b()) {
                C1186a.m1388h(getSupportFragmentManager(), this);
            }
            Log.m655i("XLog_APP ", "highSystemInit");
            C0865b.m673a();
            GuidManager.getInstance().initCloudRegister(TempCloudDevice.hasDeviceId() ? TempCloudDevice.getDeviceId() : TempCloudDevice.createDeviceId());
        }
        if (C1189d.m1402d(MyApplication.f561e) && C1186a.m1391k(this)) {
            m475r(false);
        }
    }

    /* renamed from: m */
    public final void m470m() {
        C2073a.m2459d("huaweiToPlay");
        if (MyApplication.f569m != null) {
            C2073a.m2459d("huaweiToPlay  showVideoFragment");
            m477t(MyApplication.f569m, false);
            MyApplication.f569m = null;
        }
    }

    /* renamed from: n */
    public Boolean m471n() {
        InterfaceC1737a interfaceC1737a = this.f687z;
        return Boolean.valueOf(interfaceC1737a != null && interfaceC1737a.asBinder().isBinderAlive());
    }

    /* renamed from: o */
    public boolean m472o() {
        StringBuilder sbM112a = C0413b.m112a("judgeHuaWei hadShowDialog = ");
        sbM112a.append(this.f677p);
        C2073a.m2459d(sbM112a.toString());
        C2073a.m2459d("judgeHuaWei isShowDialog = " + this.f678q);
        C2073a.m2459d("judgeHuaWei  MyApplication.open_over_play = " + MyApplication.f570n);
        return (!this.f677p && C1189d.m1402d(MyApplication.f561e) && C1186a.m1391k(MyApplication.f561e)) || this.f678q || MyApplication.f570n;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i7, int i8, @Nullable Intent intent) {
        super.onActivityResult(i7, i8, intent);
        if (i7 == 101) {
            Log.m655i("XLog_APP ", "REQUEST_CODE_MANAGE_OVERLAY_PERMISSION");
            if (this.f676o) {
                C2073a.m2456a("showVideo  3");
                m476s();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cctv.p025tv.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.onCreate(bundle);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        m474q();
        C2073a.m2459d("注册网络变化 - 屏幕开关广播 - 首页");
        if (((Boolean) C2150a.m2590a("FIRST_OPEN", Boolean.TRUE)).booleanValue()) {
            C2150a.m2591b("FIRST_OPEN", Boolean.FALSE);
        }
        C2073a.m2459d("MainActivity onCreate");
        int i7 = 0;
        if (C1186a.m1385e()) {
            m466i();
            C1191f.m1409c(false);
            C1186a.m1393m();
            Objects.requireNonNull(C1418d.m1602a());
            C1186a.m1390j();
            C1415a.m1599a().m1600b(this);
            PermissionsUtils.initPermissions(this);
            m478u();
            m468k();
            m469l();
            Handler handler = this.f679r;
            if (handler != null) {
                handler.sendEmptyMessage(800);
            }
        } else if (C1189d.m1403e(getApplicationContext()) || C1189d.m1405g(getApplicationContext()) || C1189d.m1404f(getApplicationContext()) || C1189d.m1402d(getApplicationContext())) {
            C1191f.m1409c(true);
            C1186a.m1393m();
            Objects.requireNonNull(C1418d.m1602a());
            C1186a.m1390j();
        }
        if (C1189d.m1402d(MyApplication.f561e)) {
            C1868e.f5447c = false;
        }
        C1417c c1417cM1601a = C1417c.m1601a();
        Objects.requireNonNull(c1417cM1601a);
        String strEncodeToString = "";
        String str = (String) C2150a.m2590a("error_info", "");
        if (!TextUtils.isEmpty(str)) {
            C2073a.m2456a("error stacktrace = " + str);
            Log.m651e("XLog_CRASH ", "uploadError error stacktrace = " + str);
            PostRequest postRequest = (PostRequest) CtvitHttp.post("https://ytpdata.cctv.cn/das/log").cacheMode(CacheMode.NO_CACHE);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("equipment_manufacturer", (Object) Build.BRAND);
            jSONObject.put("equipment_model", (Object) Build.MODEL);
            jSONObject.put("os_version", (Object) Build.VERSION.RELEASE);
            jSONObject.put("app_version_name", (Object) (C2025a.m2376d() + "(" + C2025a.m2375c() + ")"));
            jSONObject.put("channel", (Object) C1415a.f4150b);
            jSONObject.put("send_time", (Object) C0440a.m151b(System.currentTimeMillis(), C0440a.f273a, "GMT+08"));
            if (TextUtils.isEmpty(str)) {
                strEncodeToString = str;
            } else {
                try {
                    byte[] bytes = str.getBytes();
                    if (bytes != null) {
                        try {
                            strEncodeToString = Base64.encodeToString(bytes, 0);
                        } catch (Exception e7) {
                            C2073a.m2458c(e7);
                        }
                    }
                } catch (Exception e8) {
                    C2073a.m2458c(e8);
                }
            }
            jSONObject.put("stack_trace", (Object) strEncodeToString);
            postRequest.upJson(jSONObject.toJSONString()).execute(new C1416b(c1417cM1601a));
        }
        Log.m655i("XLog_APP ", "APP启动");
        new Thread(new RunnableC0508c(this, i7)).start();
    }

    @Override // com.cctv.p025tv.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        ServiceConnection serviceConnection = this.f685x;
        if (serviceConnection != null && this.f672k) {
            MyApplication.f561e.unbindService(serviceConnection);
        }
        ServiceConnection serviceConnection2 = this.f686y;
        if (serviceConnection2 != null && this.f670i) {
            MyApplication.f561e.unbindService(serviceConnection2);
            MyApplication.f561e.stopService(this.f673l);
        }
        Thread thread = this.f664A;
        if (thread != null) {
            thread.interrupt();
        }
        if (m471n().booleanValue()) {
            unbindService(this.f665B);
        }
        if (this.f666C != null) {
            if (Boolean.valueOf(this.f674m != null && this.f675n).booleanValue()) {
                MyApplication.f561e.unbindService(this.f666C);
            }
        }
        Log.m655i("XLog_APP ", "APP退出");
        Objects.requireNonNull(C0866c.m674a());
        Log.appenderClose();
        Handler handler = this.f679r;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f679r = null;
        }
        AlertDialog alertDialog = this.f684w;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f684w.dismiss();
        }
        super.onDestroy();
        C2073a.m2459d("MainActivity onDestroy");
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        C2073a.m2459d("onNewIntent");
        m473p();
    }

    @Override // com.cctv.p025tv.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        C2073a.m2459d("MainActivity onPause");
        MyApplication.f562f = null;
        MyApplication.f563g = null;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i7, @NonNull String[] strArr, @NonNull int[] iArr) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.onRequestPermissionsResult(i7, strArr, iArr);
        EasyPermissions.m1916b(i7, strArr, iArr, this);
    }

    @Override // com.cctv.p025tv.base.BaseActivity, android.app.Activity
    public void onRestart() {
        super.onRestart();
        m473p();
        mo406g();
        m474q();
        C2073a.m2459d("MainActivity onRestart");
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00d2  */
    @Override // com.cctv.p025tv.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onResume() {
        /*
            Method dump skipped, instructions count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cctv.p025tv.mvp.p026ui.activity.MainActivity.onResume():void");
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        Handler handler;
        this.f680s = true;
        if (!this.f681t && (handler = this.f679r) != null) {
            handler.sendEmptyMessage(800);
        }
        this.f681t = false;
        super.onStart();
    }

    @Override // com.cctv.p025tv.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        VideoFragment videoFragment;
        super.onStop();
        this.f680s = false;
        C2073a.m2459d("MainActivity onStop");
        C1191f.m1408b(MyApplication.f561e);
        if (this.f668g != null) {
            C2073a.m2459d("移除HOME键监听广播");
            unregisterReceiver(this.f668g);
            this.f668g = null;
        }
        FragmentManager fragmentManager = this.f667f;
        if (fragmentManager != null && (videoFragment = (VideoFragment) fragmentManager.findFragmentByTag("VIDEO_FRAGMENT")) != null && !videoFragment.isHidden()) {
            videoFragment.m508l().m441D();
        }
        C0988e.m958B(getSupportFragmentManager(), "VIDEO_FRAGMENT");
        C0988e.m958B(getSupportFragmentManager(), "DLNA_MODIFY_FRAGMENT");
        C0988e.m958B(getSupportFragmentManager(), "VERSION_UPDATE_FRAGMENT");
        C0988e.m958B(getSupportFragmentManager(), "SYSTEM_SETTING");
        if (C1581b.m1833b() != null && MyApplication.f569m == null) {
            ((C1830c.b) C1581b.m1833b()).m2076c();
        }
        WebSocketService webSocketService = this.f669h;
        if (webSocketService != null) {
            webSocketService.m462c();
        }
        if (C1189d.m1401c() || C1189d.m1404f(MyApplication.f561e)) {
            C1186a.m1394n(this);
        }
        Handler handler = this.f679r;
        if (handler != null) {
            handler.removeMessages(800);
        }
    }

    /* renamed from: p */
    public final void m473p() {
        if (C1186a.m1385e()) {
            C0988e.m971O(getSupportFragmentManager(), "MAIN_FRAGMENT");
            return;
        }
        C0988e.m971O(getSupportFragmentManager(), "WELCOME");
        if ((C1189d.m1403e(getApplicationContext()) || C1189d.m1405g(getApplicationContext()) || C1189d.m1402d(getApplicationContext()) || C1189d.m1404f(getApplicationContext())) && C1581b.m1833b() != null) {
            ((C1830c.b) C1581b.m1833b()).m2078e();
        }
    }

    /* renamed from: q */
    public final void m474q() {
        C2073a.m2459d("注册HOME键广播");
        this.f668g = new HomeBroadcast();
        registerReceiver(this.f668g, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), "com.cctv.tv.permission.closeSystemDialogs", null);
    }

    /* renamed from: r */
    public final void m475r(boolean z6) {
        final int i7 = 0;
        MyApplication.f568l = false;
        this.f676o = z6;
        StringBuilder sbM112a = C0413b.m112a("toDlnaPlay = ");
        sbM112a.append(this.f676o);
        Log.m655i("XLog_APP ", sbM112a.toString());
        final int i8 = 1;
        if (((Boolean) C2150a.m2590a("OVERLAY_NO_ACTIVITY", Boolean.FALSE)).booleanValue()) {
            this.f677p = true;
            return;
        }
        if (this.f678q || this.f677p || isDestroyed() || isFinishing()) {
            return;
        }
        try {
            C2073a.m2459d("SystemAlertWindow ");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.alert_system_window, (ViewGroup) null);
            builder.setView(relativeLayout);
            Button button = (Button) relativeLayout.findViewById(R.id.btn_agree_alert_sys);
            Button button2 = (Button) relativeLayout.findViewById(R.id.btn_refuse_alert_sys);
            button.requestFocus();
            final AlertDialog alertDialogCreate = builder.setCancelable(false).create();
            alertDialogCreate.show();
            this.f677p = true;
            this.f678q = true;
            C1749a.m1914c(alertDialogCreate, null);
            button.setOnClickListener(new View.OnClickListener(this) { // from class: c2.a

                /* renamed from: f */
                public final /* synthetic */ MainActivity f375f;

                {
                    this.f375f = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i7) {
                        case 0:
                            MainActivity mainActivity = this.f375f;
                            AlertDialog alertDialog = alertDialogCreate;
                            int i9 = MainActivity.f663D;
                            Objects.requireNonNull(mainActivity);
                            try {
                                Log.m655i("XLog_APP ", "打开悬浮窗授权页面");
                                mainActivity.startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + mainActivity.getPackageName())), 101);
                                MyApplication.f570n = true;
                            } catch (Exception e7) {
                                Log.m655i("XLog_APP ", "打开悬浮窗授权页面失败 e = " + e7.getMessage());
                                C2150a.m2591b("OVERLAY_NO_ACTIVITY", Boolean.TRUE);
                                MyApplication.f570n = false;
                                mainActivity.f678q = false;
                                mainActivity.m470m();
                            }
                            alertDialog.dismiss();
                            mainActivity.f678q = false;
                            break;
                        default:
                            MainActivity mainActivity2 = this.f375f;
                            AlertDialog alertDialog2 = alertDialogCreate;
                            int i10 = MainActivity.f663D;
                            Objects.requireNonNull(mainActivity2);
                            alertDialog2.dismiss();
                            mainActivity2.f678q = false;
                            if (mainActivity2.f676o) {
                                C2073a.m2456a("showVideo  2");
                                mainActivity2.m476s();
                            }
                            mainActivity2.m470m();
                            break;
                    }
                }
            });
            button2.setOnClickListener(new View.OnClickListener(this) { // from class: c2.a

                /* renamed from: f */
                public final /* synthetic */ MainActivity f375f;

                {
                    this.f375f = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i8) {
                        case 0:
                            MainActivity mainActivity = this.f375f;
                            AlertDialog alertDialog = alertDialogCreate;
                            int i9 = MainActivity.f663D;
                            Objects.requireNonNull(mainActivity);
                            try {
                                Log.m655i("XLog_APP ", "打开悬浮窗授权页面");
                                mainActivity.startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + mainActivity.getPackageName())), 101);
                                MyApplication.f570n = true;
                            } catch (Exception e7) {
                                Log.m655i("XLog_APP ", "打开悬浮窗授权页面失败 e = " + e7.getMessage());
                                C2150a.m2591b("OVERLAY_NO_ACTIVITY", Boolean.TRUE);
                                MyApplication.f570n = false;
                                mainActivity.f678q = false;
                                mainActivity.m470m();
                            }
                            alertDialog.dismiss();
                            mainActivity.f678q = false;
                            break;
                        default:
                            MainActivity mainActivity2 = this.f375f;
                            AlertDialog alertDialog2 = alertDialogCreate;
                            int i10 = MainActivity.f663D;
                            Objects.requireNonNull(mainActivity2);
                            alertDialog2.dismiss();
                            mainActivity2.f678q = false;
                            if (mainActivity2.f676o) {
                                C2073a.m2456a("showVideo  2");
                                mainActivity2.m476s();
                            }
                            mainActivity2.m470m();
                            break;
                    }
                }
            });
        } catch (Exception e7) {
            StringBuilder sbM112a2 = C0413b.m112a("requestSystemAlertWindow e = ");
            sbM112a2.append(e7.getMessage());
            Log.m651e("XLog_APP ", sbM112a2.toString());
        }
    }

    /* renamed from: s */
    public final void m476s() {
        if (MyApplication.f562f == null) {
            C2073a.m2459d("dlnaEntity 是空");
        } else {
            C2073a.m2459d("MyApplication.dlnaEntity 不是空");
            m477t(MyApplication.f562f, false);
        }
    }

    /* renamed from: t */
    public final void m477t(DlnaContentEntity dlnaContentEntity, boolean z6) {
        if (dlnaContentEntity != null && C1186a.m1389i() && C1186a.m1385e()) {
            C0580b.m418d("DLNA", MyApplication.f564h, MyApplication.f563g);
            m479v(dlnaContentEntity, z6);
        }
    }

    /* renamed from: u */
    public void m478u() {
        boolean z6;
        StringBuilder sbM112a = C0413b.m112a("mStoreService startService channel = ");
        sbM112a.append(C1415a.f4150b);
        C2073a.m2459d(sbM112a.toString());
        if (C1189d.m1401c() || C1189d.m1400b()) {
            C2073a.m2459d("mStoreService startService 2 ");
            if (m471n().booleanValue()) {
                return;
            }
            C2073a.m2459d("mStoreService startService 3 ");
            try {
                Intent intent = new Intent("com.huan.appstore.third.ThirdUpgradeService");
                boolean z7 = false;
                try {
                    getApplicationContext().getPackageManager().getPackageInfo("com.tcl.appmarket2", 1);
                    z6 = true;
                } catch (PackageManager.NameNotFoundException unused) {
                    z6 = false;
                }
                if (z6) {
                    intent.setPackage("com.tcl.appmarket2");
                } else {
                    try {
                        getApplicationContext().getPackageManager().getPackageInfo("com.changhong.appstore", 1);
                        z7 = true;
                    } catch (PackageManager.NameNotFoundException unused2) {
                    }
                    if (!z7) {
                        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("MAIN_FRAGMENT");
                        if (mainFragment != null) {
                            mainFragment.m501r(null);
                        }
                        Log.m655i("XLog_APP ", "mStoreService 无对应应用商店包名");
                        return;
                    }
                    intent.setPackage("com.changhong.appstore");
                }
                C2073a.m2459d("mStoreService startService intent = " + intent.getAction());
                bindService(intent, this.f665B, 1);
            } catch (Exception e7) {
                MainFragment mainFragment2 = (MainFragment) getSupportFragmentManager().findFragmentByTag("MAIN_FRAGMENT");
                if (mainFragment2 != null) {
                    mainFragment2.m501r(null);
                }
                StringBuilder sbM112a2 = C0413b.m112a("mStoreService startService Exception = ");
                sbM112a2.append(e7.getMessage());
                C2073a.m2459d(sbM112a2.toString());
                Log.m651e("XLog_APP ", "mStoreService startService Exception = " + e7.getMessage());
            }
            C2073a.m2459d("mStoreService startService 4 ");
        }
    }

    /* renamed from: v */
    public void m479v(final DlnaContentEntity dlnaContentEntity, final boolean z6) {
        if (this.f682u && this.f683v) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: c2.d
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity mainActivity = this.f381e;
                DlnaContentEntity dlnaContentEntity2 = dlnaContentEntity;
                boolean z7 = z6;
                int i7 = MainActivity.f663D;
                if (mainActivity.m472o()) {
                    MyApplication.f569m = dlnaContentEntity2;
                    Log.m655i("XLog_DLNA ", "MainActivity toPlayer 华为拦截 接收投屏数据");
                    if (C1581b.m1833b() != null) {
                        ((C1830c.b) C1581b.m1833b()).m2078e();
                        return;
                    }
                    return;
                }
                C0988e.m971O(mainActivity.f667f, "VIDEO_FRAGMENT");
                VideoFragment videoFragment = (VideoFragment) mainActivity.f667f.findFragmentByTag("VIDEO_FRAGMENT");
                if (videoFragment != null) {
                    if (z7) {
                        C2073a.m2459d("由 VideoFragment onCreateView 触发播放");
                        videoFragment.f861m = dlnaContentEntity2;
                    } else {
                        C2073a.m2459d("直接触发播放");
                        videoFragment.m510n(dlnaContentEntity2);
                    }
                }
            }
        });
    }
}
