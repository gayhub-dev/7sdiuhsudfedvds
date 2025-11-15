package com.cctv.p025tv.mvp.p026ui.adapter;

import android.view.View;
import p038e2.C0946b;

/* compiled from: ChangeNameAdapter.java */
/* renamed from: com.cctv.tv.mvp.ui.adapter.a */
/* loaded from: classes.dex */
public class ViewOnClickListenerC0608a implements View.OnClickListener {

    /* renamed from: e */
    public final /* synthetic */ C0946b f721e;

    /* renamed from: f */
    public final /* synthetic */ ChangeNameAdapter f722f;

    public ViewOnClickListenerC0608a(ChangeNameAdapter changeNameAdapter, C0946b c0946b) {
        this.f722f = changeNameAdapter;
        this.f721e = c0946b;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f722f.f699c.mo481c(this.f721e.f1711b);
    }
}
