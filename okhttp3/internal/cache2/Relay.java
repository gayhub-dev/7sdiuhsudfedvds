package okhttp3.internal.cache2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

/* loaded from: classes.dex */
final class Relay {
    private static final long FILE_HEADER_SIZE = 32;
    public static final ByteString PREFIX_CLEAN = ByteString.encodeUtf8("OkHttp cache v1\n");
    public static final ByteString PREFIX_DIRTY = ByteString.encodeUtf8("OkHttp DIRTY :(\n");
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    public final long bufferMaxSize;
    public boolean complete;
    public RandomAccessFile file;
    private final ByteString metadata;
    public int sourceCount;
    public Source upstream;
    public long upstreamPos;
    public Thread upstreamReader;
    public final Buffer upstreamBuffer = new Buffer();
    public final Buffer buffer = new Buffer();

    public class RelaySource implements Source {
        private FileOperator fileOperator;
        private long sourcePos;
        private final Timeout timeout = new Timeout();

        public RelaySource() {
            this.fileOperator = new FileOperator(Relay.this.file.getChannel());
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.fileOperator == null) {
                return;
            }
            RandomAccessFile randomAccessFile = null;
            this.fileOperator = null;
            synchronized (Relay.this) {
                Relay relay = Relay.this;
                int i7 = relay.sourceCount - 1;
                relay.sourceCount = i7;
                if (i7 == 0) {
                    RandomAccessFile randomAccessFile2 = relay.file;
                    relay.file = null;
                    randomAccessFile = randomAccessFile2;
                }
            }
            if (randomAccessFile != null) {
                Util.closeQuietly(randomAccessFile);
            }
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j7) throws IOException {
            long j8;
            char c7;
            Relay relay;
            if (this.fileOperator == null) {
                throw new IllegalStateException("closed");
            }
            synchronized (Relay.this) {
                while (true) {
                    long j9 = this.sourcePos;
                    Relay relay2 = Relay.this;
                    j8 = relay2.upstreamPos;
                    if (j9 != j8) {
                        long size = j8 - relay2.buffer.size();
                        long j10 = this.sourcePos;
                        if (j10 >= size) {
                            long jMin = Math.min(j7, j8 - j10);
                            Relay.this.buffer.copyTo(buffer, this.sourcePos - size, jMin);
                            this.sourcePos += jMin;
                            return jMin;
                        }
                        c7 = 2;
                    } else if (!relay2.complete) {
                        if (relay2.upstreamReader == null) {
                            relay2.upstreamReader = Thread.currentThread();
                            c7 = 1;
                            break;
                        }
                        this.timeout.waitUntilNotified(relay2);
                    } else {
                        return -1L;
                    }
                }
                if (c7 == 2) {
                    long jMin2 = Math.min(j7, j8 - this.sourcePos);
                    this.fileOperator.read(this.sourcePos + 32, buffer, jMin2);
                    this.sourcePos += jMin2;
                    return jMin2;
                }
                try {
                    Relay relay3 = Relay.this;
                    long j11 = relay3.upstream.read(relay3.upstreamBuffer, relay3.bufferMaxSize);
                    if (j11 == -1) {
                        Relay.this.commit(j8);
                        synchronized (Relay.this) {
                            Relay relay4 = Relay.this;
                            relay4.upstreamReader = null;
                            relay4.notifyAll();
                        }
                        return -1L;
                    }
                    long jMin3 = Math.min(j11, j7);
                    Relay.this.upstreamBuffer.copyTo(buffer, 0L, jMin3);
                    this.sourcePos += jMin3;
                    this.fileOperator.write(j8 + 32, Relay.this.upstreamBuffer.clone(), j11);
                    synchronized (Relay.this) {
                        Relay relay5 = Relay.this;
                        relay5.buffer.write(relay5.upstreamBuffer, j11);
                        long size2 = Relay.this.buffer.size();
                        Relay relay6 = Relay.this;
                        if (size2 > relay6.bufferMaxSize) {
                            Buffer buffer2 = relay6.buffer;
                            buffer2.skip(buffer2.size() - Relay.this.bufferMaxSize);
                        }
                        relay = Relay.this;
                        relay.upstreamPos += j11;
                    }
                    synchronized (relay) {
                        Relay relay7 = Relay.this;
                        relay7.upstreamReader = null;
                        relay7.notifyAll();
                    }
                    return jMin3;
                } catch (Throwable th) {
                    synchronized (Relay.this) {
                        Relay relay8 = Relay.this;
                        relay8.upstreamReader = null;
                        relay8.notifyAll();
                        throw th;
                    }
                }
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }
    }

    private Relay(RandomAccessFile randomAccessFile, Source source, long j7, ByteString byteString, long j8) {
        this.file = randomAccessFile;
        this.upstream = source;
        this.complete = source == null;
        this.upstreamPos = j7;
        this.metadata = byteString;
        this.bufferMaxSize = j8;
    }

    public static Relay edit(File file, Source source, ByteString byteString, long j7) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        Relay relay = new Relay(randomAccessFile, source, 0L, byteString, j7);
        randomAccessFile.setLength(0L);
        relay.writeHeader(PREFIX_DIRTY, -1L, -1L);
        return relay;
    }

    public static Relay read(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileOperator fileOperator = new FileOperator(randomAccessFile.getChannel());
        Buffer buffer = new Buffer();
        fileOperator.read(0L, buffer, 32L);
        if (!buffer.readByteString(r2.size()).equals(PREFIX_CLEAN)) {
            throw new IOException("unreadable cache file");
        }
        long j7 = buffer.readLong();
        long j8 = buffer.readLong();
        Buffer buffer2 = new Buffer();
        fileOperator.read(j7 + 32, buffer2, j8);
        return new Relay(randomAccessFile, null, j7, buffer2.readByteString(), 0L);
    }

    private void writeHeader(ByteString byteString, long j7, long j8) throws IOException {
        Buffer buffer = new Buffer();
        buffer.write(byteString);
        buffer.writeLong(j7);
        buffer.writeLong(j8);
        if (buffer.size() != 32) {
            throw new IllegalArgumentException();
        }
        new FileOperator(this.file.getChannel()).write(0L, buffer, 32L);
    }

    private void writeMetadata(long j7) throws IOException {
        Buffer buffer = new Buffer();
        buffer.write(this.metadata);
        new FileOperator(this.file.getChannel()).write(32 + j7, buffer, this.metadata.size());
    }

    public void commit(long j7) throws IOException {
        writeMetadata(j7);
        this.file.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, j7, this.metadata.size());
        this.file.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
        }
        Util.closeQuietly(this.upstream);
        this.upstream = null;
    }

    public boolean isClosed() {
        return this.file == null;
    }

    public ByteString metadata() {
        return this.metadata;
    }

    public Source newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }
}
