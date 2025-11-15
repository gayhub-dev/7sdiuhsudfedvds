package android.support.v4.util;

/* loaded from: classes.dex */
class ContainerHelpers {
    public static final int[] EMPTY_INTS = new int[0];
    public static final long[] EMPTY_LONGS = new long[0];
    public static final Object[] EMPTY_OBJECTS = new Object[0];

    private ContainerHelpers() {
    }

    public static int binarySearch(int[] iArr, int i7, int i8) {
        int i9 = i7 - 1;
        int i10 = 0;
        while (i10 <= i9) {
            int i11 = (i10 + i9) >>> 1;
            int i12 = iArr[i11];
            if (i12 < i8) {
                i10 = i11 + 1;
            } else {
                if (i12 <= i8) {
                    return i11;
                }
                i9 = i11 - 1;
            }
        }
        return ~i10;
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int idealByteArraySize(int i7) {
        for (int i8 = 4; i8 < 32; i8++) {
            int i9 = (1 << i8) - 12;
            if (i7 <= i9) {
                return i9;
            }
        }
        return i7;
    }

    public static int idealIntArraySize(int i7) {
        return idealByteArraySize(i7 * 4) / 4;
    }

    public static int idealLongArraySize(int i7) {
        return idealByteArraySize(i7 * 8) / 8;
    }

    public static int binarySearch(long[] jArr, int i7, long j7) {
        int i8 = i7 - 1;
        int i9 = 0;
        while (i9 <= i8) {
            int i10 = (i9 + i8) >>> 1;
            long j8 = jArr[i10];
            if (j8 < j7) {
                i9 = i10 + 1;
            } else {
                if (j8 <= j7) {
                    return i10;
                }
                i8 = i10 - 1;
            }
        }
        return ~i9;
    }
}
