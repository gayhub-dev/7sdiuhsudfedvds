package okhttp3.internal.publicsuffix;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;

/* loaded from: classes.dex */
public final class PublicSuffixDatabase {
    private static final byte EXCEPTION_MARKER = 33;
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    private byte[] publicSuffixExceptionListBytes;
    private byte[] publicSuffixListBytes;
    private static final byte[] WILDCARD_LABEL = {42};
    private static final String[] EMPTY_RULE = new String[0];
    private static final String[] PREVAILING_RULE = {"*"};
    private static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);

    private static String binarySearchBytes(byte[] bArr, byte[][] bArr2, int i7) {
        int i8;
        boolean z6;
        int i9;
        int i10;
        int length = bArr.length;
        int i11 = 0;
        while (i11 < length) {
            int i12 = (i11 + length) / 2;
            while (i12 > -1 && bArr[i12] != 10) {
                i12--;
            }
            int i13 = i12 + 1;
            int i14 = 1;
            while (true) {
                i8 = i13 + i14;
                if (bArr[i8] == 10) {
                    break;
                }
                i14++;
            }
            int i15 = i8 - i13;
            int i16 = i7;
            boolean z7 = false;
            int i17 = 0;
            int i18 = 0;
            while (true) {
                if (z7) {
                    i9 = 46;
                    z6 = false;
                } else {
                    z6 = z7;
                    i9 = bArr2[i16][i17] & 255;
                }
                i10 = i9 - (bArr[i13 + i18] & 255);
                if (i10 == 0) {
                    i18++;
                    i17++;
                    if (i18 == i15) {
                        break;
                    }
                    if (bArr2[i16].length != i17) {
                        z7 = z6;
                    } else {
                        if (i16 == bArr2.length - 1) {
                            break;
                        }
                        i16++;
                        z7 = true;
                        i17 = -1;
                    }
                } else {
                    break;
                }
            }
            if (i10 >= 0) {
                if (i10 <= 0) {
                    int i19 = i15 - i18;
                    int length2 = bArr2[i16].length - i17;
                    while (true) {
                        i16++;
                        if (i16 >= bArr2.length) {
                            break;
                        }
                        length2 += bArr2[i16].length;
                    }
                    if (length2 >= i19) {
                        if (length2 <= i19) {
                            return new String(bArr, i13, i15, Util.UTF_8);
                        }
                    }
                }
                i11 = i8 + 1;
            }
            length = i13 - 1;
        }
        return null;
    }

    private String[] findMatchingRule(String[] strArr) throws InterruptedException {
        String str;
        String strBinarySearchBytes;
        String strBinarySearchBytes2;
        int i7 = 0;
        if (this.listRead.get() || !this.listRead.compareAndSet(false, true)) {
            try {
                this.readCompleteLatch.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } else {
            readTheListUninterruptibly();
        }
        synchronized (this) {
            if (this.publicSuffixListBytes == null) {
                throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
            }
        }
        int length = strArr.length;
        byte[][] bArr = new byte[length][];
        for (int i8 = 0; i8 < strArr.length; i8++) {
            bArr[i8] = strArr[i8].getBytes(Util.UTF_8);
        }
        int i9 = 0;
        while (true) {
            str = null;
            if (i9 >= length) {
                strBinarySearchBytes = null;
                break;
            }
            strBinarySearchBytes = binarySearchBytes(this.publicSuffixListBytes, bArr, i9);
            if (strBinarySearchBytes != null) {
                break;
            }
            i9++;
        }
        if (length > 1) {
            byte[][] bArr2 = (byte[][]) bArr.clone();
            for (int i10 = 0; i10 < bArr2.length - 1; i10++) {
                bArr2[i10] = WILDCARD_LABEL;
                strBinarySearchBytes2 = binarySearchBytes(this.publicSuffixListBytes, bArr2, i10);
                if (strBinarySearchBytes2 != null) {
                    break;
                }
            }
            strBinarySearchBytes2 = null;
        } else {
            strBinarySearchBytes2 = null;
        }
        if (strBinarySearchBytes2 != null) {
            while (true) {
                if (i7 >= length - 1) {
                    break;
                }
                String strBinarySearchBytes3 = binarySearchBytes(this.publicSuffixExceptionListBytes, bArr, i7);
                if (strBinarySearchBytes3 != null) {
                    str = strBinarySearchBytes3;
                    break;
                }
                i7++;
            }
        }
        if (str != null) {
            return ("!" + str).split("\\.");
        }
        if (strBinarySearchBytes == null && strBinarySearchBytes2 == null) {
            return PREVAILING_RULE;
        }
        String[] strArrSplit = strBinarySearchBytes != null ? strBinarySearchBytes.split("\\.") : EMPTY_RULE;
        String[] strArrSplit2 = strBinarySearchBytes2 != null ? strBinarySearchBytes2.split("\\.") : EMPTY_RULE;
        return strArrSplit.length > strArrSplit2.length ? strArrSplit : strArrSplit2;
    }

    public static PublicSuffixDatabase get() {
        return instance;
    }

    private void readTheList() throws IOException {
        InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream(PUBLIC_SUFFIX_RESOURCE);
        if (resourceAsStream == null) {
            return;
        }
        BufferedSource bufferedSourceBuffer = Okio.buffer(new GzipSource(Okio.source(resourceAsStream)));
        try {
            byte[] bArr = new byte[bufferedSourceBuffer.readInt()];
            bufferedSourceBuffer.readFully(bArr);
            byte[] bArr2 = new byte[bufferedSourceBuffer.readInt()];
            bufferedSourceBuffer.readFully(bArr2);
            synchronized (this) {
                this.publicSuffixListBytes = bArr;
                this.publicSuffixExceptionListBytes = bArr2;
            }
            this.readCompleteLatch.countDown();
        } finally {
            Util.closeQuietly(bufferedSourceBuffer);
        }
    }

    private void readTheListUninterruptibly() {
        boolean z6 = false;
        while (true) {
            try {
                try {
                    readTheList();
                    break;
                } catch (InterruptedIOException unused) {
                    Thread.interrupted();
                    z6 = true;
                } catch (IOException e7) {
                    Platform.get().log(5, "Failed to read public suffix list", e7);
                    if (z6) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                if (z6) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z6) {
            Thread.currentThread().interrupt();
        }
    }

    public String getEffectiveTldPlusOne(String str) throws InterruptedException {
        int length;
        int length2;
        Objects.requireNonNull(str, "domain == null");
        String[] strArrSplit = IDN.toUnicode(str).split("\\.");
        String[] strArrFindMatchingRule = findMatchingRule(strArrSplit);
        if (strArrSplit.length == strArrFindMatchingRule.length && strArrFindMatchingRule[0].charAt(0) != '!') {
            return null;
        }
        if (strArrFindMatchingRule[0].charAt(0) == '!') {
            length = strArrSplit.length;
            length2 = strArrFindMatchingRule.length;
        } else {
            length = strArrSplit.length;
            length2 = strArrFindMatchingRule.length + 1;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArrSplit2 = str.split("\\.");
        for (int i7 = length - length2; i7 < strArrSplit2.length; i7++) {
            sb.append(strArrSplit2[i7]);
            sb.append('.');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void setListBytes(byte[] bArr, byte[] bArr2) {
        this.publicSuffixListBytes = bArr;
        this.publicSuffixExceptionListBytes = bArr2;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }
}
