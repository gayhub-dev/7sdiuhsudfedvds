package p087k3;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import com.easefun.povplayer.core.video.PolyvVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* compiled from: PolyvAudioFocusManager.java */
/* renamed from: k3.a */
/* loaded from: classes.dex */
public class C1232a implements AudioManager.OnAudioFocusChangeListener {

    /* renamed from: a */
    public AudioManager f2764a;

    /* renamed from: b */
    public PolyvVideoView f2765b;

    /* renamed from: c */
    public boolean f2766c;

    /* renamed from: d */
    public boolean f2767d;

    /* renamed from: e */
    public boolean f2768e = true;

    /* renamed from: f */
    public AudioFocusRequest f2769f;

    /* renamed from: g */
    public long f2770g;

    public C1232a(Context context) {
        this.f2764a = (AudioManager) context.getApplicationContext().getSystemService("audio");
        if (Build.VERSION.SDK_INT >= 26) {
            this.f2769f = new AudioFocusRequest.Builder(1).setOnAudioFocusChangeListener(this).setWillPauseWhenDucked(true).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).build()).build();
        }
    }

    /* renamed from: a */
    public void m1455a() {
        if (Build.VERSION.SDK_INT < 26) {
            this.f2764a.abandonAudioFocus(this);
            return;
        }
        AudioFocusRequest audioFocusRequest = this.f2769f;
        if (audioFocusRequest != null) {
            this.f2764a.abandonAudioFocusRequest(audioFocusRequest);
        } else {
            this.f2764a.abandonAudioFocus(this);
        }
    }

    /* renamed from: b */
    public boolean m1456b() {
        AudioFocusRequest audioFocusRequest;
        return (Build.VERSION.SDK_INT < 26 || (audioFocusRequest = this.f2769f) == null) ? this.f2764a.requestAudioFocus(this, 3, 2) == 1 : this.f2764a.requestAudioFocus(audioFocusRequest) == 1;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i7) {
        PolyvVideoView polyvVideoView = this.f2765b;
        if (polyvVideoView != null && this.f2768e) {
            if (i7 == -3) {
                IMediaPlayer mediaPlayer = polyvVideoView.getMediaPlayer();
                if (mediaPlayer != null) {
                    mediaPlayer.setVolume(0.0f, 0.0f);
                    return;
                }
                return;
            }
            if (i7 == -2) {
                if (!this.f2766c) {
                    this.f2766c = polyvVideoView.isPlaying() || this.f2765b.getSubVideoView().m594w();
                } else if (System.currentTimeMillis() - this.f2770g >= 2000) {
                    this.f2766c = this.f2765b.isPlaying() || this.f2765b.getSubVideoView().m594w();
                }
                this.f2770g = System.currentTimeMillis();
                this.f2765b.m617M(false);
                this.f2767d = true;
                return;
            }
            if (i7 == -1) {
                this.f2766c = polyvVideoView.isPlaying() || this.f2765b.getSubVideoView().m594w();
                this.f2765b.m617M(false);
                this.f2767d = true;
            } else {
                if (i7 != 1) {
                    return;
                }
                if (this.f2767d && this.f2766c) {
                    polyvVideoView.start();
                }
                IMediaPlayer mediaPlayer2 = this.f2765b.getMediaPlayer();
                if (mediaPlayer2 != null) {
                    mediaPlayer2.setVolume(1.0f, 1.0f);
                }
                this.f2767d = false;
                this.f2766c = false;
            }
        }
    }
}
