package p035e;

import android.animation.ValueAnimator;
import p035e.C0901n;
import p107n.C1506f;

/* compiled from: MDTouchHelper.java */
/* renamed from: e.k */
/* loaded from: classes.dex */
public class C0898k implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    public long f1589a = 0;

    /* renamed from: b */
    public final /* synthetic */ C0897j f1590b;

    public C0898k(C0897j c0897j) {
        this.f1590b = c0897j;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        long currentPlayTime = valueAnimator.getCurrentPlayTime();
        float f7 = currentPlayTime - this.f1589a;
        float fFloatValue = ((((Float) valueAnimator.getAnimatedValue("vx")).floatValue() * f7) / (-1000.0f)) * this.f1590b.f1582m.f1955c;
        float fFloatValue2 = (((Float) valueAnimator.getAnimatedValue("vy")).floatValue() * f7) / (-1000.0f);
        C0897j c0897j = this.f1590b;
        float f8 = fFloatValue2 * c0897j.f1582m.f1955c;
        this.f1589a = currentPlayTime;
        C0901n.c cVar = c0897j.f1570a;
        if (cVar != null) {
            float f9 = (fFloatValue / c0897j.f1579j) * c0897j.f1583n;
            float fM810a = C0897j.m810a(c0897j, f8);
            C1506f c1506f = ((C0899l) cVar).f1592b.f1595b;
            C1506f.b bVar = c1506f.f4318g;
            bVar.f4322e = (int) f9;
            bVar.f4323f = (int) fM810a;
            c1506f.f4144d.m956b(bVar);
        }
    }
}
