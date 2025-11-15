package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import p009b.C0413b;

/* loaded from: classes.dex */
public class AsyncTimeout extends Timeout {
    private static final long IDLE_TIMEOUT_MILLIS;
    private static final long IDLE_TIMEOUT_NANOS;
    private static final int TIMEOUT_WRITE_SIZE = 65536;

    @Nullable
    public static AsyncTimeout head;
    private boolean inQueue;

    @Nullable
    private AsyncTimeout next;
    private long timeoutAt;

    public static final class Watchdog extends Thread {
        public Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0015, code lost:
        
            r1.timedOut();
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r3 = this;
            L0:
                java.lang.Class<okio.AsyncTimeout> r0 = okio.AsyncTimeout.class
                monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L0
                okio.AsyncTimeout r1 = okio.AsyncTimeout.awaitTimeout()     // Catch: java.lang.Throwable -> L19
                if (r1 != 0) goto Lb
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                goto L0
            Lb:
                okio.AsyncTimeout r2 = okio.AsyncTimeout.head     // Catch: java.lang.Throwable -> L19
                if (r1 != r2) goto L14
                r1 = 0
                okio.AsyncTimeout.head = r1     // Catch: java.lang.Throwable -> L19
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                return
            L14:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                r1.timedOut()     // Catch: java.lang.InterruptedException -> L0
                goto L0
            L19:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                throw r1     // Catch: java.lang.InterruptedException -> L0
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.Watchdog.run():void");
        }
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        IDLE_TIMEOUT_MILLIS = millis;
        IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    @Nullable
    public static AsyncTimeout awaitTimeout() throws InterruptedException {
        AsyncTimeout asyncTimeout = head.next;
        if (asyncTimeout == null) {
            long jNanoTime = System.nanoTime();
            AsyncTimeout.class.wait(IDLE_TIMEOUT_MILLIS);
            if (head.next != null || System.nanoTime() - jNanoTime < IDLE_TIMEOUT_NANOS) {
                return null;
            }
            return head;
        }
        long jRemainingNanos = asyncTimeout.remainingNanos(System.nanoTime());
        if (jRemainingNanos > 0) {
            long j7 = jRemainingNanos / 1000000;
            AsyncTimeout.class.wait(j7, (int) (jRemainingNanos - (1000000 * j7)));
            return null;
        }
        head.next = asyncTimeout.next;
        asyncTimeout.next = null;
        return asyncTimeout;
    }

    private static synchronized boolean cancelScheduledTimeout(AsyncTimeout asyncTimeout) {
        AsyncTimeout asyncTimeout2 = head;
        while (asyncTimeout2 != null) {
            AsyncTimeout asyncTimeout3 = asyncTimeout2.next;
            if (asyncTimeout3 == asyncTimeout) {
                asyncTimeout2.next = asyncTimeout.next;
                asyncTimeout.next = null;
                return false;
            }
            asyncTimeout2 = asyncTimeout3;
        }
        return true;
    }

    private long remainingNanos(long j7) {
        return this.timeoutAt - j7;
    }

    private static synchronized void scheduleTimeout(AsyncTimeout asyncTimeout, long j7, boolean z6) {
        if (head == null) {
            head = new AsyncTimeout();
            new Watchdog().start();
        }
        long jNanoTime = System.nanoTime();
        if (j7 != 0 && z6) {
            asyncTimeout.timeoutAt = Math.min(j7, asyncTimeout.deadlineNanoTime() - jNanoTime) + jNanoTime;
        } else if (j7 != 0) {
            asyncTimeout.timeoutAt = j7 + jNanoTime;
        } else {
            if (!z6) {
                throw new AssertionError();
            }
            asyncTimeout.timeoutAt = asyncTimeout.deadlineNanoTime();
        }
        long jRemainingNanos = asyncTimeout.remainingNanos(jNanoTime);
        AsyncTimeout asyncTimeout2 = head;
        while (true) {
            AsyncTimeout asyncTimeout3 = asyncTimeout2.next;
            if (asyncTimeout3 == null || jRemainingNanos < asyncTimeout3.remainingNanos(jNanoTime)) {
                break;
            } else {
                asyncTimeout2 = asyncTimeout2.next;
            }
        }
        asyncTimeout.next = asyncTimeout2.next;
        asyncTimeout2.next = asyncTimeout;
        if (asyncTimeout2 == head) {
            AsyncTimeout.class.notify();
        }
    }

    public final void enter() {
        if (this.inQueue) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long jTimeoutNanos = timeoutNanos();
        boolean zHasDeadline = hasDeadline();
        if (jTimeoutNanos != 0 || zHasDeadline) {
            this.inQueue = true;
            scheduleTimeout(this, jTimeoutNanos, zHasDeadline);
        }
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return cancelScheduledTimeout(this);
    }

    public IOException newTimeoutException(@Nullable IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public final Sink sink(final Sink sink) {
        return new Sink() { // from class: okio.AsyncTimeout.1
            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                AsyncTimeout.this.enter();
                try {
                    try {
                        sink.close();
                        AsyncTimeout.this.exit(true);
                    } catch (IOException e7) {
                        throw AsyncTimeout.this.exit(e7);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() throws IOException {
                AsyncTimeout.this.enter();
                try {
                    try {
                        sink.flush();
                        AsyncTimeout.this.exit(true);
                    } catch (IOException e7) {
                        throw AsyncTimeout.this.exit(e7);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.Sink
            public Timeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                StringBuilder sbM112a = C0413b.m112a("AsyncTimeout.sink(");
                sbM112a.append(sink);
                sbM112a.append(")");
                return sbM112a.toString();
            }

            @Override // okio.Sink
            public void write(Buffer buffer, long j7) throws IOException {
                Util.checkOffsetAndCount(buffer.size, 0L, j7);
                while (true) {
                    long j8 = 0;
                    if (j7 <= 0) {
                        return;
                    }
                    Segment segment = buffer.head;
                    while (true) {
                        if (j8 >= 65536) {
                            break;
                        }
                        j8 += segment.limit - segment.pos;
                        if (j8 >= j7) {
                            j8 = j7;
                            break;
                        }
                        segment = segment.next;
                    }
                    AsyncTimeout.this.enter();
                    try {
                        try {
                            sink.write(buffer, j8);
                            j7 -= j8;
                            AsyncTimeout.this.exit(true);
                        } catch (IOException e7) {
                            throw AsyncTimeout.this.exit(e7);
                        }
                    } catch (Throwable th) {
                        AsyncTimeout.this.exit(false);
                        throw th;
                    }
                }
            }
        };
    }

    public final Source source(final Source source) {
        return new Source() { // from class: okio.AsyncTimeout.2
            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                AsyncTimeout.this.enter();
                try {
                    try {
                        source.close();
                        AsyncTimeout.this.exit(true);
                    } catch (IOException e7) {
                        throw AsyncTimeout.this.exit(e7);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.Source
            public long read(Buffer buffer, long j7) throws IOException {
                AsyncTimeout.this.enter();
                try {
                    try {
                        long j8 = source.read(buffer, j7);
                        AsyncTimeout.this.exit(true);
                        return j8;
                    } catch (IOException e7) {
                        throw AsyncTimeout.this.exit(e7);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.Source
            public Timeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                StringBuilder sbM112a = C0413b.m112a("AsyncTimeout.source(");
                sbM112a.append(source);
                sbM112a.append(")");
                return sbM112a.toString();
            }
        };
    }

    public void timedOut() {
    }

    public final void exit(boolean z6) throws IOException {
        if (exit() && z6) {
            throw newTimeoutException(null);
        }
    }

    public final IOException exit(IOException iOException) {
        return !exit() ? iOException : newTimeoutException(iOException);
    }
}
