package tv.danmaku.ijk.media.player.format.mpegts;

import p009b.C0413b;

/* loaded from: classes.dex */
public class PesPrivateDataTimeStamp {
    public final byte[] rawData;
    public final long utcTime;
    public final int utcTimeValid;
    public final int version;

    private PesPrivateDataTimeStamp(byte[] bArr, int i7, int i8, long j7) {
        this.rawData = bArr;
        this.version = i7;
        this.utcTimeValid = i8;
        this.utcTime = j7;
    }

    private static boolean checkSyncWord(byte[] bArr) {
        return (bArr[0] & 255) == 254 && (bArr[1] & 240) == 224;
    }

    public static PesPrivateDataTimeStamp parse(byte[] bArr) {
        if (checkSyncWord(bArr)) {
            return new PesPrivateDataTimeStamp(bArr, (bArr[1] & 12) >> 2, (bArr[1] & 2) >> 1, ((bArr[10] & 255) << 40) | ((bArr[11] & 255) << 32) | ((bArr[12] & 255) << 24) | ((bArr[13] & 255) << 16) | ((bArr[14] & 255) << 8) | (bArr[15] & 255));
        }
        return null;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("PesPrivateDataTimeStamp{version=");
        sbM112a.append(this.version);
        sbM112a.append(", utcTimeValid=");
        sbM112a.append(this.utcTimeValid);
        sbM112a.append(", utcTime=");
        sbM112a.append(this.utcTime);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
