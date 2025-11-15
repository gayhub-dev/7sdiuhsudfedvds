package com.cctv.p025tv.mvp.p026ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.motion.Key;
import android.support.v7.widget.ActivityChooserModel;
import android.support.v7.widget.ActivityChooserView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.cctv.cctvplayer.CCTVVideoMediaController;
import com.cctv.cctvplayer.player.DolbyHeadsetPlugReceiver;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.base.BaseFragment;
import com.cctv.p025tv.entity.HighBitrateEntity;
import com.cctv.p025tv.module.player.VideoPlayer;
import com.cctv.p025tv.module.player.WarmVideoPlayer;
import com.cctv.p025tv.module.service.WebSocketService;
import com.cctv.p025tv.mvp.p026ui.activity.MainActivity;
import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import com.ctvit.dlna.entity.CctvEntity;
import com.ctvit.dlna.entity.DlnaContentEntity;
import com.easefun.povplayer.core.video.PolyvSubVideoView;
import com.easefun.povplayer.core.video.PolyvVideoView;
import com.tencent.mars.xlog.Log;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.fourthline.cling.support.model.TransportState;
import p009b.C0413b;
import p012b2.C0439b;
import p020c2.ViewOnClickListenerC0507b;
import p035e.AbstractC0895h;
import p035e.C0901n;
import p036e0.C0912k;
import p043f.C0988e;
import p045f1.C0992a;
import p045f1.C0993b;
import p054g2.C1057h;
import p078j2.C1186a;
import p086k2.C1231b;
import p093l1.C1415a;
import p107n.AbstractC1501a;
import p107n.C1506f;
import p108n0.C1512c;
import p118o2.C1581b;
import p141r.C1814i;
import p141r.ComponentCallbacks2C1808c;
import p144r2.C1830c;
import p162u.EnumC1958b;
import p178w1.InterfaceC2024a;
import p183x.AbstractC2047h;
import p186x2.C2073a;
import p190y.AbstractC2085c;
import p192y1.RunnableC2099c;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: classes.dex */
public class VideoFragment extends BaseFragment<Object, C0439b> {

    /* renamed from: B */
    public static final /* synthetic */ int f854B = 0;

    /* renamed from: A */
    public ConstraintLayout f855A;

    /* renamed from: h */
    public VideoPlayer f856h;

    /* renamed from: i */
    public VideoPlayer f857i;

    /* renamed from: k */
    public WarmVideoPlayer f859k;

    /* renamed from: l */
    public ImageView f860l;

    /* renamed from: m */
    public DlnaContentEntity f861m;

    /* renamed from: n */
    public Dialog f862n;

    /* renamed from: p */
    public RelativeLayout f864p;

    /* renamed from: q */
    public RelativeLayout f865q;

    /* renamed from: r */
    public RelativeLayout f866r;

    /* renamed from: s */
    public TextView f867s;

    /* renamed from: t */
    public TextView f868t;

    /* renamed from: u */
    public TextView f869u;

    /* renamed from: y */
    public FrameLayout f873y;

    /* renamed from: z */
    public boolean f874z;

    /* renamed from: j */
    public boolean f858j = false;

    /* renamed from: o */
    public int f863o = 0;

    /* renamed from: v */
    public boolean f870v = false;

    /* renamed from: w */
    public boolean f871w = false;

    /* renamed from: x */
    public Handler f872x = new HandlerC0616a(Looper.getMainLooper());

    /* renamed from: com.cctv.tv.mvp.ui.fragment.VideoFragment$a */
    public class HandlerC0616a extends Handler {
        public HandlerC0616a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    C2073a.m2459d("切换成功 - SWITCH_VIDEO_SHOW");
                    VideoFragment videoFragment = VideoFragment.this;
                    videoFragment.f858j = true;
                    videoFragment.f874z = false;
                    videoFragment.f857i.setVisibility(0);
                    VideoFragment.this.f856h.mo387r();
                    VideoFragment.this.f856h.setVisibility(8);
                    VideoFragment videoFragment2 = VideoFragment.this;
                    VideoFragment.m506j(videoFragment2, videoFragment2.f867s);
                    break;
                case 1002:
                    C2073a.m2459d("切换成功 - VIDEO_SHOW");
                    VideoFragment videoFragment3 = VideoFragment.this;
                    videoFragment3.f858j = false;
                    videoFragment3.f874z = false;
                    videoFragment3.f856h.setVisibility(0);
                    VideoFragment.this.f857i.setVisibility(8);
                    VideoFragment.this.f857i.mo387r();
                    VideoFragment.this.f857i.getPlayerView().start();
                    VideoFragment videoFragment4 = VideoFragment.this;
                    VideoFragment.m506j(videoFragment4, videoFragment4.f867s);
                    break;
                case 1003:
                    VideoFragment videoFragment5 = VideoFragment.this;
                    if (!videoFragment5.f871w) {
                        videoFragment5.f857i.getPlayerView().start();
                        C2073a.m2459d("LOADING_TIME - seek =" + VideoFragment.this.f863o);
                        VideoFragment videoFragment6 = VideoFragment.this;
                        videoFragment6.f857i.mo389t(videoFragment6.f863o);
                        VideoFragment.this.f857i.getPlayerView().start();
                        break;
                    } else {
                        videoFragment5.f870v = false;
                        videoFragment5.f872x.removeMessages(1001);
                        VideoFragment.this.f872x.sendEmptyMessage(1001);
                        break;
                    }
                case 1004:
                    VideoFragment.this.f856h.mo387r();
                    VideoFragment.this.f856h.setVisibility(8);
                    VideoFragment videoFragment7 = VideoFragment.this;
                    VideoFragment.m506j(videoFragment7, videoFragment7.f867s);
                    break;
                case 1005:
                    if (((Boolean) message.obj).booleanValue()) {
                        VideoFragment videoFragment8 = VideoFragment.this;
                        videoFragment8.f868t.setText(videoFragment8.getResources().getString(R.string.phone_up_toast_text));
                    } else {
                        VideoFragment videoFragment9 = VideoFragment.this;
                        videoFragment9.f868t.setText(videoFragment9.getResources().getString(R.string.phone_down_toast_text));
                    }
                    VideoFragment videoFragment10 = VideoFragment.this;
                    videoFragment10.m515s(videoFragment10.f865q, videoFragment10.getResources().getDimension(R.dimen.dp_524), 3000L);
                    break;
                case 1006:
                    VideoFragment videoFragment11 = VideoFragment.this;
                    if (!videoFragment11.f871w) {
                        videoFragment11.f857i.getPlayerView().start();
                        VideoFragment videoFragment12 = VideoFragment.this;
                        videoFragment12.f856h.mo389t(videoFragment12.f863o);
                        break;
                    } else {
                        videoFragment11.f870v = false;
                        videoFragment11.f872x.removeMessages(1002);
                        VideoFragment.this.f872x.sendEmptyMessage(1002);
                        break;
                    }
                case 1007:
                    C2073a.m2459d("WARM_VIDEO_STOP");
                    VideoFragment.this.f859k.mo387r();
                    break;
            }
        }
    }

    /* renamed from: com.cctv.tv.mvp.ui.fragment.VideoFragment$b */
    public class C0617b implements InterfaceC2024a {
        public C0617b() {
        }

        @Override // p178w1.InterfaceC2024a
        /* renamed from: a */
        public void mo519a() {
            if (VideoFragment.this.f870v) {
                StringBuilder sbM112a = C0413b.m112a("播放器 - mVideoPlayerCopy--onStart---isLive = ");
                sbM112a.append(VideoFragment.this.f871w);
                C2073a.m2459d(sbM112a.toString());
                VideoFragment.this.f872x.removeMessages(1003);
                VideoFragment.this.f872x.sendEmptyMessage(1003);
            }
        }

        @Override // p178w1.InterfaceC2024a
        /* renamed from: b */
        public void mo520b() {
            if (VideoFragment.this.f870v) {
                C2073a.m2459d("播放器 - mVideoPlayerCopy--seekToOver");
                VideoFragment.this.f872x.removeMessages(1001);
                VideoFragment.this.f872x.sendEmptyMessage(1001);
                VideoFragment.this.f870v = false;
            }
        }
    }

    /* renamed from: com.cctv.tv.mvp.ui.fragment.VideoFragment$c */
    public class C0618c implements InterfaceC2024a {
        public C0618c() {
        }

        @Override // p178w1.InterfaceC2024a
        /* renamed from: a */
        public void mo519a() {
            StringBuilder sbM112a = C0413b.m112a("播放器 - mVideoPlayer--onStart---isLive = ");
            sbM112a.append(VideoFragment.this.f871w);
            C2073a.m2459d(sbM112a.toString());
            VideoFragment videoFragment = VideoFragment.this;
            if (videoFragment.f870v) {
                videoFragment.f872x.removeMessages(1006);
                VideoFragment.this.f872x.sendEmptyMessage(1006);
            }
        }

        @Override // p178w1.InterfaceC2024a
        /* renamed from: b */
        public void mo520b() {
            if (VideoFragment.this.f870v) {
                C2073a.m2459d("播放器 - mVideoPlayer--seekToOver");
                VideoFragment.this.f872x.removeMessages(1002);
                VideoFragment.this.f872x.sendEmptyMessage(1002);
                VideoFragment.this.f870v = false;
            }
        }
    }

    /* renamed from: com.cctv.tv.mvp.ui.fragment.VideoFragment$d */
    public class C0619d implements InterfaceC2024a {
        public C0619d() {
        }

        @Override // p178w1.InterfaceC2024a
        /* renamed from: a */
        public void mo519a() {
            if (!C1415a.f4150b.equals("shenpian")) {
                C2073a.m2459d("mVideoPlayerWarmUp stop");
                VideoFragment.this.f859k.mo387r();
            } else {
                C2073a.m2459d("mVideoPlayerWarmUp onPause---after---stop");
                VideoFragment.this.f859k.getMediaController().f470l.pause();
                VideoFragment.this.f872x.sendEmptyMessageDelayed(1007, 2000L);
            }
        }

        @Override // p178w1.InterfaceC2024a
        /* renamed from: b */
        public void mo520b() {
        }
    }

    /* renamed from: com.cctv.tv.mvp.ui.fragment.VideoFragment$e */
    public class C0620e implements Animator.AnimatorListener {

        /* renamed from: a */
        public final /* synthetic */ View f879a;

        public C0620e(VideoFragment videoFragment, View view) {
            this.f879a = view;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f879a.setVisibility(8);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* renamed from: j */
    public static void m506j(VideoFragment videoFragment, TextView textView) {
        Objects.requireNonNull(videoFragment);
        SpannableString spannableString = new SpannableString(videoFragment.getResources().getString(R.string.hd_text));
        spannableString.setSpan(new ForegroundColorSpan(videoFragment.getResources().getColor(R.color.update_btn_color)), 10, 13, 17);
        textView.setText(spannableString);
        videoFragment.m515s(videoFragment.f864p, videoFragment.getResources().getDimension(R.dimen.dp_439_5), 3000L);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return new C0439b();
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_video;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        C0988e.m970N(true);
        VideoPlayer videoPlayer = (VideoPlayer) this.f577f.findViewById(R.id.video_player);
        this.f856h = videoPlayer;
        videoPlayer.setVideoFragment(this);
        VideoPlayer videoPlayer2 = (VideoPlayer) this.f577f.findViewById(R.id.video_player_switch);
        this.f857i = videoPlayer2;
        videoPlayer2.setVideoFragment(this);
        WarmVideoPlayer warmVideoPlayer = (WarmVideoPlayer) this.f577f.findViewById(R.id.video_player_warm_up);
        this.f859k = warmVideoPlayer;
        warmVideoPlayer.setVideoFragment(this);
        this.f864p = (RelativeLayout) this.f577f.findViewById(R.id.rl_switch_toast);
        this.f865q = (RelativeLayout) this.f577f.findViewById(R.id.rl_phone_toast);
        this.f868t = (TextView) this.f577f.findViewById(R.id.tv_phone_toast);
        this.f866r = (RelativeLayout) this.f577f.findViewById(R.id.rl_device_no_support_dolby);
        this.f869u = (TextView) this.f577f.findViewById(R.id.tv_device_no_support_dolby);
        this.f867s = (TextView) this.f577f.findViewById(R.id.tv_hd);
        this.f860l = (ImageView) this.f577f.findViewById(R.id.dxloadingview);
        this.f873y = (FrameLayout) this.f577f.findViewById(R.id.fl_dxloadingview);
        this.f855A = (ConstraintLayout) this.f577f.findViewById(R.id.cl_logo_parent);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
        C1581b.m1836e().f4748d = new C1057h(this, 0);
        C1581b.m1836e().f4750f = new C1057h(this, 1);
        C1581b.m1836e().f4749e = new C1057h(this, 2);
        C1581b.m1836e().f4751g = new C1057h(this, 3);
        C1581b.m1836e().f4752h = new C1057h(this, 4);
        this.f857i.setIVideoPlayerListener(new C0617b());
        this.f856h.setIVideoPlayerListener(new C0618c());
        this.f859k.setIVideoPlayerListener(new C0619d());
    }

    /* renamed from: k */
    public final void m507k() {
        C2073a.m2459d("播放器 - changeBackPlayer");
        this.f856h.setVisibility(0);
        this.f857i.setVisibility(8);
        this.f857i.mo387r();
        this.f858j = false;
        this.f870v = false;
        this.f871w = false;
        this.f863o = 0;
        this.f874z = false;
    }

    /* renamed from: l */
    public VideoPlayer m508l() {
        if (this.f858j) {
            C2073a.m2459d("getVideoPlayer - mVideoPlayerCopy");
            return this.f857i;
        }
        C2073a.m2459d("getVideoPlayer - mVideoPlayer");
        return this.f856h;
    }

    /* renamed from: m */
    public WebSocketService m509m() {
        return ((MainActivity) getActivity()).f669h;
    }

    /* renamed from: n */
    public void m510n(DlnaContentEntity dlnaContentEntity) {
        StringBuilder sbM112a = C0413b.m112a("receiveDlnaMsg:");
        sbM112a.append(JSON.toJSONString(dlnaContentEntity));
        Log.m655i("XLog_DLNA ", sbM112a.toString());
        if (this.f856h == null) {
            C2073a.m2459d("mVideoPlayer is null");
            return;
        }
        if (dlnaContentEntity == null) {
            C2073a.m2459d("receiveDlnaMsg DlnaContentEntity is null");
            return;
        }
        if (getActivity() == null || !C1186a.m1389i()) {
            return;
        }
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity == null || !mainActivity.m472o()) {
            this.f861m = dlnaContentEntity;
            getActivity().runOnUiThread(new RunnableC2099c(this, dlnaContentEntity));
            return;
        }
        StringBuilder sbM112a2 = C0413b.m112a("DLNA activity.judgeHuaWei() = ");
        sbM112a2.append(mainActivity.m472o());
        C2073a.m2459d(sbM112a2.toString());
        MyApplication.f569m = dlnaContentEntity;
        Log.m655i("XLog_DLNA ", "fragment 华为拦截 接收投屏数据");
        if (C1581b.m1833b() != null) {
            ((C1830c.b) C1581b.m1833b()).m2078e();
        }
    }

    /* renamed from: o */
    public void m511o() {
        FrameLayout frameLayout = this.f873y;
        if (frameLayout == null || frameLayout.getVisibility() != 0) {
            return;
        }
        this.f873y.setVisibility(8);
        m508l().setHideLoading(false);
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        m510n(this.f861m);
        return this.f577f;
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.f861m = null;
        CCTVVideoMediaController mediaController = m508l().getMediaController();
        Objects.requireNonNull(mediaController);
        TextUtils.isEmpty("onDestroy...");
        mediaController.f461P.removeCallbacksAndMessages(null);
        PolyvVideoView polyvVideoView = mediaController.f470l;
        polyvVideoView.f1155I = true;
        polyvVideoView.f1170a0.m1455a();
        C0901n c0901n = polyvVideoView.f1173d0;
        if (c0901n != null) {
            Context context = polyvVideoView.f1154H;
            C1506f c1506f = c0901n.f1595b;
            c1506f.f4316e = false;
            if (((AbstractC1501a) c1506f.f4142b).mo1588a(context)) {
                ((AbstractC1501a) c1506f.f4142b).mo1590d(context);
            }
            AbstractC0895h abstractC0895h = c0901n.f1600g;
            if (abstractC0895h != null) {
                abstractC0895h.mo801c();
            }
            polyvVideoView.f1173d0.m814b();
        }
        PolyvSubVideoView polyvSubVideoView = polyvVideoView.f1161O;
        polyvSubVideoView.f1132v = true;
        polyvSubVideoView.mo574a(true);
        polyvSubVideoView.m573o();
        polyvSubVideoView.f1146r = null;
        polyvSubVideoView.f1147s = null;
        polyvSubVideoView.f1145q = null;
        IjkMediaPlayer.native_profileEnd();
        polyvVideoView.f1105e.mo584k();
        polyvVideoView.mo574a(true);
        polyvVideoView.m573o();
        polyvVideoView.f1206q = null;
        polyvVideoView.f1207r = null;
        polyvVideoView.f1208s = null;
        polyvVideoView.f1209t = null;
        polyvVideoView.f1210u = null;
        polyvVideoView.f1211v = null;
        polyvVideoView.f1212w = null;
        polyvVideoView.f1213x = null;
        polyvVideoView.f1214y = null;
        polyvVideoView.f1215z = null;
        polyvVideoView.f1204A = null;
        polyvVideoView.f1205B = null;
        IjkMediaPlayer.native_profileEnd();
        mediaController.f468j.mo378i();
        DolbyHeadsetPlugReceiver dolbyHeadsetPlugReceiver = mediaController.f468j.getDolbyHeadsetPlugReceiver();
        if (dolbyHeadsetPlugReceiver.f522c) {
            dolbyHeadsetPlugReceiver.f522c = false;
            TextUtils.isEmpty("取消注册...");
            dolbyHeadsetPlugReceiver.f520a.unregisterReceiver(dolbyHeadsetPlugReceiver);
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        super.onHiddenChanged(z6);
        if (!z6 || this.f856h == null) {
            C0988e.m970N(true);
        } else {
            m518v();
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        m517u();
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        m518v();
    }

    /* renamed from: p */
    public void m512p(String str) {
        if (m508l().getPlayerView().isPlaying()) {
            m516t();
        } else {
            m518v();
        }
        if (getActivity() == null || getContext() == null || getActivity().getWindowManager() == null) {
            return;
        }
        C2073a.m2459d("msg = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Dialog dialog = this.f862n;
        if (dialog == null) {
            this.f862n = new Dialog(getActivity(), R.style.MyDialog);
            View viewInflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.dialog_videofragment, (ViewGroup) null);
            this.f862n.setContentView(viewInflate);
            ViewGroup.LayoutParams layoutParams = viewInflate.getLayoutParams();
            layoutParams.width = getResources().getDisplayMetrics().widthPixels;
            viewInflate.setLayoutParams(layoutParams);
            this.f862n.setCancelable(false);
            TextView textView = (TextView) viewInflate.findViewById(R.id.tv_vf_dialog_title);
            Button button = (Button) viewInflate.findViewById(R.id.btn_dialog_yes);
            textView.setText(str);
            button.setOnClickListener(new ViewOnClickListenerC0507b(this));
        } else {
            dialog.dismiss();
        }
        this.f862n.show();
    }

    /* renamed from: q */
    public void m513q(List<C0993b.a> list) {
        ConstraintLayout constraintLayout = this.f855A;
        if (constraintLayout == null) {
            return;
        }
        constraintLayout.removeAllViews();
        if (list == null || list.isEmpty()) {
            return;
        }
        for (C0993b.a aVar : list) {
            Context context = getContext();
            ConstraintLayout constraintLayout2 = this.f855A;
            ImageView imageView = new ImageView(context);
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(-2, -2);
            ((ViewGroup.MarginLayoutParams) layoutParams).height = 0;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = 0;
            layoutParams.startToStart = constraintLayout2.getId();
            layoutParams.endToEnd = constraintLayout2.getId();
            layoutParams.topToTop = constraintLayout2.getId();
            layoutParams.bottomToBottom = constraintLayout2.getId();
            layoutParams.horizontalBias = Float.parseFloat(aVar.f1861a);
            layoutParams.verticalBias = Float.parseFloat(aVar.f1865e);
            layoutParams.matchConstraintPercentHeight = Float.parseFloat(aVar.f1863c);
            layoutParams.matchConstraintPercentWidth = Float.parseFloat(aVar.f1864d);
            StringBuilder sbM112a = C0413b.m112a("logos params.matchConstraintPercentHeight = ");
            sbM112a.append(layoutParams.matchConstraintPercentHeight);
            sbM112a.append("; params.matchConstraintPercentWidth = ");
            sbM112a.append(layoutParams.matchConstraintPercentWidth);
            sbM112a.append("; params.horizontalBias = ");
            sbM112a.append(layoutParams.horizontalBias);
            sbM112a.append("; params.verticalBias = ");
            sbM112a.append(layoutParams.verticalBias);
            Log.m655i("XLog_APP ", sbM112a.toString());
            if (!TextUtils.isEmpty(aVar.f1862b)) {
                C1512c c1512cM1678J = new C1512c().m1689j(AbstractC2047h.f6044b).m1678J(Integer.MIN_VALUE, Integer.MIN_VALUE);
                EnumC1958b enumC1958b = EnumC1958b.PREFER_ARGB_8888;
                Objects.requireNonNull(c1512cM1678J);
                C1512c c1512cM1682S = c1512cM1678J.m1682S(C0912k.f1644f, enumC1958b);
                C1814i c1814iM2023e = ComponentCallbacks2C1808c.m2023e(context);
                c1814iM2023e.m2043m(c1512cM1682S);
                c1814iM2023e.m2042l(aVar.f1862b).m2038j(imageView);
                constraintLayout2.addView(imageView, layoutParams);
            }
        }
    }

    /* renamed from: r */
    public void m514r(final DlnaContentEntity dlnaContentEntity, final C0992a c0992a, final HighBitrateEntity.DataBean dataBean, int i7, final String str) {
        C2073a.m2459d("播放器 - 切换视频- seek =" + i7);
        C2073a.m2459d("播放器 - 切换视频- dataBean =" + JSON.toJSONString(dataBean));
        this.f863o = i7;
        this.f871w = c0992a.f1839c;
        this.f870v = true;
        this.f874z = true;
        try {
            CctvEntity cctvEntity = (CctvEntity) JSON.parseObject(dlnaContentEntity.getCctv(), CctvEntity.class);
            cctvEntity.getPlayer().setChange_bitrate(str);
            c0992a.f1844h = cctvEntity;
        } catch (Exception e7) {
            C2073a.m2458c(e7);
        }
        if (this.f858j) {
            final int i8 = 1;
            getActivity().runOnUiThread(new Runnable(this) { // from class: g2.f

                /* renamed from: f */
                public final /* synthetic */ VideoFragment f1985f;

                {
                    this.f1985f = this;
                }

                @Override // java.lang.Runnable
                public final void run() throws NumberFormatException {
                    switch (i8) {
                        case 0:
                            VideoFragment videoFragment = this.f1985f;
                            DlnaContentEntity dlnaContentEntity2 = dlnaContentEntity;
                            C0992a c0992a2 = c0992a;
                            HighBitrateEntity.DataBean dataBean2 = dataBean;
                            String str2 = str;
                            videoFragment.f857i.setDlnaContentEntity(dlnaContentEntity2);
                            videoFragment.f857i.mo387r();
                            VideoPlayer videoPlayer = videoFragment.f857i;
                            try {
                                List<C0993b> list = c0992a2.f1843g;
                                int iM1010d = c0992a2.m1010d();
                                int i9 = Integer.parseInt(str2);
                                if (list != null && i9 < list.size()) {
                                    list.get(iM1010d).f1853d = false;
                                    list.get(i9).f1853d = true;
                                }
                                C2073a.m2459d("切换到index = " + i9);
                            } catch (Exception e8) {
                                C2073a.m2458c(e8);
                            }
                            videoPlayer.m442E(c0992a2, dataBean2, true);
                            break;
                        default:
                            VideoFragment videoFragment2 = this.f1985f;
                            DlnaContentEntity dlnaContentEntity3 = dlnaContentEntity;
                            C0992a c0992a3 = c0992a;
                            HighBitrateEntity.DataBean dataBean3 = dataBean;
                            String str3 = str;
                            videoFragment2.f856h.setDlnaContentEntity(dlnaContentEntity3);
                            videoFragment2.f856h.mo387r();
                            VideoPlayer videoPlayer2 = videoFragment2.f856h;
                            try {
                                List<C0993b> list2 = c0992a3.f1843g;
                                int iM1010d2 = c0992a3.m1010d();
                                int i10 = Integer.parseInt(str3);
                                if (list2 != null && i10 < list2.size()) {
                                    list2.get(iM1010d2).f1853d = false;
                                    list2.get(i10).f1853d = true;
                                }
                                C2073a.m2459d("切换到index = " + i10);
                            } catch (Exception e9) {
                                C2073a.m2458c(e9);
                            }
                            videoPlayer2.m442E(c0992a3, dataBean3, true);
                            break;
                    }
                }
            });
        } else {
            final int i9 = 0;
            getActivity().runOnUiThread(new Runnable(this) { // from class: g2.f

                /* renamed from: f */
                public final /* synthetic */ VideoFragment f1985f;

                {
                    this.f1985f = this;
                }

                @Override // java.lang.Runnable
                public final void run() throws NumberFormatException {
                    switch (i9) {
                        case 0:
                            VideoFragment videoFragment = this.f1985f;
                            DlnaContentEntity dlnaContentEntity2 = dlnaContentEntity;
                            C0992a c0992a2 = c0992a;
                            HighBitrateEntity.DataBean dataBean2 = dataBean;
                            String str2 = str;
                            videoFragment.f857i.setDlnaContentEntity(dlnaContentEntity2);
                            videoFragment.f857i.mo387r();
                            VideoPlayer videoPlayer = videoFragment.f857i;
                            try {
                                List<C0993b> list = c0992a2.f1843g;
                                int iM1010d = c0992a2.m1010d();
                                int i92 = Integer.parseInt(str2);
                                if (list != null && i92 < list.size()) {
                                    list.get(iM1010d).f1853d = false;
                                    list.get(i92).f1853d = true;
                                }
                                C2073a.m2459d("切换到index = " + i92);
                            } catch (Exception e8) {
                                C2073a.m2458c(e8);
                            }
                            videoPlayer.m442E(c0992a2, dataBean2, true);
                            break;
                        default:
                            VideoFragment videoFragment2 = this.f1985f;
                            DlnaContentEntity dlnaContentEntity3 = dlnaContentEntity;
                            C0992a c0992a3 = c0992a;
                            HighBitrateEntity.DataBean dataBean3 = dataBean;
                            String str3 = str;
                            videoFragment2.f856h.setDlnaContentEntity(dlnaContentEntity3);
                            videoFragment2.f856h.mo387r();
                            VideoPlayer videoPlayer2 = videoFragment2.f856h;
                            try {
                                List<C0993b> list2 = c0992a3.f1843g;
                                int iM1010d2 = c0992a3.m1010d();
                                int i10 = Integer.parseInt(str3);
                                if (list2 != null && i10 < list2.size()) {
                                    list2.get(iM1010d2).f1853d = false;
                                    list2.get(i10).f1853d = true;
                                }
                                C2073a.m2459d("切换到index = " + i10);
                            } catch (Exception e9) {
                                C2073a.m2458c(e9);
                            }
                            videoPlayer2.m442E(c0992a3, dataBean3, true);
                            break;
                    }
                }
            });
        }
    }

    /* renamed from: s */
    public final void m515s(View view, float f7, long j7) {
        view.setVisibility(0);
        float f8 = -f7;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, Key.TRANSLATION_X, 0.0f, f8);
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimatorOfFloat.setDuration(500L);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, Key.TRANSLATION_X, f8, 0.0f);
        objectAnimatorOfFloat2.setDuration(500L);
        objectAnimatorOfFloat2.setInterpolator(new DecelerateInterpolator(10.0f));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorOfFloat2).after(j7).after(objectAnimatorOfFloat);
        animatorSet.start();
        animatorSet.addListener(new C0620e(this, view));
    }

    /* renamed from: t */
    public final void m516t() {
        m508l().getMediaController().f470l.pause();
        if (C1581b.m1833b() != null) {
            ((C1830c.b) C1581b.m1833b()).m2076c();
        }
    }

    /* renamed from: u */
    public final void m517u() {
        ActivityManager activityManager = (ActivityManager) C1231b.f2761c.getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
        Iterator<ActivityManager.RunningTaskInfo> it = activityManager.getRunningTasks(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ActivityManager.RunningTaskInfo next = it.next();
            if (C1231b.f2761c.getPackageName().equals(next.topActivity.getPackageName())) {
                activityManager.moveTaskToFront(next.id, 1);
                break;
            }
        }
        m508l().getPlayerView().start();
    }

    /* renamed from: v */
    public final void m518v() {
        if (C1581b.m1833b() != null) {
            C1830c.b bVar = (C1830c.b) C1581b.m1833b();
            Objects.requireNonNull(bVar);
            C2073a.m2459d("onStop");
            Log.m655i("XLog_DLNA ", "dlna返回给手机的状态 onStop");
            C1830c.this.mo2073f(TransportState.STOPPED);
        }
        this.f856h.mo387r();
        m507k();
        Dialog dialog = this.f862n;
        if (dialog != null && dialog.isShowing()) {
            this.f862n.dismiss();
        }
        if (m509m() != null) {
            m509m().m460a();
        }
        C0988e.m970N(false);
        m513q(null);
    }
}
