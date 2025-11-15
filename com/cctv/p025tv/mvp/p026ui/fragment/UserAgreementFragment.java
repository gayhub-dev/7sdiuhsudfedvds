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
public class UserAgreementFragment extends BaseFragment {

    /* renamed from: h */
    public TextView f845h;

    /* renamed from: i */
    public TextView f846i;

    /* renamed from: j */
    public RelativeLayout f847j;

    /* renamed from: k */
    public ScrollView f848k;

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_user_agreement;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
        this.f845h.setText(C2026b.m2379b(R.string.cctv_dlna_user_agreement));
        this.f847j.setBackgroundResource(R.drawable.left_ten);
        ScrollView scrollView = this.f848k;
        if (scrollView != null) {
            scrollView.scrollTo(0, 0);
            TextView textView = this.f846i;
            if (textView != null) {
                textView.requestFocus();
            }
        }
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f845h = (TextView) this.f577f.findViewById(R.id.top_name);
        this.f847j = (RelativeLayout) this.f577f.findViewById(R.id.rl_left_bg);
        this.f848k = (ScrollView) this.f577f.findViewById(R.id.sv_text_ua);
        this.f846i = (TextView) this.f577f.findViewById(R.id.tv_policy_ua);
        ((TextView) this.f577f.findViewById(R.id.tv_record_num)).setText(C1189d.m1399a());
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        super.onHiddenChanged(z6);
        ScrollView scrollView = this.f848k;
        if (scrollView != null) {
            scrollView.scrollTo(0, 0);
            TextView textView = this.f846i;
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
