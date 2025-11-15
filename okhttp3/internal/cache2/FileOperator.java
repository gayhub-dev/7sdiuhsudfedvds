package okhttp3.internal.cache2;

import java.io.IOException;
import java.nio.channels.FileChannel;
import okio.Buffer;

/* loaded from: classes.dex */
final class FileOperator {
    private final FileChannel fileChannel;

    public FileOperator(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }

    public void read(long j7, Buffer buffer, long j8) throws IOException {
        if (j8 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (j8 > 0) {
            long jTransferTo = this.fileChannel.transferTo(j7, j8, buffer);
            j7 += jTransferTo;
            j8 -= jTransferTo;
        }
    }

    public void write(long j7, Buffer buffer, long j8) throws IOException {
        if (j8 < 0 || j8 > buffer.size()) {
            throw new IndexOutOfBoundsException();
        }
        long j9 = j7;
        long j10 = j8;
        while (j10 > 0) {
            long jTransferFrom = this.fileChannel.transferFrom(buffer, j9, j10);
            j9 += jTransferFrom;
            j10 -= jTransferFrom;
        }
    }
}
