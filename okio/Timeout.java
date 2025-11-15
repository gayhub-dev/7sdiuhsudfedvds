package okio;

import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class Timeout {
    public static final Timeout NONE = new Timeout() { // from class: okio.Timeout.1
        @Override // okio.Timeout
        public Timeout deadlineNanoTime(long j7) {
            return this;
        }

        @Override // okio.Timeout
        public void throwIfReached() {
        }

        @Override // okio.Timeout
        public Timeout timeout(long j7, TimeUnit timeUnit) {
            return this;
        }
    };
    private long deadlineNanoTime;
    private boolean hasDeadline;
    private long timeoutNanos;

    public static long minTimeout(long j7, long j8) {
        return j7 == 0 ? j8 : (j8 != 0 && j7 >= j8) ? j8 : j7;
    }

    public Timeout clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    public Timeout clearTimeout() {
        this.timeoutNanos = 0L;
        return this;
    }

    public final Timeout deadline(long j7, TimeUnit timeUnit) {
        if (j7 <= 0) {
            throw new IllegalArgumentException(C0534b.m341a("duration <= 0: ", j7));
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        return deadlineNanoTime(timeUnit.toNanos(j7) + System.nanoTime());
    }

    public long deadlineNanoTime() {
        if (this.hasDeadline) {
            return this.deadlineNanoTime;
        }
        throw new IllegalStateException("No deadline");
    }

    public boolean hasDeadline() {
        return this.hasDeadline;
    }

    public void throwIfReached() throws InterruptedIOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public Timeout timeout(long j7, TimeUnit timeUnit) {
        if (j7 < 0) {
            throw new IllegalArgumentException(C0534b.m341a("timeout < 0: ", j7));
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        this.timeoutNanos = timeUnit.toNanos(j7);
        return this;
    }

    public long timeoutNanos() {
        return this.timeoutNanos;
    }

    public final void waitUntilNotified(Object obj) throws InterruptedException, InterruptedIOException {
        try {
            boolean zHasDeadline = hasDeadline();
            long jTimeoutNanos = timeoutNanos();
            long jNanoTime = 0;
            if (!zHasDeadline && jTimeoutNanos == 0) {
                obj.wait();
                return;
            }
            long jNanoTime2 = System.nanoTime();
            if (zHasDeadline && jTimeoutNanos != 0) {
                jTimeoutNanos = Math.min(jTimeoutNanos, deadlineNanoTime() - jNanoTime2);
            } else if (zHasDeadline) {
                jTimeoutNanos = deadlineNanoTime() - jNanoTime2;
            }
            if (jTimeoutNanos > 0) {
                long j7 = jTimeoutNanos / 1000000;
                Long.signum(j7);
                obj.wait(j7, (int) (jTimeoutNanos - (1000000 * j7)));
                jNanoTime = System.nanoTime() - jNanoTime2;
            }
            if (jNanoTime >= jTimeoutNanos) {
                throw new InterruptedIOException("timeout");
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
    }

    public Timeout deadlineNanoTime(long j7) {
        this.hasDeadline = true;
        this.deadlineNanoTime = j7;
        return this;
    }
}
