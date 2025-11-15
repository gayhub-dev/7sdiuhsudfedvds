package retrofit2.adapter.rxjava2;

import p005a4.C0009a;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
final class CallEnqueueObservable<T> extends AbstractC2120l<Response<T>> {
    private final Call<T> originalCall;

    public static final class CallCallback<T> implements InterfaceC2153b, Callback<T> {
        private final Call<?> call;
        private volatile boolean disposed;
        private final InterfaceC2127s<? super Response<T>> observer;
        public boolean terminated = false;

        public CallCallback(Call<?> call, InterfaceC2127s<? super Response<T>> interfaceC2127s) {
            this.call = call;
            this.observer = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.disposed = true;
            this.call.cancel();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<T> call, Throwable th) {
            if (call.isCanceled()) {
                return;
            }
            try {
                this.observer.onError(th);
            } catch (Throwable th2) {
                C2074b.m2470J(th2);
                C1908a.m2205b(new C0009a(th, th2));
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<T> call, Response<T> response) {
            if (this.disposed) {
                return;
            }
            try {
                this.observer.onNext(response);
                if (this.disposed) {
                    return;
                }
                this.terminated = true;
                this.observer.onComplete();
            } catch (Throwable th) {
                if (this.terminated) {
                    C1908a.m2205b(th);
                    return;
                }
                if (this.disposed) {
                    return;
                }
                try {
                    this.observer.onError(th);
                } catch (Throwable th2) {
                    C2074b.m2470J(th2);
                    C1908a.m2205b(new C0009a(th, th2));
                }
            }
        }
    }

    public CallEnqueueObservable(Call<T> call) {
        this.originalCall = call;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Response<T>> interfaceC2127s) {
        Call<T> callClone = this.originalCall.clone();
        CallCallback callCallback = new CallCallback(callClone, interfaceC2127s);
        interfaceC2127s.onSubscribe(callCallback);
        if (callCallback.isDisposed()) {
            return;
        }
        callClone.enqueue(callCallback);
    }
}
