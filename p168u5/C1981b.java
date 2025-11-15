package p168u5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import p009b.C0413b;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: AggregateLifeCycle.java */
/* renamed from: u5.b */
/* loaded from: classes.dex */
public class C1981b extends AbstractC1980a implements InterfaceC1983d {

    /* renamed from: g */
    public static final InterfaceC2016c f5792g;

    /* renamed from: e */
    public final List<a> f5793e = new CopyOnWriteArrayList();

    /* renamed from: f */
    public boolean f5794f = false;

    /* compiled from: AggregateLifeCycle.java */
    /* renamed from: u5.b$a */
    public class a {

        /* renamed from: a */
        public final Object f5795a;

        /* renamed from: b */
        public volatile boolean f5796b = true;

        public a(C1981b c1981b, Object obj) {
            this.f5795a = obj;
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("{");
            sbM112a.append(this.f5795a);
            sbM112a.append(",");
            sbM112a.append(this.f5796b);
            sbM112a.append("}");
            return sbM112a.toString();
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f5792g = C2015b.m2349a(C1981b.class.getName());
    }

    /* renamed from: G */
    public boolean mo1798G(Object obj) {
        return m2306H(obj, ((obj instanceof InterfaceC1984e) && ((InterfaceC1984e) obj).isStarted()) ? false : true);
    }

    /* renamed from: H */
    public boolean m2306H(Object obj, boolean z6) {
        boolean z7;
        Iterator<a> it = this.f5793e.iterator();
        while (true) {
            if (!it.hasNext()) {
                z7 = false;
                break;
            }
            if (it.next().f5795a == obj) {
                z7 = true;
                break;
            }
        }
        if (z7) {
            return false;
        }
        a aVar = new a(this, obj);
        aVar.f5796b = z6;
        this.f5793e.add(aVar);
        if (obj instanceof InterfaceC1984e) {
            InterfaceC1984e interfaceC1984e = (InterfaceC1984e) obj;
            if (z6 && this.f5794f) {
                try {
                    interfaceC1984e.start();
                } catch (Exception e7) {
                    throw new RuntimeException(e7);
                }
            }
        }
        return true;
    }

    /* renamed from: J */
    public <T> T m2307J(Class<T> cls) {
        for (a aVar : this.f5793e) {
            if (cls.isInstance(aVar.f5795a)) {
                return (T) aVar.f5795a;
            }
        }
        return null;
    }

    /* renamed from: K */
    public <T> List<T> m2308K(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        for (a aVar : this.f5793e) {
            if (cls.isInstance(aVar.f5795a)) {
                arrayList.add(aVar.f5795a);
            }
        }
        return arrayList;
    }

    /* renamed from: L */
    public boolean mo1799L(Object obj) {
        for (a aVar : this.f5793e) {
            if (aVar.f5795a == obj) {
                this.f5793e.remove(aVar);
                return true;
            }
        }
        return false;
    }

    public void destroy() {
        ArrayList arrayList = new ArrayList(this.f5793e);
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if ((aVar.f5795a instanceof InterfaceC1983d) && aVar.f5796b) {
                ((InterfaceC1983d) aVar.f5795a).destroy();
            }
        }
        this.f5793e.clear();
    }

    @Override // p168u5.AbstractC1980a
    public void doStart() {
        for (a aVar : this.f5793e) {
            if (aVar.f5796b) {
                Object obj = aVar.f5795a;
                if (obj instanceof InterfaceC1984e) {
                    InterfaceC1984e interfaceC1984e = (InterfaceC1984e) obj;
                    if (!interfaceC1984e.isRunning()) {
                        interfaceC1984e.start();
                    }
                }
            }
        }
        this.f5794f = true;
        super.doStart();
    }

    @Override // p168u5.AbstractC1980a
    public void doStop() {
        this.f5794f = false;
        super.doStop();
        ArrayList arrayList = new ArrayList(this.f5793e);
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.f5796b) {
                Object obj = aVar.f5795a;
                if (obj instanceof InterfaceC1984e) {
                    InterfaceC1984e interfaceC1984e = (InterfaceC1984e) obj;
                    if (interfaceC1984e.isRunning()) {
                        interfaceC1984e.stop();
                    }
                }
            }
        }
    }
}
