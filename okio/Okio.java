package okio;

import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class Okio {
    public static final Logger logger = Logger.getLogger(Okio.class.getName());

    private Okio() {
    }

    public static Sink appendingSink(File file) {
        if (file != null) {
            return sink(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static Sink blackhole() {
        return new Sink() { // from class: okio.Okio.3
            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() {
            }

            @Override // okio.Sink
            public Timeout timeout() {
                return Timeout.NONE;
            }

            @Override // okio.Sink
            public void write(Buffer buffer, long j7) throws EOFException {
                buffer.skip(j7);
            }
        };
    }

    public static BufferedSource buffer(Source source) {
        return new RealBufferedSource(source);
    }

    public static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static Sink sink(OutputStream outputStream) {
        return sink(outputStream, new Timeout());
    }

    public static Source source(InputStream inputStream) {
        return source(inputStream, new Timeout());
    }

    private static AsyncTimeout timeout(final Socket socket) {
        return new AsyncTimeout() { // from class: okio.Okio.4
            @Override // okio.AsyncTimeout
            public IOException newTimeoutException(@Nullable IOException iOException) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            @Override // okio.AsyncTimeout
            public void timedOut() throws IOException {
                try {
                    socket.close();
                } catch (AssertionError e7) {
                    if (!Okio.isAndroidGetsocknameError(e7)) {
                        throw e7;
                    }
                    Logger logger2 = Okio.logger;
                    Level level = Level.WARNING;
                    StringBuilder sbM112a = C0413b.m112a("Failed to close timed out socket ");
                    sbM112a.append(socket);
                    logger2.log(level, sbM112a.toString(), (Throwable) e7);
                } catch (Exception e8) {
                    Logger logger3 = Okio.logger;
                    Level level2 = Level.WARNING;
                    StringBuilder sbM112a2 = C0413b.m112a("Failed to close timed out socket ");
                    sbM112a2.append(socket);
                    logger3.log(level2, sbM112a2.toString(), (Throwable) e8);
                }
            }
        };
    }

    public static BufferedSink buffer(Sink sink) {
        return new RealBufferedSink(sink);
    }

    private static Sink sink(final OutputStream outputStream, final Timeout timeout) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (timeout != null) {
            return new Sink() { // from class: okio.Okio.1
                @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    outputStream.close();
                }

                @Override // okio.Sink, java.io.Flushable
                public void flush() throws IOException {
                    outputStream.flush();
                }

                @Override // okio.Sink
                public Timeout timeout() {
                    return timeout;
                }

                public String toString() {
                    StringBuilder sbM112a = C0413b.m112a("sink(");
                    sbM112a.append(outputStream);
                    sbM112a.append(")");
                    return sbM112a.toString();
                }

                @Override // okio.Sink
                public void write(Buffer buffer, long j7) throws IOException {
                    Util.checkOffsetAndCount(buffer.size, 0L, j7);
                    while (j7 > 0) {
                        timeout.throwIfReached();
                        Segment segment = buffer.head;
                        int iMin = (int) Math.min(j7, segment.limit - segment.pos);
                        outputStream.write(segment.data, segment.pos, iMin);
                        int i7 = segment.pos + iMin;
                        segment.pos = i7;
                        long j8 = iMin;
                        j7 -= j8;
                        buffer.size -= j8;
                        if (i7 == segment.limit) {
                            buffer.head = segment.pop();
                            SegmentPool.recycle(segment);
                        }
                    }
                }
            };
        }
        throw new IllegalArgumentException("timeout == null");
    }

    private static Source source(final InputStream inputStream, final Timeout timeout) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (timeout != null) {
            return new Source() { // from class: okio.Okio.2
                @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    inputStream.close();
                }

                @Override // okio.Source
                public long read(Buffer buffer, long j7) throws IOException {
                    if (j7 < 0) {
                        throw new IllegalArgumentException(C0534b.m341a("byteCount < 0: ", j7));
                    }
                    if (j7 == 0) {
                        return 0L;
                    }
                    try {
                        timeout.throwIfReached();
                        Segment segmentWritableSegment = buffer.writableSegment(1);
                        int i7 = inputStream.read(segmentWritableSegment.data, segmentWritableSegment.limit, (int) Math.min(j7, 8192 - segmentWritableSegment.limit));
                        if (i7 == -1) {
                            return -1L;
                        }
                        segmentWritableSegment.limit += i7;
                        long j8 = i7;
                        buffer.size += j8;
                        return j8;
                    } catch (AssertionError e7) {
                        if (Okio.isAndroidGetsocknameError(e7)) {
                            throw new IOException(e7);
                        }
                        throw e7;
                    }
                }

                @Override // okio.Source
                public Timeout timeout() {
                    return timeout;
                }

                public String toString() {
                    StringBuilder sbM112a = C0413b.m112a("source(");
                    sbM112a.append(inputStream);
                    sbM112a.append(")");
                    return sbM112a.toString();
                }
            };
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static Sink sink(Socket socket) throws IOException {
        if (socket != null) {
            if (socket.getOutputStream() != null) {
                AsyncTimeout asyncTimeoutTimeout = timeout(socket);
                return asyncTimeoutTimeout.sink(sink(socket.getOutputStream(), asyncTimeoutTimeout));
            }
            throw new IOException("socket's output stream == null");
        }
        throw new IllegalArgumentException("socket == null");
    }

    public static Source source(File file) {
        if (file != null) {
            return source(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    @IgnoreJRERequirement
    public static Source source(Path path, OpenOption... openOptionArr) {
        if (path != null) {
            return source(Files.newInputStream(path, openOptionArr));
        }
        throw new IllegalArgumentException("path == null");
    }

    public static Source source(Socket socket) throws IOException {
        if (socket != null) {
            if (socket.getInputStream() != null) {
                AsyncTimeout asyncTimeoutTimeout = timeout(socket);
                return asyncTimeoutTimeout.source(source(socket.getInputStream(), asyncTimeoutTimeout));
            }
            throw new IOException("socket's input stream == null");
        }
        throw new IllegalArgumentException("socket == null");
    }

    public static Sink sink(File file) {
        if (file != null) {
            return sink(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    @IgnoreJRERequirement
    public static Sink sink(Path path, OpenOption... openOptionArr) {
        if (path != null) {
            return sink(Files.newOutputStream(path, openOptionArr));
        }
        throw new IllegalArgumentException("path == null");
    }
}
