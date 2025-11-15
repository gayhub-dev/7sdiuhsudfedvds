package p100m0;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import p162u.InterfaceC1968l;

/* compiled from: ResourceEncoderRegistry.java */
/* renamed from: m0.d */
/* loaded from: classes.dex */
public class C1456d {

    /* renamed from: a */
    public final List<a<?>> f4221a = new ArrayList();

    /* compiled from: ResourceEncoderRegistry.java */
    /* renamed from: m0.d$a */
    public static final class a<T> {

        /* renamed from: a */
        public final Class<T> f4222a;

        /* renamed from: b */
        public final InterfaceC1968l<T> f4223b;

        public a(Class<T> cls, InterfaceC1968l<T> interfaceC1968l) {
            this.f4222a = cls;
            this.f4223b = interfaceC1968l;
        }
    }

    @Nullable
    /* renamed from: a */
    public synchronized <Z> InterfaceC1968l<Z> m1641a(Class<Z> cls) {
        int size = this.f4221a.size();
        for (int i7 = 0; i7 < size; i7++) {
            a<?> aVar = this.f4221a.get(i7);
            if (aVar.f4222a.isAssignableFrom(cls)) {
                return (InterfaceC1968l<Z>) aVar.f4223b;
            }
        }
        return null;
    }
}
