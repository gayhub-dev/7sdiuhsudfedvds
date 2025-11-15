package com.easefun.povplayer.core.video;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.easefun.povplayer.core.video.C0757a;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import p035e.C0901n;
import p037e1.C0927h;
import p037e1.C0929j;
import p037e1.C0942w;
import p055g3.C1058a;
import p079j3.InterfaceC1199b;
import p087k3.C1232a;
import p095l3.C1423c;
import p095l3.C1424d;
import p095l3.C1433m;
import p103m3.InterfaceC1461a;
import p103m3.InterfaceC1462b;
import p103m3.InterfaceC1469i;
import p103m3.InterfaceC1470j;
import p103m3.InterfaceC1471k;
import p103m3.InterfaceC1472l;
import p103m3.InterfaceC1477q;
import p103m3.InterfaceC1478r;
import p103m3.InterfaceC1479s;
import p103m3.InterfaceC1480t;
import p103m3.InterfaceC1481u;
import p103m3.InterfaceC1482v;
import p103m3.InterfaceC1485y;
import p103m3.InterfaceC1486z;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.MediaPlayerProxy;
import tv.danmaku.ijk.media.player.format.mpegts.PesPrivateDataTimeStamp;

/* loaded from: classes.dex */
public class PolyvVideoView extends PolyvVideoViewListenerEvent {

    /* renamed from: u0 */
    public static final /* synthetic */ int f1148u0 = 0;

    /* renamed from: C */
    public float f1149C;

    /* renamed from: D */
    public float f1150D;

    /* renamed from: E */
    public boolean f1151E;

    /* renamed from: F */
    public GestureDetector f1152F;

    /* renamed from: G */
    public int f1153G;

    /* renamed from: H */
    public Context f1154H;

    /* renamed from: I */
    public boolean f1155I;

    /* renamed from: J */
    public Uri f1156J;

    /* renamed from: K */
    public boolean f1157K;

    /* renamed from: L */
    public int f1158L;

    /* renamed from: M */
    public View f1159M;

    /* renamed from: N */
    public InterfaceC1199b f1160N;

    /* renamed from: O */
    public PolyvSubVideoView f1161O;

    /* renamed from: P */
    public boolean f1162P;

    /* renamed from: Q */
    public int f1163Q;

    /* renamed from: R */
    public int f1164R;

    /* renamed from: S */
    public int f1165S;

    /* renamed from: T */
    public C1058a f1166T;

    /* renamed from: U */
    public HashMap<String, Object> f1167U;

    /* renamed from: V */
    public int f1168V;

    /* renamed from: W */
    public Map<String, String> f1169W;

    /* renamed from: a0 */
    public C1232a f1170a0;

    /* renamed from: b0 */
    public int f1171b0;

    /* renamed from: c0 */
    public boolean f1172c0;

    /* renamed from: d0 */
    public C0901n f1173d0;

    /* renamed from: e0 */
    public int f1174e0;

    /* renamed from: f0 */
    public PolyvIjkVideoView f1175f0;

    /* renamed from: g0 */
    public int f1176g0;

    /* renamed from: h0 */
    public boolean f1177h0;

    /* renamed from: i0 */
    public Handler f1178i0;

    /* renamed from: j0 */
    public IMediaPlayer.OnCompletionListener f1179j0;

    /* renamed from: k0 */
    public IMediaPlayer.OnSeekCompleteListener f1180k0;

    /* renamed from: l0 */
    public IMediaPlayer.OnVideoSizeChangedListener f1181l0;

    /* renamed from: m0 */
    public IMediaPlayer.OnPreparedListener f1182m0;

    /* renamed from: n0 */
    public IMediaPlayer.OnPreparedListener f1183n0;

    /* renamed from: o0 */
    public IMediaPlayer.OnPreparedListener f1184o0;

    /* renamed from: p0 */
    public IMediaPlayer.OnErrorListener f1185p0;

    /* renamed from: q0 */
    public IMediaPlayer.OnInfoListener f1186q0;

    /* renamed from: r0 */
    public IMediaPlayer.OnSEIRefreshListener f1187r0;

    /* renamed from: s0 */
    public final IMediaPlayer.OnAudioVividMetadataListener f1188s0;

    /* renamed from: t0 */
    public final IMediaPlayer.OnMpegTsPesPrivateDataListener f1189t0;

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$a */
    public class C0744a implements IMediaPlayer.OnPreparedListener {
        public C0744a() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            PolyvVideoView.m602x(PolyvVideoView.this, iMediaPlayer);
            PolyvVideoView polyvVideoView = PolyvVideoView.this;
            polyvVideoView.f1162P = false;
            if (polyvVideoView.getTargetState() != PolyvVideoView.this.getStatePauseCode()) {
                C0901n c0901n = PolyvVideoView.this.f1173d0;
                if (c0901n != null) {
                    c0901n.m813a();
                    PolyvVideoView polyvVideoView2 = PolyvVideoView.this;
                    polyvVideoView2.f1173d0.m815c(polyvVideoView2.f1154H);
                }
                PolyvVideoView polyvVideoView3 = PolyvVideoView.this;
                polyvVideoView3.f1162P = true;
                polyvVideoView3.m620P(true);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$b */
    public class C0745b implements IMediaPlayer.OnPreparedListener {
        public C0745b() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            PolyvVideoView.m602x(PolyvVideoView.this, iMediaPlayer);
            PolyvVideoView.this.pause();
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$c */
    public class C0746c implements IMediaPlayer.OnPreparedListener {
        public C0746c() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            PolyvVideoView polyvVideoView = PolyvVideoView.this;
            int i7 = PolyvVideoView.f1148u0;
            polyvVideoView.m621Q();
            PolyvVideoView.this.setPlayerBufferingViewVisibility(8);
            PolyvVideoView polyvVideoView2 = PolyvVideoView.this;
            int i8 = polyvVideoView2.f1165S;
            polyvVideoView2.f1165S = 0;
            if (polyvVideoView2.getTargetState() != PolyvVideoView.this.getStatePauseCode()) {
                C0901n c0901n = PolyvVideoView.this.f1173d0;
                if (c0901n != null) {
                    c0901n.m813a();
                    PolyvVideoView polyvVideoView3 = PolyvVideoView.this;
                    polyvVideoView3.f1173d0.m815c(polyvVideoView3.f1154H);
                }
                PolyvVideoView.this.m620P(false);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$d */
    public class C0747d implements IMediaPlayer.OnErrorListener {
        public C0747d() {
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
        @android.annotation.SuppressLint({"WrongConstant"})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean onError(tv.danmaku.ijk.media.player.IMediaPlayer r5, int r6, int r7) {
            /*
                r4 = this;
                com.easefun.povplayer.core.video.PolyvVideoView r0 = com.easefun.povplayer.core.video.PolyvVideoView.this
                boolean r0 = r0.m616L()
                r1 = 1
                if (r0 != 0) goto L24
                com.easefun.povplayer.core.video.PolyvVideoView r0 = com.easefun.povplayer.core.video.PolyvVideoView.this
                int r2 = r0.f1164R
                int r3 = r0.f1165S
                if (r2 != r3) goto L12
                goto L24
            L12:
                int r3 = r3 + r1
                r0.f1165S = r3
                int r5 = com.easefun.povplayer.core.video.PolyvVideoView.f1148u0
                com.easefun.povplayer.core.video.PolyvVideoView.m604z(r0)
                com.easefun.povplayer.core.video.PolyvVideoView r5 = com.easefun.povplayer.core.video.PolyvVideoView.this
                int r6 = r5.getStatePlayingCode()
                r5.setTargetState(r6)
                goto L45
            L24:
                com.easefun.povplayer.core.video.PolyvVideoView r0 = com.easefun.povplayer.core.video.PolyvVideoView.this
                m3.d r0 = r0.f1097i
                if (r0 == 0) goto L2d
                r0.onError(r5, r6, r7)
            L2d:
                com.easefun.povplayer.core.video.PolyvVideoView r7 = com.easefun.povplayer.core.video.PolyvVideoView.this
                if (r5 == 0) goto L36
                java.lang.String r5 = r5.getDataSource()
                goto L3a
            L36:
                java.lang.String r5 = r7.getCurrentPlayPath()
            L3a:
                com.easefun.povplayer.core.video.PolyvVideoView r0 = com.easefun.povplayer.core.video.PolyvVideoView.this
                int r0 = r0.f1168V
                l3.c r5 = p095l3.C1423c.m1607a(r5, r6, r0)
                com.easefun.povplayer.core.video.PolyvVideoView.m603y(r7, r5)
            L45:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.easefun.povplayer.core.video.PolyvVideoView.C0747d.onError(tv.danmaku.ijk.media.player.IMediaPlayer, int, int):boolean");
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$e */
    public class C0748e implements IMediaPlayer.OnInfoListener {
        public C0748e() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i7, Object obj) {
            InterfaceC1477q interfaceC1477q = PolyvVideoView.this.f1098j;
            if (interfaceC1477q != null && (obj instanceof Integer)) {
                interfaceC1477q.mo854a(iMediaPlayer, i7, ((Integer) obj).intValue());
            }
            if (PolyvVideoView.this.getMediaPlayer() != null) {
                if (i7 == 701) {
                    PolyvVideoView polyvVideoView = PolyvVideoView.this;
                    polyvVideoView.f1157K = true;
                    polyvVideoView.setPlayerBufferingViewVisibility(0);
                } else if (i7 == 702) {
                    PolyvVideoView polyvVideoView2 = PolyvVideoView.this;
                    polyvVideoView2.f1157K = false;
                    polyvVideoView2.setPlayerBufferingViewVisibility(8);
                }
            }
            return true;
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$f */
    public class C0749f implements IMediaPlayer.OnSEIRefreshListener {
        public C0749f() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSEIRefreshListener
        public void onSEIRefresh(IMediaPlayer iMediaPlayer, int i7, int i8) {
            InterfaceC1481u interfaceC1481u = PolyvVideoView.this.f1102n;
            if (interfaceC1481u != null) {
                interfaceC1481u.onSEIRefresh(iMediaPlayer, i7, i8);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$g */
    public class C0750g implements IMediaPlayer.OnAudioVividMetadataListener {
        public C0750g() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnAudioVividMetadataListener
        public void onMetadataChanged(IMediaPlayer iMediaPlayer) {
            InterfaceC1461a interfaceC1461a = PolyvVideoView.this.f1103o;
            if (interfaceC1461a != null) {
                interfaceC1461a.onMetadataChanged(iMediaPlayer);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$h */
    public class C0751h implements IMediaPlayer.OnMpegTsPesPrivateDataListener {
        public C0751h() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnMpegTsPesPrivateDataListener
        public void onPesPrivateData(IMediaPlayer iMediaPlayer, PesPrivateDataTimeStamp pesPrivateDataTimeStamp) {
            InterfaceC1462b interfaceC1462b = PolyvVideoView.this.f1104p;
            if (interfaceC1462b != null) {
                ((C0929j) interfaceC1462b).f1693a.mo377g(iMediaPlayer, pesPrivateDataTimeStamp);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$i */
    public class HandlerC0752i extends Handler {
        public HandlerC0752i(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 12) {
                PolyvVideoView polyvVideoView = PolyvVideoView.this;
                int i7 = PolyvVideoView.f1148u0;
                polyvVideoView.m606B(-1020);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$j */
    public class C0753j implements InterfaceC1485y {

        /* renamed from: a */
        public final /* synthetic */ PolyvSubVideoView f1199a;

        public C0753j(PolyvSubVideoView polyvSubVideoView) {
            this.f1199a = polyvSubVideoView;
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$k */
    public class C0754k implements IMediaPlayer.OnCompletionListener {
        public C0754k() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            PolyvVideoView.this.setPlayerBufferingViewVisibility(8);
            InterfaceC1479s interfaceC1479s = PolyvVideoView.this.f1095g;
            if (interfaceC1479s != null) {
                interfaceC1479s.onCompletion(iMediaPlayer);
            }
            if (PolyvVideoView.this.m612H()) {
                PolyvSubVideoView polyvSubVideoView = PolyvVideoView.this.f1161O;
                if (!polyvSubVideoView.f1136z || polyvSubVideoView.getPlayStage() == 33) {
                    return;
                }
                PolyvSubVideoView polyvSubVideoView2 = PolyvVideoView.this.f1161O;
                polyvSubVideoView2.f1130t = 3;
                polyvSubVideoView2.setVideoURI(polyvSubVideoView2.f1111E);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$l */
    public class C0755l implements IMediaPlayer.OnSeekCompleteListener {
        public C0755l() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            InterfaceC1482v interfaceC1482v = PolyvVideoView.this.f1099k;
            if (interfaceC1482v != null) {
                TextUtils.isEmpty("onSeekComplete");
                ((C0927h) interfaceC1482v).f1691a.mo386q(iMediaPlayer);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.video.PolyvVideoView$m */
    public class C0756m implements IMediaPlayer.OnVideoSizeChangedListener {
        public C0756m() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i7, int i8, int i9, int i10) {
            InterfaceC1486z interfaceC1486z = PolyvVideoView.this.f1100l;
            if (interfaceC1486z != null) {
                interfaceC1486z.onVideoSizeChanged(iMediaPlayer, i7, i8, i9, i10);
            }
        }
    }

    public PolyvVideoView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayerBufferingViewVisibility(int i7) {
        View view = this.f1159M;
        if (view != null) {
            view.setVisibility(i7);
        }
    }

    /* renamed from: x */
    public static void m602x(PolyvVideoView polyvVideoView, IMediaPlayer iMediaPlayer) {
        IMediaPlayer internalMediaPlayer;
        Objects.requireNonNull(polyvVideoView);
        if (iMediaPlayer == null) {
            return;
        }
        IjkMediaPlayer ijkMediaPlayer = iMediaPlayer instanceof IjkMediaPlayer ? (IjkMediaPlayer) iMediaPlayer : ((iMediaPlayer instanceof MediaPlayerProxy) && (internalMediaPlayer = ((MediaPlayerProxy) iMediaPlayer).getInternalMediaPlayer()) != null && (internalMediaPlayer instanceof IjkMediaPlayer)) ? (IjkMediaPlayer) internalMediaPlayer : null;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.getVideoDecoder();
        }
        polyvVideoView.setOnSeekCompleteListener(polyvVideoView.f1180k0);
        polyvVideoView.setOnVideoSizeChangedListener(polyvVideoView.f1181l0);
        iMediaPlayer.setOnBufferingUpdateListener(new C1433m(polyvVideoView));
        polyvVideoView.m621Q();
        polyvVideoView.setPlayerBufferingViewVisibility(8);
        InterfaceC1480t interfaceC1480t = polyvVideoView.f1096h;
        if (interfaceC1480t != null) {
            interfaceC1480t.onPrepared(iMediaPlayer);
        }
        InterfaceC1199b interfaceC1199b = polyvVideoView.f1160N;
        if (interfaceC1199b instanceof PolyvBaseMediaController) {
            ((PolyvBaseMediaController) interfaceC1199b).mo351a(iMediaPlayer);
        }
    }

    /* renamed from: y */
    public static void m603y(PolyvVideoView polyvVideoView, C1423c c1423c) {
        polyvVideoView.m621Q();
        polyvVideoView.setPlayerBufferingViewVisibility(8);
        InterfaceC1478r interfaceC1478r = polyvVideoView.f1206q;
        if (interfaceC1478r != null) {
            ((C0942w) interfaceC1478r).f1705a.mo379j(c1423c);
        }
    }

    /* renamed from: z */
    public static void m604z(PolyvVideoView polyvVideoView) {
        if (!polyvVideoView.f1177h0) {
            polyvVideoView.f1170a0.m1456b();
        }
        polyvVideoView.setPlayerBufferingViewVisibility(0);
        polyvVideoView.f1105e.mo582i();
        polyvVideoView.setOnPreparedListener(polyvVideoView.f1184o0);
        polyvVideoView.f1178i0.removeMessages(12);
        polyvVideoView.f1178i0.sendEmptyMessageDelayed(12, polyvVideoView.f1163Q * 1000);
        InterfaceC1199b interfaceC1199b = polyvVideoView.f1160N;
        if (interfaceC1199b != null) {
            interfaceC1199b.hide();
        }
        polyvVideoView.f1105e.mo585l();
    }

    /* renamed from: A */
    public final void m605A() {
        if (!m608D()) {
            this.f1161O.m588B();
            return;
        }
        if (mo578e()) {
            C0901n c0901n = this.f1173d0;
            if (c0901n != null) {
                c0901n.m813a();
                this.f1173d0.m815c(this.f1154H);
            }
            start();
            return;
        }
        if (getMediaPlayer() != null && getCurrentState() == getStatePreparingCode()) {
            setOnPreparedListener(this.f1182m0);
        } else if (getCurrentState() != getStateErrorCode()) {
            setVideoURI(this.f1156J);
        }
    }

    @SuppressLint({"WrongConstant"})
    /* renamed from: B */
    public final void m606B(int i7) {
        C1423c c1423cM1607a = C1423c.m1607a(getCurrentPlayPath(), i7, this.f1168V);
        m621Q();
        setPlayerBufferingViewVisibility(8);
        InterfaceC1478r interfaceC1478r = this.f1206q;
        if (interfaceC1478r != null) {
            ((C0942w) interfaceC1478r).f1705a.mo379j(c1423cM1607a);
        }
        this.f1105e.mo583j();
    }

    /* renamed from: C */
    public boolean m607C() {
        return this.f1161O.getPlayStage() == 1 && this.f1161O.m594w();
    }

    /* renamed from: D */
    public final boolean m608D() {
        return m616L() || !this.f1161O.f1107A;
    }

    /* renamed from: E */
    public final void m609E(boolean z6) {
        this.f1161O.mo574a(false);
        mo574a(false);
        if (!this.f1177h0) {
            this.f1170a0.m1455a();
        }
        this.f1161O.m593v();
        PolyvSubVideoView polyvSubVideoView = this.f1161O;
        polyvSubVideoView.f1130t = 0;
        polyvSubVideoView.f1109C = -1;
        polyvSubVideoView.f1105e.mo582i();
        this.f1161O.f1105e.mo581h();
        this.f1105e.mo582i();
        if (z6 || !this.f1172c0) {
            this.f1105e.mo581h();
        }
    }

    /* renamed from: F */
    public int m610F(Activity activity) {
        try {
            float f7 = activity.getWindow().getAttributes().screenBrightness;
            return f7 == -1.0f ? Math.round((Settings.System.getInt(activity.getContentResolver(), "screen_brightness") * 100) / 255.0f) : Math.round(f7 * 100.0f);
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: G */
    public boolean m611G() {
        return mo578e() && this.f1157K;
    }

    /* renamed from: H */
    public boolean m612H() {
        return mo578e() && getCurrentState() == getStatePlaybackCompletedCode();
    }

    /* renamed from: I */
    public boolean m613I() {
        return mo578e() && !this.f1161O.m594w();
    }

    /* renamed from: J */
    public boolean m614J() {
        return this.f1168V == 5;
    }

    /* renamed from: K */
    public boolean m615K() {
        return mo578e() && getCurrentState() == getStatePauseCode();
    }

    /* renamed from: L */
    public boolean m616L() {
        return this.f1168V == 4;
    }

    /* renamed from: M */
    public void m617M(boolean z6) {
        if (this.f1161O.m594w()) {
            this.f1161O.m595x(z6);
            return;
        }
        setTargetState(getStatePauseCode());
        if (mo578e()) {
            if (z6 && !this.f1177h0) {
                this.f1170a0.m1455a();
            }
            this.f1105e.pause();
            InterfaceC1479s interfaceC1479s = this.f1095g;
            if (interfaceC1479s != null) {
                interfaceC1479s.onPause();
            }
        }
    }

    /* renamed from: N */
    public final boolean m618N(boolean z6) {
        if (this.f1155I) {
            return false;
        }
        mo574a(false);
        InterfaceC1479s interfaceC1479s = this.f1095g;
        if (interfaceC1479s != null) {
            interfaceC1479s.mo855a();
        }
        setPlayerBufferingViewVisibility(0);
        this.f1105e.mo582i();
        setOnCompletionListener(this.f1179j0);
        setOnPreparedListener(z6 ? this.f1183n0 : this.f1182m0);
        setOnErrorListener(this.f1185p0);
        setOnInfoListener(this.f1186q0);
        this.f1175f0.setOnSEIRefreshListener(this.f1187r0);
        this.f1175f0.setOnAudioVividMetadataListener(this.f1188s0);
        this.f1175f0.setOnMpegTsPesPrivateDataListener(this.f1189t0);
        if (getCurrentPlayPath() == null) {
            m606B(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
            return false;
        }
        if (this.f1167U.containsKey("KEY_HEADERS")) {
            Map<String, String> map = (Map) this.f1167U.get("KEY_HEADERS");
            this.f1169W = map;
            if (map == null) {
                m606B(-1006);
                return false;
            }
        }
        if (this.f1167U.containsKey("KEY_HOST")) {
            String str = (String) this.f1167U.get("KEY_HOST");
            if (TextUtils.isEmpty(str)) {
                m606B(-1002);
                return false;
            }
            Map<String, String> map2 = this.f1169W;
            if (map2 == null) {
                map2 = new HashMap<>();
            }
            this.f1169W = map2;
            map2.put("host", " " + str);
        }
        this.f1178i0.removeMessages(12);
        this.f1178i0.sendEmptyMessageDelayed(12, this.f1163Q * 1000);
        return true;
    }

    /* renamed from: O */
    public void m619O(Activity activity, int i7) {
        if (i7 <= 0 && i7 != -1) {
            i7 = 0;
        } else if (i7 > 100) {
            i7 = 100;
        }
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (i7 == -1) {
            attributes.screenBrightness = -1.0f;
        } else {
            attributes.screenBrightness = i7 / 100.0f;
        }
        window.setAttributes(attributes);
    }

    /* renamed from: P */
    public final boolean m620P(boolean z6) {
        if (this.f1161O.m594w()) {
            this.f1161O.start();
            return false;
        }
        setTargetState(getStatePlayingCode());
        if (!mo578e()) {
            return false;
        }
        if (!this.f1177h0) {
            this.f1170a0.m1456b();
        }
        this.f1105e.start();
        InterfaceC1479s interfaceC1479s = this.f1095g;
        if (interfaceC1479s == null) {
            return true;
        }
        interfaceC1479s.mo856b(z6);
        return true;
    }

    /* renamed from: Q */
    public final void m621Q() {
        this.f1178i0.removeMessages(12);
    }

    /* renamed from: R */
    public final void m622R() {
        InterfaceC1199b interfaceC1199b;
        if (!m613I() || (interfaceC1199b = this.f1160N) == null) {
            return;
        }
        if (interfaceC1199b.isShowing()) {
            this.f1160N.hide();
        } else {
            this.f1160N.show();
        }
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1421a
    /* renamed from: a */
    public void mo574a(boolean z6) {
        this.f1105e.mo574a(z6);
        setTargetState(getStateIdleCode());
        setPlayerBufferingViewVisibility(8);
        m621Q();
        InterfaceC1199b interfaceC1199b = this.f1160N;
        if (interfaceC1199b != null) {
            interfaceC1199b.hide();
        }
        this.f1157K = false;
        this.f1158L = 0;
        this.f1169W = null;
        this.f1165S = 0;
        this.f1162P = false;
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1421a
    /* renamed from: b */
    public void mo575b(Uri uri, Map<String, String> map) {
        if (m618N(false)) {
            super.mo575b(uri, map);
        }
    }

    public int getAspectRatio() {
        return getCurrentAspectRatio();
    }

    public C1232a getAudioFocusManager() {
        return this.f1170a0;
    }

    public float getAudioFrameSpeed() {
        IMediaPlayer mediaPlayer = getMediaPlayer();
        if (mediaPlayer != null) {
            return mediaPlayer.getAudioFrameSpeed();
        }
        return 1.0f;
    }

    public C0757a getAudioVividMeta() {
        IMediaPlayer mediaPlayer = getMediaPlayer();
        if (mediaPlayer == null) {
            return null;
        }
        mediaPlayer.getAv3aMetadataInt(30001, 0);
        int av3aMetadataInt = mediaPlayer.getAv3aMetadataInt(10002, 0);
        if (av3aMetadataInt <= 0) {
            return null;
        }
        C0757a c0757a = new C0757a();
        for (int i7 = 0; i7 < av3aMetadataInt; i7++) {
            C0757a.a aVar = new C0757a.a();
            mediaPlayer.getAv3aMetadataInt(10003, i7);
            mediaPlayer.getAv3aMetadataInt(IMediaPlayer.MEDIA_INFO_VIDEO_DECODED_START, i7);
            mediaPlayer.getAv3aMetadataInt(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_DECODER, i7);
            mediaPlayer.getAv3aMetadataFloat(21001, i7);
            mediaPlayer.getAv3aMetadataFloat(21002, i7);
            mediaPlayer.getAv3aMetadataFloat(21003, i7);
            String[] strArrSplit = mediaPlayer.getAv3aMetadataString(11001, i7).trim().split(",");
            int i8 = 0;
            for (int i9 = 0; i9 < strArrSplit.length && i8 < aVar.f1217a.length; i9++) {
                String str = strArrSplit[i9];
                if (!str.isEmpty()) {
                    try {
                        int i10 = i8 + 1;
                        try {
                            aVar.f1217a[i8] = Short.parseShort(str.trim());
                        } catch (NumberFormatException unused) {
                        }
                        i8 = i10;
                    } catch (NumberFormatException unused2) {
                    }
                }
            }
            c0757a.f1216a.add(aVar);
        }
        return c0757a;
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (getMediaPlayer() != null) {
            return this.f1158L;
        }
        return 0;
    }

    public String getCurrentPlayPath() {
        Uri uri = this.f1156J;
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }

    public GestureDetector getGestureDetector() {
        return this.f1152F;
    }

    public boolean getNeedGestureDetector() {
        return this.f1151E;
    }

    public C1058a getPlayOption() {
        C1058a c1058a = this.f1166T;
        return c1058a == null ? C1058a.m1053a() : c1058a;
    }

    public PolyvSubVideoView getSubVideoView() {
        return this.f1161O;
    }

    public float getVideoFrameSpeed() {
        IMediaPlayer mediaPlayer = getMediaPlayer();
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoFrameSpeed();
        }
        return 1.0f;
    }

    public int getVolume() {
        AudioManager audioManager = (AudioManager) this.f1154H.getSystemService("audio");
        return (int) Math.round((audioManager.getStreamVolume(3) / audioManager.getStreamMaxVolume(3)) * 100.0d);
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return super.isPlaying() || getTargetState() == getStatePlayingCode();
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i7, KeyEvent keyEvent) {
        boolean z6 = (i7 == 4 || i7 == 24 || i7 == 25 || i7 == 164 || i7 == 82 || i7 == 5 || i7 == 6) ? false : true;
        if (m613I() && z6 && this.f1160N != null) {
            if (i7 == 79 || i7 == 85) {
                if (isPlaying()) {
                    pause();
                    this.f1160N.show();
                } else {
                    start();
                    this.f1160N.hide();
                }
                return true;
            }
            if (i7 == 126) {
                if (!isPlaying()) {
                    start();
                    this.f1160N.hide();
                }
                return true;
            }
            if (i7 == 86 || i7 == 127) {
                if (isPlaying()) {
                    pause();
                    this.f1160N.show();
                }
                return true;
            }
            m622R();
        }
        return super.onKeyDown(i7, keyEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f1151E || motionEvent.getPointerCount() != 1) {
            m622R();
            return false;
        }
        if (motionEvent.getAction() == 0) {
            this.f1149C = 0.0f;
            this.f1150D = 0.0f;
            this.f1153G = 0;
        }
        GestureDetector gestureDetector = this.f1152F;
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            switch (this.f1153G) {
                case 1:
                    m626s(false, true);
                    break;
                case 2:
                    m627t(false, true);
                    break;
                case 3:
                    m624q(false, true);
                    break;
                case 4:
                    m625r(false, true);
                    break;
                case 5:
                    m628u(false, 1, true);
                    break;
                case 6:
                    m629v(false, 1, true);
                    break;
                case 7:
                    InterfaceC1471k interfaceC1471k = this.f1214y;
                    if (interfaceC1471k != null) {
                        interfaceC1471k.m1645a(false, true);
                        break;
                    }
                    break;
                case 8:
                    InterfaceC1472l interfaceC1472l = this.f1215z;
                    if (interfaceC1472l != null) {
                        interfaceC1472l.m1646a(false, true);
                        break;
                    }
                    break;
                case 9:
                    InterfaceC1469i interfaceC1469i = this.f1204A;
                    if (interfaceC1469i != null) {
                        interfaceC1469i.m1643a(false, true);
                        break;
                    }
                    break;
                case 10:
                    InterfaceC1470j interfaceC1470j = this.f1205B;
                    if (interfaceC1470j != null) {
                        interfaceC1470j.m1644a(false, true);
                        break;
                    }
                    break;
            }
            this.f1149C = 0.0f;
            this.f1150D = 0.0f;
            this.f1153G = 0;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        m622R();
        return true;
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public void pause() {
        m617M(true);
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i7) {
        if (i7 >= getDuration()) {
            i7 = getDuration() - 100;
        } else if (i7 < 0) {
            i7 = 0;
        }
        this.f1105e.seekTo(i7);
    }

    public void setAudioFocusManager(C1232a c1232a) {
        this.f1170a0 = c1232a;
    }

    public void setAudioVividMeta(C0757a.b bVar) {
        if (getMediaPlayer() == null) {
            return;
        }
        Objects.requireNonNull(bVar);
        throw null;
    }

    public void setCustomRequestAudioFocus(boolean z6) {
        this.f1177h0 = z6;
    }

    public void setEnableBackgroundPlay(boolean z6) {
    }

    public void setEnableHdrVivid(boolean z6) {
        PolyvIjkVideoView polyvIjkVideoView = this.f1175f0;
        if (polyvIjkVideoView != null) {
            polyvIjkVideoView.setEnableHdrVivid(z6);
        }
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1421a
    public void setMediaController(InterfaceC1199b interfaceC1199b) {
        this.f1160N = interfaceC1199b;
        if (interfaceC1199b instanceof PolyvBaseMediaController) {
            ((PolyvBaseMediaController) interfaceC1199b).setVideoView(this);
        }
        super.setMediaController(interfaceC1199b);
    }

    public void setNeedGestureDetector(boolean z6) {
        this.f1151E = z6;
    }

    public void setPauseWhenLostAudioFocus(boolean z6) {
        C1232a c1232a = this.f1170a0;
        if (c1232a != null) {
            c1232a.f2768e = z6;
        }
    }

    public void setPlayerBufferingIndicator(View view) {
        this.f1159M = view;
    }

    public void setSubVideoView(@NonNull PolyvSubVideoView polyvSubVideoView) {
        this.f1161O = polyvSubVideoView;
        polyvSubVideoView.f1121O = this.f1170a0;
        polyvSubVideoView.setOnSubVideoViewPlayStatusListener(new C0753j(polyvSubVideoView));
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1421a
    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1421a
    public void setVideoURI(Uri uri) {
        if (m618N(false)) {
            this.f1105e.mo575b(uri, this.f1169W);
        }
    }

    public void setVolume(int i7) {
        Context context = this.f1154H;
        if (i7 < 0) {
            i7 = 0;
        } else if (i7 > 100) {
            i7 = 100;
        }
        double d7 = i7 / 100.0d;
        ((AudioManager) context.getSystemService("audio")).setStreamVolume(3, (int) (r8.getStreamMaxVolume(3) * d7), 0);
    }

    @Override // com.easefun.povplayer.core.video.PolyvForwardingIjkVideoView, p095l3.InterfaceC1422b, android.widget.MediaController.MediaPlayerControl
    public void start() {
        if (this.f1162P) {
            m620P(false);
        } else if (m620P(true)) {
            this.f1162P = true;
        }
    }

    public PolyvVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PolyvVideoView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1153G = 0;
        this.f1168V = 4;
        this.f1172c0 = false;
        this.f1173d0 = null;
        this.f1174e0 = 101;
        this.f1176g0 = 2;
        this.f1177h0 = false;
        this.f1178i0 = new HandlerC0752i(Looper.myLooper());
        this.f1179j0 = new C0754k();
        this.f1180k0 = new C0755l();
        this.f1181l0 = new C0756m();
        this.f1182m0 = new C0744a();
        this.f1183n0 = new C0745b();
        this.f1184o0 = new C0746c();
        this.f1185p0 = new C0747d();
        this.f1186q0 = new C0748e();
        this.f1187r0 = new C0749f();
        this.f1188s0 = new C0750g();
        this.f1189t0 = new C0751h();
        if (this.f1154H == null) {
            this.f1154H = context;
            PolyvIjkVideoView polyvIjkVideoView = new PolyvIjkVideoView(context);
            this.f1175f0 = polyvIjkVideoView;
            this.f1105e = polyvIjkVideoView;
            addView(polyvIjkVideoView);
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
            setIjkLogLevel(4);
            setLogTag("PolyvVideoView");
            this.f1152F = new GestureDetector(this.f1154H, new C1424d(this));
            C1232a c1232a = new C1232a(this.f1154H);
            this.f1170a0 = c1232a;
            c1232a.f2765b = this;
        }
    }
}
