package tv.danmaku.ijk.media.player.misc;

/* loaded from: classes.dex */
public interface IMediaDataSource {
    void close();

    long getSize();

    int readAt(long j7, byte[] bArr, int i7, int i8);
}
