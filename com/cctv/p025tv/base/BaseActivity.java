package com.cctv.p025tv.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import p186x2.C2073a;
import p190y.AbstractC2085c;

/* loaded from: classes.dex */
public abstract class BaseActivity<V, T extends AbstractC2085c> extends AppCompatActivity {

    /* renamed from: e */
    public T f575e;

    /* renamed from: c */
    public abstract T mo402c();

    /* renamed from: d */
    public abstract int mo403d();

    /* renamed from: e */
    public abstract void mo404e();

    /* renamed from: f */
    public abstract void mo405f();

    /* renamed from: g */
    public abstract void mo406g();

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.ref.WeakReference, java.util.Queue<T extends y.k>] */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        C2073a.m2459d("onCreate");
        getWindow().addFlags(128);
        setContentView(mo403d());
        T t6 = (T) mo402c();
        this.f575e = t6;
        if (t6 != null) {
            t6.f6179a = new WeakReference(this);
        }
        mo405f();
        mo404e();
        mo406g();
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Reference reference;
        super.onDestroy();
        C2073a.m2459d("onDestroy");
        T t6 = this.f575e;
        if (t6 == null || (reference = (Reference) t6.f6179a) == null) {
            return;
        }
        reference.clear();
        t6.f6179a = null;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        C2073a.m2459d("onPause");
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        C2073a.m2459d("onRestart");
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        C2073a.m2459d("onResume");
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        C2073a.m2459d("onStop");
    }
}
