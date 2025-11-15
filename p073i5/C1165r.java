package p073i5;

import android.support.constraint.solver.C0084a;
import p009b.C0413b;

/* compiled from: ThreadLocalBuffers.java */
/* renamed from: i5.r */
/* loaded from: classes.dex */
public class C1165r extends AbstractC1149b {

    /* renamed from: f */
    public final ThreadLocal<b> f2556f;

    /* compiled from: ThreadLocalBuffers.java */
    /* renamed from: i5.r$a */
    public class a extends ThreadLocal<b> {
        public a(C1165r c1165r) {
        }

        @Override // java.lang.ThreadLocal
        public b initialValue() {
            return new b();
        }
    }

    /* compiled from: ThreadLocalBuffers.java */
    /* renamed from: i5.r$b */
    public static class b {

        /* renamed from: a */
        public InterfaceC1152e f2557a;

        /* renamed from: b */
        public InterfaceC1152e f2558b;

        /* renamed from: c */
        public InterfaceC1152e f2559c;
    }

    public C1165r(int i7, int i8, int i9, int i10, int i11) {
        super(i7, i8, i9, i10, i11);
        this.f2556f = new a(this);
    }

    @Override // p073i5.InterfaceC1156i
    /* renamed from: a */
    public void mo1368a(InterfaceC1152e interfaceC1152e) {
        interfaceC1152e.clear();
        if (interfaceC1152e.mo1327W() || interfaceC1152e.mo1329f()) {
            return;
        }
        b bVar = this.f2556f.get();
        if (bVar.f2558b == null && m1344e(interfaceC1152e)) {
            bVar.f2558b = interfaceC1152e;
        } else if (bVar.f2557a == null && m1343d(interfaceC1152e)) {
            bVar.f2557a = interfaceC1152e;
        } else {
            bVar.f2559c = interfaceC1152e;
        }
    }

    @Override // p073i5.InterfaceC1156i
    /* renamed from: b */
    public InterfaceC1152e mo1369b(int i7) {
        b bVar = this.f2556f.get();
        InterfaceC1152e interfaceC1152e = bVar.f2559c;
        if (interfaceC1152e == null || interfaceC1152e.mo1350a() != i7) {
            return m1346g(i7);
        }
        InterfaceC1152e interfaceC1152e2 = bVar.f2559c;
        bVar.f2559c = null;
        return interfaceC1152e2;
    }

    @Override // p073i5.InterfaceC1156i
    /* renamed from: c */
    public InterfaceC1152e mo1370c() {
        b bVar = this.f2556f.get();
        InterfaceC1152e interfaceC1152e = bVar.f2558b;
        if (interfaceC1152e != null) {
            bVar.f2558b = null;
            return interfaceC1152e;
        }
        InterfaceC1152e interfaceC1152e2 = bVar.f2559c;
        if (interfaceC1152e2 == null || !m1344e(interfaceC1152e2)) {
            return m1347h();
        }
        InterfaceC1152e interfaceC1152e3 = bVar.f2559c;
        bVar.f2559c = null;
        return interfaceC1152e3;
    }

    @Override // p073i5.InterfaceC1156i
    public InterfaceC1152e getBuffer() {
        b bVar = this.f2556f.get();
        InterfaceC1152e interfaceC1152e = bVar.f2557a;
        if (interfaceC1152e != null) {
            bVar.f2557a = null;
            return interfaceC1152e;
        }
        InterfaceC1152e interfaceC1152e2 = bVar.f2559c;
        if (interfaceC1152e2 == null || !m1343d(interfaceC1152e2)) {
            return m1345f();
        }
        InterfaceC1152e interfaceC1152e3 = bVar.f2559c;
        bVar.f2559c = null;
        return interfaceC1152e3;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("{{");
        sbM112a.append(this.f2533b);
        sbM112a.append(",");
        return C0084a.m96a(sbM112a, this.f2535d, "}}");
    }
}
