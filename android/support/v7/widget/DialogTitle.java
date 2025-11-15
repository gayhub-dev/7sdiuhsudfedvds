package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.appcompat.C0308R;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.widget.TextView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class DialogTitle extends TextView {
    public DialogTitle(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i7, int i8) {
        int lineCount;
        super.onMeasure(i7, i8);
        Layout layout = getLayout();
        if (layout == null || (lineCount = layout.getLineCount()) <= 0 || layout.getEllipsisCount(lineCount - 1) <= 0) {
            return;
        }
        setSingleLine(false);
        setMaxLines(2);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, C0308R.styleable.TextAppearance, R.attr.textAppearanceMedium, R.style.TextAppearance.Medium);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0308R.styleable.TextAppearance_android_textSize, 0);
        if (dimensionPixelSize != 0) {
            setTextSize(0, dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
        super.onMeasure(i7, i8);
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }

    public DialogTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DialogTitle(Context context) {
        super(context);
    }
}
