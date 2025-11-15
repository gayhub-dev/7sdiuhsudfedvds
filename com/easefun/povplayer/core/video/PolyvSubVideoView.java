package com.easefun.povplayer.core.video;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.easefun.povplayer.core.video.PolyvVideoView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import p037e1.C0927h;
import p037e1.C0934o;
import p055g3.C1058a;
import p087k3.C1232a;
import p095l3.C1423c;
import p103m3.InterfaceC1464d;
import p103m3.InterfaceC1477q;
import p103m3.InterfaceC1479s;
import p103m3.InterfaceC1480t;
import p103m3.InterfaceC1482v;
import p103m3.InterfaceC1484x;
import p103m3.InterfaceC1485y;
import p103m3.InterfaceC1486z;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: classes.dex */
public class PolyvSubVideoView extends PolyvSubVideoViewListenerEvent {

    /* renamed from: a0 */
    public static final /* synthetic */ int f1106a0 = 0;

    /* renamed from: A */
    public boolean f1107A;

    /* renamed from: B */
    public List<C1058a.c> f1108B;

    /* renamed from: C */
    public int f1109C;

    /* renamed from: D */
    public Uri f1110D;

    /* renamed from: E */
    public Uri f1111E;

    /* renamed from: F */
    public Uri f1112F;

    /* renamed from: G */
    public int f1113G;

    /* renamed from: H */
    public int f1114H;

    /* renamed from: I */
    public int f1115I;

    /* renamed from: J */
    public int f1116J;

    /* renamed from: K */
    public boolean f1117K;

    /* renamed from: L */
    public int f1118L;

    /* renamed from: M */
    public HashMap<String, Object> f1119M;

    /* renamed from: N */
    public int f1120N;

    /* renamed from: O */
    public C1232a f1121O;

    /* renamed from: P */
    public int f1122P;

    /* renamed from: Q */
    public Handler f1123Q;

    /* renamed from: R */
    public IMediaPlayer.OnCompletionListener f1124R;

    /* renamed from: S */
    public IMediaPlayer.OnSeekCompleteListener f1125S;

    /* renamed from: T */
    public IMediaPlayer.OnVideoSizeChangedListener f1126T;

    /* renamed from: U */
    public IMediaPlayer.OnPreparedListener f1127U;

    /* renamed from: V */
    public IMediaPlayer.OnErrorListener f1128V;

    /* renamed from: W */
    public IMediaPlayer.OnInfoListener f1129W;

    /* renamed from: t */
    public int f1130t;

    /* renamed from: u */
    public Context f1131u;

    /* renamed from: v */
    public boolean f1132v;

    /* renamed from: w */
    public int f1133w;

    /* renamed from: x */
    public View f1134x;

    /* renamed from: y */
    public boolean f1135y;

    /* renamed from: z */
    public boolean f1136z;

    /* renamed from: com.easefun.povplayer.core.video.PolyvSubVideoView$a */
    public class HandlerC0737a extends Handler {
        public HandlerC0737a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i7 = message.what;
            if (i7 != 1 && i7 != 3) {
                if (i7 != 12) {
                    if (i7 != 13) {
                        return;
                    }
                    PolyvSubVideoView.this.setPlayerBufferingViewVisibility(0);
                    return;
                } else {
                    PolyvSubVideoView polyvSubVideoView = PolyvSubVideoView.this;
                    int i8 = PolyvSubVideoView.f1106a0;
                    polyvSubVideoView.m591t(C1423c.m1607a(polyvSubVideoView.getCurrentPlayPath(), -1020, polyvSubVideoView.getPlayStage()));
                    polyvSubVideoView.f1105e.mo583j();
                    return;
                }
            }
            PolyvSubVideoView polyvSubVideoView2 = PolyvSubVideoView.this;
            int i9 = message.arg1;
            int i10 = polyvSubVideoView2.f1115I;
            InterfaceC1484x interfaceC1484x = polyvSubVideoView2.f1147s;
            if (interfaceC1484x != null) {
                C0934o c0934o = (C0934o) interfaceC1484x;
                if (i7 == 1) {
                    c0934o.f1698a.f505m.m372w(i9, i10);
                }
            }
            InterfaceC1485y interfaceC1485y = polyvSubVideoView2.f1146r;
            if (interfaceC1485y != null) {
                PolyvVideoView.C0753j c0753j = (PolyvVideoView.C0753j) interfaceC1485y;
                if (i9 - i10 == PolyvVideoView.this.f1171b0 && i7 == 1 && c0753j.f1199a.mo578e() && !c0753j.f1199a.m592u() && PolyvVideoView.this.m608D()) {
                    PolyvVideoView polyvVideoView = PolyvVideoView.this;
                    Uri uri = polyvVideoView.f1156J;
                    if (polyvVideoView.m618N(true)) {
                        polyvVideoView.f1105e.mo575b(uri, polyvVideoView.f1169W);
                    }
                }
            }
            if (PolyvSubVideoView.this.mo578e()) {
                PolyvSubVideoView polyvSubVideoView3 = PolyvSubVideoView.this;
                int i11 = polyvSubVideoView3.f1115I - 1;
                polyvSubVideoView3.f1115I = i11;
                if (i11 >= 0) {
                    Message messageObtainMessage = polyvSubVideoView3.f1123Q.obtainMessage();
                    messageObtainMessage.copyFrom(message);
                    sendMessageDelayed(messageObtainMessage, 1000L);
                    return;
                }
                polyvSubVideoView3.mo574a(false);
                PolyvSubVideoView polyvSubVideoView4 = PolyvSubVideoView.this;
                polyvSubVideoView4.setTargetState(polyvSubVideoView4.getStatePlaybackCompletedCode());
                int i12 = message.what;
                if (i12 != 1) {
                    if (i12 == 3) {
                        PolyvSubVideoView.this.m598p((IMediaPlayer) message.obj, 3);
                        PolyvSubVideoView.this.f1130t = 33;
                        return;
                    }
                    return;
                }
                PolyvSubVideoView.this.m598p((IMediaPlayer) message.obj, 1);
                if (PolyvSubVideoView.this.getTargetState() == PolyvSubVideoView.this.getStatePlaybackCompletedCode() && PolyvSubVideoView.this.m592u()) {
                    PolyvSubVideoView.this.m587A();
                } else if (PolyvSubVideoView.this.getTargetState() == PolyvSubVideoView.this.getStatePlaybackCompletedCode()) {
                    PolyvSubVideoView polyvSubVideoView5 = PolyvSubVideoView.this;
                    if (polyvSubVideoView5.f1107A) {
                        polyvSubVideoView5.m588B();
                    }
                }
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvSubVideoView$b */
    public class C0738b implements IMediaPlayer.OnCompletionListener {
        public C0738b() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            PolyvSubVideoView.this.setPlayerBufferingViewVisibility(8);
            PolyvSubVideoView polyvSubVideoView = PolyvSubVideoView.this;
            if (polyvSubVideoView.f1130t == 2) {
                InterfaceC1479s interfaceC1479s = polyvSubVideoView.f1095g;
                if (interfaceC1479s != null) {
                    interfaceC1479s.onCompletion(iMediaPlayer);
                }
                PolyvSubVideoView polyvSubVideoView2 = PolyvSubVideoView.this;
                if (!(polyvSubVideoView2.mo578e() && polyvSubVideoView2.getCurrentState() == polyvSubVideoView2.getStatePlaybackCompletedCode()) || PolyvSubVideoView.this.getTargetState() == PolyvSubVideoView.this.getStatePauseCode()) {
                    return;
                }
                PolyvSubVideoView.this.start();
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvSubVideoView$c */
    public class C0739c implements IMediaPlayer.OnSeekCompleteListener {
        public C0739c() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            InterfaceC1482v interfaceC1482v = PolyvSubVideoView.this.f1099k;
            if (interfaceC1482v != null) {
                TextUtils.isEmpty("onSeekComplete");
                ((C0927h) interfaceC1482v).f1691a.mo386q(iMediaPlayer);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvSubVideoView$d */
    public class C0740d implements IMediaPlayer.OnVideoSizeChangedListener {
        public C0740d() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i7, int i8, int i9, int i10) {
            InterfaceC1486z interfaceC1486z = PolyvSubVideoView.this.f1100l;
            if (interfaceC1486z != null) {
                interfaceC1486z.onVideoSizeChanged(iMediaPlayer, i7, i8, i9, i10);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvSubVideoView$e */
    public class C0741e implements IMediaPlayer.OnPreparedListener {

        /* renamed from: com.easefun.povplayer.core.video.PolyvSubVideoView$e$a */
        public class a implements IMediaPlayer.OnBufferingUpdateListener {
            public a() {
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i7) {
                PolyvSubVideoView polyvSubVideoView = PolyvSubVideoView.this;
                polyvSubVideoView.f1133w = i7;
                polyvSubVideoView.m571m(iMediaPlayer, i7);
            }
        }

        public C0741e() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            PolyvSubVideoView polyvSubVideoView = PolyvSubVideoView.this;
            polyvSubVideoView.setOnSeekCompleteListener(polyvSubVideoView.f1125S);
            PolyvSubVideoView polyvSubVideoView2 = PolyvSubVideoView.this;
            polyvSubVideoView2.setOnVideoSizeChangedListener(polyvSubVideoView2.f1126T);
            if (iMediaPlayer != null) {
                iMediaPlayer.setOnBufferingUpdateListener(new a());
            }
            PolyvSubVideoView.this.m590D();
            PolyvSubVideoView.this.setPlayerBufferingViewVisibility(8);
            InterfaceC1480t interfaceC1480t = PolyvSubVideoView.this.f1096h;
            if (interfaceC1480t != null) {
                interfaceC1480t.onPrepared(iMediaPlayer);
            }
            int iMax = Math.max(PolyvSubVideoView.this.getDuration() / 1000, 1);
            PolyvSubVideoView polyvSubVideoView3 = PolyvSubVideoView.this;
            int i7 = polyvSubVideoView3.f1130t;
            if (i7 == 1) {
                int i8 = polyvSubVideoView3.f1113G;
                if (i8 <= 0 || i8 > iMax) {
                    polyvSubVideoView3.f1113G = iMax;
                }
                int i9 = polyvSubVideoView3.f1113G;
                polyvSubVideoView3.f1116J = i9;
                polyvSubVideoView3.f1115I = i9;
            } else if (i7 != 3) {
                polyvSubVideoView3.f1115I = 0;
            } else {
                int i10 = polyvSubVideoView3.f1114H;
                if (i10 <= 0 || i10 > iMax) {
                    polyvSubVideoView3.f1114H = iMax;
                }
                int i11 = polyvSubVideoView3.f1114H;
                polyvSubVideoView3.f1116J = i11;
                polyvSubVideoView3.f1115I = i11;
            }
            polyvSubVideoView3.f1117K = false;
            if (polyvSubVideoView3.getTargetState() != PolyvSubVideoView.this.getStatePauseCode()) {
                PolyvSubVideoView polyvSubVideoView4 = PolyvSubVideoView.this;
                polyvSubVideoView4.f1117K = true;
                polyvSubVideoView4.m597z(true);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvSubVideoView$f */
    public class C0742f implements IMediaPlayer.OnErrorListener {
        public C0742f() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
        public boolean onError(IMediaPlayer iMediaPlayer, int i7, int i8) {
            InterfaceC1464d interfaceC1464d = PolyvSubVideoView.this.f1097i;
            if (interfaceC1464d != null) {
                interfaceC1464d.onError(iMediaPlayer, i7, i8);
            }
            PolyvSubVideoView polyvSubVideoView = PolyvSubVideoView.this;
            polyvSubVideoView.m591t(C1423c.m1607a(iMediaPlayer != null ? iMediaPlayer.getDataSource() : polyvSubVideoView.getCurrentPlayPath(), i7, PolyvSubVideoView.this.getPlayStage()));
            return true;
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvSubVideoView$g */
    public class C0743g implements IMediaPlayer.OnInfoListener {
        public C0743g() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i7, Object obj) {
            InterfaceC1477q interfaceC1477q = PolyvSubVideoView.this.f1098j;
            if (interfaceC1477q != null && (obj instanceof Integer)) {
                interfaceC1477q.mo854a(iMediaPlayer, i7, ((Integer) obj).intValue());
            }
            if (PolyvSubVideoView.this.getMediaPlayer() == null) {
                return true;
            }
            if (i7 == 701) {
                PolyvSubVideoView polyvSubVideoView = PolyvSubVideoView.this;
                int i8 = PolyvSubVideoView.f1106a0;
                Objects.requireNonNull(polyvSubVideoView);
                PolyvSubVideoView.this.setPlayerBufferingViewVisibility(0);
                return true;
            }
            if (i7 != 702) {
                return true;
            }
            PolyvSubVideoView polyvSubVideoView2 = PolyvSubVideoView.this;
            int i9 = PolyvSubVideoView.f1106a0;
            Objects.requireNonNull(polyvSubVideoView2);
            PolyvSubVideoView.this.setPlayerBufferingViewVisibility(8);
            return true;
        }
    }

    public PolyvSubVideoView(Context context) {
        this(context, null);
    }

    private void getNextHeadAd() {
        if (m592u()) {
            List<C1058a.c> list = this.f1108B;
            int i7 = this.f1109C + 1;
            this.f1109C = i7;
            C1058a.c cVar = list.get(i7);
            try {
                Objects.requireNonNull(cVar);
                this.f1110D = Uri.parse(null);
            } catch (NullPointerException unused) {
                this.f1110D = null;
            }
            Objects.requireNonNull(cVar);
            this.f1113G = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayerBufferingViewVisibility(int i7) {
        View view = this.f1134x;
        if (view != null) {
            view.setVisibility(i7);
        }
        if (i7 == 8) {
            this.f1123Q.removeMessages(13);
        }
    }

    /* renamed from: A */
    public void m587A() {
        getNextHeadAd();
        this.f1130t = 1;
        setVideoURI(this.f1110D);
    }

    /* renamed from: B */
    public void m588B() {
        this.f1130t = 2;
        setVideoURI(this.f1112F);
    }

    /* renamed from: C */
    public final void m589C() {
        this.f1123Q.removeMessages(1);
        this.f1123Q.removeMessages(3);
    }

    /* renamed from: D */
    public final void m590D() {
        this.f1123Q.removeMessages(12);
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1421a
    /* renamed from: a */
    public void mo574a(boolean z6) {
        this.f1105e.mo574a(z6);
        setTargetState(getStateIdleCode());
        setPlayerBufferingViewVisibility(8);
        this.f1133w = 0;
        m589C();
        m590D();
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1421a
    /* renamed from: b */
    public void mo575b(Uri uri, Map<String, String> map) {
        if (m596y()) {
            super.mo575b(uri, map);
        }
    }

    public int getAspectRatio() {
        return getCurrentAspectRatio();
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (getMediaPlayer() != null) {
            return this.f1133w;
        }
        return 0;
    }

    public String getCurrentPlayPath() {
        int i7 = this.f1130t;
        Uri uri = i7 == 1 ? this.f1110D : i7 == 3 ? this.f1111E : i7 == 2 ? this.f1112F : null;
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }

    public String getHeadAdUrl() {
        Uri uri = this.f1110D;
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }

    public int getPlayStage() {
        return this.f1130t;
    }

    public String getTailAdUrl() {
        Uri uri = this.f1111E;
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }

    public String getTeaserUrl() {
        if (this.f1112F == null) {
            return null;
        }
        return this.f1111E.toString();
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return super.isPlaying() || getTargetState() == getStatePlayingCode();
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public void pause() {
        m595x(true);
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i7) {
        if (i7 >= getDuration()) {
            i7 = getDuration() - 100;
        } else if (i7 < 0) {
            i7 = 0;
        }
        super.seekTo(i7);
    }

    public void setPlayerBufferingIndicator(View view) {
        this.f1134x = view;
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1421a
    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1421a
    public void setVideoURI(Uri uri) {
        if (m596y()) {
            super.setVideoURI(uri);
        }
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public void start() {
        if (this.f1117K) {
            m597z(false);
        } else if (m597z(true)) {
            this.f1117K = true;
        }
    }

    /* renamed from: t */
    public final void m591t(C1423c c1423c) {
        m589C();
        m590D();
        setPlayerBufferingViewVisibility(8);
        setTargetState(getStatePlaybackCompletedCode());
        int i7 = this.f1130t;
        if (i7 != 1) {
            if (i7 == 2) {
                m599q(c1423c);
                return;
            } else {
                if (i7 != 3) {
                    return;
                }
                m599q(c1423c);
                return;
            }
        }
        m599q(c1423c);
        if (getTargetState() == getStatePlaybackCompletedCode() && m592u()) {
            m587A();
        } else if (getTargetState() == getStatePlaybackCompletedCode() && this.f1107A) {
            m588B();
        }
    }

    /* renamed from: u */
    public boolean m592u() {
        List<C1058a.c> list = this.f1108B;
        return (list == null || this.f1109C == list.size() - 1) ? false : true;
    }

    /* renamed from: v */
    public void m593v() {
        if (getVisibility() == 0) {
            setVisibility(8);
            m600r(false);
        }
        setPlayerBufferingViewVisibility(8);
    }

    /* renamed from: w */
    public boolean m594w() {
        return getVisibility() == 0;
    }

    /* renamed from: x */
    public void m595x(boolean z6) {
        setTargetState(getStatePauseCode());
        if (!mo578e() || this.f1115I < 0) {
            return;
        }
        m589C();
        if (z6) {
            this.f1121O.m1455a();
        }
        this.f1105e.pause();
        InterfaceC1479s interfaceC1479s = this.f1095g;
        if (interfaceC1479s != null) {
            interfaceC1479s.onPause();
        }
    }

    /* renamed from: y */
    public final boolean m596y() {
        if (this.f1132v) {
            return false;
        }
        if (getVisibility() != 0) {
            setVisibility(0);
            m600r(true);
        }
        mo574a(false);
        InterfaceC1479s interfaceC1479s = this.f1095g;
        if (interfaceC1479s != null) {
            interfaceC1479s.mo855a();
        }
        this.f1123Q.removeMessages(13);
        this.f1123Q.sendEmptyMessageDelayed(13, this.f1122P);
        this.f1105e.mo582i();
        setOnCompletionListener(this.f1124R);
        setOnPreparedListener(this.f1127U);
        setOnErrorListener(this.f1128V);
        setOnInfoListener(this.f1129W);
        if (getCurrentPlayPath() == null) {
            m591t(C1423c.m1607a(getCurrentPlayPath(), NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, getPlayStage()));
            this.f1105e.mo583j();
            return false;
        }
        this.f1123Q.removeMessages(12);
        this.f1123Q.sendEmptyMessageDelayed(12, this.f1118L * 1000);
        return true;
    }

    /* renamed from: z */
    public final boolean m597z(boolean z6) {
        setTargetState(getStatePlayingCode());
        if (!mo578e() || this.f1115I < 0) {
            return false;
        }
        m589C();
        if (this.f1115I >= 0 && this.f1130t != 2) {
            Message messageObtainMessage = this.f1123Q.obtainMessage();
            messageObtainMessage.obj = getMediaPlayer();
            messageObtainMessage.what = this.f1130t;
            messageObtainMessage.arg1 = this.f1116J;
            this.f1123Q.sendMessage(messageObtainMessage);
        }
        this.f1121O.m1456b();
        this.f1105e.start();
        InterfaceC1479s interfaceC1479s = this.f1095g;
        if (interfaceC1479s == null) {
            return true;
        }
        interfaceC1479s.mo856b(z6);
        return true;
    }

    public PolyvSubVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PolyvSubVideoView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1130t = 0;
        this.f1109C = -1;
        this.f1120N = 4;
        this.f1123Q = new HandlerC0737a(Looper.myLooper());
        this.f1124R = new C0738b();
        this.f1125S = new C0739c();
        this.f1126T = new C0740d();
        this.f1127U = new C0741e();
        this.f1128V = new C0742f();
        this.f1129W = new C0743g();
        if (this.f1131u == null) {
            this.f1131u = context;
            PolyvIjkVideoView polyvIjkVideoView = new PolyvIjkVideoView(context);
            this.f1105e = polyvIjkVideoView;
            addView(polyvIjkVideoView);
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
            setIjkLogLevel(4);
            setLogTag("PolyvSubVideoView");
        }
    }
}
