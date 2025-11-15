package com.uber.autodispose.android.lifecycle;

import android.arch.lifecycle.AbstractC0052c;
import android.arch.lifecycle.C0055f;
import android.arch.lifecycle.InterfaceC0053d;
import android.arch.lifecycle.InterfaceC0054e;
import android.arch.lifecycle.InterfaceC0061l;
import android.support.annotation.RestrictTo;
import p180w3.AbstractC2028b;
import p180w3.C2027a;
import p181w4.C2029a;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class LifecycleEventsObservable extends AbstractC2120l<AbstractC0052c.a> {

    /* renamed from: e */
    public final AbstractC0052c f1279e;

    /* renamed from: f */
    public final C2029a<AbstractC0052c.a> f1280f = new C2029a<>();

    public static final class ArchLifecycleObserver extends AbstractC2028b implements InterfaceC0053d {

        /* renamed from: f */
        public final AbstractC0052c f1281f;

        /* renamed from: g */
        public final InterfaceC2127s<? super AbstractC0052c.a> f1282g;

        /* renamed from: h */
        public final C2029a<AbstractC0052c.a> f1283h;

        public ArchLifecycleObserver(AbstractC0052c abstractC0052c, InterfaceC2127s<? super AbstractC0052c.a> interfaceC2127s, C2029a<AbstractC0052c.a> c2029a) {
            this.f1281f = abstractC0052c;
            this.f1282g = interfaceC2127s;
            this.f1283h = c2029a;
        }

        @Override // p180w3.AbstractC2028b
        /* renamed from: h */
        public void mo667h() {
            ((C0055f) this.f1281f).f94a.mo111d(this);
        }

        @InterfaceC0061l(AbstractC0052c.a.ON_ANY)
        public void onStateChange(InterfaceC0054e interfaceC0054e, AbstractC0052c.a aVar) {
            if (isDisposed()) {
                return;
            }
            if (aVar != AbstractC0052c.a.ON_CREATE || this.f1283h.m2381b() != aVar) {
                this.f1283h.onNext(aVar);
            }
            this.f1282g.onNext(aVar);
        }
    }

    public LifecycleEventsObservable(AbstractC0052c abstractC0052c) {
        this.f1279e = abstractC0052c;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super AbstractC0052c.a> interfaceC2127s) {
        ArchLifecycleObserver archLifecycleObserver = new ArchLifecycleObserver(this.f1279e, interfaceC2127s, this.f1280f);
        interfaceC2127s.onSubscribe(archLifecycleObserver);
        if (!C2027a.m2380a()) {
            interfaceC2127s.onError(new IllegalStateException("Lifecycles can only be bound to on the main thread!"));
            return;
        }
        this.f1279e.mo75a(archLifecycleObserver);
        if (archLifecycleObserver.isDisposed()) {
            ((C0055f) this.f1279e).f94a.mo111d(archLifecycleObserver);
        }
    }
}
