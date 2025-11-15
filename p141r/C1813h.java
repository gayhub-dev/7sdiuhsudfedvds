package p141r;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import java.util.Map;
import java.util.Objects;
import p036e0.AbstractC0911j;
import p036e0.C0908g;
import p036e0.C0909h;
import p036e0.C0913l;
import p084k0.C1225k;
import p108n0.C1512c;
import p108n0.C1514e;
import p108n0.C1515f;
import p108n0.InterfaceC1510a;
import p116o0.C1573b;
import p116o0.InterfaceC1577f;
import p126p0.C1732a;
import p126p0.InterfaceC1734c;
import p142r0.C1823h;
import p149s0.C1860a;
import p183x.AbstractC2047h;
import p183x.C2048i;

/* compiled from: RequestBuilder.java */
/* renamed from: r.h */
/* loaded from: classes.dex */
public class C1813h<TranscodeType> implements Cloneable {

    /* renamed from: e */
    public final C1810e f5256e;

    /* renamed from: f */
    public final C1814i f5257f;

    /* renamed from: g */
    public final Class<TranscodeType> f5258g;

    /* renamed from: h */
    public final C1512c f5259h;

    /* renamed from: i */
    @NonNull
    public C1512c f5260i;

    /* renamed from: j */
    @NonNull
    public AbstractC1815j<?, ? super TranscodeType> f5261j;

    /* renamed from: k */
    @Nullable
    public Object f5262k;

    /* renamed from: l */
    public boolean f5263l;

    /* compiled from: RequestBuilder.java */
    /* renamed from: r.h$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f5264a;

        /* renamed from: b */
        public static final /* synthetic */ int[] f5265b;

        static {
            int[] iArr = new int[EnumC1811f.values().length];
            f5265b = iArr;
            try {
                iArr[3] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5265b[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5265b[1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5265b[0] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            f5264a = iArr2;
            try {
                iArr2[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f5264a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f5264a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f5264a[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f5264a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f5264a[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f5264a[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f5264a[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    static {
        new C1512c().m1689j(AbstractC2047h.f6044b).m1680N(EnumC1811f.LOW).m1684X(true);
    }

    public C1813h(ComponentCallbacks2C1808c componentCallbacks2C1808c, C1814i c1814i, Class<TranscodeType> cls) {
        this.f5257f = c1814i;
        this.f5256e = componentCallbacks2C1808c.f5215g;
        this.f5258g = cls;
        this.f5259h = c1814i.f5275i;
        C1810e c1810e = c1814i.f5267a.f5215g;
        AbstractC1815j abstractC1815j = c1810e.f5238d.get(cls);
        if (abstractC1815j == null) {
            for (Map.Entry<Class<?>, AbstractC1815j<?, ?>> entry : c1810e.f5238d.entrySet()) {
                if (entry.getKey().isAssignableFrom(cls)) {
                    abstractC1815j = (AbstractC1815j) entry.getValue();
                }
            }
        }
        this.f5261j = abstractC1815j == null ? C1810e.f5234g : abstractC1815j;
        this.f5260i = this.f5259h;
    }

    /* renamed from: b */
    public C1813h<TranscodeType> m2035b(@NonNull C1512c c1512c) {
        Objects.requireNonNull(c1512c, "Argument must not be null");
        C1512c c1512c2 = this.f5259h;
        C1512c c1512cClone = this.f5260i;
        if (c1512c2 == c1512cClone) {
            c1512cClone = c1512cClone.clone();
        }
        this.f5260i = c1512cClone.m1686b(c1512c);
        return this;
    }

    /* renamed from: d */
    public final InterfaceC1510a m2036d(InterfaceC1577f<TranscodeType> interfaceC1577f, @Nullable C1515f c1515f, AbstractC1815j<?, ? super TranscodeType> abstractC1815j, EnumC1811f enumC1811f, int i7, int i8) {
        return m2040q(interfaceC1577f, this.f5260i, null, abstractC1815j, enumC1811f, i7, i8);
    }

    /* renamed from: g */
    public C1813h<TranscodeType> clone() {
        try {
            C1813h<TranscodeType> c1813h = (C1813h) super.clone();
            c1813h.f5260i = c1813h.f5260i.clone();
            c1813h.f5261j = c1813h.f5261j.m2045b();
            return c1813h;
        } catch (CloneNotSupportedException e7) {
            throw new RuntimeException(e7);
        }
    }

    /* renamed from: j */
    public InterfaceC1577f<TranscodeType> m2038j(ImageView imageView) {
        C1573b c1573b;
        C1823h.m2057a();
        Objects.requireNonNull(imageView, "Argument must not be null");
        if (!C1512c.m1676k(this.f5260i.f4342e, 2048) && this.f5260i.f4355r && imageView.getScaleType() != null) {
            C1512c c1512c = this.f5260i;
            if (c1512c.f4361x) {
                this.f5260i = c1512c.clone();
            }
            switch (a.f5264a[imageView.getScaleType().ordinal()]) {
                case 1:
                    C1512c c1512c2 = this.f5260i;
                    Objects.requireNonNull(c1512c2);
                    c1512c2.m1690q(AbstractC0911j.f1641b, new C0908g());
                    break;
                case 2:
                    C1512c c1512c3 = this.f5260i;
                    Objects.requireNonNull(c1512c3);
                    c1512c3.m1690q(AbstractC0911j.f1643d, new C0909h()).f4341C = true;
                    break;
                case 3:
                case 4:
                case 5:
                    C1512c c1512c4 = this.f5260i;
                    Objects.requireNonNull(c1512c4);
                    c1512c4.m1690q(AbstractC0911j.f1640a, new C0913l()).f4341C = true;
                    break;
                case 6:
                    C1512c c1512c5 = this.f5260i;
                    Objects.requireNonNull(c1512c5);
                    c1512c5.m1690q(AbstractC0911j.f1643d, new C0909h()).f4341C = true;
                    break;
            }
        }
        C1810e c1810e = this.f5256e;
        Class<TranscodeType> cls = this.f5258g;
        Objects.requireNonNull(c1810e.f5236b);
        if (Bitmap.class.equals(cls)) {
            c1573b = new C1573b(imageView, 1);
        } else {
            if (!Drawable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException("Unhandled class: " + cls + ", try .as*(Class).transcode(ResourceTranscoder)");
            }
            c1573b = new C1573b(imageView, 0);
        }
        m2039k(c1573b);
        return c1573b;
    }

    /* renamed from: k */
    public <Y extends InterfaceC1577f<TranscodeType>> Y m2039k(@NonNull Y y6) {
        C1823h.m2057a();
        Objects.requireNonNull(y6, "Argument must not be null");
        if (!this.f5263l) {
            throw new IllegalArgumentException("You must call #load() before calling #into()");
        }
        C1512c c1512c = this.f5260i;
        c1512c.f4361x = true;
        InterfaceC1510a interfaceC1510aM2036d = m2036d(y6, null, this.f5261j, c1512c.f4345h, c1512c.f4352o, c1512c.f4351n);
        InterfaceC1510a interfaceC1510aMo1819h = y6.mo1819h();
        C1514e c1514e = (C1514e) interfaceC1510aM2036d;
        if (c1514e.m1699j(interfaceC1510aMo1819h)) {
            Objects.requireNonNull(interfaceC1510aMo1819h, "Argument must not be null");
            if (interfaceC1510aMo1819h.mo1673d() || interfaceC1510aMo1819h.isRunning()) {
                c1514e.recycle();
                if (!interfaceC1510aMo1819h.isRunning()) {
                    interfaceC1510aMo1819h.mo1672c();
                }
                return y6;
            }
        }
        this.f5257f.m2041k(y6);
        y6.mo1817f(interfaceC1510aM2036d);
        C1814i c1814i = this.f5257f;
        c1814i.f5271e.f2756a.add(y6);
        C1225k c1225k = c1814i.f5269c;
        c1225k.f2753a.add(interfaceC1510aM2036d);
        if (c1225k.f2755c) {
            c1225k.f2754b.add(interfaceC1510aM2036d);
        } else {
            c1514e.mo1672c();
        }
        return y6;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: q */
    public final InterfaceC1510a m2040q(InterfaceC1577f<TranscodeType> interfaceC1577f, C1512c c1512c, C1515f c1515f, AbstractC1815j<?, ? super TranscodeType> abstractC1815j, EnumC1811f enumC1811f, int i7, int i8) {
        c1512c.f4361x = true;
        C1810e c1810e = this.f5256e;
        Object obj = this.f5262k;
        Class<TranscodeType> cls = this.f5258g;
        C2048i c2048i = c1810e.f5239e;
        Objects.requireNonNull(abstractC1815j);
        InterfaceC1734c interfaceC1734c = C1732a.f4919b;
        C1514e c1514e = (C1514e) ((C1860a.c) C1514e.f4364A).acquire();
        if (c1514e == null) {
            c1514e = new C1514e();
        }
        c1514e.f4368g = c1810e;
        c1514e.f4369h = obj;
        c1514e.f4370i = cls;
        c1514e.f4371j = c1512c;
        c1514e.f4372k = i7;
        c1514e.f4373l = i8;
        c1514e.f4374m = enumC1811f;
        c1514e.f4375n = interfaceC1577f;
        c1514e.f4376o = null;
        c1514e.f4367f = c1515f;
        c1514e.f4377p = c2048i;
        c1514e.f4378q = interfaceC1734c;
        c1514e.f4382u = 1;
        return c1514e;
    }
}
