package p010b0;

import android.support.annotation.NonNull;
import p010b0.InterfaceC0426l;
import p134q0.C1757b;
import p141r.EnumC1811f;
import p162u.C1966j;
import p162u.EnumC1957a;
import p169v.InterfaceC1986b;

/* compiled from: UnitModelLoader.java */
/* renamed from: b0.s */
/* loaded from: classes.dex */
public class C0433s<Model> implements InterfaceC0426l<Model, Model> {

    /* compiled from: UnitModelLoader.java */
    /* renamed from: b0.s$a */
    public static class a<Model> implements InterfaceC0427m<Model, Model> {
        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<Model, Model> mo120b(C0430p c0430p) {
            return new C0433s();
        }
    }

    /* compiled from: UnitModelLoader.java */
    /* renamed from: b0.s$b */
    public static class b<Model> implements InterfaceC1986b<Model> {

        /* renamed from: e */
        public final Model f260e;

        public b(Model model) {
            this.f260e = model;
        }

        @Override // p169v.InterfaceC1986b
        @NonNull
        /* renamed from: a */
        public Class<Model> mo123a() {
            return (Class<Model>) this.f260e.getClass();
        }

        @Override // p169v.InterfaceC1986b
        /* renamed from: b */
        public void mo124b() {
        }

        @Override // p169v.InterfaceC1986b
        /* renamed from: c */
        public void mo125c(EnumC1811f enumC1811f, InterfaceC1986b.a<? super Model> aVar) {
            aVar.mo141e(this.f260e);
        }

        @Override // p169v.InterfaceC1986b
        public void cancel() {
        }

        @Override // p169v.InterfaceC1986b
        @NonNull
        public EnumC1957a getDataSource() {
            return EnumC1957a.LOCAL;
        }
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a<Model> mo117a(Model model, int i7, int i8, C1966j c1966j) {
        return new InterfaceC0426l.a<>(new C1757b(model), new b(model));
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public boolean mo118b(Model model) {
        return true;
    }
}
