package p147r5;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0462g;

/* compiled from: HashSessionIdManager.java */
/* renamed from: r5.d */
/* loaded from: classes.dex */
public class C1841d extends AbstractC1839b {

    /* renamed from: i */
    public final Map<String, Set<WeakReference<InterfaceC0462g>>> f5383i = new HashMap();

    /* renamed from: G */
    public void m2104G(InterfaceC0462g interfaceC0462g) {
        AbstractC1838a abstractC1838a = (AbstractC1838a) interfaceC0462g;
        String strM2105H = m2105H(abstractC1838a.getId());
        WeakReference<InterfaceC0462g> weakReference = new WeakReference<>(abstractC1838a);
        synchronized (this) {
            Set<WeakReference<InterfaceC0462g>> hashSet = this.f5383i.get(strM2105H);
            if (hashSet == null) {
                hashSet = new HashSet<>();
                this.f5383i.put(strM2105H, hashSet);
            }
            hashSet.add(weakReference);
        }
    }

    /* renamed from: H */
    public String m2105H(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf > 0 ? str.substring(0, iLastIndexOf) : str;
    }

    /* renamed from: J */
    public String m2106J(String str, InterfaceC0458c interfaceC0458c) {
        String str2 = interfaceC0458c == null ? null : (String) interfaceC0458c.mo26a("org.eclipse.jetty.ajp.JVMRoute");
        if (str2 == null) {
            return str;
        }
        return str + '.' + str2;
    }

    /* renamed from: K */
    public boolean m2107K(String str) {
        boolean zContainsKey;
        synchronized (this) {
            zContainsKey = this.f5383i.containsKey(str);
        }
        return zContainsKey;
    }

    @Override // p147r5.AbstractC1839b, p168u5.AbstractC1980a
    public void doStart() {
        super.doStart();
    }

    @Override // p147r5.AbstractC1839b, p168u5.AbstractC1980a
    public void doStop() {
        this.f5383i.clear();
    }
}
