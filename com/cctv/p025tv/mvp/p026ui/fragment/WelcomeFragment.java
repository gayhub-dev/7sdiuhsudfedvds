package com.cctv.p025tv.mvp.p026ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.base.BaseFragment;
import p009b.C0413b;
import p132p6.C1749a;
import p190y.AbstractC2085c;

/* loaded from: classes.dex */
public class WelcomeFragment extends BaseFragment implements View.OnClickListener {

    /* renamed from: h */
    public TextView f880h;

    /* renamed from: i */
    public TextView f881i;

    /* renamed from: j */
    public TextView f882j;

    /* renamed from: k */
    public Button f883k;

    /* renamed from: l */
    public Button f884l;

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_welcom;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
        TextView textView = this.f882j;
        StringBuilder sbM112a = C0413b.m112a("\u3000\u3000");
        sbM112a.append((Object) this.f882j.getText());
        textView.setText(sbM112a.toString());
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f883k = (Button) this.f577f.findViewById(R.id.btn_policy_yes_wc);
        this.f884l = (Button) this.f577f.findViewById(R.id.btn_policy_no_wc);
        this.f880h = (TextView) this.f577f.findViewById(R.id.tv_pp);
        this.f881i = (TextView) this.f577f.findViewById(R.id.tv_ua);
        this.f882j = (TextView) this.f577f.findViewById(R.id.tv_wc_title);
        Button button = this.f883k;
        if (button != null) {
            button.requestFocus();
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
        this.f883k.setOnClickListener(this);
        this.f884l.setOnClickListener(this);
        this.f881i.setOnClickListener(this);
        this.f880h.setOnClickListener(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:86:0x010e  */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onClick(android.view.View r5) throws java.lang.IllegalAccessException, java.lang.NoSuchMethodException, java.security.NoSuchAlgorithmException, java.lang.ClassNotFoundException, java.lang.SecurityException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instructions count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cctv.p025tv.mvp.p026ui.fragment.WelcomeFragment.onClick(android.view.View):void");
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        super.onHiddenChanged(z6);
        if (z6) {
            return;
        }
        Button button = this.f883k;
        if (button != null) {
            button.requestFocus();
        }
        C1749a.m1913b(getActivity(), this.f577f);
    }
}
