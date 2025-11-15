package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.AnimatorRes;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class AnimatorInflaterCompat {
    private static final boolean DBG_ANIMATOR_INFLATER = false;
    private static final int MAX_NUM_POINTS = 100;
    private static final String TAG = "AnimatorInflater";
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_COLOR = 3;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final int VALUE_TYPE_PATH = 2;
    private static final int VALUE_TYPE_UNDEFINED = 4;

    public static class PathDataEvaluator implements TypeEvaluator<PathParser.PathDataNode[]> {
        private PathParser.PathDataNode[] mNodeArray;

        public PathDataEvaluator() {
        }

        public PathDataEvaluator(PathParser.PathDataNode[] pathDataNodeArr) {
            this.mNodeArray = pathDataNodeArr;
        }

        @Override // android.animation.TypeEvaluator
        public PathParser.PathDataNode[] evaluate(float f7, PathParser.PathDataNode[] pathDataNodeArr, PathParser.PathDataNode[] pathDataNodeArr2) {
            if (!PathParser.canMorph(pathDataNodeArr, pathDataNodeArr2)) {
                throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
            }
            PathParser.PathDataNode[] pathDataNodeArr3 = this.mNodeArray;
            if (pathDataNodeArr3 == null || !PathParser.canMorph(pathDataNodeArr3, pathDataNodeArr)) {
                this.mNodeArray = PathParser.deepCopyNodes(pathDataNodeArr);
            }
            for (int i7 = 0; i7 < pathDataNodeArr.length; i7++) {
                this.mNodeArray[i7].interpolatePathDataNode(pathDataNodeArr[i7], pathDataNodeArr2[i7], f7);
            }
            return this.mNodeArray;
        }
    }

    private AnimatorInflaterCompat() {
    }

    private static Animator createAnimatorFromXml(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, float f7) {
        return createAnimatorFromXml(context, resources, theme, xmlPullParser, Xml.asAttributeSet(xmlPullParser), null, 0, f7);
    }

    private static Keyframe createNewKeyframe(Keyframe keyframe, float f7) {
        return keyframe.getType() == Float.TYPE ? Keyframe.ofFloat(f7) : keyframe.getType() == Integer.TYPE ? Keyframe.ofInt(f7) : Keyframe.ofObject(f7);
    }

    private static void distributeKeyframes(Keyframe[] keyframeArr, float f7, int i7, int i8) {
        float f8 = f7 / ((i8 - i7) + 2);
        while (i7 <= i8) {
            keyframeArr[i7].setFraction(keyframeArr[i7 - 1].getFraction() + f8);
            i7++;
        }
    }

    private static void dumpKeyframes(Object[] objArr, String str) {
        if (objArr == null || objArr.length == 0) {
            return;
        }
        int length = objArr.length;
        for (int i7 = 0; i7 < length; i7++) {
            Keyframe keyframe = (Keyframe) objArr[i7];
            StringBuilder sbM98a = C0116a.m98a("Keyframe ", i7, ": fraction ");
            Object value = "null";
            sbM98a.append(keyframe.getFraction() < 0.0f ? "null" : Float.valueOf(keyframe.getFraction()));
            sbM98a.append(", ");
            sbM98a.append(", value : ");
            if (keyframe.hasValue()) {
                value = keyframe.getValue();
            }
            sbM98a.append(value);
        }
    }

    private static PropertyValuesHolder getPVH(TypedArray typedArray, int i7, int i8, int i9, String str) {
        PropertyValuesHolder propertyValuesHolderOfFloat;
        PropertyValuesHolder propertyValuesHolderOfObject;
        TypedValue typedValuePeekValue = typedArray.peekValue(i8);
        boolean z6 = typedValuePeekValue != null;
        int i10 = z6 ? typedValuePeekValue.type : 0;
        TypedValue typedValuePeekValue2 = typedArray.peekValue(i9);
        boolean z7 = typedValuePeekValue2 != null;
        int i11 = z7 ? typedValuePeekValue2.type : 0;
        if (i7 == 4) {
            i7 = ((z6 && isColorType(i10)) || (z7 && isColorType(i11))) ? 3 : 0;
        }
        boolean z8 = i7 == 0;
        PropertyValuesHolder propertyValuesHolderOfInt = null;
        if (i7 != 2) {
            ArgbEvaluator argbEvaluator = i7 == 3 ? ArgbEvaluator.getInstance() : null;
            if (z8) {
                if (z6) {
                    float dimension = i10 == 5 ? typedArray.getDimension(i8, 0.0f) : typedArray.getFloat(i8, 0.0f);
                    if (z7) {
                        propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, dimension, i11 == 5 ? typedArray.getDimension(i9, 0.0f) : typedArray.getFloat(i9, 0.0f));
                    } else {
                        propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, dimension);
                    }
                } else {
                    propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, i11 == 5 ? typedArray.getDimension(i9, 0.0f) : typedArray.getFloat(i9, 0.0f));
                }
                propertyValuesHolderOfInt = propertyValuesHolderOfFloat;
            } else if (z6) {
                int dimension2 = i10 == 5 ? (int) typedArray.getDimension(i8, 0.0f) : isColorType(i10) ? typedArray.getColor(i8, 0) : typedArray.getInt(i8, 0);
                if (z7) {
                    propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, dimension2, i11 == 5 ? (int) typedArray.getDimension(i9, 0.0f) : isColorType(i11) ? typedArray.getColor(i9, 0) : typedArray.getInt(i9, 0));
                } else {
                    propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, dimension2);
                }
            } else if (z7) {
                propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, i11 == 5 ? (int) typedArray.getDimension(i9, 0.0f) : isColorType(i11) ? typedArray.getColor(i9, 0) : typedArray.getInt(i9, 0));
            }
            if (propertyValuesHolderOfInt == null || argbEvaluator == null) {
                return propertyValuesHolderOfInt;
            }
            propertyValuesHolderOfInt.setEvaluator(argbEvaluator);
            return propertyValuesHolderOfInt;
        }
        String string = typedArray.getString(i8);
        String string2 = typedArray.getString(i9);
        PathParser.PathDataNode[] pathDataNodeArrCreateNodesFromPathData = PathParser.createNodesFromPathData(string);
        PathParser.PathDataNode[] pathDataNodeArrCreateNodesFromPathData2 = PathParser.createNodesFromPathData(string2);
        if (pathDataNodeArrCreateNodesFromPathData == null && pathDataNodeArrCreateNodesFromPathData2 == null) {
            return null;
        }
        if (pathDataNodeArrCreateNodesFromPathData == null) {
            if (pathDataNodeArrCreateNodesFromPathData2 != null) {
                return PropertyValuesHolder.ofObject(str, new PathDataEvaluator(), pathDataNodeArrCreateNodesFromPathData2);
            }
            return null;
        }
        PathDataEvaluator pathDataEvaluator = new PathDataEvaluator();
        if (pathDataNodeArrCreateNodesFromPathData2 == null) {
            propertyValuesHolderOfObject = PropertyValuesHolder.ofObject(str, pathDataEvaluator, pathDataNodeArrCreateNodesFromPathData);
        } else {
            if (!PathParser.canMorph(pathDataNodeArrCreateNodesFromPathData, pathDataNodeArrCreateNodesFromPathData2)) {
                throw new InflateException(" Can't morph from " + string + " to " + string2);
            }
            propertyValuesHolderOfObject = PropertyValuesHolder.ofObject(str, pathDataEvaluator, pathDataNodeArrCreateNodesFromPathData, pathDataNodeArrCreateNodesFromPathData2);
        }
        return propertyValuesHolderOfObject;
    }

    private static int inferValueTypeFromValues(TypedArray typedArray, int i7, int i8) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i7);
        boolean z6 = typedValuePeekValue != null;
        int i9 = z6 ? typedValuePeekValue.type : 0;
        TypedValue typedValuePeekValue2 = typedArray.peekValue(i8);
        boolean z7 = typedValuePeekValue2 != null;
        return ((z6 && isColorType(i9)) || (z7 && isColorType(z7 ? typedValuePeekValue2.type : 0))) ? 3 : 0;
    }

    private static int inferValueTypeOfKeyframe(Resources resources, Resources.Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_KEYFRAME);
        int i7 = 0;
        TypedValue typedValuePeekNamedValue = TypedArrayUtils.peekNamedValue(typedArrayObtainAttributes, xmlPullParser, "value", 0);
        if ((typedValuePeekNamedValue != null) && isColorType(typedValuePeekNamedValue.type)) {
            i7 = 3;
        }
        typedArrayObtainAttributes.recycle();
        return i7;
    }

    private static boolean isColorType(int i7) {
        return i7 >= 28 && i7 <= 31;
    }

    public static Animator loadAnimator(Context context, @AnimatorRes int i7) {
        return Build.VERSION.SDK_INT >= 24 ? AnimatorInflater.loadAnimator(context, i7) : loadAnimator(context, context.getResources(), context.getTheme(), i7);
    }

    private static Keyframe loadKeyframe(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, int i7, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_KEYFRAME);
        float namedFloat = TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, xmlPullParser, "fraction", 3, -1.0f);
        TypedValue typedValuePeekNamedValue = TypedArrayUtils.peekNamedValue(typedArrayObtainAttributes, xmlPullParser, "value", 0);
        boolean z6 = typedValuePeekNamedValue != null;
        if (i7 == 4) {
            i7 = (z6 && isColorType(typedValuePeekNamedValue.type)) ? 3 : 0;
        }
        Keyframe keyframeOfInt = z6 ? i7 != 0 ? (i7 == 1 || i7 == 3) ? Keyframe.ofInt(namedFloat, TypedArrayUtils.getNamedInt(typedArrayObtainAttributes, xmlPullParser, "value", 0, 0)) : null : Keyframe.ofFloat(namedFloat, TypedArrayUtils.getNamedFloat(typedArrayObtainAttributes, xmlPullParser, "value", 0, 0.0f)) : i7 == 0 ? Keyframe.ofFloat(namedFloat) : Keyframe.ofInt(namedFloat);
        int namedResourceId = TypedArrayUtils.getNamedResourceId(typedArrayObtainAttributes, xmlPullParser, "interpolator", 1, 0);
        if (namedResourceId > 0) {
            keyframeOfInt.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, namedResourceId));
        }
        typedArrayObtainAttributes.recycle();
        return keyframeOfInt;
    }

    private static ObjectAnimator loadObjectAnimator(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, float f7, XmlPullParser xmlPullParser) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        loadAnimator(context, resources, theme, attributeSet, objectAnimator, f7, xmlPullParser);
        return objectAnimator;
    }

    private static PropertyValuesHolder loadPvh(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, String str, int i7) throws XmlPullParserException, IOException {
        int size;
        PropertyValuesHolder propertyValuesHolderOfKeyframe = null;
        ArrayList arrayList = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 3 || next == 1) {
                break;
            }
            if (xmlPullParser.getName().equals("keyframe")) {
                if (i7 == 4) {
                    i7 = inferValueTypeOfKeyframe(resources, theme, Xml.asAttributeSet(xmlPullParser), xmlPullParser);
                }
                Keyframe keyframeLoadKeyframe = loadKeyframe(context, resources, theme, Xml.asAttributeSet(xmlPullParser), i7, xmlPullParser);
                if (keyframeLoadKeyframe != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(keyframeLoadKeyframe);
                }
                xmlPullParser.next();
            }
        }
        if (arrayList != null && (size = arrayList.size()) > 0) {
            Keyframe keyframe = (Keyframe) arrayList.get(0);
            Keyframe keyframe2 = (Keyframe) arrayList.get(size - 1);
            float fraction = keyframe2.getFraction();
            if (fraction < 1.0f) {
                if (fraction < 0.0f) {
                    keyframe2.setFraction(1.0f);
                } else {
                    arrayList.add(arrayList.size(), createNewKeyframe(keyframe2, 1.0f));
                    size++;
                }
            }
            float fraction2 = keyframe.getFraction();
            if (fraction2 != 0.0f) {
                if (fraction2 < 0.0f) {
                    keyframe.setFraction(0.0f);
                } else {
                    arrayList.add(0, createNewKeyframe(keyframe, 0.0f));
                    size++;
                }
            }
            Keyframe[] keyframeArr = new Keyframe[size];
            arrayList.toArray(keyframeArr);
            for (int i8 = 0; i8 < size; i8++) {
                Keyframe keyframe3 = keyframeArr[i8];
                if (keyframe3.getFraction() < 0.0f) {
                    if (i8 == 0) {
                        keyframe3.setFraction(0.0f);
                    } else {
                        int i9 = size - 1;
                        if (i8 == i9) {
                            keyframe3.setFraction(1.0f);
                        } else {
                            int i10 = i8;
                            for (int i11 = i8 + 1; i11 < i9 && keyframeArr[i11].getFraction() < 0.0f; i11++) {
                                i10 = i11;
                            }
                            distributeKeyframes(keyframeArr, keyframeArr[i10 + 1].getFraction() - keyframeArr[i8 - 1].getFraction(), i8, i10);
                        }
                    }
                }
            }
            propertyValuesHolderOfKeyframe = PropertyValuesHolder.ofKeyframe(str, keyframeArr);
            if (i7 == 3) {
                propertyValuesHolderOfKeyframe.setEvaluator(ArgbEvaluator.getInstance());
            }
        }
        return propertyValuesHolderOfKeyframe;
    }

    private static PropertyValuesHolder[] loadValues(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        int i7;
        PropertyValuesHolder[] propertyValuesHolderArr = null;
        ArrayList arrayList = null;
        while (true) {
            int eventType = xmlPullParser.getEventType();
            if (eventType == 3 || eventType == 1) {
                break;
            }
            if (eventType != 2) {
                xmlPullParser.next();
            } else {
                if (xmlPullParser.getName().equals("propertyValuesHolder")) {
                    TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
                    String namedString = TypedArrayUtils.getNamedString(typedArrayObtainAttributes, xmlPullParser, "propertyName", 3);
                    int namedInt = TypedArrayUtils.getNamedInt(typedArrayObtainAttributes, xmlPullParser, "valueType", 2, 4);
                    PropertyValuesHolder propertyValuesHolderLoadPvh = loadPvh(context, resources, theme, xmlPullParser, namedString, namedInt);
                    if (propertyValuesHolderLoadPvh == null) {
                        propertyValuesHolderLoadPvh = getPVH(typedArrayObtainAttributes, namedInt, 0, 1, namedString);
                    }
                    if (propertyValuesHolderLoadPvh != null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(propertyValuesHolderLoadPvh);
                    }
                    typedArrayObtainAttributes.recycle();
                }
                xmlPullParser.next();
            }
        }
        if (arrayList != null) {
            int size = arrayList.size();
            propertyValuesHolderArr = new PropertyValuesHolder[size];
            for (i7 = 0; i7 < size; i7++) {
                propertyValuesHolderArr[i7] = (PropertyValuesHolder) arrayList.get(i7);
            }
        }
        return propertyValuesHolderArr;
    }

    private static void parseAnimatorFromTypeArray(ValueAnimator valueAnimator, TypedArray typedArray, TypedArray typedArray2, float f7, XmlPullParser xmlPullParser) {
        long namedInt = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "duration", 1, IjkMediaCodecInfo.RANK_SECURE);
        long namedInt2 = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "startOffset", 2, 0);
        int namedInt3 = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "valueType", 7, 4);
        if (TypedArrayUtils.hasAttribute(xmlPullParser, "valueFrom") && TypedArrayUtils.hasAttribute(xmlPullParser, "valueTo")) {
            if (namedInt3 == 4) {
                namedInt3 = inferValueTypeFromValues(typedArray, 5, 6);
            }
            PropertyValuesHolder pvh = getPVH(typedArray, namedInt3, 5, 6, "");
            if (pvh != null) {
                valueAnimator.setValues(pvh);
            }
        }
        valueAnimator.setDuration(namedInt);
        valueAnimator.setStartDelay(namedInt2);
        valueAnimator.setRepeatCount(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "repeatCount", 3, 0));
        valueAnimator.setRepeatMode(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "repeatMode", 4, 1));
        if (typedArray2 != null) {
            setupObjectAnimator(valueAnimator, typedArray2, namedInt3, f7, xmlPullParser);
        }
    }

    private static void setupObjectAnimator(ValueAnimator valueAnimator, TypedArray typedArray, int i7, float f7, XmlPullParser xmlPullParser) {
        ObjectAnimator objectAnimator = (ObjectAnimator) valueAnimator;
        String namedString = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "pathData", 1);
        if (namedString == null) {
            objectAnimator.setPropertyName(TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyName", 0));
            return;
        }
        String namedString2 = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyXName", 2);
        String namedString3 = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyYName", 3);
        if (i7 != 2) {
        }
        if (namedString2 != null || namedString3 != null) {
            setupPathMotion(PathParser.createPathFromPathData(namedString), objectAnimator, f7 * 0.5f, namedString2, namedString3);
            return;
        }
        throw new InflateException(typedArray.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
    }

    private static void setupPathMotion(Path path, ObjectAnimator objectAnimator, float f7, String str, String str2) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        ArrayList arrayList = new ArrayList();
        float f8 = 0.0f;
        arrayList.add(Float.valueOf(0.0f));
        float length = 0.0f;
        do {
            length += pathMeasure.getLength();
            arrayList.add(Float.valueOf(length));
        } while (pathMeasure.nextContour());
        PathMeasure pathMeasure2 = new PathMeasure(path, false);
        int iMin = Math.min(100, ((int) (length / f7)) + 1);
        float[] fArr = new float[iMin];
        float[] fArr2 = new float[iMin];
        float[] fArr3 = new float[2];
        float f9 = length / (iMin - 1);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            if (i7 >= iMin) {
                break;
            }
            pathMeasure2.getPosTan(f8 - ((Float) arrayList.get(i8)).floatValue(), fArr3, null);
            fArr[i7] = fArr3[0];
            fArr2[i7] = fArr3[1];
            f8 += f9;
            int i9 = i8 + 1;
            if (i9 < arrayList.size() && f8 > ((Float) arrayList.get(i9)).floatValue()) {
                pathMeasure2.nextContour();
                i8 = i9;
            }
            i7++;
        }
        PropertyValuesHolder propertyValuesHolderOfFloat = str != null ? PropertyValuesHolder.ofFloat(str, fArr) : null;
        PropertyValuesHolder propertyValuesHolderOfFloat2 = str2 != null ? PropertyValuesHolder.ofFloat(str2, fArr2) : null;
        if (propertyValuesHolderOfFloat == null) {
            objectAnimator.setValues(propertyValuesHolderOfFloat2);
        } else if (propertyValuesHolderOfFloat2 == null) {
            objectAnimator.setValues(propertyValuesHolderOfFloat);
        } else {
            objectAnimator.setValues(propertyValuesHolderOfFloat, propertyValuesHolderOfFloat2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00db, code lost:
    
        if (r23 == null) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00dd, code lost:
    
        if (r13 == null) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00df, code lost:
    
        r1 = new android.animation.Animator[r13.size()];
        r2 = r13.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ed, code lost:
    
        if (r2.hasNext() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ef, code lost:
    
        r1[r14] = (android.animation.Animator) r2.next();
        r14 = r14 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00fb, code lost:
    
        if (r24 != 0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00fd, code lost:
    
        r23.playTogether(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0101, code lost:
    
        r23.playSequentially(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0104, code lost:
    
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.animation.Animator createAnimatorFromXml(android.content.Context r18, android.content.res.Resources r19, android.content.res.Resources.Theme r20, org.xmlpull.v1.XmlPullParser r21, android.util.AttributeSet r22, android.animation.AnimatorSet r23, int r24, float r25) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimatorInflaterCompat.createAnimatorFromXml(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.animation.AnimatorSet, int, float):android.animation.Animator");
    }

    public static Animator loadAnimator(Context context, Resources resources, Resources.Theme theme, @AnimatorRes int i7) {
        return loadAnimator(context, resources, theme, i7, 1.0f);
    }

    public static Animator loadAnimator(Context context, Resources resources, Resources.Theme theme, @AnimatorRes int i7, float f7) {
        XmlResourceParser animation = null;
        try {
            try {
                try {
                    animation = resources.getAnimation(i7);
                    return createAnimatorFromXml(context, resources, theme, animation, f7);
                } catch (IOException e7) {
                    Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i7));
                    notFoundException.initCause(e7);
                    throw notFoundException;
                }
            } catch (XmlPullParserException e8) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i7));
                notFoundException2.initCause(e8);
                throw notFoundException2;
            }
        } finally {
            if (animation != null) {
                animation.close();
            }
        }
    }

    private static ValueAnimator loadAnimator(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, ValueAnimator valueAnimator, float f7, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_ANIMATOR);
        TypedArray typedArrayObtainAttributes2 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
        if (valueAnimator == null) {
            valueAnimator = new ValueAnimator();
        }
        parseAnimatorFromTypeArray(valueAnimator, typedArrayObtainAttributes, typedArrayObtainAttributes2, f7, xmlPullParser);
        int namedResourceId = TypedArrayUtils.getNamedResourceId(typedArrayObtainAttributes, xmlPullParser, "interpolator", 0, 0);
        if (namedResourceId > 0) {
            valueAnimator.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, namedResourceId));
        }
        typedArrayObtainAttributes.recycle();
        if (typedArrayObtainAttributes2 != null) {
            typedArrayObtainAttributes2.recycle();
        }
        return valueAnimator;
    }
}
