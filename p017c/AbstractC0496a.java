package p017c;

import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import java.lang.reflect.InvocationTargetException;

/* compiled from: VersionedParcel.java */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: c.a */
/* loaded from: classes.dex */
public abstract class AbstractC0496a {
    /* renamed from: c */
    public static Class m296c(Class<? extends InterfaceC0498c> cls) {
        return Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
    }

    /* renamed from: a */
    public abstract void mo297a();

    /* renamed from: b */
    public abstract AbstractC0496a mo298b();

    /* renamed from: d */
    public abstract byte[] mo299d();

    /* renamed from: e */
    public abstract boolean mo300e(int i7);

    /* renamed from: f */
    public abstract int mo301f();

    /* renamed from: g */
    public int m302g(int i7, int i8) {
        return !mo300e(i8) ? i7 : mo301f();
    }

    /* renamed from: h */
    public abstract <T extends Parcelable> T mo303h();

    /* renamed from: i */
    public <T extends Parcelable> T m304i(T t6, int i7) {
        return !mo300e(i7) ? t6 : (T) mo303h();
    }

    /* renamed from: j */
    public abstract String mo305j();

    /* renamed from: k */
    public <T extends InterfaceC0498c> T m306k() {
        String strMo305j = mo305j();
        if (strMo305j == null) {
            return null;
        }
        try {
            return (T) Class.forName(strMo305j, true, AbstractC0496a.class.getClassLoader()).getDeclaredMethod("read", AbstractC0496a.class).invoke(null, mo298b());
        } catch (ClassNotFoundException e7) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e7);
        } catch (IllegalAccessException e8) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e8);
        } catch (NoSuchMethodException e9) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e9);
        } catch (InvocationTargetException e10) {
            if (e10.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e10.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e10);
        }
    }

    /* renamed from: l */
    public abstract void mo307l(int i7);

    /* renamed from: m */
    public abstract void mo308m(byte[] bArr);

    /* renamed from: n */
    public abstract void mo309n(int i7);

    /* renamed from: o */
    public abstract void mo310o(Parcelable parcelable);

    /* renamed from: p */
    public abstract void mo311p(String str);

    /* renamed from: q */
    public void m312q(InterfaceC0498c interfaceC0498c) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (interfaceC0498c == null) {
            mo311p(null);
            return;
        }
        try {
            mo311p(m296c(interfaceC0498c.getClass()).getName());
            AbstractC0496a abstractC0496aMo298b = mo298b();
            try {
                m296c(interfaceC0498c.getClass()).getDeclaredMethod("write", interfaceC0498c.getClass(), AbstractC0496a.class).invoke(null, interfaceC0498c, abstractC0496aMo298b);
                abstractC0496aMo298b.mo297a();
            } catch (ClassNotFoundException e7) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e7);
            } catch (IllegalAccessException e8) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e8);
            } catch (NoSuchMethodException e9) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e9);
            } catch (InvocationTargetException e10) {
                if (!(e10.getCause() instanceof RuntimeException)) {
                    throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e10);
                }
                throw ((RuntimeException) e10.getCause());
            }
        } catch (ClassNotFoundException e11) {
            throw new RuntimeException(interfaceC0498c.getClass().getSimpleName() + " does not have a Parcelizer", e11);
        }
    }
}
