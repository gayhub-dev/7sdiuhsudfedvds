package com.cctv.cctvplayer.widget;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes.dex */
public class PressDownImageView extends AppCompatImageView {

    /* renamed from: e */
    public static final /* synthetic */ int f536e = 0;

    /* renamed from: com.cctv.cctvplayer.widget.PressDownImageView$a */
    public class ViewOnTouchListenerC0569a implements View.OnTouchListener {

        /* renamed from: e */
        public final float[] f537e = {1.0f, 0.0f, 0.0f, 0.0f, -60.0f, 0.0f, 1.0f, 0.0f, 0.0f, -60.0f, 0.0f, 0.0f, 1.0f, 0.0f, -60.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};

        public ViewOnTouchListenerC0569a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                ((AppCompatImageView) view).setColorFilter(new ColorMatrixColorFilter(this.f537e));
            } else if (motionEvent.getAction() == 1) {
                ((AppCompatImageView) view).clearColorFilter();
                PressDownImageView pressDownImageView = PressDownImageView.this;
                int i7 = PressDownImageView.f536e;
                pressDownImageView.performClick();
            } else if (motionEvent.getAction() == 3) {
                ((AppCompatImageView) view).clearColorFilter();
            }
            return true;
        }
    }

    public PressDownImageView(Context context) {
        super(context);
        setOnTouchListener(new ViewOnTouchListenerC0569a());
    }

    public PressDownImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnTouchListener(new ViewOnTouchListenerC0569a());
    }

    public PressDownImageView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        setOnTouchListener(new ViewOnTouchListenerC0569a());
    }
}
