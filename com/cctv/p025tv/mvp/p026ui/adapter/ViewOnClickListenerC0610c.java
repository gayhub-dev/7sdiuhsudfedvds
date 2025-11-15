package com.cctv.p025tv.mvp.p026ui.adapter;

import android.view.View;
import p038e2.C0946b;

/* compiled from: SystemAdapter.java */
/* renamed from: com.cctv.tv.mvp.ui.adapter.c */
/* loaded from: classes.dex */
public class ViewOnClickListenerC0610c implements View.OnClickListener {

    /* renamed from: e */
    public final /* synthetic */ C0946b f726e;

    /* renamed from: f */
    public final /* synthetic */ SystemAdapter f727f;

    public ViewOnClickListenerC0610c(SystemAdapter systemAdapter, C0946b c0946b) {
        this.f727f = systemAdapter;
        this.f726e = c0946b;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f727f.f713c.mo481c(this.f726e.f1711b);
    }
}
