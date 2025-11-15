package okhttp3.internal.http2;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class Settings {
    public static final int COUNT = 10;
    public static final int DEFAULT_INITIAL_WINDOW_SIZE = 65535;
    public static final int ENABLE_PUSH = 2;
    public static final int HEADER_TABLE_SIZE = 1;
    public static final int INITIAL_WINDOW_SIZE = 7;
    public static final int MAX_CONCURRENT_STREAMS = 4;
    public static final int MAX_FRAME_SIZE = 5;
    public static final int MAX_HEADER_LIST_SIZE = 6;
    private int set;
    private final int[] values = new int[10];

    public void clear() {
        this.set = 0;
        Arrays.fill(this.values, 0);
    }

    public int get(int i7) {
        return this.values[i7];
    }

    public boolean getEnablePush(boolean z6) {
        return ((this.set & 4) != 0 ? this.values[2] : z6 ? 1 : 0) == 1;
    }

    public int getHeaderTableSize() {
        if ((this.set & 2) != 0) {
            return this.values[1];
        }
        return -1;
    }

    public int getInitialWindowSize() {
        if ((this.set & 128) != 0) {
            return this.values[7];
        }
        return 65535;
    }

    public int getMaxConcurrentStreams(int i7) {
        return (this.set & 16) != 0 ? this.values[4] : i7;
    }

    public int getMaxFrameSize(int i7) {
        return (this.set & 32) != 0 ? this.values[5] : i7;
    }

    public int getMaxHeaderListSize(int i7) {
        return (this.set & 64) != 0 ? this.values[6] : i7;
    }

    public boolean isSet(int i7) {
        return ((1 << i7) & this.set) != 0;
    }

    public void merge(Settings settings) {
        for (int i7 = 0; i7 < 10; i7++) {
            if (settings.isSet(i7)) {
                set(i7, settings.get(i7));
            }
        }
    }

    public Settings set(int i7, int i8) {
        if (i7 >= 0) {
            int[] iArr = this.values;
            if (i7 < iArr.length) {
                this.set = (1 << i7) | this.set;
                iArr[i7] = i8;
            }
        }
        return this;
    }

    public int size() {
        return Integer.bitCount(this.set);
    }
}
