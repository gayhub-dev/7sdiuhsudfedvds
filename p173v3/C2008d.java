package p173v3;

import com.uber.autodispose.android.lifecycle.C0860a;
import java.util.concurrent.Callable;
import p056g4.C1060a;

/* compiled from: AutoDispose.java */
/* renamed from: v3.d */
/* loaded from: classes.dex */
public final class C2008d {
    /* renamed from: a */
    public static <T> InterfaceC2009e<T> m2346a(final InterfaceC2011g interfaceC2011g) {
        return new C2007c(new C1060a(new Callable() { // from class: v3.a
            @Override // java.util.concurrent.Callable
            public final Object call() {
                try {
                    return ((C0860a) interfaceC2011g).m669b();
                } catch (C2010f e7) {
                    return new C1060a(e7);
                }
            }
        }));
    }
}
