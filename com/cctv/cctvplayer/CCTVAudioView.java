package com.cctv.cctvplayer;

import android.content.Context;
import android.util.AttributeSet;
import com.easefun.povplayer.core.video.PolyvSubVideoView;
import com.easefun.povplayer.core.video.PolyvVideoView;

/* loaded from: classes.dex */
public class CCTVAudioView extends PolyvVideoView {
    public CCTVAudioView(Context context) {
        this(context, null);
    }

    public CCTVAudioView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CCTVAudioView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        setVisibility(8);
        setSubVideoView(new PolyvSubVideoView(getContext()));
    }
}
