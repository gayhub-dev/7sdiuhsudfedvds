package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertController;
import android.support.v7.appcompat.C0308R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/* loaded from: classes.dex */
public class AlertDialog extends AppCompatDialog {
    public static final int LAYOUT_HINT_NONE = 0;
    public static final int LAYOUT_HINT_SIDE = 1;
    public final AlertController mAlert;

    public static class Builder {

        /* renamed from: P */
        private final AlertController.AlertParams f158P;
        private final int mTheme;

        public Builder(@NonNull Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        public AlertDialog create() {
            AlertDialog alertDialog = new AlertDialog(this.f158P.mContext, this.mTheme);
            this.f158P.apply(alertDialog.mAlert);
            alertDialog.setCancelable(this.f158P.mCancelable);
            if (this.f158P.mCancelable) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(this.f158P.mOnCancelListener);
            alertDialog.setOnDismissListener(this.f158P.mOnDismissListener);
            DialogInterface.OnKeyListener onKeyListener = this.f158P.mOnKeyListener;
            if (onKeyListener != null) {
                alertDialog.setOnKeyListener(onKeyListener);
            }
            return alertDialog;
        }

        @NonNull
        public Context getContext() {
            return this.f158P.mContext;
        }

        public Builder setAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mAdapter = listAdapter;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setCancelable(boolean z6) {
            this.f158P.mCancelable = z6;
            return this;
        }

        public Builder setCursor(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mCursor = cursor;
            alertParams.mLabelColumn = str;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setCustomTitle(@Nullable View view) {
            this.f158P.mCustomTitleView = view;
            return this;
        }

        public Builder setIcon(@DrawableRes int i7) {
            this.f158P.mIconId = i7;
            return this;
        }

        public Builder setIconAttribute(@AttrRes int i7) {
            TypedValue typedValue = new TypedValue();
            this.f158P.mContext.getTheme().resolveAttribute(i7, typedValue, true);
            this.f158P.mIconId = typedValue.resourceId;
            return this;
        }

        @Deprecated
        public Builder setInverseBackgroundForced(boolean z6) {
            this.f158P.mForceInverseBackground = z6;
            return this;
        }

        public Builder setItems(@ArrayRes int i7, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(i7);
            this.f158P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setMessage(@StringRes int i7) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mMessage = alertParams.mContext.getText(i7);
            return this;
        }

        public Builder setMultiChoiceItems(@ArrayRes int i7, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(i7);
            AlertController.AlertParams alertParams2 = this.f158P;
            alertParams2.mOnCheckboxClickListener = onMultiChoiceClickListener;
            alertParams2.mCheckedItems = zArr;
            alertParams2.mIsMultiChoice = true;
            return this;
        }

        public Builder setNegativeButton(@StringRes int i7, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mNegativeButtonText = alertParams.mContext.getText(i7);
            this.f158P.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButtonIcon(Drawable drawable) {
            this.f158P.mNegativeButtonIcon = drawable;
            return this;
        }

        public Builder setNeutralButton(@StringRes int i7, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mNeutralButtonText = alertParams.mContext.getText(i7);
            this.f158P.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButtonIcon(Drawable drawable) {
            this.f158P.mNeutralButtonIcon = drawable;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.f158P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.f158P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
            this.f158P.mOnItemSelectedListener = onItemSelectedListener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.f158P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setPositiveButton(@StringRes int i7, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mPositiveButtonText = alertParams.mContext.getText(i7);
            this.f158P.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButtonIcon(Drawable drawable) {
            this.f158P.mPositiveButtonIcon = drawable;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setRecycleOnMeasureEnabled(boolean z6) {
            this.f158P.mRecycleOnMeasure = z6;
            return this;
        }

        public Builder setSingleChoiceItems(@ArrayRes int i7, int i8, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(i7);
            AlertController.AlertParams alertParams2 = this.f158P;
            alertParams2.mOnClickListener = onClickListener;
            alertParams2.mCheckedItem = i8;
            alertParams2.mIsSingleChoice = true;
            return this;
        }

        public Builder setTitle(@StringRes int i7) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mTitle = alertParams.mContext.getText(i7);
            return this;
        }

        public Builder setView(int i7) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mView = null;
            alertParams.mViewLayoutResId = i7;
            alertParams.mViewSpacingSpecified = false;
            return this;
        }

        public AlertDialog show() {
            AlertDialog alertDialogCreate = create();
            alertDialogCreate.show();
            return alertDialogCreate;
        }

        public Builder(@NonNull Context context, @StyleRes int i7) {
            this.f158P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, i7)));
            this.mTheme = i7;
        }

        public Builder setIcon(@Nullable Drawable drawable) {
            this.f158P.mIcon = drawable;
            return this;
        }

        public Builder setMessage(@Nullable CharSequence charSequence) {
            this.f158P.mMessage = charSequence;
            return this;
        }

        public Builder setTitle(@Nullable CharSequence charSequence) {
            this.f158P.mTitle = charSequence;
            return this;
        }

        public Builder setItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mItems = charSequenceArr;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mNegativeButtonText = charSequence;
            alertParams.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mNeutralButtonText = charSequence;
            alertParams.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mPositiveButtonText = charSequence;
            alertParams.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setView(View view) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mView = view;
            alertParams.mViewLayoutResId = 0;
            alertParams.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mItems = charSequenceArr;
            alertParams.mOnCheckboxClickListener = onMultiChoiceClickListener;
            alertParams.mCheckedItems = zArr;
            alertParams.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int i7, String str, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mCursor = cursor;
            alertParams.mOnClickListener = onClickListener;
            alertParams.mCheckedItem = i7;
            alertParams.mLabelColumn = str;
            alertParams.mIsSingleChoice = true;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @Deprecated
        public Builder setView(View view, int i7, int i8, int i9, int i10) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mView = view;
            alertParams.mViewLayoutResId = 0;
            alertParams.mViewSpacingSpecified = true;
            alertParams.mViewSpacingLeft = i7;
            alertParams.mViewSpacingTop = i8;
            alertParams.mViewSpacingRight = i9;
            alertParams.mViewSpacingBottom = i10;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String str, String str2, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mCursor = cursor;
            alertParams.mOnCheckboxClickListener = onMultiChoiceClickListener;
            alertParams.mIsCheckedColumn = str;
            alertParams.mLabelColumn = str2;
            alertParams.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] charSequenceArr, int i7, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mItems = charSequenceArr;
            alertParams.mOnClickListener = onClickListener;
            alertParams.mCheckedItem = i7;
            alertParams.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter listAdapter, int i7, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f158P;
            alertParams.mAdapter = listAdapter;
            alertParams.mOnClickListener = onClickListener;
            alertParams.mCheckedItem = i7;
            alertParams.mIsSingleChoice = true;
            return this;
        }
    }

    public AlertDialog(@NonNull Context context) {
        this(context, 0);
    }

    public static int resolveDialogTheme(@NonNull Context context, @StyleRes int i7) {
        if (((i7 >>> 24) & 255) >= 1) {
            return i7;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0308R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public Button getButton(int i7) {
        return this.mAlert.getButton(i7);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    @Override // android.support.v7.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlert.installContent();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i7, KeyEvent keyEvent) {
        if (this.mAlert.onKeyDown(i7, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i7, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i7, KeyEvent keyEvent) {
        if (this.mAlert.onKeyUp(i7, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i7, keyEvent);
    }

    public void setButton(int i7, CharSequence charSequence, Message message) {
        this.mAlert.setButton(i7, charSequence, null, message, null);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setButtonPanelLayoutHint(int i7) {
        this.mAlert.setButtonPanelLayoutHint(i7);
    }

    public void setCustomTitle(View view) {
        this.mAlert.setCustomTitle(view);
    }

    public void setIcon(int i7) {
        this.mAlert.setIcon(i7);
    }

    public void setIconAttribute(int i7) {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(i7, typedValue, true);
        this.mAlert.setIcon(typedValue.resourceId);
    }

    public void setMessage(CharSequence charSequence) {
        this.mAlert.setMessage(charSequence);
    }

    @Override // android.support.v7.app.AppCompatDialog, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mAlert.setTitle(charSequence);
    }

    public void setView(View view) {
        this.mAlert.setView(view);
    }

    public AlertDialog(@NonNull Context context, @StyleRes int i7) {
        super(context, resolveDialogTheme(context, i7));
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    public void setButton(int i7, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.mAlert.setButton(i7, charSequence, onClickListener, null, null);
    }

    public void setIcon(Drawable drawable) {
        this.mAlert.setIcon(drawable);
    }

    public void setView(View view, int i7, int i8, int i9, int i10) {
        this.mAlert.setView(view, i7, i8, i9, i10);
    }

    public void setButton(int i7, CharSequence charSequence, Drawable drawable, DialogInterface.OnClickListener onClickListener) {
        this.mAlert.setButton(i7, charSequence, onClickListener, null, drawable);
    }

    public AlertDialog(@NonNull Context context, boolean z6, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        this(context, 0);
        setCancelable(z6);
        setOnCancelListener(onCancelListener);
    }
}
