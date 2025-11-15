package android.arch.lifecycle;

import android.arch.lifecycle.AbstractC0052c;
import android.arch.lifecycle.C0050a;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements GenericLifecycleObserver {

    /* renamed from: e */
    public final Object f77e;

    /* renamed from: f */
    public final C0050a.a f78f;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.f77e = obj;
        this.f78f = C0050a.f81c.m71b(obj.getClass());
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    /* renamed from: d */
    public void mo58d(InterfaceC0054e interfaceC0054e, AbstractC0052c.a aVar) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        C0050a.a aVar2 = this.f78f;
        Object obj = this.f77e;
        C0050a.a.m73a(aVar2.f84a.get(aVar), interfaceC0054e, aVar, obj);
        C0050a.a.m73a(aVar2.f84a.get(AbstractC0052c.a.ON_ANY), interfaceC0054e, aVar, obj);
    }
}
