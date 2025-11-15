package com.easefun.povplayer.core.video;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import com.cctv.cctvplayer.CCTVVideoView;
import java.util.Objects;
import p037e1.C0922c;
import p037e1.C0923d;
import p037e1.C0924e;
import p037e1.C0925f;
import p037e1.C0926g;
import p037e1.C0944y;
import p087k3.C1233b;
import p103m3.AbstractC1475o;
import p103m3.AbstractC1476p;
import p103m3.InterfaceC1466f;
import p103m3.InterfaceC1467g;
import p103m3.InterfaceC1468h;
import p103m3.InterfaceC1469i;
import p103m3.InterfaceC1470j;
import p103m3.InterfaceC1471k;
import p103m3.InterfaceC1472l;
import p103m3.InterfaceC1473m;
import p103m3.InterfaceC1474n;
import p103m3.InterfaceC1478r;

/* loaded from: classes.dex */
public abstract class PolyvVideoViewListenerEvent extends PolyvBaseVideoViewListenerEvent {

    /* renamed from: A */
    public InterfaceC1469i f1204A;

    /* renamed from: B */
    public InterfaceC1470j f1205B;

    /* renamed from: q */
    public InterfaceC1478r f1206q;

    /* renamed from: r */
    public InterfaceC1468h f1207r;

    /* renamed from: s */
    public InterfaceC1467g f1208s;

    /* renamed from: t */
    public InterfaceC1474n f1209t;

    /* renamed from: u */
    public InterfaceC1473m f1210u;

    /* renamed from: v */
    public AbstractC1475o f1211v;

    /* renamed from: w */
    public AbstractC1476p f1212w;

    /* renamed from: x */
    public InterfaceC1466f f1213x;

    /* renamed from: y */
    public InterfaceC1471k f1214y;

    /* renamed from: z */
    public InterfaceC1472l f1215z;

    public PolyvVideoViewListenerEvent(Context context) {
        super(context);
        this.f1206q = null;
        this.f1207r = null;
        this.f1208s = null;
        this.f1209t = null;
        this.f1210u = null;
        this.f1211v = null;
        this.f1212w = null;
        this.f1213x = null;
        this.f1214y = null;
        this.f1215z = null;
        this.f1204A = null;
        this.f1205B = null;
    }

    /* renamed from: p */
    public void m623p() {
        InterfaceC1466f interfaceC1466f = this.f1213x;
        if (interfaceC1466f != null) {
            interfaceC1466f.callback();
        }
    }

    /* renamed from: q */
    public void m624q(boolean z6, boolean z7) {
        InterfaceC1467g interfaceC1467g = this.f1208s;
        if (interfaceC1467g != null) {
            C0944y c0944y = (C0944y) interfaceC1467g;
            if (CCTVVideoView.m376h(c0944y.f1707a)) {
                return;
            }
            CCTVVideoView cCTVVideoView = c0944y.f1707a;
            if (cCTVVideoView.f516x) {
                return;
            }
            int iM610F = cCTVVideoView.f503k.m610F((Activity) cCTVVideoView.f498f) - 8;
            if (iM610F < 0) {
                iM610F = 0;
            }
            if (z6) {
                CCTVVideoView cCTVVideoView2 = c0944y.f1707a;
                cCTVVideoView2.f503k.m619O((Activity) cCTVVideoView2.f498f, iM610F);
            }
            c0944y.f1707a.f506n.m394a(iM610F, z7);
        }
    }

    /* renamed from: r */
    public void m625r(boolean z6, boolean z7) {
        InterfaceC1468h interfaceC1468h = this.f1207r;
        if (interfaceC1468h != null) {
            C0922c c0922c = (C0922c) interfaceC1468h;
            if (CCTVVideoView.m376h(c0922c.f1686a)) {
                return;
            }
            CCTVVideoView cCTVVideoView = c0922c.f1686a;
            if (cCTVVideoView.f516x) {
                return;
            }
            int iM610F = cCTVVideoView.f503k.m610F((Activity) cCTVVideoView.f498f) + 8;
            if (iM610F > 100) {
                iM610F = 100;
            }
            if (z6) {
                CCTVVideoView cCTVVideoView2 = c0922c.f1686a;
                cCTVVideoView2.f503k.m619O((Activity) cCTVVideoView2.f498f, iM610F);
            }
            c0922c.f1686a.f506n.m394a(iM610F, z7);
        }
    }

    /* renamed from: s */
    public void m626s(boolean z6, boolean z7) {
        InterfaceC1473m interfaceC1473m = this.f1210u;
        if (interfaceC1473m != null) {
            C0923d c0923d = (C0923d) interfaceC1473m;
            if (CCTVVideoView.m376h(c0923d.f1687a)) {
                return;
            }
            CCTVVideoView cCTVVideoView = c0923d.f1687a;
            if (cCTVVideoView.f516x) {
                return;
            }
            int volume = cCTVVideoView.f503k.getVolume() - C1233b.m1457a(c0923d.f1687a.f498f, 8);
            if (volume < 0) {
                volume = 0;
            }
            if (z6) {
                c0923d.f1687a.f503k.setVolume(volume);
            }
            c0923d.f1687a.f507o.m399a(volume, z7);
        }
    }

    public void setOnGestureDoubleClickListener(InterfaceC1466f interfaceC1466f) {
        this.f1213x = interfaceC1466f;
    }

    public void setOnGestureLeftDownListener(InterfaceC1467g interfaceC1467g) {
        this.f1208s = interfaceC1467g;
    }

    public void setOnGestureLeftUpListener(InterfaceC1468h interfaceC1468h) {
        this.f1207r = interfaceC1468h;
    }

    public void setOnGestureLongPressLeftBottomListener(InterfaceC1469i interfaceC1469i) {
        this.f1204A = interfaceC1469i;
    }

    public void setOnGestureLongPressLeftTopListener(InterfaceC1470j interfaceC1470j) {
        this.f1205B = interfaceC1470j;
    }

    public void setOnGestureLongPressRightBottomListener(InterfaceC1471k interfaceC1471k) {
        this.f1214y = interfaceC1471k;
    }

    public void setOnGestureLongPressRightTopListener(InterfaceC1472l interfaceC1472l) {
        this.f1215z = interfaceC1472l;
    }

    public void setOnGestureRightDownListener(InterfaceC1473m interfaceC1473m) {
        this.f1210u = interfaceC1473m;
    }

    public void setOnGestureRightUpListener(InterfaceC1474n interfaceC1474n) {
        this.f1209t = interfaceC1474n;
    }

    public void setOnGestureSwipeLeftListener(AbstractC1475o abstractC1475o) {
        this.f1211v = abstractC1475o;
    }

    public void setOnGestureSwipeRightListener(AbstractC1476p abstractC1476p) {
        this.f1212w = abstractC1476p;
    }

    public void setOnPlayErrorListener(InterfaceC1478r interfaceC1478r) {
        this.f1206q = interfaceC1478r;
    }

    /* renamed from: t */
    public void m627t(boolean z6, boolean z7) {
        InterfaceC1474n interfaceC1474n = this.f1209t;
        if (interfaceC1474n != null) {
            C0924e c0924e = (C0924e) interfaceC1474n;
            if (CCTVVideoView.m376h(c0924e.f1688a)) {
                return;
            }
            CCTVVideoView cCTVVideoView = c0924e.f1688a;
            if (cCTVVideoView.f516x) {
                return;
            }
            int iM1457a = C1233b.m1457a(c0924e.f1688a.f498f, 8) + cCTVVideoView.f503k.getVolume();
            if (iM1457a > 100) {
                iM1457a = 100;
            }
            if (z6) {
                c0924e.f1688a.f503k.setVolume(iM1457a);
            }
            c0924e.f1688a.f507o.m399a(iM1457a, z7);
        }
    }

    /* renamed from: u */
    public void m628u(boolean z6, int i7, boolean z7) {
        AbstractC1475o abstractC1475o = this.f1211v;
        if (abstractC1475o != null) {
            Objects.requireNonNull(abstractC1475o);
            C0925f c0925f = (C0925f) this.f1211v;
            if (CCTVVideoView.m376h(c0925f.f1689a)) {
                return;
            }
            CCTVVideoView cCTVVideoView = c0925f.f1689a;
            if (cCTVVideoView.f516x) {
                return;
            }
            if (cCTVVideoView.f503k.m614J()) {
                c0925f.f1689a.f511s.m397b(z7, false);
                c0925f.f1689a.f511s.m396a();
                return;
            }
            if (!c0925f.f1689a.f503k.m613I() || !c0925f.f1689a.f503k.m616L()) {
                if (z7) {
                    CCTVVideoView cCTVVideoView2 = c0925f.f1689a;
                    cCTVVideoView2.f510r = 0;
                    cCTVVideoView2.f511s.m396a();
                    return;
                }
                return;
            }
            CCTVVideoView cCTVVideoView3 = c0925f.f1689a;
            if (cCTVVideoView3.f510r == 0) {
                cCTVVideoView3.f510r = cCTVVideoView3.f503k.getCurrentPosition();
            }
            if (z7) {
                CCTVVideoView cCTVVideoView4 = c0925f.f1689a;
                if (cCTVVideoView4.f510r < 0) {
                    cCTVVideoView4.f510r = 0;
                }
                cCTVVideoView4.mo389t(cCTVVideoView4.f510r);
                if (c0925f.f1689a.f503k.m612H()) {
                    c0925f.f1689a.f503k.start();
                }
                c0925f.f1689a.f510r = 0;
            } else {
                CCTVVideoView cCTVVideoView5 = c0925f.f1689a;
                int i8 = cCTVVideoView5.f510r - (i7 * 1000);
                cCTVVideoView5.f510r = i8;
                if (i8 <= 0) {
                    cCTVVideoView5.f510r = -1;
                }
            }
            CCTVVideoView cCTVVideoView6 = c0925f.f1689a;
            cCTVVideoView6.f511s.m398c(cCTVVideoView6.f510r, cCTVVideoView6.f503k.getDuration(), z7, false);
        }
    }

    /* renamed from: v */
    public void m629v(boolean z6, int i7, boolean z7) {
        AbstractC1476p abstractC1476p = this.f1212w;
        if (abstractC1476p != null) {
            Objects.requireNonNull(abstractC1476p);
            C0926g c0926g = (C0926g) this.f1212w;
            if (CCTVVideoView.m376h(c0926g.f1690a)) {
                return;
            }
            CCTVVideoView cCTVVideoView = c0926g.f1690a;
            if (cCTVVideoView.f516x) {
                return;
            }
            if (cCTVVideoView.f503k.m614J()) {
                c0926g.f1690a.f511s.m397b(z7, true);
                if (z7) {
                    c0926g.f1690a.f511s.m396a();
                    return;
                }
                return;
            }
            if (!c0926g.f1690a.f503k.m613I() || !c0926g.f1690a.f503k.m616L()) {
                if (z7) {
                    CCTVVideoView cCTVVideoView2 = c0926g.f1690a;
                    cCTVVideoView2.f510r = 0;
                    cCTVVideoView2.f511s.m396a();
                    return;
                }
                return;
            }
            CCTVVideoView cCTVVideoView3 = c0926g.f1690a;
            if (cCTVVideoView3.f510r == 0) {
                cCTVVideoView3.f510r = cCTVVideoView3.f503k.getCurrentPosition();
            }
            if (z7) {
                CCTVVideoView cCTVVideoView4 = c0926g.f1690a;
                if (cCTVVideoView4.f510r > cCTVVideoView4.f503k.getDuration()) {
                    CCTVVideoView cCTVVideoView5 = c0926g.f1690a;
                    cCTVVideoView5.f510r = cCTVVideoView5.f503k.getDuration();
                }
                if (c0926g.f1690a.f503k.m612H()) {
                    CCTVVideoView cCTVVideoView6 = c0926g.f1690a;
                    if (cCTVVideoView6.f510r < cCTVVideoView6.f503k.getDuration()) {
                        CCTVVideoView cCTVVideoView7 = c0926g.f1690a;
                        cCTVVideoView7.mo389t(cCTVVideoView7.f510r);
                        c0926g.f1690a.f503k.start();
                    }
                } else {
                    CCTVVideoView cCTVVideoView8 = c0926g.f1690a;
                    cCTVVideoView8.mo389t(cCTVVideoView8.f510r);
                }
                c0926g.f1690a.f510r = 0;
            } else {
                CCTVVideoView cCTVVideoView9 = c0926g.f1690a;
                int i8 = cCTVVideoView9.f510r + (i7 * 1000);
                cCTVVideoView9.f510r = i8;
                if (i8 > cCTVVideoView9.f503k.getDuration()) {
                    CCTVVideoView cCTVVideoView10 = c0926g.f1690a;
                    cCTVVideoView10.f510r = cCTVVideoView10.f503k.getDuration();
                }
            }
            CCTVVideoView cCTVVideoView11 = c0926g.f1690a;
            cCTVVideoView11.f511s.m398c(cCTVVideoView11.f510r, cCTVVideoView11.f503k.getDuration(), z7, true);
        }
    }

    public PolyvVideoViewListenerEvent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1206q = null;
        this.f1207r = null;
        this.f1208s = null;
        this.f1209t = null;
        this.f1210u = null;
        this.f1211v = null;
        this.f1212w = null;
        this.f1213x = null;
        this.f1214y = null;
        this.f1215z = null;
        this.f1204A = null;
        this.f1205B = null;
    }

    public PolyvVideoViewListenerEvent(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1206q = null;
        this.f1207r = null;
        this.f1208s = null;
        this.f1209t = null;
        this.f1210u = null;
        this.f1211v = null;
        this.f1212w = null;
        this.f1213x = null;
        this.f1214y = null;
        this.f1215z = null;
        this.f1204A = null;
        this.f1205B = null;
    }
}
