package com.cctv.cctvplayer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.cctv.cctvplayer.CCTVVideoMediaController;
import com.cctv.cctvplayer.R$id;
import com.cctv.cctvplayer.R$layout;

/* loaded from: classes.dex */
public class PauseTipsView extends FrameLayout {

    /* renamed from: e */
    public RelativeLayout f532e;

    /* renamed from: f */
    public RelativeLayout f533f;

    /* renamed from: g */
    public LinearLayout f534g;

    /* renamed from: h */
    public RelativeLayout f535h;

    public PauseTipsView(Context context) {
        this(context, null);
    }

    /* renamed from: a */
    public void m395a(int i7) {
        if (i7 != 0 || this.f534g.getChildCount() >= 1) {
            this.f534g.setVisibility(i7);
        }
    }

    public void setAdvanceClickListener(View.OnClickListener onClickListener) {
        this.f533f.setOnClickListener(onClickListener);
    }

    public void setDrawBackClickListener(View.OnClickListener onClickListener) {
        this.f532e.setOnClickListener(onClickListener);
    }

    public void setMediaController(CCTVVideoMediaController cCTVVideoMediaController) {
    }

    public PauseTipsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PauseTipsView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.cctv_videoview_pause, this);
        this.f532e = (RelativeLayout) viewInflate.findViewById(R$id.drawBackLayout);
        this.f533f = (RelativeLayout) viewInflate.findViewById(R$id.advanceLayout);
        this.f534g = (LinearLayout) viewInflate.findViewById(R$id.pauseLeft);
        this.f535h = (RelativeLayout) viewInflate.findViewById(R$id.pauseRight);
        setVisibility(8);
    }
}
