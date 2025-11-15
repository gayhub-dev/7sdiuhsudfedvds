package android.arch.lifecycle;

import android.arch.lifecycle.AbstractC0052c;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p009b.C0412a;
import p009b.C0414c;

/* compiled from: LifecycleRegistry.java */
/* renamed from: android.arch.lifecycle.f */
/* loaded from: classes.dex */
public class C0055f extends AbstractC0052c {

    /* renamed from: c */
    public final WeakReference<InterfaceC0054e> f96c;

    /* renamed from: a */
    public C0412a<InterfaceC0053d, b> f94a = new C0412a<>();

    /* renamed from: d */
    public int f97d = 0;

    /* renamed from: e */
    public boolean f98e = false;

    /* renamed from: f */
    public boolean f99f = false;

    /* renamed from: g */
    public ArrayList<AbstractC0052c.b> f100g = new ArrayList<>();

    /* renamed from: b */
    public AbstractC0052c.b f95b = AbstractC0052c.b.INITIALIZED;

    /* compiled from: LifecycleRegistry.java */
    /* renamed from: android.arch.lifecycle.f$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f101a;

        /* renamed from: b */
        public static final /* synthetic */ int[] f102b;

        static {
            int[] iArr = new int[AbstractC0052c.b.values().length];
            f102b = iArr;
            try {
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f102b[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f102b[3] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f102b[4] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f102b[0] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[AbstractC0052c.a.values().length];
            f101a = iArr2;
            try {
                iArr2[AbstractC0052c.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f101a[AbstractC0052c.a.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f101a[AbstractC0052c.a.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f101a[AbstractC0052c.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f101a[AbstractC0052c.a.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f101a[AbstractC0052c.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f101a[AbstractC0052c.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* compiled from: LifecycleRegistry.java */
    /* renamed from: android.arch.lifecycle.f$b */
    public static class b {

        /* renamed from: a */
        public AbstractC0052c.b f103a;

        /* renamed from: b */
        public GenericLifecycleObserver f104b;

        public b(InterfaceC0053d interfaceC0053d, AbstractC0052c.b bVar) {
            GenericLifecycleObserver reflectiveGenericLifecycleObserver;
            Map<Class, Integer> map = C0057h.f105a;
            if (interfaceC0053d instanceof FullLifecycleObserver) {
                reflectiveGenericLifecycleObserver = new FullLifecycleObserverAdapter((FullLifecycleObserver) interfaceC0053d);
            } else if (interfaceC0053d instanceof GenericLifecycleObserver) {
                reflectiveGenericLifecycleObserver = (GenericLifecycleObserver) interfaceC0053d;
            } else {
                Class<?> cls = interfaceC0053d.getClass();
                if (C0057h.m87c(cls) == 2) {
                    List list = (List) ((HashMap) C0057h.f106b).get(cls);
                    if (list.size() == 1) {
                        reflectiveGenericLifecycleObserver = new SingleGeneratedAdapterObserver(C0057h.m85a((Constructor) list.get(0), interfaceC0053d));
                    } else {
                        InterfaceC0051b[] interfaceC0051bArr = new InterfaceC0051b[list.size()];
                        for (int i7 = 0; i7 < list.size(); i7++) {
                            interfaceC0051bArr[i7] = C0057h.m85a((Constructor) list.get(i7), interfaceC0053d);
                        }
                        reflectiveGenericLifecycleObserver = new CompositeGeneratedAdaptersObserver(interfaceC0051bArr);
                    }
                } else {
                    reflectiveGenericLifecycleObserver = new ReflectiveGenericLifecycleObserver(interfaceC0053d);
                }
            }
            this.f104b = reflectiveGenericLifecycleObserver;
            this.f103a = bVar;
        }

        /* renamed from: a */
        public void m84a(InterfaceC0054e interfaceC0054e, AbstractC0052c.a aVar) {
            AbstractC0052c.b bVarM76c = C0055f.m76c(aVar);
            this.f103a = C0055f.m77e(this.f103a, bVarM76c);
            this.f104b.mo58d(interfaceC0054e, aVar);
            this.f103a = bVarM76c;
        }
    }

    public C0055f(@NonNull InterfaceC0054e interfaceC0054e) {
        this.f96c = new WeakReference<>(interfaceC0054e);
    }

    /* renamed from: c */
    public static AbstractC0052c.b m76c(AbstractC0052c.a aVar) {
        switch (a.f101a[aVar.ordinal()]) {
            case 1:
            case 2:
                return AbstractC0052c.b.CREATED;
            case 3:
            case 4:
                return AbstractC0052c.b.STARTED;
            case 5:
                return AbstractC0052c.b.RESUMED;
            case 6:
                return AbstractC0052c.b.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + aVar);
        }
    }

    /* renamed from: e */
    public static AbstractC0052c.b m77e(@NonNull AbstractC0052c.b bVar, @Nullable AbstractC0052c.b bVar2) {
        return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
    }

    /* renamed from: i */
    public static AbstractC0052c.a m78i(AbstractC0052c.b bVar) {
        int iOrdinal = bVar.ordinal();
        if (iOrdinal == 0 || iOrdinal == 1) {
            return AbstractC0052c.a.ON_CREATE;
        }
        if (iOrdinal == 2) {
            return AbstractC0052c.a.ON_START;
        }
        if (iOrdinal == 3) {
            return AbstractC0052c.a.ON_RESUME;
        }
        if (iOrdinal == 4) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("Unexpected state value " + bVar);
    }

    @Override // android.arch.lifecycle.AbstractC0052c
    /* renamed from: a */
    public void mo75a(@NonNull InterfaceC0053d interfaceC0053d) {
        InterfaceC0054e interfaceC0054e;
        AbstractC0052c.b bVar = this.f95b;
        AbstractC0052c.b bVar2 = AbstractC0052c.b.DESTROYED;
        if (bVar != bVar2) {
            bVar2 = AbstractC0052c.b.INITIALIZED;
        }
        b bVar3 = new b(interfaceC0053d, bVar2);
        if (this.f94a.mo110c(interfaceC0053d, bVar3) == null && (interfaceC0054e = this.f96c.get()) != null) {
            boolean z6 = this.f97d != 0 || this.f98e;
            AbstractC0052c.b bVarM79b = m79b(interfaceC0053d);
            this.f97d++;
            while (bVar3.f103a.compareTo(bVarM79b) < 0 && this.f94a.f176i.containsKey(interfaceC0053d)) {
                this.f100g.add(bVar3.f103a);
                bVar3.m84a(interfaceC0054e, m78i(bVar3.f103a));
                m82g();
                bVarM79b = m79b(interfaceC0053d);
            }
            if (!z6) {
                m83h();
            }
            this.f97d--;
        }
    }

    /* renamed from: b */
    public final AbstractC0052c.b m79b(InterfaceC0053d interfaceC0053d) {
        C0412a<InterfaceC0053d, b> c0412a = this.f94a;
        AbstractC0052c.b bVar = null;
        C0414c.d<InterfaceC0053d, b> dVar = c0412a.f176i.containsKey(interfaceC0053d) ? c0412a.f176i.get(interfaceC0053d).f184h : null;
        AbstractC0052c.b bVar2 = dVar != null ? dVar.f182f.f103a : null;
        if (!this.f100g.isEmpty()) {
            bVar = this.f100g.get(r0.size() - 1);
        }
        return m77e(m77e(this.f95b, bVar2), bVar);
    }

    /* renamed from: d */
    public void m80d(@NonNull AbstractC0052c.a aVar) {
        m81f(m76c(aVar));
    }

    /* renamed from: f */
    public final void m81f(AbstractC0052c.b bVar) {
        if (this.f95b == bVar) {
            return;
        }
        this.f95b = bVar;
        if (this.f98e || this.f97d != 0) {
            this.f99f = true;
            return;
        }
        this.f98e = true;
        m83h();
        this.f98e = false;
    }

    /* renamed from: g */
    public final void m82g() {
        this.f100g.remove(r0.size() - 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002a  */
    /* renamed from: h */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m83h() {
        /*
            Method dump skipped, instructions count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.arch.lifecycle.C0055f.m83h():void");
    }
}
