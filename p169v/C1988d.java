package p169v;

import java.util.HashMap;
import java.util.Map;
import p169v.InterfaceC1987c;

/* compiled from: DataRewinderRegistry.java */
/* renamed from: v.d */
/* loaded from: classes.dex */
public class C1988d {

    /* renamed from: b */
    public static final InterfaceC1987c.a<?> f5805b = new a();

    /* renamed from: a */
    public final Map<Class<?>, InterfaceC1987c.a<?>> f5806a = new HashMap();

    /* compiled from: DataRewinderRegistry.java */
    /* renamed from: v.d$a */
    public static class a implements InterfaceC1987c.a<Object> {
        @Override // p169v.InterfaceC1987c.a
        /* renamed from: a */
        public Class<Object> mo1005a() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override // p169v.InterfaceC1987c.a
        /* renamed from: b */
        public InterfaceC1987c<Object> mo1006b(Object obj) {
            return new b(obj);
        }
    }

    /* compiled from: DataRewinderRegistry.java */
    /* renamed from: v.d$b */
    public static class b implements InterfaceC1987c<Object> {

        /* renamed from: a */
        public final Object f5807a;

        public b(Object obj) {
            this.f5807a = obj;
        }

        @Override // p169v.InterfaceC1987c
        /* renamed from: a */
        public Object mo1003a() {
            return this.f5807a;
        }

        @Override // p169v.InterfaceC1987c
        /* renamed from: b */
        public void mo1004b() {
        }
    }
}
