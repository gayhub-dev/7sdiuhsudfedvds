package com.cctv.p025tv.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cctv.p025tv.app.MyApplication;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import p186x2.C2073a;
import p190y.AbstractC2085c;

/* loaded from: classes.dex */
public abstract class BaseFragment<V, P extends AbstractC2085c> extends Fragment {

    /* renamed from: e */
    public P f576e;

    /* renamed from: f */
    public View f577f;

    /* renamed from: g */
    public InterfaceC0572a f578g;

    /* renamed from: com.cctv.tv.base.BaseFragment$a */
    public interface InterfaceC0572a {
    }

    /* renamed from: e */
    public abstract P mo407e();

    /* renamed from: f */
    public abstract int mo408f();

    /* renamed from: g */
    public abstract void mo409g();

    /* renamed from: h */
    public abstract void mo410h();

    /* renamed from: i */
    public abstract void mo411i();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        C2073a.m2459d(getClass().getSimpleName() + " onAttach");
        if (context instanceof InterfaceC0572a) {
            this.f578g = (InterfaceC0572a) context;
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.ref.WeakReference, java.util.Queue<T extends y.k>] */
    @Override // android.support.v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        C2073a.m2459d(getClass().getSimpleName() + " onCreate");
        P p7 = (P) mo407e();
        this.f576e = p7;
        if (p7 != null) {
            p7.f6179a = new WeakReference(this);
        }
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        C2073a.m2459d(getClass().getSimpleName() + " onCreateView");
        this.f577f = layoutInflater.inflate(mo408f(), viewGroup, false);
        mo410h();
        mo409g();
        mo411i();
        return this.f577f;
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        C2073a.m2459d(getClass().getSimpleName() + " onDestroy");
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroyView() {
        Reference reference;
        super.onDestroyView();
        C2073a.m2459d(getClass().getSimpleName() + " onDestroyView");
        P p7 = this.f576e;
        if (p7 == null || (reference = (Reference) p7.f6179a) == null) {
            return;
        }
        reference.clear();
        p7.f6179a = null;
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        C2073a.m2459d(getClass().getSimpleName() + " onDetach");
    }

    @Override // android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        super.onHiddenChanged(z6);
        C2073a.m2459d(getClass().getSimpleName() + " onHiddenChanged：" + z6);
        if (z6) {
            return;
        }
        MyApplication.f564h = getClass().getSimpleName();
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        C2073a.m2459d(getClass().getSimpleName() + " onPause");
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        C2073a.m2459d(getClass().getSimpleName() + " onResume");
        MyApplication.f564h = getClass().getSimpleName();
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        C2073a.m2459d(getClass().getSimpleName() + " onStart");
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        C2073a.m2459d(getClass().getSimpleName() + " onStop");
    }
}
