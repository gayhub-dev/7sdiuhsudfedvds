package com.bumptech.glide.manager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import java.util.HashSet;
import p084k0.C1215a;
import p084k0.InterfaceC1224j;
import p141r.C1814i;
import p141r.ComponentCallbacks2C1808c;

/* loaded from: classes.dex */
public class SupportRequestManagerFragment extends Fragment {

    /* renamed from: e */
    public final C1215a f438e;

    /* renamed from: f */
    public final InterfaceC1224j f439f;

    /* renamed from: g */
    public final HashSet<SupportRequestManagerFragment> f440g;

    /* renamed from: h */
    @Nullable
    public SupportRequestManagerFragment f441h;

    /* renamed from: i */
    @Nullable
    public C1814i f442i;

    /* renamed from: j */
    @Nullable
    public Fragment f443j;

    /* renamed from: com.bumptech.glide.manager.SupportRequestManagerFragment$a */
    public class C0563a implements InterfaceC1224j {
        public C0563a() {
        }

        public String toString() {
            return super.toString() + "{fragment=" + SupportRequestManagerFragment.this + "}";
        }
    }

    public SupportRequestManagerFragment() {
        C1215a c1215a = new C1215a();
        this.f439f = new C0563a();
        this.f440g = new HashSet<>();
        this.f438e = c1215a;
    }

    /* renamed from: e */
    public final void m346e(FragmentActivity fragmentActivity) {
        m347f();
        SupportRequestManagerFragment supportRequestManagerFragmentM1451c = ComponentCallbacks2C1808c.m2022c(fragmentActivity).f5218j.m1451c(fragmentActivity.getSupportFragmentManager(), null);
        this.f441h = supportRequestManagerFragmentM1451c;
        if (supportRequestManagerFragmentM1451c != this) {
            supportRequestManagerFragmentM1451c.f440g.add(this);
        }
    }

    /* renamed from: f */
    public final void m347f() {
        SupportRequestManagerFragment supportRequestManagerFragment = this.f441h;
        if (supportRequestManagerFragment != null) {
            supportRequestManagerFragment.f440g.remove(this);
            this.f441h = null;
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            m346e(getActivity());
        } catch (IllegalStateException unused) {
            Log.isLoggable("SupportRMFragment", 5);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f438e.m1443b();
        m347f();
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.f443j = null;
        m347f();
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        this.f438e.m1444d();
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        this.f438e.m1445e();
    }

    @Override // android.support.v4.app.Fragment
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{parent=");
        Fragment parentFragment = getParentFragment();
        if (parentFragment == null) {
            parentFragment = this.f443j;
        }
        sb.append(parentFragment);
        sb.append("}");
        return sb.toString();
    }
}
