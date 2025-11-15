package p095l3;

import android.net.Uri;
import android.view.SurfaceHolder;
import android.widget.TableLayout;
import com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a;
import com.easefun.povplayer.core.ijk.widget.media.PolyvGLSurfaceRenderView;
import com.easefun.povplayer.core.ijk.widget.media.PolyvGLTextureRenderView;
import java.util.Map;
import p079j3.InterfaceC1199b;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/* compiled from: IPolyvIjkVideoView.java */
/* renamed from: l3.a */
/* loaded from: classes.dex */
public interface InterfaceC1421a extends InterfaceC1422b {
    /* renamed from: a */
    void mo574a(boolean z6);

    /* renamed from: b */
    void mo575b(Uri uri, Map<String, String> map);

    /* renamed from: c */
    void mo576c(int i7);

    /* renamed from: d */
    boolean mo577d(boolean z6);

    /* renamed from: e */
    boolean mo578e();

    /* renamed from: f */
    int mo579f(int i7);

    /* renamed from: g */
    void mo580g();

    int getCurrentAspectRatio();

    int getCurrentState();

    IMediaPlayer getMediaPlayer();

    InterfaceC0736a getRenderView();

    float getSpeed();

    int getStateErrorCode();

    int getStateIdleCode();

    int getStatePauseCode();

    int getStatePlaybackCompletedCode();

    int getStatePlayingCode();

    int getStatePreparedCode();

    int getStatePreparingCode();

    SurfaceHolder getSurfaceHolder();

    int getTargetState();

    ITrackInfo[] getTrackInfo();

    int getVideoHeight();

    int getVideoWidth();

    /* renamed from: h */
    void mo581h();

    /* renamed from: i */
    void mo582i();

    /* renamed from: j */
    void mo583j();

    /* renamed from: k */
    void mo584k();

    /* renamed from: l */
    void mo585l();

    void setCurrentAspectRatio(int i7);

    void setHudView(TableLayout tableLayout);

    void setIjkLogLevel(int i7);

    void setLogTag(String str);

    void setMediaController(InterfaceC1199b interfaceC1199b);

    void setMirror(boolean z6);

    void setOnCompletionListener(IMediaPlayer.OnCompletionListener onCompletionListener);

    void setOnErrorListener(IMediaPlayer.OnErrorListener onErrorListener);

    void setOnInfoListener(IMediaPlayer.OnInfoListener onInfoListener);

    void setOnPreparedListener(IMediaPlayer.OnPreparedListener onPreparedListener);

    void setOnSeekCompleteListener(IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener);

    void setOnVideoSizeChangedListener(IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener);

    void setOptionParameters(Object[][] objArr);

    void setRender(int i7);

    void setRenderView(InterfaceC0736a interfaceC0736a);

    void setSpeed(float f7);

    void setTargetState(int i7);

    void setVRViewInitCompletionListener(PolyvGLSurfaceRenderView.InterfaceC0727c interfaceC0727c);

    void setVRViewInitCompletionListener(PolyvGLTextureRenderView.InterfaceC0730c interfaceC0730c);

    void setVideoPath(String str);

    void setVideoURI(Uri uri);
}
