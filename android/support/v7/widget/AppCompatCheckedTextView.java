package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* loaded from: classes.dex */
public class AppCompatCheckedTextView extends CheckedTextView {
    private static final int[] TINT_ATTRS = {R.attr.checkMark};
    private final AppCompatTextHelper mTextHelper;

    public AppCompatCheckedTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.applyCompoundDrawablesTints();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(@DrawableRes int i7) {
        setCheckMarkDrawable(AppCompatResources.getDrawable(getContext(), i7));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onSetTextAppearance(context, i7);
        }
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i7) {
        super(TintContextWrapper.wrap(context), attributeSet, i7);
        AppCompatTextHelper appCompatTextHelper = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper;
        appCompatTextHelper.loadFromAttributes(attributeSet, i7);
        appCompatTextHelper.applyCompoundDrawablesTints();
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getContext(), attributeSet, TINT_ATTRS, i7, 0);
        setCheckMarkDrawable(tintTypedArrayObtainStyledAttributes.getDrawable(0));
        tintTypedArrayObtainStyledAttributes.recycle();
    }
}
