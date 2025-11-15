package com.alibaba.sdk.android.oss;

import android.arch.lifecycle.C0063n;

/* loaded from: classes.dex */
public class TaskCancelException extends Exception {
    public TaskCancelException() {
    }

    public TaskCancelException(String str) {
        super(C0063n.m88a("[ErrorMessage]: ", str));
    }

    public TaskCancelException(Throwable th) {
        super(th);
    }
}
