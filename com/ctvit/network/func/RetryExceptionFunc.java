package com.ctvit.network.func;

import com.ctvit.network.exception.ApiException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import p009b.C0413b;
import p014b4.InterfaceC0443c;
import p014b4.InterfaceC0454n;
import p186x2.C2073a;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;

/* loaded from: classes.dex */
public class RetryExceptionFunc implements InterfaceC0454n<AbstractC2120l<? extends Throwable>, AbstractC2120l<?>> {
    private int count;
    private long delay;
    private long increaseDelay;

    public class Wrapper {
        private int index;
        private Throwable throwable;

        public Wrapper(Throwable th, int i7) {
            this.index = i7;
            this.throwable = th;
        }
    }

    public RetryExceptionFunc() {
        this.count = 0;
        this.delay = 500L;
        this.increaseDelay = 3000L;
    }

    @Override // p014b4.InterfaceC0454n
    public AbstractC2120l<?> apply(AbstractC2120l<? extends Throwable> abstractC2120l) {
        return abstractC2120l.zipWith(AbstractC2120l.range(1, this.count + 1), new InterfaceC0443c<Throwable, Integer, Wrapper>() { // from class: com.ctvit.network.func.RetryExceptionFunc.2
            @Override // p014b4.InterfaceC0443c
            public Wrapper apply(Throwable th, Integer num) {
                return RetryExceptionFunc.this.new Wrapper(th, num.intValue());
            }
        }).flatMap(new InterfaceC0454n<Wrapper, InterfaceC2125q<?>>() { // from class: com.ctvit.network.func.RetryExceptionFunc.1
            @Override // p014b4.InterfaceC0454n
            public InterfaceC2125q<?> apply(Wrapper wrapper) {
                if (wrapper.index > 1) {
                    StringBuilder sbM112a = C0413b.m112a("重试次数：");
                    sbM112a.append(wrapper.index);
                    C2073a.m2459d(sbM112a.toString());
                }
                int code = wrapper.throwable instanceof ApiException ? ((ApiException) wrapper.throwable).getCode() : 0;
                if (((wrapper.throwable instanceof ConnectException) || (wrapper.throwable instanceof SocketTimeoutException) || code == 1002 || code == 1005 || (wrapper.throwable instanceof SocketTimeoutException) || (wrapper.throwable instanceof TimeoutException)) && wrapper.index < RetryExceptionFunc.this.count + 1) {
                    return AbstractC2120l.timer((RetryExceptionFunc.this.increaseDelay * (wrapper.index - 1)) + RetryExceptionFunc.this.delay, TimeUnit.MILLISECONDS);
                }
                return AbstractC2120l.error(wrapper.throwable);
            }
        });
    }

    public RetryExceptionFunc(int i7, long j7) {
        this.count = 0;
        this.delay = 500L;
        this.increaseDelay = 3000L;
        this.count = i7;
        this.delay = j7;
    }

    public RetryExceptionFunc(int i7, long j7, long j8) {
        this.count = 0;
        this.delay = 500L;
        this.increaseDelay = 3000L;
        this.count = i7;
        this.delay = j7;
        this.increaseDelay = j8;
    }
}
