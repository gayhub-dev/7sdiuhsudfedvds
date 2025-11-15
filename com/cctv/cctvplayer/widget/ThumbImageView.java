package com.cctv.cctvplayer.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class ThumbImageView extends AppCompatImageView implements View.OnTouchListener {

    /* renamed from: e */
    public ViewGroup f554e;

    public ThumbImageView(Context context) {
        this(context, null);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ViewGroup viewGroup = this.f554e;
        if (viewGroup != null) {
            return viewGroup.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void setSeekBarParentView(ViewGroup viewGroup) {
        this.f554e = viewGroup;
    }

    public ThumbImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ThumbImageView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        setOnTouchListener(this);
    }
}
