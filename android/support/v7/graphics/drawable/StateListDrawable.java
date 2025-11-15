package android.support.v7.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.appcompat.C0308R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.graphics.drawable.DrawableContainer;
import android.util.AttributeSet;
import android.util.StateSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
class StateListDrawable extends DrawableContainer {
    private static final boolean DEBUG = false;
    private static final String TAG = "StateListDrawable";
    private boolean mMutated;
    private StateListState mStateListState;

    public static class StateListState extends DrawableContainer.DrawableContainerState {
        public int[][] mStateSets;

        public StateListState(StateListState stateListState, StateListDrawable stateListDrawable, Resources resources) {
            super(stateListState, stateListDrawable, resources);
            if (stateListState != null) {
                this.mStateSets = stateListState.mStateSets;
            } else {
                this.mStateSets = new int[getCapacity()][];
            }
        }

        public int addStateSet(int[] iArr, Drawable drawable) {
            int iAddChild = addChild(drawable);
            this.mStateSets[iAddChild] = iArr;
            return iAddChild;
        }

        @Override // android.support.v7.graphics.drawable.DrawableContainer.DrawableContainerState
        public void growArray(int i7, int i8) {
            super.growArray(i7, i8);
            int[][] iArr = new int[i8][];
            System.arraycopy(this.mStateSets, 0, iArr, 0, i7);
            this.mStateSets = iArr;
        }

        public int indexOfStateSet(int[] iArr) {
            int[][] iArr2 = this.mStateSets;
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                if (StateSet.stateSetMatches(iArr2[i7], iArr)) {
                    return i7;
                }
            }
            return -1;
        }

        @Override // android.support.v7.graphics.drawable.DrawableContainer.DrawableContainerState
        public void mutate() {
            int[][] iArr = this.mStateSets;
            int[][] iArr2 = new int[iArr.length][];
            for (int length = iArr.length - 1; length >= 0; length--) {
                int[][] iArr3 = this.mStateSets;
                iArr2[length] = iArr3[length] != null ? (int[]) iArr3[length].clone() : null;
            }
            this.mStateSets = iArr2;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new StateListDrawable(this, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable(Resources resources) {
            return new StateListDrawable(this, resources);
        }
    }

    public StateListDrawable() {
        this(null, null);
    }

    private void inflateChildElements(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        StateListState stateListState = this.mStateListState;
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next2 = xmlPullParser.next();
            if (next2 == 1) {
                return;
            }
            int depth2 = xmlPullParser.getDepth();
            if (depth2 < depth && next2 == 3) {
                return;
            }
            if (next2 == 2 && depth2 <= depth && xmlPullParser.getName().equals("item")) {
                TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, C0308R.styleable.StateListDrawableItem);
                int resourceId = typedArrayObtainAttributes.getResourceId(C0308R.styleable.StateListDrawableItem_android_drawable, -1);
                Drawable drawable = resourceId > 0 ? AppCompatResources.getDrawable(context, resourceId) : null;
                typedArrayObtainAttributes.recycle();
                int[] iArrExtractStateSet = extractStateSet(attributeSet);
                if (drawable == null) {
                    do {
                        next = xmlPullParser.next();
                    } while (next == 4);
                    if (next != 2) {
                        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
                    }
                    drawable = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
                }
                stateListState.addStateSet(iArrExtractStateSet, drawable);
            }
        }
    }

    private void updateStateFromTypedArray(TypedArray typedArray) {
        StateListState stateListState = this.mStateListState;
        stateListState.mChangingConfigurations |= typedArray.getChangingConfigurations();
        stateListState.mVariablePadding = typedArray.getBoolean(C0308R.styleable.StateListDrawable_android_variablePadding, stateListState.mVariablePadding);
        stateListState.mConstantSize = typedArray.getBoolean(C0308R.styleable.StateListDrawable_android_constantSize, stateListState.mConstantSize);
        stateListState.mEnterFadeDuration = typedArray.getInt(C0308R.styleable.StateListDrawable_android_enterFadeDuration, stateListState.mEnterFadeDuration);
        stateListState.mExitFadeDuration = typedArray.getInt(C0308R.styleable.StateListDrawable_android_exitFadeDuration, stateListState.mExitFadeDuration);
        stateListState.mDither = typedArray.getBoolean(C0308R.styleable.StateListDrawable_android_dither, stateListState.mDither);
    }

    public void addState(int[] iArr, Drawable drawable) {
        if (drawable != null) {
            this.mStateListState.addStateSet(iArr, drawable);
            onStateChange(getState());
        }
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    @RequiresApi(21)
    public void applyTheme(@NonNull Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer
    public void clearMutated() {
        super.clearMutated();
        this.mMutated = false;
    }

    public int[] extractStateSet(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        int[] iArr = new int[attributeCount];
        int i7 = 0;
        for (int i8 = 0; i8 < attributeCount; i8++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i8);
            if (attributeNameResource != 0 && attributeNameResource != 16842960 && attributeNameResource != 16843161) {
                int i9 = i7 + 1;
                if (!attributeSet.getAttributeBooleanValue(i8, false)) {
                    attributeNameResource = -attributeNameResource;
                }
                iArr[i7] = attributeNameResource;
                i7 = i9;
            }
        }
        return StateSet.trimStateSet(iArr, i7);
    }

    public int getStateCount() {
        return this.mStateListState.getChildCount();
    }

    public Drawable getStateDrawable(int i7) {
        return this.mStateListState.getChild(i7);
    }

    public int getStateDrawableIndex(int[] iArr) {
        return this.mStateListState.indexOfStateSet(iArr);
    }

    public StateListState getStateListState() {
        return this.mStateListState;
    }

    public int[] getStateSet(int i7) {
        return this.mStateListState.mStateSets[i7];
    }

    public void inflate(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, C0308R.styleable.StateListDrawable);
        setVisible(typedArrayObtainAttributes.getBoolean(C0308R.styleable.StateListDrawable_android_visible, true), true);
        updateStateFromTypedArray(typedArrayObtainAttributes);
        updateDensity(resources);
        typedArrayObtainAttributes.recycle();
        inflateChildElements(context, resources, xmlPullParser, attributeSet, theme);
        onStateChange(getState());
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    @NonNull
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mStateListState.mutate();
            this.mMutated = true;
        }
        return this;
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        int iIndexOfStateSet = this.mStateListState.indexOfStateSet(iArr);
        if (iIndexOfStateSet < 0) {
            iIndexOfStateSet = this.mStateListState.indexOfStateSet(StateSet.WILD_CARD);
        }
        return selectDrawable(iIndexOfStateSet) || zOnStateChange;
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer
    public void setConstantState(@NonNull DrawableContainer.DrawableContainerState drawableContainerState) {
        super.setConstantState(drawableContainerState);
        if (drawableContainerState instanceof StateListState) {
            this.mStateListState = (StateListState) drawableContainerState;
        }
    }

    public StateListDrawable(StateListState stateListState, Resources resources) {
        setConstantState(new StateListState(stateListState, this, resources));
        onStateChange(getState());
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer
    public StateListState cloneConstantState() {
        return new StateListState(this.mStateListState, this, null);
    }

    public StateListDrawable(@Nullable StateListState stateListState) {
        if (stateListState != null) {
            setConstantState(stateListState);
        }
    }
}
