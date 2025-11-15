package p011b1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p002a1.EnumC0005a;

/* compiled from: FixedBuilder.java */
/* renamed from: b1.a */
/* loaded from: classes.dex */
public class C0436a extends AbstractC0437b<ExecutorService> {

    /* renamed from: d */
    public int f267d = 1;

    @Override // p011b1.AbstractC0437b
    /* renamed from: b */
    public ExecutorService mo147b() {
        return Executors.newFixedThreadPool(this.f267d);
    }

    @Override // p011b1.AbstractC0437b
    /* renamed from: c */
    public EnumC0005a mo148c() {
        return EnumC0005a.FIXED;
    }
}
