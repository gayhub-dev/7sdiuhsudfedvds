package com.cctv.p025tv.module.player.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.cctv.p025tv.R;

/* loaded from: classes.dex */
public class PlayView extends RelativeLayout {
    public PlayView(Context context) {
        this(context, null);
    }

    public PlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PlayView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        LayoutInflater.from(getContext()).inflate(R.layout.cctv_play_view, this);
    }
}
