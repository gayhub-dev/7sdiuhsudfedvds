package okio;

import javax.annotation.Nullable;

/* loaded from: classes.dex */
final class Segment {
    public static final int SHARE_MINIMUM = 1024;
    public static final int SIZE = 8192;
    public final byte[] data;
    public int limit;
    public Segment next;
    public boolean owner;
    public int pos;
    public Segment prev;
    public boolean shared;

    public Segment() {
        this.data = new byte[8192];
        this.owner = true;
        this.shared = false;
    }

    public final void compact() {
        Segment segment = this.prev;
        if (segment == this) {
            throw new IllegalStateException();
        }
        if (segment.owner) {
            int i7 = this.limit - this.pos;
            if (i7 > (8192 - segment.limit) + (segment.shared ? 0 : segment.pos)) {
                return;
            }
            writeTo(segment, i7);
            pop();
            SegmentPool.recycle(this);
        }
    }

    @Nullable
    public final Segment pop() {
        Segment segment = this.next;
        Segment segment2 = segment != this ? segment : null;
        Segment segment3 = this.prev;
        segment3.next = segment;
        this.next.prev = segment3;
        this.next = null;
        this.prev = null;
        return segment2;
    }

    public final Segment push(Segment segment) {
        segment.prev = this;
        segment.next = this.next;
        this.next.prev = segment;
        this.next = segment;
        return segment;
    }

    public final Segment sharedCopy() {
        this.shared = true;
        return new Segment(this.data, this.pos, this.limit, true, false);
    }

    public final Segment split(int i7) {
        Segment segmentTake;
        if (i7 <= 0 || i7 > this.limit - this.pos) {
            throw new IllegalArgumentException();
        }
        if (i7 >= 1024) {
            segmentTake = sharedCopy();
        } else {
            segmentTake = SegmentPool.take();
            System.arraycopy(this.data, this.pos, segmentTake.data, 0, i7);
        }
        segmentTake.limit = segmentTake.pos + i7;
        this.pos += i7;
        this.prev.push(segmentTake);
        return segmentTake;
    }

    public final Segment unsharedCopy() {
        return new Segment((byte[]) this.data.clone(), this.pos, this.limit, false, true);
    }

    public final void writeTo(Segment segment, int i7) {
        if (!segment.owner) {
            throw new IllegalArgumentException();
        }
        int i8 = segment.limit;
        if (i8 + i7 > 8192) {
            if (segment.shared) {
                throw new IllegalArgumentException();
            }
            int i9 = segment.pos;
            if ((i8 + i7) - i9 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = segment.data;
            System.arraycopy(bArr, i9, bArr, 0, i8 - i9);
            segment.limit -= segment.pos;
            segment.pos = 0;
        }
        System.arraycopy(this.data, this.pos, segment.data, segment.limit, i7);
        segment.limit += i7;
        this.pos += i7;
    }

    public Segment(byte[] bArr, int i7, int i8, boolean z6, boolean z7) {
        this.data = bArr;
        this.pos = i7;
        this.limit = i8;
        this.shared = z6;
        this.owner = z7;
    }
}
