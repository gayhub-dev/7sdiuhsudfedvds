package com.cctv.p025tv.mvp.p026ui.fragment;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.base.BaseFragment;
import com.cctv.p025tv.module.collect.C0580b;
import com.cctv.p025tv.mvp.p026ui.adapter.ChangeNameAdapter;
import com.cctv.p025tv.mvp.p026ui.adapter.decoration.MyItemDecoration;
import com.tencent.mars.xlog.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import p038e2.C0946b;
import p043f.C0988e;
import p046f2.InterfaceC0994a;
import p078j2.C1190e;
import p078j2.C1191f;
import p086k2.C1231b;
import p101m1.C1458b;
import p118o2.C1581b;
import p132p6.C1749a;
import p179w2.C2026b;
import p186x2.C2073a;
import p186x2.C2074b;
import p190y.AbstractC2085c;

/* loaded from: classes.dex */
public class ChangeNameFragment extends BaseFragment implements View.OnFocusChangeListener, View.OnClickListener, InterfaceC0994a {

    /* renamed from: h */
    public TextView f743h;

    /* renamed from: i */
    public TextView f744i;

    /* renamed from: j */
    public RelativeLayout f745j;

    /* renamed from: l */
    public RecyclerView f747l;

    /* renamed from: m */
    public ChangeNameAdapter f748m;

    /* renamed from: o */
    public LinearLayoutManager f750o;

    /* renamed from: p */
    public int f751p;

    /* renamed from: r */
    public boolean f753r;

    /* renamed from: s */
    public boolean f754s;

    /* renamed from: k */
    public String[] f746k = {C2026b.m2379b(R.string.dlan_player_name), C2026b.m2379b(R.string.living_room), C2026b.m2379b(R.string.bedroom), C2026b.m2379b(R.string.my_tpzs), C2026b.m2379b(R.string.modify_name)};

    /* renamed from: n */
    public List<C0946b> f749n = new ArrayList();

    /* renamed from: q */
    public int f752q = 10;

    /* renamed from: t */
    public int f755t = 0;

    /* renamed from: com.cctv.tv.mvp.ui.fragment.ChangeNameFragment$a */
    public class C0612a extends RecyclerView.OnScrollListener {
        public C0612a() {
        }

        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i7) {
            super.onScrollStateChanged(recyclerView, i7);
        }

        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i7, int i8) {
            super.onScrolled(recyclerView, i7, i8);
            ChangeNameFragment changeNameFragment = ChangeNameFragment.this;
            if (!changeNameFragment.f753r) {
                if (changeNameFragment.f754s) {
                    changeNameFragment.f754s = false;
                    int iFindFirstVisibleItemPosition = changeNameFragment.f750o.findFirstVisibleItemPosition();
                    ChangeNameFragment changeNameFragment2 = ChangeNameFragment.this;
                    View childAt = changeNameFragment2.f747l.getChildAt(changeNameFragment2.f755t - iFindFirstVisibleItemPosition);
                    if (childAt != null) {
                        childAt.requestFocus();
                        return;
                    }
                    return;
                }
                return;
            }
            changeNameFragment.f753r = false;
            int iFindFirstVisibleItemPosition2 = changeNameFragment.f755t - changeNameFragment.f750o.findFirstVisibleItemPosition();
            if (iFindFirstVisibleItemPosition2 < 0 || iFindFirstVisibleItemPosition2 >= ChangeNameFragment.this.f747l.getChildCount()) {
                return;
            }
            ChangeNameFragment.this.f747l.scrollBy(ChangeNameFragment.this.f747l.getChildAt(iFindFirstVisibleItemPosition2).getLeft(), 0);
            int iFindFirstVisibleItemPosition3 = ChangeNameFragment.this.f750o.findFirstVisibleItemPosition();
            ChangeNameFragment changeNameFragment3 = ChangeNameFragment.this;
            View childAt2 = changeNameFragment3.f747l.getChildAt(changeNameFragment3.f755t - iFindFirstVisibleItemPosition3);
            if (childAt2 != null) {
                childAt2.requestFocus();
            }
        }
    }

    @Override // p046f2.InterfaceC0994a
    /* renamed from: b */
    public void mo480b(View view, boolean z6, int i7) {
        if (this.f749n == null || !z6) {
            return;
        }
        if (i7 == 1) {
            m482j(this.f752q + 1);
        } else if (i7 == r1.size() - 2) {
            m482j((this.f749n.size() - 2) - this.f752q);
        }
    }

    @Override // p046f2.InterfaceC0994a
    /* renamed from: c */
    public void mo481c(int i7) {
        if (i7 == 0) {
            C0580b.m417c("NAME_YSTPZS", getClass().getSimpleName());
            this.f744i.setText(C2026b.m2379b(R.string.dlan_player_name));
            return;
        }
        if (i7 == 1) {
            C0580b.m417c("NAME_LIVING", getClass().getSimpleName());
            this.f744i.setText(C2026b.m2379b(R.string.living_room));
        } else if (i7 == 2) {
            C0580b.m417c("NAME_BEDROOM", getClass().getSimpleName());
            this.f744i.setText(C2026b.m2379b(R.string.bedroom));
        } else if (i7 == 3) {
            C0580b.m417c("NAME_MY_TPZS", getClass().getSimpleName());
            this.f744i.setText(C2026b.m2379b(R.string.my_tpzs));
        } else {
            if (i7 != 4) {
                return;
            }
            C0580b.m417c("NAME_CUSTOM", getClass().getSimpleName());
            C0988e.m971O(getActivity().getSupportFragmentManager(), "DLNA_MODIFY_FRAGMENT");
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
        return R.layout.fragment_change_name;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
        this.f743h.setText(C2026b.m2379b(R.string.device_name_text));
        this.f744i.setText(C1581b.m1837f());
        this.f745j.setBackgroundResource(R.drawable.left_four);
        for (int i7 = 0; i7 < 5; i7++) {
            int i8 = 0;
            while (true) {
                String[] strArr = this.f746k;
                if (i8 < strArr.length) {
                    C0946b c0946b = new C0946b();
                    c0946b.f1710a = strArr[i8];
                    c0946b.f1711b = i8;
                    this.f749n.add(c0946b);
                    i8++;
                }
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
        this.f750o = linearLayoutManager;
        this.f747l.setLayoutManager(linearLayoutManager);
        this.f747l.addItemDecoration(new MyItemDecoration(getContext(), 0, C0988e.m981g(getContext(), -23.0f)));
        ChangeNameAdapter changeNameAdapter = new ChangeNameAdapter(this, this.f747l, this.f749n);
        this.f748m = changeNameAdapter;
        this.f747l.setAdapter(changeNameAdapter);
        this.f751p = C2074b.m2495r() / 3;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f747l.getLayoutParams();
        layoutParams.topMargin = (this.f751p * 126) / 241;
        this.f747l.setLayoutParams(layoutParams);
        m483k(this.f744i.getText().toString().trim());
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f743h = (TextView) this.f577f.findViewById(R.id.top_name);
        this.f744i = (TextView) this.f577f.findViewById(R.id.device_name_now);
        this.f747l = (RecyclerView) this.f577f.findViewById(R.id.recycler_change_name);
        this.f745j = (RelativeLayout) this.f577f.findViewById(R.id.rl_left_bg);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
        this.f748m.f699c = this;
        this.f747l.addOnScrollListener(new C0612a());
    }

    /* renamed from: j */
    public final void m482j(int i7) {
        this.f755t = i7;
        int iFindFirstVisibleItemPosition = this.f750o.findFirstVisibleItemPosition();
        int iFindLastVisibleItemPosition = this.f750o.findLastVisibleItemPosition();
        if (i7 < iFindFirstVisibleItemPosition) {
            this.f747l.scrollToPosition(i7);
            this.f754s = true;
        } else if (i7 == iFindFirstVisibleItemPosition) {
            this.f747l.getChildAt(this.f755t - iFindFirstVisibleItemPosition).requestFocus();
        } else if (i7 <= iFindLastVisibleItemPosition) {
            m482j(this.f755t + this.f746k.length);
        } else {
            this.f747l.scrollToPosition(i7);
            this.f753r = true;
        }
    }

    /* renamed from: k */
    public final void m483k(String str) {
        C1458b.m1642a("dlnaName =", str);
        if (str.equals(this.f746k[0])) {
            m484l(0);
            return;
        }
        if (str.equals(this.f746k[1])) {
            m484l(1);
            return;
        }
        if (str.equals(this.f746k[2])) {
            m484l(2);
        } else if (str.equals(this.f746k[3])) {
            m484l(3);
        } else {
            m484l(4);
        }
    }

    /* renamed from: l */
    public final void m484l(int i7) {
        int i8 = this.f752q + i7;
        if (this.f747l == null || i8 <= 0) {
            return;
        }
        C2073a.m2459d("index =" + i7);
        m482j(i8);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        view.getId();
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
        TextView textView = this.f744i;
        String strTrim = (textView == null || TextUtils.isEmpty(textView.getText())) ? "" : this.f744i.getText().toString().trim();
        if (!z6) {
            String string = PreferenceManager.getDefaultSharedPreferences(MyApplication.f561e).getString("dlna_name", "");
            if (string.equals(C1581b.m1837f())) {
                this.f744i.setText(C1581b.m1837f());
            } else {
                this.f744i.setText(string);
            }
            m483k(this.f744i.getText().toString().trim());
            C1749a.m1913b(getActivity(), this.f577f);
        } else if (!C1581b.m1837f().equals(strTrim)) {
            Objects.requireNonNull(C1581b.m1836e());
            SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(C1231b.f2761c).edit();
            editorEdit.putString("dlan_player_name", strTrim);
            editorEdit.commit();
            C1191f.m1410d();
            Log.m655i("XLog_DLNA ", "ChangeNameFragment 投屏服务名字改为：" + strTrim);
            C1190e.m1406a(MyApplication.f561e, strTrim);
        }
        super.onHiddenChanged(z6);
    }
}
