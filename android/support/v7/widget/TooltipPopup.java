package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.C0308R;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
class TooltipPopup {
    private static final String TAG = "TooltipPopup";
    private final View mContentView;
    private final Context mContext;
    private final WindowManager.LayoutParams mLayoutParams;
    private final TextView mMessageView;
    private final int[] mTmpAnchorPos;
    private final int[] mTmpAppPos;
    private final Rect mTmpDisplayFrame;

    public TooltipPopup(Context context) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        this.mTmpDisplayFrame = new Rect();
        this.mTmpAnchorPos = new int[2];
        this.mTmpAppPos = new int[2];
        this.mContext = context;
        View viewInflate = LayoutInflater.from(context).inflate(C0308R.layout.abc_tooltip, (ViewGroup) null);
        this.mContentView = viewInflate;
        this.mMessageView = (TextView) viewInflate.findViewById(C0308R.id.message);
        layoutParams.setTitle(getClass().getSimpleName());
        layoutParams.packageName = context.getPackageName();
        layoutParams.type = 1002;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = C0308R.style.Animation_AppCompat_Tooltip;
        layoutParams.flags = 24;
    }

    private void computePosition(View view, int i7, int i8, boolean z6, WindowManager.LayoutParams layoutParams) throws Resources.NotFoundException {
        int height;
        int i9;
        layoutParams.token = view.getApplicationWindowToken();
        int dimensionPixelOffset = this.mContext.getResources().getDimensionPixelOffset(C0308R.dimen.tooltip_precise_anchor_threshold);
        if (view.getWidth() < dimensionPixelOffset) {
            i7 = view.getWidth() / 2;
        }
        if (view.getHeight() >= dimensionPixelOffset) {
            int dimensionPixelOffset2 = this.mContext.getResources().getDimensionPixelOffset(C0308R.dimen.tooltip_precise_anchor_extra_offset);
            height = i8 + dimensionPixelOffset2;
            i9 = i8 - dimensionPixelOffset2;
        } else {
            height = view.getHeight();
            i9 = 0;
        }
        layoutParams.gravity = 49;
        int dimensionPixelOffset3 = this.mContext.getResources().getDimensionPixelOffset(z6 ? C0308R.dimen.tooltip_y_offset_touch : C0308R.dimen.tooltip_y_offset_non_touch);
        View appRootView = getAppRootView(view);
        if (appRootView == null) {
            return;
        }
        appRootView.getWindowVisibleDisplayFrame(this.mTmpDisplayFrame);
        Rect rect = this.mTmpDisplayFrame;
        if (rect.left < 0 && rect.top < 0) {
            Resources resources = this.mContext.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            this.mTmpDisplayFrame.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        appRootView.getLocationOnScreen(this.mTmpAppPos);
        view.getLocationOnScreen(this.mTmpAnchorPos);
        int[] iArr = this.mTmpAnchorPos;
        int i10 = iArr[0];
        int[] iArr2 = this.mTmpAppPos;
        iArr[0] = i10 - iArr2[0];
        iArr[1] = iArr[1] - iArr2[1];
        layoutParams.x = (iArr[0] + i7) - (appRootView.getWidth() / 2);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mContentView.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        int measuredHeight = this.mContentView.getMeasuredHeight();
        int[] iArr3 = this.mTmpAnchorPos;
        int i11 = ((iArr3[1] + i9) - dimensionPixelOffset3) - measuredHeight;
        int i12 = iArr3[1] + height + dimensionPixelOffset3;
        if (z6) {
            if (i11 >= 0) {
                layoutParams.y = i11;
                return;
            } else {
                layoutParams.y = i12;
                return;
            }
        }
        if (measuredHeight + i12 <= this.mTmpDisplayFrame.height()) {
            layoutParams.y = i12;
        } else {
            layoutParams.y = i11;
        }
    }

    private static View getAppRootView(View view) {
        View rootView = view.getRootView();
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        if ((layoutParams instanceof WindowManager.LayoutParams) && ((WindowManager.LayoutParams) layoutParams).type == 2) {
            return rootView;
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return ((Activity) context).getWindow().getDecorView();
            }
        }
        return rootView;
    }

    public void hide() {
        if (isShowing()) {
            ((WindowManager) this.mContext.getSystemService("window")).removeView(this.mContentView);
        }
    }

    public boolean isShowing() {
        return this.mContentView.getParent() != null;
    }

    public void show(View view, int i7, int i8, boolean z6, CharSequence charSequence) throws Resources.NotFoundException {
        if (isShowing()) {
            hide();
        }
        this.mMessageView.setText(charSequence);
        computePosition(view, i7, i8, z6, this.mLayoutParams);
        ((WindowManager) this.mContext.getSystemService("window")).addView(this.mContentView, this.mLayoutParams);
    }
}
