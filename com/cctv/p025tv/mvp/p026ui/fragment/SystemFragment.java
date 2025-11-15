package com.cctv.p025tv.mvp.p026ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.base.BaseFragment;
import com.cctv.p025tv.module.collect.C0580b;
import com.cctv.p025tv.mvp.p026ui.activity.MainActivity;
import com.cctv.p025tv.mvp.p026ui.adapter.SystemAdapter;
import com.cctv.p025tv.mvp.p026ui.adapter.decoration.MyItemDecoration;
import java.util.ArrayList;
import java.util.List;
import p038e2.C0946b;
import p043f.C0988e;
import p046f2.InterfaceC0994a;
import p078j2.C1189d;
import p132p6.C1749a;
import p150s1.C1868e;
import p179w2.C2026b;
import p186x2.C2073a;
import p186x2.C2074b;
import p190y.AbstractC2085c;

/* loaded from: classes.dex */
public class SystemFragment extends BaseFragment implements InterfaceC0994a {

    /* renamed from: h */
    public TextView f831h;

    /* renamed from: i */
    public RelativeLayout f832i;

    /* renamed from: k */
    public RecyclerView f834k;

    /* renamed from: l */
    public SystemAdapter f835l;

    /* renamed from: n */
    public LinearLayoutManager f837n;

    /* renamed from: o */
    public int f838o;

    /* renamed from: p */
    public int f839p;

    /* renamed from: q */
    public boolean f840q;

    /* renamed from: r */
    public boolean f841r;

    /* renamed from: j */
    public String[] f833j = {C2026b.m2379b(R.string.about_us), C2026b.m2379b(R.string.audio_track_switch), C2026b.m2379b(R.string.sharpness_switch), C2026b.m2379b(R.string.version_update), C2026b.m2379b(R.string.device_check)};

    /* renamed from: m */
    public List<C0946b> f836m = new ArrayList();

    /* renamed from: s */
    public int f842s = 0;

    /* renamed from: t */
    public int f843t = 2;

    /* renamed from: com.cctv.tv.mvp.ui.fragment.SystemFragment$a */
    public class C0615a extends RecyclerView.OnScrollListener {
        public C0615a() {
        }

        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i7) {
            super.onScrollStateChanged(recyclerView, i7);
        }

        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i7, int i8) {
            super.onScrolled(recyclerView, i7, i8);
            SystemFragment systemFragment = SystemFragment.this;
            if (!systemFragment.f840q) {
                if (systemFragment.f841r) {
                    systemFragment.f841r = false;
                    int iFindFirstVisibleItemPosition = systemFragment.f837n.findFirstVisibleItemPosition();
                    SystemFragment systemFragment2 = SystemFragment.this;
                    View childAt = systemFragment2.f834k.getChildAt(systemFragment2.f842s - iFindFirstVisibleItemPosition);
                    if (childAt != null) {
                        childAt.requestFocus();
                        return;
                    }
                    return;
                }
                return;
            }
            systemFragment.f840q = false;
            int iFindFirstVisibleItemPosition2 = systemFragment.f842s - systemFragment.f837n.findFirstVisibleItemPosition();
            if (iFindFirstVisibleItemPosition2 < 0 || iFindFirstVisibleItemPosition2 >= SystemFragment.this.f834k.getChildCount()) {
                return;
            }
            SystemFragment.this.f834k.scrollBy(SystemFragment.this.f834k.getChildAt(iFindFirstVisibleItemPosition2).getLeft(), 0);
            int iFindFirstVisibleItemPosition3 = SystemFragment.this.f837n.findFirstVisibleItemPosition();
            SystemFragment systemFragment3 = SystemFragment.this;
            View childAt2 = systemFragment3.f834k.getChildAt(systemFragment3.f842s - iFindFirstVisibleItemPosition3);
            if (childAt2 != null) {
                childAt2.requestFocus();
            }
        }
    }

    @Override // p046f2.InterfaceC0994a
    /* renamed from: b */
    public void mo480b(View view, boolean z6, int i7) {
        if (this.f836m == null || !z6) {
            return;
        }
        if (i7 == 1) {
            m503j(this.f839p + 1);
        } else if (i7 == r1.size() - 2) {
            m503j((this.f836m.size() - 2) - this.f839p);
        }
    }

    @Override // p046f2.InterfaceC0994a
    /* renamed from: c */
    public void mo481c(int i7) {
        this.f843t = i7;
        if (C1189d.m1405g(MyApplication.f561e)) {
            if (i7 == 0) {
                C0580b.m417c("ABOUTUSS", getClass().getSimpleName());
                C0988e.m971O(getActivity().getSupportFragmentManager(), "ABOUT_US");
                return;
            }
            if (i7 == 1) {
                C0580b.m417c("AUDIO_TRACK_SWITCH", getClass().getSimpleName());
                C0988e.m971O(getActivity().getSupportFragmentManager(), "AUDIO_TRACK_SWITCH");
                return;
            } else if (i7 == 2) {
                C0580b.m417c("SHARPNESSSWITCH", getClass().getSimpleName());
                C0988e.m971O(getActivity().getSupportFragmentManager(), "SHARPNESS_SWITCH");
                return;
            } else {
                if (i7 != 3) {
                    return;
                }
                C0580b.m417c("DEVICE_CHECK", getClass().getSimpleName());
                C0988e.m971O(getActivity().getSupportFragmentManager(), "DEVICE_CHECK");
                return;
            }
        }
        if (i7 == 0) {
            C0580b.m417c("ABOUTUSS", getClass().getSimpleName());
            C0988e.m971O(getActivity().getSupportFragmentManager(), "ABOUT_US");
            return;
        }
        if (i7 == 1) {
            C0580b.m417c("AUDIO_TRACK_SWITCH", getClass().getSimpleName());
            C0988e.m971O(getActivity().getSupportFragmentManager(), "AUDIO_TRACK_SWITCH");
            return;
        }
        if (i7 == 2) {
            C0580b.m417c("SHARPNESSSWITCH", getClass().getSimpleName());
            C0988e.m971O(getActivity().getSupportFragmentManager(), "SHARPNESS_SWITCH");
            return;
        }
        if (i7 != 3) {
            if (i7 != 4) {
                return;
            }
            C0580b.m417c("DEVICE_CHECK", getClass().getSimpleName());
            C0988e.m971O(getActivity().getSupportFragmentManager(), "DEVICE_CHECK");
            return;
        }
        C0580b.m417c("UPDATE", getClass().getSimpleName());
        if (!C1189d.m1401c() && !C1189d.m1400b()) {
            C1868e.m2138a(getActivity().getSupportFragmentManager(), false);
            return;
        }
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            C0988e.m971O(getActivity().getSupportFragmentManager(), "VERSION_UPDATE_FRAGMENT");
            VersionUpdateFragment versionUpdateFragment = (VersionUpdateFragment) getActivity().getSupportFragmentManager().findFragmentByTag("VERSION_UPDATE_FRAGMENT");
            if (versionUpdateFragment != null) {
                versionUpdateFragment.m505j(1, null);
            }
            mainActivity.m467j(true);
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_system;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
        this.f831h.setText(C2026b.m2379b(R.string.system_settings));
        this.f832i.setBackgroundResource(R.drawable.left_four);
        if (C1189d.m1405g(MyApplication.f561e)) {
            this.f833j = new String[]{C2026b.m2379b(R.string.about_us), C2026b.m2379b(R.string.audio_track_switch), C2026b.m2379b(R.string.sharpness_switch), C2026b.m2379b(R.string.device_check)};
        }
        this.f839p = this.f833j.length * 2;
        for (int i7 = 0; i7 < 5; i7++) {
            int i8 = 0;
            while (true) {
                String[] strArr = this.f833j;
                if (i8 < strArr.length) {
                    C0946b c0946b = new C0946b();
                    c0946b.f1710a = strArr[i8];
                    c0946b.f1711b = i8;
                    this.f836m.add(c0946b);
                    i8++;
                }
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
        this.f837n = linearLayoutManager;
        this.f834k.setLayoutManager(linearLayoutManager);
        this.f834k.addItemDecoration(new MyItemDecoration(getContext(), 0, C0988e.m981g(getContext(), -23.0f)));
        SystemAdapter systemAdapter = new SystemAdapter(this, this.f834k, this.f836m);
        this.f835l = systemAdapter;
        this.f834k.setAdapter(systemAdapter);
        this.f838o = C2074b.m2495r() / 3;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f834k.getLayoutParams();
        layoutParams.topMargin = (this.f838o * 126) / 241;
        this.f834k.setLayoutParams(layoutParams);
        m504k(this.f843t);
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f831h = (TextView) this.f577f.findViewById(R.id.top_name);
        this.f834k = (RecyclerView) this.f577f.findViewById(R.id.recycler_system);
        this.f832i = (RelativeLayout) this.f577f.findViewById(R.id.rl_left_bg);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
        this.f835l.f713c = this;
        this.f834k.addOnScrollListener(new C0615a());
    }

    /* renamed from: j */
    public final void m503j(int i7) {
        this.f842s = i7;
        int iFindFirstVisibleItemPosition = this.f837n.findFirstVisibleItemPosition();
        int iFindLastVisibleItemPosition = this.f837n.findLastVisibleItemPosition();
        if (i7 < iFindFirstVisibleItemPosition) {
            this.f834k.scrollToPosition(i7);
            this.f841r = true;
        } else if (i7 == iFindFirstVisibleItemPosition) {
            this.f834k.getChildAt(this.f842s - iFindFirstVisibleItemPosition).requestFocus();
        } else if (i7 <= iFindLastVisibleItemPosition) {
            m503j(this.f842s + this.f833j.length);
        } else {
            this.f834k.scrollToPosition(i7);
            this.f840q = true;
        }
    }

    /* renamed from: k */
    public final void m504k(int i7) {
        int i8 = this.f839p + i7;
        if (this.f834k == null || i8 <= 0) {
            return;
        }
        C2073a.m2459d("index =" + i7);
        m503j(i8);
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        if (!z6) {
            m504k(this.f843t);
            C1749a.m1913b(getActivity(), this.f577f);
        }
        super.onHiddenChanged(z6);
    }
}
