package p138q4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import p014b4.InterfaceC0454n;

/* compiled from: ArrayListSupplier.java */
/* renamed from: q4.b */
/* loaded from: classes.dex */
public enum EnumC1770b implements Callable<List<Object>>, InterfaceC0454n<Object, List<Object>> {
    INSTANCE;

    @Override // p014b4.InterfaceC0454n
    public List<Object> apply(Object obj) {
        return new ArrayList();
    }

    @Override // java.util.concurrent.Callable
    public List<Object> call() {
        return new ArrayList();
    }
}
