package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.C0308R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
class AppCompatPopupWindow extends PopupWindow {
    private static final boolean COMPAT_OVERLAP_ANCHOR = false;
    private boolean mOverlapAnchor;

    public AppCompatPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i7) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super(context, attributeSet, i7);
        init(context, attributeSet, i7, 0);
    }

    private void init(Context context, AttributeSet attributeSet, int i7, int i8) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0308R.styleable.PopupWindow, i7, i8);
        int i9 = C0308R.styleable.PopupWindow_overlapAnchor;
        if (tintTypedArrayObtainStyledAttributes.hasValue(i9)) {
            setSupportOverlapAnchor(tintTypedArrayObtainStyledAttributes.getBoolean(i9, false));
        }
        setBackgroundDrawable(tintTypedArrayObtainStyledAttributes.getDrawable(C0308R.styleable.PopupWindow_android_popupBackground));
        tintTypedArrayObtainStyledAttributes.recycle();
    }

    private void setSupportOverlapAnchor(boolean z6) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        if (COMPAT_OVERLAP_ANCHOR) {
            this.mOverlapAnchor = z6;
        } else {
            PopupWindowCompat.setOverlapAnchor(this, z6);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i7, int i8) {
        if (COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            i8 -= view.getHeight();
        }
        super.showAsDropDown(view, i7, i8);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i7, int i8, int i9, int i10) {
        if (COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            i8 -= view.getHeight();
        }
        super.update(view, i7, i8, i9, i10);
    }

    public AppCompatPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i7, @StyleRes int i8) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super(context, attributeSet, i7, i8);
        init(context, attributeSet, i7, i8);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i7, int i8, int i9) {
        if (COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            i8 -= view.getHeight();
        }
        super.showAsDropDown(view, i7, i8, i9);
    }
}
