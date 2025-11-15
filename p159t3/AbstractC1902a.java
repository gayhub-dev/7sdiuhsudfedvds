package p159t3;

import java.util.List;
import java.util.ListIterator;

/* compiled from: Filter.java */
/* renamed from: t3.a */
/* loaded from: classes.dex */
public abstract class AbstractC1902a {

    /* compiled from: Filter.java */
    /* renamed from: t3.a$a */
    public static class a {

        /* renamed from: a */
        public ListIterator<AbstractC1902a> f5608a;

        /* renamed from: b */
        public InterfaceC1906e f5609b;

        public a(List<AbstractC1902a> list, InterfaceC1906e interfaceC1906e) {
            this.f5608a = list.listIterator();
            this.f5609b = interfaceC1906e;
        }

        /* renamed from: a */
        public void m2201a(AbstractC1905d abstractC1905d) {
            if (this.f5608a.hasNext()) {
                this.f5608a.next().mo1969a(abstractC1905d, this);
            } else {
                this.f5609b.handle(abstractC1905d);
            }
        }
    }

    /* renamed from: a */
    public abstract void mo1969a(AbstractC1905d abstractC1905d, a aVar);
}
