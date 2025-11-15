package com.alibaba.sdk.android.oss.internal;

import android.support.constraint.C0072a;
import android.support.graphics.drawable.PathInterpolatorCompat;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.TaskCancelException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.BinaryUtil;
import com.alibaba.sdk.android.oss.common.utils.CRC64;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.exception.InconsistentException;
import com.alibaba.sdk.android.oss.model.GetObjectMetaRequest;
import com.alibaba.sdk.android.oss.model.GetObjectMetaResult;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.Range;
import com.alibaba.sdk.android.oss.model.ResumableDownloadRequest;
import com.alibaba.sdk.android.oss.model.ResumableDownloadResult;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class ResumableDownloadTask<Requst extends ResumableDownloadRequest, Result extends ResumableDownloadResult> implements Callable<Result> {
    public static final String TEMP_SUFFIX = ".temp";
    public final int CPU_SIZE;
    public final int KEEP_ALIVE_TIME;
    public final int MAX_CORE_POOL_SIZE;
    public final int MAX_IMUM_POOL_SIZE;
    public final int MAX_QUEUE_SIZE;
    public final int PART_SIZE_ALIGN_NUM;
    public String checkpointPath;
    public long completedPartSize;
    public long downloadPartSize;
    private CheckPoint mCheckPoint;
    private OSSCompletedCallback mCompletedCallback;
    private ExecutionContext mContext;
    public Exception mDownloadException;
    public Object mLock;
    private InternalRequestOperation mOperation;
    public long mPartExceptionCount;
    public ThreadPoolExecutor mPoolExecutor;
    private OSSProgressCallback mProgressCallback;
    private ResumableDownloadRequest mRequest;

    public static class CheckPoint implements Serializable {
        private static final long serialVersionUID = -8470273912385636504L;
        public String bucketName;
        public String downloadFile;
        public long downloadLength;
        public FileStat fileStat;
        public int md5;
        public String objectKey;
        public ArrayList<DownloadPart> parts;

        private void assign(CheckPoint checkPoint) {
            this.md5 = checkPoint.md5;
            this.downloadFile = checkPoint.downloadFile;
            this.bucketName = checkPoint.bucketName;
            this.objectKey = checkPoint.objectKey;
            this.fileStat = checkPoint.fileStat;
            this.parts = checkPoint.parts;
            this.downloadLength = checkPoint.downloadLength;
        }

        public synchronized void dump(String str) {
            FileOutputStream fileOutputStream;
            ObjectOutputStream objectOutputStream;
            this.md5 = hashCode();
            File file = new File(str + ResumableDownloadTask.TEMP_SUFFIX);
            ObjectOutputStream objectOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    objectOutputStream.writeObject(this);
                    file.renameTo(new File(str));
                    objectOutputStream.close();
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th = th2;
                    objectOutputStream2 = objectOutputStream;
                    if (objectOutputStream2 != null) {
                        objectOutputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
        }

        public int hashCode() {
            String str = this.bucketName;
            int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
            String str2 = this.downloadFile;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.objectKey;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            FileStat fileStat = this.fileStat;
            int iHashCode4 = (iHashCode3 + (fileStat == null ? 0 : fileStat.hashCode())) * 31;
            ArrayList<DownloadPart> arrayList = this.parts;
            int iHashCode5 = (iHashCode4 + (arrayList != null ? arrayList.hashCode() : 0)) * 31;
            long j7 = this.downloadLength;
            return iHashCode5 + ((int) (j7 ^ (j7 >>> 32)));
        }

        public synchronized boolean isValid(InternalRequestOperation internalRequestOperation) {
            if (this.md5 != hashCode()) {
                return false;
            }
            FileStat fileStat = FileStat.getFileStat(internalRequestOperation, this.bucketName, this.objectKey);
            FileStat fileStat2 = this.fileStat;
            Date date = fileStat2.lastModified;
            if (date == null) {
                if (fileStat2.fileLength != fileStat.fileLength || !fileStat2.etag.equals(fileStat.etag)) {
                    return false;
                }
            } else if (fileStat2.fileLength != fileStat.fileLength || !date.equals(fileStat.lastModified) || !this.fileStat.etag.equals(fileStat.etag)) {
                return false;
            }
            return true;
        }

        public synchronized void load(String str) {
            FileInputStream fileInputStream;
            Throwable th;
            ObjectInputStream objectInputStream;
            try {
                try {
                    fileInputStream = new FileInputStream(str);
                    try {
                        objectInputStream = new ObjectInputStream(fileInputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        objectInputStream = null;
                    }
                    try {
                        assign((CheckPoint) objectInputStream.readObject());
                        objectInputStream.close();
                        fileInputStream.close();
                    } catch (Throwable th3) {
                        th = th3;
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    fileInputStream = null;
                    th = th4;
                    objectInputStream = null;
                }
            } catch (Throwable th5) {
                throw th5;
            }
        }

        public synchronized void update(int i7, boolean z6) {
            this.parts.get(i7).isCompleted = z6;
            this.downloadLength += this.parts.get(i7).length;
        }
    }

    public class DownloadFileResult extends OSSResult {
        public ObjectMetadata metadata;
        public ArrayList<DownloadPartResult> partResults;

        public DownloadFileResult() {
        }
    }

    public static class DownloadPart implements Serializable {
        private static final long serialVersionUID = -3506020776131733942L;
        public long crc;
        public long end;
        public long fileStart;
        public boolean isCompleted;
        public long length;
        public int partNumber;
        public long start;

        public int hashCode() {
            int i7 = (((this.partNumber + 31) * 31) + (this.isCompleted ? 1231 : 1237)) * 31;
            long j7 = this.end;
            int i8 = (i7 + ((int) (j7 ^ (j7 >>> 32)))) * 31;
            long j8 = this.start;
            long j9 = this.crc;
            return ((i8 + ((int) (j8 ^ (j8 >>> 32)))) * 31) + ((int) (j9 ^ (j9 >>> 32)));
        }
    }

    public static class DownloadPartResult {
        public Long clientCRC;
        public long length;
        public int partNumber;
        public String requestId;
    }

    public static class FileStat implements Serializable {
        private static final long serialVersionUID = 3896323364904643963L;
        public String etag;
        public long fileLength;
        public Date lastModified;
        public String md5;
        public String requestId;
        public Long serverCRC;

        public static FileStat getFileStat(InternalRequestOperation internalRequestOperation, String str, String str2) {
            GetObjectMetaResult getObjectMetaResult = (GetObjectMetaResult) internalRequestOperation.getObjectMeta(new GetObjectMetaRequest(str, str2), null).getResult();
            FileStat fileStat = new FileStat();
            fileStat.fileLength = getObjectMetaResult.getMetadata().getContentLength();
            fileStat.etag = getObjectMetaResult.getMetadata().getETag();
            fileStat.lastModified = getObjectMetaResult.getMetadata().getLastModified();
            fileStat.serverCRC = getObjectMetaResult.getServerCRC();
            fileStat.requestId = getObjectMetaResult.getRequestId();
            return fileStat;
        }

        public int hashCode() {
            String str = this.etag;
            int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
            Date date = this.lastModified;
            int iHashCode2 = (iHashCode + (date != null ? date.hashCode() : 0)) * 31;
            long j7 = this.fileLength;
            return iHashCode2 + ((int) (j7 ^ (j7 >>> 32)));
        }
    }

    public ResumableDownloadTask(InternalRequestOperation internalRequestOperation, ResumableDownloadRequest resumableDownloadRequest, OSSCompletedCallback oSSCompletedCallback, ExecutionContext executionContext) {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors() * 2;
        this.CPU_SIZE = iAvailableProcessors;
        int i7 = iAvailableProcessors < 5 ? iAvailableProcessors : 5;
        this.MAX_CORE_POOL_SIZE = i7;
        this.MAX_IMUM_POOL_SIZE = iAvailableProcessors;
        this.KEEP_ALIVE_TIME = PathInterpolatorCompat.MAX_NUM_POINTS;
        this.PART_SIZE_ALIGN_NUM = 4096;
        this.MAX_QUEUE_SIZE = 5000;
        this.mPoolExecutor = new ThreadPoolExecutor(i7, iAvailableProcessors, 3000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(5000), new ThreadFactory() { // from class: com.alibaba.sdk.android.oss.internal.ResumableDownloadTask.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "oss-android-multipart-thread");
            }
        });
        this.mLock = new Object();
        this.mRequest = resumableDownloadRequest;
        this.mOperation = internalRequestOperation;
        this.mCompletedCallback = oSSCompletedCallback;
        this.mContext = executionContext;
        this.mProgressCallback = resumableDownloadRequest.getProgressListener();
    }

    private static Long calcObjectCRCFromParts(List<DownloadPartResult> list) {
        long jCombine = 0;
        for (DownloadPartResult downloadPartResult : list) {
            Long l7 = downloadPartResult.clientCRC;
            if (l7 == null || downloadPartResult.length <= 0) {
                return null;
            }
            jCombine = CRC64.combine(jCombine, l7.longValue(), downloadPartResult.length);
        }
        return new Long(jCombine);
    }

    private long ceilPartSize(long j7) {
        return ((j7 + 4095) / 4096) * 4096;
    }

    private void checkPartSize(long j7, int[] iArr) {
        long partSize = this.mRequest.getPartSize();
        OSSLog.logDebug("[checkPartSize] - mFileLength : " + j7);
        OSSLog.logDebug("[checkPartSize] - partSize : " + partSize);
        long j8 = j7 / partSize;
        if (j7 % partSize != 0) {
            j8++;
        }
        if (j8 != 1) {
            if (j8 > 5000) {
                partSize = ceilPartSize(j7 / 4999);
                j8 = (j7 / partSize) + (j7 % partSize == 0 ? 0L : 1L);
            }
            j7 = partSize;
        }
        int i7 = (int) j7;
        iArr[0] = i7;
        iArr[1] = (int) j8;
        OSSLog.logDebug("[checkPartSize] - partNumber : " + j8);
        OSSLog.logDebug("[checkPartSize] - partSize : " + i7);
    }

    private void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int i7 = inputStream.read(bArr);
            if (i7 == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i7);
            }
        }
    }

    private Range correctRange(Range range, long j7) {
        if (range != null) {
            begin = range.getBegin() != -1 ? range.getBegin() : 0L;
            j7 = range.getEnd() == -1 ? j7 - begin : range.getEnd() - range.getBegin();
        }
        return new Range(begin, j7 + begin);
    }

    private void createFile(String str, long j7) throws Throwable {
        RandomAccessFile randomAccessFile;
        Throwable th;
        try {
            randomAccessFile = new RandomAccessFile(new File(str), "rw");
        } catch (Throwable th2) {
            randomAccessFile = null;
            th = th2;
        }
        try {
            randomAccessFile.setLength(j7);
            randomAccessFile.close();
        } catch (Throwable th3) {
            th = th3;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0187 A[Catch: IOException -> 0x0183, TRY_LEAVE, TryCatch #2 {IOException -> 0x0183, blocks: (B:61:0x017f, B:65:0x0187), top: B:69:0x017f }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x017f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void downloadPart(com.alibaba.sdk.android.oss.internal.ResumableDownloadTask<Requst, Result>.DownloadFileResult r13, com.alibaba.sdk.android.oss.internal.ResumableDownloadTask.DownloadPart r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 399
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.ResumableDownloadTask.downloadPart(com.alibaba.sdk.android.oss.internal.ResumableDownloadTask$DownloadFileResult, com.alibaba.sdk.android.oss.internal.ResumableDownloadTask$DownloadPart):void");
    }

    private void initCheckPoint() throws Throwable {
        FileStat fileStat = FileStat.getFileStat(this.mOperation, this.mRequest.getBucketName(), this.mRequest.getObjectKey());
        Range rangeCorrectRange = correctRange(this.mRequest.getRange(), fileStat.fileLength);
        createFile(this.mRequest.getTempFilePath(), rangeCorrectRange.getEnd() - rangeCorrectRange.getBegin());
        this.mCheckPoint.bucketName = this.mRequest.getBucketName();
        this.mCheckPoint.objectKey = this.mRequest.getObjectKey();
        CheckPoint checkPoint = this.mCheckPoint;
        checkPoint.fileStat = fileStat;
        checkPoint.parts = splitFile(rangeCorrectRange, fileStat.fileLength, this.mRequest.getPartSize());
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void moveFile(java.io.File r5, java.io.File r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5.renameTo(r6)
            if (r0 != 0) goto L5b
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L49 java.io.FileNotFoundException -> L4c
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L49 java.io.FileNotFoundException -> L4c
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L41 java.io.FileNotFoundException -> L45
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L41 java.io.FileNotFoundException -> L45
            r4.copyFile(r1, r2)     // Catch: java.lang.Throwable -> L3d java.io.FileNotFoundException -> L3f
            boolean r6 = r5.delete()     // Catch: java.lang.Throwable -> L3d java.io.FileNotFoundException -> L3f
            if (r6 == 0) goto L21
            r1.close()
            r2.close()
            goto L5b
        L21:
            java.io.IOException r6 = new java.io.IOException     // Catch: java.lang.Throwable -> L3d java.io.FileNotFoundException -> L3f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3d java.io.FileNotFoundException -> L3f
            r0.<init>()     // Catch: java.lang.Throwable -> L3d java.io.FileNotFoundException -> L3f
            java.lang.String r3 = "Failed to delete original file '"
            r0.append(r3)     // Catch: java.lang.Throwable -> L3d java.io.FileNotFoundException -> L3f
            r0.append(r5)     // Catch: java.lang.Throwable -> L3d java.io.FileNotFoundException -> L3f
            java.lang.String r5 = "'"
            r0.append(r5)     // Catch: java.lang.Throwable -> L3d java.io.FileNotFoundException -> L3f
            java.lang.String r5 = r0.toString()     // Catch: java.lang.Throwable -> L3d java.io.FileNotFoundException -> L3f
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L3d java.io.FileNotFoundException -> L3f
            throw r6     // Catch: java.lang.Throwable -> L3d java.io.FileNotFoundException -> L3f
        L3d:
            r5 = move-exception
            goto L43
        L3f:
            r5 = move-exception
            goto L47
        L41:
            r5 = move-exception
            r2 = r0
        L43:
            r0 = r1
            goto L50
        L45:
            r5 = move-exception
            r2 = r0
        L47:
            r0 = r1
            goto L4e
        L49:
            r5 = move-exception
            r2 = r0
            goto L50
        L4c:
            r5 = move-exception
            r2 = r0
        L4e:
            throw r5     // Catch: java.lang.Throwable -> L4f
        L4f:
            r5 = move-exception
        L50:
            if (r0 == 0) goto L55
            r0.close()
        L55:
            if (r2 == 0) goto L5a
            r2.close()
        L5a:
            throw r5
        L5b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.ResumableDownloadTask.moveFile(java.io.File, java.io.File):void");
    }

    private ArrayList<DownloadPart> splitFile(Range range, long j7, long j8) {
        int i7 = 0;
        if (j7 <= 0) {
            DownloadPart downloadPart = new DownloadPart();
            downloadPart.start = 0L;
            downloadPart.end = -1L;
            downloadPart.length = 0L;
            downloadPart.partNumber = 0;
            ArrayList<DownloadPart> arrayList = new ArrayList<>();
            arrayList.add(downloadPart);
            return arrayList;
        }
        long begin = range.getBegin();
        long end = range.getEnd() - range.getBegin();
        int[] iArr = new int[2];
        checkPartSize(end, iArr);
        long j9 = iArr[1];
        ArrayList<DownloadPart> arrayList2 = new ArrayList<>();
        while (true) {
            long j10 = i7;
            if (j10 >= j9) {
                return arrayList2;
            }
            DownloadPart downloadPart2 = new DownloadPart();
            long j11 = j10 * j8;
            long j12 = begin + j11;
            downloadPart2.start = j12;
            int i8 = i7 + 1;
            long j13 = j9;
            long j14 = ((i8 * j8) + begin) - 1;
            downloadPart2.end = j14;
            downloadPart2.length = (j14 - j12) + 1;
            long j15 = begin + end;
            if (j14 >= j15) {
                downloadPart2.end = -1L;
                downloadPart2.length = j15 - j12;
            }
            downloadPart2.partNumber = i7;
            downloadPart2.fileStart = j11;
            arrayList2.add(downloadPart2);
            i7 = i8;
            j9 = j13;
        }
    }

    public void checkCancel() throws ClientException {
        if (this.mContext.getCancellationHandler().isCancelled()) {
            TaskCancelException taskCancelException = new TaskCancelException("Resumable download cancel");
            throw new ClientException(taskCancelException.getMessage(), taskCancelException, Boolean.TRUE);
        }
    }

    public void checkException() throws ServiceException, ClientException, IOException {
        if (this.mDownloadException != null) {
            releasePool();
            Exception exc = this.mDownloadException;
            if (exc instanceof IOException) {
                throw ((IOException) exc);
            }
            if (exc instanceof ServiceException) {
                throw ((ServiceException) exc);
            }
            if (!(exc instanceof ClientException)) {
                throw new ClientException(this.mDownloadException.getMessage(), this.mDownloadException);
            }
            throw ((ClientException) exc);
        }
    }

    public void checkInitData() throws Throwable {
        if (this.mRequest.getRange() != null && !this.mRequest.getRange().checkIsValid()) {
            throw new ClientException("Range is invalid");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mRequest.getBucketName());
        sb.append(this.mRequest.getObjectKey());
        sb.append(String.valueOf(this.mRequest.getPartSize()));
        sb.append(this.mRequest.getCRC64() == OSSRequest.CRC64Config.YES ? "-crc64" : "");
        String strCalculateMd5Str = BinaryUtil.calculateMd5Str(sb.toString().getBytes());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mRequest.getCheckPointFilePath());
        this.checkpointPath = C0072a.m92a(sb2, File.separator, strCalculateMd5Str);
        String strM92a = C0072a.m92a(new StringBuilder(), this.checkpointPath, TEMP_SUFFIX);
        this.mCheckPoint = new CheckPoint();
        if (!this.mRequest.getEnableCheckPoint().booleanValue()) {
            initCheckPoint();
            return;
        }
        try {
            this.mCheckPoint.load(this.checkpointPath);
        } catch (Exception unused) {
            removeFile(this.checkpointPath);
            removeFile(strM92a);
            removeFile(this.mRequest.getTempFilePath());
        }
        if (this.mCheckPoint.isValid(this.mOperation)) {
            return;
        }
        removeFile(this.checkpointPath);
        removeFile(strM92a);
        removeFile(this.mRequest.getTempFilePath());
        initCheckPoint();
    }

    public boolean checkWaitCondition(int i7) {
        return this.completedPartSize != ((long) i7);
    }

    public ResumableDownloadResult doMultipartDownload() throws Throwable {
        String str = this.checkpointPath + TEMP_SUFFIX;
        checkCancel();
        ResumableDownloadResult resumableDownloadResult = new ResumableDownloadResult();
        final DownloadFileResult downloadFileResult = new DownloadFileResult();
        downloadFileResult.partResults = new ArrayList<>();
        Iterator<DownloadPart> it = this.mCheckPoint.parts.iterator();
        while (it.hasNext()) {
            final DownloadPart next = it.next();
            checkException();
            ThreadPoolExecutor threadPoolExecutor = this.mPoolExecutor;
            if (threadPoolExecutor == null || next.isCompleted) {
                DownloadPartResult downloadPartResult = new DownloadPartResult();
                downloadPartResult.partNumber = next.partNumber;
                downloadPartResult.requestId = this.mCheckPoint.fileStat.requestId;
                downloadPartResult.length = next.length;
                if (this.mRequest.getCRC64() == OSSRequest.CRC64Config.YES) {
                    downloadPartResult.clientCRC = Long.valueOf(next.crc);
                }
                downloadFileResult.partResults.add(downloadPartResult);
                this.downloadPartSize++;
                this.completedPartSize++;
            } else {
                threadPoolExecutor.execute(new Runnable() { // from class: com.alibaba.sdk.android.oss.internal.ResumableDownloadTask.2
                    @Override // java.lang.Runnable
                    public void run() throws Throwable {
                        ResumableDownloadTask.this.downloadPart(downloadFileResult, next);
                        DownloadPart downloadPart = next;
                        long j7 = downloadPart.start;
                        long j8 = downloadPart.end;
                    }
                });
            }
        }
        if (checkWaitCondition(this.mCheckPoint.parts.size())) {
            synchronized (this.mLock) {
                this.mLock.wait();
            }
        }
        checkException();
        Collections.sort(downloadFileResult.partResults, new Comparator<DownloadPartResult>() { // from class: com.alibaba.sdk.android.oss.internal.ResumableDownloadTask.3
            @Override // java.util.Comparator
            public int compare(DownloadPartResult downloadPartResult2, DownloadPartResult downloadPartResult3) {
                return downloadPartResult2.partNumber - downloadPartResult3.partNumber;
            }
        });
        if (this.mRequest.getCRC64() == OSSRequest.CRC64Config.YES && this.mRequest.getRange() == null) {
            Long lCalcObjectCRCFromParts = calcObjectCRCFromParts(downloadFileResult.partResults);
            resumableDownloadResult.setClientCRC(lCalcObjectCRCFromParts);
            try {
                OSSUtils.checkChecksum(lCalcObjectCRCFromParts, this.mCheckPoint.fileStat.serverCRC, downloadFileResult.partResults.get(0).requestId);
            } catch (InconsistentException e7) {
                removeFile(this.checkpointPath);
                removeFile(str);
                removeFile(this.mRequest.getTempFilePath());
                throw e7;
            }
        }
        removeFile(this.checkpointPath);
        removeFile(str);
        moveFile(new File(this.mRequest.getTempFilePath()), new File(this.mRequest.getDownloadToFilePath()));
        resumableDownloadResult.setServerCRC(this.mCheckPoint.fileStat.serverCRC);
        resumableDownloadResult.setMetadata(downloadFileResult.metadata);
        resumableDownloadResult.setRequestId(downloadFileResult.partResults.get(0).requestId);
        resumableDownloadResult.setStatusCode(200);
        return resumableDownloadResult;
    }

    public void notifyMultipartThread() {
        this.mLock.notify();
        this.mPartExceptionCount = 0L;
    }

    public void processException(Exception exc) {
        synchronized (this.mLock) {
            this.mPartExceptionCount++;
            if (this.mDownloadException == null) {
                this.mDownloadException = exc;
                this.mLock.notify();
            }
        }
    }

    public void releasePool() {
        ThreadPoolExecutor threadPoolExecutor = this.mPoolExecutor;
        if (threadPoolExecutor != null) {
            threadPoolExecutor.getQueue().clear();
            this.mPoolExecutor.shutdown();
        }
    }

    public boolean removeFile(String str) {
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    @Override // java.util.concurrent.Callable
    public Result call() throws Throwable {
        try {
            checkInitData();
            Result result = (Result) doMultipartDownload();
            OSSCompletedCallback oSSCompletedCallback = this.mCompletedCallback;
            if (oSSCompletedCallback != null) {
                oSSCompletedCallback.onSuccess(this.mRequest, result);
            }
            return result;
        } catch (ServiceException e7) {
            OSSCompletedCallback oSSCompletedCallback2 = this.mCompletedCallback;
            if (oSSCompletedCallback2 != null) {
                oSSCompletedCallback2.onFailure(this.mRequest, null, e7);
            }
            throw e7;
        } catch (Exception e8) {
            ClientException clientException = e8 instanceof ClientException ? (ClientException) e8 : new ClientException(e8.toString(), e8);
            OSSCompletedCallback oSSCompletedCallback3 = this.mCompletedCallback;
            if (oSSCompletedCallback3 != null) {
                oSSCompletedCallback3.onFailure(this.mRequest, clientException, null);
            }
            throw clientException;
        }
    }
}
