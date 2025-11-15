package com.cctv.cctvplayer;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.motion.C0080b;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.cctvplayer.player.DolbyHeadsetPlugReceiver;
import com.cctv.cctvplayer.widget.LightTipsView;
import com.cctv.cctvplayer.widget.ProgressTipsView;
import com.cctv.cctvplayer.widget.VolumeTipsView;
import com.easefun.povplayer.core.video.PolyvSubVideoView;
import com.easefun.povplayer.core.video.PolyvVideoView;
import java.util.Objects;
import p037e1.C0922c;
import p037e1.C0923d;
import p037e1.C0924e;
import p037e1.C0925f;
import p037e1.C0926g;
import p037e1.C0927h;
import p037e1.C0928i;
import p037e1.C0929j;
import p037e1.C0930k;
import p037e1.C0931l;
import p037e1.C0933n;
import p037e1.C0934o;
import p037e1.C0935p;
import p037e1.C0936q;
import p037e1.C0939t;
import p037e1.C0940u;
import p037e1.C0941v;
import p037e1.C0942w;
import p037e1.C0943x;
import p037e1.C0944y;
import p037e1.RunnableC0932m;
import p037e1.RunnableC0937r;
import p037e1.ViewOnClickListenerC0938s;
import p043f.C0988e;
import p045f1.C0992a;
import p053g1.InterfaceC1043a;
import p053g1.InterfaceC1047e;
import p055g3.C1058a;
import p095l3.C1423c;
import p186x2.C2073a;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.format.mpegts.PesPrivateDataTimeStamp;

/* loaded from: classes.dex */
public class CCTVVideoView extends RelativeLayout {

    /* renamed from: A */
    public DolbyHeadsetPlugReceiver f488A;

    /* renamed from: B */
    public int f489B;

    /* renamed from: C */
    public TextView f490C;

    /* renamed from: D */
    public RelativeLayout f491D;

    /* renamed from: E */
    public boolean f492E;

    /* renamed from: F */
    public boolean f493F;

    /* renamed from: G */
    public C1058a.b f494G;

    /* renamed from: H */
    public boolean f495H;

    /* renamed from: I */
    public InterfaceC1043a f496I;

    /* renamed from: e */
    public int f497e;

    /* renamed from: f */
    public Context f498f;

    /* renamed from: g */
    public C0992a f499g;

    /* renamed from: h */
    public boolean f500h;

    /* renamed from: i */
    public PolyvSubVideoView f501i;

    /* renamed from: j */
    public ImageView f502j;

    /* renamed from: k */
    public PolyvVideoView f503k;

    /* renamed from: l */
    public ImageView f504l;

    /* renamed from: m */
    public CCTVVideoMediaController f505m;

    /* renamed from: n */
    public LightTipsView f506n;

    /* renamed from: o */
    public VolumeTipsView f507o;

    /* renamed from: p */
    public LinearLayout f508p;

    /* renamed from: q */
    public LinearLayout f509q;

    /* renamed from: r */
    public int f510r;

    /* renamed from: s */
    public ProgressTipsView f511s;

    /* renamed from: t */
    public InterfaceC1047e f512t;

    /* renamed from: u */
    public boolean f513u;

    /* renamed from: v */
    public int f514v;

    /* renamed from: w */
    public int f515w;

    /* renamed from: x */
    public boolean f516x;

    /* renamed from: y */
    public boolean f517y;

    /* renamed from: z */
    public ViewGroup f518z;

    public CCTVVideoView(Context context) {
        this(context, null);
    }

    /* renamed from: h */
    public static boolean m376h(CCTVVideoView cCTVVideoView) {
        return cCTVVideoView.f505m.m358i() || cCTVVideoView.f508p.getVisibility() == 0;
    }

    /* renamed from: g */
    public void mo377g(IMediaPlayer iMediaPlayer, PesPrivateDataTimeStamp pesPrivateDataTimeStamp) {
        C2073a.m2459d("OnMpegTsPesPrivateData_");
    }

    public DolbyHeadsetPlugReceiver getDolbyHeadsetPlugReceiver() {
        return this.f488A;
    }

    public ViewGroup getFullScreenPlayerContainer() {
        return this.f518z;
    }

    public CCTVVideoMediaController getMediaController() {
        return this.f505m;
    }

    public C0992a getPlayEntity() {
        return this.f499g;
    }

    public InterfaceC1047e getPlayListener() {
        return this.f512t;
    }

    public PolyvVideoView getPlayerView() {
        return this.f503k;
    }

    public int getVideoViewHeight() {
        return this.f489B;
    }

    /* renamed from: i */
    public void mo378i() {
    }

    /* renamed from: j */
    public void mo379j(C1423c c1423c) {
        String str;
        TextUtils.isEmpty("onError：" + c1423c + "&PlayOption：" + this.f503k.getPlayOption());
        int i7 = c1423c.f4164d;
        if (i7 == 1) {
            str = "片头广告";
        } else if (i7 == 3) {
            str = "片尾广告";
        } else if (i7 == 2) {
            str = "暖场视频";
        } else {
            str = i7 == 4 || i7 == 5 ? "主视频" : "";
        }
        if (i7 == 4 || i7 == 5) {
            this.f505m.m364o(true);
            this.f505m.m363n(false);
            this.f505m.m369t();
            this.f505m.m370u();
        }
        StringBuilder sbM94a = C0080b.m94a(str, "播放异常\n");
        sbM94a.append(c1423c.f4163c);
        sbM94a.append("(");
        sbM94a.append(c1423c.f4162b);
        sbM94a.append("-");
        sbM94a.append(c1423c.f4164d);
        sbM94a.append(")\n");
        sbM94a.append(c1423c.f4161a);
        TextUtils.isEmpty(sbM94a.toString());
    }

    /* renamed from: k */
    public void mo380k(IMediaPlayer iMediaPlayer, int i7, int i8) {
        TextUtils.isEmpty("what=" + i7 + "&extra=" + i8);
        if (i7 == 701 || i7 == 10005) {
            this.f505m.setBackground(true);
            this.f505m.m364o(true);
        } else if (i7 == 702) {
            this.f505m.setBackground(true);
            this.f505m.m361l(true);
            this.f505m.m364o(false);
        }
    }

    /* renamed from: l */
    public void mo381l() {
        TextUtils.isEmpty("onPause");
        this.f505m.m364o(true);
        this.f505m.m363n(true);
        this.f505m.m369t();
        this.f505m.m370u();
    }

    /* renamed from: m */
    public void mo382m() {
        TextUtils.isEmpty("onPlayAllCompletion...");
        if (!this.f505m.m357h()) {
            this.f505m.m374y();
        }
        CCTVVideoMediaController cCTVVideoMediaController = this.f505m;
        Objects.requireNonNull(cCTVVideoMediaController);
        TextUtils.isEmpty("onReset...");
        cCTVVideoMediaController.f461P.removeCallbacksAndMessages(null);
    }

    /* renamed from: n */
    public void mo383n(boolean z6) {
        TextUtils.isEmpty("onPlay.isFirst=" + z6);
        this.f491D.setVisibility(8);
        this.f505m.m364o(false);
        this.f505m.m363n(true);
        this.f505m.m357h();
        CCTVVideoMediaController cCTVVideoMediaController = this.f505m;
        if (cCTVVideoMediaController.m357h()) {
            return;
        }
        cCTVVideoMediaController.f461P.sendEmptyMessage(102);
    }

    /* renamed from: o */
    public void mo384o(IMediaPlayer iMediaPlayer) {
        TextUtils.isEmpty("onPrepared");
        this.f492E = true;
        PolyvVideoView polyvVideoView = this.f503k;
        if (polyvVideoView.getRenderView() != null) {
            polyvVideoView.setCurrentAspectRatio(0);
        }
        int i7 = this.f514v;
        if (i7 > 0) {
            mo389t(i7);
            this.f514v = 0;
        }
    }

    /* renamed from: p */
    public void mo385p() {
        TextUtils.isEmpty("onPreparing");
        if (!this.f513u) {
            this.f505m.m355f();
        }
        this.f505m.setBackground(true);
        this.f505m.m364o(true);
    }

    /* renamed from: q */
    public void mo386q(IMediaPlayer iMediaPlayer) {
        iMediaPlayer.start();
    }

    /* renamed from: r */
    public void mo387r() {
        PolyvVideoView polyvVideoView = this.f503k;
        polyvVideoView.f1161O.mo574a(false);
        polyvVideoView.mo574a(false);
        if (!polyvVideoView.f1177h0) {
            polyvVideoView.f1170a0.m1455a();
        }
        this.f503k.f1105e.mo581h();
        this.f503k.getSubVideoView().f1105e.mo581h();
        CCTVVideoMediaController cCTVVideoMediaController = this.f505m;
        Objects.requireNonNull(cCTVVideoMediaController);
        TextUtils.isEmpty("onReset...");
        cCTVVideoMediaController.f461P.removeCallbacksAndMessages(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x03a3  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0492  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x049b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x04a8  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x04c9  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0534  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0543  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x054f  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0563  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0576  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0579  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x059f  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x05d1  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x05ee  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0608  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x06ae  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x06fb  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x06fe  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x072a  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0742  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0760  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0774  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0788  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x079c  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x07aa  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x07c2  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x07fc  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0826 A[LOOP:0: B:205:0x0820->B:207:0x0826, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x085c  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x087a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0313  */
    /* renamed from: s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo388s(java.lang.String r28, boolean r29, java.lang.String r30, boolean r31) {
        /*
            Method dump skipped, instructions count: 2223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cctv.cctvplayer.CCTVVideoView.mo388s(java.lang.String, boolean, java.lang.String, boolean):void");
    }

    public void setADClickListener(InterfaceC1043a interfaceC1043a) {
        this.f496I = interfaceC1043a;
    }

    public void setEnableHdrVivid(boolean z6) {
        if (this.f503k != null) {
            C2073a.m2459d("setEnableHdrVivid flag = " + z6);
            this.f503k.setEnableHdrVivid(z6);
        }
    }

    public void setFullScreenPlayerContainer(ViewGroup viewGroup) {
        this.f518z = viewGroup;
    }

    public void setHideLoading(boolean z6) {
        this.f493F = z6;
        PolyvVideoView polyvVideoView = this.f503k;
        if (polyvVideoView != null) {
            polyvVideoView.setPlayerBufferingIndicator(z6 ? null : this.f491D);
            if (!z6 && this.f503k.m611G()) {
                this.f491D.setVisibility(0);
            }
        }
        if (z6) {
            this.f491D.setVisibility(8);
        }
    }

    public void setIsAllForbiddenGesture(boolean z6) {
        this.f517y = z6;
        this.f516x = z6;
    }

    public void setIsForbiddenGesture(boolean z6) {
        this.f516x = z6;
    }

    public void setOpenFrameSpeed(boolean z6) {
        this.f495H = z6;
    }

    public void setPlay(C0992a c0992a) {
        if (c0992a == null) {
            return;
        }
        this.f499g = c0992a;
        this.f505m.setTitle(c0992a.f1838b);
        CCTVVideoMediaController cCTVVideoMediaController = this.f505m;
        if (cCTVVideoMediaController.m357h()) {
            TextUtils.isEmpty("显示直播UI");
            return;
        }
        TextUtils.isEmpty("显示点播UI");
        cCTVVideoMediaController.f478t.setVisibility(8);
        cCTVVideoMediaController.f480v.setVisibility(0);
    }

    public void setPlayListener(InterfaceC1047e interfaceC1047e) {
        this.f512t = interfaceC1047e;
    }

    public void setRightLayoutViewWidth(int i7) {
        this.f508p.getLayoutParams().width = i7;
    }

    public void setVR(boolean z6) {
        if (z6 && this.f505m.m357h()) {
            this.f505m.getLiveStatusView().setEnabled(false);
            this.f505m.findViewById(R$id.thumbView).setEnabled(false);
            ((ViewGroup) this.f505m.getPlayProgressView().getParent()).setOnTouchListener(null);
        } else {
            this.f505m.findViewById(R$id.thumbView).setEnabled(true);
            this.f505m.getLiveStatusView().setEnabled(true);
            this.f505m.m353d();
        }
    }

    public void setVideoLeftRightMarginPx(int i7) {
        this.f515w = i7;
        this.f489B = C0988e.m979e(getContext(), 16, 9, this.f515w);
    }

    public void setVideoViewHeight(int i7) {
        this.f489B = i7;
    }

    /* renamed from: t */
    public void mo389t(int i7) {
        TextUtils.isEmpty("seekTo：" + i7);
        if (this.f492E) {
            this.f503k.seekTo(i7);
        }
    }

    /* renamed from: u */
    public void m390u() {
        C2073a.m2459d("非加密视频");
        this.f494G = new C1058a.b();
    }

    /* renamed from: v */
    public void m391v() {
        if (getMediaController().m356g()) {
            this.f508p.getLayoutParams().width = C0988e.m957A(this.f498f) / 3;
        } else {
            this.f508p.getLayoutParams().width = C0988e.m957A(this.f498f) / 2;
        }
    }

    /* renamed from: w */
    public void m392w(int i7) {
        this.f505m.setBackground(true);
        if (8 == i7) {
            if (this.f503k.getCurrentState() == this.f503k.getStatePauseCode() && !this.f503k.isPlaying()) {
                this.f505m.m363n(false);
            }
            m391v();
        } else {
            CCTVVideoMediaController cCTVVideoMediaController = this.f505m;
            for (int i8 = 0; i8 < cCTVVideoMediaController.f452G.getChildCount() && cCTVVideoMediaController.f452G.getChildAt(i8).getVisibility() != 0; i8++) {
            }
            cCTVVideoMediaController.setBackground(true);
            cCTVVideoMediaController.f452G.setVisibility(8);
            cCTVVideoMediaController.f452G.m395a(8);
            this.f505m.setBackground(false);
        }
        this.f508p.setVisibility(i7);
    }

    public CCTVVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CCTVVideoView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f497e = 1;
        this.f499g = new C0992a();
        this.f510r = 0;
        this.f498f = context;
        TextUtils.isEmpty("初始化播放器视图");
        this.f489B = C0988e.m979e(getContext(), 16, 9, this.f515w);
        View viewInflate = LayoutInflater.from(getContext()).inflate(R$layout.cctv_videoview_layout, this);
        this.f491D = (RelativeLayout) viewInflate.findViewById(R$id.loadingLayout);
        this.f490C = (TextView) viewInflate.findViewById(R$id.loading_speed);
        this.f503k = (PolyvVideoView) viewInflate.findViewById(R$id.videoview);
        this.f509q = (LinearLayout) viewInflate.findViewById(R$id.floatingLayer);
        ImageView imageView = (ImageView) viewInflate.findViewById(R$id.loadingview);
        this.f504l = imageView;
        this.f504l.post(new RunnableC0932m(this, (AnimationDrawable) imageView.getBackground()));
        this.f505m = (CCTVVideoMediaController) viewInflate.findViewById(R$id.controller);
        this.f506n = (LightTipsView) viewInflate.findViewById(R$id.tipsview_light);
        this.f507o = (VolumeTipsView) viewInflate.findViewById(R$id.tipsview_volume);
        this.f511s = (ProgressTipsView) viewInflate.findViewById(R$id.tipsview_progress);
        this.f501i = (PolyvSubVideoView) viewInflate.findViewById(R$id.sub_videoview);
        ImageView imageView2 = (ImageView) viewInflate.findViewById(R$id.sub_loadingview);
        this.f502j = imageView2;
        this.f502j.post(new RunnableC0937r(this, (AnimationDrawable) imageView2.getBackground()));
        this.f508p = (LinearLayout) viewInflate.findViewById(R$id.rightContentLayout);
        this.f505m.setCCTVVideoView(this);
        this.f505m.setSubVideoView(this.f501i);
        this.f511s.setMediaController(this.f505m);
        this.f503k.setOnPlayPauseListener(new C0939t(this));
        this.f503k.setOnInfoListener(new C0940u(this));
        this.f503k.setOnPreparedListener(new C0941v(this));
        this.f503k.setOnPlayErrorListener(new C0942w(this));
        this.f503k.setOnGestureClickListener(new C0943x(this));
        this.f503k.setOnGestureLeftDownListener(new C0944y(this));
        this.f503k.setOnGestureLeftUpListener(new C0922c(this));
        this.f503k.setOnGestureRightDownListener(new C0923d(this));
        this.f503k.setOnGestureRightUpListener(new C0924e(this));
        this.f503k.setOnGestureSwipeLeftListener(new C0925f(this));
        this.f503k.setOnGestureSwipeRightListener(new C0926g(this));
        this.f503k.setOnSeekCompleteListener(new C0927h(this));
        this.f503k.setOnBufferingUpdateListener(new C0928i(this));
        this.f503k.setOnMpegTsPesPrivateDataListener(new C0929j(this));
        this.f501i.setOnPreparedListener(new C0930k(this));
        this.f501i.setOnInfoListener(new C0931l(this));
        this.f501i.setOnPlayPauseListener(new C0933n(this));
        this.f501i.setOnSubVideoViewCountdownListener(new C0934o(this));
        this.f501i.setOnSubVideoViewPlayCompletionListener(new C0935p(this));
        this.f501i.setOnGestureClickListener(new C0936q(this));
        this.f508p.setOnClickListener(new ViewOnClickListenerC0938s(this));
        this.f503k.setSubVideoView(this.f501i);
        this.f503k.setKeepScreenOn(true);
        this.f503k.setPlayerBufferingIndicator(this.f491D);
        this.f503k.setNeedGestureDetector(true);
        this.f503k.setMediaController(this.f505m);
        this.f501i.setKeepScreenOn(true);
        this.f501i.setPlayerBufferingIndicator(this.f502j);
        this.f491D.setVisibility(this.f493F ? 8 : 0);
        DolbyHeadsetPlugReceiver dolbyHeadsetPlugReceiver = new DolbyHeadsetPlugReceiver(getContext(), this.f503k);
        this.f488A = dolbyHeadsetPlugReceiver;
        TextUtils.isEmpty("注册...");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        dolbyHeadsetPlugReceiver.f520a.registerReceiver(dolbyHeadsetPlugReceiver, intentFilter, "com.cctv.cctvplayer.permission.dolbyHeadsetPlugReceiver", null);
        dolbyHeadsetPlugReceiver.f522c = true;
    }
}
