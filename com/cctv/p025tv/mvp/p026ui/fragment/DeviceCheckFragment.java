package com.cctv.p025tv.mvp.p026ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.base.BaseFragment;
import com.cctv.p025tv.module.function.guid.TempCloudDevice;
import com.cctv.p025tv.module.service.UploadLogService;
import com.cctv.p025tv.mvp.p026ui.activity.MainActivity;
import com.cctv.p025tv.mvp.p026ui.adapter.DeviceInfoAdapter;
import com.cctv.p025tv.utils.PermissionsUtils;
import com.tencent.mars.xlog.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;
import p038e2.C0945a;
import p043f.C0988e;
import p078j2.C1193h;
import p078j2.C1194i;
import p078j2.C1195j;
import p101m1.RunnableC1457a;
import p132p6.C1749a;
import p165u2.C1974a;
import p179w2.C2025a;
import p179w2.C2026b;
import p186x2.C2073a;
import p186x2.C2074b;
import p190y.AbstractC2085c;
import p193y2.C2106a;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.RationaleDialogFragmentCompat;

/* loaded from: classes.dex */
public class DeviceCheckFragment extends BaseFragment implements View.OnClickListener {

    /* renamed from: h */
    public TextView f770h;

    /* renamed from: i */
    public RelativeLayout f771i;

    /* renamed from: j */
    public RecyclerView f772j;

    /* renamed from: k */
    public List<C0945a> f773k = new ArrayList();

    /* renamed from: l */
    public DeviceInfoAdapter f774l;

    /* renamed from: m */
    public Button f775m;

    /* renamed from: n */
    public long f776n;

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_device_check;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() throws Throwable {
        this.f770h.setText(C2026b.m2379b(R.string.device_check));
        m492j();
        this.f774l = new DeviceInfoAdapter(getContext(), this.f773k);
        this.f772j.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.f772j.setAdapter(this.f774l);
        Button button = this.f775m;
        if (button != null) {
            button.requestFocus();
        }
        StringBuilder sbM112a = C0413b.m112a("CtvitDeviceUtils.getDeviceId() = ");
        sbM112a.append(C2106a.m2549b());
        Log.m655i("XLog_APP ", sbM112a.toString());
        Log.m655i("XLog_APP ", "screenParam = " + C2074b.m2495r() + "-" + C2074b.m2489l() + "-" + C2074b.m2486i().densityDpi);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f770h = (TextView) this.f577f.findViewById(R.id.top_name);
        this.f772j = (RecyclerView) this.f577f.findViewById(R.id.rv_device_info);
        this.f771i = (RelativeLayout) this.f577f.findViewById(R.id.rl_left_bg);
        this.f775m = (Button) this.f577f.findViewById(R.id.btn_upload);
        C1749a.m1913b(getActivity(), this.f577f);
        GLSurfaceView gLSurfaceView = (GLSurfaceView) this.f577f.findViewById(R.id.gl_surface_view);
        gLSurfaceView.setEGLContextClientVersion(1);
        gLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 0, 0);
        gLSurfaceView.setRenderer(new C1193h());
        gLSurfaceView.post(new RunnableC1457a(this));
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
        this.f771i.setBackgroundResource(R.drawable.left_four);
        this.f775m.setOnClickListener(this);
    }

    /* renamed from: j */
    public final void m492j() throws Throwable {
        C0945a c0945a = new C0945a();
        c0945a.m858b("版本");
        c0945a.m857a(C2025a.m2376d());
        this.f773k.add(c0945a);
        C0945a c0945a2 = new C0945a();
        c0945a2.m858b("网络状态");
        String strM1418b = C1195j.m1418b();
        if (TextUtils.isEmpty(strM1418b)) {
            c0945a2.m857a("未连接");
        } else if ("ETHERNET".equals(strM1418b)) {
            c0945a2.m857a("有线网络");
        } else {
            c0945a2.m857a("WiFi");
        }
        this.f773k.add(c0945a2);
        C0945a c0945a3 = new C0945a();
        c0945a3.m858b("设备信息");
        c0945a3.m857a(Build.BRAND + "-" + Build.MODEL);
        this.f773k.add(c0945a3);
        C0945a c0945a4 = new C0945a();
        c0945a4.m858b("厂商信息");
        c0945a4.m857a(Build.MANUFACTURER);
        this.f773k.add(c0945a4);
        C0945a c0945a5 = new C0945a();
        c0945a5.m858b("CPU信息");
        c0945a5.m857a(Runtime.getRuntime().availableProcessors() + "核/" + C0988e.m988n());
        this.f773k.add(c0945a5);
        String strM991q = C0988e.m991q("Hardware");
        if (strM991q != null) {
            C0945a c0945a6 = new C0945a();
            c0945a6.m858b("芯片信息");
            c0945a6.m857a(strM991q);
            this.f773k.add(c0945a6);
        }
        C0945a c0945a7 = new C0945a();
        c0945a7.m858b("GPU信息");
        c0945a7.m857a(C1193h.f2615a + ServiceReference.DELIMITER + C1193h.f2616b);
        this.f773k.add(c0945a7);
        C0945a c0945a8 = new C0945a();
        c0945a8.m858b("运行内存");
        c0945a8.m857a(C1194i.m1415c(getContext()));
        this.f773k.add(c0945a8);
        C0945a c0945a9 = new C0945a();
        c0945a9.m858b("存储空间");
        c0945a9.m857a(C1194i.m1413a() + "M/" + C1194i.m1416d() + "M");
        this.f773k.add(c0945a9);
        C0945a c0945a10 = new C0945a();
        c0945a10.m858b("系统版本");
        c0945a10.m857a(Build.VERSION.RELEASE + ServiceReference.DELIMITER + Build.VERSION.SDK_INT);
        this.f773k.add(c0945a10);
        C0945a c0945a11 = new C0945a();
        c0945a11.m858b("GUID");
        c0945a11.m857a(TempCloudDevice.getGuid());
        this.f773k.add(c0945a11);
        Log.m655i("XLog_APP ", "DeviceCheckFragment deviceInfoList = " + JSON.toJSONString(this.f773k));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (view.getId() != R.id.btn_upload) {
            return;
        }
        Log.appenderFlush();
        C2073a.m2459d("TXL_MY_   appenderFlush");
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            if (!PermissionsUtils.isReadWritePermissionsStatus()) {
                Context context = getContext();
                if (context instanceof Activity) {
                    String[] strArr = PermissionsUtils.WRITE;
                    Activity activity = (Activity) context;
                    if (!EasyPermissions.m1918d(activity, Arrays.asList(strArr))) {
                        PermissionsUtils.readWritePermissions(activity);
                        return;
                    }
                    FragmentManager supportFragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                    RationaleDialogFragmentCompat rationaleDialogFragmentCompatM1921e = RationaleDialogFragmentCompat.m1921e(context.getString(R.string.permission_not_remind), context.getString(R.string.permission_not_remind_agree), "", 0, 1, strArr);
                    if (supportFragmentManager.isStateSaved()) {
                        return;
                    }
                    rationaleDialogFragmentCompatM1921e.show(supportFragmentManager, "RationaleDialogFragmentCompat");
                    return;
                }
                return;
            }
            if (System.currentTimeMillis() - this.f776n < 60000) {
                C1974a.m2299b(R.string.wait_upload).m2345a();
                return;
            }
            this.f776n = System.currentTimeMillis();
            C2073a.m2459d("main uploadLog");
            if (Boolean.valueOf(mainActivity.f674m != null && mainActivity.f675n).booleanValue()) {
                mainActivity.f674m.m458c();
            } else {
                C2073a.m2459d("uploadLogServices");
                mainActivity.f675n = MyApplication.f561e.bindService(new Intent(MyApplication.f561e, (Class<?>) UploadLogService.class), mainActivity.f666C, 1);
            }
            Log.m655i("XLog_APP ", "上传xlog日志");
            FragmentActivity activity2 = getActivity();
            View viewInflate = LayoutInflater.from(activity2).inflate(R.layout.toast_upload_log, (ViewGroup) null);
            ((TextView) viewInflate.findViewById(R.id.toast_text)).setText("日志上传\n上传期间您可以正常使用");
            Toast toast = new Toast(activity2);
            toast.setDuration(1);
            toast.setGravity(17, 0, 0);
            toast.setView(viewInflate);
            toast.show();
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) throws Throwable {
        super.onHiddenChanged(z6);
        if (z6) {
            return;
        }
        C1749a.m1913b(getActivity(), this.f577f);
        List<C0945a> list = this.f773k;
        if (list != null) {
            list.clear();
        }
        m492j();
        DeviceInfoAdapter deviceInfoAdapter = this.f774l;
        if (deviceInfoAdapter != null) {
            deviceInfoAdapter.f707a = this.f773k;
            deviceInfoAdapter.notifyDataSetChanged();
        }
        Button button = this.f775m;
        if (button != null) {
            button.requestFocus();
        }
    }
}
