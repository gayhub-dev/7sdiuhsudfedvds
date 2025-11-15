package retrofit2.adapter.rxjava2;

import p005a4.C0009a;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;
import retrofit2.Response;

/* loaded from: classes.dex */
final class ResultObservable<T> extends AbstractC2120l<Result<T>> {
    private final AbstractC2120l<Response<T>> upstream;

    public static class ResultObserver<R> implements InterfaceC2127s<Response<R>> {
        private final InterfaceC2127s<? super Result<R>> observer;

        public ResultObserver(InterfaceC2127s<? super Result<R>> interfaceC2127s) {
            this.observer = interfaceC2127s;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.observer.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            try {
                this.observer.onNext(Result.error(th));
                this.observer.onComplete();
            } catch (Throwable th2) {
                try {
                    this.observer.onError(th2);
                } catch (Throwable th3) {
                    C2074b.m2470J(th3);
                    C1908a.m2205b(new C0009a(th2, th3));
                }
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            this.observer.onSubscribe(interfaceC2153b);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Response<R> response) {
            this.observer.onNext(Result.response(response));
        }
    }

    public ResultObservable(AbstractC2120l<Response<T>> abstractC2120l) {
        this.upstream = abstractC2120l;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super Result<T>> interfaceC2127s) {
        this.upstream.subscribe(new ResultObserver(interfaceC2127s));
    }
}
