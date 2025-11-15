package com.cctv.p025tv.module.player;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.cctv.cctvplayer.CCTVVideoView;
import com.cctv.cctvplayer.player.EnumC0567a;
import com.cctv.p025tv.R;
import com.cctv.p025tv.module.function.guid.CloudUtils;
import com.cctv.p025tv.module.function.guid.TempCloudDevice;
import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import p043f.C0988e;
import p053g1.InterfaceC1044b;
import p053g1.InterfaceC1047e;
import p053g1.InterfaceC1049g;
import p078j2.C1188c;
import p095l3.C1423c;
import p118o2.C1581b;
import p144r2.C1830c;
import p158t2.C1901d;
import p164u1.C1971a;
import p171v1.C2003b;
import p178w1.InterfaceC2024a;
import p179w2.C2026b;
import p186x2.C2073a;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.format.mpegts.PesPrivateDataTimeStamp;

/* loaded from: classes.dex */
public class WarmVideoPlayer extends CCTVVideoView implements InterfaceC1044b, InterfaceC1047e, C1188c.b {

    /* renamed from: M */
    public static final /* synthetic */ int f627M = 0;

    /* renamed from: J */
    public InterfaceC2024a f628J;

    /* renamed from: K */
    public VideoFragment f629K;

    /* renamed from: L */
    public InterfaceC1049g f630L;

    /* renamed from: com.cctv.tv.module.player.WarmVideoPlayer$a */
    public class C0589a implements InterfaceC1049g {
        public C0589a() {
        }

        @Override // p053g1.InterfaceC1049g
        /* renamed from: a */
        public void mo434a(long j7) {
            if (C0988e.m996v() || C1581b.m1833b() == null) {
                return;
            }
            ((C1830c.b) C1581b.m1833b()).m2077d((int) j7, WarmVideoPlayer.this.getPlayEntity().f1847k);
        }

        @Override // p053g1.InterfaceC1049g
        /* renamed from: b */
        public void mo435b(long j7) {
            if (C1581b.m1833b() != null) {
                ((C1830c.b) C1581b.m1833b()).m2075b((int) j7);
            }
        }
    }

    public WarmVideoPlayer(Context context) {
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
    }

    @Override // p078j2.C1188c.b
    /* renamed from: f */
    public void mo431f(long j7) {
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
        super.mo382m();
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: n */
    public void mo383n(boolean z6) {
        super.mo383n(z6);
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: o */
    public void mo384o(IMediaPlayer iMediaPlayer) {
        InterfaceC2024a interfaceC2024a = this.f628J;
        if (interfaceC2024a != null) {
            interfaceC2024a.mo519a();
        }
        mo387r();
        super.mo384o(iMediaPlayer);
        C2073a.m2459d("player-life warmVideo onPrepared_");
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: p */
    public void mo385p() {
        super.mo385p();
        C2073a.m2459d("player-life warmVideo onPreparing_");
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
        if (this.f629K != null) {
            this.f629K = null;
        }
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: s */
    public void mo388s(String str, boolean z6, String str2, boolean z7) {
        String strEncryptByPublicKey;
        int iM1010d = getPlayEntity().m1010d();
        C2073a.m2459d("warmVideo curRateIndex = " + iM1010d);
        C2073a.m2459d("warmVideo playURL = " + str);
        if (TextUtils.isEmpty(C2003b.m2340a(getPlayEntity())) || iM1010d == 0) {
            if (!str.contains("http://live-tp4k.cctv.cn")) {
                C2073a.m2456a("非直播4K域名，播放原地址");
                C2073a.m2459d("warmVideo 低码不带防盗链");
                getPlayEntity().f1842f = null;
                m454x(str, z6, null, z7);
                return;
            }
            C2073a.m2459d("warmVideo 低码，但带HEADER防盗链");
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
            c1901d.m2199a(new C1971a(this, z6, (String) null, z7, str));
        }
    }

    public void setIVideoPlayerListener(InterfaceC2024a interfaceC2024a) {
        this.f628J = interfaceC2024a;
    }

    public void setVideoFragment(VideoFragment videoFragment) {
        this.f629K = videoFragment;
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: t */
    public void mo389t(int i7) {
        super.mo389t(i7);
    }

    /* renamed from: x */
    public final void m454x(String str, boolean z6, String str2, boolean z7) {
        m390u();
        this.f497e = 2;
        VideoFragment videoFragment = this.f629K;
        if (videoFragment == null || !videoFragment.isHidden()) {
            super.mo388s(str, z6, str2, z7);
        } else {
            mo387r();
        }
    }

    public WarmVideoPlayer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WarmVideoPlayer(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f630L = new C0589a();
        setPlayListener(this);
        ((RelativeLayout) getMediaController().findViewById(R.id.topLayout)).setBackgroundColor(C2026b.m2378a(R.color.top_controller_bar_style));
        ((RelativeLayout) getMediaController().findViewById(R.id.bottomLayout)).setBackgroundColor(C2026b.m2378a(R.color.top_controller_bar_style));
        getMediaController().setCCTVControllerListener(this);
        getMediaController().setOnProgressListener(this.f630L);
        setIsForbiddenGesture(true);
    }
}
