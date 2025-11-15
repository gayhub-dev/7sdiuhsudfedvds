package com.cctv.p025tv.mvp.p026ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cctv.p025tv.R;
import java.util.List;
import p038e2.C0945a;

/* loaded from: classes.dex */
public class DeviceInfoAdapter extends RecyclerView.Adapter<C0606a> {

    /* renamed from: a */
    public List<C0945a> f707a;

    /* renamed from: b */
    public Context f708b;

    /* renamed from: com.cctv.tv.mvp.ui.adapter.DeviceInfoAdapter$a */
    public class C0606a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public TextView f709a;

        /* renamed from: b */
        public TextView f710b;

        public C0606a(@NonNull DeviceInfoAdapter deviceInfoAdapter, View view) {
            super(view);
            this.f709a = (TextView) view.findViewById(R.id.tv_device_info_name);
            this.f710b = (TextView) view.findViewById(R.id.tv_device_info);
        }
    }

    public DeviceInfoAdapter(Context context, List<C0945a> list) {
        this.f707a = list;
        this.f708b = context;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<C0945a> list = this.f707a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    @SuppressLint({RecyclerView.TAG})
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i7) {
        C0606a c0606a = (C0606a) viewHolder;
        C0945a c0945a = this.f707a.get(i7);
        c0606a.f709a.setText(c0945a.f1708a);
        c0606a.f710b.setText(c0945a.f1709b);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i7) {
        return new C0606a(this, LayoutInflater.from(this.f708b).inflate(R.layout.item_device_info, viewGroup, false));
    }
}
