package io.reactivex.android.schedulers;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p194y3.AbstractC2128t;
import p201z3.InterfaceC2153b;

/* loaded from: classes.dex */
final class HandlerScheduler extends AbstractC2128t {
    private final boolean async;
    private final Handler handler;

    public static final class HandlerWorker extends AbstractC2128t.c {
        private final boolean async;
        private volatile boolean disposed;
        private final Handler handler;

        public HandlerWorker(Handler handler, boolean z6) {
            this.handler = handler;
            this.async = z6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.disposed = true;
            this.handler.removeCallbacksAndMessages(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // p194y3.AbstractC2128t.c
        @SuppressLint({"NewApi"})
        public InterfaceC2153b schedule(Runnable runnable, long j7, TimeUnit timeUnit) {
            EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
            Objects.requireNonNull(runnable, "run == null");
            Objects.requireNonNull(timeUnit, "unit == null");
            if (this.disposed) {
                return enumC0516d;
            }
            ScheduledRunnable scheduledRunnable = new ScheduledRunnable(this.handler, runnable);
            Message messageObtain = Message.obtain(this.handler, scheduledRunnable);
            messageObtain.obj = this;
            if (this.async) {
                messageObtain.setAsynchronous(true);
            }
            this.handler.sendMessageDelayed(messageObtain, timeUnit.toMillis(j7));
            if (!this.disposed) {
                return scheduledRunnable;
            }
            this.handler.removeCallbacks(scheduledRunnable);
            return enumC0516d;
        }
    }

    public static final class ScheduledRunnable implements Runnable, InterfaceC2153b {
        private final Runnable delegate;
        private volatile boolean disposed;
        private final Handler handler;

        public ScheduledRunnable(Handler handler, Runnable runnable) {
            this.handler = handler;
            this.delegate = runnable;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.handler.removeCallbacks(this);
            this.disposed = true;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.delegate.run();
            } catch (Throwable th) {
                C1908a.m2205b(th);
            }
        }
    }

    public HandlerScheduler(Handler handler, boolean z6) {
        this.handler = handler;
        this.async = z6;
    }

    @Override // p194y3.AbstractC2128t
    public AbstractC2128t.c createWorker() {
        return new HandlerWorker(this.handler, this.async);
    }

    @Override // p194y3.AbstractC2128t
    @SuppressLint({"NewApi"})
    public InterfaceC2153b scheduleDirect(Runnable runnable, long j7, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run == null");
        Objects.requireNonNull(timeUnit, "unit == null");
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(this.handler, runnable);
        Message messageObtain = Message.obtain(this.handler, scheduledRunnable);
        if (this.async) {
            messageObtain.setAsynchronous(true);
        }
        this.handler.sendMessageDelayed(messageObtain, timeUnit.toMillis(j7));
        return scheduledRunnable;
    }
}
