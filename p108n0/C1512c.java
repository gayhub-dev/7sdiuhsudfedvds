package p108n0;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import p036e0.AbstractC0911j;
import p036e0.C0903b;
import p036e0.C0912k;
import p068i0.C1137c;
import p068i0.C1139e;
import p134q0.C1756a;
import p141r.EnumC1811f;
import p142r0.C1823h;
import p162u.C1965i;
import p162u.C1966j;
import p162u.InterfaceC1964h;
import p162u.InterfaceC1969m;
import p183x.AbstractC2047h;

/* compiled from: RequestOptions.java */
/* renamed from: n0.c */
/* loaded from: classes.dex */
public class C1512c implements Cloneable {

    /* renamed from: A */
    public boolean f4339A;

    /* renamed from: B */
    public boolean f4340B;

    /* renamed from: e */
    public int f4342e;

    /* renamed from: i */
    @Nullable
    public Drawable f4346i;

    /* renamed from: j */
    public int f4347j;

    /* renamed from: k */
    @Nullable
    public Drawable f4348k;

    /* renamed from: l */
    public int f4349l;

    /* renamed from: q */
    public boolean f4354q;

    /* renamed from: s */
    @Nullable
    public Drawable f4356s;

    /* renamed from: t */
    public int f4357t;

    /* renamed from: x */
    public boolean f4361x;

    /* renamed from: y */
    @Nullable
    public Resources.Theme f4362y;

    /* renamed from: z */
    public boolean f4363z;

    /* renamed from: f */
    public float f4343f = 1.0f;

    /* renamed from: g */
    @NonNull
    public AbstractC2047h f4344g = AbstractC2047h.f6045c;

    /* renamed from: h */
    @NonNull
    public EnumC1811f f4345h = EnumC1811f.NORMAL;

    /* renamed from: m */
    public boolean f4350m = true;

    /* renamed from: n */
    public int f4351n = -1;

    /* renamed from: o */
    public int f4352o = -1;

    /* renamed from: p */
    @NonNull
    public InterfaceC1964h f4353p = C1756a.f4997b;

    /* renamed from: r */
    public boolean f4355r = true;

    /* renamed from: u */
    @NonNull
    public C1966j f4358u = new C1966j();

    /* renamed from: v */
    @NonNull
    public Map<Class<?>, InterfaceC1969m<?>> f4359v = new HashMap();

    /* renamed from: w */
    @NonNull
    public Class<?> f4360w = Object.class;

    /* renamed from: C */
    public boolean f4341C = true;

    /* renamed from: k */
    public static boolean m1676k(int i7, int i8) {
        return (i7 & i8) != 0;
    }

    /* renamed from: I */
    public C1512c m1677I(InterfaceC1969m<Bitmap> interfaceC1969m) {
        if (this.f4363z) {
            return clone().m1677I(interfaceC1969m);
        }
        m1691x(Bitmap.class, interfaceC1969m);
        m1691x(BitmapDrawable.class, new C0903b(interfaceC1969m));
        m1691x(C1137c.class, new C1139e(interfaceC1969m));
        m1681R();
        return this;
    }

    /* renamed from: J */
    public C1512c m1678J(int i7, int i8) {
        if (this.f4363z) {
            return clone().m1678J(i7, i8);
        }
        this.f4352o = i7;
        this.f4351n = i8;
        this.f4342e |= 512;
        m1681R();
        return this;
    }

    /* renamed from: K */
    public C1512c m1679K(int i7) {
        if (this.f4363z) {
            return clone().m1679K(i7);
        }
        this.f4349l = i7;
        this.f4342e |= 128;
        m1681R();
        return this;
    }

    /* renamed from: N */
    public C1512c m1680N(@NonNull EnumC1811f enumC1811f) {
        if (this.f4363z) {
            return clone().m1680N(enumC1811f);
        }
        Objects.requireNonNull(enumC1811f, "Argument must not be null");
        this.f4345h = enumC1811f;
        this.f4342e |= 8;
        m1681R();
        return this;
    }

    /* renamed from: R */
    public final C1512c m1681R() {
        if (this.f4361x) {
            throw new IllegalStateException("You cannot modify locked RequestOptions, consider clone()");
        }
        return this;
    }

    /* renamed from: S */
    public <T> C1512c m1682S(@NonNull C1965i<T> c1965i, @NonNull T t6) {
        if (this.f4363z) {
            return clone().m1682S(c1965i, t6);
        }
        Objects.requireNonNull(c1965i, "Argument must not be null");
        Objects.requireNonNull(t6, "Argument must not be null");
        this.f4358u.f5742b.put(c1965i, t6);
        m1681R();
        return this;
    }

    /* renamed from: U */
    public C1512c m1683U(@NonNull InterfaceC1964h interfaceC1964h) {
        if (this.f4363z) {
            return clone().m1683U(interfaceC1964h);
        }
        Objects.requireNonNull(interfaceC1964h, "Argument must not be null");
        this.f4353p = interfaceC1964h;
        this.f4342e |= 1024;
        m1681R();
        return this;
    }

    /* renamed from: X */
    public C1512c m1684X(boolean z6) {
        if (this.f4363z) {
            return clone().m1684X(true);
        }
        this.f4350m = !z6;
        this.f4342e |= 256;
        m1681R();
        return this;
    }

    /* renamed from: Y */
    public C1512c m1685Y(@NonNull InterfaceC1969m<Bitmap> interfaceC1969m) {
        if (this.f4363z) {
            return clone().m1685Y(interfaceC1969m);
        }
        m1677I(interfaceC1969m);
        this.f4354q = true;
        this.f4342e |= 131072;
        m1681R();
        return this;
    }

    /* renamed from: b */
    public C1512c m1686b(C1512c c1512c) {
        if (this.f4363z) {
            return clone().m1686b(c1512c);
        }
        if (m1676k(c1512c.f4342e, 2)) {
            this.f4343f = c1512c.f4343f;
        }
        if (m1676k(c1512c.f4342e, 262144)) {
            this.f4339A = c1512c.f4339A;
        }
        if (m1676k(c1512c.f4342e, 4)) {
            this.f4344g = c1512c.f4344g;
        }
        if (m1676k(c1512c.f4342e, 8)) {
            this.f4345h = c1512c.f4345h;
        }
        if (m1676k(c1512c.f4342e, 16)) {
            this.f4346i = c1512c.f4346i;
        }
        if (m1676k(c1512c.f4342e, 32)) {
            this.f4347j = c1512c.f4347j;
        }
        if (m1676k(c1512c.f4342e, 64)) {
            this.f4348k = c1512c.f4348k;
        }
        if (m1676k(c1512c.f4342e, 128)) {
            this.f4349l = c1512c.f4349l;
        }
        if (m1676k(c1512c.f4342e, 256)) {
            this.f4350m = c1512c.f4350m;
        }
        if (m1676k(c1512c.f4342e, 512)) {
            this.f4352o = c1512c.f4352o;
            this.f4351n = c1512c.f4351n;
        }
        if (m1676k(c1512c.f4342e, 1024)) {
            this.f4353p = c1512c.f4353p;
        }
        if (m1676k(c1512c.f4342e, 4096)) {
            this.f4360w = c1512c.f4360w;
        }
        if (m1676k(c1512c.f4342e, 8192)) {
            this.f4356s = c1512c.f4356s;
        }
        if (m1676k(c1512c.f4342e, 16384)) {
            this.f4357t = c1512c.f4357t;
        }
        if (m1676k(c1512c.f4342e, 32768)) {
            this.f4362y = c1512c.f4362y;
        }
        if (m1676k(c1512c.f4342e, 65536)) {
            this.f4355r = c1512c.f4355r;
        }
        if (m1676k(c1512c.f4342e, 131072)) {
            this.f4354q = c1512c.f4354q;
        }
        if (m1676k(c1512c.f4342e, 2048)) {
            this.f4359v.putAll(c1512c.f4359v);
            this.f4341C = c1512c.f4341C;
        }
        if (m1676k(c1512c.f4342e, 524288)) {
            this.f4340B = c1512c.f4340B;
        }
        if (!this.f4355r) {
            this.f4359v.clear();
            int i7 = this.f4342e & (-2049);
            this.f4342e = i7;
            this.f4354q = false;
            this.f4342e = i7 & (-131073);
            this.f4341C = true;
        }
        this.f4342e |= c1512c.f4342e;
        this.f4358u.m2297d(c1512c.f4358u);
        m1681R();
        return this;
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public C1512c clone() {
        try {
            C1512c c1512c = (C1512c) super.clone();
            C1966j c1966j = new C1966j();
            c1512c.f4358u = c1966j;
            c1966j.m2297d(this.f4358u);
            HashMap map = new HashMap();
            c1512c.f4359v = map;
            map.putAll(this.f4359v);
            c1512c.f4361x = false;
            c1512c.f4363z = false;
            return c1512c;
        } catch (CloneNotSupportedException e7) {
            throw new RuntimeException(e7);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C1512c)) {
            return false;
        }
        C1512c c1512c = (C1512c) obj;
        return Float.compare(c1512c.f4343f, this.f4343f) == 0 && this.f4347j == c1512c.f4347j && C1823h.m2058b(this.f4346i, c1512c.f4346i) && this.f4349l == c1512c.f4349l && C1823h.m2058b(this.f4348k, c1512c.f4348k) && this.f4357t == c1512c.f4357t && C1823h.m2058b(this.f4356s, c1512c.f4356s) && this.f4350m == c1512c.f4350m && this.f4351n == c1512c.f4351n && this.f4352o == c1512c.f4352o && this.f4354q == c1512c.f4354q && this.f4355r == c1512c.f4355r && this.f4339A == c1512c.f4339A && this.f4340B == c1512c.f4340B && this.f4344g.equals(c1512c.f4344g) && this.f4345h == c1512c.f4345h && this.f4358u.equals(c1512c.f4358u) && this.f4359v.equals(c1512c.f4359v) && this.f4360w.equals(c1512c.f4360w) && C1823h.m2058b(this.f4353p, c1512c.f4353p) && C1823h.m2058b(this.f4362y, c1512c.f4362y);
    }

    /* renamed from: g */
    public C1512c m1688g(@NonNull Class<?> cls) {
        if (this.f4363z) {
            return clone().m1688g(cls);
        }
        Objects.requireNonNull(cls, "Argument must not be null");
        this.f4360w = cls;
        this.f4342e |= 4096;
        m1681R();
        return this;
    }

    public int hashCode() {
        float f7 = this.f4343f;
        char[] cArr = C1823h.f5300a;
        return C1823h.m2062f(this.f4362y, C1823h.m2062f(this.f4353p, C1823h.m2062f(this.f4360w, C1823h.m2062f(this.f4359v, C1823h.m2062f(this.f4358u, C1823h.m2062f(this.f4345h, C1823h.m2062f(this.f4344g, (((((((((((((C1823h.m2062f(this.f4356s, (C1823h.m2062f(this.f4348k, (C1823h.m2062f(this.f4346i, ((Float.floatToIntBits(f7) + 527) * 31) + this.f4347j) * 31) + this.f4349l) * 31) + this.f4357t) * 31) + (this.f4350m ? 1 : 0)) * 31) + this.f4351n) * 31) + this.f4352o) * 31) + (this.f4354q ? 1 : 0)) * 31) + (this.f4355r ? 1 : 0)) * 31) + (this.f4339A ? 1 : 0)) * 31) + (this.f4340B ? 1 : 0))))))));
    }

    /* renamed from: j */
    public C1512c m1689j(@NonNull AbstractC2047h abstractC2047h) {
        if (this.f4363z) {
            return clone().m1689j(abstractC2047h);
        }
        Objects.requireNonNull(abstractC2047h, "Argument must not be null");
        this.f4344g = abstractC2047h;
        this.f4342e |= 4;
        m1681R();
        return this;
    }

    /* renamed from: q */
    public final C1512c m1690q(AbstractC0911j abstractC0911j, InterfaceC1969m<Bitmap> interfaceC1969m) {
        if (this.f4363z) {
            return clone().m1690q(abstractC0911j, interfaceC1969m);
        }
        C1965i<AbstractC0911j> c1965i = C0912k.f1645g;
        Objects.requireNonNull(abstractC0911j, "Argument must not be null");
        m1682S(c1965i, abstractC0911j);
        return m1677I(interfaceC1969m);
    }

    /* renamed from: x */
    public <T> C1512c m1691x(Class<T> cls, InterfaceC1969m<T> interfaceC1969m) {
        if (this.f4363z) {
            return clone().m1691x(cls, interfaceC1969m);
        }
        Objects.requireNonNull(cls, "Argument must not be null");
        Objects.requireNonNull(interfaceC1969m, "Argument must not be null");
        this.f4359v.put(cls, interfaceC1969m);
        int i7 = this.f4342e | 2048;
        this.f4342e = i7;
        this.f4355r = true;
        this.f4342e = i7 | 65536;
        this.f4341C = false;
        m1681R();
        return this;
    }
}
