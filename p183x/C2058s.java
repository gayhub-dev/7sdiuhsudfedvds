package p183x;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p010b0.C0428n;
import p010b0.InterfaceC0426l;
import p100m0.C1454b;
import p141r.C1812g;
import p142r0.C1822g;
import p162u.EnumC1957a;
import p162u.InterfaceC1964h;
import p162u.InterfaceC1969m;
import p169v.InterfaceC1986b;
import p183x.InterfaceC2043d;

/* compiled from: ResourceCacheGenerator.java */
/* renamed from: x.s */
/* loaded from: classes.dex */
public class C2058s implements InterfaceC2043d, InterfaceC1986b.a<Object> {

    /* renamed from: e */
    public final InterfaceC2043d.a f6123e;

    /* renamed from: f */
    public final C2044e<?> f6124f;

    /* renamed from: g */
    public int f6125g = 0;

    /* renamed from: h */
    public int f6126h = -1;

    /* renamed from: i */
    public InterfaceC1964h f6127i;

    /* renamed from: j */
    public List<InterfaceC0426l<File, ?>> f6128j;

    /* renamed from: k */
    public int f6129k;

    /* renamed from: l */
    public volatile InterfaceC0426l.a<?> f6130l;

    /* renamed from: m */
    public File f6131m;

    /* renamed from: n */
    public C2059t f6132n;

    public C2058s(C2044e<?> c2044e, InterfaceC2043d.a aVar) {
        this.f6124f = c2044e;
        this.f6123e = aVar;
    }

    @Override // p183x.InterfaceC2043d
    public void cancel() {
        InterfaceC0426l.a<?> aVar = this.f6130l;
        if (aVar != null) {
            aVar.f233c.cancel();
        }
    }

    @Override // p169v.InterfaceC1986b.a
    /* renamed from: d */
    public void mo140d(Exception exc) {
        this.f6123e.mo2401a(this.f6132n, exc, this.f6130l.f233c, EnumC1957a.RESOURCE_DISK_CACHE);
    }

    @Override // p169v.InterfaceC1986b.a
    /* renamed from: e */
    public void mo141e(Object obj) {
        this.f6123e.mo2403c(this.f6127i, obj, this.f6130l.f233c, EnumC1957a.RESOURCE_DISK_CACHE, this.f6132n);
    }

    @Override // p183x.InterfaceC2043d
    /* renamed from: f */
    public boolean mo2400f() {
        List list;
        List<Class<?>> listM145c;
        List<InterfaceC1964h> listM2404a = this.f6124f.m2404a();
        if (listM2404a.isEmpty()) {
            return false;
        }
        C2044e<?> c2044e = this.f6124f;
        C1812g c1812g = c2044e.f5973c.f5235a;
        Class<?> cls = c2044e.f5974d.getClass();
        Class<?> cls2 = c2044e.f5977g;
        Class<?> cls3 = c2044e.f5981k;
        C1454b c1454b = c1812g.f5253h;
        C1822g andSet = c1454b.f4216b.getAndSet(null);
        if (andSet == null) {
            andSet = new C1822g(cls, cls2);
        } else {
            andSet.f5297a = cls;
            andSet.f5298b = cls2;
            andSet.f5299c = null;
        }
        synchronized (c1454b.f4215a) {
            list = (List) c1454b.f4215a.get(andSet);
        }
        c1454b.f4216b.set(andSet);
        List list2 = list;
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            C0428n c0428n = c1812g.f5246a;
            synchronized (c0428n) {
                listM145c = c0428n.f234a.m145c(cls);
            }
            Iterator it = ((ArrayList) listM145c).iterator();
            while (it.hasNext()) {
                Iterator it2 = ((ArrayList) c1812g.f5248c.m1639a((Class) it.next(), cls2)).iterator();
                while (it2.hasNext()) {
                    Class cls4 = (Class) it2.next();
                    if (!((ArrayList) c1812g.f5251f.m1379a(cls4, cls3)).isEmpty() && !arrayList.contains(cls4)) {
                        arrayList.add(cls4);
                    }
                }
            }
            C1454b c1454b2 = c1812g.f5253h;
            List listUnmodifiableList = Collections.unmodifiableList(arrayList);
            synchronized (c1454b2.f4215a) {
                c1454b2.f4215a.put(new C1822g(cls, cls2), listUnmodifiableList);
            }
            list2 = arrayList;
        }
        while (true) {
            List<InterfaceC0426l<File, ?>> list3 = this.f6128j;
            if (list3 != null) {
                if (this.f6129k < list3.size()) {
                    this.f6130l = null;
                    boolean z6 = false;
                    while (!z6) {
                        if (!(this.f6129k < this.f6128j.size())) {
                            break;
                        }
                        List<InterfaceC0426l<File, ?>> list4 = this.f6128j;
                        int i7 = this.f6129k;
                        this.f6129k = i7 + 1;
                        InterfaceC0426l<File, ?> interfaceC0426l = list4.get(i7);
                        File file = this.f6131m;
                        C2044e<?> c2044e2 = this.f6124f;
                        this.f6130l = interfaceC0426l.mo117a(file, c2044e2.f5975e, c2044e2.f5976f, c2044e2.f5979i);
                        if (this.f6130l != null && this.f6124f.m2410g(this.f6130l.f233c.mo123a())) {
                            this.f6130l.f233c.mo125c(this.f6124f.f5985o, this);
                            z6 = true;
                        }
                    }
                    return z6;
                }
            }
            int i8 = this.f6126h + 1;
            this.f6126h = i8;
            if (i8 >= list2.size()) {
                int i9 = this.f6125g + 1;
                this.f6125g = i9;
                if (i9 >= listM2404a.size()) {
                    return false;
                }
                this.f6126h = 0;
            }
            InterfaceC1964h interfaceC1964h = listM2404a.get(this.f6125g);
            Class cls5 = (Class) list2.get(this.f6126h);
            InterfaceC1969m<Z> interfaceC1969mM2409f = this.f6124f.m2409f(cls5);
            C2044e<?> c2044e3 = this.f6124f;
            this.f6132n = new C2059t(interfaceC1964h, c2044e3.f5984n, c2044e3.f5975e, c2044e3.f5976f, interfaceC1969mM2409f, cls5, c2044e3.f5979i);
            File fileMo2566a = c2044e3.m2405b().mo2566a(this.f6132n);
            this.f6131m = fileMo2566a;
            if (fileMo2566a != null) {
                this.f6127i = interfaceC1964h;
                this.f6128j = this.f6124f.f5973c.f5235a.m2029d(fileMo2566a);
                this.f6129k = 0;
            }
        }
    }
}
