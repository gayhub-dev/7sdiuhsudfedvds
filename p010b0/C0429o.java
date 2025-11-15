package p010b0;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import p009b.C0413b;
import p010b0.InterfaceC0426l;
import p141r.EnumC1811f;
import p162u.C1966j;
import p162u.EnumC1957a;
import p162u.InterfaceC1964h;
import p169v.InterfaceC1986b;
import p183x.C2053n;

/* compiled from: MultiModelLoader.java */
/* renamed from: b0.o */
/* loaded from: classes.dex */
public class C0429o<Model, Data> implements InterfaceC0426l<Model, Data> {

    /* renamed from: a */
    public final List<InterfaceC0426l<Model, Data>> f238a;

    /* renamed from: b */
    public final Pools.Pool<List<Exception>> f239b;

    /* compiled from: MultiModelLoader.java */
    /* renamed from: b0.o$a */
    public static class a<Data> implements InterfaceC1986b<Data>, InterfaceC1986b.a<Data> {

        /* renamed from: e */
        public final List<InterfaceC1986b<Data>> f240e;

        /* renamed from: f */
        public final Pools.Pool<List<Exception>> f241f;

        /* renamed from: g */
        public int f242g;

        /* renamed from: h */
        public EnumC1811f f243h;

        /* renamed from: i */
        public InterfaceC1986b.a<? super Data> f244i;

        /* renamed from: j */
        @Nullable
        public List<Exception> f245j;

        public a(List<InterfaceC1986b<Data>> list, Pools.Pool<List<Exception>> pool) {
            this.f241f = pool;
            if (list.isEmpty()) {
                throw new IllegalArgumentException("Must not be empty.");
            }
            this.f240e = list;
            this.f242g = 0;
        }

        @Override // p169v.InterfaceC1986b
        @NonNull
        /* renamed from: a */
        public Class<Data> mo123a() {
            return this.f240e.get(0).mo123a();
        }

        @Override // p169v.InterfaceC1986b
        /* renamed from: b */
        public void mo124b() {
            List<Exception> list = this.f245j;
            if (list != null) {
                this.f241f.release(list);
            }
            this.f245j = null;
            Iterator<InterfaceC1986b<Data>> it = this.f240e.iterator();
            while (it.hasNext()) {
                it.next().mo124b();
            }
        }

        @Override // p169v.InterfaceC1986b
        /* renamed from: c */
        public void mo125c(EnumC1811f enumC1811f, InterfaceC1986b.a<? super Data> aVar) {
            this.f243h = enumC1811f;
            this.f244i = aVar;
            this.f245j = this.f241f.acquire();
            this.f240e.get(this.f242g).mo125c(enumC1811f, this);
        }

        @Override // p169v.InterfaceC1986b
        public void cancel() {
            Iterator<InterfaceC1986b<Data>> it = this.f240e.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
        }

        @Override // p169v.InterfaceC1986b.a
        /* renamed from: d */
        public void mo140d(Exception exc) {
            this.f245j.add(exc);
            m142f();
        }

        @Override // p169v.InterfaceC1986b.a
        /* renamed from: e */
        public void mo141e(Data data) {
            if (data != null) {
                this.f244i.mo141e(data);
            } else {
                m142f();
            }
        }

        /* renamed from: f */
        public final void m142f() {
            if (this.f242g >= this.f240e.size() - 1) {
                this.f244i.mo140d(new C2053n("Fetch failed", new ArrayList(this.f245j)));
            } else {
                this.f242g++;
                mo125c(this.f243h, this.f244i);
            }
        }

        @Override // p169v.InterfaceC1986b
        @NonNull
        public EnumC1957a getDataSource() {
            return this.f240e.get(0).getDataSource();
        }
    }

    public C0429o(List<InterfaceC0426l<Model, Data>> list, Pools.Pool<List<Exception>> pool) {
        this.f238a = list;
        this.f239b = pool;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a<Data> mo117a(Model model, int i7, int i8, C1966j c1966j) {
        InterfaceC0426l.a<Data> aVarMo117a;
        int size = this.f238a.size();
        ArrayList arrayList = new ArrayList(size);
        InterfaceC1964h interfaceC1964h = null;
        for (int i9 = 0; i9 < size; i9++) {
            InterfaceC0426l<Model, Data> interfaceC0426l = this.f238a.get(i9);
            if (interfaceC0426l.mo118b(model) && (aVarMo117a = interfaceC0426l.mo117a(model, i7, i8, c1966j)) != null) {
                interfaceC1964h = aVarMo117a.f231a;
                arrayList.add(aVarMo117a.f233c);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new InterfaceC0426l.a<>(interfaceC1964h, new a(arrayList, this.f239b));
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public boolean mo118b(Model model) {
        Iterator<InterfaceC0426l<Model, Data>> it = this.f238a.iterator();
        while (it.hasNext()) {
            if (it.next().mo118b(model)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("MultiModelLoader{modelLoaders=");
        List<InterfaceC0426l<Model, Data>> list = this.f238a;
        sbM112a.append(Arrays.toString(list.toArray(new InterfaceC0426l[list.size()])));
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
