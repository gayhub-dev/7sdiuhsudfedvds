package com.alibaba.sdk.android.oss.internal;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.graphics.drawable.PathInterpolatorCompat;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.TaskCancelException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.MultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import p009b.C0413b;

/* loaded from: classes.dex */
public abstract class BaseMultipartUploadTask<Request extends MultipartUploadRequest, Result extends CompleteMultipartUploadResult> implements Callable<Result> {
    public final int CPU_SIZE;
    public final int KEEP_ALIVE_TIME;
    public final int MAX_CORE_POOL_SIZE;
    public final int MAX_IMUM_POOL_SIZE;
    public final int MAX_QUEUE_SIZE;
    public final int PART_SIZE_ALIGN_NUM;
    public InternalRequestOperation mApiOperation;
    public boolean mCheckCRC64;
    public OSSCompletedCallback<Request, Result> mCompletedCallback;
    public ExecutionContext mContext;
    public long mFileLength;
    public boolean mIsCancel;
    public long mLastPartSize;
    public Object mLock;
    public int[] mPartAttr;
    public List<PartETag> mPartETags;
    public int mPartExceptionCount;
    public ThreadPoolExecutor mPoolExecutor;
    public OSSProgressCallback<Request> mProgressCallback;
    public Request mRequest;
    public int mRunPartTaskCount;
    public Exception mUploadException;
    public File mUploadFile;
    public String mUploadFilePath;
    public String mUploadId;
    public Uri mUploadUri;
    public long mUploadedLength;

    public BaseMultipartUploadTask(InternalRequestOperation internalRequestOperation, Request request, OSSCompletedCallback<Request, Result> oSSCompletedCallback, ExecutionContext executionContext) {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors() * 2;
        this.CPU_SIZE = iAvailableProcessors;
        int i7 = iAvailableProcessors < 5 ? iAvailableProcessors : 5;
        this.MAX_CORE_POOL_SIZE = i7;
        this.MAX_IMUM_POOL_SIZE = iAvailableProcessors;
        this.KEEP_ALIVE_TIME = PathInterpolatorCompat.MAX_NUM_POINTS;
        this.MAX_QUEUE_SIZE = 5000;
        this.PART_SIZE_ALIGN_NUM = 4096;
        this.mPoolExecutor = new ThreadPoolExecutor(i7, iAvailableProcessors, 3000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(5000), new ThreadFactory() { // from class: com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "oss-android-multipart-thread");
            }
        });
        this.mPartETags = new ArrayList();
        this.mLock = new Object();
        this.mUploadedLength = 0L;
        this.mCheckCRC64 = false;
        this.mPartAttr = new int[2];
        this.mApiOperation = internalRequestOperation;
        this.mRequest = request;
        this.mProgressCallback = request.getProgressCallback();
        this.mCompletedCallback = oSSCompletedCallback;
        this.mContext = executionContext;
        this.mCheckCRC64 = request.getCRC64() == OSSRequest.CRC64Config.YES;
    }

    public abstract void abortThisUpload();

    public long ceilPartSize(long j7) {
        return ((j7 + 4095) / 4096) * 4096;
    }

    public void checkCancel() throws ClientException {
        if (this.mContext.getCancellationHandler().isCancelled()) {
            TaskCancelException taskCancelException = new TaskCancelException("multipart cancel");
            throw new ClientException(taskCancelException.getMessage(), taskCancelException, Boolean.TRUE);
        }
    }

    public void checkException() throws ServiceException, ClientException, IOException {
        if (this.mUploadException != null) {
            releasePool();
            Exception exc = this.mUploadException;
            if (exc instanceof IOException) {
                throw ((IOException) exc);
            }
            if (exc instanceof ServiceException) {
                throw ((ServiceException) exc);
            }
            if (!(exc instanceof ClientException)) {
                throw new ClientException(this.mUploadException.getMessage(), this.mUploadException);
            }
            throw ((ClientException) exc);
        }
    }

    public void checkInitData() throws ClientException, IOException {
        if (this.mRequest.getUploadFilePath() != null) {
            this.mUploadFilePath = this.mRequest.getUploadFilePath();
            this.mUploadedLength = 0L;
            File file = new File(this.mUploadFilePath);
            this.mUploadFile = file;
            this.mFileLength = file.length();
        } else if (this.mRequest.getUploadUri() != null) {
            this.mUploadUri = this.mRequest.getUploadUri();
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = null;
            try {
                try {
                    parcelFileDescriptorOpenFileDescriptor = this.mContext.getApplicationContext().getContentResolver().openFileDescriptor(this.mUploadUri, "r");
                    this.mFileLength = parcelFileDescriptorOpenFileDescriptor.getStatSize();
                    try {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    } catch (IOException e7) {
                        OSSLog.logThrowable2Local(e7);
                    }
                } catch (IOException e8) {
                    throw new ClientException(e8.getMessage(), e8, Boolean.TRUE);
                }
            } catch (Throwable th) {
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    try {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    } catch (IOException e9) {
                        OSSLog.logThrowable2Local(e9);
                    }
                }
                throw th;
            }
        }
        if (this.mFileLength == 0) {
            throw new ClientException("file length must not be 0");
        }
        checkPartSize(this.mPartAttr);
        long partSize = this.mRequest.getPartSize();
        int i7 = this.mPartAttr[1];
        OSSLog.logDebug("[checkInitData] - partNumber : " + i7);
        OSSLog.logDebug("[checkInitData] - partSize : " + partSize);
        if (i7 > 1 && partSize < OSSConstants.MIN_PART_SIZE_LIMIT) {
            throw new ClientException("Part size must be greater than or equal to 100KB!");
        }
    }

    public void checkPartSize(int[] iArr) {
        long partSize = this.mRequest.getPartSize();
        StringBuilder sbM112a = C0413b.m112a("[checkPartSize] - mFileLength : ");
        sbM112a.append(this.mFileLength);
        OSSLog.logDebug(sbM112a.toString());
        OSSLog.logDebug("[checkPartSize] - partSize : " + partSize);
        long j7 = this.mFileLength;
        long j8 = j7 / partSize;
        if (j7 % partSize != 0) {
            j8++;
        }
        if (j8 == 1) {
            partSize = j7;
        } else if (j8 > 5000) {
            partSize = ceilPartSize(j7 / 4999);
            long j9 = this.mFileLength;
            j8 = (j9 / partSize) + (j9 % partSize == 0 ? 0L : 1L);
        }
        int i7 = (int) partSize;
        iArr[0] = i7;
        iArr[1] = (int) j8;
        this.mRequest.setPartSize(i7);
        OSSLog.logDebug("[checkPartSize] - partNumber : " + j8);
        OSSLog.logDebug("[checkPartSize] - partSize : " + i7);
        long j10 = this.mFileLength % partSize;
        if (j10 != 0) {
            partSize = j10;
        }
        this.mLastPartSize = partSize;
    }

    public boolean checkWaitCondition(int i7) {
        return this.mPartETags.size() != i7;
    }

    public CompleteMultipartUploadResult completeMultipartUploadResult() throws ClientException {
        CompleteMultipartUploadResult completeMultipartUploadResultSyncCompleteMultipartUpload;
        if (this.mPartETags.size() > 0) {
            Collections.sort(this.mPartETags, new Comparator<PartETag>() { // from class: com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask.2
                @Override // java.util.Comparator
                public int compare(PartETag partETag, PartETag partETag2) {
                    if (partETag.getPartNumber() < partETag2.getPartNumber()) {
                        return -1;
                    }
                    return partETag.getPartNumber() > partETag2.getPartNumber() ? 1 : 0;
                }
            });
            CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(this.mRequest.getBucketName(), this.mRequest.getObjectKey(), this.mUploadId, this.mPartETags);
            if (this.mRequest.getCallbackParam() != null) {
                completeMultipartUploadRequest.setCallbackParam(this.mRequest.getCallbackParam());
            }
            if (this.mRequest.getCallbackVars() != null) {
                completeMultipartUploadRequest.setCallbackVars(this.mRequest.getCallbackVars());
            }
            if (this.mRequest.getMetadata() != null) {
                ObjectMetadata objectMetadata = new ObjectMetadata();
                for (String str : this.mRequest.getMetadata().getRawMetadata().keySet()) {
                    if (!str.equals(OSSHeaders.STORAGE_CLASS)) {
                        objectMetadata.setHeader(str, this.mRequest.getMetadata().getRawMetadata().get(str));
                    }
                }
                completeMultipartUploadRequest.setMetadata(objectMetadata);
            }
            completeMultipartUploadRequest.setCRC64(this.mRequest.getCRC64());
            completeMultipartUploadResultSyncCompleteMultipartUpload = this.mApiOperation.syncCompleteMultipartUpload(completeMultipartUploadRequest);
        } else {
            completeMultipartUploadResultSyncCompleteMultipartUpload = null;
        }
        this.mUploadedLength = 0L;
        return completeMultipartUploadResultSyncCompleteMultipartUpload;
    }

    public abstract Result doMultipartUpload();

    public abstract void initMultipartUploadId();

    public void notifyMultipartThread() {
        this.mLock.notify();
        this.mPartExceptionCount = 0;
    }

    public void onProgressCallback(Request request, long j7, long j8) {
        OSSProgressCallback<Request> oSSProgressCallback = this.mProgressCallback;
        if (oSSProgressCallback != null) {
            oSSProgressCallback.onProgress(request, j7, j8);
        }
    }

    public void preUploadPart(int i7, int i8, int i9) {
    }

    public abstract void processException(Exception exc);

    public void releasePool() {
        ThreadPoolExecutor threadPoolExecutor = this.mPoolExecutor;
        if (threadPoolExecutor != null) {
            threadPoolExecutor.getQueue().clear();
            this.mPoolExecutor.shutdown();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x016a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:117:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0151 A[Catch: IOException -> 0x0155, TRY_ENTER, TryCatch #1 {IOException -> 0x0155, blocks: (B:48:0x011e, B:50:0x0123, B:52:0x0128, B:74:0x0151, B:78:0x0159, B:80:0x015e), top: B:99:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0159 A[Catch: IOException -> 0x0155, TryCatch #1 {IOException -> 0x0155, blocks: (B:48:0x011e, B:50:0x0123, B:52:0x0128, B:74:0x0151, B:78:0x0159, B:80:0x015e), top: B:99:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x015e A[Catch: IOException -> 0x0155, TRY_LEAVE, TryCatch #1 {IOException -> 0x0155, blocks: (B:48:0x011e, B:50:0x0123, B:52:0x0128, B:74:0x0151, B:78:0x0159, B:80:0x015e), top: B:99:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0172 A[Catch: IOException -> 0x016e, TryCatch #7 {IOException -> 0x016e, blocks: (B:87:0x016a, B:91:0x0172, B:93:0x0177), top: B:103:0x016a }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0177 A[Catch: IOException -> 0x016e, TRY_LEAVE, TryCatch #7 {IOException -> 0x016e, blocks: (B:87:0x016a, B:91:0x0172, B:93:0x0177), top: B:103:0x016a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void uploadPart(int r12, int r13, int r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 383
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask.uploadPart(int, int, int):void");
    }

    public void uploadPartFinish(PartETag partETag) {
    }

    @Override // java.util.concurrent.Callable
    public Result call() throws ServiceException, ClientException {
        try {
            checkInitData();
            initMultipartUploadId();
            Result result = (Result) doMultipartUpload();
            OSSCompletedCallback<Request, Result> oSSCompletedCallback = this.mCompletedCallback;
            if (oSSCompletedCallback != null) {
                oSSCompletedCallback.onSuccess(this.mRequest, result);
            }
            return result;
        } catch (ServiceException e7) {
            OSSCompletedCallback<Request, Result> oSSCompletedCallback2 = this.mCompletedCallback;
            if (oSSCompletedCallback2 != null) {
                oSSCompletedCallback2.onFailure(this.mRequest, null, e7);
            }
            throw e7;
        } catch (Exception e8) {
            ClientException clientException = e8 instanceof ClientException ? (ClientException) e8 : new ClientException(e8.toString(), e8);
            OSSCompletedCallback<Request, Result> oSSCompletedCallback3 = this.mCompletedCallback;
            if (oSSCompletedCallback3 != null) {
                oSSCompletedCallback3.onFailure(this.mRequest, clientException, null);
            }
            throw clientException;
        }
    }
}
