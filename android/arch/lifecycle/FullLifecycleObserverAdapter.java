package android.arch.lifecycle;

import android.arch.lifecycle.AbstractC0052c;

/* loaded from: classes.dex */
class FullLifecycleObserverAdapter implements GenericLifecycleObserver {

    /* renamed from: e */
    public final FullLifecycleObserver f68e;

    /* renamed from: android.arch.lifecycle.FullLifecycleObserverAdapter$a */
    public static /* synthetic */ class C0046a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f69a;

        static {
            int[] iArr = new int[AbstractC0052c.a.values().length];
            f69a = iArr;
            try {
                iArr[AbstractC0052c.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f69a[AbstractC0052c.a.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f69a[AbstractC0052c.a.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f69a[AbstractC0052c.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f69a[AbstractC0052c.a.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f69a[AbstractC0052c.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f69a[AbstractC0052c.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public FullLifecycleObserverAdapter(FullLifecycleObserver fullLifecycleObserver) {
        this.f68e = fullLifecycleObserver;
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    /* renamed from: d */
    public void mo58d(InterfaceC0054e interfaceC0054e, AbstractC0052c.a aVar) {
        switch (C0046a.f69a[aVar.ordinal()]) {
            case 1:
                this.f68e.m63f(interfaceC0054e);
                return;
            case 2:
                this.f68e.m60b(interfaceC0054e);
                return;
            case 3:
                this.f68e.m64g(interfaceC0054e);
                return;
            case 4:
                this.f68e.m61c(interfaceC0054e);
                return;
            case 5:
                this.f68e.m59a(interfaceC0054e);
                return;
            case 6:
                this.f68e.m62e(interfaceC0054e);
                return;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            default:
                return;
        }
    }
}
