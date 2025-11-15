package okhttp3.internal.cache;

import java.io.EOFException;
import java.io.IOException;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;

/* loaded from: classes.dex */
class FaultHidingSink extends ForwardingSink {
    private boolean hasErrors;

    public FaultHidingSink(Sink sink) {
        super(sink);
    }

    @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.hasErrors) {
            return;
        }
        try {
            super.close();
        } catch (IOException e7) {
            this.hasErrors = true;
            onException(e7);
        }
    }

    @Override // okio.ForwardingSink, okio.Sink, java.io.Flushable
    public void flush() {
        if (this.hasErrors) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e7) {
            this.hasErrors = true;
            onException(e7);
        }
    }

    public void onException(IOException iOException) {
    }

    @Override // okio.ForwardingSink, okio.Sink
    public void write(Buffer buffer, long j7) throws EOFException {
        if (this.hasErrors) {
            buffer.skip(j7);
            return;
        }
        try {
            super.write(buffer, j7);
        } catch (IOException e7) {
            this.hasErrors = true;
            onException(e7);
        }
    }
}
