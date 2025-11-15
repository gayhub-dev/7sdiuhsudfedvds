package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_NONE = 0;
    private ConstraintWidget[] mDisplayedWidgets;
    private int mHorizontalStyle = -1;
    private int mVerticalStyle = -1;
    private int mFirstHorizontalStyle = -1;
    private int mFirstVerticalStyle = -1;
    private int mLastHorizontalStyle = -1;
    private int mLastVerticalStyle = -1;
    private float mHorizontalBias = 0.5f;
    private float mVerticalBias = 0.5f;
    private float mFirstHorizontalBias = 0.5f;
    private float mFirstVerticalBias = 0.5f;
    private float mLastHorizontalBias = 0.5f;
    private float mLastVerticalBias = 0.5f;
    private int mHorizontalGap = 0;
    private int mVerticalGap = 0;
    private int mHorizontalAlign = 2;
    private int mVerticalAlign = 2;
    private int mWrapMode = 0;
    private int mMaxElementsWrap = -1;
    private int mOrientation = 0;
    private ArrayList<WidgetsList> mChainList = new ArrayList<>();
    private ConstraintWidget[] mAlignedBiggestElementsInRows = null;
    private ConstraintWidget[] mAlignedBiggestElementsInCols = null;
    private int[] mAlignedDimensions = null;
    private int mDisplayedWidgetsCount = 0;

    public class WidgetsList {
        private ConstraintAnchor mBottom;
        private ConstraintAnchor mLeft;
        private int mMax;
        private int mOrientation;
        private int mPaddingBottom;
        private int mPaddingLeft;
        private int mPaddingRight;
        private int mPaddingTop;
        private ConstraintAnchor mRight;
        private ConstraintAnchor mTop;
        private ConstraintWidget biggest = null;
        public int biggestDimension = 0;
        private int mWidth = 0;
        private int mHeight = 0;
        private int mStartIndex = 0;
        private int mCount = 0;
        private int mNbMatchConstraintsWidgets = 0;

        public WidgetsList(int i7, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i8) {
            this.mOrientation = 0;
            this.mPaddingLeft = 0;
            this.mPaddingTop = 0;
            this.mPaddingRight = 0;
            this.mPaddingBottom = 0;
            this.mMax = 0;
            this.mOrientation = i7;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = Flow.this.getPaddingLeft();
            this.mPaddingTop = Flow.this.getPaddingTop();
            this.mPaddingRight = Flow.this.getPaddingRight();
            this.mPaddingBottom = Flow.this.getPaddingBottom();
            this.mMax = i8;
        }

        private void recomputeDimensions() {
            this.mWidth = 0;
            this.mHeight = 0;
            this.biggest = null;
            this.biggestDimension = 0;
            int i7 = this.mCount;
            for (int i8 = 0; i8 < i7 && this.mStartIndex + i8 < Flow.this.mDisplayedWidgetsCount; i8++) {
                ConstraintWidget constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i8];
                if (this.mOrientation == 0) {
                    int width = constraintWidget.getWidth();
                    int i9 = Flow.this.mHorizontalGap;
                    if (constraintWidget.getVisibility() == 8) {
                        i9 = 0;
                    }
                    this.mWidth = width + i9 + this.mWidth;
                    int widgetHeight = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                    if (this.biggest == null || this.biggestDimension < widgetHeight) {
                        this.biggest = constraintWidget;
                        this.biggestDimension = widgetHeight;
                        this.mHeight = widgetHeight;
                    }
                } else {
                    int widgetWidth = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                    int widgetHeight2 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                    int i10 = Flow.this.mVerticalGap;
                    if (constraintWidget.getVisibility() == 8) {
                        i10 = 0;
                    }
                    this.mHeight = widgetHeight2 + i10 + this.mHeight;
                    if (this.biggest == null || this.biggestDimension < widgetWidth) {
                        this.biggest = constraintWidget;
                        this.biggestDimension = widgetWidth;
                        this.mWidth = widgetWidth;
                    }
                }
            }
        }

        public void add(ConstraintWidget constraintWidget) {
            if (this.mOrientation == 0) {
                int widgetWidth = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    widgetWidth = 0;
                }
                this.mWidth = widgetWidth + (constraintWidget.getVisibility() != 8 ? Flow.this.mHorizontalGap : 0) + this.mWidth;
                int widgetHeight = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                if (this.biggest == null || this.biggestDimension < widgetHeight) {
                    this.biggest = constraintWidget;
                    this.biggestDimension = widgetHeight;
                    this.mHeight = widgetHeight;
                }
            } else {
                int widgetWidth2 = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                int widgetHeight2 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    widgetHeight2 = 0;
                }
                this.mHeight = widgetHeight2 + (constraintWidget.getVisibility() != 8 ? Flow.this.mVerticalGap : 0) + this.mHeight;
                if (this.biggest == null || this.biggestDimension < widgetWidth2) {
                    this.biggest = constraintWidget;
                    this.biggestDimension = widgetWidth2;
                    this.mWidth = widgetWidth2;
                }
            }
            this.mCount++;
        }

        public void clear() {
            this.biggestDimension = 0;
            this.biggest = null;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mStartIndex = 0;
            this.mCount = 0;
            this.mNbMatchConstraintsWidgets = 0;
        }

        public void createConstraints(boolean z6, int i7, boolean z7) {
            ConstraintWidget constraintWidget;
            int i8 = this.mCount;
            for (int i9 = 0; i9 < i8 && this.mStartIndex + i9 < Flow.this.mDisplayedWidgetsCount; i9++) {
                ConstraintWidget constraintWidget2 = Flow.this.mDisplayedWidgets[this.mStartIndex + i9];
                if (constraintWidget2 != null) {
                    constraintWidget2.resetAnchors();
                }
            }
            if (i8 == 0 || this.biggest == null) {
                return;
            }
            boolean z8 = z7 && i7 == 0;
            int i10 = -1;
            int i11 = -1;
            for (int i12 = 0; i12 < i8; i12++) {
                int i13 = z6 ? (i8 - 1) - i12 : i12;
                if (this.mStartIndex + i13 >= Flow.this.mDisplayedWidgetsCount) {
                    break;
                }
                if (Flow.this.mDisplayedWidgets[this.mStartIndex + i13].getVisibility() == 0) {
                    if (i10 == -1) {
                        i10 = i12;
                    }
                    i11 = i12;
                }
            }
            ConstraintWidget constraintWidget3 = null;
            if (this.mOrientation != 0) {
                ConstraintWidget constraintWidget4 = this.biggest;
                constraintWidget4.setHorizontalChainStyle(Flow.this.mHorizontalStyle);
                int i14 = this.mPaddingLeft;
                if (i7 > 0) {
                    i14 += Flow.this.mHorizontalGap;
                }
                if (z6) {
                    constraintWidget4.mRight.connect(this.mRight, i14);
                    if (z7) {
                        constraintWidget4.mLeft.connect(this.mLeft, this.mPaddingRight);
                    }
                    if (i7 > 0) {
                        this.mRight.mOwner.mLeft.connect(constraintWidget4.mRight, 0);
                    }
                } else {
                    constraintWidget4.mLeft.connect(this.mLeft, i14);
                    if (z7) {
                        constraintWidget4.mRight.connect(this.mRight, this.mPaddingRight);
                    }
                    if (i7 > 0) {
                        this.mLeft.mOwner.mRight.connect(constraintWidget4.mLeft, 0);
                    }
                }
                int i15 = 0;
                while (i15 < i8 && this.mStartIndex + i15 < Flow.this.mDisplayedWidgetsCount) {
                    ConstraintWidget constraintWidget5 = Flow.this.mDisplayedWidgets[this.mStartIndex + i15];
                    if (i15 == 0) {
                        constraintWidget5.connect(constraintWidget5.mTop, this.mTop, this.mPaddingTop);
                        int i16 = Flow.this.mVerticalStyle;
                        float f7 = Flow.this.mVerticalBias;
                        if (this.mStartIndex == 0 && Flow.this.mFirstVerticalStyle != -1) {
                            i16 = Flow.this.mFirstVerticalStyle;
                            f7 = Flow.this.mFirstVerticalBias;
                        } else if (z7 && Flow.this.mLastVerticalStyle != -1) {
                            i16 = Flow.this.mLastVerticalStyle;
                            f7 = Flow.this.mLastVerticalBias;
                        }
                        constraintWidget5.setVerticalChainStyle(i16);
                        constraintWidget5.setVerticalBiasPercent(f7);
                    }
                    if (i15 == i8 - 1) {
                        constraintWidget5.connect(constraintWidget5.mBottom, this.mBottom, this.mPaddingBottom);
                    }
                    if (constraintWidget3 != null) {
                        constraintWidget5.mTop.connect(constraintWidget3.mBottom, Flow.this.mVerticalGap);
                        if (i15 == i10) {
                            constraintWidget5.mTop.setGoneMargin(this.mPaddingTop);
                        }
                        constraintWidget3.mBottom.connect(constraintWidget5.mTop, 0);
                        if (i15 == i11 + 1) {
                            constraintWidget3.mBottom.setGoneMargin(this.mPaddingBottom);
                        }
                    }
                    if (constraintWidget5 != constraintWidget4) {
                        if (z6) {
                            int i17 = Flow.this.mHorizontalAlign;
                            if (i17 == 0) {
                                constraintWidget5.mRight.connect(constraintWidget4.mRight, 0);
                            } else if (i17 == 1) {
                                constraintWidget5.mLeft.connect(constraintWidget4.mLeft, 0);
                            } else if (i17 == 2) {
                                constraintWidget5.mLeft.connect(constraintWidget4.mLeft, 0);
                                constraintWidget5.mRight.connect(constraintWidget4.mRight, 0);
                            }
                        } else {
                            int i18 = Flow.this.mHorizontalAlign;
                            if (i18 == 0) {
                                constraintWidget5.mLeft.connect(constraintWidget4.mLeft, 0);
                            } else if (i18 == 1) {
                                constraintWidget5.mRight.connect(constraintWidget4.mRight, 0);
                            } else if (i18 == 2) {
                                if (z8) {
                                    constraintWidget5.mLeft.connect(this.mLeft, this.mPaddingLeft);
                                    constraintWidget5.mRight.connect(this.mRight, this.mPaddingRight);
                                } else {
                                    constraintWidget5.mLeft.connect(constraintWidget4.mLeft, 0);
                                    constraintWidget5.mRight.connect(constraintWidget4.mRight, 0);
                                }
                            }
                        }
                    }
                    i15++;
                    constraintWidget3 = constraintWidget5;
                }
                return;
            }
            ConstraintWidget constraintWidget6 = this.biggest;
            constraintWidget6.setVerticalChainStyle(Flow.this.mVerticalStyle);
            int i19 = this.mPaddingTop;
            if (i7 > 0) {
                i19 += Flow.this.mVerticalGap;
            }
            constraintWidget6.mTop.connect(this.mTop, i19);
            if (z7) {
                constraintWidget6.mBottom.connect(this.mBottom, this.mPaddingBottom);
            }
            if (i7 > 0) {
                this.mTop.mOwner.mBottom.connect(constraintWidget6.mTop, 0);
            }
            if (Flow.this.mVerticalAlign != 3 || constraintWidget6.hasBaseline()) {
                constraintWidget = constraintWidget6;
            } else {
                for (int i20 = 0; i20 < i8; i20++) {
                    int i21 = z6 ? (i8 - 1) - i20 : i20;
                    if (this.mStartIndex + i21 >= Flow.this.mDisplayedWidgetsCount) {
                        break;
                    }
                    constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i21];
                    if (constraintWidget.hasBaseline()) {
                        break;
                    }
                }
                constraintWidget = constraintWidget6;
            }
            int i22 = 0;
            while (i22 < i8) {
                int i23 = z6 ? (i8 - 1) - i22 : i22;
                if (this.mStartIndex + i23 >= Flow.this.mDisplayedWidgetsCount) {
                    return;
                }
                ConstraintWidget constraintWidget7 = Flow.this.mDisplayedWidgets[this.mStartIndex + i23];
                if (i22 == 0) {
                    constraintWidget7.connect(constraintWidget7.mLeft, this.mLeft, this.mPaddingLeft);
                }
                if (i23 == 0) {
                    int i24 = Flow.this.mHorizontalStyle;
                    float f8 = Flow.this.mHorizontalBias;
                    if (this.mStartIndex == 0 && Flow.this.mFirstHorizontalStyle != -1) {
                        i24 = Flow.this.mFirstHorizontalStyle;
                        f8 = Flow.this.mFirstHorizontalBias;
                    } else if (z7 && Flow.this.mLastHorizontalStyle != -1) {
                        i24 = Flow.this.mLastHorizontalStyle;
                        f8 = Flow.this.mLastHorizontalBias;
                    }
                    constraintWidget7.setHorizontalChainStyle(i24);
                    constraintWidget7.setHorizontalBiasPercent(f8);
                }
                if (i22 == i8 - 1) {
                    constraintWidget7.connect(constraintWidget7.mRight, this.mRight, this.mPaddingRight);
                }
                if (constraintWidget3 != null) {
                    constraintWidget7.mLeft.connect(constraintWidget3.mRight, Flow.this.mHorizontalGap);
                    if (i22 == i10) {
                        constraintWidget7.mLeft.setGoneMargin(this.mPaddingLeft);
                    }
                    constraintWidget3.mRight.connect(constraintWidget7.mLeft, 0);
                    if (i22 == i11 + 1) {
                        constraintWidget3.mRight.setGoneMargin(this.mPaddingRight);
                    }
                }
                if (constraintWidget7 != constraintWidget6) {
                    if (Flow.this.mVerticalAlign == 3 && constraintWidget.hasBaseline() && constraintWidget7 != constraintWidget && constraintWidget7.hasBaseline()) {
                        constraintWidget7.mBaseline.connect(constraintWidget.mBaseline, 0);
                    } else {
                        int i25 = Flow.this.mVerticalAlign;
                        if (i25 == 0) {
                            constraintWidget7.mTop.connect(constraintWidget6.mTop, 0);
                        } else if (i25 == 1) {
                            constraintWidget7.mBottom.connect(constraintWidget6.mBottom, 0);
                        } else if (z8) {
                            constraintWidget7.mTop.connect(this.mTop, this.mPaddingTop);
                            constraintWidget7.mBottom.connect(this.mBottom, this.mPaddingBottom);
                        } else {
                            constraintWidget7.mTop.connect(constraintWidget6.mTop, 0);
                            constraintWidget7.mBottom.connect(constraintWidget6.mBottom, 0);
                        }
                    }
                }
                i22++;
                constraintWidget3 = constraintWidget7;
            }
        }

        public int getHeight() {
            return this.mOrientation == 1 ? this.mHeight - Flow.this.mVerticalGap : this.mHeight;
        }

        public int getWidth() {
            return this.mOrientation == 0 ? this.mWidth - Flow.this.mHorizontalGap : this.mWidth;
        }

        public void measureMatchConstraints(int i7) {
            int i8 = this.mNbMatchConstraintsWidgets;
            if (i8 == 0) {
                return;
            }
            int i9 = this.mCount;
            int i10 = i7 / i8;
            for (int i11 = 0; i11 < i9 && this.mStartIndex + i11 < Flow.this.mDisplayedWidgetsCount; i11++) {
                ConstraintWidget constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i11];
                if (this.mOrientation == 0) {
                    if (constraintWidget != null && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                        Flow.this.measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i10, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                    }
                } else if (constraintWidget != null && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                    Flow.this.measure(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i10);
                }
            }
            recomputeDimensions();
        }

        public void setStartIndex(int i7) {
            this.mStartIndex = i7;
        }

        public void setup(int i7, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i8, int i9, int i10, int i11, int i12) {
            this.mOrientation = i7;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = i8;
            this.mPaddingTop = i9;
            this.mPaddingRight = i10;
            this.mPaddingBottom = i11;
            this.mMax = i12;
        }
    }

    private void createAlignedConstraints(boolean z6) {
        ConstraintWidget constraintWidget;
        if (this.mAlignedDimensions == null || this.mAlignedBiggestElementsInCols == null || this.mAlignedBiggestElementsInRows == null) {
            return;
        }
        for (int i7 = 0; i7 < this.mDisplayedWidgetsCount; i7++) {
            this.mDisplayedWidgets[i7].resetAnchors();
        }
        int[] iArr = this.mAlignedDimensions;
        int i8 = iArr[0];
        int i9 = iArr[1];
        ConstraintWidget constraintWidget2 = null;
        for (int i10 = 0; i10 < i8; i10++) {
            ConstraintWidget constraintWidget3 = this.mAlignedBiggestElementsInCols[z6 ? (i8 - i10) - 1 : i10];
            if (constraintWidget3 != null && constraintWidget3.getVisibility() != 8) {
                if (i10 == 0) {
                    constraintWidget3.connect(constraintWidget3.mLeft, this.mLeft, getPaddingLeft());
                    constraintWidget3.setHorizontalChainStyle(this.mHorizontalStyle);
                    constraintWidget3.setHorizontalBiasPercent(this.mHorizontalBias);
                }
                if (i10 == i8 - 1) {
                    constraintWidget3.connect(constraintWidget3.mRight, this.mRight, getPaddingRight());
                }
                if (i10 > 0) {
                    constraintWidget3.connect(constraintWidget3.mLeft, constraintWidget2.mRight, this.mHorizontalGap);
                    constraintWidget2.connect(constraintWidget2.mRight, constraintWidget3.mLeft, 0);
                }
                constraintWidget2 = constraintWidget3;
            }
        }
        for (int i11 = 0; i11 < i9; i11++) {
            ConstraintWidget constraintWidget4 = this.mAlignedBiggestElementsInRows[i11];
            if (constraintWidget4 != null && constraintWidget4.getVisibility() != 8) {
                if (i11 == 0) {
                    constraintWidget4.connect(constraintWidget4.mTop, this.mTop, getPaddingTop());
                    constraintWidget4.setVerticalChainStyle(this.mVerticalStyle);
                    constraintWidget4.setVerticalBiasPercent(this.mVerticalBias);
                }
                if (i11 == i9 - 1) {
                    constraintWidget4.connect(constraintWidget4.mBottom, this.mBottom, getPaddingBottom());
                }
                if (i11 > 0) {
                    constraintWidget4.connect(constraintWidget4.mTop, constraintWidget2.mBottom, this.mVerticalGap);
                    constraintWidget2.connect(constraintWidget2.mBottom, constraintWidget4.mTop, 0);
                }
                constraintWidget2 = constraintWidget4;
            }
        }
        for (int i12 = 0; i12 < i8; i12++) {
            for (int i13 = 0; i13 < i9; i13++) {
                int i14 = (i13 * i8) + i12;
                if (this.mOrientation == 1) {
                    i14 = (i12 * i9) + i13;
                }
                ConstraintWidget[] constraintWidgetArr = this.mDisplayedWidgets;
                if (i14 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i14]) != null && constraintWidget.getVisibility() != 8) {
                    ConstraintWidget constraintWidget5 = this.mAlignedBiggestElementsInCols[i12];
                    ConstraintWidget constraintWidget6 = this.mAlignedBiggestElementsInRows[i13];
                    if (constraintWidget != constraintWidget5) {
                        constraintWidget.connect(constraintWidget.mLeft, constraintWidget5.mLeft, 0);
                        constraintWidget.connect(constraintWidget.mRight, constraintWidget5.mRight, 0);
                    }
                    if (constraintWidget != constraintWidget6) {
                        constraintWidget.connect(constraintWidget.mTop, constraintWidget6.mTop, 0);
                        constraintWidget.connect(constraintWidget.mBottom, constraintWidget6.mBottom, 0);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getWidgetHeight(ConstraintWidget constraintWidget, int i7) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i8 = constraintWidget.mMatchConstraintDefaultHeight;
            if (i8 == 0) {
                return 0;
            }
            if (i8 == 2) {
                int i9 = (int) (constraintWidget.mMatchConstraintPercentHeight * i7);
                if (i9 != constraintWidget.getHeight()) {
                    constraintWidget.setMeasureRequested(true);
                    measure(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i9);
                }
                return i9;
            }
            if (i8 == 1) {
                return constraintWidget.getHeight();
            }
            if (i8 == 3) {
                return (int) ((constraintWidget.getWidth() * constraintWidget.mDimensionRatio) + 0.5f);
            }
        }
        return constraintWidget.getHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getWidgetWidth(ConstraintWidget constraintWidget, int i7) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i8 = constraintWidget.mMatchConstraintDefaultWidth;
            if (i8 == 0) {
                return 0;
            }
            if (i8 == 2) {
                int i9 = (int) (constraintWidget.mMatchConstraintPercentWidth * i7);
                if (i9 != constraintWidget.getWidth()) {
                    constraintWidget.setMeasureRequested(true);
                    measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i9, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                }
                return i9;
            }
            if (i8 == 1) {
                return constraintWidget.getWidth();
            }
            if (i8 == 3) {
                return (int) ((constraintWidget.getHeight() * constraintWidget.mDimensionRatio) + 0.5f);
            }
        }
        return constraintWidget.getWidth();
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0068  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:105:0x011b -> B:42:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:106:0x011d -> B:42:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:108:0x0123 -> B:42:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:109:0x0125 -> B:42:0x0063). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void measureAligned(android.support.constraint.solver.widgets.ConstraintWidget[] r17, int r18, int r19, int r20, int[] r21) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Flow.measureAligned(android.support.constraint.solver.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    private void measureChainWrap(ConstraintWidget[] constraintWidgetArr, int i7, int i8, int i9, int[] iArr) {
        int i10;
        int i11;
        int i12;
        ConstraintAnchor constraintAnchor;
        int paddingRight;
        ConstraintAnchor constraintAnchor2;
        int paddingBottom;
        int i13;
        if (i7 == 0) {
            return;
        }
        this.mChainList.clear();
        WidgetsList widgetsList = new WidgetsList(i8, this.mLeft, this.mTop, this.mRight, this.mBottom, i9);
        this.mChainList.add(widgetsList);
        if (i8 == 0) {
            i10 = 0;
            int i14 = 0;
            int i15 = 0;
            while (i15 < i7) {
                ConstraintWidget constraintWidget = constraintWidgetArr[i15];
                int widgetWidth = getWidgetWidth(constraintWidget, i9);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i10++;
                }
                int i16 = i10;
                boolean z6 = (i14 == i9 || (this.mHorizontalGap + i14) + widgetWidth > i9) && widgetsList.biggest != null;
                if (!z6 && i15 > 0 && (i13 = this.mMaxElementsWrap) > 0 && i15 % i13 == 0) {
                    z6 = true;
                }
                if (z6) {
                    widgetsList = new WidgetsList(i8, this.mLeft, this.mTop, this.mRight, this.mBottom, i9);
                    widgetsList.setStartIndex(i15);
                    this.mChainList.add(widgetsList);
                } else {
                    if (i15 > 0) {
                        i14 = this.mHorizontalGap + widgetWidth + i14;
                    }
                    widgetsList.add(constraintWidget);
                    i15++;
                    i10 = i16;
                }
                i14 = widgetWidth;
                widgetsList.add(constraintWidget);
                i15++;
                i10 = i16;
            }
        } else {
            i10 = 0;
            int i17 = 0;
            int i18 = 0;
            while (i18 < i7) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i18];
                int widgetHeight = getWidgetHeight(constraintWidget2, i9);
                if (constraintWidget2.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i10++;
                }
                int i19 = i10;
                boolean z7 = (i17 == i9 || (this.mVerticalGap + i17) + widgetHeight > i9) && widgetsList.biggest != null;
                if (!z7 && i18 > 0 && (i11 = this.mMaxElementsWrap) > 0 && i18 % i11 == 0) {
                    z7 = true;
                }
                if (z7) {
                    widgetsList = new WidgetsList(i8, this.mLeft, this.mTop, this.mRight, this.mBottom, i9);
                    widgetsList.setStartIndex(i18);
                    this.mChainList.add(widgetsList);
                } else {
                    if (i18 > 0) {
                        i17 = this.mVerticalGap + widgetHeight + i17;
                    }
                    widgetsList.add(constraintWidget2);
                    i18++;
                    i10 = i19;
                }
                i17 = widgetHeight;
                widgetsList.add(constraintWidget2);
                i18++;
                i10 = i19;
            }
        }
        int size = this.mChainList.size();
        ConstraintAnchor constraintAnchor3 = this.mLeft;
        ConstraintAnchor constraintAnchor4 = this.mTop;
        ConstraintAnchor constraintAnchor5 = this.mRight;
        ConstraintAnchor constraintAnchor6 = this.mBottom;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight2 = getPaddingRight();
        int paddingBottom2 = getPaddingBottom();
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z8 = horizontalDimensionBehaviour == dimensionBehaviour || getVerticalDimensionBehaviour() == dimensionBehaviour;
        if (i10 > 0 && z8) {
            for (int i20 = 0; i20 < size; i20++) {
                WidgetsList widgetsList2 = this.mChainList.get(i20);
                if (i8 == 0) {
                    widgetsList2.measureMatchConstraints(i9 - widgetsList2.getWidth());
                } else {
                    widgetsList2.measureMatchConstraints(i9 - widgetsList2.getHeight());
                }
            }
        }
        int i21 = paddingTop;
        int i22 = paddingRight2;
        int height = 0;
        int width = 0;
        int i23 = 0;
        int i24 = paddingLeft;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i25 = paddingBottom2;
        while (i23 < size) {
            WidgetsList widgetsList3 = this.mChainList.get(i23);
            if (i8 == 0) {
                if (i23 < size - 1) {
                    constraintAnchor2 = this.mChainList.get(i23 + 1).biggest.mTop;
                    paddingBottom = 0;
                } else {
                    constraintAnchor2 = this.mBottom;
                    paddingBottom = getPaddingBottom();
                }
                ConstraintAnchor constraintAnchor9 = widgetsList3.biggest.mBottom;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i26 = height;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i27 = width;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i12 = i23;
                widgetsList3.setup(i8, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i24, i21, i22, paddingBottom, i9);
                int iMax = Math.max(i27, widgetsList3.getWidth());
                height = i26 + widgetsList3.getHeight();
                if (i12 > 0) {
                    height += this.mVerticalGap;
                }
                constraintAnchor8 = constraintAnchor11;
                width = iMax;
                constraintAnchor7 = constraintAnchor9;
                i21 = 0;
                constraintAnchor = constraintAnchor14;
                int i28 = paddingBottom;
                constraintAnchor6 = constraintAnchor2;
                i25 = i28;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i29 = height;
                int i30 = width;
                i12 = i23;
                if (i12 < size - 1) {
                    constraintAnchor = this.mChainList.get(i12 + 1).biggest.mLeft;
                    paddingRight = 0;
                } else {
                    constraintAnchor = this.mRight;
                    paddingRight = getPaddingRight();
                }
                ConstraintAnchor constraintAnchor16 = widgetsList3.biggest.mRight;
                widgetsList3.setup(i8, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i24, i21, paddingRight, i25, i9);
                width = i30 + widgetsList3.getWidth();
                int iMax2 = Math.max(i29, widgetsList3.getHeight());
                if (i12 > 0) {
                    width += this.mHorizontalGap;
                }
                height = iMax2;
                i22 = paddingRight;
                constraintAnchor8 = constraintAnchor16;
                i24 = 0;
            }
            i23 = i12 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = width;
        iArr[1] = height;
    }

    private void measureNoWrap(ConstraintWidget[] constraintWidgetArr, int i7, int i8, int i9, int[] iArr) {
        WidgetsList widgetsList;
        if (i7 == 0) {
            return;
        }
        if (this.mChainList.size() == 0) {
            widgetsList = new WidgetsList(i8, this.mLeft, this.mTop, this.mRight, this.mBottom, i9);
            this.mChainList.add(widgetsList);
        } else {
            WidgetsList widgetsList2 = this.mChainList.get(0);
            widgetsList2.clear();
            widgetsList = widgetsList2;
            widgetsList.setup(i8, this.mLeft, this.mTop, this.mRight, this.mBottom, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom(), i9);
        }
        for (int i10 = 0; i10 < i7; i10++) {
            widgetsList.add(constraintWidgetArr[i10]);
        }
        iArr[0] = widgetsList.getWidth();
        iArr[1] = widgetsList.getHeight();
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem, boolean z6) {
        super.addToSolver(linearSystem, z6);
        boolean zIsRtl = getParent() != null ? ((ConstraintWidgetContainer) getParent()).isRtl() : false;
        int i7 = this.mWrapMode;
        if (i7 != 0) {
            if (i7 == 1) {
                int size = this.mChainList.size();
                int i8 = 0;
                while (i8 < size) {
                    this.mChainList.get(i8).createConstraints(zIsRtl, i8, i8 == size + (-1));
                    i8++;
                }
            } else if (i7 == 2) {
                createAlignedConstraints(zIsRtl);
            }
        } else if (this.mChainList.size() > 0) {
            this.mChainList.get(0).createConstraints(zIsRtl, 0, true);
        }
        needsCallbackFromSolver(false);
    }

    @Override // android.support.constraint.solver.widgets.HelperWidget, android.support.constraint.solver.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.copy(constraintWidget, map);
        Flow flow = (Flow) constraintWidget;
        this.mHorizontalStyle = flow.mHorizontalStyle;
        this.mVerticalStyle = flow.mVerticalStyle;
        this.mFirstHorizontalStyle = flow.mFirstHorizontalStyle;
        this.mFirstVerticalStyle = flow.mFirstVerticalStyle;
        this.mLastHorizontalStyle = flow.mLastHorizontalStyle;
        this.mLastVerticalStyle = flow.mLastVerticalStyle;
        this.mHorizontalBias = flow.mHorizontalBias;
        this.mVerticalBias = flow.mVerticalBias;
        this.mFirstHorizontalBias = flow.mFirstHorizontalBias;
        this.mFirstVerticalBias = flow.mFirstVerticalBias;
        this.mLastHorizontalBias = flow.mLastHorizontalBias;
        this.mLastVerticalBias = flow.mLastVerticalBias;
        this.mHorizontalGap = flow.mHorizontalGap;
        this.mVerticalGap = flow.mVerticalGap;
        this.mHorizontalAlign = flow.mHorizontalAlign;
        this.mVerticalAlign = flow.mVerticalAlign;
        this.mWrapMode = flow.mWrapMode;
        this.mMaxElementsWrap = flow.mMaxElementsWrap;
        this.mOrientation = flow.mOrientation;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0104  */
    @Override // android.support.constraint.solver.widgets.VirtualLayout
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void measure(int r19, int r20, int r21, int r22) {
        /*
            Method dump skipped, instructions count: 265
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Flow.measure(int, int, int, int):void");
    }

    public void setFirstHorizontalBias(float f7) {
        this.mFirstHorizontalBias = f7;
    }

    public void setFirstHorizontalStyle(int i7) {
        this.mFirstHorizontalStyle = i7;
    }

    public void setFirstVerticalBias(float f7) {
        this.mFirstVerticalBias = f7;
    }

    public void setFirstVerticalStyle(int i7) {
        this.mFirstVerticalStyle = i7;
    }

    public void setHorizontalAlign(int i7) {
        this.mHorizontalAlign = i7;
    }

    public void setHorizontalBias(float f7) {
        this.mHorizontalBias = f7;
    }

    public void setHorizontalGap(int i7) {
        this.mHorizontalGap = i7;
    }

    public void setHorizontalStyle(int i7) {
        this.mHorizontalStyle = i7;
    }

    public void setLastHorizontalBias(float f7) {
        this.mLastHorizontalBias = f7;
    }

    public void setLastHorizontalStyle(int i7) {
        this.mLastHorizontalStyle = i7;
    }

    public void setLastVerticalBias(float f7) {
        this.mLastVerticalBias = f7;
    }

    public void setLastVerticalStyle(int i7) {
        this.mLastVerticalStyle = i7;
    }

    public void setMaxElementsWrap(int i7) {
        this.mMaxElementsWrap = i7;
    }

    public void setOrientation(int i7) {
        this.mOrientation = i7;
    }

    public void setVerticalAlign(int i7) {
        this.mVerticalAlign = i7;
    }

    public void setVerticalBias(float f7) {
        this.mVerticalBias = f7;
    }

    public void setVerticalGap(int i7) {
        this.mVerticalGap = i7;
    }

    public void setVerticalStyle(int i7) {
        this.mVerticalStyle = i7;
    }

    public void setWrapMode(int i7) {
        this.mWrapMode = i7;
    }
}
