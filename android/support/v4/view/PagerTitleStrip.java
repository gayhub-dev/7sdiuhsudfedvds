package android.support.v4.view;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Locale;

@ViewPager.DecorView
/* loaded from: classes.dex */
public class PagerTitleStrip extends ViewGroup {
    private static final float SIDE_ALPHA = 0.6f;
    private static final int TEXT_SPACING = 16;
    public TextView mCurrText;
    private int mGravity;
    private int mLastKnownCurrentPage;
    public float mLastKnownPositionOffset;
    public TextView mNextText;
    private int mNonPrimaryAlpha;
    private final PageListener mPageListener;
    public ViewPager mPager;
    public TextView mPrevText;
    private int mScaledTextSpacing;
    public int mTextColor;
    private boolean mUpdatingPositions;
    private boolean mUpdatingText;
    private WeakReference<PagerAdapter> mWatchingAdapter;
    private static final int[] ATTRS = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};
    private static final int[] TEXT_ATTRS = {R.attr.textAllCaps};

    public class PageListener extends DataSetObserver implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
        private int mScrollState;

        public PageListener() {
        }

        @Override // android.support.v4.view.ViewPager.OnAdapterChangeListener
        public void onAdapterChanged(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
            PagerTitleStrip.this.updateAdapter(pagerAdapter, pagerAdapter2);
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.updateText(pagerTitleStrip.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
            PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
            float f7 = pagerTitleStrip2.mLastKnownPositionOffset;
            if (f7 < 0.0f) {
                f7 = 0.0f;
            }
            pagerTitleStrip2.updateTextPositions(pagerTitleStrip2.mPager.getCurrentItem(), f7, true);
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i7) {
            this.mScrollState = i7;
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i7, float f7, int i8) {
            if (f7 > 0.5f) {
                i7++;
            }
            PagerTitleStrip.this.updateTextPositions(i7, f7, false);
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i7) {
            if (this.mScrollState == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.updateText(pagerTitleStrip.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
                PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
                float f7 = pagerTitleStrip2.mLastKnownPositionOffset;
                if (f7 < 0.0f) {
                    f7 = 0.0f;
                }
                pagerTitleStrip2.updateTextPositions(pagerTitleStrip2.mPager.getCurrentItem(), f7, true);
            }
        }
    }

    public static class SingleLineAllCapsTransform extends SingleLineTransformationMethod {
        private Locale mLocale;

        public SingleLineAllCapsTransform(Context context) {
            this.mLocale = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.mLocale);
            }
            return null;
        }
    }

    public PagerTitleStrip(@NonNull Context context) {
        this(context, null);
    }

    private static void setSingleLineAllCaps(TextView textView) {
        textView.setTransformationMethod(new SingleLineAllCapsTransform(textView.getContext()));
    }

    public int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.mScaledTextSpacing;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        PagerAdapter adapter = viewPager.getAdapter();
        viewPager.setInternalPageChangeListener(this.mPageListener);
        viewPager.addOnAdapterChangeListener(this.mPageListener);
        this.mPager = viewPager;
        WeakReference<PagerAdapter> weakReference = this.mWatchingAdapter;
        updateAdapter(weakReference != null ? weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.mPager;
        if (viewPager != null) {
            updateAdapter(viewPager.getAdapter(), null);
            this.mPager.setInternalPageChangeListener(null);
            this.mPager.removeOnAdapterChangeListener(this.mPageListener);
            this.mPager = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        if (this.mPager != null) {
            float f7 = this.mLastKnownPositionOffset;
            if (f7 < 0.0f) {
                f7 = 0.0f;
            }
            updateTextPositions(this.mLastKnownCurrentPage, f7, true);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i7, int i8) {
        int iMax;
        if (View.MeasureSpec.getMode(i7) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i8, paddingBottom, -2);
        int size = View.MeasureSpec.getSize(i7);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i7, (int) (size * 0.2f), -2);
        this.mPrevText.measure(childMeasureSpec2, childMeasureSpec);
        this.mCurrText.measure(childMeasureSpec2, childMeasureSpec);
        this.mNextText.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i8) == 1073741824) {
            iMax = View.MeasureSpec.getSize(i8);
        } else {
            iMax = Math.max(getMinHeight(), this.mCurrText.getMeasuredHeight() + paddingBottom);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(iMax, i8, this.mCurrText.getMeasuredState() << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mUpdatingText) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i7) {
        this.mGravity = i7;
        requestLayout();
    }

    public void setNonPrimaryAlpha(@FloatRange(from = 0.0d, m90to = 1.0d) float f7) {
        int i7 = ((int) (f7 * 255.0f)) & 255;
        this.mNonPrimaryAlpha = i7;
        int i8 = (i7 << 24) | (this.mTextColor & ViewCompat.MEASURED_SIZE_MASK);
        this.mPrevText.setTextColor(i8);
        this.mNextText.setTextColor(i8);
    }

    public void setTextColor(@ColorInt int i7) {
        this.mTextColor = i7;
        this.mCurrText.setTextColor(i7);
        int i8 = (this.mNonPrimaryAlpha << 24) | (this.mTextColor & ViewCompat.MEASURED_SIZE_MASK);
        this.mPrevText.setTextColor(i8);
        this.mNextText.setTextColor(i8);
    }

    public void setTextSize(int i7, float f7) {
        this.mPrevText.setTextSize(i7, f7);
        this.mCurrText.setTextSize(i7, f7);
        this.mNextText.setTextSize(i7, f7);
    }

    public void setTextSpacing(int i7) {
        this.mScaledTextSpacing = i7;
        requestLayout();
    }

    public void updateAdapter(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
        if (pagerAdapter != null) {
            pagerAdapter.unregisterDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = null;
        }
        if (pagerAdapter2 != null) {
            pagerAdapter2.registerDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = new WeakReference<>(pagerAdapter2);
        }
        ViewPager viewPager = this.mPager;
        if (viewPager != null) {
            this.mLastKnownCurrentPage = -1;
            this.mLastKnownPositionOffset = -1.0f;
            updateText(viewPager.getCurrentItem(), pagerAdapter2);
            requestLayout();
        }
    }

    public void updateText(int i7, PagerAdapter pagerAdapter) {
        int count = pagerAdapter != null ? pagerAdapter.getCount() : 0;
        this.mUpdatingText = true;
        CharSequence pageTitle = null;
        this.mPrevText.setText((i7 < 1 || pagerAdapter == null) ? null : pagerAdapter.getPageTitle(i7 - 1));
        this.mCurrText.setText((pagerAdapter == null || i7 >= count) ? null : pagerAdapter.getPageTitle(i7));
        int i8 = i7 + 1;
        if (i8 < count && pagerAdapter != null) {
            pageTitle = pagerAdapter.getPageTitle(i8);
        }
        this.mNextText.setText(pageTitle);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.mPrevText.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.mCurrText.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.mNextText.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.mLastKnownCurrentPage = i7;
        if (!this.mUpdatingPositions) {
            updateTextPositions(i7, this.mLastKnownPositionOffset, false);
        }
        this.mUpdatingText = false;
    }

    public void updateTextPositions(int i7, float f7, boolean z6) {
        int i8;
        int i9;
        int i10;
        int i11;
        if (i7 != this.mLastKnownCurrentPage) {
            updateText(i7, this.mPager.getAdapter());
        } else if (!z6 && f7 == this.mLastKnownPositionOffset) {
            return;
        }
        this.mUpdatingPositions = true;
        int measuredWidth = this.mPrevText.getMeasuredWidth();
        int measuredWidth2 = this.mCurrText.getMeasuredWidth();
        int measuredWidth3 = this.mNextText.getMeasuredWidth();
        int i12 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i13 = paddingRight + i12;
        int i14 = (width - (paddingLeft + i12)) - i13;
        float f8 = 0.5f + f7;
        if (f8 > 1.0f) {
            f8 -= 1.0f;
        }
        int i15 = ((width - i13) - ((int) (i14 * f8))) - i12;
        int i16 = measuredWidth2 + i15;
        int baseline = this.mPrevText.getBaseline();
        int baseline2 = this.mCurrText.getBaseline();
        int baseline3 = this.mNextText.getBaseline();
        int iMax = Math.max(Math.max(baseline, baseline2), baseline3);
        int i17 = iMax - baseline;
        int i18 = iMax - baseline2;
        int i19 = iMax - baseline3;
        int iMax2 = Math.max(Math.max(this.mPrevText.getMeasuredHeight() + i17, this.mCurrText.getMeasuredHeight() + i18), this.mNextText.getMeasuredHeight() + i19);
        int i20 = this.mGravity & 112;
        if (i20 == 16) {
            i8 = (((height - paddingTop) - paddingBottom) - iMax2) / 2;
        } else {
            if (i20 != 80) {
                i9 = i17 + paddingTop;
                i10 = i18 + paddingTop;
                i11 = paddingTop + i19;
                TextView textView = this.mCurrText;
                textView.layout(i15, i10, i16, textView.getMeasuredHeight() + i10);
                int iMin = Math.min(paddingLeft, (i15 - this.mScaledTextSpacing) - measuredWidth);
                TextView textView2 = this.mPrevText;
                textView2.layout(iMin, i9, measuredWidth + iMin, textView2.getMeasuredHeight() + i9);
                int iMax3 = Math.max((width - paddingRight) - measuredWidth3, i16 + this.mScaledTextSpacing);
                TextView textView3 = this.mNextText;
                textView3.layout(iMax3, i11, iMax3 + measuredWidth3, textView3.getMeasuredHeight() + i11);
                this.mLastKnownPositionOffset = f7;
                this.mUpdatingPositions = false;
            }
            i8 = (height - paddingBottom) - iMax2;
        }
        i9 = i17 + i8;
        i10 = i18 + i8;
        i11 = i8 + i19;
        TextView textView4 = this.mCurrText;
        textView4.layout(i15, i10, i16, textView4.getMeasuredHeight() + i10);
        int iMin2 = Math.min(paddingLeft, (i15 - this.mScaledTextSpacing) - measuredWidth);
        TextView textView22 = this.mPrevText;
        textView22.layout(iMin2, i9, measuredWidth + iMin2, textView22.getMeasuredHeight() + i9);
        int iMax32 = Math.max((width - paddingRight) - measuredWidth3, i16 + this.mScaledTextSpacing);
        TextView textView32 = this.mNextText;
        textView32.layout(iMax32, i11, iMax32 + measuredWidth3, textView32.getMeasuredHeight() + i11);
        this.mLastKnownPositionOffset = f7;
        this.mUpdatingPositions = false;
    }

    public PagerTitleStrip(@NonNull Context context, @Nullable AttributeSet attributeSet) throws Resources.NotFoundException {
        super(context, attributeSet);
        this.mLastKnownCurrentPage = -1;
        this.mLastKnownPositionOffset = -1.0f;
        this.mPageListener = new PageListener();
        TextView textView = new TextView(context);
        this.mPrevText = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.mCurrText = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.mNextText = textView3;
        addView(textView3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ATTRS);
        boolean z6 = false;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            TextViewCompat.setTextAppearance(this.mPrevText, resourceId);
            TextViewCompat.setTextAppearance(this.mCurrText, resourceId);
            TextViewCompat.setTextAppearance(this.mNextText, resourceId);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            setTextSize(0, dimensionPixelSize);
        }
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            int color = typedArrayObtainStyledAttributes.getColor(2, 0);
            this.mPrevText.setTextColor(color);
            this.mCurrText.setTextColor(color);
            this.mNextText.setTextColor(color);
        }
        this.mGravity = typedArrayObtainStyledAttributes.getInteger(3, 80);
        typedArrayObtainStyledAttributes.recycle();
        this.mTextColor = this.mCurrText.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(SIDE_ALPHA);
        this.mPrevText.setEllipsize(TextUtils.TruncateAt.END);
        this.mCurrText.setEllipsize(TextUtils.TruncateAt.END);
        this.mNextText.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, TEXT_ATTRS);
            z6 = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (z6) {
            setSingleLineAllCaps(this.mPrevText);
            setSingleLineAllCaps(this.mCurrText);
            setSingleLineAllCaps(this.mNextText);
        } else {
            this.mPrevText.setSingleLine();
            this.mCurrText.setSingleLine();
            this.mNextText.setSingleLine();
        }
        this.mScaledTextSpacing = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }
}
