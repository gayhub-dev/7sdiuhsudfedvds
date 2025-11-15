package android.support.graphics.drawable;

import android.arch.lifecycle.C0063n;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import org.xmlpull.v1.XmlPullParser;
import p009b.C0413b;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class PathInterpolatorCompat implements Interpolator {
    public static final double EPSILON = 1.0E-5d;
    public static final int MAX_NUM_POINTS = 3000;
    private static final float PRECISION = 0.002f;

    /* renamed from: mX */
    private float[] f149mX;

    /* renamed from: mY */
    private float[] f150mY;

    public PathInterpolatorCompat(Context context, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        this(context.getResources(), context.getTheme(), attributeSet, xmlPullParser);
    }

    private void initCubic(float f7, float f8, float f9, float f10) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f7, f8, f9, f10, 1.0f, 1.0f);
        initPath(path);
    }

    private void initPath(Path path) {
        int i7 = 0;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int iMin = Math.min(MAX_NUM_POINTS, ((int) (length / PRECISION)) + 1);
        if (iMin <= 0) {
            throw new IllegalArgumentException("The Path has a invalid length " + length);
        }
        this.f149mX = new float[iMin];
        this.f150mY = new float[iMin];
        float[] fArr = new float[2];
        for (int i8 = 0; i8 < iMin; i8++) {
            pathMeasure.getPosTan((i8 * length) / (iMin - 1), fArr, null);
            this.f149mX[i8] = fArr[0];
            this.f150mY[i8] = fArr[1];
        }
        if (Math.abs(this.f149mX[0]) <= 1.0E-5d && Math.abs(this.f150mY[0]) <= 1.0E-5d) {
            int i9 = iMin - 1;
            if (Math.abs(this.f149mX[i9] - 1.0f) <= 1.0E-5d && Math.abs(this.f150mY[i9] - 1.0f) <= 1.0E-5d) {
                float f7 = 0.0f;
                int i10 = 0;
                while (i7 < iMin) {
                    float[] fArr2 = this.f149mX;
                    int i11 = i10 + 1;
                    float f8 = fArr2[i10];
                    if (f8 < f7) {
                        throw new IllegalArgumentException("The Path cannot loop back on itself, x :" + f8);
                    }
                    fArr2[i7] = f8;
                    i7++;
                    f7 = f8;
                    i10 = i11;
                }
                if (pathMeasure.nextContour()) {
                    throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours");
                }
                return;
            }
        }
        StringBuilder sbM112a = C0413b.m112a("The Path must start at (0,0) and end at (1,1) start: ");
        sbM112a.append(this.f149mX[0]);
        sbM112a.append(",");
        sbM112a.append(this.f150mY[0]);
        sbM112a.append(" end:");
        int i12 = iMin - 1;
        sbM112a.append(this.f149mX[i12]);
        sbM112a.append(",");
        sbM112a.append(this.f150mY[i12]);
        throw new IllegalArgumentException(sbM112a.toString());
    }

    private void initQuad(float f7, float f8) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f7, f8, 1.0f, 1.0f);
        initPath(path);
    }

    private void parseInterpolatorFromTypeArray(TypedArray typedArray, XmlPullParser xmlPullParser) {
        if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
            String namedString = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "pathData", 4);
            Path pathCreatePathFromPathData = PathParser.createPathFromPathData(namedString);
            if (pathCreatePathFromPathData == null) {
                throw new InflateException(C0063n.m88a("The path is null, which is created from ", namedString));
            }
            initPath(pathCreatePathFromPathData);
            return;
        }
        if (!TypedArrayUtils.hasAttribute(xmlPullParser, "controlX1")) {
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
        }
        if (!TypedArrayUtils.hasAttribute(xmlPullParser, "controlY1")) {
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
        }
        float namedFloat = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "controlX1", 0, 0.0f);
        float namedFloat2 = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "controlY1", 1, 0.0f);
        boolean zHasAttribute = TypedArrayUtils.hasAttribute(xmlPullParser, "controlX2");
        if (zHasAttribute != TypedArrayUtils.hasAttribute(xmlPullParser, "controlY2")) {
            throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
        }
        if (zHasAttribute) {
            initCubic(namedFloat, namedFloat2, TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "controlX2", 2, 0.0f), TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "controlY2", 3, 0.0f));
        } else {
            initQuad(namedFloat, namedFloat2);
        }
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f7) {
        if (f7 <= 0.0f) {
            return 0.0f;
        }
        if (f7 >= 1.0f) {
            return 1.0f;
        }
        int i7 = 0;
        int length = this.f149mX.length - 1;
        while (length - i7 > 1) {
            int i8 = (i7 + length) / 2;
            if (f7 < this.f149mX[i8]) {
                length = i8;
            } else {
                i7 = i8;
            }
        }
        float[] fArr = this.f149mX;
        float f8 = fArr[length] - fArr[i7];
        if (f8 == 0.0f) {
            return this.f150mY[i7];
        }
        float f9 = (f7 - fArr[i7]) / f8;
        float[] fArr2 = this.f150mY;
        float f10 = fArr2[i7];
        return ((fArr2[length] - f10) * f9) + f10;
    }

    public PathInterpolatorCompat(Resources resources, Resources.Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_PATH_INTERPOLATOR);
        parseInterpolatorFromTypeArray(typedArrayObtainAttributes, xmlPullParser);
        typedArrayObtainAttributes.recycle();
    }
}
