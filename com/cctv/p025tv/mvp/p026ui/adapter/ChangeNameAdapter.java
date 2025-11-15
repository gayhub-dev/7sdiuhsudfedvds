package com.cctv.p025tv.mvp.p026ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.base.BaseFragment;
import java.util.List;
import p030d2.C0867a;
import p038e2.C0946b;
import p046f2.InterfaceC0994a;
import p186x2.C2074b;

/* loaded from: classes.dex */
public class ChangeNameAdapter extends RecyclerView.Adapter<C0605a> {

    /* renamed from: a */
    public List<C0946b> f697a;

    /* renamed from: b */
    public Context f698b;

    /* renamed from: c */
    public InterfaceC0994a f699c;

    /* renamed from: d */
    public RecyclerView f700d;

    /* renamed from: e */
    public final int f701e;

    /* renamed from: f */
    public final int f702f;

    /* renamed from: com.cctv.tv.mvp.ui.adapter.ChangeNameAdapter$a */
    public class C0605a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public TextView f703a;

        /* renamed from: b */
        public RelativeLayout f704b;

        /* renamed from: c */
        public RelativeLayout f705c;

        /* renamed from: d */
        public ImageView f706d;

        public C0605a(@NonNull ChangeNameAdapter changeNameAdapter, View view) {
            super(view);
            this.f703a = (TextView) view.findViewById(R.id.tv_item_change_name);
            this.f704b = (RelativeLayout) view.findViewById(R.id.rl_item_change_name_bg);
            this.f705c = (RelativeLayout) view.findViewById(R.id.rl_item_change_name);
            this.f706d = (ImageView) view.findViewById(R.id.iv_item_change_name);
        }
    }

    public ChangeNameAdapter(BaseFragment baseFragment, RecyclerView recyclerView, List<C0946b> list) {
        this.f697a = list;
        this.f698b = baseFragment.getContext();
        this.f700d = recyclerView;
        int iM2495r = C2074b.m2495r() / 3;
        this.f701e = iM2495r;
        this.f702f = (iM2495r * 255) / 317;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f697a.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    @SuppressLint({RecyclerView.TAG})
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i7) {
        C0605a c0605a = (C0605a) viewHolder;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) c0605a.f704b.getLayoutParams();
        int i8 = this.f701e;
        ((ViewGroup.MarginLayoutParams) layoutParams).width = i8;
        ((ViewGroup.MarginLayoutParams) layoutParams).height = (i8 * 255) / 317;
        c0605a.f704b.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) c0605a.f705c.getLayoutParams();
        int i9 = this.f702f;
        layoutParams2.width = i9;
        layoutParams2.height = (i9 * 255) / 317;
        c0605a.f705c.setLayoutParams(layoutParams2);
        C0946b c0946b = this.f697a.get(i7);
        c0605a.f703a.setText(this.f697a.get(i7).f1710a);
        c0605a.f705c.getBackground().setAlpha(229);
        int i10 = c0946b.f1711b;
        if (i10 == 0) {
            c0605a.f706d.setImageResource(R.drawable.icon_ystpzs_hl);
            C0867a.m675a(this.f698b, R.color.main_title, c0605a.f706d);
        } else if (i10 == 1) {
            c0605a.f706d.setImageResource(R.drawable.icon_living_hl);
            C0867a.m675a(this.f698b, R.color.main_title, c0605a.f706d);
        } else if (i10 == 2) {
            c0605a.f706d.setImageResource(R.drawable.icon_bedroom_hl);
            C0867a.m675a(this.f698b, R.color.main_title, c0605a.f706d);
        } else if (i10 == 3) {
            c0605a.f706d.setImageResource(R.drawable.icon_my_hl);
            C0867a.m675a(this.f698b, R.color.main_title, c0605a.f706d);
        } else if (i10 == 4) {
            c0605a.f706d.setImageResource(R.drawable.icon_modify_hl);
            C0867a.m675a(this.f698b, R.color.main_title, c0605a.f706d);
        }
        c0605a.f705c.setOnClickListener(new ViewOnClickListenerC0608a(this, c0946b));
        c0605a.f705c.setOnFocusChangeListener(new ViewOnFocusChangeListenerC0609b(this, c0605a, i7));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i7) {
        return new C0605a(this, LayoutInflater.from(this.f698b).inflate(R.layout.item_recycler_change_name, viewGroup, false));
    }
}
