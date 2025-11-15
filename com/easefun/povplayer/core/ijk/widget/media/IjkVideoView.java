package com.easefun.povplayer.core.ijk.widget.media;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.TableLayout;
import android.widget.TextView;
import com.easefun.povplayer.core.R$string;
import com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a;
import com.easefun.povplayer.core.ijk.widget.media.PolyvGLSurfaceRenderView;
import com.easefun.povplayer.core.ijk.widget.media.PolyvGLTextureRenderView;
import com.easefun.povplayer.core.video.PolyvVideoView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import p035e.AbstractC0895h;
import p035e.C0896i;
import p035e.C0901n;
import p071i3.C1146a;
import p079j3.C1198a;
import p079j3.C1201d;
import p079j3.InterfaceC1199b;
import p079j3.InterfaceC1200c;
import p087k3.C1235d;
import p087k3.C1236e;
import p087k3.C1237f;
import p095l3.C1425e;
import p095l3.C1426f;
import p095l3.C1427g;
import p095l3.C1428h;
import p095l3.C1429i;
import p095l3.C1430j;
import p095l3.C1431k;
import p095l3.C1432l;
import p186x2.C2074b;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.IjkTimedText;
import tv.danmaku.ijk.media.player.MediaPlayerProxy;
import tv.danmaku.ijk.media.player.TextureMediaPlayer;
import tv.danmaku.ijk.media.player.format.mpegts.PesPrivateDataTimeStamp;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/* loaded from: classes.dex */
public class IjkVideoView extends FrameLayout implements MediaController.MediaPlayerControl, InterfaceC1200c {

    /* renamed from: k0 */
    public static final int[] f989k0 = {0, 1, 2, 4, 5};

    /* renamed from: A */
    public boolean f990A;

    /* renamed from: B */
    public Context f991B;

    /* renamed from: C */
    public C1146a f992C;

    /* renamed from: D */
    public InterfaceC0736a f993D;

    /* renamed from: E */
    public int f994E;

    /* renamed from: F */
    public int f995F;

    /* renamed from: G */
    public C1201d f996G;

    /* renamed from: H */
    public long f997H;

    /* renamed from: I */
    public long f998I;

    /* renamed from: J */
    public long f999J;

    /* renamed from: K */
    public long f1000K;

    /* renamed from: L */
    public TextView f1001L;

    /* renamed from: M */
    public PolyvGLSurfaceRenderView.InterfaceC0727c f1002M;

    /* renamed from: N */
    public PolyvGLTextureRenderView.InterfaceC0730c f1003N;

    /* renamed from: O */
    public boolean f1004O;

    /* renamed from: P */
    public boolean f1005P;

    /* renamed from: Q */
    public IMediaPlayer.OnVideoSizeChangedListener f1006Q;

    /* renamed from: R */
    public IMediaPlayer.OnPreparedListener f1007R;

    /* renamed from: S */
    public IMediaPlayer.OnCompletionListener f1008S;

    /* renamed from: T */
    public IMediaPlayer.OnInfoListener f1009T;

    /* renamed from: U */
    public IMediaPlayer.OnErrorListener f1010U;

    /* renamed from: V */
    public IMediaPlayer.OnBufferingUpdateListener f1011V;

    /* renamed from: W */
    public IMediaPlayer.OnSeekCompleteListener f1012W;

    /* renamed from: a0 */
    public IMediaPlayer.OnTimedTextListener f1013a0;

    /* renamed from: b0 */
    public IMediaPlayer.OnSEIRefreshListener f1014b0;

    /* renamed from: c0 */
    public final IMediaPlayer.OnAudioVividMetadataListener f1015c0;

    /* renamed from: d0 */
    public final IMediaPlayer.OnMpegTsPesPrivateDataListener f1016d0;

    /* renamed from: e */
    public String f1017e;

    /* renamed from: e0 */
    public InterfaceC0736a.a f1018e0;

    /* renamed from: f */
    public Uri f1019f;

    /* renamed from: f0 */
    public int f1020f0;

    /* renamed from: g */
    public Map<String, String> f1021g;

    /* renamed from: g0 */
    public Object[][] f1022g0;

    /* renamed from: h */
    public int f1023h;

    /* renamed from: h0 */
    public int f1024h0;

    /* renamed from: i */
    public int f1025i;

    /* renamed from: i0 */
    public IMediaPlayer.OnSeekCompleteListener f1026i0;

    /* renamed from: j */
    public InterfaceC0736a.b f1027j;

    /* renamed from: j0 */
    public IMediaPlayer.OnVideoSizeChangedListener f1028j0;

    /* renamed from: k */
    public IMediaPlayer f1029k;

    /* renamed from: l */
    public int f1030l;

    /* renamed from: m */
    public int f1031m;

    /* renamed from: n */
    public int f1032n;

    /* renamed from: o */
    public InterfaceC1199b f1033o;

    /* renamed from: p */
    public IMediaPlayer.OnCompletionListener f1034p;

    /* renamed from: q */
    public IMediaPlayer.OnPreparedListener f1035q;

    /* renamed from: r */
    public int f1036r;

    /* renamed from: s */
    public IMediaPlayer.OnErrorListener f1037s;

    /* renamed from: t */
    public IMediaPlayer.OnInfoListener f1038t;

    /* renamed from: u */
    public IMediaPlayer.OnSEIRefreshListener f1039u;

    /* renamed from: v */
    public IMediaPlayer.OnAudioVividMetadataListener f1040v;

    /* renamed from: w */
    public IMediaPlayer.OnMpegTsPesPrivateDataListener f1041w;

    /* renamed from: x */
    public int f1042x;

    /* renamed from: y */
    public boolean f1043y;

    /* renamed from: z */
    public boolean f1044z;

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$a */
    public class C0712a implements IMediaPlayer.OnAudioVividMetadataListener {
        public C0712a() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnAudioVividMetadataListener
        public void onMetadataChanged(IMediaPlayer iMediaPlayer) {
            IMediaPlayer.OnAudioVividMetadataListener onAudioVividMetadataListener = IjkVideoView.this.f1040v;
            if (onAudioVividMetadataListener != null) {
                onAudioVividMetadataListener.onMetadataChanged(iMediaPlayer);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$b */
    public class C0713b implements IMediaPlayer.OnMpegTsPesPrivateDataListener {
        public C0713b() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnMpegTsPesPrivateDataListener
        public void onPesPrivateData(IMediaPlayer iMediaPlayer, PesPrivateDataTimeStamp pesPrivateDataTimeStamp) {
            IMediaPlayer.OnMpegTsPesPrivateDataListener onMpegTsPesPrivateDataListener = IjkVideoView.this.f1041w;
            if (onMpegTsPesPrivateDataListener != null) {
                onMpegTsPesPrivateDataListener.onPesPrivateData(iMediaPlayer, pesPrivateDataTimeStamp);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$c */
    public class C0714c implements InterfaceC0736a.a {
        public C0714c() {
        }

        @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a.a
        /* renamed from: a */
        public void mo557a(@NonNull InterfaceC0736a.b bVar, int i7, int i8, int i9) {
            bVar.getRenderView();
            InterfaceC0736a interfaceC0736a = IjkVideoView.this.f993D;
        }

        @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a.a
        /* renamed from: b */
        public void mo558b(@NonNull InterfaceC0736a.b bVar) {
            InterfaceC0736a renderView = bVar.getRenderView();
            IjkVideoView ijkVideoView = IjkVideoView.this;
            if (renderView != ijkVideoView.f993D) {
                return;
            }
            ijkVideoView.f1027j = null;
            IMediaPlayer iMediaPlayer = ijkVideoView.f1029k;
            if (iMediaPlayer != null) {
                iMediaPlayer.setDisplay(null);
            }
        }

        @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a.a
        /* renamed from: c */
        public void mo559c(@NonNull InterfaceC0736a.b bVar, int i7, int i8) {
            InterfaceC0736a renderView = bVar.getRenderView();
            IjkVideoView ijkVideoView = IjkVideoView.this;
            if (renderView != ijkVideoView.f993D) {
                return;
            }
            ijkVideoView.f1027j = bVar;
            IMediaPlayer iMediaPlayer = ijkVideoView.f1029k;
            if (iMediaPlayer != null) {
                bVar.mo566a(iMediaPlayer);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$d */
    public class C0715d implements IjkMediaPlayer.OnNativeInvokeListener {
        public C0715d(IjkVideoView ijkVideoView) {
        }

        @Override // tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener
        public boolean onNativeInvoke(int i7, Bundle bundle) {
            return true;
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$e */
    public class C0716e implements IMediaPlayer.OnVideoSizeChangedListener {
        public C0716e() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i7, int i8, int i9, int i10) {
            int i11;
            IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = IjkVideoView.this.f1028j0;
            if (onVideoSizeChangedListener != null) {
                onVideoSizeChangedListener.onVideoSizeChanged(iMediaPlayer, i7, i8, i9, i10);
            }
            IjkVideoView.this.f1030l = iMediaPlayer.getVideoWidth();
            IjkVideoView.this.f1031m = iMediaPlayer.getVideoHeight();
            IjkVideoView.this.f994E = iMediaPlayer.getVideoSarNum();
            IjkVideoView.this.f995F = iMediaPlayer.getVideoSarDen();
            IjkVideoView ijkVideoView = IjkVideoView.this;
            int i12 = ijkVideoView.f1030l;
            if (i12 == 0 || (i11 = ijkVideoView.f1031m) == 0) {
                return;
            }
            InterfaceC0736a interfaceC0736a = ijkVideoView.f993D;
            if (interfaceC0736a != null) {
                interfaceC0736a.mo562c(i12, i11);
                IjkVideoView ijkVideoView2 = IjkVideoView.this;
                ijkVideoView2.f993D.mo563d(ijkVideoView2.f994E, ijkVideoView2.f995F);
            }
            IjkVideoView.this.requestLayout();
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$f */
    public class C0717f implements IMediaPlayer.OnPreparedListener {
        public C0717f() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            int i7;
            InterfaceC0736a interfaceC0736a;
            IjkVideoView.this.f998I = System.currentTimeMillis();
            IjkVideoView ijkVideoView = IjkVideoView.this;
            C1201d c1201d = ijkVideoView.f996G;
            if (c1201d != null) {
                c1201d.f2622d = ijkVideoView.f998I - ijkVideoView.f997H;
            }
            ijkVideoView.f1023h = 2;
            int i8 = ijkVideoView.f1042x;
            if (i8 != 0) {
                ijkVideoView.seekTo(i8);
            }
            InterfaceC1199b interfaceC1199b = IjkVideoView.this.f1033o;
            if (interfaceC1199b != null) {
                interfaceC1199b.setEnabled(true);
            }
            IjkVideoView ijkVideoView2 = IjkVideoView.this;
            IMediaPlayer.OnPreparedListener onPreparedListener = ijkVideoView2.f1035q;
            if (onPreparedListener != null) {
                onPreparedListener.onPrepared(ijkVideoView2.f1029k);
            }
            IjkVideoView.this.f1030l = iMediaPlayer.getVideoWidth();
            IjkVideoView.this.f1031m = iMediaPlayer.getVideoHeight();
            IjkVideoView ijkVideoView3 = IjkVideoView.this;
            int i9 = ijkVideoView3.f1030l;
            if (i9 == 0 || (i7 = ijkVideoView3.f1031m) == 0 || (interfaceC0736a = ijkVideoView3.f993D) == null) {
                return;
            }
            interfaceC0736a.mo562c(i9, i7);
            IjkVideoView ijkVideoView4 = IjkVideoView.this;
            ijkVideoView4.f993D.mo563d(ijkVideoView4.f994E, ijkVideoView4.f995F);
            if (IjkVideoView.this.f993D.mo564e()) {
                Objects.requireNonNull(IjkVideoView.this);
                IjkVideoView ijkVideoView5 = IjkVideoView.this;
                if (ijkVideoView5.f1030l != 0 || ijkVideoView5.f1031m != 0) {
                    return;
                }
            }
            IjkVideoView ijkVideoView6 = IjkVideoView.this;
            if (ijkVideoView6.f1025i == 3 || ijkVideoView6.isPlaying() || i8 != 0) {
                return;
            }
            IjkVideoView.this.getCurrentPosition();
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$g */
    public class C0718g implements IMediaPlayer.OnCompletionListener {
        public C0718g() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            IjkVideoView ijkVideoView = IjkVideoView.this;
            ijkVideoView.f1023h = 5;
            ijkVideoView.f1025i = 5;
            InterfaceC1199b interfaceC1199b = ijkVideoView.f1033o;
            if (interfaceC1199b != null) {
                interfaceC1199b.hide();
            }
            IjkVideoView ijkVideoView2 = IjkVideoView.this;
            IMediaPlayer.OnCompletionListener onCompletionListener = ijkVideoView2.f1034p;
            if (onCompletionListener != null) {
                onCompletionListener.onCompletion(ijkVideoView2.f1029k);
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$h */
    public class C0719h implements IMediaPlayer.OnInfoListener {
        public C0719h() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i7, Object obj) {
            IMediaPlayer.OnInfoListener onInfoListener = IjkVideoView.this.f1038t;
            if (onInfoListener != null) {
                onInfoListener.onInfo(iMediaPlayer, i7, obj);
            }
            if (i7 == 3) {
                String str = IjkVideoView.this.f1017e;
                return true;
            }
            if (i7 == 901) {
                String str2 = IjkVideoView.this.f1017e;
                return true;
            }
            if (i7 == 902) {
                String str3 = IjkVideoView.this.f1017e;
                return true;
            }
            if (i7 == 10001) {
                if (obj instanceof Integer) {
                    IjkVideoView.this.f1032n = ((Integer) obj).intValue();
                }
                String str4 = IjkVideoView.this.f1017e;
                Objects.toString(obj);
                IjkVideoView ijkVideoView = IjkVideoView.this;
                InterfaceC0736a interfaceC0736a = ijkVideoView.f993D;
                if (interfaceC0736a == null) {
                    return true;
                }
                interfaceC0736a.setVideoRotation(ijkVideoView.f1032n);
                return true;
            }
            if (i7 == 10002) {
                String str5 = IjkVideoView.this.f1017e;
                return true;
            }
            switch (i7) {
                case 700:
                    String str6 = IjkVideoView.this.f1017e;
                    break;
                case IMediaPlayer.MEDIA_INFO_BUFFERING_START /* 701 */:
                    String str7 = IjkVideoView.this.f1017e;
                    break;
                case IMediaPlayer.MEDIA_INFO_BUFFERING_END /* 702 */:
                    String str8 = IjkVideoView.this.f1017e;
                    break;
                case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /* 703 */:
                    String str9 = IjkVideoView.this.f1017e;
                    Objects.toString(obj);
                    break;
                default:
                    switch (i7) {
                        case 800:
                            String str10 = IjkVideoView.this.f1017e;
                            break;
                        case IMediaPlayer.MEDIA_INFO_NOT_SEEKABLE /* 801 */:
                            String str11 = IjkVideoView.this.f1017e;
                            break;
                        case IMediaPlayer.MEDIA_INFO_METADATA_UPDATE /* 802 */:
                            String str12 = IjkVideoView.this.f1017e;
                            break;
                    }
            }
            return true;
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$i */
    public class C0720i implements IMediaPlayer.OnErrorListener {

        /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$i$a */
        public class a implements DialogInterface.OnClickListener {
            public a() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i7) {
                IjkVideoView ijkVideoView = IjkVideoView.this;
                IMediaPlayer.OnCompletionListener onCompletionListener = ijkVideoView.f1034p;
                if (onCompletionListener != null) {
                    onCompletionListener.onCompletion(ijkVideoView.f1029k);
                }
            }
        }

        public C0720i() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
        public boolean onError(IMediaPlayer iMediaPlayer, int i7, int i8) {
            IjkVideoView ijkVideoView = IjkVideoView.this;
            String str = ijkVideoView.f1017e;
            ijkVideoView.f1023h = -1;
            ijkVideoView.f1025i = -1;
            InterfaceC1199b interfaceC1199b = ijkVideoView.f1033o;
            if (interfaceC1199b != null) {
                interfaceC1199b.hide();
            }
            IjkVideoView ijkVideoView2 = IjkVideoView.this;
            IMediaPlayer.OnErrorListener onErrorListener = ijkVideoView2.f1037s;
            if ((onErrorListener == null || !onErrorListener.onError(ijkVideoView2.f1029k, i7, i8)) && IjkVideoView.this.getWindowToken() != null) {
                IjkVideoView.this.f991B.getResources();
                new AlertDialog.Builder(IjkVideoView.this.getContext()).setMessage(i7 == 200 ? R$string.VideoView_error_text_invalid_progressive_playback : R$string.VideoView_error_text_unknown).setPositiveButton(R$string.VideoView_error_button, new a()).setCancelable(false).show();
            }
            return true;
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$j */
    public class C0721j implements IMediaPlayer.OnBufferingUpdateListener {
        public C0721j() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i7) {
            IjkVideoView.this.f1036r = i7;
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$k */
    public class C0722k implements IMediaPlayer.OnSeekCompleteListener {
        public C0722k() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener = IjkVideoView.this.f1026i0;
            if (onSeekCompleteListener != null) {
                onSeekCompleteListener.onSeekComplete(iMediaPlayer);
            }
            IjkVideoView.this.f1000K = System.currentTimeMillis();
            IjkVideoView ijkVideoView = IjkVideoView.this;
            C1201d c1201d = ijkVideoView.f996G;
            if (c1201d != null) {
                c1201d.f2623e = ijkVideoView.f1000K - ijkVideoView.f999J;
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$l */
    public class C0723l implements IMediaPlayer.OnTimedTextListener {
        public C0723l() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnTimedTextListener
        public void onTimedText(IMediaPlayer iMediaPlayer, IjkTimedText ijkTimedText) {
            if (ijkTimedText != null) {
                IjkVideoView.this.f1001L.setText(ijkTimedText.getText());
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView$m */
    public class C0724m implements IMediaPlayer.OnSEIRefreshListener {
        public C0724m() {
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSEIRefreshListener
        public void onSEIRefresh(IMediaPlayer iMediaPlayer, int i7, int i8) {
            IMediaPlayer.OnSEIRefreshListener onSEIRefreshListener = IjkVideoView.this.f1039u;
            if (onSEIRefreshListener != null) {
                onSEIRefreshListener.onSEIRefresh(iMediaPlayer, i7, i8);
            }
        }
    }

    public IjkVideoView(Context context) {
        super(context);
        this.f1017e = "IjkVideoView";
        this.f1023h = 0;
        this.f1025i = 0;
        this.f1027j = null;
        this.f1029k = null;
        this.f1043y = true;
        this.f1044z = true;
        this.f990A = true;
        this.f997H = 0L;
        this.f998I = 0L;
        this.f999J = 0L;
        this.f1000K = 0L;
        this.f1004O = false;
        this.f1005P = false;
        this.f1006Q = new C0716e();
        this.f1007R = new C0717f();
        this.f1008S = new C0718g();
        this.f1009T = new C0719h();
        this.f1010U = new C0720i();
        this.f1011V = new C0721j();
        this.f1012W = new C0722k();
        this.f1013a0 = new C0723l();
        this.f1014b0 = new C0724m();
        this.f1015c0 = new C0712a();
        this.f1016d0 = new C0713b();
        this.f1018e0 = new C0714c();
        this.f1020f0 = f989k0[0];
        new ArrayList();
        this.f1024h0 = 3;
        m555o(context);
    }

    private IjkMediaPlayer getIjkMediaPlayer() {
        IMediaPlayer iMediaPlayer = this.f1029k;
        if (iMediaPlayer instanceof IjkMediaPlayer) {
            return (IjkMediaPlayer) iMediaPlayer;
        }
        if (iMediaPlayer instanceof MediaPlayerProxy) {
            IMediaPlayer internalMediaPlayer = ((MediaPlayerProxy) iMediaPlayer).getInternalMediaPlayer();
            if (internalMediaPlayer instanceof IjkMediaPlayer) {
                return (IjkMediaPlayer) internalMediaPlayer;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0010  */
    /* renamed from: p */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized boolean m540p() {
        /*
            r3 = this;
            monitor-enter(r3)
            tv.danmaku.ijk.media.player.IMediaPlayer r0 = r3.f1029k     // Catch: java.lang.Throwable -> L13
            r1 = 1
            if (r0 == 0) goto L10
            int r0 = r3.f1023h     // Catch: java.lang.Throwable -> L13
            r2 = -1
            if (r0 == r2) goto L10
            if (r0 == 0) goto L10
            if (r0 == r1) goto L10
            goto L11
        L10:
            r1 = 0
        L11:
            monitor-exit(r3)
            return r1
        L13:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView.m540p():boolean");
    }

    private void setHeaders(Map<String, String> map) {
        if (map == null) {
            this.f1021g = new HashMap();
        } else {
            this.f1021g = map;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setOption(tv.danmaku.ijk.media.player.IMediaPlayer r13) {
        /*
            Method dump skipped, instructions count: 285
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.easefun.povplayer.core.ijk.widget.media.IjkVideoView.setOption(tv.danmaku.ijk.media.player.IMediaPlayer):void");
    }

    /* renamed from: a */
    public synchronized void m541a(boolean z6) {
        IMediaPlayer iMediaPlayer = this.f1029k;
        if (iMediaPlayer != null) {
            iMediaPlayer.release();
            this.f1029k = null;
            this.f1023h = 0;
            if (z6) {
                this.f1025i = 0;
            }
        }
        if (z6) {
            C1201d c1201d = this.f996G;
            if (c1201d != null) {
                c1201d.f2621c = null;
                c1201d.f2624f.removeMessages(1);
            }
            InterfaceC0736a interfaceC0736a = this.f993D;
            if (interfaceC0736a instanceof TextureRenderView) {
                ((TextureRenderView) interfaceC0736a).m569g();
            }
        }
    }

    /* renamed from: b */
    public void m542b(Uri uri, Map<String, String> map) {
        this.f1019f = uri;
        setHeaders(map);
        this.f1042x = 0;
        if (this.f1019f == null) {
            return;
        }
        m541a(false);
        try {
            this.f1029k = m554n(this.f992C.m1311a());
            setRender(this.f992C.m1312b());
            setOption(this.f1029k);
            this.f1029k.setEnableHdrVivid(this.f1004O);
            getContext();
            this.f1029k.setOnPreparedListener(this.f1007R);
            this.f1029k.setOnVideoSizeChangedListener(this.f1006Q);
            this.f1029k.setOnCompletionListener(this.f1008S);
            this.f1029k.setOnErrorListener(this.f1010U);
            this.f1029k.setOnInfoListener(this.f1009T);
            this.f1029k.setOnBufferingUpdateListener(this.f1011V);
            this.f1029k.setOnSeekCompleteListener(this.f1012W);
            this.f1029k.setOnTimedTextListener(this.f1013a0);
            this.f1029k.setOnSEIRefreshListener(this.f1014b0);
            this.f1029k.setOnAudioVividMetadataListener(this.f1015c0);
            this.f1029k.setOnMpegTsPesPrivateDataListener(this.f1016d0);
            this.f1036r = 0;
            String scheme = this.f1019f.getScheme();
            if (Build.VERSION.SDK_INT >= 23 && this.f992C.m1313c() && (TextUtils.isEmpty(scheme) || scheme.equalsIgnoreCase("file"))) {
                this.f1029k.setDataSource(new C1198a(new File(this.f1019f.toString())));
            } else {
                this.f1029k.setDataSource(this.f991B, this.f1019f, this.f1021g);
            }
            this.f1029k.setAudioStreamType(3);
            this.f1029k.setScreenOnWhilePlaying(true);
            if (getIjkMediaPlayer() != null) {
                getIjkMediaPlayer().setOption(1, "protocol_whitelist", "async,cache,crypto,file,http,https,ijkhttphook,ijkinject,ijklivehook,ijklongurl,ijksegment,ijktcphook,pipe,rtp,rtmp,tcp,tls,udp,ijkurlhook,data");
            }
            this.f997H = System.currentTimeMillis();
            this.f1029k.prepareAsync();
            C1201d c1201d = this.f996G;
            if (c1201d != null) {
                c1201d.m1428d(this.f1029k);
            }
            this.f1023h = 1;
            m553m();
        } catch (IOException unused) {
            Objects.toString(this.f1019f);
            this.f1023h = -1;
            this.f1025i = -1;
            this.f1010U.onError(this.f1029k, 1, 0);
        } catch (IllegalArgumentException unused2) {
            Objects.toString(this.f1019f);
            this.f1023h = -1;
            this.f1025i = -1;
            this.f1010U.onError(this.f1029k, 1, 0);
        }
    }

    /* renamed from: c */
    public void m543c(int i7) {
        IjkMediaPlayer ijkMediaPlayerM2490m = C2074b.m2490m(this.f1029k);
        if (ijkMediaPlayerM2490m == null) {
            return;
        }
        ijkMediaPlayerM2490m.selectTrack(i7);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return this.f1043y;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return this.f1044z;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return this.f990A;
    }

    /* renamed from: d */
    public boolean m544d(boolean z6) {
        if (getIjkMediaPlayer() == null) {
            return false;
        }
        getIjkMediaPlayer().setOption(2, "endpoint", z6 ? 2L : 1L);
        return true;
    }

    /* renamed from: e */
    public boolean m545e() {
        return m540p();
    }

    /* renamed from: f */
    public int m546f(int i7) {
        IjkMediaPlayer ijkMediaPlayerM2490m = C2074b.m2490m(this.f1029k);
        if (ijkMediaPlayerM2490m == null) {
            return -1;
        }
        return ijkMediaPlayerM2490m.getSelectedTrack(i7);
    }

    /* renamed from: g */
    public void m547g() {
        int iM1312b = this.f992C.m1312b();
        if (iM1312b == 3) {
            PolyvGLSurfaceRenderView polyvGLSurfaceRenderView = new PolyvGLSurfaceRenderView(getContext());
            polyvGLSurfaceRenderView.setOnSetGyroListener(this);
            PolyvGLSurfaceRenderView.InterfaceC0727c interfaceC0727c = this.f1002M;
            if (interfaceC0727c != null) {
                C1428h c1428h = (C1428h) interfaceC0727c;
                C0901n c0901n = c1428h.f4169a.f1173d0;
                if (c0901n != null) {
                    c0901n.m814b();
                }
                PolyvVideoView polyvVideoView = c1428h.f4169a;
                C0901n c0901nM816a = C1235d.m1460a(polyvVideoView.getContext(), c1428h.f4169a.f1174e0, new C1425e(c1428h), new C1426f(c1428h), new C1427g(c1428h), polyvGLSurfaceRenderView).m816a(new AbstractC0895h.b(polyvGLSurfaceRenderView, null));
                polyvGLSurfaceRenderView.setMdvrLibrary(c0901nM816a);
                C1236e c1236e = new C1236e();
                C0896i c0896i = c0901nM816a.f1599f;
                c0896i.f1545f = c1236e;
                c0896i.f1544e = new C1237f(c0901nM816a);
                polyvVideoView.f1173d0 = c0901nM816a;
            }
            polyvGLSurfaceRenderView.toString();
            setRenderView(polyvGLSurfaceRenderView);
            return;
        }
        if (iM1312b == 4) {
            PolyvGLTextureRenderView polyvGLTextureRenderView = new PolyvGLTextureRenderView(getContext());
            polyvGLTextureRenderView.setOnSetGyroListener(this);
            PolyvGLTextureRenderView.InterfaceC0730c interfaceC0730c = this.f1003N;
            if (interfaceC0730c != null) {
                C1432l c1432l = (C1432l) interfaceC0730c;
                C0901n c0901n2 = c1432l.f4173a.f1173d0;
                if (c0901n2 != null) {
                    c0901n2.m814b();
                }
                PolyvVideoView polyvVideoView2 = c1432l.f4173a;
                C0901n c0901nM816a2 = C1235d.m1460a(polyvVideoView2.getContext(), c1432l.f4173a.f1174e0, new C1429i(c1432l), new C1430j(c1432l), new C1431k(c1432l), polyvGLTextureRenderView).m816a(new AbstractC0895h.c(polyvGLTextureRenderView));
                polyvGLTextureRenderView.setMdvrLibrary(c0901nM816a2);
                C1236e c1236e2 = new C1236e();
                C0896i c0896i2 = c0901nM816a2.f1599f;
                c0896i2.f1545f = c1236e2;
                c0896i2.f1544e = new C1237f(c0901nM816a2);
                polyvVideoView2.f1173d0 = c0901nM816a2;
            }
            polyvGLTextureRenderView.toString();
            setRenderView(polyvGLTextureRenderView);
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.f1029k != null) {
            return this.f1036r;
        }
        return 0;
    }

    public int getCurrentAspectRatio() {
        return this.f1020f0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (m540p()) {
            return (int) this.f1029k.getCurrentPosition();
        }
        return 0;
    }

    public int getCurrentState() {
        return this.f1023h;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        if (m540p()) {
            return (int) this.f1029k.getDuration();
        }
        return -1;
    }

    public IMediaPlayer getMediaPlayer() {
        return this.f1029k;
    }

    public InterfaceC0736a getRenderView() {
        return this.f993D;
    }

    public float getSpeed() {
        if (this.f1029k == null || getIjkMediaPlayer() == null) {
            return 0.0f;
        }
        return getIjkMediaPlayer().getSpeed(0.0f);
    }

    public int getStateErrorCode() {
        return -1;
    }

    public int getStateIdleCode() {
        return 0;
    }

    public int getStatePauseCode() {
        return 4;
    }

    public int getStatePlaybackCompletedCode() {
        return 5;
    }

    public int getStatePlayingCode() {
        return 3;
    }

    public int getStatePreparedCode() {
        return 2;
    }

    public int getStatePreparingCode() {
        return 1;
    }

    public SurfaceHolder getSurfaceHolder() {
        InterfaceC0736a.b bVar = this.f1027j;
        if (bVar == null) {
            return null;
        }
        return bVar.getSurfaceHolder();
    }

    public int getTargetState() {
        return this.f1025i;
    }

    public ITrackInfo[] getTrackInfo() {
        IMediaPlayer iMediaPlayer = this.f1029k;
        if (iMediaPlayer == null) {
            return null;
        }
        return iMediaPlayer.getTrackInfo();
    }

    public int getVideoHeight() {
        return this.f1031m;
    }

    public int getVideoWidth() {
        return this.f1030l;
    }

    /* renamed from: h */
    public void m548h() {
        if (this.f993D != null) {
            IMediaPlayer iMediaPlayer = this.f1029k;
            if (iMediaPlayer != null) {
                iMediaPlayer.setDisplay(null);
            }
            InterfaceC0736a interfaceC0736a = this.f993D;
            if (interfaceC0736a instanceof TextureRenderView) {
                ((TextureRenderView) interfaceC0736a).m569g();
            }
            View view = this.f993D.getView();
            this.f993D.mo565f(this.f1018e0);
            this.f993D = null;
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }

    /* renamed from: i */
    public void m549i() {
        C1201d c1201d = this.f996G;
        if (c1201d != null) {
            c1201d.f2622d = 0L;
            c1201d.f2623e = 0L;
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return m540p() && this.f1029k.isPlaying();
    }

    /* renamed from: j */
    public void m550j() {
        m541a(false);
        this.f1023h = -1;
        this.f1025i = -1;
        InterfaceC1199b interfaceC1199b = this.f1033o;
        if (interfaceC1199b != null) {
            interfaceC1199b.hide();
        }
    }

    /* renamed from: k */
    public void m551k() {
    }

    /* renamed from: l */
    public void m552l() {
        IMediaPlayer iMediaPlayer = this.f1029k;
        if (iMediaPlayer != null) {
            this.f1042x = 0;
            iMediaPlayer.reset();
            this.f1023h = 0;
            this.f1025i = 0;
            setRender(this.f992C.m1312b());
            setOption(this.f1029k);
            this.f1036r = 0;
            try {
                String scheme = this.f1019f.getScheme();
                if (Build.VERSION.SDK_INT >= 23 && this.f992C.m1313c() && (TextUtils.isEmpty(scheme) || scheme.equalsIgnoreCase("file"))) {
                    this.f1029k.setDataSource(new C1198a(new File(this.f1019f.toString())));
                } else {
                    this.f1029k.setDataSource(this.f991B, this.f1019f, this.f1021g);
                }
                if (getIjkMediaPlayer() != null) {
                    getIjkMediaPlayer().setOption(1, "protocol_whitelist", "async,cache,crypto,file,http,https,ijkhttphook,ijkinject,ijklivehook,ijklongurl,ijksegment,ijktcphook,pipe,rtp,rtmp,tcp,tls,udp,ijkurlhook,data");
                }
                this.f997H = System.currentTimeMillis();
                this.f1029k.prepareAsync();
                this.f1023h = 1;
            } catch (IOException unused) {
                Objects.toString(this.f1019f);
                this.f1023h = -1;
                this.f1025i = -1;
                this.f1010U.onError(this.f1029k, 1, 0);
            } catch (IllegalArgumentException unused2) {
                Objects.toString(this.f1019f);
                this.f1023h = -1;
                this.f1025i = -1;
                this.f1010U.onError(this.f1029k, 1, 0);
            }
        }
    }

    /* renamed from: m */
    public final void m553m() {
        InterfaceC1199b interfaceC1199b;
        if (this.f1029k == null || (interfaceC1199b = this.f1033o) == null) {
            return;
        }
        interfaceC1199b.setMediaPlayer(this);
        this.f1033o.setAnchorView(getParent() instanceof View ? (View) getParent() : this);
        this.f1033o.setEnabled(m540p());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: n */
    public IMediaPlayer m554n(int i7) {
        AndroidMediaPlayer androidMediaPlayer;
        if (i7 != 1) {
            IjkMediaPlayer ijkMediaPlayer = this.f1019f != null ? new IjkMediaPlayer() : 0;
            androidMediaPlayer = ijkMediaPlayer;
            androidMediaPlayer = ijkMediaPlayer;
            if (this.f1005P && ijkMediaPlayer != 0) {
                ijkMediaPlayer.setOnNativeInvokeListener(new C0715d(this));
                androidMediaPlayer = ijkMediaPlayer;
            }
        } else {
            androidMediaPlayer = new AndroidMediaPlayer();
        }
        return new TextureMediaPlayer(androidMediaPlayer);
    }

    /* renamed from: o */
    public final void m555o(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f991B = applicationContext;
        C1146a c1146a = new C1146a(applicationContext);
        this.f992C = c1146a;
        c1146a.f2519b.getBoolean(c1146a.f2518a.getString(R$string.pref_key_enable_background_play), false);
        this.f1030l = 0;
        this.f1031m = 0;
        this.f1023h = 0;
        this.f1025i = 0;
        TextView textView = new TextView(context);
        this.f1001L = textView;
        textView.setTextSize(24.0f);
        this.f1001L.setGravity(17);
        addView(this.f1001L, new FrameLayout.LayoutParams(-1, -2, 80));
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        if (m540p() && this.f1029k.isPlaying()) {
            this.f1029k.pause();
            this.f1023h = 4;
        }
        this.f1025i = 4;
    }

    /* renamed from: q */
    public void m556q(float f7, float f8, float f9) {
        if (getIjkMediaPlayer() == null || !m540p()) {
            return;
        }
        if (f7 < 0.0f) {
            f7 += 360.0f;
        }
        float f10 = f7 - 100.0f;
        if (f10 < 0.0f) {
            f10 += 360.0f;
        }
        if (f8 < 0.0f) {
            f8 += 360.0f;
        }
        if (f9 < 0.0f) {
            f9 += 360.0f;
        }
        getIjkMediaPlayer().setGyro((int) f10, (int) f8, (int) f9);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i7) {
        if (!m540p()) {
            this.f1042x = i7;
            return;
        }
        this.f999J = System.currentTimeMillis();
        this.f1029k.seekTo(i7);
        this.f1042x = 0;
    }

    public void setCurrentAspectRatio(int i7) {
        this.f1020f0 = i7;
        InterfaceC0736a interfaceC0736a = this.f993D;
        if (interfaceC0736a != null) {
            interfaceC0736a.setAspectRatio(i7);
        }
    }

    public void setEnableHdrVivid(boolean z6) {
        IMediaPlayer iMediaPlayer = this.f1029k;
        if (iMediaPlayer != null) {
            iMediaPlayer.setEnableHdrVivid(z6);
        }
        this.f1004O = z6;
    }

    public void setHudView(TableLayout tableLayout) {
        this.f996G = new C1201d(getContext(), tableLayout);
    }

    public void setIjkLogLevel(int i7) {
        this.f1024h0 = i7;
    }

    public void setLogTag(String str) {
        this.f1017e = str;
    }

    public void setMediaController(InterfaceC1199b interfaceC1199b) {
        this.f1033o = interfaceC1199b;
        m553m();
    }

    public void setMirror(boolean z6) {
        InterfaceC0736a interfaceC0736a = this.f993D;
        if (interfaceC0736a instanceof TextureRenderView) {
            ((TextureRenderView) interfaceC0736a).setMirror(z6);
        }
    }

    public void setOnAudioVividMetadataListener(IMediaPlayer.OnAudioVividMetadataListener onAudioVividMetadataListener) {
        this.f1040v = onAudioVividMetadataListener;
    }

    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener onCompletionListener) {
        this.f1034p = onCompletionListener;
    }

    public void setOnErrorListener(IMediaPlayer.OnErrorListener onErrorListener) {
        this.f1037s = onErrorListener;
    }

    public void setOnInfoListener(IMediaPlayer.OnInfoListener onInfoListener) {
        this.f1038t = onInfoListener;
    }

    public void setOnMpegTsPesPrivateDataListener(IMediaPlayer.OnMpegTsPesPrivateDataListener onMpegTsPesPrivateDataListener) {
        this.f1041w = onMpegTsPesPrivateDataListener;
    }

    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener onPreparedListener) {
        this.f1035q = onPreparedListener;
    }

    public void setOnSEIRefreshListener(IMediaPlayer.OnSEIRefreshListener onSEIRefreshListener) {
        this.f1039u = onSEIRefreshListener;
    }

    public void setOnSeekCompleteListener(IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        this.f1026i0 = onSeekCompleteListener;
    }

    public void setOnVideoSizeChangedListener(IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.f1028j0 = onVideoSizeChangedListener;
    }

    public void setOptionParameters(Object[][] objArr) {
        this.f1022g0 = objArr;
    }

    public void setReconnectInternet(boolean z6) {
        this.f1005P = z6;
    }

    public void setRender(int i7) {
        if (i7 == 0) {
            setRenderView(null);
            return;
        }
        if (i7 == 1) {
            setRenderView(new SurfaceRenderView(getContext()));
            return;
        }
        if (i7 != 2) {
            if (i7 == 3) {
                m547g();
                return;
            } else if (i7 != 4) {
                String.format(Locale.getDefault(), "invalid render %d\n", Integer.valueOf(i7));
                return;
            } else {
                m547g();
                return;
            }
        }
        TextureRenderView textureRenderView = new TextureRenderView(getContext());
        if (this.f1029k != null) {
            textureRenderView.getSurfaceHolder().mo566a(this.f1029k);
            textureRenderView.mo562c(this.f1029k.getVideoWidth(), this.f1029k.getVideoHeight());
            textureRenderView.mo563d(this.f1029k.getVideoSarNum(), this.f1029k.getVideoSarDen());
            textureRenderView.setAspectRatio(this.f1020f0);
        }
        setRenderView(textureRenderView);
    }

    public void setRenderView(InterfaceC0736a interfaceC0736a) {
        int i7;
        int i8;
        m548h();
        if (interfaceC0736a == null) {
            return;
        }
        this.f993D = interfaceC0736a;
        interfaceC0736a.setAspectRatio(this.f1020f0);
        int i9 = this.f1030l;
        if (i9 > 0 && (i8 = this.f1031m) > 0) {
            interfaceC0736a.mo562c(i9, i8);
        }
        int i10 = this.f994E;
        if (i10 > 0 && (i7 = this.f995F) > 0) {
            interfaceC0736a.mo563d(i10, i7);
        }
        View view = this.f993D.getView();
        view.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
        addView(view);
        this.f993D.mo561b(this.f1018e0);
        this.f993D.setVideoRotation(this.f1032n);
    }

    public void setSpeed(float f7) {
        if (this.f1029k == null || getIjkMediaPlayer() == null) {
            return;
        }
        getIjkMediaPlayer().setSpeed(f7);
    }

    public void setTargetState(int i7) {
        this.f1025i = i7;
    }

    public void setVRViewInitCompletionListener(PolyvGLSurfaceRenderView.InterfaceC0727c interfaceC0727c) {
        this.f1002M = interfaceC0727c;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        m542b(uri, null);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        if (m540p()) {
            if (this.f1023h == 5) {
                this.f999J = System.currentTimeMillis();
            }
            this.f1029k.start();
            this.f1023h = 3;
        }
        this.f1025i = 3;
    }

    public void setVRViewInitCompletionListener(PolyvGLTextureRenderView.InterfaceC0730c interfaceC0730c) {
        this.f1003N = interfaceC0730c;
    }

    public IjkVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1017e = "IjkVideoView";
        this.f1023h = 0;
        this.f1025i = 0;
        this.f1027j = null;
        this.f1029k = null;
        this.f1043y = true;
        this.f1044z = true;
        this.f990A = true;
        this.f997H = 0L;
        this.f998I = 0L;
        this.f999J = 0L;
        this.f1000K = 0L;
        this.f1004O = false;
        this.f1005P = false;
        this.f1006Q = new C0716e();
        this.f1007R = new C0717f();
        this.f1008S = new C0718g();
        this.f1009T = new C0719h();
        this.f1010U = new C0720i();
        this.f1011V = new C0721j();
        this.f1012W = new C0722k();
        this.f1013a0 = new C0723l();
        this.f1014b0 = new C0724m();
        this.f1015c0 = new C0712a();
        this.f1016d0 = new C0713b();
        this.f1018e0 = new C0714c();
        this.f1020f0 = f989k0[0];
        new ArrayList();
        this.f1024h0 = 3;
        m555o(context);
    }

    public IjkVideoView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1017e = "IjkVideoView";
        this.f1023h = 0;
        this.f1025i = 0;
        this.f1027j = null;
        this.f1029k = null;
        this.f1043y = true;
        this.f1044z = true;
        this.f990A = true;
        this.f997H = 0L;
        this.f998I = 0L;
        this.f999J = 0L;
        this.f1000K = 0L;
        this.f1004O = false;
        this.f1005P = false;
        this.f1006Q = new C0716e();
        this.f1007R = new C0717f();
        this.f1008S = new C0718g();
        this.f1009T = new C0719h();
        this.f1010U = new C0720i();
        this.f1011V = new C0721j();
        this.f1012W = new C0722k();
        this.f1013a0 = new C0723l();
        this.f1014b0 = new C0724m();
        this.f1015c0 = new C0712a();
        this.f1016d0 = new C0713b();
        this.f1018e0 = new C0714c();
        this.f1020f0 = f989k0[0];
        new ArrayList();
        this.f1024h0 = 3;
        m555o(context);
    }
}
