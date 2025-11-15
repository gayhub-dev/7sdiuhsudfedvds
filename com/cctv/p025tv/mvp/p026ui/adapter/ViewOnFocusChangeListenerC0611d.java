package com.cctv.p025tv.mvp.p026ui.adapter;

import android.graphics.ColorFilter;
import android.view.View;
import com.cctv.p025tv.R;
import com.cctv.p025tv.mvp.p026ui.adapter.SystemAdapter;
import java.util.Objects;
import p030d2.C0867a;
import p078j2.C1186a;

/* compiled from: SystemAdapter.java */
/* renamed from: com.cctv.tv.mvp.ui.adapter.d */
/* loaded from: classes.dex */
public class ViewOnFocusChangeListenerC0611d implements View.OnFocusChangeListener {

    /* renamed from: e */
    public final /* synthetic */ SystemAdapter.C0607a f728e;

    /* renamed from: f */
    public final /* synthetic */ int f729f;

    /* renamed from: g */
    public final /* synthetic */ SystemAdapter f730g;

    public ViewOnFocusChangeListenerC0611d(SystemAdapter systemAdapter, SystemAdapter.C0607a c0607a, int i7) {
        this.f730g = systemAdapter;
        this.f728e = c0607a;
        this.f729f = i7;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z6) {
        if (view != null && view.getId() == R.id.rl_item_change_name) {
            if (z6) {
                SystemAdapter systemAdapter = this.f730g;
                Objects.requireNonNull(systemAdapter);
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                systemAdapter.f714d.smoothScrollBy((iArr[0] - view.getLeft()) - systemAdapter.f715e, 0);
                this.f728e.f720d.getDrawable().setAlpha(229);
                this.f728e.f719c.getBackground().setAlpha(255);
                this.f728e.f720d.setColorFilter((ColorFilter) null);
                this.f728e.f717a.setTextColor(this.f730g.f712b.getResources().getColor(R.color.main_title_hl));
                C1186a.m1383c(this.f728e.f719c, Float.valueOf(1.25f));
            } else {
                this.f728e.f720d.getDrawable().setAlpha(255);
                this.f728e.f719c.getBackground().setAlpha(229);
                C0867a.m675a(this.f730g.f712b, R.color.main_title, this.f728e.f720d);
                this.f728e.f717a.setTextColor(this.f730g.f712b.getResources().getColor(R.color.main_title));
                C1186a.m1384d(this.f728e.f719c);
            }
            this.f730g.f713c.mo480b(view, z6, this.f729f);
        }
    }
}
