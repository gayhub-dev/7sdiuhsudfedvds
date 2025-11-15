package com.cctv.p025tv.mvp.p026ui.fragment;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.base.BaseFragment;
import com.cctv.p025tv.module.collect.C0580b;
import com.cctv.p025tv.mvp.p026ui.fragment.SharpnessSwitchFragment;
import com.tencent.mars.xlog.Log;
import p043f.C0988e;
import p078j2.C1197l;
import p132p6.C1749a;
import p179w2.C2026b;
import p190y.AbstractC2085c;

/* loaded from: classes.dex */
public class SharpnessSwitchFragment extends BaseFragment implements View.OnClickListener {

    /* renamed from: o */
    public static final /* synthetic */ int f823o = 0;

    /* renamed from: h */
    public TextView f824h;

    /* renamed from: i */
    public Button f825i;

    /* renamed from: j */
    public Button f826j;

    /* renamed from: k */
    public Dialog f827k;

    /* renamed from: l */
    public Button f828l;

    /* renamed from: m */
    public Button f829m;

    /* renamed from: n */
    public RelativeLayout f830n;

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_sharpness_switch;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
        this.f824h.setText(C2026b.m2379b(R.string.sharpness_switch));
        this.f830n.setBackgroundResource(R.drawable.left_four);
        if (C0988e.m960D()) {
            this.f825i.requestFocus();
        } else {
            this.f826j.requestFocus();
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f824h = (TextView) this.f577f.findViewById(R.id.top_name);
        this.f825i = (Button) this.f577f.findViewById(R.id.btn_sharpness_yes);
        this.f826j = (Button) this.f577f.findViewById(R.id.btn_sharpness_no);
        this.f830n = (RelativeLayout) this.f577f.findViewById(R.id.rl_left_bg);
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
        this.f825i.setOnClickListener(this);
        this.f826j.setOnClickListener(this);
    }

    /* renamed from: j */
    public final void m502j() {
        Dialog dialog = this.f827k;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.f827k.dismiss();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        final int i7 = 0;
        switch (view.getId()) {
            case R.id.btn_sharpness_no /* 2131230798 */:
                C0580b.m417c("SHARPNESSSWITCH_HINT_OFF", getClass().getSimpleName());
                C1197l.m1424d(MyApplication.f561e, "SHARPNESS_SWITCH_HINT", false);
                C0988e.m971O(getActivity().getSupportFragmentManager(), "SYSTEM_SETTING");
                Log.m655i("XLog_APP ", "SharpnessSwitchFragment 不提示切换分辨率");
                break;
            case R.id.btn_sharpness_yes /* 2131230799 */:
                C0580b.m417c("SHARPNESSSWITCH_HINT_ON", getClass().getSimpleName());
                if (this.f827k == null) {
                    this.f827k = new Dialog(getActivity(), R.style.MyDialog);
                    View viewInflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.dialog_sharpness, (ViewGroup) null);
                    this.f827k.setContentView(viewInflate);
                    ViewGroup.LayoutParams layoutParams = viewInflate.getLayoutParams();
                    layoutParams.width = getResources().getDisplayMetrics().widthPixels;
                    viewInflate.setLayoutParams(layoutParams);
                    Window window = this.f827k.getWindow();
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    attributes.dimAmount = 0.0f;
                    window.setAttributes(attributes);
                    this.f828l = (Button) viewInflate.findViewById(R.id.btn_dialog_auto);
                    this.f829m = (Button) viewInflate.findViewById(R.id.btn_dialog_manual);
                    viewInflate.findViewById(R.id.btn_dialog_auto).setOnClickListener(new View.OnClickListener(this) { // from class: g2.d

                        /* renamed from: f */
                        public final /* synthetic */ SharpnessSwitchFragment f1981f;

                        {
                            this.f1981f = this;
                        }

                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            switch (i7) {
                                case 0:
                                    SharpnessSwitchFragment sharpnessSwitchFragment = this.f1981f;
                                    int i8 = SharpnessSwitchFragment.f823o;
                                    C0580b.m417c("SHARPNESSSWITCH_AUTO", sharpnessSwitchFragment.getClass().getSimpleName());
                                    C1197l.m1424d(MyApplication.f561e, "SHARPNESS_SWITCH_MODEL", true);
                                    C1197l.m1424d(MyApplication.f561e, "SHARPNESS_SWITCH_HINT", true);
                                    Log.m655i("XLog_APP ", "SharpnessSwitchFragment 提示方式 自动");
                                    sharpnessSwitchFragment.m502j();
                                    break;
                                default:
                                    SharpnessSwitchFragment sharpnessSwitchFragment2 = this.f1981f;
                                    int i9 = SharpnessSwitchFragment.f823o;
                                    C0580b.m417c("SHARPNESSSWITCH_MANUAL", sharpnessSwitchFragment2.getClass().getSimpleName());
                                    C1197l.m1424d(MyApplication.f561e, "SHARPNESS_SWITCH_MODEL", false);
                                    C1197l.m1424d(MyApplication.f561e, "SHARPNESS_SWITCH_HINT", true);
                                    Log.m655i("XLog_APP ", "SharpnessSwitchFragment 提示方式 手动");
                                    sharpnessSwitchFragment2.m502j();
                                    break;
                            }
                        }
                    });
                    final int i8 = 1;
                    viewInflate.findViewById(R.id.btn_dialog_manual).setOnClickListener(new View.OnClickListener(this) { // from class: g2.d

                        /* renamed from: f */
                        public final /* synthetic */ SharpnessSwitchFragment f1981f;

                        {
                            this.f1981f = this;
                        }

                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            switch (i8) {
                                case 0:
                                    SharpnessSwitchFragment sharpnessSwitchFragment = this.f1981f;
                                    int i82 = SharpnessSwitchFragment.f823o;
                                    C0580b.m417c("SHARPNESSSWITCH_AUTO", sharpnessSwitchFragment.getClass().getSimpleName());
                                    C1197l.m1424d(MyApplication.f561e, "SHARPNESS_SWITCH_MODEL", true);
                                    C1197l.m1424d(MyApplication.f561e, "SHARPNESS_SWITCH_HINT", true);
                                    Log.m655i("XLog_APP ", "SharpnessSwitchFragment 提示方式 自动");
                                    sharpnessSwitchFragment.m502j();
                                    break;
                                default:
                                    SharpnessSwitchFragment sharpnessSwitchFragment2 = this.f1981f;
                                    int i9 = SharpnessSwitchFragment.f823o;
                                    C0580b.m417c("SHARPNESSSWITCH_MANUAL", sharpnessSwitchFragment2.getClass().getSimpleName());
                                    C1197l.m1424d(MyApplication.f561e, "SHARPNESS_SWITCH_MODEL", false);
                                    C1197l.m1424d(MyApplication.f561e, "SHARPNESS_SWITCH_HINT", true);
                                    Log.m655i("XLog_APP ", "SharpnessSwitchFragment 提示方式 手动");
                                    sharpnessSwitchFragment2.m502j();
                                    break;
                            }
                        }
                    });
                }
                this.f827k.show();
                C1749a.m1914c(this.f827k, null);
                if (!C1197l.m1422b(MyApplication.f561e, "SHARPNESS_SWITCH_MODEL", false)) {
                    this.f829m.requestFocus();
                    break;
                } else {
                    this.f828l.requestFocus();
                    break;
                }
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        super.onHiddenChanged(z6);
        if (z6) {
            m502j();
            return;
        }
        if (C0988e.m960D()) {
            this.f825i.requestFocus();
        } else {
            this.f826j.requestFocus();
        }
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        m502j();
    }
}
