package p144r2;

import android.content.Context;
import android.media.AudioManager;
import com.cctv.p025tv.module.player.VideoPlayer;
import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import com.tencent.mars.xlog.Log;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Objects;
import java.util.logging.Logger;
import org.fourthline.cling.model.ModelUtil;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.support.avtransport.lastchange.AVTransportVariable;
import org.fourthline.cling.support.lastchange.EventedValue;
import org.fourthline.cling.support.lastchange.LastChange;
import org.fourthline.cling.support.model.Channel;
import org.fourthline.cling.support.model.MediaInfo;
import org.fourthline.cling.support.model.PositionInfo;
import org.fourthline.cling.support.model.StorageMedium;
import org.fourthline.cling.support.model.TransportAction;
import org.fourthline.cling.support.model.TransportInfo;
import org.fourthline.cling.support.model.TransportState;
import org.fourthline.cling.support.renderingcontrol.lastchange.ChannelMute;
import org.fourthline.cling.support.renderingcontrol.lastchange.ChannelVolume;
import org.fourthline.cling.support.renderingcontrol.lastchange.RenderingControlVariable;
import p009b.C0413b;
import p043f.C0988e;
import p078j2.C1186a;
import p118o2.C1581b;
import p136q2.InterfaceC1763c;
import p186x2.C2073a;

/* compiled from: ZxtMediaPlayer.java */
/* renamed from: r2.c */
/* loaded from: classes.dex */
public class C1830c {

    /* renamed from: j */
    public static final Logger f5314j = Logger.getLogger(C1830c.class.getName());

    /* renamed from: a */
    public final UnsignedIntegerFourBytes f5315a;

    /* renamed from: b */
    public final LastChange f5316b;

    /* renamed from: c */
    public final LastChange f5317c;

    /* renamed from: d */
    public volatile TransportInfo f5318d = new TransportInfo();

    /* renamed from: e */
    public PositionInfo f5319e = new PositionInfo();

    /* renamed from: f */
    public MediaInfo f5320f = new MediaInfo();

    /* renamed from: g */
    public double f5321g;

    /* renamed from: h */
    public Context f5322h;

    /* renamed from: i */
    public int f5323i;

    /* compiled from: ZxtMediaPlayer.java */
    /* renamed from: r2.c$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f5324a;

        static {
            int[] iArr = new int[TransportState.values().length];
            f5324a = iArr;
            try {
                iArr[TransportState.STOPPED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5324a[TransportState.PLAYING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5324a[TransportState.PAUSED_PLAYBACK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* compiled from: ZxtMediaPlayer.java */
    /* renamed from: r2.c$b */
    public class b implements InterfaceC1763c {
        public b(a aVar) {
        }

        /* renamed from: a */
        public void m2074a() {
            C2073a.m2459d("onBuffering");
            Log.m655i("XLog_DLNA ", "dlna返回给手机的状态 onBuffering");
            C1830c.this.mo2073f(TransportState.BUFFERING);
        }

        /* renamed from: b */
        public void m2075b(int i7) {
            C2073a.m2459d("onDurationChanged：" + i7);
            synchronized (C1830c.this) {
                String timeString = ModelUtil.toTimeString(i7 / 1000);
                C1830c c1830c = C1830c.this;
                c1830c.f5320f = new MediaInfo(c1830c.f5320f.getCurrentURI(), "", new UnsignedIntegerFourBytes(1L), timeString, StorageMedium.NETWORK);
                C1830c c1830c2 = C1830c.this;
                c1830c2.f5316b.setEventedValue(c1830c2.f5315a, new AVTransportVariable.CurrentTrackDuration(timeString), new AVTransportVariable.CurrentMediaDuration(timeString));
            }
        }

        /* renamed from: c */
        public void m2076c() {
            C2073a.m2459d("onPause");
            Log.m655i("XLog_DLNA ", "dlna返回给手机的状态 onPause");
            C1830c.this.mo2073f(TransportState.PAUSED_PLAYBACK);
        }

        /* renamed from: d */
        public void m2077d(int i7, String str) {
            C2073a.m2459d("onPositionChanged：" + i7 + ";item_id = " + str);
            synchronized (C1830c.this) {
                if (C0988e.m996v()) {
                    return;
                }
                C1830c c1830c = C1830c.this;
                c1830c.f5319e = new PositionInfo(1L, c1830c.f5320f.getMediaDuration(), C1830c.this.f5320f.getCurrentURI(), ModelUtil.toTimeString(i7 / 1000), ModelUtil.toTimeString(i7 / 1000), String.valueOf(i7), "", str);
            }
        }

        /* renamed from: e */
        public void m2078e() {
            Log.m655i("XLog_DLNA ", "dlna返回给手机的状态  onWaiting");
            C1830c.this.mo2073f(TransportState.WAITING);
        }
    }

    public C1830c(UnsignedIntegerFourBytes unsignedIntegerFourBytes, Context context, LastChange lastChange, LastChange lastChange2) {
        this.f5315a = unsignedIntegerFourBytes;
        this.f5322h = context;
        this.f5316b = lastChange;
        this.f5317c = lastChange2;
    }

    /* renamed from: a */
    public synchronized TransportAction[] m2068a() {
        int i7;
        i7 = a.f5324a[this.f5318d.getCurrentTransportState().ordinal()];
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? null : new TransportAction[]{TransportAction.Stop, TransportAction.Pause, TransportAction.Seek, TransportAction.Play} : new TransportAction[]{TransportAction.Stop, TransportAction.Pause, TransportAction.Seek} : new TransportAction[]{TransportAction.Play};
    }

    /* renamed from: b */
    public double m2069b() {
        AudioManager audioManager = (AudioManager) this.f5322h.getSystemService("audio");
        return new BigDecimal(audioManager.getStreamVolume(3) / audioManager.getStreamMaxVolume(3)).setScale(1, 4).doubleValue();
    }

    /* renamed from: c */
    public void m2070c(int i7) {
        if (C1581b.m1836e().f4752h != null) {
            final long j7 = i7;
            final VideoFragment videoFragment = C1581b.m1836e().f4752h.f1992e;
            int i8 = VideoFragment.f854B;
            Objects.requireNonNull(videoFragment);
            Log.m655i("XLog_DLNA ", "fragment 快进快退 position = " + j7);
            if (videoFragment.getActivity() == null || !C1186a.m1389i()) {
                return;
            }
            videoFragment.getActivity().runOnUiThread(new Runnable() { // from class: g2.g
                @Override // java.lang.Runnable
                public final void run() {
                    VideoFragment videoFragment2 = videoFragment;
                    long j8 = j7;
                    int i9 = VideoFragment.f854B;
                    Objects.requireNonNull(videoFragment2);
                    C2073a.m2459d("seekTempPosition 快进到newPos：" + j8);
                    C2073a.m2459d("seekTempPosition isPlaying() = " + videoFragment2.m508l().f596N);
                    if (videoFragment2.m508l().f596N) {
                        videoFragment2.m508l().mo389t((int) j8);
                        videoFragment2.m517u();
                        return;
                    }
                    VideoPlayer videoPlayerM508l = videoFragment2.m508l();
                    videoPlayerM508l.f619n0 = (int) j8;
                    StringBuilder sbM112a = C0413b.m112a("seekTempPosition 临时 = ");
                    sbM112a.append(videoPlayerM508l.f619n0);
                    C2073a.m2459d(sbM112a.toString());
                }
            });
        }
    }

    /* renamed from: d */
    public synchronized void m2071d(URI uri, String str) {
        C2073a.m2459d("设置TV端播放的监听");
        this.f5320f = new MediaInfo(uri.toString(), str);
        this.f5319e = new PositionInfo(1L, "", uri.toString());
        this.f5316b.setEventedValue(this.f5315a, new AVTransportVariable.AVTransportURI(uri), new AVTransportVariable.CurrentTrackURI(uri));
        C1581b.m1836e().f4747c = new b(null);
    }

    /* renamed from: e */
    public synchronized void m2072e(double d7) {
        ChannelMute channelMute;
        this.f5321g = m2069b();
        AudioManager audioManager = (AudioManager) this.f5322h.getSystemService("audio");
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        int iRound = (int) Math.round(streamMaxVolume * d7);
        int streamVolume = audioManager.getStreamVolume(3);
        C2073a.m2459d("setVolume tag " + iRound + " maxVol =" + streamMaxVolume + " setVolume= " + d7 + " storedVolume = " + this.f5321g + " DLNA_CHANNEL = " + C0988e.f1831i + " streamVolume = " + streamVolume);
        if ("huawei".equals(C0988e.f1831i)) {
            try {
                C2073a.m2459d("setVolume tag volTemp =" + this.f5323i);
                int i7 = this.f5323i;
                if (iRound > i7 || (iRound == 100 && streamVolume < 100)) {
                    audioManager.adjustStreamVolume(3, 1, 5);
                    C2073a.m2459d("setVolume tag 音量加");
                } else if (iRound < i7 || (iRound == 0 && streamVolume > 0)) {
                    audioManager.adjustStreamVolume(3, -1, 5);
                    C2073a.m2459d("setVolume tag 音量减");
                }
            } catch (Exception e7) {
                Log.m651e("XLog_DLNA ", "setVolume error = " + e7.getMessage());
            }
            this.f5323i = iRound;
        } else {
            audioManager.setStreamVolume(3, iRound, 5);
        }
        double d8 = this.f5321g;
        if ((d8 != 0.0d || d7 <= 0.0d) && (d8 <= 0.0d || d7 != 0.0d)) {
            channelMute = null;
        } else {
            channelMute = new ChannelMute(Channel.Master, Boolean.valueOf(d8 > 0.0d && d7 == 0.0d));
        }
        LastChange lastChange = this.f5317c;
        UnsignedIntegerFourBytes unsignedIntegerFourBytes = this.f5315a;
        EventedValue[] eventedValueArr = new EventedValue[2];
        eventedValueArr[0] = new RenderingControlVariable.Volume(new ChannelVolume(Channel.Master, Integer.valueOf((int) (d7 * 100.0d))));
        eventedValueArr[1] = channelMute != null ? new RenderingControlVariable.Mute(channelMute) : null;
        lastChange.setEventedValue(unsignedIntegerFourBytes, eventedValueArr);
    }

    /* renamed from: f */
    public synchronized void mo2073f(TransportState transportState) {
        TransportState currentTransportState = this.f5318d.getCurrentTransportState();
        f5314j.fine("Current state is: " + currentTransportState + ", changing to new state: " + transportState);
        this.f5318d = new TransportInfo(transportState);
        this.f5316b.setEventedValue(this.f5315a, new AVTransportVariable.TransportState(transportState), new AVTransportVariable.CurrentTransportActions(m2068a()));
    }
}
