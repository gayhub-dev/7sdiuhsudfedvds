package android.support.multidex;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;
import p009b.C0413b;

/* loaded from: classes.dex */
final class ZipUtil {
    private static final int BUFFER_SIZE = 16384;
    private static final int ENDHDR = 22;
    private static final int ENDSIG = 101010256;

    public static class CentralDirectory {
        public long offset;
        public long size;
    }

    public static long computeCrcOfCentralDir(RandomAccessFile randomAccessFile, CentralDirectory centralDirectory) throws IOException {
        CRC32 crc32 = new CRC32();
        long j7 = centralDirectory.size;
        randomAccessFile.seek(centralDirectory.offset);
        byte[] bArr = new byte[16384];
        int i7 = randomAccessFile.read(bArr, 0, (int) Math.min(16384L, j7));
        while (i7 != -1) {
            crc32.update(bArr, 0, i7);
            j7 -= i7;
            if (j7 == 0) {
                break;
            }
            i7 = randomAccessFile.read(bArr, 0, (int) Math.min(16384L, j7));
        }
        return crc32.getValue();
    }

    public static CentralDirectory findCentralDirectory(RandomAccessFile randomAccessFile) throws IOException {
        long length = randomAccessFile.length() - 22;
        if (length < 0) {
            StringBuilder sbM112a = C0413b.m112a("File too short to be a zip file: ");
            sbM112a.append(randomAccessFile.length());
            throw new ZipException(sbM112a.toString());
        }
        long j7 = length - 65536;
        long j8 = j7 >= 0 ? j7 : 0L;
        int iReverseBytes = Integer.reverseBytes(ENDSIG);
        do {
            randomAccessFile.seek(length);
            if (randomAccessFile.readInt() == iReverseBytes) {
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                CentralDirectory centralDirectory = new CentralDirectory();
                centralDirectory.size = Integer.reverseBytes(randomAccessFile.readInt()) & 4294967295L;
                centralDirectory.offset = Integer.reverseBytes(randomAccessFile.readInt()) & 4294967295L;
                return centralDirectory;
            }
            length--;
        } while (length >= j8);
        throw new ZipException("End Of Central Directory signature not found");
    }

    public static long getZipCrc(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            return computeCrcOfCentralDir(randomAccessFile, findCentralDirectory(randomAccessFile));
        } finally {
            randomAccessFile.close();
        }
    }
}
