package com.cctv.p025tv.mvp.p026ui.adapter;

import android.graphics.ColorFilter;
import android.view.View;
import com.cctv.p025tv.R;
import com.cctv.p025tv.mvp.p026ui.adapter.ChangeNameAdapter;
import java.util.Objects;
import p030d2.C0867a;
import p078j2.C1186a;

/* compiled from: ChangeNameAdapter.java */
/* renamed from: com.cctv.tv.mvp.ui.adapter.b */
/* loaded from: classes.dex */
public class ViewOnFocusChangeListenerC0609b implements View.OnFocusChangeListener {

    /* renamed from: e */
    public final /* synthetic */ ChangeNameAdapter.C0605a f723e;

    /* renamed from: f */
    public final /* synthetic */ int f724f;

    /* renamed from: g */
    public final /* synthetic */ ChangeNameAdapter f725g;

    public ViewOnFocusChangeListenerC0609b(ChangeNameAdapter changeNameAdapter, ChangeNameAdapter.C0605a c0605a, int i7) {
        this.f725g = changeNameAdapter;
        this.f723e = c0605a;
        this.f724f = i7;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z6) {
        if (view != null && view.getId() == R.id.rl_item_change_name) {
            if (z6) {
                ChangeNameAdapter changeNameAdapter = this.f725g;
                Objects.requireNonNull(changeNameAdapter);
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                changeNameAdapter.f700d.smoothScrollBy((iArr[0] - view.getLeft()) - changeNameAdapter.f701e, 0);
                this.f723e.f706d.getDrawable().setAlpha(229);
                this.f723e.f705c.getBackground().setAlpha(255);
                this.f723e.f706d.setColorFilter((ColorFilter) null);
                this.f723e.f703a.setTextColor(this.f725g.f698b.getResources().getColor(R.color.main_title_hl));
                C1186a.m1383c(this.f723e.f705c, Float.valueOf(1.25f));
            } else {
                this.f723e.f706d.getDrawable().setAlpha(255);
                this.f723e.f705c.getBackground().setAlpha(229);
                C0867a.m675a(this.f725g.f698b, R.color.main_title, this.f723e.f706d);
                this.f723e.f703a.setTextColor(this.f725g.f698b.getResources().getColor(R.color.main_title));
                C1186a.m1384d(this.f723e.f705c);
            }
            this.f725g.f699c.mo480b(view, z6, this.f724f);
        }
    }
}
