package com.cctv.cctvplayer;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.graphics.drawable.C0116a;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.cctv.cctvplayer.player.EnumC0567a;
import com.cctv.cctvplayer.widget.PauseTipsView;
import com.cctv.cctvplayer.widget.RateTipsView;
import com.cctv.cctvplayer.widget.ScrollableSeekBar;
import com.cctv.cctvplayer.widget.ThumbImageView;
import com.easefun.povplayer.core.video.PolyvBaseMediaController;
import com.easefun.povplayer.core.video.PolyvSubVideoView;
import com.easefun.povplayer.core.video.PolyvVideoView;
import java.util.List;
import java.util.Objects;
import p009b.C0413b;
import p013b3.C0440a;
import p037e1.C0920a;
import p037e1.ViewOnLayoutChangeListenerC0921b;
import p043f.C0988e;
import p045f1.C0992a;
import p045f1.C0993b;
import p053g1.InterfaceC1044b;
import p053g1.InterfaceC1045c;
import p053g1.InterfaceC1046d;
import p053g1.InterfaceC1048f;
import p053g1.InterfaceC1049g;
import p061h1.C1081a;
import p069i1.C1142a;
import p077j1.ViewOnClickListenerC1185a;
import p165u2.C1974a;
import p186x2.C2073a;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes.dex */
public class CCTVVideoMediaController extends PolyvBaseMediaController implements View.OnClickListener {

    /* renamed from: A */
    public TextView f446A;

    /* renamed from: B */
    public ImageView f447B;

    /* renamed from: C */
    public ImageView f448C;

    /* renamed from: D */
    public RelativeLayout f449D;

    /* renamed from: E */
    public RelativeLayout f450E;

    /* renamed from: F */
    public LinearLayout f451F;

    /* renamed from: G */
    public PauseTipsView f452G;

    /* renamed from: H */
    public int f453H;

    /* renamed from: I */
    public int f454I;

    /* renamed from: J */
    public boolean f455J;

    /* renamed from: K */
    public int f456K;

    /* renamed from: L */
    public int f457L;

    /* renamed from: M */
    public boolean f458M;

    /* renamed from: N */
    public LinearLayout f459N;

    /* renamed from: O */
    public boolean f460O;

    /* renamed from: P */
    public Handler f461P;

    /* renamed from: Q */
    public SeekBar.OnSeekBarChangeListener f462Q;

    /* renamed from: e */
    public long f463e;

    /* renamed from: f */
    public InterfaceC1044b f464f;

    /* renamed from: g */
    public InterfaceC1045c f465g;

    /* renamed from: h */
    public InterfaceC1048f f466h;

    /* renamed from: i */
    public InterfaceC1049g f467i;

    /* renamed from: j */
    public CCTVVideoView f468j;

    /* renamed from: k */
    public int f469k;

    /* renamed from: l */
    public PolyvVideoView f470l;

    /* renamed from: m */
    public C1081a f471m;

    /* renamed from: n */
    public RelativeLayout f472n;

    /* renamed from: o */
    public ImageView f473o;

    /* renamed from: p */
    public TextView f474p;

    /* renamed from: q */
    public ImageView f475q;

    /* renamed from: r */
    public ImageView f476r;

    /* renamed from: s */
    public TextView f477s;

    /* renamed from: t */
    public ImageView f478t;

    /* renamed from: u */
    public TextView f479u;

    /* renamed from: v */
    public TextView f480v;

    /* renamed from: w */
    public ScrollableSeekBar f481w;

    /* renamed from: x */
    public LinearLayout f482x;

    /* renamed from: y */
    public ThumbImageView f483y;

    /* renamed from: z */
    public int f484z;

    /* renamed from: com.cctv.cctvplayer.CCTVVideoMediaController$a */
    public class ViewOnTouchListenerC0564a implements View.OnTouchListener {
        public ViewOnTouchListenerC0564a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            C2073a.m2459d("Progress getParent onTouch");
            if (CCTVVideoMediaController.this.m357h()) {
                C1974a.m2299b(R$string.live_nonsupport_progress).m2345a();
            }
            Rect rect = new Rect();
            CCTVVideoMediaController.this.f481w.getHitRect(rect);
            if (motionEvent.getY() < rect.top - 500 || motionEvent.getY() > rect.bottom + 500) {
                return false;
            }
            float fHeight = (rect.height() / 2) + rect.top;
            float x6 = motionEvent.getX() - rect.left;
            return CCTVVideoMediaController.this.f481w.onTouchEvent(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), x6 < 0.0f ? 0.0f : x6 > ((float) rect.width()) ? rect.width() : x6, fHeight, motionEvent.getMetaState()));
        }
    }

    /* renamed from: com.cctv.cctvplayer.CCTVVideoMediaController$b */
    public class HandlerC0565b extends Handler {
        public HandlerC0565b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 101:
                    if (CCTVVideoMediaController.this.f470l.isPlaying()) {
                        CCTVVideoMediaController.this.m361l(true);
                        break;
                    }
                    break;
                case 102:
                    TextUtils.isEmpty("SHOW_PROGRESS...");
                    if (CCTVVideoMediaController.this.m357h()) {
                        CCTVVideoMediaController.this.setLiveProgress(0);
                    } else {
                        CCTVVideoMediaController.this.m374y();
                    }
                    if (!CCTVVideoMediaController.this.f455J) {
                        sendMessageDelayed(obtainMessage(102), 1000L);
                        CCTVVideoMediaController cCTVVideoMediaController = CCTVVideoMediaController.this;
                        cCTVVideoMediaController.m364o(true ^ cCTVVideoMediaController.f470l.isPlaying());
                        break;
                    }
                    break;
                case 103:
                    if (!CCTVVideoMediaController.this.m357h()) {
                        CCTVVideoMediaController.this.f461P.removeMessages(103);
                        break;
                    } else {
                        TextUtils.isEmpty("LIVE_PROGRESS_TIMER...");
                        CCTVVideoMediaController.this.f461P.removeMessages(103);
                        break;
                    }
            }
        }
    }

    /* renamed from: com.cctv.cctvplayer.CCTVVideoMediaController$c */
    public class C0566c implements SeekBar.OnSeekBarChangeListener {
        public C0566c() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i7, boolean z6) {
            C2073a.m2459d("Progress onProgressChanged");
            CCTVVideoMediaController.this.setSeekBarThumbPosition(i7);
            if (z6) {
                if (!CCTVVideoMediaController.this.m357h()) {
                    CCTVVideoMediaController cCTVVideoMediaController = CCTVVideoMediaController.this;
                    cCTVVideoMediaController.f469k = (cCTVVideoMediaController.f470l.getDuration() * i7) / seekBar.getMax();
                    CCTVVideoMediaController.this.f479u.setText(C0988e.m985k(CCTVVideoMediaController.this.f469k, true));
                    CCTVVideoMediaController.this.f461P.removeMessages(103);
                    CCTVVideoMediaController.this.f461P.removeMessages(102);
                    CCTVVideoMediaController.this.f455J = false;
                }
                CCTVVideoMediaController.this.m368s();
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            C2073a.m2459d("Progress onStartTrackingTouch");
            CCTVVideoMediaController cCTVVideoMediaController = CCTVVideoMediaController.this;
            cCTVVideoMediaController.f455J = true;
            cCTVVideoMediaController.f461P.removeMessages(102);
            CCTVVideoMediaController.this.f461P.removeMessages(103);
            CCTVVideoMediaController.this.f461P.removeMessages(101);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            C2073a.m2459d("Progress onStopTrackingTouch");
            if (CCTVVideoMediaController.this.m357h()) {
                CCTVVideoMediaController.this.m366q(EnumC0567a.FAST);
            } else {
                int progress = (seekBar.getProgress() * CCTVVideoMediaController.this.f470l.getDuration()) / seekBar.getMax();
                if (CCTVVideoMediaController.this.f470l.m612H()) {
                    CCTVVideoMediaController.this.f468j.mo389t(progress);
                    CCTVVideoMediaController.this.f470l.start();
                } else {
                    CCTVVideoMediaController.this.f468j.mo389t(progress);
                }
            }
            CCTVVideoMediaController.this.f461P.removeMessages(103);
            CCTVVideoMediaController.this.f461P.removeMessages(102);
            CCTVVideoMediaController.this.f461P.sendEmptyMessageDelayed(102, 1000L);
            CCTVVideoMediaController cCTVVideoMediaController = CCTVVideoMediaController.this;
            cCTVVideoMediaController.f455J = false;
            cCTVVideoMediaController.f461P.sendEmptyMessageDelayed(101, 3000L);
        }
    }

    public CCTVVideoMediaController(Context context) {
        this(context, null);
    }

    private long getLiveEndTime() {
        if (this.f468j.getPlayEntity() != null) {
            Objects.requireNonNull(this.f468j.getPlayEntity());
        }
        return 0L;
    }

    private long getLiveStartTime() {
        if (this.f468j.getPlayEntity() != null) {
            Objects.requireNonNull(this.f468j.getPlayEntity());
        }
        return 0L;
    }

    private void setLiveStatus(boolean z6) {
        this.f478t.setSelected(!z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSeekBarThumbPosition(int i7) {
        StringBuilder sbM98a = C0116a.m98a("当前进度信息：progress=", i7, " | SeekBarWidth=");
        sbM98a.append(this.f484z);
        TextUtils.isEmpty(sbM98a.toString());
        ((RelativeLayout.LayoutParams) this.f483y.getLayoutParams()).leftMargin = (int) (((this.f484z - this.f483y.getWidth()) / this.f481w.getMax()) * i7);
    }

    /* renamed from: A */
    public void m349A(int i7) {
        if (i7 == 0 && m356g()) {
            return;
        }
        this.f476r.setVisibility(i7);
    }

    /* renamed from: B */
    public void m350B() {
        if (this.f447B.getTag() != null) {
            this.f447B.setTag(null);
            this.f447B.setImageResource(R$drawable.unlock);
            InterfaceC1045c interfaceC1045c = this.f465g;
            if (interfaceC1045c != null) {
                interfaceC1045c.m1048a();
            }
        }
    }

    @Override // com.easefun.povplayer.core.video.PolyvBaseMediaController
    /* renamed from: a */
    public void mo351a(IMediaPlayer iMediaPlayer) {
        if (m357h()) {
            return;
        }
        this.f480v.setText(C0988e.m985k(iMediaPlayer.getDuration(), true));
    }

    /* renamed from: c */
    public void m352c(View view) {
        this.f459N.removeAllViews();
        if (view == null) {
            this.f459N.setVisibility(8);
            return;
        }
        view.setVisibility(0);
        this.f459N.setVisibility(0);
        this.f459N.addView(view);
    }

    /* renamed from: d */
    public void m353d() {
        ((ViewGroup) this.f481w.getParent()).setOnTouchListener(new ViewOnTouchListenerC0564a());
    }

    /* renamed from: e */
    public final void m354e() {
        int i7 = this.f453H;
        if (i7 != -100) {
            if (i7 == 0) {
                this.f449D.setVisibility(0);
            } else if (i7 == 4) {
                this.f449D.setVisibility(4);
            }
        }
        int i8 = this.f454I;
        if (i8 != -100) {
            if (i8 == 0) {
                this.f450E.setVisibility(0);
            } else if (i8 == 4) {
                this.f450E.setVisibility(4);
            }
        }
    }

    /* renamed from: f */
    public void m355f() {
        TextUtils.isEmpty("initPlayProgressView...");
        if (!m357h()) {
            this.f478t.setVisibility(8);
            this.f481w.setProgress(0);
            this.f481w.setSecondaryProgress(0);
            this.f479u.setText("00:00");
            this.f481w.setScrollable(true);
            this.f479u.setVisibility(0);
            return;
        }
        ScrollableSeekBar scrollableSeekBar = this.f481w;
        scrollableSeekBar.setProgress(scrollableSeekBar.getMax());
        ScrollableSeekBar scrollableSeekBar2 = this.f481w;
        scrollableSeekBar2.setSecondaryProgress(scrollableSeekBar2.getMax());
        this.f461P.removeMessages(103);
        this.f481w.setScrollable(false);
        this.f479u.setVisibility(8);
        this.f480v.setText(C0988e.m984j(System.currentTimeMillis(), "HH:mm:ss"));
    }

    /* renamed from: g */
    public boolean m356g() {
        return this.f468j.getPlayEntity().f1841e;
    }

    public ImageView getBackView() {
        return this.f473o;
    }

    public RelativeLayout getBottomLayout() {
        return this.f450E;
    }

    public CCTVVideoView getCCTVVideoView() {
        return this.f468j;
    }

    public LinearLayout getControllerCenterLayout() {
        return this.f459N;
    }

    public TextView getCurTimeView() {
        return this.f479u;
    }

    public TextView getEndTimeView() {
        return this.f480v;
    }

    public long getLivePlayedMs() {
        if (this.f463e > System.currentTimeMillis() || String.valueOf(this.f463e).length() != 13) {
            this.f463e = System.currentTimeMillis();
        }
        return this.f463e;
    }

    public ImageView getLiveStatusView() {
        return this.f478t;
    }

    public InterfaceC1049g getOnProgressListener() {
        return this.f467i;
    }

    public C1081a getOrientation() {
        return this.f471m;
    }

    public ScrollableSeekBar getPlayProgressView() {
        return this.f481w;
    }

    public int getPosition() {
        return this.f469k;
    }

    public RelativeLayout getTopLayout() {
        return this.f449D;
    }

    /* renamed from: h */
    public boolean m357h() {
        return this.f468j.getPlayEntity() != null ? this.f468j.getPlayEntity().f1839c : this.f470l.m614J();
    }

    @Override // p079j3.InterfaceC1199b
    public void hide() {
    }

    /* renamed from: i */
    public boolean m358i() {
        return this.f447B.getTag() != null;
    }

    @Override // p079j3.InterfaceC1199b
    public boolean isShowing() {
        return m358i() ? this.f447B.getVisibility() == 0 : this.f449D.getVisibility() == 0 || this.f450E.getVisibility() == 0;
    }

    /* renamed from: j */
    public boolean m359j() {
        return this.f470l.getCurrentState() == this.f470l.getStateErrorCode() || this.f470l.getCurrentState() == this.f470l.getStatePlaybackCompletedCode();
    }

    /* renamed from: k */
    public void m360k(int i7) {
        if (this.f464f == null) {
            return;
        }
        int i8 = 3;
        if (i7 == 1) {
            if (this.f449D.getVisibility() == 0 && this.f450E.getVisibility() != 0) {
                i8 = 1;
            } else if (this.f449D.getVisibility() != 0 && this.f450E.getVisibility() == 0) {
                i8 = 2;
            } else if (this.f449D.getVisibility() != 0 || this.f450E.getVisibility() != 0) {
                i8 = 0;
            }
            if (i8 <= 0 || this.f456K == i8) {
                return;
            }
            this.f464f.mo430e(i8);
            this.f457L = 0;
            this.f456K = i8;
            return;
        }
        if (i7 == 2) {
            if (this.f449D.getVisibility() == 0 && this.f450E.getVisibility() != 0) {
                i8 = 2;
            } else if (this.f449D.getVisibility() != 0 && this.f450E.getVisibility() == 0) {
                i8 = 1;
            } else if (this.f449D.getVisibility() == 0 || this.f450E.getVisibility() == 0) {
                i8 = 0;
            }
            if (i8 <= 0 || this.f457L == i8) {
                return;
            }
            this.f464f.mo427b(i8);
            this.f456K = 0;
            this.f457L = i8;
        }
    }

    /* renamed from: l */
    public void m361l(boolean z6) {
        if (this.f455J || this.f458M || this.f468j.f517y) {
            return;
        }
        TextUtils.isEmpty("onHide：" + z6);
        this.f461P.removeMessages(101);
        this.f449D.setVisibility(4);
        this.f450E.setVisibility(4);
        if (m356g()) {
            C1142a.m1310b((Activity) getContext(), false);
        }
        m354e();
        m360k(2);
    }

    /* renamed from: m */
    public void m362m() {
        EnumC0567a enumC0567a = EnumC0567a.RELOAD;
        InterfaceC1048f interfaceC1048f = this.f466h;
        if (interfaceC1048f != null) {
            interfaceC1048f.m1052a();
            return;
        }
        if (this.f470l.isPlaying()) {
            this.f470l.pause();
            this.f448C.setSelected(true);
            return;
        }
        if (m357h()) {
            if (!m359j() || this.f468j == null) {
                this.f470l.start();
            } else {
                m355f();
                m366q(enumC0567a);
            }
        } else if (!m359j() || this.f468j == null) {
            this.f470l.start();
        } else {
            m367r(false, enumC0567a);
        }
        this.f448C.setSelected(false);
    }

    /* renamed from: n */
    public void m363n(boolean z6) {
        CCTVVideoView cCTVVideoView = this.f468j;
        if (cCTVVideoView.f500h || cCTVVideoView.f517y) {
            return;
        }
        TextUtils.isEmpty("onShow：" + z6);
        this.f461P.removeMessages(101);
        if (this.f447B.getTag() == null) {
            this.f461P.sendEmptyMessage(102);
            this.f449D.setVisibility(0);
            this.f450E.setVisibility(0);
        }
        m375z(0);
        this.f472n.setVisibility(8);
        if (z6) {
            this.f461P.sendEmptyMessageDelayed(101, 3000L);
        }
        if (m356g() && !m358i()) {
            C1142a.m1310b((Activity) getContext(), true);
        }
        m354e();
        m360k(1);
    }

    /* renamed from: o */
    public void m364o(boolean z6) {
        if (this.f466h != null) {
            return;
        }
        this.f448C.setSelected(z6);
        this.f461P.removeMessages(102);
        if (z6) {
            return;
        }
        m368s();
        this.f461P.sendEmptyMessageDelayed(102, 1000L);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Activity activity;
        EnumC0567a enumC0567a = EnumC0567a.FAST;
        int id = view.getId();
        if (id == R$id.back || id == R$id.backFixedLayout) {
            if (m358i()) {
                return;
            }
            if (getResources().getConfiguration().orientation != 2) {
                if (getResources().getConfiguration().orientation == 1) {
                    ((Activity) this.f471m.f2178a).finish();
                    return;
                }
                return;
            }
            C1081a c1081a = this.f471m;
            c1081a.f2179b.m350B();
            ((Activity) c1081a.f2178a).setRequestedOrientation(1);
            if (c1081a.f2184g != null) {
                ((ViewGroup) c1081a.f2179b.getParent()).getLayoutParams().height = c1081a.f2184g.getVideoViewHeight();
            }
            c1081a.f2179b.setHorizontal(false);
            c1081a.f2179b.m373x();
            if (c1081a.f2179b.f460O && (activity = (Activity) c1081a.f2178a) != null) {
                View decorView = activity.getWindow().getDecorView();
                activity.getWindow().setStatusBarColor(ViewCompat.MEASURED_STATE_MASK);
                decorView.setSystemUiVisibility(4352);
            }
            c1081a.f2179b.setTopLayoutTopMargin(0);
            CCTVVideoView cCTVVideoView = c1081a.f2184g;
            if (cCTVVideoView != null) {
                c1081a.f2181d.removeView(cCTVVideoView);
                c1081a.f2184g.setLayoutParams(c1081a.f2183f);
                ViewGroup viewGroup = (ViewGroup) c1081a.f2184g.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(c1081a.f2184g);
                }
                c1081a.f2182e.addView(c1081a.f2184g, 0);
                C0920a c0920a = C0920a.b.f1684a;
                Context context = c1081a.f2182e.getContext();
                ViewGroup viewGroup2 = c1081a.f2182e;
                Objects.requireNonNull(c0920a);
                if (context != null && viewGroup2 != null) {
                    c0920a.f1683a.put(context.toString(), viewGroup2);
                }
            }
            InterfaceC1046d interfaceC1046d = c1081a.f2180c;
            if (interfaceC1046d != null) {
                interfaceC1046d.m1051b();
                return;
            }
            return;
        }
        if (id == R$id.fullScreen || id == R$id.subFullScreen) {
            C1081a c1081a2 = this.f471m;
            c1081a2.f2179b.m350B();
            c1081a2.m1173a(6);
            ((ViewGroup) c1081a2.f2179b.getParent()).setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            c1081a2.f2179b.setHorizontal(true);
            c1081a2.f2179b.m373x();
            if (c1081a2.f2179b.f460O) {
                C1142a.m1309a((Activity) c1081a2.f2178a);
            }
            CCTVVideoView cCTVVideoView2 = (CCTVVideoView) c1081a2.f2179b.getParent().getParent();
            c1081a2.f2184g = cCTVVideoView2;
            if (cCTVVideoView2.f500h) {
                c1081a2.f2179b.setTopLayoutTopMargin(0);
                C1142a.m1310b((Activity) c1081a2.f2178a, false);
            } else {
                c1081a2.f2179b.setTopLayoutTopMargin(C0988e.m1000z(c1081a2.f2178a));
                C1142a.m1310b((Activity) c1081a2.f2178a, true);
            }
            c1081a2.f2182e = (ViewGroup) c1081a2.f2184g.getParent();
            if (c1081a2.f2184g.getFullScreenPlayerContainer() != null) {
                c1081a2.f2181d = c1081a2.f2184g.getFullScreenPlayerContainer();
            } else {
                c1081a2.f2181d = (ViewGroup) ((Activity) c1081a2.f2178a).findViewById(R.id.content);
            }
            c1081a2.f2183f = c1081a2.f2184g.getLayoutParams();
            c1081a2.f2182e.removeView(c1081a2.f2184g);
            c1081a2.f2184g.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            ViewGroup viewGroup3 = (ViewGroup) c1081a2.f2184g.getParent();
            if (viewGroup3 != null) {
                viewGroup3.removeView(c1081a2.f2184g);
            }
            c1081a2.f2181d.addView(c1081a2.f2184g);
            C0920a c0920a2 = C0920a.b.f1684a;
            Context context2 = c1081a2.f2182e.getContext();
            ViewGroup viewGroup4 = c1081a2.f2181d;
            Objects.requireNonNull(c0920a2);
            if (context2 != null && viewGroup4 != null) {
                c0920a2.f1683a.put(context2.toString(), viewGroup4);
            }
            InterfaceC1046d interfaceC1046d2 = c1081a2.f2180c;
            if (interfaceC1046d2 != null) {
                interfaceC1046d2.m1050a();
                return;
            }
            return;
        }
        if (id == R$id.playPause) {
            m362m();
            return;
        }
        if (id == R$id.rate) {
            if (this.f468j.getPlayEntity() == null) {
                return;
            }
            m361l(true);
            RateTipsView rateTipsView = new RateTipsView(getContext());
            if (m356g()) {
                rateTipsView.setPadding(0, getResources().getDimensionPixelSize(R$dimen.cctv_videoview_rate_horizontal_padding), 0, 0);
            } else {
                rateTipsView.setPadding(0, C0988e.m1000z(getContext()), 0, 0);
            }
            List<C0993b> list = this.f468j.getPlayEntity().f1843g;
            CCTVVideoView cCTVVideoView3 = this.f468j;
            rateTipsView.f548e = list;
            rateTipsView.f551h = cCTVVideoView3;
            if (cCTVVideoView3 != null) {
                rateTipsView.f552i = cCTVVideoView3.getMediaController();
                List<C0993b> list2 = rateTipsView.f548e;
                if (list2 != null && !list2.isEmpty()) {
                    rateTipsView.f549f.removeAllViews();
                    for (C0993b c0993b : rateTipsView.f548e) {
                        View viewInflate = View.inflate(rateTipsView.getContext(), R$layout.cctv_videoview_rate_item, null);
                        TextView textView = (TextView) viewInflate.findViewById(R$id.rateName);
                        ImageView imageView = (ImageView) viewInflate.findViewById(R$id.rateCheck);
                        textView.setText(c0993b.f1851b);
                        if (c0993b.f1853d) {
                            rateTipsView.f550g = imageView;
                            imageView.setVisibility(0);
                        } else {
                            imageView.setVisibility(4);
                        }
                        rateTipsView.f549f.addView(viewInflate);
                        viewInflate.setOnClickListener(new ViewOnClickListenerC1185a(rateTipsView, c0993b.f1851b, viewInflate));
                    }
                }
            }
            CCTVVideoView cCTVVideoView4 = this.f468j;
            cCTVVideoView4.m391v();
            cCTVVideoView4.f508p.removeAllViews();
            cCTVVideoView4.f508p.addView(rateTipsView);
            this.f468j.m392w(0);
            return;
        }
        if (id == R$id.lock) {
            if (this.f447B.getTag() == null) {
                this.f447B.setTag("锁屏了");
                this.f447B.setSelected(true);
                m361l(false);
                this.f471m.m1173a(14);
                InterfaceC1045c interfaceC1045c = this.f465g;
                if (interfaceC1045c != null) {
                    interfaceC1045c.m1049b();
                    return;
                }
                return;
            }
            this.f447B.setTag(null);
            this.f447B.setSelected(false);
            m363n(true);
            this.f471m.m1173a(6);
            InterfaceC1045c interfaceC1045c2 = this.f465g;
            if (interfaceC1045c2 != null) {
                interfaceC1045c2.m1048a();
                return;
            }
            return;
        }
        if (id == R$id.drawBackLayout) {
            if (!m357h()) {
                this.f468j.mo389t(this.f470l.getCurrentPosition() - 30000);
                return;
            } else {
                setLiveProgress(-30000);
                m366q(enumC0567a);
                return;
            }
        }
        if (id == R$id.advanceLayout) {
            if (!m357h()) {
                this.f468j.mo389t(this.f470l.getCurrentPosition() + 30000);
                return;
            } else {
                setLiveProgress(30000);
                m366q(enumC0567a);
                return;
            }
        }
        if (id != R$id.subSkip) {
            if (id == R$id.liveStatus) {
                m355f();
                m366q(EnumC0567a.RELOAD);
                return;
            }
            return;
        }
        if (!this.f470l.m607C()) {
            StringBuilder sbM112a = C0413b.m112a("跳过广告播放失败，当前没有播放片头广告&PlayOption：");
            sbM112a.append(this.f470l.getPlayOption());
            TextUtils.isEmpty(sbM112a.toString());
            return;
        }
        this.f477s.setVisibility(8);
        m349A(8);
        PolyvVideoView polyvVideoView = this.f470l;
        if (!polyvVideoView.m607C()) {
            polyvVideoView.m609E(false);
        } else {
            if (polyvVideoView.f1161O.m592u()) {
                polyvVideoView.f1161O.m587A();
                return;
            }
            polyvVideoView.f1161O.mo574a(false);
            polyvVideoView.f1161O.m593v();
            polyvVideoView.m605A();
        }
    }

    /* renamed from: p */
    public void m365p(boolean z6, boolean z7) {
        this.f448C.setSelected(z6);
        this.f461P.removeMessages(102);
        if (!z7 || z6) {
            return;
        }
        this.f461P.sendEmptyMessageDelayed(102, 1000L);
    }

    /* renamed from: q */
    public void m366q(EnumC0567a enumC0567a) {
        if (this.f468j.getPlayListener() == null || this.f468j.getPlayEntity() == null) {
            return;
        }
        if (this.f481w.getProgress() >= this.f481w.getMax()) {
            TextUtils.isEmpty("直播拖动 - 最新...");
            setLiveStatus(true);
            this.f468j.getPlayListener().mo429d(this.f468j.getPlayEntity().m1011e(false), enumC0567a);
        } else {
            TextUtils.isEmpty("直播拖动 - 时移...");
            setLiveStatus(false);
            this.f468j.getPlayListener().mo428c(this.f468j.getPlayEntity().m1011e(true), getLivePlayedMs(), enumC0567a);
        }
    }

    /* renamed from: r */
    public void m367r(boolean z6, EnumC0567a enumC0567a) {
        if (this.f468j.getPlayListener() == null || this.f468j.getPlayEntity() == null) {
            return;
        }
        this.f468j.getPlayListener().mo426a(this.f468j.getPlayEntity().m1011e(false), z6, enumC0567a);
    }

    /* renamed from: s */
    public void m368s() {
        this.f459N.removeAllViews();
        this.f459N.setVisibility(8);
    }

    public void setBackground(boolean z6) {
        if (z6) {
            setBackgroundColor(Color.parseColor("#00000000"));
        } else {
            setBackgroundColor(Color.parseColor("#80000000"));
        }
    }

    public void setCCTVControllerListener(InterfaceC1044b interfaceC1044b) {
        this.f464f = interfaceC1044b;
    }

    public void setCCTVLockScreenListener(InterfaceC1045c interfaceC1045c) {
        this.f465g = interfaceC1045c;
    }

    public void setCCTVPlayOrPauseListener(InterfaceC1048f interfaceC1048f) {
        this.f466h = interfaceC1048f;
    }

    public void setCCTVVideoView(CCTVVideoView cCTVVideoView) {
        this.f468j = cCTVVideoView;
    }

    public void setControlSystemStatusBar(boolean z6) {
        this.f460O = z6;
    }

    public void setDisableHiddenControler(boolean z6) {
        this.f458M = z6;
    }

    public void setHorizontal(boolean z6) {
        this.f468j.getPlayEntity().f1841e = z6;
    }

    public void setLiveProgress(int i7) {
        if (this.f470l == null || this.f468j.getPlayEntity() == null || this.f455J) {
            return;
        }
        this.f480v.setText(C0988e.m984j(System.currentTimeMillis(), "HH:mm:ss"));
    }

    public void setOnProgressListener(InterfaceC1049g interfaceC1049g) {
        this.f467i = interfaceC1049g;
    }

    public void setOrientationListener(InterfaceC1046d interfaceC1046d) {
        this.f471m.f2180c = interfaceC1046d;
    }

    public void setShowPauseView(boolean z6) {
    }

    public void setSubVideoView(PolyvSubVideoView polyvSubVideoView) {
    }

    public void setTitle(String str) {
        this.f474p.setText(str);
    }

    public void setTopLayoutTopMargin(int i7) {
        RelativeLayout relativeLayout = this.f449D;
        if (relativeLayout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams()).setMargins(0, i7, 0, 0);
            relativeLayout.requestLayout();
        }
        LinearLayout linearLayout = this.f451F;
        if (linearLayout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) linearLayout.getLayoutParams()).setMargins(0, i7, 0, 0);
            linearLayout.requestLayout();
        }
    }

    @Override // com.easefun.povplayer.core.video.PolyvBaseMediaController
    public void setVideoView(PolyvVideoView polyvVideoView) {
        this.f470l = polyvVideoView;
    }

    @Override // p079j3.InterfaceC1199b
    public void show() {
    }

    /* renamed from: t */
    public void m369t() {
        if (m357h()) {
            this.f461P.removeMessages(103);
        }
    }

    /* renamed from: u */
    public void m370u() {
        if (m357h()) {
            return;
        }
        this.f461P.removeMessages(102);
    }

    /* renamed from: v */
    public void m371v() {
        if (this.f468j.getPlayEntity() != null) {
            C0992a playEntity = this.f468j.getPlayEntity();
            String str = playEntity.m1008b() == null ? null : playEntity.m1008b().f1851b;
            if (TextUtils.isEmpty(str)) {
                this.f446A.setVisibility(8);
            } else {
                this.f446A.setText(str);
            }
        }
    }

    /* renamed from: w */
    public void m372w(int i7, int i8) {
        if (i8 <= -1) {
            this.f477s.setVisibility(8);
            return;
        }
        Objects.requireNonNull(this.f468j.getPlayEntity());
        if (i7 - i8 >= 5) {
            this.f477s.setEnabled(true);
            this.f477s.setText(i8 + " 跳过");
        } else {
            this.f477s.setEnabled(false);
            this.f477s.setText(i8 + " 广告");
        }
        this.f477s.setVisibility(0);
    }

    /* renamed from: x */
    public void m373x() {
        List<C0993b> list;
        this.f468j.m391v();
        if (m356g()) {
            TextUtils.isEmpty("显示横屏UI");
            this.f475q.setVisibility(8);
            m375z(0);
            this.f468j.m392w(8);
            if ((this.f468j.getPlayEntity() == null || (list = this.f468j.getPlayEntity().f1843g) == null || list.isEmpty()) ? false : list.size() > 1 ? true : list.get(0).f1853d) {
                this.f446A.setVisibility(0);
                m371v();
            } else {
                this.f446A.setVisibility(8);
            }
            this.f452G.m395a(0);
            this.f476r.setVisibility(8);
        } else {
            TextUtils.isEmpty("显示竖屏UI");
            this.f475q.setVisibility(0);
            m375z(8);
            this.f446A.setVisibility(8);
            this.f468j.m392w(8);
            this.f452G.m395a(8);
            if (this.f468j.f500h) {
                this.f476r.setVisibility(0);
            }
        }
        PauseTipsView pauseTipsView = this.f452G;
        if ((pauseTipsView.getVisibility() == 0 && pauseTipsView.f534g.getVisibility() == 0) || pauseTipsView.f535h.getVisibility() == 0) {
            setBackground(false);
        } else {
            setBackground(true);
        }
    }

    /* renamed from: y */
    public void m374y() {
        int max;
        PolyvVideoView polyvVideoView = this.f470l;
        if (polyvVideoView == null || this.f455J) {
            return;
        }
        long currentPosition = polyvVideoView.getCurrentPosition();
        long duration = this.f470l.getDuration();
        if (this.f470l.m612H() || currentPosition > duration) {
            currentPosition = duration;
        }
        if (duration > 0) {
            if (duration < 1) {
                max = 0;
            } else {
                max = (int) ((this.f481w.getMax() * ((this.f470l.m612H() || currentPosition > duration) ? duration : currentPosition)) / duration);
            }
            this.f481w.setProgress(max);
        }
        this.f481w.setSecondaryProgress(this.f470l.getBufferPercentage() * 10);
        TextView textView = this.f480v;
        if (textView != null) {
            textView.setText(C0988e.m985k(duration, true));
        }
        TextView textView2 = this.f479u;
        if (textView2 != null) {
            textView2.setText(C0988e.m985k(currentPosition, true));
        }
        StringBuilder sbM112a = C0413b.m112a("当前播放进度：");
        sbM112a.append(C0440a.m150a(currentPosition, true));
        C2073a.m2459d(sbM112a.toString());
        if (getOnProgressListener() != null) {
            getOnProgressListener().mo434a(currentPosition);
            getOnProgressListener().mo435b(duration);
        }
    }

    /* renamed from: z */
    public void m375z(int i7) {
        if (8 == i7) {
            this.f447B.setVisibility(8);
        } else {
            if (!m356g() || this.f468j.f500h) {
                return;
            }
            this.f447B.setVisibility(8);
        }
    }

    public CCTVVideoMediaController(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CCTVVideoMediaController(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f469k = 0;
        this.f453H = -100;
        this.f454I = -100;
        this.f460O = true;
        this.f461P = new HandlerC0565b(Looper.getMainLooper());
        this.f462Q = new C0566c();
        if (!(context instanceof Activity) && !((Activity) context).isFinishing()) {
            TextUtils.isEmpty("must use activity inflate controller");
            return;
        }
        this.f471m = new C1081a(context, this);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.cctv_videoview_controller, this);
        this.f449D = (RelativeLayout) viewInflate.findViewById(R$id.topLayout);
        this.f450E = (RelativeLayout) viewInflate.findViewById(R$id.bottomLayout);
        this.f451F = (LinearLayout) viewInflate.findViewById(R$id.fixedTopLayout);
        PauseTipsView pauseTipsView = (PauseTipsView) viewInflate.findViewById(R$id.pauseLayout);
        this.f452G = pauseTipsView;
        pauseTipsView.setMediaController(this);
        this.f473o = (ImageView) viewInflate.findViewById(R$id.back);
        this.f472n = (RelativeLayout) viewInflate.findViewById(R$id.backFixedLayout);
        this.f474p = (TextView) viewInflate.findViewById(R$id.title);
        this.f475q = (ImageView) viewInflate.findViewById(R$id.fullScreen);
        this.f476r = (ImageView) viewInflate.findViewById(R$id.subFullScreen);
        TextView textView = (TextView) viewInflate.findViewById(R$id.subSkip);
        this.f477s = textView;
        textView.setEnabled(false);
        this.f478t = (ImageView) viewInflate.findViewById(R$id.liveStatus);
        this.f479u = (TextView) viewInflate.findViewById(R$id.currenTime);
        this.f480v = (TextView) viewInflate.findViewById(R$id.endTime);
        this.f481w = (ScrollableSeekBar) viewInflate.findViewById(R$id.playProgress);
        this.f482x = (LinearLayout) viewInflate.findViewById(R$id.playProgressNodeLayout);
        ThumbImageView thumbImageView = (ThumbImageView) viewInflate.findViewById(R$id.thumbView);
        this.f483y = thumbImageView;
        thumbImageView.setSeekBarParentView(this.f482x);
        m353d();
        this.f446A = (TextView) viewInflate.findViewById(R$id.rate);
        this.f447B = (ImageView) viewInflate.findViewById(R$id.lock);
        ImageView imageView = (ImageView) viewInflate.findViewById(R$id.playPause);
        this.f448C = imageView;
        imageView.setSelected(true);
        this.f459N = (LinearLayout) viewInflate.findViewById(R$id.controllerCenterLayout);
        this.f473o.setOnClickListener(this);
        this.f472n.setOnClickListener(this);
        this.f475q.setOnClickListener(this);
        this.f476r.setOnClickListener(this);
        this.f477s.setOnClickListener(this);
        this.f448C.setOnClickListener(this);
        this.f447B.setOnClickListener(this);
        this.f446A.setOnClickListener(this);
        this.f452G.setDrawBackClickListener(this);
        this.f452G.setAdvanceClickListener(this);
        this.f478t.setOnClickListener(this);
        this.f481w.setOnSeekBarChangeListener(this.f462Q);
        this.f481w.addOnLayoutChangeListener(new ViewOnLayoutChangeListenerC0921b(this));
        this.f461P.sendEmptyMessageDelayed(101, 3000L);
    }
}
