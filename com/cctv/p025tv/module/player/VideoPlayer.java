package com.cctv.p025tv.module.player;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.cctv.cctvplayer.CCTVVideoView;
import com.cctv.cctvplayer.player.EnumC0567a;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.entity.AudioTrackEntity;
import com.cctv.p025tv.entity.HighBitrateEntity;
import com.cctv.p025tv.module.collect.C0579a;
import com.cctv.p025tv.module.player.view.PlayView;
import com.cctv.p025tv.module.service.WebSocketService;
import com.cctv.p025tv.module.service.bean.WebSocketMsgInfo;
import com.cctv.p025tv.mvp.p026ui.fragment.C0621a;
import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import com.ctvit.dlna.entity.CctvEntity;
import com.ctvit.dlna.entity.DlnaContentEntity;
import com.tencent.mars.xlog.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import okhttp3.internal.cache.DiskLruCache;
import org.fourthline.cling.support.model.TransportState;
import p009b.C0413b;
import p013b3.C0440a;
import p021c3.C0512a;
import p043f.C0988e;
import p045f1.C0992a;
import p045f1.C0993b;
import p053g1.InterfaceC1044b;
import p053g1.InterfaceC1047e;
import p053g1.InterfaceC1049g;
import p078j2.C1186a;
import p078j2.C1188c;
import p078j2.C1197l;
import p086k2.C1231b;
import p095l3.C1423c;
import p101m1.C1458b;
import p118o2.C1581b;
import p144r2.C1830c;
import p164u1.C1973c;
import p165u2.C1974a;
import p171v1.C2003b;
import p178w1.InterfaceC2024a;
import p179w2.C2026b;
import p185x1.C2066a;
import p185x1.C2067b;
import p186x2.C2073a;
import p193y2.C2106a;
import p200z2.C2150a;
import p200z2.EnumC2151b;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.format.mpegts.PesPrivateDataTimeStamp;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;
import tv.danmaku.ijk.media.player.misc.IjkMediaFormat;

/* loaded from: classes.dex */
public class VideoPlayer extends CCTVVideoView implements InterfaceC1044b, InterfaceC1047e, C1188c.b {

    /* renamed from: r0 */
    public static final /* synthetic */ int f591r0 = 0;

    /* renamed from: J */
    public int f592J;

    /* renamed from: K */
    public int f593K;

    /* renamed from: L */
    public int f594L;

    /* renamed from: M */
    public int f595M;

    /* renamed from: N */
    public boolean f596N;

    /* renamed from: O */
    public String f597O;

    /* renamed from: P */
    public long f598P;

    /* renamed from: Q */
    public long f599Q;

    /* renamed from: R */
    public PlayView f600R;

    /* renamed from: S */
    public DlnaContentEntity f601S;

    /* renamed from: T */
    public VideoFragment f602T;

    /* renamed from: U */
    public C1188c f603U;

    /* renamed from: V */
    public HighBitrateEntity.DataBean f604V;

    /* renamed from: W */
    public long f605W;

    /* renamed from: a0 */
    public boolean f606a0;

    /* renamed from: b0 */
    public boolean f607b0;

    /* renamed from: c0 */
    public boolean f608c0;

    /* renamed from: d0 */
    public boolean f609d0;

    /* renamed from: e0 */
    public double f610e0;

    /* renamed from: f0 */
    public Timer f611f0;

    /* renamed from: g0 */
    public TimerTask f612g0;

    /* renamed from: h0 */
    public InterfaceC2024a f613h0;

    /* renamed from: i0 */
    public int f614i0;

    /* renamed from: j0 */
    public long f615j0;

    /* renamed from: k0 */
    public boolean f616k0;

    /* renamed from: l0 */
    public long f617l0;

    /* renamed from: m0 */
    public boolean f618m0;

    /* renamed from: n0 */
    public int f619n0;

    /* renamed from: o0 */
    public boolean f620o0;

    /* renamed from: p0 */
    public Handler f621p0;

    /* renamed from: q0 */
    public InterfaceC1049g f622q0;

    /* renamed from: com.cctv.tv.module.player.VideoPlayer$a */
    public class HandlerC0586a extends Handler {
        public HandlerC0586a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x00bf  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0135  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x026f A[Catch: all -> 0x0272, DONT_GENERATE, TryCatch #0 {, blocks: (B:60:0x01df, B:62:0x022b, B:64:0x0233, B:66:0x0241, B:68:0x024f, B:70:0x0255, B:72:0x0264, B:75:0x026c, B:76:0x026f), top: B:136:0x01df, inners: #2 }] */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r24) {
            /*
                Method dump skipped, instructions count: 1088
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cctv.p025tv.module.player.VideoPlayer.HandlerC0586a.handleMessage(android.os.Message):void");
        }
    }

    /* renamed from: com.cctv.tv.module.player.VideoPlayer$b */
    public class C0587b implements InterfaceC1049g {
        public C0587b() {
        }

        @Override // p053g1.InterfaceC1049g
        /* renamed from: a */
        public void mo434a(long j7) {
            if (C0988e.m996v() || C1581b.m1833b() == null) {
                return;
            }
            ((C1830c.b) C1581b.m1833b()).m2077d((int) j7, VideoPlayer.this.getPlayEntity().f1847k);
        }

        @Override // p053g1.InterfaceC1049g
        /* renamed from: b */
        public void mo435b(long j7) {
            if (C1581b.m1833b() != null) {
                ((C1830c.b) C1581b.m1833b()).m2075b((int) j7);
            }
        }
    }

    /* renamed from: com.cctv.tv.module.player.VideoPlayer$c */
    public class RunnableC0588c implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ String f625e;

        public RunnableC0588c(String str) {
            this.f625e = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            VideoPlayer videoPlayer = VideoPlayer.this;
            int i7 = VideoPlayer.f591r0;
            TextView textView = videoPlayer.f490C;
            StringBuilder sbM112a = C0413b.m112a("下载速度：");
            sbM112a.append(this.f625e);
            sbM112a.append("/s");
            textView.setText(sbM112a.toString());
        }
    }

    public VideoPlayer(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getCctvAgentDataPlayDuration() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.f615j0;
        if (jCurrentTimeMillis >= 0) {
            return jCurrentTimeMillis;
        }
        return 0L;
    }

    /* renamed from: y */
    public static void m437y(VideoPlayer videoPlayer, int i7) {
        Objects.requireNonNull(videoPlayer);
        C2073a.m2459d("playerSpeed switchVideo removeIndex = " + i7);
        videoPlayer.m453z(i7 + (-1));
        List<C0993b> list = videoPlayer.getPlayEntity().f1843g;
        StringBuilder sbM112a = C0413b.m112a("playerSpeed remove before ");
        sbM112a.append(JSON.toJSONString(list));
        C2073a.m2459d(sbM112a.toString());
        list.remove(i7);
        C2073a.m2459d("playerSpeed remove after " + JSON.toJSONString(list));
    }

    /* renamed from: A */
    public final void m438A() {
        if (getPlayEntity().f1839c) {
            this.f621p0.removeMessages(1002);
        }
    }

    /* renamed from: B */
    public void m439B() {
        this.f621p0.removeMessages(1000);
        C0988e.m971O(((AppCompatActivity) getContext()).getSupportFragmentManager(), "VIDEO_FRAGMENT");
        mo388s(getPlayEntity().m1011e(false), false, null, true);
    }

    /* renamed from: C */
    public View m440C() {
        if (this.f600R == null) {
            PlayView playView = new PlayView(getContext());
            this.f600R = playView;
            playView.setTag("playView");
        }
        return this.f600R;
    }

    /* renamed from: D */
    public void m441D() {
        Fragment fragmentFindFragmentByTag = ((AppCompatActivity) getContext()).getSupportFragmentManager().findFragmentByTag("VIDEO_FRAGMENT");
        if ((fragmentFindFragmentByTag == null ? true : fragmentFindFragmentByTag.isHidden()) || getPlayEntity() == null || TextUtils.isEmpty(getPlayEntity().m1011e(false)) || !this.f616k0) {
            return;
        }
        this.f616k0 = false;
        C0988e.m983i(this, getCctvAgentDataPlayDuration());
    }

    /* renamed from: E */
    public void m442E(C0992a c0992a, HighBitrateEntity.DataBean dataBean, boolean z6) {
        Dialog dialog;
        if (c0992a == null) {
            return;
        }
        m441D();
        if (dataBean != null) {
            this.f604V = dataBean;
        }
        VideoFragment videoFragment = this.f602T;
        if (videoFragment != null && (dialog = videoFragment.f862n) != null && dialog.isShowing()) {
            videoFragment.f862n.dismiss();
        }
        this.f609d0 = z6;
        this.f514v = 0;
        this.f597O = UUID.randomUUID().toString();
        C0988e.m971O(((AppCompatActivity) getContext()).getSupportFragmentManager(), "VIDEO_FRAGMENT");
        getMediaController().m368s();
        setPlay(c0992a);
        boolean z7 = this.f609d0;
        C0992a playEntity = getPlayEntity();
        C2073a.m2459d("setdefaultVideo");
        if (C2003b.m2341b(playEntity)) {
            C2073a.m2459d("切码率 - 不走自动降码逻辑");
        } else if (z7 && C0988e.m960D() && C0988e.m962F() && !TextUtils.isEmpty(C2003b.m2340a(playEntity)) && playEntity.m1010d() != 0) {
            int iM1421a = C1197l.m1421a(MyApplication.f561e, "VOD_RESULT", 0);
            int iM1421a2 = C1197l.m1421a(MyApplication.f561e, "LIVE_RESULT", 0);
            C2073a.m2459d("vodIndex = " + iM1421a);
            C2073a.m2459d("liveIndex = " + iM1421a2);
            List<C0993b> list = playEntity.f1843g;
            if (list != null && list.size() > 0) {
                if (playEntity.f1839c) {
                    if (list.size() > iM1421a2) {
                        if (iM1421a2 == 2) {
                            playEntity.m1012f(list.size() - 1);
                        } else {
                            playEntity.m1012f(iM1421a2);
                        }
                    } else if (list.size() == iM1421a2) {
                        playEntity.m1012f(list.size() - 1);
                    } else {
                        playEntity.m1012f(0);
                    }
                } else if (list.size() > iM1421a) {
                    if (iM1421a == 2) {
                        playEntity.m1012f(list.size() - 1);
                    } else {
                        playEntity.m1012f(iM1421a);
                    }
                } else if (list.size() == iM1421a) {
                    playEntity.m1012f(list.size() - 1);
                } else {
                    playEntity.m1012f(0);
                }
            }
        }
        mo388s(c0992a.m1011e(false), false, null, false);
        this.f621p0.removeMessages(1000);
        this.f621p0.sendEmptyMessageDelayed(1000, 8000L);
        m438A();
        this.f621p0.removeMessages(1006);
        this.f621p0.sendEmptyMessageDelayed(1006, 10000L);
        Log.m655i("XLog_PLAY ", "initPlay ");
    }

    /* renamed from: F */
    public final boolean m443F(int i7) {
        StringBuilder sbM112a = C0413b.m112a("getPlayEntity().isLive() = ");
        sbM112a.append(getPlayEntity().f1839c);
        C2073a.m2459d(sbM112a.toString());
        C2073a.m2459d("getPlayerView().getDuration() = " + getPlayerView().getDuration());
        if (getPlayEntity().f1839c) {
            return true;
        }
        int duration = getPlayerView().getDuration() - getPlayerView().getCurrentPosition();
        C2073a.m2459d("rts = " + duration);
        return getPlayerView().getDuration() == 0 || duration > i7;
    }

    /* renamed from: G */
    public final boolean m444G(boolean z6) {
        if (C1197l.m1422b(MyApplication.f561e, "SHARPNESS_SWITCH_MODEL", false)) {
            return true;
        }
        VideoFragment videoFragment = this.f602T;
        videoFragment.f872x.removeMessages(1005);
        Message message = new Message();
        message.what = 1005;
        message.obj = Boolean.valueOf(z6);
        videoFragment.f872x.sendMessage(message);
        return false;
    }

    /* renamed from: H */
    public final void m445H(String str, boolean z6, String str2, boolean z7) {
        C2073a.m2459d("低码不带防盗链");
        getPlayEntity().f1842f = null;
        this.f606a0 = false;
        m452O(str, z6, str2, z7);
    }

    /* renamed from: I */
    public void m446I(String str, long j7) {
        CctvEntity.ClientBean client;
        CctvEntity.ClientBean client2;
        if (this.f602T.m509m() != null) {
            getPlayEntity().f1840d = this.f606a0;
            WebSocketService webSocketServiceM509m = this.f602T.m509m();
            Objects.requireNonNull(webSocketServiceM509m);
            if (getPlayEntity() != null && webSocketServiceM509m.f659j) {
                int i7 = 2;
                ArrayList arrayList = new ArrayList(2);
                String app_key = webSocketServiceM509m.f658i;
                String str2 = getPlayEntity().f1839c ? webSocketServiceM509m.f656g : webSocketServiceM509m.f657h;
                Object obj = getPlayEntity().f1844h;
                if ((obj instanceof CctvEntity) && (client2 = ((CctvEntity) obj).getClient()) != null) {
                    app_key = client2.getApp_key();
                }
                WebSocketMsgInfo.C0598b c0598b = new WebSocketMsgInfo.C0598b();
                c0598b.setKey("app");
                c0598b.setValue(app_key);
                arrayList.add(c0598b);
                WebSocketMsgInfo.C0598b c0598b2 = new WebSocketMsgInfo.C0598b();
                c0598b2.setKey("type");
                c0598b2.setValue(str2);
                arrayList.add(c0598b2);
                WebSocketMsgInfo webSocketMsgInfo = new WebSocketMsgInfo();
                WebSocketMsgInfo.C0597a c0597a = new WebSocketMsgInfo.C0597a();
                Objects.requireNonNull(C1231b.m1454a());
                String str3 = "";
                c0597a.setImei("");
                C2106a.m2548a();
                c0597a.setAndroid_id("");
                if (Build.VERSION.SDK_INT < 30) {
                    try {
                        if (TextUtils.isEmpty("")) {
                            str3 = Build.SERIAL;
                        }
                    } catch (Exception unused) {
                    }
                    try {
                        c0597a.setSerial(str3);
                    } catch (Exception e7) {
                        StringBuilder sbM112a = C0413b.m112a("sendMessage setSerial = ");
                        sbM112a.append(e7.getMessage());
                        Log.m651e("XLog_APP ", sbM112a.toString());
                    }
                }
                webSocketMsgInfo.setDeviceId(c0597a);
                webSocketMsgInfo.setRate(getPlayEntity().m1009c());
                if ("PREPARE".equals(str)) {
                    i7 = 1;
                } else if (!"PLAYING".equals(str)) {
                    i7 = "PAUSE".equals(str) ? 3 : "FAST".equals(str) ? 4 : "BUFFERING".equals(str) ? 5 : "ERROR".equals(str) ? 6 : "END".equals(str) ? 7 : "EXIT".equals(str) ? 8 : 0;
                }
                webSocketMsgInfo.setState(i7);
                webSocketMsgInfo.setTotal(arrayList);
                String currentPlayPath = getPlayerView().getCurrentPlayPath();
                if (getPlayEntity().f1840d && !TextUtils.isEmpty(C2003b.m2340a(getPlayEntity()))) {
                    String[] strArrSplit = currentPlayPath.split("\\?");
                    if (strArrSplit.length > 0) {
                        currentPlayPath = strArrSplit[0];
                    }
                }
                webSocketMsgInfo.setUrl(currentPlayPath);
                String jSONString = JSON.toJSONString(webSocketMsgInfo);
                C1458b.m1642a("msg = ", jSONString);
                C0512a c0512a = webSocketServiceM509m.f654e;
                if (c0512a != null) {
                    c0512a.m320b(jSONString);
                }
                if ("EXIT".equals(str)) {
                    webSocketServiceM509m.m460a();
                }
            }
        }
        Object obj2 = getPlayEntity().f1844h;
        if (!(obj2 instanceof CctvEntity) || (client = ((CctvEntity) obj2).getClient()) == null) {
            C0579a.m413b(this, new CctvEntity.ClientBean(), str, j7);
        } else {
            C0579a.m413b(this, client, str, j7);
        }
    }

    /* renamed from: J */
    public void m447J(boolean z6, String str) {
        if (getPlayerView().isPlaying() && !getPlayEntity().f1839c) {
            if (z6) {
                this.f621p0.removeMessages(1005);
                this.f621p0.sendEmptyMessage(1005);
            } else {
                this.f621p0.removeMessages(1005);
                C1581b.m1836e().f4755k = true;
            }
        }
        getPlayEntity().f1847k = str;
    }

    /* renamed from: K */
    public final void m448K(long j7) {
        this.f616k0 = true;
        this.f621p0.removeMessages(1003);
        this.f621p0.sendEmptyMessageDelayed(1003, 30000L);
        this.f615j0 = System.currentTimeMillis();
        HashMap map = new HashMap();
        C0988e.m967K(this, map);
        map.put("buffer_duration", String.valueOf(j7));
        C0988e.m973Q("play_start", "播放开始", map, this);
    }

    /* renamed from: L */
    public final void m449L() {
        C2073a.m2459d("加载时长结束统计");
        C2073a.m2459d("cancelTimerTask");
        Timer timer = this.f611f0;
        if (timer != null) {
            timer.cancel();
        }
        TimerTask timerTask = this.f612g0;
        if (timerTask != null) {
            timerTask.cancel();
        }
        this.f607b0 = false;
        this.f608c0 = false;
        this.f610e0 = 0.0d;
        this.f614i0 = 0;
    }

    /* renamed from: M */
    public final void m450M() {
        int index;
        int i7 = 0;
        while (true) {
            if (i7 >= C2066a.m2450b().f6161c.size()) {
                C2073a.m2459d("未找到立体声音轨");
                index = -1;
                break;
            }
            AudioTrackEntity audioTrackEntity = C2066a.m2450b().f6161c.get(i7);
            if (audioTrackEntity.getName().contains("aac")) {
                C2073a.m2459d("找到立体声音轨");
                index = audioTrackEntity.getIndex();
                break;
            }
            i7++;
        }
        C2073a.m2459d("playerSpeed AudioTrack aacAudioTrackIndex = " + index);
        if (index != C2066a.m2449a(this)) {
            if (C2066a.m2450b().f6160b != null) {
                try {
                    C2073a.m2459d("开始切换音轨：" + index);
                    getPlayerView().f1105e.mo576c(index);
                } catch (Exception unused) {
                }
            }
            C2073a.m2459d("playerSpeed AudioTrack 切换成立体声 ");
        }
        C2150a.m2591b("AUDIO_TRACK_AAC", Boolean.TRUE);
    }

    /* renamed from: N */
    public void m451N(boolean z6) {
        C2073a.m2459d("遥控器左右键...");
        int position = getMediaController().getPosition();
        StringBuilder sbM112a = C0413b.m112a("遥控器左右键 - 快进到position：");
        long j7 = position;
        sbM112a.append(C0440a.m150a(j7, true));
        C2073a.m2459d(sbM112a.toString());
        if (z6 && getPlayerView().m612H()) {
            m442E(getPlayEntity(), this.f604V, true);
            getMediaController().m361l(true);
            getMediaController().m368s();
        } else {
            mo389t(position);
            getPlayerView().start();
        }
        getMediaController().m365p(false, true);
        getMediaController().f461P.sendEmptyMessageDelayed(101, 3000L);
        if (getMediaController().getOnProgressListener() != null) {
            getMediaController().getOnProgressListener().mo434a(j7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x012d  */
    /* renamed from: O */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m452O(java.lang.String r15, boolean r16, java.lang.String r17, boolean r18) {
        /*
            Method dump skipped, instructions count: 524
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cctv.p025tv.module.player.VideoPlayer.m452O(java.lang.String, boolean, java.lang.String, boolean):void");
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
        String strMo2594c = EnumC2151b.f6321m.mo2594c(j7);
        if (getContext() instanceof AppCompatActivity) {
            ((AppCompatActivity) getContext()).runOnUiThread(new RunnableC0588c(strMo2594c));
        }
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: g */
    public void mo377g(IMediaPlayer iMediaPlayer, PesPrivateDataTimeStamp pesPrivateDataTimeStamp) {
        C2073a.m2459d("OnMpegTsPesPrivateData_");
        long j7 = pesPrivateDataTimeStamp.utcTime;
        this.f617l0 = j7;
        if (j7 <= 0 || this.f618m0 || !getPlayEntity().f1839c) {
            return;
        }
        this.f621p0.removeMessages(1004);
        this.f621p0.sendEmptyMessage(1004);
        this.f618m0 = true;
    }

    public DlnaContentEntity getDlnaContentEntity() {
        return this.f601S;
    }

    public String getPlayerId() {
        return this.f597O;
    }

    public HighBitrateEntity.DataBean getVideoInfo() {
        return this.f604V;
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: i */
    public void mo378i() {
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: j */
    public void mo379j(C1423c c1423c) {
        super.mo379j(c1423c);
        StringBuilder sbM112a = C0413b.m112a("player-life onError_");
        sbM112a.append(c1423c.toString());
        C2073a.m2459d(sbM112a.toString());
        Log.m655i("XLog_PLAY ", "player-life onError_" + c1423c.toString());
        m449L();
        long cctvAgentDataPlayDuration = getCctvAgentDataPlayDuration();
        C0988e.m983i(this, cctvAgentDataPlayDuration);
        C0988e.m963G(this, "2", cctvAgentDataPlayDuration, 0L, c1423c.toString());
        m446I("ERROR", 0L);
        getMediaController().m352c(m440C());
        this.f596N = false;
        this.f621p0.removeMessages(1000);
        this.f621p0.sendEmptyMessage(1000);
        this.f621p0.removeMessages(1001);
        this.f621p0.removeMessages(1004);
        this.f621p0.removeMessages(1005);
        this.f618m0 = false;
        this.f621p0.removeMessages(1003);
        this.f603U.m1396a();
        m438A();
        if (C1581b.m1833b() != null) {
            ((C1830c.b) C1581b.m1833b()).m2076c();
        }
        if (getPlayEntity().m1008b().f1854e) {
            VideoFragment videoFragment = this.f602T;
            Objects.requireNonNull(videoFragment);
            new Timer().schedule(new C0621a(videoFragment), 1500L);
        }
        m453z(-1);
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: k */
    public void mo380k(IMediaPlayer iMediaPlayer, int i7, int i8) {
        super.mo380k(iMediaPlayer, i7, i8);
        if (i7 == 701) {
            C2073a.m2459d("player-life MEDIA_INFO_BUFFERING_START");
            Log.m655i("XLog_PLAY ", "player-life MEDIA_INFO_BUFFERING_START");
            this.f608c0 = true;
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.f599Q = jCurrentTimeMillis;
            this.f605W = jCurrentTimeMillis;
            return;
        }
        if (i7 == 702) {
            this.f608c0 = false;
            C2073a.m2459d("player-life MEDIA_INFO_BUFFERING_END");
            Log.m655i("XLog_PLAY ", "player-life MEDIA_INFO_BUFFERING_END");
            if (this.f599Q > 0) {
                C0988e.m963G(this, "3", 0L, System.currentTimeMillis() - this.f599Q, null);
                long jCurrentTimeMillis2 = System.currentTimeMillis() - this.f605W;
                if (this.f607b0) {
                    C2073a.m2459d("isSeekTo");
                    this.f607b0 = false;
                } else {
                    this.f610e0 += jCurrentTimeMillis2;
                }
            }
            this.f599Q = 0L;
            this.f621p0.removeMessages(1001);
        }
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: l */
    public void mo381l() {
        super.mo381l();
        C2073a.m2459d("player-life onPause_");
        Log.m655i("XLog_PLAY ", "player-life onPause_");
        if (this.f492E) {
            m446I("PAUSE", 0L);
            getMediaController().m352c(m440C());
            if (C1581b.m1833b() != null) {
                ((C1830c.b) C1581b.m1833b()).m2076c();
            }
        }
        m449L();
        C0988e.m983i(this, getCctvAgentDataPlayDuration());
        this.f621p0.removeMessages(1003);
        if (getPlayEntity().f1839c) {
            this.f621p0.removeMessages(1004);
        } else {
            this.f621p0.removeMessages(1005);
        }
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: m */
    public void mo382m() {
        super.mo382m();
        C2073a.m2459d("player-life onPlayAllCompletion");
        Log.m655i("XLog_PLAY ", "player-life onPlayAllCompletion");
        m449L();
        this.f621p0.removeMessages(1001);
        this.f621p0.removeMessages(1004);
        this.f621p0.removeMessages(1005);
        this.f618m0 = false;
        this.f603U.m1396a();
        m438A();
        C0988e.m983i(this, getCctvAgentDataPlayDuration());
        this.f621p0.removeMessages(1003);
        m446I("END", 0L);
        getMediaController().m352c(m440C());
        if (C1581b.m1833b() != null) {
            ((C1830c.b) C1581b.m1833b()).m2076c();
        }
        String strM1011e = getPlayEntity().m1011e(false);
        if (strM1011e == null) {
            strM1011e = "";
        }
        if (getPlayEntity().f1839c || strM1011e.contains(".m3u8")) {
            C2073a.m2459d("直播 - onPlayAllCompletion，自动降码");
            m453z(-1);
        }
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: n */
    public void mo383n(boolean z6) {
        super.mo383n(z6);
        C2073a.m2459d("player-life onPlay_-- + " + z6);
        Log.m655i("XLog_PLAY ", "player-life onPlay_-- + " + z6);
        if (z6 && getPlayEntity().m1008b().f1854e) {
            VideoFragment videoFragment = this.f602T;
            Objects.requireNonNull(videoFragment);
            new Timer().schedule(new C0621a(videoFragment), 1500L);
        }
        if (C1581b.m1833b() != null) {
            C1830c.b bVar = (C1830c.b) C1581b.m1833b();
            Objects.requireNonNull(bVar);
            C2073a.m2459d("onStart");
            Log.m655i("XLog_DLNA ", "dlna返回给手机的状态 onStart");
            C1830c.this.mo2073f(TransportState.PLAYING);
        }
        if (this.f492E) {
            m446I("PLAYING", 0L);
            getMediaController().m368s();
        }
        C2073a.m2459d("startSwitchTask");
        if (m443F(300000)) {
            StringBuilder sbM112a = C0413b.m112a("isOpenStatisticAnalysis = ");
            sbM112a.append(this.f609d0);
            C2073a.m2459d(sbM112a.toString());
            if (this.f609d0 && C0988e.m960D()) {
                C2073a.m2459d("开始任务   startStatisticAnalysis");
                C2073a.m2459d("cancelTimerTask");
                Timer timer = this.f611f0;
                if (timer != null) {
                    timer.cancel();
                }
                TimerTask timerTask = this.f612g0;
                if (timerTask != null) {
                    timerTask.cancel();
                }
                this.f611f0 = new Timer(getClass().getSimpleName());
                C1973c c1973c = new C1973c(this);
                this.f612g0 = c1973c;
                this.f611f0.schedule(c1973c, 300000L, 300000L);
            }
        } else {
            C2073a.m2459d("视频小于300000分钟return ");
        }
        if (z6) {
            this.f621p0.removeMessages(1004);
            this.f621p0.sendEmptyMessageDelayed(1009, 1000L);
            return;
        }
        m448K(0L);
        if (getPlayEntity().f1839c && this.f618m0) {
            this.f621p0.removeMessages(1004);
            this.f621p0.sendEmptyMessage(1004);
        }
        if (!C0988e.m996v() || getPlayEntity().f1839c) {
            return;
        }
        this.f621p0.removeMessages(1005);
        this.f621p0.sendEmptyMessage(1005);
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: o */
    public void mo384o(IMediaPlayer iMediaPlayer) {
        int index;
        CctvEntity.PlayerBean player;
        int i7;
        C2066a c2066aM2450b = C2066a.m2450b();
        int i8 = this.f497e;
        Objects.requireNonNull(c2066aM2450b);
        if (iMediaPlayer == null || i8 != 2) {
            C2066a.m2450b().f6160b = null;
            C2066a.m2450b().f6159a = null;
            C2066a.m2450b().f6161c.clear();
        } else {
            C2066a.m2450b().f6160b = iMediaPlayer;
            C2066a.m2450b().f6159a = iMediaPlayer.getTrackInfo();
            C2066a.m2450b().f6161c.clear();
            if (C2066a.m2450b().f6159a == null) {
                List<AudioTrackEntity> list = C2066a.m2450b().f6161c;
            } else {
                StringBuilder sbM112a = C0413b.m112a("视频和音轨数量：");
                sbM112a.append(C2066a.m2450b().f6159a.length);
                C2073a.m2459d(sbM112a.toString());
                for (int i9 = 0; i9 < C2066a.m2450b().f6159a.length; i9++) {
                    ITrackInfo iTrackInfo = C2066a.m2450b().f6159a[i9];
                    if (iTrackInfo.getTrackType() == 2) {
                        String string = iTrackInfo.getFormat().getString(IjkMediaFormat.KEY_IJK_CODEC_NAME_UI);
                        if (!TextUtils.isEmpty(string)) {
                            AudioTrackEntity audioTrackEntity = new AudioTrackEntity();
                            audioTrackEntity.setIndex(i9);
                            audioTrackEntity.setName(string);
                            C2066a.m2450b().f6161c.add(audioTrackEntity);
                            C2073a.m2459d("音轨索引：" + i9 + " | 音轨名称：" + string);
                        }
                    }
                }
                List<AudioTrackEntity> list2 = C2066a.m2450b().f6161c;
            }
        }
        InterfaceC2024a interfaceC2024a = this.f613h0;
        if (interfaceC2024a != null) {
            interfaceC2024a.mo519a();
        }
        super.mo384o(iMediaPlayer);
        C2073a.m2459d("player-life onPrepared_");
        Log.m655i("XLog_PLAY ", "player-life onPrepared_");
        if (getMediaController().getOnProgressListener() != null) {
            getMediaController().getOnProgressListener().mo435b(iMediaPlayer.getDuration());
        }
        this.f596N = true;
        this.f621p0.removeMessages(1000);
        C2073a.m2459d("播放视频时长 duration = " + getPlayerView().getDuration());
        Object obj = getPlayEntity().f1844h;
        if (obj instanceof CctvEntity) {
            CctvEntity cctvEntity = (CctvEntity) obj;
            DlnaContentEntity dlnaContentEntity = this.f601S;
            if (cctvEntity != null && (player = cctvEntity.getPlayer()) != null) {
                try {
                    if (getPlayEntity() != null && dlnaContentEntity != null) {
                        String type = player.getType();
                        String seek_to = player.getSeek_to();
                        if (C2003b.m2342c(getPlayEntity().f1837a, dlnaContentEntity.getId()) && "vod".equals(type) && !player.isKeep_on() && !TextUtils.isEmpty(seek_to) && (i7 = Integer.parseInt(player.getSeek_to())) > 0) {
                            C2073a.m2459d("seekTempPosition 启播 - seekTo=" + i7);
                            mo389t(i7);
                        }
                    }
                } catch (Exception e7) {
                    C2073a.m2458c(e7);
                }
                try {
                    String speed = player.getSpeed();
                    if (!TextUtils.isEmpty(speed)) {
                        float fFloatValue = Float.valueOf(speed).floatValue();
                        if (fFloatValue > -1.0f) {
                            getPlayerView().setSpeed(fFloatValue);
                        }
                    }
                } catch (Exception e8) {
                    C2073a.m2458c(e8);
                }
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.f598P;
        m448K(jCurrentTimeMillis);
        C0988e.m963G(this, DiskLruCache.VERSION_1, 0L, jCurrentTimeMillis, null);
        m446I("PREPARE", jCurrentTimeMillis);
        if (C0988e.m996v() && !getPlayEntity().f1839c) {
            this.f621p0.removeMessages(1005);
            this.f621p0.sendEmptyMessage(1005);
        }
        this.f621p0.removeMessages(1006);
        this.f621p0.sendEmptyMessageDelayed(1006, 5000L);
        StringBuilder sb = new StringBuilder();
        sb.append("playerSpeed AudioTrack.isMultipleAudioTrack() = ");
        sb.append(C2066a.m2450b().f6161c.size() > 1);
        C2073a.m2459d(sb.toString());
        if (C1186a.m1381a()) {
            if ((C2066a.m2450b().f6161c.size() > 1) && getPlayEntity().f1839c) {
                int iM2449a = C2066a.m2449a(this);
                int i10 = 0;
                while (true) {
                    if (i10 >= C2066a.m2450b().f6161c.size()) {
                        C2073a.m2459d("未找到菁彩声音轨");
                        index = -1;
                        break;
                    }
                    AudioTrackEntity audioTrackEntity2 = C2066a.m2450b().f6161c.get(i10);
                    if (audioTrackEntity2.getName().contains("av3a")) {
                        C2073a.m2459d("找到菁彩声音轨");
                        index = audioTrackEntity2.getIndex();
                        break;
                    }
                    i10++;
                }
                if (iM2449a == index) {
                    if (((Boolean) C2150a.m2590a("AUDIO_TRACK_AAC", Boolean.FALSE)).booleanValue()) {
                        m450M();
                    } else {
                        this.f621p0.removeMessages(1007);
                        this.f621p0.sendEmptyMessageDelayed(1007, 1000L);
                    }
                }
            }
        }
        if (C1186a.m1395o()) {
            C2073a.m2459d("playerSpeed auto open ");
            if (C1186a.m1387g() > 0) {
                C2073a.m2459d("playerSpeed 视频切换过 ");
            } else {
                this.f621p0.removeMessages(1008);
                this.f621p0.sendEmptyMessageDelayed(1008, 1000L);
            }
        }
        if (this.f619n0 != 0 && !getPlayEntity().f1839c) {
            mo389t(this.f619n0);
            C2073a.m2459d("seekTempPosition onPlay");
        }
        VideoFragment videoFragment = this.f602T;
        if (videoFragment != null) {
            videoFragment.m513q(getPlayEntity().m1008b().f1860k);
        }
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: p */
    public void mo385p() {
        super.mo385p();
        C2073a.m2459d("player-life onPreparing_");
        Log.m655i("XLog_PLAY ", "player-life onPreparing_");
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: q */
    public void mo386q(IMediaPlayer iMediaPlayer) {
        InterfaceC2024a interfaceC2024a = this.f613h0;
        if (interfaceC2024a != null) {
            interfaceC2024a.mo520b();
        }
        iMediaPlayer.start();
        C2073a.m2459d("player-life onSeekComplete_");
        Log.m655i("XLog_PLAY ", "player-life onSeekComplete_");
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: r */
    public void mo387r() {
        super.mo387r();
        if (getPlayerView().isPlaying()) {
            C0988e.m983i(this, getCctvAgentDataPlayDuration());
        }
        VideoFragment videoFragment = this.f602T;
        if (videoFragment != null) {
            videoFragment.m511o();
        }
        this.f621p0.removeCallbacksAndMessages(null);
        this.f618m0 = false;
        m449L();
        C1188c c1188c = this.f603U;
        if (c1188c != null) {
            c1188c.m1396a();
        }
        if (this.f611f0 != null) {
            this.f611f0 = null;
        }
        if (this.f612g0 != null) {
            this.f612g0 = null;
        }
        C2067b.m2451a(this);
        C1581b.m1836e().f4755k = true;
        this.f593K = 0;
        this.f592J = 0;
        this.f595M = 0;
        this.f594L = 0;
        this.f619n0 = 0;
        this.f620o0 = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00fd A[RETURN] */
    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo388s(java.lang.String r12, boolean r13, java.lang.String r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 396
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cctv.p025tv.module.player.VideoPlayer.mo388s(java.lang.String, boolean, java.lang.String, boolean):void");
    }

    public void setDlnaContentEntity(DlnaContentEntity dlnaContentEntity) {
        this.f601S = dlnaContentEntity;
    }

    public void setIVideoPlayerListener(InterfaceC2024a interfaceC2024a) {
        this.f613h0 = interfaceC2024a;
    }

    public void setVideoFragment(VideoFragment videoFragment) {
        this.f602T = videoFragment;
    }

    @Override // com.cctv.cctvplayer.CCTVVideoView
    /* renamed from: t */
    public void mo389t(int i7) {
        super.mo389t(i7);
        C2073a.m2459d("player-life seekTo");
        Log.m655i("XLog_PLAY ", "player-life seekTo");
        this.f619n0 = 0;
        C2073a.m2459d("seekTempPosition 直接seekTo pos = " + i7);
        this.f607b0 = true;
        m446I("FAST", 0L);
        getMediaController().m368s();
        if (C1581b.m1833b() != null) {
            ((C1830c.b) C1581b.m1833b()).m2077d(i7, getPlayEntity().f1847k);
        }
        if (getPlayerView().isPlaying()) {
            C0988e.m983i(this, getCctvAgentDataPlayDuration());
        }
    }

    /* renamed from: z */
    public final void m453z(int i7) {
        int index;
        this.f621p0.removeMessages(1000);
        m438A();
        if (C2003b.m2341b(getPlayEntity())) {
            C2073a.m2459d("切码率 - 不走自动降码逻辑");
            return;
        }
        List<C0993b> list = getPlayEntity().f1843g;
        if (list == null || list.size() <= 1) {
            C2073a.m2459d("自动降码逻辑 - 只有一个码率");
            return;
        }
        int iM1010d = getPlayEntity().m1010d();
        if (iM1010d <= 0) {
            this.f606a0 = false;
            C2073a.m2459d("已经是最低码率了，就继续等待加载curRateIndex：" + iM1010d);
            return;
        }
        if (i7 > -1) {
            C2073a.m2459d("因设备无法支持杜比而降码");
            int i8 = 0;
            while (true) {
                if (i8 >= C2066a.m2450b().f6161c.size()) {
                    C2073a.m2459d("未找到杜比音轨");
                    index = -1;
                    break;
                }
                AudioTrackEntity audioTrackEntity = C2066a.m2450b().f6161c.get(i8);
                if (audioTrackEntity.getName().contains("ac3")) {
                    C2073a.m2459d("找到杜比音轨");
                    index = audioTrackEntity.getIndex();
                    break;
                }
                i8++;
            }
            String name = (index <= -1 || index >= C2066a.m2450b().f6161c.size()) ? null : C2066a.m2450b().f6161c.get(index).getName();
            String strM2379b = "ac3".equals(name) ? C2026b.m2379b(R.string.device_no_support_ac3) : "eac3".equals(name) ? C2026b.m2379b(R.string.device_no_support_eac3) : null;
            if (TextUtils.isEmpty(strM2379b)) {
                C2073a.m2456a("未匹配到正确的音频类型，这是一个错误，需要排查");
            } else {
                VideoFragment videoFragment = this.f602T;
                if (videoFragment != null) {
                    videoFragment.f869u.setText(strM2379b);
                    videoFragment.m515s(videoFragment.f866r, videoFragment.getResources().getDimension(R.dimen.dp_524), 5000L);
                } else {
                    C2073a.m2456a("未能弹出提示，这是一个错误，需要排查");
                }
            }
        }
        C0993b c0993b = list.get(i7 > -1 ? i7 : iM1010d - 1);
        C0993b c0993b2 = list.get(iM1010d);
        c0993b.f1853d = true;
        c0993b2.f1853d = false;
        if (getPlayerView().isPlaying() && i7 < 0) {
            C2073a.m2459d("终止自动降码逻辑，因为刚好8秒成功播放！！！");
            return;
        }
        C1974a.m2299b(R.string.auto_reduce_bit_rate).m2345a();
        mo388s(getPlayEntity().m1011e(false), false, null, false);
        C2073a.m2459d(C2026b.m2379b(R.string.auto_reduce_bit_rate) + "：" + iM1010d + " | " + getPlayEntity().m1010d());
        this.f621p0.removeMessages(1000);
        this.f621p0.sendEmptyMessageDelayed(1000, 8000L);
    }

    public VideoPlayer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoPlayer(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f592J = 0;
        this.f593K = 0;
        this.f594L = 0;
        this.f595M = 0;
        this.f597O = "";
        this.f598P = 0L;
        this.f599Q = 0L;
        this.f603U = new C1188c();
        this.f605W = 0L;
        this.f606a0 = false;
        this.f607b0 = false;
        this.f608c0 = false;
        this.f609d0 = false;
        this.f614i0 = 0;
        this.f616k0 = true;
        this.f619n0 = 0;
        this.f621p0 = new HandlerC0586a(Looper.getMainLooper());
        this.f622q0 = new C0587b();
        setPlayListener(this);
        ((RelativeLayout) getMediaController().findViewById(R.id.topLayout)).setBackgroundColor(C2026b.m2378a(R.color.top_controller_bar_style));
        ((RelativeLayout) getMediaController().findViewById(R.id.bottomLayout)).setBackgroundColor(C2026b.m2378a(R.color.top_controller_bar_style));
        getMediaController().setCCTVControllerListener(this);
        getMediaController().setOnProgressListener(this.f622q0);
        this.f603U.f2610d = this;
        setIsForbiddenGesture(true);
    }
}
