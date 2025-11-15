package com.cctv.cctvplayer.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class MarqueeTextView extends AppCompatTextView {
    public MarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z6, int i7, Rect rect) {
        if (z6) {
            super.onFocusChanged(z6, i7, rect);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean z6) {
        if (z6) {
            super.onWindowFocusChanged(z6);
        }
    }
}
