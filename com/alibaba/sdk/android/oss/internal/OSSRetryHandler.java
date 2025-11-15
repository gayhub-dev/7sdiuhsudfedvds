package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSLog;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class OSSRetryHandler {
    private int maxRetryCount = 2;

    /* renamed from: com.alibaba.sdk.android.oss.internal.OSSRetryHandler$1 */
    public static /* synthetic */ class C05531 {
        public static final /* synthetic */ int[] $SwitchMap$com$alibaba$sdk$android$oss$internal$OSSRetryType;

        static {
            int[] iArr = new int[OSSRetryType.values().length];
            $SwitchMap$com$alibaba$sdk$android$oss$internal$OSSRetryType = iArr;
            try {
                iArr[OSSRetryType.OSSRetryTypeShouldRetry.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public OSSRetryHandler(int i7) {
        setMaxRetryCount(i7);
    }

    public void setMaxRetryCount(int i7) {
        this.maxRetryCount = i7;
    }

    public OSSRetryType shouldRetry(Exception exc, int i7) {
        if (i7 >= this.maxRetryCount) {
            return OSSRetryType.OSSRetryTypeShouldNotRetry;
        }
        if (!(exc instanceof ClientException)) {
            if (!(exc instanceof ServiceException)) {
                return OSSRetryType.OSSRetryTypeShouldNotRetry;
            }
            ServiceException serviceException = (ServiceException) exc;
            return (serviceException.getErrorCode() == null || !serviceException.getErrorCode().equalsIgnoreCase("RequestTimeTooSkewed")) ? serviceException.getStatusCode() >= 500 ? OSSRetryType.OSSRetryTypeShouldRetry : OSSRetryType.OSSRetryTypeShouldNotRetry : OSSRetryType.OSSRetryTypeShouldFixedTimeSkewedAndRetry;
        }
        if (((ClientException) exc).isCanceledException().booleanValue()) {
            return OSSRetryType.OSSRetryTypeShouldNotRetry;
        }
        Exception exc2 = (Exception) exc.getCause();
        if ((exc2 instanceof InterruptedIOException) && !(exc2 instanceof SocketTimeoutException)) {
            OSSLog.logError("[shouldRetry] - is interrupted!");
            return OSSRetryType.OSSRetryTypeShouldNotRetry;
        }
        if (exc2 instanceof IllegalArgumentException) {
            return OSSRetryType.OSSRetryTypeShouldNotRetry;
        }
        StringBuilder sbM112a = C0413b.m112a("shouldRetry - ");
        sbM112a.append(exc.toString());
        OSSLog.logDebug(sbM112a.toString());
        exc.getCause().printStackTrace();
        return OSSRetryType.OSSRetryTypeShouldRetry;
    }

    public long timeInterval(int i7, OSSRetryType oSSRetryType) {
        if (C05531.$SwitchMap$com$alibaba$sdk$android$oss$internal$OSSRetryType[oSSRetryType.ordinal()] != 1) {
            return 0L;
        }
        return ((long) Math.pow(2.0d, i7)) * 200;
    }
}
