package p068i0;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import p043f.C0988e;
import p068i0.C1140f;
import p141r.ComponentCallbacks2C1808c;
import p155t.InterfaceC1891a;
import p162u.InterfaceC1969m;
import p190y.InterfaceC2086d;

/* compiled from: GifDrawable.java */
/* renamed from: i0.c */
/* loaded from: classes.dex */
public class C1137c extends Drawable implements C1140f.b, Animatable {

    /* renamed from: e */
    public final a f2478e;

    /* renamed from: f */
    public boolean f2479f;

    /* renamed from: g */
    public boolean f2480g;

    /* renamed from: h */
    public boolean f2481h;

    /* renamed from: i */
    public boolean f2482i;

    /* renamed from: j */
    public int f2483j;

    /* renamed from: k */
    public int f2484k;

    /* renamed from: l */
    public boolean f2485l;

    /* renamed from: m */
    public Paint f2486m;

    /* renamed from: n */
    public Rect f2487n;

    /* compiled from: GifDrawable.java */
    /* renamed from: i0.c$a */
    public static class a extends Drawable.ConstantState {

        /* renamed from: a */
        public final InterfaceC2086d f2488a;

        /* renamed from: b */
        public final C1140f f2489b;

        public a(InterfaceC2086d interfaceC2086d, C1140f c1140f) {
            this.f2488a = interfaceC2086d;
            this.f2489b = c1140f;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new C1137c(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new C1137c(this);
        }
    }

    public C1137c(Context context, InterfaceC1891a interfaceC1891a, InterfaceC2086d interfaceC2086d, InterfaceC1969m<Bitmap> interfaceC1969m, int i7, int i8, Bitmap bitmap) {
        a aVar = new a(interfaceC2086d, new C1140f(ComponentCallbacks2C1808c.m2022c(context), interfaceC1891a, i7, i8, interfaceC1969m, bitmap));
        this.f2482i = true;
        this.f2484k = -1;
        this.f2478e = aVar;
    }

    @Override // p068i0.C1140f.b
    /* renamed from: a */
    public void mo1301a() {
        if (getCallback() == null) {
            stop();
            invalidateSelf();
            return;
        }
        invalidateSelf();
        C1140f.a aVar = this.f2478e.f2489b.f2499i;
        if ((aVar != null ? aVar.f2505e : -1) == r0.f2491a.mo2183d() - 1) {
            this.f2483j++;
        }
        int i7 = this.f2484k;
        if (i7 == -1 || this.f2483j < i7) {
            return;
        }
        stop();
    }

    /* renamed from: b */
    public final Paint m1302b() {
        if (this.f2486m == null) {
            this.f2486m = new Paint(2);
        }
        return this.f2486m;
    }

    /* renamed from: c */
    public final void m1303c() {
        C0988e.m978d(!this.f2481h, "You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request.");
        if (this.f2478e.f2489b.f2491a.mo2183d() == 1) {
            invalidateSelf();
            return;
        }
        if (this.f2479f) {
            return;
        }
        this.f2479f = true;
        C1140f c1140f = this.f2478e.f2489b;
        if (c1140f.f2500j) {
            throw new IllegalStateException("Cannot subscribe to a cleared frame loader");
        }
        boolean zIsEmpty = c1140f.f2493c.isEmpty();
        if (c1140f.f2493c.contains(this)) {
            throw new IllegalStateException("Cannot subscribe twice in a row");
        }
        c1140f.f2493c.add(this);
        if (zIsEmpty && !c1140f.f2496f) {
            c1140f.f2496f = true;
            c1140f.f2500j = false;
            c1140f.m1306b();
        }
        invalidateSelf();
    }

    /* renamed from: d */
    public final void m1304d() {
        this.f2479f = false;
        C1140f c1140f = this.f2478e.f2489b;
        c1140f.f2493c.remove(this);
        if (c1140f.f2493c.isEmpty()) {
            c1140f.f2496f = false;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f2481h) {
            return;
        }
        if (this.f2485l) {
            int intrinsicWidth = getIntrinsicWidth();
            int intrinsicHeight = getIntrinsicHeight();
            Rect bounds = getBounds();
            if (this.f2487n == null) {
                this.f2487n = new Rect();
            }
            Gravity.apply(119, intrinsicWidth, intrinsicHeight, bounds, this.f2487n);
            this.f2485l = false;
        }
        Bitmap bitmapM1305a = this.f2478e.f2489b.m1305a();
        if (this.f2487n == null) {
            this.f2487n = new Rect();
        }
        canvas.drawBitmap(bitmapM1305a, (Rect) null, this.f2487n, m1302b());
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f2478e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f2478e.f2489b.m1305a().getHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f2478e.f2489b.m1305a().getWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f2479f;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f2485l = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i7) {
        m1302b().setAlpha(i7);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        m1302b().setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z6, boolean z7) {
        C0988e.m978d(!this.f2481h, "Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.");
        this.f2482i = z6;
        if (!z6) {
            m1304d();
        } else if (this.f2480g) {
            m1303c();
        }
        return super.setVisible(z6, z7);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f2480g = true;
        this.f2483j = 0;
        if (this.f2482i) {
            m1303c();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f2480g = false;
        m1304d();
    }

    public C1137c(a aVar) {
        this.f2482i = true;
        this.f2484k = -1;
        this.f2478e = aVar;
    }
}
