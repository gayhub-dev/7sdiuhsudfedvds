package p201z3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import p005a4.C0009a;
import p022c4.InterfaceC0514b;
import p138q4.C1774f;
import p138q4.C1777i;
import p186x2.C2074b;

/* compiled from: CompositeDisposable.java */
/* renamed from: z3.a */
/* loaded from: classes.dex */
public final class C2152a implements InterfaceC2153b, InterfaceC0514b {

    /* renamed from: e */
    public final /* synthetic */ int f6328e;

    /* renamed from: f */
    public C1777i<InterfaceC2153b> f6329f;

    /* renamed from: g */
    public volatile boolean f6330g;

    public C2152a(int i7) {
        this.f6328e = i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0050 A[Catch: all -> 0x0055, DONT_GENERATE, TryCatch #0 {, blocks: (B:9:0x0013, B:11:0x0017, B:13:0x0019, B:15:0x001f, B:30:0x0050, B:18:0x0031, B:20:0x0037, B:21:0x003b, B:24:0x0043, B:26:0x0049, B:32:0x0052), top: B:59:0x0013 }] */
    @Override // p022c4.InterfaceC0514b
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean mo322a(p201z3.InterfaceC2153b r8) {
        /*
            r7 = this;
            int r0 = r7.f6328e
            r1 = 1
            r2 = 0
            switch(r0) {
                case 0: goto L8;
                default: goto L7;
            }
        L7:
            goto L58
        L8:
            java.lang.String r0 = "disposables is null"
            java.util.Objects.requireNonNull(r8, r0)
            boolean r0 = r7.f6330g
            if (r0 == 0) goto L12
            goto L53
        L12:
            monitor-enter(r7)
            boolean r0 = r7.f6330g     // Catch: java.lang.Throwable -> L55
            if (r0 == 0) goto L19
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L55
            goto L53
        L19:
            q4.i<z3.b> r0 = r7.f6329f     // Catch: java.lang.Throwable -> L55
            q4.i r0 = (p138q4.C1777i) r0     // Catch: java.lang.Throwable -> L55
            if (r0 == 0) goto L52
            T[] r3 = r0.f5066d     // Catch: java.lang.Throwable -> L55
            int r4 = r0.f5063a     // Catch: java.lang.Throwable -> L55
            int r5 = r8.hashCode()     // Catch: java.lang.Throwable -> L55
            int r5 = p138q4.C1777i.m1965b(r5)     // Catch: java.lang.Throwable -> L55
            r5 = r5 & r4
            r6 = r3[r5]     // Catch: java.lang.Throwable -> L55
            if (r6 != 0) goto L31
            goto L41
        L31:
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Throwable -> L55
            if (r6 == 0) goto L3b
            r0.m1967c(r5, r3, r4)     // Catch: java.lang.Throwable -> L55
            goto L4c
        L3b:
            int r5 = r5 + r1
            r5 = r5 & r4
            r6 = r3[r5]     // Catch: java.lang.Throwable -> L55
            if (r6 != 0) goto L43
        L41:
            r8 = 0
            goto L4d
        L43:
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Throwable -> L55
            if (r6 == 0) goto L3b
            r0.m1967c(r5, r3, r4)     // Catch: java.lang.Throwable -> L55
        L4c:
            r8 = 1
        L4d:
            if (r8 != 0) goto L50
            goto L52
        L50:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L55
            goto L54
        L52:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L55
        L53:
            r1 = 0
        L54:
            return r1
        L55:
            r8 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L55
            throw r8
        L58:
            java.lang.String r0 = "Disposable item is null"
            java.util.Objects.requireNonNull(r8, r0)
            boolean r0 = r7.f6330g
            if (r0 == 0) goto L62
            goto L79
        L62:
            monitor-enter(r7)
            boolean r0 = r7.f6330g     // Catch: java.lang.Throwable -> L7b
            if (r0 == 0) goto L69
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L7b
            goto L79
        L69:
            q4.i<z3.b> r0 = r7.f6329f     // Catch: java.lang.Throwable -> L7b
            java.util.List r0 = (java.util.List) r0     // Catch: java.lang.Throwable -> L7b
            if (r0 == 0) goto L78
            boolean r8 = r0.remove(r8)     // Catch: java.lang.Throwable -> L7b
            if (r8 != 0) goto L76
            goto L78
        L76:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L7b
            goto L7a
        L78:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L7b
        L79:
            r1 = 0
        L7a:
            return r1
        L7b:
            r8 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L7b
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p201z3.C2152a.mo322a(z3.b):boolean");
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.LinkedList, q4.i<z3.b>] */
    /* renamed from: b */
    public boolean m2595b(InterfaceC2153b interfaceC2153b) {
        switch (this.f6328e) {
            case 0:
                if (!this.f6330g) {
                    synchronized (this) {
                        if (!this.f6330g) {
                            C1777i<InterfaceC2153b> c1777i = this.f6329f;
                            if (c1777i == null) {
                                c1777i = new C1777i<>();
                                this.f6329f = c1777i;
                            }
                            c1777i.m1966a(interfaceC2153b);
                            return true;
                        }
                    }
                }
                interfaceC2153b.dispose();
                return false;
            default:
                if (!this.f6330g) {
                    synchronized (this) {
                        if (!this.f6330g) {
                            List list = (List) this.f6329f;
                            List list2 = list;
                            if (list == null) {
                                ?? linkedList = new LinkedList();
                                this.f6329f = linkedList;
                                list2 = linkedList;
                            }
                            list2.add(interfaceC2153b);
                            return true;
                        }
                    }
                }
                interfaceC2153b.dispose();
                return false;
        }
    }

    /* renamed from: c */
    public boolean m2596c(InterfaceC2153b interfaceC2153b) {
        switch (this.f6328e) {
            case 0:
                if (mo322a(interfaceC2153b)) {
                    interfaceC2153b.dispose();
                    break;
                }
                break;
            default:
                if (mo322a(interfaceC2153b)) {
                    interfaceC2153b.dispose();
                    break;
                }
                break;
        }
        return true;
    }

    /* renamed from: d */
    public int m2597d() {
        if (this.f6330g) {
            return 0;
        }
        synchronized (this) {
            if (this.f6330g) {
                return 0;
            }
            C1777i<InterfaceC2153b> c1777i = this.f6329f;
            return c1777i != null ? c1777i.f5064b : 0;
        }
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        ArrayList arrayList = null;
        switch (this.f6328e) {
            case 0:
                if (this.f6330g) {
                    return;
                }
                synchronized (this) {
                    if (!this.f6330g) {
                        this.f6330g = true;
                        C1777i<InterfaceC2153b> c1777i = this.f6329f;
                        this.f6329f = null;
                        if (c1777i != null) {
                            for (InterfaceC2153b interfaceC2153b : c1777i.f5066d) {
                                if (interfaceC2153b instanceof InterfaceC2153b) {
                                    try {
                                        interfaceC2153b.dispose();
                                    } catch (Throwable th) {
                                        C2074b.m2470J(th);
                                        if (arrayList == null) {
                                            arrayList = new ArrayList();
                                        }
                                        arrayList.add(th);
                                    }
                                }
                            }
                            if (arrayList != null) {
                                if (arrayList.size() != 1) {
                                    throw new C0009a(arrayList);
                                }
                                throw C1774f.m1961d((Throwable) arrayList.get(0));
                            }
                        }
                    }
                }
                return;
            default:
                if (this.f6330g) {
                    return;
                }
                synchronized (this) {
                    if (!this.f6330g) {
                        this.f6330g = true;
                        List list = (List) this.f6329f;
                        this.f6329f = null;
                        if (list != null) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                try {
                                    ((InterfaceC2153b) it.next()).dispose();
                                } catch (Throwable th2) {
                                    C2074b.m2470J(th2);
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    arrayList.add(th2);
                                }
                            }
                            if (arrayList != null) {
                                if (arrayList.size() != 1) {
                                    throw new C0009a(arrayList);
                                }
                                throw C1774f.m1961d((Throwable) arrayList.get(0));
                            }
                        }
                    }
                }
                return;
        }
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        switch (this.f6328e) {
        }
        return this.f6330g;
    }
}
