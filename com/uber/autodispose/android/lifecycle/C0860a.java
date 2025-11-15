package com.uber.autodispose.android.lifecycle;

import android.arch.lifecycle.AbstractC0052c;
import android.arch.lifecycle.C0055f;
import java.util.Comparator;
import p014b4.InterfaceC0455o;
import p056g4.C1060a;
import p173v3.InterfaceC2011g;
import p187x3.C2076b;
import p187x3.C2077c;
import p187x3.C2079e;
import p187x3.C2080f;
import p187x3.InterfaceC2075a;
import p192y1.C2104h;
import p194y3.InterfaceC2112d;

/* compiled from: AndroidLifecycleScopeProvider.java */
/* renamed from: com.uber.autodispose.android.lifecycle.a */
/* loaded from: classes.dex */
public final class C0860a implements InterfaceC2011g {

    /* renamed from: a */
    public final InterfaceC2075a<AbstractC0052c.a> f1285a;

    /* renamed from: b */
    public final LifecycleEventsObservable f1286b;

    /* compiled from: AndroidLifecycleScopeProvider.java */
    /* renamed from: com.uber.autodispose.android.lifecycle.a$a */
    public static class a implements InterfaceC2075a<AbstractC0052c.a> {

        /* renamed from: e */
        public final AbstractC0052c.a f1287e;

        public a(AbstractC0052c.a aVar) {
            this.f1287e = aVar;
        }

        @Override // p187x3.InterfaceC2075a, p014b4.InterfaceC0454n
        public Object apply(Object obj) {
            return this.f1287e;
        }
    }

    public C0860a(AbstractC0052c abstractC0052c, InterfaceC2075a<AbstractC0052c.a> interfaceC2075a) {
        this.f1286b = new LifecycleEventsObservable(abstractC0052c);
        this.f1285a = interfaceC2075a;
    }

    /* renamed from: a */
    public static C0860a m668a(AbstractC0052c abstractC0052c, AbstractC0052c.a aVar) {
        return new C0860a(abstractC0052c, new a(aVar));
    }

    /* renamed from: b */
    public InterfaceC2112d m669b() throws Exception {
        int i7 = C2080f.f6177a;
        LifecycleEventsObservable lifecycleEventsObservable = this.f1286b;
        int iOrdinal = ((C0055f) lifecycleEventsObservable.f1279e).f95b.ordinal();
        lifecycleEventsObservable.f1280f.onNext(iOrdinal != 1 ? iOrdinal != 2 ? (iOrdinal == 3 || iOrdinal == 4) ? AbstractC0052c.a.ON_RESUME : AbstractC0052c.a.ON_DESTROY : AbstractC0052c.a.ON_START : AbstractC0052c.a.ON_CREATE);
        AbstractC0052c.a aVarM2381b = this.f1286b.f1280f.m2381b();
        InterfaceC2075a<AbstractC0052c.a> interfaceC2075a = this.f1285a;
        if (aVarM2381b == null) {
            throw new C2077c();
        }
        try {
            final AbstractC0052c.a aVarApply = interfaceC2075a.apply(aVarM2381b);
            LifecycleEventsObservable lifecycleEventsObservable2 = this.f1286b;
            final C2079e c2079e = aVarApply instanceof Comparable ? new Comparator() { // from class: x3.e
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ((Comparable) obj).compareTo((Comparable) obj2);
                }
            } : null;
            return lifecycleEventsObservable2.skip(1L).takeUntil(c2079e != null ? new InterfaceC0455o() { // from class: x3.d
                @Override // p014b4.InterfaceC0455o
                public final boolean test(Object obj) {
                    return c2079e.compare(obj, aVarApply) >= 0;
                }
            } : new C2104h(aVarApply)).ignoreElements();
        } catch (Exception e7) {
            if (e7 instanceof C2076b) {
                throw e7;
            }
            return new C1060a(e7);
        }
    }
}
