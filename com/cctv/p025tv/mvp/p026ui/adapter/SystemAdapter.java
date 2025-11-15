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
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.base.BaseFragment;
import java.util.List;
import p030d2.C0867a;
import p038e2.C0946b;
import p046f2.InterfaceC0994a;
import p078j2.C1189d;
import p186x2.C2074b;

/* loaded from: classes.dex */
public class SystemAdapter extends RecyclerView.Adapter<C0607a> {

    /* renamed from: a */
    public List<C0946b> f711a;

    /* renamed from: b */
    public Context f712b;

    /* renamed from: c */
    public InterfaceC0994a f713c;

    /* renamed from: d */
    public RecyclerView f714d;

    /* renamed from: e */
    public final int f715e;

    /* renamed from: f */
    public final int f716f;

    /* renamed from: com.cctv.tv.mvp.ui.adapter.SystemAdapter$a */
    public class C0607a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public TextView f717a;

        /* renamed from: b */
        public RelativeLayout f718b;

        /* renamed from: c */
        public RelativeLayout f719c;

        /* renamed from: d */
        public ImageView f720d;

        public C0607a(@NonNull SystemAdapter systemAdapter, View view) {
            super(view);
            this.f717a = (TextView) view.findViewById(R.id.tv_item_change_name);
            this.f718b = (RelativeLayout) view.findViewById(R.id.rl_item_change_name_bg);
            this.f719c = (RelativeLayout) view.findViewById(R.id.rl_item_change_name);
            this.f720d = (ImageView) view.findViewById(R.id.iv_item_change_name);
        }
    }

    public SystemAdapter(BaseFragment baseFragment, RecyclerView recyclerView, List<C0946b> list) {
        this.f711a = list;
        this.f712b = baseFragment.getContext();
        this.f714d = recyclerView;
        int iM2495r = C2074b.m2495r() / 3;
        this.f715e = iM2495r;
        this.f716f = (iM2495r * 255) / 317;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f711a.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    @SuppressLint({RecyclerView.TAG})
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i7) {
        C0607a c0607a = (C0607a) viewHolder;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) c0607a.f718b.getLayoutParams();
        int i8 = this.f715e;
        ((ViewGroup.MarginLayoutParams) layoutParams).width = i8;
        ((ViewGroup.MarginLayoutParams) layoutParams).height = (i8 * 255) / 317;
        c0607a.f718b.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) c0607a.f719c.getLayoutParams();
        int i9 = this.f716f;
        layoutParams2.width = i9;
        layoutParams2.height = (i9 * 255) / 317;
        c0607a.f719c.setLayoutParams(layoutParams2);
        C0946b c0946b = this.f711a.get(i7);
        c0607a.f717a.setText(this.f711a.get(i7).f1710a);
        c0607a.f719c.getBackground().setAlpha(229);
        if (C1189d.m1405g(MyApplication.f561e)) {
            int i10 = c0946b.f1711b;
            if (i10 == 0) {
                c0607a.f720d.setImageResource(R.drawable.about_us_hl);
                C0867a.m675a(this.f712b, R.color.main_title, c0607a.f720d);
            } else if (i10 == 1) {
                c0607a.f720d.setImageResource(R.drawable.audio_speed_hl);
                C0867a.m675a(this.f712b, R.color.main_title, c0607a.f720d);
            } else if (i10 == 2) {
                c0607a.f720d.setImageResource(R.drawable.sharpness_switch_hl);
                C0867a.m675a(this.f712b, R.color.main_title, c0607a.f720d);
            } else if (i10 == 3) {
                c0607a.f720d.setImageResource(R.drawable.device_check_hl);
                C0867a.m675a(this.f712b, R.color.main_title, c0607a.f720d);
            }
        } else {
            int i11 = c0946b.f1711b;
            if (i11 == 0) {
                c0607a.f720d.setImageResource(R.drawable.about_us_hl);
                C0867a.m675a(this.f712b, R.color.main_title, c0607a.f720d);
            } else if (i11 == 1) {
                c0607a.f720d.setImageResource(R.drawable.audio_speed_hl);
                C0867a.m675a(this.f712b, R.color.main_title, c0607a.f720d);
            } else if (i11 == 2) {
                c0607a.f720d.setImageResource(R.drawable.sharpness_switch_hl);
                C0867a.m675a(this.f712b, R.color.main_title, c0607a.f720d);
            } else if (i11 == 3) {
                c0607a.f720d.setImageResource(R.drawable.check_version);
                C0867a.m675a(this.f712b, R.color.main_title, c0607a.f720d);
            } else if (i11 == 4) {
                c0607a.f720d.setImageResource(R.drawable.device_check_hl);
                C0867a.m675a(this.f712b, R.color.main_title, c0607a.f720d);
            }
        }
        c0607a.f719c.setOnClickListener(new ViewOnClickListenerC0610c(this, c0946b));
        c0607a.f719c.setOnFocusChangeListener(new ViewOnFocusChangeListenerC0611d(this, c0607a, i7));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i7) {
        return new C0607a(this, LayoutInflater.from(this.f712b).inflate(R.layout.item_recycler_change_name, viewGroup, false));
    }
}
