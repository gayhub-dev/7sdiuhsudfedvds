package p010b0;

import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import p162u.C1966j;
import p162u.InterfaceC1964h;
import p169v.InterfaceC1986b;

/* compiled from: ModelLoader.java */
/* renamed from: b0.l */
/* loaded from: classes.dex */
public interface InterfaceC0426l<Model, Data> {

    /* compiled from: ModelLoader.java */
    /* renamed from: b0.l$a */
    public static class a<Data> {

        /* renamed from: a */
        public final InterfaceC1964h f231a;

        /* renamed from: b */
        public final List<InterfaceC1964h> f232b;

        /* renamed from: c */
        public final InterfaceC1986b<Data> f233c;

        public a(InterfaceC1964h interfaceC1964h, InterfaceC1986b<Data> interfaceC1986b) {
            List<InterfaceC1964h> listEmptyList = Collections.emptyList();
            Objects.requireNonNull(interfaceC1964h, "Argument must not be null");
            this.f231a = interfaceC1964h;
            Objects.requireNonNull(listEmptyList, "Argument must not be null");
            this.f232b = listEmptyList;
            Objects.requireNonNull(interfaceC1986b, "Argument must not be null");
            this.f233c = interfaceC1986b;
        }
    }

    @Nullable
    /* renamed from: a */
    a<Data> mo117a(Model model, int i7, int i8, C1966j c1966j);

    /* renamed from: b */
    boolean mo118b(Model model);
}
