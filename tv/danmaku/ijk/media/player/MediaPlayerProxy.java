package tv.danmaku.ijk.media.player;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceHolder;
import java.io.FileDescriptor;
import java.util.Map;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.format.mpegts.PesPrivateDataTimeStamp;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/* loaded from: classes.dex */
public class MediaPlayerProxy implements IMediaPlayer {
    public final IMediaPlayer mBackEndMediaPlayer;

    public MediaPlayerProxy(IMediaPlayer iMediaPlayer) {
        this.mBackEndMediaPlayer = iMediaPlayer;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public float getAudioFrameSpeed() {
        return this.mBackEndMediaPlayer.getAudioFrameSpeed();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getAudioSessionId() {
        return this.mBackEndMediaPlayer.getAudioSessionId();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public float getAv3aMetadataFloat(int i7, int i8) {
        return this.mBackEndMediaPlayer.getAv3aMetadataFloat(i7, i8);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getAv3aMetadataInt(int i7, int i8) {
        return this.mBackEndMediaPlayer.getAv3aMetadataInt(i7, i8);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public String getAv3aMetadataString(int i7, int i8) {
        return this.mBackEndMediaPlayer.getAv3aMetadataString(i7, i8);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public long getCurrentPosition() {
        return this.mBackEndMediaPlayer.getCurrentPosition();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public String getDataSource() {
        return this.mBackEndMediaPlayer.getDataSource();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public long getDuration() {
        return this.mBackEndMediaPlayer.getDuration();
    }

    public IMediaPlayer getInternalMediaPlayer() {
        return this.mBackEndMediaPlayer;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public MediaInfo getMediaInfo() {
        return this.mBackEndMediaPlayer.getMediaInfo();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public ITrackInfo[] getTrackInfo() {
        return this.mBackEndMediaPlayer.getTrackInfo();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public float getVideoFrameSpeed() {
        return this.mBackEndMediaPlayer.getVideoFrameSpeed();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getVideoHeight() {
        return this.mBackEndMediaPlayer.getVideoHeight();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getVideoSarDen() {
        return this.mBackEndMediaPlayer.getVideoSarDen();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getVideoSarNum() {
        return this.mBackEndMediaPlayer.getVideoSarNum();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getVideoWidth() {
        return this.mBackEndMediaPlayer.getVideoWidth();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public boolean isLooping() {
        return this.mBackEndMediaPlayer.isLooping();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public boolean isPlayable() {
        return false;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public boolean isPlaying() {
        return this.mBackEndMediaPlayer.isPlaying();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void pause() {
        this.mBackEndMediaPlayer.pause();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void prepareAsync() {
        this.mBackEndMediaPlayer.prepareAsync();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void release() {
        this.mBackEndMediaPlayer.release();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void reset() {
        this.mBackEndMediaPlayer.reset();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void seekTo(long j7) {
        this.mBackEndMediaPlayer.seekTo(j7);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setAudioStreamType(int i7) {
        this.mBackEndMediaPlayer.setAudioStreamType(i7);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int setAv3aMetadataFloat(int i7, int i8, float f7) {
        return this.mBackEndMediaPlayer.setAv3aMetadataFloat(i7, i8, f7);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDataSource(Context context, Uri uri) {
        this.mBackEndMediaPlayer.setDataSource(context, uri);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mBackEndMediaPlayer.setDisplay(surfaceHolder);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setEnableHdrVivid(boolean z6) {
        this.mBackEndMediaPlayer.setEnableHdrVivid(z6);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setKeepInBackground(boolean z6) {
        this.mBackEndMediaPlayer.setKeepInBackground(z6);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setLogEnabled(boolean z6) {
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setLooping(boolean z6) {
        this.mBackEndMediaPlayer.setLooping(z6);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnAudioVividMetadataListener(final IMediaPlayer.OnAudioVividMetadataListener onAudioVividMetadataListener) {
        if (onAudioVividMetadataListener != null) {
            this.mBackEndMediaPlayer.setOnAudioVividMetadataListener(new IMediaPlayer.OnAudioVividMetadataListener() { // from class: tv.danmaku.ijk.media.player.MediaPlayerProxy.10
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnAudioVividMetadataListener
                public void onMetadataChanged(IMediaPlayer iMediaPlayer) {
                    onAudioVividMetadataListener.onMetadataChanged(iMediaPlayer);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnAudioVividMetadataListener(null);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnBufferingUpdateListener(final IMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        if (onBufferingUpdateListener != null) {
            this.mBackEndMediaPlayer.setOnBufferingUpdateListener(new IMediaPlayer.OnBufferingUpdateListener() { // from class: tv.danmaku.ijk.media.player.MediaPlayerProxy.3
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
                public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i7) {
                    onBufferingUpdateListener.onBufferingUpdate(MediaPlayerProxy.this, i7);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnBufferingUpdateListener(null);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnCompletionListener(final IMediaPlayer.OnCompletionListener onCompletionListener) {
        if (onCompletionListener != null) {
            this.mBackEndMediaPlayer.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() { // from class: tv.danmaku.ijk.media.player.MediaPlayerProxy.2
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    onCompletionListener.onCompletion(MediaPlayerProxy.this);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnCompletionListener(null);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnErrorListener(final IMediaPlayer.OnErrorListener onErrorListener) {
        if (onErrorListener != null) {
            this.mBackEndMediaPlayer.setOnErrorListener(new IMediaPlayer.OnErrorListener() { // from class: tv.danmaku.ijk.media.player.MediaPlayerProxy.6
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
                public boolean onError(IMediaPlayer iMediaPlayer, int i7, int i8) {
                    return onErrorListener.onError(MediaPlayerProxy.this, i7, i8);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnErrorListener(null);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnInfoListener(final IMediaPlayer.OnInfoListener onInfoListener) {
        if (onInfoListener != null) {
            this.mBackEndMediaPlayer.setOnInfoListener(new IMediaPlayer.OnInfoListener() { // from class: tv.danmaku.ijk.media.player.MediaPlayerProxy.7
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
                public boolean onInfo(IMediaPlayer iMediaPlayer, int i7, Object obj) {
                    return onInfoListener.onInfo(MediaPlayerProxy.this, i7, obj);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnInfoListener(null);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnMpegTsPesPrivateDataListener(final IMediaPlayer.OnMpegTsPesPrivateDataListener onMpegTsPesPrivateDataListener) {
        if (onMpegTsPesPrivateDataListener != null) {
            this.mBackEndMediaPlayer.setOnMpegTsPesPrivateDataListener(new IMediaPlayer.OnMpegTsPesPrivateDataListener() { // from class: tv.danmaku.ijk.media.player.MediaPlayerProxy.11
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnMpegTsPesPrivateDataListener
                public void onPesPrivateData(IMediaPlayer iMediaPlayer, PesPrivateDataTimeStamp pesPrivateDataTimeStamp) {
                    onMpegTsPesPrivateDataListener.onPesPrivateData(iMediaPlayer, pesPrivateDataTimeStamp);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnMpegTsPesPrivateDataListener(null);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnPreparedListener(final IMediaPlayer.OnPreparedListener onPreparedListener) {
        if (onPreparedListener != null) {
            this.mBackEndMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() { // from class: tv.danmaku.ijk.media.player.MediaPlayerProxy.1
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    onPreparedListener.onPrepared(MediaPlayerProxy.this);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnPreparedListener(null);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnSEIRefreshListener(final IMediaPlayer.OnSEIRefreshListener onSEIRefreshListener) {
        if (onSEIRefreshListener != null) {
            this.mBackEndMediaPlayer.setOnSEIRefreshListener(new IMediaPlayer.OnSEIRefreshListener() { // from class: tv.danmaku.ijk.media.player.MediaPlayerProxy.9
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSEIRefreshListener
                public void onSEIRefresh(IMediaPlayer iMediaPlayer, int i7, int i8) {
                    onSEIRefreshListener.onSEIRefresh(MediaPlayerProxy.this, i7, i8);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnSEIRefreshListener(null);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnSeekCompleteListener(final IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        if (onSeekCompleteListener != null) {
            this.mBackEndMediaPlayer.setOnSeekCompleteListener(new IMediaPlayer.OnSeekCompleteListener() { // from class: tv.danmaku.ijk.media.player.MediaPlayerProxy.4
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
                public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                    onSeekCompleteListener.onSeekComplete(MediaPlayerProxy.this);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnSeekCompleteListener(null);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnTimedTextListener(final IMediaPlayer.OnTimedTextListener onTimedTextListener) {
        if (onTimedTextListener != null) {
            this.mBackEndMediaPlayer.setOnTimedTextListener(new IMediaPlayer.OnTimedTextListener() { // from class: tv.danmaku.ijk.media.player.MediaPlayerProxy.8
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnTimedTextListener
                public void onTimedText(IMediaPlayer iMediaPlayer, IjkTimedText ijkTimedText) {
                    onTimedTextListener.onTimedText(MediaPlayerProxy.this, ijkTimedText);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnTimedTextListener(null);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnVideoSizeChangedListener(final IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        if (onVideoSizeChangedListener != null) {
            this.mBackEndMediaPlayer.setOnVideoSizeChangedListener(new IMediaPlayer.OnVideoSizeChangedListener() { // from class: tv.danmaku.ijk.media.player.MediaPlayerProxy.5
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
                public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i7, int i8, int i9, int i10) {
                    onVideoSizeChangedListener.onVideoSizeChanged(MediaPlayerProxy.this, i7, i8, i9, i10);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnVideoSizeChangedListener(null);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setScreenOnWhilePlaying(boolean z6) {
        this.mBackEndMediaPlayer.setScreenOnWhilePlaying(z6);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    @TargetApi(14)
    public void setSurface(Surface surface) {
        this.mBackEndMediaPlayer.setSurface(surface);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setVolume(float f7, float f8) {
        this.mBackEndMediaPlayer.setVolume(f7, f8);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setWakeMode(Context context, int i7) {
        this.mBackEndMediaPlayer.setWakeMode(context, i7);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void start() {
        this.mBackEndMediaPlayer.start();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void stop() {
        this.mBackEndMediaPlayer.stop();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void updateSurface() {
        this.mBackEndMediaPlayer.updateSurface();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    @TargetApi(14)
    public void setDataSource(Context context, Uri uri, Map<String, String> map) {
        this.mBackEndMediaPlayer.setDataSource(context, uri, map);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDataSource(FileDescriptor fileDescriptor) {
        this.mBackEndMediaPlayer.setDataSource(fileDescriptor);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDataSource(String str) {
        this.mBackEndMediaPlayer.setDataSource(str);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDataSource(IMediaDataSource iMediaDataSource) {
        this.mBackEndMediaPlayer.setDataSource(iMediaDataSource);
    }
}
