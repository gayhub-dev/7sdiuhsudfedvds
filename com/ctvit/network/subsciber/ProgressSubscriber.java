package com.ctvit.network.subsciber;

import android.app.Dialog;
import android.content.DialogInterface;
import com.ctvit.network.exception.ApiException;

/* loaded from: classes.dex */
public abstract class ProgressSubscriber<T> extends BaseSubscriber<T> implements ProgressCancelListener {
    private boolean isShowProgress;
    private Dialog mDialog;
    private IProgressDialog progressDialog;

    public ProgressSubscriber() {
        this.isShowProgress = true;
        init(false);
    }

    private void dismissProgress() {
        Dialog dialog;
        if (this.isShowProgress && (dialog = this.mDialog) != null && dialog.isShowing()) {
            this.mDialog.dismiss();
        }
    }

    private void init(boolean z6) {
        IProgressDialog iProgressDialog = this.progressDialog;
        if (iProgressDialog == null) {
            return;
        }
        Dialog dialog = iProgressDialog.getDialog();
        this.mDialog = dialog;
        if (dialog == null) {
            return;
        }
        dialog.setCancelable(z6);
        if (z6) {
            this.mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.ctvit.network.subsciber.ProgressSubscriber.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    ProgressSubscriber.this.onCancelProgress();
                }
            });
        }
    }

    private void showProgress() {
        Dialog dialog;
        if (!this.isShowProgress || (dialog = this.mDialog) == null || dialog.isShowing()) {
            return;
        }
        this.mDialog.show();
    }

    @Override // com.ctvit.network.subsciber.ProgressCancelListener
    public void onCancelProgress() {
        if (isDisposed()) {
            return;
        }
        dispose();
    }

    @Override // com.ctvit.network.subsciber.BaseSubscriber, p194y3.InterfaceC2127s
    public void onComplete() {
        dismissProgress();
    }

    @Override // com.ctvit.network.subsciber.BaseSubscriber
    public void onError(ApiException apiException) {
        dismissProgress();
    }

    @Override // com.ctvit.network.subsciber.BaseSubscriber, p153s4.AbstractC1880c
    public void onStart() {
        showProgress();
    }

    public ProgressSubscriber(IProgressDialog iProgressDialog) {
        this.isShowProgress = true;
        this.progressDialog = iProgressDialog;
        init(false);
    }

    public ProgressSubscriber(IProgressDialog iProgressDialog, boolean z6, boolean z7) {
        this.isShowProgress = true;
        this.progressDialog = iProgressDialog;
        this.isShowProgress = z6;
        init(z7);
    }
}
