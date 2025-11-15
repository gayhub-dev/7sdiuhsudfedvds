package okio;

/* loaded from: classes.dex */
final class PeekSource implements Source {
    private final Buffer buffer;
    private boolean closed;
    private int expectedPos;
    private Segment expectedSegment;
    private long pos;
    private final BufferedSource upstream;

    public PeekSource(BufferedSource bufferedSource) {
        this.upstream = bufferedSource;
        Buffer buffer = bufferedSource.buffer();
        this.buffer = buffer;
        Segment segment = buffer.head;
        this.expectedSegment = segment;
        this.expectedPos = segment != null ? segment.pos : -1;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed = true;
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j7) {
        Segment segment;
        Segment segment2;
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        Segment segment3 = this.expectedSegment;
        if (segment3 != null && (segment3 != (segment2 = this.buffer.head) || this.expectedPos != segment2.pos)) {
            throw new IllegalStateException("Peek source is invalid because upstream source was used");
        }
        this.upstream.request(this.pos + j7);
        if (this.expectedSegment == null && (segment = this.buffer.head) != null) {
            this.expectedSegment = segment;
            this.expectedPos = segment.pos;
        }
        long jMin = Math.min(j7, this.buffer.size - this.pos);
        if (jMin <= 0) {
            return -1L;
        }
        this.buffer.copyTo(buffer, this.pos, jMin);
        this.pos += jMin;
        return jMin;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.upstream.timeout();
    }
}
