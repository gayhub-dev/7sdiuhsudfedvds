package com.easefun.povplayer.core.ijk.widget.media;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.MediaController;
import java.util.ArrayList;
import java.util.Iterator;
import p079j3.InterfaceC1199b;

/* loaded from: classes.dex */
public class AndroidMediaController extends MediaController implements InterfaceC1199b {

    /* renamed from: e */
    public ActionBar f987e;

    /* renamed from: f */
    public ArrayList<View> f988f;

    public AndroidMediaController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f988f = new ArrayList<>();
    }

    @Override // android.widget.MediaController, p079j3.InterfaceC1199b
    public void hide() {
        super.hide();
        ActionBar actionBar = this.f987e;
        if (actionBar != null) {
            actionBar.hide();
        }
        Iterator<View> it = this.f988f.iterator();
        while (it.hasNext()) {
            it.next().setVisibility(8);
        }
        this.f988f.clear();
    }

    public void setSupportActionBar(@Nullable ActionBar actionBar) {
        this.f987e = actionBar;
        if (isShowing()) {
            actionBar.show();
        } else {
            actionBar.hide();
        }
    }

    @Override // android.widget.MediaController, p079j3.InterfaceC1199b
    public void show() {
        super.show();
        ActionBar actionBar = this.f987e;
        if (actionBar != null) {
            actionBar.show();
        }
    }

    public AndroidMediaController(Context context) {
        super(context);
        this.f988f = new ArrayList<>();
    }
}
