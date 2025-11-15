package p035e;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import p035e.C0901n;
import p051g.C1034d;
import p107n.C1506f;

/* compiled from: MDTouchHelper.java */
/* renamed from: e.j */
/* loaded from: classes.dex */
public class C0897j {

    /* renamed from: a */
    public C0901n.c f1570a;

    /* renamed from: c */
    public GestureDetector f1572c;

    /* renamed from: f */
    public boolean f1575f;

    /* renamed from: g */
    public float f1576g;

    /* renamed from: h */
    public float f1577h;

    /* renamed from: i */
    public float f1578i;

    /* renamed from: j */
    public float f1579j;

    /* renamed from: k */
    public ValueAnimator f1580k;

    /* renamed from: l */
    public boolean f1581l;

    /* renamed from: m */
    public C1034d f1582m;

    /* renamed from: n */
    public float f1583n;

    /* renamed from: b */
    public List<C0901n.f> f1571b = new LinkedList();

    /* renamed from: d */
    public int f1573d = 0;

    /* renamed from: e */
    public b f1574e = new b(null);

    /* compiled from: MDTouchHelper.java */
    /* renamed from: e.j$a */
    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            C0897j c0897j = C0897j.this;
            if (c0897j.f1573d == 1) {
                return false;
            }
            Iterator<C0901n.f> it = c0897j.f1571b.iterator();
            while (it.hasNext()) {
                it.next().mo806b(motionEvent);
            }
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f7, float f8) {
            C0897j c0897j = C0897j.this;
            if (c0897j.f1573d == 1 || !c0897j.f1581l) {
                return false;
            }
            ValueAnimator valueAnimator = c0897j.f1580k;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator duration = ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofFloat("vx", f7, 0.0f), PropertyValuesHolder.ofFloat("vy", f8, 0.0f)).setDuration(c0897j.f1582m.f1954b);
            c0897j.f1580k = duration;
            duration.setInterpolator(c0897j.f1582m.f1953a);
            c0897j.f1580k.addUpdateListener(new C0898k(c0897j));
            c0897j.f1580k.start();
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f7, float f8) {
            C0897j c0897j = C0897j.this;
            if (c0897j.f1573d == 1) {
                return false;
            }
            C0901n.c cVar = c0897j.f1570a;
            if (cVar != null) {
                float f9 = (f7 / c0897j.f1579j) * c0897j.f1583n;
                float fM810a = C0897j.m810a(c0897j, f8);
                C1506f c1506f = ((C0899l) cVar).f1592b.f1595b;
                C1506f.b bVar = c1506f.f4318g;
                bVar.f4322e = (int) f9;
                bVar.f4323f = (int) fM810a;
                c1506f.f4144d.m956b(bVar);
            }
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            C0897j c0897j = C0897j.this;
            if (c0897j.f1573d == 1) {
                return false;
            }
            Iterator<C0901n.f> it = c0897j.f1571b.iterator();
            while (it.hasNext()) {
                it.next().mo805a(motionEvent);
            }
            return true;
        }
    }

    /* compiled from: MDTouchHelper.java */
    /* renamed from: e.j$b */
    public class b {

        /* renamed from: a */
        public float f1585a;

        /* renamed from: b */
        public float f1586b;

        /* renamed from: c */
        public float f1587c;

        public b(a aVar) {
        }
    }

    public C0897j(Context context) {
        this.f1572c = new GestureDetector(context, new a());
    }

    /* renamed from: a */
    public static float m810a(C0897j c0897j, float f7) {
        return (f7 / c0897j.f1579j) * c0897j.f1583n;
    }

    /* renamed from: b */
    public static float m811b(float f7, float f8, float f9, float f10) {
        return (float) Math.sqrt(Math.pow(f8 - f10, 2.0d) + Math.pow(f7 - f9, 2.0d));
    }

    /* renamed from: c */
    public final void m812c(float f7, float f8, float f9, float f10) {
        b bVar = this.f1574e;
        Objects.requireNonNull(bVar);
        bVar.f1585a = m811b(f7, f8, f9, f10);
        bVar.f1586b = bVar.f1587c;
    }
}
