package p035e;

import android.animation.ValueAnimator;
import android.view.MotionEvent;
import android.view.View;
import java.util.Objects;
import p035e.C0897j;
import p035e.C0901n;

/* compiled from: MDVRLibrary.java */
/* renamed from: e.m */
/* loaded from: classes.dex */
public class ViewOnTouchListenerC0900m implements View.OnTouchListener {

    /* renamed from: e */
    public final /* synthetic */ C0901n f1593e;

    public ViewOnTouchListenerC0900m(C0901n c0901n) {
        this.f1593e = c0901n;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ValueAnimator valueAnimator;
        C0897j c0897j = this.f1593e.f1601h;
        Objects.requireNonNull(c0897j);
        int action = motionEvent.getAction() & 255;
        if (action == 1 || action == 3) {
            c0897j.f1573d = 0;
        } else if (action == 6) {
            if (c0897j.f1573d == 1 && motionEvent.getPointerCount() > 2) {
                if ((motionEvent.getAction() >> 8) == 0) {
                    c0897j.m812c(motionEvent.getX(1), motionEvent.getY(1), motionEvent.getX(2), motionEvent.getY(2));
                } else if ((motionEvent.getAction() >> 8) == 1) {
                    c0897j.m812c(motionEvent.getX(0), motionEvent.getY(0), motionEvent.getX(2), motionEvent.getY(2));
                }
            }
        } else if (action == 5) {
            c0897j.f1573d = 1;
            c0897j.m812c(motionEvent.getX(0), motionEvent.getY(0), motionEvent.getX(1), motionEvent.getY(1));
        } else if (action == 2) {
            if (c0897j.f1573d == 1 && motionEvent.getPointerCount() > 1) {
                float fM811b = C0897j.m811b(motionEvent.getX(0), motionEvent.getY(0), motionEvent.getX(1), motionEvent.getY(1));
                if (c0897j.f1575f) {
                    C0897j.b bVar = c0897j.f1574e;
                    if (bVar.f1585a == 0.0f) {
                        bVar.f1585a = fM811b;
                    }
                    float f7 = (fM811b / bVar.f1585a) - 1.0f;
                    C0897j c0897j2 = C0897j.this;
                    float f8 = bVar.f1586b + (c0897j2.f1578i * 3.0f * f7);
                    bVar.f1587c = f8;
                    float fMax = Math.max(f8, c0897j2.f1576g);
                    bVar.f1587c = fMax;
                    float fMin = Math.min(fMax, C0897j.this.f1577h);
                    bVar.f1587c = fMin;
                    C0901n.c cVar = c0897j.f1570a;
                    if (cVar != null) {
                        C0899l c0899l = (C0899l) cVar;
                        C0901n.j jVar = c0899l.f1591a;
                        jVar.f1621e = fMin;
                        c0899l.f1592b.f1603j.m956b(jVar);
                    }
                    c0897j.f1579j = fMin;
                }
            }
        } else if (action == 0 && (valueAnimator = c0897j.f1580k) != null) {
            valueAnimator.cancel();
        }
        c0897j.f1572c.onTouchEvent(motionEvent);
        return true;
    }
}
