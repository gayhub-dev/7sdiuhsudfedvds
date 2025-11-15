package okio;

import javax.annotation.Nullable;

/* loaded from: classes.dex */
final class SegmentPool {
    public static final long MAX_SIZE = 65536;
    public static long byteCount;

    @Nullable
    public static Segment next;

    private SegmentPool() {
    }

    public static void recycle(Segment segment) {
        if (segment.next != null || segment.prev != null) {
            throw new IllegalArgumentException();
        }
        if (segment.shared) {
            return;
        }
        synchronized (SegmentPool.class) {
            long j7 = byteCount;
            if (j7 + 8192 > 65536) {
                return;
            }
            byteCount = j7 + 8192;
            segment.next = next;
            segment.limit = 0;
            segment.pos = 0;
            next = segment;
        }
    }

    public static Segment take() {
        synchronized (SegmentPool.class) {
            Segment segment = next;
            if (segment == null) {
                return new Segment();
            }
            next = segment.next;
            segment.next = null;
            byteCount -= 8192;
            return segment;
        }
    }
}
