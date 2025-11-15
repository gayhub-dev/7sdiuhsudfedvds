package com.cctv.p025tv.mvp.p026ui.fragment;

import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.base.BaseFragment;
import p078j2.C1189d;
import p132p6.C1749a;
import p179w2.C2026b;
import p190y.AbstractC2085c;

/* loaded from: classes.dex */
public class PrivacyPolicyFragment extends BaseFragment {

    /* renamed from: h */
    public TextView f819h;

    /* renamed from: i */
    public TextView f820i;

    /* renamed from: j */
    public RelativeLayout f821j;

    /* renamed from: k */
    public ScrollView f822k;

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_privacy_policy;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
        this.f819h.setText(C2026b.m2379b(R.string.cctv_dlna_privacy_policy));
        this.f821j.setBackgroundResource(R.drawable.left_ten);
        ScrollView scrollView = this.f822k;
        if (scrollView != null) {
            scrollView.scrollTo(0, 0);
            TextView textView = this.f820i;
            if (textView != null) {
                textView.requestFocus();
            }
        }
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f819h = (TextView) this.f577f.findViewById(R.id.top_name);
        this.f821j = (RelativeLayout) this.f577f.findViewById(R.id.rl_left_bg);
        this.f822k = (ScrollView) this.f577f.findViewById(R.id.sv_text);
        this.f820i = (TextView) this.f577f.findViewById(R.id.tv_policy);
        ((TextView) this.f577f.findViewById(R.id.tv_record_num)).setText(C1189d.m1399a());
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        super.onHiddenChanged(z6);
        ScrollView scrollView = this.f822k;
        if (scrollView != null) {
            scrollView.scrollTo(0, 0);
            TextView textView = this.f820i;
            if (textView != null) {
                textView.requestFocus();
            }
        }
        if (z6) {
            return;
        }
        C1749a.m1913b(getActivity(), this.f577f);
    }
}
