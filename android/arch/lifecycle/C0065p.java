package android.arch.lifecycle;

import java.util.HashMap;
import java.util.Iterator;

/* compiled from: ViewModelStore.java */
/* renamed from: android.arch.lifecycle.p */
/* loaded from: classes.dex */
public class C0065p {

    /* renamed from: a */
    public final HashMap<String, AbstractC0062m> f108a = new HashMap<>();

    /* renamed from: a */
    public final void m89a() {
        Iterator<AbstractC0062m> it = this.f108a.values().iterator();
        while (it.hasNext()) {
            it.next().onCleared();
        }
        this.f108a.clear();
    }
}
