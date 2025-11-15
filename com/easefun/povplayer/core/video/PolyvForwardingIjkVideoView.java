package com.easefun.povplayer.core.video;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a;
import com.easefun.povplayer.core.ijk.widget.media.PolyvGLSurfaceRenderView;
import com.easefun.povplayer.core.ijk.widget.media.PolyvGLTextureRenderView;
import java.util.Map;
import p079j3.InterfaceC1199b;
import p095l3.InterfaceC1421a;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/* loaded from: classes.dex */
public class PolyvForwardingIjkVideoView extends FrameLayout implements InterfaceC1421a {

    /* renamed from: e */
    public InterfaceC1421a f1105e;

    public PolyvForwardingIjkVideoView(Context context) {
        super(context);
        this.f1105e = null;
    }

    @Override // p095l3.InterfaceC1421a
    /* renamed from: a */
    public void mo574a(boolean z6) {
        this.f1105e.mo574a(z6);
    }

    @Override // p095l3.InterfaceC1421a
    /* renamed from: b */
    public void mo575b(Uri uri, Map<String, String> map) {
        this.f1105e.mo575b(uri, map);
    }

    @Override // p095l3.InterfaceC1421a
    /* renamed from: c */
    public void mo576c(int i7) {
        this.f1105e.mo576c(i7);
    }

    @Override // p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return this.f1105e.canPause();
    }

    @Override // p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return this.f1105e.canSeekBackward();
    }

    @Override // p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return this.f1105e.canSeekForward();
    }

    @Override // p095l3.InterfaceC1421a
    /* renamed from: d */
    public boolean mo577d(boolean z6) {
        return this.f1105e.mo577d(z6);
    }

    @Override // p095l3.InterfaceC1421a
    /* renamed from: e */
    public boolean mo578e() {
        return this.f1105e.mo578e();
    }

    @Override // p095l3.InterfaceC1421a
    /* renamed from: f */
    public int mo579f(int i7) {
        return this.f1105e.mo579f(i7);
    }

    @Override // p095l3.InterfaceC1421a
    /* renamed from: g */
    public void mo580g() {
        this.f1105e.mo580g();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    @Override // p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        return this.f1105e.getBufferPercentage();
    }

    @Override // p095l3.InterfaceC1421a
    public int getCurrentAspectRatio() {
        return this.f1105e.getCurrentAspectRatio();
    }

    @Override // p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        return this.f1105e.getCurrentPosition();
    }

    @Override // p095l3.InterfaceC1421a
    public int getCurrentState() {
        return this.f1105e.getCurrentState();
    }

    @Override // p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        return this.f1105e.getDuration();
    }

    @Override // p095l3.InterfaceC1421a
    public IMediaPlayer getMediaPlayer() {
        return this.f1105e.getMediaPlayer();
    }

    @Override // p095l3.InterfaceC1421a
    public InterfaceC0736a getRenderView() {
        return this.f1105e.getRenderView();
    }

    @Override // p095l3.InterfaceC1421a
    public float getSpeed() {
        return this.f1105e.getSpeed();
    }

    @Override // p095l3.InterfaceC1421a
    public int getStateErrorCode() {
        return this.f1105e.getStateErrorCode();
    }

    @Override // p095l3.InterfaceC1421a
    public int getStateIdleCode() {
        return this.f1105e.getStateIdleCode();
    }

    @Override // p095l3.InterfaceC1421a
    public int getStatePauseCode() {
        return this.f1105e.getStatePauseCode();
    }

    @Override // p095l3.InterfaceC1421a
    public int getStatePlaybackCompletedCode() {
        return this.f1105e.getStatePlaybackCompletedCode();
    }

    @Override // p095l3.InterfaceC1421a
    public int getStatePlayingCode() {
        return this.f1105e.getStatePlayingCode();
    }

    @Override // p095l3.InterfaceC1421a
    public int getStatePreparedCode() {
        return this.f1105e.getStatePreparedCode();
    }

    @Override // p095l3.InterfaceC1421a
    public int getStatePreparingCode() {
        return this.f1105e.getStatePreparingCode();
    }

    @Override // p095l3.InterfaceC1421a
    public SurfaceHolder getSurfaceHolder() {
        return this.f1105e.getSurfaceHolder();
    }

    @Override // p095l3.InterfaceC1421a
    public int getTargetState() {
        return this.f1105e.getTargetState();
    }

    @Override // p095l3.InterfaceC1421a
    public ITrackInfo[] getTrackInfo() {
        return this.f1105e.getTrackInfo();
    }

    @Override // p095l3.InterfaceC1421a
    public int getVideoHeight() {
        return this.f1105e.getVideoHeight();
    }

    @Override // p095l3.InterfaceC1421a
    public int getVideoWidth() {
        return this.f1105e.getVideoWidth();
    }

    @Override // p095l3.InterfaceC1421a
    /* renamed from: h */
    public void mo581h() {
        this.f1105e.mo581h();
    }

    @Override // p095l3.InterfaceC1421a
    /* renamed from: i */
    public void mo582i() {
        this.f1105e.mo582i();
    }

    @Override // p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return this.f1105e.isPlaying();
    }

    @Override // p095l3.InterfaceC1421a
    /* renamed from: j */
    public void mo583j() {
        this.f1105e.mo583j();
    }

    @Override // p095l3.InterfaceC1421a
    /* renamed from: k */
    public void mo584k() {
        this.f1105e.mo584k();
    }

    @Override // p095l3.InterfaceC1421a
    /* renamed from: l */
    public void mo585l() {
        this.f1105e.mo585l();
    }

    @Override // p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public void pause() {
        this.f1105e.pause();
    }

    @Override // p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i7) {
        this.f1105e.seekTo(i7);
    }

    @Override // p095l3.InterfaceC1421a
    public void setCurrentAspectRatio(int i7) {
        this.f1105e.setCurrentAspectRatio(i7);
    }

    @Override // p095l3.InterfaceC1421a
    public void setHudView(TableLayout tableLayout) {
        this.f1105e.setHudView(tableLayout);
    }

    @Override // p095l3.InterfaceC1421a
    public void setIjkLogLevel(int i7) {
        this.f1105e.setIjkLogLevel(i7);
    }

    @Override // p095l3.InterfaceC1421a
    public void setLogTag(String str) {
        this.f1105e.setLogTag(str);
    }

    @Override // p095l3.InterfaceC1421a
    public void setMediaController(InterfaceC1199b interfaceC1199b) {
        this.f1105e.setMediaController(interfaceC1199b);
    }

    @Override // p095l3.InterfaceC1421a
    public void setMirror(boolean z6) {
        this.f1105e.setMirror(z6);
    }

    @Override // p095l3.InterfaceC1421a
    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener onCompletionListener) {
        this.f1105e.setOnCompletionListener(onCompletionListener);
    }

    @Override // p095l3.InterfaceC1421a
    public void setOnErrorListener(IMediaPlayer.OnErrorListener onErrorListener) {
        this.f1105e.setOnErrorListener(onErrorListener);
    }

    @Override // p095l3.InterfaceC1421a
    public void setOnInfoListener(IMediaPlayer.OnInfoListener onInfoListener) {
        this.f1105e.setOnInfoListener(onInfoListener);
    }

    @Override // p095l3.InterfaceC1421a
    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener onPreparedListener) {
        this.f1105e.setOnPreparedListener(onPreparedListener);
    }

    @Override // p095l3.InterfaceC1421a
    public void setOnSeekCompleteListener(IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        this.f1105e.setOnSeekCompleteListener(onSeekCompleteListener);
    }

    @Override // p095l3.InterfaceC1421a
    public void setOnVideoSizeChangedListener(IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.f1105e.setOnVideoSizeChangedListener(onVideoSizeChangedListener);
    }

    @Override // p095l3.InterfaceC1421a
    public void setOptionParameters(Object[][] objArr) {
        this.f1105e.setOptionParameters(objArr);
    }

    @Override // p095l3.InterfaceC1421a
    public void setRender(int i7) {
        this.f1105e.setRender(i7);
    }

    @Override // p095l3.InterfaceC1421a
    public void setRenderView(InterfaceC0736a interfaceC0736a) {
        this.f1105e.setRenderView(interfaceC0736a);
    }

    @Override // p095l3.InterfaceC1421a
    public void setSpeed(float f7) {
        this.f1105e.setSpeed(f7);
    }

    @Override // p095l3.InterfaceC1421a
    public void setTargetState(int i7) {
        this.f1105e.setTargetState(i7);
    }

    @Override // p095l3.InterfaceC1421a
    public void setVRViewInitCompletionListener(PolyvGLSurfaceRenderView.InterfaceC0727c interfaceC0727c) {
        this.f1105e.setVRViewInitCompletionListener(interfaceC0727c);
    }

    @Override // p095l3.InterfaceC1421a
    public void setVideoPath(String str) {
        this.f1105e.setVideoPath(str);
    }

    @Override // p095l3.InterfaceC1421a
    public void setVideoURI(Uri uri) {
        this.f1105e.setVideoURI(uri);
    }

    @Override // p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public void start() {
        this.f1105e.start();
    }

    @Override // p095l3.InterfaceC1421a
    public void setVRViewInitCompletionListener(PolyvGLTextureRenderView.InterfaceC0730c interfaceC0730c) {
        this.f1105e.setVRViewInitCompletionListener(interfaceC0730c);
    }

    public PolyvForwardingIjkVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1105e = null;
    }

    public PolyvForwardingIjkVideoView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1105e = null;
    }
}
