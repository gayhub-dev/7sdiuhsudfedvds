package com.bumptech.glide.manager;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.HashSet;
import p084k0.C1215a;
import p084k0.InterfaceC1224j;
import p141r.C1814i;
import p141r.ComponentCallbacks2C1808c;

/* loaded from: classes.dex */
public class RequestManagerFragment extends Fragment {

    /* renamed from: e */
    public final C1215a f431e;

    /* renamed from: f */
    public final InterfaceC1224j f432f;

    /* renamed from: g */
    public final HashSet<RequestManagerFragment> f433g;

    /* renamed from: h */
    @Nullable
    public C1814i f434h;

    /* renamed from: i */
    @Nullable
    public RequestManagerFragment f435i;

    /* renamed from: j */
    @Nullable
    public Fragment f436j;

    /* renamed from: com.bumptech.glide.manager.RequestManagerFragment$a */
    public class C0562a implements InterfaceC1224j {
        public C0562a() {
        }

        public String toString() {
            return super.toString() + "{fragment=" + RequestManagerFragment.this + "}";
        }
    }

    public RequestManagerFragment() {
        C1215a c1215a = new C1215a();
        this.f432f = new C0562a();
        this.f433g = new HashSet<>();
        this.f431e = c1215a;
    }

    /* renamed from: a */
    public final void m344a(Activity activity) {
        m345b();
        RequestManagerFragment requestManagerFragmentM1450b = ComponentCallbacks2C1808c.m2022c(activity).f5218j.m1450b(activity.getFragmentManager(), null);
        this.f435i = requestManagerFragmentM1450b;
        if (requestManagerFragmentM1450b != this) {
            requestManagerFragmentM1450b.f433g.add(this);
        }
    }

    /* renamed from: b */
    public final void m345b() {
        RequestManagerFragment requestManagerFragment = this.f435i;
        if (requestManagerFragment != null) {
            requestManagerFragment.f433g.remove(this);
            this.f435i = null;
        }
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            m344a(activity);
        } catch (IllegalStateException unused) {
            Log.isLoggable("RMFragment", 5);
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f431e.m1443b();
        m345b();
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        m345b();
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.f431e.m1444d();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.f431e.m1445e();
    }

    @Override // android.app.Fragment
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{parent=");
        Fragment parentFragment = getParentFragment();
        if (parentFragment == null) {
            parentFragment = this.f436j;
        }
        sb.append(parentFragment);
        sb.append("}");
        return sb.toString();
    }
}
