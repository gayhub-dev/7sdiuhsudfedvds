package com.cctv.cctvplayer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.cctv.cctvplayer.CCTVVideoMediaController;
import com.cctv.cctvplayer.CCTVVideoView;
import com.cctv.cctvplayer.R$id;
import com.cctv.cctvplayer.R$layout;
import java.util.List;
import p045f1.C0993b;

/* loaded from: classes.dex */
public class RateTipsView extends FrameLayout {

    /* renamed from: e */
    public List<C0993b> f548e;

    /* renamed from: f */
    public LinearLayout f549f;

    /* renamed from: g */
    public View f550g;

    /* renamed from: h */
    public CCTVVideoView f551h;

    /* renamed from: i */
    public CCTVVideoMediaController f552i;

    public RateTipsView(Context context) {
        this(context, null);
    }

    public RateTipsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RateTipsView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f549f = (LinearLayout) LayoutInflater.from(context).inflate(R$layout.cctv_videoview_rate, this).findViewById(R$id.rateItemParent);
    }
}
