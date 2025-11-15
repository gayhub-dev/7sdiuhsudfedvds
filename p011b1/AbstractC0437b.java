package p011b1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import p002a1.EnumC0005a;

/* compiled from: ThreadPoolBuilder.java */
/* renamed from: b1.b */
/* loaded from: classes.dex */
public abstract class AbstractC0437b<T extends ExecutorService> {

    /* renamed from: c */
    public static Map<String, ExecutorService> f268c = new ConcurrentHashMap();

    /* renamed from: a */
    public ExecutorService f269a = null;

    /* renamed from: b */
    public String f270b = "default";

    /* renamed from: a */
    public ExecutorService m149a() {
        if (((ConcurrentHashMap) f268c).get(mo148c() + "_" + this.f270b) != null) {
            this.f269a = (ExecutorService) ((ConcurrentHashMap) f268c).get(mo148c() + "_" + this.f270b);
        } else {
            this.f269a = mo147b();
            ((ConcurrentHashMap) f268c).put(mo148c() + "_" + this.f270b, this.f269a);
        }
        return this.f269a;
    }

    /* renamed from: b */
    public abstract T mo147b();

    /* renamed from: c */
    public abstract EnumC0005a mo148c();
}
