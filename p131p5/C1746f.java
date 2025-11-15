package p131p5;

import java.io.IOException;
import p006a5.C0029o;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p113n5.C1553n;
import p113n5.C1555p;
import p113n5.InterfaceC1548i;
import p161t5.C1918j;
import p161t5.C1919k;
import p168u5.AbstractC1980a;

/* compiled from: HandlerCollection.java */
/* renamed from: p5.f */
/* loaded from: classes.dex */
public class C1746f extends AbstractC1742b {

    /* renamed from: j */
    public final boolean f4958j;

    /* renamed from: k */
    public volatile InterfaceC1548i[] f4959k;

    public C1746f() {
        this.f4958j = false;
    }

    @Override // p131p5.AbstractC1742b
    /* renamed from: M */
    public Object mo1891M(Object obj, Class cls) {
        InterfaceC1548i[] interfaceC1548iArr = this.f4959k;
        for (int i7 = 0; interfaceC1548iArr != null && i7 < interfaceC1548iArr.length; i7++) {
            obj = m1892N(interfaceC1548iArr[i7], obj, cls);
        }
        return obj;
    }

    /* renamed from: P */
    public void mo1906P(InterfaceC1548i[] interfaceC1548iArr) {
        if (!this.f4958j && isStarted()) {
            throw new IllegalStateException(AbstractC1980a.STARTED);
        }
        InterfaceC1548i[] interfaceC1548iArr2 = this.f4959k == null ? null : (InterfaceC1548i[]) this.f4959k.clone();
        this.f4959k = interfaceC1548iArr;
        C1555p c1555p = this.f4928h;
        C1919k c1919k = new C1919k();
        for (int i7 = 0; interfaceC1548iArr != null && i7 < interfaceC1548iArr.length; i7++) {
            if (interfaceC1548iArr[i7].mo1771c() != c1555p) {
                interfaceC1548iArr[i7].mo1772h(c1555p);
            }
        }
        C1555p c1555p2 = this.f4928h;
        if (c1555p2 != null) {
            c1555p2.f4670k.m2315g(this, interfaceC1548iArr2, interfaceC1548iArr, "handler");
        }
        for (int i8 = 0; interfaceC1548iArr2 != null && i8 < interfaceC1548iArr2.length; i8++) {
            if (interfaceC1548iArr2[i8] != null) {
                try {
                    if (interfaceC1548iArr2[i8].isStarted()) {
                        interfaceC1548iArr2[i8].stop();
                    }
                } catch (Throwable th) {
                    c1919k.m2229a(th);
                }
            }
        }
        int iM2228x = C1918j.m2228x(c1919k.f5649e);
        if (iM2228x != 0) {
            if (iM2228x != 1) {
                throw new RuntimeException(c1919k);
            }
            Throwable th2 = (Throwable) C1918j.m2225j(c1919k.f5649e, 0);
            if (th2 instanceof Error) {
                throw ((Error) th2);
            }
            if (!(th2 instanceof RuntimeException)) {
                throw new RuntimeException(th2);
            }
            throw ((RuntimeException) th2);
        }
    }

    @Override // p131p5.AbstractC1741a, p168u5.C1981b, p168u5.InterfaceC1983d
    public void destroy() {
        if (!isStopped()) {
            throw new IllegalStateException("!STOPPED");
        }
        InterfaceC1548i[] interfaceC1548iArr = (InterfaceC1548i[]) C1918j.m2221I(mo1891M(null, null), InterfaceC1548i.class);
        mo1906P(null);
        for (InterfaceC1548i interfaceC1548i : interfaceC1548iArr) {
            interfaceC1548i.destroy();
        }
        super.destroy();
    }

    @Override // p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() throws Exception {
        C1919k c1919k = new C1919k();
        if (this.f4959k != null) {
            for (int i7 = 0; i7 < this.f4959k.length; i7++) {
                try {
                    this.f4959k[i7].start();
                } catch (Throwable th) {
                    c1919k.m2229a(th);
                }
            }
        }
        super.doStart();
        c1919k.m2230b();
    }

    @Override // p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStop() throws Exception {
        C1919k c1919k = new C1919k();
        try {
            super.doStop();
        } catch (Throwable th) {
            c1919k.m2229a(th);
        }
        if (this.f4959k != null) {
            int length = this.f4959k.length;
            while (true) {
                int i7 = length - 1;
                if (length <= 0) {
                    break;
                }
                try {
                    this.f4959k[i7].stop();
                } catch (Throwable th2) {
                    c1919k.m2229a(th2);
                }
                length = i7;
            }
        }
        c1919k.m2230b();
    }

    @Override // p131p5.AbstractC1741a, p113n5.InterfaceC1548i
    /* renamed from: h */
    public void mo1772h(C1555p c1555p) {
        if (isStarted()) {
            throw new IllegalStateException(AbstractC1980a.STARTED);
        }
        C1555p c1555p2 = this.f4928h;
        super.mo1772h(c1555p);
        InterfaceC1548i[] interfaceC1548iArr = this.f4959k;
        for (int i7 = 0; interfaceC1548iArr != null && i7 < interfaceC1548iArr.length; i7++) {
            interfaceC1548iArr[i7].mo1772h(c1555p);
        }
        if (c1555p == null || c1555p == c1555p2) {
            return;
        }
        c1555p.f4670k.m2315g(this, null, this.f4959k, "handler");
    }

    /* renamed from: u */
    public void mo1630u(String str, C1553n c1553n, InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) throws C0029o, IOException {
        if (this.f4959k == null || !isStarted()) {
            return;
        }
        C1919k c1919k = null;
        for (int i7 = 0; i7 < this.f4959k.length; i7++) {
            try {
                this.f4959k[i7].mo1630u(str, c1553n, interfaceC0458c, interfaceC0460e);
            } catch (IOException e7) {
                throw e7;
            } catch (RuntimeException e8) {
                throw e8;
            } catch (Exception e9) {
                if (c1919k == null) {
                    c1919k = new C1919k();
                }
                c1919k.m2229a(e9);
            }
        }
        if (c1919k != null) {
            if (C1918j.m2228x(c1919k.f5649e) != 1) {
                throw new C0029o(c1919k);
            }
            throw new C0029o((Throwable) C1918j.m2225j(c1919k.f5649e, 0));
        }
    }

    public C1746f(boolean z6) {
        this.f4958j = z6;
    }
}
