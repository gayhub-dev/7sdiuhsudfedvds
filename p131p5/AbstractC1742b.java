package p131p5;

import org.eclipse.jetty.server.Handler;
import p113n5.InterfaceC1548i;
import p113n5.InterfaceC1549j;
import p161t5.C1918j;

/* compiled from: AbstractHandlerContainer.java */
/* renamed from: p5.b */
/* loaded from: classes.dex */
public abstract class AbstractC1742b extends AbstractC1741a implements InterfaceC1549j {
    /* renamed from: M */
    public Object mo1891M(Object obj, Class<?> cls) {
        return obj;
    }

    /* renamed from: N */
    public Object m1892N(InterfaceC1548i interfaceC1548i, Object obj, Class<InterfaceC1548i> cls) {
        if (interfaceC1548i == null) {
            return obj;
        }
        if (cls == null || cls.isAssignableFrom(interfaceC1548i.getClass())) {
            obj = C1918j.m2222b(obj, interfaceC1548i);
        }
        if (interfaceC1548i instanceof AbstractC1742b) {
            return ((AbstractC1742b) interfaceC1548i).mo1891M(obj, cls);
        }
        if (!(interfaceC1548i instanceof InterfaceC1549j)) {
            return obj;
        }
        InterfaceC1549j interfaceC1549j = (InterfaceC1549j) interfaceC1548i;
        InterfaceC1548i[] interfaceC1548iArrMo1774w = cls == null ? interfaceC1549j.mo1774w() : interfaceC1549j.mo1773l(cls);
        for (int i7 = 0; interfaceC1548iArrMo1774w != null && i7 < interfaceC1548iArrMo1774w.length; i7++) {
            obj = C1918j.m2222b(obj, interfaceC1548iArrMo1774w[i7]);
        }
        return obj;
    }

    /* renamed from: O */
    public <T extends InterfaceC1548i> T m1893O(Class<T> cls) {
        Object objMo1891M = mo1891M(null, cls);
        if (objMo1891M == null) {
            return null;
        }
        return (T) C1918j.m2225j(objMo1891M, 0);
    }

    @Override // p113n5.InterfaceC1549j
    /* renamed from: l */
    public Handler[] mo1773l(Class<?> cls) {
        return (InterfaceC1548i[]) C1918j.m2221I(mo1891M(null, cls), cls);
    }

    @Override // p113n5.InterfaceC1549j
    /* renamed from: w */
    public InterfaceC1548i[] mo1774w() {
        return (InterfaceC1548i[]) C1918j.m2221I(mo1891M(null, null), InterfaceC1548i.class);
    }
}
