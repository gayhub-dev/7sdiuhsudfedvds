package retrofit2.adapter.rxjava2;

import p005a4.C0009a;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;
import retrofit2.Response;

/* loaded from: classes.dex */
final class BodyObservable<T> extends AbstractC2120l<T> {
    private final AbstractC2120l<Response<T>> upstream;

    public static class BodyObserver<R> implements InterfaceC2127s<Response<R>> {
        private final InterfaceC2127s<? super R> observer;
        private boolean terminated;

        public BodyObserver(InterfaceC2127s<? super R> interfaceC2127s) {
            this.observer = interfaceC2127s;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.terminated) {
                return;
            }
            this.observer.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (!this.terminated) {
                this.observer.onError(th);
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.");
            assertionError.initCause(th);
            C1908a.m2205b(assertionError);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            this.observer.onSubscribe(interfaceC2153b);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Response<R> response) {
            if (response.isSuccessful()) {
                this.observer.onNext(response.body());
                return;
            }
            this.terminated = true;
            HttpException httpException = new HttpException(response);
            try {
                this.observer.onError(httpException);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                C1908a.m2205b(new C0009a(httpException, th));
            }
        }
    }

    public BodyObservable(AbstractC2120l<Response<T>> abstractC2120l) {
        this.upstream = abstractC2120l;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.upstream.subscribe(new BodyObserver(interfaceC2127s));
    }
}
