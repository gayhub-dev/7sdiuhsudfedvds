package com.ctvit.widget.imageview;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class PressImageView extends AppCompatImageView {

    /* renamed from: e */
    public static final /* synthetic */ int f983e = 0;

    /* renamed from: com.ctvit.widget.imageview.PressImageView$a */
    public class ViewOnTouchListenerC0711a implements View.OnTouchListener {

        /* renamed from: e */
        public final float[] f984e = {1.0f, 0.0f, 0.0f, 0.0f, -50.0f, 0.0f, 1.0f, 0.0f, 0.0f, -50.0f, 0.0f, 0.0f, 1.0f, 0.0f, -50.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};

        public ViewOnTouchListenerC0711a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                ((ImageView) view).setColorFilter(new ColorMatrixColorFilter(this.f984e));
            } else if (motionEvent.getAction() == 1) {
                ((ImageView) view).clearColorFilter();
                PressImageView pressImageView = PressImageView.this;
                int i7 = PressImageView.f983e;
                pressImageView.performClick();
            } else if (motionEvent.getAction() == 3) {
                ((ImageView) view).clearColorFilter();
            }
            return true;
        }
    }

    public PressImageView(Context context) {
        super(context);
        setOnTouchListener(new ViewOnTouchListenerC0711a());
    }

    public PressImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnTouchListener(new ViewOnTouchListenerC0711a());
    }

    public PressImageView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        setOnTouchListener(new ViewOnTouchListenerC0711a());
    }
}
