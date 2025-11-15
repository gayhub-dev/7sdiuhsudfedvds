package p183x;

import java.io.File;
import java.util.List;
import p010b0.InterfaceC0426l;
import p162u.EnumC1957a;
import p162u.InterfaceC1964h;
import p169v.InterfaceC1986b;
import p183x.InterfaceC2043d;

/* compiled from: DataCacheGenerator.java */
/* renamed from: x.a */
/* loaded from: classes.dex */
public class C2040a implements InterfaceC2043d, InterfaceC1986b.a<Object> {

    /* renamed from: e */
    public List<InterfaceC1964h> f5957e;

    /* renamed from: f */
    public final C2044e<?> f5958f;

    /* renamed from: g */
    public final InterfaceC2043d.a f5959g;

    /* renamed from: h */
    public int f5960h;

    /* renamed from: i */
    public InterfaceC1964h f5961i;

    /* renamed from: j */
    public List<InterfaceC0426l<File, ?>> f5962j;

    /* renamed from: k */
    public int f5963k;

    /* renamed from: l */
    public volatile InterfaceC0426l.a<?> f5964l;

    /* renamed from: m */
    public File f5965m;

    public C2040a(C2044e<?> c2044e, InterfaceC2043d.a aVar) {
        List<InterfaceC1964h> listM2404a = c2044e.m2404a();
        this.f5960h = -1;
        this.f5957e = listM2404a;
        this.f5958f = c2044e;
        this.f5959g = aVar;
    }

    @Override // p183x.InterfaceC2043d
    public void cancel() {
        InterfaceC0426l.a<?> aVar = this.f5964l;
        if (aVar != null) {
            aVar.f233c.cancel();
        }
    }

    @Override // p169v.InterfaceC1986b.a
    /* renamed from: d */
    public void mo140d(Exception exc) {
        this.f5959g.mo2401a(this.f5961i, exc, this.f5964l.f233c, EnumC1957a.DATA_DISK_CACHE);
    }

    @Override // p169v.InterfaceC1986b.a
    /* renamed from: e */
    public void mo141e(Object obj) {
        this.f5959g.mo2403c(this.f5961i, obj, this.f5964l.f233c, EnumC1957a.DATA_DISK_CACHE, this.f5961i);
    }

    @Override // p183x.InterfaceC2043d
    /* renamed from: f */
    public boolean mo2400f() {
        while (true) {
            List<InterfaceC0426l<File, ?>> list = this.f5962j;
            if (list != null) {
                if (this.f5963k < list.size()) {
                    this.f5964l = null;
                    boolean z6 = false;
                    while (!z6) {
                        if (!(this.f5963k < this.f5962j.size())) {
                            break;
                        }
                        List<InterfaceC0426l<File, ?>> list2 = this.f5962j;
                        int i7 = this.f5963k;
                        this.f5963k = i7 + 1;
                        InterfaceC0426l<File, ?> interfaceC0426l = list2.get(i7);
                        File file = this.f5965m;
                        C2044e<?> c2044e = this.f5958f;
                        this.f5964l = interfaceC0426l.mo117a(file, c2044e.f5975e, c2044e.f5976f, c2044e.f5979i);
                        if (this.f5964l != null && this.f5958f.m2410g(this.f5964l.f233c.mo123a())) {
                            this.f5964l.f233c.mo125c(this.f5958f.f5985o, this);
                            z6 = true;
                        }
                    }
                    return z6;
                }
            }
            int i8 = this.f5960h + 1;
            this.f5960h = i8;
            if (i8 >= this.f5957e.size()) {
                return false;
            }
            InterfaceC1964h interfaceC1964h = this.f5957e.get(this.f5960h);
            C2044e<?> c2044e2 = this.f5958f;
            File fileMo2566a = c2044e2.m2405b().mo2566a(new C2041b(interfaceC1964h, c2044e2.f5984n));
            this.f5965m = fileMo2566a;
            if (fileMo2566a != null) {
                this.f5961i = interfaceC1964h;
                this.f5962j = this.f5958f.f5973c.f5235a.m2029d(fileMo2566a);
                this.f5963k = 0;
            }
        }
    }

    public C2040a(List<InterfaceC1964h> list, C2044e<?> c2044e, InterfaceC2043d.a aVar) {
        this.f5960h = -1;
        this.f5957e = list;
        this.f5958f = c2044e;
        this.f5959g = aVar;
    }
}
