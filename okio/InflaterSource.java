package okio;

import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: classes.dex */
public final class InflaterSource implements Source {
    private int bufferBytesHeldByInflater;
    private boolean closed;
    private final Inflater inflater;
    private final BufferedSource source;

    public InflaterSource(Source source, Inflater inflater) {
        this(Okio.buffer(source), inflater);
    }

    private void releaseInflatedBytes() {
        int i7 = this.bufferBytesHeldByInflater;
        if (i7 == 0) {
            return;
        }
        int remaining = i7 - this.inflater.getRemaining();
        this.bufferBytesHeldByInflater -= remaining;
        this.source.skip(remaining);
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.inflater.end();
        this.closed = true;
        this.source.close();
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j7) {
        boolean zRefill;
        if (j7 < 0) {
            throw new IllegalArgumentException(C0534b.m341a("byteCount < 0: ", j7));
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (j7 == 0) {
            return 0L;
        }
        do {
            zRefill = refill();
            try {
                Segment segmentWritableSegment = buffer.writableSegment(1);
                int iInflate = this.inflater.inflate(segmentWritableSegment.data, segmentWritableSegment.limit, (int) Math.min(j7, 8192 - segmentWritableSegment.limit));
                if (iInflate > 0) {
                    segmentWritableSegment.limit += iInflate;
                    long j8 = iInflate;
                    buffer.size += j8;
                    return j8;
                }
                if (!this.inflater.finished() && !this.inflater.needsDictionary()) {
                }
                releaseInflatedBytes();
                if (segmentWritableSegment.pos != segmentWritableSegment.limit) {
                    return -1L;
                }
                buffer.head = segmentWritableSegment.pop();
                SegmentPool.recycle(segmentWritableSegment);
                return -1L;
            } catch (DataFormatException e7) {
                throw new IOException(e7);
            }
        } while (!zRefill);
        throw new EOFException("source exhausted prematurely");
    }

    public final boolean refill() {
        if (!this.inflater.needsInput()) {
            return false;
        }
        releaseInflatedBytes();
        if (this.inflater.getRemaining() != 0) {
            throw new IllegalStateException("?");
        }
        if (this.source.exhausted()) {
            return true;
        }
        Segment segment = this.source.buffer().head;
        int i7 = segment.limit;
        int i8 = segment.pos;
        int i9 = i7 - i8;
        this.bufferBytesHeldByInflater = i9;
        this.inflater.setInput(segment.data, i8, i9);
        return false;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public InflaterSource(BufferedSource bufferedSource, Inflater inflater) {
        if (bufferedSource == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.source = bufferedSource;
        this.inflater = inflater;
    }
}
