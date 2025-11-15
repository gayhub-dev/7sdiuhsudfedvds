package android.support.constraint.motion;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Barrier;
import android.support.constraint.C0071R;
import android.support.constraint.ConstraintHelper;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayoutStates;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Constraints;
import android.support.constraint.StateSet;
import android.support.constraint.motion.MotionScene;
import android.support.constraint.motion.utils.StopLogic;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Flow;
import android.support.constraint.solver.widgets.Guideline;
import android.support.constraint.solver.widgets.Helper;
import android.support.constraint.solver.widgets.HelperWidget;
import android.support.constraint.solver.widgets.VirtualLayout;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import p009b.C0413b;

/* loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements NestedScrollingParent2 {
    private static final boolean DEBUG = false;
    public static final int DEBUG_SHOW_NONE = 0;
    public static final int DEBUG_SHOW_PATH = 2;
    public static final int DEBUG_SHOW_PROGRESS = 1;
    private static final float EPSILON = 1.0E-5f;
    public static boolean IS_IN_EDIT_MODE = false;
    public static final int MAX_KEY_FRAMES = 50;
    public static final String TAG = "MotionLayout";
    public static final int TOUCH_UP_COMPLETE = 0;
    public static final int TOUCH_UP_COMPLETE_TO_END = 2;
    public static final int TOUCH_UP_COMPLETE_TO_START = 1;
    public static final int TOUCH_UP_DECELERATE = 4;
    public static final int TOUCH_UP_DECELERATE_AND_COMPLETE = 5;
    public static final int TOUCH_UP_STOP = 3;
    public static final int VELOCITY_LAYOUT = 1;
    public static final int VELOCITY_POST_LAYOUT = 0;
    public static final int VELOCITY_STATIC_LAYOUT = 3;
    public static final int VELOCITY_STATIC_POST_LAYOUT = 2;
    public boolean firstDown;
    private float lastPos;
    private float lastY;
    private long mAnimationStartTime;
    private int mBeginState;
    private RectF mBoundsCheck;
    public int mCurrentState;
    public int mDebugPath;
    private DecelerateInterpolator mDecelerateLogic;
    private DesignTool mDesignTool;
    public DevModeDraw mDevModeDraw;
    private int mEndState;
    public int mEndWrapHeight;
    public int mEndWrapWidth;
    public HashMap<View, MotionController> mFrameArrayList;
    private int mFrames;
    public int mHeightMeasureMode;
    private boolean mInLayout;
    public boolean mInTransition;
    public boolean mIndirectTransition;
    private boolean mInteractionEnabled;
    public Interpolator mInterpolator;
    public boolean mIsAnimating;
    private boolean mKeepAnimating;
    private KeyCache mKeyCache;
    private long mLastDrawTime;
    private float mLastFps;
    private int mLastHeightMeasureSpec;
    public int mLastLayoutHeight;
    public int mLastLayoutWidth;
    public float mLastVelocity;
    private int mLastWidthMeasureSpec;
    private float mListenerPosition;
    private int mListenerState;
    public boolean mMeasureDuringTransition;
    public Model mModel;
    private boolean mNeedsFireTransitionCompleted;
    public int mOldHeight;
    public int mOldWidth;
    private ArrayList<MotionHelper> mOnHideHelpers;
    private ArrayList<MotionHelper> mOnShowHelpers;
    public float mPostInterpolationPosition;
    private View mRegionView;
    public MotionScene mScene;
    public float mScrollTargetDT;
    public float mScrollTargetDX;
    public float mScrollTargetDY;
    public long mScrollTargetTime;
    public int mStartWrapHeight;
    public int mStartWrapWidth;
    private StateCache mStateCache;
    private StopLogic mStopLogic;
    private boolean mTemporalInterpolator;
    public ArrayList<Integer> mTransitionCompleted;
    private float mTransitionDuration;
    public float mTransitionGoalPosition;
    private boolean mTransitionInstantly;
    public float mTransitionLastPosition;
    private long mTransitionLastTime;
    private TransitionListener mTransitionListener;
    private ArrayList<TransitionListener> mTransitionListeners;
    public float mTransitionPosition;
    public TransitionState mTransitionState;
    public boolean mUndergoingMotion;
    public int mWidthMeasureMode;

    /* renamed from: android.support.constraint.motion.MotionLayout$2 */
    public static /* synthetic */ class C00752 {

        /* renamed from: $SwitchMap$android$support$constraint$motion$MotionLayout$TransitionState */
        public static final /* synthetic */ int[] f117xe3ac6329;

        static {
            int[] iArr = new int[TransitionState.values().length];
            f117xe3ac6329 = iArr;
            try {
                iArr[TransitionState.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f117xe3ac6329[TransitionState.SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f117xe3ac6329[TransitionState.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f117xe3ac6329[TransitionState.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public class DecelerateInterpolator extends MotionInterpolator {
        public float maxA;
        public float initalV = 0.0f;
        public float currentP = 0.0f;

        public DecelerateInterpolator() {
        }

        public void config(float f7, float f8, float f9) {
            this.initalV = f7;
            this.currentP = f8;
            this.maxA = f9;
        }

        @Override // android.support.constraint.motion.MotionInterpolator, android.animation.TimeInterpolator
        public float getInterpolation(float f7) {
            float f8 = this.initalV;
            if (f8 > 0.0f) {
                float f9 = this.maxA;
                if (f8 / f9 < f7) {
                    f7 = f8 / f9;
                }
                MotionLayout.this.mLastVelocity = f8 - (f9 * f7);
                return ((f8 * f7) - (((f9 * f7) * f7) / 2.0f)) + this.currentP;
            }
            float f10 = this.maxA;
            if ((-f8) / f10 < f7) {
                f7 = (-f8) / f10;
            }
            MotionLayout.this.mLastVelocity = (f10 * f7) + f8;
            return (((f10 * f7) * f7) / 2.0f) + (f8 * f7) + this.currentP;
        }

        @Override // android.support.constraint.motion.MotionInterpolator
        public float getVelocity() {
            return MotionLayout.this.mLastVelocity;
        }
    }

    public class DevModeDraw {
        private static final int DEBUG_PATH_TICKS_PER_MS = 16;
        public DashPathEffect mDashPathEffect;
        public Paint mFillPaint;
        public int mKeyFrameCount;
        public float[] mKeyFramePoints;
        public Paint mPaint;
        public Paint mPaintGraph;
        public Paint mPaintKeyframes;
        public Path mPath;
        public int[] mPathMode;
        public float[] mPoints;
        private float[] mRectangle;
        public int mShadowTranslate;
        public Paint mTextPaint;
        public final int RED_COLOR = -21965;
        public final int KEYFRAME_COLOR = -2067046;
        public final int GRAPH_COLOR = -13391360;
        public final int SHADOW_COLOR = 1996488704;
        public final int DIAMOND_SIZE = 10;
        public Rect mBounds = new Rect();
        public boolean mPresentationMode = false;

        public DevModeDraw() {
            this.mShadowTranslate = 1;
            Paint paint = new Paint();
            this.mPaint = paint;
            paint.setAntiAlias(true);
            this.mPaint.setColor(-21965);
            this.mPaint.setStrokeWidth(2.0f);
            this.mPaint.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.mPaintKeyframes = paint2;
            paint2.setAntiAlias(true);
            this.mPaintKeyframes.setColor(-2067046);
            this.mPaintKeyframes.setStrokeWidth(2.0f);
            this.mPaintKeyframes.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.mPaintGraph = paint3;
            paint3.setAntiAlias(true);
            this.mPaintGraph.setColor(-13391360);
            this.mPaintGraph.setStrokeWidth(2.0f);
            this.mPaintGraph.setStyle(Paint.Style.STROKE);
            Paint paint4 = new Paint();
            this.mTextPaint = paint4;
            paint4.setAntiAlias(true);
            this.mTextPaint.setColor(-13391360);
            this.mTextPaint.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.mRectangle = new float[8];
            Paint paint5 = new Paint();
            this.mFillPaint = paint5;
            paint5.setAntiAlias(true);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.mDashPathEffect = dashPathEffect;
            this.mPaintGraph.setPathEffect(dashPathEffect);
            this.mKeyFramePoints = new float[100];
            this.mPathMode = new int[50];
            if (this.mPresentationMode) {
                this.mPaint.setStrokeWidth(8.0f);
                this.mFillPaint.setStrokeWidth(8.0f);
                this.mPaintKeyframes.setStrokeWidth(8.0f);
                this.mShadowTranslate = 4;
            }
        }

        private void drawBasicPath(Canvas canvas) {
            canvas.drawLines(this.mPoints, this.mPaint);
        }

        private void drawPathAsConfigured(Canvas canvas) {
            boolean z6 = false;
            boolean z7 = false;
            for (int i7 = 0; i7 < this.mKeyFrameCount; i7++) {
                int[] iArr = this.mPathMode;
                if (iArr[i7] == 1) {
                    z6 = true;
                }
                if (iArr[i7] == 2) {
                    z7 = true;
                }
            }
            if (z6) {
                drawPathRelative(canvas);
            }
            if (z7) {
                drawPathCartesian(canvas);
            }
        }

        private void drawPathCartesian(Canvas canvas) {
            float[] fArr = this.mPoints;
            float f7 = fArr[0];
            float f8 = fArr[1];
            float f9 = fArr[fArr.length - 2];
            float f10 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f7, f9), Math.max(f8, f10), Math.max(f7, f9), Math.max(f8, f10), this.mPaintGraph);
            canvas.drawLine(Math.min(f7, f9), Math.min(f8, f10), Math.min(f7, f9), Math.max(f8, f10), this.mPaintGraph);
        }

        private void drawPathCartesianTicks(Canvas canvas, float f7, float f8) {
            float[] fArr = this.mPoints;
            float f9 = fArr[0];
            float f10 = fArr[1];
            float f11 = fArr[fArr.length - 2];
            float f12 = fArr[fArr.length - 1];
            float fMin = Math.min(f9, f11);
            float fMax = Math.max(f10, f12);
            float fMin2 = f7 - Math.min(f9, f11);
            float fMax2 = Math.max(f10, f12) - f8;
            StringBuilder sbM112a = C0413b.m112a("");
            sbM112a.append(((int) (((fMin2 * 100.0f) / Math.abs(f11 - f9)) + 0.5d)) / 100.0f);
            String string = sbM112a.toString();
            getTextBounds(string, this.mTextPaint);
            canvas.drawText(string, ((fMin2 / 2.0f) - (this.mBounds.width() / 2)) + fMin, f8 - 20.0f, this.mTextPaint);
            canvas.drawLine(f7, f8, Math.min(f9, f11), f8, this.mPaintGraph);
            StringBuilder sbM112a2 = C0413b.m112a("");
            sbM112a2.append(((int) (((fMax2 * 100.0f) / Math.abs(f12 - f10)) + 0.5d)) / 100.0f);
            String string2 = sbM112a2.toString();
            getTextBounds(string2, this.mTextPaint);
            canvas.drawText(string2, f7 + 5.0f, fMax - ((fMax2 / 2.0f) - (this.mBounds.height() / 2)), this.mTextPaint);
            canvas.drawLine(f7, f8, f7, Math.max(f10, f12), this.mPaintGraph);
        }

        private void drawPathRelative(Canvas canvas) {
            float[] fArr = this.mPoints;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.mPaintGraph);
        }

        private void drawPathRelativeTicks(Canvas canvas, float f7, float f8) {
            float[] fArr = this.mPoints;
            float f9 = fArr[0];
            float f10 = fArr[1];
            float f11 = fArr[fArr.length - 2];
            float f12 = fArr[fArr.length - 1];
            float fHypot = (float) Math.hypot(f9 - f11, f10 - f12);
            float f13 = f11 - f9;
            float f14 = f12 - f10;
            float f15 = (((f8 - f10) * f14) + ((f7 - f9) * f13)) / (fHypot * fHypot);
            float f16 = f9 + (f13 * f15);
            float f17 = f10 + (f15 * f14);
            Path path = new Path();
            path.moveTo(f7, f8);
            path.lineTo(f16, f17);
            float fHypot2 = (float) Math.hypot(f16 - f7, f17 - f8);
            StringBuilder sbM112a = C0413b.m112a("");
            sbM112a.append(((int) ((fHypot2 * 100.0f) / fHypot)) / 100.0f);
            String string = sbM112a.toString();
            getTextBounds(string, this.mTextPaint);
            canvas.drawTextOnPath(string, path, (fHypot2 / 2.0f) - (this.mBounds.width() / 2), -20.0f, this.mTextPaint);
            canvas.drawLine(f7, f8, f16, f17, this.mPaintGraph);
        }

        private void drawPathScreenTicks(Canvas canvas, float f7, float f8, int i7, int i8) {
            StringBuilder sbM112a = C0413b.m112a("");
            sbM112a.append(((int) ((((f7 - (i7 / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i7)) + 0.5d)) / 100.0f);
            String string = sbM112a.toString();
            getTextBounds(string, this.mTextPaint);
            canvas.drawText(string, ((f7 / 2.0f) - (this.mBounds.width() / 2)) + 0.0f, f8 - 20.0f, this.mTextPaint);
            canvas.drawLine(f7, f8, Math.min(0.0f, 1.0f), f8, this.mPaintGraph);
            StringBuilder sbM112a2 = C0413b.m112a("");
            sbM112a2.append(((int) ((((f8 - (i8 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i8)) + 0.5d)) / 100.0f);
            String string2 = sbM112a2.toString();
            getTextBounds(string2, this.mTextPaint);
            canvas.drawText(string2, f7 + 5.0f, 0.0f - ((f8 / 2.0f) - (this.mBounds.height() / 2)), this.mTextPaint);
            canvas.drawLine(f7, f8, f7, Math.max(0.0f, 1.0f), this.mPaintGraph);
        }

        private void drawRectangle(Canvas canvas, MotionController motionController) {
            this.mPath.reset();
            for (int i7 = 0; i7 <= 50; i7++) {
                motionController.buildRect(i7 / 50, this.mRectangle, 0);
                Path path = this.mPath;
                float[] fArr = this.mRectangle;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.mPath;
                float[] fArr2 = this.mRectangle;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.mPath;
                float[] fArr3 = this.mRectangle;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.mPath;
                float[] fArr4 = this.mRectangle;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.mPath.close();
            }
            this.mPaint.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.mPath, this.mPaint);
            canvas.translate(-2.0f, -2.0f);
            this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
            canvas.drawPath(this.mPath, this.mPaint);
        }

        private void drawTicks(Canvas canvas, int i7, int i8, MotionController motionController) {
            int width;
            int height;
            float f7;
            float f8;
            int i9;
            View view = motionController.mView;
            if (view != null) {
                width = view.getWidth();
                height = motionController.mView.getHeight();
            } else {
                width = 0;
                height = 0;
            }
            for (int i10 = 1; i10 < i8 - 1; i10++) {
                if (i7 != 4 || this.mPathMode[i10 - 1] != 0) {
                    float[] fArr = this.mKeyFramePoints;
                    int i11 = i10 * 2;
                    float f9 = fArr[i11];
                    float f10 = fArr[i11 + 1];
                    this.mPath.reset();
                    this.mPath.moveTo(f9, f10 + 10.0f);
                    this.mPath.lineTo(f9 + 10.0f, f10);
                    this.mPath.lineTo(f9, f10 - 10.0f);
                    this.mPath.lineTo(f9 - 10.0f, f10);
                    this.mPath.close();
                    int i12 = i10 - 1;
                    motionController.getKeyFrame(i12);
                    if (i7 == 4) {
                        int[] iArr = this.mPathMode;
                        if (iArr[i12] == 1) {
                            drawPathRelativeTicks(canvas, f9 - 0.0f, f10 - 0.0f);
                        } else if (iArr[i12] == 2) {
                            drawPathCartesianTicks(canvas, f9 - 0.0f, f10 - 0.0f);
                        } else {
                            if (iArr[i12] == 3) {
                                i9 = 3;
                                f7 = f10;
                                f8 = f9;
                                drawPathScreenTicks(canvas, f9 - 0.0f, f10 - 0.0f, width, height);
                            }
                            canvas.drawPath(this.mPath, this.mFillPaint);
                        }
                        f7 = f10;
                        f8 = f9;
                        i9 = 3;
                        canvas.drawPath(this.mPath, this.mFillPaint);
                    } else {
                        f7 = f10;
                        f8 = f9;
                        i9 = 3;
                    }
                    if (i7 == 2) {
                        drawPathRelativeTicks(canvas, f8 - 0.0f, f7 - 0.0f);
                    }
                    if (i7 == i9) {
                        drawPathCartesianTicks(canvas, f8 - 0.0f, f7 - 0.0f);
                    }
                    if (i7 == 6) {
                        drawPathScreenTicks(canvas, f8 - 0.0f, f7 - 0.0f, width, height);
                    }
                    canvas.drawPath(this.mPath, this.mFillPaint);
                }
            }
            float[] fArr2 = this.mPoints;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.mPaintKeyframes);
                float[] fArr3 = this.mPoints;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.mPaintKeyframes);
            }
        }

        private void drawTranslation(Canvas canvas, float f7, float f8, float f9, float f10) {
            canvas.drawRect(f7, f8, f9, f10, this.mPaintGraph);
            canvas.drawLine(f7, f8, f9, f10, this.mPaintGraph);
        }

        public void draw(Canvas canvas, HashMap<View, MotionController> map, int i7, int i8) {
            if (map == null || map.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i8 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.mEndState) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.mTextPaint);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.mPaint);
            }
            for (MotionController motionController : map.values()) {
                int drawPath = motionController.getDrawPath();
                if (i8 > 0 && drawPath == 0) {
                    drawPath = 1;
                }
                if (drawPath != 0) {
                    this.mKeyFrameCount = motionController.buildKeyFrames(this.mKeyFramePoints, this.mPathMode);
                    if (drawPath >= 1) {
                        int i9 = i7 / 16;
                        float[] fArr = this.mPoints;
                        if (fArr == null || fArr.length != i9 * 2) {
                            this.mPoints = new float[i9 * 2];
                            this.mPath = new Path();
                        }
                        int i10 = this.mShadowTranslate;
                        canvas.translate(i10, i10);
                        this.mPaint.setColor(1996488704);
                        this.mFillPaint.setColor(1996488704);
                        this.mPaintKeyframes.setColor(1996488704);
                        this.mPaintGraph.setColor(1996488704);
                        motionController.buildPath(this.mPoints, i9);
                        drawAll(canvas, drawPath, this.mKeyFrameCount, motionController);
                        this.mPaint.setColor(-21965);
                        this.mPaintKeyframes.setColor(-2067046);
                        this.mFillPaint.setColor(-2067046);
                        this.mPaintGraph.setColor(-13391360);
                        int i11 = this.mShadowTranslate;
                        canvas.translate(-i11, -i11);
                        drawAll(canvas, drawPath, this.mKeyFrameCount, motionController);
                        if (drawPath == 5) {
                            drawRectangle(canvas, motionController);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public void drawAll(Canvas canvas, int i7, int i8, MotionController motionController) {
            if (i7 == 4) {
                drawPathAsConfigured(canvas);
            }
            if (i7 == 2) {
                drawPathRelative(canvas);
            }
            if (i7 == 3) {
                drawPathCartesian(canvas);
            }
            drawBasicPath(canvas);
            drawTicks(canvas, i7, i8, motionController);
        }

        public void getTextBounds(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.mBounds);
        }
    }

    public class Model {
        public int mEndId;
        public int mStartId;
        public ConstraintWidgetContainer mLayoutStart = new ConstraintWidgetContainer();
        public ConstraintWidgetContainer mLayoutEnd = new ConstraintWidgetContainer();
        public ConstraintSet mStart = null;
        public ConstraintSet mEnd = null;

        public Model() {
        }

        private void debugLayout(String str, ConstraintWidgetContainer constraintWidgetContainer) {
            View view = (View) constraintWidgetContainer.getCompanionWidget();
            StringBuilder sbM94a = C0080b.m94a(str, " ");
            sbM94a.append(Debug.getName(view));
            String string = sbM94a.toString();
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append("  ========= ");
            sb.append(constraintWidgetContainer);
            int size = constraintWidgetContainer.getChildren().size();
            for (int i7 = 0; i7 < size; i7++) {
                String str2 = string + "[" + i7 + "] ";
                ConstraintWidget constraintWidget = constraintWidgetContainer.getChildren().get(i7);
                StringBuilder sbM112a = C0413b.m112a("");
                sbM112a.append(constraintWidget.mTop.mTarget != null ? "T" : "_");
                StringBuilder sbM112a2 = C0413b.m112a(sbM112a.toString());
                sbM112a2.append(constraintWidget.mBottom.mTarget != null ? "B" : "_");
                StringBuilder sbM112a3 = C0413b.m112a(sbM112a2.toString());
                sbM112a3.append(constraintWidget.mLeft.mTarget != null ? "L" : "_");
                StringBuilder sbM112a4 = C0413b.m112a(sbM112a3.toString());
                sbM112a4.append(constraintWidget.mRight.mTarget != null ? "R" : "_");
                String string2 = sbM112a4.toString();
                View view2 = (View) constraintWidget.getCompanionWidget();
                String name = Debug.getName(view2);
                if (view2 instanceof TextView) {
                    StringBuilder sbM94a2 = C0080b.m94a(name, "(");
                    sbM94a2.append((Object) ((TextView) view2).getText());
                    sbM94a2.append(")");
                    name = sbM94a2.toString();
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append("  ");
                sb2.append(name);
                sb2.append(" ");
                sb2.append(constraintWidget);
                sb2.append(" ");
                sb2.append(string2);
            }
        }

        private void debugLayoutParam(String str, ConstraintLayout.LayoutParams layoutParams) {
            StringBuilder sbM112a = C0413b.m112a(" ");
            sbM112a.append(layoutParams.startToStart != -1 ? "SS" : "__");
            StringBuilder sbM112a2 = C0413b.m112a(sbM112a.toString());
            sbM112a2.append(layoutParams.startToEnd != -1 ? "|SE" : "|__");
            StringBuilder sbM112a3 = C0413b.m112a(sbM112a2.toString());
            sbM112a3.append(layoutParams.endToStart != -1 ? "|ES" : "|__");
            StringBuilder sbM112a4 = C0413b.m112a(sbM112a3.toString());
            sbM112a4.append(layoutParams.endToEnd != -1 ? "|EE" : "|__");
            StringBuilder sbM112a5 = C0413b.m112a(sbM112a4.toString());
            sbM112a5.append(layoutParams.leftToLeft != -1 ? "|LL" : "|__");
            StringBuilder sbM112a6 = C0413b.m112a(sbM112a5.toString());
            sbM112a6.append(layoutParams.leftToRight != -1 ? "|LR" : "|__");
            StringBuilder sbM112a7 = C0413b.m112a(sbM112a6.toString());
            sbM112a7.append(layoutParams.rightToLeft != -1 ? "|RL" : "|__");
            StringBuilder sbM112a8 = C0413b.m112a(sbM112a7.toString());
            sbM112a8.append(layoutParams.rightToRight != -1 ? "|RR" : "|__");
            StringBuilder sbM112a9 = C0413b.m112a(sbM112a8.toString());
            sbM112a9.append(layoutParams.topToTop != -1 ? "|TT" : "|__");
            StringBuilder sbM112a10 = C0413b.m112a(sbM112a9.toString());
            sbM112a10.append(layoutParams.topToBottom != -1 ? "|TB" : "|__");
            StringBuilder sbM112a11 = C0413b.m112a(sbM112a10.toString());
            sbM112a11.append(layoutParams.bottomToTop != -1 ? "|BT" : "|__");
            C0413b.m112a(sbM112a11.toString()).append(layoutParams.bottomToBottom != -1 ? "|BB" : "|__");
        }

        private void debugWidget(String str, ConstraintWidget constraintWidget) {
            String string;
            String string2;
            String string3;
            StringBuilder sbM112a = C0413b.m112a(" ");
            String string4 = "__";
            if (constraintWidget.mTop.mTarget != null) {
                StringBuilder sbM112a2 = C0413b.m112a("T");
                sbM112a2.append(constraintWidget.mTop.mTarget.mType == ConstraintAnchor.Type.TOP ? "T" : "B");
                string = sbM112a2.toString();
            } else {
                string = "__";
            }
            sbM112a.append(string);
            StringBuilder sbM112a3 = C0413b.m112a(sbM112a.toString());
            if (constraintWidget.mBottom.mTarget != null) {
                StringBuilder sbM112a4 = C0413b.m112a("B");
                sbM112a4.append(constraintWidget.mBottom.mTarget.mType == ConstraintAnchor.Type.TOP ? "T" : "B");
                string2 = sbM112a4.toString();
            } else {
                string2 = "__";
            }
            sbM112a3.append(string2);
            StringBuilder sbM112a5 = C0413b.m112a(sbM112a3.toString());
            if (constraintWidget.mLeft.mTarget != null) {
                StringBuilder sbM112a6 = C0413b.m112a("L");
                sbM112a6.append(constraintWidget.mLeft.mTarget.mType == ConstraintAnchor.Type.LEFT ? "L" : "R");
                string3 = sbM112a6.toString();
            } else {
                string3 = "__";
            }
            sbM112a5.append(string3);
            StringBuilder sbM112a7 = C0413b.m112a(sbM112a5.toString());
            if (constraintWidget.mRight.mTarget != null) {
                StringBuilder sbM112a8 = C0413b.m112a("R");
                sbM112a8.append(constraintWidget.mRight.mTarget.mType == ConstraintAnchor.Type.LEFT ? "L" : "R");
                string4 = sbM112a8.toString();
            }
            sbM112a7.append(string4);
            String string5 = sbM112a7.toString();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(string5);
            sb.append(" ---  ");
            sb.append(constraintWidget);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void setupConstraintWidget(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet) {
            SparseArray<ConstraintWidget> sparseArray = new SparseArray<>();
            Constraints.LayoutParams layoutParams = new Constraints.LayoutParams(-2, -2);
            sparseArray.clear();
            sparseArray.put(0, constraintWidgetContainer);
            sparseArray.put(MotionLayout.this.getId(), constraintWidgetContainer);
            Iterator<ConstraintWidget> it = constraintWidgetContainer.getChildren().iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                sparseArray.put(((View) next.getCompanionWidget()).getId(), next);
            }
            Iterator<ConstraintWidget> it2 = constraintWidgetContainer.getChildren().iterator();
            while (it2.hasNext()) {
                ConstraintWidget next2 = it2.next();
                View view = (View) next2.getCompanionWidget();
                constraintSet.applyToLayoutParams(view.getId(), layoutParams);
                next2.setWidth(constraintSet.getWidth(view.getId()));
                next2.setHeight(constraintSet.getHeight(view.getId()));
                if (view instanceof ConstraintHelper) {
                    constraintSet.applyToHelper((ConstraintHelper) view, next2, layoutParams, sparseArray);
                    if (view instanceof Barrier) {
                        ((Barrier) view).validateParams();
                    }
                }
                layoutParams.resolveLayoutDirection(MotionLayout.this.getLayoutDirection());
                MotionLayout.this.applyConstraintsFromLayoutParams(false, view, next2, layoutParams, sparseArray);
                if (constraintSet.getVisibilityMode(view.getId()) == 1) {
                    next2.setVisibility(view.getVisibility());
                } else {
                    next2.setVisibility(constraintSet.getVisibility(view.getId()));
                }
            }
            Iterator<ConstraintWidget> it3 = constraintWidgetContainer.getChildren().iterator();
            while (it3.hasNext()) {
                ConstraintWidget next3 = it3.next();
                if (next3 instanceof VirtualLayout) {
                    ConstraintHelper constraintHelper = (ConstraintHelper) next3.getCompanionWidget();
                    Helper helper = (Helper) next3;
                    constraintHelper.updatePreLayout(constraintWidgetContainer, helper, sparseArray);
                    ((VirtualLayout) helper).captureWidgets();
                }
            }
        }

        public void build() {
            int childCount = MotionLayout.this.getChildCount();
            MotionLayout.this.mFrameArrayList.clear();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = MotionLayout.this.getChildAt(i7);
                MotionLayout.this.mFrameArrayList.put(childAt, new MotionController(childAt));
            }
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt2 = MotionLayout.this.getChildAt(i8);
                MotionController motionController = MotionLayout.this.mFrameArrayList.get(childAt2);
                if (motionController != null) {
                    if (this.mStart != null) {
                        ConstraintWidget widget = getWidget(this.mLayoutStart, childAt2);
                        if (widget != null) {
                            motionController.setStartState(widget, this.mStart);
                        } else if (MotionLayout.this.mDebugPath != 0) {
                            Debug.getLocation();
                            Debug.getName(childAt2);
                            childAt2.getClass();
                        }
                    }
                    if (this.mEnd != null) {
                        ConstraintWidget widget2 = getWidget(this.mLayoutEnd, childAt2);
                        if (widget2 != null) {
                            motionController.setEndState(widget2, this.mEnd);
                        } else if (MotionLayout.this.mDebugPath != 0) {
                            Debug.getLocation();
                            Debug.getName(childAt2);
                            childAt2.getClass();
                        }
                    }
                }
            }
        }

        public void copy(ConstraintWidgetContainer constraintWidgetContainer, ConstraintWidgetContainer constraintWidgetContainer2) {
            ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
            HashMap<ConstraintWidget, ConstraintWidget> map = new HashMap<>();
            map.put(constraintWidgetContainer, constraintWidgetContainer2);
            constraintWidgetContainer2.getChildren().clear();
            constraintWidgetContainer2.copy(constraintWidgetContainer, map);
            Iterator<ConstraintWidget> it = children.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                ConstraintWidget barrier = next instanceof android.support.constraint.solver.widgets.Barrier ? new android.support.constraint.solver.widgets.Barrier() : next instanceof Guideline ? new Guideline() : next instanceof Flow ? new Flow() : next instanceof Helper ? new HelperWidget() : new ConstraintWidget();
                constraintWidgetContainer2.add(barrier);
                map.put(next, barrier);
            }
            Iterator<ConstraintWidget> it2 = children.iterator();
            while (it2.hasNext()) {
                ConstraintWidget next2 = it2.next();
                map.get(next2).copy(next2, map);
            }
        }

        public ConstraintWidget getWidget(ConstraintWidgetContainer constraintWidgetContainer, View view) {
            if (constraintWidgetContainer.getCompanionWidget() == view) {
                return constraintWidgetContainer;
            }
            ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
            int size = children.size();
            for (int i7 = 0; i7 < size; i7++) {
                ConstraintWidget constraintWidget = children.get(i7);
                if (constraintWidget.getCompanionWidget() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        public void initFrom(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet, ConstraintSet constraintSet2) {
            this.mStart = constraintSet;
            this.mEnd = constraintSet2;
            this.mLayoutStart = new ConstraintWidgetContainer();
            this.mLayoutEnd = new ConstraintWidgetContainer();
            this.mLayoutStart.setMeasurer(MotionLayout.this.mLayoutWidget.getMeasurer());
            this.mLayoutEnd.setMeasurer(MotionLayout.this.mLayoutWidget.getMeasurer());
            this.mLayoutStart.removeAllChildren();
            this.mLayoutEnd.removeAllChildren();
            copy(MotionLayout.this.mLayoutWidget, this.mLayoutStart);
            copy(MotionLayout.this.mLayoutWidget, this.mLayoutEnd);
            if (MotionLayout.this.mTransitionLastPosition > 0.5d) {
                if (constraintSet != null) {
                    setupConstraintWidget(this.mLayoutStart, constraintSet);
                }
                setupConstraintWidget(this.mLayoutEnd, constraintSet2);
            } else {
                setupConstraintWidget(this.mLayoutEnd, constraintSet2);
                if (constraintSet != null) {
                    setupConstraintWidget(this.mLayoutStart, constraintSet);
                }
            }
            this.mLayoutStart.setRtl(MotionLayout.this.isRtl());
            this.mLayoutStart.updateHierarchy();
            this.mLayoutEnd.setRtl(MotionLayout.this.isRtl());
            this.mLayoutEnd.updateHierarchy();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.mLayoutStart;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    constraintWidgetContainer2.setHorizontalDimensionBehaviour(dimensionBehaviour);
                    this.mLayoutEnd.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (layoutParams.height == -2) {
                    ConstraintWidgetContainer constraintWidgetContainer3 = this.mLayoutStart;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    constraintWidgetContainer3.setVerticalDimensionBehaviour(dimensionBehaviour2);
                    this.mLayoutEnd.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            }
        }

        public boolean isNotConfiguredWith(int i7, int i8) {
            return (i7 == this.mStartId && i8 == this.mEndId) ? false : true;
        }

        public void measure(int i7, int i8) {
            int mode = View.MeasureSpec.getMode(i7);
            int mode2 = View.MeasureSpec.getMode(i8);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.mWidthMeasureMode = mode;
            motionLayout.mHeightMeasureMode = mode2;
            int optimizationLevel = motionLayout.getOptimizationLevel();
            MotionLayout motionLayout2 = MotionLayout.this;
            if (motionLayout2.mCurrentState == motionLayout2.getStartState()) {
                MotionLayout.this.resolveSystem(this.mLayoutEnd, optimizationLevel, i7, i8);
                if (this.mStart != null) {
                    MotionLayout.this.resolveSystem(this.mLayoutStart, optimizationLevel, i7, i8);
                }
            } else {
                if (this.mStart != null) {
                    MotionLayout.this.resolveSystem(this.mLayoutStart, optimizationLevel, i7, i8);
                }
                MotionLayout.this.resolveSystem(this.mLayoutEnd, optimizationLevel, i7, i8);
            }
            if (((MotionLayout.this.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                MotionLayout motionLayout3 = MotionLayout.this;
                motionLayout3.mWidthMeasureMode = mode;
                motionLayout3.mHeightMeasureMode = mode2;
                if (motionLayout3.mCurrentState == motionLayout3.getStartState()) {
                    MotionLayout.this.resolveSystem(this.mLayoutEnd, optimizationLevel, i7, i8);
                    if (this.mStart != null) {
                        MotionLayout.this.resolveSystem(this.mLayoutStart, optimizationLevel, i7, i8);
                    }
                } else {
                    if (this.mStart != null) {
                        MotionLayout.this.resolveSystem(this.mLayoutStart, optimizationLevel, i7, i8);
                    }
                    MotionLayout.this.resolveSystem(this.mLayoutEnd, optimizationLevel, i7, i8);
                }
                MotionLayout.this.mStartWrapWidth = this.mLayoutStart.getWidth();
                MotionLayout.this.mStartWrapHeight = this.mLayoutStart.getHeight();
                MotionLayout.this.mEndWrapWidth = this.mLayoutEnd.getWidth();
                MotionLayout.this.mEndWrapHeight = this.mLayoutEnd.getHeight();
                MotionLayout motionLayout4 = MotionLayout.this;
                motionLayout4.mMeasureDuringTransition = (motionLayout4.mStartWrapWidth == motionLayout4.mEndWrapWidth && motionLayout4.mStartWrapHeight == motionLayout4.mEndWrapHeight) ? false : true;
            }
            MotionLayout motionLayout5 = MotionLayout.this;
            int i9 = motionLayout5.mStartWrapWidth;
            int i10 = motionLayout5.mStartWrapHeight;
            int i11 = motionLayout5.mWidthMeasureMode;
            if (i11 == Integer.MIN_VALUE || i11 == 0) {
                i9 = (int) ((motionLayout5.mPostInterpolationPosition * (motionLayout5.mEndWrapWidth - i9)) + i9);
            }
            int i12 = motionLayout5.mHeightMeasureMode;
            if (i12 == Integer.MIN_VALUE || i12 == 0) {
                i10 = (int) ((motionLayout5.mPostInterpolationPosition * (motionLayout5.mEndWrapHeight - i10)) + i10);
            }
            MotionLayout.this.resolveMeasuredDimension(i7, i8, i9, i10, this.mLayoutStart.isWidthMeasuredTooSmall() || this.mLayoutEnd.isWidthMeasuredTooSmall(), this.mLayoutStart.isHeightMeasuredTooSmall() || this.mLayoutEnd.isHeightMeasuredTooSmall());
        }

        public void reEvaluateState() {
            measure(MotionLayout.this.mLastWidthMeasureSpec, MotionLayout.this.mLastHeightMeasureSpec);
            MotionLayout.this.setupMotionViews();
        }

        public void setMeasuredId(int i7, int i8) {
            this.mStartId = i7;
            this.mEndId = i8;
        }
    }

    public interface MotionTracker {
        void addMovement(MotionEvent motionEvent);

        void clear();

        void computeCurrentVelocity(int i7);

        void computeCurrentVelocity(int i7, float f7);

        float getXVelocity();

        float getXVelocity(int i7);

        float getYVelocity();

        float getYVelocity(int i7);

        void recycle();
    }

    public class StateCache {
        public float mProgress = Float.NaN;
        public float mVelocity = Float.NaN;
        public int startState = -1;
        public int endState = -1;
        public final String KeyProgress = "motion.progress";
        public final String KeyVelocity = "motion.velocity";
        public final String KeyStartState = "motion.StartState";
        public final String KeyEndState = "motion.EndState";

        public StateCache() {
        }

        public void apply() {
            int i7 = this.startState;
            if (i7 != -1 || this.endState != -1) {
                if (i7 == -1) {
                    MotionLayout.this.transitionToState(this.endState);
                } else {
                    int i8 = this.endState;
                    if (i8 == -1) {
                        MotionLayout.this.setState(i7, -1, -1);
                    } else {
                        MotionLayout.this.setTransition(i7, i8);
                    }
                }
                MotionLayout.this.setState(TransitionState.SETUP);
            }
            if (Float.isNaN(this.mVelocity)) {
                if (Float.isNaN(this.mProgress)) {
                    return;
                }
                MotionLayout.this.setProgress(this.mProgress);
            } else {
                MotionLayout.this.setProgress(this.mProgress, this.mVelocity);
                this.mProgress = Float.NaN;
                this.mVelocity = Float.NaN;
                this.startState = -1;
                this.endState = -1;
            }
        }

        public Bundle getTransitionState() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.mProgress);
            bundle.putFloat("motion.velocity", this.mVelocity);
            bundle.putInt("motion.StartState", this.startState);
            bundle.putInt("motion.EndState", this.endState);
            return bundle;
        }

        public void recordState() {
            this.endState = MotionLayout.this.mEndState;
            this.startState = MotionLayout.this.mBeginState;
            this.mVelocity = MotionLayout.this.getVelocity();
            this.mProgress = MotionLayout.this.getProgress();
        }

        public void setEndState(int i7) {
            this.endState = i7;
        }

        public void setProgress(float f7) {
            this.mProgress = f7;
        }

        public void setStartState(int i7) {
            this.startState = i7;
        }

        public void setTransitionState(Bundle bundle) {
            this.mProgress = bundle.getFloat("motion.progress");
            this.mVelocity = bundle.getFloat("motion.velocity");
            this.startState = bundle.getInt("motion.StartState");
            this.endState = bundle.getInt("motion.EndState");
        }

        public void setVelocity(float f7) {
            this.mVelocity = f7;
        }
    }

    public interface TransitionListener {
        void onTransitionChange(MotionLayout motionLayout, int i7, int i8, float f7);

        void onTransitionCompleted(MotionLayout motionLayout, int i7);

        void onTransitionStarted(MotionLayout motionLayout, int i7, int i8);

        void onTransitionTrigger(MotionLayout motionLayout, int i7, boolean z6, float f7);
    }

    public enum TransitionState {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public MotionLayout(@NonNull Context context) {
        super(context);
        this.mLastVelocity = 0.0f;
        this.mBeginState = -1;
        this.mCurrentState = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.mFrameArrayList = new HashMap<>();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionGoalPosition = 0.0f;
        this.mInTransition = false;
        this.mIndirectTransition = false;
        this.mDebugPath = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator();
        this.firstDown = true;
        this.mUndergoingMotion = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.mIsAnimating = false;
        this.mMeasureDuringTransition = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mTransitionState = TransitionState.UNDEFINED;
        this.mModel = new Model();
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mTransitionCompleted = new ArrayList<>();
        init(null);
    }

    private void checkStructure() {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return;
        }
        int startId = motionScene.getStartId();
        MotionScene motionScene2 = this.mScene;
        checkStructure(startId, motionScene2.getConstraintSet(motionScene2.getStartId()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        Iterator<MotionScene.Transition> it = this.mScene.getDefinedTransitions().iterator();
        while (it.hasNext()) {
            MotionScene.Transition next = it.next();
            MotionScene.Transition transition = this.mScene.mCurrentTransition;
            checkStructure(next);
            int startConstraintSetId = next.getStartConstraintSetId();
            int endConstraintSetId = next.getEndConstraintSetId();
            Debug.getName(getContext(), startConstraintSetId);
            Debug.getName(getContext(), endConstraintSetId);
            sparseIntArray.get(startConstraintSetId);
            sparseIntArray2.get(endConstraintSetId);
            sparseIntArray.put(startConstraintSetId, endConstraintSetId);
            sparseIntArray2.put(endConstraintSetId, startConstraintSetId);
            this.mScene.getConstraintSet(startConstraintSetId);
            this.mScene.getConstraintSet(endConstraintSetId);
        }
    }

    private void computeCurrentPositions() {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            MotionController motionController = this.mFrameArrayList.get(childAt);
            if (motionController != null) {
                motionController.setStartCurrentState(childAt);
            }
        }
    }

    private void debugPos() {
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            Debug.getLocation();
            Debug.getName(this);
            Debug.getName(getContext(), this.mCurrentState);
            Debug.getName(childAt);
            childAt.getLeft();
            childAt.getTop();
        }
    }

    private void evaluateLayout() {
        boolean z6;
        float fSignum = Math.signum(this.mTransitionGoalPosition - this.mTransitionLastPosition);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.mInterpolator;
        float interpolation = this.mTransitionLastPosition + (!(interpolator instanceof StopLogic) ? (((nanoTime - this.mTransitionLastTime) * fSignum) * 1.0E-9f) / this.mTransitionDuration : 0.0f);
        if (this.mTransitionInstantly) {
            interpolation = this.mTransitionGoalPosition;
        }
        if ((fSignum <= 0.0f || interpolation < this.mTransitionGoalPosition) && (fSignum > 0.0f || interpolation > this.mTransitionGoalPosition)) {
            z6 = false;
        } else {
            interpolation = this.mTransitionGoalPosition;
            z6 = true;
        }
        if (interpolator != null && !z6) {
            interpolation = this.mTemporalInterpolator ? interpolator.getInterpolation((nanoTime - this.mAnimationStartTime) * 1.0E-9f) : interpolator.getInterpolation(interpolation);
        }
        if ((fSignum > 0.0f && interpolation >= this.mTransitionGoalPosition) || (fSignum <= 0.0f && interpolation <= this.mTransitionGoalPosition)) {
            interpolation = this.mTransitionGoalPosition;
        }
        this.mPostInterpolationPosition = interpolation;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            MotionController motionController = this.mFrameArrayList.get(childAt);
            if (motionController != null) {
                motionController.interpolate(childAt, interpolation, nanoTime2, this.mKeyCache);
            }
        }
        if (this.mMeasureDuringTransition) {
            requestLayout();
        }
    }

    private void fireTransitionChange() {
        ArrayList<TransitionListener> arrayList;
        if ((this.mTransitionListener == null && ((arrayList = this.mTransitionListeners) == null || arrayList.isEmpty())) || this.mListenerPosition == this.mTransitionPosition) {
            return;
        }
        if (this.mListenerState != -1) {
            TransitionListener transitionListener = this.mTransitionListener;
            if (transitionListener != null) {
                transitionListener.onTransitionStarted(this, this.mBeginState, this.mEndState);
            }
            ArrayList<TransitionListener> arrayList2 = this.mTransitionListeners;
            if (arrayList2 != null) {
                Iterator<TransitionListener> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().onTransitionStarted(this, this.mBeginState, this.mEndState);
                }
            }
            this.mIsAnimating = true;
        }
        this.mListenerState = -1;
        float f7 = this.mTransitionPosition;
        this.mListenerPosition = f7;
        TransitionListener transitionListener2 = this.mTransitionListener;
        if (transitionListener2 != null) {
            transitionListener2.onTransitionChange(this, this.mBeginState, this.mEndState, f7);
        }
        ArrayList<TransitionListener> arrayList3 = this.mTransitionListeners;
        if (arrayList3 != null) {
            Iterator<TransitionListener> it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                it2.next().onTransitionChange(this, this.mBeginState, this.mEndState, this.mTransitionPosition);
            }
        }
        this.mIsAnimating = true;
    }

    private void fireTransitionStarted(MotionLayout motionLayout, int i7, int i8) {
        TransitionListener transitionListener = this.mTransitionListener;
        if (transitionListener != null) {
            transitionListener.onTransitionStarted(this, i7, i8);
        }
        ArrayList<TransitionListener> arrayList = this.mTransitionListeners;
        if (arrayList != null) {
            Iterator<TransitionListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onTransitionStarted(motionLayout, i7, i8);
            }
        }
    }

    private boolean handlesTouchEvent(float f7, float f8, View view, MotionEvent motionEvent) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                if (handlesTouchEvent(view.getLeft() + f7, view.getTop() + f8, viewGroup.getChildAt(i7), motionEvent)) {
                    return true;
                }
            }
        }
        this.mBoundsCheck.set(view.getLeft() + f7, view.getTop() + f8, f7 + view.getRight(), f8 + view.getBottom());
        if (motionEvent.getAction() == 0) {
            if (this.mBoundsCheck.contains(motionEvent.getX(), motionEvent.getY()) && view.onTouchEvent(motionEvent)) {
                return true;
            }
        } else if (view.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    private void init(AttributeSet attributeSet) {
        MotionScene motionScene;
        IS_IN_EDIT_MODE = isInEditMode();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0071R.styleable.MotionLayout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            boolean z6 = true;
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == C0071R.styleable.MotionLayout_layoutDescription) {
                    this.mScene = new MotionScene(getContext(), this, typedArrayObtainStyledAttributes.getResourceId(index, -1));
                } else if (index == C0071R.styleable.MotionLayout_currentState) {
                    this.mCurrentState = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                } else if (index == C0071R.styleable.MotionLayout_motionProgress) {
                    this.mTransitionGoalPosition = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                    this.mInTransition = true;
                } else if (index == C0071R.styleable.MotionLayout_applyMotionScene) {
                    z6 = typedArrayObtainStyledAttributes.getBoolean(index, z6);
                } else if (index == C0071R.styleable.MotionLayout_showPaths) {
                    if (this.mDebugPath == 0) {
                        this.mDebugPath = typedArrayObtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == C0071R.styleable.MotionLayout_motionDebug) {
                    this.mDebugPath = typedArrayObtainStyledAttributes.getInt(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (!z6) {
                this.mScene = null;
            }
        }
        if (this.mDebugPath != 0) {
            checkStructure();
        }
        if (this.mCurrentState != -1 || (motionScene = this.mScene) == null) {
            return;
        }
        this.mCurrentState = motionScene.getStartId();
        this.mBeginState = this.mScene.getStartId();
        this.mEndState = this.mScene.getEndId();
    }

    private void processTransitionCompleted() {
        ArrayList<TransitionListener> arrayList;
        if (this.mTransitionListener == null && ((arrayList = this.mTransitionListeners) == null || arrayList.isEmpty())) {
            return;
        }
        this.mIsAnimating = false;
        Iterator<Integer> it = this.mTransitionCompleted.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            TransitionListener transitionListener = this.mTransitionListener;
            if (transitionListener != null) {
                transitionListener.onTransitionCompleted(this, next.intValue());
            }
            ArrayList<TransitionListener> arrayList2 = this.mTransitionListeners;
            if (arrayList2 != null) {
                Iterator<TransitionListener> it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    it2.next().onTransitionCompleted(this, next.intValue());
                }
            }
        }
        this.mTransitionCompleted.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupMotionViews() {
        int childCount = getChildCount();
        this.mModel.build();
        boolean z6 = true;
        this.mInTransition = true;
        int width = getWidth();
        int height = getHeight();
        int iGatPathMotionArc = this.mScene.gatPathMotionArc();
        int i7 = 0;
        if (iGatPathMotionArc != -1) {
            for (int i8 = 0; i8 < childCount; i8++) {
                MotionController motionController = this.mFrameArrayList.get(getChildAt(i8));
                if (motionController != null) {
                    motionController.setPathMotionArc(iGatPathMotionArc);
                }
            }
        }
        for (int i9 = 0; i9 < childCount; i9++) {
            MotionController motionController2 = this.mFrameArrayList.get(getChildAt(i9));
            if (motionController2 != null) {
                this.mScene.getKeyFrames(motionController2);
                motionController2.setup(width, height, this.mTransitionDuration, getNanoTime());
            }
        }
        float staggered = this.mScene.getStaggered();
        if (staggered != 0.0f) {
            boolean z7 = ((double) staggered) < 0.0d;
            float fAbs = Math.abs(staggered);
            float fMax = -3.4028235E38f;
            float fMin = Float.MAX_VALUE;
            int i10 = 0;
            float fMin2 = Float.MAX_VALUE;
            float fMax2 = -3.4028235E38f;
            while (true) {
                if (i10 >= childCount) {
                    z6 = false;
                    break;
                }
                MotionController motionController3 = this.mFrameArrayList.get(getChildAt(i10));
                if (!Float.isNaN(motionController3.mMotionStagger)) {
                    break;
                }
                float finalX = motionController3.getFinalX();
                float finalY = motionController3.getFinalY();
                float f7 = z7 ? finalY - finalX : finalY + finalX;
                fMin2 = Math.min(fMin2, f7);
                fMax2 = Math.max(fMax2, f7);
                i10++;
            }
            if (!z6) {
                while (i7 < childCount) {
                    MotionController motionController4 = this.mFrameArrayList.get(getChildAt(i7));
                    float finalX2 = motionController4.getFinalX();
                    float finalY2 = motionController4.getFinalY();
                    float f8 = z7 ? finalY2 - finalX2 : finalY2 + finalX2;
                    motionController4.mStaggerScale = 1.0f / (1.0f - fAbs);
                    motionController4.mStaggerOffset = fAbs - (((f8 - fMin2) * fAbs) / (fMax2 - fMin2));
                    i7++;
                }
                return;
            }
            for (int i11 = 0; i11 < childCount; i11++) {
                MotionController motionController5 = this.mFrameArrayList.get(getChildAt(i11));
                if (!Float.isNaN(motionController5.mMotionStagger)) {
                    fMin = Math.min(fMin, motionController5.mMotionStagger);
                    fMax = Math.max(fMax, motionController5.mMotionStagger);
                }
            }
            while (i7 < childCount) {
                MotionController motionController6 = this.mFrameArrayList.get(getChildAt(i7));
                if (!Float.isNaN(motionController6.mMotionStagger)) {
                    motionController6.mStaggerScale = 1.0f / (1.0f - fAbs);
                    if (z7) {
                        motionController6.mStaggerOffset = fAbs - (((fMax - motionController6.mMotionStagger) / (fMax - fMin)) * fAbs);
                    } else {
                        motionController6.mStaggerOffset = fAbs - (((motionController6.mMotionStagger - fMin) * fAbs) / (fMax - fMin));
                    }
                }
                i7++;
            }
        }
    }

    private static boolean willJump(float f7, float f8, float f9) {
        if (f7 > 0.0f) {
            float f10 = f7 / f9;
            return ((f7 * f10) - (((f9 * f10) * f10) / 2.0f)) + f8 > 1.0f;
        }
        float f11 = (-f7) / f9;
        return ((((f9 * f11) * f11) / 2.0f) + (f7 * f11)) + f8 < 0.0f;
    }

    public void addTransitionListener(TransitionListener transitionListener) {
        if (this.mTransitionListeners == null) {
            this.mTransitionListeners = new ArrayList<>();
        }
        this.mTransitionListeners.add(transitionListener);
    }

    public void animateTo(float f7) {
        if (this.mScene == null) {
            return;
        }
        float f8 = this.mTransitionLastPosition;
        float f9 = this.mTransitionPosition;
        if (f8 != f9 && this.mTransitionInstantly) {
            this.mTransitionLastPosition = f9;
        }
        float f10 = this.mTransitionLastPosition;
        if (f10 == f7) {
            return;
        }
        this.mTemporalInterpolator = false;
        this.mTransitionGoalPosition = f7;
        this.mTransitionDuration = r0.getDuration() / 1000.0f;
        setProgress(this.mTransitionGoalPosition);
        this.mInterpolator = this.mScene.getInterpolator();
        this.mTransitionInstantly = false;
        this.mAnimationStartTime = getNanoTime();
        this.mInTransition = true;
        this.mTransitionPosition = f10;
        this.mTransitionLastPosition = f10;
        invalidate();
    }

    public void disableAutoTransition(boolean z6) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return;
        }
        motionScene.disableAutoTransition(z6);
    }

    @Override // android.support.constraint.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        evaluate(false);
        super.dispatchDraw(canvas);
        if (this.mScene == null) {
            return;
        }
        if ((this.mDebugPath & 1) == 1 && !isInEditMode()) {
            this.mFrames++;
            long nanoTime = getNanoTime();
            long j7 = this.mLastDrawTime;
            if (j7 != -1) {
                if (nanoTime - j7 > 200000000) {
                    this.mLastFps = ((int) ((this.mFrames / (r5 * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.mFrames = 0;
                    this.mLastDrawTime = nanoTime;
                }
            } else {
                this.mLastDrawTime = nanoTime;
            }
            Paint paint = new Paint();
            paint.setTextSize(42.0f);
            StringBuilder sbM112a = C0413b.m112a(this.mLastFps + " fps " + Debug.getState(this, this.mBeginState) + " -> ");
            sbM112a.append(Debug.getState(this, this.mEndState));
            sbM112a.append(" (progress: ");
            sbM112a.append(((int) (getProgress() * 1000.0f)) / 10.0f);
            sbM112a.append(" ) state=");
            int i7 = this.mCurrentState;
            sbM112a.append(i7 == -1 ? "undefined" : Debug.getState(this, i7));
            String string = sbM112a.toString();
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            canvas.drawText(string, 11.0f, getHeight() - 29, paint);
            paint.setColor(-7864184);
            canvas.drawText(string, 10.0f, getHeight() - 30, paint);
        }
        if (this.mDebugPath > 1) {
            if (this.mDevModeDraw == null) {
                this.mDevModeDraw = new DevModeDraw();
            }
            this.mDevModeDraw.draw(canvas, this.mFrameArrayList, this.mScene.getDuration(), this.mDebugPath);
        }
    }

    public void enableTransition(int i7, boolean z6) {
        MotionScene.Transition transition = getTransition(i7);
        if (z6) {
            transition.setEnable(true);
            return;
        }
        MotionScene motionScene = this.mScene;
        if (transition == motionScene.mCurrentTransition) {
            Iterator<MotionScene.Transition> it = motionScene.getTransitionsWithState(this.mCurrentState).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                MotionScene.Transition next = it.next();
                if (next.isEnabled()) {
                    this.mScene.mCurrentTransition = next;
                    break;
                }
            }
        }
        transition.setEnable(false);
    }

    public void evaluate(boolean z6) {
        float f7;
        boolean z7;
        int i7;
        float interpolation;
        boolean z8;
        if (this.mTransitionLastTime == -1) {
            this.mTransitionLastTime = getNanoTime();
        }
        float f8 = this.mTransitionLastPosition;
        if (f8 > 0.0f && f8 < 1.0f) {
            this.mCurrentState = -1;
        }
        boolean z9 = false;
        if (this.mKeepAnimating || (this.mInTransition && (z6 || this.mTransitionGoalPosition != f8))) {
            float fSignum = Math.signum(this.mTransitionGoalPosition - f8);
            long nanoTime = getNanoTime();
            Interpolator interpolator = this.mInterpolator;
            if (interpolator instanceof MotionInterpolator) {
                f7 = 0.0f;
            } else {
                f7 = (((nanoTime - this.mTransitionLastTime) * fSignum) * 1.0E-9f) / this.mTransitionDuration;
                this.mLastVelocity = f7;
            }
            float f9 = this.mTransitionLastPosition + f7;
            if (this.mTransitionInstantly) {
                f9 = this.mTransitionGoalPosition;
            }
            if ((fSignum <= 0.0f || f9 < this.mTransitionGoalPosition) && (fSignum > 0.0f || f9 > this.mTransitionGoalPosition)) {
                z7 = false;
            } else {
                f9 = this.mTransitionGoalPosition;
                this.mInTransition = false;
                z7 = true;
            }
            this.mTransitionLastPosition = f9;
            this.mTransitionPosition = f9;
            this.mTransitionLastTime = nanoTime;
            if (interpolator != null && !z7) {
                if (this.mTemporalInterpolator) {
                    interpolation = interpolator.getInterpolation((nanoTime - this.mAnimationStartTime) * 1.0E-9f);
                    this.mTransitionLastPosition = interpolation;
                    this.mTransitionLastTime = nanoTime;
                    Interpolator interpolator2 = this.mInterpolator;
                    if (interpolator2 instanceof MotionInterpolator) {
                        float velocity = ((MotionInterpolator) interpolator2).getVelocity();
                        this.mLastVelocity = velocity;
                        if (Math.abs(velocity) * this.mTransitionDuration <= EPSILON) {
                            this.mInTransition = false;
                        }
                        if (velocity > 0.0f && interpolation >= 1.0f) {
                            this.mTransitionLastPosition = 1.0f;
                            this.mInTransition = false;
                            interpolation = 1.0f;
                        }
                        if (velocity < 0.0f && interpolation <= 0.0f) {
                            this.mTransitionLastPosition = 0.0f;
                            this.mInTransition = false;
                            f9 = 0.0f;
                        }
                    }
                } else {
                    interpolation = interpolator.getInterpolation(f9);
                    Interpolator interpolator3 = this.mInterpolator;
                    if (interpolator3 instanceof MotionInterpolator) {
                        this.mLastVelocity = ((MotionInterpolator) interpolator3).getVelocity();
                    } else {
                        this.mLastVelocity = ((interpolator3.getInterpolation(f9 + f7) - interpolation) * fSignum) / f7;
                    }
                }
                f9 = interpolation;
            }
            if (Math.abs(this.mLastVelocity) > EPSILON) {
                setState(TransitionState.MOVING);
            }
            if ((fSignum > 0.0f && f9 >= this.mTransitionGoalPosition) || (fSignum <= 0.0f && f9 <= this.mTransitionGoalPosition)) {
                f9 = this.mTransitionGoalPosition;
                this.mInTransition = false;
            }
            if (f9 >= 1.0f || f9 <= 0.0f) {
                this.mInTransition = false;
                setState(TransitionState.FINISHED);
            }
            int childCount = getChildCount();
            this.mKeepAnimating = false;
            long nanoTime2 = getNanoTime();
            this.mPostInterpolationPosition = f9;
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = getChildAt(i8);
                MotionController motionController = this.mFrameArrayList.get(childAt);
                if (motionController != null) {
                    this.mKeepAnimating = motionController.interpolate(childAt, f9, nanoTime2, this.mKeyCache) | this.mKeepAnimating;
                }
            }
            boolean z10 = (fSignum > 0.0f && f9 >= this.mTransitionGoalPosition) || (fSignum <= 0.0f && f9 <= this.mTransitionGoalPosition);
            if (!this.mKeepAnimating && !this.mInTransition && z10) {
                setState(TransitionState.FINISHED);
            }
            if (this.mMeasureDuringTransition) {
                requestLayout();
            }
            this.mKeepAnimating = (!z10) | this.mKeepAnimating;
            if (f9 <= 0.0f && (i7 = this.mBeginState) != -1 && this.mCurrentState != i7) {
                this.mCurrentState = i7;
                this.mScene.getConstraintSet(i7).applyCustomAttributes(this);
                setState(TransitionState.FINISHED);
                z9 = true;
            }
            if (f9 >= 1.0d) {
                int i9 = this.mCurrentState;
                int i10 = this.mEndState;
                if (i9 != i10) {
                    this.mCurrentState = i10;
                    this.mScene.getConstraintSet(i10).applyCustomAttributes(this);
                    setState(TransitionState.FINISHED);
                    z9 = true;
                }
            }
            if (this.mKeepAnimating || this.mInTransition) {
                invalidate();
            } else if ((fSignum > 0.0f && f9 == 1.0f) || (fSignum < 0.0f && f9 == 0.0f)) {
                setState(TransitionState.FINISHED);
            }
            if ((!this.mKeepAnimating && this.mInTransition && fSignum > 0.0f && f9 == 1.0f) || (fSignum < 0.0f && f9 == 0.0f)) {
                onNewStateAttachHandlers();
            }
        }
        float f10 = this.mTransitionLastPosition;
        if (f10 < 1.0f) {
            if (f10 <= 0.0f) {
                int i11 = this.mCurrentState;
                int i12 = this.mBeginState;
                z8 = i11 == i12 ? z9 : true;
                this.mCurrentState = i12;
            }
            this.mNeedsFireTransitionCompleted |= z9;
            if (z9 && !this.mInLayout) {
                requestLayout();
            }
            this.mTransitionPosition = this.mTransitionLastPosition;
        }
        int i13 = this.mCurrentState;
        int i14 = this.mEndState;
        z8 = i13 == i14 ? z9 : true;
        this.mCurrentState = i14;
        z9 = z8;
        this.mNeedsFireTransitionCompleted |= z9;
        if (z9) {
            requestLayout();
        }
        this.mTransitionPosition = this.mTransitionLastPosition;
    }

    public void fireTransitionCompleted() {
        int iIntValue;
        ArrayList<TransitionListener> arrayList;
        if ((this.mTransitionListener != null || ((arrayList = this.mTransitionListeners) != null && !arrayList.isEmpty())) && this.mListenerState == -1) {
            this.mListenerState = this.mCurrentState;
            if (this.mTransitionCompleted.isEmpty()) {
                iIntValue = -1;
            } else {
                iIntValue = this.mTransitionCompleted.get(r0.size() - 1).intValue();
            }
            int i7 = this.mCurrentState;
            if (iIntValue != i7 && i7 != -1) {
                this.mTransitionCompleted.add(Integer.valueOf(i7));
            }
        }
        processTransitionCompleted();
    }

    public void fireTrigger(int i7, boolean z6, float f7) {
        TransitionListener transitionListener = this.mTransitionListener;
        if (transitionListener != null) {
            transitionListener.onTransitionTrigger(this, i7, z6, f7);
        }
        ArrayList<TransitionListener> arrayList = this.mTransitionListeners;
        if (arrayList != null) {
            Iterator<TransitionListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onTransitionTrigger(this, i7, z6, f7);
            }
        }
    }

    public void getAnchorDpDt(int i7, float f7, float f8, float f9, float[] fArr) throws Resources.NotFoundException {
        HashMap<View, MotionController> map = this.mFrameArrayList;
        View viewById = getViewById(i7);
        MotionController motionController = map.get(viewById);
        if (motionController != null) {
            motionController.getDpDt(f7, f8, f9, fArr);
            float y6 = viewById.getY();
            this.lastPos = f7;
            this.lastY = y6;
            return;
        }
        if (viewById != null) {
            viewById.getContext().getResources().getResourceName(i7);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(i7);
    }

    public ConstraintSet getConstraintSet(int i7) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getConstraintSet(i7);
    }

    public int[] getConstraintSetIds() {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getConstraintSetIds();
    }

    public String getConstraintSetNames(int i7) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return null;
        }
        return motionScene.lookUpConstraintName(i7);
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public void getDebugMode(boolean z6) {
        this.mDebugPath = z6 ? 2 : 1;
        invalidate();
    }

    public ArrayList<MotionScene.Transition> getDefinedTransitions() {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getDefinedTransitions();
    }

    public DesignTool getDesignTool() {
        if (this.mDesignTool == null) {
            this.mDesignTool = new DesignTool(this);
        }
        return this.mDesignTool;
    }

    public int getEndState() {
        return this.mEndState;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.mTransitionLastPosition;
    }

    public int getStartState() {
        return this.mBeginState;
    }

    public float getTargetPosition() {
        return this.mTransitionGoalPosition;
    }

    public MotionScene.Transition getTransition(int i7) {
        return this.mScene.getTransitionById(i7);
    }

    public Bundle getTransitionState() {
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.recordState();
        return this.mStateCache.getTransitionState();
    }

    public long getTransitionTimeMs() {
        if (this.mScene != null) {
            this.mTransitionDuration = r0.getDuration() / 1000.0f;
        }
        return (long) (this.mTransitionDuration * 1000.0f);
    }

    public float getVelocity() {
        return this.mLastVelocity;
    }

    public void getViewVelocity(View view, float f7, float f8, float[] fArr, int i7) {
        float f9;
        float velocity = this.mLastVelocity;
        float f10 = this.mTransitionLastPosition;
        if (this.mInterpolator != null) {
            float fSignum = Math.signum(this.mTransitionGoalPosition - f10);
            float interpolation = this.mInterpolator.getInterpolation(this.mTransitionLastPosition + EPSILON);
            float interpolation2 = this.mInterpolator.getInterpolation(this.mTransitionLastPosition);
            velocity = (((interpolation - interpolation2) / EPSILON) * fSignum) / this.mTransitionDuration;
            f9 = interpolation2;
        } else {
            f9 = f10;
        }
        Interpolator interpolator = this.mInterpolator;
        if (interpolator instanceof MotionInterpolator) {
            velocity = ((MotionInterpolator) interpolator).getVelocity();
        }
        MotionController motionController = this.mFrameArrayList.get(view);
        if ((i7 & 1) == 0) {
            motionController.getPostLayoutDvDp(f9, view.getWidth(), view.getHeight(), f7, f8, fArr);
        } else {
            motionController.getDpDt(f9, f7, f8, fArr);
        }
        if (i7 < 2) {
            fArr[0] = fArr[0] * velocity;
            fArr[1] = fArr[1] * velocity;
        }
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return super.isAttachedToWindow();
    }

    public boolean isInteractionEnabled() {
        return this.mInteractionEnabled;
    }

    @Override // android.support.constraint.ConstraintLayout
    public void loadLayoutDescription(int i7) {
        if (i7 == 0) {
            this.mScene = null;
            return;
        }
        try {
            this.mScene = new MotionScene(getContext(), this, i7);
            if (isAttachedToWindow()) {
                this.mScene.readFallback(this);
                this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
                rebuildScene();
                this.mScene.setRtl(isRtl());
            }
        } catch (Exception e7) {
            throw new IllegalArgumentException("unable to parse MotionScene file", e7);
        }
    }

    public int lookUpConstraintId(String str) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return 0;
        }
        return motionScene.lookUpConstraintId(str);
    }

    public MotionTracker obtainVelocityTracker() {
        return MyTracker.obtain();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        MotionScene.Transition transition;
        int i7;
        super.onAttachedToWindow();
        MotionScene motionScene = this.mScene;
        if (motionScene != null && (i7 = this.mCurrentState) != -1) {
            ConstraintSet constraintSet = motionScene.getConstraintSet(i7);
            this.mScene.readFallback(this);
            if (constraintSet != null) {
                constraintSet.applyTo(this);
            }
            this.mBeginState = this.mCurrentState;
        }
        onNewStateAttachHandlers();
        StateCache stateCache = this.mStateCache;
        if (stateCache != null) {
            stateCache.apply();
            return;
        }
        MotionScene motionScene2 = this.mScene;
        if (motionScene2 == null || (transition = motionScene2.mCurrentTransition) == null || transition.getAutoTransition() != 4) {
            return;
        }
        transitionToEnd();
        setState(TransitionState.SETUP);
        setState(TransitionState.MOVING);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MotionScene.Transition transition;
        TouchResponse touchResponse;
        int touchRegionId;
        RectF touchRegion;
        MotionScene motionScene = this.mScene;
        if (motionScene != null && this.mInteractionEnabled && (transition = motionScene.mCurrentTransition) != null && transition.isEnabled() && (touchResponse = transition.getTouchResponse()) != null && ((motionEvent.getAction() != 0 || (touchRegion = touchResponse.getTouchRegion(this, new RectF())) == null || touchRegion.contains(motionEvent.getX(), motionEvent.getY())) && (touchRegionId = touchResponse.getTouchRegionId()) != -1)) {
            View view = this.mRegionView;
            if (view == null || view.getId() != touchRegionId) {
                this.mRegionView = findViewById(touchRegionId);
            }
            if (this.mRegionView != null) {
                this.mBoundsCheck.set(r0.getLeft(), this.mRegionView.getTop(), this.mRegionView.getRight(), this.mRegionView.getBottom());
                if (this.mBoundsCheck.contains(motionEvent.getX(), motionEvent.getY()) && !handlesTouchEvent(0.0f, 0.0f, this.mRegionView, motionEvent)) {
                    return onTouchEvent(motionEvent);
                }
            }
        }
        return false;
    }

    @Override // android.support.constraint.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        this.mInLayout = true;
        try {
            if (this.mScene == null) {
                super.onLayout(z6, i7, i8, i9, i10);
                return;
            }
            int i11 = i9 - i7;
            int i12 = i10 - i8;
            if (this.mLastLayoutWidth != i11 || this.mLastLayoutHeight != i12) {
                rebuildScene();
                evaluate(true);
            }
            this.mLastLayoutWidth = i11;
            this.mLastLayoutHeight = i12;
            this.mOldWidth = i11;
            this.mOldHeight = i12;
        } finally {
            this.mInLayout = false;
        }
    }

    @Override // android.support.constraint.ConstraintLayout, android.view.View
    public void onMeasure(int i7, int i8) {
        if (this.mScene == null) {
            super.onMeasure(i7, i8);
            return;
        }
        boolean z6 = false;
        boolean z7 = (this.mLastWidthMeasureSpec == i7 && this.mLastHeightMeasureSpec == i8) ? false : true;
        if (this.mNeedsFireTransitionCompleted) {
            this.mNeedsFireTransitionCompleted = false;
            onNewStateAttachHandlers();
            processTransitionCompleted();
            z7 = true;
        }
        if (this.mDirtyHierarchy) {
            z7 = true;
        }
        this.mLastWidthMeasureSpec = i7;
        this.mLastHeightMeasureSpec = i8;
        int startId = this.mScene.getStartId();
        int endId = this.mScene.getEndId();
        if ((z7 || this.mModel.isNotConfiguredWith(startId, endId)) && this.mBeginState != -1) {
            super.onMeasure(i7, i8);
            this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(startId), this.mScene.getConstraintSet(endId));
            this.mModel.reEvaluateState();
            this.mModel.setMeasuredId(startId, endId);
        } else {
            z6 = true;
        }
        if (this.mMeasureDuringTransition || z6) {
            int paddingBottom = getPaddingBottom() + getPaddingTop();
            int width = this.mLayoutWidget.getWidth() + getPaddingRight() + getPaddingLeft();
            int height = this.mLayoutWidget.getHeight() + paddingBottom;
            int i9 = this.mWidthMeasureMode;
            if (i9 == Integer.MIN_VALUE || i9 == 0) {
                width = (int) ((this.mPostInterpolationPosition * (this.mEndWrapWidth - r7)) + this.mStartWrapWidth);
                requestLayout();
            }
            int i10 = this.mHeightMeasureMode;
            if (i10 == Integer.MIN_VALUE || i10 == 0) {
                height = (int) ((this.mPostInterpolationPosition * (this.mEndWrapHeight - r8)) + this.mStartWrapHeight);
                requestLayout();
            }
            setMeasuredDimension(width, height);
        }
        evaluateLayout();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f7, float f8, boolean z6) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, android.support.v4.view.NestedScrollingParent
    public boolean onNestedPreFling(View view, float f7, float f8) {
        return false;
    }

    @Override // android.support.v4.view.NestedScrollingParent2
    public void onNestedPreScroll(final View view, int i7, int i8, int[] iArr, int i9) {
        MotionScene.Transition transition;
        TouchResponse touchResponse;
        int touchRegionId;
        MotionScene motionScene = this.mScene;
        if (motionScene == null || (transition = motionScene.mCurrentTransition) == null || !transition.isEnabled()) {
            return;
        }
        MotionScene.Transition transition2 = this.mScene.mCurrentTransition;
        if (transition2 == null || !transition2.isEnabled() || (touchResponse = transition2.getTouchResponse()) == null || (touchRegionId = touchResponse.getTouchRegionId()) == -1 || view.getId() == touchRegionId) {
            MotionScene motionScene2 = this.mScene;
            if (motionScene2 != null && motionScene2.getMoveWhenScrollAtTop()) {
                float f7 = this.mTransitionPosition;
                if ((f7 == 1.0f || f7 == 0.0f) && view.canScrollVertically(-1)) {
                    return;
                }
            }
            if (transition2.getTouchResponse() != null && (this.mScene.mCurrentTransition.getTouchResponse().getFlags() & 1) != 0) {
                float progressDirection = this.mScene.getProgressDirection(i7, i8);
                float f8 = this.mTransitionLastPosition;
                if ((f8 <= 0.0f && progressDirection < 0.0f) || (f8 >= 1.0f && progressDirection > 0.0f)) {
                    view.setNestedScrollingEnabled(false);
                    view.post(new Runnable() { // from class: android.support.constraint.motion.MotionLayout.1
                        @Override // java.lang.Runnable
                        public void run() {
                            view.setNestedScrollingEnabled(true);
                        }
                    });
                    return;
                }
            }
            float f9 = this.mTransitionPosition;
            long nanoTime = getNanoTime();
            float f10 = i7;
            this.mScrollTargetDX = f10;
            float f11 = i8;
            this.mScrollTargetDY = f11;
            this.mScrollTargetDT = (float) ((nanoTime - this.mScrollTargetTime) * 1.0E-9d);
            this.mScrollTargetTime = nanoTime;
            this.mScene.processScrollMove(f10, f11);
            if (f9 != this.mTransitionPosition) {
                iArr[0] = i7;
                iArr[1] = i8;
            }
            evaluate(false);
            if (iArr[0] == 0 && iArr[1] == 0) {
                return;
            }
            this.mUndergoingMotion = true;
        }
    }

    @Override // android.support.v4.view.NestedScrollingParent2
    public void onNestedScroll(View view, int i7, int i8, int i9, int i10, int i11) {
    }

    @Override // android.support.v4.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View view, View view2, int i7, int i8) {
    }

    public void onNewStateAttachHandlers() {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return;
        }
        if (motionScene.autoTransition(this, this.mCurrentState)) {
            requestLayout();
            return;
        }
        int i7 = this.mCurrentState;
        if (i7 != -1) {
            this.mScene.addOnClickListeners(this, i7);
        }
        if (this.mScene.supportTouch()) {
            this.mScene.setupTouch();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i7) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            motionScene.setRtl(isRtl());
        }
    }

    @Override // android.support.v4.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View view, View view2, int i7, int i8) {
        MotionScene.Transition transition;
        MotionScene motionScene = this.mScene;
        return (motionScene == null || (transition = motionScene.mCurrentTransition) == null || transition.getTouchResponse() == null || (this.mScene.mCurrentTransition.getTouchResponse().getFlags() & 2) != 0) ? false : true;
    }

    @Override // android.support.v4.view.NestedScrollingParent2
    public void onStopNestedScroll(View view, int i7) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return;
        }
        float f7 = this.mScrollTargetDX;
        float f8 = this.mScrollTargetDT;
        motionScene.processScrollUp(f7 / f8, this.mScrollTargetDY / f8);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null || !this.mInteractionEnabled || !motionScene.supportTouch()) {
            return super.onTouchEvent(motionEvent);
        }
        MotionScene.Transition transition = this.mScene.mCurrentTransition;
        if (transition != null && !transition.isEnabled()) {
            return super.onTouchEvent(motionEvent);
        }
        this.mScene.processTouchEvent(motionEvent, getCurrentState(), this);
        return true;
    }

    @Override // android.support.constraint.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.mTransitionListeners == null) {
                this.mTransitionListeners = new ArrayList<>();
            }
            this.mTransitionListeners.add(motionHelper);
            if (motionHelper.isUsedOnShow()) {
                if (this.mOnShowHelpers == null) {
                    this.mOnShowHelpers = new ArrayList<>();
                }
                this.mOnShowHelpers.add(motionHelper);
            }
            if (motionHelper.isUseOnHide()) {
                if (this.mOnHideHelpers == null) {
                    this.mOnHideHelpers = new ArrayList<>();
                }
                this.mOnHideHelpers.add(motionHelper);
            }
        }
    }

    @Override // android.support.constraint.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.mOnShowHelpers;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.mOnHideHelpers;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    @Override // android.support.constraint.ConstraintLayout
    public void parseLayoutDescription(int i7) {
        this.mConstraintLayoutSpec = null;
    }

    @Deprecated
    public void rebuildMotion() {
        rebuildScene();
    }

    public void rebuildScene() {
        this.mModel.reEvaluateState();
        invalidate();
    }

    public boolean removeTransitionListener(TransitionListener transitionListener) {
        ArrayList<TransitionListener> arrayList = this.mTransitionListeners;
        if (arrayList == null) {
            return false;
        }
        return arrayList.remove(transitionListener);
    }

    @Override // android.support.constraint.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        MotionScene motionScene;
        MotionScene.Transition transition;
        if (this.mMeasureDuringTransition || this.mCurrentState != -1 || (motionScene = this.mScene) == null || (transition = motionScene.mCurrentTransition) == null || transition.getLayoutDuringTransition() != 0) {
            super.requestLayout();
        }
    }

    public void setDebugMode(int i7) {
        this.mDebugPath = i7;
        invalidate();
    }

    public void setInteractionEnabled(boolean z6) {
        this.mInteractionEnabled = z6;
    }

    public void setInterpolatedProgress(float f7) {
        if (this.mScene != null) {
            setState(TransitionState.MOVING);
            Interpolator interpolator = this.mScene.getInterpolator();
            if (interpolator != null) {
                setProgress(interpolator.getInterpolation(f7));
                return;
            }
        }
        setProgress(f7);
    }

    public void setOnHide(float f7) {
        ArrayList<MotionHelper> arrayList = this.mOnHideHelpers;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.mOnHideHelpers.get(i7).setProgress(f7);
            }
        }
    }

    public void setOnShow(float f7) {
        ArrayList<MotionHelper> arrayList = this.mOnShowHelpers;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.mOnShowHelpers.get(i7).setProgress(f7);
            }
        }
    }

    public void setProgress(float f7, float f8) {
        if (isAttachedToWindow()) {
            setProgress(f7);
            setState(TransitionState.MOVING);
            this.mLastVelocity = f8;
            animateTo(1.0f);
            return;
        }
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.setProgress(f7);
        this.mStateCache.setVelocity(f8);
    }

    public void setScene(MotionScene motionScene) {
        this.mScene = motionScene;
        motionScene.setRtl(isRtl());
        rebuildScene();
    }

    public void setState(TransitionState transitionState) {
        TransitionState transitionState2 = TransitionState.FINISHED;
        if (transitionState == transitionState2 && this.mCurrentState == -1) {
            return;
        }
        TransitionState transitionState3 = this.mTransitionState;
        this.mTransitionState = transitionState;
        TransitionState transitionState4 = TransitionState.MOVING;
        if (transitionState3 == transitionState4 && transitionState == transitionState4) {
            fireTransitionChange();
        }
        int i7 = C00752.f117xe3ac6329[transitionState3.ordinal()];
        if (i7 != 1 && i7 != 2) {
            if (i7 == 3 && transitionState == transitionState2) {
                fireTransitionCompleted();
                return;
            }
            return;
        }
        if (transitionState == transitionState4) {
            fireTransitionChange();
        }
        if (transitionState == transitionState2) {
            fireTransitionCompleted();
        }
    }

    public void setTransition(int i7, int i8) {
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.setStartState(i7);
            this.mStateCache.setEndState(i8);
            return;
        }
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            this.mBeginState = i7;
            this.mEndState = i8;
            motionScene.setTransition(i7, i8);
            this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(i7), this.mScene.getConstraintSet(i8));
            rebuildScene();
            this.mTransitionLastPosition = 0.0f;
            transitionToStart();
        }
    }

    public void setTransitionDuration(int i7) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return;
        }
        motionScene.setDuration(i7);
    }

    public void setTransitionListener(TransitionListener transitionListener) {
        this.mTransitionListener = transitionListener;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.setTransitionState(bundle);
        if (isAttachedToWindow()) {
            this.mStateCache.apply();
        }
    }

    @Override // android.view.View
    public String toString() {
        Context context = getContext();
        return Debug.getName(context, this.mBeginState) + "->" + Debug.getName(context, this.mEndState) + " (pos:" + this.mTransitionLastPosition + " Dpos/Dt:" + this.mLastVelocity;
    }

    public void touchAnimateTo(int i7, float f7, float f8) {
        if (this.mScene == null || this.mTransitionLastPosition == f7) {
            return;
        }
        this.mTemporalInterpolator = true;
        this.mAnimationStartTime = getNanoTime();
        float duration = this.mScene.getDuration() / 1000.0f;
        this.mTransitionDuration = duration;
        this.mTransitionGoalPosition = f7;
        this.mInTransition = true;
        if (i7 == 0 || i7 == 1 || i7 == 2) {
            if (i7 == 1) {
                f7 = 0.0f;
            } else if (i7 == 2) {
                f7 = 1.0f;
            }
            this.mStopLogic.config(this.mTransitionLastPosition, f7, f8, duration, this.mScene.getMaxAcceleration(), this.mScene.getMaxVelocity());
            int i8 = this.mCurrentState;
            this.mTransitionGoalPosition = f7;
            this.mCurrentState = i8;
            this.mInterpolator = this.mStopLogic;
        } else if (i7 == 4) {
            this.mDecelerateLogic.config(f8, this.mTransitionLastPosition, this.mScene.getMaxAcceleration());
            this.mInterpolator = this.mDecelerateLogic;
        } else if (i7 == 5) {
            if (willJump(f8, this.mTransitionLastPosition, this.mScene.getMaxAcceleration())) {
                this.mDecelerateLogic.config(f8, this.mTransitionLastPosition, this.mScene.getMaxAcceleration());
                this.mInterpolator = this.mDecelerateLogic;
            } else {
                this.mStopLogic.config(this.mTransitionLastPosition, f7, f8, this.mTransitionDuration, this.mScene.getMaxAcceleration(), this.mScene.getMaxVelocity());
                this.mLastVelocity = 0.0f;
                int i9 = this.mCurrentState;
                this.mTransitionGoalPosition = f7;
                this.mCurrentState = i9;
                this.mInterpolator = this.mStopLogic;
            }
        }
        this.mTransitionInstantly = false;
        this.mAnimationStartTime = getNanoTime();
        invalidate();
    }

    public void transitionToEnd() {
        animateTo(1.0f);
    }

    public void transitionToStart() {
        animateTo(0.0f);
    }

    public void transitionToState(int i7) {
        if (isAttachedToWindow()) {
            transitionToState(i7, -1, -1);
            return;
        }
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.setEndState(i7);
    }

    public void updateState(int i7, ConstraintSet constraintSet) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            motionScene.setConstraintSet(i7, constraintSet);
        }
        updateState();
        if (this.mCurrentState == i7) {
            constraintSet.applyTo(this);
        }
    }

    public static class MyTracker implements MotionTracker {

        /* renamed from: me */
        private static MyTracker f118me = new MyTracker();
        public VelocityTracker tracker;

        private MyTracker() {
        }

        public static MyTracker obtain() {
            f118me.tracker = VelocityTracker.obtain();
            return f118me;
        }

        @Override // android.support.constraint.motion.MotionLayout.MotionTracker
        public void addMovement(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.tracker;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // android.support.constraint.motion.MotionLayout.MotionTracker
        public void clear() {
            VelocityTracker velocityTracker = this.tracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }

        @Override // android.support.constraint.motion.MotionLayout.MotionTracker
        public void computeCurrentVelocity(int i7) {
            VelocityTracker velocityTracker = this.tracker;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(i7);
            }
        }

        @Override // android.support.constraint.motion.MotionLayout.MotionTracker
        public float getXVelocity() {
            VelocityTracker velocityTracker = this.tracker;
            if (velocityTracker != null) {
                return velocityTracker.getXVelocity();
            }
            return 0.0f;
        }

        @Override // android.support.constraint.motion.MotionLayout.MotionTracker
        public float getYVelocity() {
            VelocityTracker velocityTracker = this.tracker;
            if (velocityTracker != null) {
                return velocityTracker.getYVelocity();
            }
            return 0.0f;
        }

        @Override // android.support.constraint.motion.MotionLayout.MotionTracker
        public void recycle() {
            VelocityTracker velocityTracker = this.tracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.tracker = null;
            }
        }

        @Override // android.support.constraint.motion.MotionLayout.MotionTracker
        public void computeCurrentVelocity(int i7, float f7) {
            VelocityTracker velocityTracker = this.tracker;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(i7, f7);
            }
        }

        @Override // android.support.constraint.motion.MotionLayout.MotionTracker
        public float getXVelocity(int i7) {
            VelocityTracker velocityTracker = this.tracker;
            if (velocityTracker != null) {
                return velocityTracker.getXVelocity(i7);
            }
            return 0.0f;
        }

        @Override // android.support.constraint.motion.MotionLayout.MotionTracker
        public float getYVelocity(int i7) {
            if (this.tracker != null) {
                return getYVelocity(i7);
            }
            return 0.0f;
        }
    }

    public void transitionToState(int i7, int i8, int i9) {
        StateSet stateSet;
        int iConvertToConstraintSet;
        MotionScene motionScene = this.mScene;
        if (motionScene != null && (stateSet = motionScene.mStateSet) != null && (iConvertToConstraintSet = stateSet.convertToConstraintSet(this.mCurrentState, i7, i8, i9)) != -1) {
            i7 = iConvertToConstraintSet;
        }
        int i10 = this.mCurrentState;
        if (i10 == i7) {
            return;
        }
        if (this.mBeginState == i7) {
            animateTo(0.0f);
            return;
        }
        if (this.mEndState == i7) {
            animateTo(1.0f);
            return;
        }
        this.mEndState = i7;
        if (i10 != -1) {
            setTransition(i10, i7);
            animateTo(1.0f);
            this.mTransitionLastPosition = 0.0f;
            transitionToEnd();
            return;
        }
        this.mTemporalInterpolator = false;
        this.mTransitionGoalPosition = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionLastTime = getNanoTime();
        this.mAnimationStartTime = getNanoTime();
        this.mTransitionInstantly = false;
        this.mInterpolator = null;
        this.mTransitionDuration = this.mScene.getDuration() / 1000.0f;
        this.mBeginState = -1;
        this.mScene.setTransition(-1, this.mEndState);
        this.mScene.getStartId();
        int childCount = getChildCount();
        this.mFrameArrayList.clear();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            this.mFrameArrayList.put(childAt, new MotionController(childAt));
        }
        this.mInTransition = true;
        this.mModel.initFrom(this.mLayoutWidget, null, this.mScene.getConstraintSet(i7));
        rebuildScene();
        this.mModel.build();
        computeCurrentPositions();
        int width = getWidth();
        int height = getHeight();
        for (int i12 = 0; i12 < childCount; i12++) {
            MotionController motionController = this.mFrameArrayList.get(getChildAt(i12));
            this.mScene.getKeyFrames(motionController);
            motionController.setup(width, height, this.mTransitionDuration, getNanoTime());
        }
        float staggered = this.mScene.getStaggered();
        if (staggered != 0.0f) {
            float fMin = Float.MAX_VALUE;
            float fMax = -3.4028235E38f;
            for (int i13 = 0; i13 < childCount; i13++) {
                MotionController motionController2 = this.mFrameArrayList.get(getChildAt(i13));
                float finalY = motionController2.getFinalY() + motionController2.getFinalX();
                fMin = Math.min(fMin, finalY);
                fMax = Math.max(fMax, finalY);
            }
            for (int i14 = 0; i14 < childCount; i14++) {
                MotionController motionController3 = this.mFrameArrayList.get(getChildAt(i14));
                float finalX = motionController3.getFinalX();
                float finalY2 = motionController3.getFinalY();
                motionController3.mStaggerScale = 1.0f / (1.0f - staggered);
                motionController3.mStaggerOffset = staggered - ((((finalX + finalY2) - fMin) * staggered) / (fMax - fMin));
            }
        }
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mInTransition = true;
        invalidate();
    }

    public void updateState() {
        this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
        rebuildScene();
    }

    public void setProgress(float f7) {
        if (f7 >= 0.0f) {
            int i7 = (f7 > 1.0f ? 1 : (f7 == 1.0f ? 0 : -1));
        }
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.setProgress(f7);
            return;
        }
        if (f7 <= 0.0f) {
            this.mCurrentState = this.mBeginState;
            if (this.mTransitionLastPosition == 0.0f) {
                setState(TransitionState.FINISHED);
            }
        } else if (f7 >= 1.0f) {
            this.mCurrentState = this.mEndState;
            if (this.mTransitionLastPosition == 1.0f) {
                setState(TransitionState.FINISHED);
            }
        } else {
            this.mCurrentState = -1;
            setState(TransitionState.MOVING);
        }
        if (this.mScene == null) {
            return;
        }
        this.mTransitionInstantly = true;
        this.mTransitionGoalPosition = f7;
        this.mTransitionPosition = f7;
        this.mTransitionLastTime = -1L;
        this.mAnimationStartTime = -1L;
        this.mInterpolator = null;
        this.mInTransition = true;
        invalidate();
    }

    @Override // android.support.constraint.ConstraintLayout
    public void setState(int i7, int i8, int i9) {
        setState(TransitionState.SETUP);
        this.mCurrentState = i7;
        this.mBeginState = -1;
        this.mEndState = -1;
        ConstraintLayoutStates constraintLayoutStates = this.mConstraintLayoutSpec;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.updateConstraints(i7, i8, i9);
            return;
        }
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            motionScene.getConstraintSet(i7).applyTo(this);
        }
    }

    public void setTransition(int i7) {
        if (this.mScene != null) {
            MotionScene.Transition transition = getTransition(i7);
            this.mBeginState = transition.getStartConstraintSetId();
            this.mEndState = transition.getEndConstraintSetId();
            if (!isAttachedToWindow()) {
                if (this.mStateCache == null) {
                    this.mStateCache = new StateCache();
                }
                this.mStateCache.setStartState(this.mBeginState);
                this.mStateCache.setEndState(this.mEndState);
                return;
            }
            float f7 = Float.NaN;
            int i8 = this.mCurrentState;
            if (i8 == this.mBeginState) {
                f7 = 0.0f;
            } else if (i8 == this.mEndState) {
                f7 = 1.0f;
            }
            this.mScene.setTransition(transition);
            this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
            rebuildScene();
            this.mTransitionLastPosition = Float.isNaN(f7) ? 0.0f : f7;
            if (Float.isNaN(f7)) {
                Debug.getLocation();
                transitionToStart();
            } else {
                setProgress(f7);
            }
        }
    }

    private void checkStructure(int i7, ConstraintSet constraintSet) {
        Debug.getName(getContext(), i7);
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (constraintSet.getConstraint(childAt.getId()) == null) {
                Debug.getName(childAt);
            }
        }
        int[] knownIds = constraintSet.getKnownIds();
        for (int i9 = 0; i9 < knownIds.length; i9++) {
            int i10 = knownIds[i9];
            Debug.getName(getContext(), i10);
            findViewById(knownIds[i9]);
            constraintSet.getHeight(i10);
            constraintSet.getWidth(i10);
        }
    }

    private void checkStructure(MotionScene.Transition transition) {
        transition.debugString(getContext());
        transition.getDuration();
        transition.getStartConstraintSetId();
        transition.getEndConstraintSetId();
    }

    public void setTransition(MotionScene.Transition transition) {
        this.mScene.setTransition(transition);
        setState(TransitionState.SETUP);
        if (this.mCurrentState == this.mScene.getEndId()) {
            this.mTransitionLastPosition = 1.0f;
            this.mTransitionPosition = 1.0f;
            this.mTransitionGoalPosition = 1.0f;
        } else {
            this.mTransitionLastPosition = 0.0f;
            this.mTransitionPosition = 0.0f;
            this.mTransitionGoalPosition = 0.0f;
        }
        this.mTransitionLastTime = transition.isTransitionFlag(1) ? -1L : getNanoTime();
        int startId = this.mScene.getStartId();
        int endId = this.mScene.getEndId();
        if (startId == this.mBeginState && endId == this.mEndState) {
            return;
        }
        this.mBeginState = startId;
        this.mEndState = endId;
        this.mScene.setTransition(startId, endId);
        this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
        this.mModel.setMeasuredId(this.mBeginState, this.mEndState);
        this.mModel.reEvaluateState();
        rebuildScene();
    }

    public MotionLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLastVelocity = 0.0f;
        this.mBeginState = -1;
        this.mCurrentState = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.mFrameArrayList = new HashMap<>();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionGoalPosition = 0.0f;
        this.mInTransition = false;
        this.mIndirectTransition = false;
        this.mDebugPath = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator();
        this.firstDown = true;
        this.mUndergoingMotion = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.mIsAnimating = false;
        this.mMeasureDuringTransition = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mTransitionState = TransitionState.UNDEFINED;
        this.mModel = new Model();
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mTransitionCompleted = new ArrayList<>();
        init(attributeSet);
    }

    public MotionLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.mLastVelocity = 0.0f;
        this.mBeginState = -1;
        this.mCurrentState = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.mFrameArrayList = new HashMap<>();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionGoalPosition = 0.0f;
        this.mInTransition = false;
        this.mIndirectTransition = false;
        this.mDebugPath = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator();
        this.firstDown = true;
        this.mUndergoingMotion = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.mIsAnimating = false;
        this.mMeasureDuringTransition = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mTransitionState = TransitionState.UNDEFINED;
        this.mModel = new Model();
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mTransitionCompleted = new ArrayList<>();
        init(attributeSet);
    }
}
