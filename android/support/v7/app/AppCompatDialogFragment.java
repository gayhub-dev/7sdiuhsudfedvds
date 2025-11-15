package android.support.v7.app;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.v4.app.DialogFragment;

/* loaded from: classes.dex */
public class AppCompatDialogFragment extends DialogFragment {
    @Override // android.support.v4.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return new AppCompatDialog(getContext(), getTheme());
    }

    @Override // android.support.v4.app.DialogFragment
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setupDialog(Dialog dialog, int i7) {
        if (!(dialog instanceof AppCompatDialog)) {
            super.setupDialog(dialog, i7);
            return;
        }
        AppCompatDialog appCompatDialog = (AppCompatDialog) dialog;
        if (i7 != 1 && i7 != 2) {
            if (i7 != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        appCompatDialog.supportRequestWindowFeature(1);
    }
}
