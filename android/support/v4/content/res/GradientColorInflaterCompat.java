package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.compat.C0068R;
import android.util.AttributeSet;
import android.util.Xml;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
final class GradientColorInflaterCompat {
    private static final int TILE_MODE_CLAMP = 0;
    private static final int TILE_MODE_MIRROR = 2;
    private static final int TILE_MODE_REPEAT = 1;

    private GradientColorInflaterCompat() {
    }

    private static ColorStops checkColors(@Nullable ColorStops colorStops, @ColorInt int i7, @ColorInt int i8, boolean z6, @ColorInt int i9) {
        return colorStops != null ? colorStops : z6 ? new ColorStops(i7, i9, i8) : new ColorStops(i7, i8);
    }

    public static Shader createFromXml(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xmlPullParser);
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return createFromXmlInner(resources, xmlPullParser, attributeSetAsAttributeSet, theme);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static Shader createFromXmlInner(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException {
        String name = xmlPullParser.getName();
        if (!name.equals("gradient")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
        TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, C0068R.styleable.GradientColor);
        float namedFloat = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, xmlPullParser, "startX", C0068R.styleable.GradientColor_android_startX, 0.0f);
        float namedFloat2 = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, xmlPullParser, "startY", C0068R.styleable.GradientColor_android_startY, 0.0f);
        float namedFloat3 = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, xmlPullParser, "endX", C0068R.styleable.GradientColor_android_endX, 0.0f);
        float namedFloat4 = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, xmlPullParser, "endY", C0068R.styleable.GradientColor_android_endY, 0.0f);
        float namedFloat5 = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, xmlPullParser, "centerX", C0068R.styleable.GradientColor_android_centerX, 0.0f);
        float namedFloat6 = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, xmlPullParser, "centerY", C0068R.styleable.GradientColor_android_centerY, 0.0f);
        int namedInt = TypedArrayUtils.getNamedInt(typedArrayObtainAttributes, xmlPullParser, "type", C0068R.styleable.GradientColor_android_type, 0);
        int namedColor = TypedArrayUtils.getNamedColor(typedArrayObtainAttributes, xmlPullParser, "startColor", C0068R.styleable.GradientColor_android_startColor, 0);
        boolean zHasAttribute = TypedArrayUtils.hasAttribute(xmlPullParser, "centerColor");
        int namedColor2 = TypedArrayUtils.getNamedColor(typedArrayObtainAttributes, xmlPullParser, "centerColor", C0068R.styleable.GradientColor_android_centerColor, 0);
        int namedColor3 = TypedArrayUtils.getNamedColor(typedArrayObtainAttributes, xmlPullParser, "endColor", C0068R.styleable.GradientColor_android_endColor, 0);
        int namedInt2 = TypedArrayUtils.getNamedInt(typedArrayObtainAttributes, xmlPullParser, "tileMode", C0068R.styleable.GradientColor_android_tileMode, 0);
        float namedFloat7 = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, xmlPullParser, "gradientRadius", C0068R.styleable.GradientColor_android_gradientRadius, 0.0f);
        typedArrayObtainAttributes.recycle();
        ColorStops colorStopsCheckColors = checkColors(inflateChildElements(resources, xmlPullParser, attributeSet, theme), namedColor, namedColor3, zHasAttribute, namedColor2);
        if (namedInt != 1) {
            return namedInt != 2 ? new LinearGradient(namedFloat, namedFloat2, namedFloat3, namedFloat4, colorStopsCheckColors.mColors, colorStopsCheckColors.mOffsets, parseTileMode(namedInt2)) : new SweepGradient(namedFloat5, namedFloat6, colorStopsCheckColors.mColors, colorStopsCheckColors.mOffsets);
        }
        if (namedFloat7 > 0.0f) {
            return new RadialGradient(namedFloat5, namedFloat6, namedFloat7, colorStopsCheckColors.mColors, colorStopsCheckColors.mOffsets, parseTileMode(namedInt2));
        }
        throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x008a, code lost:
    
        if (r4.size() <= 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0091, code lost:
    
        return new android.support.v4.content.res.GradientColorInflaterCompat.ColorStops(r4, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.support.v4.content.res.GradientColorInflaterCompat.ColorStops inflateChildElements(@android.support.annotation.NonNull android.content.res.Resources r9, @android.support.annotation.NonNull org.xmlpull.v1.XmlPullParser r10, @android.support.annotation.NonNull android.util.AttributeSet r11, @android.support.annotation.Nullable android.content.res.Resources.Theme r12) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            int r0 = r10.getDepth()
            r1 = 1
            int r0 = r0 + r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 20
            r2.<init>(r3)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
        L12:
            int r3 = r10.next()
            if (r3 == r1) goto L86
            int r5 = r10.getDepth()
            if (r5 >= r0) goto L21
            r6 = 3
            if (r3 == r6) goto L86
        L21:
            r6 = 2
            if (r3 == r6) goto L25
            goto L12
        L25:
            if (r5 > r0) goto L12
            java.lang.String r3 = r10.getName()
            java.lang.String r5 = "item"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L34
            goto L12
        L34:
            int[] r3 = android.support.compat.C0068R.styleable.GradientColorItem
            android.content.res.TypedArray r3 = android.support.v4.content.res.TypedArrayUtils.obtainAttributes(r9, r12, r11, r3)
            int r5 = android.support.compat.C0068R.styleable.GradientColorItem_android_color
            boolean r6 = r3.hasValue(r5)
            int r7 = android.support.compat.C0068R.styleable.GradientColorItem_android_offset
            boolean r8 = r3.hasValue(r7)
            if (r6 == 0) goto L66
            if (r8 == 0) goto L66
            r6 = 0
            int r5 = r3.getColor(r5, r6)
            r6 = 0
            float r6 = r3.getFloat(r7, r6)
            r3.recycle()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            r4.add(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r6)
            r2.add(r3)
            goto L12
        L66:
            org.xmlpull.v1.XmlPullParserException r9 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r10 = r10.getPositionDescription()
            r11.append(r10)
            java.lang.String r10 = ": <item> tag requires a 'color' attribute and a 'offset' "
            r11.append(r10)
            java.lang.String r10 = "attribute!"
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            r9.<init>(r10)
            throw r9
        L86:
            int r9 = r4.size()
            if (r9 <= 0) goto L92
            android.support.v4.content.res.GradientColorInflaterCompat$ColorStops r9 = new android.support.v4.content.res.GradientColorInflaterCompat$ColorStops
            r9.<init>(r4, r2)
            return r9
        L92:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.GradientColorInflaterCompat.inflateChildElements(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.support.v4.content.res.GradientColorInflaterCompat$ColorStops");
    }

    private static Shader.TileMode parseTileMode(int i7) {
        return i7 != 1 ? i7 != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR : Shader.TileMode.REPEAT;
    }

    public static final class ColorStops {
        public final int[] mColors;
        public final float[] mOffsets;

        public ColorStops(@NonNull List<Integer> list, @NonNull List<Float> list2) {
            int size = list.size();
            this.mColors = new int[size];
            this.mOffsets = new float[size];
            for (int i7 = 0; i7 < size; i7++) {
                this.mColors[i7] = list.get(i7).intValue();
                this.mOffsets[i7] = list2.get(i7).floatValue();
            }
        }

        public ColorStops(@ColorInt int i7, @ColorInt int i8) {
            this.mColors = new int[]{i7, i8};
            this.mOffsets = new float[]{0.0f, 1.0f};
        }

        public ColorStops(@ColorInt int i7, @ColorInt int i8, @ColorInt int i9) {
            this.mColors = new int[]{i7, i8, i9};
            this.mOffsets = new float[]{0.0f, 0.5f, 1.0f};
        }
    }
}
