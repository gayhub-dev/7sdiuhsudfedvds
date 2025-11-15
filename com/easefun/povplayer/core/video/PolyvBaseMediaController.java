package com.easefun.povplayer.core.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import p079j3.InterfaceC1199b;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes.dex */
public abstract class PolyvBaseMediaController extends FrameLayout implements InterfaceC1199b {
    public PolyvBaseMediaController(Context context) {
        super(context);
    }

    /* renamed from: a */
    public abstract void mo351a(IMediaPlayer iMediaPlayer);

    @Override // p079j3.InterfaceC1199b
    public void setAnchorView(View view) {
    }

    @Override // p079j3.InterfaceC1199b
    public void setMediaPlayer(MediaController.MediaPlayerControl mediaPlayerControl) {
    }

    public abstract void setVideoView(PolyvVideoView polyvVideoView);

    public PolyvBaseMediaController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PolyvBaseMediaController(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
    }
}
