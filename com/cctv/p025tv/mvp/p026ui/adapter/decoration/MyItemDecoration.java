package com.cctv.p025tv.mvp.p026ui.adapter.decoration;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/* loaded from: classes.dex */
public class MyItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: d */
    public static final int[] f731d = {R.attr.listDivider};

    /* renamed from: a */
    public Drawable f732a;

    /* renamed from: b */
    public int f733b;

    /* renamed from: c */
    public int f734c;

    public MyItemDecoration(Context context, int i7, int i8) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(f731d);
        this.f732a = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        this.f734c = i8;
        if (i7 != 0 && i7 != 1) {
            this.f733b = 1;
        }
        this.f733b = i7;
    }

    @Override // android.support.v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.f733b == 0) {
            if (recyclerView.getChildAdapterPosition(view) < state.getItemCount() - 1) {
                rect.set(0, 0, this.f732a.getIntrinsicWidth() + this.f734c, 0);
                return;
            } else {
                rect.set(0, 0, 0, 0);
                return;
            }
        }
        if (recyclerView.getChildAdapterPosition(view) < state.getItemCount() - 1) {
            rect.set(0, 0, 0, this.f732a.getIntrinsicHeight() + this.f734c);
        } else {
            rect.set(0, 0, 0, 0);
        }
    }
}
