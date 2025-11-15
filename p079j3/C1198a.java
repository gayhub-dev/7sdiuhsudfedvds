package p079j3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;

/* compiled from: FileMediaDataSource.java */
/* renamed from: j3.a */
/* loaded from: classes.dex */
public class C1198a implements IMediaDataSource {

    /* renamed from: a */
    public RandomAccessFile f2617a;

    /* renamed from: b */
    public long f2618b;

    public C1198a(File file) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        this.f2617a = randomAccessFile;
        this.f2618b = randomAccessFile.length();
    }

    @Override // tv.danmaku.ijk.media.player.misc.IMediaDataSource
    public void close() throws IOException {
        this.f2618b = 0L;
        this.f2617a.close();
        this.f2617a = null;
    }

    @Override // tv.danmaku.ijk.media.player.misc.IMediaDataSource
    public long getSize() {
        return this.f2618b;
    }

    @Override // tv.danmaku.ijk.media.player.misc.IMediaDataSource
    public int readAt(long j7, byte[] bArr, int i7, int i8) throws IOException {
        if (this.f2617a.getFilePointer() != j7) {
            this.f2617a.seek(j7);
        }
        if (i8 == 0) {
            return 0;
        }
        return this.f2617a.read(bArr, 0, i8);
    }
}
