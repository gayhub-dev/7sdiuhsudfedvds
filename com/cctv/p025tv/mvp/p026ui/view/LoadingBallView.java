package com.cctv.p025tv.mvp.p026ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import com.cctv.p025tv.R;

/* loaded from: classes.dex */
public class LoadingBallView extends View {

    /* renamed from: e */
    public int f888e;

    /* renamed from: f */
    public int f889f;

    /* renamed from: g */
    public Path f890g;

    /* renamed from: h */
    public Paint f891h;

    /* renamed from: i */
    public final Point f892i;

    /* renamed from: j */
    public int f893j;

    /* renamed from: k */
    public Paint f894k;

    /* renamed from: l */
    public Path f895l;

    /* renamed from: m */
    public final Point f896m;

    /* renamed from: n */
    public int f897n;

    /* renamed from: o */
    public Paint f898o;

    /* renamed from: p */
    public Path f899p;

    /* renamed from: q */
    public final Point f900q;

    /* renamed from: r */
    public int f901r;

    /* renamed from: s */
    public Paint f902s;

    /* renamed from: t */
    public Path f903t;

    /* renamed from: u */
    public int f904u;

    /* renamed from: v */
    public int f905v;

    /* renamed from: w */
    public final int[] f906w;

    /* renamed from: x */
    public final int[] f907x;

    /* renamed from: y */
    public final int[] f908y;

    public LoadingBallView(Context context) {
        this(context, null, 0);
    }

    /* renamed from: a */
    public int m521a(int i7, int i8) {
        int mode = View.MeasureSpec.getMode(i8);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(i8) : i7;
    }

    /* renamed from: b */
    public final void m522b() {
        Point point = this.f892i;
        int i7 = point.x;
        float f7 = point.y + this.f893j;
        int[] iArr = this.f906w;
        this.f891h.setShader(new LinearGradient(0.0f, 0.0f, i7 + r3, f7, iArr[0], iArr[1], Shader.TileMode.CLAMP));
        Point point2 = this.f896m;
        int i8 = point2.x;
        int i9 = this.f897n;
        float f8 = i8 + i9;
        float f9 = point2.y + i9;
        int[] iArr2 = this.f907x;
        this.f894k.setShader(new LinearGradient(0.0f, 0.0f, f8, f9, iArr2[0], iArr2[1], Shader.TileMode.CLAMP));
        Point point3 = this.f900q;
        int i10 = point3.x;
        int i11 = this.f901r;
        float f10 = i10 + i11;
        float f11 = point3.y + i11;
        int[] iArr3 = this.f908y;
        this.f898o.setShader(new LinearGradient(0.0f, 0.0f, f10, f11, iArr3[0], iArr3[1], Shader.TileMode.CLAMP));
    }

    public int getTrajectoryRadius() {
        return 40;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() >> 1, getHeight() >> 1);
        this.f890g.reset();
        this.f895l.reset();
        this.f899p.reset();
        this.f903t.reset();
        int i7 = this.f904u;
        if (i7 < this.f888e && i7 > 0) {
            this.f888e = i7;
        }
        int i8 = this.f905v;
        if (i8 < this.f889f && i8 > 0) {
            this.f889f = i8;
        }
        double d7 = (float) (0.0f * 0.017453292519943295d);
        this.f892i.x = (int) ((Math.sin(d7) * 40.0d) + (this.f888e / 2));
        this.f892i.y = (int) ((this.f889f / 2) - (Math.cos(d7) * 40.0d));
        int i9 = ((this.f892i.x * 10) / this.f888e) + 12;
        this.f893j = i9;
        if (i9 < 12) {
            this.f893j = 12;
        }
        double d8 = (float) ((-0.0f) * 0.017453292519943295d);
        this.f896m.x = (int) ((Math.sin(d8) * 40.0d) + (r1 / 2));
        this.f896m.y = (int) ((this.f889f / 2) - (Math.cos(d8) * 40.0d));
        int i10 = ((this.f896m.x * 10) / this.f888e) + 12;
        this.f897n = i10;
        if (i10 < 12) {
            this.f897n = 12;
        }
        double d9 = (float) (270.0f * 0.017453292519943295d);
        this.f900q.x = (int) ((Math.sin(d9) * 40.0d) + (r1 / 2));
        this.f900q.y = (int) ((this.f889f / 2) - (Math.cos(d9) * 40.0d));
        int i11 = ((this.f900q.x * 10) / this.f888e) + 12;
        this.f901r = i11;
        if (i11 < 12) {
            this.f901r = 12;
        }
        m522b();
        this.f891h.setAlpha((int) 255.0f);
        Path path = this.f890g;
        Point point = this.f892i;
        path.addCircle(point.x, point.y, this.f893j, Path.Direction.CW);
        Path path2 = this.f895l;
        Point point2 = this.f896m;
        path2.addCircle(point2.x, point2.y, this.f897n, Path.Direction.CW);
        Path path3 = this.f899p;
        Point point3 = this.f900q;
        path3.addCircle(point3.x, point3.y, this.f901r, Path.Direction.CW);
        this.f903t.op(this.f890g, this.f895l, Path.Op.INTERSECT);
        this.f903t.op(this.f899p, this.f890g, Path.Op.INTERSECT);
        this.f903t.op(this.f895l, this.f899p, Path.Op.INTERSECT);
        canvas.drawPath(this.f890g, this.f891h);
        canvas.drawPath(this.f895l, this.f894k);
        canvas.drawPath(this.f899p, this.f898o);
    }

    @Override // android.view.View
    public void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        this.f904u = m521a(200, i7);
        int iM521a = m521a(200, i8);
        this.f905v = iM521a;
        setMeasuredDimension(this.f904u, iM521a);
    }

    public LoadingBallView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadingBallView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f888e = 40;
        this.f889f = 50;
        this.f892i = new Point(-20, -20);
        this.f893j = 12;
        this.f896m = new Point(50, 30);
        this.f897n = 22;
        this.f900q = new Point(-30, 50);
        this.f901r = 14;
        this.f906w = new int[]{ContextCompat.getColor(getContext(), R.color.loading_ball_color1), ContextCompat.getColor(getContext(), R.color.loading_ball_color2)};
        this.f907x = new int[]{ContextCompat.getColor(getContext(), R.color.loading_ball_color3), ContextCompat.getColor(getContext(), R.color.loading_ball_color4)};
        this.f908y = new int[]{ContextCompat.getColor(getContext(), R.color.loading_ball_color5), ContextCompat.getColor(getContext(), R.color.loading_ball_color6)};
        Paint paint = new Paint();
        this.f891h = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f891h.setAntiAlias(true);
        this.f890g = new Path();
        Paint paint2 = new Paint();
        this.f894k = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.f894k.setAntiAlias(true);
        this.f895l = new Path();
        Paint paint3 = new Paint();
        this.f898o = paint3;
        paint3.setStyle(Paint.Style.FILL);
        this.f898o.setAntiAlias(true);
        this.f899p = new Path();
        m522b();
        Paint paint4 = new Paint();
        this.f902s = paint4;
        paint4.setAntiAlias(true);
        this.f902s.setColor(-16776961);
        this.f903t = new Path();
    }
}
