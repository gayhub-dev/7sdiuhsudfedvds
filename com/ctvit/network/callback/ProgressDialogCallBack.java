package com.ctvit.network.callback;

import android.app.Dialog;
import android.content.DialogInterface;
import com.ctvit.network.exception.ApiException;
import com.ctvit.network.subsciber.IProgressDialog;
import com.ctvit.network.subsciber.ProgressCancelListener;
import p201z3.InterfaceC2153b;

/* loaded from: classes.dex */
public abstract class ProgressDialogCallBack<T> extends CallBack<T> implements ProgressCancelListener {
    private InterfaceC2153b disposed;
    private boolean isShowProgress;
    private Dialog mDialog;
    private IProgressDialog progressDialog;

    public ProgressDialogCallBack(IProgressDialog iProgressDialog) {
        this.isShowProgress = true;
        this.progressDialog = iProgressDialog;
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
            this.mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.ctvit.network.callback.ProgressDialogCallBack.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    ProgressDialogCallBack.this.onCancelProgress();
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
        InterfaceC2153b interfaceC2153b = this.disposed;
        if (interfaceC2153b == null || interfaceC2153b.isDisposed()) {
            return;
        }
        this.disposed.dispose();
    }

    @Override // com.ctvit.network.callback.CallBack
    public void onCompleted() {
        dismissProgress();
    }

    @Override // com.ctvit.network.callback.CallBack
    public void onError(ApiException apiException) {
        dismissProgress();
    }

    @Override // com.ctvit.network.callback.CallBack
    public void onStart() {
        showProgress();
    }

    public void subscription(InterfaceC2153b interfaceC2153b) {
        this.disposed = interfaceC2153b;
    }

    public ProgressDialogCallBack(IProgressDialog iProgressDialog, boolean z6, boolean z7) {
        this.isShowProgress = true;
        this.progressDialog = iProgressDialog;
        this.isShowProgress = z6;
        init(z7);
    }
}
