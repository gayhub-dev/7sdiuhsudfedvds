package com.easefun.povplayer.core.video;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import java.util.Objects;
import p037e1.C0928i;
import p103m3.InterfaceC1461a;
import p103m3.InterfaceC1462b;
import p103m3.InterfaceC1463c;
import p103m3.InterfaceC1464d;
import p103m3.InterfaceC1465e;
import p103m3.InterfaceC1477q;
import p103m3.InterfaceC1479s;
import p103m3.InterfaceC1480t;
import p103m3.InterfaceC1481u;
import p103m3.InterfaceC1482v;
import p103m3.InterfaceC1486z;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes.dex */
public abstract class PolyvBaseVideoViewListenerEvent extends PolyvForwardingIjkVideoView {

    /* renamed from: f */
    public InterfaceC1463c f1094f;

    /* renamed from: g */
    public InterfaceC1479s f1095g;

    /* renamed from: h */
    public InterfaceC1480t f1096h;

    /* renamed from: i */
    public InterfaceC1464d f1097i;

    /* renamed from: j */
    public InterfaceC1477q f1098j;

    /* renamed from: k */
    public InterfaceC1482v f1099k;

    /* renamed from: l */
    public InterfaceC1486z f1100l;

    /* renamed from: m */
    public InterfaceC1465e f1101m;

    /* renamed from: n */
    public InterfaceC1481u f1102n;

    /* renamed from: o */
    public InterfaceC1461a f1103o;

    /* renamed from: p */
    public InterfaceC1462b f1104p;

    public PolyvBaseVideoViewListenerEvent(Context context) {
        super(context);
        this.f1094f = null;
        this.f1095g = null;
        this.f1096h = null;
        this.f1097i = null;
        this.f1098j = null;
        this.f1099k = null;
        this.f1100l = null;
        this.f1101m = null;
        this.f1102n = null;
        this.f1103o = null;
        this.f1104p = null;
    }

    /* renamed from: m */
    public void m571m(IMediaPlayer iMediaPlayer, int i7) {
        InterfaceC1463c interfaceC1463c = this.f1094f;
        if (interfaceC1463c != null) {
            Objects.requireNonNull(((C0928i) interfaceC1463c).f1692a);
            TextUtils.isEmpty("onBufferingUpdate_" + i7);
        }
    }

    /* renamed from: n */
    public void m572n() {
        InterfaceC1465e interfaceC1465e = this.f1101m;
        if (interfaceC1465e != null) {
            interfaceC1465e.callback();
        }
    }

    /* renamed from: o */
    public void m573o() {
        this.f1094f = null;
        this.f1095g = null;
        this.f1096h = null;
        this.f1097i = null;
        this.f1098j = null;
        this.f1099k = null;
        this.f1100l = null;
        this.f1101m = null;
        this.f1102n = null;
        this.f1103o = null;
        this.f1104p = null;
    }

    public void setOnAudioVividMetadataListener(InterfaceC1461a interfaceC1461a) {
        this.f1103o = interfaceC1461a;
    }

    public void setOnBufferingUpdateListener(InterfaceC1463c interfaceC1463c) {
        this.f1094f = interfaceC1463c;
    }

    public void setOnErrorListener(InterfaceC1464d interfaceC1464d) {
        this.f1097i = interfaceC1464d;
    }

    public void setOnGestureClickListener(InterfaceC1465e interfaceC1465e) {
        this.f1101m = interfaceC1465e;
    }

    public void setOnInfoListener(InterfaceC1477q interfaceC1477q) {
        this.f1098j = interfaceC1477q;
    }

    public void setOnMpegTsPesPrivateDataListener(InterfaceC1462b interfaceC1462b) {
        this.f1104p = interfaceC1462b;
    }

    public void setOnPlayPauseListener(InterfaceC1479s interfaceC1479s) {
        this.f1095g = interfaceC1479s;
    }

    public void setOnPreparedListener(InterfaceC1480t interfaceC1480t) {
        this.f1096h = interfaceC1480t;
    }

    public void setOnSEIRefreshListener(InterfaceC1481u interfaceC1481u) {
        this.f1102n = interfaceC1481u;
    }

    public void setOnSeekCompleteListener(InterfaceC1482v interfaceC1482v) {
        this.f1099k = interfaceC1482v;
    }

    public void setOnVideoSizeChangedListener(InterfaceC1486z interfaceC1486z) {
        this.f1100l = interfaceC1486z;
    }

    public PolyvBaseVideoViewListenerEvent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1094f = null;
        this.f1095g = null;
        this.f1096h = null;
        this.f1097i = null;
        this.f1098j = null;
        this.f1099k = null;
        this.f1100l = null;
        this.f1101m = null;
        this.f1102n = null;
        this.f1103o = null;
        this.f1104p = null;
    }

    public PolyvBaseVideoViewListenerEvent(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1094f = null;
        this.f1095g = null;
        this.f1096h = null;
        this.f1097i = null;
        this.f1098j = null;
        this.f1099k = null;
        this.f1100l = null;
        this.f1101m = null;
        this.f1102n = null;
        this.f1103o = null;
        this.f1104p = null;
    }
}
