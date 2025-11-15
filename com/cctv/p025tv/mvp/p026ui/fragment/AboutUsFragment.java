package com.cctv.p025tv.mvp.p026ui.fragment;

import android.arch.lifecycle.C0063n;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.base.BaseFragment;
import com.cctv.p025tv.module.collect.C0580b;
import p043f.C0988e;
import p093l1.C1415a;
import p132p6.C1749a;
import p179w2.C2025a;
import p179w2.C2026b;
import p186x2.C2073a;
import p190y.AbstractC2085c;

/* loaded from: classes.dex */
public class AboutUsFragment extends BaseFragment implements View.OnClickListener, View.OnFocusChangeListener {

    /* renamed from: h */
    public TextView f735h;

    /* renamed from: i */
    public TextView f736i;

    /* renamed from: j */
    public TextView f737j;

    /* renamed from: k */
    public RelativeLayout f738k;

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_about_us;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
        this.f735h.setText(C2026b.m2379b(R.string.about_us));
        TextView textView = this.f736i;
        StringBuilder sb = new StringBuilder();
        sb.append(C2026b.m2379b(R.string.version_name));
        sb.append("  V");
        String strM2376d = C2025a.m2376d();
        if ("shenpian".equalsIgnoreCase(C1415a.f4150b)) {
            strM2376d = C0063n.m88a(strM2376d, "-T");
        }
        sb.append(strM2376d);
        textView.setText(sb.toString());
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f735h = (TextView) this.f577f.findViewById(R.id.top_name);
        this.f736i = (TextView) this.f577f.findViewById(R.id.tv_about_us_version_name);
        this.f737j = (TextView) this.f577f.findViewById(R.id.tv_about_us_privacy_policy);
        this.f738k = (RelativeLayout) this.f577f.findViewById(R.id.rl_left_bg);
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
        this.f737j.setOnClickListener(this);
        this.f738k.setBackgroundResource(R.drawable.left_four);
        this.f737j.requestFocus();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.tv_about_us_privacy_policy) {
            return;
        }
        C0580b.m417c("WELCOME", getClass().getSimpleName());
        C0988e.m971O(getActivity().getSupportFragmentManager(), "WELCOME");
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z6) {
        if (view == null) {
            return;
        }
        C2073a.m2459d(" onFocusChange：" + z6);
        view.getId();
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        super.onHiddenChanged(z6);
        if (z6) {
            return;
        }
        C1749a.m1913b(getActivity(), this.f577f);
    }
}
