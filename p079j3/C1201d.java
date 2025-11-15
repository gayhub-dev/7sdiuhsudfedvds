package p079j3;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import com.easefun.povplayer.core.R$layout;
import com.easefun.povplayer.core.R$string;
import java.util.Locale;
import p079j3.C1203f;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.MediaPlayerProxy;

/* compiled from: InfoHudViewHolder.java */
/* renamed from: j3.d */
/* loaded from: classes.dex */
public class C1201d {

    /* renamed from: a */
    public C1203f f2619a;

    /* renamed from: c */
    public IMediaPlayer f2621c;

    /* renamed from: b */
    public SparseArray<View> f2620b = new SparseArray<>();

    /* renamed from: d */
    public long f2622d = 0;

    /* renamed from: e */
    public long f2623e = 0;

    /* renamed from: f */
    public Handler f2624f = new a();

    /* compiled from: InfoHudViewHolder.java */
    /* renamed from: j3.d$a */
    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            IMediaPlayer internalMediaPlayer;
            char c7;
            String str;
            if (message.what != 1) {
                return;
            }
            IjkMediaPlayer ijkMediaPlayer = null;
            IMediaPlayer iMediaPlayer = C1201d.this.f2621c;
            if (iMediaPlayer == null) {
                return;
            }
            if (iMediaPlayer instanceof IjkMediaPlayer) {
                ijkMediaPlayer = (IjkMediaPlayer) iMediaPlayer;
            } else if ((iMediaPlayer instanceof MediaPlayerProxy) && (internalMediaPlayer = ((MediaPlayerProxy) iMediaPlayer).getInternalMediaPlayer()) != null && (internalMediaPlayer instanceof IjkMediaPlayer)) {
                ijkMediaPlayer = (IjkMediaPlayer) internalMediaPlayer;
            }
            if (ijkMediaPlayer == null) {
                return;
            }
            int videoDecoder = ijkMediaPlayer.getVideoDecoder();
            if (videoDecoder == 1) {
                C1201d.m1425a(C1201d.this, R$string.vdec, "avcodec");
            } else if (videoDecoder != 2) {
                C1201d.m1425a(C1201d.this, R$string.vdec, "");
            } else {
                C1201d.m1425a(C1201d.this, R$string.vdec, "MediaCodec");
            }
            float videoOutputFramesPerSecond = ijkMediaPlayer.getVideoOutputFramesPerSecond();
            float videoDecodeFramesPerSecond = ijkMediaPlayer.getVideoDecodeFramesPerSecond();
            C1201d c1201d = C1201d.this;
            int i7 = R$string.fps;
            Locale locale = Locale.US;
            C1201d.m1425a(c1201d, i7, String.format(locale, "%.2f / %.2f", Float.valueOf(videoDecodeFramesPerSecond), Float.valueOf(videoOutputFramesPerSecond)));
            long videoCachedDuration = ijkMediaPlayer.getVideoCachedDuration();
            long audioCachedDuration = ijkMediaPlayer.getAudioCachedDuration();
            long videoCachedBytes = ijkMediaPlayer.getVideoCachedBytes();
            long audioCachedBytes = ijkMediaPlayer.getAudioCachedBytes();
            long tcpSpeed = ijkMediaPlayer.getTcpSpeed();
            long bitRate = ijkMediaPlayer.getBitRate();
            long seekLoadDuration = ijkMediaPlayer.getSeekLoadDuration();
            float audioFrameSpeed = ijkMediaPlayer.getAudioFrameSpeed();
            float videoFrameSpeed = ijkMediaPlayer.getVideoFrameSpeed();
            C1201d.m1425a(C1201d.this, R$string.v_cache, String.format(locale, "%s, %s", C1201d.m1426b(videoCachedDuration), C1201d.m1427c(videoCachedBytes)));
            C1201d.m1425a(C1201d.this, R$string.a_cache, String.format(locale, "%s, %s", C1201d.m1426b(audioCachedDuration), C1201d.m1427c(audioCachedBytes)));
            C1201d c1201d2 = C1201d.this;
            C1201d.m1425a(c1201d2, R$string.load_cost, String.format(locale, "%d ms", Long.valueOf(c1201d2.f2622d)));
            C1201d c1201d3 = C1201d.this;
            C1201d.m1425a(c1201d3, R$string.seek_cost, String.format(locale, "%d ms", Long.valueOf(c1201d3.f2623e)));
            C1201d.m1425a(C1201d.this, R$string.seek_load_cost, String.format(locale, "%d ms", Long.valueOf(seekLoadDuration)));
            C1201d c1201d4 = C1201d.this;
            int i8 = R$string.tcp_speed;
            Object[] objArr = new Object[1];
            if (tcpSpeed <= 0) {
                str = "0 B/s";
                c7 = 0;
            } else {
                float f7 = (tcpSpeed * 1000.0f) / 1000;
                if (f7 >= 1000000.0f) {
                    c7 = 0;
                    str = String.format(locale, "%.2f MB/s", Float.valueOf((f7 / 1000.0f) / 1000.0f));
                } else {
                    c7 = 0;
                    str = f7 >= 1000.0f ? String.format(locale, "%.1f KB/s", Float.valueOf(f7 / 1000.0f)) : String.format(locale, "%d B/s", Long.valueOf((long) f7));
                }
            }
            objArr[c7] = str;
            C1201d.m1425a(c1201d4, i8, String.format(locale, "%s", objArr));
            C1201d c1201d5 = C1201d.this;
            int i9 = R$string.bit_rate;
            Object[] objArr2 = new Object[1];
            objArr2[c7] = Float.valueOf(bitRate / 1000.0f);
            C1201d.m1425a(c1201d5, i9, String.format(locale, "%.2f kbs", objArr2));
            C1201d c1201d6 = C1201d.this;
            int i10 = R$string.audio_frame_speed;
            Object[] objArr3 = new Object[2];
            objArr3[c7] = Float.valueOf(audioFrameSpeed);
            objArr3[1] = Float.valueOf(videoFrameSpeed);
            C1201d.m1425a(c1201d6, i10, String.format(locale, "%.6f | %.6f", objArr3));
            C1201d.this.f2624f.removeMessages(1);
            C1201d.this.f2624f.sendEmptyMessageDelayed(1, 500L);
        }
    }

    public C1201d(Context context, TableLayout tableLayout) {
        this.f2619a = new C1203f(context, tableLayout);
    }

    /* renamed from: a */
    public static void m1425a(C1201d c1201d, int i7, String str) {
        View view = c1201d.f2620b.get(i7);
        if (view != null) {
            TextView textView = c1201d.f2619a.m1430a(view).f2637b;
            if (textView != null) {
                textView.setText(str);
                return;
            }
            return;
        }
        C1203f c1203f = c1201d.f2619a;
        String string = c1203f.f2634a.getString(i7);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(c1203f.f2634a).inflate(R$layout.table_media_info_row2, (ViewGroup) c1203f.f2635b, false);
        C1203f.b bVarM1430a = c1203f.m1430a(viewGroup);
        TextView textView2 = bVarM1430a.f2636a;
        if (textView2 != null) {
            textView2.setText(string);
        }
        TextView textView3 = bVarM1430a.f2637b;
        if (textView3 != null) {
            textView3.setText(str);
        }
        c1203f.f2635b.addView(viewGroup);
        c1201d.f2620b.put(i7, viewGroup);
    }

    /* renamed from: b */
    public static String m1426b(long j7) {
        return j7 >= 1000 ? String.format(Locale.US, "%.2f sec", Float.valueOf(j7 / 1000.0f)) : String.format(Locale.US, "%d msec", Long.valueOf(j7));
    }

    /* renamed from: c */
    public static String m1427c(long j7) {
        return j7 >= 100000 ? String.format(Locale.US, "%.2f MB", Float.valueOf((j7 / 1000.0f) / 1000.0f)) : j7 >= 100 ? String.format(Locale.US, "%.1f KB", Float.valueOf(j7 / 1000.0f)) : String.format(Locale.US, "%d B", Long.valueOf(j7));
    }

    /* renamed from: d */
    public void m1428d(IMediaPlayer iMediaPlayer) {
        this.f2621c = iMediaPlayer;
        if (iMediaPlayer != null) {
            this.f2624f.sendEmptyMessageDelayed(1, 500L);
        } else {
            this.f2624f.removeMessages(1);
        }
    }
}
