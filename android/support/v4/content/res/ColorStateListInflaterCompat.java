package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.compat.C0068R;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class ColorStateListInflaterCompat {
    private static final int DEFAULT_COLOR = -65536;

    private ColorStateListInflaterCompat() {
    }

    @NonNull
    public static ColorStateList createFromXml(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
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

    @NonNull
    public static ColorStateList createFromXmlInner(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException {
        String name = xmlPullParser.getName();
        if (name.equals("selector")) {
            return inflate(resources, xmlPullParser, attributeSet, theme);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid color state list tag " + name);
    }

    private static ColorStateList inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth;
        int i7 = 1;
        int depth2 = xmlPullParser.getDepth() + 1;
        int[][] iArr = new int[20][];
        int[] iArrAppend = new int[20];
        int i8 = 0;
        while (true) {
            int next = xmlPullParser.next();
            if (next == i7 || ((depth = xmlPullParser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2 && xmlPullParser.getName().equals("item")) {
                TypedArray typedArrayObtainAttributes = obtainAttributes(resources, theme, attributeSet, C0068R.styleable.ColorStateListItem);
                int color = typedArrayObtainAttributes.getColor(C0068R.styleable.ColorStateListItem_android_color, -65281);
                float f7 = 1.0f;
                int i9 = C0068R.styleable.ColorStateListItem_android_alpha;
                if (typedArrayObtainAttributes.hasValue(i9)) {
                    f7 = typedArrayObtainAttributes.getFloat(i9, 1.0f);
                } else {
                    int i10 = C0068R.styleable.ColorStateListItem_alpha;
                    if (typedArrayObtainAttributes.hasValue(i10)) {
                        f7 = typedArrayObtainAttributes.getFloat(i10, 1.0f);
                    }
                }
                typedArrayObtainAttributes.recycle();
                int attributeCount = attributeSet.getAttributeCount();
                int[] iArr2 = new int[attributeCount];
                int i11 = 0;
                for (int i12 = 0; i12 < attributeCount; i12++) {
                    int attributeNameResource = attributeSet.getAttributeNameResource(i12);
                    if (attributeNameResource != 16843173 && attributeNameResource != 16843551 && attributeNameResource != C0068R.attr.alpha) {
                        int i13 = i11 + 1;
                        if (!attributeSet.getAttributeBooleanValue(i12, false)) {
                            attributeNameResource = -attributeNameResource;
                        }
                        iArr2[i11] = attributeNameResource;
                        i11 = i13;
                    }
                }
                int[] iArrTrimStateSet = StateSet.trimStateSet(iArr2, i11);
                int iModulateColorAlpha = modulateColorAlpha(color, f7);
                if (i8 != 0) {
                    int length = iArrTrimStateSet.length;
                }
                iArrAppend = GrowingArrayUtils.append(iArrAppend, i8, iModulateColorAlpha);
                iArr = (int[][]) GrowingArrayUtils.append(iArr, i8, iArrTrimStateSet);
                i8++;
            }
            i7 = 1;
        }
        int[] iArr3 = new int[i8];
        int[][] iArr4 = new int[i8][];
        System.arraycopy(iArrAppend, 0, iArr3, 0, i8);
        System.arraycopy(iArr, 0, iArr4, 0, i8);
        return new ColorStateList(iArr4, iArr3);
    }

    @ColorInt
    private static int modulateColorAlpha(@ColorInt int i7, @FloatRange(from = 0.0d, m90to = 1.0d) float f7) {
        return (i7 & ViewCompat.MEASURED_SIZE_MASK) | (Math.round(Color.alpha(i7) * f7) << 24);
    }

    private static TypedArray obtainAttributes(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }
}
