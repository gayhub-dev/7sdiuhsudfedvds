package p095l3;

import android.widget.MediaController;

/* compiled from: IPolyvMediaPlayerControl.java */
/* renamed from: l3.b */
/* loaded from: classes.dex */
public interface InterfaceC1422b extends MediaController.MediaPlayerControl {
    @Override // android.widget.MediaController.MediaPlayerControl
    boolean canPause();

    @Override // android.widget.MediaController.MediaPlayerControl
    boolean canSeekBackward();

    @Override // android.widget.MediaController.MediaPlayerControl
    boolean canSeekForward();

    @Override // android.widget.MediaController.MediaPlayerControl
    int getBufferPercentage();

    @Override // android.widget.MediaController.MediaPlayerControl
    int getCurrentPosition();

    @Override // android.widget.MediaController.MediaPlayerControl
    int getDuration();

    @Override // android.widget.MediaController.MediaPlayerControl
    boolean isPlaying();

    @Override // android.widget.MediaController.MediaPlayerControl
    void pause();

    @Override // android.widget.MediaController.MediaPlayerControl
    void seekTo(int i7);

    @Override // android.widget.MediaController.MediaPlayerControl
    void start();
}
