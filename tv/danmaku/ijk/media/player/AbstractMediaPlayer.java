package tv.danmaku.ijk.media.player;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.format.mpegts.PesPrivateDataTimeStamp;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;

/* loaded from: classes.dex */
public abstract class AbstractMediaPlayer implements IMediaPlayer {
    private IMediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener;
    private IMediaPlayer.OnCompletionListener mOnCompletionListener;
    private IMediaPlayer.OnErrorListener mOnErrorListener;
    private IMediaPlayer.OnInfoListener mOnInfoListener;
    private IMediaPlayer.OnPreparedListener mOnPreparedListener;
    private IMediaPlayer.OnSEIRefreshListener mOnSEIRefreshListener;
    private IMediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener;
    private IMediaPlayer.OnTimedTextListener mOnTimedTextListener;
    private IMediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangedListener;
    private IMediaPlayer.OnAudioVividMetadataListener onAudioVividMetadataListener;
    private IMediaPlayer.OnMpegTsPesPrivateDataListener onMpegTsPesPrivateDataListener;

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public float getAudioFrameSpeed() {
        return 1.0f;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public float getAv3aMetadataFloat(int i7, int i8) {
        return 0.0f;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getAv3aMetadataInt(int i7, int i8) {
        return 0;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public String getAv3aMetadataString(int i7, int i8) {
        return "";
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public float getVideoFrameSpeed() {
        return 1.0f;
    }

    public final void notifyOnAudioVividMetadataChanged() {
        IMediaPlayer.OnAudioVividMetadataListener onAudioVividMetadataListener = this.onAudioVividMetadataListener;
        if (onAudioVividMetadataListener != null) {
            onAudioVividMetadataListener.onMetadataChanged(this);
        }
    }

    public final void notifyOnBufferingUpdate(int i7) {
        IMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = this.mOnBufferingUpdateListener;
        if (onBufferingUpdateListener != null) {
            onBufferingUpdateListener.onBufferingUpdate(this, i7);
        }
    }

    public final void notifyOnCompletion() {
        IMediaPlayer.OnCompletionListener onCompletionListener = this.mOnCompletionListener;
        if (onCompletionListener != null) {
            onCompletionListener.onCompletion(this);
        }
    }

    public final boolean notifyOnError(int i7, int i8) {
        IMediaPlayer.OnErrorListener onErrorListener = this.mOnErrorListener;
        return onErrorListener != null && onErrorListener.onError(this, i7, i8);
    }

    public final boolean notifyOnInfo(int i7, Object obj) {
        IMediaPlayer.OnInfoListener onInfoListener = this.mOnInfoListener;
        return onInfoListener != null && onInfoListener.onInfo(this, i7, obj);
    }

    public final void notifyOnMpegTsPesPrivateData(PesPrivateDataTimeStamp pesPrivateDataTimeStamp) {
        IMediaPlayer.OnMpegTsPesPrivateDataListener onMpegTsPesPrivateDataListener = this.onMpegTsPesPrivateDataListener;
        if (onMpegTsPesPrivateDataListener != null) {
            onMpegTsPesPrivateDataListener.onPesPrivateData(this, pesPrivateDataTimeStamp);
        }
    }

    public final void notifyOnPrepared() {
        IMediaPlayer.OnPreparedListener onPreparedListener = this.mOnPreparedListener;
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared(this);
        }
    }

    public final void notifyOnSEIRefresh(int i7, int i8) {
        IMediaPlayer.OnSEIRefreshListener onSEIRefreshListener = this.mOnSEIRefreshListener;
        if (onSEIRefreshListener != null) {
            onSEIRefreshListener.onSEIRefresh(this, i7, i8);
        }
    }

    public final void notifyOnSeekComplete() {
        IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener = this.mOnSeekCompleteListener;
        if (onSeekCompleteListener != null) {
            onSeekCompleteListener.onSeekComplete(this);
        }
    }

    public final void notifyOnTimedText(IjkTimedText ijkTimedText) {
        IMediaPlayer.OnTimedTextListener onTimedTextListener = this.mOnTimedTextListener;
        if (onTimedTextListener != null) {
            onTimedTextListener.onTimedText(this, ijkTimedText);
        }
    }

    public final void notifyOnVideoSizeChanged(int i7, int i8, int i9, int i10) {
        IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = this.mOnVideoSizeChangedListener;
        if (onVideoSizeChangedListener != null) {
            onVideoSizeChangedListener.onVideoSizeChanged(this, i7, i8, i9, i10);
        }
    }

    public void resetListeners() {
        this.mOnPreparedListener = null;
        this.mOnBufferingUpdateListener = null;
        this.mOnCompletionListener = null;
        this.mOnSeekCompleteListener = null;
        this.mOnVideoSizeChangedListener = null;
        this.mOnErrorListener = null;
        this.mOnInfoListener = null;
        this.mOnTimedTextListener = null;
        this.mOnSEIRefreshListener = null;
        this.onAudioVividMetadataListener = null;
        this.onMpegTsPesPrivateDataListener = null;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int setAv3aMetadataFloat(int i7, int i8, float f7) {
        return 0;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDataSource(IMediaDataSource iMediaDataSource) {
        throw new UnsupportedOperationException();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setEnableHdrVivid(boolean z6) {
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public final void setOnAudioVividMetadataListener(IMediaPlayer.OnAudioVividMetadataListener onAudioVividMetadataListener) {
        this.onAudioVividMetadataListener = onAudioVividMetadataListener;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public final void setOnBufferingUpdateListener(IMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        this.mOnBufferingUpdateListener = onBufferingUpdateListener;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public final void setOnCompletionListener(IMediaPlayer.OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public final void setOnErrorListener(IMediaPlayer.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public final void setOnInfoListener(IMediaPlayer.OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public final void setOnMpegTsPesPrivateDataListener(IMediaPlayer.OnMpegTsPesPrivateDataListener onMpegTsPesPrivateDataListener) {
        this.onMpegTsPesPrivateDataListener = onMpegTsPesPrivateDataListener;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public final void setOnPreparedListener(IMediaPlayer.OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public final void setOnSEIRefreshListener(IMediaPlayer.OnSEIRefreshListener onSEIRefreshListener) {
        this.mOnSEIRefreshListener = onSEIRefreshListener;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public final void setOnSeekCompleteListener(IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        this.mOnSeekCompleteListener = onSeekCompleteListener;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public final void setOnTimedTextListener(IMediaPlayer.OnTimedTextListener onTimedTextListener) {
        this.mOnTimedTextListener = onTimedTextListener;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public final void setOnVideoSizeChangedListener(IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.mOnVideoSizeChangedListener = onVideoSizeChangedListener;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void updateSurface() {
    }
}
