package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import p009b.C0413b;

/* loaded from: classes.dex */
public class OSSAsyncTask<T extends OSSResult> {
    private volatile boolean canceled;
    private ExecutionContext context;
    private Future<T> future;

    public static OSSAsyncTask wrapRequestTask(Future future, ExecutionContext executionContext) {
        OSSAsyncTask oSSAsyncTask = new OSSAsyncTask();
        oSSAsyncTask.future = future;
        oSSAsyncTask.context = executionContext;
        return oSSAsyncTask;
    }

    public void cancel() {
        this.canceled = true;
        ExecutionContext executionContext = this.context;
        if (executionContext != null) {
            executionContext.getCancellationHandler().cancel();
        }
    }

    public T getResult() throws ServiceException, ClientException {
        try {
            return this.future.get();
        } catch (InterruptedException e7) {
            StringBuilder sbM112a = C0413b.m112a(" InterruptedException and message : ");
            sbM112a.append(e7.getMessage());
            throw new ClientException(sbM112a.toString(), e7);
        } catch (ExecutionException e8) {
            Throwable cause = e8.getCause();
            if (cause instanceof ClientException) {
                throw ((ClientException) cause);
            }
            if (cause instanceof ServiceException) {
                throw ((ServiceException) cause);
            }
            cause.printStackTrace();
            StringBuilder sbM112a2 = C0413b.m112a("Unexpected exception!");
            sbM112a2.append(cause.getMessage());
            throw new ClientException(sbM112a2.toString());
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public boolean isCompleted() {
        return this.future.isDone();
    }

    public void waitUntilFinished() throws ExecutionException, InterruptedException {
        try {
            this.future.get();
        } catch (Exception unused) {
        }
    }
}
