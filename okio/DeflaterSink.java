package okio;

import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class DeflaterSink implements Sink {
    private boolean closed;
    private final Deflater deflater;
    private final BufferedSink sink;

    public DeflaterSink(Sink sink, Deflater deflater) {
        this(Okio.buffer(sink), deflater);
    }

    @IgnoreJRERequirement
    private void deflate(boolean z6) {
        Segment segmentWritableSegment;
        int iDeflate;
        Buffer buffer = this.sink.buffer();
        while (true) {
            segmentWritableSegment = buffer.writableSegment(1);
            if (z6) {
                Deflater deflater = this.deflater;
                byte[] bArr = segmentWritableSegment.data;
                int i7 = segmentWritableSegment.limit;
                iDeflate = deflater.deflate(bArr, i7, 8192 - i7, 2);
            } else {
                Deflater deflater2 = this.deflater;
                byte[] bArr2 = segmentWritableSegment.data;
                int i8 = segmentWritableSegment.limit;
                iDeflate = deflater2.deflate(bArr2, i8, 8192 - i8);
            }
            if (iDeflate > 0) {
                segmentWritableSegment.limit += iDeflate;
                buffer.size += iDeflate;
                this.sink.emitCompleteSegments();
            } else if (this.deflater.needsInput()) {
                break;
            }
        }
        if (segmentWritableSegment.pos == segmentWritableSegment.limit) {
            buffer.head = segmentWritableSegment.pop();
            SegmentPool.recycle(segmentWritableSegment);
        }
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.closed) {
            return;
        }
        Throwable th = null;
        try {
            finishDeflate();
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.deflater.end();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        try {
            this.sink.close();
        } catch (Throwable th4) {
            if (th == null) {
                th = th4;
            }
        }
        this.closed = true;
        if (th != null) {
            Util.sneakyRethrow(th);
        }
    }

    public void finishDeflate() {
        this.deflater.finish();
        deflate(false);
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        deflate(true);
        this.sink.flush();
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("DeflaterSink(");
        sbM112a.append(this.sink);
        sbM112a.append(")");
        return sbM112a.toString();
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j7) {
        Util.checkOffsetAndCount(buffer.size, 0L, j7);
        while (j7 > 0) {
            Segment segment = buffer.head;
            int iMin = (int) Math.min(j7, segment.limit - segment.pos);
            this.deflater.setInput(segment.data, segment.pos, iMin);
            deflate(false);
            long j8 = iMin;
            buffer.size -= j8;
            int i7 = segment.pos + iMin;
            segment.pos = i7;
            if (i7 == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            j7 -= j8;
        }
    }

    public DeflaterSink(BufferedSink bufferedSink, Deflater deflater) {
        if (bufferedSink == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (deflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.sink = bufferedSink;
        this.deflater = deflater;
    }
}
