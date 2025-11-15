package p138q4;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* compiled from: HashMapSupplier.java */
/* renamed from: q4.g */
/* loaded from: classes.dex */
public enum EnumC1775g implements Callable<Map<Object, Object>> {
    INSTANCE;

    @Override // java.util.concurrent.Callable
    public Map<Object, Object> call() {
        return new HashMap();
    }
}
