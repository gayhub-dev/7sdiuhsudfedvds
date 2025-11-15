package retrofit2.adapter.rxjava2;

import p005a4.C0009a;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;
import retrofit2.Call;
import retrofit2.Response;

/* loaded from: classes.dex */
final class CallExecuteObservable<T> extends AbstractC2120l<Response<T>> {
    private final Call<T> originalCall;

    public static final class CallDisposable implements InterfaceC2153b {
        private final Call<?> call;
        private volatile boolean disposed;

        public CallDisposable(Call<?> call) {
            this.call = call;
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
    }

    public CallExecuteObservable(Call<T> call) {
        this.originalCall = call;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Response<T>> interfaceC2127s) {
        boolean z6;
        Call<T> callClone = this.originalCall.clone();
        CallDisposable callDisposable = new CallDisposable(callClone);
        interfaceC2127s.onSubscribe(callDisposable);
        if (callDisposable.isDisposed()) {
            return;
        }
        try {
            Response<T> responseExecute = callClone.execute();
            if (!callDisposable.isDisposed()) {
                interfaceC2127s.onNext(responseExecute);
            }
            if (callDisposable.isDisposed()) {
                return;
            }
            try {
                interfaceC2127s.onComplete();
            } catch (Throwable th) {
                th = th;
                z6 = true;
                C2074b.m2470J(th);
                if (z6) {
                    C1908a.m2205b(th);
                    return;
                }
                if (callDisposable.isDisposed()) {
                    return;
                }
                try {
                    interfaceC2127s.onError(th);
                } catch (Throwable th2) {
                    C2074b.m2470J(th2);
                    C1908a.m2205b(new C0009a(th, th2));
                }
            }
        } catch (Throwable th3) {
            th = th3;
            z6 = false;
        }
    }
}
