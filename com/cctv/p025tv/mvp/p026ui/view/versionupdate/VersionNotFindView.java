package com.cctv.p025tv.mvp.p026ui.view.versionupdate;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.module.collect.C0580b;
import com.ctvit.appupdate.entity.AppUpdateEntity;
import p009b.C0413b;
import p043f.C0988e;
import p179w2.C2025a;

/* loaded from: classes.dex */
public class VersionNotFindView extends RelativeLayout implements View.OnClickListener {

    /* renamed from: e */
    public Button f924e;

    /* renamed from: f */
    public TextView f925f;

    /* renamed from: g */
    public TextView f926g;

    public VersionNotFindView(Context context) {
        this(context, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.enter_view) {
            return;
        }
        C0580b.m417c("UPDATE_CHECK_YES", "VersionUpdateFragment");
        C0988e.m971O(((AppCompatActivity) getContext()).getSupportFragmentManager(), "SYSTEM_SETTING");
    }

    public void setVersionEntity(AppUpdateEntity.AndroidBean androidBean) {
        if (androidBean != null) {
            this.f926g.setText(androidBean.getBrief());
        }
    }

    public VersionNotFindView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VersionNotFindView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        LayoutInflater.from(getContext()).inflate(R.layout.view_app_update_not_find, this);
        Button button = (Button) findViewById(R.id.enter_view);
        this.f924e = button;
        button.requestFocus();
        this.f925f = (TextView) findViewById(R.id.update_text);
        this.f926g = (TextView) findViewById(R.id.version_update_text);
        TextView textView = this.f925f;
        StringBuilder sbM112a = C0413b.m112a("当前已是最新版本");
        sbM112a.append(C2025a.m2376d());
        textView.setText(sbM112a.toString());
        this.f924e.setOnClickListener(this);
    }
}
