package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.exception.InconsistentException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

/* loaded from: classes.dex */
public class CheckCRC64DownloadInputStream extends CheckedInputStream {
    private long mClientCRC64;
    private String mRequestId;
    private long mServerCRC64;
    private long mTotalBytesRead;
    private long mTotalLength;

    public CheckCRC64DownloadInputStream(InputStream inputStream, Checksum checksum, long j7, long j8, String str) {
        super(inputStream, checksum);
        this.mTotalLength = j7;
        this.mServerCRC64 = j8;
        this.mRequestId = str;
    }

    private void checkCRC64(int i7) throws InconsistentException {
        long j7 = this.mTotalBytesRead + i7;
        this.mTotalBytesRead = j7;
        if (j7 >= this.mTotalLength) {
            long value = getChecksum().getValue();
            this.mClientCRC64 = value;
            OSSUtils.checkChecksum(Long.valueOf(value), Long.valueOf(this.mServerCRC64), this.mRequestId);
        }
    }

    public long getClientCRC64() {
        return this.mClientCRC64;
    }

    @Override // java.util.zip.CheckedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i7 = super.read();
        checkCRC64(i7);
        return i7;
    }

    @Override // java.util.zip.CheckedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i7, int i8) throws IOException {
        int i9 = super.read(bArr, i7, i8);
        checkCRC64(i9);
        return i9;
    }
}
