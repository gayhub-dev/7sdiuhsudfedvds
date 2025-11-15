package p088k4;

import java.util.ArrayDeque;
import p022c4.EnumC0515c;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableTakeLast.java */
/* renamed from: k4.y3 */
/* loaded from: classes.dex */
public final class C1386y3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final int f4019f;

    /* compiled from: ObservableTakeLast.java */
    /* renamed from: k4.y3$a */
    public static final class a<T> extends ArrayDeque<T> implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = 7240042530241604978L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f4020e;

        /* renamed from: f */
        public final int f4021f;

        /* renamed from: g */
        public InterfaceC2153b f4022g;

        /* renamed from: h */
        public volatile boolean f4023h;

        public a(InterfaceC2127s<? super T> interfaceC2127s, int i7) {
            this.f4020e = interfaceC2127s;
            this.f4021f = i7;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f4023h) {
                return;
            }
            this.f4023h = true;
            this.f4022g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f4023h;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            InterfaceC2127s<? super T> interfaceC2127s = this.f4020e;
            while (!this.f4023h) {
                T tPoll = poll();
                if (tPoll == null) {
                    if (this.f4023h) {
                        return;
                    }
                    interfaceC2127s.onComplete();
                    return;
                }
                interfaceC2127s.onNext(tPoll);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f4020e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f4021f == size()) {
                poll();
            }
            offer(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f4022g, interfaceC2153b)) {
                this.f4022g = interfaceC2153b;
                this.f4020e.onSubscribe(this);
            }
        }
    }

    public C1386y3(InterfaceC2125q<T> interfaceC2125q, int i7) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f4019f = i7;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f4019f));
    }
}
