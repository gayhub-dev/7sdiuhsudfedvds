package com.cctv.p025tv.module.player;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cctv.cctvplayer.CCTVVideoMediaController;
import com.cctv.cctvplayer.CCTVVideoView;
import com.cctv.cctvplayer.player.EnumC0567a;
import com.cctv.p025tv.R;
import com.cctv.p025tv.module.function.guid.CloudUtils;
import com.cctv.p025tv.module.function.guid.TempCloudDevice;
import com.cctv.p025tv.mvp.p026ui.fragment.CheckPlayerFragment;
import p009b.C0413b;
import p045f1.C0992a;
import p053g1.InterfaceC1044b;
import p053g1.InterfaceC1047e;
import p053g1.InterfaceC1049g;
import p078j2.C1188c;
import p095l3.C1423c;
import p101m1.C1458b;
import p158t2.C1901d;
import p164u1.C1971a;
import p186x2.C2073a;
import p200z2.EnumC2151b;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.format.mpegts.PesPrivateDataTimeStamp;

/* loaded from: classes.dex */
public class CheckDevicePlayer extends CCTVVideoView implements InterfaceC1044b, InterfaceC1047e, C1188c.b {

    /* renamed from: N */
    public static final /* synthetic */ int f583N = 0;

    /* renamed from: J */
    public CheckPlayerFragment f584J;

    /* renamed from: K */
    public int f585K;

    /* renamed from: L */
    public C1188c f586L;

    /* renamed from: M */
    public InterfaceC1049g f587M;

    /* renamed from: com.cctv.tv.module.player.CheckDevicePlayer$a */
    public class C0584a implements InterfaceC1049g {
        public C0584a() {
        }

        @Override // p053g1.InterfaceC1049g
        /* renamed from: a */
        public void mo434a(long j7) {
            CheckDevicePlayer checkDevicePlayer = CheckDevicePlayer.this;
            int i7 = checkDevicePlayer.f585K;
            if (i7 >= 0) {
                CheckPlayerFragment checkPlayerFragment = checkDevicePlayer.f584J;
                if (checkPlayerFragment != null) {
                    checkDevicePlayer.f585K = i7 - 1;
                    checkPlayerFragment.f765o.setText(String.valueOf(i7));
                    return;
                }
                return;
            }
            CheckPlayerFragment checkPlayerFragment2 = checkDevicePlayer.f584J;
            if (checkPlayerFragment2 != null) {
                checkPlayerFragment2.m488m(true);
                CheckDevicePlayer.this.f584J.m489n(false);
                CheckDevicePlayer.this.mo387r();
            }
        }

        @Override // p053g1.InterfaceC1049g
        /* renamed from: b */
        public void mo435b(long j7) {
        }
    }

    /* renamed from: com.cctv.tv.module.player.CheckDevicePlayer$b */
    public class RunnableC0585b implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ String f589e;

        public RunnableC0585b(String str) {
            this.f589e = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            CheckDevicePlayer checkDevicePlayer = CheckDevicePlayer.this;
            int i7 = CheckDevicePlayer.f583N;
            TextView textView = checkDevicePlayer.f490C;
            StringBuilder sbM112a = C0413b.m112a("下载速度：");
            sbM112a.append(this.f589e);
            sbM112a.append("/s");
            textView.setText(sbM112a.toString());
        }
    }

    public CheckDevicePlayer(Context context) {
        this(context, null);
    }

    @Override // p053g1.InterfaceC1047e
    /* renamed from: a */
    public void mo426a(String str, boolean z6, EnumC0567a enumC0567a) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        mo388s(str, false, null, z6);
    }

    @Override // p053g1.InterfaceC1044b
    /* renamed from: b */
    public void mo427b(int i7) {
    }

    @Override // p053g1.InterfaceC1047e
    /* renamed from: c */
    public void mo428c(String str, long j7, EnumC0567a enumC0567a) {
    }

    @Override // p053g1.InterfaceC1047e
    /* renamed from: d */
    public void mo429d(String str, EnumC0567a enumC0567a) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        mo388s(str, false, null, false);
    }

    @Override // p053g1.InterfaceC1044b
    /* renamed from: e */
    public void mo430e(int i7) {
        getMediaController().findViewById(R.id.fullScreen).setVisibility(8);
        getMediaController().findViewById(R.id.subFullScreen).setVisibility(8);
        getMediaController().findViewById(R.id.rightBottomLayout).setVisibility(8);
        getMediaController().findViewById(R.id.liveStatus).setVisibility(8);
        getMediaController().findViewById(R.id.back).setVisibility(8);
        getMediaController().findViewById(R.id.fixedTopLayout).setVisibility(8);
        getMediaController().findViewById(R.id.controllerLeftLayout).setVisibility(8);
        getMediaController().findViewById(R.id.controllerLeftLayout).setVisibility(8);
    }

    @Override // p078j2.C1188c.b
    /* renamed from: f */
    public void mo431f(long j7) {
        String strMo2594c = EnumC2151b.f6321m.mo2594c(j7);
        if (getContext() instanceof AppCompatActivity) {
            ((AppCompatActivity) getContext()).runOnUiThread(new RunnableC0585b(strMo2594c));
        }
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: g */
    public void mo377g(IMediaPlayer iMediaPlayer, PesPrivateDataTimeStamp pesPrivateDataTimeStamp) {
        C2073a.m2459d("OnMpegTsPesPrivateData_");
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: i */
    public void mo378i() {
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: j */
    public void mo379j(C1423c c1423c) {
        super.mo379j(c1423c);
        C1188c c1188c = this.f586L;
        if (c1188c != null) {
            c1188c.m1396a();
        }
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: k */
    public void mo380k(IMediaPlayer iMediaPlayer, int i7, int i8) {
        super.mo380k(iMediaPlayer, i7, i8);
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: l */
    public void mo381l() {
        super.mo381l();
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: m */
    public void mo382m() {
        CheckPlayerFragment checkPlayerFragment = this.f584J;
        if (checkPlayerFragment != null) {
            checkPlayerFragment.m488m(true);
            this.f584J.m489n(false);
        }
        super.mo382m();
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: n */
    public void mo383n(boolean z6) {
        CheckPlayerFragment checkPlayerFragment;
        if (z6 && (checkPlayerFragment = this.f584J) != null) {
            checkPlayerFragment.m489n(true);
            this.f585K = getPlayerView().getDuration() / 1000;
        }
        super.mo383n(z6);
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: o */
    public void mo384o(IMediaPlayer iMediaPlayer) {
        super.mo384o(iMediaPlayer);
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: p */
    public void mo385p() {
        super.mo385p();
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: q */
    public void mo386q(IMediaPlayer iMediaPlayer) {
        iMediaPlayer.start();
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: r */
    public void mo387r() {
        super.mo387r();
        C1188c c1188c = this.f586L;
        if (c1188c != null) {
            c1188c.m1396a();
        }
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: s */
    public void mo388s(String str, boolean z6, String str2, boolean z7) {
        String strEncryptByPublicKey;
        C1458b.m1642a("playURL = ", str);
        try {
            strEncryptByPublicKey = CloudUtils.encryptByPublicKey(TempCloudDevice.getGuid(), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/ZeLwTPPLSU7QGwv6tVgdawz9n7S2CxboIEVQlQ1USAHvBRlWBsU2l7+HuUVMJ5blqGc/5y3AoaUzPGoXPfIm0GnBdFL+iLeRDwOS1KgcQ0fIquvr/2Xzj3fVA1o4Y81wJK5BP8bDTBFYMVOlOoCc1ZzWwdZBYpb4FNxt//5dAwIDAQAB");
        } catch (Exception e7) {
            e7.printStackTrace();
            strEncryptByPublicKey = "";
        }
        C1901d c1901d = new C1901d();
        c1901d.f5604e = null;
        c1901d.f5600a = false;
        c1901d.f5601b = str;
        c1901d.f5605f = "https://ytpaddr.cctv.cn/gsnw/tpa/sk/obtain";
        c1901d.f5606g = strEncryptByPublicKey;
        c1901d.f5607h = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/ZeLwTPPLSU7QGwv6tVgdawz9n7S2CxboIEVQlQ1USAHvBRlWBsU2l7+HuUVMJ5blqGc/5y3AoaUzPGoXPfIm0GnBdFL+iLeRDwOS1KgcQ0fIquvr/2Xzj3fVA1o4Y81wJK5BP8bDTBFYMVOlOoCc1ZzWwdZBYpb4FNxt//5dAwIDAQAB";
        c1901d.f5602c = ((AppCompatActivity) getContext()).getLifecycle();
        c1901d.m2199a(new C1971a(this, str, z6, (String) null, z7));
    }

    public void setFragment(CheckPlayerFragment checkPlayerFragment) {
        this.f584J = checkPlayerFragment;
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: t */
    public void mo389t(int i7) {
        super.mo389t(i7);
    }

    /* renamed from: x */
    public void m432x(C0992a c0992a) {
        this.f514v = 0;
        getMediaController().m368s();
        setPlay(c0992a);
        mo388s(c0992a.m1011e(false), false, null, false);
        CCTVVideoMediaController mediaController = getMediaController();
        mediaController.f481w.setVisibility(8);
        mediaController.f482x.setVisibility(8);
        mediaController.f483y.setVisibility(8);
        ((ViewGroup) mediaController.f481w.getParent()).setOnTouchListener(null);
        CCTVVideoMediaController mediaController2 = getMediaController();
        mediaController2.f454I = 4;
        mediaController2.f461P.removeMessages(101);
        mediaController2.f450E.setVisibility(4);
    }

    /* renamed from: y */
    public final void m433y(String str, boolean z6, String str2, boolean z7) {
        this.f497e = 2;
        CheckPlayerFragment checkPlayerFragment = this.f584J;
        if (checkPlayerFragment != null) {
            String strM1009c = getPlayEntity().m1009c();
            checkPlayerFragment.f764n.setText(String.format("%s%s%s", checkPlayerFragment.getString(R.string.check_play_tips_1), checkPlayerFragment.f758h.getPlayEntity().m1008b().f1854e ? "36".equals(strM1009c) ? "超高清菁彩视听" : "11".equals(strM1009c) ? "高清菁彩视听" : "" : "4K超高清", checkPlayerFragment.getString(R.string.check_play_tips_2)));
        }
        super.mo388s(str, z6, str2, z7);
        this.f586L.m1398c();
    }

    public CheckDevicePlayer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CheckDevicePlayer(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f586L = new C1188c();
        this.f587M = new C0584a();
        setPlayListener(this);
        getMediaController().setCCTVControllerListener(this);
        getMediaController().setOnProgressListener(this.f587M);
        this.f586L.f2610d = this;
        setIsForbiddenGesture(true);
    }
}
