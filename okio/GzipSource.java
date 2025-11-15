package okio;

import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* loaded from: classes.dex */
public final class GzipSource implements Source {
    private static final byte FCOMMENT = 4;
    private static final byte FEXTRA = 2;
    private static final byte FHCRC = 1;
    private static final byte FNAME = 3;
    private static final byte SECTION_BODY = 1;
    private static final byte SECTION_DONE = 3;
    private static final byte SECTION_HEADER = 0;
    private static final byte SECTION_TRAILER = 2;
    private final Inflater inflater;
    private final InflaterSource inflaterSource;
    private final BufferedSource source;
    private int section = 0;
    private final CRC32 crc = new CRC32();

    public GzipSource(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        Inflater inflater = new Inflater(true);
        this.inflater = inflater;
        BufferedSource bufferedSourceBuffer = Okio.buffer(source);
        this.source = bufferedSourceBuffer;
        this.inflaterSource = new InflaterSource(bufferedSourceBuffer, inflater);
    }

    private void checkEqual(String str, int i7, int i8) throws IOException {
        if (i8 != i7) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i8), Integer.valueOf(i7)));
        }
    }

    private void consumeHeader() throws IOException {
        this.source.require(10L);
        byte b7 = this.source.buffer().getByte(3L);
        boolean z6 = ((b7 >> 1) & 1) == 1;
        if (z6) {
            updateCrc(this.source.buffer(), 0L, 10L);
        }
        checkEqual("ID1ID2", 8075, this.source.readShort());
        this.source.skip(8L);
        if (((b7 >> 2) & 1) == 1) {
            this.source.require(2L);
            if (z6) {
                updateCrc(this.source.buffer(), 0L, 2L);
            }
            long shortLe = this.source.buffer().readShortLe();
            this.source.require(shortLe);
            if (z6) {
                updateCrc(this.source.buffer(), 0L, shortLe);
            }
            this.source.skip(shortLe);
        }
        if (((b7 >> 3) & 1) == 1) {
            long jIndexOf = this.source.indexOf((byte) 0);
            if (jIndexOf == -1) {
                throw new EOFException();
            }
            if (z6) {
                updateCrc(this.source.buffer(), 0L, jIndexOf + 1);
            }
            this.source.skip(jIndexOf + 1);
        }
        if (((b7 >> 4) & 1) == 1) {
            long jIndexOf2 = this.source.indexOf((byte) 0);
            if (jIndexOf2 == -1) {
                throw new EOFException();
            }
            if (z6) {
                updateCrc(this.source.buffer(), 0L, jIndexOf2 + 1);
            }
            this.source.skip(jIndexOf2 + 1);
        }
        if (z6) {
            checkEqual("FHCRC", this.source.readShortLe(), (short) this.crc.getValue());
            this.crc.reset();
        }
    }

    private void consumeTrailer() throws IOException {
        checkEqual("CRC", this.source.readIntLe(), (int) this.crc.getValue());
        checkEqual("ISIZE", this.source.readIntLe(), (int) this.inflater.getBytesWritten());
    }

    private void updateCrc(Buffer buffer, long j7, long j8) {
        Segment segment = buffer.head;
        while (true) {
            int i7 = segment.limit;
            int i8 = segment.pos;
            if (j7 < i7 - i8) {
                break;
            }
            j7 -= i7 - i8;
            segment = segment.next;
        }
        while (j8 > 0) {
            int iMin = (int) Math.min(segment.limit - r7, j8);
            this.crc.update(segment.data, (int) (segment.pos + j7), iMin);
            j8 -= iMin;
            segment = segment.next;
            j7 = 0;
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.inflaterSource.close();
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j7) throws IOException {
        if (j7 < 0) {
            throw new IllegalArgumentException(C0534b.m341a("byteCount < 0: ", j7));
        }
        if (j7 == 0) {
            return 0L;
        }
        if (this.section == 0) {
            consumeHeader();
            this.section = 1;
        }
        if (this.section == 1) {
            long j8 = buffer.size;
            long j9 = this.inflaterSource.read(buffer, j7);
            if (j9 != -1) {
                updateCrc(buffer, j8, j9);
                return j9;
            }
            this.section = 2;
        }
        if (this.section == 2) {
            consumeTrailer();
            this.section = 3;
            if (!this.source.exhausted()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }
}
