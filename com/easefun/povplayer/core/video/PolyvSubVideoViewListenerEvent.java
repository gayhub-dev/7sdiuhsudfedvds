package com.easefun.povplayer.core.video;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.cctv.cctvplayer.CCTVVideoView;
import com.easefun.povplayer.core.video.PolyvVideoView;
import java.util.Objects;
import p037e1.C0934o;
import p037e1.C0935p;
import p037e1.C0942w;
import p043f.C0988e;
import p069i1.C1142a;
import p095l3.C1423c;
import p103m3.InterfaceC1478r;
import p103m3.InterfaceC1483w;
import p103m3.InterfaceC1484x;
import p103m3.InterfaceC1485y;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes.dex */
public abstract class PolyvSubVideoViewListenerEvent extends PolyvBaseVideoViewListenerEvent {

    /* renamed from: q */
    public InterfaceC1483w f1145q;

    /* renamed from: r */
    public InterfaceC1485y f1146r;

    /* renamed from: s */
    public InterfaceC1484x f1147s;

    public PolyvSubVideoViewListenerEvent(Context context) {
        super(context);
        this.f1145q = null;
        this.f1146r = null;
        this.f1147s = null;
    }

    /* renamed from: p */
    public void m598p(IMediaPlayer iMediaPlayer, int i7) {
        InterfaceC1483w interfaceC1483w = this.f1145q;
        if (interfaceC1483w != null) {
            C0935p c0935p = (C0935p) interfaceC1483w;
            Objects.requireNonNull(c0935p);
            TextUtils.isEmpty("sub " + (i7 == 1 ? "片头广告" : i7 == 3 ? "片尾广告" : "") + " onCompletion");
            if (i7 == 3) {
                c0935p.f1699a.mo382m();
            }
        }
        InterfaceC1485y interfaceC1485y = this.f1146r;
        if (interfaceC1485y != null) {
            PolyvVideoView.C0753j c0753j = (PolyvVideoView.C0753j) interfaceC1485y;
            Objects.requireNonNull(c0753j);
            if (i7 != 1 || c0753j.f1199a.m592u() || c0753j.f1199a.getTargetState() != c0753j.f1199a.getStatePlaybackCompletedCode()) {
                if (i7 == 3) {
                    c0753j.f1199a.m593v();
                }
            } else {
                PolyvVideoView polyvVideoView = PolyvVideoView.this;
                int i8 = PolyvVideoView.f1148u0;
                if (polyvVideoView.m608D()) {
                    c0753j.f1199a.m593v();
                    PolyvVideoView.this.m605A();
                }
            }
        }
    }

    /* renamed from: q */
    public void m599q(C1423c c1423c) {
        InterfaceC1485y interfaceC1485y = this.f1146r;
        if (interfaceC1485y != null) {
            PolyvVideoView.C0753j c0753j = (PolyvVideoView.C0753j) interfaceC1485y;
            InterfaceC1478r interfaceC1478r = PolyvVideoView.this.f1206q;
            if (interfaceC1478r != null) {
                ((C0942w) interfaceC1478r).f1705a.mo379j(c1423c);
            }
            if (c1423c.f4164d == 1 && !c0753j.f1199a.m592u() && c0753j.f1199a.getTargetState() == c0753j.f1199a.getStatePlaybackCompletedCode()) {
                PolyvVideoView polyvVideoView = PolyvVideoView.this;
                int i7 = PolyvVideoView.f1148u0;
                if (polyvVideoView.m608D()) {
                    c0753j.f1199a.m593v();
                    PolyvVideoView.this.m605A();
                    return;
                }
                return;
            }
            int i8 = c1423c.f4164d;
            if (i8 == 2) {
                c0753j.f1199a.m593v();
            } else if (i8 == 3) {
                c0753j.f1199a.m593v();
            }
        }
    }

    /* renamed from: r */
    public void m600r(boolean z6) {
        InterfaceC1484x interfaceC1484x = this.f1147s;
        if (interfaceC1484x != null) {
            C0934o c0934o = (C0934o) interfaceC1484x;
            TextUtils.isEmpty("sub onVisibilityChange：" + z6);
            CCTVVideoView cCTVVideoView = c0934o.f1698a;
            cCTVVideoView.f500h = z6;
            if (z6) {
                cCTVVideoView.f505m.m361l(true);
                c0934o.f1698a.f505m.f477s.setVisibility(0);
                c0934o.f1698a.f505m.m349A(0);
                if (c0934o.f1698a.f505m.m356g()) {
                    c0934o.f1698a.f505m.setTopLayoutTopMargin(0);
                    C1142a.m1310b((Activity) c0934o.f1698a.f498f, false);
                    return;
                }
                return;
            }
            cCTVVideoView.f505m.f477s.setVisibility(8);
            c0934o.f1698a.f505m.m349A(8);
            if (c0934o.f1698a.f505m.m356g()) {
                CCTVVideoView cCTVVideoView2 = c0934o.f1698a;
                cCTVVideoView2.f505m.setTopLayoutTopMargin(C0988e.m1000z(cCTVVideoView2.f498f));
                C1142a.m1310b((Activity) c0934o.f1698a.f498f, true);
            }
        }
    }

    public void setOnSubVideoViewCountdownListener(InterfaceC1484x interfaceC1484x) {
        this.f1147s = interfaceC1484x;
    }

    public void setOnSubVideoViewPlayCompletionListener(InterfaceC1483w interfaceC1483w) {
        this.f1145q = interfaceC1483w;
    }

    public void setOnSubVideoViewPlayStatusListener(InterfaceC1485y interfaceC1485y) {
        this.f1146r = interfaceC1485y;
    }

    public PolyvSubVideoViewListenerEvent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1145q = null;
        this.f1146r = null;
        this.f1147s = null;
    }

    public PolyvSubVideoViewListenerEvent(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1145q = null;
        this.f1146r = null;
        this.f1147s = null;
    }
}
