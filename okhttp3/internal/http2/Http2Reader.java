package okhttp3.internal.http2;

import android.support.v7.widget.ActivityChooserView;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Hpack;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

/* loaded from: classes.dex */
final class Http2Reader implements Closeable {
    public static final Logger logger = Logger.getLogger(Http2.class.getName());
    private final boolean client;
    private final ContinuationSource continuation;
    public final Hpack.Reader hpackReader;
    private final BufferedSource source;

    public static final class ContinuationSource implements Source {
        public byte flags;
        public int left;
        public int length;
        public short padding;
        private final BufferedSource source;
        public int streamId;

        public ContinuationSource(BufferedSource bufferedSource) {
            this.source = bufferedSource;
        }

        private void readContinuationHeader() throws IOException {
            int i7 = this.streamId;
            int medium = Http2Reader.readMedium(this.source);
            this.left = medium;
            this.length = medium;
            byte b7 = (byte) (this.source.readByte() & 255);
            this.flags = (byte) (this.source.readByte() & 255);
            Logger logger = Http2Reader.logger;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Http2.frameLog(true, this.streamId, this.length, b7, this.flags));
            }
            int i8 = this.source.readInt() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.streamId = i8;
            if (b7 != 9) {
                throw Http2.ioException("%s != TYPE_CONTINUATION", Byte.valueOf(b7));
            }
            if (i8 != i7) {
                throw Http2.ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j7) throws IOException {
            while (true) {
                int i7 = this.left;
                if (i7 != 0) {
                    long j8 = this.source.read(buffer, Math.min(j7, i7));
                    if (j8 == -1) {
                        return -1L;
                    }
                    this.left = (int) (this.left - j8);
                    return j8;
                }
                this.source.skip(this.padding);
                this.padding = (short) 0;
                if ((this.flags & 4) != 0) {
                    return -1L;
                }
                readContinuationHeader();
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.source.timeout();
        }
    }

    public interface Handler {
        void ackSettings();

        void alternateService(int i7, String str, ByteString byteString, String str2, int i8, long j7);

        void data(boolean z6, int i7, BufferedSource bufferedSource, int i8);

        void goAway(int i7, ErrorCode errorCode, ByteString byteString);

        void headers(boolean z6, int i7, int i8, List<Header> list);

        void ping(boolean z6, int i7, int i8);

        void priority(int i7, int i8, int i9, boolean z6);

        void pushPromise(int i7, int i8, List<Header> list);

        void rstStream(int i7, ErrorCode errorCode);

        void settings(boolean z6, Settings settings);

        void windowUpdate(int i7, long j7);
    }

    public Http2Reader(BufferedSource bufferedSource, boolean z6) {
        this.source = bufferedSource;
        this.client = z6;
        ContinuationSource continuationSource = new ContinuationSource(bufferedSource);
        this.continuation = continuationSource;
        this.hpackReader = new Hpack.Reader(4096, continuationSource);
    }

    public static int lengthWithoutPadding(int i7, byte b7, short s6) throws IOException {
        if ((b7 & 8) != 0) {
            i7--;
        }
        if (s6 <= i7) {
            return (short) (i7 - s6);
        }
        throw Http2.ioException("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s6), Integer.valueOf(i7));
    }

    private void readData(Handler handler, int i7, byte b7, int i8) throws IOException {
        if (i8 == 0) {
            throw Http2.ioException("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
        }
        boolean z6 = (b7 & 1) != 0;
        if ((b7 & 32) != 0) {
            throw Http2.ioException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        short s6 = (b7 & 8) != 0 ? (short) (this.source.readByte() & 255) : (short) 0;
        handler.data(z6, i8, this.source, lengthWithoutPadding(i7, b7, s6));
        this.source.skip(s6);
    }

    private void readGoAway(Handler handler, int i7, byte b7, int i8) throws IOException {
        if (i7 < 8) {
            throw Http2.ioException("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i7));
        }
        if (i8 != 0) {
            throw Http2.ioException("TYPE_GOAWAY streamId != 0", new Object[0]);
        }
        int i9 = this.source.readInt();
        int i10 = this.source.readInt();
        int i11 = i7 - 8;
        ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(i10);
        if (errorCodeFromHttp2 == null) {
            throw Http2.ioException("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(i10));
        }
        ByteString byteString = ByteString.EMPTY;
        if (i11 > 0) {
            byteString = this.source.readByteString(i11);
        }
        handler.goAway(i9, errorCodeFromHttp2, byteString);
    }

    private List<Header> readHeaderBlock(int i7, short s6, byte b7, int i8) throws IOException {
        ContinuationSource continuationSource = this.continuation;
        continuationSource.left = i7;
        continuationSource.length = i7;
        continuationSource.padding = s6;
        continuationSource.flags = b7;
        continuationSource.streamId = i8;
        this.hpackReader.readHeaders();
        return this.hpackReader.getAndResetHeaderList();
    }

    private void readHeaders(Handler handler, int i7, byte b7, int i8) throws IOException {
        if (i8 == 0) {
            throw Http2.ioException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
        }
        boolean z6 = (b7 & 1) != 0;
        short s6 = (b7 & 8) != 0 ? (short) (this.source.readByte() & 255) : (short) 0;
        if ((b7 & 32) != 0) {
            readPriority(handler, i8);
            i7 -= 5;
        }
        handler.headers(z6, i8, -1, readHeaderBlock(lengthWithoutPadding(i7, b7, s6), s6, b7, i8));
    }

    public static int readMedium(BufferedSource bufferedSource) {
        return (bufferedSource.readByte() & 255) | ((bufferedSource.readByte() & 255) << 16) | ((bufferedSource.readByte() & 255) << 8);
    }

    private void readPing(Handler handler, int i7, byte b7, int i8) throws IOException {
        if (i7 != 8) {
            throw Http2.ioException("TYPE_PING length != 8: %s", Integer.valueOf(i7));
        }
        if (i8 != 0) {
            throw Http2.ioException("TYPE_PING streamId != 0", new Object[0]);
        }
        handler.ping((b7 & 1) != 0, this.source.readInt(), this.source.readInt());
    }

    private void readPriority(Handler handler, int i7, byte b7, int i8) throws IOException {
        if (i7 != 5) {
            throw Http2.ioException("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i7));
        }
        if (i8 == 0) {
            throw Http2.ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
        }
        readPriority(handler, i8);
    }

    private void readPushPromise(Handler handler, int i7, byte b7, int i8) throws IOException {
        if (i8 == 0) {
            throw Http2.ioException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }
        short s6 = (b7 & 8) != 0 ? (short) (this.source.readByte() & 255) : (short) 0;
        handler.pushPromise(i8, this.source.readInt() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, readHeaderBlock(lengthWithoutPadding(i7 - 4, b7, s6), s6, b7, i8));
    }

    private void readRstStream(Handler handler, int i7, byte b7, int i8) throws IOException {
        if (i7 != 4) {
            throw Http2.ioException("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i7));
        }
        if (i8 == 0) {
            throw Http2.ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
        }
        int i9 = this.source.readInt();
        ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(i9);
        if (errorCodeFromHttp2 == null) {
            throw Http2.ioException("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(i9));
        }
        handler.rstStream(i8, errorCodeFromHttp2);
    }

    private void readSettings(Handler handler, int i7, byte b7, int i8) throws IOException {
        if (i8 != 0) {
            throw Http2.ioException("TYPE_SETTINGS streamId != 0", new Object[0]);
        }
        if ((b7 & 1) != 0) {
            if (i7 != 0) {
                throw Http2.ioException("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
            }
            handler.ackSettings();
            return;
        }
        if (i7 % 6 != 0) {
            throw Http2.ioException("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i7));
        }
        Settings settings = new Settings();
        for (int i9 = 0; i9 < i7; i9 += 6) {
            int i10 = this.source.readShort() & 65535;
            int i11 = this.source.readInt();
            if (i10 == 2) {
                if (i11 != 0 && i11 != 1) {
                    throw Http2.ioException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                }
            } else if (i10 == 3) {
                i10 = 4;
            } else if (i10 == 4) {
                i10 = 7;
                if (i11 < 0) {
                    throw Http2.ioException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                }
            } else if (i10 == 5 && (i11 < 16384 || i11 > 16777215)) {
                throw Http2.ioException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(i11));
            }
            settings.set(i10, i11);
        }
        handler.settings(false, settings);
    }

    private void readWindowUpdate(Handler handler, int i7, byte b7, int i8) throws IOException {
        if (i7 != 4) {
            throw Http2.ioException("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i7));
        }
        long j7 = this.source.readInt() & 2147483647L;
        if (j7 == 0) {
            throw Http2.ioException("windowSizeIncrement was 0", Long.valueOf(j7));
        }
        handler.windowUpdate(i8, j7);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.source.close();
    }

    public boolean nextFrame(boolean z6, Handler handler) throws IOException {
        try {
            this.source.require(9L);
            int medium = readMedium(this.source);
            if (medium < 0 || medium > 16384) {
                throw Http2.ioException("FRAME_SIZE_ERROR: %s", Integer.valueOf(medium));
            }
            byte b7 = (byte) (this.source.readByte() & 255);
            if (z6 && b7 != 4) {
                throw Http2.ioException("Expected a SETTINGS frame but was %s", Byte.valueOf(b7));
            }
            byte b8 = (byte) (this.source.readByte() & 255);
            int i7 = this.source.readInt() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.fine(Http2.frameLog(true, i7, medium, b7, b8));
            }
            switch (b7) {
                case 0:
                    readData(handler, medium, b8, i7);
                    return true;
                case 1:
                    readHeaders(handler, medium, b8, i7);
                    return true;
                case 2:
                    readPriority(handler, medium, b8, i7);
                    return true;
                case 3:
                    readRstStream(handler, medium, b8, i7);
                    return true;
                case 4:
                    readSettings(handler, medium, b8, i7);
                    return true;
                case 5:
                    readPushPromise(handler, medium, b8, i7);
                    return true;
                case 6:
                    readPing(handler, medium, b8, i7);
                    return true;
                case 7:
                    readGoAway(handler, medium, b8, i7);
                    return true;
                case 8:
                    readWindowUpdate(handler, medium, b8, i7);
                    return true;
                default:
                    this.source.skip(medium);
                    return true;
            }
        } catch (IOException unused) {
            return false;
        }
    }

    public void readConnectionPreface(Handler handler) throws IOException {
        if (this.client) {
            if (!nextFrame(true, handler)) {
                throw Http2.ioException("Required SETTINGS preface not received", new Object[0]);
            }
            return;
        }
        BufferedSource bufferedSource = this.source;
        ByteString byteString = Http2.CONNECTION_PREFACE;
        ByteString byteString2 = bufferedSource.readByteString(byteString.size());
        Logger logger2 = logger;
        if (logger2.isLoggable(Level.FINE)) {
            logger2.fine(Util.format("<< CONNECTION %s", byteString2.hex()));
        }
        if (!byteString.equals(byteString2)) {
            throw Http2.ioException("Expected a connection header but was %s", byteString2.utf8());
        }
    }

    private void readPriority(Handler handler, int i7) {
        int i8 = this.source.readInt();
        handler.priority(i7, i8 & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, (this.source.readByte() & 255) + 1, (Integer.MIN_VALUE & i8) != 0);
    }
}
