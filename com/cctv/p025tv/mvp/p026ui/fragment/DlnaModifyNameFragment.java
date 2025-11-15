package com.cctv.p025tv.mvp.p026ui.fragment;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.base.BaseFragment;
import com.cctv.p025tv.module.collect.C0580b;
import com.ctvit.widget.edittext.CommonEditText;
import p043f.C0988e;
import p078j2.C1190e;
import p086k2.C1231b;
import p118o2.C1581b;
import p132p6.C1749a;
import p179w2.C2026b;
import p186x2.C2074b;
import p190y.AbstractC2085c;

/* loaded from: classes.dex */
public class DlnaModifyNameFragment extends BaseFragment implements View.OnClickListener, View.OnFocusChangeListener {

    /* renamed from: h */
    public TextView f777h;

    /* renamed from: i */
    public CommonEditText f778i;

    /* renamed from: j */
    public Button f779j;

    /* renamed from: k */
    public Button f780k;

    /* renamed from: l */
    public RelativeLayout f781l;

    /* renamed from: m */
    public RelativeLayout f782m;

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_dlna_modify_name;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
        this.f777h.setText(C2026b.m2379b(R.string.device_name_text));
        this.f782m.setBackgroundResource(R.drawable.left_four);
        String strM1837f = C1581b.m1837f();
        this.f778i.setText(strM1837f);
        this.f778i.setSelection(strM1837f.length());
        this.f778i.requestFocus();
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f781l = (RelativeLayout) this.f577f.findViewById(R.id.parent_layout);
        this.f781l.addView(View.inflate(getActivity(), R.layout.view_device_name_modify, null));
        this.f777h = (TextView) this.f577f.findViewById(R.id.top_name);
        this.f778i = (CommonEditText) this.f577f.findViewById(R.id.device_name_edit);
        this.f779j = (Button) this.f577f.findViewById(R.id.save_btn);
        this.f780k = (Button) this.f577f.findViewById(R.id.cancel_btn);
        this.f782m = (RelativeLayout) this.f577f.findViewById(R.id.rl_left_bg);
        C1749a.m1913b(getActivity(), this.f577f);
        this.f778i.performClick();
        CommonEditText.C0704c c0704cMo530a = this.f778i.mo530a();
        c0704cMo530a.f971b = 2;
        c0704cMo530a.f972c = 10;
        c0704cMo530a.m537d(R.string.device_name_lenght_min);
        c0704cMo530a.m536c(R.string.device_name_lenght_min);
        c0704cMo530a.m535b(R.string.device_name_lenght_max);
        c0704cMo530a.m534a();
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
        this.f779j.setOnClickListener(this);
        this.f780k.setOnClickListener(this);
        this.f779j.setOnFocusChangeListener(this);
        this.f780k.setOnFocusChangeListener(this);
        this.f778i.setOnFocusChangeListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cancel_btn) {
            C0580b.m417c("DEVICENAME_CANCEL", getClass().getSimpleName());
            C0988e.m971O(((AppCompatActivity) getContext()).getSupportFragmentManager(), "TV_NAME");
        } else if (id == R.id.save_btn && this.f778i.mo531b()) {
            C0580b.m417c("DEVICENAME_SAVE", getClass().getSimpleName());
            C1190e.m1406a(MyApplication.f561e, this.f778i.getText().toString());
            C0988e.m971O(((AppCompatActivity) getContext()).getSupportFragmentManager(), "TV_NAME");
        }
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z6) {
        if (view.getId() != R.id.device_name_edit) {
            return;
        }
        if (!z6) {
            C2074b.m2496s(this.f778i);
            return;
        }
        CommonEditText commonEditText = this.f778i;
        InputMethodManager inputMethodManager = (InputMethodManager) C1231b.f2761c.getSystemService("input_method");
        if (commonEditText == null || inputMethodManager == null) {
            return;
        }
        inputMethodManager.showSoftInput(commonEditText, 0);
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        super.onHiddenChanged(z6);
        if (z6) {
            C2074b.m2496s(this.f778i);
            return;
        }
        String strM1837f = C1581b.m1837f();
        this.f778i.setText(strM1837f);
        this.f778i.setSelection(strM1837f.length());
        C1749a.m1913b(getActivity(), this.f577f);
    }
}
