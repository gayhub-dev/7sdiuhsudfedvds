package okhttp3.internal.p124ws;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import p009b.C0413b;

/* loaded from: classes.dex */
final class WebSocketReader {
    public boolean closed;
    public final FrameCallback frameCallback;
    public long frameLength;
    public final boolean isClient;
    public boolean isControlFrame;
    public boolean isFinalFrame;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    public int opcode;
    public final BufferedSource source;
    private final Buffer controlFrameBuffer = new Buffer();
    private final Buffer messageFrameBuffer = new Buffer();

    public interface FrameCallback {
        void onReadClose(int i7, String str);

        void onReadMessage(String str);

        void onReadMessage(ByteString byteString);

        void onReadPing(ByteString byteString);

        void onReadPong(ByteString byteString);
    }

    public WebSocketReader(boolean z6, BufferedSource bufferedSource, FrameCallback frameCallback) {
        Objects.requireNonNull(bufferedSource, "source == null");
        Objects.requireNonNull(frameCallback, "frameCallback == null");
        this.isClient = z6;
        this.source = bufferedSource;
        this.frameCallback = frameCallback;
        this.maskKey = z6 ? null : new byte[4];
        this.maskCursor = z6 ? null : new Buffer.UnsafeCursor();
    }

    private void readControlFrame() throws ProtocolException {
        String utf8;
        long j7 = this.frameLength;
        if (j7 > 0) {
            this.source.readFully(this.controlFrameBuffer, j7);
            if (!this.isClient) {
                this.controlFrameBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(0L);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        switch (this.opcode) {
            case 8:
                short s6 = 1005;
                long size = this.controlFrameBuffer.size();
                if (size == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (size != 0) {
                    s6 = this.controlFrameBuffer.readShort();
                    utf8 = this.controlFrameBuffer.readUtf8();
                    String strCloseCodeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(s6);
                    if (strCloseCodeExceptionMessage != null) {
                        throw new ProtocolException(strCloseCodeExceptionMessage);
                    }
                } else {
                    utf8 = "";
                }
                this.frameCallback.onReadClose(s6, utf8);
                this.closed = true;
                return;
            case 9:
                this.frameCallback.onReadPing(this.controlFrameBuffer.readByteString());
                return;
            case 10:
                this.frameCallback.onReadPong(this.controlFrameBuffer.readByteString());
                return;
            default:
                StringBuilder sbM112a = C0413b.m112a("Unknown control opcode: ");
                sbM112a.append(Integer.toHexString(this.opcode));
                throw new ProtocolException(sbM112a.toString());
        }
    }

    /* JADX WARN: Finally extract failed */
    private void readHeader() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        long jTimeoutNanos = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        try {
            int i7 = this.source.readByte() & 255;
            this.source.timeout().timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
            this.opcode = i7 & 15;
            boolean z6 = (i7 & 128) != 0;
            this.isFinalFrame = z6;
            boolean z7 = (i7 & 8) != 0;
            this.isControlFrame = z7;
            if (z7 && !z6) {
                throw new ProtocolException("Control frames must be final.");
            }
            boolean z8 = (i7 & 64) != 0;
            boolean z9 = (i7 & 32) != 0;
            boolean z10 = (i7 & 16) != 0;
            if (z8 || z9 || z10) {
                throw new ProtocolException("Reserved flags are unsupported.");
            }
            int i8 = this.source.readByte() & 255;
            boolean z11 = (i8 & 128) != 0;
            if (z11 == this.isClient) {
                throw new ProtocolException(this.isClient ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
            }
            long j7 = i8 & 127;
            this.frameLength = j7;
            if (j7 == 126) {
                this.frameLength = this.source.readShort() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
            } else if (j7 == 127) {
                long j8 = this.source.readLong();
                this.frameLength = j8;
                if (j8 < 0) {
                    StringBuilder sbM112a = C0413b.m112a("Frame length 0x");
                    sbM112a.append(Long.toHexString(this.frameLength));
                    sbM112a.append(" > 0x7FFFFFFFFFFFFFFF");
                    throw new ProtocolException(sbM112a.toString());
                }
            }
            if (this.isControlFrame && this.frameLength > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            }
            if (z11) {
                this.source.readFully(this.maskKey);
            }
        } catch (Throwable th) {
            this.source.timeout().timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
            throw th;
        }
    }

    private void readMessage() throws IOException {
        while (!this.closed) {
            long j7 = this.frameLength;
            if (j7 > 0) {
                this.source.readFully(this.messageFrameBuffer, j7);
                if (!this.isClient) {
                    this.messageFrameBuffer.readAndWriteUnsafe(this.maskCursor);
                    this.maskCursor.seek(this.messageFrameBuffer.size() - this.frameLength);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            }
            if (this.isFinalFrame) {
                return;
            }
            readUntilNonControlFrame();
            if (this.opcode != 0) {
                StringBuilder sbM112a = C0413b.m112a("Expected continuation opcode. Got: ");
                sbM112a.append(Integer.toHexString(this.opcode));
                throw new ProtocolException(sbM112a.toString());
            }
        }
        throw new IOException("closed");
    }

    private void readMessageFrame() throws IOException {
        int i7 = this.opcode;
        if (i7 != 1 && i7 != 2) {
            StringBuilder sbM112a = C0413b.m112a("Unknown opcode: ");
            sbM112a.append(Integer.toHexString(i7));
            throw new ProtocolException(sbM112a.toString());
        }
        readMessage();
        if (i7 == 1) {
            this.frameCallback.onReadMessage(this.messageFrameBuffer.readUtf8());
        } else {
            this.frameCallback.onReadMessage(this.messageFrameBuffer.readByteString());
        }
    }

    private void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            readHeader();
            if (!this.isControlFrame) {
                return;
            } else {
                readControlFrame();
            }
        }
    }

    public void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }
}
