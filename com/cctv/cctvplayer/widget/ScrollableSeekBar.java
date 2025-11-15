package com.cctv.cctvplayer.widget;

import android.R;
import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* loaded from: classes.dex */
public class ScrollableSeekBar extends AppCompatSeekBar {

    /* renamed from: e */
    public boolean f553e;

    public ScrollableSeekBar(Context context) {
        super(context);
        this.f553e = true;
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f553e && super.onTouchEvent(motionEvent);
    }

    public void setScrollable(boolean z6) {
        this.f553e = z6;
    }

    public ScrollableSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.seekBarStyle);
    }

    public ScrollableSeekBar(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f553e = true;
    }
}
