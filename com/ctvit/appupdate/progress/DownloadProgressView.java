package com.ctvit.appupdate.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.constraint.solver.C0084a;
import android.util.AttributeSet;
import android.view.View;
import com.ctvit.appupdate.R$styleable;

/* loaded from: classes.dex */
public class DownloadProgressView extends View {

    /* renamed from: e */
    public Paint f928e;

    /* renamed from: f */
    public Paint f929f;

    /* renamed from: g */
    public Paint f930g;

    /* renamed from: h */
    public Paint f931h;

    /* renamed from: i */
    public int f932i;

    /* renamed from: j */
    public int f933j;

    /* renamed from: k */
    public int f934k;

    /* renamed from: l */
    public float f935l;

    /* renamed from: m */
    public float f936m;

    /* renamed from: n */
    public float f937n;

    /* renamed from: o */
    public int f938o;

    /* renamed from: p */
    public int f939p;

    /* renamed from: q */
    public float f940q;

    /* renamed from: r */
    public int f941r;

    /* renamed from: s */
    public int f942s;

    /* renamed from: t */
    public String f943t;

    public DownloadProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f941r = 100;
        this.f943t = "0%";
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.appUpdateTasksCompletedView, 0, 0);
        this.f935l = typedArrayObtainStyledAttributes.getDimension(R$styleable.appUpdateTasksCompletedView_radius, 80.0f);
        this.f937n = typedArrayObtainStyledAttributes.getDimension(R$styleable.appUpdateTasksCompletedView_strokeWidth, 10.0f);
        this.f932i = typedArrayObtainStyledAttributes.getColor(R$styleable.appUpdateTasksCompletedView_circleColor, -1);
        this.f933j = typedArrayObtainStyledAttributes.getColor(R$styleable.appUpdateTasksCompletedView_ringColor, -1);
        this.f934k = typedArrayObtainStyledAttributes.getColor(R$styleable.appUpdateTasksCompletedView_ringBgColor, -1);
        this.f936m = (this.f937n / 2.0f) + this.f935l;
        Paint paint = new Paint();
        this.f928e = paint;
        paint.setAntiAlias(true);
        this.f928e.setColor(this.f932i);
        this.f928e.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.f930g = paint2;
        paint2.setAntiAlias(true);
        this.f930g.setColor(this.f934k);
        this.f930g.setStyle(Paint.Style.STROKE);
        this.f930g.setStrokeWidth(this.f937n);
        Paint paint3 = new Paint();
        this.f929f = paint3;
        paint3.setAntiAlias(true);
        this.f929f.setColor(this.f933j);
        this.f929f.setStyle(Paint.Style.STROKE);
        this.f929f.setStrokeWidth(this.f937n);
        Paint paint4 = new Paint();
        this.f931h = paint4;
        paint4.setAntiAlias(true);
        this.f931h.setStyle(Paint.Style.FILL);
        this.f931h.setColor(this.f933j);
        this.f931h.setTextSize(this.f935l / 2.0f);
        Paint.FontMetrics fontMetrics = this.f931h.getFontMetrics();
        this.f940q = (int) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        this.f938o = getWidth() / 2;
        int height = getHeight() / 2;
        this.f939p = height;
        canvas.drawCircle(this.f938o, height, this.f935l, this.f928e);
        RectF rectF = new RectF();
        float f7 = this.f938o;
        float f8 = this.f936m;
        float f9 = f7 - f8;
        rectF.left = f9;
        float f10 = this.f939p - f8;
        rectF.top = f10;
        float f11 = f8 * 2.0f;
        rectF.right = f9 + f11;
        rectF.bottom = f10 + f11;
        canvas.drawArc(rectF, 0.0f, 360.0f, false, this.f930g);
        if (this.f942s > 0) {
            RectF rectF2 = new RectF();
            float f12 = this.f938o;
            float f13 = this.f936m;
            float f14 = f12 - f13;
            rectF2.left = f14;
            float f15 = this.f939p - f13;
            rectF2.top = f15;
            float f16 = f13 * 2.0f;
            rectF2.right = f14 + f16;
            rectF2.bottom = f15 + f16;
            canvas.drawArc(rectF2, -90.0f, (this.f942s / this.f941r) * 360.0f, false, this.f929f);
        }
        Paint paint = this.f931h;
        String str = this.f943t;
        canvas.drawText(this.f943t, this.f938o - (paint.measureText(str, 0, str.length()) / 2.0f), (this.f940q / 4.0f) + this.f939p, this.f931h);
    }

    public void setProgress(int i7) {
        this.f942s = i7;
        this.f943t = C0084a.m96a(new StringBuilder(), this.f942s, "%");
        postInvalidate();
    }

    public void setText(String str) {
        this.f943t = str;
        postInvalidate();
    }

    public void setTextSize(int i7) {
        this.f931h.setTextSize(i7);
        postInvalidate();
    }
}
