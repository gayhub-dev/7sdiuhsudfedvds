package com.cctv.p025tv.mvp.p026ui.view.versionupdate;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.cctv.p025tv.R;
import com.cctv.p025tv.module.collect.C0580b;
import p043f.C0988e;
import p070i2.RunnableC1143a;
import p150s1.C1868e;

/* loaded from: classes.dex */
public class VersionCheckingView extends RelativeLayout implements View.OnClickListener {

    /* renamed from: e */
    public Button f909e;

    /* renamed from: f */
    public ImageView f910f;

    public VersionCheckingView(Context context) {
        this(context, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.cancel_btn) {
            return;
        }
        C0580b.m417c("UPDATE_CHECK_CANCEL", "VersionUpdateFragment");
        C0988e.m971O(((AppCompatActivity) getContext()).getSupportFragmentManager(), "SYSTEM_SETTING");
    }

    public VersionCheckingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VersionCheckingView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        LayoutInflater.from(getContext()).inflate(R.layout.view_app_update_checking, this);
        ImageView imageView = (ImageView) findViewById(R.id.loadingView);
        this.f910f = imageView;
        this.f910f.post(new RunnableC1143a(this, (AnimationDrawable) imageView.getBackground()));
        Button button = (Button) findViewById(R.id.cancel_btn);
        this.f909e = button;
        button.requestFocus();
        if (C1868e.f5445a) {
            this.f909e.setVisibility(8);
        }
        this.f909e.setOnClickListener(this);
    }
}
