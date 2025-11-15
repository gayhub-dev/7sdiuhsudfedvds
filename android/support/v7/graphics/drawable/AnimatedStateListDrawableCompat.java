package android.support.v7.graphics.drawable;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.appcompat.C0308R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.graphics.drawable.DrawableContainer;
import android.support.v7.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class AnimatedStateListDrawableCompat extends StateListDrawable {
    private static final String ELEMENT_ITEM = "item";
    private static final String ELEMENT_TRANSITION = "transition";
    private static final String ITEM_MISSING_DRAWABLE_ERROR = ": <item> tag requires a 'drawable' attribute or child tag defining a drawable";
    private static final String LOGTAG = "AnimatedStateListDrawableCompat";
    private static final String TRANSITION_MISSING_DRAWABLE_ERROR = ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable";
    private static final String TRANSITION_MISSING_FROM_TO_ID = ": <transition> tag requires 'fromId' & 'toId' attributes";
    private boolean mMutated;
    private AnimatedStateListState mState;
    private Transition mTransition;
    private int mTransitionFromIndex;
    private int mTransitionToIndex;

    public static class AnimatableTransition extends Transition {

        /* renamed from: mA */
        private final Animatable f166mA;

        public AnimatableTransition(Animatable animatable) {
            super();
            this.f166mA = animatable;
        }

        @Override // android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void start() {
            this.f166mA.start();
        }

        @Override // android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void stop() {
            this.f166mA.stop();
        }
    }

    public static class AnimatedStateListState extends StateListDrawable.StateListState {
        private static final long REVERSED_BIT = 4294967296L;
        private static final long REVERSIBLE_FLAG_BIT = 8589934592L;
        public SparseArrayCompat<Integer> mStateIds;
        public LongSparseArray<Long> mTransitions;

        public AnimatedStateListState(@Nullable AnimatedStateListState animatedStateListState, @NonNull AnimatedStateListDrawableCompat animatedStateListDrawableCompat, @Nullable Resources resources) {
            super(animatedStateListState, animatedStateListDrawableCompat, resources);
            if (animatedStateListState != null) {
                this.mTransitions = animatedStateListState.mTransitions;
                this.mStateIds = animatedStateListState.mStateIds;
            } else {
                this.mTransitions = new LongSparseArray<>();
                this.mStateIds = new SparseArrayCompat<>();
            }
        }

        private static long generateTransitionKey(int i7, int i8) {
            return i8 | (i7 << 32);
        }

        public int addStateSet(@NonNull int[] iArr, @NonNull Drawable drawable, int i7) {
            int iAddStateSet = super.addStateSet(iArr, drawable);
            this.mStateIds.put(iAddStateSet, Integer.valueOf(i7));
            return iAddStateSet;
        }

        public int addTransition(int i7, int i8, @NonNull Drawable drawable, boolean z6) {
            int iAddChild = super.addChild(drawable);
            long jGenerateTransitionKey = generateTransitionKey(i7, i8);
            long j7 = z6 ? 8589934592L : 0L;
            long j8 = iAddChild;
            this.mTransitions.append(jGenerateTransitionKey, Long.valueOf(j8 | j7));
            if (z6) {
                this.mTransitions.append(generateTransitionKey(i8, i7), Long.valueOf(4294967296L | j8 | j7));
            }
            return iAddChild;
        }

        public int getKeyframeIdAt(int i7) {
            if (i7 < 0) {
                return 0;
            }
            return this.mStateIds.get(i7, 0).intValue();
        }

        public int indexOfKeyframe(@NonNull int[] iArr) {
            int iIndexOfStateSet = super.indexOfStateSet(iArr);
            return iIndexOfStateSet >= 0 ? iIndexOfStateSet : super.indexOfStateSet(StateSet.WILD_CARD);
        }

        public int indexOfTransition(int i7, int i8) {
            return (int) this.mTransitions.get(generateTransitionKey(i7, i8), -1L).longValue();
        }

        public boolean isTransitionReversed(int i7, int i8) {
            return (this.mTransitions.get(generateTransitionKey(i7, i8), -1L).longValue() & 4294967296L) != 0;
        }

        @Override // android.support.v7.graphics.drawable.StateListDrawable.StateListState, android.support.v7.graphics.drawable.DrawableContainer.DrawableContainerState
        public void mutate() {
            this.mTransitions = this.mTransitions.m2606clone();
            this.mStateIds = this.mStateIds.m2607clone();
        }

        @Override // android.support.v7.graphics.drawable.StateListDrawable.StateListState, android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new AnimatedStateListDrawableCompat(this, null);
        }

        public boolean transitionHasReversibleFlag(int i7, int i8) {
            return (this.mTransitions.get(generateTransitionKey(i7, i8), -1L).longValue() & 8589934592L) != 0;
        }

        @Override // android.support.v7.graphics.drawable.StateListDrawable.StateListState, android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable(Resources resources) {
            return new AnimatedStateListDrawableCompat(this, resources);
        }
    }

    public static class AnimatedVectorDrawableTransition extends Transition {
        private final AnimatedVectorDrawableCompat mAvd;

        public AnimatedVectorDrawableTransition(AnimatedVectorDrawableCompat animatedVectorDrawableCompat) {
            super();
            this.mAvd = animatedVectorDrawableCompat;
        }

        @Override // android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void start() {
            this.mAvd.start();
        }

        @Override // android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void stop() {
            this.mAvd.stop();
        }
    }

    public static class AnimationDrawableTransition extends Transition {
        private final ObjectAnimator mAnim;
        private final boolean mHasReversibleFlag;

        public AnimationDrawableTransition(AnimationDrawable animationDrawable, boolean z6, boolean z7) {
            super();
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i7 = z6 ? numberOfFrames - 1 : 0;
            int i8 = z6 ? 0 : numberOfFrames - 1;
            FrameInterpolator frameInterpolator = new FrameInterpolator(animationDrawable, z6);
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i7, i8);
            objectAnimatorOfInt.setAutoCancel(true);
            objectAnimatorOfInt.setDuration(frameInterpolator.getTotalDuration());
            objectAnimatorOfInt.setInterpolator(frameInterpolator);
            this.mHasReversibleFlag = z7;
            this.mAnim = objectAnimatorOfInt;
        }

        @Override // android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public boolean canReverse() {
            return this.mHasReversibleFlag;
        }

        @Override // android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void reverse() {
            this.mAnim.reverse();
        }

        @Override // android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void start() {
            this.mAnim.start();
        }

        @Override // android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void stop() {
            this.mAnim.cancel();
        }
    }

    public static class FrameInterpolator implements TimeInterpolator {
        private int[] mFrameTimes;
        private int mFrames;
        private int mTotalDuration;

        public FrameInterpolator(AnimationDrawable animationDrawable, boolean z6) {
            updateFrames(animationDrawable, z6);
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f7) {
            int i7 = (int) ((f7 * this.mTotalDuration) + 0.5f);
            int i8 = this.mFrames;
            int[] iArr = this.mFrameTimes;
            int i9 = 0;
            while (i9 < i8 && i7 >= iArr[i9]) {
                i7 -= iArr[i9];
                i9++;
            }
            return (i9 / i8) + (i9 < i8 ? i7 / this.mTotalDuration : 0.0f);
        }

        public int getTotalDuration() {
            return this.mTotalDuration;
        }

        public int updateFrames(AnimationDrawable animationDrawable, boolean z6) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.mFrames = numberOfFrames;
            int[] iArr = this.mFrameTimes;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.mFrameTimes = new int[numberOfFrames];
            }
            int[] iArr2 = this.mFrameTimes;
            int i7 = 0;
            for (int i8 = 0; i8 < numberOfFrames; i8++) {
                int duration = animationDrawable.getDuration(z6 ? (numberOfFrames - i8) - 1 : i8);
                iArr2[i8] = duration;
                i7 += duration;
            }
            this.mTotalDuration = i7;
            return i7;
        }
    }

    public static abstract class Transition {
        private Transition() {
        }

        public boolean canReverse() {
            return false;
        }

        public void reverse() {
        }

        public abstract void start();

        public abstract void stop();
    }

    public AnimatedStateListDrawableCompat() {
        this(null, null);
    }

    @Nullable
    public static AnimatedStateListDrawableCompat create(@NonNull Context context, @DrawableRes int i7, @Nullable Resources.Theme theme) throws XmlPullParserException, Resources.NotFoundException, IOException {
        int next;
        try {
            Resources resources = context.getResources();
            XmlResourceParser xml = resources.getXml(i7);
            AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next == 2) {
                return createFromXmlInner(context, resources, xml, attributeSetAsAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException | XmlPullParserException unused) {
            return null;
        }
    }

    public static AnimatedStateListDrawableCompat createFromXmlInner(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (name.equals("animated-selector")) {
            AnimatedStateListDrawableCompat animatedStateListDrawableCompat = new AnimatedStateListDrawableCompat();
            animatedStateListDrawableCompat.inflate(context, resources, xmlPullParser, attributeSet, theme);
            return animatedStateListDrawableCompat;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid animated-selector tag " + name);
    }

    private void inflateChildElements(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            int depth2 = xmlPullParser.getDepth();
            if (depth2 < depth && next == 3) {
                return;
            }
            if (next == 2 && depth2 <= depth) {
                if (xmlPullParser.getName().equals(ELEMENT_ITEM)) {
                    parseItem(context, resources, xmlPullParser, attributeSet, theme);
                } else if (xmlPullParser.getName().equals(ELEMENT_TRANSITION)) {
                    parseTransition(context, resources, xmlPullParser, attributeSet, theme);
                }
            }
        }
    }

    private void init() {
        onStateChange(getState());
    }

    private int parseItem(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, C0308R.styleable.AnimatedStateListDrawableItem);
        int resourceId = typedArrayObtainAttributes.getResourceId(C0308R.styleable.AnimatedStateListDrawableItem_android_id, 0);
        int resourceId2 = typedArrayObtainAttributes.getResourceId(C0308R.styleable.AnimatedStateListDrawableItem_android_drawable, -1);
        Drawable drawable = resourceId2 > 0 ? AppCompatResources.getDrawable(context, resourceId2) : null;
        typedArrayObtainAttributes.recycle();
        int[] iArrExtractStateSet = extractStateSet(attributeSet);
        if (drawable == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ITEM_MISSING_DRAWABLE_ERROR);
            }
            drawable = xmlPullParser.getName().equals("vector") ? VectorDrawableCompat.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
        }
        if (drawable != null) {
            return this.mState.addStateSet(iArrExtractStateSet, drawable, resourceId);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ITEM_MISSING_DRAWABLE_ERROR);
    }

    private int parseTransition(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, C0308R.styleable.AnimatedStateListDrawableTransition);
        int resourceId = typedArrayObtainAttributes.getResourceId(C0308R.styleable.AnimatedStateListDrawableTransition_android_fromId, -1);
        int resourceId2 = typedArrayObtainAttributes.getResourceId(C0308R.styleable.AnimatedStateListDrawableTransition_android_toId, -1);
        int resourceId3 = typedArrayObtainAttributes.getResourceId(C0308R.styleable.AnimatedStateListDrawableTransition_android_drawable, -1);
        Drawable drawable = resourceId3 > 0 ? AppCompatResources.getDrawable(context, resourceId3) : null;
        boolean z6 = typedArrayObtainAttributes.getBoolean(C0308R.styleable.AnimatedStateListDrawableTransition_android_reversible, false);
        typedArrayObtainAttributes.recycle();
        if (drawable == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + TRANSITION_MISSING_DRAWABLE_ERROR);
            }
            drawable = xmlPullParser.getName().equals("animated-vector") ? AnimatedVectorDrawableCompat.createFromXmlInner(context, resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
        }
        if (drawable == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + TRANSITION_MISSING_DRAWABLE_ERROR);
        }
        if (resourceId != -1 && resourceId2 != -1) {
            return this.mState.addTransition(resourceId, resourceId2, drawable, z6);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + TRANSITION_MISSING_FROM_TO_ID);
    }

    private boolean selectTransition(int i7) {
        int currentIndex;
        int iIndexOfTransition;
        Transition animatableTransition;
        Transition transition = this.mTransition;
        if (transition == null) {
            currentIndex = getCurrentIndex();
        } else {
            if (i7 == this.mTransitionToIndex) {
                return true;
            }
            if (i7 == this.mTransitionFromIndex && transition.canReverse()) {
                transition.reverse();
                this.mTransitionToIndex = this.mTransitionFromIndex;
                this.mTransitionFromIndex = i7;
                return true;
            }
            currentIndex = this.mTransitionToIndex;
            transition.stop();
        }
        this.mTransition = null;
        this.mTransitionFromIndex = -1;
        this.mTransitionToIndex = -1;
        AnimatedStateListState animatedStateListState = this.mState;
        int keyframeIdAt = animatedStateListState.getKeyframeIdAt(currentIndex);
        int keyframeIdAt2 = animatedStateListState.getKeyframeIdAt(i7);
        if (keyframeIdAt2 == 0 || keyframeIdAt == 0 || (iIndexOfTransition = animatedStateListState.indexOfTransition(keyframeIdAt, keyframeIdAt2)) < 0) {
            return false;
        }
        boolean zTransitionHasReversibleFlag = animatedStateListState.transitionHasReversibleFlag(keyframeIdAt, keyframeIdAt2);
        selectDrawable(iIndexOfTransition);
        Object current = getCurrent();
        if (current instanceof AnimationDrawable) {
            animatableTransition = new AnimationDrawableTransition((AnimationDrawable) current, animatedStateListState.isTransitionReversed(keyframeIdAt, keyframeIdAt2), zTransitionHasReversibleFlag);
        } else {
            if (!(current instanceof AnimatedVectorDrawableCompat)) {
                if (current instanceof Animatable) {
                    animatableTransition = new AnimatableTransition((Animatable) current);
                }
                return false;
            }
            animatableTransition = new AnimatedVectorDrawableTransition((AnimatedVectorDrawableCompat) current);
        }
        animatableTransition.start();
        this.mTransition = animatableTransition;
        this.mTransitionFromIndex = currentIndex;
        this.mTransitionToIndex = i7;
        return true;
    }

    private void updateStateFromTypedArray(TypedArray typedArray) {
        AnimatedStateListState animatedStateListState = this.mState;
        animatedStateListState.mChangingConfigurations |= typedArray.getChangingConfigurations();
        animatedStateListState.setVariablePadding(typedArray.getBoolean(C0308R.styleable.AnimatedStateListDrawableCompat_android_variablePadding, animatedStateListState.mVariablePadding));
        animatedStateListState.setConstantSize(typedArray.getBoolean(C0308R.styleable.AnimatedStateListDrawableCompat_android_constantSize, animatedStateListState.mConstantSize));
        animatedStateListState.setEnterFadeDuration(typedArray.getInt(C0308R.styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, animatedStateListState.mEnterFadeDuration));
        animatedStateListState.setExitFadeDuration(typedArray.getInt(C0308R.styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, animatedStateListState.mExitFadeDuration));
        setDither(typedArray.getBoolean(C0308R.styleable.AnimatedStateListDrawableCompat_android_dither, animatedStateListState.mDither));
    }

    @Override // android.support.v7.graphics.drawable.StateListDrawable
    public /* bridge */ /* synthetic */ void addState(int[] iArr, Drawable drawable) {
        super.addState(iArr, drawable);
    }

    public <T extends Drawable & Animatable> void addTransition(int i7, int i8, @NonNull T t6, boolean z6) {
        if (t6 == null) {
            throw new IllegalArgumentException("Transition drawable must not be null");
        }
        this.mState.addTransition(i7, i8, t6, z6);
    }

    @Override // android.support.v7.graphics.drawable.StateListDrawable, android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    @RequiresApi(21)
    public /* bridge */ /* synthetic */ void applyTheme(@NonNull Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    @RequiresApi(21)
    public /* bridge */ /* synthetic */ boolean canApplyTheme() {
        return super.canApplyTheme();
    }

    @Override // android.support.v7.graphics.drawable.StateListDrawable, android.support.v7.graphics.drawable.DrawableContainer
    public void clearMutated() {
        super.clearMutated();
        this.mMutated = false;
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    @NonNull
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void getHotspotBounds(@NonNull Rect rect) {
        super.getHotspotBounds(rect);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    @RequiresApi(21)
    public /* bridge */ /* synthetic */ void getOutline(@NonNull Outline outline) {
        super.getOutline(outline);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(@NonNull Rect rect) {
        return super.getPadding(rect);
    }

    @Override // android.support.v7.graphics.drawable.StateListDrawable
    public void inflate(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, C0308R.styleable.AnimatedStateListDrawableCompat);
        setVisible(typedArrayObtainAttributes.getBoolean(C0308R.styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
        updateStateFromTypedArray(typedArrayObtainAttributes);
        updateDensity(resources);
        typedArrayObtainAttributes.recycle();
        inflateChildElements(context, resources, xmlPullParser, attributeSet, theme);
        init();
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void invalidateDrawable(@NonNull Drawable drawable) {
        super.invalidateDrawable(drawable);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    @Override // android.support.v7.graphics.drawable.StateListDrawable, android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        Transition transition = this.mTransition;
        if (transition != null) {
            transition.stop();
            this.mTransition = null;
            selectDrawable(this.mTransitionToIndex);
            this.mTransitionToIndex = -1;
            this.mTransitionFromIndex = -1;
        }
    }

    @Override // android.support.v7.graphics.drawable.StateListDrawable, android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mState.mutate();
            this.mMutated = true;
        }
        return this;
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean onLayoutDirectionChanged(int i7) {
        return super.onLayoutDirectionChanged(i7);
    }

    @Override // android.support.v7.graphics.drawable.StateListDrawable, android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        int iIndexOfKeyframe = this.mState.indexOfKeyframe(iArr);
        boolean z6 = iIndexOfKeyframe != getCurrentIndex() && (selectTransition(iIndexOfKeyframe) || selectDrawable(iIndexOfKeyframe));
        Drawable current = getCurrent();
        return current != null ? z6 | current.setState(iArr) : z6;
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j7) {
        super.scheduleDrawable(drawable, runnable, j7);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int i7) {
        super.setAlpha(i7);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z6) {
        super.setAutoMirrored(z6);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override // android.support.v7.graphics.drawable.StateListDrawable, android.support.v7.graphics.drawable.DrawableContainer
    public void setConstantState(@NonNull DrawableContainer.DrawableContainerState drawableContainerState) {
        super.setConstantState(drawableContainerState);
        if (drawableContainerState instanceof AnimatedStateListState) {
            this.mState = (AnimatedStateListState) drawableContainerState;
        }
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setDither(boolean z6) {
        super.setDither(z6);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer
    public /* bridge */ /* synthetic */ void setEnterFadeDuration(int i7) {
        super.setEnterFadeDuration(i7);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer
    public /* bridge */ /* synthetic */ void setExitFadeDuration(int i7) {
        super.setExitFadeDuration(i7);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f7, float f8) {
        super.setHotspot(f7, f8);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i7, int i8, int i9, int i10) {
        super.setHotspotBounds(i7, i8, i9, i10);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setTintMode(@NonNull PorterDuff.Mode mode) {
        super.setTintMode(mode);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean setVisible(boolean z6, boolean z7) {
        boolean visible = super.setVisible(z6, z7);
        Transition transition = this.mTransition;
        if (transition != null && (visible || z7)) {
            if (z6) {
                transition.start();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        super.unscheduleDrawable(drawable, runnable);
    }

    public AnimatedStateListDrawableCompat(@Nullable AnimatedStateListState animatedStateListState, @Nullable Resources resources) {
        super(null);
        this.mTransitionToIndex = -1;
        this.mTransitionFromIndex = -1;
        setConstantState(new AnimatedStateListState(animatedStateListState, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }

    public void addState(@NonNull int[] iArr, @NonNull Drawable drawable, int i7) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable must not be null");
        }
        this.mState.addStateSet(iArr, drawable, i7);
        onStateChange(getState());
    }

    @Override // android.support.v7.graphics.drawable.StateListDrawable, android.support.v7.graphics.drawable.DrawableContainer
    public AnimatedStateListState cloneConstantState() {
        return new AnimatedStateListState(this.mState, this, null);
    }
}
