package p183x;

import p162u.EnumC1957a;
import p162u.EnumC1959c;

/* compiled from: DiskCacheStrategy.java */
/* renamed from: x.h */
/* loaded from: classes.dex */
public abstract class AbstractC2047h {

    /* renamed from: a */
    public static final AbstractC2047h f6043a = new a();

    /* renamed from: b */
    public static final AbstractC2047h f6044b = new b();

    /* renamed from: c */
    public static final AbstractC2047h f6045c = new c();

    /* compiled from: DiskCacheStrategy.java */
    /* renamed from: x.h$a */
    public static class a extends AbstractC2047h {
        @Override // p183x.AbstractC2047h
        /* renamed from: a */
        public boolean mo2427a() {
            return false;
        }

        @Override // p183x.AbstractC2047h
        /* renamed from: b */
        public boolean mo2428b() {
            return false;
        }

        @Override // p183x.AbstractC2047h
        /* renamed from: c */
        public boolean mo2429c(EnumC1957a enumC1957a) {
            return false;
        }

        @Override // p183x.AbstractC2047h
        /* renamed from: d */
        public boolean mo2430d(boolean z6, EnumC1957a enumC1957a, EnumC1959c enumC1959c) {
            return false;
        }
    }

    /* compiled from: DiskCacheStrategy.java */
    /* renamed from: x.h$b */
    public static class b extends AbstractC2047h {
        @Override // p183x.AbstractC2047h
        /* renamed from: a */
        public boolean mo2427a() {
            return true;
        }

        @Override // p183x.AbstractC2047h
        /* renamed from: b */
        public boolean mo2428b() {
            return false;
        }

        @Override // p183x.AbstractC2047h
        /* renamed from: c */
        public boolean mo2429c(EnumC1957a enumC1957a) {
            return (enumC1957a == EnumC1957a.DATA_DISK_CACHE || enumC1957a == EnumC1957a.MEMORY_CACHE) ? false : true;
        }

        @Override // p183x.AbstractC2047h
        /* renamed from: d */
        public boolean mo2430d(boolean z6, EnumC1957a enumC1957a, EnumC1959c enumC1959c) {
            return false;
        }
    }

    /* compiled from: DiskCacheStrategy.java */
    /* renamed from: x.h$c */
    public static class c extends AbstractC2047h {
        @Override // p183x.AbstractC2047h
        /* renamed from: a */
        public boolean mo2427a() {
            return true;
        }

        @Override // p183x.AbstractC2047h
        /* renamed from: b */
        public boolean mo2428b() {
            return true;
        }

        @Override // p183x.AbstractC2047h
        /* renamed from: c */
        public boolean mo2429c(EnumC1957a enumC1957a) {
            return enumC1957a == EnumC1957a.REMOTE;
        }

        @Override // p183x.AbstractC2047h
        /* renamed from: d */
        public boolean mo2430d(boolean z6, EnumC1957a enumC1957a, EnumC1959c enumC1959c) {
            return ((z6 && enumC1957a == EnumC1957a.DATA_DISK_CACHE) || enumC1957a == EnumC1957a.LOCAL) && enumC1959c == EnumC1959c.TRANSFORMED;
        }
    }

    /* renamed from: a */
    public abstract boolean mo2427a();

    /* renamed from: b */
    public abstract boolean mo2428b();

    /* renamed from: c */
    public abstract boolean mo2429c(EnumC1957a enumC1957a);

    /* renamed from: d */
    public abstract boolean mo2430d(boolean z6, EnumC1957a enumC1957a, EnumC1959c enumC1959c);
}
